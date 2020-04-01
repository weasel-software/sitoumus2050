// @flow

import type {
  CommitmentType,
  CATEGORY_TYPE,
  OperationType,
  MeterType,
  CityType,
  SMARTWAY_ARTICLE_TYPE
} from "../../../constants/types";

import { toArray, flatMap } from "lodash";
import React from "react";
import {
  render,
  fireEvent
  // cleanup,
  // waitForElement
} from "react-testing-library";
import "jest-dom/extend-expect";
import { Router } from "react-router-dom";
import { createMemoryHistory } from "history";

import CommitmentCreationForm from "./CommitmentCreationForm";
import Articles from "../../../../test-utils/articles_dump";
import Organizations from "../../../../test-utils/organizations_dump";
import User from "../../../../test-utils/user_dump";

function renderWithRouter(
  ui,
  {
    route = "/",
    history = createMemoryHistory({ initialEntries: [route] })
  } = {}
) {
  return {
    ...render(<Router history={history}>{ui}</Router>),
    // adding `history` to the returned utilities to allow us
    // to reference it in our tests (just try to avoid using
    // this to test implementation details).
    history
  };
}

const MockedCommitment: CommitmentType = {
  language: "fi_FI",
  latitude: 0,
  longitude: 0,
  acceptCriterias: true,
  address: "",
  primaryGoal: 0,
  backgroundInformation_en_US: "",
  backgroundInformation_sv_SE: "",
  backgroundInformation_fi_FI: "Mockatun sitomuksen taustatiedot",
  commitmentImageUrl: "/documents/20143/60679/fakeasdfg",
  commitmentType: "COMMITMENT",
  created: "Wed Jul 25 08:17:55 GMT 2018",
  createdByUserId: "38291",
  createdByUserName: "Sit Test",
  creatorPortrait: "",
  endDate: "2028-08-03T21:00:00.000Z",
  groupId: 20143,
  id: "97244",
  innovation_en_US: "",
  innovation_fi_FI: "Mockisitoumuksen innovaatioteksti",
  // innovation_fi_FI: "",
  innovation_sv_SE: "",
  joined: 0,
  likes: 0,
  operations: [
    // $FlowFixMe
    {
      meters: [
        {
          meterChartType: "LINE",
          meterId: "tkEj",
          meterType_en_US: "",
          meterType_fi_FI: "Julkisen liikenteen matkojen määrä  % matkoista",
          meterType_sv_SE: "",
          meterValueType: "NUMBER",
          startingLevel: 5,
          targetLevel: 555,
          meterCategory: ""
        }
      ],
      reports: [],
      operationDescription_en_US: "",
      operationDescription_fi_FI: "Mock description",
      operationDescription_sv_SE: "",
      operationId: "qQkZ",
      operationTitle_en_US: "",
      operationTitle_fi_FI: "Julkisen liikenteen suosiminen",
      operationTitle_sv_SE: ""
    }
  ],
  organizationLogo: "",
  organizationName: "",
  reportReminder: false,
  reportingInterval: "MONTH_3",
  shortDescription_fi_FI: "Mockisitoumuksen lyhyt kuvaus",
  shortDescription_en_US: "",
  shortDescription_sv_SE: "",
  startDate: "2018-07-24T21:00:00.000Z",
  status: "draft",
  title_fi_FI: "Mocksitoumuksen title",
  title_sv_SE: "",
  title_en_US: "",
  updated: "Thu Aug 02 09:04:01 GMT 2018",
  version: 1,
  categoryIds: [
    31805,
    31813,
    31815,
    31816,
    31821,
    31828,
    31829,
    31831,
    31834,
    31835,
    33553
  ]
};

