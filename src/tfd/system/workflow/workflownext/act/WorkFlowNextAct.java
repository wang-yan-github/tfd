package tfd.system.workflow.workflownext.act;

import java.net.URLDecoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.messageunit.MessageApi;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.workflow.flowpassleave.logic.FlowPassLeaveLogic;
import tfd.system.workflow.flowprocess.data.FlowProcess;
import tfd.system.workflow.flowprocess.logic.FlowProcessLogic;
import tfd.system.workflow.flowrun.data.FlowRun;
import tfd.system.workflow.flowrun.logic.FlowRunLogic;
import tfd.system.workflow.flowrunprcs.data.FlowRunPrcs;
import tfd.system.workflow.flowrunprcs.logic.FlowRunPrcsLogic;
import tfd.system.workflow.flowutility.UitilityFlowPriv;
import tfd.system.workflow.getflowdata.logic.WorkFlowDataLogic;
import tfd.system.workflow.newwork.logic.NewWorkFlowLogic;
import tfd.system.workflow.workflow.data.WorkFlow;
import tfd.system.workflow.workflow.logic.WorkFlowLogic;
import tfd.system.workflow.workflownext.logic.WorkFlowNextLogic;
import tfd.system.workflow.workflownext.tool.FlowProcessWriterItemTool;
import tfd.system.workflow.workflownext.tool.WorkFlowDataTool;
import tfd.system.workflow.workflownext.tool.WorkFlowNextCheckTool;
import tfd.system.workflow.worklist.data.WorkList;
import tfd.system.workflow.worklist.logic.WorkListLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.SysTool;

public class WorkFlowNextAct {
    // 保存表单与相关的意见
    public void saveFormDataAct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String runId = request.getParameter("runId");
        String tableName = request.getParameter("tableName");
        String flowId = request.getParameter("flowId");
        String idea = request.getParameter("idea");
        String ideaText = request.getParameter("ideaText");
        String[] ueditArr = request.getParameterValues("ueditValue");
        String data = request.getParameter("data");
        String xSql[] = request.getParameterValues("xsql");
        int runPrcsId = Integer.parseInt(request.getParameter("runPrcsId"));
        int prcsId = Integer.parseInt(request.getParameter("prcsId"));
        Map<String, String> map = new HashMap<String, String>();
        String flowData = URLDecoder.decode(data, "utf-8");
        String flowTtile = request.getParameter("flowTitleOld");
        String flowLeave = request.getParameter("flowLeave");
        Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
        Connection dbConn = null;
        int returnData = 0;
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            FlowRunLogic flowRunLogic = new FlowRunLogic();

            flowRunLogic.updateLeaveAndTitleLogic(dbConn, runId, flowTtile, flowLeave);

