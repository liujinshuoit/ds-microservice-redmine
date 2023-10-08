package com.das.consultation.entities.redmine;

import com.taskadapter.redmineapi.bean.CustomFieldDefinition;

import java.util.List;

/**
 * @Author: LJS
 * @Date: 2022/6/1 10:15
 */
public class ProjectOverviewVO {

    /**
     * 项目ID
     */
    private Integer projectId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目总任务数
     */
    private Integer allTaskNum;

    /**
     * 未完成任务数
     */
    private Integer inCompleteTaskNum;

    /**
     * 已延期任务数
     */
    private Integer delayTaskNum;

    /**
     * 是否存在风险
     */
    private Integer danger;

    /**
     * 任务统计集合
     */
    private List<IssueCountOverviewVO> issueCountOverviewVOList;

    /**
     * 客户自定义属性集合
     */
    private List<CustomFieldDefinition> customFieldDefinitionList = null;

    /**
     * 任务集合
     */
    private List<IssueOverviewVO> issueOverviewVOList;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getAllTaskNum() {
        return allTaskNum;
    }

    public void setAllTaskNum(Integer allTaskNum) {
        this.allTaskNum = allTaskNum;
    }

    public Integer getInCompleteTaskNum() {
        return inCompleteTaskNum;
    }

    public void setInCompleteTaskNum(Integer inCompleteTaskNum) {
        this.inCompleteTaskNum = inCompleteTaskNum;
    }

    public Integer getDelayTaskNum() {
        return delayTaskNum;
    }

    public void setDelayTaskNum(Integer delayTaskNum) {
        this.delayTaskNum = delayTaskNum;
    }

    public List<IssueOverviewVO> getIssueOverviewVOList() {
        return issueOverviewVOList;
    }

    public void setIssueOverviewVOList(List<IssueOverviewVO> issueOverviewVOList) {
        this.issueOverviewVOList = issueOverviewVOList;
    }

    public Integer getDanger() {
        return danger;
    }

    public void setDanger(Integer danger) {
        this.danger = danger;
    }

    public List<IssueCountOverviewVO> getIssueCountOverviewVOList() {
        return issueCountOverviewVOList;
    }

    public void setIssueCountOverviewVOList(List<IssueCountOverviewVO> issueCountOverviewVOList) {
        this.issueCountOverviewVOList = issueCountOverviewVOList;
    }

    public List<CustomFieldDefinition> getCustomFieldDefinitionList() {
        return customFieldDefinitionList;
    }

    public void setCustomFieldDefinitionList(List<CustomFieldDefinition> customFieldDefinitionList) {
        this.customFieldDefinitionList = customFieldDefinitionList;
    }
}
