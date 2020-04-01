// @flow

import type {
  ReportType,
  OperationType,
  PROFILE_TYPE,
  CommitmentType,
  LocaleType,
  NotificationType
} from "../../../constants/types";

import React, { Component } from "react";
import { NavLink, Route } from "react-router-dom";
import styled from "styled-components";

import getLocalizedString from "../../../utils/getLocalizedString";
import Editor from "./Editor";

const FlexLink = styled(NavLink)`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px 10px 20px;
  color: black !important;
  font-weight: bold;
  font-size: 16px;
  text-transform: uppercase;
`;

class EditingItem extends Component<
  {
    profile: PROFILE_TYPE,
    commitment: CommitmentType,
    report: ReportType,
    operation: OperationType,
    locale: LocaleType,
    match: *,
    resetSaveReportNotification: () => void,
    reportIndexInOperation: number,
    operationIndexInCommitment: number,
    location: *,
    saveReport: ReportType => void,
    saveReportNotification: NotificationType,
    savedReport: ?ReportType,
    editing?: boolean
  },
  {
    routeActive: boolean
  }
> {
  state = {
    routeActive: false
  };

  render() {
    const { operation, commitment } = this.props;
    const linkObject = this.props.location.pathname.endsWith(
      this.props.report.id
    )
      ? {
          pathname: this.props.match.url,
          state: { commitment },
          search: `?commitmentId=${commitment.id}&editing=true`
        }
      : {
          pathname: this.props.match.url + "/" + this.props.report.id,
          state: { commitment },
          search: `?commitmentId=${commitment.id}&editing=true`
        };

    return (
      <div style={{ border: "1px solid #ccc" }} className="col-md-12">
        <FlexLink to={linkObject}>
          {getLocalizedString("reportTitle", this.props.report)}
          <span key={String(this.state.routeActive)}>
            {!this.state.routeActive ? (
              <i className="fa fa-chevron-down report-chevron-down" />
            ) : (
              <i className="fa fa-chevron-up report-chevron-up" />
            )}
          </span>
        </FlexLink>

        <Route
          path={this.props.match.url + "/" + this.props.report.id}
          render={routeProps => {
            return (
              <Editor
                reportIndexInOperation={this.props.reportIndexInOperation}
                operationIndexInCommitment={
                  this.props.operationIndexInCommitment
                }
                commitment={commitment}
                operation={operation}
                report={this.props.report}
                profile={this.props.profile}
                saveReport={this.props.saveReport}
                saveReportNotification={this.props.saveReportNotification}
                resetSaveReportNotification={
                  this.props.resetSaveReportNotification
                }
                savedReport={this.props.savedReport}
                editing={this.props.editing}
              />
            );
          }}
        />
      </div>
    );
  }
}

export default EditingItem;
