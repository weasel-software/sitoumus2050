// @flow
import translate from "../components/reusable/translate";
import type { CommitmentType, CalculationsType } from "../constants/types";

const backgroundInformation = (
  language: "fi_FI" | "en_US" | "sv_SE",
  calculations: CalculationsType
) => {
  return (
    `${translate({
      textKey: "sit.commitmentCreation.myCo2Footprint",
      languageOverride: language
    })}\t\t\t${calculations.co2e.toFixed(0)} kg CO2e\n` +
    `${translate({
      textKey: "sit.commitmentCreation.myCo2FootprintGoal",
      languageOverride: language
    })}\t\t\t${calculations.co2eAfterTodo.toFixed(0)} kg CO2e`
  );
};

const parseBackgroundInformation = (s: string) => {
  // hack, parse numeric values from backgroundInformation string
  const [co2e, co2eAfterTodo] = s.split(/[ \t]+/).filter(el => !isNaN(el));
  return {
    co2e: Number(co2e),
    co2eAfterTodo: Number(co2eAfterTodo)
  };
};

const getCo2DecreasePercentage = (c: CommitmentType) => {
  const op = c.operations && c.operations.find(el => true);
  if (op) {
    const doneOperationIds = c.doneOperations
      ? c.doneOperations.map(op => op.operationId)
      : [];
    const summer = (sum, meter) => {
      const value = meter.targetLevel;
      return value ? parseFloat(value) + sum : sum;
    };

    const percentage = op.meters
      .filter(meter => !doneOperationIds.includes(meter.meterId))
      .reduce(summer, 0);
    return percentage;
  } else {
    return NaN;
  }
};

const primaryGoalId = 31804;

type CategoryIds = {
  categoryIds: Array<number>
};

const isHundredTodosCommitment = (
  commitment: CommitmentType | CategoryIds
): boolean => {
  return commitment.categoryIds.findIndex(id => id === 140111) >= 0;
};

export {
  backgroundInformation,
  parseBackgroundInformation,
  isHundredTodosCommitment,
  getCo2DecreasePercentage,
  primaryGoalId
};
