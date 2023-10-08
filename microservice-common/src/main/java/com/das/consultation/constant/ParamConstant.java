package com.das.consultation.constant;

/**
 * created by jun on 2020/9/28
 * describe:参数常量
 * version 1.0
 */
public class ParamConstant {
    /**
     * 健康卡新增接口参数校验
     */
    public static final String[] PARAM_HEALTH_ADD = {"ecardid", "cardno", "patientname", "cardid", "cardtype",
            "sex", "nation", "birthdate", "personneltype", "appmode",
            "payacctype"};
    /**
     * 健康卡更新接口参数校验
     */
    public static final String[] PARAM_HEALTH_UPDATE = {"ecardid", "cardno", "sex", "nation", "birthdate"};

    /**
     * 健康卡注销接口参数校验
     */
    public static final String[] PARAM_HEALTH_DELETE = {"ecardid", "cardno", "cardtype", "cardid"};
    /**
     * 远程会诊信息接口参数校验
     */
    public static final String[] PARAM_REMOTE_ADD = {"orgcode", "cardno", "modifiedtime", "uploadtime"};
    /**
     * 会诊报告信息推送接口参数校验
     */
    public static final String[] PARAM_CLT_REPORT = {"orgcode", "cardno", "visitid", "primid", "modifiedtime", "uploadtime"};
    /**
     * 会诊报告信息查询接口参数校验
     */
    public static final String[] PARAM_CLT_REPORT_QUERY = {"orgcode", "cardno", "visitid"};


    /**
     * 转诊转院接口参数校验
     */
    public static final String[] PARAM_REFERRAL = {"orgcode", "orgname", "cardno", "visitid", "turndoctoreid", "turndoctorname",
            "turnreason", "turninorgcode", "visittype", "turnicdcode", "turnicdname", "paymethod", "issign", "turntype", "turnoutdepcode",
            "turnoutdepname"};

    /**
     * 身份绑定接口参数校验
     */
    public static final String[] PARAM_BIND_CARD = {"cardno", "cardtype", "patientname", "cardIdType"};


    /**
     * 医院查询参数校验
     */
    public static final String[] PARAM_QUERY_HOSPITAL_INFO = {"orgcode"};


    /**
     * 排班信息查询接口参数校验
     */
    public static final String[] PARAM_SCHEDULE = {"orgcode", "depid", "doctorid", "begindate", "enddate"};


    /**
     * HIS推送口参数校验
     */
    public static final String[] PARAM_HIS_ACCOUNT = {"datasource", "orgcode", "cardno", "trueName", "payNo", "mchId", "state", "payMethod"
            , "payTime", "businesstype"};
    /**
     * HIS推送口参数校验
     */
    public static final String[] PARAM_HIS_DETAIL = {"itemcodehis", "itemname", "amt", "unit", "unitprice", "detailpayamt"};
    /**
     * HIS拉取接口参数校验
     */
    public static final String[] PARAM_HIS_ACCOUNT_QUERY = {"datasource", "orgcode", "billdatebegin", "billdateend"};

    /**
     * 健康卡注册请求入参数据校验
     */
    public static final String[] PARAM_EHC_REGISTER = {"app_id", "term_id", "app_key","out_register_no","out_register_time" ,"certificate_mode", "apply_method", "id_type", "id_no",
            "user_name", "user_sex", "mobile_phone", "pub_flag", "out_register_no", "apply_method"};
    /**
     * 健康卡修改请求入参数据校验
     */
    public static final String[] PARAM_EHC_ALTER = {"app_id", "term_id", "app_key","ehealth_card_id" ,"out_alter_no", "out_alter_time", "new_app_id", "user_name"};

