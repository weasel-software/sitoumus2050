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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

/**
 * The base model interface for the DoneOperation service. Represents a row in the &quot;CA_DoneOperation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.DoneOperationModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.DoneOperationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DoneOperation
 * @see fi.weasel.commitment2050.commitmentanalysis.model.impl.DoneOperationImpl
 * @see fi.weasel.commitment2050.commitmentanalysis.model.impl.DoneOperationModelImpl
 * @generated
 */
@ProviderType
public interface DoneOperationModel extends BaseModel<DoneOperation> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a done operation model instance should use the {@link DoneOperation} interface instead.
	 */

	/**
	 * Returns the primary key of this done operation.
	 *
	 * @return the primary key of this done operation
	 */
	public String getPrimaryKey();

	/**
	 * Sets the primary key of this done operation.
	 *
	 * @param primaryKey the primary key of this done operation
	 */
	public void setPrimaryKey(String primaryKey);

	/**
	 * Returns the ID of this done operation.
	 *
	 * @return the ID of this done operation
	 */
	@AutoEscape
	public String getId();

	/**
	 * Sets the ID of this done operation.
	 *
	 * @param id the ID of this done operation
	 */
	public void setId(String id);

	/**
	 * Returns the commitment ID of this done operation.
	 *
	 * @return the commitment ID of this done operation
	 */
	@AutoEscape
	public String getCommitmentId();

	/**
	 * Sets the commitment ID of this done operation.
	 *
	 * @param commitmentId the commitment ID of this done operation
	 */
	public void setCommitmentId(String commitmentId);

	/**
	 * Returns the operation ID of this done operation.
	 *
	 * @return the operation ID of this done operation
	 */
	@AutoEscape
	public String getOperationId();

	/**
	 * Sets the operation ID of this done operation.
	 *
	 * @param operationId the operation ID of this done operation
	 */
	public void setOperationId(String operationId);

	/**
	 * Returns the operation category of this done operation.
	 *
	 * @return the operation category of this done operation
	 */
	@AutoEscape
	public String getOperationCategory();

	/**
	 * Sets the operation category of this done operation.
	 *
	 * @param operationCategory the operation category of this done operation
	 */
	public void setOperationCategory(String operationCategory);

	/**
	 * Returns the operation title fi of this done operation.
	 *
	 * @return the operation title fi of this done operation
	 */
	@AutoEscape
	public String getOperationTitleFI();

	/**
	 * Sets the operation title fi of this done operation.
	 *
	 * @param operationTitleFI the operation title fi of this done operation
	 */
	public void setOperationTitleFI(String operationTitleFI);

	/**
	 * Returns the operation title sv of this done operation.
	 *
	 * @return the operation title sv of this done operation
	 */
	@AutoEscape
	public String getOperationTitleSV();

	/**
	 * Sets the operation title sv of this done operation.
	 *
	 * @param operationTitleSV the operation title sv of this done operation
	 */
	public void setOperationTitleSV(String operationTitleSV);

	/**
	 * Returns the operation title en of this done operation.
	 *
	 * @return the operation title en of this done operation
	 */
	@AutoEscape
	public String getOperationTitleEN();

	/**
	 * Sets the operation title en of this done operation.
	 *
	 * @param operationTitleEN the operation title en of this done operation
	 */
	public void setOperationTitleEN(String operationTitleEN);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(DoneOperation doneOperation);

	@Override
	public int hashCode();

	@Override
	public CacheModel<DoneOperation> toCacheModel();

	@Override
	public DoneOperation toEscapedModel();

	@Override
	public DoneOperation toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}