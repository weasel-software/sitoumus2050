// @flow

import { get } from "lodash";
import LiferayClient from "../utils/LiferayClient";
import randomstring from "randomstring";

import translate from "../components/reusable/translate";

import type {
  ORGANIZATION_SIZE,
  ORGANIZATION_TYPE_TYPE,
  ORGANIZATION_SUBTYPE_TYPE,
  COUNTRY_TYPE,
  PROFILE_TYPE,
  ORGANIZATION_TYPE,
  CATEGORY_TYPE,
  NotificationType
} from "../constants/types";

import { RegisterUser, UserExists } from "./user";
import { ErrorActions } from "./errors";

export type OrganizationDataType = {
  organizationId: string,
  organizationTypeId?: string,
  organizationSubTypeId?: string,
  organizationSizeId?: string,
  logo?: string
};

export type ORGANIZATION_STATE = {
  organizationTypes: ?Array<ORGANIZATION_TYPE_TYPE>,
  organizationSubTypes: ?{
    [string]: ORGANIZATION_SUBTYPE_TYPE
  },
  countries: ?Array<COUNTRY_TYPE>,
  organizationSizes: ?Array<ORGANIZATION_SIZE>,
  organizations: Array<ORGANIZATION_TYPE>,
  usersByOrganization: {
    [string]: Array<PROFILE_TYPE>
  },
  organizationNotification: NotificationType,
  organizationsByName: {
    [name: string]: OrganizationDataType
  },
  organizationsById: {
    [name: string]: OrganizationDataType
  }
};

// type ORGANIZATIONS_ACTION_MAP = "GET_ORGANIZATION_TYPES" | "GET_COUNTRIES";
// type ORGANIZATIONS_ACTION = { type?: ORGANIZATIONS_ACTION_MAP, value?: any };

type GetOrganizationTypesAction = {
  type: "GET_ORGANIZATION_TYPES",
  value: {
    organizationTypes: Array<ORGANIZATION_TYPE_TYPE>,
    organizationSubTypes: {
      [string]: ORGANIZATION_SUBTYPE_TYPE
    }
  }
};

type GetCountriesAction = {
  type: "GET_COUNTRIES",
  value: Array<COUNTRY_TYPE>
};

type GetOrganizationSizesAction = {
  type: "GET_ORGANIZATION_SIZES",
  value: Array<ORGANIZATION_SIZE>
};

type UpdateOrganizationAction = {
  type: "UPDATE_ORGANIZATION",
  value: any
};

type GetUserOrganizations = {
  type: "GET_ORGANIZATIONS",
  value: Array<ORGANIZATION_TYPE>
};

type GetUsersForOrganizations = {
  type: "GET_USERS_FOR_ORGANIZATIONS",
  value: {
    [string]: Array<PROFILE_TYPE>
  }
};

type addCategoriesToOrganization = {
  type: "ADD_CATEGORIES_TO_ORGANIZATION",
  value: any
};

type OrganizationUpdateNotificationAction = {
  type: "ORGANIZATION_UPDATE_NOTIFICATION",
  value: NotificationType
};

type AddUserNotificationAction = {
  type: "ADD_USER_NOTIFICATION",
  value: NotificationType
};

type GetOrganizationByOrganizationNameAction = {
  type: "GET_ORGANIZATION_BY_ORGANIZATION_NAME",
  value: {
    name: string,
    organizationData: OrganizationDataType
  }
};
type GetOrganizationByOrganizationIdAction = {
  type: "GET_ORGANIZATION_BY_ORGANIZATION_ID",
  value: {
    name: string,
    organizationData: OrganizationDataType
  }
};

export type Action =
  | GetOrganizationTypesAction
  | GetCountriesAction
  | GetOrganizationSizesAction
  | GetUserOrganizations
  | GetUsersForOrganizations
  | AddUserNotificationAction
  | UpdateOrganizationAction
  | addCategoriesToOrganization
  | OrganizationUpdateNotificationAction
  | GetOrganizationByOrganizationNameAction
  | GetOrganizationByOrganizationIdAction;

