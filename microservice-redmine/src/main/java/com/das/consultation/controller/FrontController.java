package com.das.consultation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前端页面控制器
 * @Author: LJS
 * @Date: 2022/6/2 15:10
 */
@Controller
public class FrontController {

    /**
     * 首页页面控制器
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 项目页面控制器
     * @return
     */
    @RequestMapping("/project")
    public String project() {
        return "project";
    }

    /**
     * 问题编辑页面控制器
     * @return
     */
    @RequestMapping("/issueEdit")
    public String issueEdit() {
        return "issueEdit";
    }

    /**
     * 问题新增页面控制器
     * @return
     */
    @RequestMapping("/issueAdd")
    public String issueAdd() {
        return "issueAdd";
    }
}
