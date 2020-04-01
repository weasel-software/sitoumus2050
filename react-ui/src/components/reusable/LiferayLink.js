// @flow

import React from "react";

const LiferayLink = ({
  href,
  children,
  lang,
  ...rest
}: {
  href: string,
  children: React$Node,
  lang?: string,
  rest?: any
}) => {
  const langId = window.Liferay && window.Liferay.ThemeDisplay.getLanguageId();
  const withoutLanguageId =
    lang && href.replace(langId, "").replace("/" + langId.split("_")[0], "");

  const target =
    withoutLanguageId && window.Liferay
      ? `/${lang ||
          window.Liferay.ThemeDisplay.getLanguageId()}${withoutLanguageId}`
      : href;

  const stripped = target.replace("//", "/");

  return (
    <a href={stripped} {...rest}>
      {children}
    </a>
  );
};

export default LiferayLink;
