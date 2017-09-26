package tfd.system.workflow.getflowdata.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.dept.logic.DeptLogic;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.unit.userpriv.logic.UserPrivLogic;
import tfd.system.workflow.flowformitem.logic.FLowFormItemLogic;
import tfd.system.workflow.flowprocess.data.FlowProcess;
import tfd.system.workflow.flowprocess.logic.FlowProcessLogic;
import tfd.system.workflow.flowrun.data.FlowRun;
import tfd.system.workflow.flowrun.logic.FlowRunLogic;
import tfd.system.workflow.flowrunprcs.data.FlowRunPrcs;
import tfd.system.workflow.flowrunprcs.logic.FlowRunPrcsLogic;
import tfd.system.workflow.flowutility.UitilityTool;
import tfd.system.workflow.form.logic.WorkFlowFormLogic;
import tfd.system.workflow.workflow.data.WorkFlow;
import tfd.system.workflow.workflow.logic.WorkFlowLogic;

import com.system.db.BaseDao;
import com.system.db.PageTool;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.SysTool;

public class WorkFlowDataLogic {
    public BaseDao dao = new BaseDaoImpl();

    public String getWorkFlowDataLogic(Connection conn, String runId, Account account) throws SQLException {
        ResultSet rs = null;
        String query = "";
        String[] fieldName = null;
        JSONObject json = new JSONObject();

        FlowRunLogic flowRunLogic = new FlowRunLogic();
        String flowId = flowRunLogic.getFlowIdByRunIdLogic(conn, runId);
        WorkFlowLogic workFlowLogic = new WorkFlowLogic();
        WorkFlow workFlow = new WorkFlow();
        workFlow = workFlowLogic.getWorkFlowByFlowTypeIdLogic(conn, flowId);
        String printField = "," + workFlow.getPrintField() + ",";
        String formId = workFlow.getFormId();
        // String formId=workFlowLogic.getFormIdByFlowTypeIdLogic(conn, flowId);
        FLowFormItemLogic flowFormItemLogic = new FLowFormItemLogic();
        List<String> fieldList = flowFormItemLogic.getFieldByFormIdList(conn, formId);
        WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
        String tableName = workFlowFormLogic.getFormTableNameByFormIdLogic(conn, formId);
        String fieldListStr = "";
        for (int i = 0; fieldList.size() > i; i++) {
            fieldListStr += fieldList.get(i) + ",";
        }
        if (!SysTool.isNullorEmpty(fieldListStr)) {
            fieldListStr = fieldListStr.substring(0, fieldListStr.length() - 1);
            query = "SELECT " + fieldListStr + " FROM " + tableName + " WHERE RUN_ID='" + runId + "'";
            fieldName = fieldListStr.split(",");
        }
        PreparedStatement ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        if (rs.next()) {
            for (int i = 0; i < fieldName.length; i++) {
                if (printField.indexOf("," + fieldName[i] + ",") >= 0) {
                    if (SysTool.isNullorEmpty(rs.getString(fieldName[i]))) {
                        json.accumulate(fieldName[i], "");
                    } else {
                        json.accumulate(fieldName[i], rs.getString(fieldName[i]));
                    }
                }
            }
        }
        rs.close();
        ps.close();
        FlowRun flowRun = new FlowRun();
        FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
        FlowRunPrcs flowRunPrcsBegin = new FlowRunPrcs();
        flowRunPrcsBegin = flowRunPrcsLogic.getFlowRunPrcs(conn, runId, 1, 1, account);
        String formName = workFlowFormLogic.getFormNameByFormId(conn, formId);
        flowRun = flowRunLogic.getFlowRunLogic(conn, runId);
        json.accumulate("flowTitle", flowRun.getTitle());
        json.accumulate("flowBeginTime", flowRun.getBeginTime());
        json.accumulate("flowEndTime", flowRun.getEndTime());
        json.accumulate("flowGuId", flowRun.getRunId());
        json.accumulate("flowRunId", flowRun.getId());
        json.accumulate("flowFormName", formName);
        json.accumulate("flowUserStr", flowRun.getOpUserStr());
        json.accumulate("flowBeginUser", flowRunPrcsBegin.getUserName());
        json.accumulate("flowBeginAccountId", flowRunPrcsBegin.getAccountId());
        json.accumulate("publicFiles", flowRun.getAttachId());
        return json.toString();
    }

