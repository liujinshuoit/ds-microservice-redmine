package com.das.consultation.service.redmine.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.das.consultation.config.ParamConfig;
import com.das.consultation.entities.redmine.*;
import com.das.consultation.service.redmine.RedmineService;
import com.das.consultation.session.HttpSessionTool;
import com.das.consultation.util.QywxUtils;
import com.das.consultation.util.RedmineUtils;
import com.taskadapter.redmineapi.IssueManager;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.bean.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: LJS
 * @Date: 2022/6/1 10:41
 */
@Service
public class RedmineServiceImpl implements RedmineService {

    private Logger logger = LoggerFactory.getLogger(RedmineServiceImpl.class);

    @Autowired
    private ParamConfig paramConfig;

    /**
     * 登陆，根据企业微信CODE
     * @param code
     * @return Redmine用户密钥
     */
    @Override
    public String loginByQywxCode(String code, HttpServletRequest request) throws Exception {
        /* 局部变量 */
        String qywxUserName = null;
        String qywxUserJsonStr = null;
        String qywxUserListJsonStr = null;

        /* code重复申请校验，直接返回session内数据 */
        String currentCode = HttpSessionTool.getValue(request, "code");
        if (code.equals(currentCode)) {
            qywxUserName = HttpSessionTool.getValue(request, "userName");
            logger.info("==========code is repeat, session user: {}", qywxUserName);
            return qywxUserName;
        }

        /* 企业微信用户姓名获取 */
        // 企业微信登陆用户ID获取
        logger.info("==========send getuserinfo url: {}, code: {}, tokenUrl: {}, corpId: {}, appSecret{}", paramConfig.getQywxRedmineUserInfoUrl(), code, paramConfig.getQywxTokenUrl(), paramConfig.getQywxCorpId(), paramConfig.getQywxRedmineAppSecret());
        qywxUserJsonStr = QywxUtils.getLoginUserId(paramConfig.getQywxRedmineUserInfoUrl(), code, paramConfig.getQywxTokenUrl(), paramConfig.getQywxCorpId(), paramConfig.getQywxRedmineAppSecret());
        logger.info("==========recive getuserinfo result: {}", qywxUserJsonStr);
        // 企业微信用户列表获取
        logger.info("==========send getUserList url: {}, tokenUrl: {}, corpId: {}, appSecret{}", paramConfig.getQywxUserListUrl(), paramConfig.getQywxTokenUrl(), paramConfig.getQywxCorpId(), paramConfig.getQywxUserListAppSecret());
        qywxUserListJsonStr = QywxUtils.getUserList(paramConfig.getQywxUserListUrl(), paramConfig.getQywxTokenUrl(), paramConfig.getQywxCorpId(), paramConfig.getQywxUserListAppSecret());
        logger.info("==========recive getUserList result: {}", qywxUserListJsonStr);
        // 企业微信用户姓名获取
        if (StringUtils.isEmpty(qywxUserJsonStr)) {
            logger.error("==========recive getuserinfo result is null");
            return qywxUserName;
        }
        JSONObject jsonObject1 = JSON.parseObject(qywxUserJsonStr);
        String userId = jsonObject1.getString("UserId");
        if (StringUtils.isEmpty(userId)) {
            logger.error("==========userId is null");
            return qywxUserName;
        }
        if (StringUtils.isEmpty(qywxUserListJsonStr)) {
            logger.error("==========recive getUserList result is null");
            return qywxUserName;
        }
        JSONObject jsonObject2 = JSON.parseObject(qywxUserListJsonStr);
        JSONArray jsonArray = jsonObject2.getJSONArray("userlist");
        if (null == jsonArray) {
            logger.error("==========userList jsonArray is null or empty");
            return qywxUserName;
        }
        for (int i=0; i<jsonArray.size(); i++) {
            JSONObject currentJsonObject = JSONObject.parseObject(jsonArray.get(i).toString());
            String currentUserId = currentJsonObject.getString("userid");
            if (StringUtils.isEmpty(currentUserId)) {
                logger.error("==========userList currentUserId is empty");
                return qywxUserName;
            }
            if (userId.equals(currentUserId)) {
                qywxUserName = currentJsonObject.getString("name");
            }
        }
        // 测试
//        qywxUserName = "admin";
        /* Redmine对象获取 */
        logger.info("==========send getRedmineManagerByQywxUserName url: {}, qywxUserName: {}, apiKeyJson: {}", paramConfig.getRedmineUrl(), qywxUserName, paramConfig.getRedmineApiKeyJson());
        RedmineManager redmineManager = RedmineUtils.getRedmineManagerByQywxUserName(paramConfig.getRedmineUrl(), qywxUserName, paramConfig.getRedmineApiKeyJson());
        logger.info("==========recive getRedmineManagerByQywxUserName success");
        HttpSessionTool.saveSession(request, "redmine", redmineManager);
        HttpSessionTool.saveSession(request, "userName", qywxUserName);
        HttpSessionTool.saveSession(request, "code", code);
        return qywxUserName;
    }

