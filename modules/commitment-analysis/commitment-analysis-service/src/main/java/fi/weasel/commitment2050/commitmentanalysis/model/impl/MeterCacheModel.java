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

import fi.weasel.commitment2050.commitmentanalysis.model.Meter;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Meter in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Meter
 * @generated
 */
@ProviderType
public class MeterCacheModel implements CacheModel<Meter>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MeterCacheModel)) {
			return false;
		}

		MeterCacheModel meterCacheModel = (MeterCacheModel)obj;

		if (id.equals(meterCacheModel.id)) {
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
		StringBundler sb = new StringBundler(25);

		sb.append("{id=");
		sb.append(id);
		sb.append(", commitmentId=");
		sb.append(commitmentId);
		sb.append(", operationId=");
		sb.append(operationId);
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
		sb.append(", startingLevel=");
		sb.append(startingLevel);
		sb.append(", targetLevel=");
		sb.append(targetLevel);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Meter toEntityModel() {
		MeterImpl meterImpl = new MeterImpl();

		if (id == null) {
			meterImpl.setId(StringPool.BLANK);
		}
		else {
			meterImpl.setId(id);
		}

		if (commitmentId == null) {
			meterImpl.setCommitmentId(StringPool.BLANK);
		}
		else {
			meterImpl.setCommitmentId(commitmentId);
		}

		if (operationId == null) {
			meterImpl.setOperationId(StringPool.BLANK);
		}
		else {
			meterImpl.setOperationId(operationId);
		}

		if (meterId == null) {
			meterImpl.setMeterId(StringPool.BLANK);
		}
		else {
			meterImpl.setMeterId(meterId);
		}

		if (meterCategory == null) {
			meterImpl.setMeterCategory(StringPool.BLANK);
		}
		else {
			meterImpl.setMeterCategory(meterCategory);
		}

		if (meterChartType == null) {
			meterImpl.setMeterChartType(StringPool.BLANK);
		}
		else {
			meterImpl.setMeterChartType(meterChartType);
		}

		if (meterValueType == null) {
			meterImpl.setMeterValueType(StringPool.BLANK);
		}
		else {
			meterImpl.setMeterValueType(meterValueType);
		}

		if (meterTypeFI == null) {
			meterImpl.setMeterTypeFI(StringPool.BLANK);
		}
		else {
			meterImpl.setMeterTypeFI(meterTypeFI);
		}

		if (meterTypeSV == null) {
			meterImpl.setMeterTypeSV(StringPool.BLANK);
		}
		else {
			meterImpl.setMeterTypeSV(meterTypeSV);
		}

		if (meterTypeEN == null) {
			meterImpl.setMeterTypeEN(StringPool.BLANK);
		}
		else {
			meterImpl.setMeterTypeEN(meterTypeEN);
		}

		if (startingLevel == null) {
			meterImpl.setStartingLevel(StringPool.BLANK);
		}
		else {
			meterImpl.setStartingLevel(startingLevel);
		}

		if (targetLevel == null) {
			meterImpl.setTargetLevel(StringPool.BLANK);
		}
		else {
			meterImpl.setTargetLevel(targetLevel);
		}

		meterImpl.resetOriginalValues();

		return meterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readUTF();
		commitmentId = objectInput.readUTF();
		operationId = objectInput.readUTF();
		meterId = objectInput.readUTF();
		meterCategory = objectInput.readUTF();
		meterChartType = objectInput.readUTF();
		meterValueType = objectInput.readUTF();
		meterTypeFI = objectInput.readUTF();
		meterTypeSV = objectInput.readUTF();
		meterTypeEN = objectInput.readUTF();
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
	public String meterId;
	public String meterCategory;
	public String meterChartType;
	public String meterValueType;
	public String meterTypeFI;
	public String meterTypeSV;
	public String meterTypeEN;
	public String startingLevel;
	public String targetLevel;
}