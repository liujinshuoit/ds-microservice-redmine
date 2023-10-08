package com.das.consultation.exception;

/**
 * created by jun on 2020/8/3
 * describe: 空返回错误
 * version 1.0
 */
public class NotFoundException extends Exception {
    public NotFoundException() {
        super("没有数据");
    }
}
