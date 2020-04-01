// @flow

import type { CommitmentType, CATEGORY_TYPE } from "../../constants/types";

import React, { Fragment } from "react";
import { withRouter } from "react-router-dom";
import styled from "styled-components";

import WhiteSpace from "../reusable/WhiteSpace";
import translate from "../reusable/translate";

import CommitmentLinkRow from "../commitmentList/CommitmentLinkRow";

const StyledTitle = styled.h3`
  font-size: 16px;
  font-weight: bold;
  text-transform: uppercase;
  margin-top: 0;
`;

const JoinedListContainer = styled.div`
  padding-left: 30px;
`;

const JoinedList = ({
  currentArticleId,
  articles,
  mainCategoryProperties,
  renderName,
  match
}: {
  currentArticleId: string,
  articles: Array<CommitmentType>,
  mainCategoryProperties: { [number]: Array<CATEGORY_TYPE> },
  renderName: string => string,
  match: any,
  expanded?: boolean
}) => (
  <JoinedListContainer>
    {articles && (
      <Fragment>
        <StyledTitle>
          {translate({ textKey: "sit.joinedToArticle.joined" })}
        </StyledTitle>
        <WhiteSpace height="24px" />
        {articles.map((article, index) => {
          if (article.id !== currentArticleId) {
            const lastslashindex = match.path.lastIndexOf("/");
            const link =
              match.path.substring(0, lastslashindex + 1) + article.id;
            return (
              <Fragment key={"article_" + article.id + "_JoinedList"}>
                <CommitmentLinkRow
                  id={`joined_listing_link_to_commitment_${article.id}`}
                  to={link}
                  article={article}
                  mainCategoryProperties={mainCategoryProperties}
                />
              </Fragment>
            );
          } else {
            return null;
          }
        })}
      </Fragment>
    )}
  </JoinedListContainer>
);

export default withRouter(JoinedList);
