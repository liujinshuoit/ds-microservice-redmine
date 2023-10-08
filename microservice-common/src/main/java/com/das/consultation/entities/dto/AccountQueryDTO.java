package com.das.consultation.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
public class AccountQueryDTO {

    @XmlElement
    private String datasource;//数据来源

    @XmlElement
    private String orgcode;//医疗机构代码

    @XmlElement
    private String paymethod;//支付方式

    @XmlElement
    private String billdatebegin;//开始时间


    @XmlElement
    private String billdateend;//结束时间

    @XmlElement
    private String businesstype;//业务类型






}
