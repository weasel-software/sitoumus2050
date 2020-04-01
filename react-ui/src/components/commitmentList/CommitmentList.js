// @flow

import type {ARTICLE_TYPE, CATEGORY_TYPE} from "../../constants/types";

import React, {Fragment} from "react";
import {withRouter} from "react-router-dom";
import styled from "styled-components";
import {connect} from "react-redux";

import {SecondaryButton} from "../reusable/Button";
import WhiteSpace from "../reusable/WhiteSpace";
import translate from "../reusable/translate";

import CommitmentLinkRow from "./CommitmentLinkRow";
import CommitmentStats from "./CommitmentStats";
import CommitmentWithReportRow from "./CommitmentWithReportRow";
import ClusterMap from "../reusable/ClusterMap";

const Center = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

const CommitmentList = ({
        articles,
        getMoreArticles,
        mainCategoryProperties,
        renderName,
        match,
        pageType,
        reportListing,
        stats,
        pageTypeName
    }: {
    articles: Array<ARTICLE_TYPE>,
    getMoreArticles?: () => void,
    mainCategoryProperties: { [number]: Array<CATEGORY_TYPE> },
    renderName: string => string,
    match: any,
    pageType: string,
    expanded?: boolean,
    reportListing?: boolean,
    stats?: Array<Number>,
    pageTypeName?: string
}) => (
    <div className="container">
        {!reportListing ? (
        <Fragment>
            <div className="commitmentLinkRow">
                {articles && articles.length > 0 ? (
                    <Fragment>
                      <div>
                        {articles.map((article, index) => {
                            return (
                                <Fragment
                                    key={"article_" + article.articleId + "_" + article.entryClassPK}
                                >
                                    <CommitmentLinkRow
                                        id={`commitment_listing_link_to_commitment_${
                                            article.articleId
                                        }`}
                                        to={match.path + "/details/" + article.articleId}
                                        reportListing={reportListing}
                                        article={article}
                                        mainCategoryProperties={mainCategoryProperties}
                                        listingType={pageTypeName}
                                    />
                                </Fragment>
                            );
                        })}

                        {!(articles.length < 50) &&
                        getMoreArticles && (
                            <Fragment>
                                <WhiteSpace height="40px"/>
                                <SecondaryButton
                                    id="commitment_listing_show_more_commitments"
                                    style={{
                                        alignSelf: "center",
                                        marginTop: 16
                                    }}
                                    onClick={() => {
                                        getMoreArticles();
                                        // getArticles({
                                        //   start: this.state.range.start,
                                        //   end: this.state.range.end + 50
                                        // });
                                    }}
                                >
                                    {translate({
                                        textKey: "sit.commitmentList.showMore"
                                    })}
                                </SecondaryButton>
                            </Fragment>
                        )}
                      </div>
                    </Fragment>
                ) : (
                    <Center>
                        <h4 style={{marginTop: 20}}>
                            {translate({
                                textKey: "sit.commitmentList.noMatches"
                            })}
                        </h4>
                    </Center>
                )}
                {pageType !== "33555" && pageType !== "33554" ? <ClusterMap /> :
                  <CommitmentStats pageType={pageType} stats={stats}/>}
            </div>
      </Fragment>

      ) : (
        <Fragment>
          <div className="commitmentReportRow">
            {articles.map((article, index) => {
                return (
                    <Fragment
                        key={"article_" + article.articleId + "_" + article.entryClassPK}
                    >
                        <CommitmentWithReportRow
                            id={`commitment_listing_link_to_commitment_${
                                article.articleId
                            }`}
                            to={match.path + "/details/" + article.articleId}
                            key={
                                "article_" + article.articleId + "_" + article.entryClassPK
                            }
                            reportListing={reportListing}
                            article={article}
                            mainCategoryProperties={mainCategoryProperties}
                        />
                    </Fragment>
                );
            })}
          </div>
        </Fragment>
      )}
    </div>
)

export default withRouter(
    connect(state => ({
        mainCategoryProperties: state.articles.mainCategoryProperties
    }))(CommitmentList)
);
