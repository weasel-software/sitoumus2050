// @flow

import React, { Fragment } from "react";
import { Route, Switch, withRouter } from "react-router-dom";
import styled from "styled-components";

import Modal, {
  ModalHeader,
  ModalBody,
  ModalCloseButton,
  ModalText
} from "../reusable/Modal";
import StyledLink from "../reusable/StyledLink";
import StyledButton from "../reusable/Button";
import translate from "../reusable/translate";
import Button from "../reusable/Button";
import LiferayLink from "../reusable/LiferayLink";

import EmailSignup from "./SignupWithEmail";

const RightAnchoredIcon = styled.i`
  margin-left: 18px;
`;

const StyledH2 = styled.h2`
  font-weight: 300;
  color: #666;
`;

const SignupModal = props => (
  <Route
    path="/signup-modal"
    render={routeProps => (
      <Modal show={true} dialogClassName="react-bs-modal-root">
        <Route
          path={routeProps.match.path + "/start"}
          exact
          render={() => (
            <Fragment>
              <ModalHeader>
                <StyledH2>
                  {translate({ textKey: "sit.signUp.register" })}
                </StyledH2>
                <ModalCloseButton to="/" id="register_modal_close_modal_button">
                  &#10005;
                </ModalCloseButton>
              </ModalHeader>
              <ModalBody>
                <ModalText>
                  {translate({ textKey: "sit.signUp.needAnEmailToSignUp" })}
                </ModalText>

                <div>
                  <StyledLink
                    to="/signup-modal/email"
                    id="register_modal_link_to_email_signup"
                  >
                    <StyledButton uppercase bsSize="large" block>
                      {translate({ textKey: "sit.signUp.signUpWithEmail" })}
                      <RightAnchoredIcon className="fas fa-envelope" />
                    </StyledButton>
                  </StyledLink>
                </div>

                <ModalText>
                  {translate({ textKey: "sit.signUp.signedUpAlready" })}
                  <LiferayLink
                    href="/c/portal/login"
                    id="register_modal_link_to_signup"
                  >
                    {translate({ textKey: "sit.signUp.login" })}
                  </LiferayLink>
                </ModalText>
              </ModalBody>
            </Fragment>
          )}
        />

        <Switch>
          <Route
            path={routeProps.match.path + "/email"}
            exact
            render={() => <EmailSignup {...routeProps} />}
          />

          <Route
            path={routeProps.match.path + "/registrationSuccess"}
            exact
            render={() => (
              <Fragment>
                <ModalHeader>
                  <StyledH2>
                    {translate({ textKey: "sit.signup.succeeded" })}
                  </StyledH2>
                </ModalHeader>

                <ModalBody>
                  {translate({ textKey: "sit.signUp.succeededInfo" })}
                  <LiferayLink href="/web/sitoumus2050">
                    <Button>Ok</Button>
                  </LiferayLink>
                </ModalBody>
              </Fragment>
            )}
          />
        </Switch>
      </Modal>
    )}
  />
);

export default withRouter(SignupModal);
