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
 * This class is a wrapper for {@link DoneOperation}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DoneOperation
 * @generated
 */
@ProviderType
public class DoneOperationWrapper implements DoneOperation,
	ModelWrapper<DoneOperation> {
	public DoneOperationWrapper(DoneOperation doneOperation) {
		_doneOperation = doneOperation;
	}

	@Override
	public Class<?> getModelClass() {
		return DoneOperation.class;
	}

	@Override
	public String getModelClassName() {
		return DoneOperation.class.getName();
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
	}

	@Override
	public java.lang.Object clone() {
		return new DoneOperationWrapper((DoneOperation)_doneOperation.clone());
	}

	@Override
	public int compareTo(DoneOperation doneOperation) {
		return _doneOperation.compareTo(doneOperation);
	}

	/**
	* Returns the commitment ID of this done operation.
	*
	* @return the commitment ID of this done operation
	*/
	@Override
	public java.lang.String getCommitmentId() {
		return _doneOperation.getCommitmentId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _doneOperation.getExpandoBridge();
	}

	/**
	* Returns the ID of this done operation.
	*
	* @return the ID of this done operation
	*/
	@Override
	public java.lang.String getId() {
		return _doneOperation.getId();
	}

	/**
	* Returns the operation category of this done operation.
	*
	* @return the operation category of this done operation
	*/
	@Override
	public java.lang.String getOperationCategory() {
		return _doneOperation.getOperationCategory();
	}

	/**
	* Returns the operation ID of this done operation.
	*
	* @return the operation ID of this done operation
	*/
	@Override
	public java.lang.String getOperationId() {
		return _doneOperation.getOperationId();
	}

	/**
	* Returns the operation title en of this done operation.
	*
	* @return the operation title en of this done operation
	*/
	@Override
	public java.lang.String getOperationTitleEN() {
		return _doneOperation.getOperationTitleEN();
	}

	/**
	* Returns the operation title fi of this done operation.
	*
	* @return the operation title fi of this done operation
	*/
	@Override
	public java.lang.String getOperationTitleFI() {
		return _doneOperation.getOperationTitleFI();
	}

	/**
	* Returns the operation title sv of this done operation.
	*
	* @return the operation title sv of this done operation
	*/
	@Override
	public java.lang.String getOperationTitleSV() {
		return _doneOperation.getOperationTitleSV();
	}

	/**
	* Returns the primary key of this done operation.
	*
	* @return the primary key of this done operation
	*/
	@Override
	public java.lang.String getPrimaryKey() {
		return _doneOperation.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _doneOperation.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _doneOperation.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _doneOperation.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _doneOperation.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _doneOperation.isNew();
	}

	@Override
	public void persist() {
		_doneOperation.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_doneOperation.setCachedModel(cachedModel);
	}

	/**
	* Sets the commitment ID of this done operation.
	*
	* @param commitmentId the commitment ID of this done operation
	*/
	@Override
	public void setCommitmentId(java.lang.String commitmentId) {
		_doneOperation.setCommitmentId(commitmentId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_doneOperation.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_doneOperation.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_doneOperation.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the ID of this done operation.
	*
	* @param id the ID of this done operation
	*/
	@Override
	public void setId(java.lang.String id) {
		_doneOperation.setId(id);
	}

	@Override
	public void setNew(boolean n) {
		_doneOperation.setNew(n);
	}

	/**
	* Sets the operation category of this done operation.
	*
	* @param operationCategory the operation category of this done operation
	*/
	@Override
	public void setOperationCategory(java.lang.String operationCategory) {
		_doneOperation.setOperationCategory(operationCategory);
	}

	/**
	* Sets the operation ID of this done operation.
	*
	* @param operationId the operation ID of this done operation
	*/
	@Override
	public void setOperationId(java.lang.String operationId) {
		_doneOperation.setOperationId(operationId);
	}

	/**
	* Sets the operation title en of this done operation.
	*
	* @param operationTitleEN the operation title en of this done operation
	*/
	@Override
	public void setOperationTitleEN(java.lang.String operationTitleEN) {
		_doneOperation.setOperationTitleEN(operationTitleEN);
	}

	/**
	* Sets the operation title fi of this done operation.
	*
	* @param operationTitleFI the operation title fi of this done operation
	*/
	@Override
	public void setOperationTitleFI(java.lang.String operationTitleFI) {
		_doneOperation.setOperationTitleFI(operationTitleFI);
	}

	/**
	* Sets the operation title sv of this done operation.
	*
	* @param operationTitleSV the operation title sv of this done operation
	*/
	@Override
	public void setOperationTitleSV(java.lang.String operationTitleSV) {
		_doneOperation.setOperationTitleSV(operationTitleSV);
	}

	/**
	* Sets the primary key of this done operation.
	*
	* @param primaryKey the primary key of this done operation
	*/
	@Override
	public void setPrimaryKey(java.lang.String primaryKey) {
		_doneOperation.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_doneOperation.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DoneOperation> toCacheModel() {
		return _doneOperation.toCacheModel();
	}

	@Override
	public DoneOperation toEscapedModel() {
		return new DoneOperationWrapper(_doneOperation.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _doneOperation.toString();
	}

	@Override
	public DoneOperation toUnescapedModel() {
		return new DoneOperationWrapper(_doneOperation.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _doneOperation.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DoneOperationWrapper)) {
			return false;
		}

		DoneOperationWrapper doneOperationWrapper = (DoneOperationWrapper)obj;

		if (Objects.equals(_doneOperation, doneOperationWrapper._doneOperation)) {
			return true;
		}

		return false;
	}

	@Override
	public DoneOperation getWrappedModel() {
		return _doneOperation;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _doneOperation.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _doneOperation.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_doneOperation.resetOriginalValues();
	}

	private final DoneOperation _doneOperation;
}