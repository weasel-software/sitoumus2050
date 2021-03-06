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

import fi.weasel.commitment2050.commitmentanalysis.model.Operation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Operation in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Operation
 * @generated
 */
@ProviderType
public class OperationCacheModel implements CacheModel<Operation>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OperationCacheModel)) {
			return false;
		}

		OperationCacheModel operationCacheModel = (OperationCacheModel)obj;

		if (id.equals(operationCacheModel.id)) {
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
		StringBundler sb = new StringBundler(21);

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
		sb.append(", operationDescriptionFI=");
		sb.append(operationDescriptionFI);
		sb.append(", operationDescriptionSV=");
		sb.append(operationDescriptionSV);
		sb.append(", operationDescriptionEN=");
		sb.append(operationDescriptionEN);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Operation toEntityModel() {
		OperationImpl operationImpl = new OperationImpl();

		if (id == null) {
			operationImpl.setId(StringPool.BLANK);
		}
		else {
			operationImpl.setId(id);
		}

		if (commitmentId == null) {
			operationImpl.setCommitmentId(StringPool.BLANK);
		}
		else {
			operationImpl.setCommitmentId(commitmentId);
		}

		if (operationId == null) {
			operationImpl.setOperationId(StringPool.BLANK);
		}
		else {
			operationImpl.setOperationId(operationId);
		}

		if (operationCategory == null) {
			operationImpl.setOperationCategory(StringPool.BLANK);
		}
		else {
			operationImpl.setOperationCategory(operationCategory);
		}

		if (operationTitleFI == null) {
			operationImpl.setOperationTitleFI(StringPool.BLANK);
		}
		else {
			operationImpl.setOperationTitleFI(operationTitleFI);
		}

		if (operationTitleSV == null) {
			operationImpl.setOperationTitleSV(StringPool.BLANK);
		}
		else {
			operationImpl.setOperationTitleSV(operationTitleSV);
		}

		if (operationTitleEN == null) {
			operationImpl.setOperationTitleEN(StringPool.BLANK);
		}
		else {
			operationImpl.setOperationTitleEN(operationTitleEN);
		}

		if (operationDescriptionFI == null) {
			operationImpl.setOperationDescriptionFI(StringPool.BLANK);
		}
		else {
			operationImpl.setOperationDescriptionFI(operationDescriptionFI);
		}

		if (operationDescriptionSV == null) {
			operationImpl.setOperationDescriptionSV(StringPool.BLANK);
		}
		else {
			operationImpl.setOperationDescriptionSV(operationDescriptionSV);
		}

		if (operationDescriptionEN == null) {
			operationImpl.setOperationDescriptionEN(StringPool.BLANK);
		}
		else {
			operationImpl.setOperationDescriptionEN(operationDescriptionEN);
		}

		operationImpl.resetOriginalValues();

		return operationImpl;
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
		operationDescriptionFI = objectInput.readUTF();
		operationDescriptionSV = objectInput.readUTF();
		operationDescriptionEN = objectInput.readUTF();
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

		if (operationDescriptionFI == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(operationDescriptionFI);
		}

		if (operationDescriptionSV == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(operationDescriptionSV);
		}

		if (operationDescriptionEN == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(operationDescriptionEN);
		}
	}

	public String id;
	public String commitmentId;
	public String operationId;
	public String operationCategory;
	public String operationTitleFI;
	public String operationTitleSV;
	public String operationTitleEN;
	public String operationDescriptionFI;
	public String operationDescriptionSV;
	public String operationDescriptionEN;
}