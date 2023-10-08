package com.das.consultation.utils;


import com.das.consultation.entities.ComResponse;
import com.das.consultation.entities.Header;
import com.das.consultation.enums.ResponseEnum;

import javax.xml.bind.JAXBException;

/**
 * created by jun on 2020/11/11
 * describe:响应状态
 * version 1.0
 */
public class ResultState {
    /**
     * 请求成功 带data数据
     *
     * @param t   泛型
     * @param <T> 泛型
     * @return xml字符串
     * @throws JAXBException e
     */
    public static <T> String success(T t) throws JAXBException {
        ComResponse<T> comResponse = new ComResponse<>();
        comResponse.setHead(new Header(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMsg()));
        comResponse.setData(t);
        return JsonXmlUtils.beanToXml(comResponse, t.getClass());
    }

    /**
     * 请求成功 带data数据
     *
     * @param t   泛型
     * @param <T> 泛型
     * @return xml字符串
     * @throws JAXBException e
     */
    public static <T> String success(T t, String msg) throws JAXBException {
        ComResponse<T> comResponse = new ComResponse<>();
        comResponse.setHead(new Header(ResponseEnum.SUCCESS.getCode(), msg));
        comResponse.setData(t);
        return JsonXmlUtils.beanToXml(comResponse, t.getClass());
    }

    /**
     * 请求成功 不带data数据
     *
     * @param <T> 泛型
     * @return xml字符串
     * @throws JAXBException e
     */
    public static <T> String success() throws JAXBException {
        ComResponse<T> comResponse = new ComResponse<>();
        comResponse.setHead(new Header(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMsg()));
        comResponse.setData(null);
        return JsonXmlUtils.beanToXml(comResponse);
    }
    /**
     * 请求成功 不带data数据
     *
     * @param <T> 泛型
     * @return xml字符串
     * @throws JAXBException e
     */
    public static <T> String success(String msg) throws JAXBException {
        ComResponse<T> comResponse = new ComResponse<>();
        comResponse.setHead(new Header(ResponseEnum.SUCCESS.getCode(), msg));
        comResponse.setData(null);
        return JsonXmlUtils.beanToXml(comResponse);
    }

    /**
     * 请求失败
     *
     * @param <T> 泛型
     * @return xml字符串
     * @throws JAXBException e
     */
    public static <T> String fail() throws JAXBException {
        ComResponse<T> comResponse = new ComResponse<>();
        comResponse.setHead(new Header(ResponseEnum.FAIL.getCode(), ResponseEnum.FAIL.getMsg()));
        comResponse.setData(null);
        return JsonXmlUtils.beanToXml(comResponse);
    }

    /**
     * 请求失败
     *
     * @param <T> 泛型
     * @return xml字符串
     * @throws JAXBException e
     */
    public static <T> String fail(String failMsg) throws JAXBException {
        ComResponse<T> comResponse = new ComResponse<>();
        comResponse.setHead(new Header(ResponseEnum.FAIL.getCode(), failMsg));
        comResponse.setData(null);
        return JsonXmlUtils.beanToXml(comResponse);
    }

    /**
     * 请求失败
     *
     * @param <T> 泛型
     * @return xml字符串
     * @throws JAXBException e
     */
    public static <T> String fail(T data, String failMsg) throws JAXBException {
        ComResponse<T> comResponse = new ComResponse<>();
        comResponse.setHead(new Header(ResponseEnum.FAIL.getCode(), failMsg));
        comResponse.setData(data);
        return JsonXmlUtils.beanToXml(comResponse, data.getClass());
    }
}
