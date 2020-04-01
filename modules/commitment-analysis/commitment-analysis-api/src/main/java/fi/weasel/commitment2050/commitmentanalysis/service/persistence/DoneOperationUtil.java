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

import fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the done operation service. This utility wraps {@link fi.weasel.commitment2050.commitmentanalysis.service.persistence.impl.DoneOperationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DoneOperationPersistence
 * @see fi.weasel.commitment2050.commitmentanalysis.service.persistence.impl.DoneOperationPersistenceImpl
 * @generated
 */
@ProviderType
public class DoneOperationUtil {
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
	public static void clearCache(DoneOperation doneOperation) {
		getPersistence().clearCache(doneOperation);
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
	public static List<DoneOperation> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DoneOperation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DoneOperation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DoneOperation> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DoneOperation update(DoneOperation doneOperation) {
		return getPersistence().update(doneOperation);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DoneOperation update(DoneOperation doneOperation,
		ServiceContext serviceContext) {
		return getPersistence().update(doneOperation, serviceContext);
	}

	/**
	* Caches the done operation in the entity cache if it is enabled.
	*
	* @param doneOperation the done operation
	*/
	public static void cacheResult(DoneOperation doneOperation) {
		getPersistence().cacheResult(doneOperation);
	}

	/**
	* Caches the done operations in the entity cache if it is enabled.
	*
	* @param doneOperations the done operations
	*/
	public static void cacheResult(List<DoneOperation> doneOperations) {
		getPersistence().cacheResult(doneOperations);
	}

	/**
	* Creates a new done operation with the primary key. Does not add the done operation to the database.
	*
	* @param id the primary key for the new done operation
	* @return the new done operation
	*/
	public static DoneOperation create(java.lang.String id) {
		return getPersistence().create(id);
	}

	/**
	* Removes the done operation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the done operation
	* @return the done operation that was removed
	* @throws NoSuchDoneOperationException if a done operation with the primary key could not be found
	*/
	public static DoneOperation remove(java.lang.String id)
		throws fi.weasel.commitment2050.commitmentanalysis.exception.NoSuchDoneOperationException {
		return getPersistence().remove(id);
	}

	public static DoneOperation updateImpl(DoneOperation doneOperation) {
		return getPersistence().updateImpl(doneOperation);
	}

	/**
	* Returns the done operation with the primary key or throws a {@link NoSuchDoneOperationException} if it could not be found.
	*
	* @param id the primary key of the done operation
	* @return the done operation
	* @throws NoSuchDoneOperationException if a done operation with the primary key could not be found
	*/
	public static DoneOperation findByPrimaryKey(java.lang.String id)
		throws fi.weasel.commitment2050.commitmentanalysis.exception.NoSuchDoneOperationException {
		return getPersistence().findByPrimaryKey(id);
	}

	/**
	* Returns the done operation with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the done operation
	* @return the done operation, or <code>null</code> if a done operation with the primary key could not be found
	*/
	public static DoneOperation fetchByPrimaryKey(java.lang.String id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	public static java.util.Map<java.io.Serializable, DoneOperation> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the done operations.
	*
	* @return the done operations
	*/
	public static List<DoneOperation> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the done operations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DoneOperationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of done operations
	* @param end the upper bound of the range of done operations (not inclusive)
	* @return the range of done operations
	*/
	public static List<DoneOperation> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the done operations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DoneOperationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of done operations
	* @param end the upper bound of the range of done operations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of done operations
	*/
	public static List<DoneOperation> findAll(int start, int end,
		OrderByComparator<DoneOperation> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the done operations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DoneOperationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of done operations
	* @param end the upper bound of the range of done operations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of done operations
	*/
	public static List<DoneOperation> findAll(int start, int end,
		OrderByComparator<DoneOperation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the done operations from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of done operations.
	*
	* @return the number of done operations
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static DoneOperationPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DoneOperationPersistence, DoneOperationPersistence> _serviceTracker =
		ServiceTrackerFactory.open(DoneOperationPersistence.class);
}