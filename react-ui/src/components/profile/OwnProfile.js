// @flow

import type {
  COUNTRY_TYPE,
  PROFILE_TYPE,
  NotificationType,
  CityType
} from "../../constants/types";

import React, { Fragment, Component } from "react";
import styled from "styled-components";
import { connect } from "react-redux";
import { get } from "lodash";
import produce from "immer";

import { UserActions } from "../../redux/user";

import ProfilePic from "../reusable/ProfilePic";
import TextInput from "../reusable/TextInput";
import AutoComplete from "../reusable/AutoComplete";
import Button from "../reusable/Button";
import Dropdown from "../reusable/Dropdown";
import { RadioButtonContainer, RadioButton } from "../reusable/Radio";
import InputIconContainer from "../reusable/InputIconContainer";
import NotificationContainer from "../reusable/NotificationContainer";
import translate from "../reusable/translate";
import getLocalizedString from "../../utils/getLocalizedString";

import Checkbox from "../reusable/Checkbox";
import WhiteSpace from "../reusable/WhiteSpace";
import StyledSubtitle from "../reusable/StyledSubtitle";

import UpdatePassword from "./UpdatePassword";

const Row = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
`;

const Column = styled.div`
  display: flex;
  flex-direction: column;
`;

const CheckboxFieldLabel = styled.label`
  display: flex;
  align-self: flex-start;
`;

const Label = styled.span`
  margin-right: 8px;
  min-width: 120px;
  cursor: default;
  display: flex;
  flex: 1 0 auto;
  font-weight: bold;
