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
import fi.weasel.commitment2050.commitmentanalysis.model.Meter;
import fi.weasel.commitment2050.commitmentanalysis.service.base.MeterLocalServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of the meter local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link fi.weasel.commitment2050.commitmentanalysis.service.MeterLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MeterLocalServiceBaseImpl
 * @see fi.weasel.commitment2050.commitmentanalysis.service.MeterLocalServiceUtil
 */
public class MeterLocalServiceImpl extends MeterLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link fi.weasel.commitment2050.commitmentanalysis.service.MeterLocalServiceUtil} to access the meter local service.
	 */

	private static Logger _logger = LoggerFactory.getLogger(MeterLocalServiceImpl.class);

	public List<Meter> metersFromJSONArray(String commitmentID, String operationId, JSONArray a) {
		List<Meter> meters = new ArrayList<>();

		a.forEach(o -> {
			if (o instanceof JSONObject) {
				try {
					meters.add(meterFromJSONObject(commitmentID, operationId, (JSONObject) o));
				} catch (Exception e) {
					_logger.warn("Failed to parse Meter: " + e.getLocalizedMessage());
				}
			}
		});

		return meters;
	}

	public Meter meterFromJSONObject(String commitmentID, String operationId, JSONObject o) {
		Meter m = createMeter(Long.toString(System.nanoTime()));

		m.setCommitmentId(commitmentID);

		m.setMeterId(o.getString("meterId") != null? o.getString("meterId") : "");

		m.setOperationId(operationId);

		try {
			m.setMeterCategory(o.getString("meterCategory"));
		} catch (JSONException e) {
			_logger.warn("Setting meterCategory to '' for Meter " + m.getMeterId());
			m.setMeterCategory("");
		}
		try {
			m.setMeterChartType(o.getString("meterChartType"));
		} catch (JSONException e) {
			_logger.warn("Setting meterChartType to '' for Meter " + m.getMeterId());
			m.setMeterChartType("");
		}

		try {
			m.setMeterValueType(o.getString("meterValueType"));
		} catch (JSONException e) {
			_logger.warn("Setting meterValueType to '' for Meter " + m.getMeterId());
			m.setMeterValueType("");
		}

		try {
			m.setStartingLevel(o.getString("startingLevel"));
		} catch (JSONException e) {
			_logger.warn("Setting startingLevel to '' for Meter " + m.getMeterId());
			m.setStartingLevel("");
		}
		try {
			m.setTargetLevel(o.getString("targetLevel"));
		} catch (JSONException e) {
			_logger.warn("Setting targetLevel to '' for Meter " + m.getMeterId());
			m.setTargetLevel("");
		}

		try {
			m.setMeterTypeFI(o.getString("meterType_fi_FI"));
		} catch (JSONException e) {
			_logger.warn("Setting meterTypeFI to '' for Meter " + m.getMeterId());
			m.setMeterTypeFI("");
		}
		try {
			m.setMeterTypeSV(o.getString("meterType_sv_SE"));
		} catch (JSONException e) {
			_logger.warn("Setting meterTypeSV to '' for Meter " + m.getMeterId());
			m.setMeterTypeSV("");
		}
		try {
			m.setMeterTypeEN(o.getString("meterType_en_US"));
		} catch (JSONException e) {
			_logger.warn("Setting meterTypeEN to '' for Meter " + m.getMeterId());
			m.setMeterTypeEN("");
		}

		return m;
	}

	public void truncateTable() throws ORMException {
		Session session = getMeterPersistence().openSession();
		Query query = session.createSQLQuery("truncate table CA_Meter");
		query.executeUpdate();
	}

}