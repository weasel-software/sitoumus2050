// @flow

export type CATEGORY_TYPE = {
  categoryId: string,
  companyId: string,
  createDate: string,
  description: ?string,
  descriptionCurrentValue: ?string,
  leftCategoryId: string,
  groupId: string,
  name: string,
  rightCategoryId: string,
  parentCategoryId: string,
  titleCurrentValue: string,
  title_en_US: string,
  title_fi_FI: string,
  title_sv_SE: string,
  uuid: string,
  vocabularyId: string,
  value: string
};

export type ORGANIZATION_TYPE = {
  comments: string,
  companyId: string,
  countryId: string,
  createDate: string,
  logId: string,
  modifiedDate: string,
  mvccVersion: string,
  name: string,
  organizationId: string,
  parentOrganizationId: string,
  recursable: boolean,
  regionId: string,
  statusId: string,
  treePath: string,
  type: string,
  userId: string,
  userName: string,
  uuid: string,
  emailAddresses: Array<{
    address: string,
    typeId: number
  }>,
  websites: Array<{
    url: string,
    typeId: number
  }>,
  categories?: {
    organizationType: CATEGORY_TYPE,
    organizationSubType: CATEGORY_TYPE,
    organizationSize: CATEGORY_TYPE
  },
  logo?: string
};

export type ORGANIZATION_TYPE_TYPE = {
  categoryId: string,
  companyId: string,
  createDate: string,
  description: ?string,
  descriptionCurrentValue: ?string,
  groupId: string,
  lastPublishDate: ?string,
  leftCategoryId: string,
  modifiedDate: string,
  name: string,
  parentCategoryId: string,
  rightCategoryId: string,
  title: string,
  titleCurrentValue: string,
  title_fi_FI: string,
  title_en_US: string,
  title_sv_SE: string,
  userId: string,
  userName: string,
  uuid: string,
  vocabularyId: string,
  value: string
};

export type ORGANIZATION_SUBTYPE_TYPE = ORGANIZATION_TYPE_TYPE;

export type COUNTRY_TYPE = {
  a2: string,
  a3: string,
  active: boolean,
  countryId: string,
  idd: string,
  name: string,
  nameCurrentValue: string,
  number: string,
  zipRequired: boolean
};

export type ORGANIZATION_SIZE = {
  categoryId: string,
  companyId: string,
  createDate: number,
  description: string,
  descriptionCurrentValue: string,
  groupId: string,
  lastPublishDate: ?number,
  leftCategoryId: string,
  modifiedDate: ?number,
  name: string,
  parentOrganizationId: string,
  rightCategoryId: string,
  title: string,
  titleCurrentValue: string,
  userId: string,
  userName: string,
  uuid: string,
  vocabularyId: string
};

export type ARTICLE_TYPE = {
  articleId: string,
  companyId: string,
  content: string,
  createDate: number,
  folderId: string,
  groupId: string,
  id: string,
  modifiedDate: number,
  smallImageId: ?string,
  statusByUserId: string,
  statusByUserId: string,
  title: string,
  userId: string,
  userName: string,
  uuid: string,
  titleCurrentValue: string,
  statusDate: number,
  urlTitle: string,
  status: -1 | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8,
  assetCategoryIds: {
    [number]: Array<string>
  },
  entryClassPK: string,
  organizationName?: string,
  organizationId?: string,
  key: string,
  priority: string,
  organizationLogo?: string
};

export type LocalizationsType = {
  fi_FI: string,
  sv_SE: string,
  en_US: string
};

export type SMARTWAY_ARTICLE_TYPE = {
  articleId: string,
  category: string,
  title: LocalizationsType
};

export type PROFILE_TYPE = {
  companyId: string,
  screenName: ?string,
  locale: string,
  firstName: string,
  middleName: ?string,
  lastName: string,
  personType: "Yksityishenkilö" | "Organisaation edustaja",
  allowEmailReminder?: boolean,
  addresses?: [
    {
      street1: string,
      city: string,
      countryId: number,
      primary?: boolean,
      shipping?: boolean,
      typeId?: number,
      zip: string
    }
  ],
  agreedToTermsOfUse: boolean,
  comments: string,
  emailAddress: string,
  userId: string,
  contactId: string,
  profilePicURL?: ?string
};

export type MeterType = {
  meterId: string,
  key?: string,
  commitmentOperationMeterRefId?: string,
  meterType_fi_FI: string,
  meterType_sv_SE: string,
  meterType_en_US: string,
  startingLevel?: number,
  targetLevel?: number,
  currentLevel?: string,
  meterChartType?: "LINE" | "COLUMN" | "PIE",
  meterValueType: "NUMBER" | "REALIZED",
  // meterValue: string,
  meterValues?: {
    startingLevel: number,
    targetLevel: number
  },
  meterCategory: string
};

export const ReportProgressMap = [
  "NotRelevant",
  "NoProgress",
  "NotAsFast",
  "AsPlanned",
  "Faster",
  "CompletelyAchieved"
];

