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

import fi.weasel.commitment2050.commitmentanalysis.exception.NoSuchReportException;
import fi.weasel.commitment2050.commitmentanalysis.model.Report;
import fi.weasel.commitment2050.commitmentanalysis.model.impl.ReportImpl;
import fi.weasel.commitment2050.commitmentanalysis.model.impl.ReportModelImpl;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.ReportPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the report service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReportPersistence
 * @see fi.weasel.commitment2050.commitmentanalysis.service.persistence.ReportUtil
 * @generated
 */
@ProviderType
public class ReportPersistenceImpl extends BasePersistenceImpl<Report>
	implements ReportPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ReportUtil} to access the report persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ReportImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ReportModelImpl.ENTITY_CACHE_ENABLED,
			ReportModelImpl.FINDER_CACHE_ENABLED, ReportImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ReportModelImpl.ENTITY_CACHE_ENABLED,
			ReportModelImpl.FINDER_CACHE_ENABLED, ReportImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ReportModelImpl.ENTITY_CACHE_ENABLED,
			ReportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ReportPersistenceImpl() {
		setModelClass(Report.class);
	}

	/**
	 * Caches the report in the entity cache if it is enabled.
	 *
	 * @param report the report
	 */
	@Override
	public void cacheResult(Report report) {
		entityCache.putResult(ReportModelImpl.ENTITY_CACHE_ENABLED,
			ReportImpl.class, report.getPrimaryKey(), report);

		report.resetOriginalValues();
	}

	/**
	 * Caches the reports in the entity cache if it is enabled.
	 *
	 * @param reports the reports
	 */
	@Override
	public void cacheResult(List<Report> reports) {
		for (Report report : reports) {
			if (entityCache.getResult(ReportModelImpl.ENTITY_CACHE_ENABLED,
						ReportImpl.class, report.getPrimaryKey()) == null) {
				cacheResult(report);
			}
			else {
				report.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all reports.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ReportImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the report.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Report report) {
		entityCache.removeResult(ReportModelImpl.ENTITY_CACHE_ENABLED,
			ReportImpl.class, report.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Report> reports) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Report report : reports) {
			entityCache.removeResult(ReportModelImpl.ENTITY_CACHE_ENABLED,
				ReportImpl.class, report.getPrimaryKey());
		}
	}

	/**
	 * Creates a new report with the primary key. Does not add the report to the database.
	 *
	 * @param id the primary key for the new report
	 * @return the new report
	 */
	@Override
	public Report create(String id) {
		Report report = new ReportImpl();

		report.setNew(true);
		report.setPrimaryKey(id);

		return report;
	}

	/**
	 * Removes the report with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the report
	 * @return the report that was removed
	 * @throws NoSuchReportException if a report with the primary key could not be found
	 */
	@Override
	public Report remove(String id) throws NoSuchReportException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the report with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the report
	 * @return the report that was removed
	 * @throws NoSuchReportException if a report with the primary key could not be found
	 */
	@Override
	public Report remove(Serializable primaryKey) throws NoSuchReportException {
		Session session = null;

		try {
			session = openSession();

			Report report = (Report)session.get(ReportImpl.class, primaryKey);

			if (report == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchReportException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(report);
		}
		catch (NoSuchReportException nsee) {
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
	protected Report removeImpl(Report report) {
		report = toUnwrappedModel(report);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(report)) {
				report = (Report)session.get(ReportImpl.class,
						report.getPrimaryKeyObj());
			}

			if (report != null) {
				session.delete(report);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (report != null) {
			clearCache(report);
		}

		return report;
	}

	@Override
	public Report updateImpl(Report report) {
		report = toUnwrappedModel(report);

		boolean isNew = report.isNew();

		Session session = null;

		try {
			session = openSession();

			if (report.isNew()) {
				session.save(report);

				report.setNew(false);
			}
			else {
				report = (Report)session.merge(report);
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

		entityCache.putResult(ReportModelImpl.ENTITY_CACHE_ENABLED,
			ReportImpl.class, report.getPrimaryKey(), report, false);

		report.resetOriginalValues();

		return report;
	}

	protected Report toUnwrappedModel(Report report) {
		if (report instanceof ReportImpl) {
			return report;
		}

		ReportImpl reportImpl = new ReportImpl();

		reportImpl.setNew(report.isNew());
		reportImpl.setPrimaryKey(report.getPrimaryKey());

		reportImpl.setId(report.getId());
		reportImpl.setReportId(report.getReportId());
		reportImpl.setOperationId(report.getOperationId());
		reportImpl.setCommitmentId(report.getCommitmentId());
		reportImpl.setCreatedByUserId(report.getCreatedByUserId());
		reportImpl.setCreatedByUserName(report.getCreatedByUserName());
		reportImpl.setOrganizationName(report.getOrganizationName());
		reportImpl.setProgress(report.getProgress());
		reportImpl.setReportStartDate(report.getReportStartDate());
		reportImpl.setReportEndDate(report.getReportEndDate());
		reportImpl.setReportStatus(report.isReportStatus());
		reportImpl.setReportTextFI(report.getReportTextFI());
		reportImpl.setReportTextSV(report.getReportTextSV());
		reportImpl.setReportTextEN(report.getReportTextEN());
		reportImpl.setReportTitleFI(report.getReportTitleFI());
		reportImpl.setReportTitleSV(report.getReportTitleSV());
		reportImpl.setReportTitleEN(report.getReportTitleEN());
		reportImpl.setStatus(report.getStatus());
		reportImpl.setVersion(report.getVersion());

		return reportImpl;
	}

	/**
	 * Returns the report with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the report
	 * @return the report
	 * @throws NoSuchReportException if a report with the primary key could not be found
	 */
	@Override
	public Report findByPrimaryKey(Serializable primaryKey)
		throws NoSuchReportException {
		Report report = fetchByPrimaryKey(primaryKey);

		if (report == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchReportException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return report;
	}

	/**
	 * Returns the report with the primary key or throws a {@link NoSuchReportException} if it could not be found.
	 *
	 * @param id the primary key of the report
	 * @return the report
	 * @throws NoSuchReportException if a report with the primary key could not be found
	 */
	@Override
	public Report findByPrimaryKey(String id) throws NoSuchReportException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the report with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the report
	 * @return the report, or <code>null</code> if a report with the primary key could not be found
	 */
	@Override
	public Report fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ReportModelImpl.ENTITY_CACHE_ENABLED,
				ReportImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Report report = (Report)serializable;

		if (report == null) {
			Session session = null;

			try {
				session = openSession();

				report = (Report)session.get(ReportImpl.class, primaryKey);

				if (report != null) {
					cacheResult(report);
				}
				else {
					entityCache.putResult(ReportModelImpl.ENTITY_CACHE_ENABLED,
						ReportImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ReportModelImpl.ENTITY_CACHE_ENABLED,
					ReportImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return report;
	}

	/**
	 * Returns the report with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the report
	 * @return the report, or <code>null</code> if a report with the primary key could not be found
	 */
	@Override
	public Report fetchByPrimaryKey(String id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	@Override
	public Map<Serializable, Report> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Report> map = new HashMap<Serializable, Report>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Report report = fetchByPrimaryKey(primaryKey);

			if (report != null) {
				map.put(primaryKey, report);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ReportModelImpl.ENTITY_CACHE_ENABLED,
					ReportImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Report)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_REPORT_WHERE_PKS_IN);

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

			for (Report report : (List<Report>)q.list()) {
				map.put(report.getPrimaryKeyObj(), report);

				cacheResult(report);

				uncachedPrimaryKeys.remove(report.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ReportModelImpl.ENTITY_CACHE_ENABLED,
					ReportImpl.class, primaryKey, nullModel);
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
	 * Returns all the reports.
	 *
	 * @return the reports
	 */
	@Override
	public List<Report> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of reports
	 * @param end the upper bound of the range of reports (not inclusive)
	 * @return the range of reports
	 */
	@Override
	public List<Report> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of reports
	 * @param end the upper bound of the range of reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of reports
	 */
	@Override
	public List<Report> findAll(int start, int end,
		OrderByComparator<Report> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of reports
	 * @param end the upper bound of the range of reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of reports
	 */
	@Override
	public List<Report> findAll(int start, int end,
		OrderByComparator<Report> orderByComparator, boolean retrieveFromCache) {
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

		List<Report> list = null;

		if (retrieveFromCache) {
			list = (List<Report>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_REPORT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_REPORT;

				if (pagination) {
					sql = sql.concat(ReportModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Report>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Report>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the reports from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Report report : findAll()) {
			remove(report);
		}
	}

	/**
	 * Returns the number of reports.
	 *
	 * @return the number of reports
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_REPORT);

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
		return ReportModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the report persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ReportImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_REPORT = "SELECT report FROM Report report";
	private static final String _SQL_SELECT_REPORT_WHERE_PKS_IN = "SELECT report FROM Report report WHERE id IN (";
	private static final String _SQL_COUNT_REPORT = "SELECT COUNT(report) FROM Report report";
	private static final String _ORDER_BY_ENTITY_ALIAS = "report.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Report exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ReportPersistenceImpl.class);
}