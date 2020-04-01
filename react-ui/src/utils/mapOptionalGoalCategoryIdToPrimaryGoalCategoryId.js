const mapOptionalGoalCategoryIdToPrimaryGoalCategoryId = id => {
  switch (Number(id)) {
    case 31811:
      return 31803;
    case 31812:
      return 31804;
    case 31813:
      return 31805;
    case 31814:
      return 31806;
    case 31815:
      return 31807;
    case 31816:
      return 31808;
    case 31817:
      return 31809;
    case 31818:
      return 31810;
    default:
      return id;
  }
};

export default mapOptionalGoalCategoryIdToPrimaryGoalCategoryId;
