import * as mongoose from 'mongoose';

const resultCategorySchema = new mongoose.Schema({
  index: Number,
  title: { en_US: String, fi_FI: String },
  endInfoPositive: { en_US: String, fi_FI: String, sv_SE: String },
  endInfoNegative: { en_US: String, fi_FI: String, sv_SE: String },
  endInfoCommon: { en_US: String, fi_FI: String, sv_SE: String },
  averageCo2e: Number,
  color: String,
  icon: String,
  questions: [
    {
      type: mongoose.Schema.Types.ObjectId,
      ref: 'Question',
    },
  ],
  slug: { en_US: String, fi_FI: String, sv_SE: String },
  tags: {
    en_US: [mongoose.Schema.Types.Mixed],
    fi_FI: [mongoose.Schema.Types.Mixed],
  },
  resultInfo: {
    image: String,
    title: { en_US: String, fi_FI: String, sv_SE: String },
    altTitle: { en_US: String, fi_FI: String, sv_SE: String },
    positiveDescription: { en_US: String, fi_FI: String, sv_SE: String },
    negativeDescription: { en_US: String, fi_FI: String, sv_SE: String },
  },
});

const ResultCategory = mongoose.model('ResultCategory', resultCategorySchema);

export default ResultCategory;
