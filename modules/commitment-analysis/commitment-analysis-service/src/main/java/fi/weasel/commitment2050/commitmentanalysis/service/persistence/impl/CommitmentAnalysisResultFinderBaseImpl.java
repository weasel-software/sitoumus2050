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

import fi.weasel.commitment2050.commitmentanalysis.model.CommitmentAnalysisResult;
import fi.weasel.commitment2050.commitmentanalysis.service.persistence.CommitmentAnalysisResultPersistence;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CommitmentAnalysisResultFinderBaseImpl extends BasePersistenceImpl<CommitmentAnalysisResult> {
	public CommitmentAnalysisResultFinderBaseImpl() {
		setModelClass(CommitmentAnalysisResult.class);
	}

	/**
	 * Returns the commitment analysis result persistence.
	 *
	 * @return the commitment analysis result persistence
	 */
	public CommitmentAnalysisResultPersistence getCommitmentAnalysisResultPersistence() {
		return commitmentAnalysisResultPersistence;
	}

	/**
	 * Sets the commitment analysis result persistence.
	 *
	 * @param commitmentAnalysisResultPersistence the commitment analysis result persistence
	 */
	public void setCommitmentAnalysisResultPersistence(
		CommitmentAnalysisResultPersistence commitmentAnalysisResultPersistence) {
		this.commitmentAnalysisResultPersistence = commitmentAnalysisResultPersistence;
	}

	@BeanReference(type = CommitmentAnalysisResultPersistence.class)
	protected CommitmentAnalysisResultPersistence commitmentAnalysisResultPersistence;
}