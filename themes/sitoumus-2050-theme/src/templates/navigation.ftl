<#if has_navigation && is_setup_complete>
    <#assign preferences = freeMarkerPortletPreferences.getPreferences("portletSetupPortletDecoratorId", "barebone") />
    <div aria-expanded="false" class="collapse mt-4 mt-md-0 navbar-collapse" id="navigationCollapse">
        <@liferay.navigation_menu default_preferences="${preferences}" />
    </div>
</#if>

<#macro navigation_menu
default_preferences = ""
instance_id = ""
>
    <@liferay_portlet["runtime"]
    defaultPreferences=default_preferences
    instanceId=instance_id
    portletProviderAction=portletProviderAction.VIEW
    portletProviderClassName="com.liferay.portal.kernel.theme.NavItem"
    />
</#macro>