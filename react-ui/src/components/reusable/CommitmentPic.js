// @flow

import React, { Component } from "react";
import styled from "styled-components";
import Dropzone from "react-dropzone";
import { SecondaryButton } from "./Button";
import Column from "./Column";
import WhiteSpace from "./WhiteSpace";
import translate from "./translate";

const StyledProfilePic = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 221px;
  min-width: 376px;
  width: 380px;
  height: 225px;
  border-radius: 10px;
  font-weight: bold;
  border: 2px #333 dashed;
  cursor: pointer;
  &:hover {
    box-shadow: 0px 0px 24px #777;
    color: #333;
  }
  &:active {
    box-shadow: none;
  }
  pointer-events: ${props => (props.disabled ? "none" : "auto")};
`;

class CommitmentPic extends Component<
  {
    onSelect: (?string) => void,
    commitmentPic?: string,
    disabled?: boolean,
    maxSize?: number,
    locale?: "fi_FI" | "en_US"
  },
  {
    commitmentPic: ?string
  }
> {
  state = {
    commitmentPic: this.props.commitmentPic
  };

  static defaultProps = {
    disabled: false,
    maxSize: 300000
  };

  handleSelect = (file: any) => {
    const f = file[0];

    const fr = new FileReader();

    fr.onload = file => {
      return (e => {
        this.setState({
          commitmentPic: e.target.result
        });
        this.props.onSelect(e.target.result);
      })(file);
    };

    if (f) fr.readAsDataURL(f);
  };

  removePic = () => {
    this.setState(
      {
        commitmentPic: null
      },
      () => this.props.onSelect(null)
    );
  };

  render() {
    const { disabled } = this.props;
    return (
      <Column alignItems="center" justifyContent="flex-start">
        <Dropzone
          maxSize={this.props.maxSize}
          disabled={disabled}
          multiple={false}
          accept="image/*"
          onDrop={e => this.handleSelect(e)}
          onDropRejected={rejectedFile => {
            if (rejectedFile && rejectedFile[0].size > this.props.maxSize) {
              alert(
                `${translate({
                  textKey: "sit.profilePic.tooBigAlert1"
                })} ${String(this.props.maxSize)} ${translate({
                  textKey: "sit.profilePic.tooBigAlert2"
                })}`
              );
            }
          }}
          style={{
            border: 0,
            width: 376,
            height: 221
          }}
        >
          <StyledProfilePic
            disabled={disabled}
            style={{
              border: this.props.disabled && "0px"
            }}
          >
            {this.state.commitmentPic ? (
              <img
                src={this.state.commitmentPic}
                alt="Commitment"
                style={{
                  maxWidth: "376px",
                  maxHeight: "221px"
                }}
              />
            ) : (
              translate({
                languageOverride: this.props.locale,
                textKey: "sit.profilePic.picOrLogo"
              })
            )}
          </StyledProfilePic>
        </Dropzone>

        <WhiteSpace width="20px" />

        {!disabled && (
          <SecondaryButton
            onClick={e => {
              e.preventDefault();
              this.removePic();
            }}
            style={{ height: 36, minWidth: 140, width: 140 }}
          >
            {translate({ textKey: "sit.profilePic.removePic" })}
          </SecondaryButton>
        )}
      </Column>
    );
  }
}

export default CommitmentPic;
