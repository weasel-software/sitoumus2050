// @flow

import type {
  CommitmentType,
  ARTICLE_TYPE,
  CATEGORY_TYPE
} from "../../constants/types";

import React, { Fragment, Component } from "react";
import { Link, withRouter } from "react-router-dom";
import { connect } from "react-redux";
import styled from "styled-components";
import { get } from "lodash";

import { ArticleActions } from "../../redux/articles";

import generateGraphHistory from "../../utils/generateGraphHistory";

import GoalIcon from "../reusable/GoalIcon";
import WhiteSpace from "../reusable/WhiteSpace";
import CommitmentShares from "../reusable/CommitmentShares";
import translate from "../reusable/translate";

import ReportPanel from "../commitment/ReportPanel";

import {
  JustifyLeft,
  JustifyRight,
  CommitmentName,
  CommitmentParty,
  Column,
  RoundIcon
} from "./styles";

const StyledLink = styled(Link)`
  color: #333333;
`;

const StyledCommitmentWithReportRow = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #dcdcdc;
  max-width: 960px;
  align-self: center;
  padding: 10px 10px 10px 20px;
  cursor: pointer;
  width: 100%;
  &:hover {
    background: #93be38;
    border-bottom: 1px solid #93be38;
    box-shadow: 0 3px 6px rgba(0, 0, 0, 0.4);
    color: white;
    border-radius: 6px;
    ${CommitmentName} {
      color: white;
    }
    ${CommitmentParty} {
      color: white;
    }
    ${StyledLink} {
      color: white;
    }
    ${GoalIcon} {
      color: white;
      border-color: #eee;
      img:not(.organization-logo) {
        filter: grayscale() brightness(1000);
      }
    }
  }
`;

class CommitmentWithReportRow extends Component<
  {
    article: ARTICLE_TYPE,
    id: string,
    to: string,
    getArticleContent?: string => void,
    mainCategoryProperties: { [number]: Array<CATEGORY_TYPE> }
  },
  {
    expanded: boolean,
    articleContent: CommitmentType | null
  }
> {
  state = {
    expanded: false,
    articleContent: null
  };

  getIconLink = (article, properties = this.props.mainCategoryProperties) => {
    if (!article || !properties) return "";

    const categoryId = get(article, "assetCategoryIds[0][0]");
    if (!categoryId) return "";

    const categoryProps = properties[categoryId];
    if (categoryProps && categoryProps.length > 0) {
      return categoryProps[0].value;
    } else return "";
  };

  componentWillReceiveProps(nextProps) {
    if (!this.state.articleContent && this.state.expanded) {
      // $FlowFixMe
      this.setState({ articleContent: nextProps.articleContent });
    }
  }
  render() {
    let properReports = false;
    return (
      <Fragment>
        <StyledCommitmentWithReportRow
          onClick={() => {
            if (!this.state.articleContent && this.props.getArticleContent) {
              this.props.getArticleContent(this.props.article.articleId);
            }

            this.setState({ expanded: !this.state.expanded });
          }}
        >
          <JustifyLeft>
            {this.props.article.organizationLogo ? (
              <RoundIcon>
                <img
                  className="organization-logo"
                  src={this.props.article.organizationLogo}
                  style={{
                    width: 80,
                    height: 80,
                    borderRadius: 40
                  }}
                  alt="logo"
                />
              </RoundIcon>
            ) : (
              <RoundIcon style={{ height: 80 }}>
                <i
                  className="fas fa-user"
                  style={{
                    color: "#AEAEAE",
                    fontSize: 24
                  }}
                />
              </RoundIcon>
            )}

            <Column style={{ width: "100%" }}>
              <CommitmentName>{this.props.article.title}</CommitmentName>

              <CommitmentParty>
                {this.props.article.organizationName}
                {/* {this.renderName(article.userName)} */}
              </CommitmentParty>
            </Column>
          </JustifyLeft>

          <JustifyRight>
            <StyledLink to={this.props.to} style={{ marginRight: "20px" }}>
              {translate({
                textKey: "sit.commitment.reportList.openCommitment"
              })}
            </StyledLink>
            <CommitmentShares joined={Number(this.props.article.priority)} />

            <GoalIcon
              style={{ marginLeft: "16px" }}
              icon={this.getIconLink(this.props.article)}
            />
          </JustifyRight>
        </StyledCommitmentWithReportRow>
        <Fragment>
          {this.state.expanded &&
            this.state.articleContent &&
            this.state.articleContent.operations &&
            this.state.articleContent.operations.map((op, index) => {
              let meterChartTypeList = {};

              if (op.meters) {
                op.meters.forEach((meter, index) => {
                  if (meter.meterChartType && meter.meterId) {
                    meterChartTypeList = {
                      ...meterChartTypeList,
                      [meter.meterId]: meter.meterChartType
                    };
                  }
                });
              }

              if (op.reports) {
                const graphHistory = generateGraphHistory(op);
                const newestReportIndex = op.reports
                  ? op.reports.length - 1
                  : 0;
                properReports = true;
                return (
                  <Fragment key={`ReportRow_${op.operationId}`}>
                    {op.reports && (
                      <ReportPanel
                        id={op.reports[newestReportIndex].id}
                        meterChartTypeList={meterChartTypeList}
                        commitmentStartDate={
                          (this.state.articleContent &&
                            this.state.articleContent.startDate) ||
                          ""
                        }
                        commitmentEndDate={
                          (this.state.articleContent &&
                            this.state.articleContent.endDate) ||
                          ""
                        }
                        commitment={this.state.articleContent}
                        expanded={index === 0}
                        report={op.reports[newestReportIndex]}
                        graphHistory={graphHistory}
                        reportIndex={0}
                      />
                    )}
                    <WhiteSpace height="10px" />
                  </Fragment>
                );
              } else {
                return null;
              }
            })}
        </Fragment>
        <Fragment>
          {this.state.expanded &&
            this.state.articleContent &&
            this.state.articleContent.genericReports &&
            this.state.articleContent.genericReports.map(
              (genericReport, index) => (
                <Fragment key={genericReport.id}>
                  <ReportPanel
                    id={genericReport.id}
                    commitmentStartDate={
                      (this.state.articleContent &&
                        this.state.articleContent.startDate) ||
                      ""
                    }
                    commitmentEndDate={
                      (this.state.articleContent &&
                        this.state.articleContent.endDate) ||
                      ""
                    }
                    commitment={this.state.articleContent}
                    expanded={
                      properReports === false && index === 0 ? true : false
                    }
                    report={genericReport}
                  />
                  <WhiteSpace height="10px" />
                </Fragment>
              )
            )}
        </Fragment>
      </Fragment>
    );
  }
}

export default withRouter(
  connect(
    state => ({
      articleContent: state.articles.articleContent
    }),
    {
      getArticleContent: ArticleActions.getArticleContent
    }
  )(CommitmentWithReportRow)
);
