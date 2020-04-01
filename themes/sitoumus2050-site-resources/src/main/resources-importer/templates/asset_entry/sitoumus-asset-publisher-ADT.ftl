<#assign userLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.UserLocalService")>
<#assign catLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetCategoryLocalService") />
<#assign groupLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.GroupLocalService")>

<#if entries?has_content>
    <div class="row">

        <#list entries as entry> <#-- entry is JournalArticle -->
            <#assign renderer = entry.getAssetRenderer() /> <#-- JournalArticleAssetRenderer -->
            <#assign journalArticle = renderer.getArticle() /> <#-- JournalArticleImpl -->
            <#assign document = saxReaderUtil.read(journalArticle.getContentByLocale(locale)) />
            <#assign rootElement = document.getRootElement() />
            <#assign elements = rootElement.elements() />

            <#assign group = groupLocalService.getGroup(entry.groupId) />

                <div class="col-xs-12 col-sm-4">
                
                    <#assign user = userLocalService.getUser(entry.userId) />
                
                    <h4>${entry.getTitle(locale)}</h4>
                    <h4>${user.fullName} <a href="/web${group.friendlyURL}">${group.getName(locale)}</a></h4>

                    <#list elements as element>
                        <#if "shortDescription" == element.attributeValue("name")>
                            <#assign shortDescription = element.element("dynamic-content").getText() />
                        </#if>
                    </#list>
                    ${shortDescription}
                    

                    <#-- Two ways of getting article categories.
                         Method number one: -->
                    <#assign articleCatNames = catLocalService.getCategoryNames("com.liferay.journal.model.JournalArticle", getterUtil.getLong(journalArticle.getResourcePrimKey())) />
                    <div>
                        <#list articleCatNames as catName>
                            ${catName}
                        </#list>
                    </div>

                    <#-- Method number two: -->
                    <div>
                        <@liferay_ui["asset-categories-summary"]
                            className=entry.getClassName()
                            classPK=entry.getClassPK()
                            portletURL=renderResponse.createRenderURL()
                        />
                    </div>
                    
                    <#assign viewURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, entry) />
                    <#assign viewInContext = renderer.getURLViewInContext(renderRequest, renderResponse, viewURL) />
                    <p><a href="${viewURL}">ViewURL</a></p>
                    <p><a href="${viewInContext}">viewInContext</a></p>
                </div>
            </link>
        </#list>
    </div>
</#if>