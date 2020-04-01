// @flow
import { get } from "lodash";
import randomstring from "randomstring";
import LiferayClient from "../utils/LiferayClient";
import { isHundredTodosCommitment } from "../utils/hundredTodos";
import { unexpectedCase } from "../constants/types";

import { ErrorActions } from "./errors";

import translate from "../components/reusable/translate";

import type {
  CATEGORY_TYPE,
  CommitmentType,
  NotificationType,
  SMARTWAY_ARTICLE_TYPE,
  ReportType
} from "../constants/types";

type GetMainCategoriesAction = {
  type: "GET_MAIN_CATEGORIES",
  value: {
    mainCategoriesWithTranslations: Array<CATEGORY_TYPE>,
    mainCategoriesSecondaryObjectivesWithTranslations: Array<CATEGORY_TYPE>
  }
};

type CustomArticleType = {
  articleId: string,
  assetCategoryIds: Array<string>,
  entryClassPK: string,
  organizationName: string,
  title: string,
  userName: string
};

type GetArticlesAction = {
  type: "GET_ARTICLES",
  value: Array<{
    articleId: string,
    assetCategoryIds: Array<string>,
    entryClassPK: string,
    organizationName: string,
    title: string,
    userName: string
  }>
};

type GetCommitmentsPerCityAction = {
  type: "GET_COMMITMENTS_PER_CITY",
  value: any
};

type GetCategoriesInHierarchy = {
  type: "GET_CATEGORIES_IN_HIERARCHY",
  value: any
};

type GetArticleContentAction = {
  type: "GET_ARTICLE_CONTENT",
  value: any
};

type GetJoinedToArticle = {
  type: "GET_JOINED_TO_ARTICLE",
  value: any
};

type GetUserCommitmentsAction = {
  type: "GET_USER_COMMITMENTS",
  value: Array<CommitmentType>
};

type SaveCommitmentAction = {
  type: "SAVE_COMMITMENT",
  value: any
};

type UpdateSavedCommitmentAction = {
  type: "UPDATE_SAVED_COMMITMENT",
  commitment: CommitmentType
};

type GetSecondaryCategoriesAction = {
  type: "GET_SECONDARY_CATEGORIES",
  value: Array<CATEGORY_TYPE>
};

type GetMainCategoryPropsAction = {
  type: "GET_MAIN_CATEGORY_PROPS",
  value: {
    [number]: CATEGORY_TYPE
  }
};

type GetSecondaryCategoryPropertiesAction = {
  type: "GET_SECONDARY_CATEGORY_PROPERTIES",
  value: {
    [number]: CATEGORY_TYPE
  }
};

type GetAcceptedMeterChartTypesAction = {
  type: "GET_ACCEPTED_METER_CHART_TYPES",
  value: Array<string>
};

type GetCommitmentsForOrganizationsAction = {
  type: "GET_COMMITMENTS_FOR_ORGANIZATIONS",
  value: {
    [string]: Array<CommitmentType>
  }
};

type GetCommitmentCountForAllTypesAction = {
  type: "GET_COMMITMENT_COUNT_FOR_ALL_TYPES",
  value: Array<Number>
};

type ResetSavedCommitmentAction = {
  type: "RESET_SAVED_COMMITMENT",
  value: boolean
};

type CommitmentNotificationAction = {
  type: "COMMITMENT_NOTIFICATION",
  value: NotificationType
};

type resetSaveReportNotificationAction = {
  type: "SAVE_REPORT_NOTIFICATION",
  value: NotificationType
};

type SaveReportAction = {
  type: "SAVE_REPORT",
  value: ReportType
};

type UpdateSavedReportAction = {
  type: "UPDATE_SAVED_REPORT_TO_USER_COMMITMENTS",
  value: Array<CommitmentType>
};

type SaveReportNotificationAction = {
  type: "SAVE_REPORT_NOTIFICATION",
  value: NotificationType
};

type AddLikeToCommitmentAction = {
  type: "UPDATE_LIKES_COUNT",
  value: {
    likes: number,
    articleId: string
  }
};

type GetCommitmentsByOrganizationNameAction = {
  type: "GET_COMMITMENTS_BY_ORGANIZATION_NAME",
  value: {
    name: string,
    commitments: Array<CommitmentType>
  }
};

type GetCommitmentsByOrganizationIdAction = {
  type: "GET_COMMITMENTS_BY_ORGANIZATION_ID",
  value: {
    id: string,
    commitments: Array<CommitmentType>
  }
};

