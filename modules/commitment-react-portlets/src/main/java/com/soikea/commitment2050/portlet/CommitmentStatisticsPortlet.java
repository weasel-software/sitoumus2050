package com.soikea.commitment2050.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;

/**
 * @author Rami
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.commitment",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=commitment-statistics-portlet Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/CommitmentStatistics.jsp",
		"javax.portlet.name=CommitmentStatisticsPortlet",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CommitmentStatisticsPortlet extends MVCPortlet {
}
