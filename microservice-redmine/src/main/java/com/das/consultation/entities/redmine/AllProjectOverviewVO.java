package com.das.consultation.entities.redmine;

/**
 * @Author: LJS
 * @Date: 2022/6/1 10:15
 */
public class AllProjectOverviewVO {

    /**
     * 项目总数
     */
    private Integer allProjectNum;

    /**
     * 平稳项目数
     */
    private Integer smoothProjectNum;

    /**
     * 风险项目数
     */
    private Integer dangerProjectNum;

    /**
     * 挂起项目数
     */
    private Integer stopProjectNum;

    /**
     * 总任务数
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
     * 停止任务数
     */
    private Integer stopTaskNum;

    public Integer getAllProjectNum() {
        return allProjectNum;
    }

    public void setAllProjectNum(Integer allProjectNum) {
        this.allProjectNum = allProjectNum;
    }

    public Integer getSmoothProjectNum() {
        return smoothProjectNum;
    }

    public void setSmoothProjectNum(Integer smoothProjectNum) {
        this.smoothProjectNum = smoothProjectNum;
    }

    public Integer getDangerProjectNum() {
        return dangerProjectNum;
    }

    public void setDangerProjectNum(Integer dangerProjectNum) {
        this.dangerProjectNum = dangerProjectNum;
    }

    public Integer getStopProjectNum() {
        return stopProjectNum;
    }

    public void setStopProjectNum(Integer stopProjectNum) {
        this.stopProjectNum = stopProjectNum;
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

    public Integer getStopTaskNum() {
        return stopTaskNum;
    }

    public void setStopTaskNum(Integer stopTaskNum) {
        this.stopTaskNum = stopTaskNum;
    }
}
