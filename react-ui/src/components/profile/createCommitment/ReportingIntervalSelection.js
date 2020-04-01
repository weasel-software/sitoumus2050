import React, { Fragment, Component } from "react";
import { RadioButton, RadioButtonContainer } from "../../reusable/Radio";
import StyledSubtitle from "../../reusable/StyledSubtitle";
import Row from "../../reusable/Row";
import translate from "../../reusable/translate";
import InfoIcon from "../../reusable/InfoIcon";
import { get } from "lodash";
import styled from "styled-components";

const Column = styled.div`
  display: flex;
  flex-direction: column;
  align-items: ${props => props.alignItems || "unset"};
`;

class ReportingIntervalSelection extends Component {
  render() {
    const {
      locale,
      reportingInterval,
      setReportingInterval,
      infoText
    } = this.props;

    return (
      <Fragment>
        <Row justifyContent="space-between">
          <StyledSubtitle>
            {translate({
              languageOverride: locale,
              textKey: "sit.commitmentCreation.selectReportInterval"
            })}
          </StyledSubtitle>
          <InfoIcon info={infoText} selectedLocale={locale} />
        </Row>

        <Column>
          <RadioButtonContainer
            required
            name="reportingInterval"
            onChange={e => setReportingInterval(get(e, "target.name"))}
          >
            <RadioButton
              required
              checked={reportingInterval === "MONTH_1"}
              defaultChecked={true}
              value="MONTH_1"
              id="reporting_interval_1m"
              name="MONTH_1"
              label={translate({
                languageOverride: locale,
                textKey: "sit.commitmentCreation.everyOneMonth"
              })}
              style={{ marginBottom: "-1px" }}
            />

            <RadioButton
              required
              checked={reportingInterval === "MONTH_3"}
              value="MONTH_3"
              id="reporting_interval_3m"
              name="MONTH_3"
              label={translate({
                languageOverride: locale,
                textKey: "sit.commitmentCreation.everyThreeMonths"
              })}
              style={{ marginBottom: "-1px" }}
            />

            <RadioButton
              required
              checked={reportingInterval === "MONTH_6"}
              value="MONTH_6"
              id="reporting_interval_6m"
              name="MONTH_6"
              label={translate({
                languageOverride: locale,
                textKey: "sit.commitmentCreation.everySixMonths"
              })}
              style={{ marginBottom: "-1px" }}
            />

            <RadioButton
              required
              checked={reportingInterval === "MONTH_12"}
              value="MONTH_12"
              id="reporting_interval_12m"
              name="MONTH_12"
              label={translate({
                languageOverride: locale,
                textKey: "sit.commitmentCreation.everyTwelveMonths"
              })}
              style={{ marginBottom: "-1px" }}
            />
          </RadioButtonContainer>
        </Column>
      </Fragment>
    );
  }
}

export default ReportingIntervalSelection;
