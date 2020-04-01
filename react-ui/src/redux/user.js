// @flow

import randomstring from "randomstring";

import { unexpectedCase } from "../constants/types";
import LiferayClient from "../utils/LiferayClient";
import { backgroundInformation } from "../utils/hundredTodos";
import type {
  PROFILE_TYPE,
  NotificationType,
  LocaleType,
  CityType,
  HundredTodosType
} from "../constants/types";

import { ArticleActions } from "./articles";
import { OrganizationActions } from "./organizations";
import { ErrorActions } from "./errors";

import translate from "../components/reusable/translate";

export type USER_STATE = {
  profile: ?PROFILE_TYPE,
  error?: string,
  locale: LocaleType,
  updateProfileNotification: NotificationType,
  updateProfileNotification: NotificationType,
  signUpNotification: NotificationType,
  hundredTodos: ?HundredTodosType
};

const ProfileData = {
  userId: "",
  oldPassword: "",
  newPassword1: "",
  newPassword2: "",
  passwordReset: false,
  reminderQueryQuestion: "",
  reminderQueryAnswer: "",
  screenName: "",
  emailAddress: "",
  facebookId: 0,
  openId: "",
  portrait: true,
  portraitBytes: [],
  languageId: "fi_FI",
  timeZoneId: "UTC",
  greeting: "",
  comments: "",
  firstName: "",
  middleName: "",
  lastName: "",
  prefixId: 0,
  suffixId: 0,
  male: true,
  birthdayMonth: 1,
  birthdayDay: 1,
  birthdayYear: 1,
  smsSn: "",
  facebookSn: "",
  jabberSn: "",
  skypeSn: "",
  twitterSn: "",
  jobTitle: "",
  groupIds: [],
  organizationIds: [],
  roleIds: [],
  userGroupRoles: [],
  userGroupIds: [],
  addresses: [],
  emailAddresses: [],
  phones: [],
  websites: [],
  announcementsDelivers: [],
  personType: null
};

type GetProfileAction = {
  type: "GET_PROFILE",
  value: ?PROFILE_TYPE
};

// type UpdateProfileAction = {
//   type: "UPDATE_PROFILE",
//   value: ?PROFILE_TYPE
// };

type ProfileUpdateInProgressAction = {
  type: "PROFILE_UPDATE_IN_PROGRESS",
  value: boolean
};

type ChangePasswordAction = {
  type: "CHANGE_PASSWORD",
  value: {
    userId: string,
    password1: string,
    password2: string,
    passwordReset: boolean
  }
};

type PasswordUpdateInProgressAction = {
  type: "PASSWORD_UPDATE_IN_PROGRESS",
  value: boolean
};

type SubmitRegistarionAction = {
  type: "SUBMIT_REGISTRATION",
  value: {
    success: boolean,
    error?: string
  }
};

type ClearRegistrationAction = {
  type: "CLEAR_REGISTRATION",
  value: {
    error: string,
    success: ?boolean
  }
};

type GetProfilePersonTypeAction = {
  type: "GET_PROFILE_PERSON_TYPE",
  value: "Yksityishenkilö" | "Organisaation edustaja"
};

type GetProfileAllowEmailReminderAction = {
  type: "GET_PROFILE_ALLOW_EMAIL_REMINDER",
  value: boolean
};

type GetLanguage = {
  type: "GET_LANGUAGE",
  value: "fi_FI" | "en_US" //"sv_SE" |
};

type PasswordUpdateNotificationAction = {
  type: "UPDATE_PASSWORD_NOTIFICATION",
  value: NotificationType
};

type UpdateProfileNotificationAction = {
  type: "UPDATE_PROFILE_NOTIFICATION",
  value: NotificationType
};

type SignUpNotificationAction = {
  type: "SIGN_UP_NOTIFICATION",
  value: NotificationType
};

type GetCities = {
  type: "GET_CITIES",
  value: Array<CityType>
};

