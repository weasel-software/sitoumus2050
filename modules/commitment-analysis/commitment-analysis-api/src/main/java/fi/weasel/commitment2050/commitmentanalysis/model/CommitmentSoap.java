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
public class CommitmentSoap implements Serializable {
	public static CommitmentSoap toSoapModel(Commitment model) {
		CommitmentSoap soapModel = new CommitmentSoap();

		soapModel.setCommitmentId(model.getCommitmentId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setOrganizationId(model.getOrganizationId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTitleFI(model.getTitleFI());
		soapModel.setTitleEN(model.getTitleEN());
		soapModel.setTitleSV(model.getTitleSV());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setUpdated(model.getUpdated());
		soapModel.setCreated(model.getCreated());
		soapModel.setInnovationFI(model.getInnovationFI());
		soapModel.setInnovationEN(model.getInnovationEN());
		soapModel.setInnovationSV(model.getInnovationSV());
		soapModel.setBackgroundInformationFI(model.getBackgroundInformationFI());
		soapModel.setBackgroundInformationEN(model.getBackgroundInformationEN());
		soapModel.setBackgroundInformationSV(model.getBackgroundInformationSV());
		soapModel.setShortDescriptionFI(model.getShortDescriptionFI());
		soapModel.setShortDescriptionEN(model.getShortDescriptionEN());
		soapModel.setShortDescriptionSV(model.getShortDescriptionSV());
		soapModel.setAddress(model.getAddress());
		soapModel.setLongitude(model.getLongitude());
		soapModel.setLatitude(model.getLatitude());
		soapModel.setCommitmentType(model.getCommitmentType());
		soapModel.setStatus(model.getStatus());
		soapModel.setLikes(model.getLikes());
		soapModel.setJoined(model.getJoined());

		return soapModel;
	}

	public static CommitmentSoap[] toSoapModels(Commitment[] models) {
		CommitmentSoap[] soapModels = new CommitmentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommitmentSoap[][] toSoapModels(Commitment[][] models) {
		CommitmentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommitmentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommitmentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommitmentSoap[] toSoapModels(List<Commitment> models) {
		List<CommitmentSoap> soapModels = new ArrayList<CommitmentSoap>(models.size());

		for (Commitment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommitmentSoap[soapModels.size()]);
	}

	public CommitmentSoap() {
	}

	public String getPrimaryKey() {
		return _commitmentId;
	}

	public void setPrimaryKey(String pk) {
		setCommitmentId(pk);
	}

	public String getCommitmentId() {
		return _commitmentId;
	}

	public void setCommitmentId(String commitmentId) {
		_commitmentId = commitmentId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public String getOrganizationId() {
		return _organizationId;
	}

	public void setOrganizationId(String organizationId) {
		_organizationId = organizationId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getTitleFI() {
		return _titleFI;
	}

	public void setTitleFI(String titleFI) {
		_titleFI = titleFI;
	}

	public String getTitleEN() {
		return _titleEN;
	}

	public void setTitleEN(String titleEN) {
		_titleEN = titleEN;
	}

	public String getTitleSV() {
		return _titleSV;
	}

	public void setTitleSV(String titleSV) {
		_titleSV = titleSV;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public Date getUpdated() {
		return _updated;
	}

	public void setUpdated(Date updated) {
		_updated = updated;
	}

	public Date getCreated() {
		return _created;
	}

	public void setCreated(Date created) {
		_created = created;
	}

	public String getInnovationFI() {
		return _innovationFI;
	}

	public void setInnovationFI(String innovationFI) {
		_innovationFI = innovationFI;
	}

	public String getInnovationEN() {
		return _innovationEN;
	}

	public void setInnovationEN(String innovationEN) {
		_innovationEN = innovationEN;
	}

	public String getInnovationSV() {
		return _innovationSV;
	}

	public void setInnovationSV(String innovationSV) {
		_innovationSV = innovationSV;
	}

	public String getBackgroundInformationFI() {
		return _backgroundInformationFI;
	}

	public void setBackgroundInformationFI(String backgroundInformationFI) {
		_backgroundInformationFI = backgroundInformationFI;
	}

	public String getBackgroundInformationEN() {
		return _backgroundInformationEN;
	}

	public void setBackgroundInformationEN(String backgroundInformationEN) {
		_backgroundInformationEN = backgroundInformationEN;
	}

	public String getBackgroundInformationSV() {
		return _backgroundInformationSV;
	}

	public void setBackgroundInformationSV(String backgroundInformationSV) {
		_backgroundInformationSV = backgroundInformationSV;
	}

	public String getShortDescriptionFI() {
		return _shortDescriptionFI;
	}

	public void setShortDescriptionFI(String shortDescriptionFI) {
		_shortDescriptionFI = shortDescriptionFI;
	}

	public String getShortDescriptionEN() {
		return _shortDescriptionEN;
	}

	public void setShortDescriptionEN(String shortDescriptionEN) {
		_shortDescriptionEN = shortDescriptionEN;
	}

	public String getShortDescriptionSV() {
		return _shortDescriptionSV;
	}

	public void setShortDescriptionSV(String shortDescriptionSV) {
		_shortDescriptionSV = shortDescriptionSV;
	}

	public String getAddress() {
		return _address;
	}

	public void setAddress(String address) {
		_address = address;
	}

	public double getLongitude() {
		return _longitude;
	}

	public void setLongitude(double longitude) {
		_longitude = longitude;
	}

	public double getLatitude() {
		return _latitude;
	}

	public void setLatitude(double latitude) {
		_latitude = latitude;
	}

	public String getCommitmentType() {
		return _commitmentType;
	}

	public void setCommitmentType(String commitmentType) {
		_commitmentType = commitmentType;
	}

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public int getLikes() {
		return _likes;
	}

	public void setLikes(int likes) {
		_likes = likes;
	}

	public int getJoined() {
		return _joined;
	}

	public void setJoined(int joined) {
		_joined = joined;
	}

	private String _commitmentId;
	private long _groupId;
	private long _companyId;
	private String _organizationId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _titleFI;
	private String _titleEN;
	private String _titleSV;
	private Date _startDate;
	private Date _endDate;
	private Date _updated;
	private Date _created;
	private String _innovationFI;
	private String _innovationEN;
	private String _innovationSV;
	private String _backgroundInformationFI;
	private String _backgroundInformationEN;
	private String _backgroundInformationSV;
	private String _shortDescriptionFI;
	private String _shortDescriptionEN;
	private String _shortDescriptionSV;
	private String _address;
	private double _longitude;
	private double _latitude;
	private String _commitmentType;
	private String _status;
	private int _likes;
	private int _joined;
}