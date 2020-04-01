// @flow

import type {
  CommitmentType,
  CATEGORY_TYPE,
  ORGANIZATION_TYPE,
  PROFILE_TYPE,
  OperationType,
  SMARTWAY_ARTICLE_TYPE
} from "../../constants/types";

import React, { Fragment } from "react";
import { connect } from "react-redux";
import moment from "moment";
import styled from "styled-components";
import { get } from "lodash";
import { lighten } from "polished";
import { FacebookShareCount } from "react-share";

import getLocalizedString from "../../utils/getLocalizedString";
import { isHundredTodosCommitment } from "../../utils/hundredTodos";
import mapOptionalGoalCategoryIdToPrimaryGoalCategoryId from "../../utils/mapOptionalGoalCategoryIdToPrimaryGoalCategoryId";

import Button from "../reusable/Button";
import NotificationContainer from "../reusable/NotificationContainer";
import Row from "../reusable/Row";
import Column from "../reusable/Column";
import translate from "../reusable/translate";
import WhiteSpace from "../reusable/WhiteSpace";
import HundredTodosContainer from "../profile/createCommitment/HundredTodos/HundredTodosContainer";

import ProfilePic from "../reusable/ProfilePic";
import CommitmentPic from "../reusable/CommitmentPic";
import GoalIcon from "../reusable/GoalIcon";
import AgendaIcon from "../reusable/AgendaIcon";
import CommitmentShares from "../reusable/CommitmentShares";
import SomeShareContainer from "../reusable/SomeShareContainer";

import OperationsContainer from "./OperationsContainer";
import ReportPanel from "./ReportPanel";

const CommitmentContainer = styled.div`
  display: flex;
  flex: 1 1 auto;
  flex-direction: column;
`;

const StyledDate = styled.span`
  font-weight: bold;
  font-size: 14px;
`;

const HorizontalSeparator = styled.div`
  background: #dcdcdc;
  height: 1px;
  margin-top: 20px;
  margin-bottom: 10px;
`;

const BootstrapColumnContainer = styled.div`
  padding-left: 30px;
`;

const CommitmentHeadline = styled.h3`
  font-size: 20px;
  font-weight: bold;
`;

const CommitmentParty = styled.span`
  text-transform: uppercase;
  color: #6b6b6b;
`;

const StyledSubtitle = styled.h4`
  font-size: 14px;
  font-weight: bold;
`;

const CommitmentSummary = styled.i`
  font-size: 18px;
  color: #6b6b6b;
`;

const CommitmentText = styled.span`
  font-size: 14px;
  color: #212121;
`;

const JoinCommitmentButton = styled.div`
  display: inline-flex;
  padding: 8px;
  &:hover {
    background: ${lighten(0.25, "#93be38")};
  }
  &:active {
    background: #93be38;
    color: white;
  }
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  border: 1px solid #eee;
  border-radius: 16px;
  cursor: pointer;
`;

const TreeContainer = styled.div`
  margin-top: 40px;
  width: 320px;
`;

const TreeImgContainer = styled.div`
  img {
    max-height: 200px;
    display: block;
    margin-left: auto;
    margin-right: auto;
  }
`;
const TreeLikesContainer = styled.div`
  padding-top: 10px;
  display: flex;
  justify-content: center;
`;
const TreeTopContainer = styled.div`
  display: flex;
  justify-content: space-between;
  padding-left: 40px;
  font-weight: bold;
`;

const ShareIcon = styled.img`
  height: 18px;
  width: 28px;
  padding-right: 10px;
`;

