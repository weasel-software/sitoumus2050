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

package fi.weasel.commitment2050.commitmentanalysis.service.impl;

import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.Session;
import fi.weasel.commitment2050.commitmentanalysis.model.Operation;
import fi.weasel.commitment2050.commitmentanalysis.service.MeterLocalServiceUtil;
import fi.weasel.commitment2050.commitmentanalysis.service.ReportLocalServiceUtil;
import fi.weasel.commitment2050.commitmentanalysis.service.base.OperationLocalServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of the operation local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link fi.weasel.commitment2050.commitmentanalysis.service.OperationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OperationLocalServiceBaseImpl
 * @see fi.weasel.commitment2050.commitmentanalysis.service.OperationLocalServiceUtil
 */
public class OperationLocalServiceImpl extends OperationLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link fi.weasel.commitment2050.commitmentanalysis.service.OperationLocalServiceUtil} to access the operation local service.
	 */

	private static Logger _logger = LoggerFactory.getLogger(OperationLocalServiceImpl.class);

	public List<Operation> operationsFromJSONArray(String commitmentID, JSONArray a) {
		List<Operation> operations = new ArrayList<>();
		a.forEach(o -> {
			if (o instanceof JSONObject) {
				try {
					operations.add(operationFromJSONObject(commitmentID, (JSONObject) o));
				} catch (Exception e) {
					_logger.warn("Failed to parse Operation: " + e.getLocalizedMessage());
				}
			}
		});

		return operations;
	}

	public Operation operationFromJSONObject(String commitmentID, JSONObject o) {
		Operation op = createOperation(Long.toString(System.nanoTime()));

		op.setCommitmentId(commitmentID);

		op.setOperationId(o.getString("operationId") != null? o.getString("operationId") : "");

		try {
			op.setOperationCategory(o.getString("operationCategory"));
		} catch (JSONException e) {
			_logger.warn("Setting operationCategory to '' for Operation " + op.getOperationId());
			op.setOperationCategory("");
		}

		try {
			op.setOperationTitleFI(o.getString("operationTitle_fi_FI"));
		} catch (JSONException e) {
			_logger.warn("Setting operationTitleFI to '' for Operation " + op.getOperationId());
			op.setOperationTitleFI("");
		}
		try {
			op.setOperationTitleSV(o.getString("operationTitle_sv_SE"));
		} catch (JSONException e) {
			_logger.warn("Setting operationTitleSV to '' for Operation " + op.getOperationId());
			op.setOperationTitleSV("");
		}
		try {
			op.setOperationTitleEN(o.getString("operationTitle_en_US"));
		} catch (JSONException e) {
			_logger.warn("Setting operationTitleEN to '' for Operation " + op.getOperationId());
			op.setOperationTitleEN("");
		}

		try {
			op.setOperationDescriptionFI(o.getString("operationDescription_fi_FI"));
		} catch (JSONException e) {
			_logger.warn("Setting operationDescriptionFI to '' for Operation " + op.getOperationId());
			op.setOperationDescriptionFI("");
		}
		try {
			op.setOperationDescriptionSV(o.getString("operationDescription_sv_SE"));
		} catch (JSONException e) {
			_logger.warn("Setting operationDescriptionSV to '' for Operation " + op.getOperationId());
			op.setOperationDescriptionSV("");
		}
		try {
			op.setOperationDescriptionEN(o.getString("operationDescription_en_US"));
		} catch (JSONException e) {
			_logger.warn("Setting operationDescriptionEN to '' for Operation " + op.getOperationId());
			op.setOperationDescriptionEN("");
		}

		try {
			op.setMeters(MeterLocalServiceUtil.metersFromJSONArray(commitmentID, op.getOperationId(), o.getJSONArray("meters")));
		} catch (JSONException e) {
			_logger.warn("Setting meters to empty list because: " + e.getLocalizedMessage());
			op.setMeters(new ArrayList<>());
		}

		try {
			op.setReports(ReportLocalServiceUtil.reportsFromJSONArray(commitmentID, o.getJSONArray("reports")));
		} catch (JSONException e) {
			_logger.warn("Setting reports to empty list because: " + e.getLocalizedMessage());
			op.setReports(new ArrayList<>());
		}

		return op;
	}

	public void truncateTable() throws ORMException {
		Session session = getOperationPersistence().openSession();
		Query query = session.createSQLQuery("truncate table CA_Operation");
		query.executeUpdate();
	}

}