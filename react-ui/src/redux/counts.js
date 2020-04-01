import {unexpectedCase} from "../constants/types";

export type COUNTS_STATE = {
  doneLifestyleTestCount: string,
  averageReduction: Object,
  topLifestyleCommitments: Array,
}

type getDoneLifestyleTestCount = {
  type: 'GET_LIFESTYLE_TEST_COUNT',
  value: string
}

type getAverageReduction = {
  type: 'GET_AVERAGE_REDUCTION',
  value: Array
}

type getTopLifestyleCommitments = {
  type: 'GET_TOP_LIFESTYLE_COMMITMENTS',
  value: Array
}

export type Action =
  | getDoneLifestyleTestCount
  | getAverageReduction
  | getTopLifestyleCommitments;

export const CountsActions = {
  getDoneLifestyleTestCount: () => {
    return async (dispatch: Action => void) => {
      try {
        let text;
        const response = await fetch(
          `/o/commitment2050-service/commitmentapi/getDoneLifestyleTestCount`
        );

        if (response.ok) {
          text = await response.text();
        }

        dispatch({
          type: "GET_LIFESTYLE_TEST_COUNT",
          value: text
        });
      } catch (err) {
        console.error("ERR", err);
      }
    };
  },
  getAverageReduction: () => {
    return async (dispatch: Action => void) => {
      try {
        let json = [];
        const response = await fetch(
          `/o/commitment2050-service/commitmentapi/getavgreduction`
        );

        if (response.ok) {
          json = await response.json();
        }

        dispatch({
          //  sumPercent, totalMeters, averageReductionPercentage, commitments, averageFootprintKg, totalReductionKg
          type: "GET_AVERAGE_REDUCTION",
          value: {
            averageReductionPercentage: Math.round(json[0][2]),
            totalReductionKg: Math.round(json[0][5])
          }
        });
      } catch (err) {
        console.error("ERR", err);
      }
    }
  },
  getTopLifestyleCommitments: () => {
    return async (dispatch: Action => void) => {
      try {
        let json = {};
        const response = await fetch(
          `/o/commitment2050-service/statisticsapi/commitments/getTopLifestyleCommitments`
        );

        if (response.ok) {
          json = await response.json();
        }

        dispatch({
          //  sumPercent, totalMeters, averageReductionPercentage, commitments, averageFootprintKg, totalReductionKg
          type: "GET_TOP_LIFESTYLE_COMMITMENTS",
          value: json
        });
      } catch (err) {
        console.error("ERR", err);
      }
    }
  }

}

export default function reducer(
  state: COUNTS_STATE = {
    lifestyleTestCount: "",
    averageReduction: [],
    topLifestyleCommitments: [],
  },
  action: Action
): COUNTS_STATE {
  switch (action.type) {
    case "GET_LIFESTYLE_TEST_COUNT": {
      return {
        ...state,
        doneLifestyleTestCount: action.value
      };
    }
    case "GET_AVERAGE_REDUCTION": {
      return {
        ...state,
        averageReduction: action.value
      };
    }
    case "GET_TOP_LIFESTYLE_COMMITMENTS": {
      return {
        ...state,
        topLifestyleCommitments: action.value
      }
    }
    default: {
      return state;
    }
  }
}