// @flow

import styled from "styled-components";
import { lighten, darken } from "polished";
import Button from "./Button";

const RoundButtonWithIcon = styled(Button)`
  width: ${props => props.size || "50px"};
  max-width: ${props => props.size || "50px"};
  min-width: ${props => props.size || "50px"};
  height: ${props => props.size || "50px"};
  border-radius: ${props =>
    props.size ? parseInt(props.size, 10) / 2 + "px" : "25px"};
  display: flex;
  align-items: center;
  justify-content: center;
  margin: ${props => props.margin || "auto"};
  margin-top: 0;

  &&:disabled {
    opacity: 0.5;
    background: #9fbd47;
  }
`;

const RoundDivWithIcon = styled.div`
  background: ${props => (props.disabled ? "#9fbd47" : "#93be38")};
  width: ${props => props.size || "50px"};
  max-width: ${props => props.size || "50px"};
  min-width: ${props => props.size || "50px"};
  height: ${props => props.size || "50px"};
  border-radius: ${props =>
    props.size ? parseInt(props.size, 10) / 2 + "px" : "25px"};
  display: flex;
  align-items: center;
  justify-content: center;
  margin: ${props => props.margin || "auto"};
  margin-top: 0;
  color: white;
    opacity: ${props => props.disabledDiv && "0.5"}
  

  &:hover {
    background: ${props => !props.disabledDiv && lighten(0.1, "#93BE38")};
  }
  &:active {
    background: ${props => !props.disabledDiv && darken(0.1, "#93BE38")};
  }

`;

export default RoundButtonWithIcon;
export { RoundDivWithIcon };
