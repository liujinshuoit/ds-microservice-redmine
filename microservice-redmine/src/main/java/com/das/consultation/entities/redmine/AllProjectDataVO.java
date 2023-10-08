package com.das.consultation.entities.redmine;

import java.util.List;

/**
 * @Author: LJS
 * @Date: 2022/6/1 10:15
 */
public class AllProjectDataVO {

    /**
     * 所有项目情况总览
     */
    private AllProjectOverviewVO allProjectOverviewVO;

    /**
     * 项目情况集合
     */
    private List<ProjectOverviewVO> projectOverviewVOList;

    public AllProjectOverviewVO getAllProjectOverviewVO() {
        return allProjectOverviewVO;
    }

    public void setAllProjectOverviewVO(AllProjectOverviewVO allProjectOverviewVO) {
        this.allProjectOverviewVO = allProjectOverviewVO;
    }

    public List<ProjectOverviewVO> getProjectOverviewVOList() {
        return projectOverviewVOList;
    }

    public void setProjectOverviewVOList(List<ProjectOverviewVO> projectOverviewVOList) {
        this.projectOverviewVOList = projectOverviewVOList;
    }
}
