// @flow

import React from "react";
import styled, { css } from "styled-components";

const StyledRadio = styled.div`
  width: 26px;
  height: 26px;
  background: white;
  border: ${props =>
    props.isChecked ? css`1px solid #ccc` : css`1px solid #ddd`};
  border-radius: 13px;
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
  min-height: 61px;
  min-height: 61px;
  border: 0;
  border-top: 1px solid #eee;
  border-bottom: 1px solid #eee;
  min-width: 380px;
  position: relative;
  &:hover {
    ${StyledRadio} {
      border: 1px solid #aaa;
    }
  }
`;

const IconWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

type Props = {
  id: string,
  disabled?: boolean,
  name?: string,
  value: string | number,
  label: React$Element<any> | string,
  renderIcon?: ({
    value: string | number,
    checked: boolean,
    ref: ?HTMLInputElement
  }) => React$Node,
  onChange?: ({
    value: string | number | boolean,
    name: string,
    label: React$Element<any> | string,
    checked: boolean
  }) => void,
  defaultChecked?: boolean,
  containerState?: {
    [string]: boolean
  },
  checked?: boolean,
  required?: boolean,
  rest?: any
};

class RadioButton extends React.Component<
  Props,
  {
    [string]: boolean
  }
> {
  radio = null;

  render() {
    const {
      id,
      name,
      value,
      label,
      disabled,
      renderIcon,
      onChange,
      defaultChecked,
      checked,
      required,
      ...rest
    } = this.props;

    const isChecked =
      checked !== undefined
        ? checked
        : (this.radio && this.radio.checked) || defaultChecked;

    return (
      <StyledLabel htmlFor={id} {...rest} id={"radio_label_" + id}>
        <StyledRadio isChecked={isChecked}>
          {isChecked && (
            <IconWrapper key={id + String(isChecked)}>
              <i className="fas fa-circle" style={{ fontSize: 8 }} />
            </IconWrapper>
          )}
        </StyledRadio>
        <input
          disabled={disabled}
          style={{ opacity: 0, position: "absolute" }}
          ref={radio => (this.radio = radio)}
          type="radio"
          id={id}
          name={name}
          value={value}
          checked={isChecked}
          // defaultChecked={defaultChecked}
          onChange={e => {
            const { value, checked, name } = e.target;
            onChange && onChange({ value, name, label, checked });
          }}
          required={required}
        />
        <span style={{ marginRight: "15px" }}>{label}</span>

        {renderIcon &&
          renderIcon({
            value: value,
            checked: isChecked || false,
            ref: this.radio,
            name
          })}
      </StyledLabel>
    );
  }
}

class RadioButtonContainer extends React.Component<any, any> {
  render() {
    const { innerRef, height, overflowY, ...rest } = this.props;
    return (
      <div
        style={{
          height: height || null,
          overflowY: overflowY || "hidden"
        }}
        ref={innerRef}
        onChange={e => {
          this.props.onChange && this.props.onChange(e);
          const { checked, name } = e.target;
          this.setState({
            [name]: checked
          });
        }}
      >
        {React.Children.map(this.props.children, child =>
          React.cloneElement(child, {
            ...rest,
            ...child.props,
            containerState: { ...this.state }
          })
        )}
      </div>
    );
  }
}

export { RadioButtonContainer, RadioButton };
