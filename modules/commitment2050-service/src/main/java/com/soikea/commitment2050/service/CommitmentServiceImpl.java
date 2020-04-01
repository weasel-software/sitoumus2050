package com.soikea.commitment2050.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liferay.asset.kernel.model.*;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetCategoryPropertyLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetLinkLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyServiceUtil;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleResource;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalArticleResourceLocalServiceUtil;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.*;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.search.facet.AssetEntriesFacet;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.faceted.searcher.FacetedSearcher;
import com.liferay.portal.kernel.search.facet.faceted.searcher.FacetedSearcherManager;
import com.liferay.portal.kernel.search.facet.faceted.searcher.FacetedSearcherManagerUtil;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.TermQueryImpl;
import com.liferay.portal.kernel.search.generic.TermRangeQueryImpl;
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.ratings.kernel.model.RatingsEntry;
import com.liferay.ratings.kernel.service.RatingsEntryLocalServiceUtil;
import com.soikea.commitment2050.model.*;
import fi.weasel.commitment2050.commitmentanalysis.service.*;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.internet.AddressException;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static com.soikea.commitment2050.model.CommitmentSort.latest;
import static com.soikea.commitment2050.service.Constants.*;
import static com.soikea.commitment2050.service.LFArticleXMLToJsonTransformer.*;


@Component(immediate = true, service = CommitmentService.class)
public class CommitmentServiceImpl implements CommitmentService {

    private static Logger _logger = LoggerFactory.getLogger(CommitmentServiceImpl.class.getName());

    @Reference
    UserService userService;

    @Override
    public void addVocabularyCategoriesToCommitmentByCategoryId(final long vocabularyId, JournalArticle journalArticle, long[] categoryIds) throws PortalException {
        long[] currentCategoryIds = AssetCategoryLocalServiceUtil.getCategoryIds(JournalArticle.class.getName(), journalArticle.getResourcePrimKey());
        long[] mergedCategoryIds = Stream.of(currentCategoryIds, categoryIds).flatMapToLong(LongStream::of).toArray();
        JournalArticleLocalServiceUtil.updateAsset(ADMIN_USER_ID, journalArticle, mergedCategoryIds, null,
                null, 0d);
    }

    @Override
    public void addVocabularyCategoryToCommitmentByCategoryId(final long vocabularyId, JournalArticle journalArticle, long categoryId) throws PortalException {
        addVocabularyCategoriesToCommitmentByCategoryId(vocabularyId, journalArticle, new long[]{categoryId});
    }

    @Override
    public void addVocabularyCategoriesToCommitmentByCategoryName(final long vocabularyId, JournalArticle journalArticle, final Set<String> categoryNames) throws PortalException {
        Objects.nonNull(journalArticle);
        AssetCategoryLocalServiceUtil.getCategoryIds(JournalArticle.class.getName(), journalArticle.getResourcePrimKey());

        List<AssetCategory> vocabularyCategories = AssetCategoryLocalServiceUtil.getVocabularyCategories(vocabularyId, 0, 200, null);
        long[] categoryIdsToBeAdded = vocabularyCategories.stream()
                .filter(c -> categoryNames.contains(c.getTitle("fi_FI")))
                .map(AssetCategoryModel::getCategoryId)
                .mapToLong(Long::longValue)
                .toArray();

        if(categoryNames != null && categoryNames.size() != categoryIdsToBeAdded.length) {
            _logger.warn("Tried to add {} categories by name, but only found {} categories that matched the given names", categoryNames.size(), categoryIdsToBeAdded.length);
        }

        long[] currentCategoryIds = AssetCategoryLocalServiceUtil.getCategoryIds(JournalArticle.class.getName(), journalArticle.getResourcePrimKey());
        long[] mergedCategoryIds = Stream.of(currentCategoryIds, categoryIdsToBeAdded).flatMapToLong(LongStream::of).toArray();

        JournalArticleLocalServiceUtil.updateAsset(ADMIN_USER_ID, journalArticle, mergedCategoryIds, null,
                null, 0d);
    }


    @Override
    public void addVocabularyCategoryToCommitmentByCategoryName(final long vocabularyId, final JournalArticle journalArticle, String categoryName) throws PortalException {
        addVocabularyCategoriesToCommitmentByCategoryName(vocabularyId, journalArticle, Collections.singleton(categoryName));
    }


    @Override
    public JournalArticle createCommitment(Commitment commitment, int workflowAction, boolean autoArticleId) throws PortalException {
        validateCommitment(commitment);
        ServiceContext serviceContext = populateServiceContext(commitment.getCreatedByUserId(), workflowAction);
        Map<Locale, String> titleMap = getCommitmentTitleMap(commitment);

        Map<Locale, String> descriptionMap = null;
        long userId = commitment.getCreatedByUserId();
        long folderId = getCommitmentsFolderId(commitment);
        String articleId = "";
        if ( !autoArticleId ) {
            articleId = commitment.getId();
        }
        if (StringUtils.isNotBlank(commitment.getOrganizationName())) {
            updateCommitmentOrgId(commitment);
        }
        String xmlContent = getCommitmentXmlStructure(commitment);
        JournalArticle createdJournalArticle = JournalArticleLocalServiceUtil.addArticle(
                userId,
                SITOUMUS2050_GROUP_ID,
                folderId,
                0,
                0,
                articleId,
                autoArticleId,
                1.0d,
                titleMap,
                descriptionMap,
                xmlContent,
                GLOBAL_COMMITMENT_DDM_STRUCTURE_KEY,
                GLOBAL_COMMITMENT_DDM_TEMPLATE_KEY,
                null,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, true,
                0, 0, 0, 0, 0, true,
                true,
                false, null, null,
                null,
                null,
                serviceContext);

        // Update dates
        createdJournalArticle = updateCeatedAndUpdatedDates(commitment, createdJournalArticle);

        //Save original creator to expando values
        ExpandoValueLocalServiceUtil.addValue(COMPANY_ID, JournalArticle.class.getName(),
                ExpandoTableConstants.DEFAULT_TABLE_NAME, WEB_CONTENT_ARTICLE_CREATOR_USER_ID_EXPANDO_FIELD_NAME, createdJournalArticle.getId(), createdJournalArticle.getUserId());
        if ( StringUtils.isNotBlank(createdJournalArticle.getUserName()) && !"Puuttuva tieto Puuttuva tieto".equals(createdJournalArticle.getUserName())){
            ExpandoValueLocalServiceUtil.addValue(COMPANY_ID, JournalArticle.class.getName(),
                    ExpandoTableConstants.DEFAULT_TABLE_NAME, WEB_CONTENT_ARTICLE_CREATOR_USER_NAME_EXPANDO_FIELD_NAME, createdJournalArticle.getId(), createdJournalArticle.getUserName());
        }
        // Add organization ID to expando values
        if(commitment.getOrganizationId() != null && !commitment.getOrganizationId().isEmpty()) {
            ExpandoValueLocalServiceUtil.addValue(COMPANY_ID, JournalArticle.class.getName(),
                    ExpandoTableConstants.DEFAULT_TABLE_NAME, WEB_CONTENT_ARTICLE_ORGANIZATION_ID_EXPANDO_FIELD_NAME, createdJournalArticle.getId(), commitment.getOrganizationId());
        }
        updateCommitmentUserPortraitUrl(userId, createdJournalArticle);

        System.out.println(commitment.getOrganizationName());
        if (StringUtils.isNotBlank(commitment.getOrganizationName())) {
            updateCommitmentOrganizationAndCategories(commitment, userId, createdJournalArticle);
        } else{
            updateIndividualCommitmentCategories(commitment, userId, createdJournalArticle);
        }

        _logger.trace("Created commitment journal article with id {}", createdJournalArticle.getArticleId());
        reindexArticle(createdJournalArticle);
        return createdJournalArticle;
    }

    /**
     * Update commitment portrait url. Commitment contains information about creator portrait url, because otherwise
     * getting this information would create many calls to backend when showing list of commitments.
     * @param userId user id
     * @param article commitment article
     * @throws PortalException
     */
    private void updateCommitmentUserPortraitUrl(long userId, JournalArticle article) throws PortalException {
        String url = userService.getUserPortraitUrl(userId);
        if ( StringUtils.isNotBlank(url)) {
            ExpandoValueLocalServiceUtil.addValue(COMPANY_ID, JournalArticle.class.getName(),
                    ExpandoTableConstants.DEFAULT_TABLE_NAME, WEB_CONTENT_ARTICLE_CREATOR_PORTRAIT_EXPANDO_FIELD_NAME, article.getId(), url);
        }
    }

    /**
     * Update commitment organization logo url. Commitment contains information about organization logo url, because otherwise
     * getting this information would create many calls to backend when showing list of commitments.
     * @param organizationId organization id
     * @param article
     * @throws PortalException
     */
    private void updateCommitmentOrganizationLogoUrl(long organizationId, JournalArticle article) throws PortalException {
        String url = userService.getOrganizationLogoUrl(organizationId);
        if ( StringUtils.isNotBlank(url)) {
            ExpandoValueLocalServiceUtil.addValue(COMPANY_ID, JournalArticle.class.getName(),
                    ExpandoTableConstants.DEFAULT_TABLE_NAME, WEB_CONTENT_ARTICLE_ORGANIZATION_LOGO_EXPANDO_FIELD_NAME, article.getId(), url);
        }
    }

    private void validateCommitment(Commitment commitment) throws PortalException {
        String organizationName = commitment.getOrganizationName();
        if (StringUtils.isNotBlank(organizationName)) {
            //Check that organization exists
            OrganizationLocalServiceUtil.getOrganization(Constants.COMPANY_ID, organizationName );
        }
    }


    /**
     * Update commitment categories for individuals.
     * Note! This might be a temporary solution until Ville brings more knowledge.
     * @param commitment    commitment
     * @param userId    userid
     * @param article commitment article
     * @throws PortalException @see {@link PortalException}
     */
    private void updateIndividualCommitmentCategories(Commitment commitment, long userId, JournalArticle article) throws PortalException {

        Set<Long> catset = new HashSet();
        if ( commitment.getCategoryIds() != null && commitment.getCategoryIds().size() > 0 ) {
            catset.addAll(commitment.getCategoryIds());
            //make sure commitment user type is set to individual
            catset.add(Constants.USER_TYPE_PRIVATE_PERSON);
            saveCommitmentCategories(userId, article, catset);
        }
    }


    /**
     * Update commitment organization information and copy organization categories to commitment.
     * Note! Commitment must contain organization name, otherwise this will do nothing
     * Copying some of the information directly to commitment article will speed up things when searching commitments
     * @param commitment    commitment
     * @param userId    userid
     * @param article commitment article
     * @throws PortalException @see {@link PortalException}
     */
    private void updateCommitmentOrganizationAndCategories(Commitment commitment, long userId, JournalArticle article) throws PortalException {

        Set<Long> catset = new HashSet();
        String organizationName = commitment.getOrganizationName();
        Long organizationId = null;
        if (StringUtils.isNotBlank(organizationName)) {
            Organization org = OrganizationLocalServiceUtil.getOrganization(Constants.COMPANY_ID, organizationName);
            organizationId = org.getOrganizationId();
            long[] orgcatsarray = AssetCategoryLocalServiceUtil.getCategoryIds(Organization.class.getName(), org.getPrimaryKey());
            List<Long> tmplist = Arrays.stream(orgcatsarray).boxed().collect(Collectors.toList());
            catset.addAll(tmplist);
        }

        // Update organization logo url to commitment (this will speed up things for example when listing commitments)
        if ( organizationId != null ) {
            updateCommitmentOrganizationLogoUrl(organizationId, article);
        }

        // Add organization reference to an expando field
        ExpandoValueLocalServiceUtil.addValue(COMPANY_ID, JournalArticle.class.getName(),
                ExpandoTableConstants.DEFAULT_TABLE_NAME, WEB_CONTENT_ARTICLE_ORGANIZATION_NAME_EXPANDO_FIELD_NAME, article.getId(), organizationName);

        //Update categories
        if ( catset != null && catset.size() > 0 ) {
            //merge exitings categories so we don't remove anything... //Etteplan comment...

            //This check broke the search for a bunch of commitments because it removed the existing categories (meta data) from commitments where org. category was not set... (Anders 22.9.19)

        }
        if ( commitment.getCategoryIds() != null && commitment.getCategoryIds().size() > 0 ) {
            catset.addAll(commitment.getCategoryIds());
        }
        saveCommitmentCategories(userId, article, catset);
    }

    /**
     * Update commitment organization ID information
     * @param commitment    commitment
     * @throws PortalException @see {@link PortalException}
     */
    private Commitment updateCommitmentOrgId(Commitment commitment) throws PortalException {

        String organizationName = commitment.getOrganizationName();
        Long organizationId = null;
        if (StringUtils.isNotBlank(organizationName)) {
            Organization org = OrganizationLocalServiceUtil.getOrganization(Constants.COMPANY_ID, organizationName);
            organizationId = org.getOrganizationId();
            commitment.setOrganizationId(organizationId.toString());
        }

        return commitment;
    }

