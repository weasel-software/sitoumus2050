// @flow

import type { STORE_STATE } from "../../redux/store";
import type {
  CommitmentType,
  CATEGORY_TYPE,
  ErrorType,
  PROFILE_TYPE
} from "../../constants/types";

import { get } from "lodash";
import React, { Component, Fragment } from "react";
import { withRouter, Route, Link } from "react-router-dom";
import styled from "styled-components";

import { connect } from "react-redux";
import { ArticleActions } from "../../redux/articles";

import { SecondaryButton } from "../reusable/Button";
import Button from "../reusable/Button";
import translate from "../reusable/translate";
import ScrollTo from "../reusable/ScrollTo";
import Row from "../reusable/Row";
import Column from "../reusable/Column";
import WhiteSpace from "../reusable/WhiteSpace";
import LiferayLink from "../reusable/LiferayLink";

import SingleCommitment from "./SingleCommitment";
import JoinedList from "./JoinedList";
import JoinCommitment from "./JoinCommitment";
import { isHundredTodosCommitment } from "../../utils/hundredTodos";

const StyledTitle = styled.h3`
  font-size: 16px;
  font-weight: bold;
  text-transform: uppercase;
  margin-top: 0;
`;

const VerticalSeparatorLine = styled.span`
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0 10px 0 10px;
`;

const VerticalSeparator = () => (
  <VerticalSeparatorLine>|</VerticalSeparatorLine>
);

const LanguageContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  flex: 1;
`;

const JoinCommitmentContainer = styled.div`
  flex: 1;
  display: flex;
  justify-content: ${props => props.justifyContent || "flex-end"};
  margin-left: ${props => props.marginLeft || 0};
  margin-right: ${props => props.marginRight || 0};
`;

const SpaceBetween = styled.div`
  display: flex;
  justify-content: space-between;
  flex-direction: row;
  border-bottom: 1px solid #dcdcdc;
  padding: 10px;
`;

const RoundButtonWithIcon = styled(Button)`
  width: 50px;
  max-width: 50px;
  min-width: 50px;
  height: 50px;
  border-radius: 25px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 0;
`;

const ErrorContainer = styled.div`
  background: red;
  padding: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 500px;
  flex-direction: column;
`;

const ErrorText = styled.h2`
  && {
    color: white;
  }

  font-weight: bold;
  && {
    font-size: 36px;
  }
