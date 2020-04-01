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

import fi.weasel.commitment2050.commitmentanalysis.model.ReportMeter;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ReportMeter in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ReportMeter
 * @generated
 */
@ProviderType
public class ReportMeterCacheModel implements CacheModel<ReportMeter>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ReportMeterCacheModel)) {
			return false;
		}

		ReportMeterCacheModel reportMeterCacheModel = (ReportMeterCacheModel)obj;

		if (id.equals(reportMeterCacheModel.id)) {
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
		StringBundler sb = new StringBundler(29);

		sb.append("{id=");
		sb.append(id);
		sb.append(", commitmentId=");
		sb.append(commitmentId);
		sb.append(", operationId=");
		sb.append(operationId);
		sb.append(", reportId=");
		sb.append(reportId);
		sb.append(", meterId=");
		sb.append(meterId);
		sb.append(", meterCategory=");
		sb.append(meterCategory);
		sb.append(", meterChartType=");
		sb.append(meterChartType);
		sb.append(", meterValueType=");
		sb.append(meterValueType);
		sb.append(", meterTypeFI=");
		sb.append(meterTypeFI);
		sb.append(", meterTypeSV=");
		sb.append(meterTypeSV);
		sb.append(", meterTypeEN=");
		sb.append(meterTypeEN);
		sb.append(", currentLevel=");
		sb.append(currentLevel);
		sb.append(", startingLevel=");
		sb.append(startingLevel);
		sb.append(", targetLevel=");
		sb.append(targetLevel);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ReportMeter toEntityModel() {
		ReportMeterImpl reportMeterImpl = new ReportMeterImpl();

		if (id == null) {
			reportMeterImpl.setId(StringPool.BLANK);
		}
		else {
			reportMeterImpl.setId(id);
		}

		if (commitmentId == null) {
			reportMeterImpl.setCommitmentId(StringPool.BLANK);
		}
		else {
			reportMeterImpl.setCommitmentId(commitmentId);
		}

		if (operationId == null) {
			reportMeterImpl.setOperationId(StringPool.BLANK);
		}
		else {
			reportMeterImpl.setOperationId(operationId);
		}

		if (reportId == null) {
			reportMeterImpl.setReportId(StringPool.BLANK);
		}
		else {
			reportMeterImpl.setReportId(reportId);
		}

		if (meterId == null) {
			reportMeterImpl.setMeterId(StringPool.BLANK);
		}
		else {
			reportMeterImpl.setMeterId(meterId);
		}

		if (meterCategory == null) {
			reportMeterImpl.setMeterCategory(StringPool.BLANK);
		}
		else {
			reportMeterImpl.setMeterCategory(meterCategory);
		}

		if (meterChartType == null) {
			reportMeterImpl.setMeterChartType(StringPool.BLANK);
		}
		else {
			reportMeterImpl.setMeterChartType(meterChartType);
		}

		if (meterValueType == null) {
			reportMeterImpl.setMeterValueType(StringPool.BLANK);
		}
		else {
			reportMeterImpl.setMeterValueType(meterValueType);
		}

		if (meterTypeFI == null) {
			reportMeterImpl.setMeterTypeFI(StringPool.BLANK);
		}
		else {
			reportMeterImpl.setMeterTypeFI(meterTypeFI);
		}

		if (meterTypeSV == null) {
			reportMeterImpl.setMeterTypeSV(StringPool.BLANK);
		}
		else {
			reportMeterImpl.setMeterTypeSV(meterTypeSV);
		}

		if (meterTypeEN == null) {
			reportMeterImpl.setMeterTypeEN(StringPool.BLANK);
		}
		else {
			reportMeterImpl.setMeterTypeEN(meterTypeEN);
		}

		if (currentLevel == null) {
			reportMeterImpl.setCurrentLevel(StringPool.BLANK);
		}
		else {
			reportMeterImpl.setCurrentLevel(currentLevel);
		}

		if (startingLevel == null) {
			reportMeterImpl.setStartingLevel(StringPool.BLANK);
		}
		else {
			reportMeterImpl.setStartingLevel(startingLevel);
		}

		if (targetLevel == null) {
			reportMeterImpl.setTargetLevel(StringPool.BLANK);
		}
		else {
			reportMeterImpl.setTargetLevel(targetLevel);
		}

		reportMeterImpl.resetOriginalValues();

		return reportMeterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readUTF();
		commitmentId = objectInput.readUTF();
		operationId = objectInput.readUTF();
		reportId = objectInput.readUTF();
		meterId = objectInput.readUTF();
		meterCategory = objectInput.readUTF();
		meterChartType = objectInput.readUTF();
		meterValueType = objectInput.readUTF();
		meterTypeFI = objectInput.readUTF();
		meterTypeSV = objectInput.readUTF();
		meterTypeEN = objectInput.readUTF();
		currentLevel = objectInput.readUTF();
		startingLevel = objectInput.readUTF();
		targetLevel = objectInput.readUTF();
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

		if (commitmentId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(commitmentId);
		}

		if (operationId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(operationId);
		}

		if (reportId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reportId);
		}

		if (meterId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(meterId);
		}

		if (meterCategory == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(meterCategory);
		}

		if (meterChartType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(meterChartType);
		}

		if (meterValueType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(meterValueType);
		}

		if (meterTypeFI == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(meterTypeFI);
		}

		if (meterTypeSV == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(meterTypeSV);
		}

		if (meterTypeEN == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(meterTypeEN);
		}

		if (currentLevel == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(currentLevel);
		}

		if (startingLevel == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(startingLevel);
		}

		if (targetLevel == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(targetLevel);
		}
	}

	public String id;
	public String commitmentId;
	public String operationId;
	public String reportId;
	public String meterId;
	public String meterCategory;
	public String meterChartType;
	public String meterValueType;
	public String meterTypeFI;
	public String meterTypeSV;
	public String meterTypeEN;
	public String currentLevel;
	public String startingLevel;
	public String targetLevel;
}