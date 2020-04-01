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
import fi.weasel.commitment2050.commitmentanalysis.model.Meter;
import fi.weasel.commitment2050.commitmentanalysis.model.Report;

import java.util.List;

/**
 * The extended model implementation for the Operation service. Represents a row in the &quot;CA_Operation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link fi.weasel.commitment2050.commitmentanalysis.model.Operation} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class OperationImpl extends OperationBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a operation model instance should use the {@link fi.weasel.commitment2050.commitmentanalysis.model.Operation} interface instead.
	 */
	public OperationImpl() {
	}

	private List<Meter> meters;
	private List<Report> reports;

	public List<Meter> getMeters() {
		return meters;
	}

	public void setMeters(List<Meter> meters) {
		this.meters = meters;
	}

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
}