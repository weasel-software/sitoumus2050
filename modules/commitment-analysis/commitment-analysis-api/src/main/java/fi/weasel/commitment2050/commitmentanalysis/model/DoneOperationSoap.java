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
public class DoneOperationSoap implements Serializable {
	public static DoneOperationSoap toSoapModel(DoneOperation model) {
		DoneOperationSoap soapModel = new DoneOperationSoap();

		soapModel.setId(model.getId());
		soapModel.setCommitmentId(model.getCommitmentId());
		soapModel.setOperationId(model.getOperationId());
		soapModel.setOperationCategory(model.getOperationCategory());
		soapModel.setOperationTitleFI(model.getOperationTitleFI());
		soapModel.setOperationTitleSV(model.getOperationTitleSV());
		soapModel.setOperationTitleEN(model.getOperationTitleEN());

		return soapModel;
	}

	public static DoneOperationSoap[] toSoapModels(DoneOperation[] models) {
		DoneOperationSoap[] soapModels = new DoneOperationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DoneOperationSoap[][] toSoapModels(DoneOperation[][] models) {
		DoneOperationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DoneOperationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DoneOperationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DoneOperationSoap[] toSoapModels(List<DoneOperation> models) {
		List<DoneOperationSoap> soapModels = new ArrayList<DoneOperationSoap>(models.size());

		for (DoneOperation model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DoneOperationSoap[soapModels.size()]);
	}

	public DoneOperationSoap() {
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

	public String getOperationCategory() {
		return _operationCategory;
	}

	public void setOperationCategory(String operationCategory) {
		_operationCategory = operationCategory;
	}

	public String getOperationTitleFI() {
		return _operationTitleFI;
	}

	public void setOperationTitleFI(String operationTitleFI) {
		_operationTitleFI = operationTitleFI;
	}

	public String getOperationTitleSV() {
		return _operationTitleSV;
	}

	public void setOperationTitleSV(String operationTitleSV) {
		_operationTitleSV = operationTitleSV;
	}

	public String getOperationTitleEN() {
		return _operationTitleEN;
	}

	public void setOperationTitleEN(String operationTitleEN) {
		_operationTitleEN = operationTitleEN;
	}

	private String _id;
	private String _commitmentId;
	private String _operationId;
	private String _operationCategory;
	private String _operationTitleFI;
	private String _operationTitleSV;
	private String _operationTitleEN;
}