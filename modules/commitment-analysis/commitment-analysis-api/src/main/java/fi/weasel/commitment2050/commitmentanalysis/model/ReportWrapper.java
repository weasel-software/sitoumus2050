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

package fi.weasel.commitment2050.commitmentanalysis.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Report}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Report
 * @generated
 */
@ProviderType
public class ReportWrapper implements Report, ModelWrapper<Report> {
	public ReportWrapper(Report report) {
		_report = report;
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
	public java.lang.Object clone() {
		return new ReportWrapper((Report)_report.clone());
	}

	@Override
	public int compareTo(Report report) {
		return _report.compareTo(report);
	}

	/**
	* Returns the commitment ID of this report.
	*
	* @return the commitment ID of this report
	*/
	@Override
	public java.lang.String getCommitmentId() {
		return _report.getCommitmentId();
	}

	/**
	* Returns the created by user ID of this report.
	*
	* @return the created by user ID of this report
	*/
	@Override
	public int getCreatedByUserId() {
		return _report.getCreatedByUserId();
	}

	/**
	* Returns the created by user name of this report.
	*
	* @return the created by user name of this report
	*/
	@Override
	public java.lang.String getCreatedByUserName() {
		return _report.getCreatedByUserName();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _report.getExpandoBridge();
	}

	/**
	* Returns the ID of this report.
	*
	* @return the ID of this report
	*/
	@Override
	public java.lang.String getId() {
		return _report.getId();
	}

	/**
	* Returns the operation ID of this report.
	*
	* @return the operation ID of this report
	*/
	@Override
	public java.lang.String getOperationId() {
		return _report.getOperationId();
	}

	/**
	* Returns the organization name of this report.
	*
	* @return the organization name of this report
	*/
	@Override
	public java.lang.String getOrganizationName() {
		return _report.getOrganizationName();
	}

	/**
	* Returns the primary key of this report.
	*
	* @return the primary key of this report
	*/
	@Override
	public java.lang.String getPrimaryKey() {
		return _report.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _report.getPrimaryKeyObj();
	}

	/**
	* Returns the progress of this report.
	*
	* @return the progress of this report
	*/
	@Override
	public java.lang.String getProgress() {
		return _report.getProgress();
	}

	/**
	* Returns the report end date of this report.
	*
	* @return the report end date of this report
	*/
	@Override
	public Date getReportEndDate() {
		return _report.getReportEndDate();
	}

	/**
	* Returns the report ID of this report.
	*
	* @return the report ID of this report
	*/
	@Override
	public java.lang.String getReportId() {
		return _report.getReportId();
	}

	@Override
	public java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.ReportMeter> getReportMeters() {
		return _report.getReportMeters();
	}

	/**
	* Returns the report start date of this report.
	*
	* @return the report start date of this report
	*/
	@Override
	public Date getReportStartDate() {
		return _report.getReportStartDate();
	}

	/**
	* Returns the report status of this report.
	*
	* @return the report status of this report
	*/
	@Override
	public boolean getReportStatus() {
		return _report.getReportStatus();
	}

	/**
	* Returns the report text en of this report.
	*
	* @return the report text en of this report
	*/
	@Override
	public java.lang.String getReportTextEN() {
		return _report.getReportTextEN();
	}

	/**
	* Returns the report text fi of this report.
	*
	* @return the report text fi of this report
	*/
	@Override
	public java.lang.String getReportTextFI() {
		return _report.getReportTextFI();
	}

	/**
	* Returns the report text sv of this report.
	*
	* @return the report text sv of this report
	*/
	@Override
	public java.lang.String getReportTextSV() {
		return _report.getReportTextSV();
	}

	/**
	* Returns the report title en of this report.
	*
	* @return the report title en of this report
	*/
	@Override
	public java.lang.String getReportTitleEN() {
		return _report.getReportTitleEN();
	}

	/**
	* Returns the report title fi of this report.
	*
	* @return the report title fi of this report
	*/
	@Override
	public java.lang.String getReportTitleFI() {
		return _report.getReportTitleFI();
	}

	/**
	* Returns the report title sv of this report.
	*
	* @return the report title sv of this report
	*/
	@Override
	public java.lang.String getReportTitleSV() {
		return _report.getReportTitleSV();
	}

	/**
	* Returns the status of this report.
	*
	* @return the status of this report
	*/
	@Override
	public java.lang.String getStatus() {
		return _report.getStatus();
	}

	/**
	* Returns the version of this report.
	*
	* @return the version of this report
	*/
	@Override
	public double getVersion() {
		return _report.getVersion();
	}

	@Override
	public int hashCode() {
		return _report.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _report.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _report.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _report.isNew();
	}

	/**
	* Returns <code>true</code> if this report is report status.
	*
	* @return <code>true</code> if this report is report status; <code>false</code> otherwise
	*/
	@Override
	public boolean isReportStatus() {
		return _report.isReportStatus();
	}

	@Override
	public void persist() {
		_report.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_report.setCachedModel(cachedModel);
	}

	/**
	* Sets the commitment ID of this report.
	*
	* @param commitmentId the commitment ID of this report
	*/
	@Override
	public void setCommitmentId(java.lang.String commitmentId) {
		_report.setCommitmentId(commitmentId);
	}

	/**
	* Sets the created by user ID of this report.
	*
	* @param createdByUserId the created by user ID of this report
	*/
	@Override
	public void setCreatedByUserId(int createdByUserId) {
		_report.setCreatedByUserId(createdByUserId);
	}

	/**
	* Sets the created by user name of this report.
	*
	* @param createdByUserName the created by user name of this report
	*/
	@Override
	public void setCreatedByUserName(java.lang.String createdByUserName) {
		_report.setCreatedByUserName(createdByUserName);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_report.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_report.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_report.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the ID of this report.
	*
	* @param id the ID of this report
	*/
	@Override
	public void setId(java.lang.String id) {
		_report.setId(id);
	}

	@Override
	public void setNew(boolean n) {
		_report.setNew(n);
	}

	/**
	* Sets the operation ID of this report.
	*
	* @param operationId the operation ID of this report
	*/
	@Override
	public void setOperationId(java.lang.String operationId) {
		_report.setOperationId(operationId);
	}

	/**
	* Sets the organization name of this report.
	*
	* @param organizationName the organization name of this report
	*/
	@Override
	public void setOrganizationName(java.lang.String organizationName) {
		_report.setOrganizationName(organizationName);
	}

	/**
	* Sets the primary key of this report.
	*
	* @param primaryKey the primary key of this report
	*/
	@Override
	public void setPrimaryKey(java.lang.String primaryKey) {
		_report.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_report.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the progress of this report.
	*
	* @param progress the progress of this report
	*/
	@Override
	public void setProgress(java.lang.String progress) {
		_report.setProgress(progress);
	}

	/**
	* Sets the report end date of this report.
	*
	* @param reportEndDate the report end date of this report
	*/
	@Override
	public void setReportEndDate(Date reportEndDate) {
		_report.setReportEndDate(reportEndDate);
	}

	/**
	* Sets the report ID of this report.
	*
	* @param reportId the report ID of this report
	*/
	@Override
	public void setReportId(java.lang.String reportId) {
		_report.setReportId(reportId);
	}

	@Override
	public void setReportMeters(
		java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.ReportMeter> reportMeters) {
		_report.setReportMeters(reportMeters);
	}

	/**
	* Sets the report start date of this report.
	*
	* @param reportStartDate the report start date of this report
	*/
	@Override
	public void setReportStartDate(Date reportStartDate) {
		_report.setReportStartDate(reportStartDate);
	}

	/**
	* Sets whether this report is report status.
	*
	* @param reportStatus the report status of this report
	*/
	@Override
	public void setReportStatus(boolean reportStatus) {
		_report.setReportStatus(reportStatus);
	}

	/**
	* Sets the report text en of this report.
	*
	* @param reportTextEN the report text en of this report
	*/
	@Override
	public void setReportTextEN(java.lang.String reportTextEN) {
		_report.setReportTextEN(reportTextEN);
	}

	/**
	* Sets the report text fi of this report.
	*
	* @param reportTextFI the report text fi of this report
	*/
	@Override
	public void setReportTextFI(java.lang.String reportTextFI) {
		_report.setReportTextFI(reportTextFI);
	}

	/**
	* Sets the report text sv of this report.
	*
	* @param reportTextSV the report text sv of this report
	*/
	@Override
	public void setReportTextSV(java.lang.String reportTextSV) {
		_report.setReportTextSV(reportTextSV);
	}

	/**
	* Sets the report title en of this report.
	*
	* @param reportTitleEN the report title en of this report
	*/
	@Override
	public void setReportTitleEN(java.lang.String reportTitleEN) {
		_report.setReportTitleEN(reportTitleEN);
	}

	/**
	* Sets the report title fi of this report.
	*
	* @param reportTitleFI the report title fi of this report
	*/
	@Override
	public void setReportTitleFI(java.lang.String reportTitleFI) {
		_report.setReportTitleFI(reportTitleFI);
	}

	/**
	* Sets the report title sv of this report.
	*
	* @param reportTitleSV the report title sv of this report
	*/
	@Override
	public void setReportTitleSV(java.lang.String reportTitleSV) {
		_report.setReportTitleSV(reportTitleSV);
	}

	/**
	* Sets the status of this report.
	*
	* @param status the status of this report
	*/
	@Override
	public void setStatus(java.lang.String status) {
		_report.setStatus(status);
	}

	/**
	* Sets the version of this report.
	*
	* @param version the version of this report
	*/
	@Override
	public void setVersion(double version) {
		_report.setVersion(version);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Report> toCacheModel() {
		return _report.toCacheModel();
	}

	@Override
	public Report toEscapedModel() {
		return new ReportWrapper(_report.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _report.toString();
	}

	@Override
	public Report toUnescapedModel() {
		return new ReportWrapper(_report.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _report.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReportWrapper)) {
			return false;
		}

		ReportWrapper reportWrapper = (ReportWrapper)obj;

		if (Objects.equals(_report, reportWrapper._report)) {
			return true;
		}

		return false;
	}

	@Override
	public Report getWrappedModel() {
		return _report;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _report.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _report.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_report.resetOriginalValues();
	}

	private final Report _report;
}