package com.soikea.commitment2050.service;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryModel;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.*;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.apache.commons.lang3.StringUtils;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static com.soikea.commitment2050.service.Constants.*;

/**
 * User service provides methods to interact with user and organization information
 */
@Component(immediate = true, service = UserService.class)
public class UserServiceImpl implements UserService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public User addLiferayUser(String firstName, String lastName, String email, String organizationName) throws PortalException {
        log.trace("Adding user {} {} {} {}", firstName, lastName, email, organizationName);
        if (empty(email)) {
            throw new IllegalArgumentException("Email must be given");
        }

        if (empty(firstName)) {
            firstName = "Puuttuva tieto";
        }

        if (empty(lastName)) {
            lastName = "Puuttuva tieto";
        }

        // TODO REMOVE THIS
//        email = "jouni.latvatalo+" + System.currentTimeMillis() + "@gmail.com";
        boolean sendEmail = false;

        boolean autoScreenName = true;
        String screenName = null;

        boolean autoPassword = true;
        String password1 = null;
        String password2 = null;

        String jobTitle = null;
        String openId = null;
        long facebookId = 0;
        boolean male = false;
        long[] roleIds = new long[]{};
        long[] groupIds = new long[]{};
        long[] userGroupIds = new long[]{};
        int birthdayMonth = 1;
        int birthdayYear = 1970;
        int birthdayDay = 1;
        int prefixId = 0;
        int suffixId = 0;
        Locale locale = new Locale("fi", "FI");
        // Get user organization id by organization name
        long[] organizationIds = userOrganizationIds(organizationName);

        User liferayUser = UserLocalServiceUtil.addUser(
                ADMIN_USER_ID, COMPANY_ID, autoPassword, password1, password2, autoScreenName, screenName, email,
                facebookId, openId, locale, firstName, "", lastName, prefixId, suffixId, male,
                birthdayMonth, birthdayDay, birthdayYear, jobTitle, groupIds, organizationIds, roleIds, userGroupIds, sendEmail, null
        );

        addGroupRoleToUser(liferayUser);

        if ( organizationIds.length > 0 ) {
            ExpandoValueLocalServiceUtil.addValue(COMPANY_ID, User.class.getName(),
                    ExpandoTableConstants.DEFAULT_TABLE_NAME, USER_TYPE_EXPANDO_FIELD_NAME, liferayUser.getUserId(), new String[]{PERSON_TYPE_VALUE_ORGANIZATION});
        } else {
            ExpandoValueLocalServiceUtil.addValue(COMPANY_ID, User.class.getName(),
                    ExpandoTableConstants.DEFAULT_TABLE_NAME, USER_TYPE_EXPANDO_FIELD_NAME, liferayUser.getUserId(), new String[]{PERSON_TYPE_VALUE_PRIVATE});
        }

