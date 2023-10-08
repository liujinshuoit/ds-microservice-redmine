package com.das.consultation.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taskadapter.redmineapi.*;
import com.taskadapter.redmineapi.bean.*;
import com.taskadapter.redmineapi.internal.ResultsWrapper;

import java.util.*;

/**
 * @Author: LJS
 * @Date: 2022/4/12 10:37
 */
public class RedmineUtils {

    /**
     * Redmine服务地址
     */
//    private static String url = "http://139.159.200.197/redmine";



    public static RedmineManager getRedmineManagerByQywxUserName(String redmineUrl, String qywxUserName, String apiKeyJsonStr) throws RedmineException{
        /* 局部变量 */
        // redmine管理对象
        RedmineManager redmineManager;
        String apiKey = null;

        /* 根据企业微信用户姓名获取Redmine用户apiKey */
        JSONArray jsonArray = JSONArray.parseArray(apiKeyJsonStr);
        if (null != jsonArray && !jsonArray.isEmpty()) {
            for (int i=0; i<jsonArray.size(); i++) {
                JSONObject currentJsonObject = JSONObject.parseObject(jsonArray.get(i).toString());
                String currentUserName = currentJsonObject.getString("name");
                if (qywxUserName.equals(currentUserName)) {
                    apiKey = currentJsonObject.getString("apiKey");
                }
            }
        }

        // 测试
//        apiKey = "efca8405bab16ad4061cbb2ed8b14ddf116ae925"; //admin
//        apiKey = "59c3fc6594db4bff0ab0f52d6f604f0dcfbd0210"; //李士玉

        /* 对象返回 */
        redmineManager = RedmineManagerFactory.createWithApiKey(redmineUrl, apiKey);
        return redmineManager;
    }

    /**
     * 获取项目数据
     * @return
     * @throws RedmineException
     */
    public static List<User> getUsers(String redmineUrl, String apiKeyJsonStr) throws RedmineException{
        RedmineManager redmineManager = getRedmineManagerByQywxUserName(redmineUrl, "admin", apiKeyJsonStr);
        UserManager userManager = redmineManager.getUserManager();

        /* 项目数据返回 */
        return userManager.getUsers();
    }

    /**
     * 获取项目数据
     * @param redmineManager
     * @return
     * @throws RedmineException
     */
    public static List<Project> getProjects(RedmineManager redmineManager) throws RedmineException{
        /* 局部变量 */
        // redmine项目管理对象
        ProjectManager projectManager;

        projectManager = redmineManager.getProjectManager();

        /* 项目数据返回 */
        return projectManager.getProjects();
    }

    /**
     * 获取项目数据，根据项目ID
     * @param redmineManager
     * @param redmineManager
     * @return
     * @throws RedmineException
     */
    public static Project getProjectById(RedmineManager redmineManager, Integer projectId) throws RedmineException{
        /* 局部变量 */
        // redmine项目管理对象
        ProjectManager projectManager;

        projectManager = redmineManager.getProjectManager();

        /* 项目数据返回 */
        return projectManager.getProjectById(projectId);
    }

    /**
     * 获取总任务数量
     * @param redmineManager
     * @return
     * @throws RedmineException
     */
    public static int getIssuesNum(RedmineManager redmineManager) throws RedmineException{
        /* 局部变量 */
        ResultsWrapper<Issue> issueResultsWrapper = null;

        // redmine项目管理对象
        IssueManager issueManager = redmineManager.getIssueManager();
        final Map<String, String> params = new HashMap<>();
        params.put("status_id", "*");
        // 每页数据条数
        params.put("limit", "1");
        issueResultsWrapper = issueManager.getIssues(params);

        /* 项目数据返回 */
        return issueResultsWrapper.getTotalFoundOnServer();
    }

    /**
     * 获取全部任务集合，根据项目ID
     * @param redmineManager
     * @return
     * @throws RedmineException
     */
    public static List<Issue> getIssuesByProjectId(RedmineManager redmineManager, Integer projectId) throws RedmineException{
        /* 局部变量 */
        ResultsWrapper<Issue> issueResultsWrapper = null;
        List<Issue> issueList = new ArrayList<>();

        // redmine项目管理对象
        IssueManager issueManager = redmineManager.getIssueManager();
        final Map<String, String> params = new HashMap<>();
        params.put("project_id", String.valueOf(projectId));
        params.put("status_id", "*");
        // 每页数据条数
        params.put("limit", "100");
        issueResultsWrapper = issueManager.getIssues(params);
        int totalNum = issueResultsWrapper.getTotalFoundOnServer();
        int limitNum = issueResultsWrapper.getLimitOnServer();
        int page = totalNum / limitNum + 1;
        issueList.addAll(issueResultsWrapper.getResults());
        for (int i=2; i<page+1; i++) {
            params.put("page", String.valueOf(i));
            issueResultsWrapper = issueManager.getIssues(params);
            issueList.addAll(issueResultsWrapper.getResults());
        }

        /* 项目数据返回 */
        return issueList;
    }

