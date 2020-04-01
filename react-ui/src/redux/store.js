// @flow

import { applyMiddleware, createStore, compose } from "redux";
import thunk from "redux-thunk";
import { composeWithDevTools } from "redux-devtools-extension";
import { persistStore, persistCombineReducers } from "redux-persist";

import storage from "redux-persist/lib/storage/session";

import user, { type USER_STATE } from "./user";
import articles, { type ARTICLES_STATE } from "./articles";
import organizations, { type ORGANIZATION_STATE } from "./organizations";
import errors, { type ERRORS_STATE } from "./errors";
import statistics, { type STATISTICS_STATE } from "./statistics";
import counts, { type COUNTS_STATE } from "./counts";
import type {MAINTENANCE_STATE} from "./maintenance";
import maintenance from "./maintenance";

const persistConfig = {
  key: "primary",
  storage
};

const rootReducer = persistCombineReducers(persistConfig, {
  user,
  articles,
  organizations,
  errors,
  statistics,
  maintenance,
  counts
});

let composer = null;
if (process.env.NODE_ENV !== "production") {
  composer = composeWithDevTools;
} else {
  composer = compose;
}

export type STORE_STATE = {
  user: USER_STATE,
  articles: ARTICLES_STATE,
  organizations: ORGANIZATION_STATE,
  errors: ERRORS_STATE,
  statistics: STATISTICS_STATE,
  maintenance: MAINTENANCE_STATE,
  counts: COUNTS_STATE
};

// $FlowFixMe
const store = createStore(rootReducer, composer(applyMiddleware(thunk)));
const persistor = persistStore(store);
export { persistor };
export default store;
