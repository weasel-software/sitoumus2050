// @flow

import React from "react";

import Plotly from "plotly.js-basic-dist";
import createPlotlyComponent from "react-plotly.js/factory";
import styled from "styled-components";

import translate from "../reusable/translate";

const Plot = createPlotlyComponent(Plotly);

const StyledPlot = styled(Plot)`
  width: ${props => props.width || "100%"};
  height: ${props => props.height || "70vw"};
  max-height: ${props => props.height || "60vh"};
`;

class BarChart extends React.Component<{
  labels: Array<string>,
  values: Array<number>,
  style?: any,
  margin?: boolean
}> {
  render() {
    const data = [
      {
        name: translate({ textKey: "sit.statistics.commitments" }),
        x: this.props.labels,
        y: this.props.values,
        marker: { color: "#93be38" },
        type: "bar",
        hoverinfo: "y"
      }
    ];

    const layout = {
      autosize: true,
      hoverlabel: {
        bgcolor: "#FFFFFF"
      },
      margin: this.props.margin
        ? { b: 60, l: 40, t: 0, pad: 10 }
        : { b: 280, pad: 10 },
      yaxis: {
        fixedrange: true,
        gridwidth: 2,
        nticks: 4
      },
      xaxis: { type: "category", fixedrange: true }
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
export default BarChart;
