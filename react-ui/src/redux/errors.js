// @flow

import serializeError from "serialize-error";
import type { ErrorType } from "../constants/types";

export type ERRORS_STATE = {
  displayUpdateSuccessNotification: boolean,
  notificationMessage: string,
  notificationType: string,
  error: ?ErrorType
};

type toggleUpdateSuccessNotificationAction = {
  type: "TOGGLE_UPDATE_SUCCESS_NOTIFICATION",
  value: {
    visible: boolean,
    message: string,
    type: string
  }
};

type HandleErrorAction = {
  type: "HANDLE_ERROR",
  value: ErrorType
};

type ClearErrorAction = {
  type: "CLEAR_ERROR"
};

export type Action =
  | toggleUpdateSuccessNotificationAction
  | HandleErrorAction
  | ClearErrorAction;

export const actionTypes: { [string]: string } = {
  TOGGLE_UPDATE_SUCCESS_NOTIFICATION: "TOGGLE_UPDATE_SUCCESS_NOTIFICATION"
};

export const ErrorActions = {
  toggleUpdateSuccessNotification: (
    message: string,
    type: string,
    initialize: ?boolean
  ) => {
    return async (dispatch: any, getState: any) => {
      const visible = message ? true : false;
      dispatch({
        type: "TOGGLE_UPDATE_SUCCESS_NOTIFICATION",
        value: {
          visible: visible,
          message,
          type
        }
      });
    };
  },
  handleError: (error: ErrorType) => {
    return {
      type: "HANDLE_ERROR",
      value: error
    };
  },
  clearError: () => {
    return {
      type: "CLEAR_ERROR"
    };
  }
};

export default function reducer(
  state: ERRORS_STATE = {
    notificationMessage: "",
    notificationType: "",
    displayUpdateSuccessNotification: false,
    error: null
  },
  action: Action
): ERRORS_STATE {
  switch (action.type) {
    case "TOGGLE_UPDATE_SUCCESS_NOTIFICATION": {
      return {
        ...state,
        displayUpdateSuccessNotification: action.value.visible,
        notificationMessage: action.value.message,
        notificationType: action.value.type
      };
    }
    case "HANDLE_ERROR": {
      console.log("ERROR", action.value);
      return {
        ...state,
        error: serializeError(action.value)
      };
    }
    case "CLEAR_ERROR": {
      return {
        ...state,
        error: null
      };
    }
    default:
      return state;
  }
}
