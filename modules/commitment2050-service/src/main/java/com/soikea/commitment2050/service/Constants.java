package com.soikea.commitment2050.service;

import java.util.Locale;

public class Constants {
    public static final long ADMIN_USER_ID = 20156l;

    public static final long GLOBAL_GROUP_ID = 20152l;
    public static final long SITOUMUS2050_GROUP_ID = 20143l;
    public static final long GUEST_USER_ID = 20120l;

    public static final long COMMITMENTS_FOLDER_ID = 31788;
    public static final long COMMITMENTS_GROUP_ID = 20143;
    public static final long COMMITMENTS_REPORTS_FOLDER_ID = 41273;

    public static final long GREEN_DEAL_COMMITMENTS_FOLDER_ID = 31790;
    public static final long GREEN_DEAL_REPORTS_FOLDER_ID = 41277;

    public static final long NUTRITION_COMMITMENTS_FOLDER_ID = 31792;
    public static final long NUTRITION_REPORTS_FOLDER_ID = 41275;

    public static final long CITIES_FILE_CATEGORY_ID = 138276;

    public static final String GLOBAL_COMMITMENT_DDM_STRUCTURE_KEY = "31796";
    public static final String GLOBAL_COMMITMENT_DDM_TEMPLATE_KEY = "33557";

    public static final String GLOBAL_REPORT_DDM_STRUCTURE_KEY = "40817";
    public static final String GLOBAL_REPORT_DDM_TEMPLATE_KEY = "40825";

    public static final String GLOBAL_EXAMPLE_COMMITMENT_DDM_STRUCTURE_KEY = "41393";
    public static final String GLOBAL_EXAMPLE_COMMITMENT_DDM_TEMPLATE_KEY = "50996";


    public static final long COMPANY_ID = 20116l;
    public static final String WEB_CONTENT_ARTICLE_ORGANIZATION_NAME_EXPANDO_FIELD_NAME = "Organization name";
    public static final String WEB_CONTENT_ARTICLE_ORGANIZATION_ID_EXPANDO_FIELD_NAME = "organizationId";
    public static final String WEB_CONTENT_ARTICLE_CREATOR_USER_ID_EXPANDO_FIELD_NAME = "creatorUserId";
    public static final String WEB_CONTENT_ARTICLE_CREATOR_USER_NAME_EXPANDO_FIELD_NAME = "creatorUserName";
    public static final String WEB_CONTENT_ARTICLE_CREATOR_PORTRAIT_EXPANDO_FIELD_NAME = "creatorPortrait";
    public static final String WEB_CONTENT_ARTICLE_ORGANIZATION_LOGO_EXPANDO_FIELD_NAME = "organizationLogo";

    public static final long JOURNALARTICLE_CLASSNAME_ID = 29644;

    public static final long COMMITMENT_TYPE_VOCABULARY_ID = 33552;
    public static final long GREENDEAL_CATEGORY_ID = 33554;
    public static final long OIL_INDUSTRY_CATEGORY_ID = 284155;
    public static final long PLASTIC_BAG_CATEGORY_ID = 33556;
    public static final long AUTOMOTIVE_INDUSTRY_CATEGORY_ID = 237431;
    public static final long WORK_MACHINE_CATEGORY_ID = 386044;
    public static final long DEMOLITION_CATEGORY_ID = 427415;
    public static final long NUTRITION_CATEGORY_ID = 33555;
    public static final long COMMITMENT_CATEGORY_ID = 33553;
    public static final long COMMITMENT_100_SMART_WAYS = 140111;

    public static final long PRIMARY_GOALS_VOCABULARY_ID = 31800;
    public static final long ADDITIONAL_GOALS_VOCABULARY_ID = 31801;
    public static final long AGENDA2030_VOCABULARY_ID = 31802;

    public static final long MISC_COMMITMENT_CATEGORIES_VOCABULARY_ID = 65645;
    public static final long MISC_COMMITMENT_HAS_REPORT = 65646;


    public static final long USER_TYPE_VOCABULARY_ID = 33488;//SAME AS "SITOUMUKSEN TEKIJÄ"
    public static final long USER_TYPE_PRIVATE_PERSON = 33545;

    public static final long PARENT_ORGANIZATION_ID = 0l;
    public static final long ORGANIZATION_SIZE_ID = 33547;
    public static final String ORGANIZATION_LAYOUT_SET_PROTOTYPE_UUID = "23f6922a-60cc-2cb6-7b8d-f712c81d82e6";
    public static final String ORGANIZATION_TYPE_EXPANDO_FIELD_NAME = "type";
    public static final String USER_TYPE_EXPANDO_FIELD_NAME = "personType";

