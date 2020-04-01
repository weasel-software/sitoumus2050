// @flow

import type { STORE_STATE } from "../../redux/store";
import type { NotificationType } from "../../constants/types";

import React, { Component, Fragment } from "react";
import { connect } from "react-redux";
import styled from "styled-components";
import { withRouter } from "react-router-dom";

import { UserActions } from "../../redux/user";

import { ModalHeader, ModalBody, ModalCloseButton } from "../reusable/Modal";
import TextInput from "../reusable/TextInput";
import FormField from "./FormField";
import InputIconContainer from "../reusable/InputIconContainer";
import StyledButton from "../reusable/Button";
import NotificationContainer from "../reusable/NotificationContainer";
import translate from "../reusable/translate";

const StyledH2 = styled.h2`
  font-weight: 300;
  color: #666;
`;

class EmailSignUp extends Component<
  {
    submitRegistration: (
      { firstName: string, lastName: string, email: string },
      () => void
    ) => void,
    history: *,
    signUpNotification: NotificationType
  },
  {
    firstName: string,
    lastName: string,
    email: string
  }
> {
  state = {
    firstName: "",
    lastName: "",
    email: ""
  };

  handleInput = (event: SyntheticInputEvent<any>) => {
    const { name, value } = event.target;
    this.setState({
      [name]: value
    });
  };

  submitRegistration = e => {
    e.preventDefault();
    const successRedirect = () =>
      this.props.history.push("/signup-modal/registrationSuccess");

    this.props.submitRegistration(this.state, successRedirect);
  };

  render() {
    return (
      <Fragment>
        <form onSubmit={this.submitRegistration}>
          <ModalHeader>
            <StyledH2>
              {translate({ textKey: "sit.signUp.signUpWithEmail" })}
            </StyledH2>
            <ModalCloseButton to="/web/sitoumus2050">&#10005;</ModalCloseButton>
          </ModalHeader>

          <ModalBody style={{ minHeight: "420px" }}>
            <div>
              <FormField>
                <TextInput
                  id="signup_first_name_field"
                  placeholder={translate({
                    textKey: "sit.profile.enterFirstName"
                  })}
                  required
                  type="text"
                  name="firstName"
                  onChange={this.handleInput}
                  renderIcon={({ value, ref }) => {
                    if (!value || value === "") return;
                    return (
                      <InputIconContainer
                        valid={true}
                        renderValid={() => <i className="fas fa-check" />}
                      />
                    );
                  }}
                />
              </FormField>

              <FormField>
                <TextInput
                  id="signup_last_name_field"
                  placeholder={translate({
                    textKey: "sit.profile.enterLastName"
                  })}
                  required
                  type="text"
                  name="lastName"
                  onChange={this.handleInput}
                  renderIcon={({ value, ref }) => {
                    if (!value || value === "") return;
                    return (
                      <InputIconContainer
                        valid={true}
                        renderValid={() => <i className="fas fa-check" />}
                      />
                    );
                  }}
                />
              </FormField>

              <FormField>
                <TextInput
                  id="signup_email_field"
                  placeholder={translate({ textKey: "sit.profile.enterEmail" })}
                  required
                  type="email"
                  name="email"
                  onChange={this.handleInput}
                  renderIcon={({ value, ref }) => {
                    if (!value || value === "") return;
                    const isValid = ref && ref.checkValidity();
                    return (
                      <InputIconContainer
                        valid={isValid}
                        renderValid={() => <i className="fas fa-check" />}
                        renderInvalid={() => <i className="fas fa-asterisk" />}
                      />
                    );
                  }}
                />
              </FormField>
            </div>

            <StyledButton
              type="submit"
              uppercase
              id="signup_submit_registration"
            >
              {translate({ textKey: "sit.signUp.register" })}
            </StyledButton>
            <div style={{ minHeight: "50px" }}>
              <NotificationContainer
                timeout={7500}
                handleHide={() => {}}
                notificationType={this.props.signUpNotification}
              >
                {this.props.signUpNotification.message}
              </NotificationContainer>
            </div>
          </ModalBody>
        </form>
      </Fragment>
    );
  }
}

export default withRouter(
  connect(
    (state: STORE_STATE) => ({
      signUpNotification: state.user.signUpNotification
    }),
    {
      submitRegistration: UserActions.submitRegistration
    }
  )(EmailSignUp)
);
