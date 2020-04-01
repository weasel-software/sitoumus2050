/**
 * Answers for questions 24-27
 */

const q24as = [
  {
    title: {
      en_US: 'I don’t like shopping. I only buy what I need.',
      fi_FI: 'En pidä shoppailusta. Ostan vain tarpeeseen.',
      sv_SE: 'Jag gillar inte att shoppa. Jag köper bara det jag behöver.',
    },
    index: 401,
    co2e: 527,
    multiplier: null,
    tips: [68698, 68702, 68710, 68553],
  },
  {
    title: {
      en_US:
        'Oh, I love flea markets! You can find all kinds of nice things there!',
      fi_FI: 'Voih, rakastan kirpputoreja! Niiltä löytyy vaikka mitä ihanaa!',
      sv_SE: 'Oh, jag älskar lopptorg! Där hittar jag allt möjligt härligt!',
    },
    index: 402,
    co2e: 527,
    multiplier: null,
    tips: [68700, 68702, 68553],
  },
  {
    title: {
      en_US: 'I make impulse buys every now and then.',
      fi_FI: 'Teen heräteostoksia aina silloin tällöin.',
      sv_SE: 'Jag gör impulsköp nu som då.',
    },
    index: 403,
    co2e: 1054,
    multiplier: null,
    tips: [68698, 68700, 68702, 68710, 68752, 68553],
  },
  {
    title: {
      en_US: 'I love shopping! (Although all my cupboards are already full…)',
      fi_FI: 'Rakastan shoppailua! (Vaikka kaappini ovatkin jo aivan täynnä…)',
      sv_SE: 'Jag älskar att shoppa! (även om mina skåp redan är proppfulla)',
    },
    index: 404,
    co2e: 1581,
    multiplier: null,
    tips: [68698, 68700, 68702, 68710, 68752, 68553],
  },
];
const q25as = [
  {
    title: {
      en_US: 'No',
      fi_FI: 'Ei',
      sv_SE: 'Nej',
    },
    index: 405,
    co2e: 0,
    multiplier: [0, 0],
    tips: null,
  },
  {
    title: {
      en_US: 'I use it in summer',
      fi_FI: 'Kesäkaudella',
      sv_SE: 'Under sommaren',
    },
    index: 406,
    co2e: null,
    multiplier: [421.5, 1150.2],
    tips: [68560, 68637],
  },
  {
    title: {
      en_US: 'I use it throughout the year',
      fi_FI: 'Ympärivuotisessa käytössä',
      sv_SE: 'Året om',
    },
    index: 407,
    co2e: null,
    multiplier: [2248, 2459.7],
    tips: [68637],
  },
];

const q26as = [
  {
    title: {
      en_US: '1 - 4 people',
      fi_FI: '1 - 4 henkilöä',
      sv_SE: '1-4 personer',
    },
    index: 408,
    co2e: null,
    multiplier: 2,
    tips: [68637],
  },
  {
    title: {
      en_US: '5 - 10 people',
      fi_FI: '5 - 10 henkilöä',
      sv_SE: '5-10 personer',
    },
    index: 409,
    co2e: null,
    multiplier: 7,
    tips: null,
  },
  {
    title: {
      en_US: 'More than 10 people',
      fi_FI: 'yli 10 henkilöä',
      sv_SE: 'fler än 10 personer',
    },
    index: 410,
    co2e: null,
    multiplier: 13,
    tips: null,
  },
];

const q27as = [
  {
    title: {
      en_US: 'I don’t have a pet',
      fi_FI: 'en omista lemmikkiä',
      sv_SE: 'jag äger inget husdjur',
    },
    index: 411,
    co2e: 0,
    multiplier: null,
    tips: null,
  },
  {
    title: {
      en_US: '50 euros',
      fi_FI: '50 euroa',
      sv_SE: '50 euro',
    },
    index: 412,
    co2e: 223,
    multiplier: null,
    tips: null,
  },
  {
    title: {
      en_US: '100 euros',
      fi_FI: '100 euroa',
      sv_SE: '100 euro',
    },
    index: 413,
    co2e: 446,
    multiplier: null,
    tips: null,
  },
  {
    title: {
      en_US: '200 euros or more',
      fi_FI: '200 euroa tai enemmän',
      sv_SE: '200 euro eller mer',
    },
    index: 414,
    co2e: 892,
    multiplier: null,
    tips: null,
  },
];

/**
 * Questions 24-27
 */

const cat4qs = [
  {
    index: 24,
    title: {
      en_US: 'How would you describe your shopping habits?',
      fi_FI: 'Miten kuvailisit itseäsi shoppailijana?',
      sv_SE: 'Hur skulle du beskriva dig som shoppare?',
    },
    answers: q24as,
  },
  {
    index: 25,
    title: {
      en_US: 'Do you have a summer cottage?',
      fi_FI: 'Onko sinulla käytössäsi kesämökki?',
      sv_SE: 'Har du tillgång till sommarstuga?',
    },
    answers: q25as,
  },
  {
    index: 26,
    title: {
      en_US: 'How many people use the summer cottage regularly?',
      fi_FI: 'Kuinka monta henkilöä käyttää mökkiä säännöllisesti?',
      sv_SE: 'Hur många personer använder stugan regelbundet?',
    },
    answers: q26as,
  },
  {
    index: 27,
    title: {
      en_US: 'How much money do you spend on pets every month?',
      fi_FI: 'Kuinka paljon kulutat rahaa lemmikeihin kuukaudessa?',
      sv_SE: 'Hur mycket pengar går åt till husdjuren varje månad?',
    },
    answers: q27as,
  },
];

export default cat4qs;
