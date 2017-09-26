package tfd.system.workflow.flowrunprcs.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.workflow.flowrunprcs.data.FlowRunPrcs;
import tfd.system.workflow.worklist.data.WorkList;
import tfd.system.workflow.worklist.logic.WorkListLogic;

import com.system.db.DbPoolConnection;
import com.system.tool.SysTool;

public class FlowRunPrcsLogic {
    // 获取流程发起人账号
    public String flowBeginAccountId(Connection conn, String runId) throws SQLException {
        String returnData = "";
        String queryStr = "SELECT ACCOUNT_ID FROM FLOW_RUN_PRCS WHERE RUN_ID=? AND RUN_PRCS_ID='1'";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, runId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            returnData = rs.getString("ACCOUNT_ID");
        }
        rs.close();
        ps.close();
        return returnData;
    }

    // 获取流程发起人姓名

    public String flowBeginUserName(Connection conn, String runId) throws SQLException {
        String returnData = "";
        String queryStr = "SELECT USER_NAME FROM FLOW_RUN_PRCS WHERE RUN_ID=? AND RUN_PRCS_ID='1'";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, runId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            returnData = rs.getString("USER_NAME");
        }
        rs.close();
        ps.close();
        return returnData;
    }

    public FlowRunPrcs getFlowRunPrcs(Connection conn, int Id) throws SQLException {
        FlowRunPrcs flowRunPrcs = new FlowRunPrcs();
        ResultSet rs = null;
        String queryStr = "SELECT ID,RUN_ID,FLOW_ID,RUN_PRCS_ID,PRCS_ID,DEPT_ID,OP_FLAG,TABLE_NAME,PRCS_NAME,ACCOUNT_ID,USER_NAME,DEPT_ID,USER_PRIV_ID,"
                + "LEAD_ID,CREATE_TIME,END_TIME,PASS,IDEA,STATUS,IDEA_TEXT,ATTACH_ID,FOLLOW FROM FLOW_RUN_PRCS WHERE ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setInt(1, Id);
        rs = ps.executeQuery();
        if (rs.next()) {
            flowRunPrcs.setId(rs.getInt("ID"));
            flowRunPrcs.setRunId(rs.getString("RUN_ID"));
            flowRunPrcs.setFlowId(rs.getString("FLOW_ID"));
            flowRunPrcs.setRunPrcsId(rs.getInt("RUN_PRCS_ID"));
            flowRunPrcs.setPrcsId(rs.getInt("PRCS_ID"));
            flowRunPrcs.setDeptId(rs.getString("DEPT_ID"));
            flowRunPrcs.setOpFlag(rs.getString("OP_FLAG"));
            flowRunPrcs.setTableName(rs.getString("TABLE_NAME"));
            flowRunPrcs.setPrcsName(rs.getString("PRCS_NAME"));
            flowRunPrcs.setAccountId(rs.getString("ACCOUNT_ID"));
            flowRunPrcs.setDeptId(rs.getString("DEPT_ID"));
            flowRunPrcs.setUserName(rs.getString("USER_NAME"));
            flowRunPrcs.setUserPrivId(rs.getString("USER_PRIV_ID"));
            flowRunPrcs.setLeadId(rs.getString("LEAD_ID"));
            flowRunPrcs.setCreateTime(rs.getString("CREATE_TIME"));
            flowRunPrcs.setEndTime(rs.getString("END_TIME"));
            flowRunPrcs.setPass(rs.getString("PASS"));
            flowRunPrcs.setIdea(rs.getString("IDEA"));
            flowRunPrcs.setStatus(rs.getString("STATUS"));
            flowRunPrcs.setIdeaText(rs.getString("IDEA_TEXT"));
            flowRunPrcs.setFollow(rs.getString("FOLLOW"));
            flowRunPrcs.setAttachId(rs.getString("ATTACH_ID"));
        }
        rs.close();
        ps.close();
        return flowRunPrcs;
    }

    // 获取步骤实例按PRCSID
    public FlowRunPrcs getFlowRunPrcs(Connection conn, String runId, int prcsId, int runPrcsId,
            Account account) throws SQLException {
        FlowRunPrcs flowRunPrcs = new FlowRunPrcs();
        ResultSet rs = null;
        String queryStr = "SELECT ID,RUN_ID,FLOW_ID,RUN_PRCS_ID,PRCS_ID,DEPT_ID,OP_FLAG,TABLE_NAME,PRCS_NAME,ACCOUNT_ID,USER_NAME,DEPT_ID,USER_PRIV_ID,"
                + "LEAD_ID,CREATE_TIME,END_TIME,PASS,IDEA,STATUS,IDEA_TEXT,ATTACH_ID,FOLLOW FROM FLOW_RUN_PRCS WHERE PRCS_ID=? AND RUN_PRCS_ID=? AND RUN_ID=? AND ACCOUNT_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setInt(1, prcsId);
        ps.setInt(2, runPrcsId);
        ps.setString(3, runId);
        ps.setString(4, account.getAccountId());
        rs = ps.executeQuery();
        if (rs.next()) {
            flowRunPrcs.setId(rs.getInt("ID"));
            flowRunPrcs.setRunId(rs.getString("RUN_ID"));
            flowRunPrcs.setFlowId(rs.getString("FLOW_ID"));
            flowRunPrcs.setRunPrcsId(rs.getInt("RUN_PRCS_ID"));
            flowRunPrcs.setPrcsId(rs.getInt("PRCS_ID"));
            flowRunPrcs.setDeptId(rs.getString("DEPT_ID"));
            flowRunPrcs.setOpFlag(rs.getString("OP_FLAG"));
            flowRunPrcs.setTableName(rs.getString("TABLE_NAME"));
            flowRunPrcs.setPrcsName(rs.getString("PRCS_NAME"));
            flowRunPrcs.setAccountId(rs.getString("ACCOUNT_ID"));
            flowRunPrcs.setDeptId(rs.getString("DEPT_ID"));
            flowRunPrcs.setUserName(rs.getString("USER_NAME"));
            flowRunPrcs.setUserPrivId(rs.getString("USER_PRIV_ID"));
            flowRunPrcs.setLeadId(rs.getString("LEAD_ID"));
            flowRunPrcs.setCreateTime(rs.getString("CREATE_TIME"));
            flowRunPrcs.setEndTime(rs.getString("END_TIME"));
            flowRunPrcs.setPass(rs.getString("PASS"));
            flowRunPrcs.setIdea(rs.getString("IDEA"));
            flowRunPrcs.setStatus(rs.getString("STATUS"));
            flowRunPrcs.setIdeaText(rs.getString("IDEA_TEXT"));
            flowRunPrcs.setFollow(rs.getString("FOLLOW"));
            flowRunPrcs.setAttachId(rs.getString("ATTACH_ID"));
        }
        rs.close();
        ps.close();
        return flowRunPrcs;
    }

    // 更改步骤状态
    public int updateStatusLogic(Connection conn, String runId, int prcsId, String accountId, int runPrcsId)
            throws SQLException {
        String queryStr = "UPDATE FLOW_RUN_PRCS SET STATUS='1',END_TIME=?  WHERE RUN_ID=? AND PRCS_ID=? AND ACCOUNT_ID=? AND RUN_PRCS_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
        ps.setString(2, runId);
        ps.setInt(3, prcsId);
        ps.setString(4, accountId);
        ps.setInt(5, runPrcsId);
        int i = ps.executeUpdate();
        ps.close();
        return i;
    }

    // 结束流程
    public int endWorkFlow(Connection conn, String runId) throws SQLException {
        String queryStr = "UPDATE FLOW_RUN_PRCS SET STATUS='1',END_TIME=? WHERE RUN_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
        ps.setString(2, runId);
        int i = ps.executeUpdate();
        ps.close();
        return i;
    }

    // 保存会签结果与意见
    public int savaIdeaLoigc(Connection conn, String runId, int RUN_PRCS_ID, String idea, String ideaText,
            String accountId) throws SQLException {
        String queryStr = "UPDATE FLOW_RUN_PRCS SET IDEA=?,IDEA_TEXT=? WHERE RUN_ID=? AND RUN_PRCS_ID=? AND ACCOUNT_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, idea);
        ps.setString(2, ideaText);
        ps.setString(3, runId);
        ps.setInt(4, RUN_PRCS_ID);
        ps.setString(5, accountId);
        int i = ps.executeUpdate();
        ps.close();
        return i;
    }

    // 获取会签意见
    public String getIdeaLogic(Connection conn, String runId) throws SQLException {
        JSONArray jsonArr = new JSONArray();
        ResultSet rs = null;
        String queryStr = "SELECT " + "F.RUN_PRCS_ID AS RUN_PRCS_ID," + "F.PRCS_ID AS PRCS_ID,"
                + "F.PRCS_NAME AS PRCS_NAME," + "F.CREATE_TIME AS CREATE_TIME," + "F.END_TIME AS END_TIME,"
                + "F.ACCOUNT_ID AS ACCOUNT_ID," + "F.USER_NAME AS USER_NAME," + "F.IDEA AS IDEA,"
                + "F.IDEA_TEXT AS IDEA_TEXT," + "U.HEAD_IMG AS HEAD_IMG "
                + "FROM FLOW_RUN_PRCS F,USER_INFO U WHERE F.ACCOUNT_ID=U.ACCOUNT_ID AND RUN_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, runId);
        rs = ps.executeQuery();
        while (rs.next()) {
            JSONObject json = new JSONObject();
            json.accumulate("runPrcsId", rs.getString("RUN_PRCS_ID"));
            json.accumulate("prcsId", rs.getString("PRCS_ID"));
            json.accumulate("prcsName", rs.getString("PRCS_NAME"));
            json.accumulate("userName", rs.getString("USER_NAME"));
            json.accumulate("createTime", rs.getString("CREATE_TIME"));
            json.accumulate("headImg", rs.getString("HEAD_IMG"));
            if (SysTool.isNullorEmpty(rs.getString("END_TIME"))) {
                json.accumulate("endTime", "");

            } else {
                json.accumulate("endTime", rs.getString("END_TIME"));
            }
            json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
            if (SysTool.isNullorEmpty(rs.getString("IDEA"))) {
                json.accumulate("idea", "0");
            } else {
                json.accumulate("idea", rs.getString("IDEA"));
            }
            json.accumulate("ideaText", rs.getString("IDEA_TEXT"));
            jsonArr.add(json);
        }
        rs.close();
        ps.close();
        return jsonArr.toString();
    }

    // 获取当前人员的发起的历史流程
    public String getHistoryWorkFlowLogic(Connection conn, Account account) throws SQLException {
        JSONArray jsonArr = new JSONArray();
        String queryStr = "";
        String dbType = DbPoolConnection.getInstance().getDbType();
        if (dbType.equals("mysql")) {
            queryStr = "SELECT DISTINCT F.FLOW_ID AS FLOW_ID,W.FLOW_NAME AS FLOW_NAME FROM"
                    + " FLOW_RUN F,WORK_FLOW W " + " WHERE" + " F.FLOW_ID=W.FLOW_TYPE_ID"
                    + " AND F.CREATE_USER = ?" + " AND F.ORG_ID = ?"
                    + " ORDER BY F.BEGIN_TIME DESC LIMIT 0,5";
        } else if (dbType.equals("oracle")) {
            queryStr = "SELECT * FROM" + "("
                    + "SELECT DISTINCT F.FLOW_ID AS FLOW_ID,W.FLOW_NAME AS FLOW_NAME FROM"
                    + " FLOW_RUN F,WORK_FLOW W " + " WHERE" + " F.FLOW_ID=W.FLOW_TYPE_ID"
                    + " AND F.CREATE_USER = ?" + " AND F.ORG_ID = ?" + " ORDER BY F.BEGIN_TIME DESC"
                    + ") TMP WHERE ROWNUM<=5";
        } else if (dbType.equals("sqlserver")) {

        }
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, account.getAccountId());
        ps.setString(2, account.getOrgId());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            JSONObject json = new JSONObject();
            json.accumulate("flowTypeId", rs.getString("FLOW_ID"));
            json.accumulate("flowName", rs.getString("FLOW_NAME"));
            jsonArr.add(json);
        }
        rs.close();
        ps.close();
        return jsonArr.toString();
    }

    // 多人加签
    public int addOpUsersLogic(Connection conn, String flowId, String runId, int prcsId, int runPrcsId,
            String addOpUser, Account account) throws Exception {
        int returnData = 0;
        if (addOpUser.endsWith(",")) {
            addOpUser = addOpUser.substring(0, addOpUser.length() - 1);
        }
        if (addOpUser.indexOf(",") > 0) {
            String[] tmp = addOpUser.split(",");
            for (int i = 0; tmp.length > i; i++) {
                returnData = this.addOpUserLoigc(conn, flowId, runId, prcsId, runPrcsId, tmp[i], account);
            }
        } else {
            returnData = this.addOpUserLoigc(conn, flowId, runId, prcsId, runPrcsId, addOpUser, account);
        }
        return returnData;
    }

    // 添加步骤
    public int addFlowRunPrcsLogic(Connection conn, FlowRunPrcs flowRunPrcs) throws SQLException {
        int returnData = 0;
        String queryStr = "INSERT INTO FLOW_RUN_PRCS(RUN_ID,FLOW_ID,RUN_PRCS_ID,PRCS_ID,OP_FLAG,TABLE_NAME,PRCS_NAME,ACCOUNT_ID,USER_NAME,DEPT_ID,USER_PRIV_ID,LEAD_ID,CREATE_TIME,END_TIME,PASS,IDEA,STATUS,IDEA_TEXT,ATTACH_ID) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, flowRunPrcs.getRunId());
        ps.setString(2, flowRunPrcs.getFlowId());
        ps.setInt(3, flowRunPrcs.getRunPrcsId());
        ps.setInt(4, flowRunPrcs.getPrcsId());
        ps.setString(5, "1");
        ps.setString(6, flowRunPrcs.getTableName());
        ps.setString(7, flowRunPrcs.getPrcsName());
        ps.setString(8, flowRunPrcs.getAccountId());
        ps.setString(9, flowRunPrcs.getUserName());
        ps.setString(10, flowRunPrcs.getDeptId());
        ps.setString(11, flowRunPrcs.getUserPrivId());
        ps.setString(12, flowRunPrcs.getLeadId());
        ps.setString(13, flowRunPrcs.getCreateTime());
        ps.setString(14, "");
        ps.setString(15, "0");
        ps.setString(16, "0");
        ps.setString(17, "0");
        ps.setString(18, "");
        ps.setString(19, "");
        returnData = ps.executeUpdate();
        ps.close();
        return returnData;
    }

    // 单人加签
    public int addOpUserLoigc(Connection conn, String flowId, String runId, int prcsId, int runPrcsId,
            String addOpUser, Account account) throws Exception {
        FlowRunPrcs flowRunPrcs = new FlowRunPrcs();
        flowRunPrcs = this.getFlowRunPrcs(conn, runId, prcsId, runPrcsId, account);
        UserInfo userInfo = new UserInfo();
        UserInfoLogic userInfoLogic = new UserInfoLogic();
        userInfo = userInfoLogic.getUserInfoByAccountId(conn, addOpUser);
        String createTime = SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss");
        String queryStr = "INSERT INTO FLOW_RUN_PRCS(RUN_ID,FLOW_ID,RUN_PRCS_ID,PRCS_ID,OP_FLAG,TABLE_NAME,PRCS_NAME,ACCOUNT_ID,USER_NAME,DEPT_ID,USER_PRIV_ID,LEAD_ID,CREATE_TIME,END_TIME,PASS,IDEA,STATUS,IDEA_TEXT,ATTACH_ID) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, runId);
        ps.setString(2, flowId);
        ps.setInt(3, runPrcsId);
        ps.setInt(4, prcsId);
        ps.setString(5, "1");
        ps.setString(6, flowRunPrcs.getTableName());
        ps.setString(7, flowRunPrcs.getPrcsName());
        ps.setString(8, userInfo.getAccountId());
        ps.setString(9, userInfo.getUserName());
        ps.setString(10, userInfo.getDeptId());
        ps.setString(11, userInfo.getPostPriv());
        ps.setString(12, userInfo.getLeadId());
        ps.setString(13, createTime);
        ps.setString(14, "");
        ps.setString(15, "");
        ps.setString(16, "");
        ps.setString(17, "");
        ps.setString(18, "");
        ps.setString(19, "");
        ps.executeUpdate();
        ps.close();
        WorkListLogic workListLogic = new WorkListLogic();
        WorkList workList = new WorkList();
        workList = workListLogic.getWorkListLogic(conn, runId, flowRunPrcs.getPrcsId(),
                flowRunPrcs.getRunPrcsId());
        workList.setAccountId(addOpUser);
        workList.setStatus("0");
        int returnData = workListLogic.createDoRecordLogic(conn, workList);
        return returnData;
    }

    // 设置人员观注
    public int setFollowLogic(Connection conn, String runId, String runPrcsId, String prcsId, String flag,
            Account account) throws SQLException {
        String queryStr = "UPDATE FLOW_RUN_PRCS SET FOLLOW =? WHERE RUN_ID=? AND RUN_PRCS_ID=? AND PRCS_ID=? AND ACCOUNT_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, flag);
        ps.setString(2, runId);
        ps.setString(3, runPrcsId);
        ps.setString(4, prcsId);
        ps.setString(5, account.getAccountId());
        int i = ps.executeUpdate();
        ps.close();
        return i;
    }

    // 删除流程数据
    public int delFlowRunPrcsByFlowIdLogci(Connection conn, String flowId) throws SQLException {
        String queryStr = "DELETE FROM FLOW_RUN_PRCS WHERE FLOW_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, flowId);
        int i = ps.executeUpdate();
        ps.close();
        return i;
    }

    // 获取历史办理的所有步骤
    public String getHistoryPrcsLogic(Connection conn, String runId, String runPrcsId) throws SQLException {
        JSONArray jsonArr = new JSONArray();
        String queryStr = "SELECT ID,PRCS_ID,PRCS_NAME,USER_NAME FROM FLOW_RUN_PRCS WHERE RUN_ID=? AND OP_FLAG='0' AND RUN_PRCS_ID<? ORDER BY RUN_PRCS_ID DESC";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, runId);
        ps.setString(2, runPrcsId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            JSONObject json = new JSONObject();
            json.accumulate("prcsId", rs.getString("PRCS_ID"));
            json.accumulate("Id", rs.getString("ID"));
            json.accumulate("prcsName", rs.getString("PRCS_NAME"));
            json.accumulate("userName", rs.getString("USER_NAME"));
            jsonArr.add(json);
        }
        rs.close();
        ps.close();
        return jsonArr.toString();
    }

    // 获取上一办理步骤
    public String getgetAStepLogic(Connection conn, String runId, String runPrcsId) throws SQLException {
        JSONObject json = new JSONObject();
        String queryStr = "SELECT ID,PRCS_ID,PRCS_NAME,USER_NAME FROM FLOW_RUN_PRCS WHERE RUN_ID=? AND OP_FLAG='0' AND RUN_PRCS_ID=? ORDER BY RUN_PRCS_ID DESC";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, runId);
        ps.setInt(2, Integer.parseInt(runPrcsId) - 1);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            json.accumulate("prcsId", rs.getString("PRCS_ID"));
            json.accumulate("Id", rs.getString("ID"));
            json.accumulate("prcsName", rs.getString("PRCS_NAME"));
            json.accumulate("userName", rs.getString("USER_NAME"));
        }
        rs.close();
        ps.close();
        return json.toString();
    }

    // 执行回退操作
    public int goBackSeptLogic(Connection conn, Account account, String runId, int Id, int runPrcsId,
            int prcsId) throws SQLException {
        int runPrcsIdValue = runPrcsId + 1;
        String queryStr = "INSERT INTO FLOW_RUN_PRCS(RUN_ID,FLOW_ID,RUN_PRCS_ID,PRCS_ID,OP_FLAG,TABLE_NAME,PRCS_NAME,ACCOUNT_ID,USER_NAME,DEPT_ID,USER_PRIV_ID,LEAD_ID,CREATE_TIME,PASS,IDEA,STATUS,IDEA_TEXT,FOLLOW,ATTACH_ID)"
                + "SELECT RUN_ID,FLOW_ID,"
                + runPrcsIdValue
                + " AS RUN_PRCS_ID,PRCS_ID,OP_FLAG,TABLE_NAME,PRCS_NAME,ACCOUNT_ID,USER_NAME,DEPT_ID,USER_PRIV_ID,LEAD_ID,CREATE_TIME,0 AS PASS,IDEA,0 AS STATUS,IDEA_TEXT,FOLLOW,ATTACH_ID FROM FLOW_RUN_PRCS WHERE ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setInt(1, Id);
        ps.executeUpdate();
        ps.close();
        this.updateStatusLogic(conn, runId, prcsId, account.getAccountId(), runPrcsId);
        FlowRunPrcs flowRunPrcs = new FlowRunPrcs();
        FlowRunPrcsLogic flowRunPrcsLogic = new FlowRunPrcsLogic();
        flowRunPrcs = flowRunPrcsLogic.getFlowRunPrcs(conn, Id);
        WorkListLogic workListLogic = new WorkListLogic();
        workListLogic.updateStatusLogic(conn, runId, runPrcsId, account.getAccountId());
        WorkList workList = new WorkList();
        workList = workListLogic.getWorkListLogic(conn, runId, flowRunPrcs.getPrcsId(),
                flowRunPrcs.getRunPrcsId());
        String URL = workList.getUrl();
        URL = URL.substring(0, URL.lastIndexOf("=") + 1) + runPrcsIdValue;
        workList.setUrl(URL);
        workList.setStatus("0");
        workList.setRunPrcsId(runPrcsIdValue);
        int returnData = workListLogic.createDoRecordLogic(conn, workList);
        return returnData;
    }

    public String getAllRunPrcsByRunIdLogic(Connection conn, String runId) throws SQLException {
        JSONArray jsonArr = new JSONArray();
        String queryStr = "SELECT RUN_PRCS_ID,PRCS_ID,PRCS_NAME,ACCOUNT_ID,USER_NAME,PASS,IDEA,IDEA_TEXT,CREATE_TIME,END_TIME,STATUS FROM FLOW_RUN_PRCS WHERE RUN_ID=? ORDER BY RUN_PRCS_ID DESC";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, runId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            JSONObject json = new JSONObject();
            json.accumulate("runPrcsId", rs.getString("RUN_PRCS_ID"));
            json.accumulate("prcsName", rs.getString("PRCS_NAME"));
            json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
            json.accumulate("createTime", rs.getString("CREATE_TIME"));
            json.accumulate("prcsId", rs.getString("PRCS_ID"));
            if (SysTool.isNullorEmpty(rs.getString("END_TIME"))) {
                json.accumulate("endTime", "当前");
            } else {
                json.accumulate("endTime", rs.getString("END_TIME"));
            }
            json.accumulate("status", rs.getString("STATUS"));
            json.accumulate("userName", rs.getString("USER_NAME"));
            json.accumulate("pass", rs.getString("PASS"));
            json.accumulate("idea", rs.getString("IDEA"));
            json.accumulate("ideaText", rs.getString("IDEA_TEXT"));
            jsonArr.add(json);
        }
        rs.close();
        ps.close();
        return jsonArr.toString();
    }

    public int getRunPrcsId(Connection conn, String runId) throws SQLException {
        int returnData = 0;
        String queryStr = "SELECT MAX(RUN_PRCS_ID) AS MAXRUNPRCSID FROM FLOW_RUN_PRCS WHERE RUN_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, runId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            returnData = rs.getInt("MAXRUNPRCSID");
        }
        rs.close();
        ps.close();
        return returnData;
    }

    public Map<String, List<String>> getOpUserListLogic(Connection conn, String runId) throws SQLException {
        List<String> userAccountIdList = new ArrayList<String>();
        List<String> userNameList = new ArrayList<String>();
        String queryStr = "SELECT ACCOUNT_ID,USER_NAME FROM FLOW_RUN_PRCS WHERE RUN_ID=? AND STATUS=0";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, runId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            userAccountIdList.add(rs.getString("ACCOUNT_ID"));
            userNameList.add(rs.getString("USER_NAME"));
        }
        rs.close();
        ps.close();
        Map<String, List<String>> userMap = new HashMap<String, List<String>>();
        userMap.put("userAccountIdList", userAccountIdList);
        userMap.put("userNameList", userNameList);
        return userMap;
    }

    public FlowRunPrcs getFlowRunPrcs(Connection conn, String runId, int runPrcsId) throws SQLException {
        FlowRunPrcs flowRunPrcs = new FlowRunPrcs();
        ResultSet rs = null;
        String queryStr = "SELECT ID,RUN_ID,FLOW_ID,RUN_PRCS_ID,PRCS_ID,DEPT_ID,OP_FLAG,TABLE_NAME,PRCS_NAME,ACCOUNT_ID,USER_NAME,DEPT_ID,USER_PRIV_ID,"
                + "LEAD_ID,CREATE_TIME,END_TIME,PASS,IDEA,STATUS,IDEA_TEXT,ATTACH_ID,FOLLOW FROM FLOW_RUN_PRCS WHERE RUN_PRCS_ID=? AND RUN_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setInt(1, runPrcsId);
        ps.setString(2, runId);
        rs = ps.executeQuery();
        if (rs.next()) {
            flowRunPrcs.setId(rs.getInt("ID"));
            flowRunPrcs.setRunId(rs.getString("RUN_ID"));
            flowRunPrcs.setFlowId(rs.getString("FLOW_ID"));
            flowRunPrcs.setRunPrcsId(rs.getInt("RUN_PRCS_ID"));
            flowRunPrcs.setPrcsId(rs.getInt("PRCS_ID"));
            flowRunPrcs.setDeptId(rs.getString("DEPT_ID"));
            flowRunPrcs.setOpFlag(rs.getString("OP_FLAG"));
            flowRunPrcs.setTableName(rs.getString("TABLE_NAME"));
            flowRunPrcs.setPrcsName(rs.getString("PRCS_NAME"));
            flowRunPrcs.setAccountId(rs.getString("ACCOUNT_ID"));
            flowRunPrcs.setDeptId(rs.getString("DEPT_ID"));
            flowRunPrcs.setUserName(rs.getString("USER_NAME"));
            flowRunPrcs.setUserPrivId(rs.getString("USER_PRIV_ID"));
            flowRunPrcs.setLeadId(rs.getString("LEAD_ID"));
            flowRunPrcs.setCreateTime(rs.getString("CREATE_TIME"));
            flowRunPrcs.setEndTime(rs.getString("END_TIME"));
            flowRunPrcs.setPass(rs.getString("PASS"));
            flowRunPrcs.setIdea(rs.getString("IDEA"));
            flowRunPrcs.setStatus(rs.getString("STATUS"));
            flowRunPrcs.setIdeaText(rs.getString("IDEA_TEXT"));
            flowRunPrcs.setFollow(rs.getString("FOLLOW"));
            flowRunPrcs.setAttachId(rs.getString("ATTACH_ID"));
        }
        rs.close();
        ps.close();
        return flowRunPrcs;
    }

    // 工作流较交
    public int deliverWorkLogic(Connection conn, String flowId, Account account, String changeA,
            String toChangeB) throws Exception {
        UserInfo userInfo = new UserInfo();
        UserInfoLogic userInfoLogic = new UserInfoLogic();
        userInfo = userInfoLogic.getUserInfoByAccountId(conn, toChangeB);
        String queryStr = "UPDATE FLOW_RUN_PRCS SET " + "ACCOUNT_ID=?, " + "DEPT_ID=?," + "USER_PRIV_ID=?,"
                + "LEAD_ID=?  " + "WHERE " + "PRCS_ID>1 " + "AND STATUS=0 " + "AND ACCOUNT_ID=? "
                + "AND FLOW_ID=? " + "AND ORG_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, userInfo.getAccountId());
        ps.setString(2, userInfo.getDeptId());
        ps.setString(3, userInfo.getUserPirvId());
        ps.setString(4, userInfo.getLeadId());
        ps.setString(5, changeA);
        ps.setString(6, flowId);
        ps.setString(7, account.getOrgId());
        int i = ps.executeUpdate();
        ps.close();
        return i;
    }
}