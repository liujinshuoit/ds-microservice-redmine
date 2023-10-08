package com.das.consultation.exception;

/**
 * created by jun on 2020/8/3
 * describe: 日期格式错误
 * version 1.0
 */
public class DateException extends Exception {
    public DateException(String val,String e) {
        super(val+"日期格式错误格式应为"+e+"格式");
    }
}
