// tslint:disable:max-line-length
/**
 * Answers for questions 10-16
 */
const q10as = [
  {
    title: {
      en_US: 'I don’t drive',
      fi_FI: 'en kulje autolla',
      sv_SE: 'jag kör inte bil',
    },
    index: 201,
    co2e: null,
    multiplier: 0,
    tips: null,
  },
  {
    title: {
      en_US: 'Less than 100 km',
      fi_FI: 'alle 100 km',
      sv_SE: 'mindre än 100 km',
    },
    index: 202,
    co2e: null,
    multiplier: 3120,
    tips: [
      68593,
      68595,
      68597,
      68601,
      68613,
      68615,
      68617,
      68619,
      68621,
      68603,
      68611,
      68625,
    ],
  },
  {
    title: {
      en_US: '100 - 400 km',
      fi_FI: '100 - 400 km',
      sv_SE: '100 - 400 km',
    },
    index: 203,
    co2e: null,
    multiplier: 11960,
    tips: [
      68593,
      68595,
      68597,
      68601,
      68613,
      68615,
      68617,
      68619,
      68621,
      68623,
      68603,
      68611,
      68625,
    ],
  },
  {
    title: {
      en_US: 'More than 400 km',
      fi_FI: 'yli 400 km',
      sv_SE: 'mer än 400 km',
    },
    index: 204,
    co2e: null,
    multiplier: 36400,
    tips: [
      68593,
      68595,
      68597,
      68601,
      68613,
      68615,
      68619,
      68621,
      68623,
      68603,
      68611,
      68625,
    ],
  },
];

const q11as = [
  {
    title: {
      en_US: 'Petrol',
      fi_FI: 'Bensiinillä',
      sv_SE: 'Bensin',
    },
    index: 205,
    co2e: null,
    multiplier: [0.094, 0.06, null],
    tips: [68593, 68595, 68111],
  },
  {
    title: {
      en_US: 'Diesel',
      fi_FI: 'Dieselillä',
      sv_SE: 'Diesel',
    },
    index: 206,
    co2e: null,
    multiplier: [0.083, 0.06, null],
    tips: [68593, 68595, 68111],
  },
  {
    title: {
      en_US: 'Gas or ethanol',
      fi_FI: 'Kaasulla tai etanolilla',
      sv_SE: 'Gas eller etanol',
    },
    index: 207,
    co2e: null,
    multiplier: [0.025, 0.06, null],
    tips: [68593],
  },
  {
    title: {
      en_US: 'Electricity',
      fi_FI: 'Sähköllä',
      sv_SE: 'El',
    },
    index: 208,
    co2e: null,
    multiplier: [null, 0.09, null],
    tips: [68595],
  },
  {
    title: {
      en_US: 'Hybrid',
      fi_FI: 'Hybridillä',
      sv_SE: 'Hybrid',
    },
    index: 209,
    co2e: null,
    multiplier: [0.037, 0.09, null],
    tips: [68595, 68593, 68111],
  },
];

const q12as = [
  {
    title: {
      en_US: '4 or more people in addition to myself',
      fi_FI: '4 tai useampi henkilöä lisäkseni',
      sv_SE: 'jag och 4 andra eller fler',
    },
    index: 210,
    co2e: null,
    multiplier: 5,
    tips: [68788],
  },
  {
    title: {
      en_US: '3 people in addition to myself',
      fi_FI: '3 henkilöä lisäkseni',
      sv_SE: 'jag och 3 andra',
    },
    index: 211,
    co2e: null,
    multiplier: 4,
    tips: [68605, 68788],
  },
  {
    title: {
      en_US: '2 people in addition to myself',
      fi_FI: '2 henkilöä lisäkseni',
      sv_SE: 'jag och 2 andra',
    },
    index: 212,
    co2e: null,
    multiplier: 3,
    tips: [68605, 68788],
  },
  {
    title: {
      en_US: '1 people in addition to myself',
      fi_FI: '1 henkilö lisäkseni',
      sv_SE: 'jag och 1 annan',
    },
    index: 213,
    co2e: null,
    multiplier: 2,
    tips: [68599, 68605, 68788],
  },
  {
    title: {
      en_US: 'I drive on my own',
      fi_FI: 'kuljen autolla yksin',
      sv_SE: 'jag åker bil ensam',
    },
    index: 214,
    co2e: null,
    multiplier: 1,
    tips: [68599, 68605, 68607, 68788],
  },
];

const q13as = [
  {
    title: {
      en_US: 'I don’t use public transport at all',
      fi_FI: 'en käytä joukkoliikennettä lainkaan',
      sv_SE: 'jag använder inte kollektivtrafik alls',
    },
    index: 215,
    co2e: 0,
    multiplier: null,
    tips: [68619],
  },
  {
    title: {
      en_US: 'Less than 100 km',
      fi_FI: 'alle 100 km',
      sv_SE: 'mindre än 100 km',
    },
    index: 216,
    co2e: 82,
    multiplier: null,
    tips: [68609, 68619],
  },
  {
    title: {
      en_US: '100-400 km',
      fi_FI: '100 - 400 km',
      sv_SE: '100 - 400 km',
    },
    index: 217,
    co2e: 343,
    multiplier: null,
    tips: [68609],
  },
  {
    title: {
      en_US: 'More than 400 km',
      fi_FI: 'yli 400 km',
      sv_SE: 'mer än 400 km',
    },
    index: 218,
    co2e: 735,
    multiplier: null,
    tips: [68609],
  },
];

