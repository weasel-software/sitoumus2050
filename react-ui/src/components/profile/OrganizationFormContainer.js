// @flow

import React, { Component } from "react";
import { withRouter } from "react-router";
import { connect } from "react-redux";
import { set } from "lodash";
import produce from "immer";

import { OrganizationActions } from "../../redux/organizations";
import type {
  ORGANIZATION_STATE,
  OrganizationDataType
} from "../../redux/organizations";
import type { STORE_STATE } from "../../redux/store";
import type {
  COUNTRY_TYPE,
  ORGANIZATION_TYPE,
  NotificationType,
  PROFILE_TYPE,
  ORGANIZATION_SIZE,
  ORGANIZATION_SUBTYPE_TYPE,
  ORGANIZATION_TYPE_TYPE
} from "../../constants/types";

import OrganizationForm from "./OrganizationForm";

type State = {
  countryList: Array<COUNTRY_TYPE>,
  organization: ORGANIZATION_TYPE
};

class OrganizationFormContainer extends Component<
  {
    match: *,
    registerOrganization: (ORGANIZATION_TYPE, createNew: ?boolean) => void,
    updateOrganization: ORGANIZATION_TYPE => void,
    profile: {
      userId: string
    },
    getUserOrganizations: string => void,
    getOrganizationSizes: () => void,
    getOrganizationTypes: () => void,
    organization: ORGANIZATION_TYPE,
    createNew: boolean,
    removeUserFromOrganization: (PROFILE_TYPE, string) => void,
    organizationNotification: NotificationType,
    usersByOrganization: {
      [organizationId: string]: Array<PROFILE_TYPE>
    },
    organizations: Array<ORGANIZATION_TYPE>,
    organizationSizes: Array<ORGANIZATION_SIZE>,
    countries: Array<COUNTRY_TYPE>,
    organizationSubTypes: ?{
      [string]: ORGANIZATION_SUBTYPE_TYPE
    },
    organizationTypes: Array<ORGANIZATION_TYPE_TYPE>,
    organizationsByName: {
      [name: string]: OrganizationDataType
    },
    organizationsById: {
      [name: string]: OrganizationDataType
    }
  },
  State
> {
  state = {
    logo: "",
    countryList: [],
    members: [],
    organization: this.props.organization || {
      emailAddresses: [],
      websites: [],
      categories: {
        organizationType: null,
        organizationSubType: null,
        organizationSize: null
      },
      comments: "",
      companyId: "",
      countryId: "",
      createDate: "",
      logId: "",
      modifiedDate: "",
      mvccVersion: "",
      name: "",
      organizationId: "",
      parentOrganizationId: "",
      recursable: false,
      regionId: "",
      statusId: "",
      treePath: "",
      type: "",
      userId: "",
      userName: "",
      uuid: ""
    }
  };

  handleOrganizationInput = e => {
    const { name, value } = e.target;

    this.setState(
      // $FlowFixMe
      produce(draft => {
        set(draft.organization, name, value);
      })
    );
  };

  registerOrganization = () => {
    this.props.registerOrganization(
      this.state.organization,
      this.props.createNew || false
    );
  };

  updateOrganization = () => {
    this.props.updateOrganization(this.state.organization);
  };

  handleLogo = file => {
    this.setState(
      // $FlowFixMe
      produce(draft => {
        draft.organization.logo = file;
      })
    );
  };

  render = () => {
    const organizationType =
      this.state.organization.categories &&
      this.state.organization.categories.organizationType;

    const selectedSubcategoryType =
      organizationType && this.props.organizationSubTypes
        ? this.props.organizationSubTypes[organizationType.categoryId]
        : null;

    return (
      <OrganizationForm
        handleLogo={this.handleLogo}
        createNew={this.props.createNew}
        selectedSubcategoryType={selectedSubcategoryType}
        saveOrganization={
          this.props.createNew
            ? this.registerOrganization
            : this.updateOrganization
        }
        handleOrganizationInput={this.handleOrganizationInput}
        organizationTypes={this.props.organizationTypes}
        organizationSubTypes={this.props.organizationSubTypes}
        selectOrganizationType={organizationType => {
          this.setState(
            // $FlowFixMe
            produce(draft => {
              draft.organization.categories.organizationType = organizationType;
            })
          );
        }}
        selectOrganizationSubtype={organizationSubType => {
          this.setState(
            // $FlowFixMe
            produce(draft => {
              draft.organization.categories.organizationSubType = organizationSubType;
            })
          );
        }}
        selectOrganizationSize={organizationSize => {
          this.setState(
            // $FlowFixMe
            produce(draft => {
              draft.organization.categories.organizationSize = organizationSize;
            })
          );
        }}
        countries={this.props.countries}
        organizationSizes={this.props.organizationSizes}
        organizations={this.props.organizations}
        selectedOrganizationType={
          this.state.organization.categories &&
          this.state.organization.categories.organizationType
        }
        selectedOrganizationSubType={
          this.state.organization.categories &&
          this.state.organization.categories.organizationSubType
        }
        usersByOrganization={this.props.usersByOrganization}
        organization={this.state.organization}
        removeUserFromOrganization={this.props.removeUserFromOrganization}
        organizationNotification={this.props.organizationNotification}
        organizationsByName={this.props.organizationsByName}
        organizationsById={this.props.organizationsById}
      />
    );
  };
}

export default withRouter(
  connect(
    (state: STORE_STATE) => {
      const organizations: ORGANIZATION_STATE = state.organizations;
      return {
        ...organizations
      };
    },
    {
      registerOrganization: OrganizationActions.registerOrganization,
      updateOrganization: OrganizationActions.updateOrganization,
      getUserOrganizations: OrganizationActions.getUserOrganizations,
      getOrganizationSizes: OrganizationActions.getOrganizationSizes,
      getOrganizationTypes: OrganizationActions.getOrganizationTypes,
      removeUserFromOrganization: OrganizationActions.removeUserFromOrganization
    }
  )(OrganizationFormContainer)
);
