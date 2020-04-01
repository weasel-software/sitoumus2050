// @flow

import type {
  CommitmentType,
  ORGANIZATION_TYPE,
  PROFILE_TYPE,
  CATEGORY_TYPE,
  SMARTWAY_ARTICLE_TYPE,
  NotificationType,
  MeterType,
  OperationType,
  CityType,
  HundredTodosType
} from "../../../constants/types";

import { get, merge, toArray, flatMap } from "lodash";
import React, { Component } from "react";
import produce from "immer";
import memoize from "memoize-one";
import { withRouter } from "react-router-dom";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";

import CommitmentCreationForm from "./CommitmentCreationForm";
import { ArticleActions } from "../../../redux/articles";
import LiferayClient from "../../../utils/LiferayClient";
import qs from "../../../vendor/query-string";
import Goals2050Agenda2030Map from "../../../utils/Goals2050Agenda2030Map";
import mapCO2toCategoryId from "../../../utils/mapCO2toCategoryId";
import {
  isHundredTodosCommitment,
  primaryGoalId
} from "../../../utils/hundredTodos";
import GreenDealCategorySelection from "./GreenDealCategorySelection";

class CommitmentCreationFormContainer extends Component<
  {
    hundredSmartWaysArticles: Array<SMARTWAY_ARTICLE_TYPE>,
    organizations: Array<ORGANIZATION_TYPE>,
    commitment: CommitmentType,
    location: *,
    saveCommitment: *,
    locale: "fi_FI" | "en_US", // | "sv_SE",
    profile: PROFILE_TYPE,
    readyToRender: boolean,
    secondaryCategoryProperties: Array<CATEGORY_TYPE & { value: string }>,
    mainCategoryProperties: Array<CATEGORY_TYPE & { value: string }>,
    mainCategoriesSecondaryObjectives: Array<CATEGORY_TYPE>,
    mainCategories: Array<CATEGORY_TYPE>,
    savedCommitment?: CommitmentType,
    resetSavedCommitment: () => void,
    commitments: Array<CommitmentType>,
    secondaryCategories: Array<CATEGORY_TYPE>,
    editing?: boolean,
    commitmentNotification: NotificationType,
    acceptedMeterChartTypes: Array<string>,
    resetNotification: () => void,
    cities: Array<CityType>,
    hundredTodos: HundredTodosType
  },
  {
    infoTexts: Object,
    operationsFolderId: number,
    greendealSubCategoryId: number,
    greenDealMainGoal: number,
    greenDealCategorySelected: boolean,
    readyToRender: boolean,
    newOpMeters: Array<MeterType>,
    plasticBagMeters: Array<MeterType>,
    nutritionMeters: Array<MeterType>,
    selectedCreator: ?PROFILE_TYPE | ?ORGANIZATION_TYPE,
    generalActions: Array<OperationType>,
    schoolActions: Array<OperationType>,
    greenDealActions: Array<OperationType>,
    meters: Array<MeterType>,
    commitmentData: CommitmentType,
    errors: {
      [key: string]: string | boolean
    },
    exampleCommitments: Array<CommitmentType>,
    contentAreas: Array<CATEGORY_TYPE>,
    showHundredTodos: boolean
  }
