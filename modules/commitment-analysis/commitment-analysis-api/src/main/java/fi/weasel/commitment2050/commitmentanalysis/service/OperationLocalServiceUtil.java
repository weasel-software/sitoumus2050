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

package fi.weasel.commitment2050.commitmentanalysis.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Operation. This utility wraps
 * {@link fi.weasel.commitment2050.commitmentanalysis.service.impl.OperationLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see OperationLocalService
 * @see fi.weasel.commitment2050.commitmentanalysis.service.base.OperationLocalServiceBaseImpl
 * @see fi.weasel.commitment2050.commitmentanalysis.service.impl.OperationLocalServiceImpl
 * @generated
 */
@ProviderType
public class OperationLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link fi.weasel.commitment2050.commitmentanalysis.service.impl.OperationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the operation to the database. Also notifies the appropriate model listeners.
	*
	* @param operation the operation
	* @return the operation that was added
	*/
	public static fi.weasel.commitment2050.commitmentanalysis.model.Operation addOperation(
		fi.weasel.commitment2050.commitmentanalysis.model.Operation operation) {
		return getService().addOperation(operation);
	}

	/**
	* Creates a new operation with the primary key. Does not add the operation to the database.
	*
	* @param id the primary key for the new operation
	* @return the new operation
	*/
	public static fi.weasel.commitment2050.commitmentanalysis.model.Operation createOperation(
		java.lang.String id) {
		return getService().createOperation(id);
	}

	/**
	* Deletes the operation from the database. Also notifies the appropriate model listeners.
	*
	* @param operation the operation
	* @return the operation that was removed
	*/
	public static fi.weasel.commitment2050.commitmentanalysis.model.Operation deleteOperation(
		fi.weasel.commitment2050.commitmentanalysis.model.Operation operation) {
		return getService().deleteOperation(operation);
	}

	/**
	* Deletes the operation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the operation
	* @return the operation that was removed
	* @throws PortalException if a operation with the primary key could not be found
	*/
	public static fi.weasel.commitment2050.commitmentanalysis.model.Operation deleteOperation(
		java.lang.String id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteOperation(id);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.OperationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.OperationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static fi.weasel.commitment2050.commitmentanalysis.model.Operation fetchOperation(
		java.lang.String id) {
		return getService().fetchOperation(id);
	}

	/**
	* Returns the operation with the primary key.
	*
	* @param id the primary key of the operation
	* @return the operation
	* @throws PortalException if a operation with the primary key could not be found
	*/
	public static fi.weasel.commitment2050.commitmentanalysis.model.Operation getOperation(
		java.lang.String id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOperation(id);
	}

	/**
	* Returns a range of all the operations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.OperationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of operations
	* @param end the upper bound of the range of operations (not inclusive)
	* @return the range of operations
	*/
	public static java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.Operation> getOperations(
		int start, int end) {
		return getService().getOperations(start, end);
	}

	/**
	* Returns the number of operations.
	*
	* @return the number of operations
	*/
	public static int getOperationsCount() {
		return getService().getOperationsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static fi.weasel.commitment2050.commitmentanalysis.model.Operation operationFromJSONObject(
		java.lang.String commitmentID, org.json.JSONObject o) {
		return getService().operationFromJSONObject(commitmentID, o);
	}

	public static java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.Operation> operationsFromJSONArray(
		java.lang.String commitmentID, org.json.JSONArray a) {
		return getService().operationsFromJSONArray(commitmentID, a);
	}

	public static void truncateTable()
		throws com.liferay.portal.kernel.dao.orm.ORMException {
		getService().truncateTable();
	}

	/**
	* Updates the operation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param operation the operation
	* @return the operation that was updated
	*/
	public static fi.weasel.commitment2050.commitmentanalysis.model.Operation updateOperation(
		fi.weasel.commitment2050.commitmentanalysis.model.Operation operation) {
		return getService().updateOperation(operation);
	}

	public static OperationLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OperationLocalService, OperationLocalService> _serviceTracker =
		ServiceTrackerFactory.open(OperationLocalService.class);
}