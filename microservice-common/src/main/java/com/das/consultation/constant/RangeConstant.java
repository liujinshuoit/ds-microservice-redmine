package com.das.consultation.constant;

/**
 * created by jun on 2020/9/28
 * describe: 值域校验常量
 * version 1.0
 */
public class RangeConstant {
    /**
     * 1:普通 2:新生儿
     * 仅无身份证件时按2传参，有身份证件时均按1传参。
     */
    public static final String[] PERSONNEL_TYPE = {"1", "2"};//人员类型
    /**
     * 1、APP在线申请
     * 2、医疗卫生机构自助机申请
     * 3、医疗卫生机构窗口申请
     * 4、批量预生成
     * 5、微信服务号
     * 6、微信小程序
     * 7、支付宝生活号
     * 8、支付宝小程序
     */
    public static final String[] APP_MODE = {"1", "2", "3", "4", "5", "6", "7", "8"};//操作方式
    /**
     * 预留字段默认为 0
     * 0、	无
     * 1、	微信
     * 2、	支付宝
     * 3、	银联
     * 4、	银行
     * 5、	统一支付平台
     * 9 、 其 他
     * 可多选，格式如下举例 1：1^2
     */
    public static final String[] PAY_ACC_TYPE = {"0", "1", "2", "3", "4", "5", "9"};//支付账户类型
    /**
     * 1.门急诊
     * 10.会诊信息
     * 11.转诊(院)信息
     * 2.住院
     * 3.体检
     * 4.妇幼保健
     * 5.社区医疗
     * 6.双向转诊
     * 7.计划免疫
     * 8.自我保健
     * 9.其他
     */
    public static final String[] STD_EVENT_TYPE = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};//就诊类型

    /**
     * 1=院内卡（就诊卡）；2=区域卡（健康卡） 3:身份证
     */
    public static final String[] CARD_TYPE = {"1", "2", "3"};//卡类型
    /**
     * 0：不需要 1：可不支付 2：必须支付
     */
    public static final String[] PAY_TYPE = {"0", "1", "2"};//支付要求

    /**
     * 01	现金
     * 02	支票
     * 03	汇款存款
     * 04	内部转账
     * 05	单位记账
     * 06	账户金
     * 07	统筹金
     * 08	银行卡
     * 99	其他
     * 90  微信
     * 91  支付宝
     */
    public static final String[] APP_PAY_TYPE = {"01", "02", "03", "04", "05", "06", "07", "08", "99", "90", "91"};//支付方式

    /**
     * 0：医院直接使用本系统放号
     * 1：使用其他系统排班旗号
     */
    public static final String[] RESOURCE_MANAGER_TYPE = {"0", "1"};//号源管理方式
    /**
     * 0：不需要 1：选填 2：必填
     */
    public static final String[] PATIENT_CARD_NEED = {"0", "1", "2"};//就诊卡必要性
    /**
     * 1：国家重点2：省重点3：市重点4：区重点5：普通科室
     */
    public static final String[] IS_IMPORTANT = {"1", "2", "3", "4", "5"};//重点科室
    /**
     * 1：不开通预约2：仅院内预约3：均可预约,
     */
    public static final String[] ORDER_SCOPE = {"1", "2", "3"};//预约范围

    /**
     * 0：初诊 1：复诊
     */
    public static final String[] PATIAPPLYTYPE = {"1", "2", "3"};//初诊/复诊
    /**
     * H:偏低,L:偏高,空：正常,C:危机值,N:阴性,P:阳性，S:可疑
     */
    public static final String[] ABNORMAL = {"H", "L", "null", "C", "N", "P", "S"};//异常提示代码

    /**
     * 0:约满 1：可约 -1：停诊
     */
    public static final String[] ISYUYUE = {"0", "1", "-1"};//排班标记
    /**
     * （1表示医保, 0不填表示自费）
     */
    public static final String[] YUYUEUSERTYPE = {"0", "1", null};//患者下单类型

    /**
     * 0：无，1：有
     */
    public static final String[] HAS_NO = {"0", "1"};//是否还有余号

    /**
     * 1：未支付，2：已支付
     */
    public static final String[] PAY_STATE = {"1", "2"};//支付状态
    /**
     * 1：自费，2：医保
     */
    public static final String[] MZ_TYPE = {"1", "2"};//是否医保结算
    /**
     * 1：自费支付，2：社保支付
     */
    public static final String[] PAY_TYPE_DCT = {"1", "2"};//支付单据类型
    /**
     * 1：银行2：窗口3：91160
     */
    public static final String[] PAY_CHANNEL = {"1", "2", "3"};//支付渠道

    /**
     * 1 APP 在线申请 2 医疗卫生机构自助机申 请3 医疗卫生机构窗口申请 4 批量预生成
     */
    public static final String[] APP_METHOD = {"1", "2", "3", "4"};//电子健康卡申请方式
    /**
     * 1=门诊，2=住院，3=挂号，4=体检
     */
    public static final String[] BUSINESS_TYPE = {"1", "2", "3", "4"};//HIS对账业务类型

    /**
     * 0：支付:1：退费
     */
    public static final String[] STATE = {"0", "1"};//HIS对账交易类型
    /**
     * 1：HIS,2:移动端
     */
    public static final String[] DATA_SOURCE = {"1", "2"};//数据来源
    /**
     * 1：常规检验，2：细菌培养 3:检验类别 4:药敏试验
     */
    public static final String[] CHECK_TYPE = {"1", "2", "3", "4"};//检验类别

    /**
     * 1：线上，2：线下
     */
    public static final String[] YUYUE_FROM = {"1", "2"};//预约来源
    /**
     * 1：已完成，2：已取消，3：未完成
     */
    public static final String[] ORDER_STATUS = {"1", "2"};//订单状态
    /**
     * S:敏感，I:中介，R:耐药
     */
    public static final String[] SENS_LEVEL = {"S", "I","R"};//订单状态

}
