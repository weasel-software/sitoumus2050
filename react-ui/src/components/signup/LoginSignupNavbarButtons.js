// @flow

import type { STORE_STATE } from "../../redux/store";

import React, { Fragment, Component } from "react";
import { connect } from "react-redux";
import styled from "styled-components";

import { UserActions } from "../../redux/user";
import { OrganizationActions } from "../../redux/organizations";

import StyledLink from "../reusable/StyledLink";
import translate from "../reusable/translate";
import LiferayLink from "../reusable/LiferayLink";

const Row = styled.div`
  display: flex;
  flex-direction: row;
  background: #93be38;
  align-items: center;
  color: #eee;
  justify-content: flex-end;
  height: 55px;
`;

const ProfileIconContainer = styled.div`
  padding: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #85ae2f;
  margin-left: 16px;
  flex-direction: row;
  height: 55px;
  &:hover {
    background: #91ad3e;
  }
`;

const WhiteSpace = styled.div`
  width: 6px;
`;

class LoginNavbarButtons extends Component<any, any> {
  componentDidMount() {
    this.props.getProfile();
    this.props.getCountries();
  }

  render() {
    const { profile } = this.props;
    return (
      <Row>
        {profile ? (
          <Fragment>
            <a href="/c/portal/logout" id="navbar_logout_link">
              {translate({ textKey: "sit.signOut" })}
            </a>
            <WhiteSpace />
          </Fragment>
        ) : (
          <Fragment>
            <WhiteSpace />
            {" / "}
            <WhiteSpace />
            <StyledLink
              style={{ marginRight: "4px" }}
              id="navbar_signup_link"
              to="/signup-modal/start"
            >
              {translate({ textKey: "sit.signUp.register" })}
            </StyledLink>
            <WhiteSpace />
          </Fragment>
        )}

        {profile && (
          <LiferayLink href="/profiili" id="navbar_link_to_profile">
            <ProfileIconContainer>
              {translate({ textKey: "sit.profile.title" })}
              <WhiteSpace />
              <i className="fas fa-user" />
            </ProfileIconContainer>
          </LiferayLink>
        )}
      </Row>
    );
  }
}

export default connect(
  (state: STORE_STATE) => ({
    profile: state.user.profile
  }),
  {
    getProfile: UserActions.getProfile,
    getCountries: OrganizationActions.getCountries
  }
)(LoginNavbarButtons);
