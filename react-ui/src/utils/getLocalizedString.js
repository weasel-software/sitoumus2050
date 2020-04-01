// @flow

import { get } from "lodash";

const getLocalizedString = (
  key: string,
  object: any,
  languageOverride?: "fi_FI" | "en_US"
) => {
  if (!key || !object) return "";
  const locale =
    languageOverride ||
    (window.Liferay ? window.Liferay.ThemeDisplay.getLanguageId() : "fi_FI");
  const localized = get(object, [`${key}_${locale}`]);

  if (!languageOverride && (!localized || localized === "")) return object[key];
  else return localized;
};

export default getLocalizedString;
