// @flow

import type { PROFILE_TYPE, NotificationType } from "../../constants/types";

import React, { Component, Fragment } from "react";
import { connect } from "react-redux";
import styled from "styled-components";
import { debounce } from "lodash";

import { OrganizationActions } from "../../redux/organizations";
import LiferayClient from "../../utils/LiferayClient";

import NotificationContainer from "../reusable/NotificationContainer";
import TextInput from "../reusable/TextInput";
import Button from "../reusable/Button";
import InputIconContainer from "../reusable/InputIconContainer";
import translate from "../reusable/translate";

const Separator = styled.div`
  height: 80px;
`;

const StyledH3 = styled.h3`
  font-weight: bold;
  font-size: 20px;
`;

const labelStyle = {
  width: "150px"
};

class AddUserToOrganization extends Component<
  {
    organizationId: string,
    addUserToOrganization: (?string, ?string) => *,
    inviteUserToOrganization: (string, string, string, string) => *,
    addUserToOrganizationNotification: NotificationType
  },
  {
    createUserEmail: string,
    foundUser: ?boolean,
    userToInvite: ?PROFILE_TYPE,
    userSearchInProgress: boolean
  }
> {
  state = {
    createUserEmail: "",
    foundUser: null,
    userToInvite: null,
    userSearchInProgress: false
  };

  handleUserEmail = (e: SyntheticInputEvent<HTMLInputElement>) => {
    console.log(e.target.value);
    this.setState(
      {
        createUserEmail: e.target.value,
        userSearchInProgress: true
      },
      () => {
        this.getUserByEmail();
      }
    );
  };

  getUserByEmail = debounce(async () => {
    const email = this.state.createUserEmail;
    if (!email) {
      console.warn("NO EMAIL GIVEN");
      return;
    }

    console.log("EMAIL", email, this.state.createUserEmail);

    try {
      const response = await LiferayClient("/user/get-user-by-email-address", {
        companyId: 20116,
        emailAddress: email
      });

      console.log("RESPO", response);

      this.setState({
        foundUser: true,
        userToInvite: response,
        userSearchInProgress: false
      });
    } catch (error) {
      this.setState({
        foundUser: false,
        userToInvite: null,
        userSearchInProgress: false
      });
    }
  }, 1500);

  componentDidUpdate({
    addUserToOrganizationNotification
  }: {
    addUserToOrganizationNotification: NotificationType
  }) {
    if (
      addUserToOrganizationNotification.state !== "SUCCESS" &&
      this.props.addUserToOrganizationNotification.state === "SUCCESS"
    ) {
      this.setState({
        foundUser: false,
        userToInvite: null,
        createUserEmail: ""
      });
    }
  }

  inviteUser = () => {
    if (!this.state.foundUser) {
      if (this.state.userToInvite)
        this.props.inviteUserToOrganization(
          this.state.createUserEmail,
          this.state.userToInvite.firstName,
          this.state.userToInvite.lastName,
          this.props.organizationId
        );
    }
  };

  render() {
    return (
      <form
        onSubmit={e => {
          e.preventDefault();
          if (this.state.foundUser) {
            this.props.addUserToOrganization(
              this.state.userToInvite ? this.state.userToInvite.userId : "",
              this.props.organizationId
            );
          } else {
            this.inviteUser();
          }
        }}
      >
        <StyledH3>{translate({ textKey: "sit.profile.addUsers" })}</StyledH3>
        <Separator />
        <TextInput
          required
          id="organization_add_user_email"
          onChange={this.handleUserEmail}
          label={translate({ textKey: "sit.email" })}
          placeholder={translate({ textKey: "sit.profile.enter" })}
          name=""
          type="email"
          labelStyle={labelStyle}
          value={this.state.createUserEmail}
          renderIcon={() => {
            const { foundUser } = this.state;
            return (
              <InputIconContainer
                style={{ width: "200px" }}
                valid={foundUser === true}
                renderValid={() => {
                  return (
                    <Fragment>
                      {translate({ textKey: "sit.profile.userFound" })} &nbsp;
                      <i className="fas fa-check" />
                    </Fragment>
                  );
                }}
              />
            );
          }}
        />
        <Separator />
        <div>
          <TextInput
            required
            disabled={this.state.userSearchInProgress}
            id="organization_add_user_first_name"
            value={
              this.state.userToInvite ? this.state.userToInvite.firstName : ""
            }
            onChange={e =>
              this.setState({
                userToInvite: {
                  ...this.state.userToInvite,
                  firstName: e.target.value
                }
              })
            }
            label={translate({ textKey: "sit.profile.firstName" })}
            placeholder={translate({ textKey: "sit.profile.enter" })}
            name=""
            type="text"
            labelStyle={labelStyle}
          />

          <TextInput
            required
            disabled={this.state.userSearchInProgress}
            id="organization_add_user_last_name"
            value={
              this.state.userToInvite ? this.state.userToInvite.lastName : ""
            }
            onChange={e =>
              this.setState({
                userToInvite: {
                  ...this.state.userToInvite,
                  lastName: e.target.value
                }
              })
            }
            label={translate({ textKey: "sit.profile.lastName" })}
            placeholder={translate({ textKey: "sit.profile.enter" })}
            name=""
            type="text"
            labelStyle={labelStyle}
          />
        </div>
        <Separator />
        <Button
          id="organization_add_user_send_invitation"
          uppercase
          block
          disabled={this.state.foundUser === null}
          type="submit"
        >
          {translate({ textKey: "sit.profile.inviteToOrganization" })}
        </Button>
        <NotificationContainer
          notificationType={this.props.addUserToOrganizationNotification}
          timeout={5000}
          handleHide={() => {}}
        />
      </form>
    );
  }
}

export default connect(
  state => ({
    addUserToOrganizationNotification:
      state.organizations.addUserToOrganizationNotification
  }),
  {
    addUserToOrganization: OrganizationActions.addUserToOrganization,
    inviteUserToOrganization: OrganizationActions.inviteUserToOrganization
  }
)(AddUserToOrganization);
