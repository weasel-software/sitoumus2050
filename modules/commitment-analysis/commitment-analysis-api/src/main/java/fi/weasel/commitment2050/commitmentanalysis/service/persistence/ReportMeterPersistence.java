/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package fi.weasel.commitment2050.commitmentanalysis.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import fi.weasel.commitment2050.commitmentanalysis.exception.NoSuchReportMeterException;
import fi.weasel.commitment2050.commitmentanalysis.model.ReportMeter;

/**
 * The persistence interface for the report meter service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see fi.weasel.commitment2050.commitmentanalysis.service.persistence.impl.ReportMeterPersistenceImpl
 * @see ReportMeterUtil
 * @generated
 */
@ProviderType
public interface ReportMeterPersistence extends BasePersistence<ReportMeter> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ReportMeterUtil} to access the report meter persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the report meter in the entity cache if it is enabled.
	*
	* @param reportMeter the report meter
	*/
	public void cacheResult(ReportMeter reportMeter);

	/**
	* Caches the report meters in the entity cache if it is enabled.
	*
	* @param reportMeters the report meters
	*/
	public void cacheResult(java.util.List<ReportMeter> reportMeters);

	/**
	* Creates a new report meter with the primary key. Does not add the report meter to the database.
	*
	* @param id the primary key for the new report meter
	* @return the new report meter
	*/
	public ReportMeter create(java.lang.String id);

	/**
	* Removes the report meter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the report meter
	* @return the report meter that was removed
	* @throws NoSuchReportMeterException if a report meter with the primary key could not be found
	*/
	public ReportMeter remove(java.lang.String id)
		throws NoSuchReportMeterException;

	public ReportMeter updateImpl(ReportMeter reportMeter);

	/**
	* Returns the report meter with the primary key or throws a {@link NoSuchReportMeterException} if it could not be found.
	*
	* @param id the primary key of the report meter
	* @return the report meter
	* @throws NoSuchReportMeterException if a report meter with the primary key could not be found
	*/
	public ReportMeter findByPrimaryKey(java.lang.String id)
		throws NoSuchReportMeterException;

	/**
	* Returns the report meter with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the report meter
	* @return the report meter, or <code>null</code> if a report meter with the primary key could not be found
	*/
	public ReportMeter fetchByPrimaryKey(java.lang.String id);

	@Override
	public java.util.Map<java.io.Serializable, ReportMeter> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the report meters.
	*
	* @return the report meters
	*/
	public java.util.List<ReportMeter> findAll();

	/**
	* Returns a range of all the report meters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportMeterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of report meters
	* @param end the upper bound of the range of report meters (not inclusive)
	* @return the range of report meters
	*/
	public java.util.List<ReportMeter> findAll(int start, int end);

	/**
	* Returns an ordered range of all the report meters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportMeterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of report meters
	* @param end the upper bound of the range of report meters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of report meters
	*/
	public java.util.List<ReportMeter> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReportMeter> orderByComparator);

	/**
	* Returns an ordered range of all the report meters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportMeterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of report meters
	* @param end the upper bound of the range of report meters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of report meters
	*/
	public java.util.List<ReportMeter> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReportMeter> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the report meters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of report meters.
	*
	* @return the number of report meters
	*/
	public int countAll();
}