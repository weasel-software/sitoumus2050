// @flow

import { get } from "lodash";

const translate = ({
  textKey,
  languageOverride,
  params,
  ...rest
}: {
  textKey: string,
  languageOverride?: "fi_FI" | "en_US" | "sv_SE",
  params?: Array<string>
}) => {
  if (!textKey) {
    console.warn("No textKey was provided to translate");
    return "";
  }

  const languageId = languageOverride || window.languageId;
  const translations = window.TRANSLATIONS;

  const message = translations ? get(translations, [languageId, textKey]) : "";
  if (message && !params) return message; // quick fix, optimized logic cannot be used with params

  const translation =
    window.Liferay &&
    window.Liferay.Language.get(textKey, params ? params : null);

  if (translation && translation !== textKey) {
    return translation;
  }
  // If we are here, the translation is missing. Lets check if we are running a test ..
  if (
    languageId &&
    window &&
    window.process &&
    window.process.env &&
    window.process.env.NODE_ENV === "test"
  ) {
    // TODO: this reads the file for every invocation which can be perf issue, but is sufficient for tests atleast now
    const PropReader = require("properties-reader");
    const translations = PropReader(
      "./src/language-properties/Language_" + languageId + ".properties"
    ).getAllProperties();

    let s = get(translations, [textKey]);
    if (s && params) {
      let i;
      for (i = 0; i < params.length; i++) {
        s = s.replace("{" + i + "}", params[i]);
      }
    }
    if (s) {
      return s;
    }
  }
  return `_${textKey}_`;
};

export default translate;
