// @flow

import React, {Fragment} from "react";
import styled from "styled-components";
import translate from "../reusable/translate";

const Flex = styled.div`
    display: flex;
`;
const MediumLargeGreenFont = styled.span`
    flex: 1 4;
    color: #93be38;
    font-weight: 800;    
    text-align: right;
`;

const SmallFont = styled.div`
    flex: 3 1;
    font-size: 0.95em;
    line-height: 1em;
    color: #525455;
    font-weight: 600;
`;


class SustainableLifestyleCommitments extends React.Component<{
  topLifestyleCommitments: Array,
}> {

  render() {
    let topThree = [];
    try {
      topThree = this.props.topLifestyleCommitments.sort(([aLabel, aValue], [bLabel, bValue]) => {
        return parseInt(bValue)-parseInt(aValue);
      });
    }
    catch (e) {
    }
    topThree = topThree.slice(0,3);
    return (
        <Flex style={{ flex: 1, flexDirection: "column", justifyContent: "space-evenly", padding: "20px 0"}}>
          { topThree.map(([label, value]) => (
                <Flex key={value} style={{flexDirection: "row", alignItems: "center"}}>
                  <MediumLargeGreenFont style={{fontSize: "1.5em", color: "#93be38"}}>{value}</MediumLargeGreenFont>
                  <SmallFont style={{margin: "0 2% 0 6%"}}>{translate({ textKey: "sit.statistics.frontpage." +  label})}</SmallFont>
                </Flex>
          )) }
        </Flex>
    );
  }
}

export default SustainableLifestyleCommitments;