    public static final String PERSON_TYPE_VALUE_PRIVATE = "Yksityishenkilö";
    public static final String PERSON_TYPE_VALUE_ORGANIZATION = "Organisaation edustaja";


    public static final String COMMITMENT_OPERATION_TEMPLATES_COMMON_FOLDER_ID = "41285";
    public static final String COMMITMENT_OPERATION_TEMPLATES_EDUCATIONAL_FOLDER_ID = "41287";
    public static final String COMMITMENT_OPERATION_TEMPLATES_INDIVIDUAL_FOLDER_ID = "51499";

    public static final Long COMMITMENT_OPERATION_TEMPLATES_100_SMART_WAYS_FOLDER_ID = 60526l;

    public static final String COMMITMENT_METER_TEMPLATES_COMMON = "45897";
    public static final String COMMITMENT_METER_TEMPLATES_GREEN_DEAL = "52045";

    public static final long ORGANIZATION_TYPE_EXPANDO_COLUMN_ID = 31847;

    public static final String GENERIC_COMMITMENT_FI_TITLE = "Sitoumus";
    public static final String GENERIC_REPORT_FI_TITLE = "Raportti";


    //TODO: make admin email addresses configurable
    public static final String GLOBAL_COMMITMENT_EMAIL_DDM_TEMPLATE_KEY = "31855";
    public static final String EMAIL_ADMIN_COMMIT_SAVED = "admin.commitment.save.notification.to";
    public static final String EMAIL_ADMIN_NUTRITION_SAVED = "admin.nutrition.save.notification.to";
    public static final String EMAIL_ADMIN_GREEN_DEAL_SAVED = "admin.greendeal.save.notification.to";
    public static final String EMAIL_ADMIN_AUTOMOTIVE_SAVED = "admin.automotive.save.notification.to";
    public static final String EMAIL_ADMIN_OIL_SAVED = "admin.oil.save.notification.to";
    public static final String EMAIL_ADMIN_PLASTIC_BAG_SAVED = "admin.plastic_bag.save.notification.to";


    public static final String lang_fi = "fi_FI";
    public static final String lang_en = "en_US";
    public static final String lang_sv = "sv_SE";

    public static final Locale locale_fi = new Locale("fi", "FI");
    public static final Locale locale_en = new Locale("en", "US");
    public static final Locale locale_sv = new Locale("sv", "SE");

    public static final String LEGACY_REPORT_REFERENCE_GENERIC_OPERATION_ID = "generic";

    public static final String COMMITMENT_IMAGE_FOLDER_NAME = "Sitoumusten kuvat";
    public static final long COMMITMENT_IMAGE_FOLDER_ID = 60679;


    public static final String MAIL_SENDER = "kestavakehitys@vnk.fi";
    public static final String EMAIL_SEND_TO_REVIEW_TEMPLATE_ARTICLE_URL_TITLE = "sitoumus-tarkastuksessa";
    public static final String EMAIL_100SMARTWAYS_PUBLISHED_TEMPLATE_ARTICLE_URL_TITLE = "sitoumus-100smartways-julkaisu";


    public static final String EMAIL_USER_REGISTERED_AND_ADDED_TO_ORGANIZATION = "rekisteroity-ja-liitetty-organisaatioon";
    public static final String EMAIL_COMMITMENT_APPROVED_URL_TITLE = "sitoumus-tarkastettu-ja-hyväksytty";

    public static final String EMAIL_REPORT_REMINDER = "muistutus-raportoinnista";
    public static final String EMAIL_GREENDEAL_REPORT_REMINDER = "muistutus-greendeal-raportoinnista";

    public static final String DEFAULT_LANDING_PAGE_PATH = "/profiili";
    public static final String SMARTWAYS_LOGIN_PAGE = "/elamantapasitoumus-kirjaudu";
    public static final String SMARTWAYS_REGISTRATION_SUCCESSFULL_PAGE = "/elamantapa-rekisteroityminen-onnistui";
    public static final String SMARTWAYS_LANDING_PAGE_PATH = "/profiili#/tee-elamantapa-sitoumus";
    public static final String AUTOLOGIN_PARAMETER_FROM_REGISTRATION = "fromregistration";

}
