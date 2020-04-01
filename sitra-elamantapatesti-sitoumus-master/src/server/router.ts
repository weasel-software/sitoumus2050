import * as express from 'express';
import * as path from 'path';
import * as http from 'http';
import * as request from 'superagent';
import AnswerController from './controllers/answer';
import AverageController from './controllers/average';
import CategoryController from './controllers/category';
import ExtendedResultController from './controllers/extended-result';
import NavigationController from './controllers/navigation';
import QuestionController from './controllers/question';
import ResultController from './controllers/result';
import ResultCategoryController from './controllers/result-category';
import TipController from './controllers/tip';

export function router(app) {
  // tslint:disable-next-line:no-shadowed-variable
  const router = express.Router();

  const answerController = new AnswerController();
  const averageController = new AverageController();
  const categoryController = new CategoryController();
  const questionController = new QuestionController();
  const resultController = new ResultController();
  const extendedResultController = new ExtendedResultController();
  const resultCategoryController = new ResultCategoryController();
  const tipController = new TipController();
  const navigationController = new NavigationController();

  // answers
  router.route('/answers').get(answerController.getAll);
  router.route('/answersPopulated').get(answerController.getAllPopulated);
  router.route('/answer/:id').get(answerController.get);
  router.route('/answer').put(answerController.insert);
  router.route('/answer/:id').post(answerController.update);

  // averages
  router.route('/average').get(averageController.get);
  router.route('/average').post(averageController.post);
  router.route('/average/update').get(averageController.update);

  // categories
  router.route('/categories').get(categoryController.getAll);
  router.route('/categoriesPopulated').get(categoryController.getAllPopulated);
  router.route('/category/:id').get(categoryController.get);
  router.route('/category/name/:category').get(categoryController.getOneByName);
  router.route('/category').put(categoryController.insert);
  router.route('/category/:id').post(categoryController.update);

  // questions
  router.route('/questions').get(questionController.getAll);
  router.route('/questionsPopulated').get(questionController.getAllPopulated);
  router.route('/question/:id').get(questionController.get);
  router.route('/question').put(questionController.insert);
  router.route('/question/:id').post(questionController.update);

  // results
  router.route('/extendedResult').get(extendedResultController.create);
  //router.route('/extendedResults').get(extendedResultController.getAll);
  router
    .route('/extendedResultPopulated/:id')
    .get(extendedResultController.getPopulated);
  router.route('/extendedResult/:id').get(extendedResultController.get);
  router.route('/extendedResult').put(extendedResultController.insert);
  router.route('/extendedResult/:id').post(extendedResultController.update);
  router.route('/extendedResults/count').get(extendedResultController.count);

  // result categories
  router.route('/resultCategories').get(resultCategoryController.getSorted);

  // tips
  // router.route('/tips').get(tipController.getAll);
  router.route('/tip/:id').get(tipController.get);
  router.route('/tip').put(tipController.insert);
  router.route('/tip/:id').post(tipController.update);

  // navigation
  router.route('/navigation').get(navigationController.getAll);

  app.use('/api', router);

  app.get('/api/tips', (req, res) => {
    request.get(
      'https://sitoumus2050.fi/o/commitment2050-service/commitmentapi/templates/operations/100smartways?wspd',
      (err, response) => {
        if (err) {
          console.error(err);
          res.send(400);
        } else {
          res.send(response.body.templates);
        }
      }
    );
    // http.get(
    //   {
    //     host: 'sitoumus2050.fi',
    //     path:
    //       '/o/commitment2050-service/commitmentapi/templates/operations/100smartways?wspd',
    //   },
    //   response => {
    //     let body = '';
    //     response.on('data', function(d) {
    //       console.log('d', d);
    //       body += d;
    //     });
    //     response.on('end', function() {
    //       res.send(JSON.parse(body).templates);
    //     });
    //   }
    // );
  });

  // Health check
  app.get('/api/health', (req, res) => {
    res.status(200).json({
      health: 'OK',
    });
  });

  app.use(express.static(__dirname + '/client'));
  app.get('/*', (req, res) => {
    res.sendFile(path.join(__dirname + '/client/index.html'));
  });
}