// $FlowFixMe
const hundredSmartWaysArticles: Array<SMARTWAY_ARTICLE_TYPE> = [
  {
    articleId: "119879",
    title: {
      fi_FI: "Alennan kodin lämpötilaa kahdella asteella",
      en_US: "Lower the temperature at home"
    },
    category: "Asuminen",
    decrease: "2,5"
  },
  {
    articleId: "120744",
    title: {
      fi_FI: "Annan lahjaksi aikaa"
    },
    category: "Tuotteet ja palvelut",
    decrease: "0"
  },
  {
    articleId: "120736",
    title: {
      fi_FI: "Annan lahjaksi tarpeellista"
    },
    category: "Tuotteet ja palvelut",
    decrease: "0,9"
  },
  {
    articleId: "119966",
    title: {
      fi_FI: "Otan käyttöön ilmalämpöpumpun",
      en_US: "Start using a heat pump"
    },
    category: "Asuminen",
    decrease: "7,8"
  },
  {
    articleId: "120448",
    title: {
      fi_FI: "Luovun lentämisestä vuodeksi"
    },
    category: "Liikkuminen",
    decrease: "21,4"
  },
  {
    articleId: "119982",
    title: {
      fi_FI: "Käytän kylpyhuoneen lattialämmitystä kohtuudella",
      en_US: "Use bathroom floor heating moderately"
    },
    category: "Asuminen",
    decrease: "0,6"
  }
];

const Mocked100TodosCommitment: CommitmentType = {
  ...MockedCommitment,
  operations: [
    // $FlowFixMe
    {
      meters: [
        {
          meterChartType: "LINE",
          meterId: "119966",
          meterType_en_US: "",
          meterType_fi_FI: "Otan käyttöön ilmalämpöpumpun",
          meterType_sv_SE: "",
          meterValueType: "NUMBER",
          startingLevel: 0,
          targetLevel: 3,
          meterCategory: ""
        },
        {
          meterChartType: "LINE",
          meterId: "120448",
          meterType_en_US: "",
          meterType_fi_FI: "Luovun lentämisestä vuodeksi",
          meterType_sv_SE: "",
          meterValueType: "NUMBER",
          startingLevel: 0,
          targetLevel: 8.1,
          meterCategory: ""
        },
        {
          meterChartType: "LINE",
          meterId: "119982",
          meterType_en_US: "",
          meterType_fi_FI: "Alennan kodin lämpötilaa kahdella asteella",
          meterType_sv_SE: "",
          meterValueType: "NUMBER",
          startingLevel: 0,
          targetLevel: 0.3,
          meterCategory: ""
        },
        {
          meterChartType: "LINE",
          meterId: "119879",
          meterType_en_US: "",
          meterType_fi_FI: "Käytän kylpyhuoneen lattialämmitystä kohtuudella",
          meterType_sv_SE: "",
          meterValueType: "NUMBER",
          startingLevel: 0,
          targetLevel: 2.5,
          meterCategory: ""
        }
      ],
      reports: [],
      operationDescription_en_US: "",
      operationDescription_fi_FI: "",
      operationDescription_sv_SE: "",
      operationId: "qQkZ",
      operationTitle_en_US: "",
      operationTitle_fi_FI:
        "Näillä teoilla saavutan 3.0% pienemmän hiilijalanjäljen",
      operationTitle_sv_SE: ""
    }
  ],
  doneOperations: [
    {
      operationCategory: "Liikkuminen",
      operationId: "120448",
      operationTitle_en_US: "",
      operationTitle_fi_FI: "Luovun lentämisestä vuodeksi",
      operationTitle_sv_SE: ""
    },
    {
      operationCategory: "Asuminen",
      operationId: "119879",
      operationTitle_en_US: "",
      operationTitle_fi_FI: "Käytän kylpyhuoneen lattialämmitystä kohtuudella",
      operationTitle_sv_SE: ""
    }
  ],
  categoryIds: [
    31804,
    31820,
    31824,
    31825,
    31830,
    31831,
    31835,
    33545,
    33553,
    136875,
    140111
  ]
};

const chartTypes = ["LINE", "COLUMN", "PIE"];
const contentAreas: Array<CATEGORY_TYPE> = [];

