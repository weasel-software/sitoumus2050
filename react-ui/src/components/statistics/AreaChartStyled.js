// @flow

import React, {Fragment} from "react";

import Plotly from "plotly.js-basic-dist";
import createPlotlyComponent from "react-plotly.js/factory";
import styled from "styled-components";

import translate from "../reusable/translate";

const Plot = createPlotlyComponent(Plotly);

const StyledPlot = styled(Plot)`
  width: ${props => props.width || "400px"};
  height: ${props => props.height || "200px"};
  max-height: ${props => props.height || "300px"};
`;
const Floater = styled.div`
    display: flex;
    flex-direction: column;
    color: rgb(82, 84, 85);
    font-weight: 700;
    font-size: 1em;
    text-align: right;
    margin: -125px 45px 0 0px;
    z-index: 100;
    line-height: 1.3;
    text-transform: uppercase;
`;
const Flex = styled.div`
    display: inline-flex;
    flex-direction: row;
`;
const Year = styled.span`
    color: #525455;
    z-index: 100;
    margin: 0px 0 0 0;
    justify-content: space-between;
    font-weight: 800
`;

class AreaChartStyled extends React.Component<{
    labels: Array<string>,
    values: Array<number>,
    style?: any,
    margin?: boolean
}> {
    render() {
        var trace1 = {
            x: [2018,2019,2020, 2021,2022,2023,2024,2025,2026,2027,2028,2029,2030],
            /*y: [100, 220 , 367, 650, 1380, 1730, 2760, 3330, 3680,4100,4800],*/
            y: [10300, 9350, 8900],
            fill: 'tozeroy',
            fillcolor: "#709626",
            type: 'scatter',
            mode: 'none',
            hoverinfo: "skip",
            cliponaxis: false,
            showlegend: false,
        };
        var trace2 = {
            x: [2018,2030],
            y: [10300,3000],
            cliponaxis: false,
            showlegend: false,
            type: 'scatter',
            mode: 'lines',
            line: {
                color: '#e0e0e0',
                width: 2
            },
            hoverinfo: "skip",
        };

        const data = [trace1, trace2]
        const layout = {
            autosize: true,
            margin: this.props.margin
                ? {b: 40, l: 0, t: 0, r: 0, pad: 0}
                : {b: 280, pad: 20},
            yaxis: {
                dtick: 1000,
                fixedrange: true,
                range: [0,10300],
                showticklabels: false,
                showgrid: false,
                zeroline: false,
                showline: false,
            },
            xaxis: {
                dtick: 10,
                fixedrange: true,
                range: [2018,2030],
                zeroline: false,
                showgrid: false,
                showline: true,
                showticklabels: false,
                linecolor: "#e0e0e0",
                linewidth: 2,
                tickfont: {
                    size: 16,
                    color: '#525455'
                },
            }

        };
        return (
            <Fragment>
                <Floater style={{ display: "flex", alignItems: "flex-start", flexDirection: "row", justifyContent: "flex-end", margin: "0 30px -70px 0"}}>
                    <span
                        style={{ color: "#525455", fontWeight: "normal", fontSize: "1em", textAlign: "left", margin: "0 20px 0 0", zIndex: "100"}}
                        dangerouslySetInnerHTML={{__html:translate({ textKey:"sit.statistics.frontpage.chart.co2Emissions" })}} />
                    <Flex style={{ display: "flex", flexDirection: "column", textAlign: "center"}}>
                        <span style={{ fontWeight: "normal"}}>
                            {translate({ textKey:"sit.statistics.frontpage.chart.goalvs1" })}
                        </span>
                        <span style={{ textTransform: "initial"}}>{translate({ textKey:"sit.statistics.frontpage.chart.goalvs2" })}</span>
                        <span style={{ color: "#709626"}}>{translate({ textKey:"sit.statistics.frontpage.chart.goalvs3" })}</span>
                    </Flex>
                </Floater>
                <StyledPlot
                    style={this.props.style || {}}
                    data={data}
                    layout={layout}
                    useResizeHandler={true}
                    config={{displayModeBar: false}}
                />
                <Flex style={{ justifyContent: "space-between", marginTop: "-40px"}}>
                    <Year style={{ margin: "0 0 0 -20px"}}><span style={{ margin: "0 30px 0 0"}}>2018</span><span>2020</span></Year>
                    <Year style={{ margin: "0 -20px 0 0"}}>2030</Year>
                </Flex>
            </Fragment>
        );
    }
}

export default AreaChartStyled;
