// @flow

import type {
  ReportType,
  MeterType,
  PROFILE_TYPE,
  CommitmentType,
  OperationType,
  NotificationType,
  SMARTWAY_ARTICLE_TYPE
} from "../../../constants/types";

import { ReportProgressMap } from "../../../constants/types";

import React, { Component, Fragment } from "react";
import produce from "immer";
import moment from "moment";
import styled from "styled-components";
import { get } from "lodash";
import DatePicker from "react-datepicker";

import Column from "../../reusable/Column";
import DatePickerContainer from "../../reusable/DatePickerContainer";

import NotificationContainer from "../../reusable/NotificationContainer";
import { RadioButtonContainer, RadioButton } from "../../reusable/Radio";
import Row from "../../reusable/Row";
import StyledSubtitle from "../../reusable/StyledSubtitle";
import TextInput from "../../reusable/TextInput";
import WhiteSpace from "../../reusable/WhiteSpace";
import Button from "../../reusable/Button";
import ReportProgressIcon from "../../reusable/ReportProgressIcon";
import translate from "../../reusable/translate";
import getLocalizedString from "../../../utils/getLocalizedString";
import { isHundredTodosCommitment } from "../../../utils/hundredTodos";
import HundredTodosEditor from "../createCommitment/HundredTodos/HundredTodosEditor";

import { connect } from "react-redux";
import type { STORE_STATE } from "../../../redux/store";

const CenteredLabel = styled.label`
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  position: relative;
  z-index: 0;
  border: ${props =>
    props.selected ? "2px solid #93be38" : "2px solid transparent"};
  padding: 20px 10px 20px 10px;
  border-radius: 8px;
`;

const SubTitle = styled.span`
  font-size: 14px;
  color: #6b6b6b;
`;

class ReportEditor extends Component<
  {
    reportIndexInOperation?: number,
    operationIndexInCommitment: number,
    commitment: CommitmentType,
    operation: OperationType,
    report?: ReportType,
    saveReport: (ReportType, number, ?number) => void,
    resetSaveReportNotification: () => void,
    saveReportNotification: NotificationType,
    savedReport: ?ReportType,
    profile: PROFILE_TYPE,
    editing?: boolean,
    hundredSmartWaysArticles: Array<SMARTWAY_ARTICLE_TYPE>
  },
  {
    report: ReportType,
    routeActive: boolean
  }
