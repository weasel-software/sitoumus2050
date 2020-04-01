package com.soikea.commitment2050.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import com.soikea.commitment2050.model.OrganizationLogo;
import com.soikea.commitment2050.model.OrganizationDetails;
import com.soikea.commitment2050.service.CommitmentMailService;
import com.soikea.commitment2050.service.Constants;
import com.soikea.commitment2050.service.UserService;
import com.soikea.commitment2050.service.OrganizationSpecialService;

import org.apache.commons.lang3.StringUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This is the REST API documentation for organizations. Here you will find information of how to invoke the REST API and what you can expect as return values.
 */
@ApplicationPath("/organizationapi")
@Component(immediate = true, service = Application.class)
public class OrganizationApi extends Application {
    public Set<Object> getSingletons() {
        return Collections.<Object>singleton(this);
    }
    private static Logger _logger = LoggerFactory.getLogger(OrganizationApi.class.getName());

    private static Gson gson = new GsonBuilder().create();

    @GET
    @Produces("text/plain")
    public String working() {
        return "It works!";
    }

    @Reference
    UserService userService;

    @Reference
    OrganizationSpecialService organizationSpecialService;

    @Reference
    CommitmentMailService commitmentMailService;


    @Context
    private HttpServletRequest httpServletRequest;

    /**
     * Add categories to organization
     * @param categoryIds list of category ids
     * @param organizationId organization id
     * @return Response
     */
    @POST
    @Path("/addcategories")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addOrganizationCategories(  @FormParam("categoryIds") List<Long> categoryIds, @FormParam("organizationId") long organizationId ) {

        _logger.debug("addOrganizationCategories: categoryIds: " + categoryIds + " organizationId: " + organizationId);


        try {

            User u = PortalUtil.getUser(httpServletRequest);
            Organization organization = userService.getOrganization(organizationId);

            _logger.debug("User " + u.toString() + " defaultUser:" + u.isDefaultUser());

            if ( u != null && StringUtils.isNotBlank(u.getEmailAddress()) && !u.isDefaultUser() ) {
                boolean allowAdd = userService.isAdmin(u);

                if ( !allowAdd ) {
                    //If not admin, check if user belongs to organization
                    List<Organization> userOrganizations = OrganizationLocalServiceUtil.getUserOrganizations(u.getUserId());
                    for ( Organization o : userOrganizations ) {
                        if (o.getOrganizationId() == organizationId) {
                            allowAdd = true;
                        }
                    }
                }
                if (allowAdd) {
                    long[] result = categoryIds.stream().mapToLong(l -> l).toArray();
                    userService.addCategoriesToOrganization(u.getUserId(), result, organization);
                    return Response.ok().build();
                }
            }
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (PortalException e) {
            _logger.error("Commitment creation failed!", e);
            return Response.serverError().build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }


    /**
     * Add user to organzation
     * @param userId user id
     * @param organizationId organization id
     * @return Response
     */
    @POST
    @Path("/adduser")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addUserToOrganization( @FormParam("userId") long userId, @FormParam("organizationId") long organizationId) {
        _logger.debug("adding userId: " + userId + " to organization: " + organizationId);

        try {
            User currentUser = PortalUtil.getUser(httpServletRequest);
            boolean allowAdd = isCurrentUserAllowedToModifyOrganization(organizationId, currentUser);

            if (allowAdd) {
                userService.addUserToOrganization(userId, organizationId);
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (PortalException e) {
            _logger.error("Adding user to organization failed", e);
        }
        return Response.serverError().build();
    }

    @POST
    @Path("/sendmsg/register-and-join")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response notifyUserRegisteredAndAddedToOrganization( @FormParam("email") String email, @FormParam("organizationid") long organizationId) {
        _logger.debug("Sending notification about registration to sitoumus2050 service, email: " + email + " to organization: " + organizationId);

        try {
            User currentUser = PortalUtil.getUser(httpServletRequest);
            boolean allowAdd = isCurrentUserAllowedToModifyOrganization(organizationId, currentUser);

            if (allowAdd) {
                commitmentMailService.sendEmail(email, Constants.EMAIL_USER_REGISTERED_AND_ADDED_TO_ORGANIZATION, PortalUtil.getLocale(httpServletRequest).toString());
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            _logger.error("Sending information about registration and organization add failed!", e);
        }
        return Response.serverError().build();
    }

    private boolean isCurrentUserAllowedToModifyOrganization(@FormParam("organizationId") long organizationId, User currentUser) throws PortalException {
        boolean allowAdd = isUserInOrganization(organizationId, currentUser);
        if (!allowAdd) {
            //if user does not belong to group check if user has created the organization (and thus can be added and add users to organization)
            allowAdd = isUserOrganizationCreator(organizationId, currentUser, allowAdd);
        }
        return allowAdd;
    }

    private boolean isUserOrganizationCreator(@FormParam("organizationId") long organizationId, User currentUser, boolean allowAdd) throws PortalException {
        Organization o = userService.getOrganization(organizationId);
        if (o.getUserId() == currentUser.getUserId()) {
            allowAdd = true;
        }
        return allowAdd;
    }

    private boolean isUserInOrganization(@FormParam("organizationId") long organizationId, User currentUser) throws PortalException {
        //check if user belongs to group
        return OrganizationLocalServiceUtil.hasUserOrganization(currentUser.getUserId(), organizationId);
    }


    /**
     * Update organization logo,
     * Organization id must be given
     * logo data should be base64 encoded
     *
     * @param logo  Organization's logo @see {@link OrganizationLogo}
     * @return Response
     */
    @POST
    @Path("/logo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateLogo(OrganizationLogo logo) {
        _logger.debug("update logo, organizationId: " + logo.getOrganizationId());
        try {
            Organization organization = userService.getOrganization(logo.getOrganizationId());
            if ( isOrgUpdateAllowed(organization.getName())) {
                boolean isLogo = StringUtils.isNotBlank( logo.getLogo() );
                byte[] logoBytes = new sun.misc.BASE64Decoder().decodeBuffer(logo.getLogo());
                //Liferay does not provide direct method to update logo? this is weird...
                OrganizationLocalServiceUtil.updateOrganization(
                        organization.getCompanyId(),
                        organization.getOrganizationId(),
                        organization.getParentOrganizationId(),
                        organization.getName(),
                        organization.getType(),
                        organization.getRegionId(),
                        organization.getCountryId(),
                        organization.getStatusId(),
                        organization.getComments(),
                        isLogo,
                        logoBytes,
                        false,
                        null);
                _logger.debug("Organization logo updated");
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

        } catch (PortalException e) {
            _logger.error("Updating logo failed", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (IOException e) {
            _logger.error("Updating logo failed", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    private boolean isOrgUpdateAllowed(String orgname) throws PortalException {
        User u = PortalUtil.getUser(httpServletRequest);
        _logger.debug("User" + u.getFullName());
        boolean allow = u != null && StringUtils.isNotBlank(u.getEmailAddress()) && !u.isDefaultUser();
        if ( allow && StringUtils.isNotBlank( orgname)) {
            //Check if user belongs to organization where commitment is created
            boolean orgmatch = false;
            for (Organization o : u.getOrganizations()) {
                if ( orgname.equals(o.getName())) {
                    orgmatch = true;
                    break;
                }
            }
            allow = allow && orgmatch;
        }
        return allow;
    }

    /**
     * Get organization logo url
     * @param organizationId    organization id
     * @return relative url of logo image if found, otherwise null
     */
    @GET
    @Path("/organizations/{organizationid}/logo")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getLogoUrl(@PathParam("organizationid") long organizationId) {

        try {
            String imgPath = userService.getOrganizationLogoUrl(organizationId);
            return Response.ok(imgPath).build();
        } catch (PortalException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * Get organization details
     *
     * @param organization organization name
     * @return organization details
     */
    @GET
    @Path("/organizationdetails")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrganizationDetailsByName(@QueryParam("organization") String organization) {
        return getOrganizationDetailsByNameInner(organization);
    }

    /**
     * Get organization details by organization name
     * @param organization organization name
     * @return organization details
     */
    private Response getOrganizationDetailsByNameInner(String organization) {
        try {
            if (StringUtils.isNotBlank(organization) ) {
                OrganizationDetails result = organizationSpecialService.getOrganizationDetailsByName(organization);
                if( result != null ) {
                    return Response.ok(gson.toJson(result)).build();
                } else {return Response.serverError().build();}
            } else {
                return Response.serverError().build();
            }
        } catch (PortalException e) {
            _logger.error("Organization details search failed, " + e.getMessage());
            return Response.serverError().build();
        }catch (SystemException e) {
            _logger.error("Organization details search failed, " + e.getMessage());
            return Response.serverError().build();
        }
    }
    /**
     * Get organization details
     *
     * @param organizationId organization name
     * @return organization details
     */
    @GET
    @Path("/organizationdetailsbyid")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrganizationDetailsById(@QueryParam("organizationId") String organizationId) {
        return getOrganizationDetailsByIdInner(organizationId);
    }

    /**
     * Get organization details by organization name
     * @param organizationId organization name
     * @return organization details
     */
    private Response getOrganizationDetailsByIdInner(String organizationId) {
        try {
            if (StringUtils.isNotBlank(organizationId) ) {
                OrganizationDetails result = organizationSpecialService.getOrganizationDetailsById(organizationId);
                if( result != null ) {
                    return Response.ok(gson.toJson(result)).build();
                } else {return Response.serverError().build();}
            } else {
                return Response.serverError().build();
            }
        } catch (PortalException e) {
            _logger.error("Organization details search failed, " + e.getMessage());
            return Response.serverError().build();
        }catch (SystemException e) {
            _logger.error("Organization details search failed, " + e.getMessage());
            return Response.serverError().build();
        }
    }
}
