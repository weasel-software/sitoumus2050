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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class ReportSoap implements Serializable {
	public static ReportSoap toSoapModel(Report model) {
		ReportSoap soapModel = new ReportSoap();

		soapModel.setId(model.getId());
		soapModel.setReportId(model.getReportId());
		soapModel.setOperationId(model.getOperationId());
		soapModel.setCommitmentId(model.getCommitmentId());
		soapModel.setCreatedByUserId(model.getCreatedByUserId());
		soapModel.setCreatedByUserName(model.getCreatedByUserName());
		soapModel.setOrganizationName(model.getOrganizationName());
		soapModel.setProgress(model.getProgress());
		soapModel.setReportStartDate(model.getReportStartDate());
		soapModel.setReportEndDate(model.getReportEndDate());
		soapModel.setReportStatus(model.getReportStatus());
		soapModel.setReportTextFI(model.getReportTextFI());
		soapModel.setReportTextSV(model.getReportTextSV());
		soapModel.setReportTextEN(model.getReportTextEN());
		soapModel.setReportTitleFI(model.getReportTitleFI());
		soapModel.setReportTitleSV(model.getReportTitleSV());
		soapModel.setReportTitleEN(model.getReportTitleEN());
		soapModel.setStatus(model.getStatus());
		soapModel.setVersion(model.getVersion());

		return soapModel;
	}

	public static ReportSoap[] toSoapModels(Report[] models) {
		ReportSoap[] soapModels = new ReportSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ReportSoap[][] toSoapModels(Report[][] models) {
		ReportSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ReportSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ReportSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ReportSoap[] toSoapModels(List<Report> models) {
		List<ReportSoap> soapModels = new ArrayList<ReportSoap>(models.size());

		for (Report model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ReportSoap[soapModels.size()]);
	}

	public ReportSoap() {
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

	public String getReportId() {
		return _reportId;
	}

	public void setReportId(String reportId) {
		_reportId = reportId;
	}

	public String getOperationId() {
		return _operationId;
	}

	public void setOperationId(String operationId) {
		_operationId = operationId;
	}

	public String getCommitmentId() {
		return _commitmentId;
	}

	public void setCommitmentId(String commitmentId) {
		_commitmentId = commitmentId;
	}

	public int getCreatedByUserId() {
		return _createdByUserId;
	}

	public void setCreatedByUserId(int createdByUserId) {
		_createdByUserId = createdByUserId;
	}

	public String getCreatedByUserName() {
		return _createdByUserName;
	}

	public void setCreatedByUserName(String createdByUserName) {
		_createdByUserName = createdByUserName;
	}

	public String getOrganizationName() {
		return _organizationName;
	}

	public void setOrganizationName(String organizationName) {
		_organizationName = organizationName;
	}

	public String getProgress() {
		return _progress;
	}

	public void setProgress(String progress) {
		_progress = progress;
	}

	public Date getReportStartDate() {
		return _reportStartDate;
	}

	public void setReportStartDate(Date reportStartDate) {
		_reportStartDate = reportStartDate;
	}

	public Date getReportEndDate() {
		return _reportEndDate;
	}

	public void setReportEndDate(Date reportEndDate) {
		_reportEndDate = reportEndDate;
	}

	public boolean getReportStatus() {
		return _reportStatus;
	}

	public boolean isReportStatus() {
		return _reportStatus;
	}

	public void setReportStatus(boolean reportStatus) {
		_reportStatus = reportStatus;
	}

	public String getReportTextFI() {
		return _reportTextFI;
	}

	public void setReportTextFI(String reportTextFI) {
		_reportTextFI = reportTextFI;
	}

	public String getReportTextSV() {
		return _reportTextSV;
	}

	public void setReportTextSV(String reportTextSV) {
		_reportTextSV = reportTextSV;
	}

	public String getReportTextEN() {
		return _reportTextEN;
	}

	public void setReportTextEN(String reportTextEN) {
		_reportTextEN = reportTextEN;
	}

	public String getReportTitleFI() {
		return _reportTitleFI;
	}

	public void setReportTitleFI(String reportTitleFI) {
		_reportTitleFI = reportTitleFI;
	}

	public String getReportTitleSV() {
		return _reportTitleSV;
	}

	public void setReportTitleSV(String reportTitleSV) {
		_reportTitleSV = reportTitleSV;
	}

	public String getReportTitleEN() {
		return _reportTitleEN;
	}

	public void setReportTitleEN(String reportTitleEN) {
		_reportTitleEN = reportTitleEN;
	}

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public double getVersion() {
		return _version;
	}

	public void setVersion(double version) {
		_version = version;
	}

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
}