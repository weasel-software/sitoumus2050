// @flow

import type { OrganizationDataType } from "../../redux/organizations";
import type { STORE_STATE } from "../../redux/store";
import type {
  ORGANIZATION_TYPE_TYPE,
  ORGANIZATION_SUBTYPE_TYPE
} from "../../constants/types";

import React, { Component, Fragment } from "react";
import { connect } from "react-redux";
import { withRouter, Route } from "react-router-dom";
import styled from "styled-components";
import { get } from "lodash";

import { ArticleActions } from "../../redux/articles";
import { OrganizationActions } from "../../redux/organizations";
import qs from "../../vendor/query-string";

import Column from "../reusable/Column";
import Dropdown from "../reusable/Dropdown";
import { RadioButtonContainer, RadioButton } from "../reusable/Radio";
import Row from "../reusable/Row";
import ProfilePic from "../reusable/ProfilePic";
import translate from "../reusable/translate";

import CommitmentList from "../commitmentList/CommitmentList";

import Commitment from "./index";

const Center = styled.div`
  max-width: 960px;
  margin: auto;
  display: flex;
  flex-direction: column;
`;

const JustifyRight = styled.div`
  display: flex;
  justify-content: flex-end;
  align-items: center;
  flex-direction: row;
  max-width: 960px;
  align-self: flex-end;
`;

const OrganizationRow = styled.div`
  display: flex;
  flex: 1;
  background: #f4f4f4;
  height: 204px;
  width: 100%;
  flex-direction: row;
  justify-content: center;
  padding: 20px;
`;

class OrganizationCommitments extends Component<
  {
    getCommitmentsByOrganizationName: (string, ?string) => void,
    getOrganizationByOrganizationName: string => void,
    match: any,
    commitmentsByOrganizationName: {
      [name: string]: OrganizationDataType
    },
    organizationTypes: Array<ORGANIZATION_TYPE_TYPE>,
    organizationSubTypes: {
      [string]: Array<ORGANIZATION_SUBTYPE_TYPE>
    },
    organizationsByName: {
      [name: string]: OrganizationDataType
    }
  },
  {
    organizationName: string,
    sortCriteria: ?{
      value: ?string,
      name: ?string
    }
  }
> {
  state = {
    organizationName: "",
    sortCriteria: {
      value: "latest",
      name: translate({
        textKey: "sit.commitmentList.newest"
      })
    }
  };

  componentDidMount() {
    const query = qs.parse(window.location.search);
    const organizationName = query.organization;

    this.setState({
      organizationName
    });

    this.props.getCommitmentsByOrganizationName(organizationName);
    this.props.getOrganizationByOrganizationName(organizationName);
  }

  getCommitments = (
    name = this.state.organizationName,
    sort = this.state.sortCriteria
  ) => {
    this.props.getCommitmentsByOrganizationName(name, sort ? sort.value : "");
  };

  renderName = name => {
    if (!name) return "";
    if (name.endsWith("puuttuva tieto")) return "";
    const s = name.split(" ");
    if (s[0] === s[1]) return s[0];
    else return name;
  };

  render() {
    const articles = get(
      this.props.commitmentsByOrganizationName,
      this.state.organizationName
    );
    const organization = get(
      this.props.organizationsByName,
      this.state.organizationName
    );

    if (!organization) return null;

    const orgType =
      this.props.organizationTypes &&
      this.props.organizationTypes.find(
        orgType => orgType.categoryId === organization.organizationTypeId
      );

    const orgSubType =
      organization.organizationSubTypeId &&
      this.props.organizationSubTypes &&
      orgType &&
      get(this.props.organizationSubTypes, [orgType.categoryId], []).find(
        subType => subType.categoryId === organization.organizationSubTypeId
      );

    return (
      <div>
        <Route
          exact
          path="/"
          render={routeProps => (
            <Fragment>
              <OrganizationRow>
                <Row style={{ maxWidth: "960px" }} alignItems="center">
                  <Column style={{ flexGrow: 0 }}>
                    <ProfilePic
                      profilePic={organization.logo}
                      onSelect={() => {}}
                      disabled={true}
                    />
                  </Column>
                  <Column>
                    <h2>{this.state.organizationName}</h2>{" "}
                    {orgType && <h4>{orgType.titleCurrentValue}</h4>}
                    {orgSubType && <h4>{orgSubType.titleCurrentValue}</h4>}
                  </Column>
                </Row>
              </OrganizationRow>

              <Center>
                <Row>
                  <h2>
                    {translate({ textKey: "sit.commitment.commitments" })}
                  </h2>
                  <JustifyRight>
                    <Dropdown
                      height="42px"
                      label={translate({ textKey: "sit.commitmentList.sort" })}
                      items={[
                        {
                          label: translate({
                            textKey: "sit.commitmentList.latest"
                          }),
                          name: translate({
                            textKey: "sit.commitmentList.latest"
                          }),
                          value: "latest",
                          id: "latest"
                        },
                        {
                          label: translate({
                            textKey: "sit.commitmentList.oldest"
                          }),
                          name: translate({
                            textKey: "sit.commitmentList.oldest"
                          }),
                          value: "oldest",
                          id: "oldest"
                        },
                        {
                          label: translate({
                            textKey: "sit.commitmentList.alphabetAscending"
                          }),
                          value: "alphabet_asc",
                          name: translate({
                            textKey: "sit.commitmentList.alphabetAscending"
                          }),
                          id: "alphabet_asc"
                        },
                        {
                          label: translate({
                            textKey: "sit.commitmentList.priority"
                          }),
                          value: "priority",
                          name: translate({
                            textKey: "sit.commitmentList.priority"
                          }),
                          id: "priority"
                        }
                      ]}
                      render={({ items, value }) => (
                        <RadioButtonContainer
                          style={{
                            minWidth: "160px",
                            border: 0,
                            minHeight: 36
                          }}
                        >
                          {items.map(item => (
                            <RadioButton
                              id={item.id}
                              defaultChecked={
                                get(this.state, "sortCriteria.value") ===
                                item.value
                              }
                              key={item.id}
                              label={item.label}
                              name="filterType"
                              value={item.name}
                              onChange={() => {
                                this.setState(
                                  {
                                    sortCriteria: {
                                      value: item.value,
                                      name: item.name
                                    }
                                  },
                                  () => this.getCommitments()
                                );
                              }}
                            />
                          ))}
                        </RadioButtonContainer>
                      )}
                    />
                  </JustifyRight>
                </Row>

                <CommitmentList
                  articles={articles}
                  renderName={this.renderName}
                  {...routeProps}
                />
              </Center>
            </Fragment>
          )}
        />
        <Route
          exact
          path={this.props.match.path + "/details/:articleId"}
          render={routeProps => (
            <Commitment renderName={this.renderName} {...routeProps} />
          )}
        />
      </div>
    );
  }
}

export default withRouter(
  connect(
    (state: STORE_STATE) => ({
      commitmentsByOrganizationName:
        state.articles.commitmentsByOrganizationName,
      organizationsByName: state.organizations.organizationsByName,
      organizationTypes: state.organizations.organizationTypes,
      organizationSubTypes: state.organizations.organizationSubTypes
    }),
    {
      getCommitmentsByOrganizationName:
        ArticleActions.getCommitmentsByOrganizationName,
      getOrganizationByOrganizationName:
        OrganizationActions.getOrganizationByOrganizationName
    }
  )(OrganizationCommitments)
);
