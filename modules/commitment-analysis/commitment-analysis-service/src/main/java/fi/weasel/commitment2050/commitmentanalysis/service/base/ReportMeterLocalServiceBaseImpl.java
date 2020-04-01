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

package fi.weasel.commitment2050.commitmentanalysis.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import fi.weasel.commitment2050.commitmentanalysis.model.ReportMeter;
import fi.weasel.commitment2050.commitmentanalysis.service.ReportMeterLocalService;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.CommitmentAnalysisResultFinder;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.CommitmentAnalysisResultPersistence;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.CommitmentFinder;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.CommitmentPersistence;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.DoneOperationPersistence;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.MeterPersistence;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.OperationPersistence;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.ReportMeterPersistence;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.ReportPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the report meter local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link fi.weasel.commitment2050.commitmentanalysis.service.impl.ReportMeterLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see fi.weasel.commitment2050.commitmentanalysis.service.impl.ReportMeterLocalServiceImpl
 * @see fi.weasel.commitment2050.commitmentanalysis.service.ReportMeterLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class ReportMeterLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements ReportMeterLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link fi.weasel.commitment2050.commitmentanalysis.service.ReportMeterLocalServiceUtil} to access the report meter local service.
	 */

	/**
	 * Adds the report meter to the database. Also notifies the appropriate model listeners.
	 *
	 * @param reportMeter the report meter
	 * @return the report meter that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ReportMeter addReportMeter(ReportMeter reportMeter) {
		reportMeter.setNew(true);

		return reportMeterPersistence.update(reportMeter);
	}

	/**
	 * Creates a new report meter with the primary key. Does not add the report meter to the database.
	 *
	 * @param id the primary key for the new report meter
	 * @return the new report meter
	 */
	@Override
	public ReportMeter createReportMeter(String id) {
		return reportMeterPersistence.create(id);
	}

	/**
	 * Deletes the report meter with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the report meter
	 * @return the report meter that was removed
	 * @throws PortalException if a report meter with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ReportMeter deleteReportMeter(String id) throws PortalException {
		return reportMeterPersistence.remove(id);
	}

	/**
	 * Deletes the report meter from the database. Also notifies the appropriate model listeners.
	 *
	 * @param reportMeter the report meter
	 * @return the report meter that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ReportMeter deleteReportMeter(ReportMeter reportMeter) {
		return reportMeterPersistence.remove(reportMeter);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(ReportMeter.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return reportMeterPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.ReportMeterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return reportMeterPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.ReportMeterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return reportMeterPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return reportMeterPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return reportMeterPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public ReportMeter fetchReportMeter(String id) {
		return reportMeterPersistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the report meter with the primary key.
	 *
	 * @param id the primary key of the report meter
	 * @return the report meter
	 * @throws PortalException if a report meter with the primary key could not be found
	 */
	@Override
	public ReportMeter getReportMeter(String id) throws PortalException {
		return reportMeterPersistence.findByPrimaryKey(id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return reportMeterLocalService.deleteReportMeter((ReportMeter)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return reportMeterPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the report meters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.ReportMeterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of report meters
	 * @param end the upper bound of the range of report meters (not inclusive)
	 * @return the range of report meters
	 */
	@Override
	public List<ReportMeter> getReportMeters(int start, int end) {
		return reportMeterPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of report meters.
	 *
	 * @return the number of report meters
	 */
	@Override
	public int getReportMetersCount() {
		return reportMeterPersistence.countAll();
	}

	/**
	 * Updates the report meter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param reportMeter the report meter
	 * @return the report meter that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ReportMeter updateReportMeter(ReportMeter reportMeter) {
		return reportMeterPersistence.update(reportMeter);
	}

	/**
	 * Returns the commitment local service.
	 *
	 * @return the commitment local service
	 */
	public fi.weasel.commitment2050.commitmentanalysis.service.CommitmentLocalService getCommitmentLocalService() {
		return commitmentLocalService;
	}

	/**
	 * Sets the commitment local service.
	 *
	 * @param commitmentLocalService the commitment local service
	 */
	public void setCommitmentLocalService(
		fi.weasel.commitment2050.commitmentanalysis.service.CommitmentLocalService commitmentLocalService) {
		this.commitmentLocalService = commitmentLocalService;
	}

	/**
	 * Returns the commitment persistence.
	 *
	 * @return the commitment persistence
	 */
	public CommitmentPersistence getCommitmentPersistence() {
		return commitmentPersistence;
	}

	/**
	 * Sets the commitment persistence.
	 *
	 * @param commitmentPersistence the commitment persistence
	 */
	public void setCommitmentPersistence(
		CommitmentPersistence commitmentPersistence) {
		this.commitmentPersistence = commitmentPersistence;
	}

	/**
	 * Returns the commitment finder.
	 *
	 * @return the commitment finder
	 */
	public CommitmentFinder getCommitmentFinder() {
		return commitmentFinder;
	}

	/**
	 * Sets the commitment finder.
	 *
	 * @param commitmentFinder the commitment finder
	 */
	public void setCommitmentFinder(CommitmentFinder commitmentFinder) {
		this.commitmentFinder = commitmentFinder;
	}

	/**
	 * Returns the commitment analysis result local service.
	 *
	 * @return the commitment analysis result local service
	 */
	public fi.weasel.commitment2050.commitmentanalysis.service.CommitmentAnalysisResultLocalService getCommitmentAnalysisResultLocalService() {
		return commitmentAnalysisResultLocalService;
	}

	/**
	 * Sets the commitment analysis result local service.
	 *
	 * @param commitmentAnalysisResultLocalService the commitment analysis result local service
	 */
	public void setCommitmentAnalysisResultLocalService(
		fi.weasel.commitment2050.commitmentanalysis.service.CommitmentAnalysisResultLocalService commitmentAnalysisResultLocalService) {
		this.commitmentAnalysisResultLocalService = commitmentAnalysisResultLocalService;
	}

	/**
	 * Returns the commitment analysis result persistence.
	 *
	 * @return the commitment analysis result persistence
	 */
	public CommitmentAnalysisResultPersistence getCommitmentAnalysisResultPersistence() {
		return commitmentAnalysisResultPersistence;
	}

	/**
	 * Sets the commitment analysis result persistence.
	 *
	 * @param commitmentAnalysisResultPersistence the commitment analysis result persistence
	 */
	public void setCommitmentAnalysisResultPersistence(
		CommitmentAnalysisResultPersistence commitmentAnalysisResultPersistence) {
		this.commitmentAnalysisResultPersistence = commitmentAnalysisResultPersistence;
	}

	/**
	 * Returns the commitment analysis result finder.
	 *
	 * @return the commitment analysis result finder
	 */
	public CommitmentAnalysisResultFinder getCommitmentAnalysisResultFinder() {
		return commitmentAnalysisResultFinder;
	}

	/**
	 * Sets the commitment analysis result finder.
	 *
	 * @param commitmentAnalysisResultFinder the commitment analysis result finder
	 */
	public void setCommitmentAnalysisResultFinder(
		CommitmentAnalysisResultFinder commitmentAnalysisResultFinder) {
		this.commitmentAnalysisResultFinder = commitmentAnalysisResultFinder;
	}

	/**
	 * Returns the done operation local service.
	 *
	 * @return the done operation local service
	 */
	public fi.weasel.commitment2050.commitmentanalysis.service.DoneOperationLocalService getDoneOperationLocalService() {
		return doneOperationLocalService;
	}

	/**
	 * Sets the done operation local service.
	 *
	 * @param doneOperationLocalService the done operation local service
	 */
	public void setDoneOperationLocalService(
		fi.weasel.commitment2050.commitmentanalysis.service.DoneOperationLocalService doneOperationLocalService) {
		this.doneOperationLocalService = doneOperationLocalService;
	}

	/**
	 * Returns the done operation persistence.
	 *
	 * @return the done operation persistence
	 */
	public DoneOperationPersistence getDoneOperationPersistence() {
		return doneOperationPersistence;
	}

	/**
	 * Sets the done operation persistence.
	 *
	 * @param doneOperationPersistence the done operation persistence
	 */
	public void setDoneOperationPersistence(
		DoneOperationPersistence doneOperationPersistence) {
		this.doneOperationPersistence = doneOperationPersistence;
	}

	/**
	 * Returns the meter local service.
	 *
	 * @return the meter local service
	 */
	public fi.weasel.commitment2050.commitmentanalysis.service.MeterLocalService getMeterLocalService() {
		return meterLocalService;
	}

	/**
	 * Sets the meter local service.
	 *
	 * @param meterLocalService the meter local service
	 */
	public void setMeterLocalService(
		fi.weasel.commitment2050.commitmentanalysis.service.MeterLocalService meterLocalService) {
		this.meterLocalService = meterLocalService;
	}

	/**
	 * Returns the meter persistence.
	 *
	 * @return the meter persistence
	 */
	public MeterPersistence getMeterPersistence() {
		return meterPersistence;
	}

	/**
	 * Sets the meter persistence.
	 *
	 * @param meterPersistence the meter persistence
	 */
	public void setMeterPersistence(MeterPersistence meterPersistence) {
		this.meterPersistence = meterPersistence;
	}

	/**
	 * Returns the operation local service.
	 *
	 * @return the operation local service
	 */
	public fi.weasel.commitment2050.commitmentanalysis.service.OperationLocalService getOperationLocalService() {
		return operationLocalService;
	}

	/**
	 * Sets the operation local service.
	 *
	 * @param operationLocalService the operation local service
	 */
	public void setOperationLocalService(
		fi.weasel.commitment2050.commitmentanalysis.service.OperationLocalService operationLocalService) {
		this.operationLocalService = operationLocalService;
	}

	/**
	 * Returns the operation persistence.
	 *
	 * @return the operation persistence
	 */
	public OperationPersistence getOperationPersistence() {
		return operationPersistence;
	}

	/**
	 * Sets the operation persistence.
	 *
	 * @param operationPersistence the operation persistence
	 */
	public void setOperationPersistence(
		OperationPersistence operationPersistence) {
		this.operationPersistence = operationPersistence;
	}

	/**
	 * Returns the report local service.
	 *
	 * @return the report local service
	 */
	public fi.weasel.commitment2050.commitmentanalysis.service.ReportLocalService getReportLocalService() {
		return reportLocalService;
	}

	/**
	 * Sets the report local service.
	 *
	 * @param reportLocalService the report local service
	 */
	public void setReportLocalService(
		fi.weasel.commitment2050.commitmentanalysis.service.ReportLocalService reportLocalService) {
		this.reportLocalService = reportLocalService;
	}

	/**
	 * Returns the report persistence.
	 *
	 * @return the report persistence
	 */
	public ReportPersistence getReportPersistence() {
		return reportPersistence;
	}

	/**
	 * Sets the report persistence.
	 *
	 * @param reportPersistence the report persistence
	 */
	public void setReportPersistence(ReportPersistence reportPersistence) {
		this.reportPersistence = reportPersistence;
	}

	/**
	 * Returns the report meter local service.
	 *
	 * @return the report meter local service
	 */
	public ReportMeterLocalService getReportMeterLocalService() {
		return reportMeterLocalService;
	}

	/**
	 * Sets the report meter local service.
	 *
	 * @param reportMeterLocalService the report meter local service
	 */
	public void setReportMeterLocalService(
		ReportMeterLocalService reportMeterLocalService) {
		this.reportMeterLocalService = reportMeterLocalService;
	}

	/**
	 * Returns the report meter persistence.
	 *
	 * @return the report meter persistence
	 */
	public ReportMeterPersistence getReportMeterPersistence() {
		return reportMeterPersistence;
	}

	/**
	 * Sets the report meter persistence.
	 *
	 * @param reportMeterPersistence the report meter persistence
	 */
	public void setReportMeterPersistence(
		ReportMeterPersistence reportMeterPersistence) {
		this.reportMeterPersistence = reportMeterPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("fi.weasel.commitment2050.commitmentanalysis.model.ReportMeter",
			reportMeterLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"fi.weasel.commitment2050.commitmentanalysis.model.ReportMeter");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ReportMeterLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return ReportMeter.class;
	}

	protected String getModelClassName() {
		return ReportMeter.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = reportMeterPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = fi.weasel.commitment2050.commitmentanalysis.service.CommitmentLocalService.class)
	protected fi.weasel.commitment2050.commitmentanalysis.service.CommitmentLocalService commitmentLocalService;
	@BeanReference(type = CommitmentPersistence.class)
	protected CommitmentPersistence commitmentPersistence;
	@BeanReference(type = CommitmentFinder.class)
	protected CommitmentFinder commitmentFinder;
	@BeanReference(type = fi.weasel.commitment2050.commitmentanalysis.service.CommitmentAnalysisResultLocalService.class)
	protected fi.weasel.commitment2050.commitmentanalysis.service.CommitmentAnalysisResultLocalService commitmentAnalysisResultLocalService;
	@BeanReference(type = CommitmentAnalysisResultPersistence.class)
	protected CommitmentAnalysisResultPersistence commitmentAnalysisResultPersistence;
	@BeanReference(type = CommitmentAnalysisResultFinder.class)
	protected CommitmentAnalysisResultFinder commitmentAnalysisResultFinder;
	@BeanReference(type = fi.weasel.commitment2050.commitmentanalysis.service.DoneOperationLocalService.class)
	protected fi.weasel.commitment2050.commitmentanalysis.service.DoneOperationLocalService doneOperationLocalService;
	@BeanReference(type = DoneOperationPersistence.class)
	protected DoneOperationPersistence doneOperationPersistence;
	@BeanReference(type = fi.weasel.commitment2050.commitmentanalysis.service.MeterLocalService.class)
	protected fi.weasel.commitment2050.commitmentanalysis.service.MeterLocalService meterLocalService;
	@BeanReference(type = MeterPersistence.class)
	protected MeterPersistence meterPersistence;
	@BeanReference(type = fi.weasel.commitment2050.commitmentanalysis.service.OperationLocalService.class)
	protected fi.weasel.commitment2050.commitmentanalysis.service.OperationLocalService operationLocalService;
	@BeanReference(type = OperationPersistence.class)
	protected OperationPersistence operationPersistence;
	@BeanReference(type = fi.weasel.commitment2050.commitmentanalysis.service.ReportLocalService.class)
	protected fi.weasel.commitment2050.commitmentanalysis.service.ReportLocalService reportLocalService;
	@BeanReference(type = ReportPersistence.class)
	protected ReportPersistence reportPersistence;
	@BeanReference(type = ReportMeterLocalService.class)
	protected ReportMeterLocalService reportMeterLocalService;
	@BeanReference(type = ReportMeterPersistence.class)
	protected ReportMeterPersistence reportMeterPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}