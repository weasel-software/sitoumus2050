// @flow

import React, { Fragment } from "react";
import styled from "styled-components";
import { NavLink, Route, withRouter, Redirect } from "react-router-dom";

import ExcelExport from "./ExcelExport";
import JoinCommitments from "./JoinCommitments";
import ReportReminders from "./ReportReminders";
import FormatAddress from "./Initialisators";

const MaintenanceContainer = styled.div`
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

const TabContainer = ({ children }: { children: React$Node }) => (
  <div className="container" style={{ paddingTop: 40, paddingBottom: 40 }}>
    <div className="col-md-12">{children}</div>
  </div>
);

const ContentArea = styled.div`
  background: white;
`;

class CommitmentMaintenance extends React.Component<any> {
  render() {
    return (
      <Route
        path="/"
        render={routeProps => (
          <Fragment>
            <MaintenanceContainer>
              <div className="container">
                <h1 style={{ fontWeight: 400 }}>Sitoumusten hallinta</h1>

                <TabRow>
                  <LinkContainer>
                    <StyledNavLink
                      id="link_to_excel"
                      activeStyle={{
                        fontWeight: "bold",
                        borderBottom: "6px solid #9FBD47"
                      }}
                      to="/excel"
                    >
                      lataa excel
                    </StyledNavLink>
                  </LinkContainer>
                  <LinkContainer>
                    <StyledNavLink
                      id="join_commitments"
                      activeStyle={{
                        fontWeight: "bold",
                        borderBottom: "6px solid #9FBD47"
                      }}
                      to="/join_commitments"
                    >
                      Liitä sitoumuksia
                    </StyledNavLink>
                  </LinkContainer>
                  <LinkContainer>
                    <StyledNavLink
                      id="report_reminders"
                      activeStyle={{
                        fontWeight: "bold",
                        borderBottom: "6px solid #9FBD47"
                      }}
                      to="/report_reminders"
                    >
                      Raportointi-muistutukset
                    </StyledNavLink>
                  </LinkContainer>
                  <LinkContainer>
                    <StyledNavLink
                      id="initialisators"
                      activeStyle={{
                        fontWeight: "bold",
                        borderBottom: "6px solid #9FBD47"
                      }}
                      to="/initialisators"
                    >
                      Alustusoperaatiot
                    </StyledNavLink>
                  </LinkContainer>
                </TabRow>
              </div>
            </MaintenanceContainer>
            <ContentArea>
              <Route exact path="/" render={() => <Redirect to="/excel" />} />
              <Route
                path="/excel"
                exact
                render={routeProps => {
                  return (
                    <TabContainer>
                      <ExcelExport />
                    </TabContainer>
                  );
                }}
              />
              <Route
                path="/join_commitments"
                exact
                render={routeProps => {
                  return (
                    <TabContainer>
                      <JoinCommitments />
                    </TabContainer>
                  );
                }}
              />
              <Route
                path="/report_reminders"
                exact
                render={routeProps => {
                  return (
                    <TabContainer>
                      <ReportReminders {...routeProps} />
                    </TabContainer>
                  );
                }}
              />
              <Route
                path="/initialisators"
                exact
                render={routeProps => {
                  return (
                    <TabContainer>
                      <FormatAddress {...routeProps} />
                    </TabContainer>
                  );
                }}
              />
            </ContentArea>
          </Fragment>
        )}
      />
    );
  }
}

export default withRouter(CommitmentMaintenance);