    /**
     * Save commitment categories.
     * @param userId userId
     * @param article article
     * @param categoryIds category ids
     * @throws PortalException
     */
    private void saveCommitmentCategories(long userId, JournalArticle article, Set<Long> categoryIds) throws PortalException {
        Set<Long> tmpSet = sanitizeCategorySet(categoryIds);
        long[] categoryArray = tmpSet.stream().mapToLong(l -> l).toArray();

        JournalArticleLocalServiceUtil.updateAsset(userId, article, categoryArray, null,
                null, 0d);
    }

    /**
     * Check that each category exists. If category is not found removes id from set.
     * Returns sanitazed set of categoryids, which is safe to save
     * @param categoryIds
     * @return
     */
    private Set sanitizeCategorySet(Set<Long> categoryIds) {
        Set<Long> tmpSet = new HashSet();
        for (Long catId : categoryIds ) {
            try {
                AssetCategoryLocalServiceUtil.getAssetCategory(catId);
                tmpSet.add(catId);
            } catch (PortalException e) {
                _logger.error("While saving commitment categories, A asset category with the primary key" + catId + " could not be found." +
                        "Ignoring missing catId ", e);
            }
        }
        return tmpSet;
    }

    /**
     * Reindex article, this should be called after creating and updating articles programmatically.
     * Otherwise modified information won't be in indexes and thus search do not show newest content.
     * @param article
     */
    private void reindexArticle(JournalArticle article) {
        try {
            Indexer<JournalArticle> indexer = IndexerRegistryUtil.getIndexer(JournalArticle.class);
            indexer.reindex(article);
        } catch (Exception e) {
            _logger.error("Article reindexing failed! articleId: " + article.getArticleId());
        }
    }

    /**
     * Reindex entry, this should be called after creating and updating entry programmatically.
     * Otherwise modified information won't be in indexes and thus search do not show newest content.
     * @param assetEntry assetEntry
     */
    private void reindexAssetEntry(AssetEntry assetEntry) {
        try {
            Indexer<AssetEntry> indexer = IndexerRegistryUtil.getIndexer(AssetEntry.class);
            indexer.reindex(assetEntry);
        } catch (Exception e) {
            _logger.error("AssetEntry reindexing failed! entryId: " + assetEntry.getEntryId());
        }
    }


    @Override
    public JournalArticle updateCommitment(Commitment commitment, int workflowAction) throws PortalException {

        ServiceContext serviceContext = populateServiceContext(commitment.getCreatedByUserId(), workflowAction);
        //commitment.getOrganizationId() should be Long but using string because xml.. TODO: Change to Long when Commitment model is remade
        boolean newOrgId = false;
        if(commitment.getOrganizationId() != null && commitment.getOrganizationId().isEmpty() && commitment.getOrganizationName() != null && !commitment.getOrganizationName().isEmpty()) {
            updateCommitmentOrgId(commitment);
            newOrgId = true;
        }
        //Bugfix, liferay removes sometimes related articles when updating...
        List<JournalArticle> relatedArticles = getRelatedArticles(commitment.getId());

        Map<Locale, String> titleMap = getCommitmentTitleMap(commitment);

        Map<Locale, String> descriptionMap = null;
        long userId = commitment.getCreatedByUserId();
        long folderId = getCommitmentsFolderId(commitment);
        String articleId = commitment.getId();
        double version = commitment.getVersion();
        String xmlContent = getCommitmentXmlStructure(commitment);
        JournalArticle createdJournalArticle = JournalArticleLocalServiceUtil.updateArticle(
                userId,
                SITOUMUS2050_GROUP_ID,
                folderId,
                articleId,
                version,
                titleMap,
                descriptionMap,
                xmlContent,
                null,
                serviceContext
        );

        // Add organization ID reference to an expando field for this article, if org.id was missing from before ( search would not be able to find the article based on org.id. search )
        if(newOrgId) {
            ExpandoValueLocalServiceUtil.addValue(COMPANY_ID, JournalArticle.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, WEB_CONTENT_ARTICLE_ORGANIZATION_ID_EXPANDO_FIELD_NAME, createdJournalArticle.getId(), commitment.getOrganizationId());
        }
        updateCommitmentUserPortraitUrl(userId, createdJournalArticle);


        if (StringUtils.isNotBlank(commitment.getOrganizationName())) {
            // Add organization reference to an expando field
            updateCommitmentOrganizationAndCategories(commitment, userId, createdJournalArticle);
        } else{
            updateIndividualCommitmentCategories(commitment, userId, createdJournalArticle);
        }

        //update relatedarticles (reports) and child commitments (joined) links (//Bugfix, liferay removes sometimes related articles when updating...)
        for (JournalArticle a : relatedArticles) {
            if (!createdJournalArticle.getArticleId().equals(a.getArticleId())) {
                if ( GLOBAL_REPORT_DDM_STRUCTURE_KEY.equals(a.getDDMStructureKey())) {
                    _logger.debug("DDMStructureKey for report: " + a.getDDMStructureKey());
                    try {
                        addRelatedRelationshipLink(createdJournalArticle.getArticleId(), a.getArticleId());
                    } catch (Exception e) {
                        _logger.debug("Reportlink already exists, skipping add");
                    }
                }
                else if ( GLOBAL_COMMITMENT_DDM_STRUCTURE_KEY.equals(a.getDDMStructureKey())) {
                    _logger.debug("DDMStructureKey for commitment: " + a.getDDMStructureKey());
                    try {
                        joinToParentCommitment(createdJournalArticle.getArticleId(), a.getArticleId());
                    } catch (Exception e) {
                        _logger.debug("Parent, child commitment link already exits, skipping add");
                    }
                }
            }
        }

        //update priority
        getCommitmentAssetLinkChildsAndUpdatePriority(createdJournalArticle.getArticleId());

        _logger.trace("Updated commitment journal article with id {}", createdJournalArticle.getArticleId());

        //reindex commitment
        reindexArticle(createdJournalArticle);

        return createdJournalArticle;
    }

    private Map<Locale, String> getCommitmentTitleMap(Commitment commitment) {
        Map<Locale, String> titleMap = new HashMap<>();
        if ( StringUtils.isBlank(commitment.getTitle_fi_FI()) ) {
            commitment.setTitle_fi_FI(Constants.GENERIC_COMMITMENT_FI_TITLE);
        }

        titleMap.put(locale_fi, commitment.getTitle_fi_FI());
        if ( StringUtils.isNotBlank(commitment.getTitle_en_US())) titleMap.put(locale_en, commitment.getTitle_en_US());
        if ( StringUtils.isNotBlank(commitment.getTitle_sv_SE()) ) titleMap.put(locale_sv, commitment.getTitle_sv_SE());
        return titleMap;
    }

    @Override
    public List<JournalArticle> createCommitmentReports(Commitment commitment, int workflowAction, boolean autoArticleId) throws PortalException {
        List<JournalArticle> createdReports = new ArrayList<>();
        if ( commitment.getOperations() != null && commitment.getOperations().size() > 0) {
            if (_logger.isDebugEnabled()) {
                _logger.debug("adding commitment ");
            }
            for ( Operation operation : commitment.getOperations()) {
                for ( Report report : operation.getReports() ) {
                    Map<Locale, String> titleMap = createReportOperationTitleMap(commitment, operation.getOperationTitle_fi_FI(), report);
                    JournalArticle createdJournalArticle = createReport(commitment, workflowAction, autoArticleId, report, titleMap);

                    createdReports.add(createdJournalArticle);
                    if (_logger.isTraceEnabled()) {
                        _logger.trace("Created report journal article with id {}", createdJournalArticle.getArticleId());
                    }
                }
            }
        }
        return createdReports;
    }

    @Override
    public List<JournalArticle> createCommitmentGenericReports(Commitment commitment, int workflowAction, boolean autoArticleId) throws PortalException {
        List<JournalArticle> createdReports = new ArrayList<>();
        if ( commitment.getGenericReports() != null && commitment.getGenericReports().size() > 0) {
            if (_logger.isDebugEnabled()) {
                _logger.debug("adding commitment ");
            }
            for ( Report report : commitment.getGenericReports()) {
                Map<Locale, String> titleMap = createReportOperationTitleMap(commitment, "generic", report);
                JournalArticle createdJournalArticle = createReport(commitment, workflowAction, autoArticleId, report, titleMap);

                createdReports.add(createdJournalArticle);
                if (_logger.isTraceEnabled()) {
                    _logger.trace("Created report journal article with id {}", createdJournalArticle.getArticleId());
                }
            }
        }
        return createdReports;
    }


    private Map<Locale, String> createReportOperationTitleMap(Commitment commitment, String titlePrefix, Report report) {
        Map<Locale, String> titleMap = new HashMap<>();

        if ( StringUtils.isBlank(report.getReportTitle_fi_FI()) ) {
            report.setReportTitle_fi_FI(Constants.GENERIC_REPORT_FI_TITLE);
        }
        String titlePrefix_fi = commitment.getTitle_fi_FI() + "-" + titlePrefix + " - ";
        String reportTitle_fi_fi = titlePrefix_fi + report.getReportTitle_fi_FI();
        titleMap.put(new Locale("fi", "FI"), reportTitle_fi_fi);

        if ( StringUtils.isNotBlank(report.getReportTitle_en_US())) {
            String titlePrefix_en = commitment.getTitle_fi_FI() + " - " + titlePrefix + " - ";
            String reportTitle_en_us = titlePrefix_en + report.getReportTitle_en_US();
            titleMap.put(new Locale("en", "US"), reportTitle_en_us);
        }
        if ( StringUtils.isNotBlank(report.getReportTitle_sv_SE()) ) {
            String titlePrefix_sv = commitment.getTitle_fi_FI() + "-" + titlePrefix + " - ";
            String reportTitle_sv_se = titlePrefix_sv + report.getReportTitle_sv_SE();
            titleMap.put(new Locale("sv", "SE"), reportTitle_sv_se);
        }
        return titleMap;
    }

    @Override
    public JournalArticle createCommitmentReport(Report report, int workflowAction, boolean autoArticleId) throws PortalException {
        //Get commitment information
        Commitment c = getCommitment( report.getCommitmentArticleId(), null, true );
        //if commitment found, proceed to report creating
        Map<Locale, String> titleMap = createReportOperationTitleMap(c, report.getOperationTitle_fi_FI(), report);
        return createReport(c, workflowAction, autoArticleId, report, titleMap);
    }

    private JournalArticle createReport(Commitment commitment, int workflowAction, boolean autoArticleId, Report report, Map<Locale, String> titleMap) throws PortalException {
        ServiceContext serviceContext = populateServiceContext(commitment.getCreatedByUserId(), workflowAction);
        if (_logger.isDebugEnabled()) {
            _logger.debug("titlemap" + titleMap);
        }
        Map<Locale, String> descriptionMap = null;
        long userId = commitment.getCreatedByUserId();
        long folderId = getReportsFolderId(commitment);
        String xmlContent = getReportXmlStructure(report);
        int workflow = workflowAction;
        if (!report.isReportStatus()) {
            workflow = WorkflowConstants.ACTION_SAVE_DRAFT;
        }

        String articleId = "";
        if ( !autoArticleId ) {
            articleId = report.getId();
        }
        JournalArticle createdJournalArticle = JournalArticleLocalServiceUtil.addArticle(
                userId,
                SITOUMUS2050_GROUP_ID,
                folderId,
                0,
                0,
                articleId,
                autoArticleId,
                1.0d,
                titleMap,
                descriptionMap,
                xmlContent,
                GLOBAL_REPORT_DDM_STRUCTURE_KEY,
                GLOBAL_REPORT_DDM_TEMPLATE_KEY,
                null,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, true,
                0, 0, 0, 0, 0, true,
                true,
                false, null, null,
                null,
                null,
                serviceContext);

        // Add organization reference to an expando field
        ExpandoValueLocalServiceUtil.addValue(COMPANY_ID, JournalArticle.class.getName(),
                ExpandoTableConstants.DEFAULT_TABLE_NAME, WEB_CONTENT_ARTICLE_ORGANIZATION_NAME_EXPANDO_FIELD_NAME, createdJournalArticle.getId(), commitment.getOrganizationName());
        //Save original creator to expando values
        ExpandoValueLocalServiceUtil.addValue(COMPANY_ID, JournalArticle.class.getName(),
                ExpandoTableConstants.DEFAULT_TABLE_NAME, WEB_CONTENT_ARTICLE_CREATOR_USER_ID_EXPANDO_FIELD_NAME, createdJournalArticle.getId(), createdJournalArticle.getUserId());
        if ( StringUtils.isNotBlank(createdJournalArticle.getUserName()) && !"Puuttuva tieto Puuttuva tieto".equals(createdJournalArticle.getUserName())){
            ExpandoValueLocalServiceUtil.addValue(COMPANY_ID, JournalArticle.class.getName(),
                    ExpandoTableConstants.DEFAULT_TABLE_NAME, WEB_CONTENT_ARTICLE_CREATOR_USER_NAME_EXPANDO_FIELD_NAME, createdJournalArticle.getId(), createdJournalArticle.getUserName());
        }

        addRelatedRelationshipLink(commitment.getId(), createdJournalArticle.getArticleId());

        reindexArticle(createdJournalArticle);

        updateCommitmentHasReportCategory(commitment);

        return createdJournalArticle;
    }

