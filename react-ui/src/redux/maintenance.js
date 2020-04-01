// @flow

import { unexpectedCase } from "../constants/types";
import type { NotificationType } from "../constants/types";

import translate from "../components/reusable/translate";

export type MAINTENANCE_STATE = {
  error?: string,
  joinCommitmentsNotification: NotificationType
};

type JoinCommitmentsAction = {
  type: "JOIN_COMMITMENTS",
  value: {
    parentId: string,
    childId: string
  }
};

type JoinCommitmentsInProgressAction = {
  type: "JOIN_COMMITMENTS_IN_PROGRESS",
  value: boolean
};

type JoinCommitmentsNotificationAction = {
  type: "JOIN_COMMITMENTS_NOTIFICATION",
  value: NotificationType
};

type GetGreenDealReportReminderTemplate = {
  type: "GET_GREENDEAL_REPORT_REMINDER_TEMPLATE",
  value: *
};

type SendGreenDealReportReminderNotification = {
  type: "SEND_GREENDEAL_REPORT_REMINDERS_NOTIFICATION",
  value: *
};

type FormatAllAddresses = {
  type: "FORMAT_ADDRESSES",
  value: *
};

type AddOrganizationIds = {
  type: "ADD_ORGANIZATION_IDS",
  value: *
};

type PreCalculateCommitmentsPerCityAction = {
  type: "PRE_CALCULATE_COMMITMENTS_PER_CITY",
  value: *
};

export type Action =
  | JoinCommitmentsAction
  | JoinCommitmentsInProgressAction
  | JoinCommitmentsNotificationAction
  | GetGreenDealReportReminderTemplate
  | SendGreenDealReportReminderNotification
  | FormatAllAddresses
  | AddOrganizationIds
  | PreCalculateCommitmentsPerCityAction;

export const JoinCommitmentsActions = {
  joinCommitments: ({
    parentId,
    childId
  }: {
    parentId: string,
    childId: string
  }) => {
    return async (dispatch: Action => void) => {
      dispatch({
        type: "JOIN_COMMITMENTS_NOTIFICATION",
        value: {
          state: "IN_PROGRESS",
          message: ""
        }
      });

      try {
        const formData = new FormData();
        formData.append("parentCommitmentId", parentId);
        formData.append("childCommitmentId", childId);
        const joinResponse = await fetch(
          "/o/commitment2050-service/commitmentapi/commitments/jointoparent",
          {
            headers: {
              Accept: "application/json, text/plain, */*",
              "Content-Type": "multipart/form-data",
              "Cache-Control": "no-cache"
            },
            method: "POST",
            credentials: "include",
            body: formData
          }
        );
        if (!joinResponse.ok) {
          let message = translate({ textKey: "sit.redux.join.failed" });
          dispatch({
            type: "JOIN_COMMITMENTS_NOTIFICATION",
            value: {
              state: "FAILURE",
              message
            }
          });
        } else {
          dispatch({
            type: "JOIN_COMMITMENTS_NOTIFICATION",
            value: {
              state: "SUCCESS",
              message: translate({
                textKey: "sit.redux.maintenance.join.Success"
              })
            }
          });
        }
      } catch (error) {
        let message = translate({ textKey: "sit.redux.join.failed" });
        dispatch({
          type: "JOIN_COMMITMENTS_NOTIFICATION",
          value: {
            state: "FAILURE",
            message
          }
        });
      }

      setTimeout(() => {
        dispatch({
          type: "JOIN_COMMITMENTS_NOTIFICATION",
          value: {
            state: "NOT_STARTED",
            message: ""
          }
        });
      }, 5000);
    };
  }
};

