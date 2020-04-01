//@flow

import type {
  ReportType,
  CommitmentType,
  MeterType
} from "../../constants/types";

import React, { Component, Fragment } from "react";
import styled from "styled-components";
import moment from "moment";

import getLocalizedString from "../../utils/getLocalizedString";
import { isHundredTodosCommitment } from "../../utils/hundredTodos";

import Row from "../reusable/Row";
import Column from "../reusable/Column";
import StyledSubtitle from "../reusable/StyledSubtitle";
import WhiteSpace from "../reusable/WhiteSpace";
import ReportProgressIcon from "../reusable/ReportProgressIcon";
import translate from "../reusable/translate";

import DateLineChart from "../statistics/DateLineChart";
import BarChart from "../statistics/BarChart";
import PieChart from "../statistics/PieChart";

const ReportContent = styled.div`
  padding: 20px;
  display: flex;
  flex: 1 1 auto;
  flex-direction: column;
  border: 2px solid #93be38;
  border-top: 0;
  margin: 0px;
`;

const ProgressColumn = styled.div`
  display: flex;
  flex: 2 0 auto;
  flex-direction: column;
  margin-left: 50px;
`;

const CommitmentText = styled.span`
  font-size: 14px;
  color: #212121;
  display: flex;
  flex: 0 0 auto;
  flex-direction: column;
`;

const HorizontalSeparator = styled.div`
  background: #dcdcdc;
  height: 1px;
  margin-top: 15px;
  margin-bottom: 15px;
`;

const HighlightTableRow = styled(Row)`
  flex-flow: column;
  align-items: flex-start;
  padding-left: 20px;
  min-height: 64px;
  border-left: 2px solid #93be38;
  border-right: 2px solid #93be38;
  border-top: 5px solid #93be38;
  border-bottom: 1px solid #dcdcdc;
  &:hover {
    background: #93be38;
    cursor: pointer;
    color: white;
  }
`;

class ReportPanel extends Component<
  {
    report: ReportType,
    reportIndex?: number,
    expanded?: boolean,
    graphHistory?: {
      [string]: {
        values: Array<number>,
        labels: Array<string>,
        texts: Array<string>
      }
    },
    commitment: ?CommitmentType,
    meterChartTypeList?: { [string]: string }
  },
  {
    expanded: boolean
  }
