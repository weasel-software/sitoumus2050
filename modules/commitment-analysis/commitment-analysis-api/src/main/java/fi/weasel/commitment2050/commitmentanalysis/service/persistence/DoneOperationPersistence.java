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

import fi.weasel.commitment2050.commitmentanalysis.exception.NoSuchDoneOperationException;
import fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation;

/**
 * The persistence interface for the done operation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see fi.weasel.commitment2050.commitmentanalysis.service.persistence.impl.DoneOperationPersistenceImpl
 * @see DoneOperationUtil
 * @generated
 */
@ProviderType
public interface DoneOperationPersistence extends BasePersistence<DoneOperation> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DoneOperationUtil} to access the done operation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the done operation in the entity cache if it is enabled.
	*
	* @param doneOperation the done operation
	*/
	public void cacheResult(DoneOperation doneOperation);

	/**
	* Caches the done operations in the entity cache if it is enabled.
	*
	* @param doneOperations the done operations
	*/
	public void cacheResult(java.util.List<DoneOperation> doneOperations);

	/**
	* Creates a new done operation with the primary key. Does not add the done operation to the database.
	*
	* @param id the primary key for the new done operation
	* @return the new done operation
	*/
	public DoneOperation create(java.lang.String id);

	/**
	* Removes the done operation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the done operation
	* @return the done operation that was removed
	* @throws NoSuchDoneOperationException if a done operation with the primary key could not be found
	*/
	public DoneOperation remove(java.lang.String id)
		throws NoSuchDoneOperationException;

	public DoneOperation updateImpl(DoneOperation doneOperation);

	/**
	* Returns the done operation with the primary key or throws a {@link NoSuchDoneOperationException} if it could not be found.
	*
	* @param id the primary key of the done operation
	* @return the done operation
	* @throws NoSuchDoneOperationException if a done operation with the primary key could not be found
	*/
	public DoneOperation findByPrimaryKey(java.lang.String id)
		throws NoSuchDoneOperationException;

	/**
	* Returns the done operation with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the done operation
	* @return the done operation, or <code>null</code> if a done operation with the primary key could not be found
	*/
	public DoneOperation fetchByPrimaryKey(java.lang.String id);

	@Override
	public java.util.Map<java.io.Serializable, DoneOperation> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the done operations.
	*
	* @return the done operations
	*/
	public java.util.List<DoneOperation> findAll();

	/**
	* Returns a range of all the done operations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DoneOperationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of done operations
	* @param end the upper bound of the range of done operations (not inclusive)
	* @return the range of done operations
	*/
	public java.util.List<DoneOperation> findAll(int start, int end);

	/**
	* Returns an ordered range of all the done operations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DoneOperationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of done operations
	* @param end the upper bound of the range of done operations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of done operations
	*/
	public java.util.List<DoneOperation> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DoneOperation> orderByComparator);

	/**
	* Returns an ordered range of all the done operations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DoneOperationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of done operations
	* @param end the upper bound of the range of done operations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of done operations
	*/
	public java.util.List<DoneOperation> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DoneOperation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the done operations from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of done operations.
	*
	* @return the number of done operations
	*/
	public int countAll();
}