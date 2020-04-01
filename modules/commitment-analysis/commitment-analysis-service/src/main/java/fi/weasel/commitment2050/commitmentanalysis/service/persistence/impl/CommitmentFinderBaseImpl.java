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

package fi.weasel.commitment2050.commitmentanalysis.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import fi.weasel.commitment2050.commitmentanalysis.model.Commitment;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.CommitmentPersistence;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CommitmentFinderBaseImpl extends BasePersistenceImpl<Commitment> {
	public CommitmentFinderBaseImpl() {
		setModelClass(Commitment.class);
	}

	/**
	 * Returns the commitment persistence.
	 *
	 * @return the commitment persistence
	 */
	public CommitmentPersistence getCommitmentPersistence() {
		return commitmentPersistence;
	}

	/**
	 * Sets the commitment persistence.
	 *
	 * @param commitmentPersistence the commitment persistence
	 */
	public void setCommitmentPersistence(
		CommitmentPersistence commitmentPersistence) {
		this.commitmentPersistence = commitmentPersistence;
	}

	@BeanReference(type = CommitmentPersistence.class)
	protected CommitmentPersistence commitmentPersistence;
}