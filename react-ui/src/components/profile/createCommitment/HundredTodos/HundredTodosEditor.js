// @flow

import React, { Component, Fragment } from "react";
import produce from "immer";

import WhiteSpace from "../../../reusable/WhiteSpace";
import Row from "../../../reusable/Row";
import translate from "../../../reusable/translate";

import type {
  OperationType,
  MeterType,
  ReportType,
  CommitmentType,
  DoneOperationType,
  SMARTWAY_ARTICLE_TYPE
} from "../../../../constants/types";

import HundredTodosMeterEditor from "./HundredTodosMeterEditor";
import HundredTodosAddMeter from "./HundredTodosAddMeter";
import { getCo2DecreasePercentage } from "../../../../utils/hundredTodos";

type State = {
  metersFromArticles: Array<MeterType>
};

type Props = {
  hundredSmartWaysArticles: Array<SMARTWAY_ARTICLE_TYPE>,
  locale: "fi_FI" | "en_US", // | "sv_SE"
  index: number,
  updateOperation: (OperationType, number) => void,
  setDoneOperations: (Array<DoneOperationType>) => void,
  newOpMeters: Array<MeterType>,
  removeOperation: () => void,
  infoTexts: {
    [id: string]: {
      id: string,
      info: string,
      placeholder: string
    }
  },
  id?: string,
  report?: ReportType,
  updateReport?: ReportType => void,
  lockedMeters?: Array<MeterType & { commitmentOperationMeterRefId?: ?string }>,
  commitment: CommitmentType,
  toDo: boolean,
  disableEdit?: boolean
};

class HundredTodosEditor extends Component<Props, State> {
  state = {
    metersFromArticles: []
  };

  componentDidMount() {
    this.parseMetersFromArticlesPayload(this.props.hundredSmartWaysArticles);
  }

  componentWillReceiveProps(nextProps: Props) {
    if (
      nextProps.hundredSmartWaysArticles &&
      nextProps.hundredSmartWaysArticles.length > 0 &&
      (!this.props.hundredSmartWaysArticles ||
        this.props.hundredSmartWaysArticles.length === 0)
    ) {
      this.parseMetersFromArticlesPayload(nextProps.hundredSmartWaysArticles);
    }
  }

  removeMeter = (id: string) => {
    const op = {
      ...this.getOperation()
    };
    op.meters = op.meters.filter(m => m.meterId !== id);
    const doneOps = this.props.commitment.doneOperations
      ? this.props.commitment.doneOperations
      : [];
    this.props.setDoneOperations(doneOps.filter(op => op.operationId !== id));
    this.props.updateOperation(op, this.props.index);
  };

  //Add a single doneMeter manually from the UI.
  addMeter = (meter: MeterType) => {
    if (meter && meter.meterId) {
      const op = {
        ...this.getOperation()
      };
      op.meters = op.meters.concat([meter]);
      const doneOps = this.props.commitment.doneOperations
        ? this.props.commitment.doneOperations
        : [];
      this.props.setDoneOperations(
        doneOps.concat([this.meterToDoneOperation(meter)])
      );
      this.props.updateOperation(op, this.props.index);
    }
  };

  //These are parsed from articles-data only, and sent with 0 targetLevel value.
  //These are used with doneMeters that are added manually by the user.
  //Currently done tasks do not utilize target level anywhere, so it can be 0.
  // $FlowFixMe
  parseMetersFromArticlesPayload = articles => {
    const parsedArticleMeters =
      articles &&
      articles.map(article => {
        return {
          meterId: article.articleId,
          meterType_fi_FI:
            article.title && article.title.fi_FI ? article.title.fi_FI : "",
          meterType_sv_SE:
            article.title && article.title.sv_SE ? article.title.sv_SE : "",
          meterType_en_US:
            article.title && article.title.en_US ? article.title.en_US : "",
          meterValueType: "NUMBER",
          startingLevel: 0,
          targetLevel: 0,
          meterChartType: "LINE",
          meterCategory: article ? article.category : ""
        };
      });
    this.setState(
      // $FlowFixMe
      produce(draft => {
        draft.metersFromArticles = parsedArticleMeters;
      })
    );
  };

