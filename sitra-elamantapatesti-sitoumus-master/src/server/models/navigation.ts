import * as mongoose from 'mongoose';

const navigationSchema = new mongoose.Schema({
  html: { fi_FI: String, en_US: String, sv_SE: String },
});

const Navigation = mongoose.model('Navigation', navigationSchema);

export default Navigation;