    @Override
    public JournalArticle updateCommitmentReport(Report report, int workflowAction) throws PortalException {
        //Get commitment information
        Commitment c = getCommitment( report.getCommitmentArticleId(), null, true );
        //if commitment found, proceed to report update
        Map<Locale, String> titleMap = createReportOperationTitleMap(c, report.getOperationTitle_fi_FI(), report);
        return updateReport(c, workflowAction, report, titleMap);
    }

    private JournalArticle updateReport(Commitment commitment, int workflowAction, Report report, Map<Locale, String> titleMap) throws PortalException {
        String articleId = report.getId();
        ServiceContext serviceContext = populateServiceContext(commitment.getCreatedByUserId(), workflowAction);

        Map<Locale, String> descriptionMap = null;
        long userId = commitment.getCreatedByUserId();
        long folderId = getReportsFolderId(commitment);
        double version = report.getVersion();
        String xmlContent = getReportXmlStructure(report);
        JournalArticle createdJournalArticle = JournalArticleLocalServiceUtil.updateArticle(
                userId,
                SITOUMUS2050_GROUP_ID,
                folderId,
                articleId,
                version,
                titleMap,
                descriptionMap,
                xmlContent,
                null,
                serviceContext);


        // Add organization reference to an expando field
        ExpandoValueLocalServiceUtil.addValue(COMPANY_ID, JournalArticle.class.getName(),
                ExpandoTableConstants.DEFAULT_TABLE_NAME, WEB_CONTENT_ARTICLE_ORGANIZATION_NAME_EXPANDO_FIELD_NAME, createdJournalArticle.getId(), commitment.getOrganizationName());
        //Save original creator to expando values
        ExpandoValueLocalServiceUtil.addValue(COMPANY_ID, JournalArticle.class.getName(),
                ExpandoTableConstants.DEFAULT_TABLE_NAME, WEB_CONTENT_ARTICLE_CREATOR_USER_ID_EXPANDO_FIELD_NAME, createdJournalArticle.getId(), createdJournalArticle.getUserId());
        if ( StringUtils.isNotBlank(createdJournalArticle.getUserName()) && !"Puuttuva tieto Puuttuva tieto".equals(createdJournalArticle.getUserName())){
            ExpandoValueLocalServiceUtil.addValue(COMPANY_ID, JournalArticle.class.getName(),
                    ExpandoTableConstants.DEFAULT_TABLE_NAME, WEB_CONTENT_ARTICLE_CREATOR_USER_NAME_EXPANDO_FIELD_NAME, createdJournalArticle.getId(), createdJournalArticle.getUserName());
        }


        try {
            addRelatedRelationshipLink(commitment.getId(), createdJournalArticle.getArticleId());
        } catch (PortalException e) {
            _logger.debug("Relationship between commitment and report already exists!", e);
        }

        reindexArticle(createdJournalArticle);

//        updateCommitmentHasReportCategory(commitment);

        return createdJournalArticle;
    }



    private void updateCommitmentHasReportCategory(Commitment commitment) throws PortalException {
        AssetCategory ac = null;
        try {
            ac = AssetCategoryLocalServiceUtil.getCategory(MISC_COMMITMENT_HAS_REPORT);
        } catch (Exception e) {
            _logger.warn("Current environment does not contain HasReport category, update db from production to solve this.");
        }
        if (ac != null ) {
            JournalArticle commitmentArticle = JournalArticleLocalServiceUtil.getLatestArticle(SITOUMUS2050_GROUP_ID, commitment.getId());
            addVocabularyCategoryToCommitmentByCategoryId(Constants.MISC_COMMITMENT_CATEGORIES_VOCABULARY_ID, commitmentArticle, MISC_COMMITMENT_HAS_REPORT);
            reindexArticle(commitmentArticle);
        }
    }

    private String getReportXmlStructure(Report report) {
        StringBuilder sb = new StringBuilder();

        String availableLocales = lang_fi; //fi is default lang
        if (StringUtils.isNotBlank(report.getReportTitle_en_US())) {
            availableLocales = availableLocales + ","+ lang_en;
        }
        if ( StringUtils.isNotBlank(report.getReportTitle_sv_SE())) {
            availableLocales = availableLocales + "," + lang_sv;
        }
        generateRootElement(availableLocales, sb);
        addDynamicElementAndContent(sb, "reportTitle", "text", "keyword", availableLocales, report.getReportTitle_fi_FI(), report.getReportTitle_en_US(), report.getReportTitle_sv_SE());
        addDynamicElementAndContent(sb, "commitmentArticleId", "text", "keyword", availableLocales, report.getCommitmentArticleId(), report.getCommitmentArticleId(), report.getCommitmentArticleId());
        addDynamicElementAndContent(sb, "operationTitle", "text", "keyword", availableLocales, report.getOperationTitle_fi_FI(), report.getOperationTitle_en_US(), report.getOperationTitle_sv_SE());
        addDynamicElementAndContent(sb, "commitmentOperationRefId", "text", "keyword", availableLocales, report.getCommitmentOperationRefId(), report.getCommitmentOperationRefId(), report.getCommitmentOperationRefId());

        addDynamicElementAndContent(sb, "reportStartDate", "ddm-date", "keyword", availableLocales, report.getReportStartDate(), report.getReportStartDate(), report.getReportStartDate());
        addDynamicElementAndContent(sb, "reportEndDate", "ddm-date", "keyword", availableLocales, report.getReportEndDate(), report.getReportEndDate(), report.getReportEndDate());

        for ( ReportMeter meter : report.getReportMeters() ) {
            addOpenDynamicElementAndContent(sb, "meterType", "text", "keyword", availableLocales, meter.getMeterType_fi_FI(), meter.getMeterType_en_US(), meter.getMeterType_sv_SE());
            addDynamicElementAndContent(sb, "commitmentOperationMeterRefId", "text", "keyword", availableLocales, meter.getCommitmentOperationMeterRefId(), meter.getCommitmentOperationMeterRefId(), meter.getCommitmentOperationMeterRefId());

            String meterValueType = MeterValueType.UNKOWN.toString();
            if (meter.getMeterValueType() != null ) {
                meterValueType = meter.getMeterValueType().toString();
            }
            addDynamicElementAndContent(sb, "meterValueType", "text", "keyword", availableLocales, meterValueType, meterValueType, meterValueType);

            addDynamicElementAndContent(sb, "startingLevel", "text", "keyword", availableLocales, meter.getStartingLevel(), meter.getStartingLevel(), meter.getStartingLevel());
            addDynamicElementAndContent(sb, "targetLevel", "text", "keyword", availableLocales, meter.getTargetLevel(), meter.getTargetLevel(), meter.getTargetLevel());
            addDynamicElementAndContent(sb, "currentLevel", "text", "keyword", availableLocales, meter.getCurrentLevel(), meter.getCurrentLevel(), meter.getCurrentLevel());
            sb.append("</dynamic-element>");
        }
        String progress = ProgressType.none.toString();
        if (report.getProgress() != null ) {
            progress = report.getProgress().toString();
        }
        addDynamicElementAndContent(sb, "progress", "text", "keyword", availableLocales, progress, progress, progress);
        addDynamicElementAndContent(sb, "reportText", "text_box", "text", availableLocales, report.getReportText_fi_FI(), report.getReportText_en_US(), report.getReportText_sv_SE());

        closeRootElement(sb);
        return sb.toString();
    }

    private long getReportsFolderId(Commitment commitment) {
        switch (commitment.getCommitmentType()) {
            case GREEN_DEAL:
            case PLASTIC_BAG:
            case OIL_INDUSTRY:
            case WORK_MACHINE:
            case AUTOMOTIVE_INDUSTRY:
                return GREEN_DEAL_REPORTS_FOLDER_ID;
            case NUTRITION:
                return NUTRITION_REPORTS_FOLDER_ID;
            default:
                return COMMITMENTS_REPORTS_FOLDER_ID;
        }
    }


    private long getCommitmentsFolderId(Commitment commitment) {
        switch (commitment.getCommitmentType()) {
            case GREEN_DEAL:
            case PLASTIC_BAG:
            case OIL_INDUSTRY:
            case WORK_MACHINE:
            case AUTOMOTIVE_INDUSTRY:
                return GREEN_DEAL_COMMITMENTS_FOLDER_ID;
            case NUTRITION:
                return NUTRITION_COMMITMENTS_FOLDER_ID;
            default:
                return COMMITMENTS_FOLDER_ID;
        }
    }


