// @flow

const mapCO2toCategoryId = (co2: number) => {
  if (co2 < 3000) return 136874;
  else if (co2 < 5000) return 136875;
  else if (co2 < 8000) return 136876;
  else if (co2 < 10000) return 136877;
  else if (co2 < 13000) return 136878;
  else return 136879;
};

export default mapCO2toCategoryId;