type GetHundredTodos = {
  type: "GET_HUNDRED_TODOS",
  value: ?HundredTodosType
};

export type Action =
  | GetProfileAction
  // | UpdateProfileAction
  | ProfileUpdateInProgressAction
  | ChangePasswordAction
  | PasswordUpdateInProgressAction
  | SubmitRegistarionAction
  | ClearRegistrationAction
  | GetProfilePersonTypeAction
  | GetProfileAllowEmailReminderAction
  | GetLanguage
  | PasswordUpdateNotificationAction
  | UpdateProfileNotificationAction
  | SignUpNotificationAction
  | GetCities
  | GetHundredTodos;

export const actionTypes: { [string]: string } = {
  SUBMIT_REGISTRATION: "SUBMIT_REGISTRATION",
  CHANGE_PASSWORD: "CHANGE_PASSWORD",
  GET_PROFILE: "GET_PROFILE",
  CLEAR_REGISTRATION: "CLEAR_REGISTRATION",
  GET_PROFILE_PERSON_TYPE: "GET_PROFILE_PERSON_TYPE",
  GET_PROFILE_ALLOW_EMAIL_REMINDER: "GET_PROFILE_ALLOW_EMAIL_REMINDER"
};

export const UserExists = async ({ email }: { email: string }) => {
  try {
    await LiferayClient("/user/get-user-by-email-address", {
      companyId: window.Liferay.ThemeDisplay.getCompanyId(),
      emailAddress: email
    });
    return true;
  } catch (e) {
    console.log("REG ERR", e);
    return false;
  }
};

export const RegisterUser = async ({
  firstName,
  lastName,
  email,
  password,
  passwordReset = false,
  autoPassword = false
}: {
  firstName: string,
  lastName: string,
  email: string,
  password?: string,
  passwordReset?: boolean,
  autoPassword?: boolean
}) => {
  try {
    const payload = await LiferayClient("/user/add-user", {
      companyId: window.Liferay.ThemeDisplay.getCompanyId(),
      password1: password,
      password2: password,
      autoScreenName: true,
      screenName: null,
      emailAddress: email,
      facebookId: 0,
      openId: "",
      locale: "fi_FI",
      firstName: firstName,
      middleName: "",
      lastName: lastName,
      prefixId: 0,
      suffixId: 0,
      male: true,
      birthdayMonth: 1,
      birthdayDay: 1,
      birthdayYear: 1970,
      jobTitle: "",
      groupIds: null,
      organizationIds: null,
      roleIds: null,
      userGroupIds: null,
      sendEmail: true,
      passwordReset: passwordReset,
      autoPassword: autoPassword
    });
    return payload;
  } catch (e) {
    console.log("REG ERR", e);
    return e;
  }
};

