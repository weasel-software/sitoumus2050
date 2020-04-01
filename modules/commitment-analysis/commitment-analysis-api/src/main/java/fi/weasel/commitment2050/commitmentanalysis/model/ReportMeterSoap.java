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
public class ReportMeterSoap implements Serializable {
	public static ReportMeterSoap toSoapModel(ReportMeter model) {
		ReportMeterSoap soapModel = new ReportMeterSoap();

		soapModel.setId(model.getId());
		soapModel.setCommitmentId(model.getCommitmentId());
		soapModel.setOperationId(model.getOperationId());
		soapModel.setReportId(model.getReportId());
		soapModel.setMeterId(model.getMeterId());
		soapModel.setMeterCategory(model.getMeterCategory());
		soapModel.setMeterChartType(model.getMeterChartType());
		soapModel.setMeterValueType(model.getMeterValueType());
		soapModel.setMeterTypeFI(model.getMeterTypeFI());
		soapModel.setMeterTypeSV(model.getMeterTypeSV());
		soapModel.setMeterTypeEN(model.getMeterTypeEN());
		soapModel.setCurrentLevel(model.getCurrentLevel());
		soapModel.setStartingLevel(model.getStartingLevel());
		soapModel.setTargetLevel(model.getTargetLevel());

		return soapModel;
	}

	public static ReportMeterSoap[] toSoapModels(ReportMeter[] models) {
		ReportMeterSoap[] soapModels = new ReportMeterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ReportMeterSoap[][] toSoapModels(ReportMeter[][] models) {
		ReportMeterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ReportMeterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ReportMeterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ReportMeterSoap[] toSoapModels(List<ReportMeter> models) {
		List<ReportMeterSoap> soapModels = new ArrayList<ReportMeterSoap>(models.size());

		for (ReportMeter model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ReportMeterSoap[soapModels.size()]);
	}

	public ReportMeterSoap() {
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

	public String getReportId() {
		return _reportId;
	}

	public void setReportId(String reportId) {
		_reportId = reportId;
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

	public String getCurrentLevel() {
		return _currentLevel;
	}

	public void setCurrentLevel(String currentLevel) {
		_currentLevel = currentLevel;
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
	private String _reportId;
	private String _meterId;
	private String _meterCategory;
	private String _meterChartType;
	private String _meterValueType;
	private String _meterTypeFI;
	private String _meterTypeSV;
	private String _meterTypeEN;
	private String _currentLevel;
	private String _startingLevel;
	private String _targetLevel;
}