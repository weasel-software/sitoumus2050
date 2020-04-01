package com.soikea.commitment2050.service;

import com.soikea.commitment2050.model.CommitmentCountByMainGoal;
import com.soikea.commitment2050.model.StatisticsValueAndDate;

import java.util.List;

public interface StatisticsService {
    /**
     * Get cumulative sum of commitments
     * @return List of cumulatative sum of commitments
     */
    List<StatisticsValueAndDate> getCommitmentsCreatedStatistics();

    /**
     * Get commitment count by main goal category
     * @return list of commitment counts by main goals
     */
    List<CommitmentCountByMainGoal> getCommitmentCountsByMainGoal();

    /**
     * Get commitment count by main goal category
     * @return list of commitment counts by main goals
     */
    List<CommitmentCountByMainGoal> getCommitmentCountsByOrganizationType();


    /**
     * Get commitment count by starting year
     * @return list of commitment counts by main goals
     */
    List<StatisticsValueAndDate> getCommitmentCountsByStartingYear();
}