const q14as = [
  {
    title: {
      en_US: 'I have not travelled by plane at all',
      fi_FI: 'en ole lentänyt laisinkaan',
      sv_SE: 'jag har inte flugit alls',
    },
    index: 219,
    co2e: null,
    multiplier: 0,
    tips: null,
  },
  {
    title: {
      en_US: 'Less than 5 hours',
      fi_FI: 'alle 5 tuntia',
      sv_SE: 'mindre än 5 timmar',
    },
    index: 220,
    co2e: null,
    multiplier: 668,
    tips: [68627, 68629, 68631, 68633, 68639],
  },
  {
    title: {
      en_US: '5-15 hours',
      fi_FI: '5 - 15 tuntia',
      sv_SE: '5 - 15 timmar',
    },
    index: 221,
    co2e: null,
    multiplier: 2171,
    tips: [68627, 68629, 68631, 68633, 68635, 68647, 68639],
  },
  {
    title: {
      en_US: '15 - 30 hours',
      fi_FI: '15 - 30 tuntia',
      sv_SE: '15 - 30 timmar',
    },
    index: 222,
    co2e: null,
    multiplier: 4259,
    tips: [68627, 68629, 68631, 68635, 68647, 68639],
  },
  {
    title: {
      en_US: 'More than 30 hours',
      fi_FI: 'yli 30 tuntia',
      sv_SE: 'mer än 30 timmar',
    },
    index: 223,
    co2e: null,
    multiplier: 8517,
    tips: [68627, 68629, 68631, 68635, 68647],
  },
];

const q15as = [
  {
    title: {
      en_US: 'Yes',
      fi_FI: 'kyllä',
      sv_SE: 'ja',
    },
    index: 224,
    co2e: 2,
    multiplier: 2,
    tips: null,
  },
  {
    title: {
      en_US: 'No',
      fi_FI: 'ei',
      sv_SE: 'nej',
    },
    index: 225,
    co2e: 1,
    multiplier: 1,
    tips: [68772, 68778, 68780, 68788],
  },
];

const q16as = [
  {
    title: {
      en_US: 'I have not travelled by ferry',
      fi_FI: 'en ole matkustanut laivalla',
      sv_SE: 'jag har inte rest med båt',
    },
    index: 226,
    co2e: 0,
    multiplier: null,
    tips: null,
  },
  {
    title: {
      en_US: '1 - 4 trips',
      fi_FI: '1 - 4 matkaa',
      sv_SE: '1-4 resor',
    },
    index: 227,
    co2e: 598,
    multiplier: null,
    tips: [68633, 68639],
  },
  {
    title: {
      en_US: '5 - 15 trips',
      fi_FI: '5 - 15 matkaa',
      sv_SE: '5-15 resor',
    },
    index: 228,
    co2e: 2220,
    multiplier: null,
    tips: [68633, 68635, 68639],
  },
  {
    title: {
      en_US: 'More than 15 trips',
      fi_FI: 'yli 15 matkaa',
      sv_SE: 'fler än 15 resor',
    },
    index: 229,
    co2e: 2904,
    multiplier: null,
    tips: [68633, 68635, 68639],
  },
];

/**
 * Questions 10-16
 */

const cat2qs = [
  {
    index: 10,
    title: {
      en_US: '1.	How many kilometres per week do you typically drive?',
      fi_FI: 'Kuinka monta kilometriä kuljet tyypillisesti autolla viikossa?',
      sv_SE: 'Hur många kilometer kör du med bilen en vanlig vecka?',
    },
    answers: q10as,
  },
  {
    index: 11,
    title: {
      en_US: 'What does your car run on?',
      fi_FI: 'Millä autosi kulkee?',
      sv_SE: 'Vad för bränsle använder din bil?',
    },
    answers: q11as,
  },
  {
    index: 12,
    title: {
      en_US: 'How many people usually travel with you in the car?',
      fi_FI: 'Kuinka monen henkilön kanssa autoilet pääsääntöisesti?',
      sv_SE: 'Hur många personer brukar vanligtvis åka med dig i bilen?',
    },
    answers: q12as,
  },
  {
    index: 13,
    title: {
      en_US: 'How many kilometres per week do you travel by public transport?',
      fi_FI: 'Kuinka monta kilometriä kuljet joukkoliikenteellä viikossa?',
      sv_SE: 'Hur många kilometer åker du kollektivtrafik i veckan?',
    },
    answers: q13as,
  },
  {
    index: 14,
    title: {
      en_US:
        'How many hours have you travelled by plane in the past 12 months?',
      fi_FI: 'Kuinka monta tuntia olet lentänyt viimeisen vuoden aikana?',
      sv_SE: 'Hur många timmar har du flugit det senaste året?',
    },
    answers: q14as,
  },
  {
    index: 15,
    title: {
      en_US:
        'Have you compensated for the emissions from your flights with voluntary carbon offset payments?',
      fi_FI:
        'Oletko kompensoinut lentomatkustuksen päästöjä vapaaehtoisilla maksuilla?',
      sv_SE:
        'Har du kompenserat flygtrafikens utsläpp med frivilliga avgifter?',
    },
    answers: q15as,
  },
  {
    index: 16,
    title: {
      en_US:
        'How many return trips have you made by ferry in the past 12 months?',
      fi_FI:
        'Kuinka monta edestakaista laivamatkaa olet tehnyt viimeisen vuoden aikana?',
      sv_SE: 'Hår många tur-retur-resor med båt har du gjort det senaste året?',
    },
    answers: q16as,
  },
];

export default cat2qs;
