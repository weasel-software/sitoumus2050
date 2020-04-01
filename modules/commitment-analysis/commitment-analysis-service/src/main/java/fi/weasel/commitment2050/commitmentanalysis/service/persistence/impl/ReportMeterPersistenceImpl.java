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

import fi.weasel.commitment2050.commitmentanalysis.exception.NoSuchReportMeterException;
import fi.weasel.commitment2050.commitmentanalysis.model.ReportMeter;
import fi.weasel.commitment2050.commitmentanalysis.model.impl.ReportMeterImpl;
import fi.weasel.commitment2050.commitmentanalysis.model.impl.ReportMeterModelImpl;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.ReportMeterPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the report meter service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReportMeterPersistence
 * @see fi.weasel.commitment2050.commitmentanalysis.service.persistence.ReportMeterUtil
 * @generated
 */
@ProviderType
public class ReportMeterPersistenceImpl extends BasePersistenceImpl<ReportMeter>
	implements ReportMeterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ReportMeterUtil} to access the report meter persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ReportMeterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ReportMeterModelImpl.ENTITY_CACHE_ENABLED,
			ReportMeterModelImpl.FINDER_CACHE_ENABLED, ReportMeterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ReportMeterModelImpl.ENTITY_CACHE_ENABLED,
			ReportMeterModelImpl.FINDER_CACHE_ENABLED, ReportMeterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ReportMeterModelImpl.ENTITY_CACHE_ENABLED,
			ReportMeterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ReportMeterPersistenceImpl() {
		setModelClass(ReportMeter.class);
	}

	/**
	 * Caches the report meter in the entity cache if it is enabled.
	 *
	 * @param reportMeter the report meter
	 */
	@Override
	public void cacheResult(ReportMeter reportMeter) {
		entityCache.putResult(ReportMeterModelImpl.ENTITY_CACHE_ENABLED,
			ReportMeterImpl.class, reportMeter.getPrimaryKey(), reportMeter);

		reportMeter.resetOriginalValues();
	}

	/**
	 * Caches the report meters in the entity cache if it is enabled.
	 *
	 * @param reportMeters the report meters
	 */
	@Override
	public void cacheResult(List<ReportMeter> reportMeters) {
		for (ReportMeter reportMeter : reportMeters) {
			if (entityCache.getResult(
						ReportMeterModelImpl.ENTITY_CACHE_ENABLED,
						ReportMeterImpl.class, reportMeter.getPrimaryKey()) == null) {
				cacheResult(reportMeter);
			}
			else {
				reportMeter.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all report meters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ReportMeterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the report meter.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ReportMeter reportMeter) {
		entityCache.removeResult(ReportMeterModelImpl.ENTITY_CACHE_ENABLED,
			ReportMeterImpl.class, reportMeter.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ReportMeter> reportMeters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ReportMeter reportMeter : reportMeters) {
			entityCache.removeResult(ReportMeterModelImpl.ENTITY_CACHE_ENABLED,
				ReportMeterImpl.class, reportMeter.getPrimaryKey());
		}
	}

	/**
	 * Creates a new report meter with the primary key. Does not add the report meter to the database.
	 *
	 * @param id the primary key for the new report meter
	 * @return the new report meter
	 */
	@Override
	public ReportMeter create(String id) {
		ReportMeter reportMeter = new ReportMeterImpl();

		reportMeter.setNew(true);
		reportMeter.setPrimaryKey(id);

		return reportMeter;
	}

	/**
	 * Removes the report meter with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the report meter
	 * @return the report meter that was removed
	 * @throws NoSuchReportMeterException if a report meter with the primary key could not be found
	 */
	@Override
	public ReportMeter remove(String id) throws NoSuchReportMeterException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the report meter with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the report meter
	 * @return the report meter that was removed
	 * @throws NoSuchReportMeterException if a report meter with the primary key could not be found
	 */
	@Override
	public ReportMeter remove(Serializable primaryKey)
		throws NoSuchReportMeterException {
		Session session = null;

		try {
			session = openSession();

			ReportMeter reportMeter = (ReportMeter)session.get(ReportMeterImpl.class,
					primaryKey);

			if (reportMeter == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchReportMeterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(reportMeter);
		}
		catch (NoSuchReportMeterException nsee) {
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
	protected ReportMeter removeImpl(ReportMeter reportMeter) {
		reportMeter = toUnwrappedModel(reportMeter);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(reportMeter)) {
				reportMeter = (ReportMeter)session.get(ReportMeterImpl.class,
						reportMeter.getPrimaryKeyObj());
			}

			if (reportMeter != null) {
				session.delete(reportMeter);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (reportMeter != null) {
			clearCache(reportMeter);
		}

		return reportMeter;
	}

	@Override
	public ReportMeter updateImpl(ReportMeter reportMeter) {
		reportMeter = toUnwrappedModel(reportMeter);

		boolean isNew = reportMeter.isNew();

		Session session = null;

		try {
			session = openSession();

			if (reportMeter.isNew()) {
				session.save(reportMeter);

				reportMeter.setNew(false);
			}
			else {
				reportMeter = (ReportMeter)session.merge(reportMeter);
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

		entityCache.putResult(ReportMeterModelImpl.ENTITY_CACHE_ENABLED,
			ReportMeterImpl.class, reportMeter.getPrimaryKey(), reportMeter,
			false);

		reportMeter.resetOriginalValues();

		return reportMeter;
	}

	protected ReportMeter toUnwrappedModel(ReportMeter reportMeter) {
		if (reportMeter instanceof ReportMeterImpl) {
			return reportMeter;
		}

		ReportMeterImpl reportMeterImpl = new ReportMeterImpl();

		reportMeterImpl.setNew(reportMeter.isNew());
		reportMeterImpl.setPrimaryKey(reportMeter.getPrimaryKey());

		reportMeterImpl.setId(reportMeter.getId());
		reportMeterImpl.setCommitmentId(reportMeter.getCommitmentId());
		reportMeterImpl.setOperationId(reportMeter.getOperationId());
		reportMeterImpl.setReportId(reportMeter.getReportId());
		reportMeterImpl.setMeterId(reportMeter.getMeterId());
		reportMeterImpl.setMeterCategory(reportMeter.getMeterCategory());
		reportMeterImpl.setMeterChartType(reportMeter.getMeterChartType());
		reportMeterImpl.setMeterValueType(reportMeter.getMeterValueType());
		reportMeterImpl.setMeterTypeFI(reportMeter.getMeterTypeFI());
		reportMeterImpl.setMeterTypeSV(reportMeter.getMeterTypeSV());
		reportMeterImpl.setMeterTypeEN(reportMeter.getMeterTypeEN());
		reportMeterImpl.setCurrentLevel(reportMeter.getCurrentLevel());
		reportMeterImpl.setStartingLevel(reportMeter.getStartingLevel());
		reportMeterImpl.setTargetLevel(reportMeter.getTargetLevel());

		return reportMeterImpl;
	}

	/**
	 * Returns the report meter with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the report meter
	 * @return the report meter
	 * @throws NoSuchReportMeterException if a report meter with the primary key could not be found
	 */
	@Override
	public ReportMeter findByPrimaryKey(Serializable primaryKey)
		throws NoSuchReportMeterException {
		ReportMeter reportMeter = fetchByPrimaryKey(primaryKey);

		if (reportMeter == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchReportMeterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return reportMeter;
	}

	/**
	 * Returns the report meter with the primary key or throws a {@link NoSuchReportMeterException} if it could not be found.
	 *
	 * @param id the primary key of the report meter
	 * @return the report meter
	 * @throws NoSuchReportMeterException if a report meter with the primary key could not be found
	 */
	@Override
	public ReportMeter findByPrimaryKey(String id)
		throws NoSuchReportMeterException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the report meter with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the report meter
	 * @return the report meter, or <code>null</code> if a report meter with the primary key could not be found
	 */
	@Override
	public ReportMeter fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ReportMeterModelImpl.ENTITY_CACHE_ENABLED,
				ReportMeterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ReportMeter reportMeter = (ReportMeter)serializable;

		if (reportMeter == null) {
			Session session = null;

			try {
				session = openSession();

				reportMeter = (ReportMeter)session.get(ReportMeterImpl.class,
						primaryKey);

				if (reportMeter != null) {
					cacheResult(reportMeter);
				}
				else {
					entityCache.putResult(ReportMeterModelImpl.ENTITY_CACHE_ENABLED,
						ReportMeterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ReportMeterModelImpl.ENTITY_CACHE_ENABLED,
					ReportMeterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return reportMeter;
	}

	/**
	 * Returns the report meter with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the report meter
	 * @return the report meter, or <code>null</code> if a report meter with the primary key could not be found
	 */
	@Override
	public ReportMeter fetchByPrimaryKey(String id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	@Override
	public Map<Serializable, ReportMeter> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ReportMeter> map = new HashMap<Serializable, ReportMeter>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ReportMeter reportMeter = fetchByPrimaryKey(primaryKey);

			if (reportMeter != null) {
				map.put(primaryKey, reportMeter);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ReportMeterModelImpl.ENTITY_CACHE_ENABLED,
					ReportMeterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ReportMeter)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_REPORTMETER_WHERE_PKS_IN);

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

			for (ReportMeter reportMeter : (List<ReportMeter>)q.list()) {
				map.put(reportMeter.getPrimaryKeyObj(), reportMeter);

				cacheResult(reportMeter);

				uncachedPrimaryKeys.remove(reportMeter.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ReportMeterModelImpl.ENTITY_CACHE_ENABLED,
					ReportMeterImpl.class, primaryKey, nullModel);
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
	 * Returns all the report meters.
	 *
	 * @return the report meters
	 */
	@Override
	public List<ReportMeter> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the report meters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportMeterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of report meters
	 * @param end the upper bound of the range of report meters (not inclusive)
	 * @return the range of report meters
	 */
	@Override
	public List<ReportMeter> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the report meters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportMeterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of report meters
	 * @param end the upper bound of the range of report meters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of report meters
	 */
	@Override
	public List<ReportMeter> findAll(int start, int end,
		OrderByComparator<ReportMeter> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the report meters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportMeterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of report meters
	 * @param end the upper bound of the range of report meters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of report meters
	 */
	@Override
	public List<ReportMeter> findAll(int start, int end,
		OrderByComparator<ReportMeter> orderByComparator,
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

		List<ReportMeter> list = null;

		if (retrieveFromCache) {
			list = (List<ReportMeter>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_REPORTMETER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_REPORTMETER;

				if (pagination) {
					sql = sql.concat(ReportMeterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ReportMeter>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReportMeter>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the report meters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ReportMeter reportMeter : findAll()) {
			remove(reportMeter);
		}
	}

	/**
	 * Returns the number of report meters.
	 *
	 * @return the number of report meters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_REPORTMETER);

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
		return ReportMeterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the report meter persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ReportMeterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_REPORTMETER = "SELECT reportMeter FROM ReportMeter reportMeter";
	private static final String _SQL_SELECT_REPORTMETER_WHERE_PKS_IN = "SELECT reportMeter FROM ReportMeter reportMeter WHERE id IN (";
	private static final String _SQL_COUNT_REPORTMETER = "SELECT COUNT(reportMeter) FROM ReportMeter reportMeter";
	private static final String _ORDER_BY_ENTITY_ALIAS = "reportMeter.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ReportMeter exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ReportMeterPersistenceImpl.class);
}