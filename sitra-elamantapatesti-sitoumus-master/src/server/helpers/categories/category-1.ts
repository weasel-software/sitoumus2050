/**
 * Answers for questions 1-9
 */

const q1as = [
  {
    title: {
      en_US: '1',
      fi_FI: '1',
      sv_SE: '1',
    },
    index: 101,
    co2e: null,
    multiplier: 1,
    tips: [68772],
  },
  {
    title: {
      en_US: '2',
      fi_FI: '2',
      sv_SE: '2',
    },
    index: 102,
    co2e: null,
    multiplier: 2,
    tips: [68772],
  },
  {
    title: {
      en_US: '3',
      fi_FI: '3',
      sv_SE: '3',
    },
    index: 103,
    co2e: null,
    multiplier: 3,
    tips: [68772],
  },
  {
    title: {
      en_US: '4',
      fi_FI: '4',
      sv_SE: '4',
    },
    index: 104,
    co2e: null,
    multiplier: 4,
    tips: [68772],
  },
  {
    title: {
      en_US: '5',
      fi_FI: '5',
      sv_SE: '5',
    },
    index: 105,
    co2e: null,
    multiplier: 5,
    tips: [68772],
  },
  {
    title: {
      en_US: 'More than 5',
      fi_FI: 'yli 5',
      sv_SE: 'fler än 5',
    },
    index: 106,
    co2e: null,
    multiplier: 6,
    tips: [68772],
  },
];

const q2as = [
  {
    title: {
      en_US: 'Less than 20 m2',
      fi_FI: 'alle 20 m2',
      sv_SE: 'under 20 m2',
    },
    index: 107,
    co2e: null,
    multiplier: [null, 16],
    tips: null,
  },
  {
    title: {
      en_US: '20 - 50 m2',
      fi_FI: '20 - 50 m2',
      sv_SE: '20 - 50 m2',
    },
    index: 108,
    co2e: null,
    multiplier: [null, 43],
    tips: null,
  },
  {
    title: {
      en_US: '51 - 80 m2',
      fi_FI: '51 - 80 m2',
      sv_SE: '51 - 80 m2',
    },
    index: 109,
    co2e: null,
    multiplier: [null, 67],
    tips: null,
  },
  {
    title: {
      en_US: '81 -120 m2',
      fi_FI: '81 -120 m2',
      sv_SE: '81 -120 m2',
    },
    index: 110,
    co2e: null,
    multiplier: [null, 100],
    tips: null,
  },
  {
    title: {
      en_US: '121 - 200 m2',
      fi_FI: '121 - 200 m2',
      sv_SE: '121 - 200 m2',
    },
    index: 111,
    co2e: null,
    multiplier: [null, 168],
    tips: null,
  },
  {
    title: {
      en_US: 'More than 200 m2',
      fi_FI: 'yli 200 m2',
      sv_SE: 'mer än 200 m2',
    },
    index: 112,
    co2e: null,
    multiplier: [null, 340],
    tips: null,
  },
];

const q3as = [
  {
    title: {
      en_US: 'Ecological electricity',
      fi_FI: 'Ekosähköä',
      sv_SE: 'Miljömärkt el',
    },
    index: 113,
    co2e: null,
    multiplier: [0.007, null],
    tips: [
      68417,
      68445,
      68788,
      67963,
      68414,
      68544,
      68564,
      68585,
      68587,
      68589,
      68591,
      68694,
    ],
  },
  {
    title: {
      en_US: 'Ordinary electricity',
      fi_FI: 'Tavallista sähköä',
      sv_SE: 'Vanlig el',
    },
    index: 114,
    co2e: null,
    multiplier: [0.281, null],
    tips: [
      68417,
      68445,
      68460,
      68788,
      67963,
      68414,
      68544,
      68564,
      68585,
      68587,
      68589,
      68591,
      68694,
    ],
  },
];

