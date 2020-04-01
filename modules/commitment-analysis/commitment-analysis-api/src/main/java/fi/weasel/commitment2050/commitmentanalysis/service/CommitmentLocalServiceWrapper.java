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
 * Provides a wrapper for {@link CommitmentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CommitmentLocalService
 * @generated
 */
@ProviderType
public class CommitmentLocalServiceWrapper implements CommitmentLocalService,
	ServiceWrapper<CommitmentLocalService> {
	public CommitmentLocalServiceWrapper(
		CommitmentLocalService commitmentLocalService) {
		_commitmentLocalService = commitmentLocalService;
	}

	/**
	* Adds the commitment to the database. Also notifies the appropriate model listeners.
	*
	* @param commitment the commitment
	* @return the commitment that was added
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.Commitment addCommitment(
		fi.weasel.commitment2050.commitmentanalysis.model.Commitment commitment) {
		return _commitmentLocalService.addCommitment(commitment);
	}

	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.Commitment addCommitmentAsJSON(
		org.json.JSONObject data) {
		return _commitmentLocalService.addCommitmentAsJSON(data);
	}

	/**
	* Creates a new commitment with the primary key. Does not add the commitment to the database.
	*
	* @param commitmentId the primary key for the new commitment
	* @return the new commitment
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.Commitment createCommitment(
		java.lang.String commitmentId) {
		return _commitmentLocalService.createCommitment(commitmentId);
	}

	/**
	* Deletes the commitment from the database. Also notifies the appropriate model listeners.
	*
	* @param commitment the commitment
	* @return the commitment that was removed
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.Commitment deleteCommitment(
		fi.weasel.commitment2050.commitmentanalysis.model.Commitment commitment) {
		return _commitmentLocalService.deleteCommitment(commitment);
	}

	/**
	* Deletes the commitment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commitmentId the primary key of the commitment
	* @return the commitment that was removed
	* @throws PortalException if a commitment with the primary key could not be found
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.Commitment deleteCommitment(
		java.lang.String commitmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commitmentLocalService.deleteCommitment(commitmentId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commitmentLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commitmentLocalService.dynamicQuery();
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
		return _commitmentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.CommitmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _commitmentLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.CommitmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _commitmentLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _commitmentLocalService.dynamicQueryCount(dynamicQuery);
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
		return _commitmentLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.Commitment fetchCommitment(
		java.lang.String commitmentId) {
		return _commitmentLocalService.fetchCommitment(commitmentId);
	}

	@Override
	public java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.Commitment> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return _commitmentLocalService.findByUserId(userId);
	}

	@Override
	public java.util.List getAvgReduction()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _commitmentLocalService.getAvgReduction();
	}

	@Override
	public java.util.List getAvgReductionByMeter()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _commitmentLocalService.getAvgReductionByMeter();
	}

	/**
	* Returns the commitment with the primary key.
	*
	* @param commitmentId the primary key of the commitment
	* @return the commitment
	* @throws PortalException if a commitment with the primary key could not be found
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.Commitment getCommitment(
		java.lang.String commitmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commitmentLocalService.getCommitment(commitmentId);
	}

	/**
	* Returns a range of all the commitments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.CommitmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commitments
	* @param end the upper bound of the range of commitments (not inclusive)
	* @return the range of commitments
	*/
	@Override
	public java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.Commitment> getCommitments(
		int start, int end) {
		return _commitmentLocalService.getCommitments(start, end);
	}

	/**
	* Returns the number of commitments.
	*
	* @return the number of commitments
	*/
	@Override
	public int getCommitmentsCount() {
		return _commitmentLocalService.getCommitmentsCount();
	}

	@Override
	public java.util.List getCountForAllTypes()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _commitmentLocalService.getCountForAllTypes();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _commitmentLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commitmentLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public void truncateTable()
		throws com.liferay.portal.kernel.dao.orm.ORMException {
		_commitmentLocalService.truncateTable();
	}

	/**
	* Updates the commitment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commitment the commitment
	* @return the commitment that was updated
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.Commitment updateCommitment(
		fi.weasel.commitment2050.commitmentanalysis.model.Commitment commitment) {
		return _commitmentLocalService.updateCommitment(commitment);
	}

	@Override
	public CommitmentLocalService getWrappedService() {
		return _commitmentLocalService;
	}

	@Override
	public void setWrappedService(CommitmentLocalService commitmentLocalService) {
		_commitmentLocalService = commitmentLocalService;
	}

	private CommitmentLocalService _commitmentLocalService;
}