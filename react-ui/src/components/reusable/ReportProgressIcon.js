// @flow

import React from "react";
import styled from "styled-components";
import { icon_path_report_progress } from "../../constants/constants";
import type { ReportProgressType } from "../../constants/types";

const StyledReportMoodIcon = styled.img`
  max-width: 40px;
  max-height: 40px;
  min-width: 40px;
  min-height: 40px;
  height: 40px;
  width: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 0;
  color: #93be38;
  flex: 0 0 auto;
`;

const ReportProgressIcon = ({
  icon,
  ...rest
}: {
  icon: ReportProgressType,
  rest?: any
}) => {
  if (!icon) return null;
  const link = `${icon_path_report_progress}/${icon}`;
  return <StyledReportMoodIcon {...rest} src={link} />;
};

export default ReportProgressIcon;
