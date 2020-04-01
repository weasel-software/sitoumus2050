create schema if not exists ca collate latin1_swedish_ci;

grant all on ca.* to 'ca_user'@'%' identified by 'ca_password' with grant option;

create table if not exists ca.CA_Commitment
(
    commitmentId            varchar(75) not null
        primary key,
    groupId                 bigint      null,
    companyId               bigint      null,
    userId                  bigint      null,
    userName                longtext    null,
    organizationId          varchar(75) null,
    createDate              datetime(6) null,
    modifiedDate            datetime(6) null,
    titleFI                 longtext    null,
    titleEN                 longtext    null,
    titleSV                 longtext    null,
    startDate               datetime(6) null,
    endDate                 datetime(6) null,
    updated                 datetime(6) null,
    created                 datetime(6) null,
    innovationFI            longtext    null,
    innovationEN            longtext    null,
    innovationSV            longtext    null,
    backgroundInformationFI longtext    null,
    backgroundInformationEN longtext    null,
    backgroundInformationSV longtext    null,
    shortDescriptionFI      longtext    null,
    shortDescriptionEN      longtext    null,
    shortDescriptionSV      longtext    null,
    address                 longtext    null,
    longitude               double      null,
    latitude                double      null,
    commitmentType          varchar(75) null,
    status                  varchar(75) null,
    likes                   int         null,
    joined                  int         null
);

create table if not exists ca.CA_DoneOperation
(
    id                     varchar(75) not null
        primary key,
    commitmentId           varchar(75) null,
    operationId            varchar(75) null,
    operationCategory      varchar(75) null,
    operationTitleFI       longtext    null,
    operationTitleSV       longtext    null,
    operationTitleEN       longtext    null
);

create table if not exists ca.CA_Meter
(
    id             varchar(75) not null
        primary key,
    commitmentId   varchar(75) null,
    operationId    varchar(75) null,
    meterId        varchar(75) null,
    meterCategory  varchar(75) null,
    meterChartType varchar(75) null,
    meterValueType varchar(75) null,
    meterTypeFI    longtext    null,
    meterTypeSV    longtext    null,
    meterTypeEN    longtext    null,
    startingLevel  longtext    null,
    targetLevel    longtext    null
);

create table if not exists ca.CA_Operation
(
    id                     varchar(75) not null
        primary key,
    commitmentId           varchar(75) null,
    operationId            varchar(75) null,
    operationCategory      varchar(75) null,
    operationTitleFI       longtext    null,
    operationTitleSV       longtext    null,
    operationTitleEN       longtext    null,
    operationDescriptionFI longtext    null,
    operationDescriptionSV longtext    null,
    operationDescriptionEN longtext    null
);

create table if not exists ca.CA_Report
(
    id                varchar(75) not null
        primary key,
    reportId          varchar(75) null,
    operationId       varchar(75) null,
    commitmentId      varchar(75) null,
    createdByUserId   int         null,
    createdByUserName longtext    null,
    organizationName  longtext    null,
    progress          varchar(75) null,
    reportStartDate   datetime(6) null,
    reportEndDate     datetime(6) null,
    reportStatus      tinyint     null,
    reportTextFI      longtext    null,
    reportTextSV      longtext    null,
    reportTextEN      longtext    null,
    reportTitleFI     longtext    null,
    reportTitleSV     longtext    null,
    reportTitleEN     longtext    null,
    status            varchar(75) null,
    version           double      null
);

create table if not exists ca.CA_ReportMeter
(
    id             varchar(75) not null
        primary key,
    commitmentId   varchar(75) null,
    operationId    varchar(75) null,
    reportId       varchar(75) null,
    meterId        varchar(75) null,
    meterCategory  varchar(75) null,
    meterChartType varchar(75) null,
    meterValueType varchar(75) null,
    meterTypeFI    longtext    null,
    meterTypeSV    longtext    null,
    meterTypeEN    longtext    null,
    currentLevel   longtext    null,
    startingLevel  longtext    null,
    targetLevel    longtext    null
);

create table if not exists ca.CA_Results
(
    id         varchar(75)  not null primary key,
    resultType varchar(125) not null,
    resultData varchar(75)  not null,
    calculated datetime     not null,
    success    boolean      not null
)
