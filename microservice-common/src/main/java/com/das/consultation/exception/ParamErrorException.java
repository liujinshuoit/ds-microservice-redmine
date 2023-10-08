package com.das.consultation.exception;

/**
 * created by jun on 2020/8/3
 * describe: 参数错误返回
 * version 1.0
 */
public class ParamErrorException extends Exception {
    public ParamErrorException() {
        super("参数错误");
    }
    public ParamErrorException(String str) {
        super(str+"参数有误"+",请检查"+str+"的拼写以及值是否可以为空");
    }
}
