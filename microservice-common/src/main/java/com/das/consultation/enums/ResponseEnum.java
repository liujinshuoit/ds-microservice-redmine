package com.das.consultation.enums;

/**
 * created by jun on 2020/9/22
 * describe:返回类型
 * version 1.0
 */
public enum ResponseEnum {
    SUCCESS("0","成功"),FAIL("1","失败");
    private String code;
    private String msg;

    ResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