`;
class OwnProfile extends Component<
  {
    profile: PROFILE_TYPE,
    routeProps: any,
    updateProfile: PROFILE_TYPE => *,
    updateProfileNotification: NotificationType,
    countries: Array<COUNTRY_TYPE>,
    cities: Array<CityType>
  },
  { profile: PROFILE_TYPE }
> {
  state = {
    profile: this.props.profile
  };

  handleProfileInput = ({
    value,
    name
  }: {
    value: string | number | boolean,
    name: string
  }) => {
    this.setState({
      profile: {
        ...this.state.profile,
        [name]: value
      }
    });
  };

  handleAddressInput = ({ name, value }) => {
    const address = get(this.state.profile, "addresses[0]", []);
    console.log(address, name, value);
    this.setState({
      profile: {
        ...this.state.profile,
        addresses: [
          {
            ...address,
            [name]: value,
            street1: "Unused placeholder value",
            zip: 12345,
            typeId: 11001
          }
        ]
      }
    });
  };

  handleProfileUpdate = () => {
    const { profile } = this.state;
    if (profile) this.props.updateProfile(profile);
  };

  getCountryValue = (countryId, countries = this.props.countries) => {
    if (!countryId) return null;
    return countries.find(country => country.countryId === countryId);
  };

  render() {
    const { profile } = this.state;

    return (
      <Fragment>
        <Row>
          <ProfilePic
            profilePic={profile.profilePicURL || ""}
            onSelect={pic => {
              console.log("ON SELECT PIC", pic);
              this.setState({
                profile: {
                  ...this.state.profile,
                  profilePicURL: pic
                }
              });
            }}
          />
        </Row>

        <WhiteSpace height="30px" />

        <StyledSubtitle>
          {translate({ textKey: "sit.profile.emailInUse" })}
          {": "}
          {profile
            ? profile.emailAddress
            : translate({ textKey: "sit.profile.errorFetchingEmail" })}
        </StyledSubtitle>

        <TextInput
          id="profile_first_name"
          onChange={({ target: { name, value } }) =>
            this.handleProfileInput({ name, value })
          }
          label={translate({ textKey: "sit.profile.firstName" })}
          placeholder={translate({ textKey: "sit.profile.enterFirstName" })}
          name="firstName"
          value={profile ? profile.firstName : ""}
          type="text"
          labelStyle={{ minWidth: "120px" }}
        />
        <TextInput
          id="profile_last_name"
          onChange={({ target: { name, value } }) =>
            this.handleProfileInput({ name, value })
          }
          label={translate({ textKey: "sit.profile.lastName" })}
          placeholder={translate({ textKey: "sit.profile.enterLastName" })}
          name="lastName"
          value={profile ? profile.lastName : ""}
          type="text"
          labelStyle={{ minWidth: "120px" }}
        />
        <WhiteSpace height="60px" />
        <Row>
          <Label>{translate({ textKey: "sit.profile.city" })}</Label>
          <Dropdown
            id="profile_city"
            padding="0px"
            right="30px"
            left="30px"
            height="42px"
            width="120px"
            labelMargin="0px 0px 0px 12px"
            fontSize="13px"
            showValue={false}
            hoverBackground="transparent"
            justifyLabel="flex-start"
            label={get(
              profile,
              "addresses[0].city",
              `${translate({
                textKey: "sit.commitmentCreation.selectCity"
              })}...`
            )}
            items={this.props.cities.map(city => ({
              label: getLocalizedString("name", city),
              name: getLocalizedString("name", city),
              value: city.name_fi_FI,
              id: city.name_fi_FI
            }))}
            render={({ items, value }) => (
              <RadioButtonContainer
                style={{
                  minWidth: 130,
                  border: 0,
                  minHeight: 36
                }}
                height="400px"
                overflowY="scroll"
              >
                {items.map(item => (
                  <RadioButton
                    id={item.id}
                    defaultChecked={
                      get(profile, "addresses[0].city", "") === item.value
                    }
                    key={item.id}
                    label={item.label}
                    name="cityRadioButton"
                    value={item.id}
                    onChange={event => {
                      this.setState(
                        // $FlowFixMe
                        produce(draft => {
                          this.handleAddressInput({
                            name: "city",
                            value: event.value
                          });
                        })
                      );
                    }}
                  />
                ))}
              </RadioButtonContainer>
            )}
          />
        </Row>
        <AutoComplete
          id="profile_country_autocomplete"
          items={this.props.countries}
          namePath="nameCurrentValue"
          keyPath="nameCurrentValue"
          enableFilter
          onChange={selectedItem => {
            this.handleAddressInput({
              name: "countryId",
              value: selectedItem.countryId
            });
          }}
          selectedItem={this.getCountryValue(
            get(this.state.profile, "addresses[0].countryId"),
            this.props.countries
          )}
          renderInput={({
            getInputProps,
            toggleMenu,
            isOpen,
            selectedItem
          }) => {
            return (
              <TextInput
                {...getInputProps({
                  placeholder: translate({
                    textKey: "sit.profile.enterAndChoose"
                  }),
                  name: "countryId",
                  style: { width: "100%" },
                  labelStyle: { minWidth: "120px" },
                  label: translate({ textKey: "sit.profile.country" }),
                  id: "profile_country_input",
                  onKeyDown: e => {
                    if (e.key === "Enter") {
                      const country = this.props.countries.find(
                        c =>
                          c.nameCurrentValue.toLowerCase() ===
                          e.target.value.toLowerCase()
                      );
                      if (country) {
                        this.handleAddressInput({
                          name: "countryId",
                          value: country.countryId
                        });
                        toggleMenu();
                      }
                    }
                  },
                  renderIcon: () => (
                    <Fragment>
                      {isOpen ? (
                        <InputIconContainer
                          onClick={toggleMenu}
                          key={isOpen}
                          render={() => <i className="fas fa-chevron-up" />}
                        />
                      ) : (
                        <InputIconContainer
                          onClick={toggleMenu}
                          key={isOpen}
                          render={() => <i className="fas fa-chevron-down" />}
                        />
                      )}
                    </Fragment>
                  )
                })}
              />
            );
          }}
        />
        <WhiteSpace height="60px" />
        <Row style={{ paddingTop: 20, paddingBottom: 20 }}>
          <CheckboxFieldLabel style={{ minWidth: "120px" }}>
            {translate({ textKey: "sit.profile.areYou" })}
          </CheckboxFieldLabel>
          <Column>
            <RadioButtonContainer
              onChange={e => {
                this.setState({
                  profile: {
                    ...this.state.profile,
                    personType: e.target.value
                  }
                });
              }}
            >
              <RadioButton
                checked={
                  profile
                    ? profile.personType === "Organisaation edustaja"
                    : false
                }
                id="profile_user_is_employee"
                label={translate({
                  textKey: "sit.profile.employeeOfAnOrganization"
                })}
                name="personType"
                value="Organisaation edustaja"
                style={{ border: 0, minHeight: 30 }}
                onChange={this.handleProfileInput}
              />
              <RadioButton
                checked={
                  profile ? profile.personType === "Yksityishenkilö" : false
                }
                id="profile_user_is_individual"
                label={translate({ textKey: "sit.profile.individual" })}
                value="Yksityishenkilö"
                name="personType"
                style={{ border: 0, minHeight: 30 }}
                onChange={this.handleProfileInput}
              />
            </RadioButtonContainer>
          </Column>
        </Row>
        <Row style={{ paddingTop: 20, paddingBottom: 20 }}>
          <Checkbox
            id="profile_reminder"
            checked={profile ? profile.allowEmailReminder : false}
            name="allowEmailReminder"
            onChange={({ target: { name, checked } }) =>
              this.handleProfileInput({ name, value: checked })
            }
            label={translate({
              textKey: "sit.profile.allowEmailNotifications"
            })}
            style={{ border: 0 }}
          />
        </Row>
        <Button
          id="profile_save"
          block
          uppercase
          onClick={this.handleProfileUpdate}
          disabled={
            this.props.updateProfileNotification.state === "IN_PROGRESS"
          }
          showSpinner={
            this.props.updateProfileNotification.state === "IN_PROGRESS"
          }
        >
          {translate({ textKey: "sit.save" })}
        </Button>
        {this.props.updateProfileNotification.state !== "IN_PROGRESS" && (
          <NotificationContainer
            timeout={7500}
            handleHide={() => {}}
            notificationType={this.props.updateProfileNotification}
          >
            {this.props.updateProfileNotification.message}
          </NotificationContainer>
        )}
        <WhiteSpace height="60px" />
        <UpdatePassword />
      </Fragment>
    );
  }
}

export default connect(
  state => ({
    profile: state.user.profile,
    countries: state.organizations.countries,
    updateProfileNotification: state.user.updateProfileNotification,
    cities: state.user.cities
  }),
  {
    updateProfile: UserActions.updateProfile
  }
)(OwnProfile);
