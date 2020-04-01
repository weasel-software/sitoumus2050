package com.soikea.commitment2050.service;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryProperty;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchException;
import com.soikea.commitment2050.model.*;

import javax.mail.internet.AddressException;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;


public interface CommitmentService {

    /**
     * Add categories to commitment
     * @param vocabularyId Vocabylary id
     * @param journalArticle Article that represents commitment
     * @param categoryIds   list of categoryids
     * @throws PortalException @see {@link PortalException}
     */
    void addVocabularyCategoriesToCommitmentByCategoryId(long vocabularyId, JournalArticle journalArticle, long[] categoryIds) throws PortalException;

    /**
     * Add category to commitment
     * @param vocabularyId Vocabylary id
     * @param journalArticle Article that represents commitment
     * @param categoryId list of categoryids
     * @throws PortalException @see {@link PortalException}
     */
    void addVocabularyCategoryToCommitmentByCategoryId(long vocabularyId, JournalArticle journalArticle, long categoryId) throws PortalException;

    /**
     *
     * Useful during migration: no need to map category names to category ids.
     *
     * @param vocabularyId Vocabylary id
     * @param journalArticle must not be null
     * @param categoryNames list of category names
     * @throws PortalException @see {@link PortalException}
     */
    void addVocabularyCategoriesToCommitmentByCategoryName(long vocabularyId, JournalArticle journalArticle, Set<String> categoryNames) throws PortalException;

    /**
     *
     * Useful during migration: no need to map category names to category ids.
     *
     * @param vocabularyId Vocabylary id
     * @param journalArticle must not be null
     * @param categoryName category name
     * @throws PortalException @see {@link PortalException}
     */
    void addVocabularyCategoryToCommitmentByCategoryName(long vocabularyId, JournalArticle journalArticle, String categoryName) throws PortalException;

    /**
     * Creates a new commitment.
     * If commitment id is give, tries to create new commitment, if id is already taken throws PortalException.
     * Commitment id can be null. Liferay will create it automatically. Version will be always 1.0 for new commitments
     * @param commitment commitment commitment
     * @param workflowAction Liferay's workflow id @see {@link com.liferay.portal.kernel.workflow.WorkflowConstants}
     * @param autoArticleId if true liferay create articleId automatically
     * @return JournalArticle (Liferay's generic presentation of Commitment) @see {@link JournalArticle}
     * @throws PortalException @see {@link PortalException}
     */
    JournalArticle createCommitment(Commitment commitment, int workflowAction, boolean autoArticleId) throws PortalException;

    /**
     * Updates commitment, Commitment has to have id and version.
     * Version number should match to existing newest version. Liferay will increase version automatically.
     * @param commitment commitment
     * @param workflowAction Liferay's workflow id @see {@link com.liferay.portal.kernel.workflow.WorkflowConstants}
     * @return JournalArticle (Liferay's generic presentation of Commitment) @see {@link JournalArticle}
     * @throws PortalException @see {@link PortalException}
     */
    JournalArticle updateCommitment(Commitment commitment, int workflowAction) throws PortalException;

    /**
     * Creates reports (every report is one article in Liferay) which are part of commitment. Note! this does not update actual commitment.
     * This method is created mainly for migration, although it can be used elsewhere if it makes sense.
     *
     * @param commitment Commitment
     * @param workflowAction Liferay's workflow id @see {@link com.liferay.portal.kernel.workflow.WorkflowConstants}
     * @param autoArticleId if true liferay create articleId automatically
     * @return List of JournalArticles (Liferay's generic presentation of Report) @see {@link JournalArticle}
     * @throws PortalException @see {@link PortalException}
     */
    List<JournalArticle> createCommitmentReports(Commitment commitment, int workflowAction, boolean autoArticleId) throws PortalException;

    /**
     * Creates reports (every report is one article in Liferay) which are part of commitment. Note! this does not update actual commitment.
     * This method is created mainly for migration, although it can be used elsewhere if it makes sense.
     * @param commitment commitment
     * @param workflowAction Liferay's workflow id @see {@link com.liferay.portal.kernel.workflow.WorkflowConstants}
     * @param autoArticleId if true liferay create articleId automatically
     * @return List of JournalArticles  (Liferay's generic presentation of Reports) @see {@link JournalArticle}
     * @throws PortalException @see {@link PortalException}
     */
    List<JournalArticle> createCommitmentGenericReports(Commitment commitment, int workflowAction, boolean autoArticleId) throws PortalException;

    /**
     * Creates commitment report and returns report article. This method adds always new report.
     * @param report    Report
     * @param workflowAction    Liferay's workflow id @see {@link com.liferay.portal.kernel.workflow.WorkflowConstants}
     * @param autoArticleId if true liferay create articleId automatically
     * @return JournalArticle (Liferay's generic presentation of Report) @see {@link JournalArticle}
     * @throws PortalException @see {@link PortalException}
     */
    JournalArticle createCommitmentReport(Report report, int workflowAction, boolean autoArticleId) throws PortalException;