> {
  state = {
    expanded: this.props.expanded || false
  };

  prepareData = (report: ReportType, meter: MeterType) => {
    let meterChartType;
    const meterId = meter ? meter.commitmentOperationMeterRefId || "" : "";
    const reportIndex = this.props.reportIndex || 0;
    if (this.props.meterChartTypeList) {
      meterChartType = meterId ? this.props.meterChartTypeList[meterId] : null;
    }
    if (meterChartType && meter.meterValueType === "NUMBER") {
      let labels = [];
      let values = [];
      let texts = [];

      if (meterChartType === "LINE" || meterChartType === "COLUMN") {
        let historicalValues = [];
        let historicalTexts = [];
        let historicalLabels = [];
        if (this.props.graphHistory && this.props.graphHistory[meterId]) {
          historicalValues = this.props.graphHistory[meterId].values;
          historicalTexts = this.props.graphHistory[meterId].texts;
          historicalLabels = this.props.graphHistory[meterId].labels;
        }

        values = [
          meter.startingLevel || 0,
          ...(historicalValues &&
            historicalValues.slice(0, historicalValues.length - reportIndex)),
          parseInt(meter.currentLevel, 10) || 0,
          meter.targetLevel || 0
        ];
        texts = [
          translate({
            textKey: "sit.commitment.reports.startingLevel"
          }) || "Lähtötaso",
          ...(historicalTexts &&
            historicalTexts.slice(0, historicalTexts.length - reportIndex)),
          translate({
            textKey: "sit.commitment.reports.currentLevel"
          }) || "Nykytaso",
          translate({
            textKey: "sit.commitment.reports.targetLevel"
          }) || "Tavoitetaso"
        ];
        if (meterChartType === "LINE") {
          const commitmentStartDate = this.props.commitment
            ? this.props.commitment.startDate
            : "";
          const commitmentEndDate = this.props.commitment
            ? this.props.commitment.endDate
            : "";
          labels = [
            moment(commitmentStartDate).toISOString(),
            ...(historicalLabels &&
              historicalLabels.slice(0, historicalLabels.length - reportIndex)),
            report.reportEndDate,
            moment(commitmentEndDate).toISOString()
          ];
        } else {
          labels = texts;
        }
      } else if (meterChartType === "PIE") {
        const targetLevel = meter.targetLevel || 0;
        values = [
          parseInt(meter.currentLevel, 10) || 0,
          targetLevel && targetLevel > parseInt(meter.currentLevel, 10)
            ? targetLevel - parseInt(meter.currentLevel, 10)
            : parseInt(meter.currentLevel, 10) - targetLevel
        ];
        labels = [
          translate({
            textKey: "sit.commitment.reports.currentLevel"
          }),
          translate({
            textKey: "sit.commitment.reports.leftToTargetLevel"
          })
        ];
      }

      return { meterChartType, values, labels, texts };
    }
  };

  render() {
    const { report, commitment } = this.props;
    const formattedStartDate = report.reportStartDate
      ? moment(report.reportStartDate)
          .format("l")
          .toString()
      : "";
    const formattedEndDate = report.reportEndDate
      ? moment(report.reportEndDate)
          .format("l")
          .toString()
      : "";

    const isHundredTodos = commitment && isHundredTodosCommitment(commitment);

    const hundredTodosLevels = () => {
      const doneOperationIds =
        commitment && commitment.doneOperations
          ? commitment.doneOperations.map(el => el.operationId)
          : [];

      const sumMeters = field => {
        const summer = (sum, meter) => {
          const value = meter[field];
          return value ? parseFloat(value) + sum : sum;
        };

        return (
          report.reportMeters &&
          report.reportMeters
            .filter(
              meter =>
                !doneOperationIds.includes(meter.commitmentOperationMeterRefId)
            )
            .reduce(summer, 0)
        );
      };

      return [sumMeters("currentLevel"), sumMeters("targetLevel")];
    };

    const normalLevels = () => {
      const currentLevel =
        report.reportMeters && report.reportMeters[0].currentLevel
          ? parseInt(report.reportMeters[0].currentLevel, 10)
          : -1;
      const targetLevel =
        report.reportMeters && report.reportMeters[0].targetLevel
          ? parseInt(report.reportMeters[0].targetLevel, 10)
          : -1;
      return [currentLevel, targetLevel];
    };

    const [currentLevel, targetLevel] = isHundredTodos
      ? hundredTodosLevels()
      : normalLevels();

    return (
      <Fragment>
        <HighlightTableRow
          onClick={() =>
            this.setState({
              expanded: !this.state.expanded
            })
          }
        >
          <StyledSubtitle showBottomBorder={false} style={{ fontSize: 15 }}>
            {getLocalizedString("reportTitle", report)}
          </StyledSubtitle>
        </HighlightTableRow>

        {this.state.expanded && (
          <ReportContent>
            <Row>
              <Column flex="7 1 auto">
                <StyledSubtitle
                  showBottomBorder={true}
                  paddingBottom="20px"
                  flex="0 0 auto"
                >
                  {`${translate({
                    textKey: "sit.commitment.reports.followupPeriod"
                  })} ${formattedStartDate} - ${formattedEndDate}`}
                </StyledSubtitle>
                <CommitmentText
                  dangerouslySetInnerHTML={{
                    __html: getLocalizedString("reportText", report)
                  }}
                />
                <HorizontalSeparator />
              </Column>
              <ProgressColumn>
                <StyledSubtitle flex="0 0 auto" paddingBottom="5px">
                  {translate({
                    textKey: "sit.commitment.reports.achievedOutOfTarget"
                  })}
                </StyledSubtitle>
                <CommitmentText
                  style={{
                    color: "#93be38",
                    paddingBottom: "10px",
                    fontWeight: "bold"
                  }}
                >
                  {isHundredTodos &&
                    `${translate({
                      textKey: "sit.commitment.reports.achieved"
                    }) || "Saavutettu"} ${currentLevel.toFixed(
                      1
                    )}% / ${translate({
                      textKey: "sit.commitment.reports.outOfTarget"
                    }) || "tavoitteesta"} ${targetLevel.toFixed(1)}%`}
                  {!isHundredTodos &&
                    `${translate({
                      textKey: "sit.commitment.reports.achieved"
                    }) || "Saavutettu"} ${currentLevel} / ${translate({
                      textKey: "sit.commitment.reports.outOfTarget"
                    }) || "tavoitteesta"} ${targetLevel}`}
                </CommitmentText>
                <HorizontalSeparator />
                <StyledSubtitle flex="0 0 auto" paddingBottom="12px">
                  {translate({
                    textKey: "sit.commitment.reports.progressLevel"
                  })}
                </StyledSubtitle>
                {report.progress && report.progress !== "none" ? (
                  <Row justifyContent="flex-start" flex="0 0 auto">
                    <ReportProgressIcon
                      key={report.id}
                      icon={report.progress}
                      alt={report.progress}
                      title={report.progress}
                    />
                    <WhiteSpace width="12px" />
                    <CommitmentText>
                      {translate({
                        textKey: `sit.commitment.reports.${report.progress}`
                      })}
                    </CommitmentText>
                  </Row>
                ) : (
                  <CommitmentText>
                    {translate({ textKey: `sit.commitment.reports.none` })}
                  </CommitmentText>
                )}
              </ProgressColumn>
            </Row>

            {!isHundredTodos &&
              report.reportMeters &&
              report.reportMeters.map((meter, index) => {
                const data =
                  this.props.graphHistory && this.prepareData(report, meter);

                const chartHeight = "300px";
                const chartWidth = "500px";
                if (data) {
                  return (
                    <Column
                      key={`${report.id}_${index}`}
                      style={{ justifyContent: "flex-start" }}
                    >
                      <WhiteSpace height="30px" />
                      <StyledSubtitle>
                        {getLocalizedString("meterType", meter)}
                      </StyledSubtitle>
                      {data.meterChartType === "PIE" && (
                        <PieChart
                          style={{ height: chartHeight, width: chartHeight }}
                          margin={true}
                          disableLeftMargin={true}
                          labels={data.labels}
                          values={data.values}
                          legendXPosition={1}
                          texts={data.texts}
                        />
                      )}
                      {data.meterChartType === "COLUMN" && (
                        <BarChart
                          style={{ height: chartHeight, width: chartWidth }}
                          margin={true}
                          disableLeftMargin={true}
                          labels={data.labels}
                          values={data.values}
                          texts={data.texts}
                        />
                      )}
                      {data.meterChartType === "LINE" && (
                        <DateLineChart
                          style={{ height: chartHeight, width: chartWidth }}
                          margin={true}
                          disableLeftMargin={true}
                          mode="lines+markers"
                          dates={data.labels}
                          tickFormat={"%d.%m.%Y"}
                          values={data.values}
                          texts={data.texts}
                        />
                      )}
                    </Column>
                  );
                } else {
                  return null;
                }
              })}
            <Row />
          </ReportContent>
        )}
      </Fragment>
    );
  }
}

export default ReportPanel;
