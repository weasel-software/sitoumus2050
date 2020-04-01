// @flow

import type {
  PROFILE_TYPE,
  ORGANIZATION_TYPE,
  CATEGORY_TYPE
} from "../../../constants/types";

import React, { Component, Fragment } from "react";
import { Link } from "react-router-dom";
import { Route, withRouter } from "react-router";
import styled from "styled-components";
import { connect } from "react-redux";
import { get } from "lodash";

import Button from "../../reusable/Button";
import InputIconContainer from "../../reusable/InputIconContainer";
import TextInput from "../../reusable/TextInput";
import AutoComplete from "../../reusable/AutoComplete";
import WhiteSpace from "../../reusable/WhiteSpace";
import CommitmentCreationFormContainer from "./CommitmentCreationFormContainer";

import { vocCommitmentType } from "../../../constants/constants";
import LiferayClient from "../../../utils/LiferayClient";
import Row from "../../reusable/Row";

import type { HundredTodosType } from "../../../constants/types";

import translate from "../../reusable/translate";

import { UserActions } from "../../../redux/user";

import type { STORE_STATE } from "../../../redux/store";
import { ArticleActions } from "../../../redux/articles";

const RoundButtonWithIcon = styled(Button)`
  width: ${props => props.size || "50px"};
  max-width: ${props => props.size || "50px"};
  min-width: ${props => props.size || "50px"};
  height: ${props => props.size || "50px"};
  border-radius: ${props =>
    props.size ? parseInt(props.size, 10) / 2 + "px" : "25px"};
  display: flex;
  align-items: center;
  justify-content: center;
  margin: auto;
  margin-top: 0;
`;

const RoundImage = styled.img`
  height: ${props => props.size || "100px"};
  display: flex;
  align-items: center;
  justify-content: center;
  margin: auto;
  margin-top: 0;
`;

const CenteredLink = styled(Link)`
  justify-content: center;
  display: flex;
  flex-direction: column;
  text-align: center;
  padding: 20px;
  pointer-events: ${props => (props.disabled ? "none" : "auto")}
  opacity: ${props => (props.disabled ? 0.5 : 1)}
`;

const StyledH2 = styled.h2`
  && {
    font-size: 32px;
    font-weight: 300;
    text-align: center;
  }
`;

const Center = styled.div`
  margin: auto;
`;

const CenteredText = styled.div`
  text-align: center;
`;

class CreateCommitment extends Component<
  {
    organizations: Array<ORGANIZATION_TYPE>,
    match: *,
    hundredTodos: HundredTodosType,
    getHundredTodos: () => void,
    get100SmartWays: () => void,
    profile: PROFILE_TYPE
  },
  {
    selectedCreator: ?PROFILE_TYPE | ?ORGANIZATION_TYPE,
    commitmentTypes: Array<CATEGORY_TYPE>,
    hundredTodos: HundredTodosType
  }
