// @flow

import { css } from "styled-components";

const media = {
  handheld: (...args: any) => css`
    @media (max-width: 480px) {
      ${css(...args)};
    }
  `,
  pieFix: (...args: any) => css`
    @media (max-width: 850px) {
      ${css(...args)};
    }
  `
};

export { media };
