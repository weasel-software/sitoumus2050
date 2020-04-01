// @flow
import styled from "styled-components";

const Column = styled.div`
  display: flex;
  flex-direction: column;
  flex: ${props => props.flex || 1};
  justify-content: ${props => props.justifyContent || "space-between"};
  align-items: ${props => props.alignItems || "unset"};
  flex-wrap: ${props => props.flexWrap || "unset"};
`;

export default Column;