    /**
     * Updates commitment's report, Report has to have id and version.
     * Version number should match to existing newest version. Liferay will increase version automatically.
     * @param report report
     * @param workflowAction Liferay's workflow id @see {@link com.liferay.portal.kernel.workflow.WorkflowConstants}
     * @return JournalArticle (Liferay's generic presentation of Report) @see {@link JournalArticle}
     * @throws PortalException
     */
    JournalArticle updateCommitmentReport(Report report, int workflowAction) throws PortalException;

    /**
     * Add one "like" score to article. This can be any article in Liferay
     * This does not check if user is logged in or not.
     * @param articleId articleId
     * @return amount of scores
     */
    int addRatingsEntryScore(String articleId);

    /**
     * Get articles "likes"
     * @param articleId articleId
     * @return amount of scores
     */
    int getRatingsEntryScore(String articleId);

    /**
     * Add hiearchial relation ship link between two articles
     * This can be used to link commitment to other commitment (when user "joins" to existing commitment)
     *
     * @param parentCommitmentArticleId parent commitment (articleId)
     * @param childCommitmentArticleId child commitment (articleId)
     * @throws PortalException @see {@link PortalException}
     */
    void joinToParentCommitment(String parentCommitmentArticleId, String childCommitmentArticleId) throws PortalException;

    /**
     * Calculate amount of joined commitments
     *
     * @param commitmentId commitment id
     * @return amount of joined (child) commitments
     * @throws PortalException  @see {@link PortalException}
     */
    int caclAmountOfJoinedCommitmens(String commitmentId) throws PortalException;

    /**
     * Get relatedArticles
     * @param articleId articleId
     * @return List of JournalArticles
     * @throws PortalException @see {@link PortalException}
     */
    List<JournalArticle> getRelatedArticles(String articleId) throws PortalException;

    /**
     * Get approved assetlink references to child commitments and update commitments priority value if
     * results don't match with article priority value
     *
     * @param commitmentId  parent commitmentid
     * @return  List of assetlinks
     * @throws PortalException @see {@link PortalException}
     */
    List<AssetLink> getCommitmentAssetLinkChildsAndUpdatePriority(String commitmentId) throws PortalException;

    /**
     * Get commitment's child (joined) commitments
     * @param commitmentId parent commitment id
     * @return  list of joined commitments
     * @throws PortalException @see {@link PortalException}
     */
    List<Commitment> getCommitmentJoinedCommitments(String commitmentId) throws PortalException;

    /**
     * Add related relationship link between two articles.
     * This can be used to link for example commitment and report type articles
     * @param commitmentArticleId commitment id
     * @param reportArticleId report articleId
     * @throws PortalException @see {@link PortalException}
     */
    void addRelatedRelationshipLink(String commitmentArticleId, String reportArticleId) throws PortalException;

    /**
     * Send commitment notify email to admins
     * @param emailTo email to address
     * @param commitment commitment
     * @throws PortalException @see {@link PortalException}
     * @throws AddressException @see {@link AddressException}
     */
    void sendCommitmentNotifyEmail(String emailTo, Commitment commitment) throws PortalException, AddressException;

    /**
     *
     * Get full presentation of commitment and related reports
     * If article is not found with given id and status, returns null
     *
     * @param commitmentId commitmentId
     * @param status Liferay's workflow id @see {@link com.liferay.portal.kernel.workflow.WorkflowConstants}. If null return the latest version of article no matter what is the status.
     * @return Commitments @see {@link Commitment}
     * @throws PortalException @see {@link PortalException}
     */
    Commitment getCommitment(String commitmentId, Integer status, boolean fetchReports) throws PortalException;

    /**
     * Get operation reports of commitments.
     * @param commitmentId commitmentId
     * @param operationId Commitment operationId
     * @return list of Commitment operation reports
     * @throws PortalException @see {@link PortalException}
     */
    List<Report> getOperationReports(String commitmentId, String operationId) throws PortalException;

    /**
     * Get report
     * @param reportId reportId
     * @param status Liferay's workflow id @see {@link com.liferay.portal.kernel.workflow.WorkflowConstants}. If null return the latest version of article no matter what is the status.
     * @return  Report
     * @throws PortalException @see {@link PortalException}
     */
    Report getReport(String reportId, Integer status) throws PortalException;

  List<Commitment> getGreenDealCommitments();

  List<AssetCategory> getCategoriesInHierarchy(long rootCategoryId) throws PortalException;

  List<AssetCategoryProperty> getCategoryProperties(List<Long> categoryIds) throws PortalException;