    /**
     * 根据企业微信用户ID获取所有项目情况数据
     * @return
     */
    @Override
    public AllProjectDataVO getAllProjectInfo(HttpServletRequest request) throws Exception{
        /* 局部变量 */
        AllProjectDataVO allProjectDataVO = new AllProjectDataVO();
        AllProjectOverviewVO allProjectOverviewVO = new AllProjectOverviewVO();
        List<ProjectOverviewVO> projectOverviewVOList = new ArrayList<>();
        RedmineManager redmineManager = null;
        // 项目总数
        Integer allProjectNum = 0;
        // 平稳项目数
        Integer smoothProjectNum;
        // 风险项目数
        Integer dangerProjectNum = 0;
        // 挂起项目数
        Integer stopProjectNum;
        // 总任务数
        Integer allTaskNum = 0;
        // 完成任务数
        Integer inCompleteTaskNum = 0;
        // 已延期任务数
        Integer delayTaskNum = 0;
        // 停止任务数
        Integer stopTaskNum;

        /* Redmine数据获取 */
        redmineManager = HttpSessionTool.getValue(request, "redmine");
        // 项目列表获取
        List<Project> projectList = RedmineUtils.getProjects(redmineManager);
        for (Project project : projectList) {
            ProjectOverviewVO projectOverviewVO = new ProjectOverviewVO();
            Integer projectId = project.getId();
            String projectName = project.getName();
            Integer projectAllTaskNum;
            Integer projectInCompleteTaskNum = 0;
            Integer projectDelayTaskNum = 0;

            // 二级项目不统计
            if (null != project.getParentId()) {
                continue;
            }

            List<Issue> issueList = RedmineUtils.getOpenedIssuesByProjectId(redmineManager, projectId);
            projectAllTaskNum = RedmineUtils.getIssuesNumByProjectId(redmineManager, projectId, null);
            if (null != issueList && !issueList.isEmpty()) {
                projectInCompleteTaskNum = issueList.size();
                for (Issue issue : issueList) {
                    Date currentDate = new Date();
                    Date dueDate = issue.getDueDate();
                    if (null != dueDate) {
                        if (currentDate.compareTo(issue.getDueDate()) > 0) {
                            projectDelayTaskNum++;
                        }
                    }
                }
            }

            allTaskNum += projectAllTaskNum;
            inCompleteTaskNum += projectInCompleteTaskNum;
            delayTaskNum += projectDelayTaskNum;
            // 风险项目计算
            if (projectAllTaskNum > 0) {
                if ((float)projectDelayTaskNum/projectAllTaskNum > 0.1) {
                    dangerProjectNum++;
                    projectOverviewVO.setDanger(1);
                }
            }

            projectOverviewVO.setProjectId(projectId);
            projectOverviewVO.setProjectName(projectName);
            projectOverviewVO.setAllTaskNum(projectAllTaskNum);
            projectOverviewVO.setInCompleteTaskNum(projectInCompleteTaskNum);
            projectOverviewVO.setDelayTaskNum(projectDelayTaskNum);
            projectOverviewVOList.add(projectOverviewVO);
        }

        allProjectNum = projectList.size();
        smoothProjectNum = allProjectNum - dangerProjectNum;

        /* 数据组装并返回 */
        allProjectOverviewVO.setAllProjectNum(allProjectNum);
        allProjectOverviewVO.setSmoothProjectNum(smoothProjectNum);
        allProjectOverviewVO.setDangerProjectNum(dangerProjectNum);
        allProjectOverviewVO.setAllTaskNum(allTaskNum);
        allProjectOverviewVO.setInCompleteTaskNum(inCompleteTaskNum);
        allProjectOverviewVO.setDelayTaskNum(delayTaskNum);
        allProjectDataVO.setAllProjectOverviewVO(allProjectOverviewVO);
        allProjectDataVO.setProjectOverviewVOList(projectOverviewVOList);
        return allProjectDataVO;
    }

