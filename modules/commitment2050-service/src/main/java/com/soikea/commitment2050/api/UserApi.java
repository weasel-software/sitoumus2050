package com.soikea.commitment2050.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.soikea.commitment2050.model.UserPortrait;
import com.soikea.commitment2050.service.Constants;
import com.soikea.commitment2050.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

/**
 *  This is the REST API documentation for user information. Here you will find information of how to invoke the REST API and what you can expect as return values.
 */
@ApplicationPath("/userapi")
@Component(immediate = true, service = Application.class)
public class UserApi extends Application {
    public Set<Object> getSingletons() {
        return Collections.<Object>singleton(this);
    }
    private static Logger log = LoggerFactory.getLogger(UserApi.class.getName());
    private static Gson gson = new GsonBuilder().create();

    @GET
    @Produces("text/plain")
    public String working() {
        return "It works!";
    }

    @Reference
    UserService userService;

    @Context
    private HttpServletRequest httpServletRequest;

    @Context
    private HttpServletResponse httpServletResponse;

    /**
     * Update user portrait,
     * portrait data should be base64 encoded
     * if portrait data is null, then portrait is deleted
     * userid must be given
     * @param portrait {@link UserPortrait}
     * @return Response
     */
    @POST
    @Path("/portrait")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUserPortrait(UserPortrait portrait) {
        if (log.isDebugEnabled()) {
            log.debug("update portrait, userid: " + portrait.getUserId());
        }
        try {
            User loggedInUser = getUserFromHttpServletRequest();
            boolean allowed = isUserUpdateAllowed(portrait, loggedInUser);
            if (allowed) {
                if (StringUtils.isNotBlank(portrait.getPortrait())) {
                    byte[] portraitBytes = new sun.misc.BASE64Decoder().decodeBuffer(portrait.getPortrait());
                    UserLocalServiceUtil.updatePortrait(portrait.getUserId(), portraitBytes );
                    log.debug( "userid: " + portrait.getUserId() + " update portrait success! ");
                } else {
                    User portraitUser = UserLocalServiceUtil.getUser(portrait.getUserId());
                    UserLocalServiceUtil.deletePortrait(portrait.getUserId());
                    portraitUser.setPortraitId(0);
                    UserLocalServiceUtil.updateUser(portraitUser);
                    log.debug("userid: " + portrait.getUserId() + "delete portrait success! ");
                }
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
            return Response.ok().build();

        } catch (PortalException e) {
            log.error("Updating portrait failed", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (IOException e) {
            log.error("Updating portrait failed", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * Check if logged in user can update portrait.
     * @param portrait @see {@link UserPortrait}
     * @param loggedInUser user who tries to perform update operation
     * @return true if allowed, false if not
     */
    private boolean isUserUpdateAllowed(UserPortrait portrait, User loggedInUser) {
        return loggedInUser != null && loggedInUser.getUserId() == portrait.getUserId();
    }

    private User getUserFromHttpServletRequest() throws PortalException {
        return PortalUtil.getUser(httpServletRequest);
    }

    /**
     * Get user portrait url
     * @param userId    user id
     * @return relative url of portrait image
     */
    @GET
    @Path("/users/{userid}/portraiturl")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getPortraitUrl(@PathParam("userid") long userId) {
        try {
            String url = userService.getUserPortraitUrl(userId);
            return Response.ok(url).build();
        } catch (PortalException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/users/login")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerAndLogin( @FormParam("firstName") String firstName, @FormParam("lastName") String lastName, @FormParam("emailAddress")  String emailAddress) {
        try {
            User user = UserLocalServiceUtil.addUser(
                    Constants.ADMIN_USER_ID,
                    Constants.COMPANY_ID,
                    true,
                    null,
                    null,
                    true,
                    null,
                    emailAddress,
                    0,
                    "",
                    Constants.locale_fi,
                    firstName,
                    null,
                    lastName,
                    0,
                    0,
                    true,
                    1,
                    1,
                    1970,
                    "",
                    null,
                    null,
                    null,
                    null,
                    true,
                    null
            );
            UserLocalServiceUtil.updateAgreedToTermsOfUse(user.getUserId(), true);
            String redirect =
                    Constants.SMARTWAYS_REGISTRATION_SUCCESSFULL_PAGE + "?parameterAutoLoginLogin=" + user.getEmailAddress() +
                            "&parameterAutoLoginPassword=" + user.getPasswordUnencrypted()+"&" + Constants.AUTOLOGIN_PARAMETER_FROM_REGISTRATION+"=true";
            return Response.ok( gson.toJson(redirect)).build();
        } catch (PortalException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
