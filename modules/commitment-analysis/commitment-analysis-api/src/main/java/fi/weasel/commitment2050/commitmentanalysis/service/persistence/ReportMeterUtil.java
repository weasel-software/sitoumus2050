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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import fi.weasel.commitment2050.commitmentanalysis.model.ReportMeter;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the report meter service. This utility wraps {@link fi.weasel.commitment2050.commitmentanalysis.service.persistence.impl.ReportMeterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReportMeterPersistence
 * @see fi.weasel.commitment2050.commitmentanalysis.service.persistence.impl.ReportMeterPersistenceImpl
 * @generated
 */
@ProviderType
public class ReportMeterUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ReportMeter reportMeter) {
		getPersistence().clearCache(reportMeter);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ReportMeter> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ReportMeter> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ReportMeter> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ReportMeter> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ReportMeter update(ReportMeter reportMeter) {
		return getPersistence().update(reportMeter);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ReportMeter update(ReportMeter reportMeter,
		ServiceContext serviceContext) {
		return getPersistence().update(reportMeter, serviceContext);
	}

	/**
	* Caches the report meter in the entity cache if it is enabled.
	*
	* @param reportMeter the report meter
	*/
	public static void cacheResult(ReportMeter reportMeter) {
		getPersistence().cacheResult(reportMeter);
	}

	/**
	* Caches the report meters in the entity cache if it is enabled.
	*
	* @param reportMeters the report meters
	*/
	public static void cacheResult(List<ReportMeter> reportMeters) {
		getPersistence().cacheResult(reportMeters);
	}

	/**
	* Creates a new report meter with the primary key. Does not add the report meter to the database.
	*
	* @param id the primary key for the new report meter
	* @return the new report meter
	*/
	public static ReportMeter create(java.lang.String id) {
		return getPersistence().create(id);
	}

	/**
	* Removes the report meter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the report meter
	* @return the report meter that was removed
	* @throws NoSuchReportMeterException if a report meter with the primary key could not be found
	*/
	public static ReportMeter remove(java.lang.String id)
		throws fi.weasel.commitment2050.commitmentanalysis.exception.NoSuchReportMeterException {
		return getPersistence().remove(id);
	}

	public static ReportMeter updateImpl(ReportMeter reportMeter) {
		return getPersistence().updateImpl(reportMeter);
	}

	/**
	* Returns the report meter with the primary key or throws a {@link NoSuchReportMeterException} if it could not be found.
	*
	* @param id the primary key of the report meter
	* @return the report meter
	* @throws NoSuchReportMeterException if a report meter with the primary key could not be found
	*/
	public static ReportMeter findByPrimaryKey(java.lang.String id)
		throws fi.weasel.commitment2050.commitmentanalysis.exception.NoSuchReportMeterException {
		return getPersistence().findByPrimaryKey(id);
	}

	/**
	* Returns the report meter with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the report meter
	* @return the report meter, or <code>null</code> if a report meter with the primary key could not be found
	*/
	public static ReportMeter fetchByPrimaryKey(java.lang.String id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	public static java.util.Map<java.io.Serializable, ReportMeter> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the report meters.
	*
	* @return the report meters
	*/
	public static List<ReportMeter> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<ReportMeter> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<ReportMeter> findAll(int start, int end,
		OrderByComparator<ReportMeter> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ReportMeter> findAll(int start, int end,
		OrderByComparator<ReportMeter> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the report meters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of report meters.
	*
	* @return the number of report meters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ReportMeterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ReportMeterPersistence, ReportMeterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ReportMeterPersistence.class);
}