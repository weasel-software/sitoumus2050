# Sitoumus2050.fi

## License

This is the sitoumus2050.fi project by Valtioneuvoston kanslia, available under the MIT license, see [LICENSE.md](LICENSE.md).

The project includes a modified version of Sitra's Lifestyle Test. Read about its licenses at
https://www.sitra.fi/en/articles/sitras-100-smart-ways-lifestyle-test-licences-terms-use/
or visit the repository at GitHub at
https://github.com/sitrafund/lifestyletest

## Setup instructions for local dev env:

- Install (and update!) Liferay blade: https://dev.liferay.com/fi/develop/tutorials/-/knowledge_base/7-0/installing-blade-cli
- Install Java 1.8 and maven
- Install NodeJS 8 and 10 using nvm
- Install MySQL 5.6
- Create db, see mysql/sitoumus.sql 
    - Import database dump 
- Check package.json for npm scripts:
- Create server (server will be created under /bundles, by default local env settings will be used): `npm run initBundle`
  - With Windows, change catalina shutdown port "8005" to "8105" ./bundles/tomcat-8.0.32/config/server.xml
- Get document_library contents from team member, copy document_library directory to ./bundles/data
- Set JAVA_HOME to JDK root, if not set
- Start server: `npm run start`
    - console logging should start, stop server ctrl + c
    - For some reason freemarker does not always find servicelocalor, if this happens (usually after db update), restart server
    - Tomcat might need more memory, change ./bundles/tomcat/-8.0.32/bin/setenv.sh -Xmx parameter.
- (To stop the server: `npm run stop`)
- Build and deploy everything: `npm run deploy-all`
  - If the script puts the built .jar and .war files in ./docker/deploy, move them to ./bundles/deploy instead.
  - Other available scripts see package.json
  - If above doesn't work, do the following:
    - Create your own liferay-theme.json by copying ./themes/sitoumus-2050-theme/liferay-theme.template.json.
    - Build migration package: ./migration/ & mvn clean install
    - Build themes: ./themes/sitoumus-2050-theme/ & blade gw deploy
    - Build layouts: ./themes/sitoumus-layouts/ & blade gw deploy
    - Build service: ./modules/commitment2050-service/ & blade deploy
    - Build react portlets: ./modules/commitment-react-portlets & blade deploy
    - Build translations: ./modules/hook-language & blade deploy
- Set up react-ui

## Setting up react-ui

- Run `npm run install-ui`
- Start react dev-server `npm run start-ui`
- Liferay will load the javascript hosted by the dev-server when domain is localhost, but you might prefer to use the dev-server for development. It runs on port 3000.
- React components will render themselves into dom elements that have predefined values as their data-component attribute. Take a look at react/src/index.js to see what those values are.
- To use react components in Liferay, create portlet, which can be added to any page on Liferay. See /modules/commitment-react-portlets. You need to define a java class, and a jsp file.
- For test and production usage, react-ui should be copied to Liferay theme. See /themes/sitoumus-2050-theme/src/js/main.js which contain script that will load the react scripts depending on environment.

## Commitment analysis database
- Seee README.md in modules/commitment-analysis
- See also HTTP API endpoint http://localhost:8080/o/commitment2050-service/databasealterationsapi/recreateCAdb

## Create bundles with different configs: https://dev.liferay.com/fi/develop/tutorials/-/knowledge_base/7-0/development-lifecycle-for-a-liferay-workspace):

- To generate a Liferay Portal bundle with a specific environment configuration to the workspace’s /bundles folder, run './gradlew initBundle -Pliferay.workspace.environment=[ENVIRONMENT]'
- To generate a distributable Liferay Portal installation to the workspace’s /build folder, run './gradlew distBundle[Zip|Tar] -Pliferay.workspace.environment=[ENVIRONMENT]'

## theme development:

Liferay themes are based on boostrap 3, so check it out what components/styles it offers before creating your own:
https://getbootstrap.com/docs/3.3/

Follow Liferay instructions to set up theme generator and tools :
https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/themes-generator
https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/theme-gulp-tasks

If you see permission or access errors, such as EPERM or EACCESS, do not use sudo as a work-around.
See instructions:
https://github.com/sindresorhus/guides/blob/master/npm-global-without-sudo.md

### Before running gulp task, set up liferay-theme.json:

Create your own liferay-theme.json by copying liferay-theme.template.json.

### Gulp tasks:

- Build: gulp build
- Build & deploy: gulp build
- Watch & build & deploy (You probably want to use this almost always): gulp watch

## OSGI modules

Odd behaviour? Need to uninstall module? Use gogo shell: https://dev.liferay.com/fi/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell

- Connect to gogo shell: telnet localhost 11311
- List modules: lb
- uninstall module: uninstall <moduleid>
- Warning: Commands shutdown, close, and exit stop the OSGi framework. So make sure to use the disconnect command to end the telnet Gogo Shell session.

## Docker

It is also possible to run portal in docker. See docker/README.md for more information

## Users/test data
Normaali testikäyttäjä
Sit Test
sitoumus.test@soikea.com / test

Organisaatio:
test

Sitoumus:
Sittest testi sitoumus, liitetty organisaatioon test



## Debug (Intellij Idea)
modify file: bundles/tomcat-8.0.32/setenv.sh and add -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 to CATALINA_OPTS
- restart server

## DEBUG EMAIL

Non-root way to run debugging SMTP server. You need to change the SMTP server details in your application settings to localhost:1025:
python -m smtpd -n -c DebuggingServer localhost:1025

If you want to bind SMTP port 25 you need to run this as a root:
sudo python -m smtpd -n -c DebuggingServer localhost:25

        
              
        
     