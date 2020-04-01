import * as mongoose from 'mongoose';

const questionSchema = new mongoose.Schema({
  title: { en_US: String, fi_FI: String, sv_SE: String },
  index: Number,
  answers: [
    {
      type: mongoose.Schema.Types.ObjectId,
      ref: 'Answer',
    },
  ],
});

const Question = mongoose.model('Question', questionSchema);

export default Question;
