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

import fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult;
import fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResultModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the CommitmentAnalysisResult service. Represents a row in the &quot;CA_Results&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CommitmentAnalysisResultModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommitmentAnalysisResultImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CommitmentAnalysisResultImpl
 * @see CommitmentAnalysisResult
 * @see CommitmentAnalysisResultModel
 * @generated
 */
@ProviderType
public class CommitmentAnalysisResultModelImpl extends BaseModelImpl<CommitmentAnalysisResult>
	implements CommitmentAnalysisResultModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commitment analysis result model instance should use the {@link CommitmentAnalysisResult} interface instead.
	 */
	public static final String TABLE_NAME = "CA_Results";
	public static final Object[][] TABLE_COLUMNS = {
			{ "id", Types.VARCHAR },
			{ "resultType", Types.VARCHAR },
			{ "resultData", Types.VARCHAR },
			{ "calculated", Types.TIMESTAMP },
			{ "success", Types.BOOLEAN }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("id", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("resultType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("resultData", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("calculated", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("success", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE = "create table CA_Results (id VARCHAR(75) not null primary key,resultType VARCHAR(75) null,resultData VARCHAR(75) null,calculated DATE null,success BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table CA_Results";
	public static final String ORDER_BY_JPQL = " ORDER BY commitmentAnalysisResult.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY CA_Results.id ASC";
	public static final String DATA_SOURCE = "caDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(fi.weasel.commitment2050.commitmentanalysis.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(fi.weasel.commitment2050.commitmentanalysis.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(fi.weasel.commitment2050.commitmentanalysis.service.util.ServiceProps.get(
				"lock.expiration.time.fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult"));

	public CommitmentAnalysisResultModelImpl() {
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
		return CommitmentAnalysisResult.class;
	}

	@Override
	public String getModelClassName() {
		return CommitmentAnalysisResult.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("resultType", getResultType());
		attributes.put("resultData", getResultData());
		attributes.put("calculated", getCalculated());
		attributes.put("success", getSuccess());

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

		String resultType = (String)attributes.get("resultType");

		if (resultType != null) {
			setResultType(resultType);
		}

		String resultData = (String)attributes.get("resultData");

		if (resultData != null) {
			setResultData(resultData);
		}

		Date calculated = (Date)attributes.get("calculated");

		if (calculated != null) {
			setCalculated(calculated);
		}

		Boolean success = (Boolean)attributes.get("success");

		if (success != null) {
			setSuccess(success);
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
	public String getResultType() {
		if (_resultType == null) {
			return StringPool.BLANK;
		}
		else {
			return _resultType;
		}
	}

	@Override
	public void setResultType(String resultType) {
		_resultType = resultType;
	}

	@Override
	public String getResultData() {
		if (_resultData == null) {
			return StringPool.BLANK;
		}
		else {
			return _resultData;
		}
	}

	@Override
	public void setResultData(String resultData) {
		_resultData = resultData;
	}

	@Override
	public Date getCalculated() {
		return _calculated;
	}

	@Override
	public void setCalculated(Date calculated) {
		_calculated = calculated;
	}

	@Override
	public boolean getSuccess() {
		return _success;
	}

	@Override
	public boolean isSuccess() {
		return _success;
	}

	@Override
	public void setSuccess(boolean success) {
		_success = success;
	}

	@Override
	public CommitmentAnalysisResult toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CommitmentAnalysisResult)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommitmentAnalysisResultImpl commitmentAnalysisResultImpl = new CommitmentAnalysisResultImpl();

		commitmentAnalysisResultImpl.setId(getId());
		commitmentAnalysisResultImpl.setResultType(getResultType());
		commitmentAnalysisResultImpl.setResultData(getResultData());
		commitmentAnalysisResultImpl.setCalculated(getCalculated());
		commitmentAnalysisResultImpl.setSuccess(getSuccess());

		commitmentAnalysisResultImpl.resetOriginalValues();

		return commitmentAnalysisResultImpl;
	}

	@Override
	public int compareTo(CommitmentAnalysisResult commitmentAnalysisResult) {
		String primaryKey = commitmentAnalysisResult.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommitmentAnalysisResult)) {
			return false;
		}

		CommitmentAnalysisResult commitmentAnalysisResult = (CommitmentAnalysisResult)obj;

		String primaryKey = commitmentAnalysisResult.getPrimaryKey();

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
	public CacheModel<CommitmentAnalysisResult> toCacheModel() {
		CommitmentAnalysisResultCacheModel commitmentAnalysisResultCacheModel = new CommitmentAnalysisResultCacheModel();

		commitmentAnalysisResultCacheModel.id = getId();

		String id = commitmentAnalysisResultCacheModel.id;

		if ((id != null) && (id.length() == 0)) {
			commitmentAnalysisResultCacheModel.id = null;
		}

		commitmentAnalysisResultCacheModel.resultType = getResultType();

		String resultType = commitmentAnalysisResultCacheModel.resultType;

		if ((resultType != null) && (resultType.length() == 0)) {
			commitmentAnalysisResultCacheModel.resultType = null;
		}

		commitmentAnalysisResultCacheModel.resultData = getResultData();

		String resultData = commitmentAnalysisResultCacheModel.resultData;

		if ((resultData != null) && (resultData.length() == 0)) {
			commitmentAnalysisResultCacheModel.resultData = null;
		}

		Date calculated = getCalculated();

		if (calculated != null) {
			commitmentAnalysisResultCacheModel.calculated = calculated.getTime();
		}
		else {
			commitmentAnalysisResultCacheModel.calculated = Long.MIN_VALUE;
		}

		commitmentAnalysisResultCacheModel.success = getSuccess();

		return commitmentAnalysisResultCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{id=");
		sb.append(getId());
		sb.append(", resultType=");
		sb.append(getResultType());
		sb.append(", resultData=");
		sb.append(getResultData());
		sb.append(", calculated=");
		sb.append(getCalculated());
		sb.append(", success=");
		sb.append(getSuccess());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append(
			"fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>id</column-name><column-value><![CDATA[");
		sb.append(getId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>resultType</column-name><column-value><![CDATA[");
		sb.append(getResultType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>resultData</column-name><column-value><![CDATA[");
		sb.append(getResultData());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>calculated</column-name><column-value><![CDATA[");
		sb.append(getCalculated());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>success</column-name><column-value><![CDATA[");
		sb.append(getSuccess());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = CommitmentAnalysisResult.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			CommitmentAnalysisResult.class
		};
	private String _id;
	private String _resultType;
	private String _resultData;
	private Date _calculated;
	private boolean _success;
	private CommitmentAnalysisResult _escapedModel;
}