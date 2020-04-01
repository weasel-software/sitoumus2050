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

import fi.weasel.commitment2050.commitmentanalysis.exception.NoSuchCommitmentAnalysisResultException;
import fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult;
import fi.weasel.commitment2050.commitmentanalysis.model.impl.CommitmentAnalysisResultImpl;
import fi.weasel.commitment2050.commitmentanalysis.model.impl.CommitmentAnalysisResultModelImpl;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.CommitmentAnalysisResultPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the commitment analysis result service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CommitmentAnalysisResultPersistence
 * @see fi.weasel.commitment2050.commitmentanalysis.service.persistence.CommitmentAnalysisResultUtil
 * @generated
 */
@ProviderType
public class CommitmentAnalysisResultPersistenceImpl extends BasePersistenceImpl<CommitmentAnalysisResult>
	implements CommitmentAnalysisResultPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommitmentAnalysisResultUtil} to access the commitment analysis result persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommitmentAnalysisResultImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommitmentAnalysisResultModelImpl.ENTITY_CACHE_ENABLED,
			CommitmentAnalysisResultModelImpl.FINDER_CACHE_ENABLED,
			CommitmentAnalysisResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommitmentAnalysisResultModelImpl.ENTITY_CACHE_ENABLED,
			CommitmentAnalysisResultModelImpl.FINDER_CACHE_ENABLED,
			CommitmentAnalysisResultImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommitmentAnalysisResultModelImpl.ENTITY_CACHE_ENABLED,
			CommitmentAnalysisResultModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CommitmentAnalysisResultPersistenceImpl() {
		setModelClass(CommitmentAnalysisResult.class);
	}

	/**
	 * Caches the commitment analysis result in the entity cache if it is enabled.
	 *
	 * @param commitmentAnalysisResult the commitment analysis result
	 */
	@Override
	public void cacheResult(CommitmentAnalysisResult commitmentAnalysisResult) {
		entityCache.putResult(CommitmentAnalysisResultModelImpl.ENTITY_CACHE_ENABLED,
			CommitmentAnalysisResultImpl.class,
			commitmentAnalysisResult.getPrimaryKey(), commitmentAnalysisResult);

		commitmentAnalysisResult.resetOriginalValues();
	}

	/**
	 * Caches the commitment analysis results in the entity cache if it is enabled.
	 *
	 * @param commitmentAnalysisResults the commitment analysis results
	 */
	@Override
	public void cacheResult(
		List<CommitmentAnalysisResult> commitmentAnalysisResults) {
		for (CommitmentAnalysisResult commitmentAnalysisResult : commitmentAnalysisResults) {
			if (entityCache.getResult(
						CommitmentAnalysisResultModelImpl.ENTITY_CACHE_ENABLED,
						CommitmentAnalysisResultImpl.class,
						commitmentAnalysisResult.getPrimaryKey()) == null) {
				cacheResult(commitmentAnalysisResult);
			}
			else {
				commitmentAnalysisResult.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commitment analysis results.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommitmentAnalysisResultImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commitment analysis result.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommitmentAnalysisResult commitmentAnalysisResult) {
		entityCache.removeResult(CommitmentAnalysisResultModelImpl.ENTITY_CACHE_ENABLED,
			CommitmentAnalysisResultImpl.class,
			commitmentAnalysisResult.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<CommitmentAnalysisResult> commitmentAnalysisResults) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommitmentAnalysisResult commitmentAnalysisResult : commitmentAnalysisResults) {
			entityCache.removeResult(CommitmentAnalysisResultModelImpl.ENTITY_CACHE_ENABLED,
				CommitmentAnalysisResultImpl.class,
				commitmentAnalysisResult.getPrimaryKey());
		}
	}

	/**
	 * Creates a new commitment analysis result with the primary key. Does not add the commitment analysis result to the database.
	 *
	 * @param id the primary key for the new commitment analysis result
	 * @return the new commitment analysis result
	 */
	@Override
	public CommitmentAnalysisResult create(String id) {
		CommitmentAnalysisResult commitmentAnalysisResult = new CommitmentAnalysisResultImpl();

		commitmentAnalysisResult.setNew(true);
		commitmentAnalysisResult.setPrimaryKey(id);

		return commitmentAnalysisResult;
	}

	/**
	 * Removes the commitment analysis result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the commitment analysis result
	 * @return the commitment analysis result that was removed
	 * @throws NoSuchCommitmentAnalysisResultException if a commitment analysis result with the primary key could not be found
	 */
	@Override
	public CommitmentAnalysisResult remove(String id)
		throws NoSuchCommitmentAnalysisResultException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the commitment analysis result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commitment analysis result
	 * @return the commitment analysis result that was removed
	 * @throws NoSuchCommitmentAnalysisResultException if a commitment analysis result with the primary key could not be found
	 */
	@Override
	public CommitmentAnalysisResult remove(Serializable primaryKey)
		throws NoSuchCommitmentAnalysisResultException {
		Session session = null;

		try {
			session = openSession();

			CommitmentAnalysisResult commitmentAnalysisResult = (CommitmentAnalysisResult)session.get(CommitmentAnalysisResultImpl.class,
					primaryKey);

			if (commitmentAnalysisResult == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCommitmentAnalysisResultException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commitmentAnalysisResult);
		}
		catch (NoSuchCommitmentAnalysisResultException nsee) {
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
	protected CommitmentAnalysisResult removeImpl(
		CommitmentAnalysisResult commitmentAnalysisResult) {
		commitmentAnalysisResult = toUnwrappedModel(commitmentAnalysisResult);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commitmentAnalysisResult)) {
				commitmentAnalysisResult = (CommitmentAnalysisResult)session.get(CommitmentAnalysisResultImpl.class,
						commitmentAnalysisResult.getPrimaryKeyObj());
			}

			if (commitmentAnalysisResult != null) {
				session.delete(commitmentAnalysisResult);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commitmentAnalysisResult != null) {
			clearCache(commitmentAnalysisResult);
		}

		return commitmentAnalysisResult;
	}

	@Override
	public CommitmentAnalysisResult updateImpl(
		CommitmentAnalysisResult commitmentAnalysisResult) {
		commitmentAnalysisResult = toUnwrappedModel(commitmentAnalysisResult);

		boolean isNew = commitmentAnalysisResult.isNew();

		Session session = null;

		try {
			session = openSession();

			if (commitmentAnalysisResult.isNew()) {
				session.save(commitmentAnalysisResult);

				commitmentAnalysisResult.setNew(false);
			}
			else {
				commitmentAnalysisResult = (CommitmentAnalysisResult)session.merge(commitmentAnalysisResult);
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

		entityCache.putResult(CommitmentAnalysisResultModelImpl.ENTITY_CACHE_ENABLED,
			CommitmentAnalysisResultImpl.class,
			commitmentAnalysisResult.getPrimaryKey(), commitmentAnalysisResult,
			false);

		commitmentAnalysisResult.resetOriginalValues();

		return commitmentAnalysisResult;
	}

	protected CommitmentAnalysisResult toUnwrappedModel(
		CommitmentAnalysisResult commitmentAnalysisResult) {
		if (commitmentAnalysisResult instanceof CommitmentAnalysisResultImpl) {
			return commitmentAnalysisResult;
		}

		CommitmentAnalysisResultImpl commitmentAnalysisResultImpl = new CommitmentAnalysisResultImpl();

		commitmentAnalysisResultImpl.setNew(commitmentAnalysisResult.isNew());
		commitmentAnalysisResultImpl.setPrimaryKey(commitmentAnalysisResult.getPrimaryKey());

		commitmentAnalysisResultImpl.setId(commitmentAnalysisResult.getId());
		commitmentAnalysisResultImpl.setResultType(commitmentAnalysisResult.getResultType());
		commitmentAnalysisResultImpl.setResultData(commitmentAnalysisResult.getResultData());
		commitmentAnalysisResultImpl.setCalculated(commitmentAnalysisResult.getCalculated());
		commitmentAnalysisResultImpl.setSuccess(commitmentAnalysisResult.isSuccess());

		return commitmentAnalysisResultImpl;
	}

	/**
	 * Returns the commitment analysis result with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commitment analysis result
	 * @return the commitment analysis result
	 * @throws NoSuchCommitmentAnalysisResultException if a commitment analysis result with the primary key could not be found
	 */
	@Override
	public CommitmentAnalysisResult findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCommitmentAnalysisResultException {
		CommitmentAnalysisResult commitmentAnalysisResult = fetchByPrimaryKey(primaryKey);

		if (commitmentAnalysisResult == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCommitmentAnalysisResultException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commitmentAnalysisResult;
	}

	/**
	 * Returns the commitment analysis result with the primary key or throws a {@link NoSuchCommitmentAnalysisResultException} if it could not be found.
	 *
	 * @param id the primary key of the commitment analysis result
	 * @return the commitment analysis result
	 * @throws NoSuchCommitmentAnalysisResultException if a commitment analysis result with the primary key could not be found
	 */
	@Override
	public CommitmentAnalysisResult findByPrimaryKey(String id)
		throws NoSuchCommitmentAnalysisResultException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the commitment analysis result with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commitment analysis result
	 * @return the commitment analysis result, or <code>null</code> if a commitment analysis result with the primary key could not be found
	 */
	@Override
	public CommitmentAnalysisResult fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommitmentAnalysisResultModelImpl.ENTITY_CACHE_ENABLED,
				CommitmentAnalysisResultImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CommitmentAnalysisResult commitmentAnalysisResult = (CommitmentAnalysisResult)serializable;

		if (commitmentAnalysisResult == null) {
			Session session = null;

			try {
				session = openSession();

				commitmentAnalysisResult = (CommitmentAnalysisResult)session.get(CommitmentAnalysisResultImpl.class,
						primaryKey);

				if (commitmentAnalysisResult != null) {
					cacheResult(commitmentAnalysisResult);
				}
				else {
					entityCache.putResult(CommitmentAnalysisResultModelImpl.ENTITY_CACHE_ENABLED,
						CommitmentAnalysisResultImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommitmentAnalysisResultModelImpl.ENTITY_CACHE_ENABLED,
					CommitmentAnalysisResultImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commitmentAnalysisResult;
	}

	/**
	 * Returns the commitment analysis result with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the commitment analysis result
	 * @return the commitment analysis result, or <code>null</code> if a commitment analysis result with the primary key could not be found
	 */
	@Override
	public CommitmentAnalysisResult fetchByPrimaryKey(String id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	@Override
	public Map<Serializable, CommitmentAnalysisResult> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CommitmentAnalysisResult> map = new HashMap<Serializable, CommitmentAnalysisResult>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CommitmentAnalysisResult commitmentAnalysisResult = fetchByPrimaryKey(primaryKey);

			if (commitmentAnalysisResult != null) {
				map.put(primaryKey, commitmentAnalysisResult);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommitmentAnalysisResultModelImpl.ENTITY_CACHE_ENABLED,
					CommitmentAnalysisResultImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CommitmentAnalysisResult)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMITMENTANALYSISRESULT_WHERE_PKS_IN);

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

			for (CommitmentAnalysisResult commitmentAnalysisResult : (List<CommitmentAnalysisResult>)q.list()) {
				map.put(commitmentAnalysisResult.getPrimaryKeyObj(),
					commitmentAnalysisResult);

				cacheResult(commitmentAnalysisResult);

				uncachedPrimaryKeys.remove(commitmentAnalysisResult.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommitmentAnalysisResultModelImpl.ENTITY_CACHE_ENABLED,
					CommitmentAnalysisResultImpl.class, primaryKey, nullModel);
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
	 * Returns all the commitment analysis results.
	 *
	 * @return the commitment analysis results
	 */
	@Override
	public List<CommitmentAnalysisResult> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<CommitmentAnalysisResult> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<CommitmentAnalysisResult> findAll(int start, int end,
		OrderByComparator<CommitmentAnalysisResult> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<CommitmentAnalysisResult> findAll(int start, int end,
		OrderByComparator<CommitmentAnalysisResult> orderByComparator,
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

		List<CommitmentAnalysisResult> list = null;

		if (retrieveFromCache) {
			list = (List<CommitmentAnalysisResult>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMITMENTANALYSISRESULT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMITMENTANALYSISRESULT;

				if (pagination) {
					sql = sql.concat(CommitmentAnalysisResultModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommitmentAnalysisResult>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CommitmentAnalysisResult>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the commitment analysis results from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommitmentAnalysisResult commitmentAnalysisResult : findAll()) {
			remove(commitmentAnalysisResult);
		}
	}

	/**
	 * Returns the number of commitment analysis results.
	 *
	 * @return the number of commitment analysis results
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMITMENTANALYSISRESULT);

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
		return CommitmentAnalysisResultModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commitment analysis result persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommitmentAnalysisResultImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_COMMITMENTANALYSISRESULT = "SELECT commitmentAnalysisResult FROM CommitmentAnalysisResult commitmentAnalysisResult";
	private static final String _SQL_SELECT_COMMITMENTANALYSISRESULT_WHERE_PKS_IN =
		"SELECT commitmentAnalysisResult FROM CommitmentAnalysisResult commitmentAnalysisResult WHERE id IN (";
	private static final String _SQL_COUNT_COMMITMENTANALYSISRESULT = "SELECT COUNT(commitmentAnalysisResult) FROM CommitmentAnalysisResult commitmentAnalysisResult";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commitmentAnalysisResult.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommitmentAnalysisResult exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CommitmentAnalysisResultPersistenceImpl.class);
}