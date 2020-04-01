package com.soikea.commitment2050.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.soikea.commitment2050.model.Commitment;
import com.soikea.commitment2050.model.City;

import java.util.List;

public interface FormatCommitmentAddressesService {

    /**
     * Format commitment address fields to only contain City and nothing else.
     *
     * @param commitment commitment with address to be fixed
     * @param cityList list of Finnish cities
     * @throws PortalException @see {@link PortalException}
     *
     */
    Commitment formatCommitmentAddresses(Commitment commitment, List<City> cityList);

}
