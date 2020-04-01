import Average from '../models/average';
import BaseController from './base';
import * as async from 'async';

export default class AverageController extends BaseController {
  model = Average;

  get = (req, res) => {
    this.model
      .findOne()
      .then(average => {
        res.send(average);
      })
      .catch(err => res.sendStatus(400));
  };

  post = (req, res) => {
    this.model
      .findOne()
      .then(average => {
        let totalCo2e = 0;

        const newResults = req.body.categoryCO2e.map(cat => {
          return {
            index: cat.category.index,
            co2e: cat.co2e,
          };
        });

        const newCategories = (_average, _newResults) => {
          const categories = [];
          _average.categories.map(category => {
            _newResults.map(_result => {
              if (_result.index === category.index) {
                category.count++;
                category.co2e += _result.co2e;
                totalCo2e += category.co2e;
                category.average = category.co2e / category.count;
                categories.push(category);
              }
            });
          });
          return categories;
        };

        const newAverageObject = {
          count: average.count + 1,
          categories: newCategories(average, newResults),
          average: totalCo2e / (average.count + 1),
        };

        Average.findOneAndUpdate({}, newAverageObject)
          .then(() => {
            res.sendStatus(200);
          })
          .catch(err => res.sendStatus(400));
      })
      .catch(err => res.sendStatus(400));
  };

  update = (req, res) => {
    let docs;
    async.waterfall(
      [
        cb => {
          this.model.findOne().then(doc => {
            docs = doc;
            cb();
          });
        },
        cb => {
          async.each(
            docs.categories,
            (cat, next) => {
              if (cat.name.fi_FI === 'Asuminen') {
                cat.name.sv_SE = 'Boende';
              } else if (cat.name.fi_FI === 'Liikenne ja matkailu') {
                cat.name.sv_SE = 'Trafik och resor';
              } else if (cat.name.fi_FI === 'Ruoka') {
                cat.name.sv_SE = 'Mat';
              } else if (cat.name.fi_FI === 'Tavarat ja hankinnat') {
                cat.name.sv_SE = 'Varor och inköp';
              }
              next();
            },
            error => {
              if (error) {
                console.error(error);
              } else {
                cb();
              }
            }
          );
        },
        cb => {
          this.model.remove().then(() => cb());
        },
      ],
      error => {
        if (error) {
          console.error(error);
        } else {
          this.model.remove({}).then(() => {
            const newAverage = new Average({
              categories: docs.categories,
              average: docs.average,
              count: docs.count,
            });
            newAverage
              .save()
              .then(savedAverage => {
                res.sendStatus(200);
              })
              .catch(err => res.sendStatus(400));
          });
        }
      }
    );
  };
}