`;

class Commitment extends Component<
  {
    match: *,
    renderName: string => string,
    articleContent: CommitmentType,
    getArticleContent: number => void,
    getJoinedToArticle: number => void,
    joinedToArticle: Array<CommitmentType>,
    mainCategories: Array<CATEGORY_TYPE>,
    mainCategoryProperties: Array<CATEGORY_TYPE>,
    secondaryCategories: Array<CATEGORY_TYPE>,
    secondaryCategoryProperties: Array<CATEGORY_TYPE>,
    addLikeToCommitment: string => void,
    profile: PROFILE_TYPE,
    history: any
  },
  { error: ?ErrorType }
> {
  state = {
    error: null
  };

  topEl = null;

  componentDidMount() {
    this.props.getArticleContent(this.props.match.params.articleId);
    this.props.getJoinedToArticle(this.props.match.params.articleId);
  }
  componentWillReceiveProps(nextProps) {
    if (
      nextProps.match.params.articleId !== this.props.match.params.articleId
    ) {
      this.props.getArticleContent(nextProps.match.params.articleId);
      this.props.getJoinedToArticle(nextProps.match.params.articleId);
      window.scrollTo(0, 0);
    }
  }

  addLikeToCommitment = () => {
    this.props.addLikeToCommitment(this.props.articleContent.id);
  };

  renderName = name => {
    if (!name) return "";
    if (name.endsWith("puuttuva tieto")) return "";
    const s = name.split(" ");
    if (s[0] === s[1]) return s[0];
    else return name;
  };

  render() {
    const {
      articleContent,
      mainCategories,
      mainCategoryProperties,
      secondaryCategories,
      secondaryCategoryProperties
    } = this.props;
    return (
      <div className="commitment-container" ref={topEl => (this.topEl = topEl)}>
        {this.topEl && (
          <Fragment>
            <ScrollTo element={this.topEl} />
            <Route
              path={this.props.match.path}
              exact
              render={() => {
                if (!articleContent) return null;
                if (articleContent["error"] !== undefined) {
                  return (
                    <div>
                      <SecondaryButton
                        id="commitment_details_link_to_list"
                        onClick={this.props.history.goBack()}
                      >
                        <i
                          className="fa fa-chevron-left"
                          style={{ marginRight: "6px" }}
                        />
                        {translate({ textKey: "sit.back" })}
                      </SecondaryButton>
                      <ErrorContainer>
                        <ErrorText>
                          {translate({ textKey: "sit.error.error" })}
                        </ErrorText>

                        <ErrorText>
                          {translate({ textKey: "sit.error.articleId" })}
                          {this.props.match.params.articleId}
                        </ErrorText>
                      </ErrorContainer>
                    </div>
                  );
                }

                if (articleContent["error"] === undefined) {
                  return (
                    <div className="container">
                      <div>
                        <div className="col-md-12">
                          <SpaceBetween>
                            <Link
                              to="/#list"
                              id="commitment_details_link_to_list"
                            >
                              <SecondaryButton>
                                <i
                                  className="fa fa-chevron-left"
                                  style={{ marginRight: "6px" }}
                                />
                                {translate({ textKey: "sit.back" })}
                              </SecondaryButton>
                            </Link>

                            <StyledTitle>
                              {translate({
                                textKey: "sit.commitment.actionableCommitment"
                              })}
                            </StyledTitle>
                          </SpaceBetween>
                        </div>
                      </div>
                      <div className="row">
                        <div className="col-md-12">
                          <Row
                            alignItems="flex-start"
                            style={{ marginTop: "16px" }}
                          >
                            <JoinCommitmentContainer
                              justifyContent="flex-start"
                              marginRight="auto"
                            />
                            <LanguageContainer>
                              <Row
                                justifyContent="center"
                                alignItems="flex-start"
                              >
                                <LiferayLink
                                  id="commitment_details_change_language_fi_FI"
                                  lang="fi_FI"
                                  href={`${window.Liferay.currentURL}#${
                                    this.props.match.url
                                  }`}
                                >
                                  SUOMI
                                </LiferayLink>
                                <VerticalSeparator />
                                <LiferayLink
                                  id="commitment_details_change_language_sv_SE"
                                  lang="sv_SE"
                                  href={`${window.Liferay.currentURL}#${
                                    this.props.match.url
                                  }`}
                                >
                                  SVENSKA
                                </LiferayLink>
                                <VerticalSeparator />
                                <LiferayLink
                                  id="commitment_details_change_language_en_US"
                                  lang="en_US"
                                  href={`${window.Liferay.currentURL}#${
                                    this.props.match.url
                                  }`}
                                >
                                  ENGLISH
                                </LiferayLink>
                              </Row>
                            </LanguageContainer>
                            {isHundredTodosCommitment(articleContent) ? (
                              <JoinCommitmentContainer marginLeft="auto" />
                            ) : (
                              <JoinCommitmentContainer marginLeft="auto">
                                <Column>
                                  <Link
                                    style={{
                                      opacity: this.props.profile ? 1 : 0.85,
                                      pointerEvents: this.props.profile
                                        ? "auto"
                                        : "none"
                                    }}
                                    to={{
                                      pathname: this.props.match.url + "/join",
                                      state: {
                                        commitment: articleContent
                                      }
                                    }}
                                  >
                                    <Row
                                      alignItems="center"
                                      justifyContent="flex-end"
                                    >
                                      <StyledTitle style={{ fontSize: "14px" }}>
                                        {translate({
                                          textKey:
                                            "sit.commitment.joinCommitment"
                                        })}
                                      </StyledTitle>
                                      <RoundButtonWithIcon>
                                        <i className="fa fa-plus" />
                                      </RoundButtonWithIcon>
                                    </Row>
                                  </Link>

                                  <Row>
                                    {!this.props.profile && (
                                      <span
                                        style={{
                                          color: "#777",
                                          fontSize: 13,
                                          textAlign: "center",
                                          lineHeight: "18px"
                                        }}
                                      >
                                        {translate({
                                          textKey:
                                            "sit.commitment.joiningRestricted"
                                        })}
                                      </span>
                                    )}
                                  </Row>
                                </Column>
                              </JoinCommitmentContainer>
                            )}
                          </Row>
                        </div>
                      </div>
                      <SingleCommitment
                        commitment={articleContent}
                        mainCategories={mainCategories}
                        mainCategoryProperties={mainCategoryProperties}
                        secondaryCategories={secondaryCategories}
                        secondaryCategoryProperties={
                          secondaryCategoryProperties
                        }
                        match={this.props.match}
                        renderName={this.props.renderName}
                        addLikeToCommitment={this.addLikeToCommitment}
                      />

                      <WhiteSpace height="48px" />
                      {this.props.joinedToArticle &&
                        this.props.joinedToArticle.length > 0 && (
                          <JoinedList
                            currentArticleId={this.props.match.params.articleId}
                            articles={this.props.joinedToArticle}
                            mainCategoryProperties={
                              this.props.mainCategoryProperties
                            }
                            renderName={this.renderName}
                            match={this.props.match}
                          />
                        )}
                    </div>
                  );
                }
              }}
            />

            <Route
              path={this.props.match.url + "/join"}
              exact
              render={routeProps => {
                return (
                  <div className="container">
                    <JoinCommitment
                      commitment={articleContent}
                      mainCategories={mainCategories}
                      mainCategoryProperties={mainCategoryProperties}
                      secondaryCategories={secondaryCategories}
                      secondaryCategoryProperties={secondaryCategoryProperties}
                      match={this.props.match}
                      renderName={this.props.renderName}
                      addLikeToCommitment={this.addLikeToCommitment}
                    />
                  </div>
                );
              }}
            />
          </Fragment>
        )}
      </div>
    );
  }
}

export default withRouter(
  connect(
    (state: STORE_STATE) => ({
      articleContent: state.articles.articleContent,
      mainCategories: state.articles.mainCategories,
      joinedToArticle: state.articles.joinedToArticle,
      mainCategoryProperties: state.articles.mainCategoryProperties,
      secondaryCategories: state.articles.secondaryCategories,
      secondaryCategoryProperties: state.articles.secondaryCategoryProperties,
      profile: get(state.user, "profile")
    }),
    {
      getArticleContent: ArticleActions.getArticleContent,
      getJoinedToArticle: ArticleActions.getJoinedToArticle,
      addLikeToCommitment: ArticleActions.addLikeToCommitment
    }
  )(Commitment)
);
