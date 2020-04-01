//@flow

import React, {Fragment} from "react";
import styled from "styled-components";
import translate from "../reusable/translate";
import {StatsContainer} from "./CommitmentStatisticsContainer";

function CreatedCommitments(props) {
    const Flex = styled.div`
        display: inline-flex;
        flex-direction: row;
    `;
    const LargeGreenFont = styled.span`
        font-size: 3.5em;
        color: #93be38;
        font-weight: 800;
        line-height: 1;
        @media (max-width: 664px) {
            font-size: 2.5em;
         }

    `;
    const SmallFont = styled.div`
        font-size: 1em;
        line-height: 1em;
        color: #525455;
        font-weight: 600;
    `;
    const Item = styled.div`
        display: flex;
        flex-direction: row;
        align-items: center;
    `;
    const Value = styled.div`
        flex: 2;
        font-weight: 900;
        font-size: 1.4em;
        color: #93be38;
        text-align: right;
        margin: 0 20px 0 0;     
        @media (max-width: 996px) {
            flex: 1;
        }   
    `;
    const Label = styled.div`
        flex: 3;
        font-size: 0.9em;
        line-height: 1em;
        color: #525455;
        font-weight: 600;
        @media (max-width: 996px) {
            flex: 1; 
        }
    `;

    const Column = styled.div`
        flex: 1;
        display: flex;
        flex-direction: column;
    `;
    // organizations, personal, total commitments(org. + personal), nutrition, plastic, oil, work machine, automotive, total green deals
    return (
      <Fragment>
        <StatsContainer style={{ paddingRight: "15px", paddingLeft: "15px" }}>
          <Column>
              <Item>
                  <Value> { props.lifeStyleTestCount }</Value>
                  <Label> { translate({ textKey: "sit.statistics.frontpage.count.lifestyleTests" })}</Label>
              </Item>
              <Item>
                  <Value>{ props.commitmentCounts[3] }</Value>
                  <Label>{ translate({ textKey: "sit.statistics.frontpage.count.nutritionCommitments" })}</Label>
              </Item>
          </Column>
          <Column>
              <Item>
                  <Value>{ props.commitmentCounts[0] } </Value>
                  <Label>{ translate({ textKey: "sit.statistics.frontpage.count.operationalCommitments" })}</Label>
              </Item>
              <Item>
                  <Value>{ props.commitmentCounts[8] }</Value>
                  <Label>{ translate({ textKey: "sit.statistics.frontpage.count.greenDealCommitments" })}</Label>
              </Item>
          </Column>
          <Column>
            <Item>
              <Value>{ props.commitmentCounts[1] }</Value>
              <Label>{ translate({ textKey: "sit.statistics.frontpage.count.sustainableLifestyleCommitments" })}</Label>
            </Item>
          </Column>

          </StatsContainer>
        <StatsContainer style={{ paddingRight: "15px", paddingLeft: "15px" }}>
            <Column style={{ flexDirection: "row", justifyContent: "center" }}>
              <Flex style={{ alignItems: "center", padding: "0 20px 0 0px", textAlign: "right" }} >
                <SmallFont>
                  {translate({ textKey: "sit.statistics.frontpage.totalCommitmentsBefore" })}
                </SmallFont>
              </Flex>
              <Flex style={{ alignItems: "center", padding: "0 0px 0 0px" }} ><LargeGreenFont>
                {props.commitmentCounts[2] + props.commitmentCounts[8] + props.commitmentCounts[3]}
              </LargeGreenFont></Flex>
              <Flex style={{ alignItems: "center", padding: "0 0px 0 20px", textAlign: "right" }} >
                <SmallFont>
                  {translate({ textKey: "sit.statistics.frontpage.totalCommitmentsAfter" })}
                </SmallFont>
              </Flex>
            </Column>
          </StatsContainer>

      </Fragment>
    )
}

export default CreatedCommitments;