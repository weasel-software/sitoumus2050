// @flow

import type {
  PROFILE_TYPE,
  ORGANIZATION_TYPE,
  MeterType,
  CommitmentType,
  CATEGORY_TYPE,
  OperationType,
  DoneOperationType,
  NotificationType,
  LocaleType,
  CityType,
  SMARTWAY_ARTICLE_TYPE
} from "../../../constants/types";

import React, { Fragment, Component } from "react";
import { Link, Redirect } from "react-router-dom";
import styled from "styled-components";
import DatePicker from "react-datepicker";
import moment from "moment";
import { get } from "lodash";
import produce from "immer";
import scrollIntoView from "scroll-into-view-if-needed";
import ReactGA from "react-ga";
import ReactPixel from "react-facebook-pixel";

import generateNewOperation from "../../../utils/generateNewOperation";
import qs from "../../../vendor/query-string";
import Goals2050Agenda2030Map from "../../../utils/Goals2050Agenda2030Map";
import getLocalizedString from "../../../utils/getLocalizedString";
import {
  isHundredTodosCommitment,
  primaryGoalId
} from "../../../utils/hundredTodos";
import mapOptionalGoalCategoryIdToPrimaryGoalCategoryId from "../../../utils/mapOptionalGoalCategoryIdToPrimaryGoalCategoryId";

import Button, { SecondaryButton } from "../../reusable/Button";
import InputIconContainer from "../../reusable/InputIconContainer";
import AutoComplete from "../../reusable/AutoComplete";
import CommitmentPic from "../../reusable/CommitmentPic";
import { RadioButton, RadioButtonContainer } from "../../reusable/Radio";
import WhiteSpace from "../../reusable/WhiteSpace";
import TextInput from "../../reusable/TextInput";
import GoalIcon from "../../reusable/GoalIcon";
import Checkbox from "../../reusable/Checkbox";
import AgendaIcon from "../../reusable/AgendaIcon";
import Dropdown from "../../reusable/Dropdown";
import RoundButtonWithIcon from "../../reusable/RoundButtonWithIcon";
import StyledSubtitle from "../../reusable/StyledSubtitle";
import InfoIcon from "../../reusable/InfoIcon";
import Row from "../../reusable/Row";
import NotificationContainer from "../../reusable/NotificationContainer";
import SomeShareContainer from "../../reusable/SomeShareContainer";

import ScrollTo from "../../reusable/ScrollTo";
import translate from "../../reusable/translate";

import OperationEditor from "./OperationEditor";
import HundredTodosContainer from "./HundredTodos/HundredTodosContainer";
import ReportingIntervalSelection from "./ReportingIntervalSelection";

const StyledTitle = styled.h3`
  font-size: 16px;
  font-weight: bold;
  text-transform: uppercase;
  margin-top: 0;
`;

const LanguageContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  flex: 1;
  flex-direction: column;
`;

const LanguageTitle = styled(StyledTitle)`
  cursor: pointer;
  color: ${props => props.selected && "#93BE38;"};
  margin-bottom: 0;
`;

const ErrorText = styled.span`
  color: red;
`;

const SpaceBetween = styled.div`
  display: flex;
  justify-content: space-between;
  flex-direction: row;
  border-bottom: 1px solid #dcdcdc;
  padding: 10px;
`;

const DatePickerOpen = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-direction: row;
  flex: 1;
  padding: 4px;
  border: 1px solid #eee;
`;

const CenterVertically = styled.div`
  display: flex;
  align-items: center;
  height: ${props => props.height || "60px"};
  flex: 1 1 auto;
`;

const Column = styled.div`
  display: flex;
  flex-direction: column;
  align-items: ${props => props.alignItems || "unset"};
`;

const StyledH2 = styled.h2`
  font-weight: bold;
  font-size: 20px;
  margin: 0;
`;

const Label = styled.span`
  padding-right: 8px;
  cursor: default;
  display: flex;
  flex: 1 0 auto;
  font-weight: bold;
`;

const Textarea = styled.textarea`
  outline: none;
  width: 100%;
  padding: 8px;
  border-radius: 4px;

  &:focus {
    border: ${props => (props.error ? "2px solid red" : "2px solid #93be38")};
  }

  &::placeholder {
    font-style: italic;
    font-size: 18px;
    line-height: 26px;
    color: #6b6b6b;
  }
  border: ${props => (props.error ? "2px solid red" : "2px solid transparent")};
  border-bottom: ${props => (props.error ? "2px solid red" : "1px solid #eee")};
`;

const BootstrapColumnContainer = styled.div`
  padding: 20px;
`;

const StyledCheckbox = styled(Checkbox)`
  padding: 8px;
  &:hover {
    background: #e8e8e8;
  }
`;

const VerticalSeparatorLine = styled.span`
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0 10px 0 10px;
`;

const VerticalSeparator = () => (
  <VerticalSeparatorLine>|</VerticalSeparatorLine>
);

const DatePickerContainer = styled(Row)`
  cursor: pointer;
  padding: 6px;
  border-radius: 4px;
  border: ${props => (props.error ? "2px solid red" : "2px solid transparent")};
  &:hover {
    border: ${props =>
      props.error ? "2px solid red" : !props.disabled && "2px dashed #93be38"};
  }
  &:active {
    border: ${props =>
      props.error ? "2px solid red" : !props.disabled && "2px dashed #93be38"};
  }
  &:focus {
    border: ${props =>
      props.error ? "2px solid red" : !props.disabled && "2px dashed #93be38"};
  }

  > div {
    width: 100%;
    ${"" /* react-datepicker style haxfix */};
  }
`;

const sortCities = (cities, locale): Array<CityType> => {
  const getCityDisplayName = city => getLocalizedString("name", city, locale);
  const arr = cities ? [].concat(cities) : [];
  arr.sort((a, b) =>
    getCityDisplayName(a).localeCompare(getCityDisplayName(b), "fi")
  );
  return arr;
};

class CommitmentCreationForm extends Component<
  {
    greenDealMainGoal: number | null,
    organizations: Array<ORGANIZATION_TYPE>,
    location: *,
    saveCommitment: *,
    locale: "fi_FI" | "en_US", // | "sv_SE",
    profile: PROFILE_TYPE,
    readyToRender: boolean,
    secondaryCategoryProperties: Array<CATEGORY_TYPE & { value: string }>,
    mainCategoryProperties: Array<CATEGORY_TYPE & { value: string }>,
    mainCategoriesSecondaryObjectives: Array<CATEGORY_TYPE>,
    mainCategories: Array<CATEGORY_TYPE>,
    hundredSmartWaysArticles: Array<SMARTWAY_ARTICLE_TYPE>,
    infoTexts: Object,
    savedCommitment?: CommitmentType,
    resetSavedCommitment: () => void,
    secondaryCategories: Array<CATEGORY_TYPE>,
    editing?: boolean,
    commitmentNotification: NotificationType,
    acceptedMeterChartTypes: Array<string>,
    newOpMeters: Array<MeterType>,
    plasticBagMeters: Array<MeterType>,
    nutritionMeters: Array<MeterType>,
    selectedCreator: ?PROFILE_TYPE | ?ORGANIZATION_TYPE,
    generalActions: Array<OperationType>,
    schoolActions: Array<OperationType>,
    greenDealActions: Array<OperationType>,
    meters: Array<MeterType>,
    cities: Array<CityType>,
    commitmentData: CommitmentType,
    errors: {
      [key: string]: string | boolean
    },
    exampleCommitments: Array<CommitmentType>,
    contentAreas: Array<CATEGORY_TYPE>,
    getExampleCommitments: string => void,
    readyToRender: boolean
  },
  {
    // readyToRender: boolean,
    checkboxAuthorized: boolean,
    checkboxFunctionalBusiness: boolean,
    errors: any,
    commitmentData: CommitmentType,
    selectedPrimaryGoal: number | null,
    selectedLocale: LocaleType,
    validationErrorOnLanguageChange: boolean,
    operationJustChangedToNewType: Array<boolean>,
    redirectUri?: string,
    sortedCities: Array<CityType>
  }
