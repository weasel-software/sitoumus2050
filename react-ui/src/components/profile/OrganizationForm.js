// @flow

import type {
  ORGANIZATION_TYPE,
  ORGANIZATION_TYPE_TYPE,
  CATEGORY_TYPE,
  PROFILE_TYPE,
  NotificationType
} from "../../constants/types";
import type { ORGANIZATION_STATE } from "../../redux/organizations";

import React, { Fragment } from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";
import { darken, lighten } from "polished";
import { get } from "lodash";

import ProfilePic from "../reusable/ProfilePic";
import TextInput from "../reusable/TextInput";
import AutoComplete from "../reusable/AutoComplete";
import InputIconContainer from "../reusable/InputIconContainer";

import WhiteSpace from "../reusable/WhiteSpace";
import translate from "../reusable/translate";
import Button from "../reusable/Button";
import NotificationContainer from "../reusable/NotificationContainer";

import AddUserToOrganization from "./AddUserToOrganization";

const Row = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
`;

const StyledH4 = styled.h4`
  font-weight: bold;
  font-size: 18px;
`;

const IconContainer = styled.div`
  width: 36px;
  height: 36px;
  min-width: 36px;
  min-height: 36px;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 18px;
  background: ${props => (props.background ? props.background : "white")};
  margin: 5px;
  &:hover {
    background: ${props =>
      props.background ? darken(0.1, props.background) : darken(0.1, "white")};
  }
  &:active {
    background: ${props =>
      props.background ? lighten(0.1, props.background) : darken(0.1, "white")};
  }