> {
  state = {
    selectedCreator: null,
    commitmentTypes: [],
    hundredTodos: this.props.hundredTodos
  };

  componentWillReceiveProps(nextProps) {
    if (nextProps.hundredTodos !== this.props.hundredTodos) {
      //Perform some operation
      this.setState({ hundredTodos: nextProps.hundredTodos });
      // this.classMethod();
    }
  }

  componentDidMount = async () => {
    this.props.getHundredTodos();
    this.props.get100SmartWays();

    const commitmentTypes = await LiferayClient(
      "/assetcategory/get-vocabulary-categories",
      {
        vocabularyId: vocCommitmentType,
        start: 0,
        end: 1000,
        obc: null
      }
    );

    const mainTypes = commitmentTypes.filter(t => t.parentCategoryId === "0");
    const orderedMainTypes = [];
    for (let i = 0; i < mainTypes.length; i++) {
      switch (mainTypes[i].name) {
        case "Toimenpidesitoumus": {
          orderedMainTypes[0] = mainTypes[i];
          break;
        }
        case "Green deal": {
          orderedMainTypes[1] = mainTypes[i];
          break;
        }
        case "Ravitsemussitoumus": {
          orderedMainTypes[2] = mainTypes[i];
          break;
        }
        default:
          console.log("Unknown commitment type");
      }
    }

    this.setState({
      commitmentTypes: orderedMainTypes
    });
  };

  render() {
    const creatorOrganizationTypeId = get(
      this.state,
      "selectedCreator.categories.organizationType.categoryId",
      ""
    );

    const creatorOrganizationId = get(
      this.state,
      "selectedCreator.organizationId",
      ""
    );

    return (
      <Fragment>
        <Route
          path="/tee-sitoumus"
          exact
          render={() => (
            <div>
              <StyledH2>
                {translate({
                  textKey: "sit.commitmentCreation.selectParticipationType"
                })}
              </StyledH2>
              <WhiteSpace height="40px" />
              <Center style={{ maxWidth: 480 }}>
                <AutoComplete
                  style={{ maxWidth: "480px" }}
                  keyPath="name"
                  namePath="name"
                  items={[
                    {
                      ...this.props.profile,
                      name: translate({ textKey: "sit.asIndividual" })
                    },
                    ...this.props.organizations
                  ]}
                  openOnClick={true}
                  selectedItem={this.state.selectedCreator}
                  onChange={selectedCreator => {
                    this.setState({ selectedCreator });
                  }}
                  value={this.state.selectedCreator}
                  renderInput={({ getInputProps, toggleMenu, isOpen }) => (
                    <TextInput
                      {...getInputProps({
                        placeholder: translate({
                          textKey: "sit.commitment.joining.choose"
                        }),
                        name: "creatorType",
                        style: { width: "100%" },
                        required: true,
                        renderIcon: () =>
                          isOpen ? (
                            <InputIconContainer
                              key={isOpen}
                              onClick={toggleMenu}
                            >
                              <i className="fas fa-chevron-up" />
                            </InputIconContainer>
                          ) : (
                            <InputIconContainer
                              key={isOpen}
                              onClick={toggleMenu}
                            >
                              <i className="fas fa-chevron-down" />
                            </InputIconContainer>
                          ),
                        label: "",
                        labelStyle: { minWidth: "120px" }
                      })}
                    />
                  )}
                />
              </Center>
              <WhiteSpace height="40px" />
              <CenteredText>
                {translate({ textKey: "sit.commitmentCreation.creationHelp1" })}
                <br />
                {translate({ textKey: "sit.commitmentCreation.creationHelp2" })}
              </CenteredText>
              <WhiteSpace height="40px" />
              <Row justifyContent="center">
                {this.state.commitmentTypes.map(type => (
                  <CenteredLink
                    key={type.categoryId}
                    disabled={
                      !this.state.selectedCreator ||
                      (type.categoryId !== "33553" &&
                        get(this.state.selectedCreator, "contactId") ===
                          this.props.profile.contactId)
                    }
                    to={{
                      pathname: `/tee-sitoumus/create/${type.categoryId}`,
                      search: `creatorOrganizationTypeId=${creatorOrganizationTypeId}&creatorOrganizationId=${creatorOrganizationId}`
                    }}
                  >
                    {type.categoryId === "33555" ? (
                      <RoundImage src="/documents/20143/31403/ravitsemussitoumus-merkki.png" />
                    ) : (
                      <RoundButtonWithIcon size="100px" />
                    )}

                    <WhiteSpace height="10px" />
                    <strong style={{ color: "black" }}>
                      {type.titleCurrentValue}
                    </strong>
                  </CenteredLink>
                ))}
              </Row>
              <InputIconContainer>
                <i icon="fas fa-info-circle" />
              </InputIconContainer>

              <CenteredText>
                {translate({ textKey: "sit.commitmentCreation.creationHelp3" })}
              </CenteredText>
            </div>
          )}
        />

        <Route
          path="/tee-elamantapa-sitoumus"
          render={routeProps => (
            <CommitmentCreationFormContainer
              {...this.state}
              selectedCreator={this.props.profile}
              {...routeProps}
            />
          )}
        />

        <Route
          path={this.props.match.path + "/create/:commitmentType"}
          render={routeProps => {
            return (
              <CommitmentCreationFormContainer
                {...this.state}
                hundredTodos={null}
                {...routeProps}
              />
            );
          }}
        />
      </Fragment>
    );
  }
}

export default withRouter(
  connect(
    (state: STORE_STATE) => ({
      profile: state.user.profile,
      hundredTodos: state.user.hundredTodos,
      organizations: state.organizations.organizations
    }),
    {
      getHundredTodos: UserActions.getHundredTodos,
      get100SmartWays: ArticleActions.get100SmartWays
    }
  )(CreateCommitment)
);
