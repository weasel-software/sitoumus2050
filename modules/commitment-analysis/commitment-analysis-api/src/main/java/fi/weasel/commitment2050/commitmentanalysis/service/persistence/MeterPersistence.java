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

import fi.weasel.commitment2050.commitmentanalysis.exception.NoSuchMeterException;
import fi.weasel.commitment2050.commitmentanalysis.model.Meter;

/**
 * The persistence interface for the meter service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see fi.weasel.commitment2050.commitmentanalysis.service.persistence.impl.MeterPersistenceImpl
 * @see MeterUtil
 * @generated
 */
@ProviderType
public interface MeterPersistence extends BasePersistence<Meter> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MeterUtil} to access the meter persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the meter in the entity cache if it is enabled.
	*
	* @param meter the meter
	*/
	public void cacheResult(Meter meter);

	/**
	* Caches the meters in the entity cache if it is enabled.
	*
	* @param meters the meters
	*/
	public void cacheResult(java.util.List<Meter> meters);

	/**
	* Creates a new meter with the primary key. Does not add the meter to the database.
	*
	* @param id the primary key for the new meter
	* @return the new meter
	*/
	public Meter create(java.lang.String id);

	/**
	* Removes the meter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the meter
	* @return the meter that was removed
	* @throws NoSuchMeterException if a meter with the primary key could not be found
	*/
	public Meter remove(java.lang.String id) throws NoSuchMeterException;

	public Meter updateImpl(Meter meter);

	/**
	* Returns the meter with the primary key or throws a {@link NoSuchMeterException} if it could not be found.
	*
	* @param id the primary key of the meter
	* @return the meter
	* @throws NoSuchMeterException if a meter with the primary key could not be found
	*/
	public Meter findByPrimaryKey(java.lang.String id)
		throws NoSuchMeterException;

	/**
	* Returns the meter with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the meter
	* @return the meter, or <code>null</code> if a meter with the primary key could not be found
	*/
	public Meter fetchByPrimaryKey(java.lang.String id);

	@Override
	public java.util.Map<java.io.Serializable, Meter> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the meters.
	*
	* @return the meters
	*/
	public java.util.List<Meter> findAll();

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
	public java.util.List<Meter> findAll(int start, int end);

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
	public java.util.List<Meter> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Meter> orderByComparator);

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
	public java.util.List<Meter> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Meter> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the meters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of meters.
	*
	* @return the number of meters
	*/
	public int countAll();
}