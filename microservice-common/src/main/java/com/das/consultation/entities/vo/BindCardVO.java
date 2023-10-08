package com.das.consultation.entities.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * created by jun on 2020/11/12
 * describe:身份绑定出参
 * version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class BindCardVO {
    /**
     * 健康卡号
     */
    @XmlElement
    private String cardno;
    /**
     * empi
     */
    @XmlElement
    private String empi;
}
