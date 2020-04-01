// @flow
import React from "react";
import styled from "styled-components";
import { Modal } from "react-bootstrap";
import { Link } from "react-router-dom";
import { darken } from "polished";

const StyledModal = styled(Modal)`
  .modal-content {
    border-radius: 2px;
    padding: 30px;
    background: ${props => props.background || "white"};
  }
`;

const _Modal = (props: any) => <StyledModal enforceFocus={false} {...props} />;

const StyledHeader = styled(Modal.Header)`
  display: flex;
  flex: 1;
  align-items: center;
  justify-content: center;
  border-bottom: 0;
`;

const StyledBody = styled(Modal.Body)`
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-direction: column;
  min-height: 240px;
  padding-top: 0;
  margin-bottom: 30px;
  position: static;
`;

const ModalText = styled.span`
  font-size: 12px;
  color: #666;
`;

const ModalCloseButton = styled(Link)`
  cursor: pointer;
  right: 40px;
  top: 40px;
  position: absolute;
  width: 40px;
  height: 40px;
  border-radius: 20px;
  background: #f4f4f4;
  font-size: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #333;
  &:hover {
    background: ${darken(0.05, "#f4f4f4")};
    text-decoration: none;
  }
  &:active {
    background: ${darken(0.15, "#f4f4f4")};
  }
  &:focus {
    text-decoration: none;
  }
`;

export default _Modal;
export {
  StyledHeader as ModalHeader,
  StyledBody as ModalBody,
  ModalText,
  ModalCloseButton
};
