// @flow

import React, { Component, Fragment } from "react";
import styled from "styled-components";
import { set, get } from "lodash";
import produce from "immer";
import AutoComplete from "../../reusable/AutoComplete";
import InfoIcon from "../../reusable/InfoIcon";
import TextInput from "../../reusable/TextInput";
import WhiteSpace from "../../reusable/WhiteSpace";
import { RadioButtonContainer, RadioButton } from "../../reusable/Radio";
import Button, { SecondaryButton } from "../../reusable/Button";
import Row from "../../reusable/Row";
import StyledSubtitle from "../../reusable/StyledSubtitle";
import InputIconContainer from "../../reusable/InputIconContainer";

import translate from "../../reusable/translate";

import type {
  MeterType,
  OperationType,
  CommitmentType
} from "../../../constants/types";

const StyledEditButton = styled.div`
  padding: 10px;
  padding-right: 0;
  position: relative;
  &:hover {
    color: #777;
    cursor: pointer;
  }
  &:active {
    color: #444;
    top: 1px;
  }
`;

type State = {
  meter: MeterType,
  expanded: boolean,
  isNewMeter: boolean,
  hasLocalization: boolean
};

type Props = {
  index: number,
  meterInfoText: {
    id: number,
    info: string,
    placeholder: string
  },
  locale: "fi_FI" | "en_US", // "sv_SE" |
  hasEnglishWithoutFinnish?: boolean,
  meter: MeterType,
  acceptedMeterChartTypes: Array<string>,
  operation: OperationType,
  removeMeter: MeterType => void,
  updateMeter: MeterType => void,
  error: any,
  newOpMeters: Array<MeterType>,
  isNewOperation: ?boolean,
  operationMeters?: Array<MeterType>,
  commitment: CommitmentType,
  plasticBagMeters: Array<MeterType>,
  nutritionMeters: Array<MeterType>
};

const isNewMeter = (meter: MeterType, props: Props) => {
  const m = String(meter[`meterType_fi_FI`]);

  const found =
    (props.operationMeters &&
      props.operationMeters.some(
        opMeter => String(opMeter[`meterType_fi_FI`]) === m
      )) ||
    props.newOpMeters.some(newOpMeter => {
      const f = String(newOpMeter[`meterType_fi_FI`]) === m;

      return f;
    });
  return !Boolean(found);
};

class Meter extends Component<Props, State> {
  state = {
    meter: this.props.meter,
    expanded: true,
    isNewMeter: false,
    hasLocalization: false
  };

  static getDerivedStateFromProps = (props: Props, state: State) => {
    if (
      props.locale !== "fi_FI" &&
      get(state.meter, `meterType_${props.locale}`, "") === ""
    ) {
      if (state.meter.meterType_fi_FI && !state.meter.meterType_en_US) {
        return {
          expanded: true,
          meter: {
            ...state.meter,
            meterType_fi_FI: state.meter.meterType_fi_FI,
            meterType_en_US: state.meter.meterType_fi_FI,
            meterType_sv_SE: state.meter.meterType_fi_FI
          }
        };
      } else {
        return {
          expanded: true
        };
      }
    } else if (
      state.meter.meterId &&
      props.locale === "fi_FI" &&
      state.meter[`meterType_${props.locale}`] !== ""
    ) {
      return { expanded: false };
    } else return null;
  };

