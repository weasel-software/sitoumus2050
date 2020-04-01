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
public class CommitmentAnalysisResultSoap implements Serializable {
	public static CommitmentAnalysisResultSoap toSoapModel(
		CommitmentAnalysisResult model) {
		CommitmentAnalysisResultSoap soapModel = new CommitmentAnalysisResultSoap();

		soapModel.setId(model.getId());
		soapModel.setResultType(model.getResultType());
		soapModel.setResultData(model.getResultData());
		soapModel.setCalculated(model.getCalculated());
		soapModel.setSuccess(model.getSuccess());

		return soapModel;
	}

	public static CommitmentAnalysisResultSoap[] toSoapModels(
		CommitmentAnalysisResult[] models) {
		CommitmentAnalysisResultSoap[] soapModels = new CommitmentAnalysisResultSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommitmentAnalysisResultSoap[][] toSoapModels(
		CommitmentAnalysisResult[][] models) {
		CommitmentAnalysisResultSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommitmentAnalysisResultSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommitmentAnalysisResultSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommitmentAnalysisResultSoap[] toSoapModels(
		List<CommitmentAnalysisResult> models) {
		List<CommitmentAnalysisResultSoap> soapModels = new ArrayList<CommitmentAnalysisResultSoap>(models.size());

		for (CommitmentAnalysisResult model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommitmentAnalysisResultSoap[soapModels.size()]);
	}

	public CommitmentAnalysisResultSoap() {
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

	public String getResultType() {
		return _resultType;
	}

	public void setResultType(String resultType) {
		_resultType = resultType;
	}

	public String getResultData() {
		return _resultData;
	}

	public void setResultData(String resultData) {
		_resultData = resultData;
	}

	public Date getCalculated() {
		return _calculated;
	}

	public void setCalculated(Date calculated) {
		_calculated = calculated;
	}

	public boolean getSuccess() {
		return _success;
	}

	public boolean isSuccess() {
		return _success;
	}

	public void setSuccess(boolean success) {
		_success = success;
	}

	private String _id;
	private String _resultType;
	private String _resultData;
	private Date _calculated;
	private boolean _success;
}