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
 * Provides the local service utility for DoneOperation. This utility wraps
 * {@link fi.weasel.commitment2050.commitmentanalysis.service.impl.DoneOperationLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see DoneOperationLocalService
 * @see fi.weasel.commitment2050.commitmentanalysis.service.base.DoneOperationLocalServiceBaseImpl
 * @see fi.weasel.commitment2050.commitmentanalysis.service.impl.DoneOperationLocalServiceImpl
 * @generated
 */
@ProviderType
public class DoneOperationLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link fi.weasel.commitment2050.commitmentanalysis.service.impl.DoneOperationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the done operation to the database. Also notifies the appropriate model listeners.
	*
	* @param doneOperation the done operation
	* @return the done operation that was added
	*/
	public static fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation addDoneOperation(
		fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation doneOperation) {
		return getService().addDoneOperation(doneOperation);
	}

	/**
	* Creates a new done operation with the primary key. Does not add the done operation to the database.
	*
	* @param id the primary key for the new done operation
	* @return the new done operation
	*/
	public static fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation createDoneOperation(
		java.lang.String id) {
		return getService().createDoneOperation(id);
	}

	/**
	* Deletes the done operation from the database. Also notifies the appropriate model listeners.
	*
	* @param doneOperation the done operation
	* @return the done operation that was removed
	*/
	public static fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation deleteDoneOperation(
		fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation doneOperation) {
		return getService().deleteDoneOperation(doneOperation);
	}

	/**
	* Deletes the done operation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the done operation
	* @return the done operation that was removed
	* @throws PortalException if a done operation with the primary key could not be found
	*/
	public static fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation deleteDoneOperation(
		java.lang.String id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDoneOperation(id);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation doneOperationFromJSONObject(
		java.lang.String commitmentID, org.json.JSONObject o) {
		return getService().doneOperationFromJSONObject(commitmentID, o);
	}

	public static java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation> doneOperationsFromJSONArray(
		java.lang.String commitmentID, org.json.JSONArray a) {
		return getService().doneOperationsFromJSONArray(commitmentID, a);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.DoneOperationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.DoneOperationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation fetchDoneOperation(
		java.lang.String id) {
		return getService().fetchDoneOperation(id);
	}

	/**
	* Returns the done operation with the primary key.
	*
	* @param id the primary key of the done operation
	* @return the done operation
	* @throws PortalException if a done operation with the primary key could not be found
	*/
	public static fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation getDoneOperation(
		java.lang.String id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDoneOperation(id);
	}

	/**
	* Returns a range of all the done operations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.DoneOperationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of done operations
	* @param end the upper bound of the range of done operations (not inclusive)
	* @return the range of done operations
	*/
	public static java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation> getDoneOperations(
		int start, int end) {
		return getService().getDoneOperations(start, end);
	}

	/**
	* Returns the number of done operations.
	*
	* @return the number of done operations
	*/
	public static int getDoneOperationsCount() {
		return getService().getDoneOperationsCount();
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

	public static void truncateTable()
		throws com.liferay.portal.kernel.dao.orm.ORMException {
		getService().truncateTable();
	}

	/**
	* Updates the done operation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param doneOperation the done operation
	* @return the done operation that was updated
	*/
	public static fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation updateDoneOperation(
		fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation doneOperation) {
		return getService().updateDoneOperation(doneOperation);
	}

	public static DoneOperationLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DoneOperationLocalService, DoneOperationLocalService> _serviceTracker =
		ServiceTrackerFactory.open(DoneOperationLocalService.class);
}