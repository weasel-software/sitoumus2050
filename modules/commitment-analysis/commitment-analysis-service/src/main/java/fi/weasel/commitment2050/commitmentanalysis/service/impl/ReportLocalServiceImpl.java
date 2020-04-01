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
import fi.weasel.commitment2050.commitmentanalysis.model.Report;
import fi.weasel.commitment2050.commitmentanalysis.service.ReportMeterLocalServiceUtil;
import fi.weasel.commitment2050.commitmentanalysis.service.base.ReportLocalServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The implementation of the report local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link fi.weasel.commitment2050.commitmentanalysis.service.ReportLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReportLocalServiceBaseImpl
 * @see fi.weasel.commitment2050.commitmentanalysis.service.ReportLocalServiceUtil
 */
public class ReportLocalServiceImpl extends ReportLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link fi.weasel.commitment2050.commitmentanalysis.service.ReportLocalServiceUtil} to access the report local service.
	 */

	private static Logger _logger = LoggerFactory.getLogger(ReportLocalServiceImpl.class);

	private SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
	private SimpleDateFormat isoFormatNoTime = new SimpleDateFormat("yyyy-MM-dd");

	public List<Report> reportsFromJSONArray(String commitmentID, JSONArray a) {
		List<Report> reports = new ArrayList<>();
		a.forEach(r -> {
			if (r instanceof JSONObject) {
				try {
					reports.add(reportFromJSONObject(commitmentID, (JSONObject) r));
				} catch (Exception e) {
					_logger.warn("Failed to parse Report: " + e.getLocalizedMessage());
				}
			}
		});
		return reports;
	}

	public Report reportFromJSONObject(String commitmentID, JSONObject o) {
		Report r = createReport(Long.toString(System.nanoTime()));

		r.setCommitmentId(commitmentID);

		r.setReportId(o.getString("id") != null? o.getString("id") : "");

		// Generic Reports have operation ID 'generic'
		r.setOperationId(o.getString("commitmentOperationRefId"));

		try {
			r.setCreatedByUserId(o.getInt("createdByUserId"));
		} catch (JSONException e) {
			_logger.warn("Setting createdByUserId to 0 for Report " + r.getReportId());
			r.setCreatedByUserId(0);
		}
		try {
			r.setCreatedByUserName(o.getString("createdByUserName"));
		} catch (JSONException e) {
			_logger.warn("Setting createdByUserName to '' for Report " + r.getReportId());
			r.setCreatedByUserName("");
		}

		try {
			r.setOrganizationName(o.getString("organizationName"));
		} catch (JSONException e) {
			_logger.warn("Setting organizationName to '' for Report " + r.getReportId());
			r.setOrganizationName("");
		}

		try {
			r.setProgress(o.getString("progress"));
		} catch (JSONException e) {
			_logger.warn("Setting progress to '' for Report " + r.getReportId());
			r.setProgress("");
		}

		try {
			r.setReportStatus(o.getBoolean("reportStatus"));
		} catch (JSONException e) {
			_logger.warn("Setting reportStatus to false for Report " + r.getReportId());
			r.setReportStatus(false);
		}

		try {
			r.setStatus(o.getString("status"));
		} catch (JSONException e) {
			_logger.warn("Setting status to '' for Report " + r.getReportId());
			r.setStatus("");
		}

		try {
			r.setVersion(o.getDouble("version"));
		} catch (JSONException e) {
			_logger.warn("Setting version to 0.0 for Report " + r.getReportId());
			r.setVersion(0.0);
		}

		try {
			Date startDate;
			if (o.getString("reportStartDate").contains("T")) {
				startDate = isoFormat.parse(o.getString("reportStartDate"));
			} else {
				startDate = isoFormatNoTime.parse(o.getString("reportStartDate"));
			}
			r.setReportStartDate(startDate);
		} catch (ParseException e) {
			_logger.warn(e.getMessage());
		}

		try {
			Date endDate;
			if (o.getString("reportEndDate").contains("T")) {
				endDate = isoFormat.parse(o.getString("reportEndDate"));
			} else {
				endDate = isoFormatNoTime.parse(o.getString("reportEndDate"));
			}
			r.setReportEndDate(endDate);
		} catch (ParseException e) {
			_logger.warn(e.getMessage());
		}

		r.setReportTextFI(o.getString("reportText_fi_FI"));
		r.setReportTextSV(o.getString("reportText_sv_SE"));
		r.setReportTextEN(o.getString("reportText_en_US"));

		r.setReportTitleFI(o.getString("reportTitle_fi_FI"));
		r.setReportTitleSV(o.getString("reportTitle_sv_SE"));
		r.setReportTitleEN(o.getString("reportTitle_en_US"));

		try {
			r.setReportMeters(ReportMeterLocalServiceUtil.reportMetersFromJSONArray(commitmentID, r.getOperationId(), r.getReportId(), o.getJSONArray("reportMeters")));
		} catch (JSONException e) {
			_logger.warn("Setting report meters to empty list because: " + e.getLocalizedMessage());
			r.setReportMeters(new ArrayList<>());
		}

		return r;
	}

	public void truncateTable() throws ORMException {
		Session session = getReportPersistence().openSession();
		Query query = session.createSQLQuery("truncate table CA_Report");
		query.executeUpdate();
	}

}