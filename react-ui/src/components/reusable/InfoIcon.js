// @flow

import React from "react";
import styled from "styled-components";
import getLocalizedString from "../../utils/getLocalizedString";

const StyledIcon = styled.i`
  color: ${props => props.color || "#AEAEAE"};
  font-size: 24;
`;

const Container = styled.span`
  margin-right: 8px;
`;

const InfoIcon = ({
  info,
  color,
  selectedLocale
}: {
  info: {
    id: number,
    info: string
  },
  color?: string,
  selectedLocale?: "fi_FI" | "en_US"
}) => {
  if (!info) return null;
  const titleText = selectedLocale
    ? getLocalizedString("info", info, selectedLocale)
    : info.info;
  return (
    <Container title={titleText || info.info}>
      <StyledIcon className="fas fa-info-circle" />
    </Container>
  );
};

export default InfoIcon;
