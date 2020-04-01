// @flow

import type { NotificationType } from "../../constants/types";

import React, { Component, Fragment } from "react";
import { connect } from "react-redux";
import styled from "styled-components";

import { UserActions } from "../../redux/user";

import TextInput from "../reusable/TextInput";
import Button from "../reusable/Button";
import NotificationContainer from "../reusable/NotificationContainer";
import translate from "../reusable/translate";

const StyledH3 = styled.h3`
  font-weight: bold;
  font-size: 20px;
`;

const Separator = styled.div`
  height: 30px;
`;

class UpdatePassword extends Component<
  {
    labelStyle?: any,
    userId: string,
    changePassword: ({ password1: string, password2: string }) => *,
    updatePasswordNotification: NotificationType
  },
  {
    password1: string,
    password2: string
  }
> {
  state = {
    password1: "",
    password2: ""
  };

  handleInput = (e: SyntheticInputEvent<any>) => {
    const { value, name } = e.target;
    this.setState({
      [name]: value
    });
  };

  handleSubmit = () => {
    this.props.changePassword({
      userId: this.props.userId,
      ...this.state
    });
  };

  isValid = () => {
    if (this.state.password1 && this.state.password2) {
      return this.state.password1 === this.state.password2;
    } else return false;
  };

  render() {
    return (
      <Fragment>
        <StyledH3>
          {translate({ textKey: "sit.profile.changePassword" })}
        </StyledH3>
        <Separator />
        <TextInput
          id="change_password_new_password"
          label={translate({ textKey: "sit.profile.newPassword" })}
          placeholder={translate({ textKey: "sit.profile.enter" })}
          name="password1"
          type="password"
          labelStyle={this.props.labelStyle}
          onChange={this.handleInput}
          required={true}
        />
        <TextInput
          id="change_password_new_password_confirmation"
          label={translate({ textKey: "sit.profile.confirmPassword" })}
          placeholder={translate({ textKey: "sit.profile.enter" })}
          name="password2"
          type="password"
          labelStyle={this.props.labelStyle}
          onChange={this.handleInput}
          required={true}
        />
        <Separator />
        <Button
          id="change_password_save_new_password"
          uppercase
          block
          style={{ marginLeft: 0 }}
          onClick={this.handleSubmit}
          disabled={
            !this.isValid() ||
            this.props.updatePasswordNotification.state === "IN_PROGRESS"
          }
          showSpinner={
            this.props.updatePasswordNotification.state === "IN_PROGRESS"
          }
        >
          {translate({ textKey: "sit.profile.change" })}
        </Button>
        <Separator />
        {this.props.updatePasswordNotification.state !== "IN_PROGRESS" && (
          <NotificationContainer
            timeout={7500}
            handleHide={() => {}}
            notificationType={this.props.updatePasswordNotification}
          >
            {this.props.updatePasswordNotification.message}
          </NotificationContainer>
        )}
      </Fragment>
    );
  }
}

export default connect(
  state => ({
    userId: state.user.profile ? state.user.profile.userId : "",
    updatePasswordNotification: state.user.updatePasswordNotification
  }),
  {
    changePassword: UserActions.changePassword
  }
)(UpdatePassword);
