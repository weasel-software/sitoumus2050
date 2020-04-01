// @flow

import { get, isEqual } from "lodash";
import React, { Component, Fragment } from "react";
import produce from "immer";
import styled from "styled-components";

import { SecondaryButton } from "../../reusable/Button";
import { RadioButtonContainer, RadioButton } from "../../reusable/Radio";
import TextInput from "../../reusable/TextInput";

import WhiteSpace from "../../reusable/WhiteSpace";
import Row from "../../reusable/Row";
import { RoundDivWithIcon } from "../../reusable/RoundButtonWithIcon";
import translate from "../../reusable/translate";
import InputIconContainer from "../../reusable/InputIconContainer";
import AutoComplete from "../../reusable/AutoComplete";

import generateNewOperation from "../../../utils/generateNewOperation";

import type {
  OperationType,
  MeterType,
  CommitmentType
} from "../../../constants/types";

import Meter from "./Meter";

type State = {
  operation: OperationType,
  isNewOperation: ?boolean,
  hasBeenModified: boolean
};

type Props = {
  locale: "fi_FI" | "en_US", // | "sv_SE"
  hasEnglishWithoutFinnish?: boolean,
  operation: OperationType,
  index: number,
  operationJustChangedToNewTypeToggle: (boolean, number) => void,
  operationJustChangedToNewType: boolean,
  organizationIsSchool: string => boolean,
  creatorOrganizationTypeId: string,
  availableOperations: ?{
    schoolActions: Array<OperationType>,
    generalActions: Array<OperationType>,
    greenDealActions: Array<OperationType>
  },
  updateOperation: (OperationType, number) => void,
  newOpMeters: Array<MeterType>,
  removeOperation: () => void,
  meterInfoText: {
    id: number,
    info: string,
    placeholder: string
  },
  acceptedMeterChartTypes: Array<string>,
  id?: string,
  error: any,
  commitment: CommitmentType,
  plasticBagMeters: Array<MeterType>,
  nutritionMeters: Array<MeterType>
};

const SubmitLabel = styled.label`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
`;

class OperationEditor extends Component<Props, State> {
  state = {
    operation: generateNewOperation(),
    isNewOperation: false,
    hasBeenModified: false
  };

  opWithoutKey = (op: OperationType) => {
    const { key, ...rest } = op;
    return { ...rest };
  };

  getOperationValue = (operation: OperationType) => {
    if (operation.id) return operation;
    else {
      const title_fi =
        operation[`operationTitle_fi_FI`] ||
        this.props.operation[`operationTitle_fi_FI`];

      const title_en =
        operation[`operationTitle_en_US`] ||
        this.props.operation[`operationTitle_en_US`];

      const title_sv =
        operation[`operationTitle_sv_SE`] ||
        this.props.operation[`operationTitle_sv_SE`];
      if (this.props.availableOperations) {
        const genOp =
          this.props.availableOperations &&
          this.props.availableOperations.generalActions &&
          this.props.availableOperations.generalActions.find(oper => {
            return (
              (title_fi !== "" && oper[`operationTitle_fi_FI`] === title_fi) ||
              (title_en !== "" && oper[`operationTitle_en_US`] === title_en) ||
              (title_sv !== "" && oper[`operationTitle_sv_SE`] === title_sv)
            );
          });
        if (genOp) return genOp;

        const schoolOp =
          this.props.availableOperations &&
          this.props.availableOperations.schoolActions &&
          this.props.availableOperations.schoolActions.find(
            oper =>
              (title_fi !== "" && oper[`operationTitle_fi_FI`] === title_fi) ||
              (title_en !== "" && oper[`operationTitle_en_US`] === title_en) ||
              (title_sv !== "" && oper[`operationTitle_sv_SE`] === title_sv)
          );
        if (schoolOp) return schoolOp;

        const greenDealOp =
          this.props.availableOperations &&
          this.props.availableOperations.greenDealActions &&
          this.props.availableOperations.greenDealActions.find(
            oper =>
              oper[`operationTitle_fi_FI`] === title_fi ||
              oper[`operationTitle_en_US`] === title_en ||
              oper[`operationTitle_sv_SE`] === title_sv
          );
        if (greenDealOp) return greenDealOp;
      }
      return { id: "" };
    }
  };

