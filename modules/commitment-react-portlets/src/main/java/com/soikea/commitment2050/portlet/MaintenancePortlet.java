package com.soikea.commitment2050.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.soikea.commitment2050.constants.MakeCommitmentPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;

@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.commitment",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=commitment-maintenance Portlet",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/maintenance.jsp",
                "javax.portlet.name=commitment-maintenance",
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class MaintenancePortlet extends MVCPortlet {
}