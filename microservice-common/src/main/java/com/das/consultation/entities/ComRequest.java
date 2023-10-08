package com.das.consultation.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

/**
 * created by jun on 2020/11/10
 * describe:公共入参
 * version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class ComRequest<T> {
    @XmlElement
    private String head;//头部
    @XmlAnyElement(lax = true)
    private T data;//入参数据
}
