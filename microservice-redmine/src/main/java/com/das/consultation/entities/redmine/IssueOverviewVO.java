package com.das.consultation.entities.redmine;

import java.util.List;

/**
 * @Author: LJS
 * @Date: 2022/6/1 10:15
 */
public class IssueOverviewVO {

    /**
     * 任务ID
     */
    private Integer issueId;

    /**
     * 项目名称
     */
    private String issueName;

    /**
     * 任务类型ID
     */
    private Integer issueTypeId;

    /**
     * 任务类型名称
     */
    private String issueTypeName;

    /**
     * 任务描述
     */
    private String issueDescribe;

    /**
     * 任务状态ID
     */
    private Integer issueStatusId;

    /**
     * 任务状态名称
     */
    private String issueStatusName;

    /**
     * 任务优先级ID
     */
    private Integer issuePriorityId;

    /**
     * 任务优先级名称
     */
    private Integer issuePriorityName;

    /**
     * 任务进度
     */
    private Integer issueProgress;

    /**
     * 任务指派人员ID
     */
    private Integer issueAssignsId;

    /**
     * 任务指派人员名称
     */
    private String issueAssignsName;

    /**
     * 任务需求来源
     */
    private String issueDemandSource;

    /**
     * 任务需求/问题类型ID
     */
    private String issueDemandTypeId;

    /**
     * 任务需求/问题类型名称
     */
    private String issueDemandTypeName;

    /**
     * 任务所属模块ID
     */
    private String issueModuleId;

    /**
     * 任务所属模块名称
     */
    private String issueModuleName;

    /**
     * 任务实施负责人ID
     */
    private String issueImplLeaderId;

    /**
     * 任务实施负责人名称
     */
    private String issueImplLeaderName;

    /**
     * 任务开始日期
     */
    private String issueStartDate;

    /**
     * 任务计划完成日期
     */
    private String issuePlanEndDate;

    /**
     * 任务实际完成日期
     */
    private String issueFinishDate;

    /**
     * 任务是否合理延期
     */
    private String isReasonableDelay;

    /**
     * 任务延期/暂缓原因
     */
    private String issueDelayReason;

    /**
     * 用户集合
     */
    private List<UserOverviewVO> userOverviewVOList;

    public Integer getIssueId() {
        return issueId;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

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

    public String getIssueDescribe() {
        return issueDescribe;
    }

    public void setIssueDescribe(String issueDescribe) {
        this.issueDescribe = issueDescribe;
    }

    public Integer getIssueStatusId() {
        return issueStatusId;
    }

    public void setIssueStatusId(Integer issueStatusId) {
        this.issueStatusId = issueStatusId;
    }

    public String getIssueStatusName() {
        return issueStatusName;
    }

    public void setIssueStatusName(String issueStatusName) {
        this.issueStatusName = issueStatusName;
    }

    public Integer getIssuePriorityId() {
        return issuePriorityId;
    }

    public void setIssuePriorityId(Integer issuePriorityId) {
        this.issuePriorityId = issuePriorityId;
    }

    public Integer getIssuePriorityName() {
        return issuePriorityName;
    }

    public void setIssuePriorityName(Integer issuePriorityName) {
        this.issuePriorityName = issuePriorityName;
    }

    public Integer getIssueProgress() {
        return issueProgress;
    }

    public void setIssueProgress(Integer issueProgress) {
        this.issueProgress = issueProgress;
    }

    public Integer getIssueAssignsId() {
        return issueAssignsId;
    }

    public void setIssueAssignsId(Integer issueAssignsId) {
        this.issueAssignsId = issueAssignsId;
    }

    public String getIssueAssignsName() {
        return issueAssignsName;
    }

    public void setIssueAssignsName(String issueAssignsName) {
        this.issueAssignsName = issueAssignsName;
    }

    public String getIssueDemandSource() {
        return issueDemandSource;
    }

    public void setIssueDemandSource(String issueDemandSource) {
        this.issueDemandSource = issueDemandSource;
    }

    public String getIssueDemandTypeId() {
        return issueDemandTypeId;
    }

    public void setIssueDemandTypeId(String issueDemandTypeId) {
        this.issueDemandTypeId = issueDemandTypeId;
    }

    public String getIssueDemandTypeName() {
        return issueDemandTypeName;
    }

    public void setIssueDemandTypeName(String issueDemandTypeName) {
        this.issueDemandTypeName = issueDemandTypeName;
    }

    public String getIssueModuleId() {
        return issueModuleId;
    }

    public void setIssueModuleId(String issueModuleId) {
        this.issueModuleId = issueModuleId;
    }

    public String getIssueModuleName() {
        return issueModuleName;
    }

    public void setIssueModuleName(String issueModuleName) {
        this.issueModuleName = issueModuleName;
    }

    public String getIssueImplLeaderId() {
        return issueImplLeaderId;
    }

    public void setIssueImplLeaderId(String issueImplLeaderId) {
        this.issueImplLeaderId = issueImplLeaderId;
    }

    public String getIssueImplLeaderName() {
        return issueImplLeaderName;
    }

    public void setIssueImplLeaderName(String issueImplLeaderName) {
        this.issueImplLeaderName = issueImplLeaderName;
    }

    public String getIssueStartDate() {
        return issueStartDate;
    }

    public void setIssueStartDate(String issueStartDate) {
        this.issueStartDate = issueStartDate;
    }

    public String getIssuePlanEndDate() {
        return issuePlanEndDate;
    }

    public void setIssuePlanEndDate(String issuePlanEndDate) {
        this.issuePlanEndDate = issuePlanEndDate;
    }

    public String getIssueFinishDate() {
        return issueFinishDate;
    }

    public void setIssueFinishDate(String issueFinishDate) {
        this.issueFinishDate = issueFinishDate;
    }

    public String getIsReasonableDelay() {
        return isReasonableDelay;
    }

    public void setIsReasonableDelay(String isReasonableDelay) {
        this.isReasonableDelay = isReasonableDelay;
    }

    public String getIssueDelayReason() {
        return issueDelayReason;
    }

    public void setIssueDelayReason(String issueDelayReason) {
        this.issueDelayReason = issueDelayReason;
    }

    public List<UserOverviewVO> getUserOverviewVOList() {
        return userOverviewVOList;
    }

    public void setUserOverviewVOList(List<UserOverviewVO> userOverviewVOList) {
        this.userOverviewVOList = userOverviewVOList;
    }
}
