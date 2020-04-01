// @flow

import type { STORE_STATE } from "../../redux/store";
import type { NotificationType } from "../../constants/types";

import React, { Component, Fragment } from "react";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";

import { UserActions } from "../../redux/user";

import TextInput from "../reusable/TextInput";
import FormField from "./FormField";
import InputIconContainer from "../reusable/InputIconContainer";
import StyledButton from "../reusable/Button";
import NotificationContainer from "../reusable/NotificationContainer";
import translate from "../reusable/translate";
import { get } from "lodash";
import Checkbox from "../reusable/Checkbox";
import produce from "immer";

class QuickSignUpLogin extends Component<
  {
    registerAndLogin: ({
      firstName: string,
      lastName: string,
      email: string
    }) => void,
    history: *,
    signUpNotification: NotificationType
  },
  {
    firstName: string,
    lastName: string,
    email: string,
    acceptCriterias: string
  }
> {
  state = {
    firstName: "",
    lastName: "",
    email: "",
    acceptCriterias: ""
  };
  inputs = {};

  handleInput = (event: SyntheticInputEvent<any>) => {
    const { name, value } = event.target;
    this.setState({
      [name]: value
    });
  };

  registerAndLogin = e => {
    e.preventDefault();

    this.props.registerAndLogin(this.state);
  };

  render() {
    return (
      <Fragment>
        <form onSubmit={this.registerAndLogin}>
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
            <FormField>
              <Checkbox
                error={get(this.state, "errors.acceptCriterias")}
                innerRef={r => (this.inputs.acceptCriterias = r)}
                required
                name="acceptCriterias"
                checked={this.state.acceptCriterias !== ""}
                onChange={() => {
                  this.setState(
                    // $FlowFixMe
                    produce(draft => {
                      draft.acceptCriterias = !this.state.acceptCriterias;
                    })
                  );
                }}
                label={translate({
                  textKey: "sit.signUp.acceptTerms"
                })}
                link={translate({
                  textKey: "sit.signUp.acceptTerms.link"
                })}
                linktext={translate({
                  textKey: "sit.signUp.acceptTerms.linktext"
                })}
              />
            </FormField>
          </div>

          <StyledButton type="submit" uppercase id="signup_submit_registration">
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
      registerAndLogin: UserActions.registerAndLogin
    }
  )(QuickSignUpLogin)
);
