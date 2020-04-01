// @flow

import type { STORE_STATE } from "../../../redux/store";
import type {
  CommitmentType,
  LocaleType,
  PROFILE_TYPE,
  ReportType,
  OperationType,
  NotificationType
} from "../../../constants/types";

import React, { Component, Fragment } from "react";
import { withRouter } from "react-router-dom";
import { connect } from "react-redux";

import styled from "styled-components";
import { flatMap, get } from "lodash";

import { ArticleActions } from "../../../redux/articles";
import queryString from "../../../vendor/query-string";
import getLocalizedString from "../../../utils/getLocalizedString";

import StyledSubtitle from "../../reusable/StyledSubtitle";
import Row from "../../reusable/Row";
import Column from "../../reusable/Column";
import WhiteSpace from "../../reusable/WhiteSpace";
import translate from "../../reusable/translate";

import EditingItem from "./EditingItem";

const Container = styled.div`
  padding: 20;
`;

class EditingList extends Component<
  {
    commitment: CommitmentType,
    location: *,
    match: *,
    operation: OperationType,
    operationIndexInCommitment: number,
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
    const commitment = this.props.commitment || this.getCommitmentById();
    if (!commitment) return null;
    return (
      <div className="container" ref={c => (this.c = c)}>
        <div className="row">
          <hr />
          <Row>
            <StyledSubtitle flex="1 1 auto" style={{ fontSize: 22 }}>
              {getLocalizedString("operationTitle", this.props.operation)}
            </StyledSubtitle>
          </Row>
          {this.state.readyToRender && (
            <Fragment>
              <Container>
                <Row flex={"1 1 auto"}>
                  <Column>
                    {this.props.operation.reports &&
                    this.props.operation.reports.length > 0 ? (
                      this.props.operation.reports.map((report, index) => (
                        <Fragment key={report.id + index}>
                          <EditingItem
                            operation={this.props.operation}
                            reportIndexInOperation={index}
                            operationIndexInCommitment={
                              this.props.operationIndexInCommitment
                            }
                            location={this.props.location}
                            match={this.props.match}
                            report={report}
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
                          <WhiteSpace height="10px" />
                        </Fragment>
                      ))
                    ) : (
                      <StyledSubtitle>
                        {translate({ textKey: "sit.profile.noReports" })}
                      </StyledSubtitle>
                    )}
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
    { saveReport: ArticleActions.saveReport }
  )(EditingList)
);
