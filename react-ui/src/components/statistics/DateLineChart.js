// @flow

import React from "react";

import Plotly from "plotly.js-basic-dist";
import createPlotlyComponent from "react-plotly.js/factory";

import styled from "styled-components";
import translate from "../reusable/translate";

const Plot = createPlotlyComponent(Plotly);

const StyledPlot = styled(Plot)`
  width: ${props => props.width || "80%"};
  height: ${props => props.height || "60vw"};
  max-height: ${props => !props.height && "60vh"};
  margin-top: ${props => !props.height && "1vh"};
  display: flex;
  flex: 1 1 auto;
`;

class DateLineChart extends React.Component<{
  dates: Array<string>,
  values: Array<number>,
  texts: Array<string>,
  tickFormat?: string,
  margin?: boolean,
  mode?: string,
  style?: any
}> {
  render() {
    const data = [
      {
        name: translate({ textKey: "sit.statistics.commitments" }),
        x: this.props.dates,
        y: this.props.values,
        text: this.props.texts,
        type: "scatter",
        mode: this.props.mode || "lines",
        textposition: this.props.mode ? "bottom right" : null,
        cliponaxis: false,
        marker: { color: "#93be38", size: 8 },
        hoverinfo: "text+y",
        hovermode: "closest"
      }
    ];

    const layout = {
      autosize: true,
      spikedistance: -1,
      hoverlabel: {
        bgcolor: "#FFFFFF"
      },
      margin: this.props.margin && { t: 10, l: 40, b: 60, r: 60, pad: 15 },
      xaxis: {
        tickformat: this.props.tickFormat || "%m.%Y",
        spikesnap: "cursor",
        spikedash: "solid",
        spikethickness: 1,
        spikemode: "across",
        spikecolor: "#DDDDDD",
        gridwidth: 2
      },
      yaxis: { gridwidth: 2 }
    };
    return (
      <StyledPlot
        style={this.props.style || {}}
        data={data}
        layout={layout}
        useResizeHandler={true}
        config={{ displayModeBar: false }}
      />
    );
  }
}
export default DateLineChart;
