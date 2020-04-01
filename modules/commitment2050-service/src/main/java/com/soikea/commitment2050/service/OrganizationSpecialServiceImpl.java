package com.soikea.commitment2050.service;

import com.liferay.portal.kernel.model.Organization;
import com.liferay.asset.kernel.model.*;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import com.soikea.commitment2050.model.*;
import com.soikea.commitment2050.service.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


@Component(immediate = true, service = OrganizationSpecialService.class)
public class OrganizationSpecialServiceImpl implements OrganizationSpecialService {

    @Reference
    UserService userService;

    private static Logger _logger = LoggerFactory.getLogger(OrganizationSpecialServiceImpl.class.getName());

    @Override
    public OrganizationDetails getOrganizationDetailsByName(String organization) throws PortalException {
        OrganizationDetails finalResult = new OrganizationDetails();
        String imgUrl = "";

        Organization tempResult = OrganizationLocalServiceUtil.getOrganization( Constants.COMPANY_ID, organization);

        try {

            long organizationId = tempResult.getOrganizationId();
            finalResult.setOrganizationId(String.valueOf(organizationId));

//        List<Website> websiteList = WebsiteLocalServiceUtil.getWebsites(Constants.COMPANY_ID, "com.liferay.portal.kernel.model.Organization",organizationId);

            List<AssetCategory> categories = AssetCategoryLocalServiceUtil.getCategories("com.liferay.portal.kernel.model.Organization", organizationId);
            if( categories != null ){
                for( AssetCategory category : categories ) {
                    long vocabularyId = category.getVocabularyId();
                    String categoryId = String.valueOf(category.getCategoryId());
                    if( category.getParentCategoryId() == 0 && vocabularyId != Constants.ORGANIZATION_SIZE_ID ){
                        finalResult.setOrganizationTypeId( categoryId );
                    } else if( vocabularyId == Constants.ORGANIZATION_SIZE_ID ){
                        finalResult.setOrganizationSizeId( categoryId);
                    } else {
                        finalResult.setOrganizationSubTypeId( categoryId);
                    }
                }
            }
            imgUrl = userService.getOrganizationLogoUrl(organizationId);
            finalResult.setLogo(imgUrl);
        } catch (SystemException e) {
            throw new SystemException(e);
        }catch (PortalException e) {
            throw new PortalException(e);
        }

        return finalResult;
    }

    @Override
    public OrganizationDetails getOrganizationDetailsById(String organizationId) throws PortalException {
        OrganizationDetails finalResult = new OrganizationDetails();
        String imgUrl = "";
        Organization tempResult = OrganizationLocalServiceUtil.getOrganization(Long.parseLong(organizationId));

        try {
            finalResult.setName(tempResult.getName());
            finalResult.setOrganizationId(organizationId);

//        List<Website> websiteList = WebsiteLocalServiceUtil.getWebsites(Constants.COMPANY_ID, "com.liferay.portal.kernel.model.Organization",organizationId);

            List<AssetCategory> categories = AssetCategoryLocalServiceUtil.getCategories("com.liferay.portal.kernel.model.Organization", Long.parseLong(organizationId));
            if( categories != null ){
                for( AssetCategory category : categories ) {
                    long vocabularyId = category.getVocabularyId();
                    String categoryId = String.valueOf(category.getCategoryId());
                    if( category.getParentCategoryId() == 0 && vocabularyId != Constants.ORGANIZATION_SIZE_ID ){
                        finalResult.setOrganizationTypeId( categoryId );
                    } else if( vocabularyId == Constants.ORGANIZATION_SIZE_ID ){
                        finalResult.setOrganizationSizeId( categoryId);
                    } else {
                        finalResult.setOrganizationSubTypeId( categoryId);
                    }
                }
            }
            imgUrl = userService.getOrganizationLogoUrl(Long.parseLong(organizationId));
            finalResult.setLogo(imgUrl);
        } catch (SystemException e) {
            throw new SystemException(e);
        }catch (PortalException e) {
            throw new PortalException(e);
        }

        return finalResult;
    }
}