    /**
     * 获取项目信息，根据项目ID
     * @param projectId 项目ID
     * @return
     */
    @Override
    public ProjectOverviewVO getOneProjectInfoById(Integer projectId, HttpServletRequest request) throws Exception {
        /* 局部变量 */
        ProjectOverviewVO projectOverviewVO = new ProjectOverviewVO();
        List<IssueOverviewVO> issueOverviewVOList = new ArrayList<>();
        List<CustomFieldDefinition> customFieldDefinitionList = null;
        // 任务统计集合
        List<IssueCountOverviewVO> issueCountOverviewVOList = new ArrayList<>();
        // 任务统计数据
        Map<Integer, List<Issue>> issueCountMap = new HashMap<>();
        String projectName;
        Integer projectAllTaskNum;
        Integer projectInCompleteTaskNum = 0;
        Integer projectDelayTaskNum = 0;

        /* Redmine数据获取 */
        RedmineManager redmineManager = HttpSessionTool.getValue(request, "redmine");
        Project project = RedmineUtils.getProjectById(redmineManager, projectId);
        projectName = project.getName();

        // 所有任务
//        List<Issue> issueList = RedmineUtils.getIssuesByProjectId(redmineManager, projectId);
        // 未关闭任务
        List<Issue> issueList = RedmineUtils.getOpenedIssuesByProjectId(redmineManager, projectId);
        if (issueList.isEmpty()) {
            logger.error("==========service getOneProjectInfoById issueList is empty");
        }
        // 项目所有任务计算
        projectAllTaskNum = RedmineUtils.getIssuesNumByProjectId(redmineManager, projectId, null);
        // 项目打开任务计算
        projectInCompleteTaskNum = issueList.size();
        for (Issue issue : issueList) {
            Integer issueId = issue.getId();
            Integer statusId = issue.getStatusId();
            Date currentDate = new Date();
            Date dueDate = issue.getDueDate();
            Tracker tracker = issue.getTracker();
            // 项目打开任务计算
//            if (RedmineConfig.ISSUE_OPENED_STATUS.containsKey(statusId)) {
//                projectInCompleteTaskNum++;
                // 项目延迟任务计算
                if (null != dueDate) {
                    if (currentDate.compareTo(issue.getDueDate()) > 0) {
                        projectDelayTaskNum++;
                    }
                }
//            }
            // 项目任务集合组装
            IssueOverviewVO issueOverviewVO = new IssueOverviewVO();
            issueOverviewVO.setIssueId(issue.getId());
            issueOverviewVO.setIssueName(issue.getSubject());
            issueOverviewVO.setIssueStatusName(issue.getStatusName());
            issueOverviewVO.setIssueTypeName(issue.getTracker().getName());
            issueOverviewVO.setIssueAssignsName(issue.getAssigneeName());
            issueOverviewVOList.add(issueOverviewVO);
            // 项目任务统计数据组装
            if (null != tracker) {
                Integer trackerId = tracker.getId();
                if (issueCountMap.containsKey(trackerId)) {
                    List<Issue> issueCountList = issueCountMap.get(trackerId);
                    issueCountList.add(issue);
                } else {
                    List<Issue> issueCountList = new ArrayList<>();
                    issueCountList.add(issue);
                    issueCountMap.put(trackerId, issueCountList);
                }
            }
        }

        /* 项目任务统计 */
        Set<Map.Entry<Integer, String>> issueTypeEntrySet = RedmineConfig.ISSUE_TYPE.entrySet();
        for (Map.Entry<Integer, String> issueTypeEntry : issueTypeEntrySet) {
            Integer issueTypeId = issueTypeEntry.getKey();
            String issueTypeValue = issueTypeEntry.getValue();

            IssueCountOverviewVO issueCountOverviewVO = new IssueCountOverviewVO();
            Integer countAllTaskNum = 0;
            Integer countInCompleteTaskNum = 0;
            Integer countDelayTaskNum = 0;
            String countDelayRate = "0%";

            // 该类型总任务数
            countAllTaskNum = RedmineUtils.getIssuesNumByProjectId(redmineManager, projectId, issueTypeId);
            if (!issueCountMap.isEmpty()) {
                Set<Map.Entry<Integer, List<Issue>>> isueEntrySet = issueCountMap.entrySet();
                for (Map.Entry<Integer, List<Issue>> issueEntry : isueEntrySet) {
                    Integer trackerId = issueEntry.getKey();
                    List<Issue> currentIssueList = issueEntry.getValue();
                    if (issueTypeId.equals(trackerId)) {
                        for (Issue issue : currentIssueList) {
                            Integer issueId = issue.getId();
                            Integer statusId = issue.getStatusId();
                            Date currentDate = new Date();
                            Date dueDate = issue.getDueDate();
                            // 打开任务计算
                            if (RedmineConfig.ISSUE_OPENED_STATUS.containsKey(statusId)) {
                                countInCompleteTaskNum++;
                                // 延迟任务计算
                                if (null != dueDate) {
                                    if (currentDate.compareTo(issue.getDueDate()) > 0) {
                                        countDelayTaskNum++;
                                    }
                                }
                            }
                        }
                        // 延迟率计算
                        countDelayRate = String.format("%.0f", ((float)countDelayTaskNum / countAllTaskNum)*100) + "%";
                    }
                }
            }
            // 任务统计集合组装
            issueCountOverviewVO.setIssueTypeId(issueTypeId);
            issueCountOverviewVO.setIssueTypeName(issueTypeValue);
            issueCountOverviewVO.setAllTaskNum(countAllTaskNum);
            issueCountOverviewVO.setInCompleteTaskNum(countInCompleteTaskNum);
            issueCountOverviewVO.setDelayTaskNum(countDelayTaskNum);
            issueCountOverviewVO.setDelayRate(countDelayRate);
            issueCountOverviewVOList.add(issueCountOverviewVO);
        }

        /* 数据组装并返回 */
        projectOverviewVO.setProjectName(projectName);
        projectOverviewVO.setAllTaskNum(projectAllTaskNum);
        projectOverviewVO.setInCompleteTaskNum(projectInCompleteTaskNum);
        projectOverviewVO.setDelayTaskNum(projectDelayTaskNum);
        projectOverviewVO.setIssueCountOverviewVOList(issueCountOverviewVOList);
        projectOverviewVO.setIssueOverviewVOList(issueOverviewVOList);
        projectOverviewVO.setCustomFieldDefinitionList(customFieldDefinitionList);
        return projectOverviewVO;
    }

