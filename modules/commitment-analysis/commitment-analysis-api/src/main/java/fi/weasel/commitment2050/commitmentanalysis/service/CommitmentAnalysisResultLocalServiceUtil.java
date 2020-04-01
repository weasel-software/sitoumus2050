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
 * Provides the local service utility for CommitmentAnalysisResult. This utility wraps
 * {@link fi.weasel.commitment2050.commitmentanalysis.service.impl.CommitmentAnalysisResultLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see CommitmentAnalysisResultLocalService
 * @see fi.weasel.commitment2050.commitmentanalysis.service.base.CommitmentAnalysisResultLocalServiceBaseImpl
 * @see fi.weasel.commitment2050.commitmentanalysis.service.impl.CommitmentAnalysisResultLocalServiceImpl
 * @generated
 */
@ProviderType
public class CommitmentAnalysisResultLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link fi.weasel.commitment2050.commitmentanalysis.service.impl.CommitmentAnalysisResultLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the commitment analysis result to the database. Also notifies the appropriate model listeners.
	*
	* @param commitmentAnalysisResult the commitment analysis result
	* @return the commitment analysis result that was added
	*/
	public static fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult addCommitmentAnalysisResult(
		fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult commitmentAnalysisResult) {
		return getService().addCommitmentAnalysisResult(commitmentAnalysisResult);
	}

	/**
	* Creates a new commitment analysis result with the primary key. Does not add the commitment analysis result to the database.
	*
	* @param id the primary key for the new commitment analysis result
	* @return the new commitment analysis result
	*/
	public static fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult createCommitmentAnalysisResult(
		java.lang.String id) {
		return getService().createCommitmentAnalysisResult(id);
	}

	/**
	* Deletes the commitment analysis result from the database. Also notifies the appropriate model listeners.
	*
	* @param commitmentAnalysisResult the commitment analysis result
	* @return the commitment analysis result that was removed
	*/
	public static fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult deleteCommitmentAnalysisResult(
		fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult commitmentAnalysisResult) {
		return getService()
				   .deleteCommitmentAnalysisResult(commitmentAnalysisResult);
	}

	/**
	* Deletes the commitment analysis result with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the commitment analysis result
	* @return the commitment analysis result that was removed
	* @throws PortalException if a commitment analysis result with the primary key could not be found
	*/
	public static fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult deleteCommitmentAnalysisResult(
		java.lang.String id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCommitmentAnalysisResult(id);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.CommitmentAnalysisResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.CommitmentAnalysisResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult fetchCommitmentAnalysisResult(
		java.lang.String id) {
		return getService().fetchCommitmentAnalysisResult(id);
	}

	public static java.util.List<java.lang.String> getCAResultTypes() {
		return getService().getCAResultTypes();
	}

	/**
	* Returns the commitment analysis result with the primary key.
	*
	* @param id the primary key of the commitment analysis result
	* @return the commitment analysis result
	* @throws PortalException if a commitment analysis result with the primary key could not be found
	*/
	public static fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult getCommitmentAnalysisResult(
		java.lang.String id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommitmentAnalysisResult(id);
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
	public static java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult> getCommitmentAnalysisResults(
		int start, int end) {
		return getService().getCommitmentAnalysisResults(start, end);
	}

	/**
	* Returns the number of commitment analysis results.
	*
	* @return the number of commitment analysis results
	*/
	public static int getCommitmentAnalysisResultsCount() {
		return getService().getCommitmentAnalysisResultsCount();
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

	public static fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult getResultByResultType(
		java.lang.String resultType) {
		return getService().getResultByResultType(resultType);
	}

	public static java.util.List<java.util.List<java.lang.String>> getTopLifestyleCommitments() {
		return getService().getTopLifestyleCommitments();
	}

	public static void performResultCalculations() {
		getService().performResultCalculations();
	}

	/**
	* Updates the commitment analysis result in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param commitmentAnalysisResult the commitment analysis result
	* @return the commitment analysis result that was updated
	*/
	public static fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult updateCommitmentAnalysisResult(
		fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult commitmentAnalysisResult) {
		return getService()
				   .updateCommitmentAnalysisResult(commitmentAnalysisResult);
	}

	public static CommitmentAnalysisResultLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommitmentAnalysisResultLocalService, CommitmentAnalysisResultLocalService> _serviceTracker =
		ServiceTrackerFactory.open(CommitmentAnalysisResultLocalService.class);
}