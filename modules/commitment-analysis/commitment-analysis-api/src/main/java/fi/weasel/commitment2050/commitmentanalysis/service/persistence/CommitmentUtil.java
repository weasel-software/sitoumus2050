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

import fi.weasel.commitment2050.commitmentanalysis.model.Commitment;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the commitment service. This utility wraps {@link fi.weasel.commitment2050.commitmentanalysis.service.persistence.impl.CommitmentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CommitmentPersistence
 * @see fi.weasel.commitment2050.commitmentanalysis.service.persistence.impl.CommitmentPersistenceImpl
 * @generated
 */
@ProviderType
public class CommitmentUtil {
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
	public static void clearCache(Commitment commitment) {
		getPersistence().clearCache(commitment);
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
	public static List<Commitment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Commitment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Commitment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Commitment> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Commitment update(Commitment commitment) {
		return getPersistence().update(commitment);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Commitment update(Commitment commitment,
		ServiceContext serviceContext) {
		return getPersistence().update(commitment, serviceContext);
	}

	/**
	* Caches the commitment in the entity cache if it is enabled.
	*
	* @param commitment the commitment
	*/
	public static void cacheResult(Commitment commitment) {
		getPersistence().cacheResult(commitment);
	}

	/**
	* Caches the commitments in the entity cache if it is enabled.
	*
	* @param commitments the commitments
	*/
	public static void cacheResult(List<Commitment> commitments) {
		getPersistence().cacheResult(commitments);
	}

	/**
	* Creates a new commitment with the primary key. Does not add the commitment to the database.
	*
	* @param commitmentId the primary key for the new commitment
	* @return the new commitment
	*/
	public static Commitment create(java.lang.String commitmentId) {
		return getPersistence().create(commitmentId);
	}

	/**
	* Removes the commitment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commitmentId the primary key of the commitment
	* @return the commitment that was removed
	* @throws NoSuchCommitmentException if a commitment with the primary key could not be found
	*/
	public static Commitment remove(java.lang.String commitmentId)
		throws fi.weasel.commitment2050.commitmentanalysis.exception.NoSuchCommitmentException {
		return getPersistence().remove(commitmentId);
	}

	public static Commitment updateImpl(Commitment commitment) {
		return getPersistence().updateImpl(commitment);
	}

	/**
	* Returns the commitment with the primary key or throws a {@link NoSuchCommitmentException} if it could not be found.
	*
	* @param commitmentId the primary key of the commitment
	* @return the commitment
	* @throws NoSuchCommitmentException if a commitment with the primary key could not be found
	*/
	public static Commitment findByPrimaryKey(java.lang.String commitmentId)
		throws fi.weasel.commitment2050.commitmentanalysis.exception.NoSuchCommitmentException {
		return getPersistence().findByPrimaryKey(commitmentId);
	}

	/**
	* Returns the commitment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commitmentId the primary key of the commitment
	* @return the commitment, or <code>null</code> if a commitment with the primary key could not be found
	*/
	public static Commitment fetchByPrimaryKey(java.lang.String commitmentId) {
		return getPersistence().fetchByPrimaryKey(commitmentId);
	}

	public static java.util.Map<java.io.Serializable, Commitment> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the commitments.
	*
	* @return the commitments
	*/
	public static List<Commitment> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the commitments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommitmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commitments
	* @param end the upper bound of the range of commitments (not inclusive)
	* @return the range of commitments
	*/
	public static List<Commitment> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the commitments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommitmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commitments
	* @param end the upper bound of the range of commitments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commitments
	*/
	public static List<Commitment> findAll(int start, int end,
		OrderByComparator<Commitment> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the commitments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommitmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commitments
	* @param end the upper bound of the range of commitments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commitments
	*/
	public static List<Commitment> findAll(int start, int end,
		OrderByComparator<Commitment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the commitments from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of commitments.
	*
	* @return the number of commitments
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CommitmentPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommitmentPersistence, CommitmentPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CommitmentPersistence.class);
}