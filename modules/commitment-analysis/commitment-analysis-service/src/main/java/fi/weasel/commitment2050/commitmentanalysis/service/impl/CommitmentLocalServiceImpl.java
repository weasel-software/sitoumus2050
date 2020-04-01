/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser generic Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser generic Public License for more
 * details.
 */

package fi.weasel.commitment2050.commitmentanalysis.service.impl;

import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import fi.weasel.commitment2050.commitmentanalysis.model.*;
import fi.weasel.commitment2050.commitmentanalysis.service.*;
import fi.weasel.commitment2050.commitmentanalysis.service.base.CommitmentLocalServiceBaseImpl;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The implementation of the commitment local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link fi.weasel.commitment2050.commitmentanalysis.service.CommitmentLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CommitmentLocalServiceBaseImpl
 * @see fi.weasel.commitment2050.commitmentanalysis.service.CommitmentLocalServiceUtil
 */
public class CommitmentLocalServiceImpl extends CommitmentLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link fi.weasel.commitment2050.commitmentanalysis.service.CommitmentLocalServiceUtil} to access the commitment local service.
	 */

	private static Logger _logger = LoggerFactory.getLogger(CommitmentLocalServiceImpl.class);


	private SimpleDateFormat mediumFormatter = new SimpleDateFormat("MMM dd',' yyyy hh:mm:ss a");
	private SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
	private SimpleDateFormat isoFormatNoTime = new SimpleDateFormat("yyyy-MM-dd");

	public Commitment addCommitmentAsJSON(JSONObject data) {
        Commitment c = commitmentFromJSON(data);

        if (c.isNew()) {
        	_logger.info("Inserting Commitment with ID " + c.getCommitmentId());
        	addCommitment(c);
		} else {
			_logger.info("Updating Commitment with ID " + c.getCommitmentId());
			updateCommitment(c);
		}

		for (Operation operation : c.getOperations()) {

			if (operation.isNew()) {
				_logger.info("Inserting Operation with ID " + operation.getOperationId());
				OperationLocalServiceUtil.addOperation(operation);
			} else {
				_logger.info("Updating Operation with ID " + operation.getOperationId());
				OperationLocalServiceUtil.updateOperation(operation);
			}

			for (Meter meter : operation.getMeters()) {
				if (meter.isNew()) {
					_logger.info("Inserting Meter with ID " + meter.getMeterId());
					MeterLocalServiceUtil.addMeter(meter);
				} else {
					_logger.info("Updating Meter with ID " + meter.getMeterId());
					MeterLocalServiceUtil.updateMeter(meter);
				}
			}

			for (Report report : operation.getReports()) {
				if (report.isNew()) {
					_logger.info("Inserting Report with ID " + report.getReportId());
					ReportLocalServiceUtil.addReport(report);
				} else {
					_logger.info("Updating Report with ID " + report.getReportId());
					ReportLocalServiceUtil.updateReport(report);
				}

				for (ReportMeter reportMeter : report.getReportMeters()) {
					if (reportMeter.isNew()) {
						_logger.info("Inserting ReportMeter for Report with ID " + reportMeter.getMeterId());
						ReportMeterLocalServiceUtil.addReportMeter(reportMeter);
					} else {
						_logger.info("Updating ReportMeter for Report with ID " + reportMeter.getMeterId());
						ReportMeterLocalServiceUtil.updateReportMeter(reportMeter);
					}
				}
			}
		}

		for (DoneOperation doneOperation : c.getDoneOperations()) {

			if (doneOperation.isNew()) {
				_logger.info("Inserting DoneOperation with ID " + doneOperation.getOperationId());
				DoneOperationLocalServiceUtil.addDoneOperation(doneOperation);
			} else {
				_logger.info("Updating DoneOperation with ID " + doneOperation.getOperationId());
				DoneOperationLocalServiceUtil.updateDoneOperation(doneOperation);
			}
		}

		for (Report genericReport : c.getGenericReports()) {
			if (genericReport.isNew()) {
				_logger.info("Inserting generic Report with ID " + genericReport.getReportId());
				ReportLocalServiceUtil.addReport(genericReport);
			} else {
				_logger.info("Updating generic Report with ID " + genericReport.getReportId());
				ReportLocalServiceUtil.updateReport(genericReport);
			}

			for (ReportMeter reportMeter : genericReport.getReportMeters()) {
				if (reportMeter.isNew()) {
					_logger.info("Inserting ReportMeter for generic Report with ID " + reportMeter.getMeterId());
					ReportMeterLocalServiceUtil.addReportMeter(reportMeter);
				} else {
					_logger.info("Updating ReportMeter for generic Report with ID " + reportMeter.getMeterId());
					ReportMeterLocalServiceUtil.updateReportMeter(reportMeter);
				}
			}
		}

		return c;
	}

	private Commitment commitmentFromJSON(org.json.JSONObject o) {
		Commitment c = createCommitment(o.getString("id"));

		c.setGroupId(o.getInt("groupId"));

		c.setAddress(o.getString("address"));
		try {
			c.setLatitude(o.getDouble("latitude"));
		} catch (JSONException e) {
			_logger.warn(e.getMessage());
		}
		try {
			c.setLongitude(o.getDouble("longitude"));
		} catch (JSONException e) {
			_logger.warn(e.getMessage());
		}

		try {
			c.setOrganizationId(o.getString("organizationId"));
		} catch (JSONException e) {
			_logger.info("Setting organization ID to 0");
		}

		c.setUserId(o.getLong("createdByUserId"));
		c.setUserName(o.getString("createdByUserName"));

		try {
			Date createdDate = mediumFormatter.parse(o.getString("created"));
			c.setCreated(createdDate);
		} catch (ParseException e) {
			_logger.warn(e.getMessage());
		}
		try {
			Date updatedDate = mediumFormatter.parse(o.getString("updated"));
			c.setUpdated(updatedDate);
		} catch (ParseException e) {
			_logger.warn(e.getMessage());
		}

		try {
			Date startDate;
			if (o.getString("startDate").contains("T")) {
				startDate = isoFormat.parse(o.getString("startDate"));
			} else {
				startDate = isoFormatNoTime.parse(o.getString("startDate"));
			}
			c.setStartDate(startDate);
		} catch (ParseException e) {
			_logger.warn(e.getMessage());
		}
		try {
			Date endDate;
			if (o.getString("endDate").contains("T")) {
				endDate = isoFormat.parse(o.getString("endDate"));
			} else {
				endDate = isoFormatNoTime.parse(o.getString("endDate"));
			}
			c.setEndDate(endDate);
		} catch (ParseException e) {
			_logger.warn(e.getMessage());
		}

		c.setTitleFI(o.getString("title_fi_FI"));
		c.setTitleSV(o.getString("title_sv_SE"));
		c.setTitleEN(o.getString("title_en_US"));

		c.setShortDescriptionFI(o.getString("shortDescription_fi_FI"));
		c.setShortDescriptionSV(o.getString("shortDescription_sv_SE"));
		c.setShortDescriptionEN(o.getString("shortDescription_en_US"));

		c.setBackgroundInformationFI(o.getString("backgroundInformation_fi_FI"));
		c.setBackgroundInformationSV(o.getString("backgroundInformation_sv_SE"));
		c.setBackgroundInformationEN(o.getString("backgroundInformation_en_US"));

		c.setInnovationFI(o.getString("innovation_fi_FI"));
		c.setInnovationSV(o.getString("innovation_sv_SE"));
		c.setInnovationEN(o.getString("innovation_en_US"));

		c.setStatus(o.getString("status"));
		c.setLikes(o.getInt("likes"));
		c.setCommitmentType(o.getString("commitmentType"));

		try {
			c.setOperations(OperationLocalServiceUtil.operationsFromJSONArray(c.getCommitmentId(), o.getJSONArray("operations")));
		} catch (JSONException e) {
			c.setOperations(new ArrayList<>());
		}

		try {
			c.setDoneOperations(DoneOperationLocalServiceUtil.doneOperationsFromJSONArray(c.getCommitmentId(), o.getJSONArray("doneOperations")));
		} catch (JSONException e) {
			c.setDoneOperations(new ArrayList<>());
		}

		try {
			c.setGenericReports(ReportLocalServiceUtil.reportsFromJSONArray(c.getCommitmentId(), o.getJSONArray("genericReports")));
		} catch (JSONException e) {
			_logger.warn("Setting generic reports to empty list because: " + e.getLocalizedMessage());
			c.setGenericReports(new ArrayList<>());
		}

		return c;
	}

	public List<Commitment> findByUserId(long userId) throws SystemException {
		return commitmentFinder.findByUserId(userId);
	}

	public List getAvgReduction() throws SystemException {
		return commitmentFinder.getAverageReduction();
	}
	public List getAvgReductionByMeter() throws SystemException {
		return commitmentFinder.getAvgReductionByMeter();
	}
	public List getCountForAllTypes() throws SystemException {
		return commitmentFinder.getCountForAllTypes();
	}

	public void truncateTable() throws ORMException {
		Session session = getCommitmentPersistence().openSession();
		Query query = session.createSQLQuery("truncate table CA_Commitment");
		query.executeUpdate();
	}

}