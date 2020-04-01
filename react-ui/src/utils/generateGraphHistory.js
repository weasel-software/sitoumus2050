// @flow

import moment from "moment";

import type { OperationType } from "../constants/types";

const generateGraphHistory = (operation: OperationType) => {
  let graphHistory = {};
  if (operation.reports) {
    for (
      let reportIndex = 0;
      reportIndex < operation.reports.length - 1;
      reportIndex++
    ) {
      const endDateLabel =
        operation.reports && operation.reports[reportIndex].reportEndDate;
      const endDateText = moment(endDateLabel)
        .format("l")
        .toString();
      const reportMeters =
        operation.reports && operation.reports[reportIndex].reportMeters;
      if (reportMeters) {
        for (
          let meterIndex = 0;
          meterIndex < reportMeters.length;
          meterIndex++
        ) {
          const meterId =
            reportMeters[meterIndex].commitmentOperationMeterRefId;

          const currentLevel = reportMeters[meterIndex].currentLevel;
          if (meterId) {
            if (!graphHistory[meterId]) {
              graphHistory = {
                ...graphHistory,
                [meterId]: { values: [], texts: [], labels: [] }
              };
            }
            if (currentLevel) {
              graphHistory = {
                ...graphHistory,
                [meterId]: {
                  values: [...graphHistory[meterId].values, currentLevel],
                  labels: [...graphHistory[meterId].labels, endDateLabel],
                  texts: [...graphHistory[meterId].texts, endDateText]
                }
              };
            }
          }
        }
      }
    }
  }
  return graphHistory;
};

export default generateGraphHistory;
