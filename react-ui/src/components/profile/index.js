// @flow

import type { STORE_STATE } from "../../redux/store";
import type { PROFILE_TYPE } from "../../constants/types";

import { get } from "lodash";
import React, { Fragment } from "react";
import styled from "styled-components";
import { NavLink, Route, withRouter, Redirect } from "react-router-dom";
import { connect } from "react-redux";

import translate from "../reusable/translate";
import { UserActions } from "../../redux/user";

import CommitmentCreationFormContainer from "./createCommitment/CommitmentCreationFormContainer";
import OwnProfile from "./OwnProfile";
import MyOrganisations from "./MyOrganizations";
import CreateCommitment from "./createCommitment/CreateCommitment";
import MyCommitments from "./MyCommitments";
import OperationsList from "./reports/OperationsList";

const ProfileContainer = styled.div`
  background: #f4f4f4;
  display: flex;
  align-items: flex-start;
  flex-direction: column;
`;

const TabRow = styled.div`
  display: flex;
  flex-direction: row;
  padding-top: 20px;
  flex: 1;
  min-height: 70px;
`;

const LinkContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  min-height: 70px;
  padding: 0 40px 0 0;
`;

const StyledNavLink = styled(NavLink)`
  && {
    height: 70px;
    border-bottom: 6px solid transparent;
    text-transform: uppercase;
    color: #555;
    font-weight: normal;
    text-decoration: none;
    &:hover {
      text-decoration: none;
    }
    &:active {
      text-decoration: none;
    }
    &:focus {
      text-decoration: none;
    }
  }
`;

const ContentArea = styled.div`
  background: white;
`;

const TabContainer = ({ children }: { children: React$Node }) => (
  <div className="container" style={{ paddingTop: 40, paddingBottom: 40 }}>
    <div className="col-md-12">{children}</div>
  </div>
);

class Profile extends React.Component<
  { getProfile: string => void, profile: PROFILE_TYPE },
  any
> {
  componentDidMount() {
    if (window.Liferay) {
      const isSignedIn = window.Liferay.ThemeDisplay.isSignedIn();
      const id = window.Liferay.ThemeDisplay.getUserId();
      if (isSignedIn) this.props.getProfile(id);
      else {
        window.location.href = "/c/portal/login";
      }
    }
  }
  render() {
    const { profile } = this.props;
    return (
      <Route
        path="/"
        render={routeProps => (
          <Fragment>
            <ProfileContainer>
              <div className="container">
                <h1 style={{ fontWeight: 400 }}>
                  {translate({ textKey: "sit.profile.title" })}
                </h1>

                <TabRow>
                  <LinkContainer>
                    <StyledNavLink
                      id="link_to_profile"
                      activeStyle={{
                        fontWeight: "bold",
                        borderBottom: "6px solid #9FBD47"
                      }}
                      to="/oma"
                    >
                      {translate({ textKey: "sit.profile.ownProfile" })}
                    </StyledNavLink>
                  </LinkContainer>

                  <LinkContainer>
                    <StyledNavLink
                      id="link_to_my_organizations"
                      activeStyle={{
                        fontWeight: "bold",
                        borderBottom: "6px solid #9FBD47"
                      }}
                      to="/organisaationi"
                    >
                      {translate({ textKey: "sit.profile.myOrganisations" })}
                    </StyledNavLink>
                  </LinkContainer>

                  <LinkContainer>
                    <StyledNavLink
                      id="link_to_my_commitments"
                      activeStyle={{
                        fontWeight: "bold",
                        borderBottom: "6px solid #9FBD47"
                      }}
                      to="/sitoumukset"
                    >
                      {translate({ textKey: "sit.profile.ownCommitments" })}
                    </StyledNavLink>
                  </LinkContainer>

                  <LinkContainer>
                    <StyledNavLink
                      id="link_to_create_commitment"
                      activeStyle={{
                        fontWeight: "bold",
                        borderBottom: "6px solid #9FBD47"
                      }}
                      to="/tee-sitoumus"
                    >
                      {translate({ textKey: "sit.profile.createCommitment" })}
                    </StyledNavLink>
                  </LinkContainer>
                </TabRow>
              </div>
            </ProfileContainer>
            <ContentArea>
              <Route exact path="/" render={() => <Redirect to="/oma" />} />
              <Route
                path="/oma"
                exact
                render={routeProps => {
                  if (!profile) return null;
                  return (
                    <TabContainer>
                      <OwnProfile routeProps={routeProps} />
                    </TabContainer>
                  );
                }}
              />
              <Route
                path="/organisaationi"
                render={routeProps => (
                  <TabContainer>
                    <MyOrganisations routeProps={routeProps} />
                  </TabContainer>
                )}
              />

              <Route
                path="/sitoumukset"
                exact
                render={routeProps => (
                  <MyCommitments
                    routeProps={routeProps}
                    TabContainer={TabContainer}
                  />
                )}
              />

              <Route
                path="/tee-sitoumus"
                render={routeProps => (
                  <TabContainer>
                    <CreateCommitment match={routeProps.match} />
                  </TabContainer>
                )}
              />
              <Route
                path="/tee-elamantapa-sitoumus"
                render={routeProps => (
                  <TabContainer>
                    <CreateCommitment match={routeProps.match} />
                  </TabContainer>
                )}
              />
            </ContentArea>

            <Route
              path="/muokkaa-sitoumusta"
              render={routeProps => {
                const commitment = get(routeProps, "location.state.commitment");
                return (
                  <CommitmentCreationFormContainer
                    {...routeProps}
                    commitment={commitment}
                    editing={true}
                  />
                );
              }}
            />

            <Route
              path="/muokkaa-raporttia"
              render={routeProps => {
                const commitment = get(routeProps, "location.state.commitment");
                return (
                  <OperationsList commitment={commitment} {...routeProps} />
                );
              }}
            />

            <Route
              path="/raportoi"
              render={routeProps => {
                const commitment = get(routeProps, "location.state.commitment");
                return (
                  <OperationsList commitment={commitment} {...routeProps} />
                );
              }}
            />
          </Fragment>
        )}
      />
    );
  }
}

export default withRouter(
  connect(
    (state: STORE_STATE) => ({
      profile: state.user.profile
    }),
    { getProfile: UserActions.getProfile }
  )(Profile)
);
