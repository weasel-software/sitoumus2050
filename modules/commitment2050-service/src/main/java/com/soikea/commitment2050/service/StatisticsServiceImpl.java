package com.soikea.commitment2050.service;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.util.comparator.ArticleCreateDateComparator;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.soikea.commitment2050.api.OrganizationApi;
import com.soikea.commitment2050.model.CommitmentCountByMainGoal;
import com.soikea.commitment2050.model.CommitmentSort;
import com.soikea.commitment2050.model.StatisticsValueAndDate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component(immediate = true, service = StatisticsService.class)
public class StatisticsServiceImpl implements StatisticsService {
    private static Logger log = LoggerFactory.getLogger(OrganizationApi.class.getName());

    @Reference
    CommitmentService commitmentService;

    @Override
    public List<StatisticsValueAndDate> getCommitmentsCreatedStatistics() {
        OrderByComparator odc = new ArticleCreateDateComparator(true);
        Map<String, StatisticsValueAndDate> statistics = new LinkedHashMap<>();
        List<JournalArticle> articles = JournalArticleLocalServiceUtil.getArticlesByStructureId(
                Constants.SITOUMUS2050_GROUP_ID, Constants.GLOBAL_COMMITMENT_DDM_STRUCTURE_KEY,
                WorkflowConstants.STATUS_APPROVED,-1, -1, odc);
        double i = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String storableDate = "", currentDate = "";
        JournalArticle tempArticle = null;
        for (JournalArticle article : articles) {
            //Check if map already contains another version of commitment (createDate should be same across different versions...)
            if ( !statistics.containsKey(article.getArticleId())) {
                i++;
                currentDate = simpleDateFormat.format(article.getCreateDate());
                if( storableDate == ""){
                    storableDate = currentDate;
                }else if( !currentDate.equals(storableDate) ){
                    StatisticsValueAndDate svad = new StatisticsValueAndDate(i, storableDate);
                    statistics.put(article.getArticleId(), svad);
                    storableDate = currentDate;
                    tempArticle = article;
                }

            }
        }
        StatisticsValueAndDate svad = new StatisticsValueAndDate(i, storableDate);
        statistics.put(tempArticle.getArticleId(), svad);

        AssetCategoryLocalServiceUtil.getVocabularyCategories(Constants.PRIMARY_GOALS_VOCABULARY_ID, -1, -1, null );
        return new ArrayList<StatisticsValueAndDate>(statistics.values());

    }

    @Override
    public List<CommitmentCountByMainGoal> getCommitmentCountsByMainGoal() {
        List<CommitmentCountByMainGoal> countsByMainGoal = new ArrayList<CommitmentCountByMainGoal>();
        try {
            List<AssetCategory> mainGoals = AssetCategoryLocalServiceUtil.getVocabularyCategories(Constants.PRIMARY_GOALS_VOCABULARY_ID, -1, -1, null );

            //Get main goals
            for ( AssetCategory ass : mainGoals) {
                int count = commitmentService.countPublishedCommitmentsByCategory(ass.getCategoryId());
                CommitmentCountByMainGoal commitmentCountByMainGoal = new CommitmentCountByMainGoal();
                commitmentCountByMainGoal.setCategoryId(ass.getCategoryId());
                commitmentCountByMainGoal.setCategoryname(ass.getTitleCurrentValue());
                commitmentCountByMainGoal.setAmountOfCommitments(count);
                countsByMainGoal.add(commitmentCountByMainGoal);
            }

        } catch (SearchException e) {
            log.error("Get commitments count by main goal failed", e);
        }

        return countsByMainGoal;

    }

    @Override
    public List<CommitmentCountByMainGoal> getCommitmentCountsByOrganizationType() {
        List<CommitmentCountByMainGoal> countsByOrganizationType = new ArrayList<CommitmentCountByMainGoal>();
        try {
            List<AssetCategory> organizationTypes = AssetCategoryLocalServiceUtil.getVocabularyCategories(0, Constants.USER_TYPE_VOCABULARY_ID, -1, -1, null );

            //Get main goals
            for ( AssetCategory ass : organizationTypes) {
                int count = commitmentService.countPublishedCommitmentsByCategory(ass.getCategoryId());
                CommitmentCountByMainGoal commitmentCountByOrganizationTypes = new CommitmentCountByMainGoal();
                commitmentCountByOrganizationTypes.setCategoryId(ass.getCategoryId());
                commitmentCountByOrganizationTypes.setCategoryname(ass.getTitleCurrentValue());
                commitmentCountByOrganizationTypes.setAmountOfCommitments(count);
                countsByOrganizationType.add(commitmentCountByOrganizationTypes);
            }

        } catch (SearchException e) {
            log.error("Get commitments count by main goal failed", e);
        }

        return countsByOrganizationType;

    }

    @Override
    public List<StatisticsValueAndDate> getCommitmentCountsByStartingYear() {
        OrderByComparator odc = new ArticleCreateDateComparator(true);
        Map<String, StatisticsValueAndDate> statistics = new LinkedHashMap<>();
        List<JournalArticle> articles = JournalArticleLocalServiceUtil.getArticlesByStructureId(
                Constants.SITOUMUS2050_GROUP_ID, Constants.GLOBAL_COMMITMENT_DDM_STRUCTURE_KEY,
                WorkflowConstants.STATUS_APPROVED,-1, -1, odc);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String storableYear = "", currentYear = "";
        double i = 0;
        JournalArticle tempArticle = null;
        for (JournalArticle article : articles) {
            //Check if map already contains another version of commitment (createDate should be same across different versions...)
            if ( !statistics.containsKey(article.getArticleId())) {
                i++;
                tempArticle = article;
                currentYear = simpleDateFormat.format(article.getCreateDate());
                if( storableYear == "" ){
                    storableYear = currentYear;
                }else if( !currentYear.equals(storableYear) ){
                    StatisticsValueAndDate svad = new StatisticsValueAndDate(i, storableYear);
                    statistics.put(article.getArticleId(), svad);

                    i = 0;
                    storableYear = currentYear;
                }
            }
        }
        StatisticsValueAndDate svad = new StatisticsValueAndDate(i, storableYear);
        statistics.put(tempArticle.getArticleId(), svad);

        AssetCategoryLocalServiceUtil.getVocabularyCategories(Constants.PRIMARY_GOALS_VOCABULARY_ID, -1, -1, null );
        return new ArrayList<StatisticsValueAndDate>(statistics.values());

    }

}
