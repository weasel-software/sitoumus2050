<#assign assetEntryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService") />
<#assign journalArticleLocalService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />
<#assign assetCategoryPropertyLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetCategoryPropertyLocalService")>

<#assign article = journalArticleLocalService.getArticle(20143, .vars['reserved-article-id'].data)>
<#assign asset = assetEntryLocalService.getEntry('com.liferay.journal.model.JournalArticle', article.resourcePrimKey) >

<div>
    <h3>Kategoriat</h3>
    <p>
        <#list asset.getCategories() as category>

        <#-- Categories can store key / value data. One value could be the category picture.
            The picture could be fetched as follows:

            assign imageUrl = assetCategoryPropertyLocalService.getCategoryProperty(category.getCategoryId(), "imageUrl")

         -->
            ${category.name}<#if category?has_next>, </#if>
        </#list>
    </p>
</div>


<div>
    Aloituspvm:
    <#assign Aloituspvm_Data = getterUtil.getString(startDate.getData())>

    <#if validator.isNotNull(Aloituspvm_Data)>
        <#assign Aloituspvm_DateObj = dateUtil.parseDate("yyyy-MM-dd", Aloituspvm_Data, locale)>

        ${dateUtil.getDate(Aloituspvm_DateObj, "dd MMM yyyy - HH:mm:ss", locale)}
    </#if>
</div>

<div>
    Lopetuspvm:
    <#assign endDate_Data = getterUtil.getString(endDate.getData())>

    <#if validator.isNotNull(endDate_Data)>
        <#assign endDate_Data_DateObj = dateUtil.parseDate("yyyy-MM-dd", endDate_Data, locale)>

        ${dateUtil.getDate(endDate_Data_DateObj, "dd MMM yyyy - HH:mm:ss", locale)}
    </#if>
</div>

<div>
    <h2>Sitoumus lyhyesti</h2>
    <div>
        ${shortDescription.getData()}
    </div>
</div>

<div>
    <h2>Taustatietoa</h2>
    <div>
        ${backgroundInformation.getData()}
    </div>
</div>

<div>
    <h2>Mitä uutta sitoumuksessa</h2>
    <div>
        ${innovation.getData()}
    </div>
</div>

<div>
    <h2>Toimenpiteet ja tavoitteet</h2>
    <div>
        <#if operationTitle.getSiblings()?has_content>

            <#list operationTitle.getSiblings() as cur_operationTitle>
                <h4>${cur_operationTitle.getData()}</h4>
                <p>${cur_operationTitle.operationDescription.data}</p>
                <#if cur_operationTitle.meterType.getSiblings()?has_content>
                    <p><b>Mittarit:</b>
                    </p>
                    <#list cur_operationTitle.meterType.getSiblings() as cur_meterType>
                        <p>
                            Mittari:"${(cur_meterType.data)!?json_string}"<br/>
                            Mittarin arvon tyyppi:"${(cur_meterType.meterValueType.data)!?json_string}"<br/>
                            Lähtötaso:"${(cur_meterType.startingLevel.data)!?json_string}"<br/>
                            Tavoitetaso:"${(cur_meterType.targetLevel.data)!?json_string}"<br/>
                            Graafi:"${(cur_meterType.meterChartType.data)!?json_string}"<br/>
                            <#if cur_meterType?has_next>,</#if>
                        </p>
                    </#list>
                </#if>
            </#list>
        </#if>
    </div>
</div>

<div>
    <h2>Osoite</h2>
    <div>
        ${address.getData()!""}
    </div>
</div>
<div>
    <h2>Koordinaatit kartalla</h2>
    <div>
    ${(geolocation.data)!""}
    </div>
</div>

<pre>
    Raportointiväli: ${(reportingInterval.data)!""}
    Raportointimuistutus: <#if reportReminder?has_content>"${getterUtil.getBoolean(reportReminder.getData())?c}"<#else>"false"</#if>
    Sitoumuskriteerit: <#if acceptCriterias?has_content>"${getterUtil.getBoolean(acceptCriterias.getData())?c}"<#else>"false"</#if>
</pre>