    @Override
    public int addRatingsEntryScore(String articleId) {
        Double likes = 0d;
        try {
            JournalArticle article = JournalArticleLocalServiceUtil.getArticle(SITOUMUS2050_GROUP_ID, articleId);

            RatingsEntry ratingsEntry = null;
            try {
                ratingsEntry = RatingsEntryLocalServiceUtil.getEntry(Constants.ADMIN_USER_ID, JournalArticle.class.getName(), article.getResourcePrimKey());
            } catch (PortalException e) {
                _logger.info("No ratings, adding new");
            }

            if (ratingsEntry == null) {
                likes = 1d;
                ratingsEntry = RatingsEntryLocalServiceUtil.createRatingsEntry(article.getPrimaryKey());
                long classNameId = ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class.getName());
                ratingsEntry.setClassNameId(classNameId);
                ratingsEntry.setCreateDate(new Date());
                ratingsEntry.setCompanyId(Constants.COMPANY_ID);
                ratingsEntry.setClassPK(article.getResourcePrimKey());
                ratingsEntry.setScore(likes);
                ratingsEntry.setUserName("Admin");
                ratingsEntry.setUserId(Constants.ADMIN_USER_ID);
                RatingsEntryLocalServiceUtil.addRatingsEntry(ratingsEntry);
            } else {
                likes = ratingsEntry.getScore() + 1d;
                ratingsEntry.setScore(likes);
                RatingsEntryLocalServiceUtil.updateRatingsEntry(ratingsEntry);
            }
        } catch (PortalException e) {
            _logger.error("Failed to add or update rating entry", e);
        }
        return likes.intValue();
    }

    @Override
    public int getRatingsEntryScore(String articleId) {
        Double likes = 0d;
            try {
                JournalArticle article = JournalArticleLocalServiceUtil.getArticle(SITOUMUS2050_GROUP_ID, articleId);
                RatingsEntry ratingsEntry = RatingsEntryLocalServiceUtil.getEntry(Constants.ADMIN_USER_ID, JournalArticle.class.getName(), article.getResourcePrimKey());
                likes = ratingsEntry.getScore();
            } catch (PortalException e) {
            }
        return likes.intValue();
    }


    /**
     * If commitment has created or updated date set, sets them to the journal article and updates the given journal article.
     *
     * @param commitment
     * @param journalArticle The original journal article if there were no dates to update, or the updated journal article if something was updated
     * @return
     */
    private JournalArticle updateCeatedAndUpdatedDates(Commitment commitment, JournalArticle journalArticle) {
        boolean shouldUpdate = false;
        if(commitment.getCreated() != null) {
            journalArticle.setCreateDate(commitment.getCreated());
            shouldUpdate = true;
        }
        if(commitment.getUpdated() != null) {
            journalArticle.setModifiedDate(commitment.getUpdated());
            shouldUpdate = true;
        }
        if(shouldUpdate) {
            return JournalArticleLocalServiceUtil.updateJournalArticle(journalArticle);
        }
        return journalArticle;
    }


    @Override
    public void joinToParentCommitment(String parentCommitmentArticleId, String childCommitmentArticleId) throws PortalException {
        JournalArticle parentArticle = JournalArticleLocalServiceUtil.getArticle(SITOUMUS2050_GROUP_ID, parentCommitmentArticleId);
        JournalArticle childArticle = JournalArticleLocalServiceUtil.getArticle(SITOUMUS2050_GROUP_ID, childCommitmentArticleId);

        AssetEntry parentEntry = AssetEntryLocalServiceUtil.getEntry(JournalArticle.class.getName(), parentArticle.getResourcePrimKey());
        long parentEntryId = parentEntry.getEntryId();
        long childEntryId = AssetEntryLocalServiceUtil.getEntry(JournalArticle.class.getName(), childArticle.getResourcePrimKey()).getEntryId();
        AssetLinkLocalServiceUtil.addLink(ADMIN_USER_ID, parentEntryId, childEntryId, AssetLinkConstants.TYPE_CHILD, 0);
        reindexAssetEntry(parentEntry);

        //reindex
        reindexArticle(parentArticle);
        reindexArticle(childArticle);

    }

    private void updateCommitmentPriority(AssetEntry entry, Double priority) {
        entry.setPriority(priority);
        AssetEntryLocalServiceUtil.updateAssetEntry(entry);
    }

    @Override
    public int caclAmountOfJoinedCommitmens(String commitmentId) throws PortalException {
        return  getCommitmentAssetLinkChildsAndUpdatePriority(commitmentId).size();
    }

    @Override
    public List<JournalArticle> getRelatedArticles(String articleId) throws PortalException {
        JournalArticle a = JournalArticleLocalServiceUtil.getArticle(SITOUMUS2050_GROUP_ID, articleId);
        long entryId = AssetEntryLocalServiceUtil.getEntry(JournalArticle.class.getName(), a.getResourcePrimKey()).getEntryId();
        List<AssetLink> links = AssetLinkLocalServiceUtil.getLinks(entryId);
        List<JournalArticle> articles = new ArrayList<>();
        for ( AssetLink al : links ) {
            AssetEntry relatedEntry = AssetEntryLocalServiceUtil.getEntry(al.getEntryId2());
            JournalArticleResource journalArticleResource = JournalArticleResourceLocalServiceUtil.getJournalArticleResource(relatedEntry.getClassPK());
            JournalArticle wc = JournalArticleLocalServiceUtil.getLatestArticle(journalArticleResource.getResourcePrimKey());
            articles.add(wc);
        }
        return articles;
    }

    private long[] getAssetLinkEntryIds(String articleId) throws PortalException {
        JournalArticle a = JournalArticleLocalServiceUtil.getArticle(SITOUMUS2050_GROUP_ID, articleId);
        long entryId = AssetEntryLocalServiceUtil.getEntry(JournalArticle.class.getName(), a.getResourcePrimKey()).getEntryId();
        List<AssetLink> alinks = AssetLinkLocalServiceUtil.getLinks(entryId);
        long[] links = alinks.stream()
                .map(AssetLink::getEntryId2)
                .mapToLong(Long::longValue)
                .toArray();

        return links;
    }

    @Override
    public List<AssetLink> getCommitmentAssetLinkChildsAndUpdatePriority(String commitmentId) throws PortalException {
/*        if (_logger.isDebugEnabled()){
            _logger.debug("Getting child commitments: " + commitmentId);
        }*/
        JournalArticle parentArticle = JournalArticleLocalServiceUtil.getLatestArticle(SITOUMUS2050_GROUP_ID, commitmentId);
        AssetEntry parentEntry = AssetEntryLocalServiceUtil.getEntry(JournalArticle.class.getName(), parentArticle.getResourcePrimKey());
        Double parentPriority = parentEntry.getPriority();
        long parentEntryId = parentEntry.getEntryId();
/*        if (_logger.isDebugEnabled()) {
            _logger.debug("ParentEntryId: " + parentEntryId);
        }*/
        List<AssetLink> childs = AssetLinkLocalServiceUtil.getDirectLinks(parentEntryId, AssetLinkConstants.TYPE_CHILD);
        List<AssetLink> tmpLinks = new ArrayList<>();
        for (AssetLink l : childs) {
            if (l.getEntryId2() != parentEntryId) {
                tmpLinks.add(l);
            }
        }
/*        if (_logger.isDebugEnabled()) {
            _logger.debug("Childs found: " + tmpLinks.size());
        }*/

        //update parent commitment priority if published childs count does not match with priority
        if ( parentPriority.intValue() != tmpLinks.size()) {
            updateCommitmentPriority(parentEntry, new Double( tmpLinks.size()));
            reindexArticle(parentArticle);
        }
        return tmpLinks;
    }

    @Override
    public List<Commitment> getCommitmentJoinedCommitments(String commitmentId) throws PortalException {
        List<Commitment> childCommitments = new ArrayList<>();
        List<AssetLink> childs = getCommitmentAssetLinkChildsAndUpdatePriority(commitmentId);
        for ( AssetLink al : childs ) {
            AssetEntry relatedEntry = AssetEntryLocalServiceUtil.getEntry(al.getEntryId2());
            if (_logger.isDebugEnabled()) {
                _logger.debug("Found related article " + relatedEntry.getTitle());
            }
            JournalArticleResource journalArticleResource = JournalArticleResourceLocalServiceUtil.getJournalArticleResource(relatedEntry.getClassPK());
            try {
                Commitment c = getCommitment(journalArticleResource.getArticleId(), WorkflowConstants.STATUS_APPROVED, true);
                if ( c != null ) {
                    childCommitments.add(c);
                }
            } catch (PortalException e) {
                _logger.warn("Joined commitment not found, id: " + journalArticleResource.getArticleId() + ". Usually this is false alarm, since commitment might not yet been published.", e);
            }
        }
        return childCommitments;
    }

    @Override
    public void addRelatedRelationshipLink(String articleParentId, String articleChildId) throws PortalException {
        JournalArticle cArticle = JournalArticleLocalServiceUtil.getArticle(SITOUMUS2050_GROUP_ID, articleParentId);
        JournalArticle rArticle = JournalArticleLocalServiceUtil.getArticle(SITOUMUS2050_GROUP_ID, articleChildId);

        long cEntryId = AssetEntryLocalServiceUtil.getEntry(JournalArticle.class.getName(), cArticle.getResourcePrimKey()).getEntryId();
        long rEntryId = AssetEntryLocalServiceUtil.getEntry(JournalArticle.class.getName(), rArticle.getResourcePrimKey()).getEntryId();
        AssetLinkLocalServiceUtil.addLink(ADMIN_USER_ID, cEntryId, rEntryId, AssetLinkConstants.TYPE_RELATED, 0);

    }

    /**
     *
     * @param userId
     * @param workflowAction, see WorkflowConstants.ACTION_PUBLISH and WorkflowConstants.ACTION_SAVE_DRAFT
     * @return
     */
    private ServiceContext populateServiceContext(long userId, int workflowAction) {
        ServiceContext serviceContext = new ServiceContext();
        serviceContext.setAddGuestPermissions(true);
        serviceContext.setScopeGroupId(SITOUMUS2050_GROUP_ID);
        serviceContext.setCompanyId(COMPANY_ID);
        serviceContext.setUserId(userId);
        serviceContext.setWorkflowAction(workflowAction);
        return serviceContext;
    }

    private String getCommitmentXmlStructure(Commitment commitment) {

        StringBuilder sb = new StringBuilder();

        String availableLocales = "fi_FI"; //fi is default lang
        if (StringUtils.isNotBlank(commitment.getTitle_en_US())) {
            availableLocales = availableLocales + ",en_US";
        }
        if ( StringUtils.isNotBlank(commitment.getTitle_sv_SE())) {
            availableLocales = availableLocales + ",sv_SE";
        }
        generateRootElement(availableLocales, sb);
        addDynamicElementAndContent(sb, "startDate", "ddm-date", "keyword", availableLocales, commitment.getStartDate(), commitment.getStartDate(), commitment.getStartDate());
        addDynamicElementAndContent(sb, "endDate", "ddm-date", "keyword", availableLocales, commitment.getEndDate(), commitment.getEndDate(), commitment.getEndDate());
        addDynamicElementAndContent(sb, "shortDescription", "text_box", "text", availableLocales, commitment.getShortDescription_fi_FI(), commitment.getShortDescription_en_US(), commitment.getShortDescription_sv_SE());
        addDynamicElementAndContent(sb, "backgroundInformation", "text_box", "text", availableLocales, commitment.getBackgroundInformation_fi_FI(), commitment.getBackgroundInformation_en_US(), commitment.getBackgroundInformation_sv_SE());
        addDynamicElementAndContent(sb, "innovation", "text_box", "text", availableLocales, commitment.getInnovation_fi_FI(), commitment.getInnovation_en_US(), commitment.getInnovation_sv_SE());

        if( commitment.getOperations() != null ) {
            for (Operation operation : commitment.getOperations()) {
                addOpenDynamicElementAndContent(sb, "operationTitle", "text_box", "text", availableLocales, operation.getOperationTitle_fi_FI(), operation.getOperationTitle_en_US(), operation.getOperationTitle_sv_SE());
                String operationId = operation.getOperationId();
                if (StringUtils.isBlank(operationId)) {
                    operationId = generateId();
                }
                addDynamicElementAndContent(sb, "operationId", "text", "keyword", availableLocales, operationId, operationId, operationId);
                addDynamicElementAndContent(sb, "operationDescription", "text_box", "text", availableLocales, operation.getOperationDescription_fi_FI(), operation.getOperationDescription_en_US(), operation.getOperationDescription_sv_SE());

                if( operation.getMeters() != null ) {
                    for (Meter meter : operation.getMeters()) {
                        addOpenDynamicElementAndContent(sb, "meterType", "text", "keyword", availableLocales, meter.getMeterType_fi_FI(), meter.getMeterType_en_US(), meter.getMeterType_sv_SE());

                        String meterId = meter.getMeterId();
                        if (StringUtils.isBlank(meterId)) {
                            meterId = generateId();
                        }
                        addDynamicElementAndContent(sb, "meterId", "text", "keyword", availableLocales, meterId, meterId, meterId);

                        String meterValueType = MeterValueType.UNKOWN.toString();
                        if (meter.getMeterValueType() != null) {
                            meterValueType = meter.getMeterValueType().toString();
                        }
                        addDynamicElementAndContent(sb, "meterValueType", "text", "keyword", availableLocales, meterValueType, meterValueType, meterValueType);

                        addDynamicElementAndContent(sb, "startingLevel", "text", "keyword", availableLocales, meter.getStartingLevel(), meter.getStartingLevel(), meter.getStartingLevel());
                        addDynamicElementAndContent(sb, "targetLevel", "text", "keyword", availableLocales, meter.getTargetLevel(), meter.getTargetLevel(), meter.getTargetLevel());
                        String meterChartType = "";
                        if (meter.getMeterChartType() != null) {
                            meterChartType = meter.getMeterChartType().toString();
                        }
                        addDynamicElementAndContent(sb, "meterChartType", "text", "keyword", availableLocales, meterChartType, meterChartType, meterChartType);
                        addDynamicElementAndContent(sb, "meterCategory", "list", "keyword", availableLocales, meter.getMeterCategory(), meter.getMeterCategory(), meter.getMeterCategory());

                        sb.append("</dynamic-element>");
                    }
                }
                sb.append("</dynamic-element>");
            }
        }
        if( commitment.getDoneOperations() != null ) {
            //Done operations only need title and cateogry information...
            for (DoneOperation operation : commitment.getDoneOperations()) {
                addOpenDynamicElementAndContent(sb, "doneOperation", "text", "text", availableLocales, operation.getOperationTitle_fi_FI(), operation.getOperationTitle_en_US(), operation.getOperationTitle_sv_SE());
                addDynamicElementAndContent(sb, "doneOperationId", "text", "keyword", availableLocales, operation.getOperationId(), operation.getOperationId(), operation.getOperationId());
                addDynamicElementAndContent(sb, "doneOperationCategory", "list", "keyword", availableLocales, operation.getOperationCategory(), operation.getOperationCategory(), operation.getOperationCategory());
                sb.append("</dynamic-element>");
            }
        }
        addDynamicElementAndContent(sb, "address", "text", "keyword", availableLocales, commitment.getAddress(), commitment.getAddress(), commitment.getAddress());
        addDynamicElementAndContent(sb, "organizationId", "text", "text", availableLocales, commitment.getOrganizationId(), commitment.getOrganizationId(), commitment.getOrganizationId());

        if(commitment.getLatitude() != null && commitment.getLongitude() != null) {
            String geolocationAsJson = getGeolocationAsJson(commitment);
            addDynamicElementAndContent(sb, "geolocation", "ddm-geolocation", "keyword", availableLocales, geolocationAsJson, geolocationAsJson, geolocationAsJson);
        }

        String reportingInterval = ReportingInterval.MONTH_6.toString();
        if (commitment.getReportingInterval() != null) {
            reportingInterval = commitment.getReportingInterval().toString();
        }
        addDynamicElementAndContent(sb, "reportingInterval", "text", "keyword", availableLocales, reportingInterval, reportingInterval, reportingInterval);
        String reportReminder = String.valueOf(commitment.isReportReminder());
        addDynamicElementAndContent(sb, "reportReminder", "boolean", "keyword", availableLocales, reportReminder, reportReminder, reportReminder);
        String acceptCriterias = String.valueOf( commitment.isAcceptCriterias());
        addDynamicElementAndContent(sb, "acceptCriterias", "boolean", "keyword", availableLocales, acceptCriterias, acceptCriterias, acceptCriterias);

        addDynamicElementAndContent(sb, "commitmentImageUrl", "document_library", "keyword", availableLocales, commitment.getCommitmentImageUrl(), commitment.getCommitmentImageUrl(), commitment.getCommitmentImageUrl());

        closeRootElement(sb);
        return sb.toString();
    }

    /**
     *
     *
     * @return {"latitude":"123.123","longitude":"123.123"}
     */
    private String getGeolocationAsJson(Commitment commitment) {
        StringBuilder sb = new StringBuilder("{\"latitude\":");
        sb.append("\""+ commitment.getLatitude() +"\", ");
        sb.append("\"longitude\":");
        sb.append("\""+ commitment.getLongitude() +"\"}");
        return sb.toString();
    }

    private void closeRootElement(StringBuilder sb) {
        sb.append("</root>");
    }

    private void generateRootElement(String availableLocales, StringBuilder sb) {
        sb.append("<?xml version=\"1.0\"?>");
        sb.append("<root available-locales=\"").append(availableLocales).append("\" default-locale=\"").append("fi_FI").append("\">");
    }

    private StringBuilder addDynamicElementAndContent(StringBuilder sb, String name, String type, String indexType, String availableLocales, String content_fi, String content_en, String content_sv) {
        sb.append("<dynamic-element name=\"").append(name).append("\" instance-id=\"").append(generateId()).append("\" type=\"").append(type).append("\" index-type=\"").append(indexType).append("\">");
                    addDynamicContent(sb, lang_fi, content_fi);
                    if (availableLocales.contains(lang_en)) addDynamicContent(sb, lang_en, content_en);
                    if ( availableLocales.contains(lang_sv))addDynamicContent(sb, lang_sv, content_sv);
        sb.append("</dynamic-element>");
        return sb;
    }

    private StringBuilder addOpenDynamicElementAndContent(StringBuilder sb, String name, String type, String indexType, String availableLocales, String content_fi, String content_en, String content_sv) {
            sb.append("<dynamic-element name=\"").append(name).append("\" instance-id=\"").append(generateId()).append("\" type=\"").append(type).append("\" index-type=\"").append(indexType).append("\" >");
            addDynamicContent(sb, lang_fi, content_fi);
            if (availableLocales.contains(lang_en)) addDynamicContent(sb, lang_en, content_en);
            if ( availableLocales.contains(lang_sv)) addDynamicContent(sb, lang_sv, content_sv);
        return sb;
    }

    private void addDynamicContent(StringBuilder sb, String languageId, String content) {
        if (StringUtils.isBlank(content) || "null".equalsIgnoreCase(content)) {
            content="";
        }

        String cleanContent = Jsoup.clean(content, "", Whitelist.basic());

        sb.append("<dynamic-content language-id=\"").append(languageId).append("\">");
        sb.append("<![CDATA[").append(cleanContent).append("]]>");
        sb.append("</dynamic-content>");
    }

    private String generateId() {
        String instanceId = PwdGenerator.getPassword(10);
        return instanceId;
    }


    @Override
    public void sendCommitmentNotifyEmail(String emailTo, Commitment commitment) throws PortalException, AddressException {
        JournalArticle article = JournalArticleLocalServiceUtil.getLatestArticle(Constants.SITOUMUS2050_GROUP_ID, commitment.getId());

        String userEmailAddress = "";
        try {
            User user = userService.getUser(commitment.getCreatedByUserId());
            userEmailAddress = user.getEmailAddress();
        } catch (Exception e) {
            _logger.error("Commitment creator email not found!", e);
            userEmailAddress = "Commitment creator email not known.";
        }

        String to = emailTo;
        String from = Constants.MAIL_SENDER;
        String title = article.getTitle(new Locale("fi", "FI"));
        if (StringUtils.isBlank(title)) {
            title = article.getTitle(new Locale("en", "US"));
        }
        if (StringUtils.isBlank(title)) {
            title = article.getTitle(new Locale("sv", "SE"));
        }
        List<String> availableLanguageIds = Arrays.asList(article.getAvailableLanguageIds());
        //TODO: use template?
        String subject = "Sitoumus2050, tarkasta sitoumus " + title;

        StringBuilder sb = new StringBuilder();
        sb.append("<pre>")
                .append(" Artikkelin id: ").append( article.getArticleId()).append("\n")
                .append(" Käyttäjä: ").append( commitment.getCreatedByUserName()).append("\n")
                .append(" Käyttäjän email: ").append(userEmailAddress).append("\n")
                .append(" Osoite: ").append(commitment.getAddress()).append("\n")
                .append(" Organisaatio: ").append(commitment.getOrganizationName()).append("\n")
                .append(" Aloituspäivä: ").append(commitment.getStartDate()).append("\n")
                .append(" lopetuspäivä: ").append(commitment.getEndDate()).append("\n")
                .append(" Sitoumuskriteerit: ").append(commitment.isAcceptCriterias()).append("\n")
                .append(" Raportointimuistutus: ").append(commitment.isReportReminder()).append("\n")
                .append(" Raportointiväli: ").append(commitment.getReportingInterval().toString()).append("\n")
                .append("</pre>");

        sb.append("<h3>Kategoriat</h3>");
        sb.append("<pre>");
        for (long id : commitment.getCategoryIds()) {
            AssetCategory c = AssetCategoryLocalServiceUtil.getAssetCategory(id);
            AssetVocabulary av = AssetVocabularyServiceUtil.getVocabulary(c.getVocabularyId());
            sb.append(" Sanastosta: ").append(av.getName()).append(" kategoria:").append( c.getName()).append("\n");
        }
        sb.append("</pre>");

        if (availableLanguageIds.contains(lang_fi)) {
            sb.append("<div>");
                addEmailField(sb, "Sitoumus lyhyesti", commitment.getShortDescription_fi_FI());
                addEmailField(sb, "Taustatietoa", commitment.getBackgroundInformation_fi_FI());
                addEmailField(sb, "Mitä uutta sitoumuksessa", commitment.getInnovation_fi_FI());
            sb.append("</div>");

            sb.append("<div>");
            sb.append("<h2>Operaatiot ja tavoitteet</h2>");
                for ( Operation o : commitment.getOperations() ) {
                    sb.append("<div>");
                        sb.append("<h4>").append(o.getOperationTitle_fi_FI()).append("</h4>");
                        sb.append("<p>").append(o.getOperationDescription_fi_FI()).append("</p>");
                        sb.append("<h4>").append("Mittarit").append("</h4>");
                        for (Meter m : o.getMeters()) {
                            sb.append("<pre>");
                            sb.append(" Mittari: ").append(m.getMeterType_fi_FI()).append("\n");
                            sb.append(" Mittarin arvon tyyppi: ").append(m.getMeterValueType()).append("\n");
                            sb.append(" Lähtötaso: ").append(m.getStartingLevel()).append("\n");
                            sb.append(" Tavoitetaso: ").append(m.getTargetLevel()).append("\n");
                            sb.append(" Graafi: ").append(String.valueOf(m.getMeterChartType())).append("\n");
                            sb.append("</pre>");
                        }
                    sb.append("</div>");
                }
            sb.append("</div>");

        }

        String body = sb.toString();
        if (_logger.isDebugEnabled()) {
            _logger.debug("MAIL MESSAGE BODY: " + body);
        }
        LFMailUtil.sendMail(body, to, from, subject, true);
    }

    private void addEmailField(StringBuilder sb, String title, String value) {
        sb.append("<div>")
                .append("<h2>").append(title).append("</h2>")
                .append("<div>").append(value).append("</div>")
            .append("</div>");
    }




    @Override
    public Commitment getCommitment(String commitmentId, Integer status, boolean fetchReports) throws PortalException {
        JournalArticle a = null;
        if ( status != null ) {
            a = JournalArticleLocalServiceUtil.getLatestArticle(Constants.SITOUMUS2050_GROUP_ID, commitmentId, status);
        } else {
            a = JournalArticleLocalServiceUtil.getLatestArticle(Constants.SITOUMUS2050_GROUP_ID, commitmentId);
        }
        Commitment c = null;
        if ( a != null ) {
            Map<String, List<Report>> reportMap = fetchReports ? getOperationReportMap(commitmentId) : new HashMap<>();
            c = mapJournalArticleToCommitment(a, reportMap);
        }

        return c;
    }

    private Commitment mapJournalArticleToCommitment(JournalArticle a, Map<String, List<Report>> reportMap) throws PortalException {
        String commitmentId = a.getArticleId();
        Commitment c = new Commitment();

        JSONObject jc = lfCommitmentArticleContentXMLToJSON(a.getContent());
        c.setId(a.getArticleId());
        c.setVersion(a.getVersion());
        c.setUpdated(a.getModifiedDate());
        c.setCreated(a.getCreateDate());
        c.setGroupId(a.getGroupId());
        c.setStatus(WorkflowConstants.getStatusLabel(a.getStatus()));

        c.setTitle_fi_FI(a.getTitle(locale_fi));
        c.setTitle_en_US(a.getTitle(locale_en, false));
        c.setTitle_sv_SE(a.getTitle(locale_sv, false));

        c.setStartDate(jc.getString("startDate_fi_FI"));
        c.setEndDate(jc.getString("endDate_fi_FI"));

        c.setInnovation_fi_FI(jc.getString("innovation_fi_FI"));
        c.setInnovation_en_US(jc.getString("innovation_en_US"));
        c.setInnovation_sv_SE(jc.getString("innovation_sv_SE"));

        c.setBackgroundInformation_fi_FI(jc.getString( "backgroundInformation_fi_FI"));
        c.setBackgroundInformation_en_US(jc.getString( "backgroundInformation_en_US"));
        c.setBackgroundInformation_sv_SE(jc.getString( "backgroundInformation_sv_SE"));

        c.setShortDescription_fi_FI(jc.getString("shortDescription_fi_FI"));
        c.setShortDescription_en_US(jc.getString("shortDescription_en_US"));
        c.setShortDescription_sv_SE(jc.getString("shortDescription_sv_SE"));

        c.setCommitmentImageUrl(jc.getString("commitmentImageUrl_fi_FI"));

        List<Operation> operations = new ArrayList<Operation>();
        JSONArray joperations = jc.getJSONArray("operations");
        if (_logger.isDebugEnabled()) {
            _logger.debug("Operation size: " + joperations.length());
        }
        for ( int i = 0; i < joperations.length(); i++ ) {
            JSONObject jo = joperations.getJSONObject(i);
            Operation o = new Operation();
            o.setOperationTitle_fi_FI(jo.getString("operationTitle_fi_FI"));
            o.setOperationTitle_en_US(jo.getString("operationTitle_en_US"));
            o.setOperationTitle_sv_SE(jo.getString("operationTitle_sv_SE"));

            o.setOperationDescription_fi_FI(jo.getString("operationDescription_fi_FI"));
            o.setOperationDescription_en_US(jo.getString("operationDescription_en_US"));
            o.setOperationDescription_sv_SE(jo.getString("operationDescription_sv_SE"));

            o.setOperationId(jo.getString("operationId_fi_FI"));
            List<Meter> meters = new ArrayList<>();
            JSONArray jmeters = jo.getJSONArray("meters");
            for ( int j = 0; j < jmeters.length(); j++ ) {
                JSONObject jm = jmeters.getJSONObject(j);
                Meter m = new Meter();
                m.setMeterId(jm.getString("meterId_fi_FI"));
                m.setMeterType_fi_FI(jm.getString("meterType_fi_FI"));
                m.setMeterType_en_US(jm.getString("meterType_en_US"));
                m.setMeterType_sv_SE(jm.getString("meterType_sv_SE"));
                m.setTargetLevel(jm.getString("targetLevel_fi_FI"));
                m.setStartingLevel(jm.getString("startingLevel_fi_FI"));
                m.setMeterCategory(jo.getString("meterCategory_fi_FI"));

                String meterValueType = jm.getString("meterValueType_fi_FI");
                if (StringUtils.isNotBlank(meterValueType)) {
                    m.setMeterValueType(MeterValueType.valueOf(meterValueType));
                }
                String meterChartType = jm.getString("meterChartType_fi_FI");
                if (StringUtils.isNotBlank(meterChartType)) {
                    m.setMeterChartType(MeterChartType.valueOf(meterChartType));
                }
                meters.add(m);
            }

            if (reportMap.containsKey(o.getOperationId())) {
                o.setReports(reportMap.get(o.getOperationId()));
            }

            o.setMeters(meters);

            operations.add(o);
        }

        List<DoneOperation> doneOperations = new ArrayList<DoneOperation>();
        JSONArray jdoneOperations = jc.getJSONArray("doneOperations");
        if (_logger.isDebugEnabled()) {
            _logger.debug("doneOperation size: " + jdoneOperations.length());
        }
        for ( int i = 0; i < jdoneOperations.length(); i++ ) {
            JSONObject jo = jdoneOperations.getJSONObject(i);
            DoneOperation o = new DoneOperation();
            o.setOperationTitle_fi_FI(jo.getString("doneOperation_fi_FI"));
            o.setOperationTitle_en_US(jo.getString("doneOperation_en_US"));
            o.setOperationTitle_sv_SE(jo.getString("doneOperation_sv_SE"));
            o.setOperationId(jo.getString("doneOperationId_fi_FI"));
            o.setOperationCategory(jo.getString("doneOperationCategory_fi_FI"));
            doneOperations.add(o);
        }

        Serializable organization_name = a.getExpandoBridge().getAttribute("Organization name", false);
        if (organization_name != null ) {
            c.setOrganizationName(organization_name.toString());
        } else {
            c.setOrganizationName("");
        }

        Serializable creatorUserId = a.getExpandoBridge().getAttribute(Constants.WEB_CONTENT_ARTICLE_CREATOR_USER_ID_EXPANDO_FIELD_NAME, false);
        if (creatorUserId != null ) {
            c.setCreatedByUserId(Long.valueOf( creatorUserId.toString()));
        } else {
            c.setCreatedByUserId(a.getUserId());
        }

        Serializable creatorUserName = a.getExpandoBridge().getAttribute(Constants.WEB_CONTENT_ARTICLE_CREATOR_USER_NAME_EXPANDO_FIELD_NAME, false);
        if (creatorUserName != null ) {
            c.setCreatedByUserName(creatorUserName.toString());
        } else {
            c.setCreatedByUserName("");
        }

        Serializable creatorPortraitUrl = a.getExpandoBridge().getAttribute(Constants.WEB_CONTENT_ARTICLE_CREATOR_PORTRAIT_EXPANDO_FIELD_NAME, false);
        if (creatorPortraitUrl != null ) {
            c.setCreatorPortrait(creatorPortraitUrl.toString());
        } else {
            c.setCreatorPortrait("");
        }

        Serializable organizationLogoUrl = a.getExpandoBridge().getAttribute(Constants.WEB_CONTENT_ARTICLE_ORGANIZATION_LOGO_EXPANDO_FIELD_NAME, false);
        if (organizationLogoUrl != null ) {
            c.setOrganizationLogo(organizationLogoUrl.toString());
        } else {
            c.setOrganizationLogo("");
        }

        c.setAddress(jc.getString("address_fi_FI"));
        c.setOrganizationId(jc.getString("organizationId_fi_FI"));

        String geojson = jc.getString("geolocation");
        if (StringUtils.isNotBlank(geojson) ) {
            JSONObject geolocation = JSONFactoryUtil.createJSONObject(geojson);
            String longitude = geolocation.getString("longitude", "0");
            String latitude = geolocation.getString("latitude", "0");
            Double longitude1 = 0d;
            Double latitude1 = 0d;
            try {
                longitude1 = Double.valueOf(longitude);
                latitude1 = Double.valueOf(latitude);
            } catch (Exception e) {
                _logger.warn("Commitment: " + commitmentId + " Latitude and longitude not set, defaults to 0, 0");
            }
            c.setLongitude(longitude1);
            c.setLatitude(latitude1);
        }

        c.setCommitmentType(resolveCommitmentType(a));

        String reportingInterval = jc.getString("reportingInterval_fi_FI");
        if ( StringUtils.isNotBlank(reportingInterval)) {
            c.setReportingInterval( ReportingInterval.valueOf(reportingInterval));
        }
        c.setReportReminder(jc.getBoolean("reportReminder_fi_FI"));
        c.setAcceptCriterias(jc.getBoolean("acceptCriterias_fi_FI"));

        c.setCategoryIds(getCategoryIds(a));
        c.setOperations(operations);
        c.setDoneOperations(doneOperations);

        if (reportMap.containsKey(LEGACY_REPORT_REFERENCE_GENERIC_OPERATION_ID)) {
            c.setGenericReports(reportMap.get(LEGACY_REPORT_REFERENCE_GENERIC_OPERATION_ID));
        }

        //likes
        int likes = getRatingsEntryScore(commitmentId);
        c.setLikes(likes);
        //child (joined) commitments
        List<AssetLink> commitmentAssetLinkChilds = getCommitmentAssetLinkChildsAndUpdatePriority(commitmentId);
        int childs = commitmentAssetLinkChilds.size();
        c.setJoined(childs);
        return c;
    }

    private Map<String, List<Report>> getOperationReportMap(String commitmentId) throws PortalException {
        Map<String, List<Report>> reportMap = new HashMap<>();
        List<Report> reports = getCommitmentReports(commitmentId);
        for ( Report r : reports ) {
            if ( reportMap.containsKey(r.getCommitmentOperationRefId())) {
                reportMap.get(r.getCommitmentOperationRefId()).add(r);
            } else {
                List<Report> list = new ArrayList<>();
                list.add(r);
                reportMap.put(r.getCommitmentOperationRefId(), list);
            }
        }
        return reportMap;
    }

    @Override
    public List<Report> getOperationReports(String commitmentId, String operationId) throws PortalException {
        Map<String, List<Report>> reportMap = getOperationReportMap(commitmentId);

        List<Report> results = new ArrayList<>();
        if (reportMap.containsKey(operationId)) {
            results = reportMap.get(operationId);
        }
        return results;
    }


    @Override
    public Report getReport(String reportId, Integer status) throws PortalException {
        JournalArticle a;
        if ( status != null ) {
            a = JournalArticleLocalServiceUtil.getLatestArticle(Constants.SITOUMUS2050_GROUP_ID, reportId, status);
        } else {
            a = JournalArticleLocalServiceUtil.getLatestArticle(Constants.SITOUMUS2050_GROUP_ID, reportId);
        }
        JSONObject jc = lfReportArticleContentXMLToJSON(a.getContent());

        Report r = new Report();
        r.setId(a.getArticleId());
        r.setVersion(a.getVersion());
        r.setReportTitle_fi_FI(jc.getString("reportTitle_fi_FI"));
        r.setReportTitle_en_US(jc.getString("reportTitle_en_US"));
        r.setReportTitle_sv_SE(jc.getString("reportTitle_sv_SE"));
        r.setCommitmentArticleId(jc.getString("commitmentArticleId_fi_FI"));

        r.setOperationTitle_fi_FI(jc.getString("operationTitle_fi_FI"));
        r.setOperationTitle_en_US(jc.getString("operationTitle_en_US"));
        r.setOperationTitle_sv_SE(jc.getString("operationTitle_sv_SE"));

        r.setCommitmentOperationRefId(jc.getString("commitmentOperationRefId_fi_FI"));

        r.setStatus(WorkflowConstants.getStatusLabel(a.getStatus()));

        r.setReportStartDate(jc.getString("reportStartDate_fi_FI"));
        r.setReportEndDate(jc.getString("reportEndDate_fi_FI"));

        ProgressType progressType = ProgressType.NoProgress;
        String progress = jc.getString("progress_fi_FI");
        if ( StringUtils.isNotBlank(progress)) {
            try {
                progressType = ProgressType.valueOf(progress);
            } catch (IllegalArgumentException e) {
                _logger.warn("Report progressType is not know, returning noprogress");
            }
        }
        r.setProgress(progressType);
        r.setReportText_fi_FI(jc.getString("reportText_fi_FI"));
        r.setReportText_en_US(jc.getString("reportText_en_US"));
        r.setReportText_sv_SE(jc.getString("reportText_sv_SE"));

        List<ReportMeter> meters = new ArrayList<ReportMeter>();
        JSONArray jmeters = jc.getJSONArray("meters");
        if (_logger.isDebugEnabled() ) {
            _logger.debug("Report meter size: " + jmeters.length());
        }
        for ( int i = 0; i < jmeters.length(); i++ ) {
            JSONObject jm = jmeters.getJSONObject(i);
            ReportMeter m = new ReportMeter();
            m.setCommitmentOperationMeterRefId(jm.getString("commitmentOperationMeterRefId_fi_FI"));
            m.setMeterType_fi_FI(jm.getString("meterType_fi_FI"));
            m.setMeterType_en_US(jm.getString("meterType_en_US"));
            m.setMeterType_sv_SE(jm.getString("meterType_sv_SE"));
            m.setTargetLevel(jm.getString("targetLevel_fi_FI"));
            m.setStartingLevel(jm.getString("startingLevel_fi_FI"));
            m.setCurrentLevel(jm.getString("currentLevel_fi_FI"));

            String meterValueType = jm.getString("meterValueType_fi_FI");
            if (StringUtils.isNotBlank(meterValueType)) {
                m.setMeterValueType(MeterValueType.valueOf(meterValueType));
            }
            meters.add(m);
        }

        r.setReportMeters(meters);



        Serializable organization_name = a.getExpandoBridge().getAttribute("Organization name", false);
        if (organization_name != null ) {
            r.setOrganizationName(organization_name.toString());
        } else {
            r.setOrganizationName("");
        }

        Serializable creatorUserId = a.getExpandoBridge().getAttribute("creatorUserId", false);
        if (creatorUserId != null ) {
            r.setCreatedByUserId(Long.valueOf( creatorUserId.toString()));
        } else {
            r.setCreatedByUserId(a.getUserId());
        }

        Serializable creatorUserName = a.getExpandoBridge().getAttribute("creatorUserName", false);
        if (creatorUserName != null ) {
            r.setCreatedByUserName(creatorUserName.toString());
        } else {
            r.setCreatedByUserName("");
        }

        return r;
    }

    private CommitmentType resolveCommitmentType(JournalArticle a) {
        List<Long> cats = getCategoryIds(a);
        if (cats.contains(COMMITMENT_CATEGORY_ID)) {
            return CommitmentType.COMMITMENT;
        }
        if (cats.contains(NUTRITION_CATEGORY_ID)) {
            return CommitmentType.NUTRITION;
        }
        if (cats.contains(PLASTIC_BAG_CATEGORY_ID)) {
            return CommitmentType.PLASTIC_BAG;
        }
        if (cats.contains(OIL_INDUSTRY_CATEGORY_ID)) {
            return CommitmentType.OIL_INDUSTRY;
        }
        if (cats.contains(AUTOMOTIVE_INDUSTRY_CATEGORY_ID)) {
            return CommitmentType.AUTOMOTIVE_INDUSTRY;
        }
        if (cats.contains(WORK_MACHINE_CATEGORY_ID)) {
            return CommitmentType.WORK_MACHINE;
        }
        if (cats.contains(DEMOLITION_CATEGORY_ID)) {
            return CommitmentType.DEMOLITION;
        }
        // Must be compared after green deal sub categories
        if (cats.contains(GREENDEAL_CATEGORY_ID)) {
            return CommitmentType.GREEN_DEAL;
        }
        return CommitmentType.COMMITMENT;
    }

    private List<AssetCategory> getCategories(JournalArticle a) {
        List<AssetCategory> categories = AssetCategoryLocalServiceUtil.getCategories(JournalArticle.class.getName(), a.getResourcePrimKey());
        return categories;
    }

    private List<Long> getCategoryIds(JournalArticle a) {
        long[] ids = AssetCategoryLocalServiceUtil.getCategoryIds(JournalArticle.class.getName(), a.getResourcePrimKey());
        List<Long> oids = Arrays.stream(ids).boxed().collect(Collectors.toList());
        return oids;
    }

    @Override
    public List<Commitment> getGreenDealCommitments() {
        List<AssetEntry> assets = AssetEntryLocalServiceUtil
            .getAssetCategoryAssetEntries(Constants.GREENDEAL_CATEGORY_ID);

        List<Long> articlePrimKeys = assets.stream()
            .filter(asset -> Constants.JOURNALARTICLE_CLASSNAME_ID == asset.getClassNameId())
            .map(asset -> {
                _logger.info("asset = " + asset.toString());
                return asset.getClassPK();
            }).collect(Collectors.toList());

        DynamicQuery q = JournalArticleLocalServiceUtil.dynamicQuery();
        q.add(RestrictionsFactoryUtil.in("resourcePrimKey", articlePrimKeys));
        List<JournalArticle> articles = JournalArticleLocalServiceUtil.dynamicQuery(q);

        Map<Long, JournalArticle> map = new HashMap<>();
        articles.forEach(article -> {
            long key = article.getResourcePrimKey();
            JournalArticle existing = map.get(key);
            if(existing == null || article.getVersion() > existing.getVersion()) {
                map.put(key, article);
            }
        });

        return map.values().stream().map(article -> {
            try {
                return mapJournalArticleToCommitment(article, Collections.emptyMap());
            } catch (PortalException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    @Override
    public List<AssetCategory> getCategoriesInHierarchy(long rootCategoryId) throws PortalException {
        AssetCategory root = AssetCategoryLocalServiceUtil.getAssetCategory(rootCategoryId);

        DynamicQuery q = AssetCategoryLocalServiceUtil.dynamicQuery();
        q.add(RestrictionsFactoryUtil.ge("leftCategoryId", root.getLeftCategoryId()));
        q.add(RestrictionsFactoryUtil.le("rightCategoryId", root.getRightCategoryId()));

        return AssetCategoryLocalServiceUtil.dynamicQuery(q);
    }

    @Override
    public List<AssetCategoryProperty> getCategoryProperties(List<Long> categoryIds) throws PortalException {

        DynamicQuery q = AssetCategoryPropertyLocalServiceUtil.dynamicQuery();
        q.add(RestrictionsFactoryUtil.in("categoryId", categoryIds));

        return AssetCategoryPropertyLocalServiceUtil.dynamicQuery(q);
    }

    @Override
    public List<Report> getCommitmentReports(String commitmentId) throws PortalException {
        List<JournalArticle> articles = getRelatedArticles(commitmentId);
        List<Report> reports = new ArrayList<>();
        for (JournalArticle a : articles) {
            if ( GLOBAL_REPORT_DDM_STRUCTURE_KEY.equals(a.getDDMStructureKey())) {
                Report r = getReport(a.getArticleId(), WorkflowConstants.STATUS_APPROVED );
                reports.add(r);
            }

        }
        return reports;
    }

    @Override
    public List<OperationTemplate> getOperationTemplates(long folderId) throws PortalException {
        List<JournalArticle> articles = JournalArticleLocalServiceUtil.getArticles(SITOUMUS2050_GROUP_ID, folderId);
        articles = filterToLatest(articles);
        List<OperationTemplate> results = new ArrayList<>();
        for ( JournalArticle a : articles ) {
            JSONObject jo = lfOperationTemplateArticleContentXMLToJSON(a.getContent());
            OperationTemplate o = new OperationTemplate();

            o.setId(a.getArticleId());
            o.setOperationTitle_fi_FI(jo.getString("operationTitle_fi_FI"));
            o.setOperationTitle_en_US(jo.getString("operationTitle_en_US"));
            o.setOperationTitle_sv_SE(jo.getString("operationTitle_sv_SE"));


            List<MeterTemplate> meters = new ArrayList<>();
            JSONArray jmeters = jo.getJSONArray("meters");
            for ( int j = 0; j < jmeters.length(); j++ ) {
                JSONObject jm = jmeters.getJSONObject(j);
                MeterTemplate m = new MeterTemplate();
                m.setId("");
                m.setMeterType_fi_FI(jm.getString("meterType_fi_FI"));
                m.setMeterType_en_US(jm.getString("meterType_en_US"));
                m.setMeterType_sv_SE(jm.getString("meterType_sv_SE"));
                String meterValueType = jm.getString("meterValueType_fi_FI");
                if (StringUtils.isNotBlank(meterValueType)) {
                    try {
                        m.setMeterValueType(MeterValueType.valueOf(meterValueType));
                    } catch (IllegalArgumentException e) {
                    _logger.error("MeterValue not supported, Check meterTemplate id:" + m.getId() + " " + m.getMeterType_fi_FI(), e);
                    }
                }
                meters.add(m);
            }
            o.setMeters(meters);
            results.add(o);
        }

        return results;
    }

    @Override
    public JSONObject getSmartWaysOperationTemplates(List<String> ids) throws PortalException {
        JSONObject result = JSONFactoryUtil.createJSONObject();
        JSONArray jarray = JSONFactoryUtil.createJSONArray();

        List<JournalFolder> folders = JournalFolderLocalServiceUtil.getFolders(SITOUMUS2050_GROUP_ID, Constants.COMMITMENT_OPERATION_TEMPLATES_100_SMART_WAYS_FOLDER_ID);
        for ( JournalFolder f : folders ) {
            addSmartWayOperationTemplatesFromFolder(f.getFolderId(), f.getName(), ids, jarray);
        }
        result.put("templates", jarray);
        return result;
    }

    private JSONArray addSmartWayOperationTemplatesFromFolder(long folderId, String category, List<String> testIds, JSONArray operationTemplates) throws PortalException {
        List<JournalArticle> articles = JournalArticleLocalServiceUtil.getArticles(SITOUMUS2050_GROUP_ID, folderId);
        articles = filterToLatest(articles);
        for (JournalArticle a : articles ) {
            JSONObject jo = LF100SmartWaysOperationTemplateReader.lfOperationTemplateArticleContentXMLToJSON(a.getContent());
            jo.put("articleId", a.getArticleId());
            jo.put("category", category);
            if (testIds == null || (testIds != null && (testIds.size() == 0 || testIds.contains(jo.getString("testId")))) ) {
                operationTemplates.put(jo);
            }
        }
        return operationTemplates;
    }

    private List<JournalArticle> filterToLatest(List<JournalArticle> articles) throws PortalException {
        Map<String, JournalArticle> map = new HashMap<>();
        for ( JournalArticle a : articles) {
            JournalArticle tmp = JournalArticleLocalServiceUtil.getLatestArticle(a.getResourcePrimKey());
            map.put(tmp.getArticleId(), tmp);
        }
        return new ArrayList<>(map.values());
    }

    @Override
    public List<MeterTemplate> getMeterTemplates(long folderId) throws PortalException {
        List<JournalArticle> articles = JournalArticleLocalServiceUtil.getArticles(SITOUMUS2050_GROUP_ID, folderId);
        articles = filterToLatest(articles);
        List<MeterTemplate> results = new ArrayList<>();
        for ( JournalArticle a : articles ) {
            JSONObject jm = LFArticleXMLToJsonTransformer.lfSimpleFlatArticleContentXMLToJSON(a.getContent());
            MeterTemplate m = new MeterTemplate();
            m.setId(a.getArticleId());
            m.setMeterType_fi_FI(jm.getString("meterType_fi_FI"));
            m.setMeterType_en_US(jm.getString("meterType_en_US"));
            m.setMeterType_sv_SE(jm.getString("meterType_sv_SE"));
            String meterValueType = jm.getString("meterValueType_fi_FI");
            if (StringUtils.isNotBlank(meterValueType)) {
                try {
                    m.setMeterValueType(MeterValueType.valueOf(meterValueType));
                } catch (IllegalArgumentException e) {
                    _logger.error("MeterValue not supported, Check meterTemplate id:" + m.getId() + " " + m.getMeterType_fi_FI(), e);
                }
            }
            results.add(m);
        }
        return results;
    }

    @Override
    public List<Commitment> getUserCommitments(Long userId) throws PortalException {
        Map<String, Commitment> results = new HashMap();
        List<ExpandoValue> exps = ExpandoValueLocalServiceUtil.getColumnValues(COMPANY_ID, JournalArticle.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, "creatorUserId", String.valueOf( userId), 0, 10000);
        for ( ExpandoValue e : exps ) {
            try {
                JournalArticle a = JournalArticleLocalServiceUtil.getArticle(e.getClassPK());
                Commitment c = getCommitment(a.getArticleId(), null, true);
                //Filter out articles which belongs to organization
                if ( StringUtils.isBlank(c.getOrganizationName())) {
                    results.put(a.getArticleId(), c);
                }
            } catch (PortalException e1) {
                _logger.warn("User article search can't get details about commitment, classPK: " + e.getClassPK() + ". Is article moved to trashcan?.", e);
            }
        }
        return new ArrayList<>(results.values());
    }

    @Override
    public List<Commitment> getOrganizationCommitments(String organization, String organizationId, Integer status) throws PortalException {
        Map<String, Commitment> results = new HashMap();
        List<ExpandoValue> exps = null;
        if(!organization.isEmpty())
            exps = ExpandoValueLocalServiceUtil.getColumnValues(COMPANY_ID, JournalArticle.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, "Organization name", organization, 0, 10000);
        else
            exps = ExpandoValueLocalServiceUtil.getColumnValues(COMPANY_ID, JournalArticle.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, "organizationId", organizationId, 0, 10000);
        for ( ExpandoValue e : exps ) {
            try {
                JournalArticle a = JournalArticleLocalServiceUtil.getArticle(e.getClassPK());
                Commitment c = getCommitment(a.getArticleId(), status, true);
                if (c != null ) {
                    results.put(a.getArticleId(), c);
                }
            } catch (PortalException e1) {
                _logger.warn("Organization article search can't get details about commitment, classPK: " + e.getClassPK() + ". Is article moved to trashcan?.", e);
            }
        }
        return new ArrayList<>( results.values());
    }

    @Override
    public Hits searchCommitments(String keywords, List<Long> categoryIds, String language, Integer start, Integer end, Long creatorUserId, String organization, CommitmentSort sort) throws SearchException {
        return searchCommitments(keywords, categoryIds, language, start, end, creatorUserId, organization, sort, true);
    }

    /**
     * Search commitments
     * @param keywords  Search terms
     * @param categoryIds list of categoryIds
     * @param language preferred language (search only content which have content in given lang). If not spesified, fi_FI is default
     * @param start pagination start index
     * @param end   pagination end index
     * @param creatorUserId commitment userId
     * @param organization commitment organization name
     * @param sort  Sort parameter, @see {@link CommitmentSort}
     * @param makeLocalizedSearch  If true then searches content only from given lang. False --> search from all langs
     * @return  Search hits @see {@link Hits}
     * @throws SearchException @see {@link SearchException}
     */
    private Hits searchCommitments(String keywords, List<Long> categoryIds, String language, Integer start, Integer end, Long creatorUserId, String organization, CommitmentSort sort, boolean makeLocalizedSearch) throws SearchException {
        if (categoryIds == null ) {
            categoryIds = new ArrayList<>();
        }
        long companyId = Constants.COMPANY_ID;
        long[] groupIds = new long[]{ SITOUMUS2050_GROUP_ID };
        if (StringUtils.isBlank(language)) {
            language = Constants.lang_fi;
        }
        Locale locale = locale_fi;
        if (language.equals(lang_en)) {
            locale = locale_en;
        }
        if (language.equals(lang_sv)) {
            locale = locale_sv;
        }

        List<Long> commitmentTypes = new ArrayList();
        if (categoryIds.contains(GREENDEAL_CATEGORY_ID)) {
            commitmentTypes.add(GREENDEAL_CATEGORY_ID);
        }
        if ( categoryIds.contains(COMMITMENT_CATEGORY_ID)) {
            commitmentTypes.add(COMMITMENT_CATEGORY_ID);
        }
        if ( categoryIds.contains(NUTRITION_CATEGORY_ID)) {
            commitmentTypes.add(NUTRITION_CATEGORY_ID);
        }
        if (commitmentTypes.isEmpty()) {
            commitmentTypes.add(GREENDEAL_CATEGORY_ID);
            commitmentTypes.add(COMMITMENT_CATEGORY_ID);
            commitmentTypes.add(NUTRITION_CATEGORY_ID);
        }
        categoryIds.remove(GREENDEAL_CATEGORY_ID);
        categoryIds.remove(COMMITMENT_CATEGORY_ID);
        categoryIds.remove(NUTRITION_CATEGORY_ID);

        FacetedSearcherManager facetedSearcherManager= FacetedSearcherManagerUtil.getFacetedSearcherManager();

        FacetedSearcher facetedSearcher = facetedSearcherManager.createFacetedSearcher();

        SearchContext searchContext = new SearchContext();

        String[] entryClassNames = { JournalArticle.class.getName() };
        searchContext.setEntryClassNames(entryClassNames);
        searchContext.setCompanyId(companyId);
        searchContext.setCommitImmediately(true);
        searchContext.setGroupIds(groupIds);
        if (makeLocalizedSearch) {
            searchContext.setLocale(locale);
        }

        Sort[] sorts;
        if ( sort == null) {
            sort = latest;
        }

        switch (sort) {
            case latest:
                sorts = new Sort[] {new Sort(Field.CREATE_DATE, 6, true)};
                break;
            case oldest:
                sorts = new Sort[] {new Sort(Field.CREATE_DATE, 6, false)};
                break;
            case alphabet_asc:
                sorts = new Sort[] {new Sort("localized_title_" + language + "_sortable", 3, false)};
                break;
            case alphabet_desc:
                sorts = new Sort[] {new Sort("localized_title_" + language + "_sortable", 3, true)};
                break;
            case most_popular:
                sorts = new Sort[] {new Sort(Field.RATINGS, false)};
                break;
            case most_viewed:
                sorts = new Sort[] {new Sort(Field.VIEW_COUNT, false)};
                break;
            case priority:
                sorts = new Sort[2];
                sorts[0] = new Sort(Field.PRIORITY, 7, true);
                sorts[1] = new Sort(Field.CREATE_DATE, 6, true);
                break;
            default:
                sorts = new Sort[] {new Sort(Field.CREATE_DATE, 6, false)};
                break;
        }

        searchContext.setSorts(sorts);

        if ( start != null && end != null ) {
            searchContext.setStart(start);
            searchContext.setEnd(end);
        }

        //TEXT QUERY
        if (StringUtils.isNotBlank(keywords)){
//                keywords = buildTextQuery(keywords);
            searchContext.setKeywords(keywords);
        }

        List<BooleanClause> booleanClauses = new ArrayList<BooleanClause>();

        //Add some localization logic which gives better results. Why searcContext.setLocale() does not work, or what is the purpose of that...?
        if ( makeLocalizedSearch ) {
            if ( language.equals(lang_fi)) {
                //if display language is finnish then exclude all commitments which gave generic fi title (these articles does not have really finnish content, but liferay requires that default language exists...)
                // so this query filters those out.
//                BooleanQueryImpl bqLocTitle = new BooleanQueryImpl();
//                bqLocTitle.addRequiredTerm("title_fi_FI", Constants.GENERIC_COMMITMENT_FI_TITLE, true, false);
//                bqLocTitle.addExactTerm("title_fi_FI", Constants.GENERIC_COMMITMENT_FI_TITLE);
//                BooleanClause locTitleClause = BooleanClauseFactoryUtil.create(bqLocTitle, BooleanClauseOccur.MUST_NOT.getName());
//                booleanClauses.add(locTitleClause);

                //It appears that requiredterm nor exactTerm does not provide good results, thus we check if operationTitle_fi_FI has some text
                //Every commitment should have something in this field...
                TermRangeQuery tmrOperQuer = new TermRangeQueryImpl("ddm__text__31797__operationTitle_fi_FI", "a*", "z*", true, true);
                BooleanClause tmrOperQuerClause = BooleanClauseFactoryUtil.create(tmrOperQuer, BooleanClauseOccur.MUST.getName());
                booleanClauses.add(tmrOperQuerClause);
            }
            if ( language.equals(lang_en)) {
                //if display language is english then include all commitments which have english title, ie. excludes all commitmens which does not have localized content.
                TermRangeQuery tmrquer = new TermRangeQueryImpl("title_en_US", "a*", "z*", true, true);
                BooleanClause locTitleClause = BooleanClauseFactoryUtil.create(tmrquer, BooleanClauseOccur.MUST.getName());
                booleanClauses.add(locTitleClause);
            }
        }

        //Limit search only to creator articles
        if ( creatorUserId != null ) {
            BooleanQueryImpl bqCreatorUserId = new BooleanQueryImpl();
            TermQuery userIdQuery = new TermQueryImpl("expando__keyword__custom_fields__creatorUserId", String.valueOf( creatorUserId));
            bqCreatorUserId.add(userIdQuery, BooleanClauseOccur.MUST);
            BooleanClause creatorUserIdClause = BooleanClauseFactoryUtil.create(bqCreatorUserId, BooleanClauseOccur.MUST.getName());
            booleanClauses.add(creatorUserIdClause);
        }

        //Limit search only to organization articles
        if ( StringUtils.isNotBlank(organization) ) {
            BooleanQueryImpl bqOrgName = new BooleanQueryImpl();
            TermQuery orgQuery = new TermQueryImpl("expando__keyword__custom_fields__Organization name", organization);
            bqOrgName.add(orgQuery, BooleanClauseOccur.MUST);
            BooleanClause orgNameClause = BooleanClauseFactoryUtil.create(bqOrgName, BooleanClauseOccur.MUST.getName());
            booleanClauses.add(orgNameClause);
        }

        //Limit search only to Commitment structures
        String structureKey = GLOBAL_COMMITMENT_DDM_STRUCTURE_KEY;
        BooleanQueryImpl bqStructureKeyQuery = new BooleanQueryImpl();
        TermQuery structureQuery = new TermQueryImpl("ddmStructureKey", structureKey);
        bqStructureKeyQuery.add(structureQuery, BooleanClauseOccur.MUST);
        BooleanClause structureClause = BooleanClauseFactoryUtil.create(bqStructureKeyQuery, BooleanClauseOccur.MUST.getName());
        booleanClauses.add(structureClause);

        //Add commitment type category clauses
        BooleanQueryImpl bqCommitmentType = new BooleanQueryImpl();
        for (Long c : commitmentTypes) {
            TermQuery condition = new TermQueryImpl(Field.ASSET_CATEGORY_IDS, String.valueOf(c));
            bqCommitmentType.add(condition, BooleanClauseOccur.SHOULD);
        }
        BooleanClause commitmentClause = BooleanClauseFactoryUtil.create(bqCommitmentType, BooleanClauseOccur.MUST.getName());
        booleanClauses.add(commitmentClause);

        //Add category filters
        Map<Long, List<Long>> catmap = new HashMap();
        for (Long c : categoryIds ) {
            try {
                AssetCategory ac = AssetCategoryLocalServiceUtil.getAssetCategory(c);
                if (catmap.containsKey(ac.getVocabularyId())) {
                    catmap.get(ac.getVocabularyId()).add(c);
                } else {
                    List<Long> l = new ArrayList<>();
                    l.add(c);
                    catmap.put(ac.getVocabularyId(), l);
                }
            } catch (PortalException e) {
                _logger.error("Category not found, id: " + c);
            }
        }
        for (List<Long> catlist : catmap.values()) {
            BooleanQueryImpl bqCategories = new BooleanQueryImpl();
            for (Long c : catlist) {
                TermQuery condition = new TermQueryImpl(Field.ASSET_CATEGORY_IDS, String.valueOf(c));
                bqCategories.add(condition, BooleanClauseOccur.SHOULD);
            }
            BooleanClause categoryClause = BooleanClauseFactoryUtil.create(bqCategories, BooleanClauseOccur.MUST.getName());
            booleanClauses.add(categoryClause);
        }
        searchContext.setBooleanClauses(booleanClauses.toArray(new BooleanClause[booleanClauses.size()]));
        Facet assetEntriesFacet = new AssetEntriesFacet(searchContext);
        assetEntriesFacet.setStatic(true);
        searchContext.addFacet(assetEntriesFacet);

        //Define return fields, this will boost performance significantly
        searchContext.getQueryConfig().setSelectedFieldNames(
                "articleId",
                "title_" + language,
                "ddm__text__31797__shortDescription_" + language,
                "assetCategoryIds",
                "expando__keyword__custom_fields__Organization name",
                "expando__keyword__custom_fields__organizationId",
                "expando__custom_fields__creatorUserName",
                "expando__keyword__custom_fields__creatorUserId",
                "status",
                "createdDate",
                "priority",
                "expando__keyword__custom_fields__creatorPortrait",
                "expando__keyword__custom_fields__organizationLogo",
                "ddm__keyword__31797__address_fi_FI"
        );


        Hits hits = facetedSearcher.search(searchContext);
        if ( _logger.isDebugEnabled()) {
            _logger.debug("Liferay faceted search time: " + hits.getSearchTime());
            _logger.debug("Hits: " + hits.getLength());
        }
        return hits;
    }

    private static String buildTextQuery(String text) {
        if ( StringUtils.isNotBlank(text)) {
            text = text.trim() + "*";
            text = text.replaceAll(" ", "* AND ");
        }
        return text;
    }

    @Override
    public String addCommitmentImageToDocumentLibrary(CommitmentImage commitmentImage) throws PortalException {
        long repositoryId = Constants.SITOUMUS2050_GROUP_ID;
        String title = commitmentImage.getFileName();
        String mimeType = commitmentImage.getMimeType();
        byte[] bytes = Base64.getDecoder().decode(commitmentImage.getImageData());
        ServiceContext sc = populateServiceContext(Constants.ADMIN_USER_ID, WorkflowConstants.ACTION_PUBLISH);

        FileEntry fe = DLAppLocalServiceUtil.addFileEntry(
                ADMIN_USER_ID,
                repositoryId,
                COMMITMENT_IMAGE_FOLDER_ID,
                title,
                mimeType,
                bytes,
                sc
                );


        //TODO: does liferay have util or smth.. that generates url?
        String url = "/documents/" + repositoryId + "/" + COMMITMENT_IMAGE_FOLDER_ID + "/" + fe.getTitle() + "/" + fe.getUuid() + "?t=" + fe.getModifiedDate().getTime();
        return url;
    }

    @Override
    public int countPublishedCommitmentsByCategory(long categoryId) throws SearchException {
        List<Long> cats = new ArrayList<>();
        cats.add(categoryId);
        Hits hits = searchCommitments(null, cats, null, null, null, null, null, null, false);
        return hits.getLength();
    }

    @Override
    public List<Commitment> getAllLatestApprovedCommitments() throws PortalException {
        return getAllLatestCommitmentsByStatus(WorkflowConstants.STATUS_APPROVED);
    }

    @Override
    public List<Commitment> getRangeOfLatestApprovedCommitments(int start, int end) throws PortalException {
        return getRangeOfLatestCommitmentsByStatus(WorkflowConstants.STATUS_APPROVED, start, end);
    }

    @Override
    public List<Commitment> getRangeOfAllCommitments(int start, int end) throws PortalException {
        return getRangeOfLatestCommitmentsByStatus(WorkflowConstants.STATUS_ANY, start, end);
    }

    /**
     * Get all latest commitments by status, @see {@link WorkflowConstants} for status codes
     * @param status
     * @return list of commitments
     * @throws PortalException
     */
    private List<Commitment> getAllLatestCommitmentsByStatus(int status) throws PortalException {
        List<JournalArticle> articles = JournalArticleLocalServiceUtil.getArticlesByStructureId(
                Constants.SITOUMUS2050_GROUP_ID, Constants.GLOBAL_COMMITMENT_DDM_STRUCTURE_KEY,
                status,-1, -1, null);
        Map<String, Commitment> map = new HashMap<>();
        for (JournalArticle a : articles ) {
            Commitment c = getCommitment(a.getArticleId(), status, true);
            map.put(c.getId(), c);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * Get a range of all commitments by status @see {@link WorkflowConstants} for status codes
     * @param status
     * @param start
     * @param end
     * @return list of commitments
     * @throws PortalException
     */
    private List<Commitment> getRangeOfLatestCommitmentsByStatus(int status, int start, int end) throws PortalException {
        List<JournalArticle> articles = JournalArticleLocalServiceUtil.getArticlesByStructureId(
                Constants.SITOUMUS2050_GROUP_ID, Constants.GLOBAL_COMMITMENT_DDM_STRUCTURE_KEY,
                status,start, end, null);
        Map<String, Commitment> map = new HashMap<>();
        for (JournalArticle a : articles ) {
            Commitment c = getCommitment(a.getArticleId(), status, true);
            map.put(c.getId(), c);
        }
        return new ArrayList<>(map.values());
    }

    @Override
    public List<Commitment> getAllLatestCommitments() throws PortalException {
        return getAllLatestCommitmentsByStatus(WorkflowConstants.STATUS_ANY);
    }

    @Override
    public int getDoneLifestyleTestCount() {
        String testAPIURL = PropsUtil.get("lifestyletest.api.url.base");
        int count = 0;

        URL obj;
        try {
            obj = new URL(testAPIURL + "/api/extendedResults/count");
        } catch (MalformedURLException e) {
            _logger.error("Malformed URL '" + testAPIURL + "'");
            return count;
        }

        try {
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            int responseCode = con.getResponseCode();

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                count = Integer.parseInt(response.toString());

            }
        } catch (IOException e) {
            _logger.error(e.getLocalizedMessage());
        }

        _logger.info("Found number of done Elämäntapatesti to be " + count);
        return count;
    }

    @Override
    public void recreateAnalysisDb() {
        Gson gson = new GsonBuilder().create();

        List<Commitment> cs = null;
        try {
            cs = getAllLatestCommitments();
        } catch (PortalException e) {
            e.printStackTrace();
        }

        // Truncate all Commitment Analysis tables
        try {
            CommitmentLocalServiceUtil.truncateTable();
        } catch (ORMException e) {
            e.printStackTrace();
        }
        try {
            DoneOperationLocalServiceUtil.truncateTable();
        } catch (ORMException e) {
            e.printStackTrace();
        }
        try {
            MeterLocalServiceUtil.truncateTable();
        } catch (ORMException e) {
            e.printStackTrace();
        }
        try {
            OperationLocalServiceUtil.truncateTable();
        } catch (ORMException e) {
            e.printStackTrace();
        }
        try {
            ReportLocalServiceUtil.truncateTable();
        } catch (ORMException e) {
            e.printStackTrace();
        }
        try {
            ReportMeterLocalServiceUtil.truncateTable();
        } catch (ORMException e) {
            e.printStackTrace();
        }

        // Create JSON of all commitments and store in CA tables
        cs.forEach(commitment -> {

            try {
                User u = userService.getUser(commitment.getCreatedByUserId());
                if ( u != null && u.getEmailAddress() != null ) {
                    commitment.setCreatorEmail(u.getEmailAddress());
                }
            } catch (PortalException e) {
                _logger.warn("Cannot create extended information", e);
            }

            org.json.JSONObject obj = new org.json.JSONObject(gson.toJson(commitment));

            // Attempt to store the JSON in CA db
            try {
                _logger.info("Working on commitment with ID: " + obj.getString("id"));
                CommitmentLocalServiceUtil.addCommitmentAsJSON(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        _logger.debug("----------");
        _logger.debug("Recreate Commitment Analysis Database has finished.");

        _logger.debug("Performing calculations...");

        CommitmentAnalysisResultLocalServiceUtil.performResultCalculations();

        _logger.debug("----------");
        _logger.debug("Commitment analysis calculations finished.");

    }
}