  isNewOperation = (op: OperationType): boolean | null => {
    // Commitment is of NUTRITIONAL type or GREEN_DEAL type with plastic bag commitment categoryID, therefore user cannot choose a pre existing operation template
    if (
      this.props.commitment.commitmentType === "NUTRITION" ||
      (this.props.commitment.commitmentType === "GREEN_DEAL" &&
        this.props.commitment.categoryIds.find(id => id === 33556))
    )
      return true;

    const opTitle = String(op[`operationTitle_fi_FI`]);

    // Operation has no title or meters. Operation templates are selectable.
    if (!opTitle && (!op.meters || op.meters.length === 0)) return false;
    const found =
      (this.props.availableOperations &&
        this.props.availableOperations.generalActions.some(
          g => String(g[`operationTitle_fi_FI`]) === opTitle
        )) ||
      (this.props.availableOperations &&
        this.props.availableOperations.schoolActions.some(
          g => String(g[`operationTitle_fi_FI`]) === opTitle
        ));

    return !Boolean(found);
  };

  componentDidMount() {
    const selectedAvailableOperation = this.getOperationValue(
      this.props.operation
    );
    const operationWithTranslations = {
      ...this.props.operation,
      operationTitle_en_US: get(
        selectedAvailableOperation,
        "operationTitle_en_US",
        this.props.operation.operationTitle_en_US
      ),
      operationTitle_fi_FI: get(
        selectedAvailableOperation,
        "operationTitle_fi_FI",
        this.props.operation.operationTitle_fi_FI
      ),
      operationTitle_sv_SE: get(
        selectedAvailableOperation,
        "operationTitle_sv_SE",
        this.props.operation.operationTitle_sv_SE
      )
    };
    this.setState(
      // $FlowFixMe
      produce(draft => {
        draft.operation = operationWithTranslations;
        draft.isNewOperation = this.props.operationJustChangedToNewType
          ? true
          : this.props.operation.operationId !== undefined
            ? this.isNewOperation(this.props.operation)
            : false;
      })
    );
    this.props.operationJustChangedToNewTypeToggle(false, this.props.index);
    this.props.updateOperation(operationWithTranslations, this.props.index);
  }

  componentDidUpdate(prevProps: Props, prevState: State) {
    const thisOp = this.opWithoutKey(this.state.operation);
    const prevOp = this.opWithoutKey(prevState.operation);
    if (!isEqual(thisOp, prevOp)) {
      this.setState(
        // $FlowFixMe
        produce(draft => {
          draft.hasBeenModified = true;
          if (
            this.state.operation.operationTitle_fi_FI &&
            !this.props.commitment.title_en_US &&
            this.state.isNewOperation
          ) {
            draft.operation = {
              ...this.state.operation,
              operationTitle_en_US: this.state.operation.operationTitle_fi_FI,
              operationDescription_en_US:
                this.state.operation.operationDescription_fi_FI || ""
            };
          }
        })
      );
      this.props.updateOperation(this.state.operation, this.props.index);
    }
  }

  handleNewOpInput = (e: SyntheticInputEvent<any>) => {
    const { name, value } = e.target;
    this.setState(
      // $FlowFixMe
      produce(draft => {
        draft.operation[name] = value;
        draft.hasBeenModified = true;
      })
    );
  };

  metersWithoutMeterTemplates = (meters: Array<MeterType>) => {
    // Meter having a key means it was explicitly added by user (meaning user clicked "Add new meter")
    // Meter having a meterId means that it was saved as part of an Operation
    // If meter has neither it's actually a meter template that we don't want to render here
    return meters.filter(meter => {
      if (meter.key) return true;
      else if (meter.meterId) return true;
      else return false;
    });
  };

