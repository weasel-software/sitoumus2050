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

package fi.weasel.commitment2050.commitmentanalysis.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import fi.weasel.commitment2050.commitmentanalysis.model.Report;
import fi.weasel.commitment2050.commitmentanalysis.model.ReportModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the Report service. Represents a row in the &quot;CA_Report&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link ReportModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ReportImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReportImpl
 * @see Report
 * @see ReportModel
 * @generated
 */
@ProviderType
public class ReportModelImpl extends BaseModelImpl<Report>
	implements ReportModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a report model instance should use the {@link Report} interface instead.
	 */
	public static final String TABLE_NAME = "CA_Report";
	public static final Object[][] TABLE_COLUMNS = {
			{ "id", Types.VARCHAR },
			{ "reportId", Types.VARCHAR },
			{ "operationId", Types.VARCHAR },
			{ "commitmentId", Types.VARCHAR },
			{ "createdByUserId", Types.INTEGER },
			{ "createdByUserName", Types.VARCHAR },
			{ "organizationName", Types.VARCHAR },
			{ "progress", Types.VARCHAR },
			{ "reportStartDate", Types.TIMESTAMP },
			{ "reportEndDate", Types.TIMESTAMP },
			{ "reportStatus", Types.BOOLEAN },
			{ "reportTextFI", Types.VARCHAR },
			{ "reportTextSV", Types.VARCHAR },
			{ "reportTextEN", Types.VARCHAR },
			{ "reportTitleFI", Types.VARCHAR },
			{ "reportTitleSV", Types.VARCHAR },
			{ "reportTitleEN", Types.VARCHAR },
			{ "status", Types.VARCHAR },
			{ "version", Types.DOUBLE }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("id", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("reportId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("operationId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commitmentId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createdByUserId", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("createdByUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("organizationName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("progress", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("reportStartDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("reportEndDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("reportStatus", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("reportTextFI", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("reportTextSV", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("reportTextEN", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("reportTitleFI", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("reportTitleSV", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("reportTitleEN", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("status", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("version", Types.DOUBLE);
	}

	public static final String TABLE_SQL_CREATE = "create table CA_Report (id VARCHAR(75) not null primary key,reportId VARCHAR(75) null,operationId VARCHAR(75) null,commitmentId VARCHAR(75) null,createdByUserId INTEGER,createdByUserName STRING null,organizationName STRING null,progress VARCHAR(75) null,reportStartDate DATE null,reportEndDate DATE null,reportStatus BOOLEAN,reportTextFI STRING null,reportTextSV STRING null,reportTextEN STRING null,reportTitleFI STRING null,reportTitleSV STRING null,reportTitleEN STRING null,status VARCHAR(75) null,version DOUBLE)";
	public static final String TABLE_SQL_DROP = "drop table CA_Report";
	public static final String ORDER_BY_JPQL = " ORDER BY report.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY CA_Report.id ASC";
	public static final String DATA_SOURCE = "caDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(fi.weasel.commitment2050.commitmentanalysis.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.fi.weasel.commitment2050.commitmentanalysis.model.Report"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(fi.weasel.commitment2050.commitmentanalysis.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.fi.weasel.commitment2050.commitmentanalysis.model.Report"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(fi.weasel.commitment2050.commitmentanalysis.service.util.ServiceProps.get(
				"lock.expiration.time.fi.weasel.commitment2050.commitmentanalysis.model.Report"));

	public ReportModelImpl() {
	}

	@Override
	public String getPrimaryKey() {
		return _id;
	}

	@Override
	public void setPrimaryKey(String primaryKey) {
		setId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _id;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((String)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return Report.class;
	}

	@Override
	public String getModelClassName() {
		return Report.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("reportId", getReportId());
		attributes.put("operationId", getOperationId());
		attributes.put("commitmentId", getCommitmentId());
		attributes.put("createdByUserId", getCreatedByUserId());
		attributes.put("createdByUserName", getCreatedByUserName());
		attributes.put("organizationName", getOrganizationName());
		attributes.put("progress", getProgress());
		attributes.put("reportStartDate", getReportStartDate());
		attributes.put("reportEndDate", getReportEndDate());
		attributes.put("reportStatus", getReportStatus());
		attributes.put("reportTextFI", getReportTextFI());
		attributes.put("reportTextSV", getReportTextSV());
		attributes.put("reportTextEN", getReportTextEN());
		attributes.put("reportTitleFI", getReportTitleFI());
		attributes.put("reportTitleSV", getReportTitleSV());
		attributes.put("reportTitleEN", getReportTitleEN());
		attributes.put("status", getStatus());
		attributes.put("version", getVersion());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String id = (String)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		String reportId = (String)attributes.get("reportId");

		if (reportId != null) {
			setReportId(reportId);
		}

		String operationId = (String)attributes.get("operationId");

		if (operationId != null) {
			setOperationId(operationId);
		}

		String commitmentId = (String)attributes.get("commitmentId");

		if (commitmentId != null) {
			setCommitmentId(commitmentId);
		}

		Integer createdByUserId = (Integer)attributes.get("createdByUserId");

		if (createdByUserId != null) {
			setCreatedByUserId(createdByUserId);
		}

		String createdByUserName = (String)attributes.get("createdByUserName");

		if (createdByUserName != null) {
			setCreatedByUserName(createdByUserName);
		}

		String organizationName = (String)attributes.get("organizationName");

		if (organizationName != null) {
			setOrganizationName(organizationName);
		}

		String progress = (String)attributes.get("progress");

		if (progress != null) {
			setProgress(progress);
		}

		Date reportStartDate = (Date)attributes.get("reportStartDate");

		if (reportStartDate != null) {
			setReportStartDate(reportStartDate);
		}

		Date reportEndDate = (Date)attributes.get("reportEndDate");

		if (reportEndDate != null) {
			setReportEndDate(reportEndDate);
		}

		Boolean reportStatus = (Boolean)attributes.get("reportStatus");

		if (reportStatus != null) {
			setReportStatus(reportStatus);
		}

		String reportTextFI = (String)attributes.get("reportTextFI");

		if (reportTextFI != null) {
			setReportTextFI(reportTextFI);
		}

		String reportTextSV = (String)attributes.get("reportTextSV");

		if (reportTextSV != null) {
			setReportTextSV(reportTextSV);
		}

		String reportTextEN = (String)attributes.get("reportTextEN");

		if (reportTextEN != null) {
			setReportTextEN(reportTextEN);
		}

		String reportTitleFI = (String)attributes.get("reportTitleFI");

		if (reportTitleFI != null) {
			setReportTitleFI(reportTitleFI);
		}

		String reportTitleSV = (String)attributes.get("reportTitleSV");

		if (reportTitleSV != null) {
			setReportTitleSV(reportTitleSV);
		}

		String reportTitleEN = (String)attributes.get("reportTitleEN");

		if (reportTitleEN != null) {
			setReportTitleEN(reportTitleEN);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Double version = (Double)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}
	}

	@Override
	public String getId() {
		if (_id == null) {
			return StringPool.BLANK;
		}
		else {
			return _id;
		}
	}

	@Override
	public void setId(String id) {
		_id = id;
	}

	@Override
	public String getReportId() {
		if (_reportId == null) {
			return StringPool.BLANK;
		}
		else {
			return _reportId;
		}
	}

	@Override
	public void setReportId(String reportId) {
		_reportId = reportId;
	}

	@Override
	public String getOperationId() {
		if (_operationId == null) {
			return StringPool.BLANK;
		}
		else {
			return _operationId;
		}
	}

	@Override
	public void setOperationId(String operationId) {
		_operationId = operationId;
	}

	@Override
	public String getCommitmentId() {
		if (_commitmentId == null) {
			return StringPool.BLANK;
		}
		else {
			return _commitmentId;
		}
	}

	@Override
	public void setCommitmentId(String commitmentId) {
		_commitmentId = commitmentId;
	}

	@Override
	public int getCreatedByUserId() {
		return _createdByUserId;
	}

	@Override
	public void setCreatedByUserId(int createdByUserId) {
		_createdByUserId = createdByUserId;
	}

	@Override
	public String getCreatedByUserName() {
		if (_createdByUserName == null) {
			return StringPool.BLANK;
		}
		else {
			return _createdByUserName;
		}
	}

	@Override
	public void setCreatedByUserName(String createdByUserName) {
		_createdByUserName = createdByUserName;
	}

	@Override
	public String getOrganizationName() {
		if (_organizationName == null) {
			return StringPool.BLANK;
		}
		else {
			return _organizationName;
		}
	}

	@Override
	public void setOrganizationName(String organizationName) {
		_organizationName = organizationName;
	}

	@Override
	public String getProgress() {
		if (_progress == null) {
			return StringPool.BLANK;
		}
		else {
			return _progress;
		}
	}

	@Override
	public void setProgress(String progress) {
		_progress = progress;
	}

	@Override
	public Date getReportStartDate() {
		return _reportStartDate;
	}

	@Override
	public void setReportStartDate(Date reportStartDate) {
		_reportStartDate = reportStartDate;
	}

	@Override
	public Date getReportEndDate() {
		return _reportEndDate;
	}

	@Override
	public void setReportEndDate(Date reportEndDate) {
		_reportEndDate = reportEndDate;
	}

	@Override
	public boolean getReportStatus() {
		return _reportStatus;
	}

	@Override
	public boolean isReportStatus() {
		return _reportStatus;
	}

	@Override
	public void setReportStatus(boolean reportStatus) {
		_reportStatus = reportStatus;
	}

	@Override
	public String getReportTextFI() {
		if (_reportTextFI == null) {
			return StringPool.BLANK;
		}
		else {
			return _reportTextFI;
		}
	}

	@Override
	public void setReportTextFI(String reportTextFI) {
		_reportTextFI = reportTextFI;
	}

	@Override
	public String getReportTextSV() {
		if (_reportTextSV == null) {
			return StringPool.BLANK;
		}
		else {
			return _reportTextSV;
		}
	}

	@Override
	public void setReportTextSV(String reportTextSV) {
		_reportTextSV = reportTextSV;
	}

	@Override
	public String getReportTextEN() {
		if (_reportTextEN == null) {
			return StringPool.BLANK;
		}
		else {
			return _reportTextEN;
		}
	}

	@Override
	public void setReportTextEN(String reportTextEN) {
		_reportTextEN = reportTextEN;
	}

	@Override
	public String getReportTitleFI() {
		if (_reportTitleFI == null) {
			return StringPool.BLANK;
		}
		else {
			return _reportTitleFI;
		}
	}

	@Override
	public void setReportTitleFI(String reportTitleFI) {
		_reportTitleFI = reportTitleFI;
	}

	@Override
	public String getReportTitleSV() {
		if (_reportTitleSV == null) {
			return StringPool.BLANK;
		}
		else {
			return _reportTitleSV;
		}
	}

	@Override
	public void setReportTitleSV(String reportTitleSV) {
		_reportTitleSV = reportTitleSV;
	}

	@Override
	public String getReportTitleEN() {
		if (_reportTitleEN == null) {
			return StringPool.BLANK;
		}
		else {
			return _reportTitleEN;
		}
	}

	@Override
	public void setReportTitleEN(String reportTitleEN) {
		_reportTitleEN = reportTitleEN;
	}

	@Override
	public String getStatus() {
		if (_status == null) {
			return StringPool.BLANK;
		}
		else {
			return _status;
		}
	}

	@Override
	public void setStatus(String status) {
		_status = status;
	}

	@Override
	public double getVersion() {
		return _version;
	}

	@Override
	public void setVersion(double version) {
		_version = version;
	}

	@Override
	public Report toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Report)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ReportImpl reportImpl = new ReportImpl();

		reportImpl.setId(getId());
		reportImpl.setReportId(getReportId());
		reportImpl.setOperationId(getOperationId());
		reportImpl.setCommitmentId(getCommitmentId());
		reportImpl.setCreatedByUserId(getCreatedByUserId());
		reportImpl.setCreatedByUserName(getCreatedByUserName());
		reportImpl.setOrganizationName(getOrganizationName());
		reportImpl.setProgress(getProgress());
		reportImpl.setReportStartDate(getReportStartDate());
		reportImpl.setReportEndDate(getReportEndDate());
		reportImpl.setReportStatus(getReportStatus());
		reportImpl.setReportTextFI(getReportTextFI());
		reportImpl.setReportTextSV(getReportTextSV());
		reportImpl.setReportTextEN(getReportTextEN());
		reportImpl.setReportTitleFI(getReportTitleFI());
		reportImpl.setReportTitleSV(getReportTitleSV());
		reportImpl.setReportTitleEN(getReportTitleEN());
		reportImpl.setStatus(getStatus());
		reportImpl.setVersion(getVersion());

		reportImpl.resetOriginalValues();

		return reportImpl;
	}

	@Override
	public int compareTo(Report report) {
		String primaryKey = report.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Report)) {
			return false;
		}

		Report report = (Report)obj;

		String primaryKey = report.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
	}

	@Override
	public CacheModel<Report> toCacheModel() {
		ReportCacheModel reportCacheModel = new ReportCacheModel();

		reportCacheModel.id = getId();

		String id = reportCacheModel.id;

		if ((id != null) && (id.length() == 0)) {
			reportCacheModel.id = null;
		}

		reportCacheModel.reportId = getReportId();

		String reportId = reportCacheModel.reportId;

		if ((reportId != null) && (reportId.length() == 0)) {
			reportCacheModel.reportId = null;
		}

		reportCacheModel.operationId = getOperationId();

		String operationId = reportCacheModel.operationId;

		if ((operationId != null) && (operationId.length() == 0)) {
			reportCacheModel.operationId = null;
		}

		reportCacheModel.commitmentId = getCommitmentId();

		String commitmentId = reportCacheModel.commitmentId;

		if ((commitmentId != null) && (commitmentId.length() == 0)) {
			reportCacheModel.commitmentId = null;
		}

		reportCacheModel.createdByUserId = getCreatedByUserId();

		reportCacheModel.createdByUserName = getCreatedByUserName();

		String createdByUserName = reportCacheModel.createdByUserName;

		if ((createdByUserName != null) && (createdByUserName.length() == 0)) {
			reportCacheModel.createdByUserName = null;
		}

		reportCacheModel.organizationName = getOrganizationName();

		String organizationName = reportCacheModel.organizationName;

		if ((organizationName != null) && (organizationName.length() == 0)) {
			reportCacheModel.organizationName = null;
		}

		reportCacheModel.progress = getProgress();

		String progress = reportCacheModel.progress;

		if ((progress != null) && (progress.length() == 0)) {
			reportCacheModel.progress = null;
		}

		Date reportStartDate = getReportStartDate();

		if (reportStartDate != null) {
			reportCacheModel.reportStartDate = reportStartDate.getTime();
		}
		else {
			reportCacheModel.reportStartDate = Long.MIN_VALUE;
		}

		Date reportEndDate = getReportEndDate();

		if (reportEndDate != null) {
			reportCacheModel.reportEndDate = reportEndDate.getTime();
		}
		else {
			reportCacheModel.reportEndDate = Long.MIN_VALUE;
		}

		reportCacheModel.reportStatus = getReportStatus();

		reportCacheModel.reportTextFI = getReportTextFI();

		String reportTextFI = reportCacheModel.reportTextFI;

		if ((reportTextFI != null) && (reportTextFI.length() == 0)) {
			reportCacheModel.reportTextFI = null;
		}

		reportCacheModel.reportTextSV = getReportTextSV();

		String reportTextSV = reportCacheModel.reportTextSV;

		if ((reportTextSV != null) && (reportTextSV.length() == 0)) {
			reportCacheModel.reportTextSV = null;
		}

		reportCacheModel.reportTextEN = getReportTextEN();

		String reportTextEN = reportCacheModel.reportTextEN;

		if ((reportTextEN != null) && (reportTextEN.length() == 0)) {
			reportCacheModel.reportTextEN = null;
		}

		reportCacheModel.reportTitleFI = getReportTitleFI();

		String reportTitleFI = reportCacheModel.reportTitleFI;

		if ((reportTitleFI != null) && (reportTitleFI.length() == 0)) {
			reportCacheModel.reportTitleFI = null;
		}

		reportCacheModel.reportTitleSV = getReportTitleSV();

		String reportTitleSV = reportCacheModel.reportTitleSV;

		if ((reportTitleSV != null) && (reportTitleSV.length() == 0)) {
			reportCacheModel.reportTitleSV = null;
		}

		reportCacheModel.reportTitleEN = getReportTitleEN();

		String reportTitleEN = reportCacheModel.reportTitleEN;

		if ((reportTitleEN != null) && (reportTitleEN.length() == 0)) {
			reportCacheModel.reportTitleEN = null;
		}

		reportCacheModel.status = getStatus();

		String status = reportCacheModel.status;

		if ((status != null) && (status.length() == 0)) {
			reportCacheModel.status = null;
		}

		reportCacheModel.version = getVersion();

		return reportCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{id=");
		sb.append(getId());
		sb.append(", reportId=");
		sb.append(getReportId());
		sb.append(", operationId=");
		sb.append(getOperationId());
		sb.append(", commitmentId=");
		sb.append(getCommitmentId());
		sb.append(", createdByUserId=");
		sb.append(getCreatedByUserId());
		sb.append(", createdByUserName=");
		sb.append(getCreatedByUserName());
		sb.append(", organizationName=");
		sb.append(getOrganizationName());
		sb.append(", progress=");
		sb.append(getProgress());
		sb.append(", reportStartDate=");
		sb.append(getReportStartDate());
		sb.append(", reportEndDate=");
		sb.append(getReportEndDate());
		sb.append(", reportStatus=");
		sb.append(getReportStatus());
		sb.append(", reportTextFI=");
		sb.append(getReportTextFI());
		sb.append(", reportTextSV=");
		sb.append(getReportTextSV());
		sb.append(", reportTextEN=");
		sb.append(getReportTextEN());
		sb.append(", reportTitleFI=");
		sb.append(getReportTitleFI());
		sb.append(", reportTitleSV=");
		sb.append(getReportTitleSV());
		sb.append(", reportTitleEN=");
		sb.append(getReportTitleEN());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", version=");
		sb.append(getVersion());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(61);

		sb.append("<model><model-name>");
		sb.append("fi.weasel.commitment2050.commitmentanalysis.model.Report");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>id</column-name><column-value><![CDATA[");
		sb.append(getId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reportId</column-name><column-value><![CDATA[");
		sb.append(getReportId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>operationId</column-name><column-value><![CDATA[");
		sb.append(getOperationId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>commitmentId</column-name><column-value><![CDATA[");
		sb.append(getCommitmentId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createdByUserId</column-name><column-value><![CDATA[");
		sb.append(getCreatedByUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createdByUserName</column-name><column-value><![CDATA[");
		sb.append(getCreatedByUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>organizationName</column-name><column-value><![CDATA[");
		sb.append(getOrganizationName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>progress</column-name><column-value><![CDATA[");
		sb.append(getProgress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reportStartDate</column-name><column-value><![CDATA[");
		sb.append(getReportStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reportEndDate</column-name><column-value><![CDATA[");
		sb.append(getReportEndDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reportStatus</column-name><column-value><![CDATA[");
		sb.append(getReportStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reportTextFI</column-name><column-value><![CDATA[");
		sb.append(getReportTextFI());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reportTextSV</column-name><column-value><![CDATA[");
		sb.append(getReportTextSV());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reportTextEN</column-name><column-value><![CDATA[");
		sb.append(getReportTextEN());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reportTitleFI</column-name><column-value><![CDATA[");
		sb.append(getReportTitleFI());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reportTitleSV</column-name><column-value><![CDATA[");
		sb.append(getReportTitleSV());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reportTitleEN</column-name><column-value><![CDATA[");
		sb.append(getReportTitleEN());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>version</column-name><column-value><![CDATA[");
		sb.append(getVersion());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Report.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Report.class
		};
	private String _id;
	private String _reportId;
	private String _operationId;
	private String _commitmentId;
	private int _createdByUserId;
	private String _createdByUserName;
	private String _organizationName;
	private String _progress;
	private Date _reportStartDate;
	private Date _reportEndDate;
	private boolean _reportStatus;
	private String _reportTextFI;
	private String _reportTextSV;
	private String _reportTextEN;
	private String _reportTitleFI;
	private String _reportTitleSV;
	private String _reportTitleEN;
	private String _status;
	private double _version;
	private Report _escapedModel;
}