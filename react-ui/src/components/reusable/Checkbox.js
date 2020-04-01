// @flow

import React from "react";
import styled, { css } from "styled-components";

const StyledCheckbox = styled.div`
  min-width: 26px;
  height: 26px;
  background: white;
  border: ${props =>
    props.isChecked ? css`1px solid #ccc` : css`1px solid #ddd`};
  border-radius: 4px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 8px;
`;

const StyledLabel = styled.label`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  color: #666;
  font-size: 13px;
  &:hover {
    cursor: pointer;
  }
  min-height: ${props => props.height || "61px"};
  border: 0;
  border-top: 1px solid #eee;
  border-bottom: 1px solid #eee;
  min-width: ${props => props.minWidth || 0};
  position: relative;
  margin: 0;
  margin-top: -1px;
  &:hover {
    ${StyledCheckbox} {
      border: 1px solid #aaa;
    }
  }
  padding: 6px;
  border-radius: ${props => (props.invalid ? "4px" : 0)};
  border: ${props =>
    props.invalid ? "2px solid red" : "2px solid transparent"};
  opacity: ${props => (props.disabled ? 0.75 : 1)};
  pointer-events: ${props => (props.disabled ? "none" : "auto")};
`;

export default class Checkbox extends React.Component<
  {
    name: string,
    label: React$Element<any> | string,
    renderIcon?: ({ value: boolean, ref: ?HTMLInputElement }) => React$Node,
    onChange: (SyntheticInputEvent<any>) => void,
    checked?: boolean,
    innerRef?: any,
    required?: boolean,
    disabled?: boolean,
    link?: string,
    linktext?: string,
    rest?: any
  },
  {
    innerVal: string,
    invalid?: boolean
  }
> {
  state = {
    invalid: false,
    innerVal: ""
  };

  checkbox = null;
  render() {
    const {
      name,
      label,
      renderIcon,
      onChange,
      checked,
      innerRef,
      required,
      disabled,
      link,
      linktext,
      ...rest
    } = this.props;

    const isChecked =
      checked !== undefined ? checked : this.checkbox && this.checkbox.checked;

    return (
      <StyledLabel
        htmlFor={name}
        invalid={this.state.invalid}
        disabled={disabled}
        {...rest}
      >
        <StyledCheckbox isChecked={isChecked}>
          {isChecked && (
            <div key={name + String(isChecked)}>
              <i className="fas fa-check" />
            </div>
          )}
        </StyledCheckbox>
        <input
          disabled={disabled}
          checked={isChecked}
          onInvalid={() =>
            this.setState({
              invalid: true
            })
          }
          onBlur={() =>
            this.setState({
              invalid: false
            })
          }
          required={required || false}
          style={{ width: 0, height: 0, opacity: 0, position: "absolute" }}
          ref={innerRef || (checkbox => (this.checkbox = checkbox))}
          type="checkbox"
          id={name}
          name={name}
          onChange={e => {
            this.setState({
              innerVal: e.target.value
            });

            onChange && onChange(e);
          }}
        />
        {link !== undefined && linktext !== undefined ? (
          <span>
            {label}{" "}
            <a target="_blank" href={link}>
              {linktext}
            </a>
          </span>
        ) : (
          <span>{label}</span>
        )}

        {renderIcon &&
          renderIcon({ value: Boolean(isChecked), ref: this.checkbox })}
      </StyledLabel>
    );
  }
}
