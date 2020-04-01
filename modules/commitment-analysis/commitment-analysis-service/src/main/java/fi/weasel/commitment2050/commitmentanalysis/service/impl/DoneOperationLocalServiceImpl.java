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
import fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation;
import fi.weasel.commitment2050.commitmentanalysis.service.base.DoneOperationLocalServiceBaseImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of the done operation local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link fi.weasel.commitment2050.commitmentanalysis.service.DoneOperationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DoneOperationLocalServiceBaseImpl
 * @see fi.weasel.commitment2050.commitmentanalysis.service.DoneOperationLocalServiceUtil
 */
public class DoneOperationLocalServiceImpl
	extends DoneOperationLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link fi.weasel.commitment2050.commitmentanalysis.service.DoneOperationLocalServiceUtil} to access the done operation local service.
	 */

	private static Logger _logger = LoggerFactory.getLogger(DoneOperationLocalServiceImpl.class);

	public List<DoneOperation> doneOperationsFromJSONArray(String commitmentID, JSONArray a) {
		List<DoneOperation> doneOperations = new ArrayList<>();
		a.forEach(o -> {
			if (o instanceof JSONObject) {
				try {
					doneOperations.add(doneOperationFromJSONObject(commitmentID, (JSONObject) o));
				} catch (Exception e) {
					_logger.warn("Failed to parse DoneOperation: " + e.getLocalizedMessage());
				}
			}
		});

		return doneOperations;
	}

	public DoneOperation doneOperationFromJSONObject(String commitmentID, JSONObject o) {
		DoneOperation op = createDoneOperation(Long.toString(System.nanoTime()));

		op.setCommitmentId(commitmentID);

		op.setOperationId(o.getString("operationId") != null? o.getString("operationId") : "");

		try {
			op.setOperationCategory(o.getString("operationCategory"));
		} catch (JSONException e) {
			_logger.warn("Setting operationCategory to '' for DoneOperation " + op.getOperationId());
			op.setOperationCategory("");
		}

		try {
			op.setOperationTitleFI(o.getString("operationTitle_fi_FI"));
		} catch (JSONException e) {
			_logger.warn("Setting operationTitleFI to '' for DoneOperation " + op.getOperationId());
			op.setOperationTitleFI("");
		}
		try {
			op.setOperationTitleSV(o.getString("operationTitle_sv_SE"));
		} catch (JSONException e) {
			_logger.warn("Setting operationTitleSV to '' for DoneOperation " + op.getOperationId());
			op.setOperationTitleSV("");
		}
		try {
			op.setOperationTitleEN(o.getString("operationTitle_en_US"));
		} catch (JSONException e) {
			_logger.warn("Setting operationTitleEN to '' for DoneOperation " + op.getOperationId());
			op.setOperationTitleEN("");
		}

		return op;
	}

	public void truncateTable() throws ORMException {
		Session session = getDoneOperationPersistence().openSession();
		Query query = session.createSQLQuery("truncate table CA_DoneOperation");
		query.executeUpdate();
	}

}