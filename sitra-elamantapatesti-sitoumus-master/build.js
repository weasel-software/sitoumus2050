const fs = require('fs-extra');
const concat = require('concat');
const chalk = require('chalk');
const join = require('path').join;
const packageName = require('./package.json').name;

console.log(chalk.yellow('Bundling the app into web component'));

const BUILD_PATH = 'dist/public';
const DIST_PATH = 'dist/client';
const files = [
  join(BUILD_PATH, 'polyfills.js'),
  join(BUILD_PATH, 'scripts.js'),
  join(BUILD_PATH, 'runtime.js'),
  join(BUILD_PATH, 'main.js'),
];

const html = `
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>${packageName}</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<base href="/">
<link rel="stylesheet" href="/${packageName}.css">
</head>
<body>
<${packageName}></${packageName}>
<script src="/${packageName}.js"></script>
</body>
</html>
`;

fs.ensureDirSync(DIST_PATH);

concat(files, join(DIST_PATH, `${packageName}.js`))
  .then(result => {

    const p1 = new Promise((resolve, reject) => {
      fs.copy(join(BUILD_PATH, 'styles.css'), join(DIST_PATH, `${packageName}.css`))
        .then(() => resolve())
        .catch(error => resolve(console.error("Couldn't locate", error.path, "so it wasn't included in the package.")))
    })

    const p2 = new Promise((resolve, reject) => {
      fs.copy(join(BUILD_PATH, 'assets'), join(DIST_PATH, 'assets'))
        .then(() => resolve())
        .catch(error => resolve(console.error("Couldn't locate", error.path, "so it wasn't included in the package.")))
    })

    const p3 = new Promise((resolve, reject) => {
      fs.copy(join(BUILD_PATH, 'styles', 'fonts'), join(DIST_PATH))
        .then(() => resolve())
        .catch(error => resolve(console.error("Couldn't locate", error.path, "so it wasn't included in the package.")))
    })

    const p4 = new Promise((resolve, reject) => {
      fs.writeFile(join(DIST_PATH, 'index.html'), html)
        .then(() => resolve())
        .catch(error => resolve(console.error("Couldn't locate", error.path, "so it wasn't included in the package.")))
    })


    Promise.all([p1, p2, p3, p4]).then(() => {
      // fs.removeSync(join(BUILD_PATH));
      console.log(chalk.green('Web component built'));
    })
  });