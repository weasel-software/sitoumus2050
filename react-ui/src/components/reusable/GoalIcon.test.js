// @flow

import React from "react";
import ReactDOM from "react-dom";
import { render } from "react-testing-library";
import "jest-dom/extend-expect";

import GoalIcon from "./GoalIcon";

it("renders without crashing", () => {
  const div = document.createElement("div");
  ReactDOM.render(<GoalIcon icon="" />, div);
  ReactDOM.unmountComponentAtNode(div);
});

it("renders an icon", () => {
  const { container } = render(<GoalIcon icon="testi_ikoni" />);
  const img = container.querySelector("img");
  expect(img.src).toContain("testi_ikoni");
});
