// @flow

import type { CommitmentType, OperationType } from "../../constants/types";

import React, { Fragment } from "react";
import styled from "styled-components";

import generateGraphHistory from "../../utils/generateGraphHistory";
import getLocalizedString from "../../utils/getLocalizedString";

import WhiteSpace from "../reusable/WhiteSpace";
import Row from "../reusable/Row";
import translate from "../reusable/translate";

import ReportPanel from "./ReportPanel";

const StyledTitle = styled.h3`
  font-size: 16px;
  font-weight: bold;
  text-transform: uppercase;
  margin-top: 36px;
`;

const StyledSubtitle = styled.h4`
  font-size: 14px;
  font-weight: bold;
  margin-bottom: 10px;
`;

const StyledList = styled.ol`
  padding: 0px;
`;

const StyledListitem = styled.li`
  font-size: 14px;
  font-weight: bold;
  display: flex;
  flex: 1 0 0;
  justify-content: space-between;
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
  border-right: 1px solid transparent;
  border-left: 1px solid transparent;
  border: ${props => props.isSelected && "1px solid #93BE38 "};
  align-items: center;
`;

const CommitmentText = styled.span`
  font-size: 14px;
  color: #212121;
`;

const OperationsContainer = ({
  operations,
  joinCommitmentMode,
  selectedOperations,
  selectOperation,
  meterChartTypeList,
  commitment
}: {
  operations: ?Array<OperationType>,
  joinCommitmentMode?: boolean,
  selectOperation?: OperationType => void,
  selectedOperations?: Array<OperationType>,
  meterChartTypeList: {},
  commitment: CommitmentType
}) => (
  <div>
    <StyledTitle>
      {translate({ textKey: "sit.commitment.operations" })}
    </StyledTitle>
    <WhiteSpace height="30px" />
    <StyledList>
      {operations &&
        operations.map((op, index) => {
          const isSelected =
            joinCommitmentMode &&
            selectedOperations &&
            selectedOperations.some(
              oper => oper.operationId === op.operationId
            );
          const graphHistory = generateGraphHistory(op);
          return (
            <Fragment key={op.operationId + index}>
              <StyledListitem isSelected={isSelected}>
                <StyledSubtitle>
                  {getLocalizedString("operationTitle", op)}
                </StyledSubtitle>

                {joinCommitmentMode && (
                  <span
                    key={String(isSelected)}
                    onClick={() => selectOperation && selectOperation(op)}
                  >
                    {isSelected ? (
                      <i
                        className="fas fa-minus-circle"
                        style={{ fontSize: 22, color: "#ccc" }}
                      />
                    ) : (
                      <i
                        className="fas fa-plus-circle"
                        style={{ fontSize: 22, color: "#ccc" }}
                      />
                    )}
                  </span>
                )}
              </StyledListitem>
              <WhiteSpace height="12px" />
              <CommitmentText
                dangerouslySetInnerHTML={{
                  __html: getLocalizedString("operationDescription", op)
                }}
              />
              <WhiteSpace height="12px" />
              <StyledSubtitle>
                {translate({ textKey: "sit.commitment.meters" })}:
              </StyledSubtitle>

              {op.meters &&
                op.meters.map((meter, index) => {
                  const meterTypeText = getLocalizedString("meterType", meter);
                  if (meter.meterChartType && meter.meterId) {
                    meterChartTypeList = {
                      ...meterChartTypeList,
                      [meter.meterId]: meter.meterChartType
                    };
                  }
                  return (
                    <Fragment key={index}>
                      {meterTypeText !== "none" &&
                      meterTypeText !== "" &&
                      meterTypeText !== undefined ? (
                        <CommitmentText style={{ padding: 0 }}>
                          {meterTypeText}
                          <Row justifyContent="flex-start">
                            {meter.startingLevel &&
                              meter.startingLevel !== "null" && (
                                <span>
                                  {translate({
                                    textKey:
                                      "sit.commitment.reports.startingLevel"
                                  })}: {meter.startingLevel}
                                </span>
                              )}

                            {meter.targetLevel &&
                              meter.targetLevel !== "null" && (
                                <span style={{ marginLeft: "80px" }}>
                                  {translate({
                                    textKey:
                                      "sit.commitment.reports.targetLevel"
                                  })}: {meter.targetLevel}
                                </span>
                              )}
                          </Row>
                        </CommitmentText>
                      ) : (
                        <CommitmentText>
                          {translate({
                            textKey: "sit.commitment.noMeters"
                          })}
                        </CommitmentText>
                      )}
                      <WhiteSpace height="20px" />
                    </Fragment>
                  );
                })}

              <div>
                {op.reports &&
                  op.reports
                    .slice(0)
                    .reverse()
                    .map((report, index) => (
                      <Fragment key={index}>
                        <ReportPanel
                          meterChartTypeList={meterChartTypeList}
                          commitment={commitment}
                          expanded={index === 0}
                          report={report}
                          reportIndex={index}
                          graphHistory={graphHistory}
                        />
                        <WhiteSpace height="10px" />
                      </Fragment>
                    ))}
                <WhiteSpace height="40px" />
              </div>
            </Fragment>
          );
        })}
    </StyledList>
  </div>
);

export default OperationsContainer;
