// @flow

import type { ORGANIZATION_STATE } from "../../redux/organizations";
import type { STORE_STATE } from "../../redux/store";
import type { ORGANIZATION_TYPE, PROFILE_TYPE } from "../../constants/types";

import React, { Fragment, Component } from "react";
import { Route } from "react-router-dom";
import styled from "styled-components";
import { withRouter } from "react-router";
import { connect } from "react-redux";

import { OrganizationActions } from "../../redux/organizations";

import StyledLink from "../reusable/StyledLink";
import WhiteSpace from "../reusable/WhiteSpace";
import Button from "../reusable/Button";
import translate from "../reusable/translate";

import OrganizationFormContainer from "./OrganizationFormContainer";

const Row = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
`;

const Column = styled.div`
  display: flex;
  flex-direction: column;
`;

const StyledH3 = styled.h3`
  font-weight: bold;
  font-size: 20px;
  padding: 20px;
  background: #eee;
  margin: 0;
`;

const SeparatorLine = styled.div`
  border-bottom: 1px solid #dcdcdc;
  height: 40px;
`;

const RoundButton = styled(Button)`
  border-radius: 100px;
  max-width: 40px;
  max-height: 40px;
  min-width: 40px;
  min-height: 40px;
`;

const RowSpaceBetween = styled(Row)`
  justify-content: space-between;
`;

const AddOrgText = styled.span`
  font-size: 14px;
  font-weight: bold;
  color: black;
  &:hover {
    text-decoration: underline;
  }
`;

class MyOrganisations extends Component<{
  organizations: Array<ORGANIZATION_TYPE>,
  match: *,
  profile: PROFILE_TYPE,
  getUserOrganizations: string => void,
  getOrganizationSizes: () => void,
  getOrganizationTypes: () => void
}> {
  componentDidMount() {
    this.props.getOrganizationTypes();
    // this.getCountries();
    this.props.getOrganizationSizes();
    this.props.profile &&
      this.props.getUserOrganizations(this.props.profile.userId);
  }

  handleOrganizationInput = e => {
    const { name, value } = e.target;

    this.setState({
      [name]: value
    });
  };

  render = () => {
    return (
      <Fragment>
        <Route
          path={this.props.match.path + "/create-new"}
          render={() => (
            <OrganizationFormContainer createNew={true} organization={null} />
          )}
        />
        <Route
          path="/organisaationi"
          exact
          render={() => (
            <Fragment>
              <RowSpaceBetween>
                <h2>
                  {translate({
                    textKey: "sit.profile.myCommitments.organizations"
                  })}
                </h2>
                <StyledLink
                  to={this.props.match.path + "/create-new"}
                  id="organization_link_to_add_new_organization"
                >
                  <Row>
                    <AddOrgText>
                      {translate({
                        textKey: "sit.profile.myCommitments.addOrganization"
                      })}
                    </AddOrgText>
                    <RoundButton>+</RoundButton>
                  </Row>
                </StyledLink>
              </RowSpaceBetween>

              {this.props.organizations.map(org => (
                <Column
                  key={org.organizationId}
                  style={{
                    border: "2px solid #93BE38",
                    marginTop: 20,
                    marginBottom: 20
                  }}
                >
                  <StyledH3>{org.name}</StyledH3>

                  <OrganizationFormContainer
                    organization={org}
                    createNew={false}
                  />

                  <WhiteSpace height="60px" />

                  <SeparatorLine />
                </Column>
              ))}
            </Fragment>
          )}
        />
      </Fragment>
    );
  };
}

export default withRouter(
  connect(
    (state: STORE_STATE) => {
      const organizations: ORGANIZATION_STATE = state.organizations;
      return {
        ...organizations,
        profile: state.user.profile
      };
    },
    {
      getUserOrganizations: OrganizationActions.getUserOrganizations,
      getOrganizationSizes: OrganizationActions.getOrganizationSizes,
      getOrganizationTypes: OrganizationActions.getOrganizationTypes
    }
  )(MyOrganisations)
);
