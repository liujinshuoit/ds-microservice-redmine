package com.das.consultation.exception;

/**
 * created by jun on 2020/8/3
 * describe: 自定义异常返回对象
 * version 1.0
 */
public class MyException extends RuntimeException {
    private String errorCode;
    private String msg;

    public MyException(String message) {
        super(message);
    }

    public MyException(String errorCode, String msg) {
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
