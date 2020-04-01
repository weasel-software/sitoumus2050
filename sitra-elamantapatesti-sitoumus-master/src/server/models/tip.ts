import * as mongoose from 'mongoose';

const tipSchema = new mongoose.Schema({
  wpId: Number,
  images: String,
  title: { en_US: String, fi_FI: String, sv_SE: String },
  tag: { en_US: String, fi_FI: String, sv_SE: String },
  url: { en_US: String, fi_FI: String, sv_SE: String },
  environmentEffect: { en_US: String, fi_FI: String, sv_SE: String },
});

const Tip = mongoose.model('Tip', tipSchema);

export default Tip;