const SingleCommitment = ({
  renderName,
  commitment,
  mainCategories,
  mainCategoryProperties,
  secondaryCategories,
  secondaryCategoryProperties,
  match,
  selectOperation,
  selectedOperations,
  joinCommitmentMode,
  organizations,
  profile,
  selectCreator,
  selectedCreator,
  addLikeToCommitment,
  commitmentData,
  commitmentType,
  creatorOrganizationId,
  creatorOrganizationTypeId,
  locale,
  hundredSmartWaysArticles
}: {
  match: *,
  renderName: string => string,
  commitment: CommitmentType,
  mainCategories: Array<CATEGORY_TYPE>,
  mainCategoryProperties: Array<CATEGORY_TYPE>,
  secondaryCategories: Array<CATEGORY_TYPE>,
  secondaryCategoryProperties: Array<CATEGORY_TYPE>,
  organizations: Array<ORGANIZATION_TYPE>,
  profile: PROFILE_TYPE,
  selectOperation?: OperationType => void,
  selectedOperations?: Array<OperationType>,
  joinCommitmentMode?: boolean,
  commitmentData?: {
    operations: Array<OperationType>,
    categoryIds: [number] | []
  },
  commitmentType?: number,
  creatorOrganizationId?: number,
  creatorOrganizationTypeId?: number,
  selectCreator?: string => void,
  locale?: string,
  selectedCreator?: ?PROFILE_TYPE | ?ORGANIZATION_TYPE,
  addLikeToCommitment: () => void,
  hundredSmartWaysArticles: Array<SMARTWAY_ARTICLE_TYPE>
}) => {
  if (commitment && commitment["error"] === undefined) {
    const showHundredTodos = isHundredTodosCommitment(commitment);

    const {
      operations = [],
      categoryIds = [],
      createdByUserName = "",
      organizationName = "",
      organizationId = "",
      startDate = "",
      endDate = "",
      joined = 0
    } = commitment;

    const title = getLocalizedString("title", commitment);
    const shortDescription = getLocalizedString("shortDescription", commitment);
    const backgroundInformation = getLocalizedString(
      "backgroundInformation",
      commitment
    );
    const innovation = getLocalizedString("innovation", commitment);
    const formattedStartDate = startDate
      ? moment(startDate)
          .format("DD.MM.YYYY")
          .toString()
      : "";
    const formattedEndDate = endDate
      ? moment(endDate)
          .format("DD.MM.YYYY")
          .toString()
      : "";

    let meterChartTypeList = {};

    const protocolAndHost =
      window.location.protocol + "//" + window.location.host;
    const commitmentShareUrl =
      protocolAndHost +
      "/o/commitment2050-service/share-commitment/" +
      commitment.id;
    return (
      <CommitmentContainer>
        {!commitment.organizationLogo && !commitment.commitmentImageUrl && (
          <WhiteSpace height="80px" />
        )}
        {commitment.organizationLogo && (
          <ProfilePic
            disabled={true}
            profilePic={commitment.organizationLogo}
            onSelect={() => {}}
          />
        )}
        <div className="row">
          <div className="col-md-6">
            <BootstrapColumnContainer>
              <StyledDate>{`${formattedStartDate} - ${formattedEndDate}`}</StyledDate>
              <HorizontalSeparator />

              <CommitmentHeadline>
                {title}
                {/* Musti ja Mirri sitoutuu muovikassien kulutuksen
                                    vähentämiseen */}
              </CommitmentHeadline>

              <WhiteSpace height="6px" />

              <CommitmentParty>
                {organizationName ? (
                  <a
                    href={`/organisaation-sitoumukset/?organizationId=${organizationId}&organization=${encodeURIComponent(
                      organizationName
                    )}`}
                  >
                    {renderName(organizationName)}
                  </a>
                ) : (
                  renderName(createdByUserName)
                )}
              </CommitmentParty>

              <WhiteSpace height="20px" />

              {joined !== 0 && <CommitmentShares joined={joined} />}
              <WhiteSpace height="12px" />

              <StyledSubtitle>
                {translate({ textKey: "sit.commitment.shortDescription" })}
              </StyledSubtitle>

              <WhiteSpace height="8px" />

              <CommitmentSummary
                dangerouslySetInnerHTML={{
                  __html: shortDescription
                }}
              />

              <WhiteSpace height="40px" />

              {!showHundredTodos && (
                <div>
                  <StyledSubtitle>
                    {translate({ textKey: "sit.commitment.backgroundInfo" })}
                  </StyledSubtitle>

                  <WhiteSpace height="8px" />

                  <CommitmentText>
                    <span
                      dangerouslySetInnerHTML={{
                        __html: backgroundInformation
                      }}
                    />
                  </CommitmentText>

                  <WhiteSpace height="40px" />

                  <StyledSubtitle>
                    {translate({ textKey: "sit.commitment.whatNew" })}
                  </StyledSubtitle>

                  <WhiteSpace height="8px" />

                  <CommitmentText>
                    <span
                      dangerouslySetInnerHTML={{
                        __html: innovation
                      }}
                    />
                  </CommitmentText>
                </div>
              )}

              {showHundredTodos && (
                <div>
                  <HundredTodosContainer
                    commitment={commitment}
                    locale={locale}
                    disableEdit={true}
                    hundredSmartWaysArticles={hundredSmartWaysArticles}
                  />
                </div>
              )}
            </BootstrapColumnContainer>
          </div>
          <div className="col-md-6">
            <BootstrapColumnContainer>
              {commitment.commitmentImageUrl && (
                <CommitmentPic
                  commitmentPic={commitment.commitmentImageUrl}
                  disabled={true}
                  onSelect={() => {}}
                />
              )}
              <StyledSubtitle>
                {translate({ textKey: "sit.commitment.commitmentGoals" })}
              </StyledSubtitle>

              <WhiteSpace height="8px" />

              <Row justifyContent="flex-start" flexWrap="wrap">
                {categoryIds.map((categoryId, index) => {
                  let key = null,
                    icon = "",
                    title = "";
                  for (let mainCategory of mainCategories) {
                    const mappedCategoryId = mapOptionalGoalCategoryIdToPrimaryGoalCategoryId(
                      categoryId
                    ).toString();
                    if (mappedCategoryId === mainCategory.categoryId) {
                      if (
                        get(mainCategoryProperties, [
                          mappedCategoryId,
                          0,
                          "value"
                        ]) !== ""
                      ) {
                        icon = get(mainCategoryProperties, [
                          mappedCategoryId,
                          0,
                          "value"
                        ]);
                        key = mappedCategoryId;
                        title = mainCategory.titleCurrentValue;
                      }
                      break;
                    }
                  }

                  return key !== null ? (
                    <GoalIcon
                      key={key + String(index)}
                      icon={icon}
                      alt={title}
                      title={title}
                      style={{
                        border:
                          key === categoryIds[0].toString() ? "solid" : "dashed"
                      }}
                    />
                  ) : null;
                })}
              </Row>

              <WhiteSpace height="40px" />

              <StyledSubtitle>
                {translate({
                  textKey: "sit.commitment.commitmentSupportsAgenda2030"
                })}
              </StyledSubtitle>

              <CommitmentText>
                {translate({
                  textKey:
                    "sit.commitment.commitmentSupportsFollowingGlobalGoals"
                })}
              </CommitmentText>

              <WhiteSpace height="10px" />

              <Row justifyContent="flex-start" flexWrap="wrap">
                {categoryIds &&
                  categoryIds.map((categoryId, index) => {
                    let key = null,
                      icon = "",
                      title = "";
                    for (let secondaryCategory of secondaryCategories) {
                      if (
                        Number(categoryId) ===
                        Number(secondaryCategory.categoryId)
                      ) {
                        if (
                          get(secondaryCategoryProperties, [
                            categoryId,
                            0,
                            "value"
                          ]) !== ""
                        ) {
                          icon = get(secondaryCategoryProperties, [
                            categoryId,
                            0,
                            "value"
                          ]);
                          key = categoryId;
                          title = secondaryCategory.titleCurrentValue;
                        }
                        break;
                      }
                    }

                    return key !== null ? (
                      <AgendaIcon
                        key={key}
                        /*icon={
                          locale && locale !== "fi_FI"
                            ? `${icon}_${locale}`
                            : icon
                        }*/
                        icon={icon}
                        alt={title}
                        title={title}
                      />
                    ) : null;
                  })}
              </Row>

              <WhiteSpace height="40px" />

              <hr />

              <SomeShareContainer
                facebook={{
                  url: commitmentShareUrl,
                  quote: "Sitoumus2050 - kestävät elämäntavat"
                }}
                twitter={{
                  url: commitmentShareUrl,
                  title:
                    "Tein suunnitelman ilmastonmuutoksen ratkaisemiseksi. Millaisen sinä teet?"
                }}
                linkedin={{
                  url: commitmentShareUrl
                }}
              />

              <hr />

              <div>
                <Column>
                  <TreeContainer>
                    <TreeTopContainer>
                      <FacebookShareCount
                        url={encodeURIComponent(window.location.href)}
                      >
                        {shareCount => (
                          <div>
                            <ShareIcon
                              alt=""
                              src="/documents/20143/31403/shares.png"
                            />
                            {shareCount}
                          </div>
                        )}
                      </FacebookShareCount>
                      <CommitmentShares joined={joined} />
                    </TreeTopContainer>
                    <TreeImgContainer>
                      <img alt="" src="/documents/20143/31403/puu.png" />
                    </TreeImgContainer>
                    <TreeLikesContainer>
                      <JoinCommitmentButton onClick={addLikeToCommitment}>
                        <i className="fas fa-heart" />
                        <WhiteSpace width="10px" />
                        <strong>{commitment.likes}</strong>
                      </JoinCommitmentButton>
                    </TreeLikesContainer>
                  </TreeContainer>
                  <WhiteSpace height="50px" />

                  {!selectedOperations ||
                    (selectedOperations.length === 0 && (
                      <Fragment>
                        <NotificationContainer
                          notificationType={{ state: "SUCCESS", message: "" }}
                        >
                          {translate({
                            textKey:
                              "sit.commitment.joining.selectOperationsToCopy"
                          })}
                        </NotificationContainer>
                      </Fragment>
                    ))}

                  {selectedOperations && (
                    <Fragment>
                      <StyledSubtitle>
                        {translate({
                          textKey: "sit.commitment.joining.copiedOperations"
                        })}
                      </StyledSubtitle>
                      {selectedOperations.length > 0 ? (
                        selectedOperations.map((op, index) => (
                          <StyledSubtitle key={op.operationId + String(index)}>
                            {index + 1}
                            {"."} {getLocalizedString("operationTitle", op)}
                          </StyledSubtitle>
                        ))
                      ) : (
                        <StyledSubtitle>
                          {translate({
                            textKey:
                              "sit.commitmentCreation.noSelectedOperations"
                          })}
                        </StyledSubtitle>
                      )}
                      <WhiteSpace height="20px" />
                      {translate({
                        textKey: "sit.commitment.joining.joinAsUserOrganization"
                      })}
                      <WhiteSpace height="10px" />
                      <select
                        style={{ height: 48 }}
                        onChange={e => {
                          const { value } = e.target;
                          selectCreator && selectCreator(value);
                        }}
                      >
                        <option value="">
                          {translate({
                            textKey: "sit.commitment.joining.choose"
                          })}
                        </option>
                        {commitment.commitmentType === "COMMITMENT" && (
                          <option value={JSON.stringify(profile)}>
                            {translate({
                              textKey: "sit.profile.individual"
                            })}
                          </option>
                        )}
                        {organizations &&
                          organizations.map(org => (
                            <option
                              key={org.organizationId}
                              value={JSON.stringify(org)}
                            >
                              {org.name}
                            </option>
                          ))}
                      </select>
                      <WhiteSpace height="20px" />
                      <Button
                        disabled={!selectedCreator}
                        onClick={() => {
                          window.location = `
                          /profiili#/tee-sitoumus/create/${commitmentType ||
                            ""}?creatorOrganizationTypeId=${creatorOrganizationTypeId ||
                            ""}&creatorOrganizationId=${creatorOrganizationId ||
                            ""}&commitmentData=${encodeURIComponent(
                            JSON.stringify(commitmentData)
                          )}&joinCommitmentId=${commitment.id}
                      `;
                        }}
                        style={{
                          pointerEvents: selectedCreator ? "auto" : "none",
                          height: 36,
                          minWidth: 180,
                          alignSelf: "flex-end"
                        }}
                      >
                        {translate({
                          textKey: "sit.commitment.joining.join"
                        })}
                      </Button>
                    </Fragment>
                  )}
                </Column>
              </div>
            </BootstrapColumnContainer>
          </div>
        </div>
        <BootstrapColumnContainer>
          {!showHundredTodos && (
            <OperationsContainer
              operations={operations}
              selectOperation={selectOperation}
              selectedOperations={selectedOperations}
              joinCommitmentMode={joinCommitmentMode}
              meterChartTypeList={meterChartTypeList}
              commitment={commitment}
            />
          )}

          {commitment.genericReports && (
            <div>
              <StyledSubtitle>
                {translate({
                  textKey: "sit.commitment.reports.genericReports"
                })}
              </StyledSubtitle>
              <WhiteSpace height="10px" />
              <div>
                {commitment.genericReports &&
                  commitment.genericReports
                    .slice(0)
                    .reverse()
                    .map((report, index) => (
                      <Fragment key={index}>
                        <ReportPanel
                          report={report}
                          expanded={index === 0}
                          meterChartTypeList={meterChartTypeList}
                          commitment={commitment}
                        />
                        <WhiteSpace height="10px" />
                      </Fragment>
                    ))}
              </div>
            </div>
          )}
        </BootstrapColumnContainer>
      </CommitmentContainer>
    );
  } else return null;
};

SingleCommitment.defaultProps = {
  joinCommitmentMode: false
};

export default connect(state => ({
  organizations: state.organizations.organizations,
  profile: state.user.profile,
  locale: state.user.locale,
  hundredSmartWaysArticles: state.articles.hundredSmartWaysArticles.sort(
    (a, b) => {
      const titleA = a.title && a.title.fi_FI ? a.title.fi_FI : "";
      const titleB = b.title && b.title.fi_FI ? b.title.fi_FI : "";
      return titleA.localeCompare(titleB, "fi");
    }
  )
}))(SingleCommitment);