export const MaintenanceActions = {
  sendGreenDealReportReminders: () => {
    return async (dispatch: Action => void) => {
      try {
        dispatch({
          type: "SEND_GREENDEAL_REPORT_REMINDERS_NOTIFICATION",
          value: {
            state: "IN_PROGRESS",
            message: ""
          }
        });

        const response = await fetch(
          `/o/commitment2050-service/maintenanceapi/send-greendeal-report-reminders`,
          {
            method: "POST",
            credentials: "include"
          }
        );
        if (response.ok) {
          const content = await response.json();
          const value =
            content && content.error
              ? {
                  state: "FAILURE",
                  message: content.error
                }
              : {
                  state: "SUCCESS",
                  message: "OK"
                };

          dispatch({
            type: "SEND_GREENDEAL_REPORT_REMINDERS_NOTIFICATION",
            value: value
          });
        } else {
          console.error(
            "getGreenDealReportReminderTemplate response not OK",
            response
          );
        }
      } catch (err) {
        console.error("ERR", err);
        dispatch({
          type: "SEND_GREENDEAL_REPORT_REMINDERS_NOTIFICATION",
          value: {
            state: "FAILURE",
            message: err.toString()
          }
        });
      }
    };
  },

  getGreenDealReportReminderTemplate: () => {
    return async (dispatch: Action => void) => {
      try {
        const response = await fetch(
          `/o/commitment2050-service/maintenanceapi/templates/greendeal-report-reminder`
        );
        if (response.ok) {
          const content = await response.json();
          dispatch({
            type: "GET_GREENDEAL_REPORT_REMINDER_TEMPLATE",
            value: content
          });
        } else if (response.status === 404) {
          const content = await response.json();
          dispatch({
            type: "GET_GREENDEAL_REPORT_REMINDER_TEMPLATE",
            value: content
          });
        } else {
          console.error(
            "getGreenDealReportReminderTemplate response not OK",
            response
          );
          dispatch({
            type: "GET_GREENDEAL_REPORT_REMINDER_TEMPLATE",
            value: { error: "email template not found" }
          });
        }
      } catch (err) {
        console.error("ERR", err);
      }
    };
  },

  formatAllAddresses: () => {
    return async (dispatch: Action => void) => {
      try {
        dispatch({
          type: "FORMAT_ADDRESSES",
          value: {
            state: "IN_PROGRESS",
            message: ""
          }
        });
        const response = await fetch(
          "/o/commitment2050-service/databasealterationsapi/formataddresses",
          {
            method: "POST",
            credentials: "include"
          }
        );
        if (response.ok) {
          dispatch({
            type: "FORMAT_ADDRESSES",
            value: {
              state: "SUCCESS",
              message: "Osoitteet muokattu onnistuneesti!"
            }
          });
        } else if (response.status === 404) {
          dispatch({
            type: "FORMAT_ADDRESSES",
            value: {
              state: "FAILURE",
              message:
                "Palvelin vastasi 404. Osoitteita ei onnistuttu muokkaamaan."
            }
          });
        } else {
          dispatch({
            type: "FORMAT_ADDRESSES",
            value: {
              state: "FAILURE",
              message:
                "Jokin meni vikaan! Osoitteita ei onnistuttu muokkaamaan."
            }
          });
        }
      } catch (err) {
        dispatch({
          type: "FORMAT_ADDRESSES",
          value: {
            state: "FAILURE",
            message: err.toString()
          }
        });
      }
    };
  },

  preCalculateCommitmentsPerCity: () => {
    return async (dispatch: Action => void) => {
      try {
        dispatch({
          type: "PRE_CALCULATE_COMMITMENTS_PER_CITY",
          value: {
            state: "IN_PROGRESS",
            message: ""
          }
        });
        const response = await fetch(
          "/o/commitment2050-service/maintenanceapi/pre-calculate-commitments-per-city",
          {
            method: "POST",
            credentials: "include"
          }
        );
        if (response.ok) {
          dispatch({
            type: "PRE_CALCULATE_COMMITMENTS_PER_CITY",
            value: {
              state: "SUCCESS",
              message: "Sitoumukset laskettu onnistuneesti!"
            }
          });
        } else if (response.status === 404) {
          dispatch({
            type: "PRE_CALCULATE_COMMITMENTS_PER_CITY",
            value: {
              state: "FAILURE",
              message:
                "Palvelin vastasi 404. Sitoumuksia ei onnistuttu laskemaan."
            }
          });
        } else {
          dispatch({
            type: "PRE_CALCULATE_COMMITMENTS_PER_CITY",
            value: {
              state: "FAILURE",
              message: "Jokin meni vikaan! Sitoumuksia ei onnistuttu laskemaan."
            }
          });
        }
      } catch (err) {
        dispatch({
          type: "PRE_CALCULATE_COMMITMENTS_PER_CITY",
          value: {
            state: "FAILURE",
            message: err.toString()
          }
        });
      }
    };
  },

  addOrganizationIds: () => {
    return async (dispatch: Action => void) => {
      try {
        dispatch({
          type: "ADD_ORGANIZATION_IDS",
          value: {
            state: "IN_PROGRESS",
            message: ""
          }
        });
        const response = await fetch(
          "/o/commitment2050-service/databasealterationsapi/updateOrgIds",
          {
            method: "POST",
            credentials: "include"
          }
        );
        if (response.ok) {
          dispatch({
            type: "ADD_ORGANIZATION_IDS",
            value: {
              state: "SUCCESS",
              message: "Osoitteet muokattu onnistuneesti!"
            }
          });
        } else if (response.status === 404) {
          dispatch({
            type: "ADD_ORGANIZATION_IDS",
            value: {
              state: "FAILURE",
              message: "Palvelin vastasi 404. ID ei onnistuttu lisätä."
            }
          });
        } else {
          dispatch({
            type: "ADD_ORGANIZATION_IDS",
            value: {
              state: "FAILURE",
              message: "Jokin meni vikaan! ID ei onnistuttu lisätä."
            }
          });
        }
      } catch (err) {
        dispatch({
          type: "ADD_ORGANIZATION_IDS",
          value: {
            state: "FAILURE",
            message: err.toString()
          }
        });
      }
    };
  }
};

export default function reducer(
  state: MAINTENANCE_STATE = {
    error: "",
    joinCommitmentsNotification: {
      state: "NOT_STARTED",
      message: ""
    }
  },
  action: Action
): MAINTENANCE_STATE {
  switch (action.type) {
    case "JOIN_COMMITMENTS": {
      return {
        ...state
      };
    }
    case "JOIN_COMMITMENTS_IN_PROGRESS": {
      return {
        ...state,
        joinCommitmentsInProgress: action.value
      };
    }

    case "JOIN_COMMITMENTS_NOTIFICATION": {
      return {
        ...state,
        joinCommitmentsNotification: action.value
      };
    }
    case "SEND_GREENDEAL_REPORT_REMINDERS_NOTIFICATION": {
      return {
        ...state,
        sendGreenDealReportRemindersNotification: action.value
      };
    }
    case "GET_GREENDEAL_REPORT_REMINDER_TEMPLATE": {
      return {
        ...state,
        greenDealReportReminderTemplate: action.value
      };
    }
    case "FORMAT_ADDRESSES": {
      return {
        ...state,
        formatCommitmentAddressesResponse: action.value
      };
    }
    case "ADD_ORGANIZATION_IDS": {
      return {
        ...state,
        addOrganizationIdsResponse: action.value
      };
    }
    case "PRE_CALCULATE_COMMITMENTS_PER_CITY": {
      return {
        ...state,
        preCalculateCommitmentsPerCityResponse: action.value
      };
    }
    default:
      unexpectedCase(action.value);
      return state;
  }
}
