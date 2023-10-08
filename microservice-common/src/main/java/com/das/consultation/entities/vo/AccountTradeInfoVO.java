package com.das.consultation.entities.vo;

import com.das.consultation.entities.query.AccountQueryDetailVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * created by jun on 2020/11/26
 * describe:his对账账拉取出参实体
 * version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountTradeInfoVO {

    @XmlElement
    private String visittype;//数据来源

    @XmlElement
    private String visitid;//就诊ID

    @XmlElement
    private String trueName;//医疗机构代码

    @XmlElement
    private String treatTime;//支付方式

    @XmlElement
    private String payNo;//开始时间


    @XmlElement
    private String invoiceNo;//结束时间

    @XmlElement
    private String mchId;//业务类型
    @XmlElement
    private String state;//业务类型
    @XmlElement
    private String businesstype;//业务类型
    @XmlElement
    private String payMethod;//业务类型
    @XmlElement
    private String payTime;//业务类型
    @XmlElement
    private String webPayNo;//业务类型
    @XmlElement
    private String payTotalAmount;//业务类型
    @XmlElement
    private String payAmt;//业务类型
    @XmlElement
    private String payMedicalAmount;//业务类型
    @XmlElement
    private String refundMan;//业务类型
    @XmlElement
    private String refundTime;//业务类型
    @XmlElement
    private String refundNo;//业务类型
    @XmlElement
    private String refundTotalAmount;//业务类型
    @XmlElement
    private String refundAmt;//业务类型
    @XmlElement
    private String refundMedicalAmount;//业务类型

    @XmlElement(name = "items")
    private List<AccountQueryDetailVO> items;//明细


}
