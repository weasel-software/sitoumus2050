// tslint:disable:max-line-length
/**
 * Answers for question 17-23
 */
const q17as = [
  {
    title: {
      en_US: 'Less',
      fi_FI: 'vähemmän',
      sv_SE: 'mindre',
    },
    index: 301,
    co2e: 1360,
    multiplier: 0.85,
    tips: [68548, 68692],
  },
  {
    title: {
      en_US: 'About the same amount ',
      fi_FI: 'suunnilleen saman verran ',
      sv_SE: 'ungefär lika mycket',
    },
    index: 302,
    co2e: 1600,
    multiplier: 1,
    tips: [68548, 68692],
  },
  {
    title: {
      en_US: 'More',
      fi_FI: 'enemmän',
      sv_SE: 'mer',
    },
    index: 303,
    co2e: 1840,
    multiplier: 1.15,
    tips: [68686, 68548, 68692],
  },
];
const q18as = [
  {
    title: {
      en_US: 'Never',
      fi_FI: 'en lainkaan',
      sv_SE: 'inte alls',
    },
    index: 304,
    co2e: -356,
    multiplier: 0,
    tips: null,
  },
  {
    title: {
      en_US: '1 - 3 times a week',
      fi_FI: '1 - 3 kertaa viikossa',
      sv_SE: '1-3 gånger i veckan',
    },
    index: 305,
    co2e: -258,
    multiplier: 2.6,
    tips: [
      68651,
      68653,
      68655,
      68657,
      68659,
      68661,
      68665,
      68667,
      68669,
      68671,
    ],
  },
  {
    title: {
      en_US: '4 - 7 times a week',
      fi_FI: '4 - 7 kertaa viikossa',
      sv_SE: '4-7  gånger i veckan',
    },
    index: 306,
    co2e: -134,
    multiplier: 5.9,
    tips: [
      68651,
      68653,
      68655,
      68657,
      68659,
      68661,
      68665,
      68667,
      68669,
      68671,
    ],
  },
  {
    title: {
      en_US: 'Several times a day',
      fi_FI: 'useita kertoja päivässä',
      sv_SE: 'flera gånger om dagen',
    },
    index: 307,
    co2e: 515,
    multiplier: 23.8,
    tips: [
      68651,
      68653,
      68655,
      68657,
      68659,
      68661,
      68665,
      68667,
      68669,
      68671,
    ],
  },
];

const q19as = [
  {
    title: {
      en_US: 'Never',
      fi_FI: 'en lainkaan',
      sv_SE: 'inte alls',
    },
    index: 308,
    co2e: -58,
    multiplier: 0,
    tips: null,
  },
  {
    title: {
      en_US: '1 - 3 times a week',
      fi_FI: '1 - 3 kertaa viikossa',
      sv_SE: '1-3 gånger i veckan',
    },
    index: 309,
    co2e: -41,
    multiplier: 2.6,
    tips: [
      68651,
      68653,
      68655,
      68659,
      68661,
      68663,
      68665,
      68667,
      68669,
      68671,
    ],
  },
  {
    title: {
      en_US: '4 - 7 times a week',
      fi_FI: '4 - 7 kertaa viikossa',
      sv_SE: '4-7  gånger i veckan',
    },
    index: 310,
    co2e: -19,
    multiplier: 5.9,
    tips: [
      68651,
      68653,
      68655,
      68659,
      68661,
      68663,
      68665,
      68667,
      68669,
      68671,
    ],
  },
  {
    title: {
      en_US: 'Several times a day',
      fi_FI: 'useita kertoja päivässä',
      sv_SE: 'flera gånger om dagen',
    },
    index: 311,
    co2e: 13,
    multiplier: 23.5,
    tips: [
      68651,
      68653,
      68655,
      68659,
      68661,
      68663,
      68665,
      68667,
      68669,
      68671,
    ],
  },
];

