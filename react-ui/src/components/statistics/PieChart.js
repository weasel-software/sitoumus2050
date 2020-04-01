// @flow
import React from "react";

import Plotly from "plotly.js-basic-dist";
import createPlotlyComponent from "react-plotly.js/factory";
import styled from "styled-components";

import translate from "../reusable/translate";
import { media } from "../../utils/style-utils";

const Plot = createPlotlyComponent(Plotly);

const StyledPlot = styled(Plot)`
  width: ${props => props.width || "100%"};
  height: ${props => props.height || "500px"};
  ${media.pieFix`
    height: ${props => props.height || "1000px"};
  `};
`;

class PieChart extends React.Component<{
  labels: Array<string>,
  values: Array<number>,
  style?: any
}> {
  render() {
    const data = [
      {
        name: translate({ textKey: "sit.statistics.commitments" }),
        labels: this.props.labels,
        values: this.props.values,
        type: "pie",
        textinfo: "none",
        hoverinfo: "value+label"
      }
    ];

    const layout = {
      showlegend: true,
      margin: {
        t: 0,
        l: 0
      },
      legend: {
        orientation: "h",
        traceorder: "reversed"
      },
      yaxis: {
        autorange: "reversed"
      },
      hoverlabel: {
        bgcolor: "#fff",
        bordercolor: "#000"
      }
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
export default PieChart;
