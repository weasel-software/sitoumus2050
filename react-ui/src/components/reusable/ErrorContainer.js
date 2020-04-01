// @flow

import React from "react";
import { connect } from "react-redux";
import Modal, { ModalHeader } from "./Modal";
import type { STORE_STATE } from "../../redux/store";
import type { ErrorType } from "../../constants/types";
import { SecondaryButton } from "./Button";
import styled from "styled-components";

import translate from "./translate";

import { ErrorActions } from "../../redux/errors";

const AlignEnd = styled.div`
  display: flex;
  justify-content: flex-end;
  flex: 1;
`;

const ErrorContainer = (props: {
  error: ErrorType,
  clearError: () => void
}) => (
  <Modal
    show={props.error !== null}
    background="#E27339"
    dialogClassName="react-bs-modal-root"
  >
    <ModalHeader>
      <div>
        <h2 style={{ color: "white", margin: 0 }}>
          {translate({ textKey: "sit.error.unexpectedError" })}
        </h2>
        <hr />
      </div>
    </ModalHeader>
    <div style={{ color: "#eee", fontWeight: "bold", textAlign: "center" }}>
      {props.error && props.error.message}
    </div>

    <div style={{ color: "#eee", fontWeight: "bold", textAlign: "center" }}>
      {props.error && props.error.fileName}
    </div>

    <div style={{ color: "#eee", fontWeight: "bold", textAlign: "center" }}>
      {props.error && props.error.lineNumber}
    </div>

    <AlignEnd>
      <SecondaryButton
        style={{ width: "140px", minWidth: "140px", height: "48px" }}
        onClick={props.clearError}
      >
        {translate({ textKey: "sit.error.close" })}
      </SecondaryButton>
    </AlignEnd>
  </Modal>
);

export default connect(
  (state: STORE_STATE) => ({
    error: state.errors.error
  }),
  { clearError: ErrorActions.clearError }
)(ErrorContainer);
