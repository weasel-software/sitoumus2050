// $flow

import Row from "./Row";
import styled from "styled-components";

const DatePickerContainer = styled(Row)`
  cursor: pointer;
  padding: 6px;
  border-radius: 4px;
  border: ${props => (props.error ? "2px solid red" : "2px solid transparent")};
  &:hover {
    border: ${props => (props.error ? "2px solid red" : "2px dashed #93be38")};
  }
  &:active {
    border: ${props => (props.error ? "2px solid red" : "2px dashed #93be38")};
  }
  &:focus {
    border: ${props => (props.error ? "2px solid red" : "2px dashed #93be38")};
  }

  > div {
    width: 100%;
    ${"" /* react-datepicker style haxfix */};
  }
`;

export default DatePickerContainer;
