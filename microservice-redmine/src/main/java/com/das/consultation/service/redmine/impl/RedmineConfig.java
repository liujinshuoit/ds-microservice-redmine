package com.das.consultation.service.redmine.impl;

import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LJS
 * @Date: 2022/6/13 9:37
 */
@Configuration
public class RedmineConfig {

    /**
     * 任务关闭状态集合
     */
    static final Map<Integer, String> ISSUE_CLOSED_STATUS = new HashMap();

    /**
     * 任务打开状态集合
     */
    static final Map<Integer, String> ISSUE_OPENED_STATUS = new HashMap();

    /**
     * 任务类型集合
     */
    static final Map<Integer, String> ISSUE_TYPE = new HashMap();

    static {
        /* 问题关闭状态集合设置 */
        ISSUE_CLOSED_STATUS.put(29, "(结项)");
        ISSUE_CLOSED_STATUS.put(16, "研发完成");
        ISSUE_CLOSED_STATUS.put(5, "已处理");
        ISSUE_CLOSED_STATUS.put(15, "已发版");
        ISSUE_CLOSED_STATUS.put(6, "已取消");
        ISSUE_CLOSED_STATUS.put(30, "（已完成）");
        ISSUE_CLOSED_STATUS.put(27, "(发版)");
        ISSUE_CLOSED_STATUS.put(28, "(上线)");

        /* 问题打开状态集合设置 */
        ISSUE_OPENED_STATUS.put(18, "(新建)");
        ISSUE_OPENED_STATUS.put(19, "(需求分析)");
        ISSUE_OPENED_STATUS.put(20, "(原型设计)");
        ISSUE_OPENED_STATUS.put(21, "(UI设计)");
        ISSUE_OPENED_STATUS.put(22, "(详细设计)");
        ISSUE_OPENED_STATUS.put(23, "(前端开发)");
        ISSUE_OPENED_STATUS.put(24, "(后端开发)");
        ISSUE_OPENED_STATUS.put(25, "(测试用例)");
        ISSUE_OPENED_STATUS.put(26, "(测试)");
        ISSUE_OPENED_STATUS.put(1, "新建");
        ISSUE_OPENED_STATUS.put(2, "分析中");
        ISSUE_OPENED_STATUS.put(12, "已回退");
        ISSUE_OPENED_STATUS.put(7, "已暂缓");
        ISSUE_OPENED_STATUS.put(14, "设计中");
        ISSUE_OPENED_STATUS.put(3, "研发/处理中");
        ISSUE_OPENED_STATUS.put(17, "申请测试");
        ISSUE_OPENED_STATUS.put(10, "测试中");
        ISSUE_OPENED_STATUS.put(11, "测试回退");
        ISSUE_OPENED_STATUS.put(4, "测试通过");
        ISSUE_OPENED_STATUS.put(9, "交付回退");

        /* 任务类型设置 */
        ISSUE_TYPE.put(1, "外部需求");
        ISSUE_TYPE.put(2, "外部问题");
        ISSUE_TYPE.put(3, "内部需求");
        ISSUE_TYPE.put(4, "内部问题");
    }

}