  componentDidMount() {
    let meterWithTranslations = null;
    if (this.props.operationMeters) {
      meterWithTranslations = this.props.operationMeters.find(meter => {
        return (
          meter[`meterType_fi_FI`] === this.props.meter[`meterType_fi_FI`] ||
          meter[`meterType_en_US`] === this.props.meter[`meterType_en_US`] ||
          meter[`meterType_sv_SE`] === this.props.meter[`meterType_sv_SE`]
        );
      });
      if (!meterWithTranslations) {
        meterWithTranslations = this.props.newOpMeters.find(meter => {
          return (
            meter[`meterType_fi_FI`] === this.props.meter[`meterType_fi_FI`] ||
            meter[`meterType_en_US`] === this.props.meter[`meterType_en_US`] ||
            meter[`meterType_sv_SE`] === this.props.meter[`meterType_sv_SE`]
          );
        });
      }
    }
    const updatedMeter = {
      ...this.props.meter,
      meterType_fi_FI: get(
        meterWithTranslations,
        "meterType_fi_FI",
        this.props.meter.meterType_fi_FI
      ),

      meterType_en_US: get(
        meterWithTranslations,
        "meterType_en_US",
        this.props.meter.meterType_en_US
      ),
      meterType_sv_SE: get(
        meterWithTranslations,
        "meterType_sv_SE",
        this.props.meter.meterType_sv_SE
      )
    };
    this.setState({
      meter: updatedMeter,
      hasLocalization: this.props.meter[`meterType_${this.props.locale}`]
        ? true
        : false,
      isNewMeter: this.state.meter.meterId
        ? isNewMeter(this.state.meter, this.props)
        : false,
      expanded: this.props.meter.meterId ? false : true
    });
    this.props.updateMeter(updatedMeter);
  }

  handleInput = (e: SyntheticInputEvent<any>) => {
    const { value, name } = e.target;
    this.setState(
      // $FlowFixMe
      produce(draft => {
        set(draft.meter, name, value);
      }),
      () => {}
    );
  };

  getMeterValue = () => {
    let availableMeters = [];
    if (this.props.operationMeters)
      availableMeters.push(...this.props.operationMeters);
    if (this.props.newOpMeters) availableMeters.push(...this.props.newOpMeters);
    const meter =
      availableMeters &&
      availableMeters.find(
        m =>
          String(m[`meterType_fi_FI`]) ===
            String(this.state.meter[`meterType_fi_FI`]) ||
          String(m[`meterType_en_US`]) ===
            String(this.state.meter[`meterType_en_US`]) ||
          String(m[`meterType_sv_SE`]) ===
            String(this.state.meter[`meterType_sv_SE`])
      );

    if (meter) return String(meter[`meterType_${this.props.locale}`]);
  };

  generateKeys(data: any) {
    const keyed = data.map(d => {
      return {
        key: String(Math.random()),
        ...d
      };
    });
    return keyed;
  }

