// @flow

import styled from "styled-components";
import { darken, lighten } from "polished";
import { Link } from "react-router-dom";

const StyledLink = styled(Link)`
  color: #eee;
  text-decoration: none;
  &:hover {
    text-decoration: none;
    color: ${darken(0.08, "#eee")};
  }
  &:active {
    text-decoration: none;
    color: ${lighten(0.08, "#eee")};
  }
  &:focus {
    text-decoration: none;
    color: ${lighten(0.08, "#eee")};
  }
`;

export default StyledLink;
