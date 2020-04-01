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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link CommitmentAnalysisResult}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CommitmentAnalysisResult
 * @generated
 */
@ProviderType
public class CommitmentAnalysisResultWrapper implements CommitmentAnalysisResult,
	ModelWrapper<CommitmentAnalysisResult> {
	public CommitmentAnalysisResultWrapper(
		CommitmentAnalysisResult commitmentAnalysisResult) {
		_commitmentAnalysisResult = commitmentAnalysisResult;
	}

	@Override
	public Class<?> getModelClass() {
		return CommitmentAnalysisResult.class;
	}

	@Override
	public String getModelClassName() {
		return CommitmentAnalysisResult.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("resultType", getResultType());
		attributes.put("resultData", getResultData());
		attributes.put("calculated", getCalculated());
		attributes.put("success", getSuccess());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String id = (String)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		String resultType = (String)attributes.get("resultType");

		if (resultType != null) {
			setResultType(resultType);
		}

		String resultData = (String)attributes.get("resultData");

		if (resultData != null) {
			setResultData(resultData);
		}

		Date calculated = (Date)attributes.get("calculated");

		if (calculated != null) {
			setCalculated(calculated);
		}

		Boolean success = (Boolean)attributes.get("success");

		if (success != null) {
			setSuccess(success);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CommitmentAnalysisResultWrapper((CommitmentAnalysisResult)_commitmentAnalysisResult.clone());
	}

	@Override
	public int compareTo(CommitmentAnalysisResult commitmentAnalysisResult) {
		return _commitmentAnalysisResult.compareTo(commitmentAnalysisResult);
	}

	/**
	* Returns the calculated of this commitment analysis result.
	*
	* @return the calculated of this commitment analysis result
	*/
	@Override
	public Date getCalculated() {
		return _commitmentAnalysisResult.getCalculated();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _commitmentAnalysisResult.getExpandoBridge();
	}

	/**
	* Returns the ID of this commitment analysis result.
	*
	* @return the ID of this commitment analysis result
	*/
	@Override
	public java.lang.String getId() {
		return _commitmentAnalysisResult.getId();
	}

	/**
	* Returns the primary key of this commitment analysis result.
	*
	* @return the primary key of this commitment analysis result
	*/
	@Override
	public java.lang.String getPrimaryKey() {
		return _commitmentAnalysisResult.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commitmentAnalysisResult.getPrimaryKeyObj();
	}

	/**
	* Returns the result data of this commitment analysis result.
	*
	* @return the result data of this commitment analysis result
	*/
	@Override
	public java.lang.String getResultData() {
		return _commitmentAnalysisResult.getResultData();
	}

	/**
	* Returns the result type of this commitment analysis result.
	*
	* @return the result type of this commitment analysis result
	*/
	@Override
	public java.lang.String getResultType() {
		return _commitmentAnalysisResult.getResultType();
	}

	/**
	* Returns the success of this commitment analysis result.
	*
	* @return the success of this commitment analysis result
	*/
	@Override
	public boolean getSuccess() {
		return _commitmentAnalysisResult.getSuccess();
	}

	@Override
	public int hashCode() {
		return _commitmentAnalysisResult.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _commitmentAnalysisResult.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _commitmentAnalysisResult.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _commitmentAnalysisResult.isNew();
	}

	/**
	* Returns <code>true</code> if this commitment analysis result is success.
	*
	* @return <code>true</code> if this commitment analysis result is success; <code>false</code> otherwise
	*/
	@Override
	public boolean isSuccess() {
		return _commitmentAnalysisResult.isSuccess();
	}

	@Override
	public void persist() {
		_commitmentAnalysisResult.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_commitmentAnalysisResult.setCachedModel(cachedModel);
	}

	/**
	* Sets the calculated of this commitment analysis result.
	*
	* @param calculated the calculated of this commitment analysis result
	*/
	@Override
	public void setCalculated(Date calculated) {
		_commitmentAnalysisResult.setCalculated(calculated);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_commitmentAnalysisResult.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_commitmentAnalysisResult.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_commitmentAnalysisResult.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the ID of this commitment analysis result.
	*
	* @param id the ID of this commitment analysis result
	*/
	@Override
	public void setId(java.lang.String id) {
		_commitmentAnalysisResult.setId(id);
	}

	@Override
	public void setNew(boolean n) {
		_commitmentAnalysisResult.setNew(n);
	}

	/**
	* Sets the primary key of this commitment analysis result.
	*
	* @param primaryKey the primary key of this commitment analysis result
	*/
	@Override
	public void setPrimaryKey(java.lang.String primaryKey) {
		_commitmentAnalysisResult.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_commitmentAnalysisResult.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public void setResultCAResultType(
		fi.weasel.commitment2050.commitmentanalysis.model.CAResultType type) {
		_commitmentAnalysisResult.setResultCAResultType(type);
	}

	/**
	* Sets the result data of this commitment analysis result.
	*
	* @param resultData the result data of this commitment analysis result
	*/
	@Override
	public void setResultData(java.lang.String resultData) {
		_commitmentAnalysisResult.setResultData(resultData);
	}

	/**
	* Sets the result type of this commitment analysis result.
	*
	* @param resultType the result type of this commitment analysis result
	*/
	@Override
	public void setResultType(java.lang.String resultType) {
		_commitmentAnalysisResult.setResultType(resultType);
	}

	/**
	* Sets whether this commitment analysis result is success.
	*
	* @param success the success of this commitment analysis result
	*/
	@Override
	public void setSuccess(boolean success) {
		_commitmentAnalysisResult.setSuccess(success);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CommitmentAnalysisResult> toCacheModel() {
		return _commitmentAnalysisResult.toCacheModel();
	}

	@Override
	public CommitmentAnalysisResult toEscapedModel() {
		return new CommitmentAnalysisResultWrapper(_commitmentAnalysisResult.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _commitmentAnalysisResult.toString();
	}

	@Override
	public CommitmentAnalysisResult toUnescapedModel() {
		return new CommitmentAnalysisResultWrapper(_commitmentAnalysisResult.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _commitmentAnalysisResult.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommitmentAnalysisResultWrapper)) {
			return false;
		}

		CommitmentAnalysisResultWrapper commitmentAnalysisResultWrapper = (CommitmentAnalysisResultWrapper)obj;

		if (Objects.equals(_commitmentAnalysisResult,
					commitmentAnalysisResultWrapper._commitmentAnalysisResult)) {
			return true;
		}

		return false;
	}

	@Override
	public CommitmentAnalysisResult getWrappedModel() {
		return _commitmentAnalysisResult;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _commitmentAnalysisResult.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _commitmentAnalysisResult.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_commitmentAnalysisResult.resetOriginalValues();
	}

	private final CommitmentAnalysisResult _commitmentAnalysisResult;
}