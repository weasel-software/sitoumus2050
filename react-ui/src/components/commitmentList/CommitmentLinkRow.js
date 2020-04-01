// @flow

import type {
  ARTICLE_TYPE,
  CATEGORY_TYPE,
  CommitmentType
} from "../../constants/types";

import React, {Fragment} from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";
import { get, truncate } from "lodash";

import getLocalizedString from "../../utils/getLocalizedString";

import GoalIcon from "../reusable/GoalIcon";
import CommitmentShares from "../reusable/CommitmentShares";

import {
  JustifyLeft,
  JustifyRight,
  CommitmentName,
  CommitmentParty,
  Column,
  RoundIcon,
  ComListIconText,
  SquareIcon,
  CommitmentIsParentType
} from "./styles";

const StyledCommitmentLinkRow = styled(Link)`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #dcdcdc;
  max-width: 550px;
  align-self: center;
  padding: 10px 10px 10px 10px;
  cursor: pointer;
  width: 100%;
  &:hover {
    border-bottom: 1px solid #93be38;
    box-shadow: 0px 0px 6px rgba(0, 0, 0, 0.4);
    border-radius: 6px;
  }
`;

const CommitmentDescription = styled.p`
  color: #333333;
  font-size: 1em;
  font-weight: normal;
  flex-wrap: wrap;
  overflow-wrap: break-word;
  word-wrap: break-word;
  max-width: 460px;
`;

const BoldCommitmentName = styled(CommitmentName)`
  font-weight: bold;
`


const CommitmentLinkRow = ({
  article,
  id,
  to,
  mainCategoryProperties,
  listingType
}: {
  article: ARTICLE_TYPE | CommitmentType,
  id: string,
  to: string,
  mainCategoryProperties: { [number]: Array<CATEGORY_TYPE> },
  listingType?: string
}) => {
  const SHORT_DESCRIPTION_MAX_LENGTH = 103; 

  const getIconLink = (article, properties = mainCategoryProperties) => {
    if (!article || !properties) return "";

    const categoryId = article.categoryIds
      ? get(article, "categoryIds[0]")
      : get(article, "assetCategoryIds[0][0]");
    if (!categoryId) return "";

    const categoryProps = properties[categoryId];
    if (categoryProps && categoryProps.length > 0) {
      return categoryProps[0].value;
    } else return "";
  };

  const priority = article.priority || article.joinedCount || null;
  const title = article.title_fi_FI
    ? getLocalizedString("title", article)
    : article.title || null;
  
  const shortDescription = article.shortDescription 
    ? truncate(article.shortDescription, {
      'length': SHORT_DESCRIPTION_MAX_LENGTH,
      'omission': '…',
      'separator': /[\s.!?]/
    })
    : null;

  const getIconType = (article) => {
    if (article.organizationLogo) return 'organizationLogo';
    else if (getIconLink(article).length !== 0) return 'goalIcon';
    return 'noIcon';
  };

  const getIcon = (article) => ({
    organizationLogo: (
      <SquareIcon>
        <img
          className="organization-logo"
          src={article.organizationLogo}
          style={{
            maxWidth: 80,
            maxHeight: 80
          }}
          alt="logo"
        />
      </SquareIcon>
    ),
    goalIcon: (
      <GoalIcon icon={getIconLink(article)} />
    ),
    noIcon: (
      <SquareIcon/>
    ),
  });

  return (
    <StyledCommitmentLinkRow id={id} to={to}>
      <ComListIconText >
        {listingType == "henkilo" ? "": getIcon(article)[getIconType(article)]}
        <Column style={{ flex: "1" }}>
          <CommitmentParty> {article.organizationName}<CommitmentIsParentType> {article.joinedCount > 0 ? "Kattositoumus" : ""} </CommitmentIsParentType></CommitmentParty>
          { listingType == "henkilo" ? (
            <Fragment>
              <BoldCommitmentName>{title}</BoldCommitmentName>
              <CommitmentDescription>{ shortDescription ? shortDescription : "" }</CommitmentDescription>
            </Fragment>
            ) : (
              <CommitmentName>{title}</CommitmentName>
            )}
        </Column>
      </ComListIconText>

      <JustifyRight>
        {priority && <CommitmentShares joinedCount={article.joinedCount} shared={article.shared} likes={article.likes} />}
      </JustifyRight>
    </StyledCommitmentLinkRow>
  );
};

export default CommitmentLinkRow;
