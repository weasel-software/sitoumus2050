module.exports = function override(config, env) {
  if (env === "production") {
    const rewireDefinePlugin = require("react-app-rewire-define-plugin");
    const PropReader = require("properties-reader");
    const fi_FI = PropReader(
      "./src/language-properties/Language_fi_FI.properties"
    ).getAllProperties();

    const sv_SE = PropReader(
      "./src/language-properties/Language_sv_SE.properties"
    ).getAllProperties();

    const en_US = PropReader(
      "./src/language-properties/Language_en_US.properties"
    ).getAllProperties();

    const translations = {
      fi_FI: JSON.stringify(fi_FI),
      sv_SE: JSON.stringify(sv_SE),
      en_US: JSON.stringify(en_US)
    };

    const rewireVendorSplitting = require("react-app-rewire-vendor-splitting");
    config = rewireVendorSplitting(config, env);

    config = rewireDefinePlugin(config, env, {
      "window.TRANSLATIONS": translations
    });

    config.output.publicPath = "/o/sitoumus-2050-theme/react-ui/build/";
  } else {
    config.output.publicPath = "http://localhost:3000/";
  }
  return config;
};
