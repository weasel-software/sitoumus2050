// @flow

import React, { Component } from "react";
import styled from "styled-components";
import onClickOutside from "react-onclickoutside";

import InputIconContainer from "./InputIconContainer";
import Row from "./Row";

const Container = styled.div`
  padding: ${props => props.padding || "4px 8px 4px 8px"};
  &:hover {
    background: ${props => props.hoverBackground || "rgba(0, 0, 0, 0.025)"};
  }
  border: ${props => props.error && "2px solid red"};
  width: ${props => props.width || "100%"};
`;

const Column = styled.div`
  display: flex;
  flex-direction: column;
  position: absolute;
  z-index: 100;
  background: #fefefe;
  border: 1px solid #ccc;
  box-shadow: 0 2px 24px #ccc;
  padding: ${props => props.internalPadding || "6px 12px 6px 12px"};
  right: ${props => props.right || "unset"};
`;

const CenterVertically = styled.div`
  display: flex;
  align-items: center;

  height: ${props => props.height || "60px"};
  flex: 1 1 auto;
`;

const Label = styled.span`
  padding-right:  6px;
  font-size: ${props => props.fontSize || "inherit"};
  margin: ${props => props.labelMargin || "0px"};
  justify-content: ${props => props.justifyContent || "initial"};
  color: ${props => props.disabled && "grey"}
  cursor: default;
  display: flex;
  flex: 1 1 auto;
`;

const IconContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  right: 16px;
`;

//$FlowFixMe
if (!("classList" in SVGElement.prototype)) {
  //$FlowFixMe
  Object.defineProperty(SVGElement.prototype, "classList", {
    get() {
      return {
        contains: className => {
          return this.className.baseVal.split(" ").indexOf(className) !== -1;
        }
      };
    }
  });
}
class Dropdown extends Component<
  {
    height?: string,
    style?: any,
    justifyLabel?: string,
    label: string | React$Node,
    error?: boolean,
    disabled?: boolean,
    render: ({
      items: Array<any>,
      value: string | number,
      toggleOpen: () => void
    }) => React$Node,
    enableOnClickOutside?: any,
    disableOnClickOutside?: any,
    fontSize?: string,
    items: Array<any>,
    onSelect?: (SyntheticInputEvent<any>) => void,
    right?: string,
    padding?: string,
    internalPadding?: string,
    labelMargin?: string,
    hoverBackground?: string,
    showValue?: boolean,
    width?: string,
    _ref?: () => React$Node
  },
  {
    value: ?string | ?number,
    isOpen: boolean
  }
> {
  state = {
    value: null,
    isOpen: false
  };

  static defaultProps = {
    showValue: true
  };

  handleClickOutside = evt => {
    this.setState({ isOpen: false });
  };

  toggleOpen = () => {
    this.setState({ isOpen: !this.state.isOpen }, () => {
      if (this.state.isOpen) {
        this.props.enableOnClickOutside && this.props.enableOnClickOutside();
      } else {
        this.props.disableOnClickOutside && this.props.disableOnClickOutside();
      }
    });
  };

  render() {
    const {
      height,
      justifyLabel,
      right,
      padding,
      internalPadding,

      labelMargin,
      fontSize,
      disabled,
      hoverBackground,
      showValue,
      width,
      _ref
    } = this.props;
    return (
      <Container
        style={this.props.style}
        error={this.props.error}
        padding={padding}
        hoverBackground={hoverBackground}
      >
        <Row onClick={this.toggleOpen}>
          <CenterVertically height={height}>
            <Label
              justifyContent={justifyLabel}
              disabled={disabled}
              labelMargin={labelMargin}
              fontSize={fontSize}
            >
              {this.props.label}
            </Label>
          </CenterVertically>
          <IconContainer style={{ width: "30px" }}>
            <InputIconContainer
              innerRef={_ref || null}
              height={height}
              key={String(this.state.isOpen)}
              render={() => (
                <i
                  className={
                    this.state.isOpen && !this.props.disabled
                      ? "fas fa-chevron-up"
                      : "fas fa-chevron-down"
                  }
                />
              )}
            />
          </IconContainer>
        </Row>
        {/*{showValue && (this.state.value ? this.state.value : null)}*/}

        {this.state.isOpen &&
          !this.props.disabled && (
            <Column
              internalPadding={internalPadding}
              right={right}
              width={width}
              onChange={e => {
                this.setState({
                  value: e.target.value
                });
                this.props.onSelect && this.props.onSelect(e);
              }}
            >
              {this.props.render
                ? this.props.render({
                    items: this.props.items,
                    value: this.state.value || "",
                    toggleOpen: this.toggleOpen
                  })
                : console.warn("NO RENDER PROP PROVIDED TO DROPDOWN COMPONENT")}
            </Column>
          )}
      </Container>
    );
  }
}

export default onClickOutside(Dropdown);
