package com.soikea.commitment2050.api;

import static com.soikea.commitment2050.service.Constants.SITOUMUS2050_GROUP_ID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.soikea.commitment2050.model.Commitment;
import com.soikea.commitment2050.service.*;

import java.util.Map;
import java.util.stream.Collectors;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;
import org.apache.commons.lang3.StringUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Helper methods to get information about commitments
 *
 */
@ApplicationPath("/maintenanceapi")
@Component(immediate = true, service = Application.class)
public class MaintenanceApi extends Application {
    public Set<Object> getSingletons() {
        return Collections.<Object>singleton(this);
    }
    private static Logger log = LoggerFactory.getLogger(MaintenanceApi.class.getName());
    private static Gson gson = new GsonBuilder().create();

    @Reference
    CommitmentService commitmentService;

    @Reference
    CommitmentMailService commitmentMailService;

    @Reference
    UserService userService;

    @Reference
    CommitmentsPerCityService commitmentsPerCityService;

    @Context
    private HttpServletRequest httpServletRequest;

    /**
     * Calculates commitmentsPerCity when called from UI
     * instead of waiting for the scheduled nightly event.
     */
    @POST
    @Path("/pre-calculate-commitments-per-city")
    public Response getCommitmentsPerCity() {
        try {
            if (userService.isAdmin(getUser())) {
                try {
                    commitmentsPerCityService.calculateCommitmentsPerCity();
                    return Response.ok().build();
                } catch (Exception e) {
                    log.error("ERROR! Something went wrong with calculating commitments per city: ", e);
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                }
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e){
            log.error("Failed at verifying user credentials");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Path("/printCommitmentsWithoutCountry")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchCommitmentsWithoutAddress() {
        List<Commitment> result = new ArrayList<>();
        try {
            List<Commitment> commitments = commitmentService.getAllLatestCommitments();
            for ( Commitment c : commitments ) {
                if ( StringUtils.isBlank(c.getAddress()) || c.getLatitude() == null || c.getLongitude() == null) {
                    result.add(c);
                }
            }
            JSONObject json = JSONFactoryUtil.createJSONObject();
            json.put("size", result.size());
            json.put("data", result);
            return Response.ok(json.toJSONString()).build();
        } catch (PortalException e) {
            log.error("get commitments without address failed", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/templates/greendeal-report-reminder")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGreenDealReportReminderTemplate() {
        try {
            String lang = Constants.lang_fi;
            JournalArticle article = JournalArticleLocalServiceUtil
                .getLatestArticleByUrlTitle(SITOUMUS2050_GROUP_ID, Constants.EMAIL_GREENDEAL_REPORT_REMINDER, WorkflowConstants.STATUS_APPROVED);
            JSONObject json = LFArticleXMLToJsonTransformer.lfSimpleFlatArticleContentXMLToJSON(article.getContent());
            String subject = json.getString("Subject_" + lang);
            String body = JournalArticleLocalServiceUtil.getArticleContent(SITOUMUS2050_GROUP_ID, article.getArticleId(), article.getVersion(), null, article.getDDMTemplateKey(), lang, null, null);

            JsonObject o = new JsonObject();
            o.addProperty("subject", subject);
            o.addProperty("body", body);
            return Response.ok(gson.toJson(o)).build();
        } catch (PortalException e) {
            JsonObject o = new JsonObject();
            o.addProperty("error", "Error while loading email template with name "+Constants.EMAIL_GREENDEAL_REPORT_REMINDER+". Please, verify that template exists!");
            return Response.status(404).entity(gson.toJson(o)).build();
        }
    }

    @POST
    @Path("/send-greendeal-report-reminders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendGreenDealReportReminders() throws PortalException {
        if (userService.isAdmin(getUser())) {
            List<Commitment> commitments = commitmentService.getGreenDealCommitments();

            List<Long> userIds = commitments.stream().map(c -> c.getCreatedByUserId())
                .collect(Collectors.toList());
            DynamicQuery q = UserLocalServiceUtil.dynamicQuery();
            q.add(RestrictionsFactoryUtil.in("userId", userIds));
            List<User> users = UserLocalServiceUtil.dynamicQuery(q);
            Map<Long, User> userMap = users.stream().collect(Collectors.toMap(u -> u.getUserId(), u -> u));

            List<Commitment> sent = new ArrayList<>();
            List<Commitment> failed = new ArrayList<>();
            commitments.forEach(c -> {
                User user = userMap.get(c.getCreatedByUserId());
                if(user != null) {
                    try {
                        commitmentMailService.sendEmail(user.getEmailAddress(), Constants.EMAIL_GREENDEAL_REPORT_REMINDER, user.getLocale().toString());
                        sent.add(c);
                    } catch (Exception e) {
                        log.error("Error in sendGreenDealReportReminders:" + e.getMessage());
                        failed.add(c);
                    }
                }
            });

            if(!sent.isEmpty()) {
                log.info("Report reminders were sent to following GreenDeal commitments:");
                sent.forEach(c -> {
                    User user = userMap.get(c.getCreatedByUserId());
                    log.error("Commmitment "+c.getId()+" / "+c.getTitle_fi_FI()+", email "+user.getEmailAddress());
                });
            }
            JsonObject o = new JsonObject();
            if(!failed.isEmpty()) {
                log.error("Report reminders could not be sent to following GreenDeal commitments:");
                failed.forEach(c -> {
                    User user = userMap.get(c.getCreatedByUserId());
                    log.error("Commmitment "+c.getId()+" / "+c.getTitle_fi_FI()+", email "+user.getEmailAddress());
                });
                o.addProperty("error", "Some reminders could not be sent. Please, see the server logs for details.");
            }

            return Response.ok(gson.toJson(o)).build();


        } else {
            return Response.status(Status.UNAUTHORIZED).build();
        }
    }

    /**
     * API that can be used to verify email configuration.
     * Sends email to specified address. Address must be in etteplanmore.com domain.
     * @param emailAddress
     */
    @GET
    @Path("/sendtestemail")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sendTestEmail(@QueryParam("emailAddress") String emailAddress) throws PortalException {
        try {
            if (userService.isAdmin(getUser())) {
                if(emailAddress.endsWith("@weasel.fi")) {
                    commitmentMailService.sendEmail(emailAddress, "Test email", "This mail was sent as test to verify email configuration.",false);
                    return Response.ok("Test email sent").build();
                } else {
                    return Response.status(Status.BAD_REQUEST).entity("Invalid domain for test email").build();
                }
            } else {
                return Response.status(Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            log.error("sendTestEmail failed", e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error\n"+e.getMessage()).build();
        }

    }

    private User getUser() throws PortalException {
        return PortalUtil.getUser(httpServletRequest);
    }


}
