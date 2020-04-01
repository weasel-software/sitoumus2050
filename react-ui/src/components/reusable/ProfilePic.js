// @flow

import React, { Component } from "react";
import styled from "styled-components";
import Dropzone from "react-dropzone";
import { SecondaryButton } from "./Button";
import Row from "./Row";
import WhiteSpace from "./WhiteSpace";
import translate from "./translate";

const StyledProfilePic = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 160px;
  min-width: 160px;
  width: 160px;
  height: 160px;
  border-radius: 80px;
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

class ProfilePic extends Component<
  {
    onSelect: (?string) => void,
    profilePic?: string,
    disabled?: boolean,
    maxSize?: number
  },
  {
    profilePic: ?string
  }
> {
  state = {
    profilePic: this.props.profilePic
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
          profilePic: e.target.result
        });
        this.props.onSelect(e.target.result);
      })(file);
    };

    if (f) fr.readAsDataURL(f);
  };

  removePic = () => {
    this.setState(
      {
        profilePic: null
      },
      () => this.props.onSelect(null)
    );
  };

  render() {
    const { disabled } = this.props;
    return (
      <Row alignItems="center" justifyContent="flex-start">
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
            width: 160,
            height: 160
          }}
        >
          <StyledProfilePic disabled={disabled}>
            {this.state.profilePic ? (
              <img
                src={this.state.profilePic}
                alt="Users profile"
                style={{
                  width: "160px",
                  height: "160px",
                  minWidth: "160px",
                  minHeight: "160px",
                  borderRadius: "80px",
                  border: "2px #333 dashed"
                }}
              />
            ) : (
              translate({ textKey: "sit.profilePic.picOrLogo" })
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
      </Row>
    );
  }
}

export default ProfilePic;