            WorkFlowDataTool workFlowDataTool = new WorkFlowDataTool();
            map = workFlowDataTool.getDataMap(flowData, ueditArr, xSql);
            FlowProcessWriterItemTool writerFieldTool = new FlowProcessWriterItemTool();
            String[] writerField = writerFieldTool.getFlowProcessWriterItemTool(dbConn, flowId, prcsId);
            // 保存表单数据
            WorkFlowNextLogic workFlowNextLogic = new WorkFlowNextLogic();
            workFlowNextLogic.saveFlowFormDataLogic(dbConn, map, writerField, runId, tableName);
            // 保存子表数据
            String jsonData = request.getParameter("listControlsData");
            workFlowNextLogic.saveChildTableData(dbConn, runId, jsonData);
            // 保存附件
            // 保存会签意见
            FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
            returnData = flowRunPrcsLogic.savaIdeaLoigc(dbConn, runId, runPrcsId, idea, ideaText,
                    account.getAccountId());
            dbConn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
    }

    // 下一步
    public void workFlowNextAct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject json = new JSONObject();
        String nextPrcsList = "";
        Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
        String returnData = "";
        Connection dbConn = null;
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            String runId = request.getParameter("runId");
            String tableName = request.getParameter("tableName");
            String flowId = request.getParameter("flowId");
            String idea = request.getParameter("idea");
            String ideaText = request.getParameter("ideaText");
            String[] ueditArr = request.getParameterValues("ueditValue");
            String data = request.getParameter("data");
            String xSql[] = request.getParameterValues("xsql");
            int prcsId = Integer.parseInt(request.getParameter("prcsId"));
            int runPrcsId = Integer.parseInt(request.getParameter("runPrcsId"));
            FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
            FlowProcess flowProcess = new FlowProcess();
            Map<String, String> map = new HashMap<String, String>();
            String flowData = URLDecoder.decode(data, "utf-8");
            WorkFlowDataTool workFlowDataTool = new WorkFlowDataTool();
            map = workFlowDataTool.getDataMap(flowData, ueditArr, xSql);
            FlowProcessWriterItemTool writerFieldTool = new FlowProcessWriterItemTool();
            String[] writerField = writerFieldTool.getFlowProcessWriterItemTool(dbConn, flowId, prcsId);
            // 保存表单数据
            WorkFlowNextLogic workFlowNextLogic = new WorkFlowNextLogic();
            workFlowNextLogic.saveFlowFormDataLogic(dbConn, map, writerField, runId, tableName);
            // 保存子表数据
            String jsonData = request.getParameter("listControlsData");
            workFlowNextLogic.saveChildTableData(dbConn, runId, jsonData);
            // 保存会签意见
            FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
            flowRunPrcsLogic.savaIdeaLoigc(dbConn, runId, runPrcsId, idea, ideaText, account.getAccountId());

            WorkFlow workFlow = new WorkFlow();
            WorkFlowLogic workFlowLogic = new WorkFlowLogic();
            workFlow = workFlowLogic.getWorkFlowByFlowTypeIdLogic(dbConn, flowId);
            // 判断流程是不是启用行政级别审批
            String leavePass = workFlow.getLeavePass();
            flowProcess = flowProcessLogic.getFlowProcessLogic(dbConn, flowId, prcsId);
            if (leavePass.equals("1")) {
                String leadPrcsLeave = flowProcess.getLeadPrcsLeave();
                FlowPassLeaveLogic flowPassLeaveLogic = new FlowPassLeaveLogic();
                nextPrcsList = flowPassLeaveLogic.getNextPrcsByPassLeave(dbConn, flowId, leadPrcsLeave);
            } else {
                // 判断下一步结点
                WorkFlowNextCheckTool workFlowNextCheckTool = new WorkFlowNextCheckTool();
                nextPrcsList = workFlowNextCheckTool.workFlowNextCheckLogic(dbConn, runId, prcsId, flowId,
                        tableName);
            }
            // 结束流程
            if (nextPrcsList.equals("0")) {
                flowRunPrcsLogic.endWorkFlow(dbConn, runId);
                WorkListLogic workListLogic = new WorkListLogic();
                workListLogic.updateStatusLogic(dbConn, runId, runPrcsId, account.getAccountId());
                FlowRunLogic flowRunLogic = new FlowRunLogic();
                flowRunLogic.endFlowRunLogic(dbConn, runId);
                returnData = "{\"isEnd\":\"END\"}";
            } else {
                json.accumulate("forceParallel", flowProcess.getForceParallel());
                JSONArray temp = workFlowNextLogic.getAutoUser(dbConn, flowId, runId, nextPrcsList, account,
                        prcsId, runPrcsId);
                json.accumulate("nextnode", temp);
                returnData = json.toString();
            }
            dbConn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
    }

    // 获取下一步相关信息
    public void getNextPrcsInfoAct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String nextPrcsStr = request.getParameter("nextPrcs");
        String nextPrcs = "";
        String returnData = "";
        if (SysTool.isNullorEmpty(nextPrcsStr)) {
            response.getWriter().print(returnData);
            response.getWriter().flush();
        } else {
            JSONArray jsonArr = JSONArray.fromObject(nextPrcsStr);
            for (int i = 0; jsonArr.size() > i; i++) {
                JSONObject json = JSONObject.fromObject(jsonArr.getString(i));
                nextPrcs = nextPrcs + json.getString("prcsId") + ",";
            }
            if (nextPrcs.equals(null) || nextPrcs.equals("")) {
                response.getWriter().print("{\"flag\":\"false\"}");
                response.getWriter().flush();
                return;
            } else {
                if (nextPrcs.indexOf(",") > 0) {
                    nextPrcs = nextPrcs.substring(0, nextPrcs.length() - 1);
                }
            }
            String flowId = request.getParameter("flowId");
            Connection dbConn = null;
            try {
                dbConn = DbPoolConnection.getInstance().getConnection();
                WorkFlowNextLogic workFlowNextLogic = new WorkFlowNextLogic();
                returnData = workFlowNextLogic.getNextPrcsInfoLogic(dbConn, flowId, nextPrcs);
                dbConn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DbOp dbop = new DbOp();
                dbop.connClose(dbConn);
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().print(returnData);
                response.getWriter().flush();
            }
        }

    }

    // 转交下一步
    public void goNextAct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<String> toAccountList = new ArrayList<String>();
        String smsRemind = request.getParameter("smsRemind");
        JSONObject smsRemindJson = JSONObject.fromObject(smsRemind);
        String paramStr = request.getParameter("paramStr");
        String runId = request.getParameter("runId");
        String tableName = request.getParameter("tableName");
        String flowId = request.getParameter("flowId");
        int prcsId = Integer.parseInt(request.getParameter("prcsId"));
        int runPrcsId = Integer.parseInt(request.getParameter("runPrcsId")) + 1;
        Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
        Connection dbConn = DbPoolConnection.getInstance().getConnection();
        WorkFlowNextLogic workFlowNextLogic = new WorkFlowNextLogic();
        FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
        UserInfoLogic userInfoLogic = new UserInfoLogic();
        int prcsRunId = workFlowNextLogic.getPrcsRunId(dbConn, runId);
        UitilityFlowPriv uitilityFlowPriv = new UitilityFlowPriv();
        FlowProcess flowProcess = new FlowProcess();
        WorkFlowLogic workFlowLogic = new WorkFlowLogic();
        FlowRunLogic flowRunLogic = new FlowRunLogic();
        FlowRun flowRun = flowRunLogic.getFlowRunLogic(dbConn, runId);
        String parentWait = flowRun.getParentWait();
        String waitPrcsId = flowRun.getWaitPrcsId();

        WorkFlowDataLogic workFlowData = new WorkFlowDataLogic();
        String childRunId = workFlowData.getChildRunIdByRunIdLogic(dbConn, tableName, runId);
        // 判断子流程不是等待
        if (parentWait.equals("1")) {
            // 强制等待
            boolean childFlowEnd = workFlowLogic.getWorkFlowEndLogic(dbConn, childRunId);
            if (childFlowEnd) {
                // 已结束//更新待办记录状态
                flowRunLogic.updateFlowWaitLogic(dbConn, runId, "", "");
                WorkListLogic workListLogic = new WorkListLogic();
                workListLogic.updateStatusLogic(dbConn, runId, runPrcsId, account.getAccountId());
                FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
                flowRunPrcsLogic.updateStatusLogic(dbConn, runId, prcsId, account.getAccountId(), prcsRunId);
                if (prcsId == 0) {
                    flowRunLogic.endFlowRunLogic(dbConn, runId);
                }
                boolean nextFlag = uitilityFlowPriv.getForceAggregationPriv(dbConn, runId, prcsId);
                if (nextFlag) {
                    JSONArray jsonArr = JSONArray.fromObject(paramStr);
                    for (int i = 0; jsonArr.size() > i; i++) {
                        JSONObject json = JSONObject.fromObject(jsonArr.get(i));
                        flowProcess = flowProcessLogic.getFlowProcessLogic(dbConn, flowId,
                                Integer.parseInt(json.getString("prcsId")));
                        // 处理子流程节点
                        // 获取子流程的发起人，也就是下一步的经办人员
                        // --------------------------------------------------------------------要处理------------------------
                        String nextOptUser = account.getAccountId();
                        if (flowProcess.getPrcsType().equals("6")) {
                            String childFlow = flowProcess.getChildFlow();
                            AccountLogic accountLogic = new AccountLogic();
                            Account childAccount = accountLogic.getAccountByAccountId(dbConn, nextOptUser);
                            // 新子流程的相关数据
                            NewWorkFlowLogic newWorkFlowLogic = new NewWorkFlowLogic();
                            newWorkFlowLogic.createChildWorkLogic(dbConn, childAccount, "子流程", childFlow,
                                    flowId, runId, flowProcess);
                            flowRunLogic.updateFlowWaitLogic(dbConn, runId, flowProcess.getWaitPrcsId(),
                                    flowProcess.getParnetWait());
                        }
                        // 处理非子流程节点
                        else {
                            UserInfo userInfo = userInfoLogic.getUserInfoByAccount(dbConn, account);
                            FlowRunPrcs flowRunPrcs = new FlowRunPrcs();
                            flowRunPrcs.setRunId(runId);
                            flowRunPrcs.setFlowId(flowId);
                            flowRunPrcs.setTableName(tableName);
                            flowRunPrcs.setPrcsId(Integer.parseInt(json.getString("prcsId")));
                            flowRunPrcs.setPrcsName(flowProcess.getPrcsName());
                            flowRunPrcs.setAccountId(nextOptUser);
                            flowRunPrcs.setUserName(userInfo.getUserName());
                            flowRunPrcs.setDeptId(userInfo.getDeptId());
                            flowRunPrcs.setUserPrivId(userInfo.getPostPriv());
                            flowRunPrcs.setLeadId(userInfo.getLeadId());
                            flowRunPrcs.setTransferUser(account.getAccountId());
                            flowRunPrcs.setOrgId(account.getOrgId());
                            workFlowNextLogic.goNextLogic(dbConn, flowRunPrcs, prcsRunId);
                            // 生成下一步的待办列表
                            WorkList workList = new WorkList();
                            String path = tableName.toLowerCase() + "/" + json.getString("prcsId")
                                    + "/index.jsp";
                            ;
                            workList.setTitle(workListLogic.getTitle(dbConn, runId));
                            workList.setPrcsName(flowProcess.getPrcsName());
                            workList.setRunId(runId);
                            workList.setModule("workflow");
                            workList.setAccountId(nextOptUser);
                            workList.setUrl("/system/workflow/dowork/" + path + "?runId=" + runId
                                    + "&runPrcsId=" + runPrcsId);
                            workList.setReadUrl("/system/workflow/dowork/" + tableName
                                    + "/print/index.jsp?runId=" + runId);
                            workList.setStatus("0");
                            workList.setCreateTime(SysTool.getCurDateTimeStr());
                            workList.setOrgId(account.getOrgId());
                            workList.setDelFlag("0");
                            workList.setPrcsId(Integer.parseInt(json.getString("prcsId")));
                            workList.setRunPrcsId(runPrcsId);
                            workList.setOrgId(account.getOrgId());
                            workListLogic.createDoRecordLogic(dbConn, workList);
                            String opUserStr = flowRunLogic.getOpUserStrLogic(dbConn, runId);
                            flowRunLogic.updateOpUserStrLogic(dbConn, runId, opUserStr + "," + nextOptUser);
                        }
                    }
                }
                dbConn.commit();
                dbConn.close();
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().print("OK");
                response.getWriter().flush();

            } else {
                dbConn.commit();
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().print("flowWait");
                response.getWriter().flush();
            }
        } else if (parentWait.equals("0") || SysTool.isNullorEmpty(parentWait)) {
            // 不等待
            boolean nextFlag = uitilityFlowPriv.getForceAggregationPriv(dbConn, runId, prcsId);
            // 更新待办记录状态
            WorkListLogic workListLogic = new WorkListLogic();
            workListLogic.updateStatusLogic(dbConn, runId, runPrcsId - 1, account.getAccountId());
            //
            UserInfo userInfo = new UserInfo();
            // 更新流程步骤状态
            FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
            WorkList workList = new WorkList();
            flowRunPrcsLogic.updateStatusLogic(dbConn, runId, runPrcsId - 1, account.getAccountId(),
                    prcsRunId);
            // 更淅主表数据
            // 判断步骤是不是结束结点
            if (prcsId == 0) {
                flowRunLogic.endFlowRunLogic(dbConn, runId);
            }
            if (nextFlag) {
                JSONArray jsonArr = JSONArray.fromObject(paramStr);
                for (int i = 0; jsonArr.size() > i; i++) {
                    JSONObject json = JSONObject.fromObject(jsonArr.get(i));
                    flowProcess = flowProcessLogic.getFlowProcessLogic(dbConn, flowId,
                            Integer.parseInt(json.getString("prcsId")));
                    // 处理子流程节点
                    if (flowProcess.getPrcsType().equals("6")) {
                        // 获取子流程的发起人，也就是下一步的经办人员
                        String opUserId = json.getString("opAccount");
                        String childFlow = flowProcess.getChildFlow();
                        AccountLogic accountLogic = new AccountLogic();
                        Account childAccount = accountLogic.getAccountByAccountId(dbConn, opUserId);
                        userInfo = userInfoLogic.getUserInfoByAccountId(dbConn, opUserId);
                        // 新子流程的相关数据
                        NewWorkFlowLogic newWorkFlowLogic = new NewWorkFlowLogic();
                        newWorkFlowLogic.createChildWorkLogic(dbConn, childAccount, "子流程", childFlow, flowId,
                                runId, flowProcess);
                        flowRunLogic.updateFlowWaitLogic(dbConn, runId, flowProcess.getWaitPrcsId(),
                                flowProcess.getParnetWait());
                        // nextOptUser=account.getAccountId();
                        FlowRunPrcs flowRunPrcs = new FlowRunPrcs();
                        flowRunPrcs.setRunId(runId);
                        flowRunPrcs.setFlowId(flowId);
                        flowRunPrcs.setTableName(tableName);
                        flowRunPrcs.setPrcsId(Integer.parseInt(json.getString("prcsId")));
                        flowRunPrcs.setPrcsName(flowProcess.getPrcsName());
                        flowRunPrcs.setAccountId(opUserId);
                        flowRunPrcs.setUserName(userInfo.getUserName());
                        flowRunPrcs.setDeptId(userInfo.getDeptId());
                        flowRunPrcs.setUserPrivId(userInfo.getPostPriv());
                        flowRunPrcs.setLeadId(userInfo.getLeadId());
                        flowRunPrcs.setTransferUser(account.getAccountId());
                        flowRunPrcs.setOrgId(account.getOrgId());
                        workFlowNextLogic.goNextLogic(dbConn, flowRunPrcs, prcsRunId);
                        // 生成下一步的待办列表
                        String path = tableName.toLowerCase() + "/"
                                + Integer.parseInt(json.getString("prcsId")) + "/index.jsp";
                        workList.setTitle(workListLogic.getTitle(dbConn, runId));
                        workList.setRunId(runId);
                        workList.setModule("workflow");
                        workList.setPrcsName(flowProcess.getPrcsName());
                        workList.setAccountId(opUserId);
                        workList.setUrl("/system/workflow/dowork/" + path + "?runId=" + runId + "&runPrcsId="
                                + runPrcsId);
                        workList.setReadUrl("/system/workflow/dowork/" + tableName
                                + "/print/index.jsp?runId=" + runId);
                        workList.setRunPrcsId(runPrcsId);
                        workList.setStatus("0");
                        workList.setDelFlag("0");
                        workList.setCreateTime(SysTool.getCurDateTimeStr());
                        workList.setOrgId(account.getOrgId());
                        workList.setPrcsId(Integer.parseInt(json.getString("prcsId")));
                        workListLogic.createDoRecordLogic(dbConn, workList);
                        String opUserStr = flowRunLogic.getOpUserStrLogic(dbConn, runId);
                        flowRunLogic.updateOpUserStrLogic(dbConn, runId, opUserStr + "," + opUserId);
                    }
                    // 处理非子流程节点
                    else {
                        // 主办人员处理
                        String opUserId = json.getString("opAccount");
                        toAccountList.add(opUserId);
                        userInfo = userInfoLogic.getUserInfoByAccountId(dbConn, opUserId);
                        FlowRunPrcs flowRunPrcs = new FlowRunPrcs();
                        flowRunPrcs.setRunId(runId);
                        flowRunPrcs.setFlowId(flowId);
                        flowRunPrcs.setStatus("0");
                        flowRunPrcs.setOpFlag("0");
                        flowRunPrcs.setTableName(tableName);
                        flowRunPrcs.setPrcsId(Integer.parseInt(json.getString("prcsId")));
                        flowRunPrcs.setPrcsName(flowProcess.getPrcsName());
                        flowRunPrcs.setAccountId(opUserId);
                        flowRunPrcs.setUserName(userInfo.getUserName());
                        flowRunPrcs.setDeptId(userInfo.getDeptId());
                        flowRunPrcs.setUserPrivId(userInfo.getPostPriv());
                        flowRunPrcs.setLeadId(userInfo.getLeadId());
                        flowRunPrcs.setTransferUser(account.getAccountId());
                        flowRunPrcs.setOrgId(account.getOrgId());
                        ;
                        workFlowNextLogic.goNextLogic(dbConn, flowRunPrcs, prcsRunId);
                        String path = tableName.toLowerCase() + "/" + json.getString("prcsId") + "/index.jsp";
                        ;
                        workList.setTitle(workListLogic.getTitle(dbConn, runId));
                        workList.setRunId(runId);
                        workList.setModule("workflow");
                        workList.setPrcsName(flowProcess.getPrcsName());
                        workList.setAccountId(opUserId);
                        workList.setUrl("/system/workflow/dowork/" + path + "?runId=" + runId
                                + "&opFlag=0&runPrcsId=" + runPrcsId);
                        workList.setReadUrl("/system/workflow/dowork/" + tableName
                                + "/print/index.jsp?runId=" + runId);
                        workList.setStatus("0");
                        workList.setDelFlag("0");
                        workList.setCreateTime(SysTool.getCurDateTimeStr());
                        workList.setOrgId(account.getOrgId());
                        workList.setRunPrcsId(runPrcsId);
                        workList.setPrcsId(Integer.parseInt(json.getString("prcsId")));
                        workListLogic.createDoRecordLogic(dbConn, workList);
                        String opUserStr = flowRunLogic.getOpUserStrLogic(dbConn, runId);
                        flowRunLogic.updateOpUserStrLogic(dbConn, runId, opUserStr + "," + opUserId);
                        // 主办人员处理结束
                        // 知会人员处理开始
                        String zhAccountIds = json.getString("zhAccount");
                        String[] zhAccountArr = null;
                        if (!SysTool.isNullorEmpty(zhAccountIds)) {
                            if (zhAccountIds.endsWith(",")) {
                                zhAccountIds = zhAccountIds.substring(0, zhAccountIds.length() - 1);
                            }
                            if (zhAccountIds.indexOf(",") > 0) {
                                zhAccountArr = zhAccountIds.split(",");
                            } else {
                                zhAccountArr = new String[] { zhAccountIds };
                            }
                            for (int k = 0; zhAccountArr.length > k; k++) {
                                toAccountList.add(zhAccountArr[k]);
                                userInfo = userInfoLogic.getUserInfoByAccountId(dbConn, zhAccountArr[k]);
                                flowRunPrcs.setRunId(runId);
                                flowRunPrcs.setFlowId(flowId);
                                flowRunPrcs.setStatus("0");
                                flowRunPrcs.setOpFlag("1");
                                flowRunPrcs.setTableName(tableName);
                                flowRunPrcs.setPrcsId(Integer.parseInt(json.getString("prcsId")));
                                flowRunPrcs.setPrcsName(flowProcess.getPrcsName());
                                flowRunPrcs.setAccountId(zhAccountArr[k]);
                                flowRunPrcs.setUserName(userInfo.getUserName());
                                flowRunPrcs.setDeptId(userInfo.getDeptId());
                                flowRunPrcs.setUserPrivId(userInfo.getPostPriv());
                                flowRunPrcs.setLeadId(userInfo.getLeadId());
                                flowRunPrcs.setTransferUser(account.getAccountId());
                                flowRunPrcs.setOrgId(account.getOrgId());
                                workFlowNextLogic.goNextLogic(dbConn, flowRunPrcs, prcsRunId);

                                workList.setTitle(workListLogic.getTitle(dbConn, runId));
                                workList.setRunId(runId);
                                workList.setModule("workflow");
                                workList.setPrcsName(flowProcess.getPrcsName());
                                workList.setAccountId(zhAccountArr[k]);
                                workList.setUrl("/system/workflow/dowork/" + path + "?runId=" + runId
                                        + "&opFlag=1&runPrcsId=" + runPrcsId);
                                workList.setReadUrl("/system/workflow/dowork/" + tableName.toLowerCase()
                                        + "/print/index.jsp?runId=" + runId);
                                workList.setStatus("0");
                                workList.setDelFlag("0");
                                workList.setCreateTime(SysTool.getCurDateTimeStr());
                                workList.setOrgId(account.getOrgId());
                                workList.setRunPrcsId(runPrcsId);
                                workList.setPrcsId(Integer.parseInt(json.getString("prcsId")));
                                workListLogic.createDoRecordLogic(dbConn, workList);
                                opUserStr = flowRunLogic.getOpUserStrLogic(dbConn, runId);
                                flowRunLogic.updateOpUserStrLogic(dbConn, runId, opUserStr + ","
                                        + zhAccountArr[k]);
                            }
                        }
                        // 知会人员处理结束

                    }

                    // 需发送信息提醒的人员的ID
                    String flowName = workFlowLogic.getFlowName(dbConn, flowId);
                    MessageApi messageApi = new MessageApi();
                    messageApi.sendMessage(dbConn, "workflow", smsRemindJson, "你有一个" + flowName + "需要你处理！",
                            account.getAccountId(), toAccountList, account.getOrgId());
                }
            }
        } else if (parentWait.equals("2")) {
            System.out.println("指定步骤等待！");
        }

        dbConn.commit();
        dbConn.close();
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print("OK");
        response.getWriter().flush();
    }

}
