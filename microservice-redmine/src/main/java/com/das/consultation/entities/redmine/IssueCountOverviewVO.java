package com.das.consultation.entities.redmine;

/**
 * @Author: LJS
 * @Date: 2022/6/1 10:15
 */
public class IssueCountOverviewVO {

    /**
     * 任务类型ID
     */
    private Integer issueTypeId;

    /**
     * 任务类型名称
     */
    private String issueTypeName;

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
     * 延期率
     */
    private String delayRate;

    public Integer getIssueTypeId() {
        return issueTypeId;
    }

    public void setIssueTypeId(Integer issueTypeId) {
        this.issueTypeId = issueTypeId;
    }

    public String getIssueTypeName() {
        return issueTypeName;
    }

    public void setIssueTypeName(String issueTypeName) {
        this.issueTypeName = issueTypeName;
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

    public String getDelayRate() {
        return delayRate;
    }

    public void setDelayRate(String delayRate) {
        this.delayRate = delayRate;
    }
}