> {
  constructor(props) {
    super(props);
    const commitmentTypeId = this.props.hundredTodos
      ? this.props.hundredTodos.commitmentTypeId
      : get(this.props, "match.params.commitmentType");

    const commitmentTypeName = this.getCommitmentTypeName(commitmentTypeId);

    let categoryIds;

    if (this.props.hundredTodos) {
      categoryIds = this.getHundredTodosCategoryIds(this.props);
    } else {
      categoryIds = commitmentTypeId ? [Number(commitmentTypeId)] : [];
    }

    this.state = {
      infoTexts: {},
      operationsFolderId: 0,
      greendealSubCategoryId: 0,
      //defaults to "Hiilineutraali yhteiskunta" by id
      greenDealMainGoal: 31803,
      greenDealCategorySelected: false,
      exampleCommitments: [],
      plasticBagMeters: [],
      contentAreas: [],
      readyToRender: false,
      errors: {},
      newOpMeters: [],
      selectedCreator: null,
      generalActions: [],
      schoolActions: [],
      greenDealActions: [],
      mainCategory: null,
      meters: [],
      nutritionMeters: [],
      showHundredTodos: false,
      commitmentData: this.getCommitmentFromProps(this.props.commitment) || {
        id: "",
        groupId: null,
        title_fi_FI: this.props.hundredTodos
          ? this.props.hundredTodos.title_fi_FI
          : "",
        title_sv_SE: this.props.hundredTodos
          ? this.props.hundredTodos.title_sv_SE
          : "",
        title_en_US: this.props.hundredTodos
          ? this.props.hundredTodos.title_en_US
          : "",
        startDate: this.props.hundredTodos
          ? this.props.hundredTodos.startDate
          : "",
        endDate: this.props.hundredTodos ? this.props.hundredTodos.endDate : "",
        innovation_fi_FI: "",
        innovation_sv_SE: "",
        innovation_en_US: "",
        backgroundInformation_fi_FI: this.props.hundredTodos
          ? this.props.hundredTodos.backgroundInformation_fi_FI
          : "",
        backgroundInformation_sv_SE: this.props.hundredTodos
          ? this.props.hundredTodos.backgroundInformation_sv_SE
          : "",
        backgroundInformation_en_US: this.props.hundredTodos
          ? this.props.hundredTodos.backgroundInformation_en_US
          : "",
        shortDescription_fi_FI: "",
        shortDescription_sv_SE: "",
        shortDescription_en_US: "",
        operations: [],
        organizationName: "",
        createdByUserId: "",
        address: "",
        longitude: null,
        latitude: null,
        language: "",
        reportingInterval:
          commitmentTypeName === "GREEN_DEAL" ? "CUSTOM" : "MONTH_1",
        reportReminder: false,
        acceptCriterias: false,
        categoryIds,
        commitmentType: commitmentTypeName,
        status: "draft"
      }
    };
  }

  getHundredTodosCategoryIds = props => {
    return [
      props.hundredTodos.commitmentTypeId,
      primaryGoalId,
      140111, // 100 fiksua tekoa
      mapCO2toCategoryId(props.hundredTodos.calculations.co2e)
    ].concat(Goals2050Agenda2030Map["hundredTodos"]);
  };

  getCommitmentTypeName = commitmentTypeId => {
    switch (commitmentTypeId) {
      case "33553": {
        return "COMMITMENT";
      }
      case "33554": {
        return "GREEN_DEAL";
      }
      case "33555": {
        return "NUTRITION";
      }
      default:
        return "COMMITMENT"; // commitments created after the lifestyle test default to COMMITMENT
    }
  };

  getCommitmentFromProps = memoize((commitment: CommitmentType) => {
    this.setState(
      // $FlowFixMe
      produce(draft => {
        draft.commitmentData = commitment;
      }),
      () => {}
    );

    return commitment;
  });

  componentWillReceiveProps(newprops) {
    if (newprops.hundredTodos !== this.props.hundredTodos) {
      this.setState(
        // $FlowFixMe
        produce(draft => {
          draft.showHundredTodos =
            newprops.hundredTodos ||
            (newprops.commitment
              ? isHundredTodosCommitment(newprops.commitment)
              : false);
          draft.commitmentData.title_fi_FI = newprops.hundredTodos.title_fi_FI;
          draft.commitmentData.title_sv_SE = newprops.hundredTodos.title_sv_SE;
          draft.commitmentData.title_en_US = newprops.hundredTodos.title_en_US;
          draft.commitmentData.operations = [
            this.createOperation(newprops.hundredTodos)
          ];
          draft.commitmentData.doneOperations = newprops.hundredTodos.tipsDone
            ? newprops.hundredTodos.tipsDone.map(el => {
                return {
                  operationId: el.id ? el.id : ""
                };
              })
            : [];
          draft.commitmentData.backgroundInformation_fi_FI =
            newprops.hundredTodos.backgroundInformation_fi_FI;
          draft.commitmentData.backgroundInformation_en_US =
            newprops.hundredTodos.backgroundInformation_en_US;
          draft.commitmentData.backgroundInformation_sv_SE =
            newprops.hundredTodos.backgroundInformation_sv_SE;
          draft.commitmentData.startDate = newprops.hundredTodos.startDate;
          draft.commitmentData.endDate = newprops.hundredTodos.endDate;
          draft.commitmentData.categoryIds = this.getHundredTodosCategoryIds(
            newprops
          );
        })
      );
    }

    if (newprops.savedCommitment) {
      this.getCommitmentFromProps(newprops.savedCommitment);
      this.props.resetSavedCommitment();
    }
  }

  createOperation = (hundredTodos: HundredTodosType) => {
    const toDoPercentageSum = hundredTodos.tipsTodo
      ? 100 *
        hundredTodos.tipsTodo.reduce((sum, todo) => sum + todo.decrease, 0)
      : 0;

    const toDoPercentageSumString = toDoPercentageSum.toFixed(1);

    const meters = this.parseMetersFromTestPayload(
      hundredTodos.tipsTodo.concat(hundredTodos.tipsDone)
    );

    const operation = {
      operationId: "",
      articleId: "",
      id: "",
      meters: meters,
      operationDescription_fi_FI: "",
      operationDescription_sv_SE: "",
      operationDescription_en_US: "",
      key: String(Math.random()),
      reports: [],
      operationTitle_en_US: `With these deeds I can achieve ${toDoPercentageSumString}% better coco`,
      operationTitle_fi_FI: `Näillä teoilla saavutan ${toDoPercentageSumString}% pienemmän hiilijalanjäljen`,
      operationTitle_sv_SE: `Med dessa åtgärder får jag ett ${toDoPercentageSumString} % lägre koldioxidavtryck`
    };

    return operation;
  };

  //these meters are parsed from the payload coming from the lifestyle test.
  //targetValues are different for meters parsed from the test payload compared
  //to those in article-data, hence meters are parsed in two different functions.
  parseMetersFromTestPayload = unparsedMeters =>
    unparsedMeters &&
    unparsedMeters.map(meter => {
      const article =
        this.props.hundredSmartWaysArticles &&
        this.props.hundredSmartWaysArticles.find(
          el => el.articleId === meter.id
        );
      return {
        meterId: meter.id,
        meterType_fi_FI: article ? article.title.fi_FI : "",
        meterType_sv_SE: article ? article.title.sv_SE : "",
        meterType_en_US: article ? article.title.en_US : "",
        meterValueType: "NUMBER",
        startingLevel: 0,
        targetLevel: meter.decrease * 100,
        meterChartType: "LINE",
        meterCategory: article ? article.category : ""
      };
    });

  //should be made dynamic for greendeal subtypes. Add new classification under Hallintapaneeli->Sivustot->Koko portaali->Luokittelut->Sitoumustyyppi.
  //add a new info-text field to each commitment type with the folderID of the info-texts folder that contains the info texts for that commitment type.
  //Then refactor this to get that field and use it instead of hard-coding.
  getInfoText = async () => {
    let infoText = {};
    if (this.state.commitmentData.commitmentType === "GREEN_DEAL") {
      infoText = await LiferayClient("/journal.journalarticle/get-articles", {
        groupId: 20143,
        folderId: 40866
      });
    } else {
      infoText = await LiferayClient("/journal.journalarticle/get-articles", {
        groupId: 20143,
        folderId: 40862
      });
    }

    let res = {};

    for (const i of infoText) {
      const finnishInfo = await LiferayClient(
        "/journal.journalarticle/get-article-content",
        {
          groupId: 20143,
          articleId: i.articleId,
          languageId: "fi_FI",
          portletRequestModel: null,
          themeDisplay: null
        }
      );
      const englishInfo = await LiferayClient(
        "/journal.journalarticle/get-article-content",
        {
          groupId: 20143,
          articleId: i.articleId,
          languageId: "en_US",
          portletRequestModel: null,
          themeDisplay: null
        }
      );
      const swedishInfo = await LiferayClient(
        "/journal.journalarticle/get-article-content",
        {
          groupId: 20143,
          articleId: i.articleId,
          languageId: "sv_SE",
          portletRequestModel: null,
          themeDisplay: null
        }
      );

      if (finnishInfo) {
        let defaultJson = null;
        if (this.props.locale === "fi_FI") {
          defaultJson = JSON.parse(finnishInfo);
        } else if (englishInfo && this.props.locale === "en_US") {
          defaultJson = JSON.parse(englishInfo);
        } else if (swedishInfo && this.props.locale === "sv_SE") {
          defaultJson = JSON.parse(swedishInfo);
        }
        const finnishJson = JSON.parse(finnishInfo);
        const englishJson = englishInfo && JSON.parse(englishInfo);
        const swedishJson = swedishInfo && JSON.parse(swedishInfo);
        const json = {
          ...defaultJson,
          info_fi_FI: finnishJson.info,
          placeholder_fi_FI: finnishJson.placeholder,
          info_en_US: englishJson ? englishJson.info : "",
          placeholder_en_US: englishJson ? englishJson.placeholder : "",
          info_sv_SE: swedishJson ? swedishJson.info : "",
          placeholder_sv_SE: swedishJson ? swedishJson.placeholder : ""
        };
        res[i.articleId] = json;
      }
    }

    this.setState(
      // $FlowFixMe
      produce(draft => {
        draft.infoTexts = res;
      })
    );
  };

  getNewOpMeters = async () => {
    const newOpMetersResponse = await fetch(
      "/o/commitment2050-service/commitmentapi/templates/meters/folder/45897"
    );

    const json = await newOpMetersResponse.json();

    this.setState(
      // $FlowFixMe
      produce(draft => {
        draft.newOpMeters = json.data;
      })
    );
  };

  getOrganizationById = (_id, organizations = this.props.organizations) => {
    const query = this.props.location && qs.parse(this.props.location.search);
    const id = _id || (query && query.creatorOrganizationId);
    const foundOrg =
      organizations &&
      organizations.find(org => Number(org.organizationId) === Number(id));
    return foundOrg;
  };

  getCommitmentData = () => {
    if (this.props.commitment)
      return merge(this.state.commitmentData, this.props.commitment);

    const query = this.props.location && qs.parse(this.props.location.search);
    const id = query && query.commitmentId;
    const queryCommitment =
      query && query.commitmentData && JSON.parse(query.commitmentData);

    if (queryCommitment) {
      return merge(this.state.commitmentData, queryCommitment);
    }

    const commitment = this.props.commitments.find(
      commit => Number(commit.id) === Number(id)
    );
    if (commitment) return merge(this.state.commitmentData, commitment);
  };

  componentWillMount() {
    this.addCategoryIds();
  }

  getGreenDealOperationsAndMeters = async () => {
    //const greendealAutomotiveIndustryOperationsResponse = await fetch(
    //  "/o/commitment2050-service/commitmentapi/templates/operations/folder/237439"
    //);
    const greenDealActionsResponse = await fetch(
      `/o/commitment2050-service/commitmentapi/templates/operations/folder/${this.state.operationsFolderId}`
    );
    const greenDealActionsJson =
      greenDealActionsResponse.ok && (await greenDealActionsResponse.json());

    const plasticBagMetersTemplateJson = await fetch(
      "/o/commitment2050-service/commitmentapi/templates/meters/greendeal"
    );
    const plasticBagMetersJson = await plasticBagMetersTemplateJson.json();

    const generateKeys = data =>
      data.map(d => {
        return {
          key: String(Math.random()),
          ...d
        };
      });

    this.setState(
      // $FlowFixMe
      produce(draft => {
        draft.plasticBagMeters = generateKeys(plasticBagMetersJson.data);
        draft.greenDealActions =
          greenDealActionsJson && generateKeys(greenDealActionsJson.data);
      })
    );
  };

  componentDidMount = async () => {
    this.props.resetNotification();

    const generalOperationsResponse = await fetch(
      "/o/commitment2050-service/commitmentapi/templates/operations/common"
    );

    const generalOperationsJson =
      generalOperationsResponse.ok && (await generalOperationsResponse.json());

    const schoolOperationsResponse = await fetch(
      "/o/commitment2050-service/commitmentapi/templates/operations/educational"
    );

    const schoolOperationsJson =
      schoolOperationsResponse.ok && (await schoolOperationsResponse.json());

    this.getInfoText();
    this.getNewOpMeters();

    const generateKeys = data =>
      data.map(d => {
        return {
          key: String(Math.random()),
          ...d
        };
      });

    const commonMetersResponse = await fetch(
      "/o/commitment2050-service/commitmentapi/templates/meters/common"
    );
    const commonMetersTemplateJson = await commonMetersResponse.json();

    const nutritionMetersTemplateResponse = await fetch(
      `/o/commitment2050-service/commitmentapi/templates/meters/folder/59400`
    );
    const nutritionMetersTemplateJson = await nutritionMetersTemplateResponse.json();

    let contentAreas = [];

    if (this.state.commitmentData.commitmentType === "NUTRITION") {
      // Ravitsemussitoumus id -> parentCategoryId: 33555,
      //   vocabularyId: 33552
      //HAE KATEGORIAN ALIGATEGORIAT
      const contentAreasResponse = await LiferayClient(
        "/assetcategory/get-vocabulary-categories",
        {
          parentCategoryId: 33555,
          vocabularyId: 33552,
          start: 0,
          end: 1000,
          obc: null
        }
      );
      if (contentAreasResponse) contentAreas.push(...contentAreasResponse);
    }

    this.state.commitmentData.commitmentType === "GREEN_DEAL" &&
      this.getExampleCommitments(33556);

    const organization = this.getOrganizationById();

    const creator = this.props.profile; //organization || this.props.profile; // Currently organizations don't have their own address field. Leaving here for later extension.
    const address = get(creator, "addresses[0]");

    const country =
      address && address.countryId !== "0"
        ? await LiferayClient("/country/get-country", {
            countryId: Number(address.countryId)
          })
        : await LiferayClient("/country/get-country", {
            countryId: 88
          });
    const commitment = this.getCommitmentData() || this.state.commitmentData;

    this.setState(
      // $FlowFixMe
      produce(draft => {
        draft.commitmentData = commitment;
        draft.generalActions =
          generalOperationsJson && generateKeys(generalOperationsJson.data);
        draft.schoolActions =
          schoolOperationsJson && generateKeys(schoolOperationsJson.data);
        draft.meters = generateKeys(commonMetersTemplateJson.data);
        draft.showOperationCreationComponent = false;
        draft.commitmentData.organizationName = organization
          ? organization.name
          : this.state.commitmentData.organizationName || "";

        draft.commitmentData.address =
          commitment.address !== ""
            ? commitment.address
            : address
            ? `${address.city},${country.nameCurrentValue}`
            : `Helsinki,Suomi`;
        const cityObject = this.props.cities.find(city =>
          commitment.address !== ""
            ? city.name_fi_FI === commitment.address.split(",")[0]
            : address
            ? city.name_fi_FI === address.city
            : city.name_fi_FI === "Helsinki"
        );
        if (cityObject) {
          draft.commitmentData.longitude = cityObject.longitude;
          draft.commitmentData.latitude = cityObject.latitude;
        }
        draft.readyToRender = true;
        draft.contentAreas = contentAreas;
        draft.nutritionMeters = generateKeys(nutritionMetersTemplateJson.data);
        draft.commitmentData.organizationLogo =
          this.state.commitmentData.organizationLogo || organization
            ? organization && organization.logo
            : "";
        draft.showHundredTodos =
          this.props.hundredTodos ||
          (commitment ? isHundredTodosCommitment(commitment) : false);
      })
    );
  };

  addCategoryIds = () => {
    //commitment type categoryID is added in the constructor.
    let categoryIdsWithNew = [];
    const preExistingCategoryIds = this.state.commitmentData.categoryIds;
    const { commitmentType } = this.state.commitmentData;
    if (commitmentType === "NUTRITION") {
      categoryIdsWithNew = [...preExistingCategoryIds, 31810, 31820, 31821];
    } else if (
      commitmentType === "GREEN_DEAL" &&
      this.state.greendealSubCategoryId === 33556
    ) {
      //special treatmet for plastic bag subtype
      categoryIdsWithNew = [...preExistingCategoryIds, 33556];
    } else if (
      commitmentType === "GREEN_DEAL" &&
      this.state.greendealSubCategoryId !== 33556 &&
      this.state.greendealSubCategoryId !== 0
    ) {
      //any other greendeal subtype
      const agenda2030goals =
        Goals2050Agenda2030Map[this.state.greenDealMainGoal];

      categoryIdsWithNew = [
        ...preExistingCategoryIds,
        this.state.greendealSubCategoryId,
        this.state.greenDealMainGoal,
        ...agenda2030goals
      ];
    } else {
      categoryIdsWithNew = preExistingCategoryIds;
    }
    this.setState(
      // $FlowFixMe
      produce(draft => {
        draft.commitmentData.categoryIds = categoryIdsWithNew;
      })
    );
  };

  //saves the main goal, folder id of the folder containing the operations of this deal and the group ID of the deal itself.
  setGreenDealMetaData = (goal, operationsFolderId, subCategoryId) => {
    this.setState(
      // $FlowFixMe
      produce(draft => {
        draft.greenDealMainGoal = goal;
        draft.operationsFolderId = operationsFolderId;
        draft.greendealSubCategoryId = subCategoryId;
      }),
      () => {
        this.addCategoryIds();
        this.getGreenDealOperationsAndMeters();
      }
    );
  };

  getExampleCommitments = async categoryId => {
    const exampleResponse = await fetch(
      `/o/commitment2050-service/commitmentapi/examplecommitments?categoryid=${categoryId}`
    );

    const exampleJson = await exampleResponse.json();

    if (exampleJson)
      this.setState(
        // $FlowFixMe
        produce(draft => {
          draft.exampleCommitments = exampleJson.data;
        })
      );
  };

  //if the user selects greendeal, show greendeal stuff and then allow normal UI flow for commitment creation
  greenDealCategorySelection = isSelected => {
    this.setState(
      // $FlowFixMe
      produce(draft => {
        draft.greenDealCategorySelected = isSelected;
      })
    );
  };

  render() {
    return this.state.commitmentData.commitmentType === "GREEN_DEAL" &&
      this.state.greenDealCategorySelected === false ? (
      <GreenDealCategorySelection
        setGreendealCategorySelected={this.greenDealCategorySelection}
        setGreenDealMetaData={this.setGreenDealMetaData}
        {...this.state}
        {...this.props}
      />
    ) : (
      <CommitmentCreationForm
        {...this.state}
        infoTexts={this.state.infoTexts}
        greenDealActions={this.state.greenDealActions}
        // $FlowFixMe
        getExampleCommitments={this.getExampleCommitments}
        {...this.props}
      />
    );
  }
}

