package com.soikea.commitment2050.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;

import java.util.Set;

public interface UserService {
    /**
     * Add/create a new Liferay user.
     *
     * @param firstName First name
     * @param lastName  Last name
     * @param email email
     * @param organizationName organization name. Can be null.
     * @return  Added user
     * @throws PortalException @see {@link PortalException}
     */
    User addLiferayUser(String firstName, String lastName, String email, String organizationName) throws PortalException;

    /**
     * Create organization
     * @param organizationName Organization name. Note! Name is unique in  Liferay.
     * @param organizationType  organization type category
     * @param industry Industry type
     * @return Organization
     * @throws PortalException @see {@link PortalException}
     */
    Organization createOrganization(String organizationName, String organizationType, String industry) throws PortalException;

    /**
     * Add categories to organization by name
     * @param vocabularyId vocabulary id
     * @param organization organization id
     * @param categoryNames list of category names
     * @throws PortalException @see {@link PortalException}
     */
    void addVocabularyCategoriesToOrganizationByCategoryName(final long vocabularyId, Organization organization, final Set<String> categoryNames) throws PortalException;

    /**
     * Add categorie to organization
     * @param userId    User id
     * @param categoryIds List of category ids
     * @param organization organization
     * @throws PortalException @see {@link PortalException}
     */
    void addCategoriesToOrganization(long userId, long[] categoryIds, Organization organization) throws PortalException;

    /**
     * Check if user in admin role
     * @param user user
     * @return boolen true if is in admin role otherwise false
     */
    boolean isAdmin(User user);

    /**
     * Get relative url of portrait image. If por
     * @param userId userid
     * @return relative url of portrait image
     * @throws PortalException @see {@link PortalException}
     */
    String  getUserPortraitUrl(long userId) throws PortalException;

    /**
     * Get user by user id
     * @param userId user id
     * @return User @see {@link User}
     * @throws PortalException @see {@link PortalException}
     */
    User getUser(long userId) throws PortalException;

    /**
     * Get organization by id
     * @param organizationId organization id
     * @return Organization @see {@link Organization}
     * @throws PortalException @see {@link PortalException}
     */
    Organization getOrganization(long organizationId) throws PortalException;

    /**
     * Add user to organization
     * @param userId user id
     * @param organizationId organizatino id
     * @throws PortalException @see {@link PortalException}
     */
    void addUserToOrganization(long userId, long organizationId) throws PortalException;

    /**
     * Get organization logo url by organization id
     * @param organizationId organization id
     * @return relative url to organization logo if logo exists, otherwise null
     * @throws PortalException @see {@link PortalException}
     */
    String getOrganizationLogoUrl(long organizationId) throws PortalException;

    /**
     * Get organization by name (name is unique for organizations in Liferay).
     * Returns null if organization is not found.
     * @param organizationName
     * @return organization if found, otherwise null.
     */
    Organization getOrganizationByName(String organizationName);
}
