// @flow

import type { STORE_STATE } from "../../redux/store";
import type { CommitmentType, ORGANIZATION_TYPE } from "../../constants/types";

import React, { Fragment, Component } from "react";
import { connect } from "react-redux";
import { Link, withRouter } from "react-router-dom";
import styled from "styled-components";
import { get } from "lodash";

import getLocalizedString from "../../utils/getLocalizedString";
import { ArticleActions } from "../../redux/articles";

import Button, { SecondaryButton } from "../reusable/Button";
import WhiteSpace from "../reusable/WhiteSpace";
import Row from "../reusable/Row";
import translate from "../reusable/translate";

const CommitmentContainer = styled.div`
  display: flex;
  flex: 1;
  flex-direction: column;
  margin-left: 40px;
`;

const SeparatorLine = styled.div`
  background: #dcdcdc;
  height: 1px;
  width: 100%;
  margin: 20px 0px 20px 0px;
`;

const IconWithText = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 8px;
  > svg {
    margin: 6px;
  }
  > strong {
    margin: 6px;
  }
`;

const IconWrapper = styled.div`
  display: flex;
`;

const TitleH3 = styled.h3`
  font-size: 30px;
  font-weight: bold;
  text-transform: uppercase;
`;

const AlignEnd = styled.div`
  display: flex;
  justify-content: flex-end;
`;

const AlignStart = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
`;

const SpaceAround = styled.div`
  display: flex;
  justify-content: space-around;
  align-items: center;
`;

const TitleH4 = styled.h4`
  font-size: 20px;
  font-weight: bold;
  text-transform: uppercase;
`;

type GROUPED_ARTICLES = {
  ["approved" | "draft"]: Array<CommitmentType>
};

const Column = styled.div`
  display: flex;
  flex-direction: column;
`;

const groupByStatus = (
  commitments: Array<CommitmentType>
): ?GROUPED_ARTICLES => {
  if (!commitments) return null;
  return commitments.reduce((acc, value) => {
    const { status } = value;

    if (acc[status]) {
      acc[status].push(value);
    } else {
      acc[status] = [value];
    }
    return acc;
  }, {});
};

class MyCommitments extends Component<
  {
    TabContainer: any,
    userCommitments: Array<CommitmentType>,
    match: any,
    commitmentsForOrganizations: {
      [organizationName: string]: Array<CommitmentType>
    },
    organizations: Array<ORGANIZATION_TYPE>,
    getCommitmentsForOrganizations: (Array<string>) => void,
    getCommitmentsForOrganizationById: (Array<string>) => void,
    getUserCommitments: string => void,
    userId: string,
    locale: string
  },
  any
