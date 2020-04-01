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

package fi.weasel.commitment2050.commitmentanalysis.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import fi.weasel.commitment2050.commitmentanalysis.model.Commitment;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Commitment in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Commitment
 * @generated
 */
@ProviderType
public class CommitmentCacheModel implements CacheModel<Commitment>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommitmentCacheModel)) {
			return false;
		}

		CommitmentCacheModel commitmentCacheModel = (CommitmentCacheModel)obj;

		if (commitmentId.equals(commitmentCacheModel.commitmentId)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commitmentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(63);

		sb.append("{commitmentId=");
		sb.append(commitmentId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", organizationId=");
		sb.append(organizationId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", titleFI=");
		sb.append(titleFI);
		sb.append(", titleEN=");
		sb.append(titleEN);
		sb.append(", titleSV=");
		sb.append(titleSV);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", updated=");
		sb.append(updated);
		sb.append(", created=");
		sb.append(created);
		sb.append(", innovationFI=");
		sb.append(innovationFI);
		sb.append(", innovationEN=");
		sb.append(innovationEN);
		sb.append(", innovationSV=");
		sb.append(innovationSV);
		sb.append(", backgroundInformationFI=");
		sb.append(backgroundInformationFI);
		sb.append(", backgroundInformationEN=");
		sb.append(backgroundInformationEN);
		sb.append(", backgroundInformationSV=");
		sb.append(backgroundInformationSV);
		sb.append(", shortDescriptionFI=");
		sb.append(shortDescriptionFI);
		sb.append(", shortDescriptionEN=");
		sb.append(shortDescriptionEN);
		sb.append(", shortDescriptionSV=");
		sb.append(shortDescriptionSV);
		sb.append(", address=");
		sb.append(address);
		sb.append(", longitude=");
		sb.append(longitude);
		sb.append(", latitude=");
		sb.append(latitude);
		sb.append(", commitmentType=");
		sb.append(commitmentType);
		sb.append(", status=");
		sb.append(status);
		sb.append(", likes=");
		sb.append(likes);
		sb.append(", joined=");
		sb.append(joined);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Commitment toEntityModel() {
		CommitmentImpl commitmentImpl = new CommitmentImpl();

		if (commitmentId == null) {
			commitmentImpl.setCommitmentId(StringPool.BLANK);
		}
		else {
			commitmentImpl.setCommitmentId(commitmentId);
		}

		commitmentImpl.setGroupId(groupId);
		commitmentImpl.setCompanyId(companyId);

		if (organizationId == null) {
			commitmentImpl.setOrganizationId(StringPool.BLANK);
		}
		else {
			commitmentImpl.setOrganizationId(organizationId);
		}

		commitmentImpl.setUserId(userId);

		if (userName == null) {
			commitmentImpl.setUserName(StringPool.BLANK);
		}
		else {
			commitmentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commitmentImpl.setCreateDate(null);
		}
		else {
			commitmentImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commitmentImpl.setModifiedDate(null);
		}
		else {
			commitmentImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (titleFI == null) {
			commitmentImpl.setTitleFI(StringPool.BLANK);
		}
		else {
			commitmentImpl.setTitleFI(titleFI);
		}

		if (titleEN == null) {
			commitmentImpl.setTitleEN(StringPool.BLANK);
		}
		else {
			commitmentImpl.setTitleEN(titleEN);
		}

		if (titleSV == null) {
			commitmentImpl.setTitleSV(StringPool.BLANK);
		}
		else {
			commitmentImpl.setTitleSV(titleSV);
		}

		if (startDate == Long.MIN_VALUE) {
			commitmentImpl.setStartDate(null);
		}
		else {
			commitmentImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			commitmentImpl.setEndDate(null);
		}
		else {
			commitmentImpl.setEndDate(new Date(endDate));
		}

		if (updated == Long.MIN_VALUE) {
			commitmentImpl.setUpdated(null);
		}
		else {
			commitmentImpl.setUpdated(new Date(updated));
		}

		if (created == Long.MIN_VALUE) {
			commitmentImpl.setCreated(null);
		}
		else {
			commitmentImpl.setCreated(new Date(created));
		}

		if (innovationFI == null) {
			commitmentImpl.setInnovationFI(StringPool.BLANK);
		}
		else {
			commitmentImpl.setInnovationFI(innovationFI);
		}

		if (innovationEN == null) {
			commitmentImpl.setInnovationEN(StringPool.BLANK);
		}
		else {
			commitmentImpl.setInnovationEN(innovationEN);
		}

		if (innovationSV == null) {
			commitmentImpl.setInnovationSV(StringPool.BLANK);
		}
		else {
			commitmentImpl.setInnovationSV(innovationSV);
		}

		if (backgroundInformationFI == null) {
			commitmentImpl.setBackgroundInformationFI(StringPool.BLANK);
		}
		else {
			commitmentImpl.setBackgroundInformationFI(backgroundInformationFI);
		}

		if (backgroundInformationEN == null) {
			commitmentImpl.setBackgroundInformationEN(StringPool.BLANK);
		}
		else {
			commitmentImpl.setBackgroundInformationEN(backgroundInformationEN);
		}

		if (backgroundInformationSV == null) {
			commitmentImpl.setBackgroundInformationSV(StringPool.BLANK);
		}
		else {
			commitmentImpl.setBackgroundInformationSV(backgroundInformationSV);
		}

		if (shortDescriptionFI == null) {
			commitmentImpl.setShortDescriptionFI(StringPool.BLANK);
		}
		else {
			commitmentImpl.setShortDescriptionFI(shortDescriptionFI);
		}

		if (shortDescriptionEN == null) {
			commitmentImpl.setShortDescriptionEN(StringPool.BLANK);
		}
		else {
			commitmentImpl.setShortDescriptionEN(shortDescriptionEN);
		}

		if (shortDescriptionSV == null) {
			commitmentImpl.setShortDescriptionSV(StringPool.BLANK);
		}
		else {
			commitmentImpl.setShortDescriptionSV(shortDescriptionSV);
		}

		if (address == null) {
			commitmentImpl.setAddress(StringPool.BLANK);
		}
		else {
			commitmentImpl.setAddress(address);
		}

		commitmentImpl.setLongitude(longitude);
		commitmentImpl.setLatitude(latitude);

		if (commitmentType == null) {
			commitmentImpl.setCommitmentType(StringPool.BLANK);
		}
		else {
			commitmentImpl.setCommitmentType(commitmentType);
		}

		if (status == null) {
			commitmentImpl.setStatus(StringPool.BLANK);
		}
		else {
			commitmentImpl.setStatus(status);
		}

		commitmentImpl.setLikes(likes);
		commitmentImpl.setJoined(joined);

		commitmentImpl.resetOriginalValues();

		return commitmentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		commitmentId = objectInput.readUTF();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		organizationId = objectInput.readUTF();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		titleFI = objectInput.readUTF();
		titleEN = objectInput.readUTF();
		titleSV = objectInput.readUTF();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
		updated = objectInput.readLong();
		created = objectInput.readLong();
		innovationFI = objectInput.readUTF();
		innovationEN = objectInput.readUTF();
		innovationSV = objectInput.readUTF();
		backgroundInformationFI = objectInput.readUTF();
		backgroundInformationEN = objectInput.readUTF();
		backgroundInformationSV = objectInput.readUTF();
		shortDescriptionFI = objectInput.readUTF();
		shortDescriptionEN = objectInput.readUTF();
		shortDescriptionSV = objectInput.readUTF();
		address = objectInput.readUTF();

		longitude = objectInput.readDouble();

		latitude = objectInput.readDouble();
		commitmentType = objectInput.readUTF();
		status = objectInput.readUTF();

		likes = objectInput.readInt();

		joined = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (commitmentId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(commitmentId);
		}

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		if (organizationId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(organizationId);
		}

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (titleFI == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(titleFI);
		}

		if (titleEN == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(titleEN);
		}

		if (titleSV == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(titleSV);
		}

		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);
		objectOutput.writeLong(updated);
		objectOutput.writeLong(created);

		if (innovationFI == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(innovationFI);
		}

		if (innovationEN == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(innovationEN);
		}

		if (innovationSV == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(innovationSV);
		}

		if (backgroundInformationFI == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(backgroundInformationFI);
		}

		if (backgroundInformationEN == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(backgroundInformationEN);
		}

		if (backgroundInformationSV == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(backgroundInformationSV);
		}

		if (shortDescriptionFI == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(shortDescriptionFI);
		}

		if (shortDescriptionEN == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(shortDescriptionEN);
		}

		if (shortDescriptionSV == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(shortDescriptionSV);
		}

		if (address == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(address);
		}

		objectOutput.writeDouble(longitude);

		objectOutput.writeDouble(latitude);

		if (commitmentType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(commitmentType);
		}

		if (status == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(status);
		}

		objectOutput.writeInt(likes);

		objectOutput.writeInt(joined);
	}

	public String commitmentId;
	public long groupId;
	public long companyId;
	public String organizationId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String titleFI;
	public String titleEN;
	public String titleSV;
	public long startDate;
	public long endDate;
	public long updated;
	public long created;
	public String innovationFI;
	public String innovationEN;
	public String innovationSV;
	public String backgroundInformationFI;
	public String backgroundInformationEN;
	public String backgroundInformationSV;
	public String shortDescriptionFI;
	public String shortDescriptionEN;
	public String shortDescriptionSV;
	public String address;
	public double longitude;
	public double latitude;
	public String commitmentType;
	public String status;
	public int likes;
	public int joined;
}