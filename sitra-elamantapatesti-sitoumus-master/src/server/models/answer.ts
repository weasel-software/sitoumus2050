import * as mongoose from 'mongoose';

const answerSchema = new mongoose.Schema({
  title: { en_US: String, fi_FI: String, sv_SE: String },
  index: Number,
  co2e: mongoose.Schema.Types.Mixed,
  multiplier: mongoose.Schema.Types.Mixed,
  tips: [{ type: mongoose.Schema.Types.ObjectId, ref: 'Tip' }],
});

const Answer = mongoose.model('Answer', answerSchema);

export default Answer;