    /**
     * 健康卡修改请求入参数据校验
     */
    public static final String[] PARAM_EHC_GENERATE = {"app_id", "term_id", "app_key","ehealth_card_id","out_generate_no" ,"out_generate_time"};
    /**
     * 健康卡验证请求入参数据校验
     */
    public static final String[] PARAM_EHC_VERIFY = {"appId","termId", "appKey", "cardInfo"};
    /**
     * 注销健康卡请求入参数据校验
     */
    public static final String[] PARAM_EHC_CLOSE = {"app_id","term_id", "app_key", "ehealth_card_id","out_close_no","out_close_time"};
    /**
     * 检查申请单推送入参数据校验
     */
    public static final String[] PARAM_CHECK_APPLICATION_FORM = {"orgcode", "cardno", "patientid", "visitid", "checkapplpid"};
    /**
     * 检查申请单推送入参数据校验
     */
    public static final String[] PARAM_CHECK_APPLICATION_FORM_DETAIL = {"checkcode", "checkname", "applydeptname", "applydoctorname", "applytime", "clinicalconclude"};
    /**
     * 检查报告详情推送入参数据校验
     */
    public static final String[] PARAM_CHECK_REPORT_DETAIL = {"orgcode", "orgname", "cardno", "checkapplpid", "patientname", "sex"};
    /**
     * 检查报告详情推送入参数据校验
     */
    public static final String[] PARAM_CHECK_REPORT_DETAIL_ITEM = {"deptname", "checkresult"};

    /**
     * 检查报告详情查询入参数据校验
     */
    public static final String[] PARAM_CHECK_REPORT_DETAIL_QUERY = {"orgcode", "checkapplpid"};

    /**
     * 检验报告详情查询入参数据校验
     */
    public static final String[] PARAM_INSPECT_REPORT_DETAIL_QUERY = {"orgcode", "testapplpid"};
    /**
     * 检验申请单推送入参数据校验
     */
    public static final String[] PARAM_INSPECT_APPLICATION_FORM = {"orgcode", "cardno", "patientid", "visitid", "testapplpid", "applydeptname", "applydoctorname", "applytime", "clinicalconclude"};
    /**
     * 检验申请单推送入参数据校验
     */
    public static final String[] PARAM_INSPECT_APPLICATION_FORM_DETAIL = {"checktype", "checkcode", "checkname"};

    /**
     * 检验报告详情送入参数据校验
     */
    public static final String[] PARAM_INSPECT_REPORT_DETAIL = {"orgcode", "orgname", "cardno", "testapplpid", "patientname", "sex"};
    /**
     * 检验报告详情ITEMS送入参数据校验
     */
    public static final String[] PARAM_INSPECT_REPORT_DETAIL_ITEMS = {"itemname", "itemresult", "abnormal"};

    /**
     * 预约挂号信息推送入参数据校验
     */
    public static final String[] PARAM_APPOINTMENT_INFO = {"orgcode", "cardno", "orderid", "depid", "depname", "doctorid", "doctorname"
            , "queueno", "submitdate", "visitdate", "begintime", "endtime", "numresid", "scheduleid", "patientname", "sex", "paymethod"
            , "yuyuefrom", "ordertime", "orderstatus", "paystatus", "orderamt"};

    /**
     * 预约列表查询入参数据校验
     */
    public static final String[] PARAM_APPOINTMENT_LIST = {"orgcode", "begindate", "enddate", "cardno"};

    /**
     * 患者报到取号推送入参效验
     */
    public static final String[] PARAM_TAKE_NUMBER = {"orgcode", "orderid", "cardno", "queueno", "qhfph"};

    /**
     * 药敏试验的入参效验
     */
    public static final String[] BACTERIALCULTURE_INFO = {"orgcode", "orgname", "cardno", "testapplpid", "patientname", "sex"};

    /**
     * 药敏试验的入参效验
     */
    public static final String[] DRUGSENSITIVITY_REPORT = {"orgcode", "orgname", "cardno", "testapplpid", "patientname", "sex"};

    /**
     *细菌培养入参效验
     */
    public static final String[] CULTURE_DETAIL= {"orgcode", "orgname", "cardno", "visittype","visitid","testapplpid", "patientname", "sex"};

    /**
     *细菌培养ITEMS入参效验
     */
    public static final String[] CULTURE_DETAIL_ITEMS= {"culturecode","culturename","cultureresult"};
    /**
     *细菌培养报告查询入参效验
     */
    public static final String[] CULTURE_DETAIL_QUERY= {"orgcode","testapplpid"};

    /**
     *药敏试验推送入参效验
     */
    public static final String[] CHEMO_DETAIL= {"orgcode", "orgname", "cardno", "visittype","testapplpid", "patientname", "sex"};

    /**
     *药敏试验ITEMS入参效验
     */
    public static final String[]CHEMO_DETAIL_ITEMS= {"culturecode","culturename","anticode","antiname","senslevel"};
    /**
     *药敏试验报告查询入参效验
     */
    public static final String[] CHEMO_DETAIL_QUERY= {"orgcode","testapplpid"};
}
