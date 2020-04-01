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

import fi.weasel.commitment2050.commitmentanalysis.exception.NoSuchCommitmentException;
import fi.weasel.commitment2050.commitmentanalysis.model.Commitment;

/**
 * The persistence interface for the commitment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see fi.weasel.commitment2050.commitmentanalysis.service.persistence.impl.CommitmentPersistenceImpl
 * @see CommitmentUtil
 * @generated
 */
@ProviderType
public interface CommitmentPersistence extends BasePersistence<Commitment> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommitmentUtil} to access the commitment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the commitment in the entity cache if it is enabled.
	*
	* @param commitment the commitment
	*/
	public void cacheResult(Commitment commitment);

	/**
	* Caches the commitments in the entity cache if it is enabled.
	*
	* @param commitments the commitments
	*/
	public void cacheResult(java.util.List<Commitment> commitments);

	/**
	* Creates a new commitment with the primary key. Does not add the commitment to the database.
	*
	* @param commitmentId the primary key for the new commitment
	* @return the new commitment
	*/
	public Commitment create(java.lang.String commitmentId);

	/**
	* Removes the commitment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commitmentId the primary key of the commitment
	* @return the commitment that was removed
	* @throws NoSuchCommitmentException if a commitment with the primary key could not be found
	*/
	public Commitment remove(java.lang.String commitmentId)
		throws NoSuchCommitmentException;

	public Commitment updateImpl(Commitment commitment);

	/**
	* Returns the commitment with the primary key or throws a {@link NoSuchCommitmentException} if it could not be found.
	*
	* @param commitmentId the primary key of the commitment
	* @return the commitment
	* @throws NoSuchCommitmentException if a commitment with the primary key could not be found
	*/
	public Commitment findByPrimaryKey(java.lang.String commitmentId)
		throws NoSuchCommitmentException;

	/**
	* Returns the commitment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commitmentId the primary key of the commitment
	* @return the commitment, or <code>null</code> if a commitment with the primary key could not be found
	*/
	public Commitment fetchByPrimaryKey(java.lang.String commitmentId);

	@Override
	public java.util.Map<java.io.Serializable, Commitment> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the commitments.
	*
	* @return the commitments
	*/
	public java.util.List<Commitment> findAll();

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
	public java.util.List<Commitment> findAll(int start, int end);

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
	public java.util.List<Commitment> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Commitment> orderByComparator);

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
	public java.util.List<Commitment> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Commitment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the commitments from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of commitments.
	*
	* @return the number of commitments
	*/
	public int countAll();
}