  getMetersToDisplay = (props: Props) => {
    const findMeterArticle = id => {
      return (
        props.hundredSmartWaysArticles &&
        props.hundredSmartWaysArticles.find(el => el.articleId === id)
      );
    };

    const operation = this.getOperation(props);
    const meters = operation
      ? operation.meters.map(meter => {
          if (!meter.meterCategory) {
            const article = findMeterArticle(meter.meterId);
            return {
              ...meter,
              meterCategory: article ? article.category : ""
            };
          } else {
            return meter;
          }
        })
      : [];

    const filteredMeters = meters.filter(m => {
      const index = props.commitment.doneOperations
        ? props.commitment.doneOperations.findIndex(
            op => op.operationId === m.meterId
          )
        : -1;
      return props.toDo ? index < 0 : index >= 0;
    });
    return this.groupMeters(filteredMeters);
  };

  // $FlowFixMe
  groupMeters(meters: Array<MeterType>, initialGrouping = {}) {
    const groupedMeters = meters.reduce(
      (acc, meter) => {
        const category = meter.meterCategory ? meter.meterCategory : "";
        let arr = acc[category];
        if (arr) {
          arr.push(meter);
        } else {
          arr = [meter];
          acc[category] = arr;
        }
        return acc;
      },
      { ...initialGrouping }
    );

    return groupedMeters;
  }

  // $FlowFixMe
  getOperation = (props = this.props): ?OperationType => {
    return (
      props.commitment &&
      props.commitment.operations &&
      props.commitment.operations.find(el => true)
    ); // find first or undefined
  };
  meterToDoneOperation = (meter: MeterType) => {
    return {
      operationId: meter.meterId ? meter.meterId : "",
      operationTitle_fi_FI: meter.meterType_fi_FI,
      operationTitle_sv_SE: meter.meterType_sv_SE,
      operationTitle_en_US: meter.meterType_en_US,
      operationCategory: meter.meterCategory
    };
  };

  render() {
    const { id } = this.props;
    const operation = this.getOperation();
    const metersToDisplay = this.getMetersToDisplay(this.props);
    const metersByCategory = Object.keys(metersToDisplay).map(key => {
      const meters = metersToDisplay[key].map((meter, index) => {
        return (
          <HundredTodosMeterEditor
            {...this.props}
            key={meter.meterId || meter.key}
            operation={operation}
            index={index}
            operationMeters={[0]}
            meter={meter}
            removeMeter={this.removeMeter}
            disableEdit={this.props.disableEdit}
          />
        );
      });
      const trimmedKey = key.toLowerCase().replace(new RegExp(" ", "g"), "");
      return (
        <div key={key}>
          <div style={{ borderBottom: "1px solid grey", fontWeight: "bold" }}>
            {translate({
              languageOverride: this.props.locale,
              textKey:
                "sit.commitmentCreation.elamantapa.meterCategory." + trimmedKey
            })}
          </div>
          {meters}
        </div>
      );
    });

    const meters = operation ? operation.meters : [];
    const selectedMeterIds = meters.map(m => m.meterId);

    const addNewDoneTask = (
      <HundredTodosAddMeter
        locale={this.props.locale}
        addMeter={this.addMeter}
        meters={this.state.metersFromArticles}
        selectedMeterIds={selectedMeterIds}
      />
    );

    const title = this.props.toDo
      ? translate({
          languageOverride: this.props.locale,
          textKey: "sit.commitmentCreation.elamantapa.todoMetersTitle",
          params: [getCo2DecreasePercentage(this.props.commitment).toFixed(1)]
        })
      : translate({
          languageOverride: this.props.locale,
          textKey: "sit.commitmentCreation.elamantapa.doneMetersTitle"
        });

    return (
      <div
        id={id}
        style={{ border: "2px solid #93BE38", padding: 10, borderRadius: 6 }}
      >
        <Fragment>
          <span
            data-testid={
              "hundredtodos_title_" + (this.props.toDo ? "todo" : "done")
            }
          >
            <b>{title}</b>
          </span>
        </Fragment>

        <WhiteSpace height="10px" />
        {metersByCategory}
        <WhiteSpace height="12px" />

        {!this.props.toDo &&
          !this.props.disableEdit && (
            <div>
              <Row justifyContent="flex-start" alignItems="center">
                <h5
                  style={{
                    color: "#000",
                    fontWeight: "bold",
                    textTransform: "uppercase"
                  }}
                >
                  {translate({
                    languageOverride: this.props.locale,
                    textKey: "sit.commitment.operations.addTaskTitle"
                  })}
                </h5>
              </Row>
              <Row justifyContent="flex-start" alignItems="center">
                {addNewDoneTask}
              </Row>
            </div>
          )}
      </div>
    );
  }
}

export default HundredTodosEditor;