  /**
     * Get all reports of commitment
     * @param commitmentId commitmentId
     * @return List of reports @see {@link Report}
     * @throws PortalException @see {@link PortalException}
     */
    List<Report> getCommitmentReports(String commitmentId) throws PortalException;

    /**
     * Get operation templates
     * @param folderId Operation templates folder id
     * @return List of OperationTemplates @see {@link OperationTemplate}
     * @throws PortalException @see {@link PortalException}
     */
    List<OperationTemplate> getOperationTemplates(long folderId) throws PortalException;

    /**
     * Get 100 smart ways operation templates as json
     *
     * if list of testids is given then filters out other template
     *
     * @param testIds list of testIds, user defines testIds for templates
     * @return List of OperationTemplates @see {@link OperationTemplate}
     * @throws PortalException @see {@link PortalException}
     */
    JSONObject getSmartWaysOperationTemplates(List<String> testIds) throws PortalException;

    /**
     * Get meter templates
     * @param folderId  folder id of templates
     * @return  List of meter templates @see {@link MeterTemplate}
     * @throws PortalException @see {@link PortalException}
     */
    List<MeterTemplate> getMeterTemplates(long folderId) throws PortalException;

    /**
     * Get user commitments.
     * This  returns latest commitments no matter if it is published or not.
     * Main use case is when showing logged in user's commitments
     * @param userId UserId
     * @return List of commitments @see {@link Commitment}
     * @throws PortalException @see {@link PortalException}
     */
    List<Commitment> getUserCommitments(Long userId) throws PortalException;

    /**
     * Get organization commitments by name or id
     * if status is null, This returns latest commitments, no matter if it is published or not.
     *
     * @param organization Organization name
     * @param organizationId Organization id
     * @param status Commitment status, if status is null, then returns latest commitments (no matter if it is published or not) @see {@link com.liferay.portal.kernel.workflow.WorkflowConstants},
     * @return List of Commitments @see {@link Commitment}
     * @throws PortalException @see {@link PortalException}
     */
    List<Commitment> getOrganizationCommitments(String organization, String organizationId, Integer status) throws PortalException;

    /**
     * Search commitments
     *
     * Search approved articles which structurekey is @see {@link Constants#GLOBAL_COMMITMENT_DDM_STRUCTURE_KEY},
     * from company @see {@link Constants#COMPANY_ID} and group @see {@link Constants#SITOUMUS2050_GROUP_ID}
     *
     * If sort is not provided results are sorted by create date
     *
     * Given categoryIds will be automatically transferred to different logical search terms.
     *
     * @param keywords  search string
     * @param categoryIds   list of categoryIds
     * @param language      language
     * @param start     start index (pagination)
     * @param end       end index (pagination)
     * @param creatorUserId creator user id. Note, search target field is article's expando field "creatorUserId"
     * @param organization  organization name. Note, search target field article's expando field "Organization name"
     * @param sort          sort order, @see {@link CommitmentSort}, if null then default sort order is by create date
     * @return  Hits of commitments which match to terms. Hits are Liferay presentation of search results, which can be read and converted to articles/commitments if needed. However, that would be time consuming so raw hits.toList data is adviced to use in most cases...
     * @throws SearchException  @see {@link SearchException}
     */
    Hits searchCommitments(String keywords, List<Long> categoryIds, String language, Integer start, Integer end, Long creatorUserId, String organization, CommitmentSort sort) throws SearchException;

    /**
     * Add commitment image to document library, folder: "Sitoumusten kuvat"
     * @param commitmentImage Commitment image information @see {@link CommitmentImage}
     * @return image url if successfully added
     * @throws PortalException @see {@link PortalException}
     */
    String addCommitmentImageToDocumentLibrary(CommitmentImage commitmentImage) throws PortalException;

    /**
     *
     * Count commitments by category id
     * @param categoryId categoryid
     * @return amount of commitments
     */
    int countPublishedCommitmentsByCategory(long categoryId) throws SearchException;

    /**
     * Get all approved commitments
     *
     * @return list of approved commitments
     */
    List<Commitment> getAllLatestApprovedCommitments() throws PortalException;

    /**
     * Get a range of all commitments
     *
     * @return list of commitments with any status
     */
    List<Commitment> getRangeOfAllCommitments(int start, int end) throws PortalException;

    /**
     * Get a range of approved commitments
     *
     * @return list of approved commitments
     */
    List<Commitment> getRangeOfLatestApprovedCommitments(int start, int end) throws PortalException;

    /**
     * Get all latest commitments (both drafts and approved)
     *
     *
     */
    List<Commitment> getAllLatestCommitments() throws PortalException;

    int getDoneLifestyleTestCount();

    void recreateAnalysisDb() throws PortalException;
}
