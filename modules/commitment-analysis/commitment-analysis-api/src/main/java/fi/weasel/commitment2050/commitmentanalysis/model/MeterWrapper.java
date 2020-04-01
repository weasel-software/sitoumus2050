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
 * This class is a wrapper for {@link Meter}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Meter
 * @generated
 */
@ProviderType
public class MeterWrapper implements Meter, ModelWrapper<Meter> {
	public MeterWrapper(Meter meter) {
		_meter = meter;
	}

	@Override
	public Class<?> getModelClass() {
		return Meter.class;
	}

	@Override
	public String getModelClassName() {
		return Meter.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("commitmentId", getCommitmentId());
		attributes.put("operationId", getOperationId());
		attributes.put("meterId", getMeterId());
		attributes.put("meterCategory", getMeterCategory());
		attributes.put("meterChartType", getMeterChartType());
		attributes.put("meterValueType", getMeterValueType());
		attributes.put("meterTypeFI", getMeterTypeFI());
		attributes.put("meterTypeSV", getMeterTypeSV());
		attributes.put("meterTypeEN", getMeterTypeEN());
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
		return new MeterWrapper((Meter)_meter.clone());
	}

	@Override
	public int compareTo(Meter meter) {
		return _meter.compareTo(meter);
	}

	/**
	* Returns the commitment ID of this meter.
	*
	* @return the commitment ID of this meter
	*/
	@Override
	public java.lang.String getCommitmentId() {
		return _meter.getCommitmentId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _meter.getExpandoBridge();
	}

	/**
	* Returns the ID of this meter.
	*
	* @return the ID of this meter
	*/
	@Override
	public java.lang.String getId() {
		return _meter.getId();
	}

	/**
	* Returns the meter category of this meter.
	*
	* @return the meter category of this meter
	*/
	@Override
	public java.lang.String getMeterCategory() {
		return _meter.getMeterCategory();
	}

	/**
	* Returns the meter chart type of this meter.
	*
	* @return the meter chart type of this meter
	*/
	@Override
	public java.lang.String getMeterChartType() {
		return _meter.getMeterChartType();
	}

	/**
	* Returns the meter ID of this meter.
	*
	* @return the meter ID of this meter
	*/
	@Override
	public java.lang.String getMeterId() {
		return _meter.getMeterId();
	}

	/**
	* Returns the meter type en of this meter.
	*
	* @return the meter type en of this meter
	*/
	@Override
	public java.lang.String getMeterTypeEN() {
		return _meter.getMeterTypeEN();
	}

	/**
	* Returns the meter type fi of this meter.
	*
	* @return the meter type fi of this meter
	*/
	@Override
	public java.lang.String getMeterTypeFI() {
		return _meter.getMeterTypeFI();
	}

	/**
	* Returns the meter type sv of this meter.
	*
	* @return the meter type sv of this meter
	*/
	@Override
	public java.lang.String getMeterTypeSV() {
		return _meter.getMeterTypeSV();
	}

	/**
	* Returns the meter value type of this meter.
	*
	* @return the meter value type of this meter
	*/
	@Override
	public java.lang.String getMeterValueType() {
		return _meter.getMeterValueType();
	}

	/**
	* Returns the operation ID of this meter.
	*
	* @return the operation ID of this meter
	*/
	@Override
	public java.lang.String getOperationId() {
		return _meter.getOperationId();
	}

	/**
	* Returns the primary key of this meter.
	*
	* @return the primary key of this meter
	*/
	@Override
	public java.lang.String getPrimaryKey() {
		return _meter.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _meter.getPrimaryKeyObj();
	}

	/**
	* Returns the starting level of this meter.
	*
	* @return the starting level of this meter
	*/
	@Override
	public java.lang.String getStartingLevel() {
		return _meter.getStartingLevel();
	}

	/**
	* Returns the target level of this meter.
	*
	* @return the target level of this meter
	*/
	@Override
	public java.lang.String getTargetLevel() {
		return _meter.getTargetLevel();
	}

	@Override
	public int hashCode() {
		return _meter.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _meter.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _meter.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _meter.isNew();
	}

