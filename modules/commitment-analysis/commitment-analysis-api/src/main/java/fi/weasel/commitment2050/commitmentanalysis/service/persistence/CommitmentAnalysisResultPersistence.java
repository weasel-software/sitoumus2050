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

import fi.weasel.commitment2050.commitmentanalysis.exception.NoSuchCommitmentAnalysisResultException;
import fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult;

/**
 * The persistence interface for the commitment analysis result service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see fi.weasel.commitment2050.commitmentanalysis.service.persistence.impl.CommitmentAnalysisResultPersistenceImpl
 * @see CommitmentAnalysisResultUtil
 * @generated
 */
@ProviderType
public interface CommitmentAnalysisResultPersistence extends BasePersistence<CommitmentAnalysisResult> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommitmentAnalysisResultUtil} to access the commitment analysis result persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the commitment analysis result in the entity cache if it is enabled.
	*
	* @param commitmentAnalysisResult the commitment analysis result
	*/
	public void cacheResult(CommitmentAnalysisResult commitmentAnalysisResult);

	/**
	* Caches the commitment analysis results in the entity cache if it is enabled.
	*
	* @param commitmentAnalysisResults the commitment analysis results
	*/
	public void cacheResult(
		java.util.List<CommitmentAnalysisResult> commitmentAnalysisResults);

	/**
	* Creates a new commitment analysis result with the primary key. Does not add the commitment analysis result to the database.
	*
	* @param id the primary key for the new commitment analysis result
	* @return the new commitment analysis result
	*/
	public CommitmentAnalysisResult create(java.lang.String id);

	/**
	* Removes the commitment analysis result with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the commitment analysis result
	* @return the commitment analysis result that was removed
	* @throws NoSuchCommitmentAnalysisResultException if a commitment analysis result with the primary key could not be found
	*/
	public CommitmentAnalysisResult remove(java.lang.String id)
		throws NoSuchCommitmentAnalysisResultException;

	public CommitmentAnalysisResult updateImpl(
		CommitmentAnalysisResult commitmentAnalysisResult);

	/**
	* Returns the commitment analysis result with the primary key or throws a {@link NoSuchCommitmentAnalysisResultException} if it could not be found.
	*
	* @param id the primary key of the commitment analysis result
	* @return the commitment analysis result
	* @throws NoSuchCommitmentAnalysisResultException if a commitment analysis result with the primary key could not be found
	*/
	public CommitmentAnalysisResult findByPrimaryKey(java.lang.String id)
		throws NoSuchCommitmentAnalysisResultException;

	/**
	* Returns the commitment analysis result with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the commitment analysis result
	* @return the commitment analysis result, or <code>null</code> if a commitment analysis result with the primary key could not be found
	*/
	public CommitmentAnalysisResult fetchByPrimaryKey(java.lang.String id);

	@Override
	public java.util.Map<java.io.Serializable, CommitmentAnalysisResult> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commitment analysis results.
	*
	* @return the commitment analysis results
	*/
	public java.util.List<CommitmentAnalysisResult> findAll();

	/**
	* Returns a range of all the commitment analysis results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommitmentAnalysisResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commitment analysis results
	* @param end the upper bound of the range of commitment analysis results (not inclusive)
	* @return the range of commitment analysis results
	*/
	public java.util.List<CommitmentAnalysisResult> findAll(int start, int end);

	/**
	* Returns an ordered range of all the commitment analysis results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommitmentAnalysisResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commitment analysis results
	* @param end the upper bound of the range of commitment analysis results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of commitment analysis results
	*/
	public java.util.List<CommitmentAnalysisResult> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommitmentAnalysisResult> orderByComparator);

	/**
	* Returns an ordered range of all the commitment analysis results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommitmentAnalysisResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of commitment analysis results
	* @param end the upper bound of the range of commitment analysis results (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of commitment analysis results
	*/
	public java.util.List<CommitmentAnalysisResult> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommitmentAnalysisResult> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commitment analysis results from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commitment analysis results.
	*
	* @return the number of commitment analysis results
	*/
	public int countAll();
}