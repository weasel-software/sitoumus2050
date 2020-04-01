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

import fi.weasel.commitment2050.commitmentanalysis.model.Report;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Report in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Report
 * @generated
 */
@ProviderType
public class ReportCacheModel implements CacheModel<Report>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReportCacheModel)) {
			return false;
		}

		ReportCacheModel reportCacheModel = (ReportCacheModel)obj;

		if (id.equals(reportCacheModel.id)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, id);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{id=");
		sb.append(id);
		sb.append(", reportId=");
		sb.append(reportId);
		sb.append(", operationId=");
		sb.append(operationId);
		sb.append(", commitmentId=");
		sb.append(commitmentId);
		sb.append(", createdByUserId=");
		sb.append(createdByUserId);
		sb.append(", createdByUserName=");
		sb.append(createdByUserName);
		sb.append(", organizationName=");
		sb.append(organizationName);
		sb.append(", progress=");
		sb.append(progress);
		sb.append(", reportStartDate=");
		sb.append(reportStartDate);
		sb.append(", reportEndDate=");
		sb.append(reportEndDate);
		sb.append(", reportStatus=");
		sb.append(reportStatus);
		sb.append(", reportTextFI=");
		sb.append(reportTextFI);
		sb.append(", reportTextSV=");
		sb.append(reportTextSV);
		sb.append(", reportTextEN=");
		sb.append(reportTextEN);
		sb.append(", reportTitleFI=");
		sb.append(reportTitleFI);
		sb.append(", reportTitleSV=");
		sb.append(reportTitleSV);
		sb.append(", reportTitleEN=");
		sb.append(reportTitleEN);
		sb.append(", status=");
		sb.append(status);
		sb.append(", version=");
		sb.append(version);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Report toEntityModel() {
		ReportImpl reportImpl = new ReportImpl();

		if (id == null) {
			reportImpl.setId(StringPool.BLANK);
		}
		else {
			reportImpl.setId(id);
		}

		if (reportId == null) {
			reportImpl.setReportId(StringPool.BLANK);
		}
		else {
			reportImpl.setReportId(reportId);
		}

		if (operationId == null) {
			reportImpl.setOperationId(StringPool.BLANK);
		}
		else {
			reportImpl.setOperationId(operationId);
		}

		if (commitmentId == null) {
			reportImpl.setCommitmentId(StringPool.BLANK);
		}
		else {
			reportImpl.setCommitmentId(commitmentId);
		}

		reportImpl.setCreatedByUserId(createdByUserId);

		if (createdByUserName == null) {
			reportImpl.setCreatedByUserName(StringPool.BLANK);
		}
		else {
			reportImpl.setCreatedByUserName(createdByUserName);
		}

		if (organizationName == null) {
			reportImpl.setOrganizationName(StringPool.BLANK);
		}
		else {
			reportImpl.setOrganizationName(organizationName);
		}

		if (progress == null) {
			reportImpl.setProgress(StringPool.BLANK);
		}
		else {
			reportImpl.setProgress(progress);
		}

		if (reportStartDate == Long.MIN_VALUE) {
			reportImpl.setReportStartDate(null);
		}
		else {
			reportImpl.setReportStartDate(new Date(reportStartDate));
		}

		if (reportEndDate == Long.MIN_VALUE) {
			reportImpl.setReportEndDate(null);
		}
		else {
			reportImpl.setReportEndDate(new Date(reportEndDate));
		}

		reportImpl.setReportStatus(reportStatus);

		if (reportTextFI == null) {
			reportImpl.setReportTextFI(StringPool.BLANK);
		}
		else {
			reportImpl.setReportTextFI(reportTextFI);
		}

		if (reportTextSV == null) {
			reportImpl.setReportTextSV(StringPool.BLANK);
		}
		else {
			reportImpl.setReportTextSV(reportTextSV);
		}

		if (reportTextEN == null) {
			reportImpl.setReportTextEN(StringPool.BLANK);
		}
		else {
			reportImpl.setReportTextEN(reportTextEN);
		}

		if (reportTitleFI == null) {
			reportImpl.setReportTitleFI(StringPool.BLANK);
		}
		else {
			reportImpl.setReportTitleFI(reportTitleFI);
		}

		if (reportTitleSV == null) {
			reportImpl.setReportTitleSV(StringPool.BLANK);
		}
		else {
			reportImpl.setReportTitleSV(reportTitleSV);
		}

		if (reportTitleEN == null) {
			reportImpl.setReportTitleEN(StringPool.BLANK);
		}
		else {
			reportImpl.setReportTitleEN(reportTitleEN);
		}

		if (status == null) {
			reportImpl.setStatus(StringPool.BLANK);
		}
		else {
			reportImpl.setStatus(status);
		}

		reportImpl.setVersion(version);

		reportImpl.resetOriginalValues();

		return reportImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readUTF();
		reportId = objectInput.readUTF();
		operationId = objectInput.readUTF();
		commitmentId = objectInput.readUTF();

		createdByUserId = objectInput.readInt();
		createdByUserName = objectInput.readUTF();
		organizationName = objectInput.readUTF();
		progress = objectInput.readUTF();
		reportStartDate = objectInput.readLong();
		reportEndDate = objectInput.readLong();

		reportStatus = objectInput.readBoolean();
		reportTextFI = objectInput.readUTF();
		reportTextSV = objectInput.readUTF();
		reportTextEN = objectInput.readUTF();
		reportTitleFI = objectInput.readUTF();
		reportTitleSV = objectInput.readUTF();
		reportTitleEN = objectInput.readUTF();
		status = objectInput.readUTF();

		version = objectInput.readDouble();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (id == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(id);
		}

		if (reportId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reportId);
		}

		if (operationId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(operationId);
		}

		if (commitmentId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(commitmentId);
		}

		objectOutput.writeInt(createdByUserId);

		if (createdByUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(createdByUserName);
		}

		if (organizationName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(organizationName);
		}

		if (progress == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(progress);
		}

		objectOutput.writeLong(reportStartDate);
		objectOutput.writeLong(reportEndDate);

		objectOutput.writeBoolean(reportStatus);

		if (reportTextFI == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reportTextFI);
		}

		if (reportTextSV == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reportTextSV);
		}

		if (reportTextEN == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reportTextEN);
		}

		if (reportTitleFI == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reportTitleFI);
		}

		if (reportTitleSV == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reportTitleSV);
		}

		if (reportTitleEN == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reportTitleEN);
		}

		if (status == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(status);
		}

		objectOutput.writeDouble(version);
	}

	public String id;
	public String reportId;
	public String operationId;
	public String commitmentId;
	public int createdByUserId;
	public String createdByUserName;
	public String organizationName;
	public String progress;
	public long reportStartDate;
	public long reportEndDate;
	public boolean reportStatus;
	public String reportTextFI;
	public String reportTextSV;
	public String reportTextEN;
	public String reportTitleFI;
	public String reportTitleSV;
	public String reportTitleEN;
	public String status;
	public double version;
}