export type ReportProgressType =
  | "NotRelevant"
  | "NoProgress"
  | "NotAsFast"
  | "AsPlanned"
  | "Faster"
  | "CompletelyAchieved";

export type ReportType = {
  commitmentArticleId: string,
  commitmentOperationRefId: string,
  createdByUserId: string,
  createdByUserName: string,
  +id: string,
  operationTitle_en_US: string,
  operationTitle_fi_FI: string,
  operationTitle_sv_SE: string,
  organizationName: string,
  progress: ?ReportProgressType,
  reportMeters: Array<MeterType & { commitmentOperationMeterRefId?: ?string }>,
  reportStartDate: string,
  reportEndDate: string,
  reportStatus: boolean,
  reportText_en_US: string,
  reportText_fi_FI: string,
  reportText_sv_SE: string,
  reportTitle_en_US: string,
  reportTitle_fi_FI: string,
  reportTitle_sv_SE: string,
  +status: "approved" | "draft",
  +version: string
};

export type OperationType = {
  operationTitle_fi_FI: string,
  operationTitle_sv_SE: string,
  operationTitle_en_US: string,
  meters: Array<MeterType>,
  articleId: string,
  id: string,
  meters: Array<MeterType>,
  operationDescription?: string,
  operationDescription_fi_FI?: string,
  operationDescription_en_US?: string,
  operationDescription_sv_SE?: string,
  key: string,
  operationId: string,
  reports: ?Array<ReportType>
};

export type DoneOperationType = {
  operationTitle_fi_FI: string,
  operationTitle_sv_SE: string,
  operationTitle_en_US: string,
  operationCategory: string,
  operationId: string
};

export type CommitmentType = {
  userName?: string,
  id: string,
  groupId: ?number,
  title_fi_FI: string,
  title_sv_SE: string,
  title_en_US: string,
  startDate: string,
  endDate: string,
  innovation_fi_FI: string,
  innovation_sv_SE: string,
  innovation_en_US: string,
  innovation_fi_FI: string,
  innovation_sv_SE: string,
  innovation_en_US: string,
  backgroundInformation_fi_FI: string,
  backgroundInformation_sv_SE: string,
  backgroundInformation_en_US: string,
  shortDescription_fi_FI: string,
  shortDescription_sv_SE: string,
  shortDescription_en_US: string,
  operations: ?Array<OperationType>,
  doneOperations?: ?Array<DoneOperationType>,
  genericReports?: Array<ReportType>,
  +organizationName?: string,
  +organizationId?: string,
  +createdByUserId: string,
  +createdByUserName?: string,
  address: string,
  longitude: ?number,
  latitude: ?number,
  language: string,
  reportingInterval: any,
  reportReminder: boolean,
  acceptCriterias: boolean,
  categoryIds: Array<number>,
  commitmentType: "COMMITMENT" | "GREEN_DEAL" | "NUTRITION",
  status: "draft" | "approved",
  version?: number,
  joined?: number,
  likes?: number,
  error?: string,
  organizationLogo?: string,
  commitmentImageUrl: string
};

export type DatesTextsValuesDataType = {
  values: Array<number>,
  texts: Array<string>,
  dates: Array<string>
};

export type LabelValueDataType = {
  values: Array<number>,
  labels: Array<string>
};

export type ErrorType = {
  message: string,
  fileName?: string,
  lineNumber?: number
};

export type LocaleType = "en_US" | "fi_FI"; // "sv_SE" |

export type CityType = {
  name_fi_FI: string,
  name_en_US: string,
  name_sv_SE: string,
  type: string,
  mainLanguage: string,
  municipality_fi_FI: string,
  municipality_en_US: string,
  municipality_sv_SE: string,
  latitude: number,
  longitude: number
};

export type NotificationType = {
  state: "SUCCESS" | "FAILURE" | "IN_PROGRESS" | "NOT_STARTED",
  message: string
};

export type CalculationsType = {
  co2e: number,
  co2eAfterDone: number,
  co2eAfterTodo: number,
  reduction: number
};

export type HundredTodosTipsType = {
  id: number,
  increase: number, // increase or decrease should have value, but not both
  decrease: number,
  article: Object
};

export type HundredTodosType = {
  calculations: CalculationsType,
  tipsTodo: Array<HundredTodosTipsType>,
  tipsDone: Array<HundredTodosTipsType>,
  startDate: string,
  endDate: string,
  primaryGoalId: number,
  commitmentTypeId: number,
  title_fi_FI: string,
  title_sv_SE: string,
  title_en_US: string,
  backgroundInformation_fi_FI: string,
  backgroundInformation_sv_SE: string,
  backgroundInformation_en_US: string
};

/* Hack to enforce exhaustive switch case in flow */
type Empty = "empty type" & "nothing there";

export function unexpectedCase(impossible: Empty): void {
  // console.log(`Unexpected case ${impossible}`);
}
