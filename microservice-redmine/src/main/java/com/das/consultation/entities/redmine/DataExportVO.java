package com.das.consultation.entities.redmine;

import java.util.List;

/**
 * @Author: LJS
 * @Date: 2022/6/1 10:15
 */
public class DataExportVO {

    /**
     * 项目ID
     */
    private Integer projectId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 任务集合
     */
    private List<IssueOverviewVO> issueList;

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

    public List<IssueOverviewVO> getIssueList() {
        return issueList;
    }

    public void setIssueList(List<IssueOverviewVO> issueList) {
        this.issueList = issueList;
    }
}