`;

const Separator = () => <WhiteSpace height="80px" />;

const handleInvalidForm = ({ invalidFields, fields, form }) => {
  console.log(invalidFields, fields);
};

const OrganizationForm = ({
  handleOrganizationInput,
  organizationTypes,
  organizationSubTypes,
  selectedOrganizationSubType,
  selectedOrganizationType,
  saveOrganization,
  selectOrganizationType,
  selectOrganizationSubtype,
  selectedSubcategoryType,
  organization,
  usersByOrganization,
  organizationSizes,
  selectOrganizationSize,
  removeUserFromOrganization,
  createNew,
  organizationNotification,
  handleLogo
}: ORGANIZATION_STATE & {
  handleOrganizationInput: (SyntheticInputEvent<*>) => void,
  saveOrganization: () => void,
  selectOrganizationType: ORGANIZATION_TYPE_TYPE => void,
  selectOrganizationSubtype: ORGANIZATION_TYPE_TYPE => void,
  selectOrganizationSize: CATEGORY_TYPE => void,
  selectedOrganizationType: ?CATEGORY_TYPE,
  selectedSubcategoryType: ?CATEGORY_TYPE,
  organization: ORGANIZATION_TYPE,
  removeUserFromOrganization: (PROFILE_TYPE, string) => void,
  createNew?: boolean,
  organizationNotification: NotificationType,
  handleLogo: () => void
}) => {
  const members =
    usersByOrganization && organization
      ? usersByOrganization[organization.organizationId]
      : [];

  return (
    <Fragment>
      <form
        onInvalid={handleInvalidForm}
        style={{ padding: 20 }}
        onSubmit={e => {
          e.preventDefault();
          saveOrganization();
          // registerOrganization();
        }}
      >
        {createNew && (
          <Row style={{ marginBottom: 30 }}>
            <Link to="/organisaationi">
              <i className="fas fa-chevron-left" />
              <span style={{ marginLeft: 8 }}>
                {translate({ textKey: "sit.back" })}
              </span>
            </Link>
            <h2 style={{ margin: 0, marginLeft: 20 }}>
              {translate({
                textKey: "sit.profile.myCommitments.addOrganization"
              })}
            </h2>
          </Row>
        )}

        <Row>
          <ProfilePic onSelect={handleLogo} profilePic={organization.logo} />
        </Row>

        <WhiteSpace height="20px" />

        <TextInput
          required={true}
          value={organization ? get(organization, "name", "") : ""}
          onChange={handleOrganizationInput}
          labelStyle={{ minWidth: "120px" }}
          type="text"
          label={translate({ textKey: "sit.profile.organization" })}
          name="name"
          placeholder={translate({
            textKey: "sit.profile.enterOrganizationName"
          })}
        />
        {organizationTypes && (
          <Fragment>
            <AutoComplete
              id="organization_type_autocomplete"
              keyPath="categoryId"
              namePath="name"
              items={organizationTypes}
              selectedItem={
                organization &&
                organization.categories &&
                organization.categories.organizationType
              }
              onChange={selectedItem => selectOrganizationType(selectedItem)}
              renderInput={({ getInputProps, toggleMenu, isOpen }) => (
                <TextInput
                  {...getInputProps({
                    placeholder: translate({
                      textKey:
                        "sit.profile.organizationForm.selectOrganizationType"
                    }),
                    name: "organisationType",
                    style: { width: "100%" },
                    required: true,
                    renderIcon: () =>
                      isOpen ? (
                        <InputIconContainer key={isOpen} onClick={toggleMenu}>
                          <i className="fas fa-chevron-up" />
                        </InputIconContainer>
                      ) : (
                        <InputIconContainer key={isOpen} onClick={toggleMenu}>
                          <i className="fas fa-chevron-down" />
                        </InputIconContainer>
                      ),
                    label: translate({
                      textKey: "sit.profile.organizationForm.organizationType"
                    }),
                    labelStyle: { minWidth: "120px" }
                  })}
                />
              )}
            />

            {selectedSubcategoryType && (
              <AutoComplete
                id="organization_subtype_autocomplete"
                items={selectedSubcategoryType}
                selectedItem={
                  organization &&
                  organization.categories &&
                  organization.categories.organizationSubType
                }
                onChange={selectedItem =>
                  selectOrganizationSubtype(selectedItem)
                }
                renderInput={({ getInputProps, toggleMenu, isOpen }) => (
                  <TextInput
                    {...getInputProps({
                      placeholder: translate({
                        textKey:
                          "sit.profile.organizationForm.selectLineOfBusiness"
                      }),
                      name: "toimiala",
                      style: { width: "100%" },
                      disabled: !selectedOrganizationType,
                      required: true,
                      renderIcon: () =>
                        isOpen ? (
                          <InputIconContainer key={isOpen} onClick={toggleMenu}>
                            <i className="fas fa-chevron-up" />
                          </InputIconContainer>
                        ) : (
                          <InputIconContainer key={isOpen} onClick={toggleMenu}>
                            <i className="fas fa-chevron-down" />
                          </InputIconContainer>
                        ),
                      label: translate({
                        textKey: "sit.profile.lineOfBusiness"
                      }),
                      labelStyle: { minWidth: "120px" }
                    })}
                  />
                )}
              />
            )}
          </Fragment>
        )}
        {selectedOrganizationType &&
          selectedOrganizationType.categoryId === "33497" && (
            <AutoComplete
              id="organization_size_autocomplete"
              items={organizationSizes}
              selectedItem={
                organization &&
                organization.categories &&
                organization.categories.organizationSize
              }
              onChange={selectedItem => selectOrganizationSize(selectedItem)}
              renderInput={({ getInputProps, toggleMenu, isOpen }) => (
                <TextInput
                  {...getInputProps({
                    placeholder: translate({
                      textKey: "sit.profile.enter"
                    }),
                    name: "koko",
                    style: { width: "100%" },
                    disabled: !selectedOrganizationType,
                    required: true,
                    renderIcon: () =>
                      isOpen ? (
                        <InputIconContainer key={isOpen} onClick={toggleMenu}>
                          <i className="fas fa-chevron-up" />
                        </InputIconContainer>
                      ) : (
                        <InputIconContainer key={isOpen} onClick={toggleMenu}>
                          <i className="fas fa-chevron-down" />
                        </InputIconContainer>
                      ),
                    label: translate({
                      textKey: "sit.profile.sizeOfOrganization"
                    }),
                    labelStyle: { minWidth: "120px" }
                  })}
                />
              )}
            />
          )}
        <TextInput
          id="organization_website"
          value={organization ? get(organization, "websites[0].url", "") : ""}
          onChange={handleOrganizationInput}
          required={false}
          labelStyle={{ minWidth: "120px" }}
          type="url"
          label={translate({ textKey: "sit.profile.homepage" })}
          name="websites[0].url"
          placeholder={translate({
            textKey: "sit.profile.organizationForm.organizationUrlExample"
          })}
        />
        <TextInput
          id="organization_short_description"
          value={organization ? get(organization, "comments", "") : ""}
          onChange={handleOrganizationInput}
          labelStyle={{ minWidth: "120px" }}
          required={true}
          type="text"
          label={translate({ textKey: "sit.profile.shortDescription" })}
          name="comments"
          placeholder={translate({ textKey: "sit.profile.enter" })}
        />
        <Separator />
        <Row>
          <TextInput
            id="organization_contact_email"
            value={
              organization
                ? get(organization, "emailAddresses[0].address", "")
                : ""
            }
            onChange={handleOrganizationInput}
            required={true}
            labelStyle={{ minWidth: "120px" }}
            type="email"
            label={translate({ textKey: "sit.profile.contactPerson" })}
            name="emailAddresses[0].address"
            placeholder={translate({
              textKey: "sit.profile.enterEmail"
            })}
          />
        </Row>
        <Row>
          <TextInput
            id="organization_communications_email"
            value={
              organization
                ? get(organization, "emailAddresses[1].address", "")
                : ""
            }
            onChange={handleOrganizationInput}
            labelStyle={{ minWidth: "120px" }}
            type="email"
            label={translate({
              textKey: "sit.profile.contactPersonForCommunications"
            })}
            name="emailAddresses[1].address"
            placeholder={translate({
              textKey: "sit.profile.enterEmail"
            })}
          />
        </Row>
        <Button block type="submit" uppercase id="organization_save_button">
          {createNew
            ? translate({
                textKey: "sit.profile.organizationForm.createOrganization"
              })
            : translate({
                textKey: "sit.profile.organizationForm.saveChanges"
              })}
        </Button>
        <Separator />
        <NotificationContainer
          timeout={7500}
          handleHide={() => {}}
          notificationType={organizationNotification}
        >
          {organizationNotification.message}
        </NotificationContainer>
        <Separator />
      </form>

      {!createNew && (
        <div style={{ padding: 20 }}>
          <AddUserToOrganization organizationId={organization.organizationId} />

          <Separator />

          <StyledH4>{translate({ textKey: "sit.profile.users" })}</StyledH4>

          {members &&
            members.length > 0 &&
            members.map(member => (
              <TextInput
                id={`organization_remove_user_${member.userId}`}
                key={member.userId}
                type="email"
                name=""
                value={member.emailAddress}
                label={translate({ textKey: "sit.email" })}
                style={{ width: "100%" }}
                renderIcon={() => (
                  <Fragment>
                    <IconContainer
                      background="#eee"
                      onClick={() => {
                        const confirmed = window.confirm(
                          `${translate({
                            textKey:
                              "sit.profile.organizationForm.removeUserFromOrganizationConfirmation1"
                          })} ${member.firstName} ${
                            member.lastName
                          } ${translate({
                            textKey:
                              "sit.profile.organizationForm.removeUserFromOrganizationConfirmation2"
                          })}?`
                        );
                        if (confirmed)
                          removeUserFromOrganization(
                            member,
                            organization.organizationId
                          );
                      }}
                    >
                      <i
                        className="fas fa-times"
                        style={{
                          fontSize: 18,
                          margin: 8,
                          color: "#333"
                        }}
                      />
                    </IconContainer>
                  </Fragment>
                )}
              />
            ))}
        </div>
      )}
    </Fragment>
  );
};

export default OrganizationForm;