    // 获取工作流程表单数据JSON
    public String getWorkFlowDataLogic(Connection conn, FlowRunPrcs flowRunPrcs, FlowProcess flowProcess,
            Account account) throws SQLException {
        ResultSet rs = null;
        String query = "";
        String xmacroFlag = "";
        String xdocnumFlag = "";
        JSONObject json = new JSONObject();
        if (SysTool.isNullorEmpty(flowRunPrcs.getIdea())) {
            json.accumulate("idea", "");
        } else {
            json.accumulate("idea", flowRunPrcs.getIdea());
        }
        if (SysTool.isNullorEmpty(flowRunPrcs.getIdeaText())) {
            json.accumulate("ideaText", "");
        } else {
            json.accumulate("ideaText", flowRunPrcs.getIdeaText());
        }
        String[] fieldName = null;
        FLowFormItemLogic flowFormItemLogic = new FLowFormItemLogic();
        String selectField = flowFormItemLogic.getAllFlowFormItemLogic(conn, flowRunPrcs.getFlowId(),
                flowRunPrcs.getPrcsId());
        if (!SysTool.isNullorEmpty(selectField)) {
            query = "SELECT " + selectField + " FROM " + flowRunPrcs.getTableName() + " WHERE RUN_ID='"
                    + flowRunPrcs.getRunId() + "'";
            fieldName = selectField.split(",");
        } else {
            return "[]";
        }
        PreparedStatement ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        if (rs.next()) {
            for (int i = 0; i < fieldName.length; i++) {
                if (SysTool.isNullorEmpty(rs.getString(fieldName[i]))) {
                    json.accumulate(fieldName[i], "");
                } else {
                    json.accumulate(fieldName[i], rs.getString(fieldName[i]));
                }
            }
        }
        rs.close();
        ps.close();
        // 获取步骤会签意见

        // 获取当前可写宏控件列表
        FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
        String writerFieldStr = flowProcess.getWriterField();
        writerFieldStr = "," + writerFieldStr + ",";

        String hiddenfield = flowProcess.getHiddenField();
        // 工作流表单宏控件处理
        for (int j = 0; fieldName.length > j; j++) {
            if (!SysTool.isNullorEmpty(writerFieldStr)) {
                if (writerFieldStr.indexOf("," + fieldName[j] + ",") >= 0) {
                    // 处理工作流计数器
                    xdocnumFlag = flowFormItemLogic.getXDocNumLogic(conn, fieldName[j],
                            flowRunPrcs.getFlowId());
                    if (!SysTool.isNullorEmpty(xdocnumFlag)) {
                        UitilityTool uitilityTool = new UitilityTool();
                        json.remove(fieldName[j]);
                        String value = uitilityTool.createAutoNum(conn, flowRunPrcs.getFlowId());
                        json.accumulate(fieldName[j], value);
                    }
                    // 宏控件处理
                    xmacroFlag = flowFormItemLogic.getAutoFieldModelLogic(conn, fieldName[j],
                            flowRunPrcs.getFlowId());
                    if (!SysTool.isNullorEmpty(xmacroFlag)) {
                        JSONObject xmacroJson = JSONObject.fromObject(xmacroFlag);
                        if (xmacroJson.getString("type").equals("1")) {
                            // 年份
                            SimpleDateFormat df = new SimpleDateFormat(xmacroJson.getString("format"));// 设置日期格式
                            json.remove(fieldName[j]);
                            json.accumulate(fieldName[j], df.format(new Date()));
                        } else if (xmacroJson.getString("type").equals("2")) {
                            // /日期期
                            SimpleDateFormat df = new SimpleDateFormat(xmacroJson.getString("format"));// 设置日期格式
                            json.remove(fieldName[j]);
                            json.accumulate(fieldName[j], df.format(new Date()));
                        } else if (xmacroJson.getString("type").equals("3")) {
                            // 时间
                            SimpleDateFormat df = new SimpleDateFormat(xmacroJson.getString("format"));// 设置日期格式
                            json.remove(fieldName[j]);
                            json.accumulate(fieldName[j], df.format(new Date()));
                        } else if (xmacroJson.getString("type").equals("5")) {
                            // 流程名称
                            json.remove(fieldName[j]);
                            json.accumulate(fieldName[j], flowRunPrcs.getPrcsName());
                        } else if (xmacroJson.getString("type").equals("6")) {
                            // 流程编号
                            json.remove(fieldName[j]);
                            json.accumulate(fieldName[j], flowRunPrcs.getRunId());
                        } else if (xmacroJson.getString("type").equals("7")) {
                            // 流程发起人账号
                            FlowRunPrcsLogic flowrunPrcsLogic = new FlowRunPrcsLogic();
                            json.remove(fieldName[j]);
                            json.accumulate(fieldName[j],
                                    flowrunPrcsLogic.flowBeginAccountId(conn, flowRunPrcs.getRunId()));
                        } else if (xmacroJson.getString("type").equals("8")) {
                            // 流程发起人姓名
                            FlowRunPrcsLogic flowrunPrcsLogic = new FlowRunPrcsLogic();
                            json.remove(fieldName[j]);
                            json.accumulate(fieldName[j],
                                    flowrunPrcsLogic.flowBeginUserName(conn, flowRunPrcs.getRunId()));
                        } else if (xmacroJson.getString("type").equals("9")) {
                            // 流程发起人部门
                            FlowRunPrcsLogic flowrunPrcsLogic = new FlowRunPrcsLogic();
                            String accountId = flowrunPrcsLogic.flowBeginAccountId(conn,
                                    flowRunPrcs.getRunId());
                            UserInfoLogic userInfoLogic = new UserInfoLogic();
                            String deptId = userInfoLogic.getDeptId(conn, accountId);
                            DeptLogic deptLogic = new DeptLogic();
                            json.remove(fieldName[j]);
                            json.accumulate(fieldName[j], deptLogic.getDeptNameShort(conn, deptId));
                        } else if (xmacroJson.getString("type").equals("10")) {
                            // 流程发起人角色
                            UserPrivLogic userPrivLogic = new UserPrivLogic();
                            json.remove(fieldName[j]);
                            json.accumulate(fieldName[j],
                                    userPrivLogic.getUserPrivNameById(conn, account.getUserPriv()));
                        } else if (xmacroJson.getString("type").equals("11")) {
                            // 当前用户帐号
                            json.remove(fieldName[j]);
                            json.accumulate(fieldName[j], account.getAccountId());
                        } else if (xmacroJson.getString("type").equals("12")) {
                            // 当前用户姓名
                            UserInfoLogic userInfoLogic = new UserInfoLogic();
                            json.remove(fieldName[j]);
                            json.accumulate(fieldName[j],
                                    userInfoLogic.getUserNameByAccountIdLogic(conn, account.getAccountId()));
                        } else if (xmacroJson.getString("type").equals("13")) {
                            // 当前用户部门
                            UserInfoLogic userInfoLogic = new UserInfoLogic();
                            String deptId = userInfoLogic.getDeptId(conn, account.getAccountId());
                            DeptLogic deptLogic = new DeptLogic();
                            json.remove(fieldName[j]);
                            json.accumulate(fieldName[j], deptLogic.getDeptNameShort(conn, deptId));
                        } else if (xmacroJson.getString("type").equals("14")) {
                            // 当前用户长部门
                            UserInfoLogic userInfoLogic = new UserInfoLogic();
                            String deptId = userInfoLogic.getDeptId(conn, account.getAccountId());
                            DeptLogic deptLogic = new DeptLogic();
                            json.remove(fieldName[j]);
                            json.accumulate(fieldName[j], deptLogic.getDeptNameLong(conn, deptId));
                        } else if (xmacroJson.getString("type").equals("21")) {
                            // 处理工作流文号
                            json.remove(fieldName[j]);
                            String value = createAutoCode(conn, flowRunPrcs.getFlowId(),
                                    flowRunPrcs.getRunId(), account);
                            json.accumulate(fieldName[j], value);
                        }
                    }
                }
            }
            if (!SysTool.isNullorEmpty(hiddenfield)) {
                if (hiddenfield.indexOf("," + fieldName[j] + ",") >= 0) {
                    json.remove(fieldName[j]);
                }
            }
        }
        FlowRun flowRun = new FlowRun();
        FlowRunLogic flowRunLogic = new FlowRunLogic();
        WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
        String formId = flowProcessLogic.getFormIdbyFlowIdLogic(conn, flowRunPrcs.getFlowId(), 1);
        FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
        FlowRunPrcs flowRunPrcsBegin = new FlowRunPrcs();
        flowRunPrcsBegin = flowRunPrcsLogic.getFlowRunPrcs(conn, flowRunPrcs.getRunId(), 1, 1, account);
        String formName = workFlowFormLogic.getFormNameByFormId(conn, formId);
        flowRun = flowRunLogic.getFlowRunLogic(conn, flowRunPrcs.getRunId());
        json.accumulate("flowFollow", flowRunPrcs.getFollow());
        json.accumulate("publicFileId", flowRun.getAttachId());
        json.accumulate("publicFilePriv", flowProcess.getPublicFile());
        json.accumulate("flowTitle", flowRun.getTitle());
        json.accumulate("flowTitleOld", flowRun.getTitle());
        json.accumulate("flowBeginTime", flowRun.getBeginTime());
        json.accumulate("flowEndTime", flowRun.getEndTime());
        json.accumulate("flowGuId", flowRun.getRunId());
        json.accumulate("flowRunId", flowRun.getId());
        json.accumulate("flowFormName", formName);
        json.accumulate("flowUserStr", flowRun.getOpUserStr());
        json.accumulate("opFlag", flowRunPrcs.getOpFlag());
        json.accumulate("flowBeginUser", flowRunPrcsBegin.getUserName());
        json.accumulate("flowBeginAccountId", flowRunPrcsBegin.getAccountId());
        json.accumulate("goBack", flowProcess.getGoBack());
        json.accumulate("prcsName", flowProcess.getPrcsName());
        json.accumulate("follow", flowProcess.getFollow());
        json.accumulate("mustField", flowProcess.getMustField());
        json.accumulate("flowLeave", flowRun.getFlowLeave());
        return json.toString();
    }

