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

import fi.weasel.commitment2050.commitmentanalysis.exception.NoSuchDoneOperationException;
import fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation;
import fi.weasel.commitment2050.commitmentanalysis.model.impl.DoneOperationImpl;
import fi.weasel.commitment2050.commitmentanalysis.model.impl.DoneOperationModelImpl;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.DoneOperationPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the done operation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DoneOperationPersistence
 * @see fi.weasel.commitment2050.commitmentanalysis.service.persistence.DoneOperationUtil
 * @generated
 */
@ProviderType
public class DoneOperationPersistenceImpl extends BasePersistenceImpl<DoneOperation>
	implements DoneOperationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DoneOperationUtil} to access the done operation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DoneOperationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DoneOperationModelImpl.ENTITY_CACHE_ENABLED,
			DoneOperationModelImpl.FINDER_CACHE_ENABLED,
			DoneOperationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DoneOperationModelImpl.ENTITY_CACHE_ENABLED,
			DoneOperationModelImpl.FINDER_CACHE_ENABLED,
			DoneOperationImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DoneOperationModelImpl.ENTITY_CACHE_ENABLED,
			DoneOperationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public DoneOperationPersistenceImpl() {
		setModelClass(DoneOperation.class);
	}

	/**
	 * Caches the done operation in the entity cache if it is enabled.
	 *
	 * @param doneOperation the done operation
	 */
	@Override
	public void cacheResult(DoneOperation doneOperation) {
		entityCache.putResult(DoneOperationModelImpl.ENTITY_CACHE_ENABLED,
			DoneOperationImpl.class, doneOperation.getPrimaryKey(),
			doneOperation);

		doneOperation.resetOriginalValues();
	}

	/**
	 * Caches the done operations in the entity cache if it is enabled.
	 *
	 * @param doneOperations the done operations
	 */
	@Override
	public void cacheResult(List<DoneOperation> doneOperations) {
		for (DoneOperation doneOperation : doneOperations) {
			if (entityCache.getResult(
						DoneOperationModelImpl.ENTITY_CACHE_ENABLED,
						DoneOperationImpl.class, doneOperation.getPrimaryKey()) == null) {
				cacheResult(doneOperation);
			}
			else {
				doneOperation.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all done operations.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DoneOperationImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the done operation.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DoneOperation doneOperation) {
		entityCache.removeResult(DoneOperationModelImpl.ENTITY_CACHE_ENABLED,
			DoneOperationImpl.class, doneOperation.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<DoneOperation> doneOperations) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DoneOperation doneOperation : doneOperations) {
			entityCache.removeResult(DoneOperationModelImpl.ENTITY_CACHE_ENABLED,
				DoneOperationImpl.class, doneOperation.getPrimaryKey());
		}
	}

	/**
	 * Creates a new done operation with the primary key. Does not add the done operation to the database.
	 *
	 * @param id the primary key for the new done operation
	 * @return the new done operation
	 */
	@Override
	public DoneOperation create(String id) {
		DoneOperation doneOperation = new DoneOperationImpl();

		doneOperation.setNew(true);
		doneOperation.setPrimaryKey(id);

		return doneOperation;
	}

	/**
	 * Removes the done operation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the done operation
	 * @return the done operation that was removed
	 * @throws NoSuchDoneOperationException if a done operation with the primary key could not be found
	 */
	@Override
	public DoneOperation remove(String id) throws NoSuchDoneOperationException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the done operation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the done operation
	 * @return the done operation that was removed
	 * @throws NoSuchDoneOperationException if a done operation with the primary key could not be found
	 */
	@Override
	public DoneOperation remove(Serializable primaryKey)
		throws NoSuchDoneOperationException {
		Session session = null;

		try {
			session = openSession();

			DoneOperation doneOperation = (DoneOperation)session.get(DoneOperationImpl.class,
					primaryKey);

			if (doneOperation == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDoneOperationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(doneOperation);
		}
		catch (NoSuchDoneOperationException nsee) {
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
	protected DoneOperation removeImpl(DoneOperation doneOperation) {
		doneOperation = toUnwrappedModel(doneOperation);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(doneOperation)) {
				doneOperation = (DoneOperation)session.get(DoneOperationImpl.class,
						doneOperation.getPrimaryKeyObj());
			}

			if (doneOperation != null) {
				session.delete(doneOperation);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (doneOperation != null) {
			clearCache(doneOperation);
		}

		return doneOperation;
	}

	@Override
	public DoneOperation updateImpl(DoneOperation doneOperation) {
		doneOperation = toUnwrappedModel(doneOperation);

		boolean isNew = doneOperation.isNew();

		Session session = null;

		try {
			session = openSession();

			if (doneOperation.isNew()) {
				session.save(doneOperation);

				doneOperation.setNew(false);
			}
			else {
				doneOperation = (DoneOperation)session.merge(doneOperation);
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

		entityCache.putResult(DoneOperationModelImpl.ENTITY_CACHE_ENABLED,
			DoneOperationImpl.class, doneOperation.getPrimaryKey(),
			doneOperation, false);

		doneOperation.resetOriginalValues();

		return doneOperation;
	}

	protected DoneOperation toUnwrappedModel(DoneOperation doneOperation) {
		if (doneOperation instanceof DoneOperationImpl) {
			return doneOperation;
		}

		DoneOperationImpl doneOperationImpl = new DoneOperationImpl();

		doneOperationImpl.setNew(doneOperation.isNew());
		doneOperationImpl.setPrimaryKey(doneOperation.getPrimaryKey());

		doneOperationImpl.setId(doneOperation.getId());
		doneOperationImpl.setCommitmentId(doneOperation.getCommitmentId());
		doneOperationImpl.setOperationId(doneOperation.getOperationId());
		doneOperationImpl.setOperationCategory(doneOperation.getOperationCategory());
		doneOperationImpl.setOperationTitleFI(doneOperation.getOperationTitleFI());
		doneOperationImpl.setOperationTitleSV(doneOperation.getOperationTitleSV());
		doneOperationImpl.setOperationTitleEN(doneOperation.getOperationTitleEN());

		return doneOperationImpl;
	}

	/**
	 * Returns the done operation with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the done operation
	 * @return the done operation
	 * @throws NoSuchDoneOperationException if a done operation with the primary key could not be found
	 */
	@Override
	public DoneOperation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDoneOperationException {
		DoneOperation doneOperation = fetchByPrimaryKey(primaryKey);

		if (doneOperation == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDoneOperationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return doneOperation;
	}

	/**
	 * Returns the done operation with the primary key or throws a {@link NoSuchDoneOperationException} if it could not be found.
	 *
	 * @param id the primary key of the done operation
	 * @return the done operation
	 * @throws NoSuchDoneOperationException if a done operation with the primary key could not be found
	 */
	@Override
	public DoneOperation findByPrimaryKey(String id)
		throws NoSuchDoneOperationException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the done operation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the done operation
	 * @return the done operation, or <code>null</code> if a done operation with the primary key could not be found
	 */
	@Override
	public DoneOperation fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DoneOperationModelImpl.ENTITY_CACHE_ENABLED,
				DoneOperationImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DoneOperation doneOperation = (DoneOperation)serializable;

		if (doneOperation == null) {
			Session session = null;

			try {
				session = openSession();

				doneOperation = (DoneOperation)session.get(DoneOperationImpl.class,
						primaryKey);

				if (doneOperation != null) {
					cacheResult(doneOperation);
				}
				else {
					entityCache.putResult(DoneOperationModelImpl.ENTITY_CACHE_ENABLED,
						DoneOperationImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DoneOperationModelImpl.ENTITY_CACHE_ENABLED,
					DoneOperationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return doneOperation;
	}

	/**
	 * Returns the done operation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the done operation
	 * @return the done operation, or <code>null</code> if a done operation with the primary key could not be found
	 */
	@Override
	public DoneOperation fetchByPrimaryKey(String id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	@Override
	public Map<Serializable, DoneOperation> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DoneOperation> map = new HashMap<Serializable, DoneOperation>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DoneOperation doneOperation = fetchByPrimaryKey(primaryKey);

			if (doneOperation != null) {
				map.put(primaryKey, doneOperation);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DoneOperationModelImpl.ENTITY_CACHE_ENABLED,
					DoneOperationImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DoneOperation)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DONEOPERATION_WHERE_PKS_IN);

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

			for (DoneOperation doneOperation : (List<DoneOperation>)q.list()) {
				map.put(doneOperation.getPrimaryKeyObj(), doneOperation);

				cacheResult(doneOperation);

				uncachedPrimaryKeys.remove(doneOperation.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DoneOperationModelImpl.ENTITY_CACHE_ENABLED,
					DoneOperationImpl.class, primaryKey, nullModel);
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
	 * Returns all the done operations.
	 *
	 * @return the done operations
	 */
	@Override
	public List<DoneOperation> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<DoneOperation> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<DoneOperation> findAll(int start, int end,
		OrderByComparator<DoneOperation> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<DoneOperation> findAll(int start, int end,
		OrderByComparator<DoneOperation> orderByComparator,
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

		List<DoneOperation> list = null;

		if (retrieveFromCache) {
			list = (List<DoneOperation>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DONEOPERATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DONEOPERATION;

				if (pagination) {
					sql = sql.concat(DoneOperationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DoneOperation>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DoneOperation>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the done operations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DoneOperation doneOperation : findAll()) {
			remove(doneOperation);
		}
	}

	/**
	 * Returns the number of done operations.
	 *
	 * @return the number of done operations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DONEOPERATION);

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
		return DoneOperationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the done operation persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DoneOperationImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_DONEOPERATION = "SELECT doneOperation FROM DoneOperation doneOperation";
	private static final String _SQL_SELECT_DONEOPERATION_WHERE_PKS_IN = "SELECT doneOperation FROM DoneOperation doneOperation WHERE id IN (";
	private static final String _SQL_COUNT_DONEOPERATION = "SELECT COUNT(doneOperation) FROM DoneOperation doneOperation";
	private static final String _ORDER_BY_ENTITY_ALIAS = "doneOperation.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DoneOperation exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(DoneOperationPersistenceImpl.class);
}