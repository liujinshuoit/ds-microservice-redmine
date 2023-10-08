package com.das.consultation.util;


import cn.hutool.json.JSONUtil;
import com.das.consultation.entities.ComResponse;
import com.das.consultation.entities.Header;
import com.das.consultation.enums.ResponseEnum;

/**
 * created by ljs on 2022/06/03
 * describe:响应状态
 * version 1.0
 */
public class ResultUtils {
    /**
     * 请求成功 带data数据
     * @param t   泛型
     * @param <T> 泛型
     * @return xml字符串
     */
    public static <T> String success(T t) {
        ComResponse<T> comResponse = new ComResponse<>();
        comResponse.setHead(new Header(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMsg()));
        comResponse.setData(t);
        return JSONUtil.toJsonStr(comResponse);
    }

    /**
     * 请求成功 带data数据
     *
     * @param t   泛型
     * @param <T> 泛型
     * @return xml字符串
     */
    public static <T> String success(T t, String msg) {
        ComResponse<T> comResponse = new ComResponse<>();
        comResponse.setHead(new Header(ResponseEnum.SUCCESS.getCode(), msg));
        comResponse.setData(t);
        return JSONUtil.toJsonStr(comResponse);
    }

    /**
     * 请求成功 不带data数据
     *
     * @param <T> 泛型
     * @return xml字符串
     */
    public static <T> String success() {
        ComResponse<T> comResponse = new ComResponse<>();
        comResponse.setHead(new Header(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMsg()));
        comResponse.setData(null);
        return JSONUtil.toJsonStr(comResponse);
    }
    /**
     * 请求成功 不带data数据
     *
     * @param <T> 泛型
     * @return xml字符串
     */
    public static <T> String success(String msg) {
        ComResponse<T> comResponse = new ComResponse<>();
        comResponse.setHead(new Header(ResponseEnum.SUCCESS.getCode(), msg));
        comResponse.setData(null);
        return JSONUtil.toJsonStr(comResponse);
    }

    /**
     * 请求失败
     *
     * @param <T> 泛型
     * @return xml字符串
     */
    public static <T> String fail() {
        ComResponse<T> comResponse = new ComResponse<>();
        comResponse.setHead(new Header(ResponseEnum.FAIL.getCode(), ResponseEnum.FAIL.getMsg()));
        comResponse.setData(null);
        return JSONUtil.toJsonStr(comResponse);
    }

    /**
     * 请求失败
     *
     * @param <T> 泛型
     * @return xml字符串
     */
    public static <T> String fail(String failMsg) {
        ComResponse<T> comResponse = new ComResponse<>();
        comResponse.setHead(new Header(ResponseEnum.FAIL.getCode(), failMsg));
        comResponse.setData(null);
        return JSONUtil.toJsonStr(comResponse);
    }

    /**
     * 请求失败
     * @param <T> 泛型
     * @return xml字符串
     */
    public static <T> String fail(T data, String failMsg) {
        ComResponse<T> comResponse = new ComResponse<>();
        comResponse.setHead(new Header(ResponseEnum.FAIL.getCode(), failMsg));
        comResponse.setData(data);
        return JSONUtil.toJsonStr(comResponse);
    }
}
