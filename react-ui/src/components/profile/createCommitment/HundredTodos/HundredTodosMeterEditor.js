// @flow

import React from "react";

import type { MeterType } from "../../../../constants/types";

import Button from "../../../reusable/Button";
import { RoundIcon } from "../../../../components/commitmentList/styles";
import translate from "../../../reusable/translate";

const HundredTodosMeterEditor = (props: any) => {
  const setMeterLevel = (meter: MeterType, done: boolean) => {
    const updateMeter = el => {
      if (
        el.meterId === meter.meterId ||
        el.commitmentOperationMeterRefId === meter.meterId
      ) {
        return {
          ...el,
          currentLevel: done ? el.targetLevel : el.startingLevel
        };
      } else {
        return el;
      }
    };

    const updatedReportMeters = props.report.reportMeters.map(updateMeter);
    const updatedReport = {
      ...props.report,
      reportMeters: updatedReportMeters
    };
    props.updateReport(updatedReport);
    return false;
  };

  const { meter, report } = props;
  const reportedMeter =
    report &&
    report.reportMeters.find(
      m => m.commitmentOperationMeterRefId === meter.meterId
    );

  const isMeterEditable = (meter: MeterType) =>
    !(
      props.lockedMeters &&
      props.lockedMeters.find(
        el => el.commitmentOperationMeterRefId === meter.meterId
      )
    );
  const editable = isMeterEditable(meter); // raportointia varten
  const locale = "meterType_" + props.locale;

  return (
    meter && (
      <div
        className="row"
        style={{
          borderBottom: "1px solid grey",
          paddingLeft: "30px",
          lineHeight: "25px"
        }}
      >
        <div
          className="col-md-9 col-xs-6"
          style={{ display: "inline-block" }}
          data-testid={meter.meterId || meter.key}
        >
          {meter[locale]}
        </div>
        {props.toDo ? (
          <div
            className="col-md-3 col-xs-6"
            style={{ textAlign: "right", paddingRight: 20 }}
          >
            {" " + Math.round(meter.targetLevel * 100) / 100 + "%"}
            {reportedMeter &&
              (reportedMeter.currentLevel === reportedMeter.targetLevel ? (
                <RoundIcon
                  onClick={editable ? el => setMeterLevel(meter, false) : null}
                  style={{
                    height: 20,
                    float: "right",
                    minWidth: 30,
                    minHeight: 30,
                    cursor: editable ? "pointer" : "auto",
                    background: editable ? "#93be38" : "#AEAEAE",
                    marginTop: 2,
                    marginLeft: 5,
                    marginRight: 0
                  }}
                >
                  <i
                    className="fas fa-check"
                    style={{
                      color: "white"
                    }}
                  />
                </RoundIcon>
              ) : (
                <Button
                  type="button"
                  style={{
                    width: "auto",
                    float: "right",
                    height: "30px",
                    minWidth: 180,
                    margin: 2,
                    marginLeft: 5,
                    marginRight: 0,
                    display: editable ? "auto" : "none"
                  }}
                  disabled={!editable}
                  onClick={el => setMeterLevel(meter, true)}
                >
                  {translate({
                    textKey: "sit.commitment.reports.markAsDone"
                  })}
                </Button>
              ))}
          </div>
        ) : (
          !props.disableEdit && (
            <div
              className="col-md-3 col-xs-6"
              style={{ display: "inline", textAlign: "right" }}
            >
              <RoundIcon
                style={{
                  height: 20,
                  float: "right",
                  minWidth: 25,
                  minHeight: 25,
                  cursor: "pointer",
                  background: "#ffffff",
                  marginTop: 2,
                  marginLeft: 5,
                  marginRight: 0
                }}
                data-testid={"removemeter_" + meter.meterId}
                onClick={() => {
                  props.removeMeter(meter.meterId);
                }}
              >
                <i className="far fa-times-circle" />
              </RoundIcon>
            </div>
          )
        )}
      </div>
    )
  );
};
export default HundredTodosMeterEditor;
