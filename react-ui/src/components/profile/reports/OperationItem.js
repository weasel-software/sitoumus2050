// @flow

import type {
  ReportType,
  PROFILE_TYPE,
  CommitmentType,
  OperationType,
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
  text-transform: uppercase;
`;

class OperationItem extends Component<
  {
    profile: PROFILE_TYPE,
    commitment: CommitmentType,
    operation: OperationType,
    operationIndexInCommitment: number,
    locale: LocaleType,
    match: *,
    location: *,
    saveReport: ReportType => void,
    resetSaveReportNotification: () => void,
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
      operation.operationId
    )
      ? {
          pathname: "/raportoi",
          state: { commitment },
          search: `?commitmentId=${commitment.id}`
        }
      : {
          pathname: this.props.match.url + "/" + operation.operationId,
          state: { commitment },
          search: `?commitmentId=${commitment.id}`
        };

    return (
      <div style={{ border: "1px solid #ccc" }}>
        <FlexLink to={linkObject}>
          {getLocalizedString("operationTitle", operation)}
          <span key={String(this.state.routeActive)}>
            {!this.state.routeActive ? (
              <i className="fa fa-chevron-down report-chevron-down" />
            ) : (
              <i className="fa fa-chevron-up report-chevron-up" />
            )}
          </span>
        </FlexLink>

        <Route
          path={this.props.match.url + "/" + operation.operationId}
          render={routeProps => {
            return (
              <Editor
                commitment={commitment}
                operation={operation}
                operationIndexInCommitment={
                  this.props.operationIndexInCommitment
                }
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

export default OperationItem;