export default withRouter(
  connect(
    state => ({
      mainCategories: toArray(state.articles.mainCategories),
      hundredSmartWaysArticles: state.articles.hundredSmartWaysArticles.sort(
        (a, b) => {
          const titleA = a.title && a.title.fi_FI ? a.title.fi_FI : "";
          const titleB = b.title && b.title.fi_FI ? b.title.fi_FI : "";
          return titleA.localeCompare(titleB, "fi");
        }
      ),
      mainCategoriesSecondaryObjectives: toArray(
        state.articles.mainCategoriesSecondaryObjectives
      ),
      secondaryCategories: toArray(state.articles.secondaryCategories),
      mainCategoryProperties: flatMap(
        state.articles.mainCategoryProperties,
        category => category
      ),
      secondaryCategoryProperties: flatMap(
        state.articles.secondaryCategoryProperties,
        category => category
      ),
      acceptedMeterChartTypes: state.articles.acceptedMeterChartTypes,
      profile: state.user.profile,
      locale: state.user.locale,
      cities: state.user.cities,
      savedCommitment: state.articles.savedCommitment,
      commitments: [
        ...state.articles.userCommitments,
        ...flatMap(state.articles.commitmentsForOrganizations, c => c)
      ],
      organizations: state.organizations.organizations,
      commitmentNotification: state.articles.commitmentNotification
    }),
    (dispatch: any) => {
      const resetNotification = {
        type: "COMMITMENT_NOTIFICATION",
        value: {
          state: "NOT_STARTED",
          message: ""
        }
      };
      return {
        ...bindActionCreators(
          {
            saveCommitment: ArticleActions.saveCommitment,
            resetSavedCommitment: ArticleActions.resetSavedCommitment,
            resetNotification: () => dispatch(resetNotification)
          },
          dispatch
        )
      };
    }
  )(CommitmentCreationFormContainer)
);
