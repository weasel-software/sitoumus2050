import * as dotenv from 'dotenv';
import * as express from 'express';
import * as expressCluster from 'express-cluster';
import { database } from './database';
import { router } from './router';
import { server } from './server';

dotenv.load({ path: '.env' });
const PORT = process.env.PORT || 3001;

expressCluster(
  worker => {
    const app = express();
    database();
    server(app);
    router(app);
    return app.listen(PORT);
  },
  { count: 2 }
);