> {
  state = {
    checkboxFunctionalBusiness: false,
    checkboxAuthorized: false,
    errors: {},
    selectedPrimaryGoal: isHundredTodosCommitment(this.props.commitmentData)
      ? primaryGoalId
      : null,
    commitmentData: this.props.commitmentData,
    selectedLocale: this.props.locale,
    validationErrorOnLanguageChange: false,
    operationJustChangedToNewType: [],
    sortedCities: sortCities(this.props.cities, this.props.locale)
  };

  inputs = {};
  primaryGoal: any = null;
  selectedCity: any = null;
  endDatePicker: any = null;
  startDatePicker: any = null;
  addOperationButton: any = null;
  commitmentTop: any = null;
  hasEnglishWithoutFinnish: boolean =
    this.props.commitmentData &&
    this.props.commitmentData.innovation_fi_FI === "" &&
    (this.props.commitmentData.title_en_US !== "" ||
      this.props.commitmentData.title_sv_SE !== "");

  handleInput = (
    e: SyntheticInputEvent<any>,
    shouldLocalize: boolean = false
  ) => {
    const { value, name } = e.target;
    this.setState(
      // $FlowFixMe
      produce(draft => {
        draft.commitmentData[name] = value;
      }),
      () => {
        this.checkInputForError(name);
      }
    );
  };

  checkInputForError = (inputName: string) => {
    const inputNameWithoutLocale = inputName.replace(
      "_" + this.state.selectedLocale,
      ""
    );
    const { inputs } = this;
    const { errors } = this.state;
    const valid =
      inputs[inputNameWithoutLocale] &&
      inputs[inputNameWithoutLocale].checkValidity &&
      inputs[inputNameWithoutLocale].checkValidity();
    errors[inputName] = !Boolean(valid);
    this.setState(
      // $FlowFixMe
      produce(draft => {
        draft.errors = errors;
      })
    );
  };

  organizationIsSchool = (id: string) => {
    switch (id) {
      case "33493": {
        return true;
      }
      case "38128": {
        return true;
      }
      case "33494": {
        return true;
      }
      default:
        return false;
    }
  };

  changeLanguage = (language: "fi_FI" | "en_US" | "sv_SE") => {
    if (
      !this.hasEnglishWithoutFinnish &&
      (language === "en_US" || language === "sv_SE") &&
      this.state.selectedLocale === "fi_FI" &&
      this.validateAll()
    ) {
      this.setState(
        // $FlowFixMe
        produce(draft => {
          draft.validationErrorOnLanguageChange = true;
        })
      );
    } else {
      this.setState(
        // $FlowFixMe
        produce(draft => {
          draft.selectedLocale = language;
          if (language === "en_US" && !this.state.commitmentData.title_en_US) {
            draft.commitmentData = {
              ...this.state.commitmentData,
              backgroundInformation_en_US: this.state.commitmentData
                .backgroundInformation_fi_FI,
              innovation_en_US: this.state.commitmentData.innovation_fi_FI,
              shortDescription_en_US: this.state.commitmentData
                .shortDescription_fi_FI,
              title_en_US: this.state.commitmentData.title_fi_FI
            };
          } else if (
            language === "sv_SE" &&
            !this.state.commitmentData.title_sv_SE
          ) {
            draft.commitmentData = {
              ...this.state.commitmentData,
              backgroundInformation_sv_SE: this.state.commitmentData
                .backgroundInformation_fi_FI,
              innovation_sv_SE: this.state.commitmentData.innovation_fi_FI,
              shortDescription_sv_SE: this.state.commitmentData
                .shortDescription_fi_FI,
              title_sv_SE: this.state.commitmentData.title_fi_FI
            };
          }
        })
      );
    }
  };

  validateOperation = (operation: OperationType) => {
    const error: any = {
      message: ""
    };

    if (!operation[`operationTitle_${this.state.selectedLocale}`]) {
      error.message = translate({
        textKey: "sit.commitmentCreation.selectOrCreateOperationError"
      });
      error.target = `operation_${operation.key}`;
      return error;
    }
    if (operation.meters.length === 0) {
      error.message = translate({
        textKey: "sit.commitmentCreation.atleastOneMeterRequiredError"
      });
      error.target = `operation_${operation.key}`;
      return error;
    }

    const invalid =
      operation.meters.length > 0 &&
      operation.meters.some(meter => {
        if (!meter[`meterType_${this.state.selectedLocale}`]) {
          error.message = translate({
            textKey: "sit.commitmentCreation.selectOrCreateMeterError"
          });
          error.target = `meter_${String(meter.key)}`;
          return true;
        }
        if (meter.meterValueType === "NUMBER") {
          if (
            meter.startingLevel == null ||
            meter.targetLevel == null ||
            meter.startingLevel === "" ||
            meter.targetLevel === ""
          ) {
            error.message = translate({
              textKey: "sit.commitmentCreation.meterMissingValues"
            });
            error.target = `meter_${String(meter.key)}`;
            return true;
          }
        }
        return false;
      });

    if (invalid) return error;
    return false;
  };

  updateOperation = (op: OperationType, index: number) => {
    const { errors } = this.state;
    errors["noOperations"] = false;
    errors["operation"] = false;
    this.setState(
      // $FlowFixMe
      produce(draft => {
        draft.commitmentData.operations[index] = op;
        draft.errors = errors;
      })
    );
  };

  setDoneOperations = (ops: Array<DoneOperationType>) => {
    this.setState(
      // $FlowFixMe
      produce(draft => {
        draft.commitmentData.doneOperations = ops;
      })
    );
  };

  operationJustChangedToNewTypeToggle = (status: boolean, index: number) => {
    this.setState(
      // $FlowFixMe
      produce(draft => {
        draft.operationJustChangedToNewType[index] = status;
      })
    );
  };

  saveDraft = (commitmentData: CommitmentType = this.state.commitmentData) => {
    this.props.saveCommitment(commitmentData);
  };

  validateAll = (
    commitmentData: CommitmentType = this.state.commitmentData
  ) => {
    const { errors } = this.state;
    const { inputs } = this;
    const keys = Object.keys(inputs);

    const invalid = keys.some(key => {
      if (inputs[key]) {
        const valid = inputs[key].checkValidity && inputs[key].checkValidity();
        if (!valid) {
          inputs[key].focus();
          errors[`${key}_${this.state.selectedLocale}`] = true;
          return true;
        } else {
          if (key === "contentArea") {
            // Autocomplete may have an input value but no item has been selected from the dropdown. Check for that case manually.
            const valid = this.props.contentAreas.some(
              area =>
                String(area.titleCurrentValue) ===
                String(this.inputs.contentArea.value)
            );

            if (!valid) {
              inputs[key].focus();
              errors[`${key}_${this.state.selectedLocale}`] = true;
              return true;
            }
          }

          errors[`${key}_${this.state.selectedLocale}`] = false;
        }
      }
      return false;
    });

    if (invalid) {
      this.setState(
        // $FlowFixMe
        produce(draft => {
          draft.errors = errors;
        })
      );
      return true;
    }

    let objValid = true;

    if (!this.state.selectedPrimaryGoal) {
      errors["primaryGoal"] = true;
      this.primaryGoal && this.scrollIntoView(this.primaryGoal);

      this.setState(
        // $FlowFixMe
        produce(draft => {
          draft.errors = errors;
        })
      );
      objValid = false;
    }

    if (
      this.primaryGoal &&
      this.state.commitmentData.commitmentType !== "NUTRITION" &&
      !objValid
    ) {
      return true; // Don't fail saving if primary goal was not defined. Exception case for nutrition commitment
    }
    if (!commitmentData.operations || commitmentData.operations.length === 0) {
      errors["noOperations"] = true;
      this.setState(
        // $FlowFixMe
        produce(draft => {
          draft.errors = errors;
        })
      );
      this.scrollIntoView(this.addOperationButton);
      return true;
    }

    let err;
    const invalidOp =
      commitmentData.operations &&
      commitmentData.operations.length > 0 &&
      commitmentData.operations.some(op => {
        const error = this.validateOperation(op);
        if (error) {
          err = error;
          errors["operation"] = err;
          this.setState(
            // $FlowFixMe
            produce(draft => {
              draft.errors = errors;
            })
          );
          const opElement = document.getElementById(
            `operationEditor_${op.key}`
          );

          this.scrollIntoView(opElement);
          return true;
        }
        return false;
      });

    if (invalidOp) {
      return true;
    }

    this.setState({ validationErrorOnLanguageChange: false });
  };

  saveCommitment = (commitmentData: CommitmentType, newStatus: string = "") => {
    const sendForInspection = this.state.commitmentData.status !== "approved";
    const invalidFieldFound = this.validateAll(commitmentData);
    if (invalidFieldFound) {
      return;
    }
    const query = this.props.location && qs.parse(this.props.location.search);
    const joinCommitmentId = query && query.joinCommitmentId;

    this.props.saveCommitment(
      newStatus
        ? {
            ...commitmentData,
            status: newStatus
          }
        : commitmentData,
      sendForInspection,
      this.props.editing,
      joinCommitmentId
    );
  };

  scrollIntoView = (element: ?HTMLElement) => {
    if (!element) return;
    scrollIntoView(element, {
      scrollMode: "if-needed",
      block: "center",
      inline: "nearest"
    });
  };

  // $FlowFixMe
  componentWillReceiveProps(nextProps) {
    if (nextProps.cities !== this.props.cities) {
      this.setState(
        // $FlowFixMe
        produce(draft => {
          draft.sortedCities = sortCities(
            nextProps.cities,
            this.state.selectedLocale
          );
        })
      );
    }

    if (this.state.commitmentData !== nextProps.commitmentData) {
      this.setState(
        // $FlowFixMe
        produce(draft => {
          draft.commitmentData = nextProps.commitmentData;
        })
      );
    } else if (
      nextProps.commitmentData.address !== this.state.commitmentData.address
    ) {
      this.setState(
        // $FlowFixMe
        produce(draft => {
          draft.commitmentData = {
            ...this.state.commitmentData,
            address: nextProps.commitmentData.address,
            longitude: nextProps.commitmentData.longitude,
            latitude: nextProps.commitmentData.latitude
          };
        })
      );
    }

    if (this.props.commitmentData.categoryIds[0] < 31820) {
      this.setState(
        // $FlowFixMe
        produce(draft => {
          draft.selectedPrimaryGoal = this.props.commitmentData.categoryIds[0];
        })
      );
    }

    const gaConversionTracking = action => {
      ReactGA.event({
        category: "Commitment",
        action: action,
        label: "Successful",
        value: 1
      });
    };

    if (nextProps.savedCommitment) {
      // TODO: this is not the best place for handling post-save events
      // It would probably be better to handle this in Redux actions.
      if (!this.props.commitmentData.id) {
        // a new commitment has been saved
        if (nextProps.savedCommitment.status === "draft") {
          gaConversionTracking("Draft");
        } else {
          gaConversionTracking("Publish");
          ReactPixel.track("Submit application");
        }
      } else if (
        this.props.commitmentData.status === "draft" &&
        nextProps.savedCommitment.status === "approved"
      ) {
        gaConversionTracking("Publish");
        ReactPixel.track("Submit application");
      } else {
        // draft -> draft or approved -> approved, this will not be sent to GA
      }
    }

    if (
      !this.props.editing &&
      this.props.commitmentNotification !== nextProps.commitmentNotification &&
      nextProps.commitmentNotification &&
      nextProps.commitmentNotification.state === "SUCCESS"
    ) {
      setTimeout(() => {
        this.setState(
          // $FlowFixMe
          produce(draft => {
            draft.redirectUri = "/sitoumukset";
          })
        );
      }, 1500);
    }
  }

  componentDidMount() {
    const query = this.props.location && qs.parse(this.props.location.search);
    const organizations = this.props.organizations;
    if (query && query.creatorOrganizationId && organizations) {
      let organizationName = null;
      organizations.forEach(organization => {
        if (organization.organizationId === query.creatorOrganizationId) {
          organizationName = organization.name;
        }
      });
      if (organizationName) {
        this.setState(
          // $FlowFixMe
          produce(draft => {
            draft.selectedPrimaryGoal = this.props.greenDealMainGoal;
            draft.commitmentData = {
              ...draft.commitmentData,
              organizationName
            };
          })
        );
      } else {
        this.setState(
          // $FlowFixMe
          produce(draft => {
            draft.selectedPrimaryGoal = this.props.greenDealMainGoal;
          })
        );
      }
    }
  }

  setReportingInterval = (name: string) => {
    this.setState(
      // $FlowFixMe
      produce(draft => {
        draft.commitmentData.reportingInterval = name;
      })
    );
  };

  //Gets the infotext object by comparing the given section string and the section string
  //in each info-text object.
  getInfoText = (section: string) => {
    const { infoTexts } = this.props;
    let returnText = {
      id: 0,
      info: translate({
        languageOverride: this.state.selectedLocale,
        textKey: "sit.commitmentCreation.infoError"
      }),
      placeholder: "",
      placeholder_fi_FI: "",
      placeholder_en_US: "",
      placeholder_sv_SE: ""
    };

    if (infoTexts) {
      Object.keys(infoTexts).forEach(infoTextKey => {
        if (infoTexts[infoTextKey].section === section) {
          returnText = infoTexts[infoTextKey];
        }
      });
    }
    return returnText;
  };

  render() {
    if (!this.props.readyToRender) return null;
    const query = this.props.location && qs.parse(this.props.location.search);
    const creatorOrganizationTypeId = query && query.creatorOrganizationTypeId;
    const commitmentType = this.state.commitmentData.commitmentType;
    const isGreenDeal =
      this.state.commitmentData.commitmentType === "GREEN_DEAL";
    const foundMainGoal =
      this.state.selectedPrimaryGoal &&
      this.props.mainCategories.find(element => {
        return (
          Number(element.categoryId) === Number(this.state.selectedPrimaryGoal)
        );
      });

    const infoTextSections = {
      summaryInfo: "summary",
      actionsInfo: "actions",
      newInfo: "new",
      backgroundInfo: "background",
      reminderInfo: "reminder",
      reminderInfoLifeStyle: "reminderLS",
      mainGoalInfo: "mainGoal",
      otherGoalsInfo: "otherGoals",
      agendaInfo: "agenda",
      meterInfo: "meter"
    };

    const getCityDisplayName = city =>
      getLocalizedString("name", city, this.state.selectedLocale);

    const cityItems = [
      {
        label: translate({
          languageOverride: this.state.selectedLocale,
          textKey: "sit.commitmentCreation.selectCity.abroad"
        }),
        name: translate({
          languageOverride: this.state.selectedLocale,
          textKey: "sit.commitmentCreation.selectCity.abroad"
        }),
        id: "Ulkomaat",
        value: "Ulkomaat"
      }
    ].concat(
      this.state.sortedCities.map(city => ({
        label: getCityDisplayName(city),
        name: getCityDisplayName(city),
        value: city.name_fi_FI,
        id: city.name_fi_FI
      }))
    );

    const parsedCity = this.state.commitmentData.address.split(",")[0];
    const foundCity = cityItems.find(el => el.value === parsedCity);

    const cityLabel =
      (foundCity && foundCity.label) ||
      parsedCity ||
      `${translate({
        languageOverride: this.state.selectedLocale,
        textKey: "sit.commitmentCreation.selectCity"
      })}...`;

    const protocolAndHost =
      window.location.protocol + "//" + window.location.host;
    const commitmentShareUrl =
      protocolAndHost +
      "/o/commitment2050-service/share-commitment/" +
      this.state.commitmentData.id;

    const showHundredTodos = isHundredTodosCommitment(
      this.state.commitmentData
    );

    return this.state.redirectUri ? (
      <Redirect to={this.state.redirectUri} />
    ) : (
      <div className="container">
        <div className="row">
          <div
            className="col-md-12"
            ref={commitmentTop => (this.commitmentTop = commitmentTop)}
          >
            {this.commitmentTop && <ScrollTo element={this.commitmentTop} />}
            <SpaceBetween>
              <Link to="/tee-sitoumus">
                <SecondaryButton
                  style={{ height: 36, minWidth: 160, width: 160 }}
                >
                  <i
                    className="fa fa-chevron-left"
                    style={{ marginRight: "6px" }}
                  />
                  {translate({
                    textKey: "sit.back"
                  })}
                </SecondaryButton>
              </Link>

              {(() => {
                if (commitmentType)
                  switch (commitmentType) {
                    case "COMMITMENT": {
                      return (
                        <StyledTitle>
                          {translate({
                            textKey: "sit.commitment.actionableCommitment"
                          })}
                        </StyledTitle>
                      );
                    }
                    case "GREEN_DEAL": {
                      return (
                        <StyledTitle>
                          {translate({
                            textKey: "sit.commitment.greenDealCommitment"
                          })}
                        </StyledTitle>
                      );
                    }
                    case "NUTRITION": {
                      return (
                        <StyledTitle>
                          {translate({
                            textKey: "sit.commitment.nutritionCommitment"
                          })}
                        </StyledTitle>
                      );
                    }
                    default:
                      return <StyledTitle>{null}</StyledTitle>;
                  }
              })()}
            </SpaceBetween>
          </div>
        </div>

        <WhiteSpace height="40px" />
        {!showHundredTodos && (
          <Row>
            <LanguageContainer>
              <Row justifyContent="center" alignItems="center">
                <LanguageTitle
                  id="commitment_creation_change_language_fi_FI"
                  selected={this.state.selectedLocale === "fi_FI"}
                  onClick={() => this.changeLanguage("fi_FI")}
                >
                  SUOMI
                </LanguageTitle>
                <VerticalSeparator />
                <LanguageTitle
                  id="commitment_creation_change_language_en_US"
                  selected={this.state.selectedLocale === "en_US"}
                  onClick={() => this.changeLanguage("en_US")}
                >
                  ENGLISH
                </LanguageTitle>
                <VerticalSeparator />
                <LanguageTitle
                  id="commitment_creation_change_language_sv_SE"
                  selected={this.state.selectedLocale === "sv_SE"}
                  onClick={() => this.changeLanguage("sv_SE")}
                >
                  SVENSKA
                </LanguageTitle>
              </Row>
              {this.state.validationErrorOnLanguageChange ? (
                <CenterVertically>
                  <ErrorText>
                    {translate({
                      textKey:
                        "sit.commitmentCreation.validationErrorOnLanguageChange"
                    })}
                  </ErrorText>
                </CenterVertically>
              ) : (
                <WhiteSpace height="40px" />
              )}
            </LanguageContainer>
          </Row>
        )}

        <div className="row">
          <div className="col-md-6">
            <BootstrapColumnContainer>
              <TextInput
                id="commitment_name_input"
                error={get(
                  this.state,
                  `errors.title_${this.state.selectedLocale}`
                )}
                innerRef={r => (this.inputs.title = r)}
                required
                onChange={this.handleInput}
                name={`title_${this.state.selectedLocale}`}
                type="text"
                placeholder={translate({
                  languageOverride: this.state.selectedLocale,
                  textKey: "sit.commitmentCreation.inputCommitmentName"
                })}
                value={getLocalizedString(
                  "title",
                  this.state.commitmentData,
                  this.state.selectedLocale
                )}
                label={
                  showHundredTodos
                    ? null
                    : translate({
                        languageOverride: this.state.selectedLocale,
                        textKey: "sit.commitmentCreation.commitmentName"
                      })
                }
                textComponent={StyledH2}
                inputStyle={
                  showHundredTodos
                    ? {
                        paddingLeft: 0,
                        fontWeight: "bold",
                        fontSize: 18
                      }
                    : {
                        fontSize: 18
                      }
                }
                style={{
                  position: "relative",
                  paddingLeft: 0
                }}
                renderIcon={({ value }) => (
                  <InputIconContainer
                    valid={Boolean(value)}
                    renderValid={() => <i className="fas fa-check" />}
                    renderInvalid={() => null}
                  />
                )}
              />
              <StyledSubtitle>
                {translate({
                  languageOverride: this.state.selectedLocale,
                  textKey: "sit.commitmentCreation.duration"
                })}
              </StyledSubtitle>
              <Row>
                <DatePickerOpen>
                  <DatePickerContainer
                    justifyContent="flex-start"
                    alignItems="center"
                    error={get(this.state, "errors.startDate")}
                    disabled={
                      this.state.selectedLocale !== "fi_FI" &&
                      !this.hasEnglishWithoutFinnish
                    }
                  >
                    <DatePicker
                      ref={startDatePicker =>
                        (this.startDatePicker = startDatePicker)
                      }
                      required
                      disabled={
                        this.state.selectedLocale !== "fi_FI" &&
                        !this.hasEnglishWithoutFinnish
                      }
                      selected={
                        this.state.commitmentData.startDate
                          ? moment(this.state.commitmentData.startDate)
                          : null
                      }
                      minDate={moment()}
                      placeholderText={translate({
                        languageOverride: this.state.selectedLocale,
                        textKey: "sit.commitment.operations.starting"
                      })}
                      onChange={val =>
                        this.setState(
                          // $FlowFixMe
                          produce(draft => {
                            draft.commitmentData.startDate = val.toJSON();
                            draft.commitmentData.endDate =
                              draft.commitmentData.endDate &&
                              draft.commitmentData.startDate >
                                draft.commitmentData.endDate
                                ? val.toJSON()
                                : draft.commitmentData.endDate;
                          }),
                          () => {
                            this.checkInputForError("startDate");
                          }
                        )
                      }
                      showMonthDropdown
                      showYearDropdown
                      dropdownMode="select"
                      customInput={
                        <Row
                          justifyContent="flex-start"
                          alignItems="center"
                          style={{ position: "relative" }}
                        >
                          <input
                            onChange={() => true} // Tests require either onChange or readOnly. Readonly doesn't work with "required" validation. So this is a workaround.
                            required
                            ref={startDate =>
                              (this.inputs.startDate = startDate)
                            }
                            value={this.state.commitmentData.startDate}
                            style={{ opacity: 0, position: "absolute" }}
                            onFocus={() => this.startDatePicker.setOpen()}
                          />

                          <i
                            className="fas fa-calendar-alt"
                            style={{
                              marginRight: "6px",
                              fontSize: 18,
                              cursor: "pointer"
                            }}
                          />
                          <span style={{ fontSize: 13, marginLeft: "6px" }}>
                            {this.state.commitmentData.startDate
                              ? moment(
                                  this.state.commitmentData.startDate
                                ).format("DD.MM.YYYY")
                              : translate({
                                  languageOverride: this.state.selectedLocale,
                                  textKey:
                                    "sit.commitment.operations.chooseStartingDate"
                                })}
                          </span>
                        </Row>
                      }
                    />
                  </DatePickerContainer>
                </DatePickerOpen>

                <DatePickerOpen>
                  <DatePickerContainer
                    justifyContent="flex-start"
                    alignItems="center"
                    error={get(this.state, "errors.endDate")}
                    disabled={
                      this.state.selectedLocale !== "fi_FI" &&
                      !this.hasEnglishWithoutFinnish
                    }
                  >
                    <DatePicker
                      ref={picker => (this.endDatePicker = picker)}
                      required
                      disabled={
                        this.state.selectedLocale !== "fi_FI" &&
                        !this.hasEnglishWithoutFinnish
                      }
                      placeholderText={translate({
                        languageOverride: this.state.selectedLocale,
                        textKey: "sit.commitment.operations.chooseEndingDate"
                      })}
                      selected={
                        this.state.commitmentData.endDate
                          ? moment(this.state.commitmentData.endDate)
                          : null
                      }
                      onChange={val =>
                        this.setState(
                          // $FlowFixMe
                          produce(draft => {
                            draft.commitmentData.endDate = val.toJSON();
                          }),
                          () => {
                            this.checkInputForError("startDate");
                          }
                        )
                      }
                      showMonthDropdown
                      showYearDropdown
                      dropdownMode="select"
                      minDate={
                        this.state.commitmentData.startDate
                          ? moment(this.state.commitmentData.startDate)
                          : moment()
                      }
                      customInput={
                        <Row
                          justifyContent="flex-start"
                          alignItems="center"
                          style={{ position: "relative" }}
                        >
                          <input
                            onChange={() => true} // Tests require either onChange or readOnly. Readonly doesn't work with "required" validation. So this is a workaround.
                            required
                            ref={endDate => (this.inputs.endDate = endDate)}
                            value={this.state.commitmentData.endDate}
                            style={{ opacity: 0, position: "absolute" }}
                            onFocus={() => this.endDatePicker.setOpen()}
                          />
                          <i
                            className="fas fa-calendar-alt"
                            style={{
                              marginRight: "6px",
                              fontSize: 18,
                              cursor: "pointer"
                            }}
                          />
                          <span style={{ fontSize: 13, marginLeft: "6px" }}>
                            {this.state.commitmentData.endDate
                              ? moment(
                                  this.state.commitmentData.endDate
                                ).format("DD.MM.YYYY")
                              : translate({
                                  languageOverride: this.state.selectedLocale,
                                  textKey:
                                    "sit.commitment.operations.chooseEndingDate"
                                })}
                          </span>
                        </Row>
                      }
                    />
                  </DatePickerContainer>
                </DatePickerOpen>
              </Row>
              <WhiteSpace height="24px" />

              <Row>
                <CenterVertically>
                  <Label
                    style={{
                      color:
                        this.state.selectedLocale !== "fi_FI" &&
                        !this.hasEnglishWithoutFinnish &&
                        "grey"
                    }}
                  >
                    {translate({
                      languageOverride: this.state.selectedLocale,
                      textKey: "sit.commitmentCreation.selectCity"
                    })}
                  </Label>
                  <Dropdown
                    id="citySelector"
                    padding="0px"
                    internalPadding="6px 0px 6px 12px"
                    right="30px"
                    height="42px"
                    showValue={false}
                    disabled={
                      this.state.selectedLocale !== "fi_FI" &&
                      !this.hasEnglishWithoutFinnish
                    }
                    hoverBackground="transparent"
                    justifyLabel="flex-end"
                    _ref={selectedCity => (this.selectedCity = selectedCity)}
                    error={get(this.state, `errors.selectedCity`, false)}
                    label={cityLabel}
                    items={cityItems}
                    render={({ items, value }) => (
                      <RadioButtonContainer
                        style={{
                          minWidth: 150,
                          border: 0,
                          minHeight: 36
                        }}
                        height="400px"
                        overflowY="scroll"
                      >
                        {items.map(item => (
                          <RadioButton
                            id={item.id}
                            defaultChecked={
                              get(this.state.commitmentData, "address").split(
                                ","
                              )[0] === item.value
                            }
                            key={item.id}
                            label={item.label}
                            name="cityRadioButton"
                            value={item.id}
                            onChange={event => {
                              const { errors } = this.state;
                              errors["selectedCity"] = false;
                              this.setState(
                                // $FlowFixMe
                                produce(draft => {
                                  const cityObject = this.props.cities.find(
                                    city => {
                                      return city.name_fi_FI === event.value;
                                    }
                                  );
                                  draft.commitmentData.address = `${event.value.toString()},Suomi`;
                                  if (cityObject) {
                                    draft.commitmentData.longitude =
                                      cityObject.longitude;
                                    draft.commitmentData.latitude =
                                      cityObject.latitude;
                                    draft.errors = errors;
                                  }
                                })
                              );
                            }}
                          />
                        ))}
                      </RadioButtonContainer>
                    )}
                  />
                </CenterVertically>
              </Row>

              <WhiteSpace height="24px" />

              <Row justifyContent="space-between">
                <StyledSubtitle showBottomBorder paddingBottom={"10px"}>
                  {translate({
                    languageOverride: this.state.selectedLocale,
                    textKey: "sit.commitment.shortDescription"
                  })}
                  <InfoIcon
                    info={this.getInfoText(infoTextSections.summaryInfo)}
                    selectedLocale={this.state.selectedLocale}
                  />
                </StyledSubtitle>
              </Row>
              <Textarea
                error={get(
                  this.state,
                  `errors.shortDescription_${this.state.selectedLocale}`
                )}
                innerRef={r => (this.inputs.shortDescription = r)}
                name={`shortDescription_${this.state.selectedLocale}`}
                onChange={this.handleInput}
                maxLength={400}
                rows={10}
                required
                placeholder={
                  showHundredTodos
                    ? get(
                        this.props,
                        `infoTexts.152980.placeholder_${
                          this.state.selectedLocale
                        }`
                      )
                    : this.getInfoText(infoTextSections.summaryInfo)[
                        `placeholder_${this.state.selectedLocale}`
                      ] ||
                      this.getInfoText(infoTextSections.summaryInfo).placeholder
                }
                value={getLocalizedString(
                  "shortDescription",
                  this.state.commitmentData,
                  this.state.selectedLocale
                )}
              />
              <span>
                {this.state.commitmentData[
                  `shortDescription_${this.state.selectedLocale}`
                ]
                  ? 400 -
                    this.state.commitmentData[
                      `shortDescription_${this.state.selectedLocale}`
                    ].length
                  : 400}{" "}
                {translate({
                  languageOverride: this.state.selectedLocale,
                  textKey: "sit.charsLeft"
                })}
              </span>
              <WhiteSpace height="30px" />

              {this.props.contentAreas &&
                this.props.contentAreas.length > 0 && (
                  <Row justifyContent="space-between">
                    <AutoComplete
                      items={this.props.contentAreas}
                      selectedItem={this.props.contentAreas.find(area =>
                        this.state.commitmentData.categoryIds.includes(
                          Number(area.categoryId)
                        )
                      )}
                      onChange={v => {
                        const { errors } = this.state;
                        errors["contentArea"] = false;
                        this.setState(
                          // $FlowFixMe
                          produce(draft => {
                            draft.errors = errors;
                            // Filter out potential previously selected contentArea id
                            const ids = draft.commitmentData.categoryIds.filter(
                              id => {
                                const val = this.props.contentAreas.some(
                                  area => Number(area.categoryId) === Number(id)
                                );
                                return !val;
                              }
                            );

                            draft.commitmentData.categoryIds = [
                              ...ids,
                              Number(v.categoryId)
                            ];
                          }),
                          () => {
                            this.props.getExampleCommitments(v.categoryId);
                          }
                        );
                      }}
                      renderInput={({ getInputProps, toggleMenu, isOpen }) => (
                        <TextInput
                          error={get(this.state, "errors.contentArea")}
                          innerRef={contentArea =>
                            (this.inputs.contentArea = contentArea)
                          }
                          {...getInputProps({
                            name: "contentArea",
                            type: "text",
                            label: (
                              <span>
                                <span style={{ marginRight: "6px" }}>
                                  {translate({
                                    languageOverride: this.state.selectedLocale,
                                    textKey:
                                      "sit.commitment.operations.selectContent"
                                  })}
                                  {"  "}
                                </span>
                                <InfoIcon
                                  info={this.getInfoText(
                                    infoTextSections.mainGoalInfo
                                  )}
                                  selectedLocale={this.state.selectedLocale}
                                />
                              </span>
                            ),
                            disabled:
                              this.state.selectedLocale !== "fi_FI" &&
                              !this.hasEnglishWithoutFinnish,
                            required: false,

                            renderIcon: ({ value, isValid }) => (
                              <Fragment>
                                <InputIconContainer
                                  valid={Boolean(value)}
                                  renderValid={() => (
                                    <i className="fas fa-check" />
                                  )}
                                />
                                {isOpen ? (
                                  <InputIconContainer
                                    key="up"
                                    onClick={toggleMenu}
                                    render={() => (
                                      <i className="fas fa-chevron-up" />
                                    )}
                                  />
                                ) : (
                                  <InputIconContainer
                                    key="down"
                                    onClick={toggleMenu}
                                    render={() => (
                                      <i className="fas fa-chevron-down" />
                                    )}
                                  />
                                )}
                              </Fragment>
                            )
                          })}
                        />
                      )}
                    />
                  </Row>
                )}
              <Row justifyContent="space-between">
                <StyledSubtitle>
                  {translate({
                    languageOverride: this.state.selectedLocale,
                    textKey: "sit.commitment.operations"
                  })}
                </StyledSubtitle>
                {!showHundredTodos && (
                  <InfoIcon
                    info={this.getInfoText(infoTextSections.actionsInfo)}
                    selectedLocale={this.state.selectedLocale}
                  />
                )}
              </Row>

              {showHundredTodos ? (
                <Fragment>
                  <HundredTodosContainer
                    commitment={this.state.commitmentData}
                    locale={this.state.selectedLocale}
                    hasEnglishWithoutFinnish={this.hasEnglishWithoutFinnish}
                    id={`HundredTodosContainer`}
                    error={get(this.state, "errors.operation")}
                    infoTexts={get(this.props, "infoTexts")}
                    newOpMeters={this.props.newOpMeters}
                    meters={this.props.meters}
                    acceptedMeterChartTypes={this.props.acceptedMeterChartTypes}
                    updateOperation={this.updateOperation}
                    setDoneOperations={this.setDoneOperations}
                    index={0}
                    availableOperations={
                      commitmentType === "COMMITMENT"
                        ? {
                            schoolActions: this.props.schoolActions,
                            generalActions: this.props.generalActions
                          }
                        : commitmentType === "GREEN_DEAL" &&
                          !this.state.commitmentData.categoryIds.find(
                            id => id === 33556
                          )
                          ? {
                              greenDealActions: this.props.greenDealActions
                            }
                          : null
                    }
                    hundredSmartWaysArticles={
                      this.props.hundredSmartWaysArticles
                    }
                  />
                  <WhiteSpace height="24px" />
                </Fragment>
              ) : (
                <Fragment>
                  {this.state.commitmentData.operations &&
                    this.state.commitmentData.operations.map((op, index) => (
                      <Fragment
                        key={"operation_editor_" + op.key + op.operationId}
                      >
                        <OperationEditor
                          commitment={this.state.commitmentData}
                          locale={this.state.selectedLocale}
                          hasEnglishWithoutFinnish={
                            this.hasEnglishWithoutFinnish
                          }
                          operationJustChangedToNewTypeToggle={
                            this.operationJustChangedToNewTypeToggle
                          }
                          operationJustChangedToNewType={
                            this.state.operationJustChangedToNewType[index]
                          }
                          id={`operationEditor_${op.key}`}
                          error={get(this.state, "errors.operation")}
                          meterInfoText={this.getInfoText(
                            infoTextSections.meterInfo
                          )}
                          newOpMeters={this.props.newOpMeters}
                          plasticBagMeters={this.props.plasticBagMeters}
                          nutritionMeters={this.props.nutritionMeters}
                          operation={op}
                          meters={this.props.meters}
                          acceptedMeterChartTypes={
                            this.props.acceptedMeterChartTypes
                          }
                          organizationIsSchool={this.organizationIsSchool}
                          creatorOrganizationTypeId={creatorOrganizationTypeId}
                          availableOperations={
                            commitmentType === "COMMITMENT"
                              ? {
                                  schoolActions: this.props.schoolActions,
                                  generalActions: this.props.generalActions,
                                  greenDealActions: []
                                }
                              : commitmentType === "GREEN_DEAL" &&
                                !this.state.commitmentData.categoryIds.find(
                                  id => id === 33556
                                )
                                ? {
                                    schoolActions: [],
                                    generalActions: [],
                                    greenDealActions: this.props
                                      .greenDealActions
                                  }
                                : null
                          }
                          updateOperation={this.updateOperation}
                          index={index}
                          removeOperation={() =>
                            this.setState(
                              // $FlowFixMe
                              produce(draft => {
                                draft.commitmentData.operations.splice(
                                  index,
                                  1
                                );
                              })
                            )
                          }
                        />
                        <WhiteSpace height="24px" />
                      </Fragment>
                    ))}
                  <Row
                    data-testid="add_oper"
                    innerRef={addOp => (this.addOperationButton = addOp)}
                    style={{
                      marginTop: "16px",
                      paddingTop: "16px",
                      borderTop: "1px solid #ccc"
                    }}
                    justifyContent="flex-start"
                    alignItems="center"
                    onClick={() => {
                      if (
                        this.state.selectedLocale === "fi_FI" ||
                        this.hasEnglishWithoutFinnish
                      ) {
                        let error;
                        this.state.commitmentData.operations &&
                          this.state.commitmentData.operations.length > 0 &&
                          this.state.commitmentData.operations.some(op => {
                            const err = this.validateOperation(op);
                            if (err !== false) {
                              error = err;
                              const opEl = document.getElementById(
                                `operationEditor_${op.key}`
                              );
                              this.scrollIntoView(opEl);
                              return true;
                            } else return false;
                          });

                        if (error) {
                          const { errors } = this.state;
                          errors["operation"] = error;
                          this.setState(
                            // $FlowFixMe
                            produce(draft => {
                              draft.errors = errors;
                            })
                          );
                          return;
                        }

                        const newOperationKey = String(Math.random());

                        this.setState(
                          // $FlowFixMe
                          produce(draft => {
                            draft.commitmentData.operations.push(
                              generateNewOperation(newOperationKey)
                            );
                            const { errors } = this.state;
                            errors["noOperations"] = false;
                            errors["operation"] = false;
                            draft.errors = errors;
                          }),
                          () => {
                            const opEl = document.getElementById(
                              `operationEditor_${newOperationKey}`
                            );
                            this.scrollIntoView(opEl);
                          }
                        );
                      }
                    }}
                  >
                    <RoundButtonWithIcon
                      margin="0"
                      disabled={
                        this.state.selectedLocale !== "fi_FI" &&
                        !this.hasEnglishWithoutFinnish
                      }
                    >
                      <i className="fa fa-plus" />
                    </RoundButtonWithIcon>
                    <h5
                      style={{
                        color: "#000",
                        fontWeight: "bold",
                        textTransform: "uppercase",
                        marginLeft: 24
                      }}
                    >
                      {translate({
                        languageOverride: this.state.selectedLocale,
                        textKey: "sit.commitment.operations.addOperation"
                      })}
                    </h5>
                  </Row>
                  {this.state.errors["noOperations"] && (
                    <Fragment>
                      <WhiteSpace height="20px" />
                      <span
                        style={{
                          color: "red",
                          padding: 10,
                          border: "1px solid red"
                        }}
                      >
                        {translate({
                          languageOverride: this.state.selectedLocale,
                          textKey: "sit.commitment.operations.mustHaveOne"
                        })}
                      </span>
                    </Fragment>
                  )}
                </Fragment>
              )}

              <WhiteSpace height="40px" />
              {!showHundredTodos && (
                <Fragment>
                  <Row justifyContent="space-between">
                    <StyledSubtitle showBottomBorder paddingBottom={"10px"}>
                      {translate({
                        languageOverride: this.state.selectedLocale,
                        textKey: "sit.commitmentCreation.whatNew"
                      })}
                    </StyledSubtitle>

                    <InfoIcon
                      info={this.getInfoText(infoTextSections.newInfo)}
                      selectedLocale={this.state.selectedLocale}
                    />
                  </Row>
                  <WhiteSpace height="20px" />
                  <Textarea
                    data-testid="innovation"
                    error={get(
                      this.state,
                      `errors.innovation_${this.state.selectedLocale}`
                    )}
                    innerRef={r => (this.inputs.innovation = r)}
                    required
                    name={`innovation_${this.state.selectedLocale}`}
                    onChange={this.handleInput}
                    maxLength={3500}
                    rows={10}
                    placeholder={
                      this.getInfoText(infoTextSections.newInfo)[
                        `placeholder_${this.state.selectedLocale}`
                      ] ||
                      this.getInfoText(infoTextSections.newInfo).placeholder
                    }
                    value={getLocalizedString(
                      "innovation",
                      this.state.commitmentData,
                      this.state.selectedLocale
                    )}
                  />
                  <span>
                    {this.state.commitmentData[
                      `innovation_${this.state.selectedLocale}`
                    ]
                      ? 3500 -
                        this.state.commitmentData[
                          `innovation_${this.state.selectedLocale}`
                        ].length
                      : 3500}{" "}
                    {translate({
                      languageOverride: this.state.selectedLocale,
                      textKey: "sit.charsLeft"
                    })}
                  </span>
                  <WhiteSpace height="20px" />

                  <Row justifyContent="space-between">
                    <StyledSubtitle showBottomBorder paddingBottom={"10px"}>
                      {translate({
                        languageOverride: this.state.selectedLocale,
                        textKey: "sit.commitment.backgroundInfo"
                      })}
                    </StyledSubtitle>
                    <InfoIcon
                      info={this.getInfoText(infoTextSections.backgroundInfo)}
                      selectedLocale={this.state.selectedLocale}
                    />
                  </Row>

                  <WhiteSpace height="20px" />

                  <Textarea
                    error={get(
                      this.state,
                      `errors.backgroundInformation_${
                        this.state.selectedLocale
                      }`
                    )}
                    innerRef={r => (this.inputs.backgroundInformation = r)}
                    required
                    name={`backgroundInformation_${this.state.selectedLocale}`}
                    onChange={this.handleInput}
                    maxLength={3500}
                    rows={10}
                    placeholder={
                      this.getInfoText(infoTextSections.backgroundInfo)[
                        `placeholder_${this.state.selectedLocale}`
                      ] ||
                      this.getInfoText(infoTextSections.backgroundInfo)
                        .placeholder
                    }
                    value={getLocalizedString(
                      "backgroundInformation",
                      this.state.commitmentData,
                      this.state.selectedLocale
                    )}
                  />
                  <span>
                    {this.state.commitmentData[
                      `backgroundInformation_${this.state.selectedLocale}`
                    ]
                      ? 3500 -
                        this.state.commitmentData[
                          `backgroundInformation_${this.state.selectedLocale}`
                        ].length
                      : 3500}{" "}
                    {translate({
                      languageOverride: this.state.selectedLocale,
                      textKey: "sit.charsLeft"
                    })}
                  </span>
                  <WhiteSpace height="40px" />
                </Fragment>
              )}
              {!isGreenDeal && (
                <ReportingIntervalSelection
                  locale={this.state.selectedLocale}
                  infoText={
                    showHundredTodos
                      ? get(this.props, "infoTexts.152956")
                      : this.getInfoText(infoTextSections.reminderInfo)
                  }
                  reportingInterval={
                    this.state.commitmentData.reportingInterval
                  }
                  setReportingInterval={this.setReportingInterval}
                />
              )}

              {!showHundredTodos && (
                <Fragment>
                  <WhiteSpace height="40px" />
                  {!isGreenDeal && (
                    <Checkbox
                      innerRef={r => (this.inputs.reportReminder = r)}
                      name="reportReminder"
                      checked={this.state.commitmentData.reportReminder}
                      label={translate({
                        languageOverride: this.state.selectedLocale,
                        textKey: "sit.commitmentCreation.reportReminderEmail"
                      })}
                      onChange={() => {
                        this.setState(
                          // $FlowFixMe
                          produce(draft => {
                            draft.commitmentData.reportReminder = !this.state
                              .commitmentData.reportReminder;
                          })
                        );
                      }}
                    />
                  )}
                  <Checkbox
                    error={get(this.state, "errors.acceptCriterias")}
                    innerRef={r => (this.inputs.acceptCriterias = r)}
                    required
                    name="acceptCriterias"
                    checked={this.state.commitmentData.acceptCriterias}
                    onChange={() => {
                      this.setState(
                        // $FlowFixMe
                        produce(draft => {
                          draft.commitmentData.acceptCriterias = !this.state
                            .commitmentData.acceptCriterias;
                        })
                      );
                    }}
                    label={
                      this.state.commitmentData.commitmentType === "GREEN_DEAL"
                        ? translate({
                            languageOverride: this.state.selectedLocale,
                            textKey:
                              "sit.commitmentCreation.greenDealCriteriaAccept"
                          })
                        : translate({
                            languageOverride: this.state.selectedLocale,
                            textKey: "sit.commitmentCreation.criteriaAccept"
                          })
                    }
                  />
                  {this.state.commitmentData.organizationName && (
                    <Fragment>
                      <Checkbox
                        innerRef={r => (this.inputs.authtorized = r)}
                        name="authorized"
                        checked={this.state.checkboxAuthorized}
                        label={translate({
                          languageOverride: this.state.selectedLocale,
                          textKey: "sit.commitmentCreation.authorizedOnBehalf"
                        })}
                        onChange={() => {
                          this.setState(
                            // $FlowFixMe
                            produce(draft => {
                              draft.checkboxAuthorized = !this.state
                                .checkboxAuthorized;
                            })
                          );
                        }}
                      />
                      <Checkbox
                        innerRef={r => (this.inputs.functionalBusiness = r)}
                        name="functionalBusiness"
                        checked={this.state.checkboxFunctionalBusiness}
                        label={translate({
                          languageOverride: this.state.selectedLocale,
                          textKey: "sit.commitmentCreation.functionalBusiness"
                        })}
                        onChange={() => {
                          this.setState(
                            // $FlowFixMe
                            produce(draft => {
                              draft.checkboxFunctionalBusiness = !this.state
                                .checkboxFunctionalBusiness;
                            })
                          );
                        }}
                      />
                    </Fragment>
                  )}
                </Fragment>
              )}
            </BootstrapColumnContainer>
          </div>

          <div className="col-md-6">
            <BootstrapColumnContainer>
              <CommitmentPic
                commitmentPic={
                  this.state.commitmentData.commitmentImageUrl || ""
                }
                disabled={
                  this.state.selectedLocale !== "fi_FI" &&
                  !this.hasEnglishWithoutFinnish
                }
                onSelect={pic =>
                  this.setState(
                    // $FlowFixMe
                    produce(draft => {
                      draft.commitmentData.commitmentImageUrl = pic;
                    })
                  )
                }
              />
              <WhiteSpace height="20px" />
              {this.state.commitmentData.commitmentType !== "NUTRITION" && (
                <Fragment>
                  <Row>
                    {showHundredTodos ? (
                      <div>
                        {translate({
                          languageOverride: this.state.selectedLocale,
                          textKey: "sit.commitmentCreation.elamantapa.mainGoal"
                        })}&nbsp;
                        {translate({
                          languageOverride: this.state.selectedLocale,
                          textKey:
                            "sit.commitmentCreation.elamantapa.selectSecondaryGoals"
                        })}
                      </div>
                    ) : this.state.commitmentData.commitmentType ===
                      "GREEN_DEAL" &&
                    !this.state.commitmentData.categoryIds.find(
                      id => id === 33556
                    ) ? (
                      <div>
                        {// $FlowFixMe
                        translate({
                          languageOverride: this.state.selectedLocale,
                          textKey:
                            "sit.commitmentCreation.greendeal.automotive.mainGoal"
                        }) +
                          " " +
                          (foundMainGoal && foundMainGoal.titleCurrentValue) +
                          "."}&nbsp;
                        {translate({
                          languageOverride: this.state.selectedLocale,
                          textKey:
                            "sit.commitmentCreation.elamantapa.selectSecondaryGoals"
                        })}
                      </div>
                    ) : (
                      <CenterVertically>
                        <Label
                          style={{
                            color:
                              this.state.selectedLocale !== "fi_FI" &&
                              !this.hasEnglishWithoutFinnish &&
                              "grey"
                          }}
                        >
                          {translate({
                            languageOverride: this.state.selectedLocale,
                            textKey: "sit.commitmentCreation.selectMainGoal"
                          })}
                        </Label>
                        <InfoIcon
                          info={this.getInfoText(infoTextSections.mainGoalInfo)}
                          selectedLocale={this.state.selectedLocale}
                        />

                        <Dropdown
                          padding="0px"
                          height="42px"
                          right="30px"
                          showValue={false}
                          disabled={
                            this.state.selectedLocale !== "fi_FI" &&
                            !this.hasEnglishWithoutFinnish
                          }
                          hoverBackground="transparent"
                          justifyLabel="flex-end"
                          _ref={primaryGoal => (this.primaryGoal = primaryGoal)}
                          error={get(this.state, `errors.primaryGoal`, false)}
                          label={
                            foundMainGoal
                              ? getLocalizedString(
                                  "title",
                                  foundMainGoal,
                                  this.state.selectedLocale
                                ) ||
                                getLocalizedString(
                                  // Fallback to Finnish if selected not found
                                  "title",
                                  foundMainGoal,
                                  "fi_FI"
                                )
                              : `${translate({
                                  languageOverride: this.state.selectedLocale,
                                  textKey:
                                    "sit.commitmentCreation.selectMainGoal"
                                })}...`
                          }
                          items={this.props.mainCategories.map(
                            (mainCategory, index) => ({
                              label: mainCategory.titleCurrentValue,
                              name: mainCategory.titleCurrentValue,
                              value: Number(mainCategory.categoryId),
                              id: mainCategory.categoryId
                            })
                          )}
                          render={({ items, value }) => (
                            <RadioButtonContainer
                              style={{
                                minWidth: "160px",
                                border: 0,
                                minHeight: 36
                              }}
                            >
                              {items.map(item => (
                                <RadioButton
                                  id={item.id}
                                  defaultChecked={
                                    get(this.state, "selectedPrimaryGoal") ===
                                    item.value
                                  }
                                  key={item.id}
                                  label={item.label}
                                  name="mainGoal"
                                  value={Number(item.id)}
                                  onChange={event => {
                                    const v = event.value;
                                    const { errors } = this.state;
                                    errors["primaryGoal"] = false;
                                    this.setState(
                                      // $FlowFixMe
                                      produce(draft => {
                                        draft.errors = errors;
                                        const mainCategoryIds = this.props.mainCategories.map(
                                          sec => Number(sec.categoryId)
                                        );
                                        const secondaryCategoryIds = this.props.secondaryCategories.map(
                                          sec => Number(sec.categoryId)
                                        );

                                        const mappedIds =
                                          // $FlowFixMe
                                          Goals2050Agenda2030Map[v];

                                        let ids = this.state.commitmentData.categoryIds
                                          .filter(
                                            id =>
                                              !secondaryCategoryIds.includes(
                                                Number(id)
                                              ) // filter out any potentially selected agenda2030 ids
                                          )
                                          .filter(
                                            id =>
                                              Number(id) < 31811 ||
                                              Number(id) > 31818 // filter out any potentially selected secondary goal ids HELLO NAMING
                                          )
                                          .filter(
                                            id =>
                                              !mainCategoryIds.includes(
                                                Number(id)
                                              )
                                          ); // filter out any previously selected maincategory

                                        if (!ids.includes(Number(v))) {
                                          ids.push(Number(v));
                                        }
                                        draft.selectedPrimaryGoal =
                                          ids[ids.length - 1];
                                        draft.commitmentData.categoryIds = [
                                          ...mappedIds,
                                          ...ids
                                        ];
                                      })
                                    );
                                  }}
                                />
                              ))}
                            </RadioButtonContainer>
                          )}
                        />
                      </CenterVertically>
                    )}
                  </Row>
                  <Dropdown
                    padding="4px 0px 4px 0px"
                    hoverBackground="transparent"
                    height="54px"
                    right="30px"
                    disabled={
                      this.state.selectedLocale !== "fi_FI" &&
                      !this.hasEnglishWithoutFinnish
                    }
                    style={{ marginTop: "-8px" }}
                    items={this.props.mainCategoriesSecondaryObjectives}
                    label={
                      <span>
                        <span style={{ marginRight: "6px" }}>
                          <strong>
                            {translate({
                              languageOverride: this.state.selectedLocale,
                              textKey:
                                "sit.commitmentCreation.selectSecondaryGoals"
                            })}
                          </strong>
                        </span>
                        <InfoIcon
                          info={{
                            id: 1234567890,
                            info: translate({
                              languageOverride: this.state.selectedLocale,
                              textKey:
                                "sit.commitmentCreation.atmostThreeGoalsInfo"
                            })
                          }}
                        />
                      </span>
                    }
                    internalPadding="0 0 0 0"
                    showValue={false}
                    onSelect={e => {
                      const { name } = e.target;

                      this.setState(
                        // $FlowFixMe
                        produce(draft => {
                          if (
                            draft.commitmentData.categoryIds.includes(
                              Number(name)
                            )
                          )
                            draft.commitmentData.categoryIds = draft.commitmentData.categoryIds.filter(
                              c => Number(c) !== Number(name)
                            );
                          else
                            draft.commitmentData.categoryIds.push(Number(name));
                        })
                      );
                    }}
                    render={({ items }) => {
                      const objectives = this.props
                        .mainCategoriesSecondaryObjectives;

                      let selectedCount = 0;
                      objectives.forEach(obj => {
                        if (
                          this.state.commitmentData.categoryIds.includes(
                            Number(obj.categoryId)
                          )
                        ) {
                          selectedCount++;
                        }
                      });

                      return items.map(item => {
                        const checked = this.state.commitmentData.categoryIds.includes(
                          Number(item.categoryId)
                        );

                        return (
                          <StyledCheckbox
                            disabled={
                              (!checked && selectedCount === 3) ||
                              mapOptionalGoalCategoryIdToPrimaryGoalCategoryId(
                                item.categoryId
                              ) === this.state.selectedPrimaryGoal
                            }
                            name={item.categoryId}
                            checked={this.state.commitmentData.categoryIds.includes(
                              Number(item.categoryId)
                            )}
                            height="52px"
                            style={{ border: 0 }}
                            label={item.titleCurrentValue}
                            key={item.uuid || item.id}
                          />
                        );
                      });
                    }}
                  />
                  <WhiteSpace height="30px" />
                  <Row justifyContent="flex-start" flexWrap="wrap">
                    {this.props.mainCategoryProperties.map(category => {
                      const active = this.state.commitmentData.categoryIds.some(
                        id =>
                          mapOptionalGoalCategoryIdToPrimaryGoalCategoryId(
                            Number(id)
                          ) === Number(category.categoryId)
                      );

                      return (
                        <GoalIcon
                          style={{
                            borderStyle:
                              active &&
                              this.state.selectedPrimaryGoal &&
                              category.categoryId ===
                                this.state.selectedPrimaryGoal.toString()
                                ? "solid"
                                : "dashed",
                            opacity: active ? 1.0 : 0.25
                          }}
                          key={category.categoryId}
                          icon={category.value}
                          alt={category.value}
                          title={category.value}
                        />
                      );
                    })}
                  </Row>
                  <WhiteSpace height="30px" />
                  <Row justifyContent="space-between">
                    <StyledSubtitle>
                      {translate({
                        languageOverride: this.state.selectedLocale,
                        textKey: "sit.commitmentCreation.canSelect2030Goals"
                      })}
                    </StyledSubtitle>
                    <InfoIcon
                      info={this.getInfoText(infoTextSections.agendaInfo)}
                      selectedLocale={this.state.selectedLocale}
                    />
                  </Row>
                  <Row>
                    {translate({
                      languageOverride: this.state.selectedLocale,
                      textKey:
                        "sit.commitment.commitmentSupportsFollowingGlobalGoals"
                    })}
                  </Row>
                  <Row justifyContent="flex-start" flexWrap="wrap">
                    {this.props.secondaryCategoryProperties.map(category => {
                      const active = this.state.commitmentData.categoryIds.includes(
                        Number(category.categoryId)
                      );

                      const mainCategoryId = this.state.commitmentData.categoryIds.find(
                        id =>
                          this.props.mainCategories.some(
                            main => Number(main.categoryId) === id
                          )
                      );

                      const mainCategoryAgendas = showHundredTodos
                        ? Goals2050Agenda2030Map["hundredTodos"]
                        : mainCategoryId &&
                          Goals2050Agenda2030Map[mainCategoryId];

                      const isMainCategoryAgenda =
                        mainCategoryAgendas &&
                        mainCategoryAgendas.length > 0 &&
                        mainCategoryAgendas.includes(
                          Number(category.categoryId)
                        );

                      return (
                        <AgendaIcon
                          isActive={active}
                          preventEdit={Boolean(isMainCategoryAgenda)}
                          key={category.categoryId}
                          icon={category.value}
                          alt={category.value}
                          title={category.value}
                          onClick={() => {
                            this.setState(
                              // $FlowFixMe
                              produce(draft => {
                                if (active)
                                  draft.commitmentData.categoryIds = draft.commitmentData.categoryIds.filter(
                                    id => id !== Number(category.categoryId)
                                  );
                                else
                                  draft.commitmentData.categoryIds.push(
                                    Number(category.categoryId)
                                  );
                              })
                            );
                          }}
                        />
                      );
                    })}
                  </Row>
                </Fragment>
              )}

              <Column>
                {this.props.exampleCommitments.length > 0 && (
                  <StyledSubtitle>
                    {translate({
                      languageOverride: this.state.selectedLocale,
                      textKey: "sit.commitmentCreation.othersExamples"
                    })}
                  </StyledSubtitle>
                )}

                {this.props.exampleCommitments &&
                  this.props.exampleCommitments.map(example => (
                    <Row
                      key={example.id}
                      style={{
                        background: "#F4F4F4",
                        padding: 20,
                        margin: 10,
                        boxShadow: "0 3px 6px rgba(0, 0, 0, 0.18)"
                      }}
                    >
                      {getLocalizedString(
                        "content",
                        example,
                        this.state.selectedLocale
                      )}
                    </Row>
                  ))}
              </Column>
            </BootstrapColumnContainer>

            {this.state.commitmentData.id && (
              <div style={{ paddingLeft: 20 }}>
                <hr />
                <SomeShareContainer
                  facebook={{
                    url: commitmentShareUrl,
                    quote: "Sitoumus2050 - kestävät elämäntavat"
                  }}
                  twitter={{
                    url: commitmentShareUrl,
                    title:
                      "Tein suunnitelman ilmastonmuutoksen ratkaisemiseksi. Millaisen sinä teet?"
                  }}
                  linkedin={{
                    url: commitmentShareUrl
                  }}
                />
              </div>
            )}
          </div>
        </div>

        <Row>
          {showHundredTodos && (
            <Button
              disabled={
                this.props.commitmentNotification &&
                this.props.commitmentNotification.state === "IN_PROGRESS"
              }
              uppercase
              block
              onClick={() =>
                this.saveCommitment(this.state.commitmentData, "approved")
              }
            >
              {this.state.commitmentData.status !== "approved"
                ? translate({
                    textKey: "sit.commitmentCreation.publishYourCommitment"
                  })
                : translate({
                    textKey: "sit.save"
                  })}
            </Button>
          )}
          {!showHundredTodos && (
            <Button
              disabled={
                (commitmentType === "NUTRITION" &&
                  this.state.commitmentData.status === "approved") ||
                (this.props.commitmentNotification &&
                  this.props.commitmentNotification.state === "IN_PROGRESS") ||
                (this.state.commitmentData.organizationName &&
                  (!this.state.checkboxAuthorized ||
                    !this.state.checkboxFunctionalBusiness))
              }
              uppercase
              block
              onClick={() => this.saveCommitment(this.state.commitmentData)}
            >
              {this.state.commitmentData.status !== "approved"
                ? translate({
                    textKey: "sit.commitmentCreation.sendForVerification"
                  })
                : translate({
                    textKey: "sit.save"
                  })}
            </Button>
          )}
          {this.state.commitmentData.status !== "approved" && (
            <SecondaryButton
              uppercase
              block
              onClick={() => this.saveDraft()}
              disabled={
                this.state.commitmentData.status === "approved" ||
                (this.props.commitmentNotification &&
                  this.props.commitmentNotification.state === "IN_PROGRESS")
              }
            >
              {translate({
                textKey: "sit.commitmentCreation.saveDraft"
              })}
            </SecondaryButton>
          )}
        </Row>
        <Row justifyContent="center">
          <NotificationContainer
            notificationType={this.props.commitmentNotification}
          >
            {this.props.commitmentNotification.message}
          </NotificationContainer>
        </Row>
      </div>
    );
  }
}

export default CommitmentCreationForm;
