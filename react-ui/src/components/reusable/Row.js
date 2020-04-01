// @flow
import styled from "styled-components";

const Row = styled.div`
  display: flex;
  flex-direction: row;
  flex: ${props => props.flex || "1 1 auto"};
  justify-content: ${props => props.justifyContent || "space-between"};
  align-items: ${props => props.alignItems || "unset"};
  flex-wrap: ${props => props.flexWrap || "unset"};
`;

export default Row;
