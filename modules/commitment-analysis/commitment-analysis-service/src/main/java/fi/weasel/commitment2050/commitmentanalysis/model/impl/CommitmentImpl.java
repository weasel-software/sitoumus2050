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
import fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation;
import fi.weasel.commitment2050.commitmentanalysis.model.Operation;
import fi.weasel.commitment2050.commitmentanalysis.model.Report;

import java.util.List;

/**
 * The extended model implementation for the Commitment service. Represents a row in the &quot;CommitmentAnalysis_Commitment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link fi.weasel.commitment2050.commitmentanalysis.model.Commitment} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class CommitmentImpl extends CommitmentBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a commitment model instance should use the {@link fi.weasel.commitment2050.commitmentanalysis.model.Commitment} interface instead.
	 */
	public CommitmentImpl() {
	}

	private List<Operation> operations;
	private List<DoneOperation> doneOperations;
	private List<Report> genericReports;

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public List<DoneOperation> getDoneOperations() {
		return doneOperations;
	}

	public void setDoneOperations(List<DoneOperation> doneOperations) {
		this.doneOperations = doneOperations;
	}

	public List<Report> getGenericReports() {
		return genericReports;
	}

	public void setGenericReports(List<Report> genericReports) {
		this.genericReports = genericReports;
	}
}