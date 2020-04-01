import React from "react";
import translate from "../../../reusable/translate";
import Row from "../../../reusable/Row";
import { get } from "lodash";

const HundredTodosAddMeter = props => {
  const { meters, locale } = props;
  let targetMeter = {};
  const findMeter = e => {
    const eventTargetValue = get(e, "target.value");
    targetMeter = meters.find(
      m => String(m[`meterType_${locale}`]) === String(eventTargetValue)
    );
  };

  const isSelected = meter =>
    props.selectedMeterIds && props.selectedMeterIds.includes(meter.meterId);

  return (
    <span>
      <div>
        <div
          style={{
            border: "2px solid rgb(147, 190, 56)",
            padding: "10px",
            borderRadius: "6px"
          }}
        >
          <select
            data-testid="hundredtodos-addmeter"
            style={{ height: "48px", width: "100%", wordWrap: true }}
            onChange={e => {
              findMeter(e);
            }}
          >
            <option>
              {translate({
                languageOverride: locale,
                textKey: "sit.commitment.operations.selectOperation"
              })}
            </option>
            {meters &&
              meters.map(m => (
                <option disabled={isSelected(m)} key={m.meterId}>
                  {m[`meterType_${locale}`]}
                </option>
              ))}
          </select>
          <Row justifyContent="flex-start" alignItems="center">
            <button
              style={{
                height: "36px",
                minWidth: "120px",
                width: "180px",
                marginTop: "15px",
                background: "#93be38",
                borderRadius: "4px",
                color: "white"
              }}
              onClick={() => props.addMeter(targetMeter)}
            >
              {translate({
                languageOverride: locale,
                textKey: "sit.commitment.operations.addTaskButton"
              })}
            </button>
          </Row>
        </div>
      </div>
    </span>
  );
};
export default HundredTodosAddMeter;