const q20as = [
  {
    title: {
      en_US: 'Never',
      fi_FI: 'en lainkaan',
      sv_SE: 'inte alls',
    },
    index: 312,
    co2e: -139,
    multiplier: 0,
    tips: null,
  },
  {
    title: {
      en_US: '1 - 3 times a week',
      fi_FI: '1 - 3 kertaa viikossa',
      sv_SE: '1-3 gånger i veckan',
    },
    index: 313,
    co2e: -118,
    multiplier: 2.6,
    tips: [68651, 68653, 68659, 68665, 68667, 68669, 68671, 68676, 68679],
  },
  {
    title: {
      en_US: '4 - 7 times a week',
      fi_FI: '4 - 7 kertaa viikossa',
      sv_SE: '4-7  gånger i veckan',
    },
    index: 314,
    co2e: -92,
    multiplier: 5.9,
    tips: [68651, 68653, 68659, 68665, 68667, 68669, 68671, 68676, 68679],
  },
  {
    title: {
      en_US: 'Several times a day',
      fi_FI: 'useita kertoja päivässä',
      sv_SE: 'flera gånger om dagen',
    },
    index: 315,
    co2e: 44,
    multiplier: 23.8,
    tips: [68651, 68653, 68659, 68665, 68667, 68669, 68671, 68676, 68679],
  },
];

const q21as = [
  {
    title: {
      en_US: 'None',
      fi_FI: 'en yhtään',
      sv_SE: 'inte alls',
    },
    index: 316,
    co2e: 0,
    multiplier: null,
    tips: null,
  },
  {
    title: {
      en_US: '1 portion every now and then',
      fi_FI: '1 annos silloin tällöin',
      sv_SE: '1 portion nu som då',
    },
    index: 317,
    co2e: 88,
    multiplier: null,
    tips: [68649],
  },
  {
    title: {
      en_US: 'Fewer than 3 portions a day',
      fi_FI: 'alle 3 annosta päivässä',
      sv_SE: 'mindre än 3 portioner om dagen',
    },
    index: 318,
    co2e: 263,
    multiplier: null,
    tips: [68649],
  },
  {
    title: {
      en_US: '3 - 5 portions a day',
      fi_FI: '3 - 5 annosta päivässä',
      sv_SE: '3-5 portioner om dagen',
    },
    index: 319,
    co2e: 460,
    multiplier: null,
    tips: [68649],
  },
  {
    title: {
      en_US: 'More than 5 portions a day',
      fi_FI: 'yli 5 annosta päivässä',
      sv_SE: 'fler än 5 portioner om dagen',
    },
    index: 320,
    co2e: 635,
    multiplier: null,
    tips: [68649],
  },
];

const q22as = [
  {
    title: {
      en_US: 'None',
      fi_FI: 'en yhtäkään',
      sv_SE: 'inga alls',
    },
    index: 321,
    co2e: 0,
    multiplier: null,
    tips: [68671],
  },
  {
    title: {
      en_US: '1 - 2 meals per week',
      fi_FI: '1 - 2 ateriaa viikossa',
      sv_SE: '1-2 måltider i veckan',
    },
    index: 322,
    co2e: 44,
    multiplier: null,
    tips: [68665, 68671, 68686, 68692, 68710],
  },
  {
    title: {
      en_US: '3 - 5 meals per week',
      fi_FI: '3 - 5 ateriaa viikossa',
      sv_SE: '3-5 måltider i veckan',
    },
    index: 323,
    co2e: 117,
    multiplier: null,
    tips: [68665, 68671, 68686, 68692, 68710],
  },
  {
    title: {
      en_US: 'More than 5 meals per week',
      fi_FI: 'yli 5 ateriaa viikossa',
      sv_SE: 'fler än 5 måltider i veckan',
    },
    index: 324,
    co2e: 175,
    multiplier: null,
    tips: [68665, 68671, 68686, 68692, 68710],
  },
];

