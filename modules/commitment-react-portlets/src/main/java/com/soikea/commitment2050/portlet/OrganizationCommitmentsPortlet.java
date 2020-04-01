package com.soikea.commitment2050.portlet;

import com.soikea.commitment2050.constants.MakeCommitmentPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author ramiv
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.commitment",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=organization-commitments-portlet Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/OrganizationCommitments.jsp",
		"javax.portlet.name=OrganizationCommitments",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class OrganizationCommitmentsPortlet extends MVCPortlet {
}
