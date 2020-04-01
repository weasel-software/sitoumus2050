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

import fi.weasel.commitment2050.commitmentanalysis.model.Meter;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the meter service. This utility wraps {@link fi.weasel.commitment2050.commitmentanalysis.service.persistence.impl.MeterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MeterPersistence
 * @see fi.weasel.commitment2050.commitmentanalysis.service.persistence.impl.MeterPersistenceImpl
 * @generated
 */
@ProviderType
public class MeterUtil {
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
	public static void clearCache(Meter meter) {
		getPersistence().clearCache(meter);
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
	public static List<Meter> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Meter> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Meter> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Meter> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Meter update(Meter meter) {
		return getPersistence().update(meter);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Meter update(Meter meter, ServiceContext serviceContext) {
		return getPersistence().update(meter, serviceContext);
	}

	/**
	* Caches the meter in the entity cache if it is enabled.
	*
	* @param meter the meter
	*/
	public static void cacheResult(Meter meter) {
		getPersistence().cacheResult(meter);
	}

	/**
	* Caches the meters in the entity cache if it is enabled.
	*
	* @param meters the meters
	*/
	public static void cacheResult(List<Meter> meters) {
		getPersistence().cacheResult(meters);
	}

	/**
	* Creates a new meter with the primary key. Does not add the meter to the database.
	*
	* @param id the primary key for the new meter
	* @return the new meter
	*/
	public static Meter create(java.lang.String id) {
		return getPersistence().create(id);
	}

	/**
	* Removes the meter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the meter
	* @return the meter that was removed
	* @throws NoSuchMeterException if a meter with the primary key could not be found
	*/
	public static Meter remove(java.lang.String id)
		throws fi.weasel.commitment2050.commitmentanalysis.exception.NoSuchMeterException {
		return getPersistence().remove(id);
	}

	public static Meter updateImpl(Meter meter) {
		return getPersistence().updateImpl(meter);
	}

	/**
	* Returns the meter with the primary key or throws a {@link NoSuchMeterException} if it could not be found.
	*
	* @param id the primary key of the meter
	* @return the meter
	* @throws NoSuchMeterException if a meter with the primary key could not be found
	*/
	public static Meter findByPrimaryKey(java.lang.String id)
		throws fi.weasel.commitment2050.commitmentanalysis.exception.NoSuchMeterException {
		return getPersistence().findByPrimaryKey(id);
	}

	/**
	* Returns the meter with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the meter
	* @return the meter, or <code>null</code> if a meter with the primary key could not be found
	*/
	public static Meter fetchByPrimaryKey(java.lang.String id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	public static java.util.Map<java.io.Serializable, Meter> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the meters.
	*
	* @return the meters
	*/
	public static List<Meter> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the meters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of meters
	* @param end the upper bound of the range of meters (not inclusive)
	* @return the range of meters
	*/
	public static List<Meter> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the meters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of meters
	* @param end the upper bound of the range of meters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of meters
	*/
	public static List<Meter> findAll(int start, int end,
		OrderByComparator<Meter> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the meters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of meters
	* @param end the upper bound of the range of meters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of meters
	*/
	public static List<Meter> findAll(int start, int end,
		OrderByComparator<Meter> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the meters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of meters.
	*
	* @return the number of meters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MeterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MeterPersistence, MeterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(MeterPersistence.class);
}