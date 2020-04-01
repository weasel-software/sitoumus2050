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
 * This class is a wrapper for {@link Commitment}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Commitment
 * @generated
 */
@ProviderType
public class CommitmentWrapper implements Commitment, ModelWrapper<Commitment> {
	public CommitmentWrapper(Commitment commitment) {
		_commitment = commitment;
	}

	@Override
	public Class<?> getModelClass() {
		return Commitment.class;
	}

	@Override
	public String getModelClassName() {
		return Commitment.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("commitmentId", getCommitmentId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("organizationId", getOrganizationId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("titleFI", getTitleFI());
		attributes.put("titleEN", getTitleEN());
		attributes.put("titleSV", getTitleSV());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("updated", getUpdated());
		attributes.put("created", getCreated());
		attributes.put("innovationFI", getInnovationFI());
		attributes.put("innovationEN", getInnovationEN());
		attributes.put("innovationSV", getInnovationSV());
		attributes.put("backgroundInformationFI", getBackgroundInformationFI());
		attributes.put("backgroundInformationEN", getBackgroundInformationEN());
		attributes.put("backgroundInformationSV", getBackgroundInformationSV());
		attributes.put("shortDescriptionFI", getShortDescriptionFI());
		attributes.put("shortDescriptionEN", getShortDescriptionEN());
		attributes.put("shortDescriptionSV", getShortDescriptionSV());
		attributes.put("address", getAddress());
		attributes.put("longitude", getLongitude());
		attributes.put("latitude", getLatitude());
		attributes.put("commitmentType", getCommitmentType());
		attributes.put("status", getStatus());
		attributes.put("likes", getLikes());
		attributes.put("joined", getJoined());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String commitmentId = (String)attributes.get("commitmentId");

		if (commitmentId != null) {
			setCommitmentId(commitmentId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String organizationId = (String)attributes.get("organizationId");

		if (organizationId != null) {
			setOrganizationId(organizationId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String titleFI = (String)attributes.get("titleFI");

		if (titleFI != null) {
			setTitleFI(titleFI);
		}

		String titleEN = (String)attributes.get("titleEN");

		if (titleEN != null) {
			setTitleEN(titleEN);
		}

		String titleSV = (String)attributes.get("titleSV");

		if (titleSV != null) {
			setTitleSV(titleSV);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Date updated = (Date)attributes.get("updated");

		if (updated != null) {
			setUpdated(updated);
		}

		Date created = (Date)attributes.get("created");

		if (created != null) {
			setCreated(created);
		}

		String innovationFI = (String)attributes.get("innovationFI");

		if (innovationFI != null) {
			setInnovationFI(innovationFI);
		}

		String innovationEN = (String)attributes.get("innovationEN");

		if (innovationEN != null) {
			setInnovationEN(innovationEN);
		}

		String innovationSV = (String)attributes.get("innovationSV");

		if (innovationSV != null) {
			setInnovationSV(innovationSV);
		}

		String backgroundInformationFI = (String)attributes.get(
				"backgroundInformationFI");

		if (backgroundInformationFI != null) {
			setBackgroundInformationFI(backgroundInformationFI);
		}

		String backgroundInformationEN = (String)attributes.get(
				"backgroundInformationEN");

		if (backgroundInformationEN != null) {
			setBackgroundInformationEN(backgroundInformationEN);
		}

		String backgroundInformationSV = (String)attributes.get(
				"backgroundInformationSV");

		if (backgroundInformationSV != null) {
			setBackgroundInformationSV(backgroundInformationSV);
		}

		String shortDescriptionFI = (String)attributes.get("shortDescriptionFI");

		if (shortDescriptionFI != null) {
			setShortDescriptionFI(shortDescriptionFI);
		}

		String shortDescriptionEN = (String)attributes.get("shortDescriptionEN");

		if (shortDescriptionEN != null) {
			setShortDescriptionEN(shortDescriptionEN);
		}

		String shortDescriptionSV = (String)attributes.get("shortDescriptionSV");

		if (shortDescriptionSV != null) {
			setShortDescriptionSV(shortDescriptionSV);
		}

		String address = (String)attributes.get("address");

		if (address != null) {
			setAddress(address);
		}

		Double longitude = (Double)attributes.get("longitude");

		if (longitude != null) {
			setLongitude(longitude);
		}

		Double latitude = (Double)attributes.get("latitude");

		if (latitude != null) {
			setLatitude(latitude);
		}

		String commitmentType = (String)attributes.get("commitmentType");

		if (commitmentType != null) {
			setCommitmentType(commitmentType);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Integer likes = (Integer)attributes.get("likes");

		if (likes != null) {
			setLikes(likes);
		}

		Integer joined = (Integer)attributes.get("joined");

		if (joined != null) {
			setJoined(joined);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CommitmentWrapper((Commitment)_commitment.clone());
	}

	@Override
	public int compareTo(Commitment commitment) {
		return _commitment.compareTo(commitment);
	}

	/**
	* Returns the address of this commitment.
	*
	* @return the address of this commitment
	*/
	@Override
	public java.lang.String getAddress() {
		return _commitment.getAddress();
	}

	/**
	* Returns the background information en of this commitment.
	*
	* @return the background information en of this commitment
	*/
	@Override
	public java.lang.String getBackgroundInformationEN() {
		return _commitment.getBackgroundInformationEN();
	}

	/**
	* Returns the background information fi of this commitment.
	*
	* @return the background information fi of this commitment
	*/
	@Override
	public java.lang.String getBackgroundInformationFI() {
		return _commitment.getBackgroundInformationFI();
	}

	/**
	* Returns the background information sv of this commitment.
	*
	* @return the background information sv of this commitment
	*/
	@Override
	public java.lang.String getBackgroundInformationSV() {
		return _commitment.getBackgroundInformationSV();
	}

	/**
	* Returns the commitment ID of this commitment.
	*
	* @return the commitment ID of this commitment
	*/
	@Override
	public java.lang.String getCommitmentId() {
		return _commitment.getCommitmentId();
	}

	/**
	* Returns the commitment type of this commitment.
	*
	* @return the commitment type of this commitment
	*/
	@Override
	public java.lang.String getCommitmentType() {
		return _commitment.getCommitmentType();
	}

	/**
	* Returns the company ID of this commitment.
	*
	* @return the company ID of this commitment
	*/
	@Override
	public long getCompanyId() {
		return _commitment.getCompanyId();
	}

	/**
	* Returns the created of this commitment.
	*
	* @return the created of this commitment
	*/
	@Override
	public Date getCreated() {
		return _commitment.getCreated();
	}

	/**
	* Returns the create date of this commitment.
	*
	* @return the create date of this commitment
	*/
	@Override
	public Date getCreateDate() {
		return _commitment.getCreateDate();
	}

	@Override
	public java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation> getDoneOperations() {
		return _commitment.getDoneOperations();
	}

	/**
	* Returns the end date of this commitment.
	*
	* @return the end date of this commitment
	*/
	@Override
	public Date getEndDate() {
		return _commitment.getEndDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _commitment.getExpandoBridge();
	}

	@Override
	public java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.Report> getGenericReports() {
		return _commitment.getGenericReports();
	}

	/**
	* Returns the group ID of this commitment.
	*
	* @return the group ID of this commitment
	*/
	@Override
	public long getGroupId() {
		return _commitment.getGroupId();
	}

	/**
	* Returns the innovation en of this commitment.
	*
	* @return the innovation en of this commitment
	*/
	@Override
	public java.lang.String getInnovationEN() {
		return _commitment.getInnovationEN();
	}

	/**
	* Returns the innovation fi of this commitment.
	*
	* @return the innovation fi of this commitment
	*/
	@Override
	public java.lang.String getInnovationFI() {
		return _commitment.getInnovationFI();
	}

	/**
	* Returns the innovation sv of this commitment.
	*
	* @return the innovation sv of this commitment
	*/
	@Override
	public java.lang.String getInnovationSV() {
		return _commitment.getInnovationSV();
	}

	/**
	* Returns the joined of this commitment.
	*
	* @return the joined of this commitment
	*/
	@Override
	public int getJoined() {
		return _commitment.getJoined();
	}

	/**
	* Returns the latitude of this commitment.
	*
	* @return the latitude of this commitment
	*/
	@Override
	public double getLatitude() {
		return _commitment.getLatitude();
	}

	/**
	* Returns the likes of this commitment.
	*
	* @return the likes of this commitment
	*/
	@Override
	public int getLikes() {
		return _commitment.getLikes();
	}

	/**
	* Returns the longitude of this commitment.
	*
	* @return the longitude of this commitment
	*/
	@Override
	public double getLongitude() {
		return _commitment.getLongitude();
	}

	/**
	* Returns the modified date of this commitment.
	*
	* @return the modified date of this commitment
	*/
	@Override
	public Date getModifiedDate() {
		return _commitment.getModifiedDate();
	}

	@Override
	public java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.Operation> getOperations() {
		return _commitment.getOperations();
	}

	/**
	* Returns the organization ID of this commitment.
	*
	* @return the organization ID of this commitment
	*/
	@Override
	public java.lang.String getOrganizationId() {
		return _commitment.getOrganizationId();
	}

	/**
	* Returns the primary key of this commitment.
	*
	* @return the primary key of this commitment
	*/
	@Override
	public java.lang.String getPrimaryKey() {
		return _commitment.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commitment.getPrimaryKeyObj();
	}

	/**
	* Returns the short description en of this commitment.
	*
	* @return the short description en of this commitment
	*/
	@Override
	public java.lang.String getShortDescriptionEN() {
		return _commitment.getShortDescriptionEN();
	}

	/**
	* Returns the short description fi of this commitment.
	*
	* @return the short description fi of this commitment
	*/
	@Override
	public java.lang.String getShortDescriptionFI() {
		return _commitment.getShortDescriptionFI();
	}

	/**
	* Returns the short description sv of this commitment.
	*
	* @return the short description sv of this commitment
	*/
	@Override
	public java.lang.String getShortDescriptionSV() {
		return _commitment.getShortDescriptionSV();
	}

	/**
	* Returns the start date of this commitment.
	*
	* @return the start date of this commitment
	*/
	@Override
	public Date getStartDate() {
		return _commitment.getStartDate();
	}

	/**
	* Returns the status of this commitment.
	*
	* @return the status of this commitment
	*/
	@Override
	public java.lang.String getStatus() {
		return _commitment.getStatus();
	}

	/**
	* Returns the title en of this commitment.
	*
	* @return the title en of this commitment
	*/
	@Override
	public java.lang.String getTitleEN() {
		return _commitment.getTitleEN();
	}

	/**
	* Returns the title fi of this commitment.
	*
	* @return the title fi of this commitment
	*/
	@Override
	public java.lang.String getTitleFI() {
		return _commitment.getTitleFI();
	}

	/**
	* Returns the title sv of this commitment.
	*
	* @return the title sv of this commitment
	*/
	@Override
	public java.lang.String getTitleSV() {
		return _commitment.getTitleSV();
	}

	/**
	* Returns the updated of this commitment.
	*
	* @return the updated of this commitment
	*/
	@Override
	public Date getUpdated() {
		return _commitment.getUpdated();
	}

	/**
	* Returns the user ID of this commitment.
	*
	* @return the user ID of this commitment
	*/
	@Override
	public long getUserId() {
		return _commitment.getUserId();
	}

	/**
	* Returns the user name of this commitment.
	*
	* @return the user name of this commitment
	*/
	@Override
	public java.lang.String getUserName() {
		return _commitment.getUserName();
	}

	/**
	* Returns the user uuid of this commitment.
	*
	* @return the user uuid of this commitment
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _commitment.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _commitment.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _commitment.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _commitment.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _commitment.isNew();
	}

	@Override
	public void persist() {
		_commitment.persist();
	}

	/**
	* Sets the address of this commitment.
	*
	* @param address the address of this commitment
	*/
	@Override
	public void setAddress(java.lang.String address) {
		_commitment.setAddress(address);
	}

	/**
	* Sets the background information en of this commitment.
	*
	* @param backgroundInformationEN the background information en of this commitment
	*/
	@Override
	public void setBackgroundInformationEN(
		java.lang.String backgroundInformationEN) {
		_commitment.setBackgroundInformationEN(backgroundInformationEN);
	}

	/**
	* Sets the background information fi of this commitment.
	*
	* @param backgroundInformationFI the background information fi of this commitment
	*/
	@Override
	public void setBackgroundInformationFI(
		java.lang.String backgroundInformationFI) {
		_commitment.setBackgroundInformationFI(backgroundInformationFI);
	}

	/**
	* Sets the background information sv of this commitment.
	*
	* @param backgroundInformationSV the background information sv of this commitment
	*/
	@Override
	public void setBackgroundInformationSV(
		java.lang.String backgroundInformationSV) {
		_commitment.setBackgroundInformationSV(backgroundInformationSV);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_commitment.setCachedModel(cachedModel);
	}

	/**
	* Sets the commitment ID of this commitment.
	*
	* @param commitmentId the commitment ID of this commitment
	*/
	@Override
	public void setCommitmentId(java.lang.String commitmentId) {
		_commitment.setCommitmentId(commitmentId);
	}

	/**
	* Sets the commitment type of this commitment.
	*
	* @param commitmentType the commitment type of this commitment
	*/
	@Override
	public void setCommitmentType(java.lang.String commitmentType) {
		_commitment.setCommitmentType(commitmentType);
	}

	/**
	* Sets the company ID of this commitment.
	*
	* @param companyId the company ID of this commitment
	*/
	@Override
	public void setCompanyId(long companyId) {
		_commitment.setCompanyId(companyId);
	}

	/**
	* Sets the created of this commitment.
	*
	* @param created the created of this commitment
	*/
	@Override
	public void setCreated(Date created) {
		_commitment.setCreated(created);
	}

	/**
	* Sets the create date of this commitment.
	*
	* @param createDate the create date of this commitment
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_commitment.setCreateDate(createDate);
	}

	@Override
	public void setDoneOperations(
		java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation> doneOperations) {
		_commitment.setDoneOperations(doneOperations);
	}

	/**
	* Sets the end date of this commitment.
	*
	* @param endDate the end date of this commitment
	*/
	@Override
	public void setEndDate(Date endDate) {
		_commitment.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_commitment.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_commitment.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_commitment.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setGenericReports(
		java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.Report> genericReports) {
		_commitment.setGenericReports(genericReports);
	}

	/**
	* Sets the group ID of this commitment.
	*
	* @param groupId the group ID of this commitment
	*/
	@Override
	public void setGroupId(long groupId) {
		_commitment.setGroupId(groupId);
	}

	/**
	* Sets the innovation en of this commitment.
	*
	* @param innovationEN the innovation en of this commitment
	*/
	@Override
	public void setInnovationEN(java.lang.String innovationEN) {
		_commitment.setInnovationEN(innovationEN);
	}

	/**
	* Sets the innovation fi of this commitment.
	*
	* @param innovationFI the innovation fi of this commitment
	*/
	@Override
	public void setInnovationFI(java.lang.String innovationFI) {
		_commitment.setInnovationFI(innovationFI);
	}

	/**
	* Sets the innovation sv of this commitment.
	*
	* @param innovationSV the innovation sv of this commitment
	*/
	@Override
	public void setInnovationSV(java.lang.String innovationSV) {
		_commitment.setInnovationSV(innovationSV);
	}

	/**
	* Sets the joined of this commitment.
	*
	* @param joined the joined of this commitment
	*/
	@Override
	public void setJoined(int joined) {
		_commitment.setJoined(joined);
	}

	/**
	* Sets the latitude of this commitment.
	*
	* @param latitude the latitude of this commitment
	*/
	@Override
	public void setLatitude(double latitude) {
		_commitment.setLatitude(latitude);
	}

	/**
	* Sets the likes of this commitment.
	*
	* @param likes the likes of this commitment
	*/
	@Override
	public void setLikes(int likes) {
		_commitment.setLikes(likes);
	}

	/**
	* Sets the longitude of this commitment.
	*
	* @param longitude the longitude of this commitment
	*/
	@Override
	public void setLongitude(double longitude) {
		_commitment.setLongitude(longitude);
	}

	/**
	* Sets the modified date of this commitment.
	*
	* @param modifiedDate the modified date of this commitment
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_commitment.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_commitment.setNew(n);
	}

	@Override
	public void setOperations(
		java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.Operation> operations) {
		_commitment.setOperations(operations);
	}

	/**
	* Sets the organization ID of this commitment.
	*
	* @param organizationId the organization ID of this commitment
	*/
	@Override
	public void setOrganizationId(java.lang.String organizationId) {
		_commitment.setOrganizationId(organizationId);
	}

	/**
	* Sets the primary key of this commitment.
	*
	* @param primaryKey the primary key of this commitment
	*/
	@Override
	public void setPrimaryKey(java.lang.String primaryKey) {
		_commitment.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_commitment.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the short description en of this commitment.
	*
	* @param shortDescriptionEN the short description en of this commitment
	*/
	@Override
	public void setShortDescriptionEN(java.lang.String shortDescriptionEN) {
		_commitment.setShortDescriptionEN(shortDescriptionEN);
	}

	/**
	* Sets the short description fi of this commitment.
	*
	* @param shortDescriptionFI the short description fi of this commitment
	*/
	@Override
	public void setShortDescriptionFI(java.lang.String shortDescriptionFI) {
		_commitment.setShortDescriptionFI(shortDescriptionFI);
	}

	/**
	* Sets the short description sv of this commitment.
	*
	* @param shortDescriptionSV the short description sv of this commitment
	*/
	@Override
	public void setShortDescriptionSV(java.lang.String shortDescriptionSV) {
		_commitment.setShortDescriptionSV(shortDescriptionSV);
	}

	/**
	* Sets the start date of this commitment.
	*
	* @param startDate the start date of this commitment
	*/
	@Override
	public void setStartDate(Date startDate) {
		_commitment.setStartDate(startDate);
	}

	/**
	* Sets the status of this commitment.
	*
	* @param status the status of this commitment
	*/
	@Override
	public void setStatus(java.lang.String status) {
		_commitment.setStatus(status);
	}

	/**
	* Sets the title en of this commitment.
	*
	* @param titleEN the title en of this commitment
	*/
	@Override
	public void setTitleEN(java.lang.String titleEN) {
		_commitment.setTitleEN(titleEN);
	}

	/**
	* Sets the title fi of this commitment.
	*
	* @param titleFI the title fi of this commitment
	*/
	@Override
	public void setTitleFI(java.lang.String titleFI) {
		_commitment.setTitleFI(titleFI);
	}

	/**
	* Sets the title sv of this commitment.
	*
	* @param titleSV the title sv of this commitment
	*/
	@Override
	public void setTitleSV(java.lang.String titleSV) {
		_commitment.setTitleSV(titleSV);
	}

	/**
	* Sets the updated of this commitment.
	*
	* @param updated the updated of this commitment
	*/
	@Override
	public void setUpdated(Date updated) {
		_commitment.setUpdated(updated);
	}

	/**
	* Sets the user ID of this commitment.
	*
	* @param userId the user ID of this commitment
	*/
	@Override
	public void setUserId(long userId) {
		_commitment.setUserId(userId);
	}

	/**
	* Sets the user name of this commitment.
	*
	* @param userName the user name of this commitment
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_commitment.setUserName(userName);
	}

	/**
	* Sets the user uuid of this commitment.
	*
	* @param userUuid the user uuid of this commitment
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_commitment.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Commitment> toCacheModel() {
		return _commitment.toCacheModel();
	}

	@Override
	public Commitment toEscapedModel() {
		return new CommitmentWrapper(_commitment.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _commitment.toString();
	}

	@Override
	public Commitment toUnescapedModel() {
		return new CommitmentWrapper(_commitment.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _commitment.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommitmentWrapper)) {
			return false;
		}

		CommitmentWrapper commitmentWrapper = (CommitmentWrapper)obj;

		if (Objects.equals(_commitment, commitmentWrapper._commitment)) {
			return true;
		}

		return false;
	}

	@Override
	public Commitment getWrappedModel() {
		return _commitment;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _commitment.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _commitment.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_commitment.resetOriginalValues();
	}

	private final Commitment _commitment;
}