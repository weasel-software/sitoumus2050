package com.soikea.commitment2050.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.soikea.commitment2050.model.CommitmentCountByMainGoal;
import com.soikea.commitment2050.model.StatisticsValueAndDate;
import com.soikea.commitment2050.service.StatisticsService;
import fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult;
import fi.weasel.commitment2050.commitmentanalysis.service.CommitmentAnalysisResultLocalServiceUtil;
import fi.weasel.commitment2050.commitmentanalysis.service.CommitmentLocalServiceUtil;
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
import java.util.*;

/**
 * This is the REST API documentation for commitment's statistical data. Here you will find information of how to invoke the REST API and what you can expect as return values.
 */
@ApplicationPath("/statisticsapi")
@Component(immediate = true, service = Application.class)
public class StatisticsApi extends Application {
    public Set<Object> getSingletons() {
        return Collections.<Object>singleton(this);
    }
    private static Logger log = LoggerFactory.getLogger(StatisticsApi.class.getName());

    @Reference
    StatisticsService statisticsService;

    @Context
    private HttpServletRequest httpServletRequest;

    private static final String ENDPOINT_BASE = "/o/commitment2050-service/statisticsapi";

    private static final String ENDPOINT_COMMITMENT_SUM_DAILY = "/commitments/cumulativesum";
    private static final String ENDPOINT_COMMITMENT_COUNT_BY_MAIN_GOAL = "/commitments/countsbymaingoal";
    private static final String ENDPOINT_COMMITMENT_COUNT_BY_ORGANIZATION_TYPE = "/commitments/countsbyorganizationtype";
    private static final String ENDPOINT_COMMITMENT_COUNT_BY_STARTING_YEAR = "/commitments/countsbystartingyear";
    private static final String ENDPOINT_COMMITMENT_COUNT_TOP_LIFESTYLE_COMMITMENTS = "/commitments/getTopLifestyleCommitments";

    private static final String ENDPOINT_CA_RESULT_TYPES = "/results/resultTypes";
    private static final String ENDPOINT_CA_RESULT_GET_BY_TYPE = "/results/get/{resultType}";

    private static Gson gson = new GsonBuilder().create();


    @GET
    @Produces("text/plain")
    public String working() {
        return "It works!";
    }

    /**
     * List statistics endpoint urls
     * @return list of statistics endpoint urls
     */
    @GET
    @Path("/help")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEndpointDescriptions() {
        List<String> urls = new ArrayList();
        urls.add(ENDPOINT_BASE + ENDPOINT_COMMITMENT_SUM_DAILY);
        urls.add(ENDPOINT_BASE + ENDPOINT_COMMITMENT_COUNT_BY_MAIN_GOAL);
        urls.add(ENDPOINT_BASE + ENDPOINT_CA_RESULT_TYPES);
        return Response.ok(gson.toJson(urls)).build();
    }

    /**
     * Get cumulative sum of commitments
     * @return List of cumulatative sum of commitments
     */
    @GET
    @Path(ENDPOINT_COMMITMENT_SUM_DAILY)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCumulativeSum() {
        List<StatisticsValueAndDate> stats = statisticsService.getCommitmentsCreatedStatistics();
        return Response.ok(gson.toJson(stats)).build();
    }

    /**
     * Get commmitment count by main goal
     * @return List of date / cumulative sum tuples
     */
    @GET
    @Path(ENDPOINT_COMMITMENT_COUNT_BY_MAIN_GOAL)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommitmentCountsByMainGoal() {
        List<CommitmentCountByMainGoal> stats = statisticsService.getCommitmentCountsByMainGoal();
        return Response.ok(gson.toJson(stats)).build();
    }

         /** Get commmitment count by organization
     * @return List of date / cumulative sum tuples
     */
    @GET
    @Path(ENDPOINT_COMMITMENT_COUNT_BY_ORGANIZATION_TYPE)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommitmentCountsByOrganization() {
        List<CommitmentCountByMainGoal> stats = statisticsService.getCommitmentCountsByOrganizationType();
        return Response.ok(gson.toJson(stats)).build();
    }

    /**
     * Get commmitment count by starting year
     * @return List of dates and counts
     */
    @GET
    @Path(ENDPOINT_COMMITMENT_COUNT_BY_STARTING_YEAR)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommitmentCountsByStartingYear() {
        List<StatisticsValueAndDate> stats = statisticsService.getCommitmentCountsByStartingYear();
        return Response.ok(gson.toJson(stats)).build();
    }

    @GET
    @Path(ENDPOINT_CA_RESULT_TYPES)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCAResultTypes() {
        List<String> types = CommitmentAnalysisResultLocalServiceUtil.getCAResultTypes();
        return Response.ok(gson.toJson(types)).build();
    }

    @GET
    @Path(ENDPOINT_CA_RESULT_GET_BY_TYPE)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCAResultByType(@PathParam("resultType") String resultType) {
        if (resultType == null || resultType.length() == 0) {
            throw new BadRequestException("resultType was not defined");
        }
        CommitmentAnalysisResult result = CommitmentAnalysisResultLocalServiceUtil.getResultByResultType(resultType);
        return Response.ok(gson.toJson(result)).build();
    }

    @GET
    @Path(ENDPOINT_COMMITMENT_COUNT_TOP_LIFESTYLE_COMMITMENTS)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopLifestyleCommitments() {
        List commitments =  CommitmentAnalysisResultLocalServiceUtil.getTopLifestyleCommitments();

        return Response.ok(gson.toJson(commitments)).build();
    }

}
