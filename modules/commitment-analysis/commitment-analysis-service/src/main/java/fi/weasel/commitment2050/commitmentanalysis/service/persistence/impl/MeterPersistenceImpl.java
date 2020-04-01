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

import fi.weasel.commitment2050.commitmentanalysis.exception.NoSuchMeterException;
import fi.weasel.commitment2050.commitmentanalysis.model.Meter;
import fi.weasel.commitment2050.commitmentanalysis.model.impl.MeterImpl;
import fi.weasel.commitment2050.commitmentanalysis.model.impl.MeterModelImpl;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.MeterPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the meter service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MeterPersistence
 * @see fi.weasel.commitment2050.commitmentanalysis.service.persistence.MeterUtil
 * @generated
 */
@ProviderType
public class MeterPersistenceImpl extends BasePersistenceImpl<Meter>
	implements MeterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MeterUtil} to access the meter persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MeterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MeterModelImpl.ENTITY_CACHE_ENABLED,
			MeterModelImpl.FINDER_CACHE_ENABLED, MeterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MeterModelImpl.ENTITY_CACHE_ENABLED,
			MeterModelImpl.FINDER_CACHE_ENABLED, MeterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MeterModelImpl.ENTITY_CACHE_ENABLED,
			MeterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public MeterPersistenceImpl() {
		setModelClass(Meter.class);
	}

	/**
	 * Caches the meter in the entity cache if it is enabled.
	 *
	 * @param meter the meter
	 */
	@Override
	public void cacheResult(Meter meter) {
		entityCache.putResult(MeterModelImpl.ENTITY_CACHE_ENABLED,
			MeterImpl.class, meter.getPrimaryKey(), meter);

		meter.resetOriginalValues();
	}

	/**
	 * Caches the meters in the entity cache if it is enabled.
	 *
	 * @param meters the meters
	 */
	@Override
	public void cacheResult(List<Meter> meters) {
		for (Meter meter : meters) {
			if (entityCache.getResult(MeterModelImpl.ENTITY_CACHE_ENABLED,
						MeterImpl.class, meter.getPrimaryKey()) == null) {
				cacheResult(meter);
			}
			else {
				meter.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all meters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MeterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the meter.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Meter meter) {
		entityCache.removeResult(MeterModelImpl.ENTITY_CACHE_ENABLED,
			MeterImpl.class, meter.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Meter> meters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Meter meter : meters) {
			entityCache.removeResult(MeterModelImpl.ENTITY_CACHE_ENABLED,
				MeterImpl.class, meter.getPrimaryKey());
		}
	}

	/**
	 * Creates a new meter with the primary key. Does not add the meter to the database.
	 *
	 * @param id the primary key for the new meter
	 * @return the new meter
	 */
	@Override
	public Meter create(String id) {
		Meter meter = new MeterImpl();

		meter.setNew(true);
		meter.setPrimaryKey(id);

		return meter;
	}

	/**
	 * Removes the meter with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the meter
	 * @return the meter that was removed
	 * @throws NoSuchMeterException if a meter with the primary key could not be found
	 */
	@Override
	public Meter remove(String id) throws NoSuchMeterException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the meter with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the meter
	 * @return the meter that was removed
	 * @throws NoSuchMeterException if a meter with the primary key could not be found
	 */
	@Override
	public Meter remove(Serializable primaryKey) throws NoSuchMeterException {
		Session session = null;

		try {
			session = openSession();

			Meter meter = (Meter)session.get(MeterImpl.class, primaryKey);

			if (meter == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMeterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(meter);
		}
		catch (NoSuchMeterException nsee) {
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
	protected Meter removeImpl(Meter meter) {
		meter = toUnwrappedModel(meter);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(meter)) {
				meter = (Meter)session.get(MeterImpl.class,
						meter.getPrimaryKeyObj());
			}

			if (meter != null) {
				session.delete(meter);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (meter != null) {
			clearCache(meter);
		}

		return meter;
	}

	@Override
	public Meter updateImpl(Meter meter) {
		meter = toUnwrappedModel(meter);

		boolean isNew = meter.isNew();

		Session session = null;

		try {
			session = openSession();

			if (meter.isNew()) {
				session.save(meter);

				meter.setNew(false);
			}
			else {
				meter = (Meter)session.merge(meter);
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

		entityCache.putResult(MeterModelImpl.ENTITY_CACHE_ENABLED,
			MeterImpl.class, meter.getPrimaryKey(), meter, false);

		meter.resetOriginalValues();

		return meter;
	}

	protected Meter toUnwrappedModel(Meter meter) {
		if (meter instanceof MeterImpl) {
			return meter;
		}

		MeterImpl meterImpl = new MeterImpl();

		meterImpl.setNew(meter.isNew());
		meterImpl.setPrimaryKey(meter.getPrimaryKey());

		meterImpl.setId(meter.getId());
		meterImpl.setCommitmentId(meter.getCommitmentId());
		meterImpl.setOperationId(meter.getOperationId());
		meterImpl.setMeterId(meter.getMeterId());
		meterImpl.setMeterCategory(meter.getMeterCategory());
		meterImpl.setMeterChartType(meter.getMeterChartType());
		meterImpl.setMeterValueType(meter.getMeterValueType());
		meterImpl.setMeterTypeFI(meter.getMeterTypeFI());
		meterImpl.setMeterTypeSV(meter.getMeterTypeSV());
		meterImpl.setMeterTypeEN(meter.getMeterTypeEN());
		meterImpl.setStartingLevel(meter.getStartingLevel());
		meterImpl.setTargetLevel(meter.getTargetLevel());

		return meterImpl;
	}

	/**
	 * Returns the meter with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the meter
	 * @return the meter
	 * @throws NoSuchMeterException if a meter with the primary key could not be found
	 */
	@Override
	public Meter findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMeterException {
		Meter meter = fetchByPrimaryKey(primaryKey);

		if (meter == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMeterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return meter;
	}

	/**
	 * Returns the meter with the primary key or throws a {@link NoSuchMeterException} if it could not be found.
	 *
	 * @param id the primary key of the meter
	 * @return the meter
	 * @throws NoSuchMeterException if a meter with the primary key could not be found
	 */
	@Override
	public Meter findByPrimaryKey(String id) throws NoSuchMeterException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the meter with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the meter
	 * @return the meter, or <code>null</code> if a meter with the primary key could not be found
	 */
	@Override
	public Meter fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MeterModelImpl.ENTITY_CACHE_ENABLED,
				MeterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Meter meter = (Meter)serializable;

		if (meter == null) {
			Session session = null;

			try {
				session = openSession();

				meter = (Meter)session.get(MeterImpl.class, primaryKey);

				if (meter != null) {
					cacheResult(meter);
				}
				else {
					entityCache.putResult(MeterModelImpl.ENTITY_CACHE_ENABLED,
						MeterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MeterModelImpl.ENTITY_CACHE_ENABLED,
					MeterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return meter;
	}

	/**
	 * Returns the meter with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the meter
	 * @return the meter, or <code>null</code> if a meter with the primary key could not be found
	 */
	@Override
	public Meter fetchByPrimaryKey(String id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	@Override
	public Map<Serializable, Meter> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Meter> map = new HashMap<Serializable, Meter>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Meter meter = fetchByPrimaryKey(primaryKey);

			if (meter != null) {
				map.put(primaryKey, meter);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MeterModelImpl.ENTITY_CACHE_ENABLED,
					MeterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Meter)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_METER_WHERE_PKS_IN);

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

			for (Meter meter : (List<Meter>)q.list()) {
				map.put(meter.getPrimaryKeyObj(), meter);

				cacheResult(meter);

				uncachedPrimaryKeys.remove(meter.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MeterModelImpl.ENTITY_CACHE_ENABLED,
					MeterImpl.class, primaryKey, nullModel);
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
	 * Returns all the meters.
	 *
	 * @return the meters
	 */
	@Override
	public List<Meter> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the meters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of meters
	 * @param end the upper bound of the range of meters (not inclusive)
	 * @return the range of meters
	 */
	@Override
	public List<Meter> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the meters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of meters
	 * @param end the upper bound of the range of meters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of meters
	 */
	@Override
	public List<Meter> findAll(int start, int end,
		OrderByComparator<Meter> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the meters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MeterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of meters
	 * @param end the upper bound of the range of meters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of meters
	 */
	@Override
	public List<Meter> findAll(int start, int end,
		OrderByComparator<Meter> orderByComparator, boolean retrieveFromCache) {
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

		List<Meter> list = null;

		if (retrieveFromCache) {
			list = (List<Meter>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_METER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_METER;

				if (pagination) {
					sql = sql.concat(MeterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Meter>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Meter>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the meters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Meter meter : findAll()) {
			remove(meter);
		}
	}

	/**
	 * Returns the number of meters.
	 *
	 * @return the number of meters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_METER);

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
		return MeterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the meter persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MeterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_METER = "SELECT meter FROM Meter meter";
	private static final String _SQL_SELECT_METER_WHERE_PKS_IN = "SELECT meter FROM Meter meter WHERE id IN (";
	private static final String _SQL_COUNT_METER = "SELECT COUNT(meter) FROM Meter meter";
	private static final String _ORDER_BY_ENTITY_ALIAS = "meter.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Meter exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(MeterPersistenceImpl.class);
}