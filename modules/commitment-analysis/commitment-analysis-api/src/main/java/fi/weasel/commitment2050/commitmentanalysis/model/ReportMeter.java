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
 * The extended model interface for the ReportMeter service. Represents a row in the &quot;CA_ReportMeter&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ReportMeterModel
 * @see fi.weasel.commitment2050.commitmentanalysis.model.impl.ReportMeterImpl
 * @see fi.weasel.commitment2050.commitmentanalysis.model.impl.ReportMeterModelImpl
 * @generated
 */
@ImplementationClassName("fi.weasel.commitment2050.commitmentanalysis.model.impl.ReportMeterImpl")
@ProviderType
public interface ReportMeter extends ReportMeterModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link fi.weasel.commitment2050.commitmentanalysis.model.impl.ReportMeterImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ReportMeter, String> ID_ACCESSOR = new Accessor<ReportMeter, String>() {
			@Override
			public String get(ReportMeter reportMeter) {
				return reportMeter.getId();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<ReportMeter> getTypeClass() {
				return ReportMeter.class;
			}
		};
}