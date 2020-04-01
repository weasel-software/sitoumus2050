# Sitra Elamantapatesti

[Production app](https://elamantapatesti.sitra.fi/)
[Staging app](https://sitra-elamantapatesti.herokuapp.com/)

## Development server
Make your own version of .env file. You can copy .env.example. Setup mongodb, run: `npm run synch:db`

Run `npm run dev` for a dev server. The app will automatically open in browser, if not navigate to `http://localhost:5000/`. The app will automatically reload if you change any of the source files.

To run only the REST API run `npm run dev:server`.

There are more specific commands to run the server, which can be found in `package.json`.

## Test and production build, include to liferay
Currently the complex manual process is:

- Change all references in code from /assets/images/ to /o/sitoumus-2050-theme/eltest/assets/images/ (this is the real location for images when in liferay...)
- When creating test or prod build, the .env file content must be must be changed accordingly to target environment.
- Execute following scripts
    - first: npm run build
    - after build create client with right configuration: npm run build:client-test or npm run build:client-prod
- Copy /dist/client contents to themes/sitoumus-2050-theme/src/eltest --> make new Liferay docker image and deploy to it in aws
- Compress /dist/server.js package.json and package-lock.json in same zip and deploy it to aws beanstalk, depending on .env settings:  Sitoumus2050-eltest-test or Sitoumus2050-eltest-prod 
- it also might be necessary to update mongodb. Check .env settings that you have right mongodb target, then execute npm run sync:db

The test can be added to Liferay by adding Elämäntapatesti article to page, See articleid:153002 which have structure/template: elamantapatesti.json, elamatapatesti.ftl. Update article template if inclusion code needs changes.




## Database setup

You'll need a working database to run the app.
- Make sure you have a MongoDB process running.
    - If you don't have MongoDB, [install](https://www.mongodb.com/).
- Copy .env.example -> .env and fill in database url.
- Run `npm run sync` to initialize/sync database.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive | pipe | service | class | module`.

## Build

Run `npm run build` to build the project. The build artifacts will be stored in the `dist/` directory.

There are more specific commands to build the app, which can be found in `package.json`.

## Deployment

Every commit to the master branch gets automatically deployed to the staging app in Heroku.
Production deployment is made with `Promote to production` button in Heroku pipeline.

## Project structure

```
.
├── e2e
├── dist                # Compiled files
│   ├── public
│   └── server
├── src                 # Source files
│   ├── client          
│   |   ├── app         # Angular app files
│   |   ├── assets      # Static assets
│   |   ├── styles      # Global styles
│   |   └── ...
│   └── server         
│       ├── controllers # Server app logic
│       ├── helpers     # Helper tools
│       ├── models      # Mongoose models
│       └── ...
├── .angular-cli.json   # Angular-CLI configurations
├── proxy.conf.json     # Proxy rules for ng serve
└── ...
```

## Running unit tests

> Tests not implemented

Run `npm run test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

> Tests not implemented

Run `npm run e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).
Before running the tests make sure you are serving the app via `ng serve`.

## Monthly reports

Run `npm run reports` and gather all the data from `results` folder.

## Further help

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 1.0.1.

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).
