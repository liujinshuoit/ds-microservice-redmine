package com.das.consultation.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * created by jun on 2020/11/26
 * describe:对账明细入参实体
 * version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountDetailDTO {

    @XmlElement
    private String uuid;

    @XmlElement
    private String payNo;//支付订单号

    @XmlElement
    private String itemcodehis;//收费项目代码

    @XmlElement
    private String itemcodeyb;//医保项目代码

    @XmlElement
    private String itemname;//收费项目名称


    @XmlElement
    private String excutedept;//执行科室

    @XmlElement
    private String deptaddress;//执行科室地址

    @XmlElement
    private String std;//规格

    @XmlElement
    private String amt;//数量

    @XmlElement
    private String unit;//数量单位

    @XmlElement
    private String unitprice;//单价

    @XmlElement
    private String detailpayamt;//应收金额
}
