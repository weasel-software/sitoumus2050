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
 * Provides a wrapper for {@link CommitmentAnalysisResultLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CommitmentAnalysisResultLocalService
 * @generated
 */
@ProviderType
public class CommitmentAnalysisResultLocalServiceWrapper
	implements CommitmentAnalysisResultLocalService,
		ServiceWrapper<CommitmentAnalysisResultLocalService> {
	public CommitmentAnalysisResultLocalServiceWrapper(
		CommitmentAnalysisResultLocalService commitmentAnalysisResultLocalService) {
		_commitmentAnalysisResultLocalService = commitmentAnalysisResultLocalService;
	}

	/**
	* Adds the commitment analysis result to the database. Also notifies the appropriate model listeners.
	*
	* @param commitmentAnalysisResult the commitment analysis result
	* @return the commitment analysis result that was added
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult addCommitmentAnalysisResult(
		fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult commitmentAnalysisResult) {
		return _commitmentAnalysisResultLocalService.addCommitmentAnalysisResult(commitmentAnalysisResult);
	}

	/**
	* Creates a new commitment analysis result with the primary key. Does not add the commitment analysis result to the database.
	*
	* @param id the primary key for the new commitment analysis result
	* @return the new commitment analysis result
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult createCommitmentAnalysisResult(
		java.lang.String id) {
		return _commitmentAnalysisResultLocalService.createCommitmentAnalysisResult(id);
	}

	/**
	* Deletes the commitment analysis result from the database. Also notifies the appropriate model listeners.
	*
	* @param commitmentAnalysisResult the commitment analysis result
	* @return the commitment analysis result that was removed
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult deleteCommitmentAnalysisResult(
		fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult commitmentAnalysisResult) {
		return _commitmentAnalysisResultLocalService.deleteCommitmentAnalysisResult(commitmentAnalysisResult);
	}

	/**
	* Deletes the commitment analysis result with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the commitment analysis result
	* @return the commitment analysis result that was removed
	* @throws PortalException if a commitment analysis result with the primary key could not be found
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult deleteCommitmentAnalysisResult(
		java.lang.String id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commitmentAnalysisResultLocalService.deleteCommitmentAnalysisResult(id);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commitmentAnalysisResultLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commitmentAnalysisResultLocalService.dynamicQuery();
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
		return _commitmentAnalysisResultLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.CommitmentAnalysisResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _commitmentAnalysisResultLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.CommitmentAnalysisResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _commitmentAnalysisResultLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _commitmentAnalysisResultLocalService.dynamicQueryCount(dynamicQuery);
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
		return _commitmentAnalysisResultLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult fetchCommitmentAnalysisResult(
		java.lang.String id) {
		return _commitmentAnalysisResultLocalService.fetchCommitmentAnalysisResult(id);
	}

	@Override
	public java.util.List<java.lang.String> getCAResultTypes() {
		return _commitmentAnalysisResultLocalService.getCAResultTypes();
	}

	/**
	* Returns the commitment analysis result with the primary key.
	*
	* @param id the primary key of the commitment analysis result
	* @return the commitment analysis result
	* @throws PortalException if a commitment analysis result with the primary key could not be found
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult getCommitmentAnalysisResult(
		java.lang.String id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commitmentAnalysisResultLocalService.getCommitmentAnalysisResult(id);
	}

	/**
	* Returns a range of all the commitment analysis results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.CommitmentAnalysisResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commitment analysis results
	* @param end the upper bound of the range of commitment analysis results (not inclusive)
	* @return the range of commitment analysis results
	*/
	@Override
	public java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult> getCommitmentAnalysisResults(
		int start, int end) {
		return _commitmentAnalysisResultLocalService.getCommitmentAnalysisResults(start,
			end);
	}

	/**
	* Returns the number of commitment analysis results.
	*
	* @return the number of commitment analysis results
	*/
	@Override
	public int getCommitmentAnalysisResultsCount() {
		return _commitmentAnalysisResultLocalService.getCommitmentAnalysisResultsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _commitmentAnalysisResultLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commitmentAnalysisResultLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult getResultByResultType(
		java.lang.String resultType) {
		return _commitmentAnalysisResultLocalService.getResultByResultType(resultType);
	}

	@Override
	public java.util.List<java.util.List<java.lang.String>> getTopLifestyleCommitments() {
		return _commitmentAnalysisResultLocalService.getTopLifestyleCommitments();
	}

	@Override
	public void performResultCalculations() {
		_commitmentAnalysisResultLocalService.performResultCalculations();
	}

	/**
	* Updates the commitment analysis result in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commitmentAnalysisResult the commitment analysis result
	* @return the commitment analysis result that was updated
	*/
	@Override
	public fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult updateCommitmentAnalysisResult(
		fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult commitmentAnalysisResult) {
		return _commitmentAnalysisResultLocalService.updateCommitmentAnalysisResult(commitmentAnalysisResult);
	}

	@Override
	public CommitmentAnalysisResultLocalService getWrappedService() {
		return _commitmentAnalysisResultLocalService;
	}

	@Override
	public void setWrappedService(
		CommitmentAnalysisResultLocalService commitmentAnalysisResultLocalService) {
		_commitmentAnalysisResultLocalService = commitmentAnalysisResultLocalService;
	}

	private CommitmentAnalysisResultLocalService _commitmentAnalysisResultLocalService;
}