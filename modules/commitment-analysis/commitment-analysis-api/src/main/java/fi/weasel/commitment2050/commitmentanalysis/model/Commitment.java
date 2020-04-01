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

package fi.weasel.commitment2050.commitmentanalysis.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Commitment service. Represents a row in the &quot;CA_Commitment&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see CommitmentModel
 * @see fi.weasel.commitment2050.commitmentanalysis.model.impl.CommitmentImpl
 * @see fi.weasel.commitment2050.commitmentanalysis.model.impl.CommitmentModelImpl
 * @generated
 */
@ImplementationClassName("fi.weasel.commitment2050.commitmentanalysis.model.impl.CommitmentImpl")
@ProviderType
public interface Commitment extends CommitmentModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.CommitmentImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Commitment, String> COMMITMENT_ID_ACCESSOR = new Accessor<Commitment, String>() {
			@Override
			public String get(Commitment commitment) {
				return commitment.getCommitmentId();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<Commitment> getTypeClass() {
				return Commitment.class;
			}
		};

	public java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.Operation> getOperations();

	public void setOperations(
		java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.Operation> operations);

	public java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation> getDoneOperations();

	public void setDoneOperations(
		java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.DoneOperation> doneOperations);

	public java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.Report> getGenericReports();

	public void setGenericReports(
		java.util.List<fi.weasel.commitment2050.commitmentanalysis.model.Report> genericReports);
}