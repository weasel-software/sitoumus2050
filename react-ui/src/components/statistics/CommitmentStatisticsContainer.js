//@flow

import type {
  ReportType,
  CommitmentType,
  MeterType
} from "../../constants/types";

import React, { Component, Fragment } from "react";
import styled from "styled-components";
import { connect } from "react-redux";

import {ArticleActions} from "../../redux/articles";
import {CountsActions} from "../../redux/counts";

import translate from "../reusable/translate";

import BarChartStyled from "./BarChartStyled";
import CreatedCommitments from "./CreatedCommitments";
import SustainableLifestyleCommitments from "./SustainableLifestyleCommitments";
import {withRouter} from "react-router-dom";

const Left = styled.div`
    display: inline-flex;
    align-items: center;
`;
const Right = styled.div`
    display: inline-flex;
    align-items: center;
    @media (max-width: 996px) {
          margin-top: 25px;
    }

    @media (max-width: 664px) {
      flex-direction: column;
      justify-content: stretch;
      align-items: stretch; 
      &>.second {
        flex-direction: column-reverse;
      }
    }
`;

const Flex = styled.div`
    display: inline-flex;
    flex-direction: row;
`;

const LargeGreenFont = styled.span`
    font-size: 3.5em;
    color: #93be38;
    font-weight: 800;
    line-height: 1;
    @media (max-width: 996px) {
        font-size: 2.5em;
    }

`;
const SmallFont = styled.div`
    font-size: 1em;
    line-height: 1em;
    color: #525455;
    font-weight: 600;
`;
const BigPercentage = styled.div`
  flex: 1;
  display: flex; 
  justify-content: center;
  align-items: stretch;
  text-align: center;
  flex-direction: column;
  color: #93be38;
  font-size: 5.5em;
  font-weight: 600;
`;
const Border = styled.div`
    border-bottom: 2px solid #e0e0e0;
`;
const StatsColumn = styled.div`
    flex: 1;
    display: flex;
    flex-direction: column;
    max-width: 357px;
    margin: 0 4%;
    @media (max-width: 996px) {
      max-width: 946px;
      align-items: stretch;
    }
`;
const StatsBox = styled.div`
    flex: 1;
    display: flex;
    min-height: 180px;
    width: 100%;
    background-color: ${props => props.dark ? "#f4f4f4" : "inherit"};
`;
export const StatsContainer = styled.div`
    display: flex; 
    align-items: stretch; 
    max-width: 1167px; 
    margin: auto;
    justify-content: center;
    padding: ${props => props.extraPadding ? "52px 0 25px 0" : "25px 0" };
    @media (max-width: 996px) {
      align-items: space-around;
      padding: 10px 0;
      flex-direction: column;
    }
`;
const AchievementsHeader = styled.div`
  font-size: 32px;
  font-weight: 300; 
  padding: 46px 0 0 0;
  @media (max-width: 996px) {
     font-size: 26px;
     padding: 24px 0 0 0;
  }
`;

const kgToContainers = (kgCO2) => {
  // The atomic weights of oxygen (O) and carbon (C) are 16 and 12, respectively, and there is 1 C and 2 O in a CO2
  // molecule. Which means 12/(12 + 2 * 16) = 0.2727 is C. So 1 kg CO2 contains 0.2727 kg carbon.
  // One 20-foot container is 33.2 m^3 and amorphous carbon weighs 1800 kg / m^3 so a container is 59760 kg.
  return Math.round(kgCO2 * 0.2727 / 59760);
};

