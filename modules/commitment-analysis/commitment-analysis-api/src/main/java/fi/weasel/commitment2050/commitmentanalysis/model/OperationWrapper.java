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
 * This class is a wrapper for {@link Operation}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Operation
 * @generated
 */
@ProviderType
public class OperationWrapper implements Operation, ModelWrapper<Operation> {
	public OperationWrapper(Operation operation) {
		_operation = operation;
	}

	@Override
	public Class<?> getModelClass() {
		return Operation.class;
	}

	@Override
	public String getModelClassName() {
		return Operation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("commitmentId", getCommitmentId());
		attributes.put("operationId", getOperationId());
		attributes.put("operationCategory", getOperationCategory());
		attributes.put("operationTitleFI", getOperationTitleFI());
		attributes.put("operationTitleSV", getOperationTitleSV());
		attributes.put("operationTitleEN", getOperationTitleEN());
		attributes.put("operationDescriptionFI", getOperationDescriptionFI());
		attributes.put("operationDescriptionSV", getOperationDescriptionSV());
		attributes.put("operationDescriptionEN", getOperationDescriptionEN());

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

		String operationCategory = (String)attributes.get("operationCategory");

		if (operationCategory != null) {
			setOperationCategory(operationCategory);
		}

		String operationTitleFI = (String)attributes.get("operationTitleFI");

		if (operationTitleFI != null) {
			setOperationTitleFI(operationTitleFI);
		}

		String operationTitleSV = (String)attributes.get("operationTitleSV");

		if (operationTitleSV != null) {
			setOperationTitleSV(operationTitleSV);
		}

		String operationTitleEN = (String)attributes.get("operationTitleEN");

		if (operationTitleEN != null) {
			setOperationTitleEN(operationTitleEN);
		}

		String operationDescriptionFI = (String)attributes.get(
				"operationDescriptionFI");

		if (operationDescriptionFI != null) {
			setOperationDescriptionFI(operationDescriptionFI);
		}

		String operationDescriptionSV = (String)attributes.get(
				"operationDescriptionSV");

		if (operationDescriptionSV != null) {
			setOperationDescriptionSV(operationDescriptionSV);
		}

		String operationDescriptionEN = (String)attributes.get(
				"operationDescriptionEN");

		if (operationDescriptionEN != null) {
			setOperationDescriptionEN(operationDescriptionEN);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new OperationWrapper((Operation)_operation.clone());
	}

	@Override
	public int compareTo(Operation operation) {
		return _operation.compareTo(operation);
	}

	/**
	* Returns the commitment ID of this operation.
	*
	* @return the commitment ID of this operation
	*/
	@Override
	public java.lang.String getCommitmentId() {
		return _operation.getCommitmentId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _operation.getExpandoBridge();
	}

	/**
	* Returns the ID of this operation.
	*
	* @return the ID of this operation
	*/
	@Override
	public java.lang.String getId() {
		return _operation.getId();
	}

	@Override
	public java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.Meter> getMeters() {
		return _operation.getMeters();
	}

	/**
	* Returns the operation category of this operation.
	*
	* @return the operation category of this operation
	*/
	@Override
	public java.lang.String getOperationCategory() {
		return _operation.getOperationCategory();
	}

	/**
	* Returns the operation description en of this operation.
	*
	* @return the operation description en of this operation
	*/
	@Override
	public java.lang.String getOperationDescriptionEN() {
		return _operation.getOperationDescriptionEN();
	}

	/**
	* Returns the operation description fi of this operation.
	*
	* @return the operation description fi of this operation
	*/
	@Override
	public java.lang.String getOperationDescriptionFI() {
		return _operation.getOperationDescriptionFI();
	}

	/**
	* Returns the operation description sv of this operation.
	*
	* @return the operation description sv of this operation
	*/
	@Override
	public java.lang.String getOperationDescriptionSV() {
		return _operation.getOperationDescriptionSV();
	}

	/**
	* Returns the operation ID of this operation.
	*
	* @return the operation ID of this operation
	*/
	@Override
	public java.lang.String getOperationId() {
		return _operation.getOperationId();
	}

	/**
	* Returns the operation title en of this operation.
	*
	* @return the operation title en of this operation
	*/
	@Override
	public java.lang.String getOperationTitleEN() {
		return _operation.getOperationTitleEN();
	}

	/**
	* Returns the operation title fi of this operation.
	*
	* @return the operation title fi of this operation
	*/
	@Override
	public java.lang.String getOperationTitleFI() {
		return _operation.getOperationTitleFI();
	}

	/**
	* Returns the operation title sv of this operation.
	*
	* @return the operation title sv of this operation
	*/
	@Override
	public java.lang.String getOperationTitleSV() {
		return _operation.getOperationTitleSV();
	}

	/**
	* Returns the primary key of this operation.
	*
	* @return the primary key of this operation
	*/
	@Override
	public java.lang.String getPrimaryKey() {
		return _operation.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _operation.getPrimaryKeyObj();
	}

	@Override
	public java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.Report> getReports() {
		return _operation.getReports();
	}

	@Override
	public int hashCode() {
		return _operation.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _operation.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _operation.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _operation.isNew();
	}

	@Override
	public void persist() {
		_operation.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_operation.setCachedModel(cachedModel);
	}

	/**
	* Sets the commitment ID of this operation.
	*
	* @param commitmentId the commitment ID of this operation
	*/
	@Override
	public void setCommitmentId(java.lang.String commitmentId) {
		_operation.setCommitmentId(commitmentId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_operation.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_operation.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_operation.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the ID of this operation.
	*
	* @param id the ID of this operation
	*/
	@Override
	public void setId(java.lang.String id) {
		_operation.setId(id);
	}

	@Override
	public void setMeters(
		java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.Meter> meters) {
		_operation.setMeters(meters);
	}

	@Override
	public void setNew(boolean n) {
		_operation.setNew(n);
	}

	/**
	* Sets the operation category of this operation.
	*
	* @param operationCategory the operation category of this operation
	*/
	@Override
	public void setOperationCategory(java.lang.String operationCategory) {
		_operation.setOperationCategory(operationCategory);
	}

	/**
	* Sets the operation description en of this operation.
	*
	* @param operationDescriptionEN the operation description en of this operation
	*/
	@Override
	public void setOperationDescriptionEN(
		java.lang.String operationDescriptionEN) {
		_operation.setOperationDescriptionEN(operationDescriptionEN);
	}

	/**
	* Sets the operation description fi of this operation.
	*
	* @param operationDescriptionFI the operation description fi of this operation
	*/
	@Override
	public void setOperationDescriptionFI(
		java.lang.String operationDescriptionFI) {
		_operation.setOperationDescriptionFI(operationDescriptionFI);
	}

	/**
	* Sets the operation description sv of this operation.
	*
	* @param operationDescriptionSV the operation description sv of this operation
	*/
	@Override
	public void setOperationDescriptionSV(
		java.lang.String operationDescriptionSV) {
		_operation.setOperationDescriptionSV(operationDescriptionSV);
	}

	/**
	* Sets the operation ID of this operation.
	*
	* @param operationId the operation ID of this operation
	*/
	@Override
	public void setOperationId(java.lang.String operationId) {
		_operation.setOperationId(operationId);
	}

	/**
	* Sets the operation title en of this operation.
	*
	* @param operationTitleEN the operation title en of this operation
	*/
	@Override
	public void setOperationTitleEN(java.lang.String operationTitleEN) {
		_operation.setOperationTitleEN(operationTitleEN);
	}

	/**
	* Sets the operation title fi of this operation.
	*
	* @param operationTitleFI the operation title fi of this operation
	*/
	@Override
	public void setOperationTitleFI(java.lang.String operationTitleFI) {
		_operation.setOperationTitleFI(operationTitleFI);
	}

	/**
	* Sets the operation title sv of this operation.
	*
	* @param operationTitleSV the operation title sv of this operation
	*/
	@Override
	public void setOperationTitleSV(java.lang.String operationTitleSV) {
		_operation.setOperationTitleSV(operationTitleSV);
	}

	/**
	* Sets the primary key of this operation.
	*
	* @param primaryKey the primary key of this operation
	*/
	@Override
	public void setPrimaryKey(java.lang.String primaryKey) {
		_operation.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_operation.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public void setReports(
		java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.Report> reports) {
		_operation.setReports(reports);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Operation> toCacheModel() {
		return _operation.toCacheModel();
	}

	@Override
	public Operation toEscapedModel() {
		return new OperationWrapper(_operation.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _operation.toString();
	}

	@Override
	public Operation toUnescapedModel() {
		return new OperationWrapper(_operation.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _operation.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OperationWrapper)) {
			return false;
		}

		OperationWrapper operationWrapper = (OperationWrapper)obj;

		if (Objects.equals(_operation, operationWrapper._operation)) {
			return true;
		}

		return false;
	}

	@Override
	public Operation getWrappedModel() {
		return _operation;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _operation.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _operation.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_operation.resetOriginalValues();
	}

	private final Operation _operation;
}