const q4as = [
  {
    title: {
      en_US: 'Block of flats',
      fi_FI: 'Kerrostalo',
      sv_SE: 'Höghus',
    },
    index: 115,
    co2e: null,
    multiplier: [8, 1400, 500, 42.72],
    tips: [67904, 68452, 68514, 68587, 68788, 67857],
  },
  {
    title: {
      en_US: 'Single-family house or semi-detached house',
      fi_FI: 'Omakoti- tai paritalo',
      sv_SE: 'Egnahems- eller parhus',
    },
    index: 116,
    co2e: null,
    multiplier: [6.9, 4600, 900, 66.84],
    tips: [
      67904,
      67968,
      67973,
      67978,
      68514,
      68516,
      68528,
      68557,
      68587,
      68788,
      67857,
    ],
  },
  {
    title: {
      en_US: 'Terraced house',
      fi_FI: 'Rivitalo',
      sv_SE: 'Radhus',
    },
    index: 117,
    co2e: null,
    multiplier: [6.9, 2600, 700, 22.2872],
    tips: [
      67904,
      67968,
      67973,
      67978,
      68514,
      68516,
      68528,
      68557,
      68587,
      68788,
      67857,
    ],
  },
];

const q5as = [
  {
    title: {
      en_US: 'Before 1990',
      fi_FI: 'ennen 1990',
      sv_SE: 'innan 1990',
    },
    index: 118,
    co2e: null,
    multiplier: [240, null],
    tips: null,
  },
  {
    title: {
      en_US: '1990 - 2010',
      fi_FI: '1990 - 2010',
      sv_SE: '1990 - 2010',
    },
    index: 119,
    co2e: null,
    multiplier: [160, null],
    tips: null,
  },
  {
    title: {
      en_US: 'After 2010',
      fi_FI: '2010 jälkeen',
      sv_SE: 'efter 2010',
    },
    index: 120,
    co2e: null,
    multiplier: [130, null],
    tips: null,
  },
];

// TODO: co2e, multiplier and tips missing
const q6as = [
  {
    title: {
      en_US: 'Regular District Heating',
      fi_FI: 'Tavallinen kaukolämpö',
      sv_SE: 'Vanlig fjärrvärme',
    },
    index: 121,
    co2e: '',
    multiplier: [0.147, null],
    tips: [67973, 67978, 68528, 68546, 68411],
  },
  {
    title: {
      en_US: 'Green Distrtict Heating / Wood or pellets',
      fi_FI: 'Vihreä kaukolämpö / Puu tai pelletti',
      sv_SE: 'Grön fjärrvärme /Trä eller pellet',
    },
    index: 122,
    co2e: '',
    multiplier: [0.014, null],
    tips: [67973, 67978, 68528, 68546, 68411],
  },
  {
    title: {
      en_US: 'Light fuel oil',
      fi_FI: 'Kevyt polttoöljy',
      sv_SE: 'Lätt brännolja',
    },
    index: 123,
    co2e: '',
    multiplier: [0.265, null],
    tips: [67973, 67978, 68528, 68546, 68411],
  },
  {
    title: {
      en_US: 'Electricity',
      fi_FI: 'Sähkö',
      sv_SE: 'El',
    },
    index: 124,
    co2e: '',
    multiplier: [null, null],
    tips: [67973, 67978, 68414, 68445, 68516, 68528, 68546, 68411],
  },
  {
    title: {
      en_US: 'Natural gas',
      fi_FI: 'Maakaasu',
      sv_SE: 'Naturgas',
    },
    index: 125,
    co2e: '',
    multiplier: [0.199, null],
    tips: [67973, 67978, 68528, 68546, 68411],
  },
  {
    title: {
      en_US: 'Ground-source heat pump or air-source heat pump',
      fi_FI: 'Maalämpö tai Ilmalämpöpumppu',
      sv_SE: 'Jordvärme eller luftvärmepump',
    },
    index: 127,
    co2e: '',
    multiplier: [null, null],
    tips: [68528, 68546, 68411],
  },
  {
    title: {
      // tslint:disable-next-line:quotemark
      en_US: "I don't know",
      fi_FI: 'En tiedä',
      sv_SE: 'Vet inte',
    },
    index: 128,
    co2e: '',
    multiplier: [null, null],
    tips: [67973, 67978, 68528, 68546, 68411],
  },
];

const q7as = [
  {
    title: {
      en_US: 'Southern Finland',
      fi_FI: 'Etelä-Suomessa',
      sv_SE: 'Södra Finland',
    },
    index: 129,
    co2e: 0,
    multiplier: null,
    tips: null,
  },
  {
    title: {
      en_US: 'Central Finland',
      fi_FI: 'Maan keskiosassa',
      sv_SE: 'I landets mellersta delar',
    },
    index: 130,
    co2e: null,
    multiplier: null,
    tips: null,
  },
  {
    title: {
      en_US: 'Northern Finland',
      fi_FI: 'Pohjois-Suomessa',
      sv_SE: 'Norra Finland',
    },
    index: 131,
    co2e: null,
    multiplier: null,
    tips: null,
  },
];

