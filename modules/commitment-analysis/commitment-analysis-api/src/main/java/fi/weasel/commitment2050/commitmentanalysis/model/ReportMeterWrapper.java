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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ReportMeter}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReportMeter
 * @generated
 */
@ProviderType
public class ReportMeterWrapper implements ReportMeter,
	ModelWrapper<ReportMeter> {
	public ReportMeterWrapper(ReportMeter reportMeter) {
		_reportMeter = reportMeter;
	}

	@Override
	public Class<?> getModelClass() {
		return ReportMeter.class;
	}

	@Override
	public String getModelClassName() {
		return ReportMeter.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("commitmentId", getCommitmentId());
		attributes.put("operationId", getOperationId());
		attributes.put("reportId", getReportId());
		attributes.put("meterId", getMeterId());
		attributes.put("meterCategory", getMeterCategory());
		attributes.put("meterChartType", getMeterChartType());
		attributes.put("meterValueType", getMeterValueType());
		attributes.put("meterTypeFI", getMeterTypeFI());
		attributes.put("meterTypeSV", getMeterTypeSV());
		attributes.put("meterTypeEN", getMeterTypeEN());
		attributes.put("currentLevel", getCurrentLevel());
		attributes.put("startingLevel", getStartingLevel());
		attributes.put("targetLevel", getTargetLevel());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String id = (String)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		String commitmentId = (String)attributes.get("commitmentId");

		if (commitmentId != null) {
			setCommitmentId(commitmentId);
		}

		String operationId = (String)attributes.get("operationId");

		if (operationId != null) {
			setOperationId(operationId);
		}

		String reportId = (String)attributes.get("reportId");

		if (reportId != null) {
			setReportId(reportId);
		}

		String meterId = (String)attributes.get("meterId");

		if (meterId != null) {
			setMeterId(meterId);
		}

		String meterCategory = (String)attributes.get("meterCategory");

		if (meterCategory != null) {
			setMeterCategory(meterCategory);
		}

		String meterChartType = (String)attributes.get("meterChartType");

		if (meterChartType != null) {
			setMeterChartType(meterChartType);
		}

		String meterValueType = (String)attributes.get("meterValueType");

		if (meterValueType != null) {
			setMeterValueType(meterValueType);
		}

		String meterTypeFI = (String)attributes.get("meterTypeFI");

		if (meterTypeFI != null) {
			setMeterTypeFI(meterTypeFI);
		}

		String meterTypeSV = (String)attributes.get("meterTypeSV");

		if (meterTypeSV != null) {
			setMeterTypeSV(meterTypeSV);
		}

		String meterTypeEN = (String)attributes.get("meterTypeEN");

		if (meterTypeEN != null) {
			setMeterTypeEN(meterTypeEN);
		}

		String currentLevel = (String)attributes.get("currentLevel");

		if (currentLevel != null) {
			setCurrentLevel(currentLevel);
		}

		String startingLevel = (String)attributes.get("startingLevel");

		if (startingLevel != null) {
			setStartingLevel(startingLevel);
		}

		String targetLevel = (String)attributes.get("targetLevel");

		if (targetLevel != null) {
			setTargetLevel(targetLevel);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ReportMeterWrapper((ReportMeter)_reportMeter.clone());
	}

	@Override
	public int compareTo(ReportMeter reportMeter) {
		return _reportMeter.compareTo(reportMeter);
	}

	/**
	* Returns the commitment ID of this report meter.
	*
	* @return the commitment ID of this report meter
	*/
	@Override
	public java.lang.String getCommitmentId() {
		return _reportMeter.getCommitmentId();
	}

	/**
	* Returns the current level of this report meter.
	*
	* @return the current level of this report meter
	*/
	@Override
	public java.lang.String getCurrentLevel() {
		return _reportMeter.getCurrentLevel();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _reportMeter.getExpandoBridge();
	}

	/**
	* Returns the ID of this report meter.
	*
	* @return the ID of this report meter
	*/
	@Override
	public java.lang.String getId() {
		return _reportMeter.getId();
	}

	/**
	* Returns the meter category of this report meter.
	*
	* @return the meter category of this report meter
	*/
	@Override
	public java.lang.String getMeterCategory() {
		return _reportMeter.getMeterCategory();
	}

	/**
	* Returns the meter chart type of this report meter.
	*
	* @return the meter chart type of this report meter
	*/
	@Override
	public java.lang.String getMeterChartType() {
		return _reportMeter.getMeterChartType();
	}

	/**
	* Returns the meter ID of this report meter.
	*
	* @return the meter ID of this report meter
	*/
	@Override
	public java.lang.String getMeterId() {
		return _reportMeter.getMeterId();
	}

	/**
	* Returns the meter type en of this report meter.
	*
	* @return the meter type en of this report meter
	*/
	@Override
	public java.lang.String getMeterTypeEN() {
		return _reportMeter.getMeterTypeEN();
	}

	/**
	* Returns the meter type fi of this report meter.
	*
	* @return the meter type fi of this report meter
	*/
	@Override
	public java.lang.String getMeterTypeFI() {
		return _reportMeter.getMeterTypeFI();
	}

	/**
	* Returns the meter type sv of this report meter.
	*
	* @return the meter type sv of this report meter
	*/
	@Override
	public java.lang.String getMeterTypeSV() {
		return _reportMeter.getMeterTypeSV();
	}

	/**
	* Returns the meter value type of this report meter.
	*
	* @return the meter value type of this report meter
	*/
	@Override
	public java.lang.String getMeterValueType() {
		return _reportMeter.getMeterValueType();
	}

	/**
	* Returns the operation ID of this report meter.
	*
	* @return the operation ID of this report meter
	*/
	@Override
	public java.lang.String getOperationId() {
		return _reportMeter.getOperationId();
	}

	/**
	* Returns the primary key of this report meter.
	*
	* @return the primary key of this report meter
	*/
	@Override
	public java.lang.String getPrimaryKey() {
		return _reportMeter.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _reportMeter.getPrimaryKeyObj();
	}

	/**
	* Returns the report ID of this report meter.
	*
	* @return the report ID of this report meter
	*/
	@Override
	public java.lang.String getReportId() {
		return _reportMeter.getReportId();
	}

	/**
	* Returns the starting level of this report meter.
	*
	* @return the starting level of this report meter
	*/
	@Override
	public java.lang.String getStartingLevel() {
		return _reportMeter.getStartingLevel();
	}

	/**
	* Returns the target level of this report meter.
	*
	* @return the target level of this report meter
	*/
	@Override
	public java.lang.String getTargetLevel() {
		return _reportMeter.getTargetLevel();
	}

	@Override
	public int hashCode() {
		return _reportMeter.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _reportMeter.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _reportMeter.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _reportMeter.isNew();
	}

	@Override
	public void persist() {
		_reportMeter.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_reportMeter.setCachedModel(cachedModel);
	}

	/**
	* Sets the commitment ID of this report meter.
	*
	* @param commitmentId the commitment ID of this report meter
	*/
	@Override
	public void setCommitmentId(java.lang.String commitmentId) {
		_reportMeter.setCommitmentId(commitmentId);
	}

	/**
	* Sets the current level of this report meter.
	*
	* @param currentLevel the current level of this report meter
	*/
	@Override
	public void setCurrentLevel(java.lang.String currentLevel) {
		_reportMeter.setCurrentLevel(currentLevel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_reportMeter.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_reportMeter.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_reportMeter.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the ID of this report meter.
	*
	* @param id the ID of this report meter
	*/
	@Override
	public void setId(java.lang.String id) {
		_reportMeter.setId(id);
	}

	/**
	* Sets the meter category of this report meter.
	*
	* @param meterCategory the meter category of this report meter
	*/
	@Override
	public void setMeterCategory(java.lang.String meterCategory) {
		_reportMeter.setMeterCategory(meterCategory);
	}

	/**
	* Sets the meter chart type of this report meter.
	*
	* @param meterChartType the meter chart type of this report meter
	*/
	@Override
	public void setMeterChartType(java.lang.String meterChartType) {
		_reportMeter.setMeterChartType(meterChartType);
	}

	/**
	* Sets the meter ID of this report meter.
	*
	* @param meterId the meter ID of this report meter
	*/
	@Override
	public void setMeterId(java.lang.String meterId) {
		_reportMeter.setMeterId(meterId);
	}

	/**
	* Sets the meter type en of this report meter.
	*
	* @param meterTypeEN the meter type en of this report meter
	*/
	@Override
	public void setMeterTypeEN(java.lang.String meterTypeEN) {
		_reportMeter.setMeterTypeEN(meterTypeEN);
	}

	/**
	* Sets the meter type fi of this report meter.
	*
	* @param meterTypeFI the meter type fi of this report meter
	*/
	@Override
	public void setMeterTypeFI(java.lang.String meterTypeFI) {
		_reportMeter.setMeterTypeFI(meterTypeFI);
	}

	/**
	* Sets the meter type sv of this report meter.
	*
	* @param meterTypeSV the meter type sv of this report meter
	*/
	@Override
	public void setMeterTypeSV(java.lang.String meterTypeSV) {
		_reportMeter.setMeterTypeSV(meterTypeSV);
	}

	/**
	* Sets the meter value type of this report meter.
	*
	* @param meterValueType the meter value type of this report meter
	*/
	@Override
	public void setMeterValueType(java.lang.String meterValueType) {
		_reportMeter.setMeterValueType(meterValueType);
	}

	@Override
	public void setNew(boolean n) {
		_reportMeter.setNew(n);
	}

	/**
	* Sets the operation ID of this report meter.
	*
	* @param operationId the operation ID of this report meter
	*/
	@Override
	public void setOperationId(java.lang.String operationId) {
		_reportMeter.setOperationId(operationId);
	}

	/**
	* Sets the primary key of this report meter.
	*
	* @param primaryKey the primary key of this report meter
	*/
	@Override
	public void setPrimaryKey(java.lang.String primaryKey) {
		_reportMeter.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_reportMeter.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the report ID of this report meter.
	*
	* @param reportId the report ID of this report meter
	*/
	@Override
	public void setReportId(java.lang.String reportId) {
		_reportMeter.setReportId(reportId);
	}

	/**
	* Sets the starting level of this report meter.
	*
	* @param startingLevel the starting level of this report meter
	*/
	@Override
	public void setStartingLevel(java.lang.String startingLevel) {
		_reportMeter.setStartingLevel(startingLevel);
	}

	/**
	* Sets the target level of this report meter.
	*
	* @param targetLevel the target level of this report meter
	*/
	@Override
	public void setTargetLevel(java.lang.String targetLevel) {
		_reportMeter.setTargetLevel(targetLevel);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ReportMeter> toCacheModel() {
		return _reportMeter.toCacheModel();
	}

	@Override
	public ReportMeter toEscapedModel() {
		return new ReportMeterWrapper(_reportMeter.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _reportMeter.toString();
	}

	@Override
	public ReportMeter toUnescapedModel() {
		return new ReportMeterWrapper(_reportMeter.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _reportMeter.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReportMeterWrapper)) {
			return false;
		}

		ReportMeterWrapper reportMeterWrapper = (ReportMeterWrapper)obj;

		if (Objects.equals(_reportMeter, reportMeterWrapper._reportMeter)) {
			return true;
		}

		return false;
	}

	@Override
	public ReportMeter getWrappedModel() {
		return _reportMeter;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _reportMeter.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _reportMeter.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_reportMeter.resetOriginalValues();
	}

	private final ReportMeter _reportMeter;
}