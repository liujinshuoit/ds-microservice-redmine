package com.das.consultation.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * created by jun on 2020/11/26
 * describe:his对账入参实体
 * version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountDTO {

    @XmlElement
    private String datasource;//数据来源


    private String orgcode;//医疗机构代码

    @XmlElement
    private String businesstype;//业务类型

    @XmlElement
    private String visitid;//就诊id

    @XmlElement
    private String visittype;//就诊类别

    @XmlElement
    private String cardno;//健康卡号

    @XmlElement
    private String trueName;//患者姓名

    @XmlElement
    private String treatTime;//就诊时间


    @XmlElement
    private String payNo;//支付订单号

    @XmlElement
    private String invoiceNo;//发票号

    @XmlElement
    private String mchId;//商户id

    @XmlElement
    private String state;//交易类型

    @XmlElement
    private String payMethod;//支付方式

    @XmlElement
    private String payTime;//订单支付时间

    @XmlElement
    private String webPayNo;//交易流水号

    @XmlElement
    private String payTotalAmount;//支付总金额

    @XmlElement
    private String payAmt;//自费支付金额

    @XmlElement
    private String payMedicalAmount;//医保支付金额

    @XmlElement
    private String refundMan;//退费人

    @XmlElement
    private String refundTime;//订单退费时间

    @XmlElement
    private String refundNo;//退费流水号

    @XmlElement
    private String refundTotalAmount;//退费总金额

    @XmlElement
    private String refundAmt;//自费退费金额

    @XmlElement
    private String refundMedicalAmount;//医保退费金额


    private String accountCode;//账单流水号

    @XmlElement(name = "items")
    private List<AccountDetailDTO> items;//明细


}