const q8as = [
  {
    title: {
      en_US: 'Cool, about 19°C',
      fi_FI: 'Viileä, noin 19°C',
      sv_SE: 'Svalt, cirka 19°C',
    },
    index: 132,
    co2e: null,
    multiplier: null,
    tips: [67904, 68411],
  },
  {
    title: {
      en_US: 'Moderate, about 21°C',
      fi_FI: 'Kohtalainen, noin 21°C',
      sv_SE: 'Måttligt varmt, cirka 21°C',
    },
    index: 133,
    co2e: 0,
    multiplier: null,
    tips: [67857, 67904, 68411, 68546],
  },
  {
    title: {
      en_US: 'Warm, about 23°C',
      fi_FI: 'Lämmin, noin 23°C',
      sv_SE: 'Varmt, cirka 23°C',
    },
    index: 134,
    co2e: null,
    multiplier: null,
    tips: [67857, 67904, 68411, 68546],
  },
];

const q9as = [
  {
    title: {
      en_US: 'About 30 minutes ',
      fi_FI: 'noin 30 minuuttia ',
      sv_SE: 'cirka 30 minuter',
    },
    index: 135,
    co2e: -178,
    multiplier: null,
    tips: [67954],
  },
  {
    title: {
      en_US: 'About 60 minutes',
      fi_FI: 'noin 60 minuuttia',
      sv_SE: 'cirka 60 minuter',
    },
    index: 136,
    co2e: 0,
    multiplier: null,
    tips: [67948, 67954],
  },
  {
    title: {
      en_US: 'About 120 minutes',
      fi_FI: 'noin 120 minuuttia',
      sv_SE: 'cirka 120 minuter',
    },
    index: 137,
    co2e: 336,
    multiplier: null,
    tips: [67948, 67954],
  },
];

/**
 * Questions 1-9
 */

const cat1qs = [
  {
    index: 1,
    title: {
      en_US: 'How many people live in your household?',
      fi_FI: 'Kuinka monta henkilöä kotitaloudessasi asuu?',
      sv_SE: 'Hur många personer bor i ditt hushåll?',
    },
    answers: q1as,
  },
  {
    index: 2,
    title: {
      en_US: 'What is the living area of your home?',
      fi_FI: 'Kuinka iso on asuntosi asuinpinta-ala?',
      sv_SE: 'Hur stor är bostadsytan i din bostad?',
    },
    answers: q2as,
  },
  {
    index: 3,
    title: {
      en_US: 'What kind of electricity do you use?',
      fi_FI: 'Mitä sähköä käytät?',
      sv_SE: 'Vilken el använder du?',
    },
    answers: q3as,
  },
  {
    index: 4,
    title: {
      en_US: 'What kind of house do you live in?',
      fi_FI: 'Minkälaisessa talossa asut?',
      sv_SE: 'I hurdant hus bor du?',
    },
    answers: q4as,
  },
  {
    index: 5,
    title: {
      en_US: 'When was the house built?',
      fi_FI: 'Milloin talo on rakennettu?',
      sv_SE: 'När är huset byggt?',
    },
    answers: q5as,
  },
  {
    index: 6,
    title: {
      en_US: 'What is the primary heating method of your home?',
      fi_FI: 'Mikä on kotisi ensisijainen lämmitysmuoto?',
      sv_SE: 'Med vad värmer du i första hand ditt hem med?',
    },
    answers: q6as,
  },
  {
    index: 7,
    title: {
      en_US: 'Where in Finland do you live?',
      fi_FI: 'Missä päin Suomea asut?',
      sv_SE: 'Var i Finland bor du?',
    },
    answers: q7as,
  },
  {
    index: 8,
    title: {
      en_US: 'What is the room temperature in your home in winter?',
      fi_FI: 'Mikä on asuntosi huonelämpötila talvikaudella?',
      sv_SE: 'Hur varmt är det i din bostad under vintern?',
    },
    answers: q8as,
  },
  {
    index: 9,
    title: {
      en_US: 'How much time per week do you spend having a shower?',
      fi_FI: 'Kuinka kauan olet suihkussa viikossa?',
      sv_SE: 'Hur länge duschar du i veckan?',
    },
    answers: q9as,
  },
];

export default cat1qs;
