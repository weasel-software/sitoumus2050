// @flow

import React from "react";
import styled from "styled-components";

const StyledTextInput = styled.input`
  display: flex;
  justify-content: flex-start;
  align-items: center;
  border: 0;
  appearance: none;
  outline: none;
  font-size: 13px;
  border-bottom: 1px dashed #aaa;
`;

const Text = styled.span`
  font-size: 16px;
  font-weight: bold;
`;

const Container = styled.div`
  display: flex;
  align-items: center;
  border: 1px solid transparent;
  &:hover {
    border: ${props => !props.focused && "1px dotted #aaa"};
  }
  padding: ${props => (!props.focused ? "6px 6px 6px 6px" : "0 0 0 6px")};
`;

class InputWithIcon extends React.Component<
  {
    renderIcon?: ({ value: ?string, ref: ?HTMLInputElement }) => React$Node,
    type: "text" | "number" | "email" | "password",
    name: string,
    placeholder?: string,
    required?: boolean,
    onChange?: (SyntheticInputEvent<any>) => void,
    label?: string,
    labelStyle?: any,
    textStyle?: any,
    inputStyle?: any,
    textComponent?: any,
    style?: any,
    rest?: any,
    value?: string
  },
  {
    focused: boolean,
    innerVal: string
  }
> {
  input = null;
  state = {
    focused: false,
    innerVal: ""
  };
  render() {
    const value = this.props.value || (this.input && this.input.value);
    const {
      type,
      placeholder,
      required,
      label,
      name,
      textComponent,
      inputStyle,
      textStyle,
      style,
      ...rest
    } = this.props;

    const TextComp = textComponent || Text;

    return (
      <Container
        focused={this.state.focused}
        style={style}
        onClick={() =>
          this.setState({
            focused: true
          })
        }
      >
        {this.state.focused ? (
          <StyledTextInput
            defaultValue={this.state.innerVal}
            autoFocus={true}
            type={type || "text"}
            placeholder={placeholder}
            required={required}
            name={name}
            style={inputStyle}
            {...rest}
            innerRef={input => (this.input = input)}
            onBlur={() =>
              this.setState({
                focused: false
              })
            }
            onKeyDown={e => {
              if (e.key === "Enter") {
                this.setState({
                  focused: false
                });
              }
            }}
            onChange={e => {
              this.props.onChange && this.props.onChange(e);
              this.setState({ innerVal: e.target.value });
            }}
          />
        ) : (
          <div>
            <TextComp style={textStyle}>{value || placeholder}</TextComp>
          </div>
        )}
        {this.props.renderIcon &&
          this.props.renderIcon({ value, ref: this.input })}
      </Container>
    );
  }
}

export default InputWithIcon;
