// @flow

import React from "react";
import styled from "styled-components";
import { icon_path_commitment2050_goals } from "../../constants/constants";

const StyledGoalIcon = styled.img`
  width: 80px;
  min-width: 80px;
  display: flex;
  justify-content: center;
  align-items: center;
  border: ${props => props && `1px ${props.borderStyle || "solid"} #93BE38`};
  border-radius: 5px;
  margin-right: 12px;
  color: #93be38;
  padding: 10px;
`;

const GoalIcon = ({ icon, ...rest }: { icon: string, rest?: any }) => {
  if (!icon) return null;
  const link = `${icon_path_commitment2050_goals}/${icon}`;
  return <StyledGoalIcon {...rest} src={link} />;
};

export default GoalIcon;
