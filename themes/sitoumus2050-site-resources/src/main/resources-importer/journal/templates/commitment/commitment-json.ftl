<#assign assetEntryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService") />
<#assign journalArticleLocalService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />
<#assign assetCategoryPropertyLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetCategoryPropertyLocalService")/>
<#assign article = journalArticleLocalService.getArticle(20143, .vars['reserved-article-id'].data)/>
<#assign assetCategoryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetCategoryLocalService")/>
<#assign userLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.UserLocalService")/>

<#assign  categories = assetCategoryLocalService.getCategories("com.liferay.journal.model.JournalArticle", article.getResourcePrimKey())/>
<#assign userName = article.getUserName()/>

<#assign articleExp = article.getExpandoBridge()/>
<#attempt>
        <#assign organizationName = articleExp.getAttribute("Organization name") />
    <#recover>
        <#assign organizationName = "" />
</#attempt>
<#attempt>
    <#assign creatorUserId = articleExp.getAttribute("creatorUserId") />
    <#recover>
        <#assign creatorUserId = "" />
</#attempt>
<#attempt>
    <#assign creatorUserName = articleExp.getAttribute("creatorUserName") />
    <#recover>
        <#assign creatorUserName = "" />
</#attempt>


<#assign startDate_Data = getterUtil.getString(startDate.getData())>

<#assign endDate_Data = getterUtil.getString(endDate.getData())>

{
"id":"${(.vars['reserved-article-id'].data!)}",
"title":"${(.vars['reserved-article-title'].data!"")?json_string}",
"userName":"${(creatorUserName!"")?json_string}",
"userId":"${(creatorUserId!"")?json_string}",
"organizationName":"${(organizationName!"")?json_string}",
"startDate":"${(startDate_Data!)?json_string}",
"endDate":"${(endDate_Data!)?json_string}",
"shortDescription":"${(shortDescription.data!"")?json_string}",
"backgroundInformation":"${(backgroundInformation.data!"")?json_string}",
"innovation":"${(innovation.data!"")?json_string}",
"commitmentImageUrl":"${(commitmentImageUrl.data!"")?json_string}",
"operations": [
<#if operationTitle.getSiblings()?has_content>

    <#list operationTitle.getSiblings() as cur_operationTitle>
    {   "operationTitle":"${(cur_operationTitle.data!"")?json_string}",
        "operationId":"${(cur_operationTitle.operationId.data!"")?json_string}",
        "operationDescription":"${(cur_operationTitle.operationDescription.data!"")?json_string}",
        "meters": [
        <#if cur_operationTitle.meterType.getSiblings()?has_content>
            <#list cur_operationTitle.meterType.getSiblings() as cur_meterType>
            {
                "meterType":"${(cur_meterType.data!"")?json_string}",
                "meterId":"${(cur_meterType.meterId.data!"")?json_string}",
                "meterValueType":"${(cur_meterType.meterValueType.data!"")?json_string}",
                "startingLevel":"${(cur_meterType.startingLevel.data!"")?json_string}",
                "targetLevel":"${(cur_meterType.targetLevel.data!"")?json_string}",
                "meterChartType":"${(cur_meterType.meterChartType.data!"")?json_string}"
                "meterCategory":"${(cur_meterType.meterCategory.data!"")?json_string}",
            }<#if cur_meterType?has_next>,</#if>
            </#list>
        </#if>
        ]
    }<#if cur_operationTitle?has_next>,</#if>
    </#list>
</#if>
],
"address": "${(address.data!"")?json_string}",
<#assign latitude = 0/>
<#assign longitude = 0/>

<#if geolocation?has_content>
    <#assign geolocationJSONObject = jsonFactoryUtil.createJSONObject(geolocation.getData())>
    <#assign latitude = geolocationJSONObject.getDouble("latitude")>
    <#assign longitude = geolocationJSONObject.getDouble("longitude")>
    "geolocation": {
        "latitude":"${latitude!0.0}",
        "longitude":"${longitude!0.0}"
    },
</#if>
"categories":[
        <#list categories as category>
        {
            "categoryId": ${(category.categoryId!0)?json_string},
            "parentCategoryId": ${(category.parentCategoryId!0)?json_string},
            "vocabularyId": ${(category.vocabularyId!0)?json_string},
            "name": "${(category.name!"")?json_string}",
            "titleCurrentValue": "${(category.titleCurrentValue!"")?json_string}",
            <#assign icon = "" />
            <#if category.getVocabularyId() == 31800 || category.getVocabularyId() == 31801 || category.getVocabularyId() == 31802>
                <#attempt>
                    <#assign icon = assetCategoryPropertyLocalService.getCategoryProperty(category.getCategoryId(), "icon").getValue()/>
                    <#recover>
                </#attempt>
            </#if>
            "icon": "${icon}"
        }<#if category?has_next>,</#if>
        </#list>
    ],
"reportingInterval":"${(reportingInterval.data!)?json_string}",
"reportReminder":<#if reportReminder?has_content>"${getterUtil.getBoolean(reportReminder.getData())?c}"<#else>"false"</#if>,
"acceptCriterias":<#if acceptCriterias?has_content>"${getterUtil.getBoolean(acceptCriterias.getData())?c}"<#else>"false"</#if>,
"doneOperations": [
<#if doneOperation.getSiblings()?has_content>

    <#list doneOperation.getSiblings() as cur_operation>
    {   "operationTitle":"${(cur_operation.data!"")?json_string}",
        "operationCategory":"${(cur_operation.doneOperationCategory.data!"")?json_string}"
    }<#if cur_operation?has_next>,</#if>
    </#list>
</#if>
]
}