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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import fi.weasel.commitment2050.commitmentanalysis.exception.NoSuchCommitmentException;
import fi.weasel.commitment2050.commitmentanalysis.model.Commitment;
import fi.weasel.commitment2050.commitmentanalysis.model.impl.CommitmentImpl;
import fi.weasel.commitment2050.commitmentanalysis.model.impl.CommitmentModelImpl;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.CommitmentPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the commitment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CommitmentPersistence
 * @see fi.weasel.commitment2050.commitmentanalysis.service.persistence.CommitmentUtil
 * @generated
 */
@ProviderType
public class CommitmentPersistenceImpl extends BasePersistenceImpl<Commitment>
	implements CommitmentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommitmentUtil} to access the commitment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommitmentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommitmentModelImpl.ENTITY_CACHE_ENABLED,
			CommitmentModelImpl.FINDER_CACHE_ENABLED, CommitmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommitmentModelImpl.ENTITY_CACHE_ENABLED,
			CommitmentModelImpl.FINDER_CACHE_ENABLED, CommitmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommitmentModelImpl.ENTITY_CACHE_ENABLED,
			CommitmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CommitmentPersistenceImpl() {
		setModelClass(Commitment.class);
	}

	/**
	 * Caches the commitment in the entity cache if it is enabled.
	 *
	 * @param commitment the commitment
	 */
	@Override
	public void cacheResult(Commitment commitment) {
		entityCache.putResult(CommitmentModelImpl.ENTITY_CACHE_ENABLED,
			CommitmentImpl.class, commitment.getPrimaryKey(), commitment);

		commitment.resetOriginalValues();
	}

	/**
	 * Caches the commitments in the entity cache if it is enabled.
	 *
	 * @param commitments the commitments
	 */
	@Override
	public void cacheResult(List<Commitment> commitments) {
		for (Commitment commitment : commitments) {
			if (entityCache.getResult(
						CommitmentModelImpl.ENTITY_CACHE_ENABLED,
						CommitmentImpl.class, commitment.getPrimaryKey()) == null) {
				cacheResult(commitment);
			}
			else {
				commitment.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all commitments.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommitmentImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the commitment.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Commitment commitment) {
		entityCache.removeResult(CommitmentModelImpl.ENTITY_CACHE_ENABLED,
			CommitmentImpl.class, commitment.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Commitment> commitments) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Commitment commitment : commitments) {
			entityCache.removeResult(CommitmentModelImpl.ENTITY_CACHE_ENABLED,
				CommitmentImpl.class, commitment.getPrimaryKey());
		}
	}

	/**
	 * Creates a new commitment with the primary key. Does not add the commitment to the database.
	 *
	 * @param commitmentId the primary key for the new commitment
	 * @return the new commitment
	 */
	@Override
	public Commitment create(String commitmentId) {
		Commitment commitment = new CommitmentImpl();

		commitment.setNew(true);
		commitment.setPrimaryKey(commitmentId);

		commitment.setCompanyId(companyProvider.getCompanyId());

		return commitment;
	}

	/**
	 * Removes the commitment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commitmentId the primary key of the commitment
	 * @return the commitment that was removed
	 * @throws NoSuchCommitmentException if a commitment with the primary key could not be found
	 */
	@Override
	public Commitment remove(String commitmentId)
		throws NoSuchCommitmentException {
		return remove((Serializable)commitmentId);
	}

	/**
	 * Removes the commitment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commitment
	 * @return the commitment that was removed
	 * @throws NoSuchCommitmentException if a commitment with the primary key could not be found
	 */
	@Override
	public Commitment remove(Serializable primaryKey)
		throws NoSuchCommitmentException {
		Session session = null;

		try {
			session = openSession();

			Commitment commitment = (Commitment)session.get(CommitmentImpl.class,
					primaryKey);

			if (commitment == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCommitmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commitment);
		}
		catch (NoSuchCommitmentException nsee) {
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
	protected Commitment removeImpl(Commitment commitment) {
		commitment = toUnwrappedModel(commitment);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commitment)) {
				commitment = (Commitment)session.get(CommitmentImpl.class,
						commitment.getPrimaryKeyObj());
			}

			if (commitment != null) {
				session.delete(commitment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commitment != null) {
			clearCache(commitment);
		}

		return commitment;
	}

	@Override
	public Commitment updateImpl(Commitment commitment) {
		commitment = toUnwrappedModel(commitment);

		boolean isNew = commitment.isNew();

		CommitmentModelImpl commitmentModelImpl = (CommitmentModelImpl)commitment;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commitment.getCreateDate() == null)) {
			if (serviceContext == null) {
				commitment.setCreateDate(now);
			}
			else {
				commitment.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!commitmentModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commitment.setModifiedDate(now);
			}
			else {
				commitment.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (commitment.isNew()) {
				session.save(commitment);

				commitment.setNew(false);
			}
			else {
				commitment = (Commitment)session.merge(commitment);
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

		entityCache.putResult(CommitmentModelImpl.ENTITY_CACHE_ENABLED,
			CommitmentImpl.class, commitment.getPrimaryKey(), commitment, false);

		commitment.resetOriginalValues();

		return commitment;
	}

	protected Commitment toUnwrappedModel(Commitment commitment) {
		if (commitment instanceof CommitmentImpl) {
			return commitment;
		}

		CommitmentImpl commitmentImpl = new CommitmentImpl();

		commitmentImpl.setNew(commitment.isNew());
		commitmentImpl.setPrimaryKey(commitment.getPrimaryKey());

		commitmentImpl.setCommitmentId(commitment.getCommitmentId());
		commitmentImpl.setGroupId(commitment.getGroupId());
		commitmentImpl.setCompanyId(commitment.getCompanyId());
		commitmentImpl.setOrganizationId(commitment.getOrganizationId());
		commitmentImpl.setUserId(commitment.getUserId());
		commitmentImpl.setUserName(commitment.getUserName());
		commitmentImpl.setCreateDate(commitment.getCreateDate());
		commitmentImpl.setModifiedDate(commitment.getModifiedDate());
		commitmentImpl.setTitleFI(commitment.getTitleFI());
		commitmentImpl.setTitleEN(commitment.getTitleEN());
		commitmentImpl.setTitleSV(commitment.getTitleSV());
		commitmentImpl.setStartDate(commitment.getStartDate());
		commitmentImpl.setEndDate(commitment.getEndDate());
		commitmentImpl.setUpdated(commitment.getUpdated());
		commitmentImpl.setCreated(commitment.getCreated());
		commitmentImpl.setInnovationFI(commitment.getInnovationFI());
		commitmentImpl.setInnovationEN(commitment.getInnovationEN());
		commitmentImpl.setInnovationSV(commitment.getInnovationSV());
		commitmentImpl.setBackgroundInformationFI(commitment.getBackgroundInformationFI());
		commitmentImpl.setBackgroundInformationEN(commitment.getBackgroundInformationEN());
		commitmentImpl.setBackgroundInformationSV(commitment.getBackgroundInformationSV());
		commitmentImpl.setShortDescriptionFI(commitment.getShortDescriptionFI());
		commitmentImpl.setShortDescriptionEN(commitment.getShortDescriptionEN());
		commitmentImpl.setShortDescriptionSV(commitment.getShortDescriptionSV());
		commitmentImpl.setAddress(commitment.getAddress());
		commitmentImpl.setLongitude(commitment.getLongitude());
		commitmentImpl.setLatitude(commitment.getLatitude());
		commitmentImpl.setCommitmentType(commitment.getCommitmentType());
		commitmentImpl.setStatus(commitment.getStatus());
		commitmentImpl.setLikes(commitment.getLikes());
		commitmentImpl.setJoined(commitment.getJoined());

		return commitmentImpl;
	}

	/**
	 * Returns the commitment with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the commitment
	 * @return the commitment
	 * @throws NoSuchCommitmentException if a commitment with the primary key could not be found
	 */
	@Override
	public Commitment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCommitmentException {
		Commitment commitment = fetchByPrimaryKey(primaryKey);

		if (commitment == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCommitmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commitment;
	}

	/**
	 * Returns the commitment with the primary key or throws a {@link NoSuchCommitmentException} if it could not be found.
	 *
	 * @param commitmentId the primary key of the commitment
	 * @return the commitment
	 * @throws NoSuchCommitmentException if a commitment with the primary key could not be found
	 */
	@Override
	public Commitment findByPrimaryKey(String commitmentId)
		throws NoSuchCommitmentException {
		return findByPrimaryKey((Serializable)commitmentId);
	}

	/**
	 * Returns the commitment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commitment
	 * @return the commitment, or <code>null</code> if a commitment with the primary key could not be found
	 */
	@Override
	public Commitment fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CommitmentModelImpl.ENTITY_CACHE_ENABLED,
				CommitmentImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Commitment commitment = (Commitment)serializable;

		if (commitment == null) {
			Session session = null;

			try {
				session = openSession();

				commitment = (Commitment)session.get(CommitmentImpl.class,
						primaryKey);

				if (commitment != null) {
					cacheResult(commitment);
				}
				else {
					entityCache.putResult(CommitmentModelImpl.ENTITY_CACHE_ENABLED,
						CommitmentImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CommitmentModelImpl.ENTITY_CACHE_ENABLED,
					CommitmentImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commitment;
	}

	/**
	 * Returns the commitment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commitmentId the primary key of the commitment
	 * @return the commitment, or <code>null</code> if a commitment with the primary key could not be found
	 */
	@Override
	public Commitment fetchByPrimaryKey(String commitmentId) {
		return fetchByPrimaryKey((Serializable)commitmentId);
	}

	@Override
	public Map<Serializable, Commitment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Commitment> map = new HashMap<Serializable, Commitment>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Commitment commitment = fetchByPrimaryKey(primaryKey);

			if (commitment != null) {
				map.put(primaryKey, commitment);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CommitmentModelImpl.ENTITY_CACHE_ENABLED,
					CommitmentImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Commitment)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMMITMENT_WHERE_PKS_IN);

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

			for (Commitment commitment : (List<Commitment>)q.list()) {
				map.put(commitment.getPrimaryKeyObj(), commitment);

				cacheResult(commitment);

				uncachedPrimaryKeys.remove(commitment.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CommitmentModelImpl.ENTITY_CACHE_ENABLED,
					CommitmentImpl.class, primaryKey, nullModel);
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
	 * Returns all the commitments.
	 *
	 * @return the commitments
	 */
	@Override
	public List<Commitment> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<Commitment> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<Commitment> findAll(int start, int end,
		OrderByComparator<Commitment> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<Commitment> findAll(int start, int end,
		OrderByComparator<Commitment> orderByComparator,
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

		List<Commitment> list = null;

		if (retrieveFromCache) {
			list = (List<Commitment>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMMITMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMITMENT;

				if (pagination) {
					sql = sql.concat(CommitmentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Commitment>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Commitment>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the commitments from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Commitment commitment : findAll()) {
			remove(commitment);
		}
	}

	/**
	 * Returns the number of commitments.
	 *
	 * @return the number of commitments
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMITMENT);

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
		return CommitmentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commitment persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CommitmentImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_COMMITMENT = "SELECT commitment FROM Commitment commitment";
	private static final String _SQL_SELECT_COMMITMENT_WHERE_PKS_IN = "SELECT commitment FROM Commitment commitment WHERE commitmentId IN (";
	private static final String _SQL_COUNT_COMMITMENT = "SELECT COUNT(commitment) FROM Commitment commitment";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commitment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Commitment exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CommitmentPersistenceImpl.class);
}