// @flow

// Polyfills for IE11
import "core-js/fn/array";
import "core-js/fn/string";
import "core-js/fn/object";
import "core-js/fn/promise";
import "classlist-polyfill";
import "element-dataset";
import React from "react";
import "./index.css";
import "./App.css";
import "react-datepicker/dist/react-datepicker.min.css";

import ReactHabitat from "react-habitat";
import { ReduxDomFactory } from "react-habitat-redux";
import { Router } from "react-router-dom";
import createHistory from "history/createHashHistory";
import { PersistGate } from "redux-persist/es/integration/react";
import ReactGA from "react-ga";
import ReactPixel from "react-facebook-pixel";

import store from "./redux/store";

import Profile from "./components/profile";
import Signup from "./components/signup/";
import Commitment from "./components/commitment";
import CommitmentListContainer from "./components/commitmentList/CommitmentListContainer";
import CommitmentStatisticsContainer from "./components/statistics/CommitmentStatisticsContainer";
import LoginSignupNavbarButtons from "./components/signup/LoginSignupNavbarButtons";
import Statistics from "./components/statistics";
import OrganizationCommitments from "./components/commitment/OrganizationCommitments";
import ReportListingContainer from "./components/commitmentList/ReportListContainer";
import CommitmentMaintenance from "./components/maintenance/CommitmentMaintenance";

import LanguageSelector from "./components/reusable/LanguageSelector";
import ErrorContainer from "./components/reusable/ErrorContainer";
import SomeShareContainer from "./components/reusable/SomeShareContainer";

import { persistor } from "./redux/store";
import QuickSignUpLogin from "./components/signup/QuickSignUpLogin";

const history = createHistory();

// const maxThemeContainerWidth = "1440px";
// const maxContanetContainerWidth = "1176px";
// const iconMargin = "10px";
// const green1 = "#93BE38";
// const green2 = "#85AE2F";
// const grey1 = "#333333";
// const grey2 = "#F4F4F4";
// const grey3 = "#AEAEAE";
// const grey4 = "#212121";
// const transparentTextBgColor = "rgba(51,51,51,0.75)";
// const white = "#ffffff";
// const boxShadowGrey = "0px 4px 4px 0px #D8D8D8";
// const baseFontSize = "16px";
// const baseLineHeight = "30px";

const WrapperFactory = Component => props => (
  <PersistGate loading={null} persistor={persistor}>
    <Router history={history}>
      <Component {...props} />
    </Router>
  </PersistGate>
);

class HabitatBootstrapper extends ReactHabitat.Bootstrapper {
  constructor() {
    super();

    const builder = new ReactHabitat.ContainerBuilder();
    builder.factory = new ReduxDomFactory(store);

    builder
      .register(WrapperFactory(LoginSignupNavbarButtons))
      .as("navbar_links")
      .withOptions({
        tag: "div",
        className: "inline-block"
      });
    builder
      .register(WrapperFactory(QuickSignUpLogin))
      .as("quick_sign_up_login")
      .withOptions({
        tag: "div",
        className: "inline-block"
      });
    builder.register(WrapperFactory(Signup)).as("signup_register");
    builder.register(WrapperFactory(Profile)).as("profile");
    builder.register(WrapperFactory(Statistics)).as("statistics");
    builder.register(WrapperFactory(Commitment)).as("commitment");
    builder
      .register(WrapperFactory(CommitmentListContainer))
      .as("commitmentListing");
    builder.register(WrapperFactory(CommitmentStatisticsContainer)).as("commitmentStatistics");
    builder
      .register(WrapperFactory(ReportListingContainer))
      .as("reportListing");
    builder.register(WrapperFactory(LanguageSelector)).as("language_selector");

    builder.register(WrapperFactory(ErrorContainer)).as("error_container");
    builder
      .register(WrapperFactory(OrganizationCommitments))
      .as("organizationCommitments");

    builder
      .register(WrapperFactory(SomeShareContainer))
      .as("some_share_container");

    builder
      .register(WrapperFactory(CommitmentMaintenance))
      .as("commitmentMaintenance");

    ReactGA.initialize("UA-129217965-1", {
      debug: true
    });

    const options = {
      autoConfig: true, // set pixel's autoConfig
      debug: true // enable logs
    };
    ReactPixel.init("2251498305124763", {}, options);
    this.setContainer(builder.build());
  }
}

export default new HabitatBootstrapper();