	@Override
	public void persist() {
		_meter.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_meter.setCachedModel(cachedModel);
	}

	/**
	* Sets the commitment ID of this meter.
	*
	* @param commitmentId the commitment ID of this meter
	*/
	@Override
	public void setCommitmentId(java.lang.String commitmentId) {
		_meter.setCommitmentId(commitmentId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_meter.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_meter.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_meter.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the ID of this meter.
	*
	* @param id the ID of this meter
	*/
	@Override
	public void setId(java.lang.String id) {
		_meter.setId(id);
	}

	/**
	* Sets the meter category of this meter.
	*
	* @param meterCategory the meter category of this meter
	*/
	@Override
	public void setMeterCategory(java.lang.String meterCategory) {
		_meter.setMeterCategory(meterCategory);
	}

	/**
	* Sets the meter chart type of this meter.
	*
	* @param meterChartType the meter chart type of this meter
	*/
	@Override
	public void setMeterChartType(java.lang.String meterChartType) {
		_meter.setMeterChartType(meterChartType);
	}

	/**
	* Sets the meter ID of this meter.
	*
	* @param meterId the meter ID of this meter
	*/
	@Override
	public void setMeterId(java.lang.String meterId) {
		_meter.setMeterId(meterId);
	}

	/**
	* Sets the meter type en of this meter.
	*
	* @param meterTypeEN the meter type en of this meter
	*/
	@Override
	public void setMeterTypeEN(java.lang.String meterTypeEN) {
		_meter.setMeterTypeEN(meterTypeEN);
	}

	/**
	* Sets the meter type fi of this meter.
	*
	* @param meterTypeFI the meter type fi of this meter
	*/
	@Override
	public void setMeterTypeFI(java.lang.String meterTypeFI) {
		_meter.setMeterTypeFI(meterTypeFI);
	}

	/**
	* Sets the meter type sv of this meter.
	*
	* @param meterTypeSV the meter type sv of this meter
	*/
	@Override
	public void setMeterTypeSV(java.lang.String meterTypeSV) {
		_meter.setMeterTypeSV(meterTypeSV);
	}

	/**
	* Sets the meter value type of this meter.
	*
	* @param meterValueType the meter value type of this meter
	*/
	@Override
	public void setMeterValueType(java.lang.String meterValueType) {
		_meter.setMeterValueType(meterValueType);
	}

	@Override
	public void setNew(boolean n) {
		_meter.setNew(n);
	}

	/**
	* Sets the operation ID of this meter.
	*
	* @param operationId the operation ID of this meter
	*/
	@Override
	public void setOperationId(java.lang.String operationId) {
		_meter.setOperationId(operationId);
	}

	/**
	* Sets the primary key of this meter.
	*
	* @param primaryKey the primary key of this meter
	*/
	@Override
	public void setPrimaryKey(java.lang.String primaryKey) {
		_meter.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_meter.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the starting level of this meter.
	*
	* @param startingLevel the starting level of this meter
	*/
	@Override
	public void setStartingLevel(java.lang.String startingLevel) {
		_meter.setStartingLevel(startingLevel);
	}

	/**
	* Sets the target level of this meter.
	*
	* @param targetLevel the target level of this meter
	*/
	@Override
	public void setTargetLevel(java.lang.String targetLevel) {
		_meter.setTargetLevel(targetLevel);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Meter> toCacheModel() {
		return _meter.toCacheModel();
	}

	@Override
	public Meter toEscapedModel() {
		return new MeterWrapper(_meter.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _meter.toString();
	}

	@Override
	public Meter toUnescapedModel() {
		return new MeterWrapper(_meter.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _meter.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MeterWrapper)) {
			return false;
		}

		MeterWrapper meterWrapper = (MeterWrapper)obj;

		if (Objects.equals(_meter, meterWrapper._meter)) {
			return true;
		}

		return false;
	}

	@Override
	public Meter getWrappedModel() {
		return _meter;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _meter.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _meter.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_meter.resetOriginalValues();
	}

	private final Meter _meter;
}