    /**
     * 获取问题列表，根据项目ID和选择条件
     * @param projectId 项目ID
     * @return
     */
    @Override
    public List<IssueOverviewVO> getIssueListByProjectIdAndSelect(Integer projectId, String status, String typeId,  HttpServletRequest request) throws Exception {
        /* 局部变量 */
        List<IssueOverviewVO> issueOverviewVOList = new ArrayList<>();
        List<Issue> issueList = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        /* Redmine数据获取 */
        RedmineManager redmineManager = HttpSessionTool.getValue(request, "redmine");
        if ("*".equals(status) || "c".equals(status)) {
            issueList = RedmineUtils.getIssuesByProjectId(redmineManager, projectId);
        } else {
            issueList = RedmineUtils.getOpenedIssuesByProjectId(redmineManager, projectId);
        }
        if (null == issueList || issueList.isEmpty()) {
            logger.error("==========service getIssueListByProjectIdAndSelect issueList is empty");
        }
        for (Issue issue : issueList) {
            Boolean isStatusValid = false;
            Boolean isTypeValid = false;
            Integer issueId = issue.getId();
            Integer statusId = issue.getStatusId();
            Date currentDate = new Date();
            Date dueDate = issue.getDueDate();
            Tracker tracker = issue.getTracker();

            // 任务完结状态筛选
            switch (status) {
                case "*" :
                    isStatusValid = true;
                    break;
                case "o" :
                    if (RedmineConfig.ISSUE_OPENED_STATUS.containsKey(statusId)) {
                        isStatusValid = true;
                    }
                    break;
                case "d" :
                    if (RedmineConfig.ISSUE_OPENED_STATUS.containsKey(statusId)) {
                        // 项目延迟任务筛选
                        if ("d".equals(status)) {
                            if (null != dueDate) {
                                if (currentDate.compareTo(issue.getDueDate()) > 0) {
                                    isStatusValid = true;
                                }
                            }
                        }
                    }
                    break;
                case "c" :
                    if (RedmineConfig.ISSUE_CLOSED_STATUS.containsKey(statusId)) {
                        isStatusValid = true;
                    }
                    break;
                default:
                    isStatusValid = true;
                    break;

            }

            // 任务类型筛选
            if (null != tracker) {
                Integer trackerId = tracker.getId();
                String trackerIdStr = String.valueOf(trackerId);
                if ("*".equals(typeId) || trackerIdStr.equals(typeId)) {
                    isTypeValid = true;
                } else {
                    isTypeValid = false;
                }
            }

            // 任务集合组装
            if (isStatusValid && isTypeValid) {
                IssueOverviewVO issueOverviewVO = new IssueOverviewVO();
                issueOverviewVO.setIssueId(issue.getId());
                issueOverviewVO.setIssueName(issue.getSubject());
                issueOverviewVO.setIssueStatusName(issue.getStatusName());
                issueOverviewVO.setIssueTypeName(issue.getTracker().getName());
                issueOverviewVO.setIssueDescribe(issue.getDescription());
                issueOverviewVO.setIssueAssignsName(issue.getAssigneeName());
                issueOverviewVO.setIssueProgress(issue.getDoneRatio());
                issueOverviewVO.setIssuePriorityId(issue.getPriorityId());
                issueOverviewVO.setIssueStartDate(format.format(issue.getStartDate()));
                issueOverviewVO.setIssuePlanEndDate(format.format(issue.getDueDate()));
                CustomField delayReasonCustomField = issue.getCustomFieldById(25);
                if (null != delayReasonCustomField) {
                    issueOverviewVO.setIssueDelayReason(delayReasonCustomField.getValue());
                }
                CustomField demandSourceCustomField = issue.getCustomFieldById(1);
                if (null != demandSourceCustomField) {
                    issueOverviewVO.setIssueDemandSource(demandSourceCustomField.getValue());
                }
                CustomField demandTypeCustomField = issue.getCustomFieldById(22);
                if (null != demandTypeCustomField) {
                    issueOverviewVO.setIssueDemandTypeName(demandTypeCustomField.getValue());
                }
                CustomField moduleCustomField = issue.getCustomFieldById(3);
                if (null != moduleCustomField) {
                    issueOverviewVO.setIssueModuleName(moduleCustomField.getValue());
                }

                issueOverviewVOList.add(issueOverviewVO);
            }
        }

        /* 数据组装并返回 */
        return issueOverviewVOList;
    }

