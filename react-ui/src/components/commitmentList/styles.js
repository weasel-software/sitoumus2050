import styled from "styled-components";

export const CommitmentName = styled.h4`
  color: #333333;
  font-size: 1em;
  font-weight: normal;
  flex-wrap: wrap;
  overflow-wrap: break-word;
  word-wrap: break-word;
  max-width: 460px
`;

export const Header = styled.span`
  color: #333333;
  font-size: 1.75em;
  font-weight: 300;
`;

export const Column = styled.div`
  display: flex;
  flex-direction: column;
  align-self: stretch;
`;

export const RoundIcon = styled.div`
  min-width: 80px;
  min-height: 80px;
  border-radius: 75px;
  background: #ececec;
  margin-right: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
`;

export const SquareIcon = styled.div`

  width: 80px;
  max-width: 80px;
  display: flex;
  justify-content: center;
  align-items: center;
  border:0px solid #93BE38;
  border-radius: 0px;
  margin-right: 12px;
  color: #93be38;
  align-self: stretch;
`;

export const CommitmentParty = styled.span`
  font-size: 14px;
  color: #6b6b6b;
  text-transform: uppercase;
  font-weight: bold;
  margin-right: 5px;
`;

export const CommitmentIsParentType = styled.span`
  font-size: 14px;
  color: #18819d;
  text-transform: uppercase;
  font-weight: bolder;
`;

export const JustifyLeft = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
  flex-direction: row;
  align-self: flex-start;
`;

export const ComListIconText = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
  flex-direction: row;
  align-self: stretch;
`;

export const JustifyRight = styled.div`
  display: flex;
  justify-content: flex-end;
  align-items: center;
  flex-direction: row;
  max-width: 960px;
  align-self: stretch;
`;

