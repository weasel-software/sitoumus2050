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
public class OperationSoap implements Serializable {
	public static OperationSoap toSoapModel(Operation model) {
		OperationSoap soapModel = new OperationSoap();

		soapModel.setId(model.getId());
		soapModel.setCommitmentId(model.getCommitmentId());
		soapModel.setOperationId(model.getOperationId());
		soapModel.setOperationCategory(model.getOperationCategory());
		soapModel.setOperationTitleFI(model.getOperationTitleFI());
		soapModel.setOperationTitleSV(model.getOperationTitleSV());
		soapModel.setOperationTitleEN(model.getOperationTitleEN());
		soapModel.setOperationDescriptionFI(model.getOperationDescriptionFI());
		soapModel.setOperationDescriptionSV(model.getOperationDescriptionSV());
		soapModel.setOperationDescriptionEN(model.getOperationDescriptionEN());

		return soapModel;
	}

	public static OperationSoap[] toSoapModels(Operation[] models) {
		OperationSoap[] soapModels = new OperationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OperationSoap[][] toSoapModels(Operation[][] models) {
		OperationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OperationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OperationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OperationSoap[] toSoapModels(List<Operation> models) {
		List<OperationSoap> soapModels = new ArrayList<OperationSoap>(models.size());

		for (Operation model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OperationSoap[soapModels.size()]);
	}

	public OperationSoap() {
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

	public String getOperationDescriptionFI() {
		return _operationDescriptionFI;
	}

	public void setOperationDescriptionFI(String operationDescriptionFI) {
		_operationDescriptionFI = operationDescriptionFI;
	}

	public String getOperationDescriptionSV() {
		return _operationDescriptionSV;
	}

	public void setOperationDescriptionSV(String operationDescriptionSV) {
		_operationDescriptionSV = operationDescriptionSV;
	}

	public String getOperationDescriptionEN() {
		return _operationDescriptionEN;
	}

	public void setOperationDescriptionEN(String operationDescriptionEN) {
		_operationDescriptionEN = operationDescriptionEN;
	}

	private String _id;
	private String _commitmentId;
	private String _operationId;
	private String _operationCategory;
	private String _operationTitleFI;
	private String _operationTitleSV;
	private String _operationTitleEN;
	private String _operationDescriptionFI;
	private String _operationDescriptionSV;
	private String _operationDescriptionEN;
}