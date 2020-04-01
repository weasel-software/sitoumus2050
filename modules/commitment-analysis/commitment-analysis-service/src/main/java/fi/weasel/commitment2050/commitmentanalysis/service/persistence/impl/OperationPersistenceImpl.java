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

package fi.weasel.commitment2050.commitmentanalysis.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import fi.weasel.commitment2050.commitmentanalysis.exception.NoSuchOperationException;
import fi.weasel.commitment2050.commitmentanalysis.model.Operation;
import fi.weasel.commitment2050.commitmentanalysis.model.impl.OperationImpl;
import fi.weasel.commitment2050.commitmentanalysis.model.impl.OperationModelImpl;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.OperationPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the operation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OperationPersistence
 * @see fi.weasel.commitment2050.commitmentanalysis.service.persistence.OperationUtil
 * @generated
 */
@ProviderType
public class OperationPersistenceImpl extends BasePersistenceImpl<Operation>
	implements OperationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OperationUtil} to access the operation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OperationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OperationModelImpl.ENTITY_CACHE_ENABLED,
			OperationModelImpl.FINDER_CACHE_ENABLED, OperationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OperationModelImpl.ENTITY_CACHE_ENABLED,
			OperationModelImpl.FINDER_CACHE_ENABLED, OperationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OperationModelImpl.ENTITY_CACHE_ENABLED,
			OperationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public OperationPersistenceImpl() {
		setModelClass(Operation.class);
	}

	/**
	 * Caches the operation in the entity cache if it is enabled.
	 *
	 * @param operation the operation
	 */
	@Override
	public void cacheResult(Operation operation) {
		entityCache.putResult(OperationModelImpl.ENTITY_CACHE_ENABLED,
			OperationImpl.class, operation.getPrimaryKey(), operation);

		operation.resetOriginalValues();
	}

	/**
	 * Caches the operations in the entity cache if it is enabled.
	 *
	 * @param operations the operations
	 */
	@Override
	public void cacheResult(List<Operation> operations) {
		for (Operation operation : operations) {
			if (entityCache.getResult(OperationModelImpl.ENTITY_CACHE_ENABLED,
						OperationImpl.class, operation.getPrimaryKey()) == null) {
				cacheResult(operation);
			}
			else {
				operation.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all operations.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OperationImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the operation.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Operation operation) {
		entityCache.removeResult(OperationModelImpl.ENTITY_CACHE_ENABLED,
			OperationImpl.class, operation.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Operation> operations) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Operation operation : operations) {
			entityCache.removeResult(OperationModelImpl.ENTITY_CACHE_ENABLED,
				OperationImpl.class, operation.getPrimaryKey());
		}
	}

	/**
	 * Creates a new operation with the primary key. Does not add the operation to the database.
	 *
	 * @param id the primary key for the new operation
	 * @return the new operation
	 */
	@Override
	public Operation create(String id) {
		Operation operation = new OperationImpl();

		operation.setNew(true);
		operation.setPrimaryKey(id);

		return operation;
	}

	/**
	 * Removes the operation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the operation
	 * @return the operation that was removed
	 * @throws NoSuchOperationException if a operation with the primary key could not be found
	 */
	@Override
	public Operation remove(String id) throws NoSuchOperationException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the operation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the operation
	 * @return the operation that was removed
	 * @throws NoSuchOperationException if a operation with the primary key could not be found
	 */
	@Override
	public Operation remove(Serializable primaryKey)
		throws NoSuchOperationException {
		Session session = null;

		try {
			session = openSession();

			Operation operation = (Operation)session.get(OperationImpl.class,
					primaryKey);

			if (operation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOperationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(operation);
		}
		catch (NoSuchOperationException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Operation removeImpl(Operation operation) {
		operation = toUnwrappedModel(operation);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(operation)) {
				operation = (Operation)session.get(OperationImpl.class,
						operation.getPrimaryKeyObj());
			}

			if (operation != null) {
				session.delete(operation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (operation != null) {
			clearCache(operation);
		}

		return operation;
	}

	@Override
	public Operation updateImpl(Operation operation) {
		operation = toUnwrappedModel(operation);

		boolean isNew = operation.isNew();

		Session session = null;

		try {
			session = openSession();

			if (operation.isNew()) {
				session.save(operation);

				operation.setNew(false);
			}
			else {
				operation = (Operation)session.merge(operation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(OperationModelImpl.ENTITY_CACHE_ENABLED,
			OperationImpl.class, operation.getPrimaryKey(), operation, false);

		operation.resetOriginalValues();

		return operation;
	}

	protected Operation toUnwrappedModel(Operation operation) {
		if (operation instanceof OperationImpl) {
			return operation;
		}

		OperationImpl operationImpl = new OperationImpl();

		operationImpl.setNew(operation.isNew());
		operationImpl.setPrimaryKey(operation.getPrimaryKey());

		operationImpl.setId(operation.getId());
		operationImpl.setCommitmentId(operation.getCommitmentId());
		operationImpl.setOperationId(operation.getOperationId());
		operationImpl.setOperationCategory(operation.getOperationCategory());
		operationImpl.setOperationTitleFI(operation.getOperationTitleFI());
		operationImpl.setOperationTitleSV(operation.getOperationTitleSV());
		operationImpl.setOperationTitleEN(operation.getOperationTitleEN());
		operationImpl.setOperationDescriptionFI(operation.getOperationDescriptionFI());
		operationImpl.setOperationDescriptionSV(operation.getOperationDescriptionSV());
		operationImpl.setOperationDescriptionEN(operation.getOperationDescriptionEN());

		return operationImpl;
	}

	/**
	 * Returns the operation with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the operation
	 * @return the operation
	 * @throws NoSuchOperationException if a operation with the primary key could not be found
	 */
	@Override
	public Operation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOperationException {
		Operation operation = fetchByPrimaryKey(primaryKey);

		if (operation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOperationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return operation;
	}

	/**
	 * Returns the operation with the primary key or throws a {@link NoSuchOperationException} if it could not be found.
	 *
	 * @param id the primary key of the operation
	 * @return the operation
	 * @throws NoSuchOperationException if a operation with the primary key could not be found
	 */
	@Override
	public Operation findByPrimaryKey(String id)
		throws NoSuchOperationException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the operation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the operation
	 * @return the operation, or <code>null</code> if a operation with the primary key could not be found
	 */
	@Override
	public Operation fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(OperationModelImpl.ENTITY_CACHE_ENABLED,
				OperationImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Operation operation = (Operation)serializable;

		if (operation == null) {
			Session session = null;

			try {
				session = openSession();

				operation = (Operation)session.get(OperationImpl.class,
						primaryKey);

				if (operation != null) {
					cacheResult(operation);
				}
				else {
					entityCache.putResult(OperationModelImpl.ENTITY_CACHE_ENABLED,
						OperationImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(OperationModelImpl.ENTITY_CACHE_ENABLED,
					OperationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return operation;
	}

	/**
	 * Returns the operation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the operation
	 * @return the operation, or <code>null</code> if a operation with the primary key could not be found
	 */
	@Override
	public Operation fetchByPrimaryKey(String id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	@Override
	public Map<Serializable, Operation> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Operation> map = new HashMap<Serializable, Operation>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Operation operation = fetchByPrimaryKey(primaryKey);

			if (operation != null) {
				map.put(primaryKey, operation);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(OperationModelImpl.ENTITY_CACHE_ENABLED,
					OperationImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Operation)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_OPERATION_WHERE_PKS_IN);

		for (int i = 0; i < uncachedPrimaryKeys.size(); i++) {
			query.append(StringPool.QUESTION);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				qPos.add((String)primaryKey);
			}

			for (Operation operation : (List<Operation>)q.list()) {
				map.put(operation.getPrimaryKeyObj(), operation);

				cacheResult(operation);

				uncachedPrimaryKeys.remove(operation.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(OperationModelImpl.ENTITY_CACHE_ENABLED,
					OperationImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the operations.
	 *
	 * @return the operations
	 */
	@Override
	public List<Operation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<Operation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<Operation> findAll(int start, int end,
		OrderByComparator<Operation> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<Operation> findAll(int start, int end,
		OrderByComparator<Operation> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Operation> list = null;

		if (retrieveFromCache) {
			list = (List<Operation>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_OPERATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OPERATION;

				if (pagination) {
					sql = sql.concat(OperationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Operation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Operation>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the operations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Operation operation : findAll()) {
			remove(operation);
		}
	}

	/**
	 * Returns the number of operations.
	 *
	 * @return the number of operations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OPERATION);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return OperationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the operation persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(OperationImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_OPERATION = "SELECT operation FROM Operation operation";
	private static final String _SQL_SELECT_OPERATION_WHERE_PKS_IN = "SELECT operation FROM Operation operation WHERE id IN (";
	private static final String _SQL_COUNT_OPERATION = "SELECT COUNT(operation) FROM Operation operation";
	private static final String _ORDER_BY_ENTITY_ALIAS = "operation.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Operation exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(OperationPersistenceImpl.class);
}