type Get100SmartWays = {
  type: "GET_100SMARTWAYS_ARTICLES",
  value: Array<SMARTWAY_ARTICLE_TYPE>
};

export type Action =
  | GetCategoriesInHierarchy
  | GetMainCategoriesAction
  | GetArticlesAction
  | GetCommitmentsPerCityAction
  | GetCommitmentCountForAllTypesAction
  | GetArticleContentAction
  | GetJoinedToArticle
  | GetUserCommitmentsAction
  | SaveCommitmentAction
  | UpdateSavedCommitmentAction
  | GetSecondaryCategoriesAction
  | GetMainCategoryPropsAction
  | GetSecondaryCategoryPropertiesAction
  | GetAcceptedMeterChartTypesAction
  | GetCommitmentsForOrganizationsAction
  | ResetSavedCommitmentAction
  | CommitmentNotificationAction
  | resetSaveReportNotificationAction
  | SaveReportAction
  | UpdateSavedReportAction
  | SaveReportNotificationAction
  | AddLikeToCommitmentAction
  | GetCommitmentsByOrganizationNameAction
  | GetCommitmentsByOrganizationIdAction
  | Get100SmartWays;

export type ARTICLES_STATE = {
  articles: Array<CustomArticleType>,
  articleContent: ?CommitmentType,
  mainCategories: Array<CATEGORY_TYPE>,
  secondaryCategories: Array<CATEGORY_TYPE>,
  joinedToArticle: ?Array<CommitmentType>,
  mainCategoryProperties: {
    [number]: CATEGORY_TYPE
  },
  commitmentsPerCity: Object,
  commitmentCountForAllTypes: Object,
  fetchedCategoryHierarchy: Object,
  secondaryCategoryProperties: {
    [number]: CATEGORY_TYPE
  },
  acceptedMeterChartTypes: Array<string>,
  userCommitments: ?Array<CommitmentType>,
  savedCommitment: ?CommitmentType,
  commitmentNotification: NotificationType,
  commitmentsForOrganizations: {
    // käyttäjän organisaatioiden sitoumukset
    [orgId: string]: Array<CommitmentType>
  },
  savedReport: ?ReportType,
  saveReportNotification: NotificationType,
  commitmentsByOrganizationName: {
    // minkä hyvänsä organisaation sitoumukset
    [organizationName: string]: Array<CommitmentType>
  },
  commitmentsByOrganizationId: {
    // minkä hyvänsä organisaation sitoumukset
    [organizationId: string]: Array<CommitmentType>
  },
  hundredSmartWaysArticles: Array<SMARTWAY_ARTICLE_TYPE>
};

const deLiferayCommitmentArticle = (LiferaydArticle, localeTitleKey, localeShortDescriptionKey) => {
  return {
    articleId: LiferaydArticle.fields.articleId.value,
    assetCategoryIds: [
      LiferaydArticle.fields.assetCategoryIds
        ? { ...LiferaydArticle.fields.assetCategoryIds.values }
        : null
    ],
    entryClassPK: LiferaydArticle.fields.entryClassPK.value,
    organizationName: get(
      LiferaydArticle,
      "fields.expando__keyword__custom_fields__Organization name.value"
    ),
    title: get(LiferaydArticle.fields, [localeTitleKey, "value"], ""),
    shortDescription: get(LiferaydArticle.fields, [localeShortDescriptionKey, "value"], ""),
    userName: get(
      LiferaydArticle,
      "fields.expando__keyword__custom_fields__creatorUserName name.value"
    ),
    priority: get(LiferaydArticle, "fields.priority.value", 0.0),
    organizationLogo: get(
      LiferaydArticle,
      "fields.expando__keyword__custom_fields__organizationLogo.value",
      ""
    ),
    joinedCount: LiferaydArticle.fields.joinedCount ? LiferaydArticle.fields.joinedCount.value : 0,
    likes: LiferaydArticle.fields.likes ? LiferaydArticle.fields.likes.value : 0
  };
};

