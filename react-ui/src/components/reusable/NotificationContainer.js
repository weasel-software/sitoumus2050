// @flow

import type { NotificationType } from "../../constants/types";

import React, { Component } from "react";
import styled, { keyframes } from "styled-components";

const fadeIn = keyframes`
  from {
    opacity: 0;
  }

  to {
    opacity: 1;
  }
`;

const fadeOut = keyframes`
  from {
    opacity: 0;
  }

  to {
    opacity: 1;
  }
`;

const StyledNotification = styled.div`
  color: white;
  font-weight: bold;
  box-shadow: 0 2px 32px #ccc;
  padding: 6px 12px 6px 12px;
  border-radius: 6px;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  z-index: 1;
  visibility: ${props => (props.out ? "hidden" : "visible")};
  animation: ${props => (props.out ? fadeOut : fadeIn)} 0.35s linear;
  transition: visibility 0.35s linear;
  background: ${props => (props.success ? "#93be38" : "red")};
`;

class NotificationContainer extends Component<
  {
    handleHide?: () => void, // todo: this doesn't work as expected, should be removed or fixed
    notificationType?: NotificationType,
    children?: React$Node,
    timeout?: number
  },
  any
> {
  componentDidMount() {
    setTimeout(() => {
      this.props.handleHide && this.props.handleHide();
    }, this.props.timeout || 2500);
  }
  componentWillUnmount() {
    this.props.handleHide && this.props.handleHide();
  }
  render() {
    if (!this.props.notificationType) return null;
    switch (this.props.notificationType.state) {
      case "SUCCESS": {
        return (
          <StyledNotification success={true}>
            {this.props.children || this.props.notificationType.message}
          </StyledNotification>
        );
      }

      case "FAILURE": {
        return (
          <StyledNotification success={false}>
            {this.props.children || this.props.notificationType.message}
          </StyledNotification>
        );
      }
      case "IN_PROGRESS": {
        return (
          <StyledNotification success={true}>
            {(this.props.children !== "" && this.props.children) ||
              this.props.notificationType.message || (
                <div>
                  <i className="fa fa-spin fa-spinner" />
                </div>
              )}
          </StyledNotification>
        );
      }
      default:
        return null;
    }
  }
}

export default NotificationContainer;
