package com.soikea.commitment2050.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Lassi
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.commitment",
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=Report-listing-portlet Portlet",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/ReportListing.jsp",
		"javax.portlet.name=ReportListingPortlet", "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class ReportListingPortlet extends MVCPortlet {
}
