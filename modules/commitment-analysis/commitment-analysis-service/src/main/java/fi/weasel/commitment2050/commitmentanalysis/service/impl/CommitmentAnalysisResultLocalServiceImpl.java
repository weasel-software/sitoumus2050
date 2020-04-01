/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package fi.weasel.commitment2050.commitmentanalysis.service.impl;

import fi.weasel.commitment2050.commitmentanalysis.model.CAResultType;
import fi.weasel.commitment2050.commitmentanalysis.model.Commitment;
import fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult;
import fi.weasel.commitment2050.commitmentanalysis.model.impl.CommitmentAnalysisResultImpl;
import fi.weasel.commitment2050.commitmentanalysis.service.base.CommitmentAnalysisResultLocalServiceBaseImpl;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.CommitmentAnalysisResultFinder;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.CommitmentFinder;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.impl.CommitmentAnalysisResultFinderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.*;

/**
 * The implementation of the commitment analysis results local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link fi.weasel.commitment2050.commitmentanalysis.service.CommitmentAnalysisResultLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CommitmentAnalysisResultLocalServiceBaseImpl
 * @see fi.weasel.commitment2050.commitmentanalysis.service.CommitmentAnalysisResultLocalServiceUtil
 */
public class CommitmentAnalysisResultLocalServiceImpl
        extends CommitmentAnalysisResultLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. Always use {@link fi.weasel.commitment2050.commitmentanalysis.service.CommitmentAnalysisResultLocalServiceUtil} to access the commitment analysis results local service.
     */

    private static Logger _logger = LoggerFactory.getLogger(CommitmentAnalysisResultLocalServiceImpl.class);

    public List<String> getCAResultTypes() {
        List<String> types = new ArrayList<>();
        for (CAResultType t : CAResultType.values())
            types.add(t.name());
        return types;
    }

    public CommitmentAnalysisResult getResultByResultType(String resultType) {
        return commitmentAnalysisResultFinder.findLatestByResultType(resultType);
    }

    public List<List<String>> getTopLifestyleCommitments() {
        List<String> types = getCAResultTypes();
        List<List<String>> commitments = new ArrayList<List<String>>();
        for (String type : types) {
            if (type.startsWith("COUNT_PERSONAL_COMMITMENTS_WILL_")) {
                CommitmentAnalysisResult result = getResultByResultType(type);
                if (result != null) {
                    List<String> pair = new ArrayList<String>();
                    pair.add(type);
                    pair.add(getResultByResultType(type).getResultData());
                    commitments.add(pair);
                }
            }
        }
        return commitments;
    }

    public void performResultCalculations() {
        // Total emissions reduced

        //CommitmentFinder commitmentFinder = new CommitmentFinderImpl();

        addCommitmentAnalysisResult(getTotalCarbonReducedKg(commitmentFinder));
        addCommitmentAnalysisResult(countApprovedPersonalCommitmentsFromTest(commitmentFinder));

        addCommitmentAnalysisResult(countApprovedCommitmentsOfTypes(
        		commitmentFinder, CAResultType.COUNT_APPROVED_COMMITMENTS_PERSONAL,
				new String[]{"COMMITMENT"}, false));

        addCommitmentAnalysisResult(countApprovedCommitmentsOfTypes(
        		commitmentFinder, CAResultType.COUNT_APPROVED_COMMITMENTS_ORGANIZATION,
				new String[]{"COMMITMENT"}, true));

        addCommitmentAnalysisResult(countApprovedCommitmentsOfTypes(
        		commitmentFinder, CAResultType.COUNT_APPROVED_COMMITMENTS_NUTRITION,
				new String[]{"NUTRITION"}, true));

        addCommitmentAnalysisResult(countApprovedCommitmentsOfTypes(
        		commitmentFinder, CAResultType.COUNT_APPROVED_GREEN_DEAL_TOTAL,
				new String[]{"GREEN_DEAL", "AUTOMOTIVE_INDUSTRY", "WORK_MACHINE", "PLASTIC_BAG", "OIL_INDUSTRY", "DEMOLITION"}, true));

        addCommitmentAnalysisResult(countApprovedCommitmentsOfTypes(
        		commitmentFinder, CAResultType.COUNT_APPROVED_GREEN_DEAL_OIL_INDUSTRY,
				new String[]{"OIL_INDUSTRY"}, true));

        addCommitmentAnalysisResult(countApprovedCommitmentsOfTypes(
        		commitmentFinder, CAResultType.COUNT_APPROVED_GREEN_DEAL_AUTOMOTIVE_INDUSTRY,
				new String[]{"AUTOMOTIVE_INDUSTRY"}, true));

        addCommitmentAnalysisResult(countApprovedCommitmentsOfTypes(
        		commitmentFinder, CAResultType.COUNT_APPROVED_GREEN_DEAL_WORK_MACHINE,
				new String[]{"WORK_MACHINE"}, true));

        addCommitmentAnalysisResult(countApprovedCommitmentsOfTypes(
        		commitmentFinder, CAResultType.COUNT_APPROVED_GREEN_DEAL_PLASTIC_BAG,
				new String[]{"PLASTIC_BAG"}, true));

        addCommitmentAnalysisResult(countApprovedCommitmentsOfTypes(
        		commitmentFinder, CAResultType.COUNT_APPROVED_GREEN_DEAL_DEMOLITION,
				new String[]{"DEMOLITION"}, true));

        addCommitmentAnalysisResult(countTopLifestyleCommitmentByMeterId(
                commitmentFinder,
                CAResultType.COUNT_PERSONAL_COMMITMENTS_WILL_CHOOSE_LOCAL_FOOD,
                120512));

        addCommitmentAnalysisResult(countTopLifestyleCommitmentByMeterId(
                commitmentFinder,
                CAResultType.COUNT_PERSONAL_COMMITMENTS_WILL_DO_VEGAN_CHALLENGE,
                120576));

        addCommitmentAnalysisResult(countTopLifestyleCommitmentByMeterId(
                commitmentFinder,
                CAResultType.COUNT_PERSONAL_COMMITMENTS_WILL_REPAIR_GOOD_PRODUCTS,
                120696));
    }

    private CommitmentAnalysisResult getTotalCarbonReducedKg(CommitmentFinder finder) {
        CAResultType type = CAResultType.TOTAL_CARBON_REDUCED_KG;

        try {
            Double reductionKg = finder.getAverageReductionKg();

            return new CommitmentAnalysisResultImpl(type, reductionKg.toString(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new CommitmentAnalysisResultImpl(type, "-", false);
    }

    private CommitmentAnalysisResult countApprovedPersonalCommitmentsFromTest(CommitmentFinder finder) {
        CAResultType type = CAResultType.COUNT_APPROVED_COMMITMENTS_FROM_LIFESTYLE_TEST;

        try {
            BigInteger count = finder.getCommitmentCountLifestyleTest();

            return new CommitmentAnalysisResultImpl(type, count.toString(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new CommitmentAnalysisResultImpl(type, "-", false);
    }

    private CommitmentAnalysisResult countApprovedCommitmentsOfTypes(CommitmentFinder finder, CAResultType resultType, String[] commitmentTypes, boolean organization) {
		try {
			BigInteger count = finder.getCommitmentCountByTypeAndOrgIdNullOrNotNull(commitmentTypes, organization);

			return new CommitmentAnalysisResultImpl(resultType, count.toString(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CommitmentAnalysisResultImpl(resultType, "-", false);
	}

	private CommitmentAnalysisResult countTopLifestyleCommitmentByMeterId(CommitmentFinder finder, CAResultType resultType, long meterId) {
        try {
            BigInteger count = finder.getWillDoLifestyleCommitmentCountByMeterId(meterId);

            return new CommitmentAnalysisResultImpl(resultType, count.toString(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CommitmentAnalysisResultImpl(resultType, "-", false);
    }
}