const generalActions: Array<OperationType> = [
  {
    articleId: "66666",
    operationId: "66666",
    reports: [],
    id: "44048",
    key: "0.3646358504010938",
    meters: [
      {
        meterId: "",
        meterType_en_US: "Funding to R&D on sustainable development",
        meterType_fi_FI:
          "Rahoitus kestävän kehityksen t&k toimintaan ja hankkeisiin €/á",
        meterType_sv_SE:
          "Finansiering för FoU-verksamhet som anknyter till hållbar utveckling, €/år",
        meterValueType: "NUMBER",
        meterCategory: ""
      }
    ],
    operationTitle_en_US:
      "Funding targeted to sustainable development projects and research (e.g. bioeconomy, circular economy)",
    operationTitle_fi_FI:
      "Rahoituksen suuntaaminen kestävän kehityksen (esim bio-kiertotalous) hankkeisiin ja tutkimukseen",
    operationTitle_sv_SE:
      "Finansiering för projekt och forskning som gäller hållbar utveckling (t.ex. bioekonomi och cirkulär ekonomi)"
  }
];

const greenDealMeters: Array<MeterType> = [
  {
    meterId: "52076",
    key: "0.9922490046475092",
    meterType_en_US: "",
    meterType_fi_FI: "%",
    meterType_sv_SE: "",
    meterValueType: "NUMBER",
    meterCategory: ""
  },
  {
    meterId: "52075",
    key: "0.992249001232092",
    meterType_en_US: "",
    meterType_fi_FI: "kpl",
    meterType_sv_SE: "",
    meterValueType: "NUMBER",
    meterCategory: ""
  }
];

const nutritionMeters: Array<MeterType> = [
  {
    meterId: "521236",
    key: "0.9912346475092",
    meterType_en_US: "",
    meterType_fi_FI: "%",
    meterType_sv_SE: "",
    meterValueType: "NUMBER",
    meterCategory: ""
  },
  {
    meterId: "5212375",
    key: "0.992249123123092",
    meterType_en_US: "",
    meterType_fi_FI: "kpl",
    meterType_sv_SE: "",
    meterValueType: "NUMBER",
    meterCategory: ""
  }
];

const commonMeters: Array<MeterType> = [
  {
    meterId: "47977",
    key: "0.7753769899354592",
    meterType_en_US: "Total recordable incidence frequency TRIF (all injuries)",
    meterType_fi_FI: "Tapaturmataajuus (kaikki tapaturmat), TRIF",
    meterType_sv_SE: "Olycksfallsfrekvens (alla olycksfall), TRIF",
    meterValueType: "NUMBER",
    meterCategory: ""
  },
  {
    meterId: "47971237",
    key: "0.77123123211239354592",
    meterType_en_US: "Proportion of renewable energy %",
    meterType_fi_FI: "Uusiutuvan energian osuus %",
    meterType_sv_SE: "Andelen förnybar energi, %",
    meterValueType: "NUMBER",
    meterCategory: ""
  }
];

const cities: Array<CityType> = [
  {
    name_fi_FI: "haa",
    name_en_US: "hoo",
    name_sv_SE: "ehh",
    municipality_fi_FI: "ahh",
    municipality_en_US: "ahh",
    municipality_sv_SE: "ahh",
    longitude: 0,
    latitude: 0,
    type: "ta",
    mainLanguage: "te"
  }
];

