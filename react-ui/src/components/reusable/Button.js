// @flow

import React from "react";
import styled from "styled-components";
import { darken, lighten } from "polished";

const StyledButton = styled.button`
  background: #93be38;
  text-transform: ${props => (props.uppercase ? "uppercase" : "none")};
  color: white;
  max-width: ${props => (props.block ? "none" : "380px")};
  min-width: 320px;
  border-radius: 4px;
  font-size: 16px;
  font-weight: 500;
  height: 60px;
  display: flex;
  width: 100%;
  justify-content: center;
  align-items: center;
  margin: 8px;
  position: relative;
  appearance: none;
  outline: none;
  border: 0;

  &:hover {
    background: ${lighten(0.1, "#93BE38")};
  }
  &:active {
    background: ${darken(0.1, "#93BE38")};
  }

  &&:disabled {
    opacity: 0.5;
    background: #9fbd47;
  }
`;

const StyledSecondaryButton = styled(StyledButton)`
  background: white;
  border: 1px solid #9fbd47;
  color: #9fbd47;
  border-radius: 5px;
  box-shadow: 0 3px 6px rgba(0, 0, 0, 0.06);

  &:hover {
    background: ${darken(0.025, "white")};
  }
  &:active {
    background: ${darken(0.05, "white")};
    box-shadow: 0 1px 6px rgba(0, 0, 0, 0.06);
  }

  &&:disabled {
    color: white;
    opacity: 0.5;
    background: #9fbd47;
  }
`;

const Button = ({
  showSpinner,
  children,
  ...rest
}: {
  showSpinner?: boolean,
  children: React$Node,
  rest?: any
}) => (
  <StyledButton {...rest}>
    <div style={{ position: "relative" }}>
      {children}
      {showSpinner && (
        <span style={{ marginLeft: "8px", position: "absolute" }}>
          <i className="fas fa-spin fa-spinner" style={{ fontSize: 26 }} />
        </span>
      )}
    </div>
  </StyledButton>
);

const SecondaryButton = ({
  showSpinner,
  children,
  disabled,
  ...rest
}: {
  showSpinner?: boolean,
  children: React$Node,
  disabled?: boolean,
  rest?: any
}) => (
  <StyledSecondaryButton {...rest} disabled={disabled}>
    <div style={{ position: "relative" }}>
      {children}
      {showSpinner && (
        <span style={{ marginLeft: "8px", position: "absolute" }}>
          <i className="fas fa-spin fa-spinner" style={{ fontSize: 26 }} />
        </span>
      )}
    </div>
  </StyledSecondaryButton>
);

export default Button;
export { SecondaryButton };