    /**
     * 获取任务数量，根据项目ID
     * @param redmineManager
     * @return
     * @throws RedmineException
     */
    public static int getIssuesNumByProjectId(RedmineManager redmineManager, Integer projectId, Integer trackerId) throws RedmineException{
        /* 局部变量 */
        ResultsWrapper<Issue> issueResultsWrapper = null;

        // redmine项目管理对象
        IssueManager issueManager = redmineManager.getIssueManager();
        final Map<String, String> params = new HashMap<>();
        if (null != projectId) {
            params.put("project_id", String.valueOf(projectId));
        }
        // 任务跟踪
        if (null != trackerId) {
            params.put("tracker_id", String.valueOf(trackerId));
        }
        // 任务状态
        params.put("status_id", "*");
        // 每页数据条数
        params.put("limit", "1");
        issueResultsWrapper = issueManager.getIssues(params);

        /* 项目数据返回 */
        return issueResultsWrapper.getTotalFoundOnServer();
    }

    /**
     * 获取打开任务集合，根据项目ID
     * @param redmineManager
     * @return
     * @throws RedmineException
     */
    public static List<Issue> getOpenedIssuesByProjectId(RedmineManager redmineManager, Integer projectId) throws RedmineException{
        /* 数据返回 */
        return redmineManager.getIssueManager().getIssues(String.valueOf(projectId), null);
    }

//    /**
//     * 测试用
//     * @param args
//     * @throws Exception
//     */
//    public static void main(String[] args) throws Exception {
//
//
//        RedmineManager redmineManager;
//        /* Redmine连接信息 */
//        String url = "http://139.159.200.197/redmine";
//        String apiKey = "efca8405bab16ad4061cbb2ed8b14ddf116ae925";
//        redmineManager = RedmineManagerFactory.createWithApiKey(url, apiKey);
////        redmineManager.setObjectsPerPage(1000);
//        System.out.println(redmineManager);
//
//
//        /* 用户 */
//        UserManager userManager = redmineManager.getUserManager();
//        User currentUser = userManager.getCurrentUser();
//        Collection<CustomField> customFields = currentUser.getCustomFields();
//        List<User> userList = userManager.getUsers();
//        for (User u : userList) {
//            String uApiKey = u.getApiKey();
//            String uName = u.getFullName();
//            System.out.println(uName + ":" + uApiKey);
//        }
//
//        /* 项目 */
//        ProjectManager projectManager = redmineManager.getProjectManager();
//        List<Project> projectList = projectManager.getProjects();
//        for (Project project : projectList) {
//            System.out.println(project.getName());
//        }
//        Project project = projectManager.getProjectById(5);
//
//
//
//        /* 配置 */
//        CustomFieldManager customFieldManager = redmineManager.getCustomFieldManager();
//        List<CustomFieldDefinition> customFieldDefinitionList = customFieldManager.getCustomFieldDefinitions();
//
//        /* 问题 */
//        IssueManager issueManager = redmineManager.getIssueManager();
//        List<Issue> issueListTest = issueManager.getIssues("2", null);
//        Issue issue1 = issueManager.getIssueById(1111);
//        List<Issue> issueList = issueManager.getIssues("jf-hsmp", null);
//        ResultsWrapper<Issue> issueList1 = issueManager.getIssues(new HashMap<>());
//
//        // Create issue
////        Issue issueToCreate = IssueFactory.create("some subject");
////        Issue createdIssue = issueManager.createIssue(projectKey , issueToCreate);
//
//
//
//        final Map<String, String> params = new HashMap<String, String>();
//        params.put("project_id", "5");
//        // 任务状态
//        params.put("status_id", "*");
////        params.put("status_id", "o"); //打开
////        params.put("status_id", "c"); //关闭
//        // 任务跟踪
//        params.put("tracker_id", "2");
//        // 每页条数
//        params.put("limit", "1");
//        // 页数
//        params.put("page", "1");
//        final ResultsWrapper<Issue> issueResultsWrapper = issueManager.getIssues(params);
//        List<Issue> issueList2 = issueResultsWrapper.getResults();
//        for (Issue issue : issueList2) {
//            issue.getCustomFields();
//            System.out.println(issue.getStatusName() + "->" + issue.getStatusId());
//        }
//
//
//
//        List<CustomFieldDefinition> customfieldlist = null;
//        List<String> projectlist = new ArrayList<String>();
//        try {
//            customfieldlist = redmineManager.getCustomFieldManager().getCustomFieldDefinitions();
//            for(int i=0;i<customfieldlist.size();i++){
//                if(customfieldlist.get(i).getName().equals("项目"))
//                    projectlist = customfieldlist.get(i).getPossibleValues();
//            }
//        } catch (RedmineException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//
//        Issue issue2 = issueManager.getIssueById(5, Include.journals, Include.relations, Include.attachments);
//        System.out.println(issue2.getJournals());
//        for (Issue issue : issueList) {
//            System.out.println(issue.getId() + "#" + issue.getSubject());
//        }
//
//        /* 附件 */
//        AttachmentManager attachmentManager = redmineManager.getAttachmentManager();
//    }

}
