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

import fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing DoneOperation in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see DoneOperation
 * @generated
 */
@ProviderType
public class DoneOperationCacheModel implements CacheModel<DoneOperation>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DoneOperationCacheModel)) {
			return false;
		}

		DoneOperationCacheModel doneOperationCacheModel = (DoneOperationCacheModel)obj;

		if (id.equals(doneOperationCacheModel.id)) {
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
		StringBundler sb = new StringBundler(15);

		sb.append("{id=");
		sb.append(id);
		sb.append(", commitmentId=");
		sb.append(commitmentId);
		sb.append(", operationId=");
		sb.append(operationId);
		sb.append(", operationCategory=");
		sb.append(operationCategory);
		sb.append(", operationTitleFI=");
		sb.append(operationTitleFI);
		sb.append(", operationTitleSV=");
		sb.append(operationTitleSV);
		sb.append(", operationTitleEN=");
		sb.append(operationTitleEN);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DoneOperation toEntityModel() {
		DoneOperationImpl doneOperationImpl = new DoneOperationImpl();

		if (id == null) {
			doneOperationImpl.setId(StringPool.BLANK);
		}
		else {
			doneOperationImpl.setId(id);
		}

		if (commitmentId == null) {
			doneOperationImpl.setCommitmentId(StringPool.BLANK);
		}
		else {
			doneOperationImpl.setCommitmentId(commitmentId);
		}

		if (operationId == null) {
			doneOperationImpl.setOperationId(StringPool.BLANK);
		}
		else {
			doneOperationImpl.setOperationId(operationId);
		}

		if (operationCategory == null) {
			doneOperationImpl.setOperationCategory(StringPool.BLANK);
		}
		else {
			doneOperationImpl.setOperationCategory(operationCategory);
		}

		if (operationTitleFI == null) {
			doneOperationImpl.setOperationTitleFI(StringPool.BLANK);
		}
		else {
			doneOperationImpl.setOperationTitleFI(operationTitleFI);
		}

		if (operationTitleSV == null) {
			doneOperationImpl.setOperationTitleSV(StringPool.BLANK);
		}
		else {
			doneOperationImpl.setOperationTitleSV(operationTitleSV);
		}

		if (operationTitleEN == null) {
			doneOperationImpl.setOperationTitleEN(StringPool.BLANK);
		}
		else {
			doneOperationImpl.setOperationTitleEN(operationTitleEN);
		}

		doneOperationImpl.resetOriginalValues();

		return doneOperationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readUTF();
		commitmentId = objectInput.readUTF();
		operationId = objectInput.readUTF();
		operationCategory = objectInput.readUTF();
		operationTitleFI = objectInput.readUTF();
		operationTitleSV = objectInput.readUTF();
		operationTitleEN = objectInput.readUTF();
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

		if (operationCategory == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(operationCategory);
		}

		if (operationTitleFI == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(operationTitleFI);
		}

		if (operationTitleSV == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(operationTitleSV);
		}

		if (operationTitleEN == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(operationTitleEN);
		}
	}

	public String id;
	public String commitmentId;
	public String operationId;
	public String operationCategory;
	public String operationTitleFI;
	public String operationTitleSV;
	public String operationTitleEN;
}