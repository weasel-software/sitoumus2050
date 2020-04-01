// @flow

import React from "react";
import styled from "styled-components";
import ShareIcon from "./ShareIcon"

const CommitmentSharesContainerOuter = styled.div`
  display: flex;
  flex-direction: column;
`;
const CommitmentSharesContainerInner = styled.div`
  display: flex;
  flex-direction: row;
  background: #fff;
`;

const CommitmentSharesText = styled.span`
  font-size: 12px;
  font-weight: bold;
  color: black;
`;

const RoundIcon = styled.div`
  height: 40px;
  width: 40px;
  border-radius: 20px;
  border: 1px solid #aeaeae;
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: ${props => props.zIndex || 0};
  background: white;
  margin-left: ${props => props.marginLeft || "initial"};
  color: black;  
`;
const NoStyleIcon = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: ${props => props.zIndex || 0};
  background: white;
  padding: 0 10px 0 10px;
  color: black;
`;
const NoStyleIconIcon = styled.i`
  opacity: 0.8;
`


const CommitmentShares = ({
  joinedCount = 0,
  shared = 0,
  likes = 0,
  ...rest
}: {
  joinedCount: number,
  shared: number,
  likes: number,
  rest?: any
}) => (
  <CommitmentSharesContainerOuter>
      <CommitmentSharesContainerInner>
          <NoStyleIcon zIndex={0}>
              <NoStyleIconIcon className="fa fa-heart" />
          </NoStyleIcon>
          <NoStyleIcon zIndex={1} marginLeft="-10px">
              <CommitmentSharesText>+{likes}</CommitmentSharesText>
          </NoStyleIcon>
      </CommitmentSharesContainerInner>
      <CommitmentSharesContainerInner>
          <NoStyleIcon zIndex={0}>
            <NoStyleIconIcon><ShareIcon/></NoStyleIconIcon>
          </NoStyleIcon>
          <NoStyleIcon zIndex={1} marginLeft="-10px">
              <CommitmentSharesText>+{joinedCount}</CommitmentSharesText>
          </NoStyleIcon>
      </CommitmentSharesContainerInner>
{/*      <CommitmentSharesContainerInner>
          <NoStyleIcon zIndex={0}>
              <i className="fa fa-share-alt" />
          </NoStyleIcon>
          <NoStyleIcon zIndex={1} marginLeft="-10px">
              <CommitmentSharesText>+{shared}</CommitmentSharesText>
          </NoStyleIcon>
      </CommitmentSharesContainerInner>*/}
  </CommitmentSharesContainerOuter>
);

export default CommitmentShares;