    // 获取工作流程表单数据MAP
    public Map<String, String> getFormDateMapLogic(Connection conn, String flowId, String runId,
            String formId, String tableName) throws SQLException {
        ResultSet rs = null;
        Map<String, String> formData = new HashMap<String, String>();
        FLowFormItemLogic flowFormItemLogic = new FLowFormItemLogic();
        List<String> fieldList = flowFormItemLogic.getFieldByFormIdList(conn, formId);
        String queryStr = "SELECT * FROM " + tableName + " WHERE RUN_ID='" + runId + "'";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        rs = ps.executeQuery();
        if (rs.next()) {
            formData.put("ID", rs.getString("ID"));
            formData.put("RUN_ID", rs.getString("RUN_ID"));
            for (int i = 0; fieldList.size() > i; i++) {
                formData.put(fieldList.get(i), rs.getString(fieldList.get(i)));
            }

        }
        return formData;
    }

    // 获取所有子表数据
    public String getAllChildTableDataLogic(Connection conn, String runId) throws SQLException {
        FlowRunLogic flowRunLogic = new FlowRunLogic();
        String flowId = flowRunLogic.getFlowIdByRunIdLogic(conn, runId);
        PreparedStatement ps = null;
        String queryStr = "";
        ResultSet rs = null;
        FLowFormItemLogic flowFormItemLogic = new FLowFormItemLogic();
        String childTableJson = flowFormItemLogic.getAllXlistJsonLogic(conn, flowId);
        JSONArray jsonArr = JSONArray.fromObject(childTableJson);
        JSONArray returnJsonArr = new JSONArray();
        if (jsonArr.size() > 0) {
            for (int i = 0; jsonArr.size() > i; i++) {
                JSONObject tableJson = new JSONObject();
                String selectFieldStr = "";
                String selectTitleStr = "";
                JSONObject json = jsonArr.getJSONObject(i);
                String title = json.getString("title");
                String table = json.getString("table");
                String model = json.getString("model");
                JSONArray mJsonArr = JSONArray.fromObject(model);
                ArrayList<String> fileList = new ArrayList<String>();
                for (int j = 0; mJsonArr.size() > j; j++) {
                    JSONObject mJson = mJsonArr.getJSONObject(j);
                    String fieldName = mJson.getString("fieldname");
                    fileList.add(fieldName);
                    selectFieldStr += fieldName + ",";
                    String fieldTitle = mJson.getString("title");
                    selectTitleStr += fieldTitle + ",";
                }
                if (selectTitleStr.length() > 0) {
                    selectTitleStr = selectTitleStr.substring(0, selectTitleStr.length() - 1);
                }
                JSONArray dataJsonArr = new JSONArray();
                queryStr = "SELECT " + selectFieldStr + "ID FROM " + table + " WHERE RUN_ID=?";
                ps = conn.prepareStatement(queryStr);
                ps.setString(1, runId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    JSONObject dataJson = new JSONObject();
                    for (int s = 0; fileList.size() > s; s++) {
                        dataJson.accumulate(fileList.get(s), rs.getString(fileList.get(s)));
                    }
                    dataJsonArr.add(dataJson);
                }
                tableJson.accumulate("tableData", dataJsonArr);
                tableJson.accumulate("title", title);
                tableJson.accumulate("tableName", table);
                tableJson.accumulate("tableTitle", selectTitleStr);
                returnJsonArr.add(tableJson);
            }
            rs.close();
            ps.close();
        } else {
            return returnJsonArr.toString();
        }
        return returnJsonArr.toString();
    }

