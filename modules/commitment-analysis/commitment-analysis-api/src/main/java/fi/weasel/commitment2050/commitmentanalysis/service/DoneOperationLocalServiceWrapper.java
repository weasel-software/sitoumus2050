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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DoneOperationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DoneOperationLocalService
 * @generated
 */
@ProviderType
public class DoneOperationLocalServiceWrapper
	implements DoneOperationLocalService,
		ServiceWrapper<DoneOperationLocalService> {
	public DoneOperationLocalServiceWrapper(
		DoneOperationLocalService doneOperationLocalService) {
		_doneOperationLocalService = doneOperationLocalService;
	}

	/**
	* Adds the done operation to the database. Also notifies the appropriate model listeners.
	*
	* @param doneOperation the done operation
	* @return the done operation that was added
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation addDoneOperation(
		fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation doneOperation) {
		return _doneOperationLocalService.addDoneOperation(doneOperation);
	}

	/**
	* Creates a new done operation with the primary key. Does not add the done operation to the database.
	*
	* @param id the primary key for the new done operation
	* @return the new done operation
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation createDoneOperation(
		java.lang.String id) {
		return _doneOperationLocalService.createDoneOperation(id);
	}

	/**
	* Deletes the done operation from the database. Also notifies the appropriate model listeners.
	*
	* @param doneOperation the done operation
	* @return the done operation that was removed
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation deleteDoneOperation(
		fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation doneOperation) {
		return _doneOperationLocalService.deleteDoneOperation(doneOperation);
	}

	/**
	* Deletes the done operation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the done operation
	* @return the done operation that was removed
	* @throws PortalException if a done operation with the primary key could not be found
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation deleteDoneOperation(
		java.lang.String id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _doneOperationLocalService.deleteDoneOperation(id);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _doneOperationLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation doneOperationFromJSONObject(
		java.lang.String commitmentID, org.json.JSONObject o) {
		return _doneOperationLocalService.doneOperationFromJSONObject(commitmentID,
			o);
	}

	@Override
	public java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation> doneOperationsFromJSONArray(
		java.lang.String commitmentID, org.json.JSONArray a) {
		return _doneOperationLocalService.doneOperationsFromJSONArray(commitmentID,
			a);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _doneOperationLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _doneOperationLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _doneOperationLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _doneOperationLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _doneOperationLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _doneOperationLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation fetchDoneOperation(
		java.lang.String id) {
		return _doneOperationLocalService.fetchDoneOperation(id);
	}

	/**
	* Returns the done operation with the primary key.
	*
	* @param id the primary key of the done operation
	* @return the done operation
	* @throws PortalException if a done operation with the primary key could not be found
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation getDoneOperation(
		java.lang.String id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _doneOperationLocalService.getDoneOperation(id);
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
	@Override
	public java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation> getDoneOperations(
		int start, int end) {
		return _doneOperationLocalService.getDoneOperations(start, end);
	}

	/**
	* Returns the number of done operations.
	*
	* @return the number of done operations
	*/
	@Override
	public int getDoneOperationsCount() {
		return _doneOperationLocalService.getDoneOperationsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _doneOperationLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _doneOperationLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public void truncateTable()
		throws com.liferay.portal.kernel.dao.orm.ORMException {
		_doneOperationLocalService.truncateTable();
	}

	/**
	* Updates the done operation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param doneOperation the done operation
	* @return the done operation that was updated
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation updateDoneOperation(
		fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation doneOperation) {
		return _doneOperationLocalService.updateDoneOperation(doneOperation);
	}

	@Override
	public DoneOperationLocalService getWrappedService() {
		return _doneOperationLocalService;
	}

	@Override
	public void setWrappedService(
		DoneOperationLocalService doneOperationLocalService) {
		_doneOperationLocalService = doneOperationLocalService;
	}

	private DoneOperationLocalService _doneOperationLocalService;
}