// @flow

import type { STORE_STATE } from "../../../redux/store";
import type {
  CommitmentType,
  LocaleType,
  PROFILE_TYPE,
  ReportType,
  NotificationType
} from "../../../constants/types";

import React, { Component, Fragment } from "react";
import { withRouter, Link } from "react-router-dom";
import { connect } from "react-redux";
import styled from "styled-components";
import { flatMap, get } from "lodash";

import { SecondaryButton } from "../../reusable/Button";
import ScrollTo from "../../reusable/ScrollTo";
import StyledSubtitle from "../../reusable/StyledSubtitle";
import Row from "../../reusable/Row";
import Column from "../../reusable/Column";
import WhiteSpace from "../../reusable/WhiteSpace";
import translate from "../../reusable/translate";

import getLocalizedString from "../../../utils/getLocalizedString";
import { ArticleActions } from "../../../redux/articles";
import queryString from "../../../vendor/query-string";

import OperationItem from "./OperationItem";
import EditingList from "./EditingList";

const Container = styled.div`
  padding: 20;
`;

const OrgNameText = styled.span`
  font-size: 14px;
  text-transform: uppercase;
  color: #6b6b6b;
`;

class OperationsList extends Component<
  {
    commitment: CommitmentType,
    location: *,
    match: *,
    commitments: Array<CommitmentType>,
    locale: LocaleType,
    saveReport: ReportType => void,
    profile: PROFILE_TYPE,
    resetSaveReportNotification: () => void,
    saveReportNotification: NotificationType,
    savedReport: ?ReportType,
    editing?: boolean
  },
  {
    readyToRender: boolean,
    editing: boolean
  }
> {
  state = {
    readyToRender: false,
    editing: false
  };
  c = null;

  componentDidMount() {
    if (this.c) {
      this.setState({
        readyToRender: true,
        editing: queryString.parse(this.props.location.search).editing
      });
    }
  }

  getCommitmentById = () => {
    const query = queryString.parse(this.props.location.search);
    const id = query && query.commitmentId;
    return this.props.commitments.find(com => com.id === id);
  };

  render() {
    const commitment = this.getCommitmentById();
    if (!commitment) return null;
    return (
      <div className="container" ref={c => (this.c = c)}>
        <div className="row">
          <WhiteSpace height="20px" />

          <Row justifyContent="space-between" alignItems="center">
            <Link to="/sitoumukset">
              <SecondaryButton style={{ height: 42, minWidth: 160 }}>
                <i className="fa fa-chevron-left" />{" "}
                {translate({ textKey: "sit.back" })}
              </SecondaryButton>
            </Link>
            <span>
              {this.state.editing ? (
                <StyledSubtitle flex={0}>
                  {translate({ textKey: "sit.profile.editReports" })}
                </StyledSubtitle>
              ) : (
                <StyledSubtitle flex={0}>
                  {translate({ textKey: "sit.profile.reportCommitment" })}
                </StyledSubtitle>
              )}
            </span>
          </Row>
          <hr />

          {this.state.readyToRender && (
            <Fragment>
              <ScrollTo element={this.c} />
              <Container>
                <Row flex={1}>
                  <Column>
                    <StyledSubtitle style={{ fontSize: 22 }}>
                      {getLocalizedString("title", commitment)}
                    </StyledSubtitle>
                    <OrgNameText>
                      {commitment.organizationName ||
                        commitment.createdByUserName}
                    </OrgNameText>
                  </Column>
                </Row>

                <Row flex={"1 1 auto"}>
                  <Column>
                    {commitment.operations &&
                      commitment.operations.map((op, index) => (
                        <Fragment key={op.operationId + index}>
                          {this.state.editing ? (
                            <EditingList
                              location={this.props.location}
                              operationIndexInCommitment={index}
                              match={this.props.match}
                              operation={op}
                              locale={this.props.locale}
                              commitment={commitment}
                              saveReport={this.props.saveReport}
                              profile={this.props.profile}
                              saveReportNotification={
                                this.props.saveReportNotification
                              }
                              savedReport={this.props.savedReport}
                              resetSaveReportNotification={
                                this.props.resetSaveReportNotification
                              }
                              editing={this.state.editing}
                            />
                          ) : (
                            <OperationItem
                              location={this.props.location}
                              operationIndexInCommitment={index}
                              EditingList={this.props.location}
                              match={this.props.match}
                              operation={op}
                              locale={this.props.locale}
                              commitment={commitment}
                              saveReport={this.props.saveReport}
                              profile={this.props.profile}
                              resetSaveReportNotification={
                                this.props.resetSaveReportNotification
                              }
                              saveReportNotification={
                                this.props.saveReportNotification
                              }
                              savedReport={this.props.savedReport}
                              editing={this.state.editing}
                            />
                          )}
                          <WhiteSpace height="10px" />
                        </Fragment>
                      ))}
                  </Column>
                </Row>
              </Container>
            </Fragment>
          )}
        </div>
      </div>
    );
  }
}

export default withRouter(
  connect(
    (state: STORE_STATE) => {
      const commitments = [
        ...get(state.articles, "userCommitments", []),
        ...flatMap(state.articles.commitmentsForOrganizations, com => com)
      ];

      return {
        commitments,
        locale: state.user.locale,
        profile: state.user.profile,
        saveReportNotification: state.articles.saveReportNotification,
        savedReport: state.articles.savedReport
      };
    },
    {
      saveReport: ArticleActions.saveReport,
      resetSaveReportNotification: ArticleActions.resetSaveReportNotification
    }
  )(OperationsList)
);
