// @flow

import React from "react";
import styled from "styled-components";

const StyledWhiteSpace = styled.div`
  height: ${props => props.height || 0};
  width: ${props => props.width || 0};
`;

const WhiteSpace = ({ width, height }: { width?: string, height?: string }) => (
  <StyledWhiteSpace width={width} height={height} />
);

export default WhiteSpace;
