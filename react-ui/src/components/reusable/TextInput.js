// @flow

import React from "react";
import styled from "styled-components";
import { media } from "../../utils/style-utils";

const StyledTextInput = styled.input`
  display: flex;
  justify-content: flex-start;
  align-items: center;

  border: 0;
  appearance: none;
  outline: none;
  font-size: 13px;
  width: 100%;
  flex: 1;
  min-height: ${props => props.minHeight || "60px"};

  padding-left: 12px;
  border-radius: 4px;
  &:focus {
    border: ${props => (props.error ? "2px solid red" : "2px solid #93be38")};
  }
  border: ${props => (props.error ? "2px solid red" : "2px solid transparent")};
  ${
    "" /* border-top: ${props => (props.error ? "2px solid red" : "1px solid #eee")};
  border-bottom: ${props => (props.error ? "2px solid red" : "1px solid #eee")}; */
  }
  border-top: ${props => (props.error ? "2px solid red" : 0)};
  border-bottom: ${props => (props.error ? "2px solid red" : 0)};
`;

const Row = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  min-height: ${props => (props.minHeight ? props.minHeight : "61px")};
  border: 0;
  border-top: 1px solid #eee;
  border-bottom: 1px solid #eee;
  margin-top: -1px;
  position: relative;
  flex: 1;
  ${media.handheld`
      flex-direction: column;
  `};
  opacity: ${props => (props.disabled ? 0.5 : 1)};
  pointer-events: ${props => (props.disabled ? "none" : "auto")};
`;

const Label = styled.label`
  margin: 0;
  margin-right: 8px;
  display: flex;
  ${media.handheld`
      align-self: flex-start;
  `};
`;

const IconContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  right: 16px;
`;

class TextInput extends React.Component<
  {
    renderIcon?: ({
      value: ?string,
      ref: ?HTMLInputElement,
      isValid?: boolean
    }) => React$Node,
    type: "text" | "number" | "email" | "password" | "url",
    name: string,
    placeholder?: string | React$Node | React$Element<any>,
    required?: boolean,
    onChange?: (SyntheticInputEvent<any>) => void,
    label?: string | React$Node | React$Element<any>,
    defaultValue?: string,
    minHeight?: string,
    labelStyle?: any,
    inputStyle?: any,
    disabled?: boolean,
    style?: any,
    renderRightButton?: () => React$Node,
    error?: boolean,
    rest?: any
  },
  any
> {
  input = null;

  state = {
    error: false
  };

  render() {
    const value = this.input && this.input.value;
    const {
      type,
      placeholder,
      required,
      label,
      name,
      defaultValue,
      labelStyle,
      inputStyle,
      onChange,
      style,
      minHeight,
      disabled,
      error,
      ...rest
    } = this.props;

    return (
      <Row style={style} minHeight={minHeight} disabled={disabled}>
        {label && (
          <Label htmlFor={name} style={labelStyle}>
            {label}
          </Label>
        )}
        <StyledTextInput
          error={error}
          type={type || "text"}
          placeholder={placeholder}
          required={required}
          name={name}
          style={inputStyle}
          defaultValue={defaultValue}
          innerRef={input => (this.input = input)}
          minHeight={minHeight}
          onKeyDown={e => {
            if (type === "number" && e.keyCode === 69) {
              e.preventDefault();
              return false;
            } else return true;
          }}
          onChange={e => {
            onChange && onChange(e);
            this.setState({ innerVal: e.target.value });
          }}
          disabled={disabled}
          {...rest}
        />
        {this.props.renderIcon && (
          <IconContainer>
            {this.props.renderIcon({
              value,
              ref: this.input,
              valid: this.input ? this.input.checkValidity() : null
            })}
          </IconContainer>
        )}

        {this.props.renderRightButton && this.props.renderRightButton()}
      </Row>
    );
  }
}

export default TextInput;
