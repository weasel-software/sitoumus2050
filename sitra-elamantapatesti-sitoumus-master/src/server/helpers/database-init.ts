/*
*
*   Run with ts-node
*   NOTE: script doesn't stop automatically
*
*/

import * as async from 'async';
import * as dotenv from 'dotenv';
import * as mongoose from 'mongoose';
import Answer from '../models/answer';
import Average from '../models/average';
import Category from '../models/category';
import Question from '../models/question';
import ResultCategory from '../models/result-category';
import Tip from '../models/tip';
import categories from './categories/categories';
import resultCategories from './categories/result-categories';
import { fetchTips } from './fetch-tips-from-wp';
const chalk = require('chalk');

dotenv.load({ path: '.env' });

mongoose.connect(
  process.env.MONGOHQ_URL
);

const saveTips = async function(cb) {
  console.log('Starting to fetch tips from sitra.fi');
  const tips = await fetchTips();
  const promises = tips.map(tip => {
    return new Promise((resolve, reject) => {
      Tip.findOneAndUpdate({ wpId: tip.wpId }, tip, {
        upsert: true,
        new: true,
      })
        .then(model => {
          console.log('Tip', tip.wpId, chalk.yellow('updated'));
          resolve(model);
        })
        .catch(error => {
          console.error(error, tip);
          reject();
        });
    });
  });
  Promise.all(promises).then(result => {
    console.log('Tips fetched and saved');
    cb(null, result);
  });
};

const saveResultCategories = async function(tips, callback) {
  const promises = resultCategories.map(category => {
    return new Promise((resolve, reject) => {
      ResultCategory.findOneAndUpdate({ index: category.index }, category, {
        upsert: true,
        new: true,
      })
        .then(model => {
          console.log(category.slug.en_US, chalk.yellow('updated'));
          resolve(model);
        })
        .catch(error => {
          console.error(error, category);
          reject();
        });
    });
  });
  Promise.all(promises).then(result => {
    callback(null, tips);
  });
};

const initBD = async function(tips, cb) {
  // Map of tips (wpId: Tip) for lookups when mapping tips in answers.
  const tipsObj = tips.reduce((result, tip) => {
    result[tip.wpId] = tip;
    return result;
  }, {});
  async.each(
    categories,
    (category, nextCategory) => {
      const _questions = [];

      async.each(
        category.questions,
        (question, nextQuestion) => {
          // temporary array to save answers saved to db
          const _answers = [];

          async.each(
            question.answers,
            (answer, nextAnswer) => {
              if (answer.tips) {
                answer.tips = answer.tips.map(tipWpId => tipsObj[tipWpId]);
              }

              Answer.findOneAndUpdate({ index: answer.index }, answer, {
                upsert: true,
                new: true,
              })
                .then(_answer => {
                  console.log(
                    'Answer',
                    answer.title.en_US.substring(0, 25),
                    '/',
                    answer.title.fi_FI.substring(0, 25),
                    chalk.yellow('updated!')
                  );
                  _answers.push(_answer);
                  nextAnswer(null);
                })
                .catch(error => nextAnswer(error));
            },
            err => {
              if (err) {
                nextQuestion(err);
              } else {
                question.answers = _answers;

                Question.findOneAndUpdate({ index: question.index }, question, {
                  upsert: true,
                  new: true,
                })
                  .then(_question => {
                    console.log(
                      'Question',
                      question.title.en_US.substring(0, 25),
                      '/',
                      question.title.fi_FI.substring(0, 25),
                      chalk.yellow('updated!')
                    );
                    _questions.push(_question);
                    nextQuestion(null);
                  })
                  .catch(error => nextQuestion(error));
              }
            }
          );
        },
        err => {
          if (err) {
            nextCategory(err);
          } else {
            category.questions = _questions;

            Category.update({ index: category.index }, category, {
              upsert: true,
            })
              .then(_category => {
                if (_category.upserted) {
                  console.log(
                    'Category',
                    category.title.en_US,
                    category.title.fi_FI,
                    chalk.green('created!')
                  );
                } else if (_category.nModified > 0) {
                  console.log(
                    'Category',
                    category.title.en_US,
                    category.title.fi_FI,
                    chalk.yellow('updated!')
                  );
                } else {
                  console.log(
                    'Category',
                    category.title.en_US,
                    category.title.fi_FI,
                    chalk.grey('already up-to-date!')
                  );
                }
                nextCategory(null);
              })
              .catch(error => nextCategory(error));
          }
        }
      );
    },
    err => {
      if (err) {
        cb(err);
      } else {
        cb(null);
      }
    }
  );
};

const saveAverage = function(cb) {
  const average = new Average({
    average: 7635,
    count: 3381,
    categories: [
      {
        name: {
          fi_FI: 'Asuminen',
          en_US: 'Living',
        },
        icon: 'test-icon-asuminen.svg',
        color: 'orange',
        co2e: 8839699,
        count: 3381,
        index: 1,
        average: 2614,
      },
      {
        name: {
          fi_FI: 'Liikenne ja matkailu',
          en_US: 'Transport and tourism',
        },
        icon: 'test-icon-liikenne-ja-matkailu.svg',
        color: 'blue',
        co2e: 9548970,
        count: 3381,
        index: 2,
        average: 2824,
      },
      {
        name: {
          fi_FI: 'Ruoka',
          en_US: 'Food',
        },
        icon: 'test-icon-ruoka.svg',
        color: 'green',
        co2e: 5030343,
        count: 3381,
        index: 3,
        average: 1487,
      },
      {
        name: {
          fi_FI: 'Tavarat ja hankinnat',
          en_US: 'Things and purchases',
        },
        icon: 'test-icon-tuotteet-ja-palvelut.svg',
        color: 'yellow',
        co2e: 2397228,
        count: 3381,
        index: 4,
        average: 709,
      },
    ],
  });
  Average.remove({}).then(() => {
    average.save().then(() => cb(null));
  });
};

async.waterfall([saveTips, saveResultCategories, initBD, saveAverage], err => {
  if (err) {
    console.error(err);
  } else {
    console.log('Database initialized');
    process.exit(0);
  }
});
