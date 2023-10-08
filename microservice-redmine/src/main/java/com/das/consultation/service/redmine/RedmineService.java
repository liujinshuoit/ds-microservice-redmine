package com.das.consultation.service.redmine;

import com.das.consultation.entities.redmine.*;
import com.taskadapter.redmineapi.bean.Issue;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: LJS
 * @Date: 2022/6/1 8:52
 */
public interface RedmineService {

    /**
     * 登陆，根据企业微信CODE
     * @param code
     * @return Redmine用户密钥
     */
    String loginByQywxCode(String code, HttpServletRequest request) throws Exception;

    /**
     * 获取所有项目信息
     * @return
     */
    AllProjectDataVO getAllProjectInfo(HttpServletRequest request)  throws Exception;

    /**
     * 获取项目信息，根据项目ID
     * @param projectId 项目ID
     * @return
     */
    ProjectOverviewVO getOneProjectInfoById(Integer projectId ,HttpServletRequest request)  throws Exception;

    /**
     * 获取问题列表，根据项目ID和选择条件
     * @param projectId 项目ID
     * @return
     */
    List<IssueOverviewVO> getIssueListByProjectIdAndSelect(Integer projectId, String status, String typeId, HttpServletRequest request)  throws Exception;

    /**
     * 获取任务，根据任务ID
     * @param issueId 任务ID
     * @return
     */
    IssueOverviewVO getOneIssueInfoById(Integer issueId, HttpServletRequest request)  throws Exception;

    /**
     * 获取用户列表
     * @return
     * @throws Exception
     */
    List<UserOverviewVO> getUserList() throws Exception;

    /**
     * 创建任务
     * @param issue 任务
     * @return
     */
    int createIssue(Issue issue, HttpServletRequest request)  throws Exception;

    /**
     * 更新任务
     * @param issue 任务
     * @return
     */
    void updateIssue(Issue issue, HttpServletRequest request)  throws Exception;

    /**
     * 获取导出数据
     * @return
     */
    List<DataExportVO> getExportData(HttpServletRequest request)  throws Exception;

}
