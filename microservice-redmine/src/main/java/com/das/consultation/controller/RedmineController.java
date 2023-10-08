package com.das.consultation.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.das.consultation.entities.redmine.*;
import com.das.consultation.service.redmine.RedmineService;
import com.das.consultation.util.ResultUtils;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @Author: LJS
 * @Date: 2022/5/31 10:12
 */
@RestController
public class RedmineController {

    private Logger logger = LoggerFactory.getLogger(RedmineController.class);

    /**
     * Redmine服务
     */
    @Resource
    private RedmineService redmineService;

    /**
     * 登陆，根据企业微信CODE
     * @param jsonStr
     * @return Redmine用户密钥
     */
    @RequestMapping(value = "/loginByQywxCode", method = RequestMethod.POST)
    public ResponseEntity<String> loginByQywxCode(@RequestBody String jsonStr, HttpServletRequest request) {
        /* 局部变量 */
        String result = null;

        try {
            if (StringUtils.isEmpty(jsonStr)) {
                logger.error("==========login param is null");
            }
            logger.info("==========login param: " + jsonStr);
            JSONObject rootObject = JSON.parseObject(jsonStr);
            String code = rootObject.getString("code");
            if (StringUtils.isEmpty(code)) {
                logger.error("==========login param code is null");
                return ResponseEntity.ok(ResultUtils.fail(result));
            }
            result = redmineService.loginByQywxCode(code, request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* 服务返回 */
        return ResponseEntity.ok(ResultUtils.success(result));
    }

    /**
     * 根获取所有项目信息
     * @return
     */
    @RequestMapping(value = "/getAllProjectInfo", method = RequestMethod.POST)
    public ResponseEntity<String> getAllProjectInfoController(HttpServletRequest request) {
        /* 局部变量 */
        AllProjectDataVO allProjectDataVO = null;

        /* Redmine项目数据获取 */
        try {
            allProjectDataVO = redmineService.getAllProjectInfo(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* 数据组装并返回 */
        return ResponseEntity.ok(ResultUtils.success(allProjectDataVO));
    }

    /**
     * 获取项目信息，根据项目ID
     * @param jsonStr
     * @return
     */
    @RequestMapping(value = "/getOneProjectInfoById", method = RequestMethod.POST)
    public ResponseEntity<String>  getOneProjectInfoById(@RequestBody String jsonStr, HttpServletRequest request) {
        /* 局部变量 */
        ProjectOverviewVO projectOverviewVO = null;
        Integer id;

        try {
            JSONObject rootObject = JSON.parseObject(jsonStr);
            String projectId = rootObject.getString("projectId");
            if (StringUtils.isEmpty(projectId)) {
                logger.error("==========getOneProjectInfoById param projectId is null");
            }
            id = Integer.valueOf(projectId);
            projectOverviewVO = redmineService.getOneProjectInfoById(id, request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* 数据组装并返回 */
        return ResponseEntity.ok(ResultUtils.success(projectOverviewVO));
    }

    /**
     * 获取问题列表，根据项目ID和选择条件
     * @param jsonStr
     * @return
     */
    @RequestMapping(value = "/getIssueListByProjectIdAndSelect", method = RequestMethod.POST)
    public ResponseEntity<String> getIssueListByProjectIdAndSelect(@RequestBody String jsonStr, HttpServletRequest request) throws RedmineException {
        /* 局部变量 */
        List<IssueOverviewVO> issueCountOverviewVOList = null;
        Integer projectId;
        String status;
        String typeId;

        try {
            JSONObject rootObject = JSON.parseObject(jsonStr);
            String projectIdStr = rootObject.getString("projectId");
            status = rootObject.getString("status");
            typeId = rootObject.getString("typeId");
            if (StringUtils.isEmpty(projectIdStr)) {
                logger.error("==========getIssueListByProjectIdAndSelect param projectId is null");
            }
            projectId = Integer.valueOf(projectIdStr);

            issueCountOverviewVOList = redmineService.getIssueListByProjectIdAndSelect(projectId, status, typeId, request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* 数据组装并返回 */
        return ResponseEntity.ok(ResultUtils.success(issueCountOverviewVOList));
    }

    /**
     * 获取任务信息，根据任务ID
     * @param jsonStr
     * @return
     */
    @RequestMapping(value = "/getOneIssueInfoById", method = RequestMethod.POST)
    public ResponseEntity<String>  getOneIssueInfoById(@RequestBody String jsonStr, HttpServletRequest request) {
        /* 局部变量 */
        IssueOverviewVO issueOverviewVO = null;
        Integer id;

        try {
            JSONObject rootObject = JSON.parseObject(jsonStr);
            String issueId = rootObject.getString("issueId");
            if (StringUtils.isEmpty(issueId)) {
                logger.error("==========getOneIssueInfoById param issueId is null");
            }
            id = Integer.valueOf(issueId);
            issueOverviewVO = redmineService.getOneIssueInfoById(id, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* 数据组装并返回 */
        return ResponseEntity.ok(ResultUtils.success(issueOverviewVO));
    }

    /**
     * 获取用户列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    public ResponseEntity<String> getUserList() {
        /* 局部变量 */
        List<UserOverviewVO> userOverviewVOList = null;

        try {
            userOverviewVOList = redmineService.getUserList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* 服务返回 */
        return ResponseEntity.ok(ResultUtils.success(userOverviewVOList));
    }

    /**
     * 创建任务
     * @param jsonStr 任务json
     * @return
     */
    @RequestMapping(value = "/createIssue", method = RequestMethod.POST)
    public ResponseEntity<String>  createIssue(@RequestBody String jsonStr, HttpServletRequest request) {
        /* 局部变量 */
        // 创建结果
        Integer result;
        // 项目ID
        String projectIdStr;
        Integer projectId;
        // 任务名称
        String issueName;
        // 任务类型ID
        Integer issueTypeId;
        // 任务描述
        String issueDescribe;
        // 任务状态ID
        Integer issueStatusId;
        // 任务优先级ID
        Integer issuePriorityId;
        // 任务进度
        Integer issueProgress;
        // 任务指派人员ID
        Integer issueAssignsId;
        // 任务需求来源
        String issueDemandSource;
        // 任务需求/问题类型ID
        String issueDemandTypeId;
        // 任务所属模块ID
        String issueModuleId;
        // 任务实施负责人ID
        String issueImplLeaderId;
        // 任务开始日期
        Date issueStartDate = null;
        // 任务计划完成日期
        Date issuePlanEndDate = null;
        // 时间格式化
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            /* 报文解析 */
            JSONObject rootObject = JSON.parseObject(jsonStr);
            // 项目ID
            projectIdStr = rootObject.getString("projectId");
            projectId = Integer.valueOf(projectIdStr);
            // 任务名称
            issueName = rootObject.getString("issueName");
            // 任务类型ID
            issueTypeId = Integer.valueOf(rootObject.getString("issueTypeId"));
            // 任务描述
            issueDescribe = rootObject.getString("issueDescribe");
            // 任务状态ID
            issueStatusId = Integer.valueOf(rootObject.getString("issueStatusId"));
            // 任务优先级ID
            issuePriorityId = Integer.valueOf(rootObject.getString("issuePriorityId"));
            // 任务进度
            issueProgress = Integer.valueOf(rootObject.getString("issueProgress"));
            // 指派给
            issueAssignsId = Integer.valueOf(rootObject.getString("issueAssignsId"));
            // 需求来源
            issueDemandSource = rootObject.getString("issueDemandSource");
            // 需求/问题类型
            issueDemandTypeId = rootObject.getString("issueDemandTypeId");
            // 所属模块
            issueModuleId = rootObject.getString("issueModuleId");
            // 实施负责人
            issueImplLeaderId = rootObject.getString("issueImplLeaderId");
            // 任务计划开始i时间
            if (!StringUtils.isBlank(rootObject.getString("issueStartDate"))) {
                issueStartDate = format.parse(rootObject.getString("issueStartDate"));
            }
            // 任务计划结束时间
            if (!StringUtils.isBlank(rootObject.getString("issuePlanEndDate"))) {
                issuePlanEndDate = format.parse(rootObject.getString("issuePlanEndDate"));
            }

            /* 必输项校验 */
            if (StringUtils.isBlank(issueName) ||
                    null == issueTypeId ||
                    null == issueStatusId ||
                    null == issuePriorityId ||
                    StringUtils.isBlank(issueDemandSource) ||
                    StringUtils.isBlank(issueDemandTypeId) ||
                    StringUtils.isBlank(issueModuleId) ||
                    StringUtils.isBlank(issueImplLeaderId)
                    ) {
                return ResponseEntity.ok(ResultUtils.fail("请输入必输项"));
            }

            /* 任务组装 */
            Issue issue = IssueFactory.create(projectId, issueName);
            issue.setSubject(issueName);
            Tracker tracker = TrackerFactory.create(issueTypeId);
            issue.setTracker(tracker);
            issue.setDescription(issueDescribe);
            issue.setStatusId(issueStatusId);
            issue.setPriorityId(issuePriorityId);
            issue.setDoneRatio(issueProgress);
            issue.setAssigneeId(issueAssignsId);
            CustomField customField2 = CustomFieldFactory.create(1, "需求来源", issueDemandSource);
            CustomField customField1 = CustomFieldFactory.create(22, "需求/问题类型", issueDemandTypeId);
            CustomField customField3 = CustomFieldFactory.create(3, "所属模块", issueModuleId);
            CustomField customField4 = CustomFieldFactory.create(4, "实施负责人", issueImplLeaderId);
            issue.addCustomField(customField1);
            issue.addCustomField(customField2);
            issue.addCustomField(customField3);
            issue.addCustomField(customField4);
            issue.setStartDate(issueStartDate);
            issue.setDueDate(issuePlanEndDate);

            /* 任务创建 */
            result = redmineService.createIssue(issue, request);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(ResultUtils.fail(e.getMessage()));
        }

        /* 数据组装并返回 */
        return ResponseEntity.ok(ResultUtils.success(result));
    }

    /**
     * 更新任务
     * @param jsonStr 任务json
     * @return
     */
    @RequestMapping(value = "/updateIssue", method = RequestMethod.POST)
    public ResponseEntity<String>  updateIssue(@RequestBody String jsonStr, HttpServletRequest request) {
        /* 局部变量 */
        // 创建结果
        Integer result;
        // 项目ID
        String projectIdStr;
        Integer projectId;
        // 任务ID
        Integer issueId;
        // 任务名称
        String issueName;
        // 任务类型ID
        Integer issueTypeId;
        // 任务描述
        String issueDescribe;
        // 任务状态ID
        Integer issueStatusId;
        // 任务优先级ID
        Integer issuePriorityId;
        // 任务进度
        Integer issueProgress;
        // 任务指派人员ID
        Integer issueAssignsId;
        // 任务需求来源
        String issueDemandSource;
        // 任务需求/问题类型ID
        String issueDemandTypeId;
        // 任务所属模块ID
        String issueModuleId;
        // 任务实施负责人ID
        String issueImplLeaderId;
        // 任务开始日期
        Date issueStartDate = null;
        // 任务计划完成日期
        Date issuePlanEndDate = null;
        // 任务实际完成时间
        String issueFinishDate = null;
        // 任务是否合理延期/暂缓
        String isReasonableDelay;
        // 任务延期/暂缓原因
        String issueDelayReason;
        // 时间格式化
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            /* 报文解析 */
            JSONObject rootObject = JSON.parseObject(jsonStr);
            // 项目ID
            projectIdStr = rootObject.getString("projectId");
            projectId = Integer.valueOf(projectIdStr);
            // 任务ID
            issueId = Integer.valueOf(rootObject.getString("issueId"));
            // 任务名称
            issueName = rootObject.getString("issueName");
            // 任务类型ID
            issueTypeId = Integer.valueOf(rootObject.getString("issueTypeId"));
            // 任务描述
            issueDescribe = rootObject.getString("issueDescribe");
            // 任务状态ID
            issueStatusId = Integer.valueOf(rootObject.getString("issueStatusId"));
            // 任务优先级ID
            issuePriorityId = Integer.valueOf(rootObject.getString("issuePriorityId"));
            // 任务进度
            issueProgress = Integer.valueOf(rootObject.getString("issueProgress"));
            // 指派给
            issueAssignsId = Integer.valueOf(rootObject.getString("issueAssignsId"));
            // 需求来源
            issueDemandSource = rootObject.getString("issueDemandSource");
            // 需求/问题类型
            issueDemandTypeId = rootObject.getString("issueDemandTypeId");
            // 所属模块
            issueModuleId = rootObject.getString("issueModuleId");
            // 实施负责人
            issueImplLeaderId = rootObject.getString("issueImplLeaderId");
            // 任务计划开始i时间
            if (!StringUtils.isBlank(rootObject.getString("issueStartDate"))) {
                issueStartDate = format.parse(rootObject.getString("issueStartDate"));
            }
            // 任务计划结束时间
            if (!StringUtils.isBlank(rootObject.getString("issuePlanEndDate"))) {
                issuePlanEndDate = format.parse(rootObject.getString("issuePlanEndDate"));
            }
            // 任务实际完成时间
            issueFinishDate = rootObject.getString("issueFinishDate");
            // 是否合理延期/暂缓
            isReasonableDelay = rootObject.getString("isReasonableDelay");
            // 延期/暂缓原因
            issueDelayReason = rootObject.getString("issueDelayReason");

            /* 必输项校验 */
            if (StringUtils.isBlank(issueName) ||
                    null == issueTypeId ||
                    null == issueStatusId ||
                    null == issuePriorityId ||
                    StringUtils.isBlank(issueDemandSource) ||
                    StringUtils.isBlank(issueDemandTypeId) ||
                    StringUtils.isBlank(issueModuleId) ||
                    StringUtils.isBlank(issueImplLeaderId)
                    ) {
                return ResponseEntity.ok(ResultUtils.fail("请输入必输项"));
            }

            /* 任务组装 */
//            Issue issue = IssueFactory.create(projectId, issueName);
            Issue issue = IssueFactory.create(issueId);
            issue.setSubject(issueName);
            Tracker tracker = TrackerFactory.create(issueTypeId);
            issue.setTracker(tracker);
            issue.setDescription(issueDescribe);
            issue.setStatusId(issueStatusId);
            issue.setPriorityId(issuePriorityId);
            issue.setDoneRatio(issueProgress);
            issue.setAssigneeId(issueAssignsId);
            CustomField customField2 = CustomFieldFactory.create(1, "需求来源", issueDemandSource);
            CustomField customField1 = CustomFieldFactory.create(22, "需求/问题类型", issueDemandTypeId);
            CustomField customField3 = CustomFieldFactory.create(3, "所属模块", issueModuleId);
            CustomField customField4 = CustomFieldFactory.create(4, "实施负责人", issueImplLeaderId);
            CustomField customField5 = CustomFieldFactory.create(28, "实际完成时间", issueFinishDate);
            CustomField customField6 = CustomFieldFactory.create(24, "是否合理延期/暂缓", isReasonableDelay);
            CustomField customField7 = CustomFieldFactory.create(25, "延期/暂缓原因", issueDelayReason);
            issue.addCustomField(customField1);
            issue.addCustomField(customField2);
            issue.addCustomField(customField3);
            issue.addCustomField(customField4);
            issue.addCustomField(customField5);
            issue.addCustomField(customField6);
            issue.addCustomField(customField7);
            issue.setStartDate(issueStartDate);
            issue.setDueDate(issuePlanEndDate);

            /* 任务创建 */
            redmineService.updateIssue(issue, request);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(ResultUtils.fail(e.getMessage()));
        }

        /* 数据组装并返回 */
        return ResponseEntity.ok(ResultUtils.success());
    }

    /**
     * 导出Excel
     * @return
     */
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        /* 局部变量 */
        List<DataExportVO> dataExportVOList = null;
        Integer projectId;
        String status;
        String typeId;
        // 导出结果
        boolean exportResult = false;
        // 导出文件名称
        String exportFileName;
        // 开始日期
        LocalDate startDate;
        // 日期格式化
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            dataExportVOList = redmineService.getExportData(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* Excel导出 */
        try {
            /* Excel组装 */
            // Excel名称设置
            exportFileName = "QY-DelayTask-" + df.format(new Date()) + ".xlsx";
            // Excel对象创建
            SXSSFWorkbook wb = new SXSSFWorkbook(500);
            // sheet创建
            Sheet sh = wb.createSheet("sheet" + 1);
            sh.setDefaultColumnWidth(20);
            sh.setColumnWidth(0, 5*256);
            sh.setColumnWidth(1, 15*256);
            sh.setColumnWidth(2, 20*256);
            sh.setColumnWidth(3, 10*256);
            sh.setColumnWidth(4, 50*256);
            sh.setColumnWidth(5, 10*256);
            sh.setColumnWidth(6, 15*256);
            sh.setColumnWidth(7, 5*256);
            sh.setColumnWidth(8, 10*256);
            sh.setColumnWidth(9, 12*256);
            sh.setColumnWidth(10, 12*256);
            sh.setColumnWidth(11, 15*256);
            sh.setColumnWidth(12, 20*256);
            sh.setColumnWidth(13, 20*256);
            sh.setColumnWidth(14, 20*256);
            // Excel头样式
            CellStyle headStyle = wb.createCellStyle();
            headStyle.setAlignment(HorizontalAlignment.CENTER);
            headStyle.setBorderLeft(BorderStyle.THIN);
            headStyle.setBorderTop(BorderStyle.THIN);
            headStyle.setBorderRight(BorderStyle.THIN);
            headStyle.setBorderBottom(BorderStyle.THIN);
            Font fontHeader = wb.createFont();
            fontHeader.setBold(true);
            fontHeader.setFontHeightInPoints((short) 11);
            headStyle.setFont(fontHeader);
            // Excel数据样式
            CellStyle dataStyle = wb.createCellStyle();
            dataStyle.setAlignment(HorizontalAlignment.LEFT);
            dataStyle.setBorderLeft(BorderStyle.THIN);
            dataStyle.setBorderTop(BorderStyle.THIN);
            dataStyle.setBorderRight(BorderStyle.THIN);
            dataStyle.setBorderBottom(BorderStyle.THIN);
            Font font = wb.createFont();
            font.setBold(false);
            font.setFontName("宋体");
            font.setFontHeightInPoints((short) 10);
            dataStyle.setFont(font);

            // Excel头行对象创建
            Row row = sh.createRow(0);
            Cell cell0 = row.createCell(0);
            XSSFRichTextString richString0 = new XSSFRichTextString("序号");
            richString0.applyFont(font);
            cell0.setCellValue(richString0);
            cell0.setCellStyle(headStyle);
            Cell cell1 = row.createCell(1);
            XSSFRichTextString richString1 = new XSSFRichTextString("项目名称");
            richString1.applyFont(font);
            cell1.setCellValue(richString1);
            cell1.setCellStyle(headStyle);
            Cell cell2 = row.createCell(2);
            XSSFRichTextString richString2 = new XSSFRichTextString("任务名称");
            richString2.applyFont(font);
            cell2.setCellValue(richString2);
            cell2.setCellStyle(headStyle);
            Cell cell3 = row.createCell(3);
            XSSFRichTextString richString3 = new XSSFRichTextString("任务类型");
            richString3.applyFont(font);
            cell3.setCellValue(richString3);
            cell3.setCellStyle(headStyle);
            Cell cell4 = row.createCell(4);
            XSSFRichTextString richString4 = new XSSFRichTextString("任务描述");
            richString4.applyFont(font);
            cell4.setCellValue(richString4);
            cell4.setCellStyle(headStyle);
            Cell cell5 = row.createCell(5);
            XSSFRichTextString richString5 = new XSSFRichTextString("指派给");
            richString5.applyFont(font);
            cell5.setCellValue(richString5);
            cell5.setCellStyle(headStyle);
            Cell cell6 = row.createCell(6);
            XSSFRichTextString richString6 = new XSSFRichTextString("状态");
            richString6.applyFont(font);
            cell6.setCellValue(richString6);
            cell6.setCellStyle(headStyle);
            Cell cell7 = row.createCell(7);
            XSSFRichTextString richString7 = new XSSFRichTextString("进度");
            richString7.applyFont(font);
            cell7.setCellValue(richString7);
            cell7.setCellStyle(headStyle);
            Cell cell8 = row.createCell(8);
            XSSFRichTextString richString8 = new XSSFRichTextString("优先级");
            richString8.applyFont(font);
            cell8.setCellValue(richString8);
            cell8.setCellStyle(headStyle);
            Cell cell9 = row.createCell(9);
            XSSFRichTextString richString9 = new XSSFRichTextString("开始日期");
            richString9.applyFont(font);
            cell9.setCellValue(richString9);
            cell9.setCellStyle(headStyle);
            Cell cell10 = row.createCell(10);
            XSSFRichTextString richString10 = new XSSFRichTextString("计划完成日期");
            richString10.applyFont(font);
            cell10.setCellValue(richString10);
            cell10.setCellStyle(headStyle);
            Cell cell11 = row.createCell(11);
            XSSFRichTextString richString11 = new XSSFRichTextString("延期暂缓原因");
            richString11.applyFont(font);
            cell11.setCellValue(richString11);
            cell11.setCellStyle(headStyle);
            Cell cell12 = row.createCell(12);
            XSSFRichTextString richString12 = new XSSFRichTextString("需求来源");
            richString12.applyFont(font);
            cell12.setCellValue(richString12);
            cell12.setCellStyle(headStyle);
            Cell cell13 = row.createCell(13);
            XSSFRichTextString richString13 = new XSSFRichTextString("需求问题类型");
            richString13.applyFont(font);
            cell13.setCellValue(richString13);
            cell13.setCellStyle(headStyle);
            Cell cell14 = row.createCell(14);
            XSSFRichTextString richString14 = new XSSFRichTextString("所属模块");
            richString14.applyFont(font);
            cell14.setCellValue(richString14);
            cell14.setCellStyle(headStyle);

            // Excel数据行创建及填充
            int dataLineCount = 0;
            for (int d=0; d<dataExportVOList.size(); d++) {
                String projectName = dataExportVOList.get(d).getProjectName();
                List<IssueOverviewVO> issueList = dataExportVOList.get(d).getIssueList();
                if (null == issueList || issueList.isEmpty()) {
                    continue;
                }
                for (int i=0; i<issueList.size(); i++) {
                    dataLineCount++;
                    // 当前任务i
                    IssueOverviewVO currentOverviewVO = issueList.get(i);
                    // 行创建
                    row = sh.createRow(dataLineCount);
                    // 列数据填充
                    // 任务名称
                    String issueName = currentOverviewVO.getIssueName();
                    // 任务类型名称
                    String issueTypeName = currentOverviewVO.getIssueTypeName();
                    // 任务描述
                    String issueDescribe = currentOverviewVO.getIssueDescribe();
                    // 任务指派人员名称
                    String issueAssignsName = currentOverviewVO.getIssueAssignsName();
                    // 任务状态名称
                    String issueStatusName = currentOverviewVO.getIssueStatusName();
                    // 任务进度
                    Integer issueProgress = currentOverviewVO.getIssueProgress();
                    // 任务优先级名称
                    Integer issuePriorityId = currentOverviewVO.getIssuePriorityId();
                    String issuePriorityName;
                    switch (issuePriorityId) {
                        case 1:
                            issuePriorityName = "低";
                            break;
                        case 2:
                            issuePriorityName = "中";
                            break;
                        case 3:
                            issuePriorityName = "高";
                            break;
                        case 4:
                            issuePriorityName = "优先";
                            break;
                        default:
                            issuePriorityName = "";
                    }
                    // 任务开始日期
                    String issueStartDate = currentOverviewVO.getIssueStartDate();
                    // 任务计划完成日期
                    String issuePlanEndDate = currentOverviewVO.getIssuePlanEndDate();
                    // 任务延期/暂缓原因
                    String issueDelayReason = currentOverviewVO.getIssueDelayReason();
                    // 任务需求来源
                    String issueDemandSource = currentOverviewVO.getIssueDemandSource();
                    // 任务需求/问题类型名称
                    String issueDemandTypeName = currentOverviewVO.getIssueDemandTypeName();
                    // 任务所属模块名称
                    String issueModuleName = currentOverviewVO.getIssueModuleName();

                    // 序号
                    Cell dCell0 = row.createCell(0);
                    dCell0.setCellStyle(dataStyle);
                    dCell0.setCellValue(dataLineCount);
                    // 项目名称
                    Cell dCell1 = row.createCell(1);
                    dCell1.setCellStyle(dataStyle);
                    dCell1.setCellValue(projectName);
                    // 任务名称
                    Cell dCell2 = row.createCell(2);
                    dCell2.setCellStyle(dataStyle);
                    dCell2.setCellValue(null == issueName ? "" :issueName);
                    // 任务类型
                    Cell dCell3 = row.createCell(3);
                    dCell3.setCellStyle(dataStyle);
                    dCell3.setCellValue(null == issueTypeName ? "" : issueTypeName);
                    // 任务描述
                    Cell dCell4 = row.createCell(4);
                    dCell4.setCellStyle(dataStyle);
                    dCell4.setCellValue(null == issueDescribe ? "" : issueDescribe);
                    // 指派给
                    Cell dCell5 = row.createCell(5);
                    dCell5.setCellStyle(dataStyle);
                    dCell5.setCellValue(null == issueAssignsName ? "" : issueAssignsName);
                    // 状态
                    Cell dCell6 = row.createCell(6);
                    dCell6.setCellStyle(dataStyle);
                    dCell6.setCellValue(null == issueStatusName ? "" : issueStatusName);
                    // 进度
                    Cell dCell7 = row.createCell(7);
                    dCell7.setCellStyle(dataStyle);
                    dCell7.setCellValue(null == issueProgress ? "" : issueProgress+"%");
                    // 优先级
                    Cell dCell8 = row.createCell(8);
                    dCell8.setCellStyle(dataStyle);
                    dCell8.setCellValue(null == issuePriorityName ? "" : String.valueOf(issuePriorityName));
                    // 开始日期
                    Cell dCell9 = row.createCell(9);
                    dCell9.setCellStyle(dataStyle);
                    dCell9.setCellValue(null == issueStartDate ? "" : issueStartDate);
                    // 计划完成日期
                    Cell dCell10 = row.createCell(10);
                    dCell10.setCellStyle(dataStyle);
                    dCell10.setCellValue(null == issuePlanEndDate ? "" : issuePlanEndDate);
                    // 延期暂缓原因
                    Cell dCell11 = row.createCell(11);
                    dCell11.setCellStyle(dataStyle);
                    dCell11.setCellValue(null == issueDelayReason ? "" : issueDelayReason);
                    // 需求来源
                    Cell dCell12 = row.createCell(12);
                    dCell12.setCellStyle(dataStyle);
                    dCell12.setCellValue(null == issueDemandSource ? "" : issueDemandSource);
                    // 需求问题类型
                    Cell dCell13 = row.createCell(13);
                    dCell13.setCellStyle(dataStyle);
                    dCell13.setCellValue(null == issueDemandTypeName ? "" : issueDemandTypeName);
                    // 所属模块
                    Cell dCell14 = row.createCell(14);
                    dCell14.setCellStyle(dataStyle);
                    dCell14.setCellValue(null == issueModuleName ? "" : issueModuleName);
                }
            }
            /* Excel下载 */
            exportResult = download(request, exportFileName, response, wb, null);
        } catch (Exception e) {
            e.printStackTrace();
            /* 服务异常返回 */
//            return ResponseEntity.ok(ResultUtils.fail(e.getMessage()));
        }

        /* 服务返回 */
        if (!exportResult) {
//            return ResponseEntity.ok(ResultUtils.fail("导出失败"));
        }




        /* 数据组装并返回 */
//        return ResponseEntity.ok(ResultUtils.success(issueCountOverviewVOList));
    }

    public boolean download(HttpServletRequest request, String filename, HttpServletResponse response, SXSSFWorkbook workbook, XSSFWorkbook wb) {
        /* 局部变量 */
        // 输出流
        OutputStream out = null;
        // 是否成功标识
        boolean falg = false;
        try {
            /* 返回头设置 */
            response.addHeader("200", "导出成功");
            response.setContentType("application/octet-stream");
            response.setHeader("content-type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename, "utf8"));

            /* 文件输出 */
            logger.info("File {} output start......", filename);
            out = response.getOutputStream();
            if (workbook == null) {
                wb.write(out);
            } else {
                workbook.write(out);
                //文件销毁
                workbook.dispose();
            }
            logger.info("File {} output end......", filename);
            /* 刷新缓存 */
            out.flush();
            falg = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("File {} output error，message: {}", filename, e.getMessage());
        } finally {
            /* 输出流关闭 */
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return falg;
    }

}