  render() {
    const {
      availableOperations,
      creatorOrganizationTypeId,
      error,
      id
    } = this.props;

    let unsortedOperations = {};
    if (
      this.props.organizationIsSchool(creatorOrganizationTypeId) &&
      availableOperations
    ) {
      unsortedOperations =
        availableOperations && availableOperations.schoolActions;
    } else if (this.props.commitment.commitmentType === "GREEN_DEAL") {
      unsortedOperations =
        availableOperations && availableOperations.greenDealActions;
    } else {
      unsortedOperations =
        availableOperations && availableOperations.generalActions;
    }
    //sort the operations array before displaying
    let operations = [];
    if (unsortedOperations) {
      let tmpArray = [];
      for (var i = 0; i < unsortedOperations.length; i++) {
        tmpArray[i] = [];
        tmpArray[i][0] =
          unsortedOperations[i][`operationTitle_${this.props.locale}`];
        tmpArray[i][1] = unsortedOperations[i];
      }
      tmpArray.sort();
      tmpArray.forEach(a => {
        operations.push(a[1]);
      });
    }
    const selectedOperationTemplate =
      operations &&
      operations.find(
        op =>
          String(op[`operationTitle_${this.props.locale}`]) ===
          String(this.state.operation[`operationTitle_${this.props.locale}`])
      );
    const { isNewOperation } = this.state;

    return (
      <div
        id={id}
        style={{ border: "2px solid #93BE38", padding: 10, borderRadius: 6 }}
      >
        <form
          data-testid="operation_form"
          onSubmit={e => {
            e.preventDefault();
            this.setState(
              // $FlowFixMe
              produce(draft => {
                if (
                  this.metersWithoutMeterTemplates(this.state.operation.meters)
                    .length < 3 &&
                  !(this.props.commitment.commitmentType === "GREEN_DEAL")
                ) {
                  draft.operation.meters.push({
                    key: "new_meter_" + Math.random()
                  });
                } else if (
                  this.props.commitment.commitmentType === "GREEN_DEAL"
                ) {
                  draft.operation.meters.push({
                    key: "new_meter_" + Math.random()
                  });
                }
              })
            );
          }}
        >
          <Row justifyContent="flex-start">
            <SecondaryButton
              disabled={
                this.props.locale !== "fi_FI" &&
                !this.props.hasEnglishWithoutFinnish
              }
              onClick={e => {
                e.preventDefault();
                if (this.state.hasBeenModified) {
                  const result =
                    window &&
                    window.confirm(
                      translate({
                        languageOverride: this.props.locale,
                        textKey:
                          "sit.commitment.operations.removeOperationConfirmation"
                      })
                    );
                  if (result) {
                    this.props.removeOperation();
                  }
                } else {
                  this.props.removeOperation();
                }
              }}
              type="button"
              style={{ height: 36, color: "red", maxWidth: 200, minWidth: 200 }}
            >
              {translate({
                languageOverride: this.props.locale,
                textKey: "sit.commitment.operations.removeOperation"
              })}
            </SecondaryButton>
          </Row>

          {error &&
            error.target === `operation_${this.state.operation.key}` && (
              <span
                style={{
                  padding: 10,
                  background: "white",
                  color: "red",
                  fontWeight: "bold",
                  position: "relative",
                  zIndex: 1
                }}
              >
                {error.message}
              </span>
            )}

          {this.props.availableOperations && (
            <RadioButtonContainer
              name="create_new_op_radios"
              onChange={e => {
                const name = get(e, "target.name");
                const emptyOp = generateNewOperation(this.state.operation.key);
                this.setState(
                  // $FlowFixMe
                  produce(draft => {
                    const createNew = name === "create_new" ? true : false;
                    draft.isNewOperation = createNew;

                    if (createNew !== this.state.isNewOperation) {
                      draft.operation = emptyOp;
                      this.props.operationJustChangedToNewTypeToggle(
                        createNew,
                        this.props.index
                      );
                    }
                  })
                );
              }}
            >
              <Row>
                <RadioButton
                  style={{ minWidth: 0, width: "100%" }}
                  disabled={
                    this.props.locale !== "fi_FI" &&
                    !this.props.hasEnglishWithoutFinnish
                  }
                  value=""
                  name="pick_existing"
                  checked={isNewOperation === false || isNewOperation === null}
                  id={`pick_existing_operation_radio_${this.props.index}`}
                  label={translate({
                    languageOverride: this.props.locale,
                    textKey: "sit.commitment.reports.existing"
                  })}
                />
                {!(this.props.commitment.commitmentType === "GREEN_DEAL") && (
                  <RadioButton
                    style={{ minWidth: 0, width: "100%" }}
                    disabled={
                      (this.props.locale !== "fi_FI" &&
                        !this.props.hasEnglishWithoutFinnish) ||
                      this.props.commitment.commitmentType === "GREEN_DEAL"
                    }
                    value=""
                    name="create_new"
                    checked={isNewOperation === true}
                    id={`create_new_operation_radio_${this.props.index}`}
                    label={translate({
                      languageOverride: this.props.locale,
                      textKey: "sit.commitment.operations.newOperation"
                    })}
                  />
                )}
              </Row>
            </RadioButtonContainer>
          )}

          {isNewOperation === false ||
          (isNewOperation === null && operations && operations.length > 0) ? (
            <Fragment>
              <AutoComplete
                items={operations}
                namePath={`operationTitle_${this.props.locale}`}
                keyPath={"key"}
                onChange={op => {
                  this.setState(
                    // $FlowFixMe
                    produce(draft => {
                      const operation = {
                        ...op,
                        meters: []
                      };
                      draft.operation = operation;
                      draft.hasBeenModified = true;
                    })
                  );
                }}
                value={String(this.getOperationValue(this.state.operation).id)}
                selectedItem={this.state.operation}
                renderInput={({ getInputProps, toggleMenu, isOpen }) => (
                  <TextInput
                    {...getInputProps({
                      name: "operationDropdown",
											disabled:
												this.props.locale !== "fi_FI" &&
												!this.props.hasEnglishWithoutFinnish,
                      type: "text",
                      label: (
                        <span>
                          <span style={{ marginRight: "6px" }}>
                            {translate({
                              languageOverride: this.props.locale,
                              textKey:
                                "sit.commitment.operations.selectOperation"
                            })}
                            {"  "}
                          </span>
                        </span>
                      ),
                      renderIcon: ({ value, isValid }) => (
                        <Fragment>
                          <InputIconContainer
                            valid={Boolean(value)}
                            renderValid={() => <i className="fas fa-check" />}
                          />
                          {isOpen ? (
                            <InputIconContainer
                              key="up"
                              onClick={toggleMenu}
                              render={() => <i className="fas fa-chevron-up" />}
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

              {Boolean(
                this.state.operation[
                  `operationDescription_${this.props.locale}`
                ]
              ) && (
                <div>
                  {
                    this.state.operation[
                      `operationDescription_${this.props.locale}`
                    ]
                  }
                </div>
              )}
            </Fragment>
          ) : (
            <Fragment>
              <TextInput
                required
                type="text"
                name={`operationTitle_${this.props.locale}`}
                onChange={e => this.handleNewOpInput(e)}
                value={get(this.state.operation, [
                  `operationTitle_${this.props.locale}`
                ])}
                label={translate({
                  languageOverride: this.props.locale,
                  textKey: "sit.commitment.operations.name"
                })}
              />

              <TextInput
                required
                type="text"
                name={`operationDescription_${this.props.locale}`}
                onChange={e => this.handleNewOpInput(e)}
                value={get(this.state.operation, [
                  `operationDescription_${this.props.locale}`
                ])}
                label={translate({
                  languageOverride: this.props.locale,
                  textKey: "sit.commitment.operations.operationDescription"
                })}
              />
            </Fragment>
          )}

          <input
            type="submit"
            id={`submit_operation` + this.props.index}
            style={{ opacity: 0, position: "absolute" }}
          />
        </form>

        <WhiteSpace height="10px" />

        {this.metersWithoutMeterTemplates(this.state.operation.meters).map(
          (meter, index) => {
            return (
              <Meter
                locale={this.props.locale}
                hasEnglishWithoutFinnish={this.props.hasEnglishWithoutFinnish}
                error={this.props.error}
                meterInfoText={this.props.meterInfoText}
                key={meter.meterId || meter.key}
                operation={this.state.operation}
                index={index}
                operationMeters={
                  selectedOperationTemplate
                    ? selectedOperationTemplate.meters
                    : []
                }
                isNewOperation={isNewOperation}
                plasticBagMeters={this.props.plasticBagMeters}
                nutritionMeters={this.props.nutritionMeters}
                newOpMeters={this.props.newOpMeters}
                commitment={this.props.commitment}
                meter={meter}
                acceptedMeterChartTypes={this.props.acceptedMeterChartTypes}
                updateMeter={i => {
                  this.setState(
                    // $FlowFixMe
                    produce(draft => {
                      const meterIndex = this.state.operation.meters.findIndex(
                        m => {
                          if (m.key && i.key && m.key === i.key) return true;
                          if (m.meterId && i.meterId && m.meterId === i.meterId)
                            return true;
                          return false;
                        }
                      );
                      draft.operation.meters[meterIndex] = i;
                    })
                  );
                }}
                removeMeter={meter =>
                  this.setState(
                    // $FlowFixMe
                    produce(draft => {
                      const meterIndex = this.state.operation.meters.findIndex(
                        m => {
                          if (meter.key && m.key && meter.key === m.key)
                            return true;
                          if (
                            meter.meterId &&
                            m.meterId &&
                            meter.meterId === m.meterId
                          )
                            return true;
                          return false;
                        }
                      );

                      draft.operation.meters.splice(meterIndex, 1);
                    })
                  )
                }
              />
            );
          }
        )}

        <WhiteSpace height="12px" />

        {this.metersWithoutMeterTemplates(this.state.operation.meters).length <
          3 &&
          !(this.props.commitment.commitmentType === "GREEN_DEAL") && (
            <Row justifyContent="flex-start" alignItems="center">
              <SubmitLabel
                htmlFor={
                  this.props.locale === "fi_FI" ||
                  this.props.hasEnglishWithoutFinnish
                    ? `submit_operation` + this.props.index
                    : ""
                }
                data-testid="add_meter_label"
              >
                <RoundDivWithIcon
                  margin="0"
                  disabledDiv={
                    this.props.locale !== "fi_FI" &&
                    !this.props.hasEnglishWithoutFinnish
                  }
                >
                  <i className="fa fa-plus" />
                </RoundDivWithIcon>
                <h5
                  style={{
                    color: "#000",
                    fontWeight: "bold",
                    textTransform: "uppercase",
                    marginLeft: 24
                  }}
                >
                  {translate({
                    languageOverride: this.props.locale,
                    textKey: "sit.commitment.operations.addMeter"
                  })}
                </h5>
              </SubmitLabel>
            </Row>
          )}

        {this.props.commitment.commitmentType === "GREEN_DEAL" && (
          <Row justifyContent="flex-start" alignItems="center">
            <SubmitLabel
              htmlFor={
                this.props.locale === "fi_FI" ||
                this.props.hasEnglishWithoutFinnish
                  ? `submit_operation` + this.props.index
                  : ""
              }
              data-testid="add_meter_label"
            >
              <RoundDivWithIcon
                margin="0"
                disabledDiv={
                  this.props.locale !== "fi_FI" &&
                  !this.props.hasEnglishWithoutFinnish
                }
              >
                <i className="fa fa-plus" />
              </RoundDivWithIcon>
              <h5
                style={{
                  color: "#000",
                  fontWeight: "bold",
                  textTransform: "uppercase",
                  marginLeft: 24
                }}
              >
                {translate({
                  languageOverride: this.props.locale,
                  textKey: "sit.commitment.operations.addMeter"
                })}
              </h5>
            </SubmitLabel>
          </Row>
        )}
      </div>
    );
  }
}

export default OperationEditor;
