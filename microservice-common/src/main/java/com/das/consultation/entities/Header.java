package com.das.consultation.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * created by jun on 2020/11/11
 * describe:头部信息
 * version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Header {
    /**
     * 状态码
     */
    @XmlElement
    private String result;
    /**
     * 状态描述
     */
    @XmlElement
    private String desc;
}
