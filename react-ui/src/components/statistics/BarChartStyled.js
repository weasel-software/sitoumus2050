// @flow

import React from "react";

import Plotly from "plotly.js-basic-dist";
import createPlotlyComponent from "react-plotly.js/factory";
import styled from "styled-components";

import translate from "../reusable/translate";

const Plot = createPlotlyComponent(Plotly);

const StyledPlot = styled(Plot)`
  width: ${props => props.width || "450px"};
  max-height: ${props => props.height || "215px"};
`;

class BarChartStyled extends React.Component<{
    labels: Array<string>,
    values: Array<number>,
    style?: any,
    margin?: boolean
}> {
    render() {
        let trace1 =
            {
                name: "",
                x: ["<b>10300 kg</b>", "<b>3000 kg</b>"],
                y: [10300, 3000],
                type: "bar",
                marker: {color: "rgba(83, 84, 85, 1)"},
                cliponaxis: false,
                showlegend: false,
                width: 0.25,
                hoverinfo: "skip"
            };

        let trace2 =
            {
                name: "",
                x: ["<b>10300 kg</b>", "<b>3000 kg</b>"],
                y: ['', 7300],
                type: "bar",
                marker: { color: "#709626" },
                textposition: "outside",
                cliponaxis: false,
                showlegend: false,
                width: 0.25,
                hoverinfo: "skip",
            };

        const data = [trace1, trace2]
        const layout = {
            autosize: true,
            barmode: 'stack',
            margin: this.props.margin
                ? {b: 40, l: 0, r: 0, t: 20, pad: 0}
                : {b: 280, pad: 20},
            yaxis: {
                fixedrange: true,
                showticklabels: false,
                showgrid: false,
                zeroline: false,
                showline: false,
                tickfont: {
                    size: 25,
                    color: '#93be38'
                },
            },
            xaxis: {
                fixedrange: true,
                zeroline: false,
                showline: false,
                tickfont: {
                    size: 25,
                    color: '#93be38'
                },
            },
            annotations: [{
              x: 0,
              y: 11300,
              xref: 'x',
              yref: 'y',
              text: "<b>" + translate({textKey: "sit.statistics.frontpage.chart.year2019"}) + "</b>",
              showarrow: false,
              ax: 0,
              ay: 0,
              font: {
                  color: "rgba(83, 84, 85, 1)",
                  size: 14
              }
            }, {
              x: 1,
              y: 11300,
              xref: 'x',
              yref: 'y',
              text: "<b>" + translate({textKey: "sit.statistics.frontpage.chart.goal2030"}) + "</b>",
              showarrow: false,
              ax: 0,
              ay: 0,
              font: {
                color: "rgba(83, 84, 85, 1)",
                size: 14
              }

            }]
        };
        return (
            <StyledPlot
                style={this.props.style || {}}
                width={this.props.width || {}}
                height={this.props.height || {}}
                data={data}
                layout={layout}
                useResizeHandler={true}
                config={{displayModeBar: false}}
            />
        );
    }
}

export default BarChartStyled;