class CommitmentStatisticsContainer extends Component<
  {
    report: ReportType,
    reportIndex?: number,
    expanded?: boolean,
    graphHistory?: {
      [string]: {
        values: Array<number>,
        labels: Array<string>,
        texts: Array<string>
      }
    },
    commitment: ?CommitmentType,
    meterChartTypeList?: { [string]: string },
  },
  {
    expanded: boolean
  }
  > {
  state = {
    expanded: this.props.expanded || false
  };

  componentWillMount() {
    this.props.getCommitmentCounts();
    this.props.getDoneLifestyleTestCount();
    this.props.getAverageReduction();
    this.props.getTopLifestyleCommitments();
  }

  render() {
    return (
      <Fragment>
          <CreatedCommitments
            commitmentCounts= { this.props.commitmentCounts }
            lifeStyleTestCount = { this.props.doneLifestyleTestCount }
          />

        <StatsContainer>
          <div style={{ flex: 1, textAlign: "center" }}>
            <Border style={{width: "100%" }}></Border>
            <AchievementsHeader>
              {translate({ textKey:"sit.statistics.frontpage.elamantavatSaavutuksiaTitle" })}
            </AchievementsHeader>
          </div>
        </StatsContainer>

        <StatsContainer extraPadding>
          <Left style={{ flex: 1 }}>
            <StatsColumn>
              <span
                style={{ color: "#525455", fontSize: "1.3em", fontWeight: 300, textAlign: "center", margin: "0 0 10px 0"}}
                dangerouslySetInnerHTML={{__html:translate({ textKey: "sit.statistics.frontpage.averageFootprint" })}} />
              <BarChartStyled
                margin={true}
                disableLeftMargin={true}
                width="auto"
                height="325px"
              />
              <Flex style={{ justifyContent: "stretch", textAlign: "center"}}>
                <SmallFont
                  style={{ flex: 1, color: "#525455", fontSize: "0.9em", textAlign: "center"}}
                  dangerouslySetInnerHTML={{__html:translate({ textKey: "sit.statistics.frontpage.co2ePersonYear" })}} />
                <SmallFont
                  style={{ flex: 1, color: "#525455", fontSize: "0.9em", textAlign: "center"}}
                  dangerouslySetInnerHTML={{__html:translate({ textKey: "sit.statistics.frontpage.co2ePersonYear" })}} />
              </Flex>
            </StatsColumn>
          </Left>
          <Right style={{ display: "flex", flex: 2, alignItems: "stretch" }}>
            <StatsColumn className="first" style={{ margin: "0 2%"}}>
              <StatsBox light style={{ flexDirection: "column", justifyContent: "stretch", textAlign: "center", margin: "0 0 9px 0"}}>
                <div
                  style={{ color: "#525455", fontSize: "1.3em", fontWeight: 300 }}
                  dangerouslySetInnerHTML={{__html:translate({ textKey: "sit.statistics.frontpage.averageReductionSince2015" })}}>
                </div>
                <BigPercentage>
                  { this.props.averageReduction.averageReductionPercentage }%
                </BigPercentage>
              </StatsBox>
              <StatsBox dark style={{ flexDirection: "column", margin: "9px 0 0 0" }}>
                <Flex style={{flex: 1, display: "flex", flexDirection: "row", alignItems: "stretch"}}>
                  <div style={{flex: 1, display: "flex", flexDirection: "row", alignItems: "flex-end", width: "100%"}}>
                    <SmallFont style={{ flex: 1, textAlign: "center"}}>
                      {translate({ textKey: "sit.statistics.frontpage.totalCO2Before" })}
                    </SmallFont>
                  </div>
                </Flex>
                <Flex style={{flex: 1, display: "flex", alignItems: "center"}}>
                  <LargeGreenFont style={{ flex: 1, textAlign: "center"}}>
                    { new Intl.NumberFormat('fi-FI').format(this.props.averageReduction.totalReductionKg) }
                  </LargeGreenFont>
                </Flex>
                <Flex style={{flex: 1, display: "flex" }}>
                  <SmallFont
                    style={{ flex: 1, textAlign: "center"}}
                    dangerouslySetInnerHTML={{ __html:translate({ textKey: "sit.statistics.frontpage.totalCO2After" })}}>
                  </SmallFont>
                </Flex>
              </StatsBox>
            </StatsColumn>
            <StatsColumn className="second" style={{ alignItems: "stretch", margin: "0 2%" }}>
              <StatsBox dark style={{ margin: "0 0 9px 0" }}>
                <SustainableLifestyleCommitments topLifestyleCommitments={this.props.topLifestyleCommitments} />
              </StatsBox>
              <StatsBox light style={{ flexDirection: "column", alignItems: "center", justifyContent: "center", margin: "9px 0 0 0"}}>
                <SmallFont style={{ textTransform: "uppercase", fontSize: "0.9em", fontWeight: 900 }}>
                  {translate({ textKey:"sit.statistics.frontpage.corresponds" })}
                </SmallFont>
                <Flex style={{ flexDirection: "row", alignItems: "center", justifyContent: "flex-end", width: "100%"}}>
                  <Flex style={{ flex: 1, flexDirection: "column"}}>
                    <Flex style={{ alignItems: "center", justifyContent: "center" }} ><LargeGreenFont>
                      { String(kgToContainers(this.props.averageReduction.totalReductionKg)) }
                    </LargeGreenFont></Flex>
                    <Flex style={{ alignItems: "center", justifyContent: "center", textAlign: "center", padding: "5px 0 0 0" }} >
                      <SmallFont style={{ fontSize: "0.85em" }}>
                        {translate({ textKey: "sit.statistics.frontpage.containers" })}
                      </SmallFont>
                    </Flex>
                  </Flex>
                  <Flex style={{ alignItems: "right", padding: "20px 8px 0 2px", textAlign: "right" }} ><img width="185" height="132" src={require('../../images/container.png')} /></Flex>
                </Flex>
              </StatsBox>
            </StatsColumn>
          </Right>
        </StatsContainer>
      </Fragment>
    );
  }
}
export default withRouter(
  connect(
    state => ({
      commitmentCounts: state.articles.commitmentCountForAllTypes,
      doneLifestyleTestCount: state.counts.doneLifestyleTestCount,
      averageReduction: state.counts.averageReduction,
      topLifestyleCommitments: state.counts.topLifestyleCommitments
    }),
    {
      getCommitmentCounts: ArticleActions.getCommitmentCountForAllTypes,
      getDoneLifestyleTestCount: CountsActions.getDoneLifestyleTestCount,
      getAverageReduction: CountsActions.getAverageReduction,
      getTopLifestyleCommitments: CountsActions.getTopLifestyleCommitments
    }
  )(CommitmentStatisticsContainer)
);
