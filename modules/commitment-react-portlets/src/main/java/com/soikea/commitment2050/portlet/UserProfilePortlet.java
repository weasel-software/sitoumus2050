package com.soikea.commitment2050.portlet;

import com.soikea.commitment2050.constants.MakeCommitmentPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.commitment",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=user-profile-portlet Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/UserProfile.jsp",
		"javax.portlet.name=UserProfile",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class UserProfilePortlet extends MVCPortlet {
}