  render = () => {
    const { removeMeter, error } = this.props;
    const { meter, expanded, isNewMeter } = this.state;
    if (meter === null) return null;

    const operationMeters =
      this.props.commitment.id || !this.props.isNewOperation
        ? this.generateKeys(this.props.operationMeters)
        : this.props.newOpMeters;

    const meters =
      this.props.commitment.commitmentType === "NUTRITION"
        ? this.props.nutritionMeters
        : this.props.commitment.commitmentType === "GREEN_DEAL" &&
          this.props.commitment.categoryIds.find(id => id === 33556)
          ? this.props.plasticBagMeters
          : this.props.commitment.commitmentType === "GREEN_DEAL" &&
            !this.props.commitment.categoryIds.find(id => id === 33556)
            ? operationMeters
            : this.props.isNewOperation === true
              ? this.props.newOpMeters
              : operationMeters;

    if (!expanded && !(isNewMeter && !this.state.hasLocalization)) {
      return (
        <div
          style={{
            padding: 10,
            border: "2px solid #93BE38",
            marginBottom: "12px"
          }}
        >
          <Row
            style={{
              padding: 10
            }}
          >
            <h4
              style={{
                margin: 0,
                paddingTop: 10,
                paddingBottom: 10,
                fontWeight: "bold"
              }}
            >
              {meter[`meterType_${this.props.locale}`]}
            </h4>

            <StyledEditButton
              onClick={() => {
                this.setState({
                  expanded: !this.state.expanded
                });
              }}
            >
              <i className="far fa-edit" />
            </StyledEditButton>
          </Row>

          {meter.meterValueType === "NUMBER" ? (
            <Fragment>
              <Row justifyContent="space-between" style={{ padding: 10 }}>
                <strong>
                  {translate({
                    languageOverride: this.props.locale,
                    textKey: "sit.commitment.reports.startingLevel"
                  })}:
                </strong>{" "}
                {get(meter, "startingLevel")}
                <strong>
                  {translate({
                    languageOverride: this.props.locale,
                    textKey: "sit.commitment.reports.targetLevel"
                  })}:
                </strong>{" "}
                {get(meter, "targetLevel")}
              </Row>
              <WhiteSpace height="5px" />
              {meter &&
                meter.meterChartType && (
                  <Row justifyContent="initial" style={{ padding: 10 }}>
                    <strong style={{ marginRight: 10 }}>
                      {translate({
                        languageOverride: this.props.locale,
                        textKey: "sit.commitment.reports.meterType"
                      })}:{" "}
                    </strong>
                    {translate({
                      languageOverride: this.props.locale,
                      textKey: `sit.statistics.chartTypes.${get(
                        meter,
                        "meterChartType"
                      )}`
                    })}
                  </Row>
                )}
            </Fragment>
          ) : (
            <Row justifyContent="space-between" style={{ padding: 10 }}>
              {translate({
                languageOverride: this.props.locale,
                textKey: "sit.commitment.reports.fullFilled"
              })}{" "}
              /{" "}
              {translate({
                languageOverride: this.props.locale,
                textKey: "sit.commitment.reports.notFullfilled"
              })}
            </Row>
          )}
        </div>
      );
    }

    if (expanded || (isNewMeter && !this.state.hasLocalization))
      return (
        <form
          data-testid="expanded_meter_form"
          style={{ border: "2px dashed #93BE38", padding: 10 }}
          onSubmit={e => {
            e.preventDefault();
            this.setState({
              expanded: false,
              hasLocalization: true
            });
            this.props.updateMeter(this.state.meter);
          }}
        >
          <Row justifyContent="space-between">
            <StyledSubtitle>
              {translate({
                languageOverride: this.props.locale,
                textKey: "sit.commitment.reports.chooseOrGiveMeter"
              })}
            </StyledSubtitle>
            <InfoIcon
              info={this.props.meterInfoText}
              selectedLocale={this.props.locale}
            />
          </Row>

          <RadioButtonContainer onChange={() => {}}>
            <Row justifyContent="flex-start">
              <RadioButton
                id={`choose_existing_meter_${this.props.index}_${
                  meter.meterId ? String(meter.meterId) : ""
                }`}
                value=""
                disabled={
                  this.props.locale !== "fi_FI" &&
                  !this.props.hasEnglishWithoutFinnish
                }
                checked={!isNewMeter}
                label={translate({
                  languageOverride: this.props.locale,
                  textKey: "sit.commitment.reports.existing"
                })}
                onChange={() =>
                  this.setState(
                    // $FlowFixMe
                    produce(draft => {
                      draft.isNewMeter = false;
                      draft.meter.startingLevel = "";
                      draft.meter.targetLevel = "";
                      draft.meter.meterType = "";
                      draft.meter.meterType_en_US = "";
                      draft.meter.meterType_sv_SE = "";
                      draft.meter.meterType_fi_FI = "";
                    })
                  )
                }
                style={{
                  marginBottom: "-1px",
                  minWidth: 40,
                  marginRight: 20,
                  width: "100%"
                }}
              />
              <RadioButton
                id={`choose_new_meter_${this.props.index}_${
                  meter.meterId ? String(meter.meterId) : ""
                }`}
                value=""
                disabled={
                  this.props.locale !== "fi_FI" &&
                  !this.props.hasEnglishWithoutFinnish
                }
                checked={isNewMeter}
                label={translate({
                  languageOverride: this.props.locale,
                  textKey: "sit.commitment.reports.new"
                })}
                onChange={() =>
                  this.setState(
                    // $FlowFixMe
                    produce(draft => {
                      draft.isNewMeter = true;
                      draft.meter.meterValueType = "NUMBER"; // set type to number by default for new meters
                      draft.meter.startingLevel = "";
                      draft.meter.targetLevel = "";
                      draft.meter.meterType = "";
                      draft.meter.meterType_en_US = "";
                      draft.meter.meterType_sv_SE = "";
                      draft.meter.meterType_fi_FI = "";
                    })
                  )
                }
                style={{ marginBottom: "-1px", minWidth: 40, width: "100%" }}
              />
            </Row>
          </RadioButtonContainer>

          <WhiteSpace height="10px" />

          {error &&
            error.target === `meter_${String(meter.key)}` && (
              <Fragment>
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
                <WhiteSpace height="20px" />
              </Fragment>
            )}

          {!isNewMeter && (
            <div>
              <Row justifyContent="space-between">
                <AutoComplete
                  items={meters}
                  namePath={`meterType_${this.props.locale}`}
                  keyPath={"key"}
                  onChange={m => {
                    this.setState(
                      // $FlowFixMe
                      produce(draft => {
                        const meter = {
                          ...m,
                          id: "",
                          key: this.state.meter.key
                        };
                        draft.meter = meter;
                      })
                    );
                  }}
                  value={this.getMeterValue()}
                  renderInput={({ getInputProps, toggleMenu, isOpen }) => (
                    <TextInput
                      {...getInputProps({
                        name: "meterDropdown",
                        type: "text",
                        label: (
                          <span>
                            <span style={{ marginRight: "6px" }}>
                              {translate({
                                languageOverride: this.props.locale,
                                textKey: "sit.commitment.reports.chooseMeter"
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
            </div>
          )}

          <WhiteSpace height="10px" />

          {isNewMeter && (
            <Fragment>
              <StyledSubtitle style={{ paddingBottom: 4 }}>
                {translate({
                  languageOverride: this.props.locale,
                  textKey: "sit.commitment.reports.nameOfNewMeter"
                })}
              </StyledSubtitle>
              <TextInput
                id={`new_meter_name_${this.props.index}`}
                value={meter[`meterType_${this.props.locale}`]}
                required
                type="text"
                name={`meterType_${this.props.locale}`}
                onChange={v => {
                  const value = get(v, "target.value");
                  this.setState(
                    // $FlowFixMe
                    produce(draft => {
                      draft.meter[`meterType_${this.props.locale}`] = value;
                    })
                  );
                }}
                placeholder={translate({
                  languageOverride: this.props.locale,
                  textKey: "sit.commitment.reports.nameOfNewMeterPlaceholder"
                })}
              />
            </Fragment>
          )}

          {isNewMeter && (
            <Fragment>
              <StyledSubtitle style={{ paddingBottom: 4 }}>
                {translate({
                  languageOverride: this.props.locale,
                  textKey: "sit.commitment.reports.meterReportingType"
                })}
              </StyledSubtitle>
              <RadioButtonContainer
                required
                onChange={e => {
                  const value = get(e, "target.value");
                  this.setState(
                    // $FlowFixMe
                    produce(draft => {
                      if (value === "REALIZED") {
                        draft.meter.meterValues = undefined;
                        draft.meter.startingLevel = "";
                        draft.meter.targetLevel = "";
                      }
                      draft.meter.meterValueType = value;
                    })
                  );
                }}
              >
                <RadioButton
                  required
                  disabled={
                    this.props.locale !== "fi_FI" &&
                    !this.props.hasEnglishWithoutFinnish
                  }
                  checked={this.state.meter.meterValueType === "NUMBER"}
                  name="meterValueType"
                  value="NUMBER"
                  label={translate({
                    languageOverride: this.props.locale,
                    textKey: "sit.commitment.reports.number"
                  })}
                  id={`meter_id_number_${this.props.index}`}
                  style={{ marginBottom: "-1px" }}
                />

                <RadioButton
                  required
                  disabled={
                    this.props.locale !== "fi_FI" &&
                    !this.props.hasEnglishWithoutFinnish
                  }
                  checked={this.state.meter.meterValueType === "REALIZED"}
                  name="meterValueType"
                  value="REALIZED"
                  style={{ marginBottom: "-1px" }}
                  label={`${translate({
                    languageOverride: this.props.locale,
                    textKey: "sit.commitment.reports.fullFilled"
                  })} / ${translate({
                    languageOverride: this.props.locale,
                    textKey: "sit.commitment.reports.notFullfilled"
                  })}`}
                  id={`meter_id_bool_${this.props.index}`}
                />
              </RadioButtonContainer>
            </Fragment>
          )}

          {meter.meterValueType === "NUMBER" && (
            <Fragment>
              <WhiteSpace height="10px" />
              <Row alignItems="center">
                <TextInput
                  required
                  disabled={
                    this.props.locale !== "fi_FI" &&
                    !this.props.hasEnglishWithoutFinnish
                  }
                  data-testid={`materStartingLevelId_${this.props.index}`}
                  value={get(meter, "startingLevel", "")}
                  style={{
                    border: "1px solid #ccc",
                    borderRadius: "4px"
                  }}
                  minHeight="40px"
                  name="startingLevel"
                  type="number"
                  placeholder={translate({
                    languageOverride: this.props.locale,
                    textKey: "sit.commitment.reports.setStartingLevel"
                  })}
                  onChange={e => this.handleInput(e)}
                />
                <span style={{ padding: "10px" }}>
                  <i className="fas fa-chevron-right" />
                </span>

                <TextInput
                  required
                  disabled={
                    this.props.locale !== "fi_FI" &&
                    !this.props.hasEnglishWithoutFinnish
                  }
                  data-testid={`materTargetLevelId_${this.props.index}`}
                  value={get(meter, "targetLevel", "")}
                  style={{
                    border: "1px solid #ccc",
                    borderRadius: "4px"
                  }}
                  minHeight="40px"
                  name="targetLevel"
                  type="number"
                  placeholder={translate({
                    languageOverride: this.props.locale,
                    textKey: "sit.commitment.reports.setTargetLevel"
                  })}
                  onChange={e => this.handleInput(e)}
                />
              </Row>
              <WhiteSpace height="10px" />
              <Row>
                <select
                  id={`meter_chart_type_select_${this.props.index}`}
                  data-testid={`meter_chart_type_select_${this.props.index}`}
                  required
                  disabled={
                    this.props.locale !== "fi_FI" &&
                    !this.props.hasEnglishWithoutFinnish
                  }
                  style={{ height: "48px" }}
                  onChange={e => {
                    const newValue = e.target.value;
                    this.setState(
                      // $FlowFixMe
                      produce(draft => {
                        draft.meter.meterChartType = newValue;
                      })
                    );
                  }}
                  value={get(meter, "meterChartType", "")}
                >
                  <option value="">
                    {translate({
                      languageOverride: this.props.locale,
                      textKey: "sit.commitment.reports.selectGraphType"
                    })}
                  </option>
                  {this.props.acceptedMeterChartTypes &&
                    this.props.acceptedMeterChartTypes.map(
                      (chartType, index) => {
                        return (
                          <option
                            key={
                              meter[`meterType_${this.props.locale}`] +
                              String(this.props.operation.key) +
                              String(this.props.meter.key) +
                              chartType
                            }
                            value={chartType}
                          >
                            {translate({
                              languageOverride: this.props.locale,
                              textKey: `sit.statistics.chartTypes.${chartType}`
                            })}
                          </option>
                        );
                      }
                    )}
                </select>
              </Row>
            </Fragment>
          )}

          <WhiteSpace height="20px" />

          <Row
            justifyContent="space-between"
            style={{ borderTop: "1px solid #eee", paddingTop: 10 }}
          >
            <SecondaryButton
              type="button"
              disabled={
                this.props.locale !== "fi_FI" &&
                !this.props.hasEnglishWithoutFinnish
              }
              onClick={e => {
                e.preventDefault();
                if (
                  this.props.locale === "fi_FI" &&
                  !this.props.hasEnglishWithoutFinnish
                ) {
                  removeMeter(meter);
                }
              }}
              style={{
                height: 36,
                minWidth: 140,
                width: 140,
                color: "red"
              }}
            >
              {translate({
                languageOverride: this.props.locale,
                textKey: "sit.commitment.reports.removeMeter"
              })}
            </SecondaryButton>

            <Button
              data-testid={`close_meter_${this.props.index}`}
              style={{ height: 36, minWidth: 120, width: 120 }}
              type="submit"
            >
              {translate({
                languageOverride: this.props.locale,
                textKey: "sit.commitment.reports.ready"
              })}
            </Button>
          </Row>
        </form>
      );
  };
}

export default Meter;