> {
  componentDidMount() {
    if (this.props.userId) {
      this.props.getUserCommitments(this.props.userId);
      this.props.getCommitmentsForOrganizationById(
        this.props.organizations.map(org => org.organizationId)
      );
    }
  }

  getExistingLocalizedTitle = commitment => {
    let title = getLocalizedString("title", commitment);
    let explanation = "";
    if (!title) {
      if (commitment.title_fi_FI !== "Sitoumus") {
        explanation = `${translate({
          textKey: "sit.myCommitments.titleNotFound"
        })} 
        ${translate({ textKey: "sit.myCommitments.titleInFinnish" })}: `;
        title = commitment.title_fi_FI;
      } else {
        explanation = `${translate({
          textKey: "sit.myCommitments.addTitleSuggestion"
        })}`;
      }
    } else if (title === "Sitoumus" && this.props.locale === "fi_FI") {
      if (commitment.title_en_US) {
        explanation = `${translate({
          textKey: "sit.myCommitments.titleNotFound"
        })}
        ${translate({ textKey: "sit.myCommitments.titleInEnglish" })}: `;
        title = commitment.title_en_US;
      } else {
        explanation = `${translate({
          textKey: "sit.myCommitments.addTitleSuggestion"
        })}`;
        title = "";
      }
    }

    return { explanation, title };
  };

  renderCommitment = (commitment, organizationId) => {
    const explainedTitle =
      commitment && this.getExistingLocalizedTitle(commitment);
    return (
      <Fragment key={commitment.id}>
        <hr />
        <CommitmentContainer>
          <Row alignItems="center">
            <AlignStart>
              {commitment.commitmentImageUrl && (
                <img
                  alt="Sitoumuksen logo"
                  src={commitment.commitmentImageUrl || ""}
                  style={{ width: 160, height: 160, borderRadius: 80 }}
                />
              )}
              <WhiteSpace width="30px" />
              <p>
                {explainedTitle.explanation}
                <strong>{explainedTitle.title}</strong>
              </p>
            </AlignStart>

            <Column>
              <Row>
                <span>{commitment.userName}</span>

                <AlignEnd>
                  <Link
                    to={{
                      pathname: "/muokkaa-sitoumusta",
                      state: {
                        commitment
                      },
                      search: `?commitmentId=${
                        commitment.id
                      }&creatorOrganizationId=${
                        organizationId ? organizationId : ""
                      }`
                    }}
                  >
                    <SecondaryButton
                      style={{
                        height: "36px",
                        minWidth: 180
                      }}
                    >
                      {translate({ textKey: "sit.edit" })}
                    </SecondaryButton>
                  </Link>

                  <WhiteSpace width="20px" />

                  <Link
                    to={{
                      pathname: "/raportoi",
                      state: {
                        commitment
                      },
                      search: `?commitmentId=${commitment.id}`
                    }}
                  >
                    <Button
                      style={{
                        height: "36px",
                        minWidth: 180
                      }}
                    >
                      {translate({ textKey: "sit.report" })}
                    </Button>
                  </Link>
                </AlignEnd>
              </Row>
              <SpaceAround>
                <Link
                  to={{
                    pathname: "/muokkaa-raporttia",
                    state: {
                      commitment
                    },
                    search: `?commitmentId=${commitment.id}&editing=true`
                  }}
                  style={{ display: "flex" }}
                >
                  {translate({ textKey: "sit.profile.editReports" })}
                </Link>
                <IconWrapper>
                  <IconWithText>
                    <i className="fas fa-share-alt" />
                    <strong>{commitment.joined}</strong>
                  </IconWithText>

                  <IconWithText>
                    <i className="fas fa-heart" />
                    <strong>{commitment.likes}</strong>
                  </IconWithText>
                </IconWrapper>
              </SpaceAround>
            </Column>
          </Row>
        </CommitmentContainer>
      </Fragment>
    );
  };

  render() {
    const {
      TabContainer,
      userCommitments,
      commitmentsForOrganizations
    } = this.props;
    const groupedUserCommitments = groupByStatus(userCommitments);
    return (
      <Fragment>
        <TabContainer>
          <TitleH3>
            {translate({ textKey: "sit.profile.personalCommitments" })}
          </TitleH3>
          <WhiteSpace height="30px" />
          {groupedUserCommitments &&
            Object.keys(groupedUserCommitments).map(status => (
              <Fragment key={"status_" + status}>
                {status === "approved" && (
                  <TitleH4>
                    {translate({ textKey: "sit.commitment.valids" })}
                  </TitleH4>
                )}
                {status === "draft" && (
                  <TitleH4>
                    {translate({ textKey: "sit.commitment.drafts" })}
                  </TitleH4>
                )}

                {groupedUserCommitments[status].map(this.renderCommitment)}
              </Fragment>
            ))}
          <WhiteSpace height="30px" />
          <TitleH3>
            {translate({ textKey: "sit.profile.organizationCommitments" })}
          </TitleH3>
          <WhiteSpace height="30px" />
          <SeparatorLine />
          {commitmentsForOrganizations &&
            Object.keys(commitmentsForOrganizations).map(key => {
              const grouped =
                key && groupByStatus(commitmentsForOrganizations[key]);
              return (
                grouped &&
                Object.keys(grouped).length > 0 && (
                  <div
                    key={key}
                    style={{
                      border: "2px solid #93be38",
                      margin: 10,
                      marginTop: 20
                    }}
                  >
                    <Row
                      style={{
                        padding: 20,
                        background: "#eee"
                      }}
                    >
                      <h2 style={{ margin: 0 }}>
                        {this.props.organizations[0].name}
                      </h2>
                    </Row>

                    {Object.keys(grouped).map(status => (
                      <div
                        key={"status_" + status}
                        style={{ marginLeft: "12px", padding: 10 }}
                      >
                        {status === "approved" && (
                          <TitleH4>
                            {translate({
                              textKey: "sit.profile.validCommitments"
                            })}
                          </TitleH4>
                        )}
                        {status === "draft" && (
                          <TitleH4>
                            {translate({
                              textKey: "sit.profile.draftCommitments"
                            })}
                          </TitleH4>
                        )}

                        {grouped[status].map(commitment => {
                          const org = this.props.organizations.find(
                            o => <o className="organizationId"></o> === key
                          );
                          return this.renderCommitment(
                            commitment,
                            org && org.organizationId
                          );
                        })}
                      </div>
                    ))}
                  </div>
                )
              );
            })}
        </TabContainer>
      </Fragment>
    );
  }
}

export default withRouter(
  connect(
    (state: STORE_STATE) => ({
      userCommitments: state.articles.userCommitments,
      commitmentsForOrganizations: state.articles.commitmentsForOrganizations,
      organizations: state.organizations.organizations,
      userId: get(state.user, "profile.userId"),
      locale: state.user.locale
    }),
    {
      getCommitmentsForOrganizationById:
        ArticleActions.getCommitmentsForOrganizationById,
      getUserCommitments: ArticleActions.getUserCommitments
    }
  )(MyCommitments)
);
