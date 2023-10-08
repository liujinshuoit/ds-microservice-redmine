package com.das.consultation.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by jun on 2020/8/10
 * describe:xml报文提示信息
 * version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XmlMessage {
    /**
     * 返回状态
     */
    private String result;
    /**
     * 返回信息
     */
    private String desc;
}
