// @flow

import React, {Fragment} from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";
import { get } from "lodash";

import getLocalizedString from "../../utils/getLocalizedString";

import GoalIcon from "../reusable/GoalIcon";
import CommitmentShares from "../reusable/CommitmentShares";

import {
  JustifyLeft,
  JustifyRight,
  CommitmentName,
  CommitmentParty,
  Header,
  Column,
  RoundIcon,
  ComListIconText,
  SquareIcon,
  CommitmentIsParentType
} from "./styles";
import translate from "../reusable/translate";

const CommitmentStatsOuterWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  max-width: 300px;
  align-self: flex-start;
  padding: 10px 10px 10px 0px;
  width: 100%;
`;

const CommitmentStatsWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  border-bottom: 1px solid #dcdcdc;
  align-self: flex-start;
  padding: 10px 10px 10px 0px;
  width: 100%;
`;

const Singlestatsrow = styled.div`
  display: flex;
  justify-content: space-between;
`;

const Data = styled.span`
  display: flex;
  justify-content: space-between;
`;

const GreenData = styled(Data)`
 color: #86ad2e;
 font-weight: 800;
`;

const CommitmentStats = ({
  pageType,
  stats
}: {
  pageType: string,
  stats?: Array<Number> // [ organizations, personal, total commitments(org. + personal), nutrition, plastic, oil, work machine, automotive, total green deals, demolitions ]
}) => {
  return (
      <Fragment>
        {stats ?
          <CommitmentStatsOuterWrapper>
            <CommitmentStatsWrapper>
              <Header>
                {translate({
                  textKey: "sit.commitmentList.achievements"
                })}
              </Header>
            </CommitmentStatsWrapper>
            {pageType === "33554" ?
              <Fragment>
                <CommitmentStatsWrapper>
                  <Singlestatsrow>
                    <Data>
                      {translate({
                        textKey: "sit.commitmentList.muovipussienVahentaminen"
                      })}
                    </Data>
                    <GreenData>{stats[4]}</GreenData>
                  </Singlestatsrow>
                  <Singlestatsrow>
                    <Data>
                      {translate({
                        textKey: "sit.commitmentList.autoala"
                      })}
                    </Data>
                    <GreenData>{stats[7]}</GreenData>
                  </Singlestatsrow>
                  <Singlestatsrow>
                    <Data>
                      {translate({
                        textKey: "sit.commitmentList.oljyjatehuolto"
                      })}
                    </Data>
                    <GreenData>{stats[5]}</GreenData>
                  </Singlestatsrow>
                  <Singlestatsrow>
                    <Data>
                      {translate({
                        textKey: "sit.commitmentList.tyokoneala"
                      })}
                    </Data>
                    <GreenData>{stats[6]}</GreenData>
                  </Singlestatsrow>
                  <Singlestatsrow>
                    <Data>
                      {translate({
                        textKey: "sit.commitmentList.kestavaPurkaminen"
                      })}
                    </Data>
                    <GreenData>{stats[9]}</GreenData>
                  </Singlestatsrow>

                </CommitmentStatsWrapper>
                {/* <CommitmentStatsWrapper style={{alignItems: "center"}}>
                <span style={{padding: "20px 0px 0px 0px"}}>
                  {translate({
                    textKey: "sit.commitmentList.achievements.reductions"
                  })}
                </span>
                  <span style={{fontSize: "2.5em", color: "#86ad2e", fontWeight: "800", margin: "5px 0 5px 0"}}>
                  32 692 115
                </span>
                  <span style={{padding: "0px 0px 20px 0px"}}>
                  {translate({
                    textKey: "sit.commitmentList.achievements.tons"
                  })}
                </span>
                </CommitmentStatsWrapper> */}
              </Fragment>
              :
              <CommitmentStatsWrapper>
                <Singlestatsrow>

                  <Data>
                    {translate({
                      textKey: "sit.commitmentList.ravitsemusSitoumukset"
                    })}
                  </Data>
                  <GreenData>
                    {stats[3]}
                  </GreenData>
                </Singlestatsrow>
              </CommitmentStatsWrapper>
            }
          </CommitmentStatsOuterWrapper>
          : ""
        }
      </Fragment>
  );
};

export default CommitmentStats;