const q23as = [
  {
    title: {
      en_US: 'Never',
      fi_FI: 'en koskaan',
      sv_SE: 'aldrig',
    },
    index: 325,
    co2e: 0,
    multiplier: null,
    tips: null,
  },
  {
    title: {
      en_US: 'Seldom',
      fi_FI: 'harvoin',
      sv_SE: 'sällan',
    },
    index: 326,
    co2e: 4,
    multiplier: null,
    tips: [68541, 68665],
  },
  {
    title: {
      en_US: 'Every week',
      fi_FI: 'viikoittain',
      sv_SE: 'varje vecka',
    },
    index: 327,
    co2e: 16,
    multiplier: null,
    tips: [68541, 68665, 68696],
  },
  {
    title: {
      en_US: 'Every day',
      fi_FI: 'päivittäin',
      sv_SE: 'varje dag',
    },
    index: 328,
    co2e: 57,
    multiplier: null,
    tips: [68541, 68665, 68696],
  },
];

/**
 * Question 17-23
 */

const cat3qs = [
  {
    index: 17,
    title: {
      en_US: 'How much do you eat compared with the other people at a meal?',
      fi_FI:
        'Syötkö mielestäsi paljon vai vähän verrattuna kanssasi aterioiviin?',
      sv_SE:
        'Tycker du att du äter mycket eller lite jämfört med de andra som äter tillsammans med dig?',
    },
    answers: q17as,
  },
  {
    index: 18,
    title: {
      en_US:
        'How often do you have beef, cold cuts or cheese as part of your meal?',
      fi_FI:
        'Kuinka usein yhteensä nautit aterioillasi nautaa, leikkeleitä tai juustoa?',
      sv_SE:
        'Hur ofta äter du nötkött, charkuterier eller ost under måltiderna?',
    },
    answers: q18as,
  },
  {
    index: 19,
    title: {
      en_US:
        'How often do you have pork, chicken, fish or eggs as part of your meal?',
      fi_FI:
        'Kuinka usein yhteensä nautit aterioillasi possua, kanaa, kalaa tai kananmunaa?',
      sv_SE:
        'Hur ofta äter du svinkött, kyckling, fisk eller ägg under måltiderna?',
    },
    answers: q19as,
  },
  {
    index: 20,
    title: {
      en_US:
        'How often do you have milk products (milk, sour milk, yoghurt, quark, cream, butter) as part of your meal?',
      fi_FI:
        'Kuinka usein nautit aterioillasi maitotuotteita (maito, piimä, jogurtti, rahka, kerma, voi)?',
      sv_SE:
        'Hur ofta äter du mjölkprodukter (mjölk, surmjölk, yoghurt, kvarg, grädde smör) under måltiderna?',
    },
    answers: q20as,
  },
  {
    index: 21,
    title: {
      en_US:
        'How many portions (cup/mug/pint/glass) of coffee, tea, juice, beer or wine do you drink every day?',
      fi_FI:
        'Kuinka monta annosta (kuppi/muki/tuoppi/lasi) nautit kahvia, teetä, tuoremehua, olutta tai viiniä päivässä?',
      sv_SE:
        'Hur många portioner (koppar/muggar/stop/glas) dricker du kaffe, te, juice, öl eller vin om dagen?',
    },
    answers: q21as,
  },
  {
    index: 22,
    title: {
      en_US: 'How many meals per week do you eat out or in a canteen at work?',
      fi_FI:
        'Kuinka monta ateriaa viikossa syöt kodin ulkopuolella tai työpaikkaruokalassa?',
      sv_SE:
        'Hur många måltider i veckan äter du utanför hemmet eller i personalmatsalen?',
    },
    answers: q22as,
  },
  {
    index: 23,
    title: {
      en_US: 'How often do you throw food away?',
      fi_FI: 'Kuinka usein heität ruokaa roskiin?',
      sv_SE: 'Hur ofta kastar du bort mat?',
    },
    answers: q23as,
  },
];

export default cat3qs;