    /**
     * 获取任务，根据任务ID
     * @param issueId 任务ID
     * @return
     */
    @Override
    public IssueOverviewVO getOneIssueInfoById(Integer issueId, HttpServletRequest request) throws Exception {
        /* 局部变量 */
        IssueOverviewVO issueOverviewVO = new IssueOverviewVO();
        // 任务名称
        String issueName;
        // 任务类型ID
        Integer issueTypeId = 0;
        // 任务类型名称
        String issueTypeName = "";
        // 任务描述
        String issueDescribe;
        // 任务状态ID
        Integer issueStatusId;
        // 任务状态名称
        String issueStatusName;
        // 任务优先级ID
        Integer issuePriorityId;
        // 任务优先级名称
        Integer issuePriorityName;
        // 任务进度
        Integer issueProgress;
        // 任务指派人员ID
        Integer issueAssignsId;
        // 任务指派人员名称
        String issueAssignsName;
        // 任务需求来源
        String issueDemandSource = null;
        // 任务需求/问题类型ID
        String issueDemandTypeId = null;
        // 任务需求/问题类型名称
        String issueDemandTypeName = null;
        // 任务所属模块ID
        String issueModuleId = null;
        // 任务所属模块名称
        String issueModuleName = null;
        // 任务实施负责人ID
        String issueImplLeaderId = null;
        // 任务实施负责人名称
        String issueImplLeaderName = null;
        // 任务开始日期
        Date issueStartDate;
        // 任务计划完成日期
        Date issuePlanEndDate;
        // 任务实际完成日期
        String issueFinishDate = null;
        // 任务是否合理延期
        String isReasonableDelay = null;
        // 任务延期/暂缓原因
         String issueDelayReason = null;
        // 用户集合
        List<UserOverviewVO> userOverviewVOList = new ArrayList<>();


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        /* Redmine数据获取 */
        RedmineManager redmineManager = HttpSessionTool.getValue(request, "redmine");
        IssueManager issueManager = redmineManager.getIssueManager();
        Issue issue = issueManager.getIssueById(issueId);

        /* 数据获取 */
        issueName = issue.getSubject();
        Tracker tracker = issue.getTracker();
        if (null != tracker) {
            issueTypeId = tracker.getId();
            issueTypeName = tracker.getName();
        }
        issueDescribe = issue.getDescription();
        issueStatusId = issue.getStatusId();
        issueStatusName = issue.getStatusName();
        issuePriorityId = issue.getPriorityId();
        issueProgress = issue.getDoneRatio();
        issueAssignsId = issue.getAssigneeId();
        issueAssignsName = issue.getAssigneeName();
        CustomField demandSourceCustomField = issue.getCustomFieldById(1);
        if (null != demandSourceCustomField) {
            issueDemandSource = demandSourceCustomField.getValue();
        }
        CustomField demandTypeCustomField = issue.getCustomFieldById(22);
        if (null != demandTypeCustomField) {
            issueDemandTypeId = demandTypeCustomField.getValue();
            issueDemandTypeName = demandTypeCustomField.getValue();
        }
        CustomField moduleCustomField = issue.getCustomFieldById(3);
        if (null != moduleCustomField) {
            issueModuleId = moduleCustomField.getValue();
            issueModuleName = moduleCustomField.getValue();
        }
        CustomField implLeaderCustomField = issue.getCustomFieldById(4);
        if (null != implLeaderCustomField) {
            issueImplLeaderId = implLeaderCustomField.getValue();
            issueImplLeaderName = implLeaderCustomField.getValue();
        }
        issueStartDate = issue.getStartDate();
        issuePlanEndDate = issue.getDueDate();
        CustomField finishDateCustomField = issue.getCustomFieldById(28);
        if (null != finishDateCustomField) {
            issueFinishDate = finishDateCustomField.getValue();
        }
        CustomField reasonableDelayCustomField = issue.getCustomFieldById(24);
        if (null != reasonableDelayCustomField) {
            isReasonableDelay = reasonableDelayCustomField.getValue();
        }
        CustomField delayReasonCustomField = issue.getCustomFieldById(25);
        if (null != delayReasonCustomField) {
            issueDelayReason = delayReasonCustomField.getValue();
        }
        // 用户列表获取
        userOverviewVOList = this.getUserList();
        if (userOverviewVOList.isEmpty()) {

        }

        /* 数据组装 */
        issueOverviewVO.setIssueName(issueName);
        issueOverviewVO.setIssueTypeId(issueTypeId);
        issueOverviewVO.setIssueTypeName(issueTypeName);
        issueOverviewVO.setIssueDescribe(issueDescribe);
        issueOverviewVO.setIssueStatusId(issueStatusId);
        issueOverviewVO.setIssueStatusName(issueStatusName);
        issueOverviewVO.setIssuePriorityId(issuePriorityId);
        issueOverviewVO.setIssueProgress(issueProgress);
        issueOverviewVO.setIssueAssignsId(issueAssignsId);
        issueOverviewVO.setIssueAssignsName(issueAssignsName);

        issueOverviewVO.setIssueDemandSource(issueDemandSource);
        issueOverviewVO.setIssueDemandTypeId(issueDemandTypeId);
        issueOverviewVO.setIssueDemandTypeName(issueDemandTypeName);
        issueOverviewVO.setIssueModuleId(issueModuleId);
        issueOverviewVO.setIssueModuleName(issueModuleName);
        issueOverviewVO.setIssueImplLeaderId(issueImplLeaderId);
        issueOverviewVO.setIssueImplLeaderName(issueImplLeaderName);
        issueOverviewVO.setIssueFinishDate(issueFinishDate);
        issueOverviewVO.setIsReasonableDelay(isReasonableDelay);
        issueOverviewVO.setIssueDelayReason(issueDelayReason);

        if (null != issueStartDate) {
            issueOverviewVO.setIssueStartDate(format.format(issueStartDate));
        }
        if (null != issuePlanEndDate) {
            issueOverviewVO.setIssuePlanEndDate(format.format(issuePlanEndDate));
        }
        issueOverviewVO.setUserOverviewVOList(userOverviewVOList);

        /* 数据返回 */
        return issueOverviewVO;
    }

