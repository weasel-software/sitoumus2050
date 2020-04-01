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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class MeterSoap implements Serializable {
	public static MeterSoap toSoapModel(Meter model) {
		MeterSoap soapModel = new MeterSoap();

		soapModel.setId(model.getId());
		soapModel.setCommitmentId(model.getCommitmentId());
		soapModel.setOperationId(model.getOperationId());
		soapModel.setMeterId(model.getMeterId());
		soapModel.setMeterCategory(model.getMeterCategory());
		soapModel.setMeterChartType(model.getMeterChartType());
		soapModel.setMeterValueType(model.getMeterValueType());
		soapModel.setMeterTypeFI(model.getMeterTypeFI());
		soapModel.setMeterTypeSV(model.getMeterTypeSV());
		soapModel.setMeterTypeEN(model.getMeterTypeEN());
		soapModel.setStartingLevel(model.getStartingLevel());
		soapModel.setTargetLevel(model.getTargetLevel());

		return soapModel;
	}

	public static MeterSoap[] toSoapModels(Meter[] models) {
		MeterSoap[] soapModels = new MeterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MeterSoap[][] toSoapModels(Meter[][] models) {
		MeterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MeterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MeterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MeterSoap[] toSoapModels(List<Meter> models) {
		List<MeterSoap> soapModels = new ArrayList<MeterSoap>(models.size());

		for (Meter model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MeterSoap[soapModels.size()]);
	}

	public MeterSoap() {
	}

	public String getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(String pk) {
		setId(pk);
	}

	public String getId() {
		return _id;
	}

	public void setId(String id) {
		_id = id;
	}

	public String getCommitmentId() {
		return _commitmentId;
	}

	public void setCommitmentId(String commitmentId) {
		_commitmentId = commitmentId;
	}

	public String getOperationId() {
		return _operationId;
	}

	public void setOperationId(String operationId) {
		_operationId = operationId;
	}

	public String getMeterId() {
		return _meterId;
	}

	public void setMeterId(String meterId) {
		_meterId = meterId;
	}

	public String getMeterCategory() {
		return _meterCategory;
	}

	public void setMeterCategory(String meterCategory) {
		_meterCategory = meterCategory;
	}

	public String getMeterChartType() {
		return _meterChartType;
	}

	public void setMeterChartType(String meterChartType) {
		_meterChartType = meterChartType;
	}

	public String getMeterValueType() {
		return _meterValueType;
	}

	public void setMeterValueType(String meterValueType) {
		_meterValueType = meterValueType;
	}

	public String getMeterTypeFI() {
		return _meterTypeFI;
	}

	public void setMeterTypeFI(String meterTypeFI) {
		_meterTypeFI = meterTypeFI;
	}

	public String getMeterTypeSV() {
		return _meterTypeSV;
	}

	public void setMeterTypeSV(String meterTypeSV) {
		_meterTypeSV = meterTypeSV;
	}

	public String getMeterTypeEN() {
		return _meterTypeEN;
	}

	public void setMeterTypeEN(String meterTypeEN) {
		_meterTypeEN = meterTypeEN;
	}

	public String getStartingLevel() {
		return _startingLevel;
	}

	public void setStartingLevel(String startingLevel) {
		_startingLevel = startingLevel;
	}

	public String getTargetLevel() {
		return _targetLevel;
	}

	public void setTargetLevel(String targetLevel) {
		_targetLevel = targetLevel;
	}

	private String _id;
	private String _commitmentId;
	private String _operationId;
	private String _meterId;
	private String _meterCategory;
	private String _meterChartType;
	private String _meterValueType;
	private String _meterTypeFI;
	private String _meterTypeSV;
	private String _meterTypeEN;
	private String _startingLevel;
	private String _targetLevel;
}