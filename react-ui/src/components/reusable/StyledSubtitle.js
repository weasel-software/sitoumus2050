// @flow

import styled from "styled-components";

const StyledSubtitle = styled.h4`
  font-size: 14px;
  font-weight: bold;
  padding-bottom: ${props => props.paddingBottom || "0px"}
  border-bottom: ${props => (props.showBottomBorder ? "1px solid #dcdcdc" : 0)};
  display: flex;
  flex: ${props => (props.flex !== undefined ? props.flex : "1 0 auto")};
  justify-content: space-between;
`;

export default StyledSubtitle;
