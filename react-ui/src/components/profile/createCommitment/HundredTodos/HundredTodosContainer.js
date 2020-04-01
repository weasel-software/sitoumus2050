import React from "react";

import WhiteSpace from "../../../reusable/WhiteSpace";

import HundredTodosEditor from "./HundredTodosEditor";
import Row from "../../../reusable/Row";
import Column from "../../../reusable/Column";
import translate from "../../../reusable/translate";
import { parseBackgroundInformation } from "../../../../utils/hundredTodos";

const HundredTodosContainer = props => {
  let { co2e, co2eAfterTodo } = parseBackgroundInformation(
    props.commitment.backgroundInformation_fi_FI
  );

  return (
    <div>
      <div
        style={{ border: "2px solid #93BE38", padding: 10, borderRadius: 6 }}
      >
        <Row>
          <Column>
            {translate({ textKey: "sit.commitmentCreation.myCo2Footprint" })}
          </Column>
          <Column>{co2e.toFixed(0)} kg CO2e</Column>
        </Row>
        <Row>
          <Column>
            {translate({
              textKey: "sit.commitmentCreation.myCo2FootprintGoal"
            })}
          </Column>
          <Column>{co2eAfterTodo.toFixed(0)} kg CO2e</Column>
        </Row>
      </div>

      <WhiteSpace height="24px" />
      <HundredTodosEditor {...props} toDo={true} />
      <WhiteSpace height="24px" />
      <HundredTodosEditor {...props} toDo={false} />
    </div>
  );
};

export default HundredTodosContainer;
