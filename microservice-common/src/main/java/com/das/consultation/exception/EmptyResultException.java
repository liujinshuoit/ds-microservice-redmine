package com.das.consultation.exception;

/**
 * created by jun on 2020/8/3
 * describe: 空返回错误
 * version 1.0
 */
public class EmptyResultException extends Exception {
    public EmptyResultException() {
        super("empty result ..");
    }
    public EmptyResultException(String xml) {
        super(xml+"不能为空");
    }
}