describe("CommitmentCreationForm", () => {
  it("mounts without errors", () => {
    renderWithRouter(
      <CommitmentCreationForm
        infoTexts={{}}
        greenDealMainGoal={null}
        greenDealActions={[]}
        plasticBagMeters={[]}
        acceptedMeterChartTypes={chartTypes}
        commitmentData={MockedCommitment}
        commitmentNotification={Articles.commitmentNotification}
        contentAreas={contentAreas}
        errors={{}}
        generalActions={generalActions}
        schoolActions={generalActions}
        greendealAutomotiveIndustryActions={[]}
        getExampleCommitments={() => {}}
        exampleCommitments={[]}
        locale="fi_FI"
        greenDealMeters={greenDealMeters}
        nutritionMeters={nutritionMeters}
        location=""
        mainCategories={toArray(Articles.mainCategories)}
        mainCategoriesSecondaryObjectives={toArray(
          Articles.mainCategoriesSecondaryObjectives
        )}
        secondaryCategories={toArray(Articles.secondaryCategories)}
        mainCategoryProperties={flatMap(
          Articles.mainCategoryProperties,
          category => category
        )}
        secondaryCategoryProperties={flatMap(
          Articles.secondaryCategoryProperties,
          category => category
        )}
        hundredSmartWaysArticles={hundredSmartWaysArticles}
        profile={User.profile}
        organizations={Organizations.organizations}
        meters={commonMeters}
        newOpMeters={commonMeters}
        readyToRender={true}
        resetSavedCommitment={() => {}}
        saveCommitment={() => {}}
        selectedCreator={User.profile}
        cities={cities}
      />
    );
  });

  it("adds an operation to commitment", async () => {
    const {
      debug,
      getByText,
      getByTestId,
      getByPlaceholderText,
      queryByTestId
    } = renderWithRouter(
      <CommitmentCreationForm
        infoTexts={{}}
        greenDealActions={[]}
        plasticBagMeters={[]}
        greenDealMainGoal={null}
        greendealMainType=""
        location=""
        acceptedMeterChartTypes={chartTypes}
        commitmentData={MockedCommitment}
        commitmentNotification={Articles.commitmentNotification}
        contentAreas={contentAreas}
        errors={{}}
        generalActions={generalActions}
        schoolActions={generalActions}
        greendealAutomotiveIndustryActions={[]}
        getExampleCommitments={() => {}}
        exampleCommitments={[]}
        locale="fi_FI"
        greenDealMeters={greenDealMeters}
        nutritionMeters={nutritionMeters}
        mainCategories={toArray(Articles.mainCategories)}
        mainCategoriesSecondaryObjectives={toArray(
          Articles.mainCategoriesSecondaryObjectives
        )}
        secondaryCategories={toArray(Articles.secondaryCategories)}
        mainCategoryProperties={flatMap(
          Articles.mainCategoryProperties,
          category => category
        )}
        secondaryCategoryProperties={flatMap(
          Articles.secondaryCategoryProperties,
          category => category
        )}
        hundredSmartWaysArticles={hundredSmartWaysArticles}
        profile={User.profile}
        organizations={Organizations.organizations}
        meters={commonMeters}
        newOpMeters={commonMeters}
        readyToRender={true}
        resetSavedCommitment={() => {}}
        saveCommitment={() => {}}
        selectedCreator={User.profile}
        editing={true}
        cities={cities}
      />
    );

    const nameInput = getByPlaceholderText("Syötä sitoumuksen nimi");
    expect(nameInput.value).toEqual("Mocksitoumuksen title");

    const addOp = getByText("Lisää toimenpide");
    fireEvent.click(addOp);

    const select = getByText("Valitse toimenpide");

    select.value = "Rahoituksen suuntaaminen";
    fireEvent.change(select);

    const addMeter = getByTestId("add_meter_label");
    fireEvent.click(addMeter);

    const pickMeter = getByTestId("existing_meter_select_1");
    pickMeter.value = "Tapaturmataajuus (kaikki tapaturmat), TRIF";
    fireEvent.change(pickMeter);

    debug(pickMeter);

    const startingLevel = getByPlaceholderText("Aseta lähtötaso");
    const targetLevel = getByPlaceholderText("Aseta tavoitetaso");

    startingLevel.value = 1;
    fireEvent.change(startingLevel);

    targetLevel.value = 10;
    fireEvent.change(targetLevel);

    const graphTypeSelect = getByTestId("meter_chart_type_select_1");
    graphTypeSelect.value = "LINE";
    fireEvent.change(graphTypeSelect);

    debug(graphTypeSelect);

    const closeMeterButton = getByTestId("close_meter_1");
    fireEvent.click(closeMeterButton);

    expect(queryByTestId("existing_meter_select_1")).toBeFalsy();
    // expect(pickMeterAfter).toBeFalsy();
  });

  const HundredTodosCommitmentCreationForm = (
    <CommitmentCreationForm
      infoTexts={{}}
      greenDealActions={[]}
      plasticBagMeters={[]}
      greenDealMainGoal={0}
      location=""
      acceptedMeterChartTypes={chartTypes}
      commitmentData={Mocked100TodosCommitment}
      commitmentNotification={Articles.commitmentNotification}
      contentAreas={contentAreas}
      errors={{}}
      generalActions={generalActions}
      schoolActions={generalActions}
      greendealAutomotiveIndustryActions={[]}
      getExampleCommitments={() => {}}
      exampleCommitments={[]}
      locale="fi_FI"
      greenDealMeters={greenDealMeters}
      nutritionMeters={nutritionMeters}
      mainCategories={toArray(Articles.mainCategories)}
      mainCategoriesSecondaryObjectives={toArray(
        Articles.mainCategoriesSecondaryObjectives
      )}
      secondaryCategories={toArray(Articles.secondaryCategories)}
      mainCategoryProperties={flatMap(
        Articles.mainCategoryProperties,
        category => category
      )}
      secondaryCategoryProperties={flatMap(
        Articles.secondaryCategoryProperties,
        category => category
      )}
      profile={User.profile}
      organizations={Organizations.organizations}
      meters={commonMeters}
      newOpMeters={commonMeters}
      readyToRender={true}
      resetSavedCommitment={() => {}}
      saveCommitment={() => {}}
      selectedCreator={User.profile}
      editing={true}
      cities={cities}
      hundredSmartWaysArticles={hundredSmartWaysArticles}
    />
  );

  it("renders 100todos commitment form", async () => {
    const {
      debug,
      getByText,
      getByTestId,
      getByPlaceholderText,
      queryByTestId
    } = renderWithRouter(HundredTodosCommitmentCreationForm);

    const nameInput = getByPlaceholderText("Syötä sitoumuksen nimi");
    expect(nameInput.value).toEqual("Mocksitoumuksen title");

    const todoTitle = getByTestId("hundredtodos_title_todo");
    expect(todoTitle.textContent).toEqual(
      "Näillä teoilla saavutan 3.3% pienemmän hiilijalanjäljen"
    );

    const doneTitle = getByTestId("hundredtodos_title_done");
    expect(doneTitle.textContent).toEqual("Nämä toteutuvat jo arjessani");
  });

  it("adds new 100todos meter", async () => {
    const {
      debug,
      getByText,
      getByTestId,
      getByPlaceholderText,
      queryByTestId
    } = renderWithRouter(HundredTodosCommitmentCreationForm);

    expect(queryByTestId("120744")).toBeFalsy();

    const opSelect = getByTestId("hundredtodos-addmeter");
    opSelect.value = "Annan lahjaksi aikaa";
    fireEvent.change(opSelect);

    const addOp = getByText("Lisää toimenpide");
    fireEvent.click(addOp);

    expect(queryByTestId("120744")).toBeTruthy();
    // expect(queryByTestId("120744")).toBeFalsy();
  });
  it("removes 100todos meter", async () => {
    const {
      debug,
      getByText,
      getByTestId,
      getByPlaceholderText,
      queryByTestId
    } = renderWithRouter(HundredTodosCommitmentCreationForm);

    expect(queryByTestId("119879")).toBeTruthy();

    const remove = getByTestId("removemeter_119879");
    fireEvent.click(remove);

    expect(queryByTestId("119879")).toBeFalsy();
  });
});