> {
  initReportMeters = () => {
    let meters = null;
    if (this.props.editing) {
      meters = this.props.report && this.props.report.reportMeters;
    } else if (isHundredTodosCommitment(this.props.commitment)) {
      const latestReport = this.findPreviousReport("");
      meters = latestReport && latestReport.reportMeters;
    }

    return (
      meters ||
      this.props.operation.meters.map(meter => {
        return {
          ...meter,
          commitmentOperationMeterRefId: meter.meterId
        };
      })
    );
  };

  findPreviousReport = (reportId: string) => {
    const op = this.props.operation;

    if (op && op.reports && op.reports.length > 0) {
      const currIndex = op.reports.findIndex(el => el.id === reportId);
      if (currIndex === 0) {
        return null;
      } else if (currIndex > 0) {
        return op.reports && op.reports[currIndex - 1];
      } else {
        return op.reports && op.reports[op.reports.length - 1]; // return latest
      }
    }

    return null;
  };

  state = {
    routeActive: false,
    report: {
      reportMeters: this.initReportMeters(),
      commitmentArticleId: this.props.commitment.id,
      commitmentOperationRefId: this.props.operation.operationId,
      createdByUserId: get(this.props.profile, "userId", ""),
      operationTitle_en_US: this.props.operation.operationTitle_en_US,
      operationTitle_fi_FI: this.props.operation.operationTitle_fi_FI,
      operationTitle_sv_SE: this.props.operation.operationTitle_sv_SE,
      id:
        (this.props.editing && this.props.report && this.props.report.id) || "",
      organizationName: this.props.commitment.organizationName || "",
      createdByUserName: get(this.props.profile, "screenName", ""),
      reportStartDate:
        (this.props.editing &&
          this.props.report &&
          this.props.report.reportStartDate) ||
        "",
      reportEndDate:
        (this.props.editing &&
          this.props.report &&
          this.props.report.reportEndDate) ||
        "",
      reportText_en_US:
        (this.props.editing &&
          this.props.report &&
          this.props.report.reportText_en_US) ||
        "",
      reportText_fi_FI:
        (this.props.editing &&
          this.props.report &&
          this.props.report.reportText_fi_FI) ||
        "",
      reportText_sv_SE:
        (this.props.editing &&
          this.props.report &&
          this.props.report.reportText_sv_SE) ||
        "",
      reportTitle_en_US:
        (this.props.editing &&
          this.props.report &&
          this.props.report.reportTitle_en_US) ||
        "",
      reportTitle_fi_FI:
        (this.props.editing &&
          this.props.report &&
          this.props.report.reportTitle_fi_FI) ||
        "",
      reportTitle_sv_SE:
        (this.props.editing &&
          this.props.report &&
          this.props.report.reportTitle_sv_SE) ||
        "",
      status:
        (this.props.editing && this.props.report && this.props.report.status) ||
        "draft",
      progress:
        (this.props.editing &&
          this.props.report &&
          this.props.report.progress) ||
        null,
      reportStatus:
        (this.props.editing &&
          this.props.report &&
          this.props.report.reportStatus) ||
        false,
      version:
        (this.props.editing &&
          this.props.report &&
          this.props.report.version) ||
        ""
    }
  };

  saveReport = (reportData: ReportType) => {
    const tempStartDateLocale = moment(reportData.reportStartDate);
    const tempEndDateLocale = moment(reportData.reportEndDate);
    tempStartDateLocale.locale("fi");
    tempEndDateLocale.locale("fi");
    const startDate = tempStartDateLocale.format("l");
    const endDate = tempEndDateLocale.format("l");
    tempStartDateLocale.locale("en");
    tempEndDateLocale.locale("en");
    const enStartDate = tempStartDateLocale.format("l");
    const enEndDate = tempEndDateLocale.format("l");

    reportData = {
      ...reportData,
      reportTitle_fi_FI: `${startDate}-${endDate} ${
        reportData.operationTitle_fi_FI
      }`,
      reportTitle_en_US: reportData.reportText_en_US
        ? `${enStartDate}-${enEndDate} ${reportData.operationTitle_en_US}`
        : ""
    };
    this.props.saveReport(
      reportData,
      this.props.operationIndexInCommitment,
      this.props.reportIndexInOperation
    );
  };

  componentDidMount() {
    this.props.resetSaveReportNotification();
  }

  // $FlowFixMe
  componentWillReceiveProps(nextProps) {
    const report = nextProps.operation.reports
      ? nextProps.reportIndexInOperation !== undefined
        ? nextProps.operation.reports[nextProps.reportIndexInOperation]
        : nextProps.operation.reports[nextProps.operation.reports.length - 1]
      : null;

    if (
      report &&
      report.id !== "" &&
      report.commitmentOperationRefId ===
        this.state.report.commitmentOperationRefId &&
      nextProps.saveReportNotification &&
      this.props.saveReportNotification.state !== "SUCCESS" &&
      nextProps.saveReportNotification.state === "SUCCESS"
    ) {
      this.setState({ report: report });
    }
  }

  updateReport = (report: ReportType): void => {
    this.setState(
      // $FlowFixMe
      produce(draft => {
        draft.report = report;
      })
    );
  };

  getLockedMeters = (): Array<MeterType & { commitmentOperationMeterRefId?: ?string }> => {
    const latestReport = this.findPreviousReport("");
    const meterIsDone = meter => meter.currentLevel === meter.targetLevel;
    if (this.props.editing) {
      const editingLatest =
        latestReport && latestReport.id === this.state.report.id;
      if (editingLatest) {
        const previousReport = this.findPreviousReport(this.state.report.id);
        return previousReport
          ? previousReport.reportMeters.filter(meterIsDone)
          : [];
      } else {
        return this.state.report.reportMeters;
      }
    } else {
      return latestReport ? latestReport.reportMeters.filter(meterIsDone) : [];
    }
  };

  render() {
    const isHundredTodos = isHundredTodosCommitment(this.props.commitment);
    return (
      <div style={{ padding: 20 }}>
        <form
          onSubmit={e => {
            e.preventDefault();
            this.saveReport(this.state.report);
          }}
        >
          <StyledSubtitle>
            {translate({
              textKey: "sit.profile.operationReport.selectReportTime"
            })}
          </StyledSubtitle>

          <Row>
            <div className="container">
              <div className="col-md-6">
                <DatePickerContainer>
                  <DatePicker
                    required
                    showMonthDropdown
                    showYearDropdown
                    dropdownMode="select"
                    minDate={moment(this.props.commitment.startDate)}
                    maxDate={moment(this.props.commitment.endDate)}
                    selected={
                      this.state.report.reportStartDate
                        ? moment(this.state.report.reportStartDate)
                        : null
                    }
                    placeholderText={translate({
                      textKey: "sit.commitment.operations.starting"
                    })}
                    onChange={val => {
                      this.setState(
                        // $FlowFixMe
                        produce(draft => {
                          draft.report.reportStartDate = val;
                          draft.report.reportEndDate =
                            draft.report.reportEndDate &&
                            draft.report.reportStartDate >
                              draft.report.reportEndDate
                              ? val.toJSON()
                              : draft.report.reportEndDate;
                        })
                      );
                    }}
                    customInput={
                      <Row
                        justifyContent="flex-start"
                        alignItems="center"
                        style={{ position: "relative" }}
                      >
                        <input
                          defaultValue={this.state.report.reportStartDate}
                          style={{
                            opacity: 0,
                            position: "absolute"
                          }}
                        />

                        <i
                          className="fas fa-calendar-alt"
                          style={{
                            marginRight: "6px",
                            fontSize: 18,
                            cursor: "pointer"
                          }}
                        />
                        <span
                          style={{
                            fontSize: 13,
                            marginLeft: "6px"
                          }}
                        >
                          {this.state.report.reportStartDate
                            ? moment(this.state.report.reportStartDate).format(
                                "DD.MM.YYYY"
                              )
                            : translate({
                                textKey:
                                  "sit.commitment.operations.chooseStartingDate"
                              })}
                        </span>
                      </Row>
                    }
                  />
                </DatePickerContainer>
              </div>
              <div className="col-md-6">
                <DatePickerContainer>
                  <DatePicker
                    required
                    showMonthDropdown
                    showYearDropdown
                    dropdownMode="select"
                    minDate={
                      this.state.report.reportStartDate
                        ? moment(this.state.report.reportStartDate)
                        : moment(this.props.commitment.startDate)
                    }
                    maxDate={moment(this.props.commitment.endDate)}
                    selected={
                      this.state.report.reportEndDate
                        ? moment(this.state.report.reportEndDate)
                        : null
                    }
                    placeholderText={translate({
                      textKey: "sit.commitment.operations.ending"
                    })}
                    onChange={val => {
                      this.setState(
                        // $FlowFixMe
                        produce(draft => {
                          draft.report.reportEndDate = val;
                        })
                      );
                    }}
                    customInput={
                      <Row
                        justifyContent="flex-start"
                        alignItems="center"
                        style={{ position: "relative" }}
                      >
                        <input
                          defaultValue={this.state.report.reportEndDate}
                          style={{
                            opacity: 0,
                            position: "absolute"
                          }}
                        />

                        <i
                          className="fas fa-calendar-alt"
                          style={{
                            marginRight: "6px",
                            fontSize: 18,
                            cursor: "pointer"
                          }}
                        />
                        <span
                          style={{
                            fontSize: 13,
                            marginLeft: "6px"
                          }}
                        >
                          {this.state.report.reportEndDate
                            ? moment(this.state.report.reportEndDate).format(
                                "DD.MM.YYYY"
                              )
                            : translate({
                                textKey:
                                  "sit.commitment.operations.chooseEndingDate"
                              })}
                        </span>
                      </Row>
                    }
                  />
                </DatePickerContainer>
              </div>
            </div>
          </Row>

          <WhiteSpace height="30px" />

          {this.state.report.reportStartDate &&
            this.state.report.reportEndDate && (
              <Fragment>
                <h4
                  style={{
                    color: "#777",
                    margin: 0
                  }}
                >
                  <i>
                    {moment(this.state.report.reportStartDate).format("l")}{" "}
                    {" - "}
                    {moment(this.state.report.reportEndDate).format("l")}{" "}
                    {/* title gets repeated for 100smartways commitment, so let's hide it here */}
                    {!isHundredTodos &&
                      getLocalizedString(
                        "operationTitle",
                        this.props.operation
                      )}
                  </i>
                  {isHundredTodos && (
                    <div style={{ paddingTop: 5 }}>
                      {translate({
                        textKey: "sit.profile.operationReport.100smartways.info"
                      })}
                    </div>
                  )}
                </h4>
                <WhiteSpace height="30px" />

                {isHundredTodos && (
                  <HundredTodosEditor
                    {...this.props}
                    updateOperation={(op: OperationType, index: number) => {}}
                    setDoneOperations={() => {}}
                    removeOperation={() => {}}
                    newOpMeters={[]}
                    locale={"fi_FI"}
                    infoTexts={{}}
                    index={0}
                    commitment={this.props.commitment}
                    toDo={true}
                    report={this.state.report}
                    updateReport={this.updateReport}
                    lockedMeters={this.getLockedMeters()}
                  />
                )}

                {!isHundredTodosCommitment(this.props.commitment) &&
                  this.props.operation.meters.map((meter, index) => (
                    <div key={meter.meterId}>
                      <WhiteSpace height="30px" />
                      <strong>
                        {translate({
                          textKey: "sit.profile.operationReport.meter"
                        })}{" "}
                        {index + 1}:
                      </strong>{" "}
                      {getLocalizedString("meterType", meter)}
                      <WhiteSpace height="10px" />
                      {meter.meterValueType === "NUMBER" && (
                        <Fragment>
                          <Row alignItems="center" justifyContent="flex-start">
                            <div
                              style={{
                                padding: 15,
                                border: "1px solid #ddd",
                                borderRadius: 6,
                                color: "#777"
                              }}
                            >
                              <i>
                                {translate({
                                  textKey:
                                    "sit.commitment.reports.startingLevel"
                                })}{" "}
                                {meter.startingLevel}
                              </i>
                            </div>
                            <WhiteSpace width="20px" />
                            <i className="fa fa-chevron-right" />
                            <WhiteSpace width="20px" />
                            <TextInput
                              name=""
                              value={
                                this.state.report.reportMeters[index]
                                  .currentLevel
                              }
                              onChange={e => {
                                const { value } = e.target;
                                this.setState(
                                  // $FlowFixMe
                                  produce(draft => {
                                    draft.report.reportMeters[
                                      index
                                    ].currentLevel = value;
                                  })
                                );
                              }}
                              required
                              type="number"
                              placeholder={translate({
                                textKey:
                                  "sit.profile.operationReport.realizedLevel"
                              })}
                              inputStyle={{
                                border: "1px solid #9eBe38",
                                borderRadius: "6px"
                              }}
                              style={{
                                maxWidth: 140
                              }}
                            />

                            <WhiteSpace width="20px" />
                            <i className="fa fa-chevron-right" />
                            <WhiteSpace width="20px" />

                            <div
                              style={{
                                padding: 15,
                                border: "1px solid #ddd",
                                borderRadius: 6,
                                color: "#777"
                              }}
                            >
                              <i>
                                {translate({
                                  textKey: "sit.commitment.reports.targetLevel"
                                })}{" "}
                                {meter.targetLevel}
                              </i>
                            </div>
                          </Row>
                        </Fragment>
                      )}
                      {meter.meterValueType === "REALIZED" && (
                        <Fragment>
                          <RadioButtonContainer>
                            <RadioButton
                              required
                              id={"realized_" + String(meter.meterId) + index}
                              name={String(meter.meterId) + index}
                              value="realized"
                              label={translate({
                                textKey: "sit.commitment.reports.fullFilled"
                              })}
                              checked={
                                get(
                                  this.state,
                                  `report.reportMeters[${index}].currentLevel`
                                ) === "realized"
                              }
                              onChange={e => {
                                const { value } = e;
                                this.setState(
                                  // $FlowFixMe
                                  produce(draft => {
                                    draft.report.reportMeters[
                                      index
                                    ].currentLevel = value;
                                  })
                                );
                              }}
                            />
                            <RadioButton
                              id={
                                "not_realized_" + String(meter.meterId) + index
                              }
                              required
                              name={String(meter.meterId) + index}
                              value="notRealized"
                              checked={
                                get(
                                  this.state,
                                  `report.reportMeters[${index}].currentLevel`
                                ) === "notRealized"
                              }
                              label={translate({
                                textKey: "sit.commitment.reports.notFullfilled"
                              })}
                              onChange={e => {
                                const { value } = e;
                                this.setState(
                                  // $FlowFixMe
                                  produce(draft => {
                                    draft.report.reportMeters[
                                      index
                                    ].currentLevel = value;
                                  })
                                );
                              }}
                            />
                          </RadioButtonContainer>
                        </Fragment>
                      )}
                    </div>
                  ))}

                <WhiteSpace height="60px" />

                <StyledSubtitle>
                  {translate({
                    textKey: "sit.profile.operationReport.progress"
                  })}
                </StyledSubtitle>

                <SubTitle>
                  {translate({
                    textKey: "sit.profile.operationReport.progressTitle"
                  })}
                </SubTitle>

                <Row justifyContent="flex-start" alignItems="flex-start">
                  <div className="row">
                    {ReportProgressMap.map(icon => (
                      <div className="col-md-2 col-sm-4 col-xs-6">
                        <Column
                          key={icon}
                          required
                          alignItems="center"
                          justifyContent="center"
                          style={{ maxWidth: 120 }}
                          onChange={e => {
                            const { value } = e.target;
                            this.setState(
                              // $FlowFixMe
                              produce(draft => {
                                draft.report.progress = value;
                              })
                            );
                          }}
                          value={this.state.report.progress}
                        >
                          <CenteredLabel
                            selected={this.state.report.progress === icon}
                          >
                            <input
                              required
                              defaultChecked={
                                get(this.state, `report.progress`) === icon
                              }
                              type="radio"
                              name="progress"
                              value={icon}
                              style={{
                                position: "absolute",
                                bottom: 0,
                                opacity: 0
                              }}
                            />
                            <ReportProgressIcon
                              style={{
                                zIndex: 1,
                                background: "white"
                              }}
                              icon={icon}
                              onClick={() => console.log("SELECT")}
                            />
                            <span
                              style={{
                                fontWeight: "normal",
                                fontSize: 13,
                                textAlign: "center",
                                lineHeight: "18px",
                                marginTop: 12
                              }}
                            >
                              {translate({
                                textKey: `sit.commitment.reports.${icon}`
                              })}
                            </span>
                          </CenteredLabel>
                        </Column>
                      </div>
                    ))}
                  </div>
                </Row>

                <StyledSubtitle>
                  {translate({
                    textKey: "sit.profile.operationReport.reportInFinnish"
                  })}
                </StyledSubtitle>

                <textarea
                  rows={10}
                  placeholder={translate({
                    textKey:
                      "sit.commitment.operationReport.writeReportInFinnish"
                  })}
                  style={{
                    width: "100%",
                    padding: 20,
                    fontWeight: "regular"
                  }}
                  value={this.state.report.reportText_fi_FI}
                  required
                  onChange={e => {
                    const { value } = e.target;
                    this.setState(
                      // $FlowFixMe
                      produce(draft => {
                        draft.report[`reportText_fi_FI`] = value;
                      })
                    );
                  }}
                />

                {!isHundredTodosCommitment(this.props.commitment) &&
                  this.props.commitment.title_en_US !== "" && (
                    <Fragment>
                      <StyledSubtitle>
                        {translate({
                          textKey: "sit.profile.operationReport.reportInEnglish"
                        })}
                      </StyledSubtitle>

                      <textarea
                        rows={10}
                        placeholder={translate({
                          textKey:
                            "sit.commitment.operationReport.writeReportInEnglish"
                        })}
                        style={{
                          width: "100%",
                          padding: 20,
                          fontWeight: "regular"
                        }}
                        required
                        value={this.state.report.reportText_en_US}
                        onChange={e => {
                          const { value } = e.target;
                          this.setState(
                            // $FlowFixMe
                            produce(draft => {
                              draft.report[`reportText_en_US`] = value;
                            })
                          );
                        }}
                      />
                    </Fragment>
                  )}

                <Button
                  type="submit"
                  disabled={
                    (this.props.saveReportNotification &&
                      this.props.saveReportNotification.state ===
                        "IN_PROGRESS") ||
                    (!this.props.editing && this.state.report.id !== "")
                  }
                >
                  {translate({
                    textKey: "sit.profile.operationReport.publishReport"
                  })}
                </Button>

                <NotificationContainer
                  notificationType={this.props.saveReportNotification}
                />
              </Fragment>
            )}
        </form>
      </div>
    );
  }
}

export default connect((state: STORE_STATE) => ({
  hundredSmartWaysArticles: state.articles.hundredSmartWaysArticles
}))(ReportEditor);