export const UserActions = {
  getCities: () => {
    return async (dispatch: Action => void) => {
      try {
        let json = {};
        const response = await fetch(
          `/documents/20143/138272/suomi_kuntaluettelo.json`
        );

        if (response.ok) {
          json = await response.json();

          dispatch({
            type: "GET_CITIES",
            value: json
          });
        }
      } catch (err) {
        console.error("ERR", err);
      }
    };
  },

  getHundredTodos: () => {
    return async (dispatch: Action => void) => {
      try {
        const tipsTodoString = localStorage.getItem("tipsTodo");
        const tipsDoneString = localStorage.getItem("tipsDone");
        const calculationsString = localStorage.getItem("calculations");

        if (tipsTodoString || tipsDoneString || calculationsString) {
          const tipsTodo = JSON.parse(tipsTodoString || "[]");
          const tipsDone = JSON.parse(tipsDoneString || "[]");
          const calculations = JSON.parse(calculationsString || "{}");

          const startDate = new Date();
          const endDate = new Date();
          endDate.setFullYear(endDate.getFullYear() + 1);

          const params: Array<string> = calculations
            ? [calculations.reduction.toString()]
            : [];

          const hundredTodos = {
            tipsTodo,
            tipsDone,
            calculations,
            commitmentTypeId: 33553,
            startDate: startDate.toISOString(),
            endDate: endDate.toISOString(),
            primaryGoalId: 31804, // Kestävät elämäntavat,
            title_fi_FI: translate({
              textKey: "sit.commitmentCreation.reduce.co2.title_pre",
              languageOverride: "fi_FI"
            })
              .concat(" ")
              .concat(params[0])
              .concat(
                translate({
                  textKey: "sit.commitmentCreation.reduce.co2.title_suf",
                  languageOverride: "fi_FI"
                })
              ),
            title_sv_SE: translate({
              textKey: "sit.commitmentCreation.reduce.co2.title_pre",
              languageOverride: "sv_SE"
            })
              .concat(" ")
              .concat(params[0])
              .concat(
                translate({
                  textKey: "sit.commitmentCreation.reduce.co2.title_suf",
                  languageOverride: "sv_SE"
                })
              ),
            title_en_US: translate({
              textKey: "sit.commitmentCreation.reduce.co2.title_pre",
              languageOverride: "en_US"
            })
              .concat(" ")
              .concat(params[0])
              .concat(
                translate({
                  textKey: "sit.commitmentCreation.reduce.co2.title_suf",
                  languageOverride: "en_US"
                })
              ),
            backgroundInformation_fi_FI: backgroundInformation(
              "fi_FI",
              calculations
            ),
            backgroundInformation_sv_SE: backgroundInformation(
              "sv_SE",
              calculations
            ),
            backgroundInformation_en_US: backgroundInformation(
              "en_US",
              calculations
            )
          };

          dispatch({
            type: "GET_HUNDRED_TODOS",
            value: hundredTodos
          });
        } else {
          dispatch({
            type: "GET_HUNDRED_TODOS",
            value: null
          });
        }
      } catch (err) {
        console.error("ERR", err);
      }
    };
  },

  getProfile: () => {
    return async (dispatch: Action => void) => {
      const languageId =
        window.Liferay && window.Liferay.ThemeDisplay.getLanguageId();
      dispatch({
        type: "GET_LANGUAGE",
        value: languageId
      });
      window.languageId = languageId; // Make this globally available for convenience. Used in translate.js

      // $FlowFixMe
      dispatch(ArticleActions.getMainCategories());
      // $FlowFixMe
      dispatch(ArticleActions.get100SmartWays());
      // $FlowFixMe
      dispatch(ArticleActions.getAcceptedMeterChartTypes());
      // $FlowFixMe
      dispatch(UserActions.getCities());

      const isSignedIn =
        window.Liferay && window.Liferay.ThemeDisplay.isSignedIn();

      if (isSignedIn) {
        const userId =
          window.Liferay && window.Liferay.ThemeDisplay.getUserId();

        let profile = await LiferayClient("/user/get-user-by-id", { userId });

        if (profile) {
          console.log("PROFILE", profile);

          const userProfilePicURLResp = await fetch(
            `/o/commitment2050-service/userapi/users/${userId}/portraiturl`
          );

          /*console.log("USER PIC", userProfilePicURLResp);*/

          const pic =
            userProfilePicURLResp.ok && (await userProfilePicURLResp.text());
          /*console.log("PIC", pic);*/

          const allowEmailReminder = await LiferayClient(
            "/expandovalue/get-data",
            {
              companyId: 20116,
              className: "com.liferay.portal.kernel.model.User",
              tableName: "CUSTOM_FIELDS",
              columnName: "allowEmailReminder",
              classPK: profile.userId
            }
          );

          const personType = await LiferayClient("/expandovalue/get-data", {
            companyId: 20116,
            className: "com.liferay.portal.kernel.model.User",
            tableName: "CUSTOM_FIELDS",
            columnName: "personType",
            classPK: profile.userId
          });

          const addresses = await LiferayClient("/address/get-addresses", {
            className: "com.liferay.portal.kernel.model.Contact",
            classPK: profile.contactId
          });

          /*console.log("ADDRESSES REPLY", addresses);*/

          dispatch({
            type: "GET_PROFILE",
            value: {
              ...ProfileData,
              ...profile,
              allowEmailReminder,
              personType: personType[0],
              addresses: addresses,
              profilePicURL: pic
            }
          });

          dispatch({
            type: "PROFILE_UPDATE_IN_PROGRESS",
            value: false
          });
          // $FlowFixMe
          dispatch(ArticleActions.getUserCommitments(profile.userId));

          // $FlowFixMe
          dispatch(OrganizationActions.getUserOrganizations(profile.userId));
        }
      } else {
        console.log("SIGNED IN ?, NOPE");
        dispatch({
          type: "GET_PROFILE",
          value: null
        });
      }
    };
  },

  updateProfile: (profile: PROFILE_TYPE) => {
    return async (dispatch: Action => void, getState: any) => {
      dispatch({
        type: "UPDATE_PROFILE_NOTIFICATION",
        value: {
          state: "IN_PROGRESS",
          message: ""
        }
      });

      const organizationIds = getState().organizations.organizations.map(
        org => org.organizationId
      );

      const prof = {
        ...ProfileData,
        ...profile,
        organizationIds
      };

      console.log("PAYLOAD", prof);

      try {
        await LiferayClient("/user/update-user", {
          ...prof
        });

        await LiferayClient("/expandovalue/add-values", {
          companyId: 20116,
          className: "com.liferay.portal.kernel.model.User",
          tableName: "CUSTOM_FIELDS",
          classPK: prof.userId,
          attributeValues: {
            allowEmailReminder: prof ? prof.allowEmailReminder : false
          }
        });

        await LiferayClient("/expandovalue/add-values", {
          companyId: 20116,
          className: "com.liferay.portal.kernel.model.User",
          tableName: "CUSTOM_FIELDS",
          classPK: prof.userId,
          attributeValues: {
            personType: prof ? prof.personType : []
          }
        });

        // dispatch({
        //   type: "UPDATE_PROFILE",
        //   value: {
        //     ...updatedProfile,
        //     allowEmailReminder: updatedEmailReminder,
        //     updatedPersonType: updatedPersonType[0]
        //   }
        // });

        await fetch("/o/commitment2050-service/userapi/portrait", {
          method: "POST",
          body: JSON.stringify({
            portrait:
              profile.profilePicURL && profile.profilePicURL.split(",")[1],
            userId: profile.userId
          }),
          headers: {
            Accept: "application/json, text/plain, */*",
            "Content-Type": "application/json",
            "Cache-Control": "no-cache"
          },
          credentials: "include"
        });

        dispatch({
          type: "UPDATE_PROFILE_NOTIFICATION",
          value: {
            state: "SUCCESS",
            message: translate({
              textKey: "sit.redux.user.profileUpdateSuccess"
            })
          }
        });

        // $FlowFixMe
        dispatch(UserActions.getProfile());
      } catch (err) {
        console.error(err);
        dispatch({
          type: "UPDATE_PROFILE_NOTIFICATION",
          value: {
            state: "FAILURE",
            message: translate({
              textKey: "sit.redux.user.profileUpdateFailed"
            })
          }
        });
      }

      setTimeout(() => {
        dispatch({
          type: "UPDATE_PROFILE_NOTIFICATION",
          value: {
            state: "NOT_STARTED",
            message: ""
          }
        });
      }, 5000);
      // dispatch(
      //   ErrorActions.toggleUpdateSuccessNotification(message, type, true)
      // );
    };
  },

  changePassword: ({
    userId,
    password1,
    password2
  }: {
    userId: number,
    password1: string,
    password2: string
  }) => {
    return async (dispatch: Action => void) => {
      dispatch({
        type: "UPDATE_PASSWORD_NOTIFICATION",
        value: {
          state: "IN_PROGRESS",
          message: ""
        }
      });

      try {
        const response = await LiferayClient("/user/update-password", {
          userId,
          password1,
          password2,
          passwordReset: false
        });
        dispatch({
          type: "CHANGE_PASSWORD",
          value: response
        });
        // message = "Salasanan vaihtaminen onnistui";
        // type = "success";
        dispatch({
          type: "UPDATE_PASSWORD_NOTIFICATION",
          value: {
            state: "SUCCESS",
            message: translate({
              textKey: "sit.redux.user.passwordUpdateSuccess"
            })
          }
        });
      } catch (error) {
        let message = "";
        if (
          error
            .toString()
            .includes("must not be equal to their current password")
        ) {
          message = translate({
            textKey: "sit.redux.user.passwordAlreadyExists"
          });
        } else {
          message = translate({ textKey: "sit.redux.unexpectedError" });
        }
        dispatch({
          type: "UPDATE_PASSWORD_NOTIFICATION",
          value: {
            state: "FAILURE",
            message
          }
        });
      }

      setTimeout(() => {
        dispatch({
          type: "UPDATE_PASSWORD_NOTIFICATION",
          value: {
            state: "NOT_STARTED",
            message: ""
          }
        });
      }, 5000);
    };
  },

  registerAndLogin: (
    {
      email,
      firstName,
      lastName
    }: {
      email: string,
      firstName: string,
      lastName: string
    },
    callback: () => void
  ) => {
    return async (dispatch: Action => void) => {
      try {
        const formData = new FormData();
        formData.append("firstName", firstName);
        formData.append("lastName", lastName);
        formData.append("emailAddress", email);

        const response = await fetch(
          `/o/commitment2050-service/userapi/users/login`,
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
        if (response.ok) {
          const data = await response.json();
          window.location.href = data;
        } else {
          dispatch({
            type: "SIGN_UP_NOTIFICATION",
            value: {
              state: "FAILURE",
              message: translate({
                textKey: "sit.redux.user.registrationFailedEmail"
              })
            }
          });

          setTimeout(() => {
            dispatch({
              type: "SIGN_UP_NOTIFICATION",
              value: {
                state: "NOT_STARTED",
                message: ""
              }
            });
          }, 3500);

          return;
        }
      } catch (err) {
        console.error("E", err);
        dispatch(
          // $FlowFixMe
          ErrorActions.handleError(Error(err))
        );
      }
    };
  },

  submitRegistration: (
    {
      email,
      firstName,
      lastName,
      passwordReset = false
    }: {
      email: string,
      firstName: string,
      lastName: string,
      passwordReset?: boolean
    },
    callback: () => void
  ) => {
    return async (dispatch: Action => void) => {
      dispatch({
        type: "SIGN_UP_NOTIFICATION",
        value: {
          state: "IN_PROGRESS",
          message: ""
        }
      });
      try {
        const pass = randomstring.generate(8);

        const registerPayload = await RegisterUser({
          firstName,
          lastName,
          email,
          password: pass,
          passwordReset: true
        });
        console.log(
          "REGISTER PYALOAD",
          registerPayload,
          typeof registerPayload
        );
        if (registerPayload && registerPayload.toString().includes("Error")) {
          dispatch({
            type: "SIGN_UP_NOTIFICATION",
            value: {
              state: "FAILURE",
              message: translate({
                textKey: "sit.redux.user.registrationFailedEmail"
              })
            }
          });

          setTimeout(() => {
            dispatch({
              type: "SIGN_UP_NOTIFICATION",
              value: {
                state: "NOT_STARTED",
                message: ""
              }
            });
          }, 3500);

          return;
        }

        dispatch({
          type: "SUBMIT_REGISTRATION",
          value: {
            success: true
          }
        });
        dispatch({
          type: "SIGN_UP_NOTIFICATION",
          value: {
            state: "SUCCESS",
            message: translate({
              textKey: "sit.redux.user.registrationSuccess"
            })
          }
        });
        callback();
      } catch (error) {
        dispatch({
          type: "SUBMIT_REGISTRATION",
          value: {
            error,
            success: false
          }
        });
        dispatch({
          type: "SIGN_UP_NOTIFICATION",
          value: {
            state: "FAILURE",
            message: translate({ textKey: "sit.redux.user.registrationFailed" })
          }
        });
        if (
          error.toString().includes("must not be duplicate but is already used")
        ) {
          dispatch(
            // $FlowFixMe
            ErrorActions.toggleUpdateSuccessNotification(
              translate({
                textKey: "sit.redux.user.registrationEmailAlreadyExistsInfo"
              }),
              "failure",
              true
            )
          );
          dispatch({
            type: "SIGN_UP_NOTIFICATION",
            value: {
              state: "FAILURE",
              message: translate({
                textKey: "sit.redux.user.registrationFailedEmail"
              })
            }
          });
        } else {
          dispatch(
            // $FlowFixMe
            ErrorActions.toggleUpdateSuccessNotification(
              translate({ textKey: "sit.redux.unexpectedError" }),
              "failure",
              true
            )
          );
        }
        console.error("REGISTRATION FAILED", error);
      }
      setTimeout(() => {
        dispatch({
          type: "SIGN_UP_NOTIFICATION",
          value: {
            state: "NOT_STARTED",
            message: ""
          }
        });
      }, 5000);
    };
  },

  clearRegistration: () => {
    return (dispatch: ClearRegistrationAction => void) => {
      dispatch({
        type: "CLEAR_REGISTRATION",
        value: {
          error: "",
          success: null
        }
      });
    };
  }
};

export default function reducer(
  state: USER_STATE = {
    profile: null,
    error: "",
    cities: [],
    locale: window.Liferay && window.Liferay.ThemeDisplay.getLanguageId(),
    updatePasswordNotification: {
      state: "NOT_STARTED",
      message: ""
    },
    updateProfileNotification: {
      state: "NOT_STARTED",
      message: ""
    },
    signUpNotification: {
      state: "NOT_STARTED",
      message: ""
    },
    hundredTodos: null
  },
  action: Action
): USER_STATE {
  switch (action.type) {
    case "SUBMIT_REGISTRATION": {
      return {
        ...state,
        ...action.value
      };
    }
    case "CHANGE_PASSWORD": {
      return {
        ...state
      };
    }
    case "GET_PROFILE": {
      return {
        ...state,
        profile: action.value
      };
    }
    case "CLEAR_REGISTRATION": {
      return {
        ...state,
        ...action.value
      };
    }
    case "PROFILE_UPDATE_IN_PROGRESS": {
      return {
        ...state,
        profileUpdateInProgress: action.value
      };
    }
    case "PASSWORD_UPDATE_IN_PROGRESS": {
      return {
        ...state,
        passwordUpdateInProgress: action.value
      };
    }

    case "GET_PROFILE_PERSON_TYPE": {
      return {
        ...state,
        profile: {
          ...state.profile,
          personType: action.value
        }
      };
    }
    case "GET_PROFILE_ALLOW_EMAIL_REMINDER": {
      return {
        ...state,
        profile: {
          ...state.profile,
          allowEmailReminder: action.value
        }
      };
    }
    case "GET_LANGUAGE": {
      return {
        ...state,
        locale: action.value
      };
    }
    case "UPDATE_PASSWORD_NOTIFICATION": {
      return {
        ...state,
        updatePasswordNotification: action.value
      };
    }
    case "UPDATE_PROFILE_NOTIFICATION": {
      return {
        ...state,
        updateProfileNotification: action.value
      };
    }
    case "SIGN_UP_NOTIFICATION": {
      return {
        ...state,
        signUpNotification: action.value
      };
    }
    case "GET_CITIES": {
      return {
        ...state,
        cities: action.value
      };
    }
    case "GET_HUNDRED_TODOS": {
      return {
        ...state,
        hundredTodos: action.value
      };
    }
    default:
      unexpectedCase(action.value);
      return state;
  }
}