        return liferayUser;

    }

    private void addGroupRoleToUser(User liferayUser) {
        if (liferayUser.hasOrganization()) {
            try {
                List<Organization> userOrganizations = liferayUser.getOrganizations();
                if (userOrganizations.size() != 1) {
                    // currently we assume that user can only belong to one organization
                    return;
                }
                Role orgAdminRole = RoleLocalServiceUtil.getRole(COMPANY_ID, RoleConstants.ORGANIZATION_ADMINISTRATOR);

                UserGroupRoleLocalServiceUtil.addUserGroupRoles(liferayUser.getUserId(), userOrganizations.iterator().next().getGroupId(), new long[]{orgAdminRole.getRoleId()});
            } catch (PortalException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private long[] userOrganizationIds(String organizationName) {
        if (empty(organizationName)) {
            return new long[]{};
        } else {
            Organization organization = OrganizationLocalServiceUtil.fetchOrganization(COMPANY_ID, organizationName);
            if (organization == null) {
                throw new RuntimeException("User had organization with the name " + organizationName + " but no such organization was found from liferay");
            }

            return new long[]{organization.getOrganizationId()};
        }

    }

    @Override
    public Organization createOrganization(String organizationName, String organizationType, String industry) throws PortalException {
        log.info("Create organization with name {} and type {}", organizationName, organizationType);
        if (empty(organizationName)) {
            throw new IllegalArgumentException("Organization name can not be null or empty");
        }

        if (OrganizationLocalServiceUtil.fetchOrganization(COMPANY_ID, organizationName) == null) {
            Organization organization = OrganizationLocalServiceUtil.addOrganization(ADMIN_USER_ID, PARENT_ORGANIZATION_ID, organizationName, false);

//            LayoutSetLocalServiceUtil.updateLayoutSetPrototypeLinkEnabled(organization.getGroupId(), false, true, ORGANIZATION_LAYOUT_SET_PROTOTYPE_UUID);

            //Update organization type
            ExpandoValueLocalServiceUtil.addValue(COMPANY_ID, Organization.class.getName(),
                    ExpandoTableConstants.DEFAULT_TABLE_NAME, ORGANIZATION_TYPE_EXPANDO_FIELD_NAME, organization.getOrganizationId(), new String[]{organizationType});

            if (StringUtils.isNotBlank(organizationType) ) {
                log.debug("Adding OrganizationType" + organizationType + " to organization: " +  organization.getName());
                Set<String> categoryNames = new HashSet<String>();
                categoryNames.add(organizationType);
                addVocabularyCategoriesToOrganizationByCategoryName(Constants.USER_TYPE_VOCABULARY_ID, organization, categoryNames);
                if ( StringUtils.isNotBlank(industry)) {
                    Set<String> industrySet = new HashSet<String>();
                    industrySet.add(industry);
                    addVocabularyCategoriesToOrganizationByCategoryName(Constants.USER_TYPE_VOCABULARY_ID, organization, industrySet);
                }
            }
            return organization;
        } else {
            throw new IllegalArgumentException("Organization with name " + organizationName + " already exists");
        }
    }

    private boolean empty(final String s) {
        return s == null || s.trim().isEmpty();
    }

    @Override
    public void addVocabularyCategoriesToOrganizationByCategoryName(final long vocabularyId, Organization organization, final Set<String> categoryNames) throws PortalException {
        Objects.nonNull(organization);
        AssetCategoryLocalServiceUtil.getCategoryIds(Organization.class.getName(), organization.getPrimaryKey());

        List<AssetCategory> vocabularyCategories = AssetCategoryLocalServiceUtil.getVocabularyCategories(vocabularyId, 0, 200, null);
        long[] categoryIdsToBeAdded = vocabularyCategories.stream()
                .filter(c -> categoryNames.contains(c.getTitle("fi_FI")))
                .map(AssetCategoryModel::getCategoryId)
                .mapToLong(Long::longValue)
                .toArray();

        if(categoryNames != null && categoryNames.size() != categoryIdsToBeAdded.length) {
            log.warn("Tried to add {} categories by name, but only found {} categories that matched the given names", categoryNames.size(), categoryIdsToBeAdded.length);
        }

        long[] currentCategoryIds = AssetCategoryLocalServiceUtil.getCategoryIds(Organization.class.getName(), organization.getPrimaryKey());
        long[] mergedCategoryIds = Stream.of(currentCategoryIds, categoryIdsToBeAdded).flatMapToLong(LongStream::of).toArray();

        OrganizationLocalServiceUtil.updateAsset(ADMIN_USER_ID, organization, mergedCategoryIds, null);
    }

    @Override
    public void addCategoriesToOrganization(long userId, long[] categoryIds, Organization organization) throws PortalException {
        OrganizationLocalServiceUtil.updateAsset(userId, organization, categoryIds, null);
    }

    @Override
    public boolean isAdmin(User user) {
        if (user != null ) {
            List<Role> userRoles = user.getRoles();
            for(Role r : userRoles) {
                if ("Administrator".equalsIgnoreCase(r.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getUserPortraitUrl(long userId) throws PortalException {
        User u = UserLocalServiceUtil.getUser(userId);
        String url = null;
        if (u.getPortraitId() > 0 ) {
            url = UserConstants.getPortraitURL(PortalUtil.getPathImage(), u.isMale(), u.getPortraitId(), u.getUserUuid());
            if (log.isDebugEnabled()) {
                log.debug("User portrait url:");
            }
        }
        return url;
    }

    @Override
    public User getUser(long userId) throws PortalException {
        return UserLocalServiceUtil.getUser(userId);
    }

    @Override
    public Organization getOrganization(long organizationId) throws PortalException {
        return OrganizationLocalServiceUtil.getOrganization(organizationId);
    }

    @Override
    public void addUserToOrganization(long userId, long organizationId) throws PortalException {
        User u = UserLocalServiceUtil.getUser(userId);
        UserLocalServiceUtil.addOrganizationUser(organizationId, u);
    }

    @Override
    public String getOrganizationLogoUrl(long organizationId) throws PortalException {
        Organization org = getOrganization(organizationId);
        // Is there better way to create path to organization image?
        // now it seems to be something like: "/image/organization_logo?img_id=94003&t=1531772927065"
        if (org.getLogoId() != 0 ) {
            return "/image/organization_logo?img_id=" + org.getLogoId();
        } else {
            return null;
        }
    }

    @Override
    public Organization getOrganizationByName(String organizationName) {
        try {
            return OrganizationLocalServiceUtil.getOrganization(Constants.COMPANY_ID, organizationName);
        } catch (PortalException e) {
            log.error("Organization not found", e);
        }
        return null;
    }
}
