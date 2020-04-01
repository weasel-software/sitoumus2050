// @flow

import { Component } from "react";
import { values } from "lodash";

class ScrollTo extends Component<
  {
    element?: ?HTMLElement,
    position?: {
      x: number,
      y: number
    },
    top?: boolean
  },
  any
> {
  componentDidMount() {
    const { element, position, top } = this.props;
    if (element) {
      element.scrollIntoView && element.scrollIntoView();
    } else if (position) {
      window.scrollTo(...values(position));
    } else if (top) {
      window.scrollTo(0, 0);
    }
  }

  render() {
    return null;
  }
}

export default ScrollTo;
