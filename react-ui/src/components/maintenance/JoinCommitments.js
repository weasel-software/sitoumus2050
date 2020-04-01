// @flow

import type { NotificationType } from "../../constants/types";

import React, { Component, Fragment } from "react";
import { connect } from "react-redux";
import styled from "styled-components";

import { JoinCommitmentsActions } from "../../redux/maintenance";

import TextInput from "../reusable/TextInput";
import Button from "../reusable/Button";
import translate from "../reusable/translate";
import NotificationContainer from "../reusable/NotificationContainer";

const StyledH3 = styled.h3`
  font-weight: bold;
  font-size: 20px;
`;

const Separator = styled.div`
  height: 30px;
`;

class JoinCommitments extends Component<
  {
    labelStyle?: any,
    joinCommitments: ({ parentId: string, childId: string }) => *,
    joinCommitmentsNotification: NotificationType
  },
  {
    parentId: string,
    childId: string
  }
> {
  state = {
    parentId: "",
    childId: ""
  };

  handleInput = (e: SyntheticInputEvent<any>) => {
    const { value, name } = e.target;
    this.setState({
      [name]: value
    });
  };

  handleSubmit = () => {
    this.props.joinCommitments({
      ...this.state
    });
  };

  isValid = () => {
    if (this.state.parentId && this.state.childId) {
      return true;
    } else return false;
  };

  render() {
    return (
      <Fragment>
        <StyledH3>
          {translate({ textKey: "sit.commitment.maintenance.join" })}
        </StyledH3>
        <Separator />
        <TextInput
          id="parent_id"
          label={translate({
            textKey: "sit.commitment.maintenance.join.parent"
          })}
          placeholder={translate({
            textKey: "sit.commitment.maintenance.join.parent"
          })}
          name="parentId"
          type="text"
          labelStyle={this.props.labelStyle}
          onChange={this.handleInput}
          required={true}
        />
        <TextInput
          id="child_id"
          label={translate({
            textKey: "sit.commitment.maintenance.join.child"
          })}
          placeholder={translate({
            textKey: "sit.commitment.maintenance.join.child"
          })}
          name="childId"
          type="text"
          labelStyle={this.props.labelStyle}
          onChange={this.handleInput}
          required={true}
        />
        <Separator />
        <Button
          id="join_commitments"
          uppercase
          block
          style={{ marginLeft: 0 }}
          onClick={this.handleSubmit}
          disabled={
            !this.isValid() ||
            this.props.joinCommitmentsNotification.state === "IN_PROGRESS"
          }
          showSpinner={
            this.props.joinCommitmentsNotification.state === "IN_PROGRESS"
          }
        >
          {translate({ textKey: "sit.commitment.maintenance.join" })}
        </Button>
        <Separator />
        {this.props.joinCommitmentsNotification.state !== "IN_PROGRESS" && (
          <NotificationContainer
            timeout={7500}
            handleHide={() => {}}
            notificationType={this.props.joinCommitmentsNotification}
          >
            {this.props.joinCommitmentsNotification.message}
          </NotificationContainer>
        )}
      </Fragment>
    );
  }
}

export default connect(
  state => ({
    joinCommitmentsNotification: state.maintenance.joinCommitmentsNotification
  }),
  {
    joinCommitments: JoinCommitmentsActions.joinCommitments
  }
)(JoinCommitments);
