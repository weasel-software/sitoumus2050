<#if !entries?has_content>
    <#if !themeDisplay.isSignedIn()>
    ${renderRequest.setAttribute("PORTLET_CONFIGURATOR_VISIBILITY", true)}
    </#if>

<div class="alert alert-info">
    <@liferay_ui["message"]
			key="there-are-no-results"
		/>
</div>
</#if>

<#if entries?has_content>
    <#assign journalArticleLocalService = serviceLocator.findService('com.liferay.journal.service.JournalArticleLocalService')/>
    <#assign list = []/>

<#assign instanceId = themeDisplay.getPortletDisplay().getInstanceId() />
<div id="carousel${instanceId}" class="carousel col4 fade" data-ride="carousel">
    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item row active">
            <#list entries as entry>
                <#assign article = journalArticleLocalService.getLatestArticle(entry.getClassPK())/>
                <#assign articleContent = journalArticleLocalService.getArticleContent(
                themeDisplay.getLayout().getGroupId(),
                article.getArticleId(),
                "view",
                null,
                themeDisplay.getLocale().toString(),
                portletRequestModel,
                themeDisplay)
                />
                <#if entry?index != 0 && entry?index % 4 == 0>
                    </div>
                    <div class="item row">
                </#if>
                    <#assign assetRenderer = entry.getAssetRenderer() />
                    <#assign entryTitle = htmlUtil.escape(assetRenderer.getTitle(locale)) />
                    <div class="col-md-3">
                        <#if articleContent?has_content>
                            ${articleContent}
                        </#if>
                        <div class="asset-abstract">
                            <@getEditIcon />
                        </div>
                    </div>
            </#list>
        </div>
    </div>
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <#list entries as entry>
            <#if entry?index % 4 == 0>
                <li data-target="#carousel${instanceId}" data-slide-to="${entry?index / 4}" <#if entry?index == 0>class="active"</#if>></li>
            </#if>
        </#list>
    </ol>
</div>
</#if>



<#macro getEditIcon>
    <#if assetRenderer.hasEditPermission(themeDisplay.getPermissionChecker())>
        <#assign redirectURL = renderResponse.createRenderURL() />

    ${redirectURL.setParameter("mvcPath", "/add_asset_redirect.jsp")}
    ${redirectURL.setWindowState("pop_up")}

        <#assign editPortletURL = assetRenderer.getURLEdit(renderRequest, renderResponse, windowStateFactory.getWindowState("pop_up"), redirectURL)!"" />

        <#if validator.isNotNull(editPortletURL)>
            <#assign title = languageUtil.format(locale, "edit-x", entryTitle, false) />

            <@liferay_ui["icon"]
            cssClass="icon-monospaced visible-interaction"
            icon="pencil"
            markupView="lexicon"
            message=title
            url="javascript:Liferay.Util.openWindow({id:'" + renderResponse.getNamespace() + "editAsset', title: '" + title + "', uri:'" + htmlUtil.escapeURL(editPortletURL.toString()) + "'});"
            />
        </#if>
    </#if>
</#macro>

<#macro getSocialBookmarks>
    <#if getterUtil.getBoolean(enableSocialBookmarks)>
        <@liferay_ui["social-bookmarks"]
        displayStyle="${socialBookmarksDisplayStyle}"
        target="_blank"
        title=entry.getTitle(locale)
        url=viewURL
        />
    </#if>
</#macro>
