package com.soikea.commitment2050.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import com.soikea.commitment2050.model.*;

public interface OrganizationSpecialService {


    /**
     * Get organization details.
     *
     * @param organization Organization name
     * @return Details of an organization
     * @throws PortalException @see {@link PortalException}
     * @throws SystemException @see {@link SystemException}
     */
    OrganizationDetails getOrganizationDetailsByName(String organization) throws PortalException;
    OrganizationDetails getOrganizationDetailsById(String organizationId) throws PortalException;
}
