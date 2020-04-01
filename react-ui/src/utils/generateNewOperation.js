// @flow

const generateNewOperation = (key: ?string) => {
  return {
    operationId: "",
    articleId: "",
    id: "",
    operationTitle_fi_FI: "",
    operationTitle_sv_SE: "",
    operationTitle_en_US: "",
    meters: [],
    operationDescription_fi_FI: "",
    operationDescription_sv_SE: "",
    operationDescription_en_US: "",
    key: key || String(Math.random()),
    reports: []
  };
};

export default generateNewOperation;
