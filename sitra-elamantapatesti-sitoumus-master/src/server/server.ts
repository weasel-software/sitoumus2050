import * as bodyParser from 'body-parser';
import * as compression from 'compression';
import * as morgan from 'morgan';

export function server(app) {
  // use body-parser to parse request body as json
  app.use(bodyParser.json());
  app.use(bodyParser.urlencoded({ extended: false }));

  // use morgan server logging in dev mode
  app.use(morgan('dev'));
  app.use(compression());

  app.use(function(req, res, next) {
    res.header('Access-Control-Allow-Origin', '*');
    res.header(
      'Access-Control-Allow-Methods',
      'GET, PUT, POST, DELETE, OPTIONS'
    );
    res.header(
      'Access-Control-Allow-Headers',
      'Content-Type, Authorization, Content-Length, X-Requested-With'
    );

    if ('OPTIONS' === req.method) {
      res.send(200);
    } else {
      next();
    }
  });

  // redirect HTTP to HTTPS redirect in heroku
  // app.use(function(req, res, next) {
  //   if (process.env.NODE_ENV === 'production') {
  //     if (req.headers['x-forwarded-proto'] !== 'https') {
  //       res.redirect(302, 'https://' + req.hostname + req.originalUrl);
  //     } else {
  //       next();
  //     }
  //   } else {
  //     next();
  //   }
  // });
}
