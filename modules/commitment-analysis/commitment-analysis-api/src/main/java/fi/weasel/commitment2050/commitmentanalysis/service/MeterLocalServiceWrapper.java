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
 * Provides a wrapper for {@link MeterLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MeterLocalService
 * @generated
 */
@ProviderType
public class MeterLocalServiceWrapper implements MeterLocalService,
	ServiceWrapper<MeterLocalService> {
	public MeterLocalServiceWrapper(MeterLocalService meterLocalService) {
		_meterLocalService = meterLocalService;
	}

	/**
	* Adds the meter to the database. Also notifies the appropriate model listeners.
	*
	* @param meter the meter
	* @return the meter that was added
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.Meter addMeter(
		fi.weasel.commitment2050.commitmentanalysis.model.Meter meter) {
		return _meterLocalService.addMeter(meter);
	}

	/**
	* Creates a new meter with the primary key. Does not add the meter to the database.
	*
	* @param id the primary key for the new meter
	* @return the new meter
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.Meter createMeter(
		java.lang.String id) {
		return _meterLocalService.createMeter(id);
	}

	/**
	* Deletes the meter from the database. Also notifies the appropriate model listeners.
	*
	* @param meter the meter
	* @return the meter that was removed
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.Meter deleteMeter(
		fi.weasel.commitment2050.commitmentanalysis.model.Meter meter) {
		return _meterLocalService.deleteMeter(meter);
	}

	/**
	* Deletes the meter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the meter
	* @return the meter that was removed
	* @throws PortalException if a meter with the primary key could not be found
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.Meter deleteMeter(
		java.lang.String id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _meterLocalService.deleteMeter(id);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _meterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _meterLocalService.dynamicQuery();
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
		return _meterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.MeterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _meterLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.MeterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _meterLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _meterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _meterLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.Meter fetchMeter(
		java.lang.String id) {
		return _meterLocalService.fetchMeter(id);
	}

	/**
	* Returns the meter with the primary key.
	*
	* @param id the primary key of the meter
	* @return the meter
	* @throws PortalException if a meter with the primary key could not be found
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.Meter getMeter(
		java.lang.String id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _meterLocalService.getMeter(id);
	}

	/**
	* Returns a range of all the meters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.MeterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of meters
	* @param end the upper bound of the range of meters (not inclusive)
	* @return the range of meters
	*/
	@Override
	public java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.Meter> getMeters(
		int start, int end) {
		return _meterLocalService.getMeters(start, end);
	}

	/**
	* Returns the number of meters.
	*
	* @return the number of meters
	*/
	@Override
	public int getMetersCount() {
		return _meterLocalService.getMetersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _meterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _meterLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.Meter meterFromJSONObject(
		java.lang.String commitmentID, java.lang.String operationId,
		org.json.JSONObject o) {
		return _meterLocalService.meterFromJSONObject(commitmentID,
			operationId, o);
	}

	@Override
	public java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.Meter> metersFromJSONArray(
		java.lang.String commitmentID, java.lang.String operationId,
		org.json.JSONArray a) {
		return _meterLocalService.metersFromJSONArray(commitmentID,
			operationId, a);
	}

	@Override
	public void truncateTable()
		throws com.liferay.portal.kernel.dao.orm.ORMException {
		_meterLocalService.truncateTable();
	}

	/**
	* Updates the meter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param meter the meter
	* @return the meter that was updated
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.Meter updateMeter(
		fi.weasel.commitment2050.commitmentanalysis.model.Meter meter) {
		return _meterLocalService.updateMeter(meter);
	}

	@Override
	public MeterLocalService getWrappedService() {
		return _meterLocalService;
	}

	@Override
	public void setWrappedService(MeterLocalService meterLocalService) {
		_meterLocalService = meterLocalService;
	}

	private MeterLocalService _meterLocalService;
}