export type RegisterOrganizationPayloadType = {
  organizationName: ?string,
  description: ?string,
  contactEmail: ?string,
  communicationEmail: ?string,
  organizationType: ?ORGANIZATION_TYPE_TYPE,
  organizationSubType: ?ORGANIZATION_SUBTYPE_TYPE,
  homepageUrl: string
};

export const OrganizationActions = {
  getCountries: () => {
    return async (dispatch: GetCountriesAction => void) => {
      const countries = await LiferayClient("/country/get-countries", {
        active: true
      });

      dispatch({
        type: "GET_COUNTRIES",
        value: countries
      });
    };
  },
  getOrganizationSizes: () => {
    return async (dispatch: GetOrganizationSizesAction => void) => {
      const sizes = await LiferayClient(
        "/assetcategory/get-vocabulary-categories",
        {
          vocabularyId: 33547,
          start: 0,
          end: 100,
          obc: null
        }
      );
      dispatch({
        type: "GET_ORGANIZATION_SIZES",
        value: sizes
      });
    };
  },
  getOrganizationTypes: () => {
    return async (dispatch: GetOrganizationTypesAction => void) => {
      const response = await LiferayClient(
        "/assetcategory/get-vocabulary-categories",
        {
          vocabularyId: 33488,
          start: 0,
          end: 100,
          obc: null
        }
      );

      const organizationTypes = response.reduce((acc, value) => {
        const { parentCategoryId } = value;

        if (!acc[parentCategoryId]) {
          acc[parentCategoryId] = [value];
        } else {
          acc[parentCategoryId].push(value);
        }
        return acc;
      }, {});

      const mainTypes = organizationTypes["0"];

      const subTypes = Object.keys(organizationTypes)
        .filter(key => {
          return key !== "0";
        })
        .reduce((acc, val) => {
          acc[val] = organizationTypes[val];
          return acc;
        }, {});

      dispatch({
        type: "GET_ORGANIZATION_TYPES",
        value: {
          organizationTypes: mainTypes,
          organizationSubTypes: subTypes
        }
      });
    };
  },

  addCategoriesToOrganization: (
    categories: ?{
      organizationType: CATEGORY_TYPE,
      organizationSize: CATEGORY_TYPE,
      organizationSubType: CATEGORY_TYPE
    },
    organizationId: string,
    userId: string
  ) => {
    return async (dispatch: Action => void) => {
      if (!categories || !organizationId || !userId)
        return console.error(
          "INSUFFICIENT DATA PROVIDED TO ADD CATEGORIES TO ORGANIZATION"
        );
      const formData = new FormData();

      categories.organizationType &&
        formData.append("categoryIds", categories.organizationType.categoryId);
      categories.organizationSubType &&
        formData.append(
          "categoryIds",
          categories.organizationSubType.categoryId
        );
      categories &&
        categories.organizationSize &&
        formData.append("categoryIds", categories.organizationSize.categoryId);

      formData.append("organizationId", organizationId);

      return await fetch(
        `/o/commitment2050-service/organizationapi/addcategories`,
        {
          headers: {
            Accept: "application/json, text/plain, */*",
            "Content-Type": "multipart/form-data",
            "Cache-Control": "no-cache"
          },
          method: "POST",
          body: formData,
          credentials: "include"
        }
      );
    };
  },

  updateOrganization: (organizationData: ORGANIZATION_TYPE) => {
    return async (
      dispatch: Action => void,
      getState: () => { user: { profile: { userId: string } } }
    ) => {
      const emailAddresses = [];

      dispatch({
        type: "ORGANIZATION_UPDATE_NOTIFICATION",
        value: {
          state: "IN_PROGRESS",
          message: ""
        }
      });

      const email1 = get(organizationData, "emailAddresses[0].address");
      if (email1) {
        emailAddresses.push({
          address: email1,
          typeId: 12004
        });
      }

      const email2 = get(organizationData, "emailAddresses[1].address");
      if (email2) {
        emailAddresses.push({
          address: email2,
          typeId: 12005
        });
      }

      const pl = {
        parentOrganizationId: 0,
        organizationId: organizationData.organizationId,
        name: organizationData.name,
        type: "organization",
        regionId: 0,
        countryId: 88,
        statusId: 12017,
        comments: organizationData.comments,
        site: false,
        addresses: [
          {
            street1: "Helsinki",
            city: "Helsinki",
            zip: "0",
            countryId: 88,
            typeId: 12001,
            mailing: true,
            primary: true
          }
        ],
        emailAddresses,
        orgLabors: [],
        phones: [],
        websites: [
          {
            url: organizationData.websites[0].url,
            typeId: 12020
          }
        ]
      };

      const endpoint = "/organization/update-organization";
      const response = await LiferayClient(endpoint, pl);

      console.log("UPDATE ORG RESPONSE", response);

      const userId = getState().user.profile.userId;

      /*
       * Update all org. commitments with new org.name
       */

      const updOrgNameResp = await fetch(
        "/o/commitment2050-service/commitmentapi/updateCommitmentsOrgName",
        {
          method: "POST",
          body: JSON.stringify(response),
          headers: {
            Accept: "application/json, text/plain, */*",
            "Content-Type": "application/json",
            "Cache-Control": "no-cache"
          },
          credentials: "include"
        }
      );

      console.log(updOrgNameResp);
      await fetch("/o/commitment2050-service/organizationapi/logo", {
        method: "POST",
        body: JSON.stringify({
          logo: organizationData.logo
            ? organizationData.logo.split(",")[1]
            : null,
          organizationId: organizationData.organizationId
        }),
        headers: {
          Accept: "application/json, text/plain, */*",
          "Content-Type": "application/json",
          "Cache-Control": "no-cache"
        },
        credentials: "include"
      });

      await dispatch(
        // $FlowFixMe
        OrganizationActions.addCategoriesToOrganization(
          organizationData.categories,
          response.organizationId,
          userId
        )
      );

      // $FlowFixMe
      await dispatch(OrganizationActions.getUserOrganizations(userId));

      dispatch({
        type: "ORGANIZATION_UPDATE_NOTIFICATION",
        value: {
          state: "SUCCESS",
          message: translate({
            textKey: "sit.redux.organization.updatedSuccessfully"
          })
        }
      });

      setTimeout(() => {
        dispatch({
          type: "ORGANIZATION_UPDATE_NOTIFICATION",
          value: {
            state: "NOT_STARTED",
            message: ""
          }
        });
      }, 5000);
    };
  },

  registerOrganization: (
    organizationData: ORGANIZATION_TYPE,
    createNew: boolean
  ) => {
    return async (
      dispatch: Action => void,
      getState: () => { user: { profile: { userId: string } } }
    ) => {
      dispatch({
        type: "ORGANIZATION_UPDATE_NOTIFICATION",
        value: {
          state: "IN_PROGRESS",
          message: ""
        }
      });

      console.log("ORG DATA PLAYLOAD", organizationData);

      const emailAddresses = [];

      const email1 = get(organizationData, "emailAddresses[0].address");
      if (email1) {
        emailAddresses.push({
          address: email1,
          typeId: 12004
        });
      }

      const email2 = get(organizationData, "emailAddresses[1].address");
      if (email2) {
        emailAddresses.push({
          address: email2,
          typeId: 12005
        });
      }

      const pl = {
        parentOrganizationId: 0,
        name: organizationData.name,
        type: "organization",
        regionId: 0,
        countryId: 88,
        statusId: 12017,
        comments: organizationData.comments,
        site: false,
        addresses: [
          {
            street1: "Helsinki",
            city: "Helsinki",
            zip: "0",
            countryId: 88,
            typeId: 12001,
            mailing: true,
            primary: true
          }
        ],
        emailAddresses,
        orgLabors: [],
        phones: [],
        websites:
          organizationData.websites[0] && organizationData.websites[0].url
            ? [
                {
                  url: organizationData.websites[0].url,
                  typeId: 12020
                }
              ]
            : []
      };
      const endpoint = "/organization/add-organization";

      try {
        const response = await LiferayClient(endpoint, pl);
        const userId = getState().user.profile.userId;

        const addUserFormData = new FormData();
        addUserFormData.append("userId", userId);
        addUserFormData.append("organizationId", response.organizationId);

        await fetch("/o/commitment2050-service/organizationapi/adduser", {
          headers: {
            Accept: "application/json, text/plain, */*",
            // "Content-Type": "multipart/form-data",
            "Cache-Control": "no-cache"
          },
          method: "POST",
          body: addUserFormData,
          credentials: "include"
        });

        await fetch("/o/commitment2050-service/organizationapi/logo", {
          method: "POST",
          headers: {
            Accept: "application/json, text/plain, */*",
            "Content-Type": "application/json",
            "Cache-Control": "no-cache"
          },
          credentials: "include",
          body: JSON.stringify({
            logo: organizationData.logo
              ? organizationData.logo.split(",")[1]
              : null,
            organizationId: Number(response.organizationId)
          })
        });

        await dispatch(
          // $FlowFixMe
          OrganizationActions.addCategoriesToOrganization(
            organizationData.categories,
            response.organizationId,
            userId
          )
        );

        // $FlowFixMe
        dispatch(OrganizationActions.getUserOrganizations(userId));

        dispatch({
          type: "ORGANIZATION_UPDATE_NOTIFICATION",
          value: {
            state: "SUCCESS",
            message: translate({
              textKey: "sit.redux.organization.creationSucceeded"
            })
          }
        });
      } catch (error) {
        let message = "";
        console.log("Organiztion response: " + error.toString());
        if (error.toString().includes("There is another organization named")) {
          message = translate({
            textKey: "sit.redux.organization.alreadyExists"
          });
        } else if (error.toString().includes("WebsiteURLException")) {
          message = translate({ textKey: "sit.redux.organization.URLFormat" });
        } else {
          message = translate({
            textKey: "sit.redux.organization.creationFailed"
          });
        }

        dispatch({
          type: "ORGANIZATION_UPDATE_NOTIFICATION",
          value: {
            state: "FAILURE",
            message: message
          }
        });
      }

      setTimeout(() => {
        dispatch({
          type: "ORGANIZATION_UPDATE_NOTIFICATION",
          value: {
            state: "NOT_STARTED",
            message: ""
          }
        });
      }, 5000);
    };
  },
  getUserOrganizations: (id: string) => {
    return async (dispatch: Action => void) => {
      const organizations: Array<ORGANIZATION_TYPE> = await LiferayClient(
        "/organization/get-user-organizations",
        {
          userId: id
        }
      );
      let usersByOrganization = {};

      /* console.log("ORGS", organizations);*/

      for (let organization of organizations) {
        const { organizationId } = organization;
        usersByOrganization[organizationId] = await LiferayClient(
          "/user/get-organization-users",
          {
            organizationId
          }
        );

        const orgLogoResponse = await fetch(
          `/o/commitment2050-service/organizationapi/organizations/${organizationId}/logo`
        );

        const orgLogo = orgLogoResponse.ok && (await orgLogoResponse.text());

        organization["logo"] = orgLogo ? orgLogo : "";

        const websites = await LiferayClient("/website/get-websites", {
          className: "com.liferay.portal.kernel.model.Organization",
          classPK: organizationId
        });

        const categories = await LiferayClient(
          "/assetcategory/get-categories",
          {
            className: "com.liferay.portal.kernel.model.Organization",
            classPK: organizationId
          }
        );

        organization["websites"] = websites;

        const organizationType = categories.find(
          cat => cat.parentCategoryId === "0" && cat.vocabularyId !== "33547"
        );
        organization["categories"] = {
          organizationType,
          organizationSubType: categories.find(
            cat => cat.parentCategoryId === organizationType.categoryId
          ),
          organizationSize: categories.find(cat => cat.vocabularyId === "33547")
        };

        const emailAddresses = await LiferayClient(
          "/emailaddress/get-email-addresses",
          {
            className: "com.liferay.portal.kernel.model.Organization",
            classPK: organizationId
          }
        );

        organization["emailAddresses"] = emailAddresses;
      }

      dispatch({
        type: "GET_USERS_FOR_ORGANIZATIONS",
        value: usersByOrganization
      });

      dispatch({
        type: "GET_ORGANIZATIONS",
        value: organizations
      });
    };
  },

  inviteUserToOrganization: (
    email: string,
    firstName: string,
    lastName: string,
    organizationId: string
  ) => {
    return async (dispatch: Action => void, getState: any) => {
      dispatch({
        type: "ADD_USER_NOTIFICATION",
        value: {
          state: "IN_PROGRESS",
          message: ""
        }
      });
      const pass = randomstring.generate(8);
      try {
        const exists = await UserExists({
          email: email
        });

        if (!exists) {
          const formData = new FormData();
          formData.append("email", email);
          formData.append("organizationid", organizationId);

          const informUserAboutRegistration = await fetch(
            "/o/commitment2050-service//organizationapi/sendmsg/register-and-join",
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

          if (!informUserAboutRegistration.ok) {
            dispatch(
              // $FlowFixMe
              ErrorActions.handleError(
                Error(
                  informUserAboutRegistration.status +
                    " " +
                    informUserAboutRegistration.statusText
                )
              )
            );
          }
        }
        const registered = await RegisterUser({
          firstName,
          lastName,
          email,
          passwordReset: true,
          password: pass
        });

        if (registered) {
          if (registered.toString().includes("Error")) {
            dispatch({
              type: "ADD_USER_NOTIFICATION",
              value: {
                state: "FAILURE",
                message: registered.toString()
              }
            });
          } else {
            console.log("USER INVITED", email, firstName, lastName, pass);
            dispatch({
              type: "ADD_USER_NOTIFICATION",
              value: {
                state: "SUCCESS",
                message: translate({
                  textKey: "sit.redux.organization.userAddSuccess"
                })
              }
            });
            dispatch(
              // $FlowFixMe
              OrganizationActions.addUserToOrganization(
                registered.userId,
                organizationId
              )
            );
          }
        }

        setTimeout(() => {
          dispatch({
            type: "ADD_USER_NOTIFICATION",
            value: {
              state: "NOT_STARTED",
              message: ""
            }
          });
        }, 5000);
      } catch (error) {
        console.error("E", error);
        // $FlowFixMe
        dispatch(ErrorActions.handleError(Error(error)));
      }
    };
  },

  addUserToOrganization: (userId: string, organizationId: string) => {
    return async (
      dispatch: Action => void,
      getState: () => { user: { profile: { userId: string } } }
    ) => {
      if (!userId || !organizationId) return;
      dispatch({
        type: "ADD_USER_NOTIFICATION",
        value: {
          state: "IN_PROGRESS",
          message: ""
        }
      });
      console.log("ADD TO ORG", userId, organizationId);
      if (userId && organizationId) {
        try {
          const addUserFormData = new FormData();

          addUserFormData.append("userId", userId);
          addUserFormData.append("organizationId", organizationId);

          const response = await fetch(
            "/o/commitment2050-service/organizationapi/adduser",
            {
              headers: {
                Accept: "application/json, text/plain, */*",
                "Content-Type": "multipart/form-data",
                "Cache-Control": "no-cache"
              },
              method: "POST",
              body: addUserFormData,
              credentials: "include"
            }
          );

          // const json = await response.json();

          console.log("ADD USER RESP", response);
          // $FlowFixMe
          dispatch(OrganizationActions.getUserOrganizations(userId));

          dispatch({
            type: "ADD_USER_NOTIFICATION",
            value: {
              state: "SUCCESS",
              message: translate({
                textKey: "sit.redux.organization.userAddSuccess"
              })
            }
          });
        } catch (error) {
          dispatch({
            type: "ADD_USER_NOTIFICATION",
            value: {
              state: "FAILURE",
              message: translate({
                textKey: "sit.redux.organization.uerAddFailed"
              })
            }
          });
          console.error("FAILED TO ADD USER TO ORG", error);
        }

        setTimeout(() => {
          dispatch({
            type: "ADD_USER_NOTIFICATION",
            value: {
              state: "NOT_STARTED",
              message: ""
            }
          });
        }, 5000);
      }
    };
  },

  removeUserFromOrganization: (
    member: PROFILE_TYPE,
    organizationId: string
  ) => {
    return async (dispatch: Action => void, getState: any) => {
      console.log("DERP", member, organizationId);

      try {
        await LiferayClient("/user/unset-organization-users", {
          userIds: [member.userId],
          organizationId
        });
        const userId = getState().user.profile.userId;

        // $FlowFixMe
        dispatch(OrganizationActions.getUserOrganizations(userId));
      } catch (err) {
        console.error("ERROR REMOVING USER FROM ORGANIZATION", err);
      }
    };
  },

  clearAddUserSuccess: () => {
    return {
      type: "CLEAR_ADD_USER_SUCCESS"
    };
  },

  getOrganizationByOrganizationName: (name: string) => {
    return async (dispatch: Action => void) => {
      const organizationResponse = await fetch(
        `/o/commitment2050-service/organizationapi/organizationdetails?organization=${name}`
      );
      if (organizationResponse.ok) {
        const organizationData = await organizationResponse.json();
        //console.log("ORGANIZATIONDATA", organizationData);
        dispatch({
          type: "GET_ORGANIZATION_BY_ORGANIZATION_NAME",
          value: {
            name,
            organizationData
          }
        });
      } else {
        ErrorActions.handleError(
          Error(organizationResponse.status + organizationResponse.statusText)
        );
      }
    };
  },

  getOrganizationByOrganizationId: (organizationId: string) => {
    return async (dispatch: Action => void) => {
      const organizationResponse = await fetch(
        `/o/commitment2050-service/organizationapi/organizationdetailsbyid?organizationId=${organizationId}`
      );
      const organizationData =
        organizationResponse.ok && (await organizationResponse.json());
      if (organizationData) {
        //console.log("ORGANIZATIONDATA", organizationData);
        const name = organizationData.name;
        dispatch({
          type: "GET_ORGANIZATION_BY_ORGANIZATION_ID",
          value: {
            name,
            organizationData
          }
        });
      } else {
        ErrorActions.handleError(
          Error(organizationResponse.status + organizationResponse.statusText)
        );
      }
    };
  }
};

export default function reducer(
  state: ORGANIZATION_STATE = {
    organizationTypes: [],
    organizationSubTypes: {},
    countries: [],
    organizationSizes: [],
    organizations: [],
    usersByOrganization: {},
    addSuccess: false,
    organizationNotification: {
      state: "NOT_STARTED",
      message: ""
    },
    addUserToOrganizationNotification: {
      state: "NOT_STARTED",
      message: ""
    },
    organizationsByName: {},
    organizationsById: {}
  },
  action: Action
): ORGANIZATION_STATE {
  switch (action.type) {
    case "GET_ORGANIZATION_TYPES": {
      return {
        ...state,
        ...action.value
      };
    }
    case "GET_COUNTRIES": {
      return {
        ...state,
        countries: action.value
      };
    }
    case "GET_ORGANIZATION_SIZES": {
      return {
        ...state,
        organizationSizes: action.value
      };
    }
    case "GET_ORGANIZATIONS": {
      return {
        ...state,
        organizations: action.value
      };
    }
    case "GET_USERS_FOR_ORGANIZATIONS": {
      return {
        ...state,
        usersByOrganization: action.value
      };
    }
    case "ORGANIZATION_UPDATE_NOTIFICATION": {
      return {
        ...state,
        organizationNotification: action.value
      };
    }
    case "ADD_USER_NOTIFICATION": {
      return {
        ...state,
        addUserToOrganizationNotification: action.value
      };
    }
    case "GET_ORGANIZATION_BY_ORGANIZATION_NAME": {
      return {
        ...state,
        organizationsByName: {
          ...state.organizationsByName,
          [action.value.name]: action.value.organizationData
        }
      };
    }
    case "GET_ORGANIZATION_BY_ORGANIZATION_ID": {
      return {
        ...state,
        organizationsById: {
          ...state.organizationsById,
          [action.value.name]: action.value.organizationData
        }
      };
    }
    default:
      return state;
  }
}
