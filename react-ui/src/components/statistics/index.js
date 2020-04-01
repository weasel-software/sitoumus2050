// @flow

import React, { Fragment } from "react";
import styled from "styled-components";
import { NavLink, Route, withRouter, Redirect } from "react-router-dom";
import { connect } from "react-redux";

import DateLineChart from "./DateLineChart";
import BarChart from "./BarChart";
import PieChart from "./PieChart";

import translate from "../reusable/translate";

import { StatisticsActions } from "../../redux/statistics";

import type {
  LabelValueDataType,
  DatesTextsValuesDataType
} from "../../constants/types";

const StatisticsContainer = styled.div`
  display: flex;
  align-items: flex-start;
  flex-direction: column;
`;

const TabRow = styled.div`
  display: flex;
  flex-direction: row;
  flex: 1 1 auto;
`;

const LinkContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 0 10px 0 0;
`;

const LinkDividerLine = styled.div`
  border-left: 1px solid #555;
  margin-top: 5px;
  padding: 0 10px 0 0;
  height: 20px;
`;

const StyledNavLink = styled(NavLink)`
  && {
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

const StyledTopic = styled.span`
  && {
    border-bottom: 6px solid transparent;
    text-transform: uppercase;
    color: #555;
    font-weight: bold;
    text-decoration: none;
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

class Statistics extends React.Component<
  {
    mainGoalData: LabelValueDataType,
    organizationTypeData: LabelValueDataType,
    startingYearData: LabelValueDataType,
    cumulativeData: DatesTextsValuesDataType,
    updateByOrganizationTypeData: () => void,
    updateByMainGoalData: () => void,
    updateByStartingYearData: () => void,
    updateCumulativeData: () => void
  },
  any
> {
  componentWillMount() {
    if (this.props.mainGoalData.labels.length <= 1) {
      this.props.updateCumulativeData();
      this.props.updateByOrganizationTypeData();
      this.props.updateByMainGoalData();
      this.props.updateByStartingYearData();
    }
  }
  render() {
    const {
      mainGoalData,
      organizationTypeData,
      startingYearData,
      cumulativeData
    } = this.props;
    return (
      <Route
        path="/"
        render={routeProps => (
          <Fragment>
            <StatisticsContainer>
              <div className="container">
                <h1 style={{ fontWeight: 400, paddingBottom: 30 }}>
                  {translate({ textKey: "sit.statistics.title" })}
                </h1>

                <TabRow>
                  <LinkContainer>
                    <StyledTopic>
                      {translate({ textKey: "sit.statistics.commitments" })}:{" "}
                    </StyledTopic>
                  </LinkContainer>
                  <LinkContainer>
                    <StyledNavLink
                      id="link_to_cumulative"
                      activeStyle={{
                        color: "#93be38"
                      }}
                      to="/kumulatiivinen"
                    >
                      {translate({ textKey: "sit.statistics.combined" })}
                    </StyledNavLink>
                  </LinkContainer>
                  <LinkDividerLine />
                  <LinkContainer>
                    <StyledNavLink
                      id="link_to_byTypes"
                      activeStyle={{
                        color: "#93be38"
                      }}
                      to="/tavoitteittain"
                    >
                      {translate({ textKey: "sit.statistics.byGoals" })}
                    </StyledNavLink>
                  </LinkContainer>
                  <LinkDividerLine />
                  <LinkContainer>
                    <StyledNavLink
                      id="link_to_by_starting_years"
                      activeStyle={{
                        color: "#9FBD47"
                      }}
                      to="/aloitusvuosittain"
                    >
                      {translate({ textKey: "sit.statistics.byStartingYears" })}
                    </StyledNavLink>
                  </LinkContainer>
                  <LinkDividerLine />
                  <LinkContainer>
                    <StyledNavLink
                      id="link_to_create_commitment"
                      activeStyle={{
                        color: "#9FBD47"
                      }}
                      to="/organisaatiotyypeittain"
                    >
                      {translate({
                        textKey: "sit.statistics.byOrganizationTypes"
                      })}
                    </StyledNavLink>
                  </LinkContainer>
                </TabRow>
              </div>
            </StatisticsContainer>
            <ContentArea>
              <Route
                exact
                path="/"
                render={() => <Redirect to="/kumulatiivinen" />}
              />
              <Route
                path="/kumulatiivinen"
                exact
                re
                render={routeProps => {
                  return (
                    <TabContainer>
                      <DateLineChart
                        routeProps={routeProps}
                        dates={cumulativeData.dates}
                        values={cumulativeData.values}
                        texts={cumulativeData.texts}
                      />
                    </TabContainer>
                  );
                }}
              />
              <Route
                path="/tavoitteittain"
                render={routeProps => (
                  <TabContainer>
                    <BarChart
                      routeProps={routeProps}
                      labels={mainGoalData.labels}
                      values={mainGoalData.values}
                    />
                  </TabContainer>
                )}
              />

              <Route
                path="/aloitusvuosittain"
                render={routeProps => (
                  <TabContainer>
                    <BarChart
                      routeProps={routeProps}
                      labels={startingYearData.labels}
                      values={startingYearData.values}
                    />
                  </TabContainer>
                )}
              />

              <Route
                path="/organisaatiotyypeittain"
                render={routeProps => (
                  <TabContainer>
                    <PieChart
                      routeProps={routeProps}
                      labels={organizationTypeData.labels}
                      values={organizationTypeData.values}
                    />
                  </TabContainer>
                )}
              />
            </ContentArea>
          </Fragment>
        )}
      />
    );
  }
}

export default withRouter(
  connect(
    state => ({
      updatingBarChart: state.statistics.updatingBarChart,
      cumulativeData: state.statistics.data.cumulativeData,
      mainGoalData: state.statistics.data.mainGoalData,
      startingYearData: state.statistics.data.startingYearData,
      organizationTypeData: state.statistics.data.organizationTypeData
    }),
    {
      updateByOrganizationTypeData:
        StatisticsActions.updateByOrganizationTypeData,
      updateByStartingYearData: StatisticsActions.updateByStartingYearData,
      updateByMainGoalData: StatisticsActions.updateByMainGoalData,
      updateCumulativeData: StatisticsActions.updateCumulativeData
    }
  )(Statistics)
);
