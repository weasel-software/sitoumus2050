// @flow

import React, { Fragment } from "react";
import styled from "styled-components";
import { darken } from "polished";
import LiferayLink from "./LiferayLink";
import moment from "moment";
import "moment/locale/fi";
import "moment/locale/sv";

import { ArticleActions } from "../../redux/articles";

const StyledLink = styled(LiferayLink)`
  padding: 2px 10px 2px 10px;
  border-radius: 4px;
  margin: 2px;
  &:hover {
    background: ${darken(0.025, "#93BE38")};
  }

  background: ${props =>
    props.selected ? darken(0.025, "#9eBe38") : "transparent"};
`;

const LanguageSelector = () => {
  const url = window.location.pathname + window.location.hash;
  const language = window.Liferay.ThemeDisplay.getLanguageId();
  moment.locale(language.split("_")[0]);
  ArticleActions.getMainCategories();
  return (
    <Fragment>
      <StyledLink
        href={url}
        lang="fi_FI"
        selected={language.includes("fi")}
        id="navbar_language_select_fi_FI"
      >
        FI
      </StyledLink>
      {
        <StyledLink
          href={url}
          lang="sv_SE"
          selected={language.includes("sv")}
          id="navbar_language_select_sv_SE"
        >
          SV
        </StyledLink>
      }
      <StyledLink
        href={url}
        lang="en_US"
        selected={language.includes("en")}
        id="navbar_language_select_en_US"
      >
        EN
      </StyledLink>
    </Fragment>
  );
};

export default LanguageSelector;
