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

import fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommitmentAnalysisResult in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see CommitmentAnalysisResult
 * @generated
 */
@ProviderType
public class CommitmentAnalysisResultCacheModel implements CacheModel<CommitmentAnalysisResult>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommitmentAnalysisResultCacheModel)) {
			return false;
		}

		CommitmentAnalysisResultCacheModel commitmentAnalysisResultCacheModel = (CommitmentAnalysisResultCacheModel)obj;

		if (id.equals(commitmentAnalysisResultCacheModel.id)) {
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
		StringBundler sb = new StringBundler(11);

		sb.append("{id=");
		sb.append(id);
		sb.append(", resultType=");
		sb.append(resultType);
		sb.append(", resultData=");
		sb.append(resultData);
		sb.append(", calculated=");
		sb.append(calculated);
		sb.append(", success=");
		sb.append(success);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommitmentAnalysisResult toEntityModel() {
		CommitmentAnalysisResultImpl commitmentAnalysisResultImpl = new CommitmentAnalysisResultImpl();

		if (id == null) {
			commitmentAnalysisResultImpl.setId(StringPool.BLANK);
		}
		else {
			commitmentAnalysisResultImpl.setId(id);
		}

		if (resultType == null) {
			commitmentAnalysisResultImpl.setResultType(StringPool.BLANK);
		}
		else {
			commitmentAnalysisResultImpl.setResultType(resultType);
		}

		if (resultData == null) {
			commitmentAnalysisResultImpl.setResultData(StringPool.BLANK);
		}
		else {
			commitmentAnalysisResultImpl.setResultData(resultData);
		}

		if (calculated == Long.MIN_VALUE) {
			commitmentAnalysisResultImpl.setCalculated(null);
		}
		else {
			commitmentAnalysisResultImpl.setCalculated(new Date(calculated));
		}

		commitmentAnalysisResultImpl.setSuccess(success);

		commitmentAnalysisResultImpl.resetOriginalValues();

		return commitmentAnalysisResultImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readUTF();
		resultType = objectInput.readUTF();
		resultData = objectInput.readUTF();
		calculated = objectInput.readLong();

		success = objectInput.readBoolean();
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

		if (resultType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(resultType);
		}

		if (resultData == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(resultData);
		}

		objectOutput.writeLong(calculated);

		objectOutput.writeBoolean(success);
	}

	public String id;
	public String resultType;
	public String resultData;
	public long calculated;
	public boolean success;
}