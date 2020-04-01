// @flow

import moment from "moment";

import { ErrorActions } from "./errors";

import type {
  LabelValueDataType,
  DatesTextsValuesDataType
} from "../constants/types";

export type STATISTICS_STATE = {
  data: {
    cumulativeData: DatesTextsValuesDataType,
    mainGoalData: LabelValueDataType,
    startingYearData: LabelValueDataType,
    organizationTypeData: LabelValueDataType
  },
  updatingBarChart: boolean
};

type updateCumulativeData = {
  type: "UPDATE_CUMULATIVE_DATA",
  value: DatesTextsValuesDataType
};

type updateByStartingYearData = {
  type: "UPDATE_STARTING_YEAR_DATA",
  value: LabelValueDataType
};

type updateByMainGoalData = {
  type: "UPDATE_MAIN_GOAL_DATA",
  value: LabelValueDataType
};

type updateByOrganizationTypeData = {
  type: "UPDATE_ORGANIZATION_TYPE_DATA",
  value: LabelValueDataType
};

type updatingBarChart = {
  type: "UPDATING_BAR_CHART",
  value: boolean
};

type commitmentcount = {
  type: "COMMITMENT_COUNT",
  value: string
};

export type Action =
  | updateCumulativeData
  | updateByStartingYearData
  | updateByMainGoalData
  | updateByOrganizationTypeData
  | updatingBarChart;

export const actionTypes: { [string]: string } = {
  UPDATE_CUMULATIVE_DATA: "UPDATE_CUMULATIVE_DATA",
  UPDATE_STARTING_YEAR_DATA: "UPDATE_STARTING_YEAR_DATA",
  UPDATE_MAIN_GOAL_DATA: "UPDATE_MAIN_GOAL_DATA",
  UPDATE_ORGANIZATION_TYPE_DATA: "UPDATE_ORGANIZATION_TYPE_DATA",
  UPDATING_BAR_CHART: "UPDATING_BAR_CHART"
};

export const StatisticsActions = {
  updateCumulativeData: () => {
    return async (dispatch: Action => void) => {
      let formattedData = { dates: [], values: [], texts: [] };
      try {
        const response = await fetch(
          `/o/commitment2050-service/statisticsapi/commitments/cumulativesum`
        );
        let json = [{ date: "", value: 0 }];
        if (response.ok) {
          json = await response.json();
          console.log(json);
        } else if (!response.ok) {
          throw new Error(response.status + " " + response.statusText);
        }

        json.forEach(element => {
          formattedData.dates.push(
            moment(element.date, "DD.MM.YYYY").format("YYYY-MM-DD HH:mm:ss")
          );
          formattedData.texts.push(element.date);
          formattedData.values.push(element.value);
        });
      } catch (error) {
        console.error("E", error);
        // $FlowFixMe
        dispatch(ErrorActions.handleError(Error(error)));
      }
      dispatch({
        type: "UPDATE_CUMULATIVE_DATA",
        value: formattedData
      });
    };
  },

  updateByMainGoalData: () => {
    return async (dispatch: Action => void) => {
      let formattedData = { labels: [], values: [] };
      try {
        dispatch({
          type: "UPDATING_BAR_CHART",
          value: true
        });
        const response = await fetch(
          `/o/commitment2050-service/statisticsapi/commitments/countsbymaingoal`
        );
        let json = [{ categoryname: "", amountOfCommitments: 0 }];
        if (response.ok) {
          json = await response.json();
        } else if (!response.ok) {
          throw new Error(response.status + " " + response.statusText);
        }

        json.forEach(element => {
          formattedData.labels.push(element.categoryname);
          formattedData.values.push(element.amountOfCommitments);
        });
      } catch (error) {
        console.error("E", error);
        // $FlowFixMe
        dispatch(ErrorActions.handleError(Error(error)));
      }

      dispatch({
        type: "UPDATE_MAIN_GOAL_DATA",
        value: formattedData
      });
      dispatch({
        type: "UPDATING_BAR_CHART",
        value: false
      });
    };
  },

  updateByStartingYearData: () => {
    return async (dispatch: Action => void) => {
      let json = [{ value: 0, date: "" }];
      let formattedData = { labels: [], values: [] };
      try {
        dispatch({
          type: "UPDATING_BAR_CHART",
          value: true
        });
        const response = await fetch(
          `/o/commitment2050-service/statisticsapi/commitments/countsbystartingyear`
        );
        if (response.ok) {
          json = await response.json();
        } else if (!response.ok) {
          throw new Error(response.status + " " + response.statusText);
        }

        json.forEach(element => {
          formattedData.labels.push(element.date);
          formattedData.values.push(element.value);
        });
      } catch (error) {
        console.error("E", error);
        // $FlowFixMe
        dispatch(ErrorActions.handleError(Error(error)));
      }
      dispatch({
        type: "UPDATE_STARTING_YEAR_DATA",
        value: formattedData
      });
      dispatch({
        type: "UPDATING_BAR_CHART",
        value: false
      });
    };
  },
  updateByOrganizationTypeData: () => {
    return async (dispatch: Action => void) => {
      let formattedData = { labels: [], values: [] };
      try {
        dispatch({
          type: "UPDATING_BAR_CHART",
          value: true
        });
        const response = await fetch(
          `/o/commitment2050-service/statisticsapi/commitments/countsbyorganizationtype`
        );
        let json = [{ amountOfCommitments: 0, categoryname: "" }];
        if (response.ok) {
          json = await response.json();
        } else if (!response.ok) {
          throw new Error(response.status + " " + response.statusText);
        }
        json.forEach(element => {
          let categoryName = element.categoryname;
          if (categoryName.includes("2. aste")) {
            categoryName = "Koulu/oppilaitos (2. aste)";
          } else if (categoryName.includes("perusasteen")) {
            categoryName = "Koulu/oppilaitos (perusaste)";
          }
          formattedData.labels.push(categoryName);
          formattedData.values.push(element.amountOfCommitments);
        });
      } catch (error) {
        console.error("E", error);
        // $FlowFixMe
        dispatch(ErrorActions.handleError(Error(error)));
      }
      dispatch({
        type: "UPDATE_ORGANIZATION_TYPE_DATA",
        value: formattedData
      });
      dispatch({
        type: "UPDATING_BAR_CHART",
        value: false
      });
    };
  }
};

export default function reducer(
  state: STATISTICS_STATE = {
    data: {
      cumulativeData: {
        values: [],
        dates: [],
        texts: []
      },
      mainGoalData: {
        values: [],
        labels: []
      },
      startingYearData: {
        values: [],
        labels: []
      },
      organizationTypeData: {
        values: [],
        labels: []
      }
    },
    updatingBarChart: true
  },
  action: Action
): STATISTICS_STATE {
  switch (action.type) {
    case "UPDATE_CUMULATIVE_DATA": {
      return {
        ...state,
        data: {
          ...state.data,
          cumulativeData: action.value
        }
      };
    }
    case "UPDATE_MAIN_GOAL_DATA": {
      return {
        ...state,
        data: {
          ...state.data,
          mainGoalData: action.value
        }
      };
    }
    case "UPDATE_STARTING_YEAR_DATA": {
      return {
        ...state,
        data: {
          ...state.data,
          startingYearData: action.value
        }
      };
    }
    case "UPDATE_ORGANIZATION_TYPE_DATA": {
      return {
        ...state,
        data: {
          ...state.data,
          organizationTypeData: action.value
        }
      };
    }
    case "UPDATING_BAR_CHART": {
      return {
        ...state,
        updatingBarChart: action.value
      };
    }
    default:
      return state;
  }
}
