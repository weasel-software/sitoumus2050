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

import fi.weasel.commitment2050.commitmentanalysis.exception.NoSuchOperationException;
import fi.weasel.commitment2050.commitmentanalysis.model.Operation;

/**
 * The persistence interface for the operation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see fi.weasel.commitment2050.commitmentanalysis.service.persistence.impl.OperationPersistenceImpl
 * @see OperationUtil
 * @generated
 */
@ProviderType
public interface OperationPersistence extends BasePersistence<Operation> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OperationUtil} to access the operation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the operation in the entity cache if it is enabled.
	*
	* @param operation the operation
	*/
	public void cacheResult(Operation operation);

	/**
	* Caches the operations in the entity cache if it is enabled.
	*
	* @param operations the operations
	*/
	public void cacheResult(java.util.List<Operation> operations);

	/**
	* Creates a new operation with the primary key. Does not add the operation to the database.
	*
	* @param id the primary key for the new operation
	* @return the new operation
	*/
	public Operation create(java.lang.String id);

	/**
	* Removes the operation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the operation
	* @return the operation that was removed
	* @throws NoSuchOperationException if a operation with the primary key could not be found
	*/
	public Operation remove(java.lang.String id)
		throws NoSuchOperationException;

	public Operation updateImpl(Operation operation);

	/**
	* Returns the operation with the primary key or throws a {@link NoSuchOperationException} if it could not be found.
	*
	* @param id the primary key of the operation
	* @return the operation
	* @throws NoSuchOperationException if a operation with the primary key could not be found
	*/
	public Operation findByPrimaryKey(java.lang.String id)
		throws NoSuchOperationException;

	/**
	* Returns the operation with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the operation
	* @return the operation, or <code>null</code> if a operation with the primary key could not be found
	*/
	public Operation fetchByPrimaryKey(java.lang.String id);

	@Override
	public java.util.Map<java.io.Serializable, Operation> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the operations.
	*
	* @return the operations
	*/
	public java.util.List<Operation> findAll();

	/**
	* Returns a range of all the operations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OperationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of operations
	* @param end the upper bound of the range of operations (not inclusive)
	* @return the range of operations
	*/
	public java.util.List<Operation> findAll(int start, int end);

	/**
	* Returns an ordered range of all the operations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OperationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of operations
	* @param end the upper bound of the range of operations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of operations
	*/
	public java.util.List<Operation> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Operation> orderByComparator);

	/**
	* Returns an ordered range of all the operations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OperationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of operations
	* @param end the upper bound of the range of operations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of operations
	*/
	public java.util.List<Operation> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Operation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the operations from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of operations.
	*
	* @return the number of operations
	*/
	public int countAll();
}