    // 获取子表数据
    public JSONArray getChildTableDataLogic(Connection conn, String runId, String writerJson)
            throws Exception {
        JSONArray tables = new JSONArray();
        if (SysTool.isNullorEmpty(writerJson)) {
            return tables;
        }

        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            JSONArray writerFields = JSONArray.fromObject(writerJson);
            for (int i = 0; writerFields.size() > i; i++) {
                JSONObject table = new JSONObject();
                JSONArray tableData = new JSONArray();

                JSONObject writerField = writerFields.getJSONObject(i);
                String tableName = writerField.getString("table");
                String readWriter = writerField.getString("read");
                String writerfield = writerField.getString("writerfield");
                String title = writerField.getString("title");
                String printfiled = writerField.getString("print");

                if (!SysTool.isNullorEmpty(printfiled)) {
                    String[] fieldArr = printfiled.split(",");
                    String queryStr = "SELECT * FROM " + tableName + " WHERE RUN_ID=?";
                    ps = conn.prepareStatement(queryStr);
                    ps.setString(1, runId);

                    rs = ps.executeQuery();
                    while (rs.next()) {
                        JSONObject rowData = new JSONObject();
                        rowData.accumulate("runId", rs.getString("RUN_ID"));
                        rowData.accumulate("tag", rs.getString("TAG"));

                        for (int j = 0; fieldArr.length > j; j++) {
                            if (SysTool.isNullorEmpty(rs.getString(fieldArr[j]))) {
                                rowData.accumulate(fieldArr[j], "");
                            } else {
                                rowData.accumulate(fieldArr[j], rs.getString(fieldArr[j]));
                            }
                        }
                        tableData.add(rowData);
                    }

                    table.accumulate("table", tableName);
                    table.accumulate("read", readWriter);
                    table.accumulate("writerfield", writerfield);
                    table.accumulate("print", printfiled);
                    table.accumulate("title", title);
                    table.accumulate("data", tableData);
                    tables.add(table);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        } finally {
            dao.close(rs, ps, null);
        }
        return tables;
    }

    // 生成文号控件数据
    public String createAutoCode(Connection conn, String flowTypeId, String runId, Account account)
            throws SQLException {
        String returnData = "";
        WorkFlowLogic workFlowLogic = new WorkFlowLogic();
        WorkFlow workFlow = new WorkFlow();
        workFlow = workFlowLogic.getWorkFlowByFlowTypeIdLogic(conn, flowTypeId);
        UitilityTool uitilityTool = new UitilityTool();
        returnData = uitilityTool.createCode(conn, workFlow.getAutoCode(), flowTypeId, runId, account);
        return returnData;
    }

    // 获取childWorkFlowRunId 通过 runId
    public String getChildRunIdByRunIdLogic(Connection conn, String tableName, String runId)
            throws SQLException {
        String returnData = "";
        String queryStr = "SELECT CHILD_RUN_ID FROM " + tableName + " WHERE RUN_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, runId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            returnData = rs.getString("CHILD_RUN_ID");
        }
        rs.close();
        ps.close();
        return returnData;
    }