    /**
     * 获取用户列表
     * @return
     */
    @Override
    public List<UserOverviewVO> getUserList() throws Exception {
        /* 局部变量 */
        List<UserOverviewVO> userOverviewVOList = new ArrayList<>();
        // 用户集合
        List<User> userList = null;


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        /* Redmine数据获取 */
        userList = RedmineUtils.getUsers(paramConfig.getRedmineUrl(), paramConfig.getRedmineApiKeyJson());
        if (null == userList) {
            logger.error("==========service userList is null");
            return userOverviewVOList;
        }

        /* 数据组装 */
        for (User user : userList) {
            Integer currentUserId = user.getId();
            String currentUserName = user.getFullName();
            UserOverviewVO userOverviewVO = new UserOverviewVO();
            userOverviewVO.setId(currentUserId);
            userOverviewVO.setName(currentUserName);
            userOverviewVOList.add(userOverviewVO);
        }

        /* 数据返回 */
        return userOverviewVOList;
    }

    /**
     * 创建任务
     * @param issue 任务
     * @return
     */
    @Override
    public int createIssue(Issue issue, HttpServletRequest request) throws Exception {
        /* 局部变量 */
        RedmineManager redmineManager = HttpSessionTool.getValue(request, "redmine");
        IssueManager issueManager = redmineManager.getIssueManager();
        Issue createdIssue = issueManager.createIssue(issue);
        if (null == createdIssue) {
            return 0;
        }
        return 1;
    }

