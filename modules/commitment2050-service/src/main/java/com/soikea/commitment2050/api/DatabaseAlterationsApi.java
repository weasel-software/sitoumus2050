package com.soikea.commitment2050.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liferay.expando.kernel.model.ExpandoValueModel;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.OrganizationModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import com.soikea.commitment2050.model.Commitment;
import com.soikea.commitment2050.model.City;

import com.soikea.commitment2050.service.*;

import fi.weasel.commitment2050.commitmentanalysis.service.*;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import java.util.*;
import java.util.stream.Collectors;

import static com.soikea.commitment2050.service.Constants.*;

/**
 * Methods for running special case database operations that are not run normally in the course of the application.
 */

@ApplicationPath("/databasealterationsapi")
@Component(immediate = true, service = Application.class)
public class DatabaseAlterationsApi extends Application {
    public Set<Object> getSingletons() {
        return Collections.<Object>singleton(this);
    }
    private static Logger _logger = LoggerFactory.getLogger(DatabaseAlterationsApi.class.getName());

    private static Gson gson = new GsonBuilder().create();

    @GET
    @Produces("text/plain")
    public String working() {
        return "It works!";
    }

    @Reference
    CommitmentService commitmentService;

    @Reference
    FormatCommitmentAddressesService formatCommitmentAddressesService;

    @Reference
    UserService userService;

    @Reference
    CommitmentsPerCityService commitmentsPerCityService;

    @Context
    private HttpServletRequest httpServletRequest;

    /**
     * Updates all commitments' address field with city only
     */
    @POST
    @Path("/formataddresses")
    public Response reformatAddresses() throws PortalException  {
        if (userService.isAdmin(PortalUtil.getUser(httpServletRequest))) {
            List<Commitment> commitments;
            List<City> cities;
            int commitmentsCount = 0;

            try {
                commitmentsCount = JournalArticleLocalServiceUtil.getNotInTrashArticlesCount(COMMITMENTS_GROUP_ID, COMMITMENTS_FOLDER_ID);
            } catch (Exception e) {
                _logger.error("Failed to get articles count.");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
            }

            try {
                cities = commitmentsPerCityService.getCitiesAsList();
            } catch(Exception e){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
            }
            for (int i = 0; i < commitmentsCount; i = i + 50) {
                try {
                    commitments = commitmentService.getRangeOfLatestApprovedCommitments(i, i + 50);
                    if (commitments != null) {
                        commitments.forEach((commitment) -> {
                            String commitmentAddress = commitment.getAddress();
                            Commitment adjustedCommitment = formatCommitmentAddressesService.formatCommitmentAddresses(commitment, cities);
                            if (!commitmentAddress.equals(adjustedCommitment.getAddress())) {
                                _logger.debug("Address has been changed, updating...");
                                try {
                                    commitmentService.updateCommitment(adjustedCommitment, WorkflowConstants.ACTION_PUBLISH);
                                } catch (Exception _e) {
                                    _logger.error("Failed on commitment update! (Address)", _e);
                                }
                            } else {
                                _logger.debug("Address has not been changed, skipping address update...");
                            }
                        });
                    }

                } catch (Exception e) {
                    _logger.error("ERROR: " + e.getMessage());
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
                }
            }
            _logger.debug("----------");
            _logger.debug("Update operation has finished.");
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    /**
     * Adds organization ids to commitments
     */
    @POST
    @Path("/updateOrgIds")
    public Response updateOrgIds() throws PortalException  {
        if (userService.isAdmin(PortalUtil.getUser(httpServletRequest))) {
            List<Commitment> commitments;
            int commitmentsCount = 0;

            try {
                commitmentsCount = JournalArticleLocalServiceUtil.getNotInTrashArticlesCount(COMMITMENTS_GROUP_ID, COMMITMENTS_FOLDER_ID);
            } catch (Exception e) {
                _logger.error("Failed to get articles count.");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
            }

            for (int i = 0; i < commitmentsCount; i = i + 50) {
                try {
                    commitments = commitmentService.getRangeOfAllCommitments(i, i + 50);
                    if (commitments != null) {
                        commitments.forEach((commitment) -> {
                            if (commitment.getOrganizationId() != null && commitment.getOrganizationId().isEmpty() && commitment.getOrganizationName() != null && !commitment.getOrganizationName().isEmpty()) {
                                _logger.debug("Org.Id missing, adding.. Status: (" +commitment.getStatus() +")");
                                try {
                                    if(WorkflowConstants.LABEL_APPROVED.equals(commitment.getStatus())) {
                                        commitmentService.updateCommitment(commitment, WorkflowConstants.ACTION_PUBLISH);
                                    } else {
                                        commitmentService.updateCommitment(commitment, WorkflowConstants.ACTION_SAVE_DRAFT);
                                    }
                                } catch (Exception _e) {
                                    _logger.error("Failed on commitment update! (Org.Id)", _e);
                                }
                            } else {
                                _logger.debug("Org.Id is not needed or exists, skipping org.id. update");
                            }
                        });
                    }
                } catch (Exception e) {
                    _logger.error("ERROR: " + e.getMessage());
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
                }
            }
            _logger.debug("----------");
            _logger.debug("Update operation has finished.");
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    /**
     * Recreate ca-db api-endpoint, login as admin to use
     * Scheduled job uses commitmentservice.recreateAnalysisDb() directly
     *
     */
    @GET
    @Path("/recreateCAdb")
    public Response recreateCommitmentAnalysisDatabase() throws PortalException  {
        if (userService.isAdmin(PortalUtil.getUser(httpServletRequest))) {
            commitmentService.recreateAnalysisDb();
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    /**
     * Adds organization ids to commitments
     */
    @GET
    @Path("/performCommitmentAnalysisCalculations")
    public Response performCommitmentAnalysisCalculations() throws PortalException  {
        if (userService.isAdmin(PortalUtil.getUser(httpServletRequest))) {

            _logger.debug("Performing calculations...");

            CommitmentAnalysisResultLocalServiceUtil.performResultCalculations();

            _logger.debug("----------");
            _logger.debug("Commitment analysis calculations finished.");

            return Response.ok().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

}
