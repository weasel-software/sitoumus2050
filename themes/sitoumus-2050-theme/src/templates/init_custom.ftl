<#--
This file allows you to override and define new FreeMarker variables.
-->
<#assign
    journalArticleLocalService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService")
    userGroupRoleLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.UserGroupRoleLocalService")
    currentFriendlyUrl = themeDisplay.getURLCurrent()
/>

<#assign showcontrolmenu = false/>
<#-- Control menu, do show or not to show -->
<#if is_signed_in && user?has_content>
    <#assign roles = user.getRoles()/>
    <#list roles as role>
        <#if role.getName() == "Administrator">
            <#assign showcontrolmenu = true />
            <#break>
        </#if>
    </#list>
    <#assign siteRoles = userGroupRoleLocalService.getUserGroupRoles(themeDisplay.getUserId(), themeDisplay.getScopeGroupId())>
    <#list siteRoles as siteRole>
        <#if siteRole.getRole().getName() == "Site Administrator" || siteRole.getRole().getName() == "nutrition-approver" >
            <#assign showcontrolmenu = true />
            <#break>
        </#if>
    </#list>
</#if>

<#if !showcontrolmenu && css_class?contains("has-control-menu")>
    <#assign css_class = css_class?replace("has-control-menu", "")/>
</#if>