    /**
     * 更新任务
     * @param issue 任务
     * @return
     */
    @Override
    public void updateIssue(Issue issue, HttpServletRequest request) throws Exception {
        /* 局部变量 */
        RedmineManager redmineManager = HttpSessionTool.getValue(request, "redmine");
        IssueManager issueManager = redmineManager.getIssueManager();
        try {
            issueManager.update(issue);
        } catch (IllegalArgumentException e) {
            logger.info("Redmine server response error");
        }
    }

    /**
     * 获取导出数据
     * @return
     * @throws Exception
     */
    @Override
    public List<DataExportVO> getExportData(HttpServletRequest request) throws Exception {
        /* 局部变量 */
        List<DataExportVO> dataExportVOList = new ArrayList<>();

        /* Redmine数据获取 */
        RedmineManager redmineManager = HttpSessionTool.getValue(request, "redmine");
        // 项目列表获取
        List<Project> projectList = RedmineUtils.getProjects(redmineManager);

        for (Project p : projectList) {
            Integer projectId = p.getId();
            String projectName = p.getName();
            // 二级项目不统计
            if (null != p.getParentId()) {
                continue;
            }
            DataExportVO dataExportVO = new DataExportVO();
            List<IssueOverviewVO> issueOverviewVOList = this.getIssueListByProjectIdAndSelect(projectId, "d", "*",  request);
            dataExportVO.setProjectId(projectId);
            dataExportVO.setProjectName(projectName);
            dataExportVO.setIssueList(issueOverviewVOList);
            dataExportVOList.add(dataExportVO);
        }

        /* 服务返回 */
        return dataExportVOList;
    }
}