    // 获取流程选择控件数据
    public String getSelectWorkFlowLogic(Connection conn, Account account, String model, String q_runId,
            String q_title, int pageSize, int page, String sortOrder, String sortName) throws Exception {
        String whereStr = "";
        if (!SysTool.isNullorEmpty(q_runId)) {
            whereStr += " AND A.RUN_ID='" + q_runId + "' ";
        }
        if (!SysTool.isNullorEmpty(q_title)) {
            whereStr += " AND A.TITLE LIKE '%" + q_title + "%' ";
        }
        JSONObject json = new JSONObject();
        List<String> pramList = new ArrayList<String>();
        pramList.add(account.getAccountId());
        if (model.equals("1")) {
            json = this.getFLowListByBeginUserLogic(conn, pramList, whereStr, pageSize, page, sortOrder,
                    sortName);
        }
        if (model.equals("2")) {

        }
        return json.toString();
    }

    public JSONObject getFLowListByBeginUserLogic(Connection conn, List<String> pramList, String whereStr,
            int pageSize, int page, String sortOrder, String sortName) throws Exception {
        String queryStr = "SELECT A.ID AS ID,A.RUN_ID AS RUN_ID,A.TITLE AS TITLE,B.ACCOUNT_ID AS ACCOUNT_ID,A.BEGIN_TIME AS BEGIN_TIME,A.OP_USER_STR AS OP_USER_STR "
                + " FROM FLOW_RUN A, FLOW_RUN_PRCS B WHERE B.PRCS_ID='1' AND B.ACCOUNT_ID=? AND A.RUN_ID=B.RUN_ID "
                + whereStr;
        String optStr = "[{'function':'selectFlowRun','name':'选择','parm':'RUN_ID'}]";
        JSONArray optArrJson = JSONArray.fromObject(optStr);
        PageTool pageTool = new PageTool();
        JSONObject json = pageTool.getPageData(conn, queryStr, pramList, pageSize, page, optArrJson,
                sortOrder, sortName);
        return json;
    }

}
