// tslint:disable:max-line-length

import cat1qs from './category-1';
import cat2qs from './category-2';
import cat3qs from './category-3';
import cat4qs from './category-4';

const categories = [
  {
    index: 1,
    title: {
      en_US: 'Living',
      fi_FI: 'Asuminen',
      sv_SE: 'Boende',
    },
    endInfoPositive: {
      en_US: 'Your use of energy at home is at a moderate level.',
      fi_FI: 'Kotisi ja tapasi kuluttaa energiaa ovat kohtuullisella tasolla.',
      sv_SE: 'Ditt hem och dina vanor förbrukar energi och är på rimlig nivå.',
    },
    endInfoNegative: {
      en_US:
        'You’re quite the energy consumer. Is the energy you use produced in a sustainable way?',
      fi_FI:
        'Olet melkoinen energiankuluttaja. Onko käyttämäsi energia tuotettu kestävästi?',
      sv_SE:
        'Du använder rätt mycket energi. Har den energi du använder producerats på ett hållbart sätt?',
    },
    endInfoCommon: {
      en_US: 'Now, let’s have a look at how you get from one place to another!',
      fi_FI: 'Katsotaan seuraavaksi, mikä sinua liikuttaa!',
      sv_SE: 'Näst ska vi ta en titt på hur du rör på dig!',
    },
    averageCo2e: 2700,
    color: 'orange',
    icon: 'test-icon-asuminen.svg',
    questions: cat1qs,
    slug: {
      en_US: 'home-lover',
      fi_FI: 'pesanrakentaja',
      sv_SE: 'bobyggare',
    },
    tags: {
      en_US: ['living'],
      fi_FI: ['asuminen'],
      sv_SE: ['boende'],
    },
    resultInfo: {
      image: 'tulos-pesanrakentaja@3x.png',
      title: {
        en_US: 'YOU ARE AN ECONOMICAL HOME-LOVER',
        fi_FI: 'OLET SÄÄSTELIÄS PESÄN&shy;RAKENTAJA',
        sv_SE: 'DU ÄR EN SPARSAM BOBYGGARE',
      },
      altTitle: {
        en_US: 'I am an economical home-lover!',
        fi_FI: 'Olen säästeliäs pesänrakentaja!',
        sv_SE: 'Jag är en sparsam bobyggare!',
      },
      positiveDescription: {
        en_US:
          'You are the heart and soul of your home and create balance around you. You know the impact of your lifestyle on the environment, although you may occasionally give in to temptations.',
        fi_FI:
          'Olet kodin henki tai hengetär, joka rakentaa ympärilleen tasapainoa. Tunnet elämäntapasi vaikutukset ympäristöön vaikka saatat joskus sortua houkutuksiin.',
        sv_SE:
          'Du är hemmets härskare som skapar balans runt omkring sig. Du känner till hur din livsstil inverkar på miljön även om du ibland kan falla för frestelsen.',
      },
      negativeDescription: {
        en_US:
          'Your home is your castle and you understand how your home consumes energy. A few easy tips may save you up to hundreds of euros per year.',
        fi_FI:
          'Kotisi on linnasi ja ymmärrät miten kotisi kuluttaa energiaa. Muutamalla helpolla konstilla voit säästää jo satoja euroja vuodessa.',
        sv_SE:
          'Ditt hem är din borg och du förstår hur det förbrukar energi. Med några lätta tricks kan du spara hundratals euro i året.',
      },
    },
  },
  {
    index: 2,
    title: {
      en_US: 'Transport and tourism',
      fi_FI: 'Liikenne ja matkailu',
      sv_SE: 'Trafik och resor',
    },
    endInfoPositive: {
      en_US: 'You use low-carbon transport and are hopefully getting fitter, too!',
      fi_FI: 'Liikut vähäpäästöisesti ja kuntosikin toivottavasti nousee!',
      sv_SE:
        'Du använder transportsätt med låga utsläpp och din kondition blir kanske bättre!',
    },
    endInfoNegative: {
      en_US:
        'You seem to accumulate additional kilometres easily! Have you thought about reducing the emissions or compensating for them through offset payments?',
      fi_FI:
        'Sinulle taitaa kertyä helposti lisäkilometrejä? Oletko miettinyt päästöjen vähentämistä tai hyvittämistä päästömaksuilla?',
      sv_SE:
        'Du samlar lätt på dig extrakilometrar? Har du tänkt på att minska utsläppen eller gottgöra dem med utsläppsavgifter?',
    },
    endInfoCommon: {
      en_US: 'Let’s have a look how you eat!',
      fi_FI: 'Vilkaistaan, millainen ruokailija olet!',
      sv_SE: 'Så tar vi en titt på hur du äter!',
    },
    averageCo2e: 2000,
    color: 'blue',
    icon: 'test-icon-liikenne-ja-matkailu.svg',
    questions: cat2qs,
    slug: {
      en_US: 'eco-traveler',
      fi_FI: 'kanssamatkustaja',
      sv_SE: 'medresenär',
    },
    tags: {
      en_US: ['transport', 'tourism'],
      fi_FI: ['matkailu', 'liikkuminen'],
      sv_SE: ['trafik', 'resor'],
    },
    resultInfo: {
      image: 'tulos-kanssamatkustaja@3x.png',
      title: {
        en_US: 'YOU ARE A RESPONSIBLE ECO-TRAVELER',
        fi_FI: 'OLET VASTUULLINEN KANSSA&shy;MATKUSTAJA',
        sv_SE: 'DU ÄR EN ANSVARSKÄNNANDE MEDRESENÄR',
      },
      altTitle: {
        en_US: 'I am a responsible eco-traveler!',
        fi_FI: 'Olen vastuullinen kanssamatkustaja!',
        sv_SE: 'Jag är en ansvarskännande medresenär!',
      },
      positiveDescription: {
        en_US:
          'You are a real explorer! You know the impacts of travelling and tourism on the environment. Combining trips and sharing transport comes naturally to you.',
        fi_FI:
          'Olet oikea löytöretkeilijä! Tunnet liikkumisen ja matkailun vaikutukset ympäristöön. Matkojen yhdistäminen ja kulkuneuvon jakaminen ovat sinulle luontevia.',
        sv_SE:
          'Du är en verklig upptäcktsresande! Du känner till hur din mobilitet och dina resor påverkar miljön. Att kombinera resor och att dela fordon är naturligt för dig.',
      },
      negativeDescription: {
        en_US:
          'When you travel, you could take advantage of different modes of transport and save money and time. Can you think of ways to reduce sedentary time?',
        fi_FI:
          'Liikkumisessa voisit hyödyntää eri liikennevälineitä ja säästää aikaa ja rahaa. Keksitkö keinoja miten voisit vähentää istumista?',
        sv_SE:
          'I mobiliteten kunde du utnyttja olika transportmedel och spara både tid och pengar. Kan du komma på sätt att minska på sittandet?',
      },
    },
  },
  {
    index: 3,
    title: {
      en_US: 'Food',
      fi_FI: 'Ruoka',
      sv_SE: 'Mat',
    },
    endInfoPositive: {
      en_US: 'Your eating habits look fairly good!',
      fi_FI: 'Ruokailutottumuksesi ovat melko hyvällä mallilla!',
      sv_SE: 'Dina matvanor är rätt bra!',
    },
    endInfoNegative: {
      en_US:
        'Hello there, you! You are a real hedonist and eat whatever you feel like.',
      fi_FI: 'Hei sinä siellä! Olet oikea nautiskelija ja syöt miten huvittaa.',
      sv_SE: 'Hej, du där! Du är en riktig gourmand och du äter hur du vill.',
    },
    endInfoCommon: {
      en_US:
        'To save money or to go shopping, that’s one final thing to have a look at!',
      fi_FI: 'Säästää vai shoppailla, selvitetään se vielä!',
      sv_SE: 'Spara eller shoppa, låt oss ta reda på det!',
    },
    averageCo2e: 2100,
    color: 'green',
    icon: 'test-icon-ruoka.svg',
    questions: cat3qs,
    slug: {
      en_US: 'gourmet-lover',
      fi_FI: 'herkuttelija',
      sv_SE: 'gourmand',
    },
    tags: {
      en_US: ['food'],
      fi_FI: ['ruoka'],
      sv_SE: ['mat'],
    },
    resultInfo: {
      image: 'tulos-herkuttelija@3x.png',
      title: {
        en_US: 'YOU ARE A GREEN GOURMET LOVER',
        fi_FI: 'OLET VIHREÄ HERKUTTELIJA',
        sv_SE: 'DU ÄR EN GRÖN GOURMAND',
      },
      altTitle: {
        en_US: 'I am a green gourmet lover!',
        fi_FI: 'Olen vihreä herkuttelija!',
        sv_SE: 'Jag är en grön gourmand',
      },
      positiveDescription: {
        en_US:
          'You know that the way to the heart and healthier veins is through the stomach. You know the impact of your lifestyle on the environment.',
        fi_FI:
          'Tiedät, että tie sydämeen ja terveempiin verisuoniin käy vatsan kautta. Tunnet elämäntapasi vaikutukset ympäristöön.',
        sv_SE:
          'Du vet att vägen till hjärtat och friskare blodkärl går via magen. Du känner till hur din livsstil påverkar miljön.',
      },
      negativeDescription: {
        en_US:
          'You may rely too much on nutrition derived from animals. You could add more vegetables to your plate, even just for the taste.',
        fi_FI:
          'Saatat luottaa liikaa eläinkunnasta peräisin olevaan ravintoon. Voisit lisätä kasviksia lautaselle jo maun vuoksi.',
        sv_SE:
          'Du kan förlita dig lite väl mycket på föda från djurriket och du kunde lägga till grönsaker på tallriken för smakens skull.',
      },
    },
  },
  {
    index: 4,
    title: {
      en_US: 'Things and purchases',
      fi_FI: 'Tavarat ja hankinnat',
      sv_SE: 'Varor och inköp',
    },
    endInfoPositive: {
      en_US:
        'You manage with less than people do on average. Many people could follow your example!',
      fi_FI:
        'Pärjäät keskivertoa vähemmällä. Moni voisi ottaa sinusta esimerkkiä!',
      sv_SE:
        'Du klarar dig med mindre än de flesta. Du kunde vara ett föredöme för många!',
    },
    endInfoNegative: {
      en_US:
        'You are doing well, good for you! But have you thought about the amount of natural resources you consume?',
      fi_FI:
        'Sinulla menee hyvin, mutta menköön! Entä oletko miettinyt, kuinka paljon kulutat luonnonvaroja?',
      sv_SE:
        'Det går bra för dig och låt gå! Men har du funderat över hur mycket naturresurser du förbrukar?',
    },
    endInfoCommon: {
      en_US: '',
      fi_FI: '',
      sv_SE: '',
    },
    averageCo2e: 1200,
    color: 'yellow',
    icon: 'test-icon-tuotteet-ja-palvelut.svg',
    questions: cat4qs,
    slug: {
      en_US: 'indulger',
      fi_FI: 'hemmottelija',
      sv_SE: 'njutare',
    },
    tags: {
      en_US: ['services'],
      fi_FI: ['palvelut'],
      sv_SE: ['tjänster'],
    },
    resultInfo: {
      image: 'tulos-hemmottelija@3x.png',
      title: {
        en_US: 'YOU ARE A QUALITY-CONSCIOUS INDULGER',
        fi_FI: 'OLET LAATU&shy;TIETOINEN HEMMOTTELIJA',
        sv_SE: 'DU ÄR EN KVALITETS&shy;MEDVETEN NJUTARE',
      },
      altTitle: {
        en_US: 'I am a quality-conscious indulger!',
        fi_FI: 'Olen laatutietoinen hemmottelija!',
        sv_SE: 'Jag är en kvalitetsmedveten njutare!',
      },
      positiveDescription: {
        en_US:
          'You understand the impact your choices have on your lifestyle and the environment.',
        fi_FI: 'Ymmärrät valintojesi vaikutuksen elämäntapaasi ja ympäristölle.',
        sv_SE: 'Du förstår hur dina val inverkar på din livsstil och miljön.',
      },
      negativeDescription: {
        en_US:
          'Can you bear a crack in the idyll? Join the other Finns who recycle, repair and rent.',
        fi_FI:
          'Kestätkö särön idyllissä? Liity muiden suomalaisten joukkoon: kierrätä, korjaa ja vuokraa.',
        sv_SE:
          'Tål du en spricka i idyllen? Anslut dig till de andra finländarna: återvinn, reparera och hyr.',
      },
    },
  },
];

export default categories;
