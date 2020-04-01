package com.soikea.commitment2050.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryProperty;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;
import com.liferay.journal.exception.NoSuchArticleException;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.search.facet.AssetEntriesFacet;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.faceted.searcher.FacetedSearcher;
import com.liferay.portal.kernel.search.facet.faceted.searcher.FacetedSearcherManager;
import com.liferay.portal.kernel.search.facet.faceted.searcher.FacetedSearcherManagerUtil;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.TermQueryImpl;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.soikea.commitment2050.model.*;
import com.soikea.commitment2050.service.*;
import com.soikea.commitment2050.configuration.SitoumusConfiguration;

import java.util.stream.Collectors;

import fi.weasel.commitment2050.commitmentanalysis.service.CommitmentLocalServiceUtil;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.CommitmentUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONString;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Modified;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

import static com.soikea.commitment2050.service.Constants.*;

/**
 * This is the REST API documentation for commitments. Here you will find information of how to invoke the REST API and what you can expect as return values.
 */
@ApplicationPath("/commitmentapi")
@Component(immediate = true, service = Application.class, configurationPid = "com.soikea.commitment2050.configuration.SitoumusConfiguration")
public class CommitmentApi extends Application {

    private volatile SitoumusConfiguration sitoumusConfiguration;

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        sitoumusConfiguration = ConfigurableUtil.createConfigurable(
        SitoumusConfiguration.class, properties);
    }

    public Set<Object> getSingletons() {
        return Collections.<Object>singleton(this);
    }
    private static Logger _logger = LoggerFactory.getLogger(CommitmentApi.class.getName());
    private static Gson gson = new GsonBuilder().create();

    @GET
    @Produces("text/plain")
    public String working() {
        return "It works!";
    }

    @Reference
    CommitmentService commitmentService;

    @Reference
    CommitmentMailService commitmentMailService;

    @Reference
    UserService userService;

    @Context
    private HttpServletRequest httpServletRequest;

    /**
     * Get the stored commitmentsPerCityMap.
     * Map is stored in CommitmentsPerCityMap model and updated nightly.
     */
    @GET
    @Path("/commitments-per-city")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommitmentsPerCity() {
        try{
            JSONObject response = CommitmentsPerCityMap.getCommitmentsPerCity();
            return Response.ok(response.toJSONString()).build();
        } catch(Exception e){
            _logger.error("ERROR! Getting commitments per city failed with error: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR! Server might not have pre-calculated the resource you are looking for.").build();
        }
    }

    /**
     * Add or update commitment.
     * If commitment has id then tries to find equivalent article and if found updates it (if allowed).
     * If commitment does not have id or id does not match in any existing article then creates new article.
     * @param commitment Commitment
     * @return commitment @see {@link Commitment}
     */
    @POST
    @Path("/commitments")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrUpdateCommitment(Commitment commitment) {
        boolean articleExists = false;
        try {
            articleExists = JournalArticleLocalServiceUtil.hasArticle(SITOUMUS2050_GROUP_ID, commitment.getId());
        } catch (Exception e) {
            //Nothing to do here, just extra cautious with Liferay...
        }
        if ( articleExists ) {
            return updateCommitment(commitment);
        } else {
            if ( StringUtils.isNotBlank( commitment.getId() )) {
                _logger.warn("addOrUpdateCommitment(), Commitment id given, but article not found. Commitment id will be ignored");
            }
            return addCommitment(commitment);
        }
    }

    private Response addCommitment(Commitment commitment) {
        _logger.debug("Creating new article");
        try {
            boolean allowSave = isCommitmentAddAllowed(commitment);
            if ( allowSave ) {
                JournalArticle a = null;
                if ( WorkflowConstants.LABEL_APPROVED.equals(commitment.getStatus()) ) {
                    a = commitmentService.createCommitment(commitment, WorkflowConstants.ACTION_PUBLISH, true);
                } else {
                    a = commitmentService.createCommitment(commitment, WorkflowConstants.ACTION_SAVE_DRAFT, true);
                }
                Commitment newCommitment = commitmentService.getCommitment( a.getArticleId(), null, true );
                return Response.ok(gson.toJson(newCommitment)).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (PortalException e) {
            _logger.error("Commitment creation failed!", e);
            return Response.serverError().build();

        }
    }

    private Response updateCommitment(Commitment commitment) {
        if (_logger.isDebugEnabled()) _logger.debug("Updating article, id: " + commitment.getId());
        try {
            boolean allowSave = isCommitmentUpdateAllowed(commitment);
            if (allowSave) {
                JournalArticle a = null;
                if ( WorkflowConstants.LABEL_APPROVED.equals(commitment.getStatus()) ) {
                    a = commitmentService.updateCommitment(commitment, WorkflowConstants.ACTION_PUBLISH);
                } else {
                    a = commitmentService.updateCommitment(commitment, WorkflowConstants.ACTION_SAVE_DRAFT);
                }
                Commitment updatedCommitment = commitmentService.getCommitment(a.getArticleId(), null, true);
                return Response.ok(gson.toJson(updatedCommitment)).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (NoSuchArticleException e) {
            _logger.error("Commitment update failed!", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (PortalException e) {
            _logger.error("Commitment creation failed!", e);
            return Response.serverError().build();

        }
    }

    /**
     * Updates organization name for each commitment that the org. has created (also drafts)
     * Called from organizations.js, when org.name changed on profile page
     */
    @POST
    @Path("/updateCommitmentsOrgName")
    public Response updateCommitmentsOrgName(String updatedOrgData) throws PortalException  {
        if (userService.isAdmin(PortalUtil.getUser(httpServletRequest))) {
            org.json.JSONObject orgdata = new org.json.JSONObject(updatedOrgData);
            String organizationId = (String)orgdata.get("organizationId");
            String organizationName = (String)orgdata.get("name");

            List<Commitment> commitments = commitmentService.getOrganizationCommitments("",organizationId, null);

            if (commitments != null) {
                commitments.forEach((commitment) -> {
                    if (!commitment.getOrganizationName().equals(organizationName) && commitment.getOrganizationId().equals(organizationId)) {
                        _logger.debug("New name for org. with id :" +organizationId +", Old name - " +commitment.getOrganizationName()  +", New name - " +organizationName);
                        try {
                            commitment.setOrganizationName(organizationName);
                            if(WorkflowConstants.LABEL_APPROVED.equals(commitment.getStatus())) {
                                commitmentService.updateCommitment(commitment, WorkflowConstants.ACTION_PUBLISH);
                            } else {
                                commitmentService.updateCommitment(commitment, WorkflowConstants.ACTION_SAVE_DRAFT);
                            }
                        } catch (Exception _e) {
                            _logger.error("Failed on commitment update! (Org.name)", _e);
                        }
                    } else {
                        _logger.debug("Org.name will not be updated. Id: " +commitment.getId());
                    }
                });
            } else {
                _logger.debug("No commitments found for " + (String)orgdata.get("organizationName") );
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }


    public boolean isSmartWaysCommitment(Commitment commitment) {
        boolean isSmartWays = commitment.getCategoryIds().contains( Constants.COMMITMENT_100_SMART_WAYS );
        return isSmartWays;
    }

    /**
     * Send email notify about created or updated commitment
     * Note! commitment must have id (ie. save commitment first, so that Liferay generates articleId before calling this)
     * @param commitment commitment
     * @return commitment @see {@link Commitment}
     */
    @POST
    @Path("/sendemailnotify")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendEmailNotify(Commitment commitment) {

        try {
            boolean allowSave = isCommitmentUpdateAllowed(commitment);
            if ( allowSave ) {
                User u = getUser();

                //Send to admin
                commitmentService.sendCommitmentNotifyEmail(getEmailToAdminAddress(commitment), commitment);
                //Send information to commitment creator
                String emailTemplate = Constants.EMAIL_SEND_TO_REVIEW_TEMPLATE_ARTICLE_URL_TITLE;
                if ( isSmartWaysCommitment(commitment) ) {
                    emailTemplate = Constants.EMAIL_100SMARTWAYS_PUBLISHED_TEMPLATE_ARTICLE_URL_TITLE;
                }
                commitmentMailService.sendEmail(u.getEmailAddress(), emailTemplate, PortalUtil.getLocale(httpServletRequest).toString());
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (PortalException e) {
            _logger.error("Could not send commitment created notify email!", e);
            return Response.serverError().entity(e.getMessage()).build();

        } catch (AddressException e) {
            _logger.error("Could not send commitment created notify email!", e);
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    private User getUser() throws PortalException {
        return PortalUtil.getUser(httpServletRequest);
    }

    /**
     * Check if user has rights to add commitment:
     * User must be logged in
     * If trying to create organization commitment then checks that user belongs to organization
     * @param commitment commitment
     * @return commitment @see {@link Commitment}
     * @throws PortalException @see {@link PortalException} @see {@link PortalException}
     */
    private boolean isCommitmentAddAllowed(Commitment commitment) throws PortalException {
        User u = getUser();
        _logger.debug("User" + u.getFullName());
        boolean allowSave = u != null && StringUtils.isNotBlank(u.getEmailAddress()) && !u.isDefaultUser();

        if ( allowSave && StringUtils.isNotBlank( commitment.getOrganizationName())) {
            //Check if user belongs to organization where commitment is created
            boolean orgmatch = false;
            for (Organization o : u.getOrganizations()) {
                if ( commitment.getOrganizationName().equals(o.getName())) {
                    orgmatch = true;
                    break;
                }
            }
            allowSave = allowSave && orgmatch;
        }
        return allowSave;
    }

    /**
     * Check if user has rights to update commitment:
     * User must be logged in
     * If trying to modify organization commitment then checks that user belongs to organization
     * @param commitment
     * @return true if update allowed, otherwise false
     * @throws PortalException @see {@link PortalException}
     */
    private boolean isCommitmentUpdateAllowed(Commitment commitment) throws PortalException {
        User u = getUser();
        _logger.debug("User" + u.getFullName());
        boolean allowSave = u != null && StringUtils.isNotBlank(u.getEmailAddress()) && !u.isDefaultUser();

        boolean userMatch = false;
        if ( allowSave && StringUtils.isNotBlank( commitment.getOrganizationId())) {
            //Organization, check if user belongs to organization where commitment is updated
            userMatch = false;
            for (Organization o : u.getOrganizations()) {
                if ( Long.parseLong(commitment.getOrganizationId()) == (o.getOrganizationId())) {
                    userMatch = true;
                    break;
                }
            }
        } else {
            //Person, only article original creator can update via api
            Commitment latestPreviousVersion = commitmentService.getCommitment(commitment.getId(), null, true);
            userMatch = latestPreviousVersion.getCreatedByUserId() == u.getUserId();
        }
        allowSave = allowSave && userMatch;
        return allowSave;
    }

    /**
     * Add report.
     * Report's commitmentArticleId must be given.
     * @param report Report @see {@link Report}
     * @return report with updated information like id
     */
    @POST
    @Path("/reports")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrUpdateReport(Report report) {
        boolean articleExists = false;
        try {
            articleExists = JournalArticleLocalServiceUtil.hasArticle(SITOUMUS2050_GROUP_ID, report.getId());
        } catch (Exception e) {
            //Nothing to do here, just extra cautious with Liferay...
        }
        if ( articleExists ) {
            return updateReport(report);
        } else {
            if ( StringUtils.isNotBlank( report.getId() )) {
                _logger.warn("addOrUpdateReport(), Report id given, but article not found. Report id will be ignored and a new report will be created");
            }
            return addReport(report);
        }
    }

    private Response updateReport(Report report) {
        if (_logger.isDebugEnabled()) _logger.debug("Updating article, id: " + report.getId());
        try {
            boolean allowSave = isReportSaveAllowed(report.getCommitmentArticleId());
            if (allowSave) {
                JournalArticle a = null;
                a = commitmentService.updateCommitmentReport(report, WorkflowConstants.ACTION_PUBLISH);
                Report updatedReport = commitmentService.getReport(a.getArticleId(), null);
                return Response.ok(gson.toJson(updatedReport)).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (NoSuchArticleException e) {
            _logger.error("Report update failed!", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (PortalException e) {
            _logger.error("Report update failed!", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    private Response addReport(Report report) {
        try {
            if ( isReportSaveAllowed(report.getCommitmentArticleId()) ) {
                JournalArticle commitmentReport = commitmentService.createCommitmentReport(report, WorkflowConstants.ACTION_PUBLISH, true);
                Report r = commitmentService.getReport(commitmentReport.getArticleId(), null);
                return Response.ok(gson.toJson(r)).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * Check if user has rights to save report. This actually checks if user has right to update commitment and thus
     * reporting is allowed.
     * @param commitmentArticleId
     * @return
     * @throws PortalException
     */
    private boolean isReportSaveAllowed(String commitmentArticleId) throws PortalException {
        boolean reportAddAllowed = false;
        Commitment c = commitmentService.getCommitment(commitmentArticleId, null, true);
        reportAddAllowed = isCommitmentUpdateAllowed(c);
        return reportAddAllowed;
    }

    /**
     * Get latest approved commitment
     * @param commitmentId commitment id (Liferay's articleId)
     * @return Commitment @see {@link Commitment}
     */
    @GET
    @Path("/commitments/approved/{commitmentid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommitment(@PathParam("commitmentid") String commitmentId) {
        try {
            Commitment c = commitmentService.getCommitment(commitmentId, WorkflowConstants.STATUS_APPROVED, true);
            //Fix for when saving from control panel, somehow liferay makes org.id disappear
            if(c.getOrganizationId() != null && c.getOrganizationId().isEmpty() && c.getOrganizationName() != null && !c.getOrganizationName().isEmpty()) {
                commitmentService.updateCommitment(c, WorkflowConstants.ACTION_PUBLISH);
            }
            return Response.ok(gson.toJson(c)).build();
        } catch (PortalException e) {
            _logger.error("Failed to get commitment: " + commitmentId);
            return Response.serverError().build();
        }
    }

    /**
     * Get latest approved or draft commitment
     * @param commitmentId  commitment id (Liferay's articleId)
     * @return Latest version of commitment (draft or approved) @see {@link Commitment}
     */
    @GET
    @Path("/commitments/latest/{commitmentid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUpdatableCommitment(@PathParam("commitmentid") String commitmentId) {
        try {
            Commitment c = commitmentService.getCommitment(commitmentId, null, true);
            return Response.ok(gson.toJson(c)).build();
        } catch (PortalException e) {
            _logger.error("Failed to get commitment: " + commitmentId);
            return Response.serverError().build();
        }
    }



    /**
     * Search commitments
     * - Searches only articles which structure is commitment, key: 31796
     * - if categoryids does not contain any commitmenttype categories, then all (currently 3 will be added: nutrition, greendeal, commitment) will be added to search terms. If at least one is given, then searh limits to only that commitmentype.
     * - if language is empty, defaults to fi_FI
     * - if organization name is given, search should only return commitments which organization name matches exactly query param. However, this  feature is still experimental and might need changes
     * - list can be sorted with sort parameter
     * - list can be paginated with start and end parameters
     *
     * Results are generated by Liferays search engine, which returns very limited list of indexed values. These are not transformed to actual commitment model, because it would be very time consuming operation and thus generate performance issues.
     * Returned values are roughly (in addition Liferay might add a few more fields which are not here mentioned ):
     *   "articleId",
     *   "title_" + language,
     *   "assetCategoryIds",
     *   "expando__keyword__custom_fields__Organization name",
     *   "expando__custom_fields__creatorUserName",
     *   "expando__keyword__custom_fields__creatorUserId",
     *   "status",
     *   "createdDate",
     *   "priority",
     *   "expando__keyword__custom_fields__creatorPortrait",
     *   "expando__keyword__custom_fields__organizationLogo"
     *
     *
     * @param keywords  search string
     * @param categoryIds List of Category ids
     * @param organization Organization name, if given, query should return only commitments which organization name matches exactly to query param
     * @param language if empty, defaults to fi_FI
     * @param start start index (pagination)
     * @param end end index (pagination)
     * @param sort, @see {@link CommitmentSort} enum for values
     *
     * @return List of commitment search results. Results are generated by Liferays search engine, which returns very limited list of indexed values and thus performs well.
     */
    @GET
    @Path("/commitments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchCommitments(@QueryParam("keywords") String keywords,
                                      @QueryParam("categoryIds") List<Long> categoryIds,
                                      @QueryParam("language") String language,
                                      @QueryParam("organization") String organization,
                                      @QueryParam("start") Integer start,
                                      @QueryParam("end") Integer end,
                                      @QueryParam("sort") String sort) {
        _logger.debug("Search commitments, keywords: " + keywords + " categoryIds: " + categoryIds + " sort: " + sort);

        try {
            CommitmentSort csort = CommitmentSort.latest;
            if (StringUtils.isNotBlank(sort)) {
                try {
                    csort = CommitmentSort.valueOf(sort);
                } catch (IllegalArgumentException e) {
                    _logger.error("Not acceptable sort value: "+ sort + ", defaults to latest.");
                }
            }
            JSONObject result = doFacetedSearch(keywords, categoryIds, language, start, end, null, organization, csort);
            return Response.ok(result.toJSONString()).build();
        } catch (SearchException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    /**
     * Add child article to parent article relationship.
     * User has to be logged in and rights to edit childcommitment
     * Child commitment (article) must exists before calling this.
     * Updates parent commitment priority

     * @param parentCommitmentId parent commitment id (articleId)
     * @param childCommitmentId child commitment id (articleId)
     * @return Response ok if successfull
     */
    @POST
    @Path("/commitments/jointoparent")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response joinToParentCommitment(@FormParam("parentCommitmentId") String parentCommitmentId, @FormParam("childCommitmentId") String childCommitmentId ) {
        _logger.debug("Joining, parent commitmentId : " + parentCommitmentId + " child commitmentId: " + childCommitmentId);
        try {
            User user = getUser();
            Commitment childC = commitmentService.getCommitment(childCommitmentId, null, true);
            if (isCommitmentUpdateAllowed(childC) || userService.isAdmin(user)) {
                commitmentService.joinToParentCommitment(parentCommitmentId, childCommitmentId);
                return Response.ok("Joined successfully commitment " + childCommitmentId + " to " + parentCommitmentId + "!").build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        }  catch (PortalException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    /**
     * Get all commitment reports
     * @param commitmentId id of commitment
     * @return list of Reports @see {@link Report}
     */
    @GET
    @Path("/commitments/{commitmentid}/reports")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReports(@PathParam("commitmentid") String commitmentId) {
        if ( _logger.isDebugEnabled()) _logger.debug("Getting reports for commitment: " + commitmentId);
        try {
            List<Report> reports = commitmentService.getCommitmentReports(commitmentId);
            JSONObject result = JSONFactoryUtil.createJSONObject();
            result.put("data", reports);
            return Response.ok(result.toJSONString()).build();
        } catch (PortalException e) {
            _logger.error("Get reports for commitment: " + commitmentId + " failed!", e);
            return Response.serverError().build();
        }
    }

    /**
     * Get commitment operation reports (Generic method, this can be used if folder id is known but api does not support specific search)
     * @param commitmentId commitment id
     * @param operationid operation id
     * @return List of Reports {@link Report}
     */
    @GET
    @Path("/commitments/{commitmentid}/{operationid}/reports")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReports(@PathParam("commitmentid") String commitmentId, @PathParam("operationid") String operationid) {
        if ( _logger.isDebugEnabled()) _logger.debug("Getting reports for commitment: " + commitmentId + " operation: " + operationid);
        try {
            List<Report> reports = commitmentService.getOperationReports(commitmentId, operationid);

            JSONObject result = JSONFactoryUtil.createJSONObject();
            result.put("data", reports);
            return Response.ok(result.toJSONString()).build();
        } catch (PortalException e) {
            _logger.error("Get reports for commitment: " + commitmentId + " failed!", e);
            return Response.serverError().build();
        }
    }

    /**
     * Get report
     * @param reportId id of commitment
     * @return Report @see {@link Report}
     */
    @GET
    @Path("/reports/{reportid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReport(@PathParam("reportid") String reportId) {
        if ( _logger.isDebugEnabled()) _logger.debug("Getting report: " + reportId);
        try {
            Report report = commitmentService.getReport(reportId, WorkflowConstants.STATUS_APPROVED);
            return Response.ok(gson.toJson(report)).build();
        } catch (PortalException e) {
            _logger.error("Get report: " + reportId + " failed!", e);
            return Response.serverError().build();
        }
    }

    /**
     * Get operation templates from folder
     * @param folderId  folder id
     * @return List of OperationTemplates @see {@link OperationTemplate}
     */
    @GET
    @Path("/templates/operations/folder/{folderid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOperationTemplatesByFolderId(@PathParam("folderid") String folderId) {
        return getOperationTemplates(folderId);
    }

    /**
     * Get common operation templates
     * @return List of OperationTemplates @see {@link OperationTemplate}
     */
    @GET
    @Path("/templates/operations/common")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOperationTemplatesCommon() {
        return getOperationTemplates(COMMITMENT_OPERATION_TEMPLATES_COMMON_FOLDER_ID);
    }

    /**
     * Get educational operation templates
     * @return List of OperationTemplates @see {@link OperationTemplate}
     */
    @GET
    @Path("/templates/operations/educational")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOperationTemplatesEducational() {
        return getOperationTemplates(COMMITMENT_OPERATION_TEMPLATES_EDUCATIONAL_FOLDER_ID);
    }

    /**
     * Get individual operation templates
     * @return List of OperationTemplates @see {@link OperationTemplate}
     */
    @GET
    @Path("/templates/operations/individual")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOperationTemplatesIndividual() {
        return getOperationTemplates(COMMITMENT_OPERATION_TEMPLATES_INDIVIDUAL_FOLDER_ID);
    }

    /**
     * Get "100 smart ways" operation templates.
     * If list of ids is empty returns all templates
     *
     * @return List of OperationTemplates @see {@link OperationTemplate}
     */
    @GET
    @Path("/templates/operations/100smartways")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOperationTemplates100SmartWays(@QueryParam("ids") List<String> ids) {
        try {
            JSONObject result = commitmentService.getSmartWaysOperationTemplates(ids);
            return Response.ok(result.toJSONString()).build();
        } catch (Exception e) {
            _logger.error("Get 100 Smart ways operation templates failed!", e);
            return Response.serverError().build();
        }
    }

    private Response getOperationTemplates(String folderId) {
        if ( _logger.isDebugEnabled()) _logger.debug("Getting operation templates from folder: " + folderId);
        try {
            List<OperationTemplate> results = commitmentService.getOperationTemplates(Long.valueOf( folderId));
            JSONObject result = JSONFactoryUtil.createJSONObject();
            result.put("data", results);
            return Response.ok(result.toJSONString()).build();
        } catch (Exception e) {
            _logger.error("Get operation templates from folder: " + folderId + " failed!", e);
            return Response.serverError().build();
        }
    }

    /**
     * get common meter templates
     * @return List of meter templates @see {@link MeterTemplate}
     */
    @GET
    @Path("/templates/meters/common")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMeterTemplatesCommon() {
        return getMeterTemplates(COMMITMENT_METER_TEMPLATES_COMMON);
    }

    /**
     * Get greendeal meter templates
     * @return List of meter templates, @see {@link MeterTemplate}
     */
    @GET
    @Path("/templates/meters/greendeal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMeterTemplatesGreendeal() {
        return getMeterTemplates(COMMITMENT_METER_TEMPLATES_GREEN_DEAL);
    }

    /**
     * Get meter templates from folder.
     * @param folderId meter folder id
     * @return List of meter templates, @see {@link MeterTemplate}
     */
    @GET
    @Path("/templates/meters/folder/{folderid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMeterTemplatesByFolderId(@PathParam("folderid") String folderId) {
        return getMeterTemplates(folderId);
    }

    private Response getMeterTemplates(String folderId) {
        if ( _logger.isDebugEnabled()) _logger.debug("Getting meter templates from folder: " + folderId);
        try {
            List<MeterTemplate> results = commitmentService.getMeterTemplates(Long.valueOf( folderId));
            JSONObject result = JSONFactoryUtil.createJSONObject();
            result.put("data", results);
            return Response.ok(result.toJSONString()).build();
        } catch (Exception e) {
            _logger.error("Get meter templates from folder: " + folderId + " failed!", e);
            return Response.serverError().build();
        }
    }

    /**
     * Get list of latest organization commitments (published or drafts)
     *
     * @param organization organization name
     * @return List of Commitments @see {@link Commitment}
     */
    @GET
    @Path("/organizationcommitments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommitmentsByOrganizationName(@QueryParam("organization") String organization, @QueryParam("organizationId") String organizationId) {
       if(organization != null)
        return getOrganizationCommitmentsInner(organization, "",null);
       else {
           return getOrganizationCommitmentsInner("",organizationId, null);
       }

    }

    /**
     * Get organization commitments by organization name and status
     * @param organization organization name
     * @param status status of commitments
     * @return list of organization commitments @see {@link Commitment}
     */
    private Response getOrganizationCommitmentsInner(String organization, String organizationId, Integer status) {
        _logger.debug("search organization commitments, organization name: " + organization +" , organization id: " + organizationId);
        try {
            if (StringUtils.isNotBlank(organization) ) {
                List<Commitment> results = commitmentService.getOrganizationCommitments(organization, "", status);
                JSONObject result = JSONFactoryUtil.createJSONObject();
                result.put("data", results);
                return Response.ok(result.toJSONString()).build();
            } else if (StringUtils.isNotBlank(organizationId)) {
                List<Commitment> results = commitmentService.getOrganizationCommitments("",organizationId, status);
                JSONObject result = JSONFactoryUtil.createJSONObject();
                result.put("data", results);
                return Response.ok(result.toJSONString()).build();
            } else {
                return Response.serverError().build();
            }
        } catch (SearchException e) {
            _logger.error("Organization commitment search failed, " + e.getMessage());
            return Response.serverError().build();
        } catch (PortalException e) {
            _logger.error("Organization commitment search failed, " + e.getMessage());
            return Response.serverError().build();

        }
    }

    /**
     * Get list of approved organization commitments
     *
     * @param organization organization name
     * @return List of approved commitments @see {@link Commitment}
     */
    @GET
    @Path("/organizationcommitments/approved")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApprovedCommitmentsByOrganizationName(@QueryParam("organization") String organization) {
        return getOrganizationCommitmentsInner(organization, "", WorkflowConstants.STATUS_APPROVED);
    }

    /**
     * Get list of user's latest commitments (draft or approved). This list does not include commitments which are organization commitments, althought user might have created those.
     * To get organization commitments see {@link #getCommitmentsByOrganizationName(String)}
     * @param userId Id of user
     * @return List of Commitments @see {@link Commitment}
     */
    @GET
    @Path("/usercommitments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommitmentsByUserId(@QueryParam("userId") Long userId) {
        _logger.debug("search user commitments, userid" + userId);
        try {
            if (userId != null ) {
                List<Commitment> results = commitmentService.getUserCommitments(userId);
                JSONObject result = JSONFactoryUtil.createJSONObject();
                result.put("data", results);
                return Response.ok(result.toJSONString()).build();
            } else {
                return Response.serverError().build();
            }
        } catch (SearchException e) {
            _logger.error("Usercommitments search failed, " + e.getMessage());
            return Response.serverError().build();
        } catch (PortalException e) {
            _logger.error("Usercommitments search failed, " + e.getMessage());
            return Response.serverError().build();
        }
    }

    private JSONObject doFacetedSearch(String keywords, List<Long> categoryIds, String language, Integer start, Integer end, Long creatorUserId, String organization, CommitmentSort sort) throws SearchException {
        Hits hits = commitmentService.searchCommitments(keywords, categoryIds, language, start, end, creatorUserId, organization, sort);
        hits.toList().forEach((hit) -> {
            try {
                int joined = commitmentService.caclAmountOfJoinedCommitmens(hit.get("articleId"));
                int likes = commitmentService.getRatingsEntryScore(hit.get("articleId"));
                hit.addNumber("joinedCount", joined);
                hit.addNumber("likes", likes);
            } catch (PortalException e) {
                e.printStackTrace();
            }
        });
        JSONObject result = JSONFactoryUtil.createJSONObject();
        result.put("data", hits.toList());
        return result;
    }

    private EmailAndCategoryIDPair[] getSitoumusConfiguration() {
        String config = "";
        try {
            config = sitoumusConfiguration.jsonConfiguration();
        } catch (Exception e) {
            _logger.error("Email configuration possibly not configured in Liferay system settings. Please add configuration!", e);
        }

        Gson gson = new Gson();
        EmailAndCategoryIDPair[] configArray  = gson.fromJson(config, EmailAndCategoryIDPair[].class);

        return configArray;
    }

    private String getEmailToAdminAddress(Commitment commitment) throws PortalException {
        EmailAndCategoryIDPair[] configArray = getSitoumusConfiguration();

        List<String> emails = commitment.getCategoryIds().stream().map(id -> {

            String to = "";

            for (EmailAndCategoryIDPair emailIdPair : configArray)
            {
                if(emailIdPair.categoryId == id.intValue()){
                    to = emailIdPair.email;
                }
            }
            return to;
        }).collect(Collectors.toList());

        String to = "";

        List<String> emailsWithoutDuplicates = emails.stream()
                .distinct()
                .collect(Collectors.toList());
        emailsWithoutDuplicates.removeIf(String::isEmpty);

        if(emailsWithoutDuplicates.size() == 0){
            emailsWithoutDuplicates.add(PropsUtil.get(EMAIL_ADMIN_COMMIT_SAVED));
        }
        to = String.join(", ", emailsWithoutDuplicates);

        if (StringUtils.isBlank(to)) {
            throw new PortalException("Unable to find email addresses to send notifications to based on commitment categoryIds. Make sure they are configured in portal-ext.properties and restart server");
        }
        return to;
    }
	/*

	 //NOTE: Saving this if for some reason categoryID based emailing turns out to be poop
    private String getEmailToAdminAddress(Commitment commitment) throws PortalException {
        String to = "";
        switch (commitment.getCommitmentType()) {
            case GREEN_DEAL:
                to = PropsUtil.get(EMAIL_ADMIN_GREEN_DEAL_SAVED);
                break;
            case NUTRITION:
                to = PropsUtil.get(EMAIL_ADMIN_NUTRITION_SAVED);
                break;
            default:
                to = PropsUtil.get(EMAIL_ADMIN_COMMIT_SAVED);
                break;
        }
        if (StringUtils.isBlank(to)) {
            throw new PortalException("Commitments notification email to address is not configured in portal-ext.properties. Add configuration and restart server");
        }
        return to;
    }
    */
    /**
     * Get likes of report or commitment
     * @param articleId  Commitment or report id
     * @return amount of likes
     */
    @GET
    @Path("/likecommitment/{articleid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getlikes(@PathParam("articleid") String articleId) {
        int likes = commitmentService.getRatingsEntryScore(articleId);
        return Response.ok(likes).build();
    }

    /**
     * Add like to report or commitment
     * (note! Currently by design anyone can add like)
     * @param articleId  Commitment or report id
     * @return amount of likes
     */
    @POST
    @Path("/likecommitment/{articleid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addlike(@PathParam("articleid") String articleId) {
        int likes = commitmentService.addRatingsEntryScore(articleId);
        return Response.ok(likes).build();
    }

    /**
     * Search random example commitments
     *
     * @param categoryId Category id
     * @param amount if amount of examples is given, if amount is now given returns all found examples
     * @param language language
     * @return List of example commitments
     */
    @GET
    @Path("/examplecommitments/random")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRandomExampleCommitments(@QueryParam("categoryid") String categoryId, @QueryParam("amount") int amount, String language ) {
        return searchExampleCommitments(categoryId, amount, language);
    }
    /**
     * Get example commitments
     *
     * @param categoryId Category id
     * @param language language
     * @return List of example commitments
     */
    @GET
    @Path("/examplecommitments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExampleCommitments(@QueryParam("categoryid") String categoryId, String language ) {
        return searchExampleCommitments(categoryId, null, language);
    }

    /**
     * Count amount of child (joined) commitments.
     *
     * @param commitmentId  parent commitmentid
     * @return amount of joined commitments
     */
    @GET
    @Path("/commitments/{commitmentid}/joinedcount")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getJoinedCommitmentsAmount(@PathParam("commitmentid") String commitmentId) {
        try {
            int amount = commitmentService.caclAmountOfJoinedCommitmens(commitmentId);
            return Response.ok(amount).build();
        } catch (PortalException e) {
            _logger.error("Getting related child commitments failed, commitmendid: " + commitmentId);
            return Response.serverError().build();
        }
    }

    /**
     * Return child (joined) commitments
     *
     * @param commitmentId  parent commitmentid
     * @return List of joined commitments
     */
    @GET
    @Path("/commitments/{commitmentid}/joined")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJoinedCommitments(@PathParam("commitmentid") String commitmentId) {
        try {
            List<Commitment> childs = commitmentService.getCommitmentJoinedCommitments(commitmentId);
            JSONObject result = JSONFactoryUtil.createJSONObject();
            result.put("data", childs);
            return Response.ok(result.toJSONString()).build();
        } catch (PortalException e) {
            _logger.error("Getting related child commitments failed, commitmendid: " + commitmentId, e);
            return Response.serverError().build();
        }
    }


    private Response searchExampleCommitments(@QueryParam("categoryid") String categoryId, @QueryParam("amount") Integer amount, String language) {
        try {

            FacetedSearcherManager facetedSearcherManager= FacetedSearcherManagerUtil.getFacetedSearcherManager();

            FacetedSearcher facetedSearcher = facetedSearcherManager.createFacetedSearcher();

            SearchContext searchContext = new SearchContext();
            if (StringUtils.isBlank(language)) {
                language = "fi_FI";
            }
            Locale locale = new Locale(language);
            String[] entryClassNames = { JournalArticle.class.getName() };
            searchContext.setEntryClassNames(entryClassNames);
            searchContext.setCompanyId(Constants.COMPANY_ID);
            searchContext.setCommitImmediately(true);
            long[] groupIds = new long[]{ SITOUMUS2050_GROUP_ID };

            searchContext.setGroupIds(groupIds);
            searchContext.setLocale(locale);

            List<BooleanClause> booleanClauses = new ArrayList<BooleanClause>();

            //Limit search only to Commitment structures
            String structureKey = GLOBAL_EXAMPLE_COMMITMENT_DDM_STRUCTURE_KEY;
            BooleanQueryImpl bqStructureKeyQuery = new BooleanQueryImpl();
            TermQuery structureQuery = new TermQueryImpl("ddmStructureKey", structureKey);
            bqStructureKeyQuery.add(structureQuery, BooleanClauseOccur.MUST);
            BooleanClause structureClause = BooleanClauseFactoryUtil.create(bqStructureKeyQuery, BooleanClauseOccur.MUST.getName());
            booleanClauses.add(structureClause);

            //Add commitment type category clauses
            BooleanQueryImpl bqCommitmentType = new BooleanQueryImpl();
            TermQuery condition = new TermQueryImpl(Field.ASSET_CATEGORY_IDS, categoryId);
            bqCommitmentType.add(condition, BooleanClauseOccur.MUST);
            BooleanClause commitmentClause = BooleanClauseFactoryUtil.create(bqCommitmentType, BooleanClauseOccur.MUST.getName());
            booleanClauses.add(commitmentClause);

            searchContext.setBooleanClauses(booleanClauses.toArray(new BooleanClause[booleanClauses.size()]));

            Facet assetEntriesFacet = new AssetEntriesFacet(searchContext);
            assetEntriesFacet.setStatic(true);
            searchContext.addFacet(assetEntriesFacet);

            //Define return fields, this will boost performance significantly
            searchContext.getQueryConfig().setSelectedFieldNames(
                    "articleId"
            );

            Sort[] sorts = new Sort[] {new Sort("ddm__text__41394__content_" + language + "_String_sortable", 3, false)};
            searchContext.setSorts(sorts);


            Hits hits = facetedSearcher.search(searchContext);
            if ( _logger.isDebugEnabled()) {
                _logger.debug("Liferay faceted search time: " + hits.getSearchTime());
                _logger.debug("Hits: " + hits.getLength());
            }


            List<Document> documents = hits.toList();
            if (amount != null && amount > 0 ) {
                try {
                    Collections.shuffle(documents);
                    documents = documents.subList(0, amount);
                } catch (Exception e) {
                    //catch the exception if amount is bigger than result size.... ie. just show all the results
                }
            }

            List<ExampleCommitment> results = new ArrayList<>();

            for (Document d : documents) {
                String aid = d.get("articleId");
                JournalArticle a = JournalArticleLocalServiceUtil.getLatestArticle(SITOUMUS2050_GROUP_ID, aid);
                JSONObject ecJson = LFArticleXMLToJsonTransformer.lfSimpleFlatArticleContentXMLToJSON(a.getContent());
                ExampleCommitment ec = new ExampleCommitment();
                ec.setId(aid);
                ec.setContent_fi_FI(ecJson.getString("content_fi_FI"));
                ec.setContent_en_US(ecJson.getString("content_en_US"));
                ec.setContent_sv_SE(ecJson.getString("content_sv_SE"));
                results.add(ec);
            }

            JSONObject result = JSONFactoryUtil.createJSONObject();
            result.put("data", results);
            return Response.ok(result.toJSONString()).build();

        } catch (Exception e) {
            _logger.error("Example commitment search failed!", e);
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    /**
     * Return list of commitment chart types
     *
     * @return List of chart types
     */
    @GET
    @Path("/commitments/charts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJoinedCommitments() {
        return Response.ok(gson.toJson(MeterChartType.getCommitmentChartNames())).build();
    }


    /**
     * Add commitment image to Liferay's document library, folder: "Sitoumusten kuvat"
     * If successfully added return relative url to image
     *
     * filename must be given (and perhaps mut be unique... or else Liferay errors with duplicate file entry message...)
     * image data should be base64 encoded
     * image mimetype must be given
     *
     * @param commitmentImage Commitment image @see {@link CommitmentImage}
     * @return Response, relative url to image if added successfully
     */
    @POST
    @Path("/image")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateCommitmentImage(CommitmentImage commitmentImage) {
        if (_logger.isDebugEnabled()) {
            _logger.debug("Add commitment image: " + commitmentImage.getFileName());
        }
        try {
            User u = getUser();
            _logger.debug("User" + u.getFullName());
            boolean allowSave = u != null && StringUtils.isNotBlank(u.getEmailAddress()) && !u.isDefaultUser();
            if (allowSave) {
                String url = commitmentService.addCommitmentImageToDocumentLibrary(commitmentImage);
                return Response.ok(url).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (PortalException e) {
            _logger.error("Adding commitment image failed", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }


    @GET
    @Path("/commitments/csv")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getAllCommitmentsAsCSV() {
        return getCommitmentsAsCSV(null);
    }


    @GET
    @Path("/commitments/csv/{categoryid}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getCommitmentsCSVByCategoryId(@PathParam("categoryid") String categoryid) {
        return getCommitmentsAsCSV(Long.valueOf(categoryid));
    }

    @GET
    @Path("/categories/{categoryid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories(@PathParam("categoryid") String rootCategoryid) {
        try {
            List<AssetCategory> categoryList = commitmentService.getCategoriesInHierarchy(Long.valueOf(rootCategoryid));
            Map<Long, List<AssetCategoryProperty>> categoryProperties =
                commitmentService.getCategoryProperties(categoryList.stream().map(c -> c.getCategoryId()).collect(Collectors.toList()))
                .stream()
                .collect(Collectors.groupingBy(s -> s.getCategoryId()));

            Map<Long, List<AssetCategory>> categoryChildren = categoryList.stream()
                .collect(Collectors.groupingBy(s -> s.getParentCategoryId()));

            AssetCategory root = categoryList.stream().filter(c -> c.getParentCategory() == null).findFirst().get();

            CategoryHierarchy hierarchy = getCategoryHierarchy(root, categoryChildren, categoryProperties);
            return Response.ok(gson.toJson(hierarchy)).build();

        } catch (PortalException e) {
            _logger.error("getCategories with id failed: "+rootCategoryid, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    private CategoryHierarchy getCategoryHierarchy(AssetCategory root, Map<Long, List<AssetCategory>> categoryChildren, Map<Long, List<AssetCategoryProperty>> categoryProperties) {

        List<AssetCategory> children = categoryChildren.remove(root.getCategoryId());
        List<AssetCategoryProperty> properties = categoryProperties.remove(root.getCategoryId());

        CategoryHierarchy c = new CategoryHierarchy();
        c.setCategoryId(String.valueOf(root.getCategoryId()));
        c.setCompanyId(String.valueOf(root.getCompanyId()));
        c.setCreateDate(String.valueOf(root.getCreateDate()));
        c.setDescription(root.getDescription());
        c.setDescriptionCurrentValue(root.getDescriptionCurrentValue());
        c.setGroupId(String.valueOf(root.getGroupId()));
        c.setLeftCategoryId(String.valueOf(root.getLeftCategoryId()));
        c.setName(root.getName());
        c.setParentCategoryId(String.valueOf(root.getParentCategoryId()));
        c.setRightCategoryId(String.valueOf(root.getRightCategoryId()));
        c.setTitleCurrentValue(root.getTitleCurrentValue());
        c.setUuid(root.getUuid());
        c.setVocabularyId(String.valueOf(root.getVocabularyId()));
        c.setTitleMap(root.getTitleMap());

        List<CategoryHierarchy> list = children != null ?
            children.stream().map(child -> getCategoryHierarchy(child, categoryChildren, categoryProperties))
            .collect(Collectors.toList()) : Collections.emptyList();
        c.setChildren(list);

        Map<String, String> map = properties != null ? properties.stream().collect(
            Collectors.toMap((prop) -> prop.getKey(), (prop) -> prop.getValue())) : Collections.emptyMap();
        c.setProperties(map);

        return c;
    }

    private Response getCommitmentsAsCSV(Long categoryId) {
        try {
            if (userService.isAdmin(getUser())) {
                List<Commitment> filtered = new ArrayList<>();
                List<Commitment> cs = commitmentService.getAllLatestCommitments();
                if ( categoryId != null ) {
                    //filter by categoryid (use allLatestResults as it includes drafts also...
                    for ( Commitment c : cs) {
                        if (c.getCategoryIds().contains(categoryId)) {
                            filtered.add(c);
                        }
                    }
                } else {
                    filtered = cs;
                }

                // organizationId -> type
                Map<Long, String> organizationIdsToTypes = ExpandoValueLocalServiceUtil
                    .getColumnValues(Constants.ORGANIZATION_TYPE_EXPANDO_COLUMN_ID,
                        QueryUtil.ALL_POS, QueryUtil.ALL_POS).stream()
                    .collect(Collectors.toMap(el -> el.getClassPK(), el -> el.getData()));

                Map<String, String> organizationNamesToTypes = OrganizationLocalServiceUtil
                    .getOrganizations(QueryUtil.ALL_POS, QueryUtil.ALL_POS).stream()
                    .collect(Collectors.toMap(el -> el.getName(),
                        el -> Optional.ofNullable(organizationIdsToTypes.get(el.getOrganizationId())).orElse("")));

                for ( Commitment c : filtered) {
                    try {
                        User u = userService.getUser(c.getCreatedByUserId());
                        if ( u != null && u.getEmailAddress() != null ) {
                            c.setCreatorEmail(u.getEmailAddress());
                        }
                    } catch (PortalException e) {
                        _logger.warn("Cannot create extended information", e);
                    }
                }

                JSONArray jsonArray = new JSONArray(
                    filtered.stream().map(el -> {
                        String type = organizationNamesToTypes.get(el.getOrganizationName());
                        if(type == null) {
                            type = "";
                        }
                        org.json.JSONObject obj = new org.json.JSONObject(gson.toJson(el));
                        return obj.put("organizationType", type);
                    }).collect(Collectors.toList()));

                String csv = CDL.toString(jsonArray);
                Response.ResponseBuilder response = Response.ok(csv);
                response.header("Content-Disposition", "attachment; filename=commitments.csv");
                return response.build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            _logger.error("creating csv failed!", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }


    /**
     * Get current amount of commitments
     */
    @GET
    @Path("/countCommitments")
    public Response countCommitments() throws PortalException  {
        if (userService.isAdmin(PortalUtil.getUser(httpServletRequest))) {
            try {
                int count = CommitmentLocalServiceUtil.getCommitmentsCount();
                Response.ResponseBuilder response = Response.ok(String.valueOf(count));
                return response.build();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    /**
     * Get commitments ( pagination )
     *
     * @param rangeFrom, pagination start from index
     * @param rangeTo, pagination end index
     *
     * */
    @GET
    @Path("/getallcommitments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCommitments(@QueryParam("rangeFrom") int rangeFrom, @QueryParam("rangeTo") int rangeTo) throws PortalException  {
        if (userService.isAdmin(PortalUtil.getUser(httpServletRequest))) {
            List<fi.weasel.commitment2050.commitmentanalysis.model.Commitment> commitments;
            try {
                commitments = CommitmentLocalServiceUtil.getCommitments(rangeFrom, rangeTo);
                Gson gson = new Gson();
                String commitmentsAsJson = gson.toJson(commitments);
                Response.ResponseBuilder response = Response.ok(commitmentsAsJson);
                return response.build();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    /**
     * Get current users commitments
     * Gets both organisation and own
     * */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserCommitments() throws PortalException  {
    List<fi.weasel.commitment2050.commitmentanalysis.model.Commitment> commitments;
        try {
            User u = getUser();
            long userId = u.getUserId();
            commitments = (List<fi.weasel.commitment2050.commitmentanalysis.model.Commitment>) CommitmentLocalServiceUtil.findByUserId(userId);
            Gson gson = new Gson();
            String commitmentsAsJson = gson.toJson(commitments);
            Response.ResponseBuilder response = Response.ok(commitmentsAsJson);
            return response.build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Calculates average co2 reduction for all commitments
     *
     * */
    @GET
    @Path("/getavgreduction")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvgReduction() throws PortalException  {
        List commitments;

        try {
            commitments = CommitmentLocalServiceUtil.getAvgReduction();
            Gson gson = new Gson();
            String commitmentsAsJson = gson.toJson(commitments);
            Response.ResponseBuilder response = Response.ok(commitmentsAsJson);
            return response.build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Calculates average co2 reduction for all commitments by meter
     *
     * */
    @GET
    @Path("/getavgreductionbymeter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvgReductionByMeter() throws PortalException  {
        List commitments;

        try {
            commitments = CommitmentLocalServiceUtil.getAvgReductionByMeter();
            Gson gson = new Gson();
            String commitmentsAsJson = gson.toJson(commitments);
            Response.ResponseBuilder response = Response.ok(commitmentsAsJson);
            return response.build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Counts amount of commitments, results are in the following order:  [ organizations, personal, total commitments(org. + personal), nutrition, plastic, oil, work machine, automotive, total green deals, demolis ]
     *
     * */
    @GET
    @Path("/getCountForAllTypes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountForAllTypes() throws PortalException  {
        List results;

        try {
            results = CommitmentLocalServiceUtil.getCountForAllTypes();
            Gson gson = new Gson();
            String commitmentsAsJson = gson.toJson(results);
            Response.ResponseBuilder response = Response.ok(commitmentsAsJson);
            return response.build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Counts the number of done lifestyle tests.
     *
     * */
    @GET
    @Path("/getDoneLifestyleTestCount")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getDoneLifestyleTestCount() {
        int count = commitmentService.getDoneLifestyleTestCount();
        Response.ResponseBuilder response = Response.ok(count);
        return response.build();
    }
}
