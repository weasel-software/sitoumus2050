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
import fi.weasel.commitment2050.commitmentanalysis.model.ReportMeter;
import fi.weasel.commitment2050.commitmentanalysis.service.base.ReportMeterLocalServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of the report meter local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link fi.weasel.commitment2050.commitmentanalysis.service.ReportMeterLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReportMeterLocalServiceBaseImpl
 * @see fi.weasel.commitment2050.commitmentanalysis.service.ReportMeterLocalServiceUtil
 */
public class ReportMeterLocalServiceImpl extends ReportMeterLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link fi.weasel.commitment2050.commitmentanalysis.service.ReportMeterLocalServiceUtil} to access the report meter local service.
	 */

	private static Logger _logger = LoggerFactory.getLogger(ReportMeterLocalServiceImpl.class);

	public List<ReportMeter> reportMetersFromJSONArray(String commitmentID, String operationID, String reportID, JSONArray a) {
		List<ReportMeter> reportMeters = new ArrayList<>();

		a.forEach(m -> {
			if (m instanceof JSONObject) {
				try {
					reportMeters.add(reportMeterFromJSONObject(commitmentID, operationID, reportID, (JSONObject) m));
				} catch (Exception e) {
					_logger.warn("Failed to parse ReportMeter: " + e.getLocalizedMessage());
				}
			}
		});

		return reportMeters;
	}

	public ReportMeter reportMeterFromJSONObject(String commitmentID, String operationID, String reportID, JSONObject o) {
		ReportMeter m = createReportMeter(Long.toString(System.nanoTime()));

		m.setCommitmentId(commitmentID);

		m.setOperationId(operationID);

		m.setReportId(reportID);

		m.setMeterId(o.getString("commitmentOperationMeterRefId") != null?
				o.getString("commitmentOperationMeterRefId") : "");

		try {
			m.setMeterCategory(o.getString("meterCategory"));
		} catch (JSONException e) {
			_logger.warn("Setting meterCategory to '' for ReportMeter " + m.getMeterId());
			m.setMeterCategory("");
		}
		try {
			m.setMeterChartType(o.getString("meterChartType"));
		} catch (JSONException e) {
			_logger.warn("Setting meterChartType to '' for ReportMeter " + m.getMeterId());
			m.setMeterChartType("");
		}

		try {
			m.setMeterValueType(o.getString("meterValueType"));
		} catch (JSONException e) {
			_logger.warn("Setting meterValueType to '' for ReportMeter " + m.getMeterId());
			m.setMeterValueType("");
		}

		try {
			m.setStartingLevel(o.getString("startingLevel"));
		} catch (JSONException e) {
			_logger.warn("Setting startingLevel to '' for ReportMeter " + m.getMeterId());
			m.setStartingLevel("");
		}
		try {
			m.setTargetLevel(o.getString("targetLevel"));
		} catch (JSONException e) {
			_logger.warn("Setting targetLevel to '' for ReportMeter " + m.getMeterId());
			m.setTargetLevel("");
		}
		try {
			m.setCurrentLevel(o.getString("currentLevel"));
		} catch (JSONException e) {
			_logger.warn("Setting currentLevel to '' for ReportMeter " + m.getMeterId());
			m.setCurrentLevel("");
		}

		try {
			m.setMeterTypeFI(o.getString("meterType_fi_FI"));
		} catch (JSONException e) {
			_logger.warn("Setting meterTypeFI to '' for ReportMeter " + m.getMeterId());
			m.setMeterTypeFI("");
		}
		try {
			m.setMeterTypeSV(o.getString("meterType_sv_SE"));
		} catch (JSONException e) {
			_logger.warn("Setting meterTypeSV to '' for ReportMeter " + m.getMeterId());
			m.setMeterTypeSV("");
		}
		try {
			m.setMeterTypeEN(o.getString("meterType_en_US"));
		} catch (JSONException e) {
			_logger.warn("Setting meterTypeEN to '' for ReportMeter " + m.getMeterId());
			m.setMeterTypeEN("");
		}

		return m;
	}

	public void truncateTable() throws ORMException {
		Session session = getReportMeterPersistence().openSession();
		Query query = session.createSQLQuery("truncate table CA_ReportMeter");
		query.executeUpdate();
	}

}