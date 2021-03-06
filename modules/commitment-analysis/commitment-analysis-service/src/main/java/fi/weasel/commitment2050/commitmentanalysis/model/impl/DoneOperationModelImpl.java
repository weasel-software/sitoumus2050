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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation;
import fi.weasel.commitment2050.commitmentanalysis.model.DoneOperationModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the DoneOperation service. Represents a row in the &quot;CA_DoneOperation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link DoneOperationModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DoneOperationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DoneOperationImpl
 * @see DoneOperation
 * @see DoneOperationModel
 * @generated
 */
@ProviderType
public class DoneOperationModelImpl extends BaseModelImpl<DoneOperation>
	implements DoneOperationModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a done operation model instance should use the {@link DoneOperation} interface instead.
	 */
	public static final String TABLE_NAME = "CA_DoneOperation";
	public static final Object[][] TABLE_COLUMNS = {
			{ "id", Types.VARCHAR },
			{ "commitmentId", Types.VARCHAR },
			{ "operationId", Types.VARCHAR },
			{ "operationCategory", Types.VARCHAR },
			{ "operationTitleFI", Types.VARCHAR },
			{ "operationTitleSV", Types.VARCHAR },
			{ "operationTitleEN", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("id", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("commitmentId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("operationId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("operationCategory", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("operationTitleFI", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("operationTitleSV", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("operationTitleEN", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table CA_DoneOperation (id VARCHAR(75) not null primary key,commitmentId VARCHAR(75) null,operationId VARCHAR(75) null,operationCategory VARCHAR(75) null,operationTitleFI VARCHAR(75) null,operationTitleSV STRING null,operationTitleEN STRING null)";
	public static final String TABLE_SQL_DROP = "drop table CA_DoneOperation";
	public static final String ORDER_BY_JPQL = " ORDER BY doneOperation.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY CA_DoneOperation.id ASC";
	public static final String DATA_SOURCE = "caDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(fi.weasel.commitment2050.commitmentanalysis.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(fi.weasel.commitment2050.commitmentanalysis.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(fi.weasel.commitment2050.commitmentanalysis.service.util.ServiceProps.get(
				"lock.expiration.time.fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation"));

	public DoneOperationModelImpl() {
	}

	@Override
	public String getPrimaryKey() {
		return _id;
	}

	@Override
	public void setPrimaryKey(String primaryKey) {
		setId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _id;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((String)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return DoneOperation.class;
	}

	@Override
	public String getModelClassName() {
		return DoneOperation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("commitmentId", getCommitmentId());
		attributes.put("operationId", getOperationId());
		attributes.put("operationCategory", getOperationCategory());
		attributes.put("operationTitleFI", getOperationTitleFI());
		attributes.put("operationTitleSV", getOperationTitleSV());
		attributes.put("operationTitleEN", getOperationTitleEN());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String id = (String)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		String commitmentId = (String)attributes.get("commitmentId");

		if (commitmentId != null) {
			setCommitmentId(commitmentId);
		}

		String operationId = (String)attributes.get("operationId");

		if (operationId != null) {
			setOperationId(operationId);
		}

		String operationCategory = (String)attributes.get("operationCategory");

		if (operationCategory != null) {
			setOperationCategory(operationCategory);
		}

		String operationTitleFI = (String)attributes.get("operationTitleFI");

		if (operationTitleFI != null) {
			setOperationTitleFI(operationTitleFI);
		}

		String operationTitleSV = (String)attributes.get("operationTitleSV");

		if (operationTitleSV != null) {
			setOperationTitleSV(operationTitleSV);
		}

		String operationTitleEN = (String)attributes.get("operationTitleEN");

		if (operationTitleEN != null) {
			setOperationTitleEN(operationTitleEN);
		}
	}

	@Override
	public String getId() {
		if (_id == null) {
			return StringPool.BLANK;
		}
		else {
			return _id;
		}
	}

	@Override
	public void setId(String id) {
		_id = id;
	}

	@Override
	public String getCommitmentId() {
		if (_commitmentId == null) {
			return StringPool.BLANK;
		}
		else {
			return _commitmentId;
		}
	}

	@Override
	public void setCommitmentId(String commitmentId) {
		_commitmentId = commitmentId;
	}

	@Override
	public String getOperationId() {
		if (_operationId == null) {
			return StringPool.BLANK;
		}
		else {
			return _operationId;
		}
	}

	@Override
	public void setOperationId(String operationId) {
		_operationId = operationId;
	}

	@Override
	public String getOperationCategory() {
		if (_operationCategory == null) {
			return StringPool.BLANK;
		}
		else {
			return _operationCategory;
		}
	}

	@Override
	public void setOperationCategory(String operationCategory) {
		_operationCategory = operationCategory;
	}

	@Override
	public String getOperationTitleFI() {
		if (_operationTitleFI == null) {
			return StringPool.BLANK;
		}
		else {
			return _operationTitleFI;
		}
	}

	@Override
	public void setOperationTitleFI(String operationTitleFI) {
		_operationTitleFI = operationTitleFI;
	}

	@Override
	public String getOperationTitleSV() {
		if (_operationTitleSV == null) {
			return StringPool.BLANK;
		}
		else {
			return _operationTitleSV;
		}
	}

	@Override
	public void setOperationTitleSV(String operationTitleSV) {
		_operationTitleSV = operationTitleSV;
	}

	@Override
	public String getOperationTitleEN() {
		if (_operationTitleEN == null) {
			return StringPool.BLANK;
		}
		else {
			return _operationTitleEN;
		}
	}

	@Override
	public void setOperationTitleEN(String operationTitleEN) {
		_operationTitleEN = operationTitleEN;
	}

	@Override
	public DoneOperation toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (DoneOperation)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DoneOperationImpl doneOperationImpl = new DoneOperationImpl();

		doneOperationImpl.setId(getId());
		doneOperationImpl.setCommitmentId(getCommitmentId());
		doneOperationImpl.setOperationId(getOperationId());
		doneOperationImpl.setOperationCategory(getOperationCategory());
		doneOperationImpl.setOperationTitleFI(getOperationTitleFI());
		doneOperationImpl.setOperationTitleSV(getOperationTitleSV());
		doneOperationImpl.setOperationTitleEN(getOperationTitleEN());

		doneOperationImpl.resetOriginalValues();

		return doneOperationImpl;
	}

	@Override
	public int compareTo(DoneOperation doneOperation) {
		String primaryKey = doneOperation.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DoneOperation)) {
			return false;
		}

		DoneOperation doneOperation = (DoneOperation)obj;

		String primaryKey = doneOperation.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
	}

	@Override
	public CacheModel<DoneOperation> toCacheModel() {
		DoneOperationCacheModel doneOperationCacheModel = new DoneOperationCacheModel();

		doneOperationCacheModel.id = getId();

		String id = doneOperationCacheModel.id;

		if ((id != null) && (id.length() == 0)) {
			doneOperationCacheModel.id = null;
		}

		doneOperationCacheModel.commitmentId = getCommitmentId();

		String commitmentId = doneOperationCacheModel.commitmentId;

		if ((commitmentId != null) && (commitmentId.length() == 0)) {
			doneOperationCacheModel.commitmentId = null;
		}

		doneOperationCacheModel.operationId = getOperationId();

		String operationId = doneOperationCacheModel.operationId;

		if ((operationId != null) && (operationId.length() == 0)) {
			doneOperationCacheModel.operationId = null;
		}

		doneOperationCacheModel.operationCategory = getOperationCategory();

		String operationCategory = doneOperationCacheModel.operationCategory;

		if ((operationCategory != null) && (operationCategory.length() == 0)) {
			doneOperationCacheModel.operationCategory = null;
		}

		doneOperationCacheModel.operationTitleFI = getOperationTitleFI();

		String operationTitleFI = doneOperationCacheModel.operationTitleFI;

		if ((operationTitleFI != null) && (operationTitleFI.length() == 0)) {
			doneOperationCacheModel.operationTitleFI = null;
		}

		doneOperationCacheModel.operationTitleSV = getOperationTitleSV();

		String operationTitleSV = doneOperationCacheModel.operationTitleSV;

		if ((operationTitleSV != null) && (operationTitleSV.length() == 0)) {
			doneOperationCacheModel.operationTitleSV = null;
		}

		doneOperationCacheModel.operationTitleEN = getOperationTitleEN();

		String operationTitleEN = doneOperationCacheModel.operationTitleEN;

		if ((operationTitleEN != null) && (operationTitleEN.length() == 0)) {
			doneOperationCacheModel.operationTitleEN = null;
		}

		return doneOperationCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{id=");
		sb.append(getId());
		sb.append(", commitmentId=");
		sb.append(getCommitmentId());
		sb.append(", operationId=");
		sb.append(getOperationId());
		sb.append(", operationCategory=");
		sb.append(getOperationCategory());
		sb.append(", operationTitleFI=");
		sb.append(getOperationTitleFI());
		sb.append(", operationTitleSV=");
		sb.append(getOperationTitleSV());
		sb.append(", operationTitleEN=");
		sb.append(getOperationTitleEN());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append(
			"fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>id</column-name><column-value><![CDATA[");
		sb.append(getId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>commitmentId</column-name><column-value><![CDATA[");
		sb.append(getCommitmentId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>operationId</column-name><column-value><![CDATA[");
		sb.append(getOperationId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>operationCategory</column-name><column-value><![CDATA[");
		sb.append(getOperationCategory());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>operationTitleFI</column-name><column-value><![CDATA[");
		sb.append(getOperationTitleFI());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>operationTitleSV</column-name><column-value><![CDATA[");
		sb.append(getOperationTitleSV());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>operationTitleEN</column-name><column-value><![CDATA[");
		sb.append(getOperationTitleEN());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = DoneOperation.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			DoneOperation.class
		};
	private String _id;
	private String _commitmentId;
	private String _operationId;
	private String _operationCategory;
	private String _operationTitleFI;
	private String _operationTitleSV;
	private String _operationTitleEN;
	private DoneOperation _escapedModel;
}