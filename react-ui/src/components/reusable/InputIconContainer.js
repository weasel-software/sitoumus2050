// @flow

import React from "react";
import styled from "styled-components";

const FasContainer = styled.div`
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  right: ${props => props.right || "0px"};
  height: ${props => props.height || "auto"};
  padding: 10px;
  color: ${props =>
    (props.valid === true && "#A7BC52") || (props.valid === false && "red")};
`;

FasContainer.defaultProps = {
  className: "fas-container"
};

const IconContainer = ({
  valid,
  renderValid,
  renderInvalid,
  render,
  children,
  ...rest
}: {
  valid?: ?boolean,
  renderValid?: () => React$Node,
  renderInvalid?: () => React$Node,
  render?: () => React$Node,
  children?: React$Node,
  rest?: any
}) => {
  return (
    <FasContainer valid={valid} key={String(valid)} {...rest}>
      {valid === true && renderValid && renderValid()}
      {valid === false && renderInvalid && renderInvalid()}
      {render && !renderValid && !renderInvalid && render && render()}
      {children}
    </FasContainer>
  );
};

export default IconContainer;