const getTranslationsForCategories = categories => {
  const categoriesWithTranslations = categories.map(category => {
    const allTitle = category.title;
    let title_en_US = "",
      title_sv_SE = "",
      title_fi_FI = "",
      startIndex = 0,
      endIndex = 0;

    startIndex = allTitle.indexOf(`language-id="fi_FI"`) + 20;
    if (startIndex !== 19) {
      endIndex = allTitle.indexOf("<", startIndex);
      title_fi_FI = allTitle.substring(startIndex, endIndex);
    }

    startIndex = allTitle.indexOf(`language-id="en_US"`) + 20;
    if (startIndex !== 19) {
      endIndex = allTitle.indexOf("<", startIndex);
      title_en_US = allTitle.substring(startIndex, endIndex);
    }

    startIndex = allTitle.indexOf(`language-id="sv_SE"`) + 20;
    if (startIndex !== 19) {
      endIndex = allTitle.indexOf("<", startIndex);
      title_sv_SE = allTitle.substring(startIndex, endIndex);
    }
    return {
      ...category,
      title_en_US,
      title_sv_SE,
      title_fi_FI
    };
  });
  return categoriesWithTranslations;
};

export const ArticleActions = {
  getCategoriesInHierarchy: (categoryID: string) => {
    return async (dispatch: Action => void) => {
      try {
        let json = {};
        const response = await fetch(
          `/o/commitment2050-service/commitmentapi/categories/${categoryID}`
        );
        if (response.ok) {
          json = await response.json();
          dispatch({
            type: "GET_CATEGORIES_IN_HIERARCHY",
            value: json
          });
        }
      } catch (err) {
        console.error("ERR", err);
      }
    };
  },

  getCommitmentsPerCity: () => {
    return async (dispatch: Action => void) => {
      try {
        let json = {};
        const response = await fetch(
          `/o/commitment2050-service/commitmentapi/commitments-per-city`
        );
        if (response.ok) {
          json = await response.json();
          dispatch({
            type: "GET_COMMITMENTS_PER_CITY",
            value: json
          });
        }
      } catch (err) {
        console.error("ERR", err);
      }
    };
  },

  getMainCategories: () => {
    return async (dispatch: Action => void) => {
      const mainCategories = await LiferayClient(
        "/assetcategory/get-vocabulary-categories",
        {
          vocabularyId: 31800,
          start: 0,
          end: 10000,
          obc: null
        }
      );

      const mainCategoriesSecondaryObjectives = await LiferayClient(
        "/assetcategory/get-vocabulary-categories",
        {
          vocabularyId: 31801,
          start: 0,
          end: 10000,
          obc: null
        }
      );

      const mainCategoriesWithTranslations = getTranslationsForCategories(
        mainCategories
      );
      const mainCategoriesSecondaryObjectivesWithTranslations = getTranslationsForCategories(
        mainCategoriesSecondaryObjectives
      );

      dispatch({
        type: "GET_MAIN_CATEGORIES",
        value: {
          mainCategoriesWithTranslations,
          mainCategoriesSecondaryObjectivesWithTranslations
        }
      });

      let iconData = {};
      for (const category of mainCategories) {
        const data = await LiferayClient(
          "/assetcategoryproperty/get-category-properties",
          {
            entryId: category.categoryId
          }
        );

        iconData[category.categoryId] = data;
      }

      dispatch({
        type: "GET_MAIN_CATEGORY_PROPS",
        value: iconData
      });

      const secondaryCategories = await LiferayClient(
        "/assetcategory/get-vocabulary-categories",
        {
          vocabularyId: 31802,
          start: 0,
          end: 10000,
          obc: null
        }
      );

      dispatch({
        type: "GET_SECONDARY_CATEGORIES",
        value: secondaryCategories
      });

      let secondaryIconData = {};
      for (const category of secondaryCategories) {
        const data = await LiferayClient(
          "/assetcategoryproperty/get-category-properties",
          {
            entryId: category.categoryId
          }
        );

        secondaryIconData[category.categoryId] = data;
      }

      dispatch({
        type: "GET_SECONDARY_CATEGORY_PROPERTIES",
        value: secondaryIconData
      });
    };
  },

  get100SmartWays: () => {
    return async (dispatch: Action => void) => {
      try {
        let json = {};
        const response = await fetch(
          `/o/commitment2050-service/commitmentapi/templates/operations/100smartways`
        );

        if (response.ok) {
          json = await response.json();
          dispatch({
            type: "GET_100SMARTWAYS_ARTICLES",
            value: json.templates
          });
        }
      } catch (err) {
        console.error("ERR", err);
      }
    };
  },

  getArticles: ({
    searchTerm = "",
    categoryIds,
    locale,
    range = {
      start: 0,
      end: 50
    },
    sort
  }: {
    searchTerm: string,
    categoryIds: ?Array<string>,
    locale: string,
    range: {
      start: number,
      end: number
    },
    sort: string
  }) => {
    return async (dispatch: Action => void) => {
      locale =
        locale ||
        (window.Liferay && window.Liferay.ThemeDisplay.getLanguageId());
      if (!locale) return;
      const keywords = searchTerm;

      const categoryString =
        categoryIds &&
        categoryIds.length > 0 &&
        categoryIds
          .filter(val => val !== "")
          .map(val => `&categoryIds=${val}`)
          .join("");

      const response = await fetch(
        `/o/commitment2050-service/commitmentapi/commitments?keywords=${
          keywords ? JSON.stringify(keywords) : ""
        }${categoryString ? categoryString : ""}&language=${locale}&start=${
          range.start
        }&end=${range.end}&sort=${sort ? sort : ""}`,
        {
          headers: {
            Accept: "application/json, text/plain, */*",
            "Content-Type": "multipart/form-data",
            "Cache-Control": "no-cache"
          },
          method: "GET",
          credentials: "include"
        }
      );

      const data = response.ok && (await response.json());
      const localeTitleKey = "title_" + locale;
      const localeShortDescriptionKey = "ddm__text__31797__shortDescription_" + locale;

      const commitments = data
        ? data.data.map(commit =>
            deLiferayCommitmentArticle(commit, localeTitleKey, localeShortDescriptionKey)
          )
        : [];

      dispatch({
        type: "GET_ARTICLES",
        value: commitments
      });
    };
  },

  /* TODO: Add a similar get for org.id
   *  1. Have to add keywordsearch by org.id to backend, since only name search exists (commitmentapi => commitments GET)
   *  2. Add into orgnaizationcommitments.js
   * */
  getCommitmentsByOrganizationName: (name: string, sort: string) => {
    return async (dispatch: Action => void, getState: () => any) => {
      const locale = getState().user.locale;
      const urlEncName = encodeURIComponent(name);
      const response = await fetch(
        `/o/commitment2050-service/commitmentapi/commitments?organization=${urlEncName.toLowerCase()}&language=fi_FI&start=0&end=1000&sort=${sort ||
          ""}`,
        {
          headers: {
            Accept: "application/json, text/plain, */*",
            "Content-Type": "multipart/form-data",
            "Cache-Control": "no-cache"
          },
          method: "GET",
          credentials: "include"
        }
      );
      const json = response.ok && (await response.json());
      const localeTitleKey = "title_" + locale;
      const localeShortDescriptionKey = "ddm__text__31797__shortDescription_" + locale;

      if (json)
        dispatch({
          type: "GET_COMMITMENTS_BY_ORGANIZATION_NAME",
          value: {
            name,
            commitments: json.data.map(commitment =>
              deLiferayCommitmentArticle(commitment, localeTitleKey, localeShortDescriptionKey)
            )
          }
        });
    };
  },

  getArticleContent: (articleId: string) => {
    return async (dispatch: Action => void) => {
      let json = {};
      try {
        const response = await fetch(
          `/o/commitment2050-service/commitmentapi/commitments/approved/${articleId}`
        );
        if (response.ok) {
          json = await response.json();
        } else if (!response.ok) {
          throw new Error(response.status + " " + response.statusText);
        }
      } catch (err) {
        json = { articleId, error: err };
        console.error("E", err);
      }

      dispatch({
        type: "GET_ARTICLE_CONTENT",
        value: json
      });
    };
  },

  getJoinedToArticle: (articleId: string) => {
    return async (dispatch: Action => void) => {
      let json = [];
      try {
        const response = await fetch(
          `/o/commitment2050-service/commitmentapi/commitments/${articleId}/joined`
        );
        if (response.ok) {
          json = await response.json();
          json = json.data;
        } else if (!response.ok) {
          throw new Error(response.status + " " + response.statusText);
        }
      } catch (err) {
        console.error("E", err);
      }

      dispatch({
        type: "GET_JOINED_TO_ARTICLE",
        value: json
      });
    };
  },

  getUserCommitments: (userId: string) => {
    return async (dispatch: Action => void) => {
      try {
        const r = await fetch(
          `/o/commitment2050-service/commitmentapi/usercommitments?userId=${userId}`
        );

        if (r.ok) {
          const json = await r.json();

          // TODO: Tässä filtteröidään raportit pois sitoumuslistasta. Pitäisi tapahtua taustan puolella todennäköisesti
          dispatch({
            type: "GET_USER_COMMITMENTS",
            value: json.data.filter(comm => comm.categoryIds.length > 0)
          });
        }
      } catch (err) {
        console.error("E", err);
      }
    };
  },

  getCommitmentsForOrganizations: (organizationNames: Array<string>) => {
    return async (dispatch: Action => void) => {
      try {
        let results = {};
        for (const name of organizationNames) {
          const urlEncName = encodeURIComponent(name);
          const response = await fetch(
            `/o/commitment2050-service/commitmentapi/organizationcommitments?organization=${urlEncName}`
          );

          if (response.ok) {
            const json = await response.json();

            // TODO: Tässä filtteröidään raportit pois sitoumuslistasta. Pitäisi tapahtua taustan puolella todennäköisesti
            const withoutReports = json.data.filter(
              comm => comm.categoryIds.length > 0
            );
            if (withoutReports) results[name] = withoutReports;
          }
        }

        console.log("ORG COMMS", results);

        dispatch({
          type: "GET_COMMITMENTS_FOR_ORGANIZATIONS",
          value: results
        });
      } catch (err) {
        console.error("ERR", err);
      }
    };
  },

  getCommitmentsForOrganizationById: (organizationId: string) => {
    return async (dispatch: Action => void) => {
      try {
        let results = {};
        /*for (const id of organizationId) {*/
        const response = await fetch(
          `/o/commitment2050-service/commitmentapi/organizationcommitments?organizationId=${organizationId}`
        );

        if (response.ok) {
          const json = await response.json();

          // TODO: Tässä filtteröidään raportit pois sitoumuslistasta. Pitäisi tapahtua taustan puolella todennäköisesti
          const withoutReports = json.data.filter(
            comm => comm.categoryIds.length > 0
          );
          if (withoutReports)
            results[withoutReports[0].organizationId] = withoutReports;
        }
        /*}*/

        console.log("ORG COMMS", results);

        dispatch({
          type: "GET_COMMITMENTS_FOR_ORGANIZATIONS",
          value: results
        });
      } catch (err) {
        console.error("ERR", err);
      }
    };
  },

  getCommitmentCountForAllTypes: () => {
    return async (dispatch: Action => void) => {
      try {
        let json = [];
        const response = await fetch(
          `/o/commitment2050-service/commitmentapi/getCountForAllTypes`
        );

        if (response.ok) {
          json = await response.json();
        }

        dispatch({
          type: "GET_COMMITMENT_COUNT_FOR_ALL_TYPES",
          value: json[0]
        });
      } catch (err) {
        console.error("ERR", err);
      }
    };
  },

  saveCommitment: (
    data: {
      id: string,
      groupId: number,
      title: string,
      startDate: string,
      endDate: string,
      updated: string,
      created: string,
      innovation: string,
      backgroundInformation: string,
      shortDescription: string,
      operations: Array<any>,
      organizationName: string,
      createdByUserId: string,
      address: string,
      longitude: number,
      latitude: number,
      language: string,
      commitmentType: any,
      reportingInterval: any,
      reportReminder: boolean,
      acceptCriterias: boolean,
      categoryIds: Array<number>,
      commitmentImageUrl: ?string
    },
    sendForInspection: boolean,
    editing?: boolean,
    joinCommitmentId?: string
  ) => {
    return async (dispatch: Action => void, getState: any) => {
      dispatch({
        type: "COMMITMENT_NOTIFICATION",
        value: {
          state: "IN_PROGRESS",
          message: ""
        }
      });

      const userId = getState().user.profile.userId;
      const locale = getState().user.locale;

      const payload = {
        ...data,
        createdByUserId: userId,
        id: data.id || null,
        language: locale,
        groupId: 20143,
        operations: data.operations || {}
      };

      console.log("COMM DATA", payload, data);

      let r;

      try {
        if (
          data.commitmentImageUrl &&
          data.commitmentImageUrl.includes("data:image")
        ) {
          const imageType = data.commitmentImageUrl.split(":")[1].split(";")[0];
          const logoResponse =
            data.commitmentImageUrl &&
            (await fetch("/o/commitment2050-service/commitmentapi/image", {
              method: "POST",
              body: JSON.stringify({
                imageData: data.commitmentImageUrl.split(",")[1],
                fileName: randomstring.generate(16),
                mimeType: imageType
              }),
              headers: {
                Accept: "application/json, text/plain, */*",
                "Content-Type": "application/json",
                "Cache-Control": "no-cache"
              },
              credentials: "include"
            }));

          const v = logoResponse && (await logoResponse.text());

          if (v) {
            payload["commitmentImageUrl"] = v;
          }
        } else {
          payload["commitmentImageUrl"] = data.commitmentImageUrl;
        }
        r = await fetch("/o/commitment2050-service/commitmentapi/commitments", {
          method: "POST",
          body: JSON.stringify(payload),
          headers: {
            Accept: "application/json, text/plain, */*",
            "Content-Type": "application/json",
            "Cache-Control": "no-cache"
          },
          credentials: "include"
        });

        console.log("RESP from save commitment: ", r);

        if (!r.ok) {
          dispatch(
            // $FlowFixMe
            ErrorActions.handleError(Error(r.status + " " + r.statusText))
          );
          dispatch({
            type: "COMMITMENT_NOTIFICATION",
            value: {
              state: "NOT_STARTED",
              message: ""
            }
          });
        }
      } catch (e) {
        // $FlowFixMe
        dispatch(ErrorActions.handleError(e));
        dispatch({
          type: "COMMITMENT_NOTIFICATION",
          value: {
            state: "NOT_STARTED",
            message: ""
          }
        });
      }

      const savedJson = r && (await r.json());

      const isHundredTodos = isHundredTodosCommitment(data);

      if (!isHundredTodos && sendForInspection) {
        try {
          const e = await fetch(
            "/o/commitment2050-service/commitmentapi/sendemailnotify",
            {
              method: "POST",
              body: JSON.stringify(savedJson),
              headers: {
                Accept: "application/json, text/plain, */*",
                "Content-Type": "application/json",
                "Cache-Control": "no-cache"
              },
              credentials: "include"
            }
          );
          console.log(e);
          if (!e.ok) {
            dispatch(
              // $FlowFixMe
              ErrorActions.handleError(Error(e.status + " " + e.statusText))
            );
            dispatch({
              type: "COMMITMENT_NOTIFICATION",
              value: {
                state: "NOT_STARTED",
                message: ""
              }
            });
          }
        } catch (e) {
          // $FlowFixMe
          dispatch(ErrorActions.handleError(e));
          dispatch({
            type: "COMMITMENT_NOTIFICATION",
            value: {
              state: "NOT_STARTED",
              message: ""
            }
          });
        }
      }

      console.log("SAVED", savedJson);

      savedJson &&
        dispatch({ type: "UPDATE_SAVED_COMMITMENT", commitment: savedJson });

      const message = editing
        ? translate({ textKey: "sit.redux.articles.commitmentSaved" })
        : sendForInspection
        ? isHundredTodos
          ? translate({ textKey: "sit.redux.articles.commitmentPublished" })
          : translate({
              textKey: "sit.redux.articles.commitmentSentForVerification"
            })
        : translate({ textKey: "sit.redux.articles.commitmentSavedAsDraft" });

      dispatch({
        type: "COMMITMENT_NOTIFICATION",
        value: {
          state: "SUCCESS",
          message
        }
      });

      // /commitments/jointoparent
      //       parentCommitmentId - parent commitment id (articleId)
      // childCommitmentId - child commitment id (articleId)

      if (savedJson && joinCommitmentId) {
        const formData = new FormData();
        formData.append("parentCommitmentId", joinCommitmentId);
        formData.append("childCommitmentId", savedJson.id);

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
          dispatch(
            // $FlowFixMe
            ErrorActions.handleError(
              Error(joinResponse.status + " " + joinResponse.statusText)
            )
          );
        }
      }

      setTimeout(() => {
        dispatch({
          type: "COMMITMENT_NOTIFICATION",
          value: {
            state: "NOT_STARTED",
            message: ""
          }
        });
      }, 5000);
    };
  },

  resetSavedCommitment: () => {
    return {
      type: "RESET_SAVED_COMMITMENT",
      value: true
    };
  },

  resetSaveReportNotification: () => {
    return async (dispatch: Action => void) => {
      dispatch({
        type: "SAVE_REPORT_NOTIFICATION",
        value: {
          state: "NOT_STARTED",
          message: ""
        }
      });
    };
  },

  saveReport: (
    reportData: ReportType,
    operationIndexInCommitment: number,
    reportIndexInOperation: number
  ) => {
    return async (dispatch: Action => void, getState: any) => {
      dispatch({
        type: "SAVE_REPORT_NOTIFICATION",
        value: {
          state: "IN_PROGRESS",
          message: ""
        }
      });

      const response = await fetch(
        "/o/commitment2050-service/commitmentapi/reports",
        {
          method: "POST",
          body: JSON.stringify({
            ...reportData
          }),
          headers: {
            Accept: "application/json, text/plain, */*",
            "Content-Type": "application/json",
            "Cache-Control": "no-cache"
          },
          credentials: "include"
        }
      );

      if (response.ok) {
        const savedJson = await response.json();

        let commitments = null;

        if (savedJson.organizationName) {
          commitments = [
            ...getState().articles.commitmentsForOrganizations[
              savedJson.organizationName
            ]
          ];
        } else {
          commitments = [...getState().articles.userCommitments];
        }
        const articleIndex = commitments.findIndex(
          commitment => commitment.id === savedJson.commitmentArticleId
        );
        if (articleIndex !== -1) {
          const articleContent = commitments[articleIndex];
          let updatedOperations = [...articleContent.operations];

          if (reportIndexInOperation !== undefined) {
            updatedOperations[operationIndexInCommitment].reports[
              reportIndexInOperation
            ] = savedJson;
          } else {
            const reports =
              updatedOperations[operationIndexInCommitment].reports;
            updatedOperations[operationIndexInCommitment] = {
              ...updatedOperations[operationIndexInCommitment],
              reports: [...(reports ? reports : []), savedJson]
            };
          }
          const updatedArticleContent = {
            ...articleContent,
            operations: updatedOperations
          };
          if (savedJson.organizationName) {
            let updatedOrganizationCommitments = {
              ...getState().articles.commitmentsForOrganizations
            };

            updatedOrganizationCommitments[savedJson.organizationName][
              articleIndex
            ] = updatedArticleContent;
            dispatch({
              type: "GET_COMMITMENTS_FOR_ORGANIZATIONS",
              value: updatedOrganizationCommitments
            });
          } else {
            commitments[articleIndex] = updatedArticleContent;
            console.log(commitments, updatedArticleContent);
            dispatch({
              type: "UPDATE_SAVED_REPORT_TO_USER_COMMITMENTS",
              value: commitments
            });
          }
        }
        dispatch({
          type: "SAVE_REPORT_NOTIFICATION",
          value: {
            state: "SUCCESS",
            message: translate({ textKey: "sit.redux.articles.reportSaved" })
          }
        });
      } else {
        dispatch({
          type: "SAVE_REPORT_NOTIFICATION",
          value: {
            state: "FAILURE",
            message: translate({
              textKey: "sit.redux.articles.reportSavingFailed"
            })
          }
        });
      }

      setTimeout(() => {
        dispatch({
          type: "SAVE_REPORT_NOTIFICATION",
          value: {
            state: "NOT_STARTED",
            message: ""
          }
        });
      }, 5000);

      console.log("SAVE RESPOSE", response);
    };
  },

  addLikeToCommitment: (articleId: string) => {
    return async (dispatch: Action => void, getState: any) => {
      try {
        const response = await fetch(
          `/o/commitment2050-service/commitmentapi/likecommitment/${articleId}`,
          {
            method: "POST",

            headers: {
              Accept: "application/json, text/plain, */*",
              "Content-Type": "application/json",
              "Cache-Control": "no-cache"
            }
          }
        );

        if (response.ok) {
          const newLikeAmount = await response.json();
          if (getState().articles.articleContent.id === articleId) {
            dispatch({
              type: "UPDATE_LIKES_COUNT",
              value: {
                likes: newLikeAmount,
                articleId: articleId
              }
            });
          }
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
  getAcceptedMeterChartTypes: () => {
    return async (dispatch: Action => void) => {
      try {
        const r = await fetch(
          `/o/commitment2050-service/commitmentapi/commitments/charts`
        );

        if (r.ok) {
          const json = await r.json();

          dispatch({
            type: "GET_ACCEPTED_METER_CHART_TYPES",
            value: json
          });
        }
      } catch (err) {
        console.error("E", err);
        dispatch(
          // $FlowFixMe
          ErrorActions.handleError(Error(err))
        );
      }
    };
  }
};

export default function reducer(
  state: ARTICLES_STATE = {
    articles: [],
    mainCategories: [],
    secondaryCategories: [],
    articleContent: null,
    acceptedMeterChartTypes: [],
    operations: [],
    joinedToArticle: [],
    mainCategoryProperties: {},
    secondaryCategoryProperties: {},
    userCommitments: [],
    commitmentsForOrganizations: {},
    commitmentCountForAllTypes: [],
    savedCommitment: null,
    commitmentNotification: {
      state: "NOT_STARTED",
      message: ""
    },
    savedReport: null,
    saveReportNotification: {
      state: "NOT_STARTED",
      message: ""
    },
    commitmentsByOrganizationName: {},
    commitmentsByOrganizationId: {},
    hundredSmartWaysArticles: [],
    fetchedCategoryHierarchy: {},
    commitmentsPerCity: {}
  },
  action: Action
): ARTICLES_STATE {
  switch (action.type) {
    case "GET_ARTICLES": {
      return {
        ...state,
        articles: action.value
      };
    }
    case "GET_MAIN_CATEGORIES": {
      return {
        ...state,
        mainCategories: action.value.mainCategoriesWithTranslations,
        mainCategoriesSecondaryObjectives:
          action.value.mainCategoriesSecondaryObjectivesWithTranslations
      };
    }

    case "GET_COMMITMENTS_PER_CITY": {
      return {
        ...state,
        commitmentsPerCity: action.value
      };
    }

    case "GET_ARTICLE_CONTENT": {
      return {
        ...state,
        articleContent: action.value
      };
    }

    case "GET_JOINED_TO_ARTICLE": {
      return {
        ...state,
        joinedToArticle: action.value
      };
    }

    case "GET_MAIN_CATEGORY_PROPS": {
      return {
        ...state,
        mainCategoryProperties: action.value
      };
    }
    case "GET_USER_COMMITMENTS": {
      return {
        ...state,
        userCommitments: action.value
      };
    }
    case "GET_SECONDARY_CATEGORY_PROPERTIES": {
      return {
        ...state,
        secondaryCategoryProperties: action.value
      };
    }
    case "GET_SECONDARY_CATEGORIES": {
      return {
        ...state,
        secondaryCategories: action.value
      };
    }
    case "GET_ACCEPTED_METER_CHART_TYPES": {
      return {
        ...state,
        acceptedMeterChartTypes: action.value
      };
    }
    case "UPDATE_SAVED_COMMITMENT": {
      return {
        ...state,
        savedCommitment: action.commitment
      };
    }
    case "GET_COMMITMENTS_FOR_ORGANIZATIONS": {
      return {
        ...state,
        commitmentsForOrganizations: action.value
      };
    }
    case "GET_COMMITMENT_COUNT_FOR_ALL_TYPES": {
      return {
        ...state,
        commitmentCountForAllTypes: action.value
      };
    }
    case "RESET_SAVED_COMMITMENT": {
      return {
        ...state,
        savedCommitment: null
      };
    }
    case "COMMITMENT_NOTIFICATION": {
      return {
        ...state,
        commitmentNotification: action.value
      };
    }
    case "SAVE_REPORT": {
      return {
        ...state
      };
    }
    case "UPDATE_SAVED_REPORT_TO_USER_COMMITMENTS": {
      return {
        ...state,
        userCommitments: action.value
      };
    }
    case "SAVE_REPORT_NOTIFICATION": {
      return {
        ...state,
        saveReportNotification: action.value
      };
    }
    case "UPDATE_LIKES_COUNT": {
      return {
        ...state,
        articleContent: {
          ...state.articleContent,
          likes: action.value.likes
        }
      };
    }
    case "GET_COMMITMENTS_BY_ORGANIZATION_NAME": {
      return {
        ...state,
        commitmentsByOrganizationName: {
          [action.value.name]: action.value.commitments
        }
      };
    }
    case "GET_COMMITMENTS_BY_ORGANIZATION_ID": {
      return {
        ...state,
        commitmentsByOrganizationId: {
          [action.value.id]: action.value.commitments
        }
      };
    }
    case "GET_100SMARTWAYS_ARTICLES": {
      return {
        ...state,
        hundredSmartWaysArticles: action.value
      };
    }
    case "GET_CATEGORIES_IN_HIERARCHY": {
      return {
        ...state,
        fetchedCategoryHierarchy: action.value
      };
    }
    default: {
      unexpectedCase(action.value);
      return state;
    }
  }
}
