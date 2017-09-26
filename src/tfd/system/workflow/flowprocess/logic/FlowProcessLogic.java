package tfd.system.workflow.flowprocess.logic;

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
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.dept.logic.DeptLogic;
import tfd.system.unit.userpriv.logic.UserPrivLogic;
import tfd.system.workflow.flowformitem.logic.FLowFormItemLogic;
import tfd.system.workflow.flowprocess.data.FlowProcess;
import tfd.system.workflow.workflow.data.WorkFlow;
import tfd.system.workflow.workflow.logic.WorkFlowLogic;

import com.system.tool.SysTool;

public class FlowProcessLogic {
    // 初始化生成图形JSON
    public String getFlowProcessListLogic(Connection conn, String flowId) throws SQLException {
        JSONObject jsontmp = new JSONObject();
        jsontmp.accumulate("readOnly", "1");
        jsontmp.accumulate("createUpLoad", "1");
        jsontmp.accumulate("edit", "1");
        jsontmp.accumulate("down", "1");
        WorkFlowLogic workFlow = new WorkFlowLogic();
        String formId = workFlow.getFormIdByFlowTypeIdLogic(conn, flowId);
        String query = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flage = true;
        query = "SELECT COUNT(*) FROM FLOW_PROCESS WHERE FLOW_ID='" + flowId + "'";
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        if (rs.next()) {
            if (rs.getInt(1) > 0) {
                flage = false;
            }
        }
        ps.close();
        if (flage) {
            query = "INSERT INTO FLOW_PROCESS (FLOW_ID,FORM_ID,PRCS_ID,PRCS_NAME,PRCS_TYPE,PRINT_X,PRINT_Y,NEXT_PRCS,PUBLIC_FILE)VALUES(?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, flowId);
            ps.setString(2, formId);
            ps.setInt(3, 1);
            ps.setString(4, "开始");
            ps.setString(5, "1");
            ps.setString(6, "200");
            ps.setString(7, "200");
            ps.setString(8, "0");
            ps.setString(9, jsontmp.toString());
            ps.executeUpdate();
            ps.close();
            query = "INSERT INTO FLOW_PROCESS (FLOW_ID,FORM_ID,PRCS_ID,PRCS_NAME,PRCS_TYPE,PRINT_X,PRINT_Y)VALUES(?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, flowId);
            ps.setString(2, formId);
            ps.setInt(3, 0);
            ps.setString(4, "结束");
            ps.setString(5, "2");
            ps.setString(6, "800");
            ps.setString(7, "200");
            ps.executeUpdate();
            ps.close();
        }
        query = "SELECT ID,FLOW_ID,FORM_ID,PRCS_ID,PRCS_NAME,NEXT_PRCS,PRCS_TYPE,PRINT_X,PRINT_Y FROM FLOW_PROCESS WHERE FLOW_ID='"
                + flowId + "'";
        JSONArray jsonArr = new JSONArray();
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            JSONObject json = new JSONObject();
            if (SysTool.isNullorEmpty(rs.getString("NEXT_PRCS"))) {
                json.accumulate("params", "{\"nextPrcs\":[]}");
            } else {
                json.accumulate("params", "{\"nextPrcs\":[" + rs.getString("NEXT_PRCS") + "]}");
            }
            json.accumulate("x", rs.getString("PRINT_X"));
            json.accumulate("y", rs.getString("PRINT_Y"));
            json.accumulate("sid", rs.getString("PRCS_ID"));
            json.accumulate("prcsName", rs.getString("PRCS_NAME"));
            json.accumulate("prcsId", rs.getString("PRCS_ID"));
            json.accumulate("prcsType", rs.getString("PRCS_TYPE"));
            jsonArr.add(json);
        }
        rs.close();
        ps.close();
        return jsonArr.toString();
    }

    // 保存图形布局
    public int updateFlowProcessLayoutLogic(Connection conn, String[] layoutStr, String flowId)
            throws SQLException {
        String prcsid = "";
        String printx = "";
        String printy = "";
        int j = 0;
        PreparedStatement ps = null;
        String query = "UPDATE FLOW_PROCESS SET PRINT_X=?,PRINT_Y=? WHERE FLOW_ID=? AND PRCS_ID=?";
        for (int i = 0; i < layoutStr.length; i++) {
            prcsid = layoutStr[i].split(",")[0].split(":")[1];
            printx = layoutStr[i].split(",")[1].split(":")[1];
            printy = layoutStr[i].split(",")[2].split(":")[1];
            ps = conn.prepareStatement(query);
            ps.setString(1, printx);
            ps.setString(2, printy);
            ps.setString(3, flowId);
            ps.setString(4, prcsid);
            j = ps.executeUpdate();
        }
        ps.close();
        return j;
    }

    // 添加并发结点
    public String addParalleNodelogic(Connection conn, String flowId, String prcsType, String parentId,
            String printx, String printy) throws SQLException {
        JSONObject jsontmp = new JSONObject();
        jsontmp.accumulate("readOnly", "1");
        jsontmp.accumulate("createUpLoad", "1");
        jsontmp.accumulate("edit", "1");
        jsontmp.accumulate("down", "1");
        WorkFlowLogic workFlow = new WorkFlowLogic();
        String formId = workFlow.getFormIdByFlowTypeIdLogic(conn, flowId);
        PreparedStatement ps = null;
        String query;
        int prcsId = 0;
        ResultSet rs = null;
        String nextPrcs = "";
        query = "SELECT MAX(PRCS_ID) AS MAX_PRCS_ID FROM FLOW_PROCESS WHERE FLOW_ID='" + flowId + "'";
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        if (rs.next()) {
            prcsId = rs.getInt("MAX_PRCS_ID") + 1;
        }

        query = "SELECT NEXT_PRCS FROM FLOW_PROCESS WHERE FLOW_ID='" + flowId + "' AND PRCS_ID='" + parentId
                + "'";
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        if (rs.next()) {
            nextPrcs = rs.getString("NEXT_PRCS");
        }
        if (nextPrcs.equals("") || nextPrcs == null) {
            nextPrcs = prcsId + "";
        } else {
            nextPrcs = nextPrcs + "," + prcsId;
        }
        query = "UPDATE FLOW_PROCESS SET NEXT_PRCS='" + nextPrcs + "' WHERE FLOW_ID='" + flowId
                + "' AND PRCS_ID='" + parentId + "'";
        ps = conn.prepareStatement(query);
        ps.executeUpdate();
        query = "INSERT FLOW_PROCESS (FLOW_ID,FORM_ID,PRCS_ID,PRCS_TYPE,PRINT_X,PRINT_Y,PRCS_NAME,PUBLIC_FILE) VALUES(?,?,?,?,?,?,?,?)";
        ps = conn.prepareStatement(query);
        ps.setString(1, flowId);
        ps.setString(2, formId);
        ps.setInt(3, prcsId);
        ps.setString(4, prcsType);
        ps.setString(5, printx);
        ps.setString(6, printy);
        String prcsName = "";
        if (prcsType.equals("6")) {
            prcsName = "子流程节点" + prcsId;
        } else {
            prcsName = "并发结点" + prcsId;
        }
        ps.setString(7, prcsName);
        ps.setString(8, jsontmp.toString());
        ps.executeUpdate();
        rs.close();
        ps.close();
        JSONObject json = new JSONObject();
        json.accumulate("prcsId", prcsId);
        json.accumulate("sid", "1");
        json.accumulate("prcsName", prcsName);
        return json.toString();
    }

    // 添加普通节点
    public String addSimpleNodelogic(Connection conn, String flowId, String prcsType, String parentId,
            String printx, String printy) throws SQLException {
        JSONObject jsontmp = new JSONObject();
        jsontmp.accumulate("readOnly", "1");
        jsontmp.accumulate("createUpLoad", "1");
        jsontmp.accumulate("edit", "1");
        jsontmp.accumulate("down", "1");
        WorkFlowLogic workFlow = new WorkFlowLogic();
        String formId = workFlow.getFormIdByFlowTypeIdLogic(conn, flowId);
        PreparedStatement ps = null;
        String query;
        int prcsId = 0;
        ResultSet rs = null;
        String nextPrcs = "";
        query = "SELECT MAX(PRCS_ID) AS MAX_PRCS_ID FROM FLOW_PROCESS WHERE FLOW_ID='" + flowId + "'";
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        if (rs.next()) {
            prcsId = rs.getInt("MAX_PRCS_ID") + 1;
        }
        query = "SELECT NEXT_PRCS FROM FLOW_PROCESS WHERE FLOW_ID='" + flowId + "' AND PRCS_ID='" + parentId
                + "'";
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        if (rs.next()) {
            nextPrcs = rs.getString("NEXT_PRCS");
        }
        if (nextPrcs.equals("") || nextPrcs == null) {
            nextPrcs = prcsId + "";
        } else {
            nextPrcs = nextPrcs + "," + prcsId;
        }
        query = "UPDATE FLOW_PROCESS SET NEXT_PRCS='" + nextPrcs + "' WHERE FLOW_ID='" + flowId
                + "' AND PRCS_ID='" + parentId + "'";
        ps = conn.prepareStatement(query);
        ps.executeUpdate();
        query = "INSERT INTO FLOW_PROCESS (FLOW_ID,FORM_ID,PRCS_ID,PRCS_TYPE,PRINT_X,PRINT_Y,PRCS_NAME,PUBLIC_FILE) VALUES(?,?,?,?,?,?,?,?)";
        JSONObject json = new JSONObject();
        ps = conn.prepareStatement(query);
        ps.setString(1, flowId);
        ps.setString(2, formId);
        ps.setInt(3, prcsId);
        ps.setString(4, prcsType);
        ps.setString(5, printx);
        ps.setString(6, printy);
        String prcsName = "";
        if (prcsType.equals("6")) {
            prcsName = "子流程节点" + prcsId;
        } else {
            prcsName = "普通步骤" + prcsId;
        }
        ps.setString(7, prcsName);
        ps.setString(8, jsontmp.toString());
        ps.executeUpdate();
        rs.close();
        ps.close();
        json.accumulate("prcsId", prcsId);
        json.accumulate("sid", "1");
        json.accumulate("prcsName", prcsName);
        return json.toString();
    }

    // 删除节点
    public void removeNodeLoic(Connection conn, String prcsId, String flowId) throws SQLException {
        PreparedStatement ps = null;
        String query;
        query = "UPDATE FLOW_PROCESS SET NEXT_PRCS=SUBSTR(REPLACE(CONCAT(',',NEXT_PRCS,','),'," + prcsId
                + ",',','),2,LENGTH(REPLACE(CONCAT(',',NEXT_PRCS,','),'," + prcsId
                + ",',','))-2) WHERE INSTR(CONCAT(',',NEXT_PRCS,','),'," + prcsId + ",')";
        ps = conn.prepareStatement(query);
        ps.executeUpdate();
        query = "DELETE FROM FLOW_PROCESS WHERE PRCS_ID=? AND FLOW_ID=?";
        ps = conn.prepareStatement(query);
        ps.setString(1, prcsId);
        ps.setString(2, flowId);
        ps.executeUpdate();
        ps.close();
    }

    // 获取步骤可写字段
    public String getWriterByFlowIdLogic(Connection conn, String prcsId, String flowId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        JSONArray jsonArr = new JSONArray();
        String query;
        String writerField = "";
        String[] tmpArray = null;
        String formId = null;
        query = "SELECT FORM_ID,WRITER_FIELD FROM FLOW_PROCESS WHERE PRCS_ID=? AND FLOW_ID=?";
        ps = conn.prepareStatement(query);
        ps.setString(1, prcsId);
        ps.setString(2, flowId);
        rs = ps.executeQuery();
        if (rs.next()) {
            writerField = rs.getString("WRITER_FIELD");
            formId = rs.getString("FORM_ID");
        }
        if (!SysTool.isNullorEmpty(writerField)) {
            FLowFormItemLogic logic = new FLowFormItemLogic();
            tmpArray = writerField.split(",");

            for (int i = 0; i < tmpArray.length; i++) {
                JSONObject json = new JSONObject();
                json.accumulate("text", logic.getTitleBy(conn, formId, tmpArray[i]));
                json.accumulate("id", tmpArray[i]);
                jsonArr.add(json);
            }
        }
        rs.close();
        ps.close();
        return jsonArr.toString();
    }

    // 更新可写字段
    public void updateWriterFieldLogic(Connection conn, String flowId, int prcsId, String writerFieldStr,
            String publicFile) throws SQLException {
        PreparedStatement ps = null;
        String query;
        query = "UPDATE FLOW_PROCESS SET WRITER_FIELD=?,PUBLIC_FILE=? WHERE FLOW_ID=? AND PRCS_ID=?";
        ps = conn.prepareStatement(query);
        ps.setString(1, writerFieldStr);
        ps.setString(2, publicFile);
        ps.setString(3, flowId);
        ps.setInt(4, prcsId);
        ps.executeUpdate();
        ps.close();
    }

    // 获取表单FORM_ID通过FLOW_ID
    public String getFormIdbyFlowIdLogic(Connection conn, String flowId, int prcsId) throws SQLException {
        String returndate = "";
        String query = "SELECT FORM_ID FROM FLOW_PROCESS WHERE FLOW_ID=? AND PRCS_ID=?";
        ResultSet rs = null;
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, flowId);
        ps.setInt(2, prcsId);
        rs = ps.executeQuery();
        if (rs.next()) {
            returndate = rs.getString("FORM_ID");
        }
        rs.close();
        ps.close();
        return returndate;
    }

    // 获取只读字段
    public List<Map<String, String>> getReadOnlyField(Connection conn, String flowId, int prcsId)
            throws SQLException {
        ResultSet rs = null;
        PreparedStatement ps = null;
        String query = "";
        List<Map<String, String>> fieldList = new ArrayList<Map<String, String>>();
        String writerFieldStr = "";
        query = "SELECT WRITER_FIELD FROM FLOW_PROCESS WHERE PRCS_ID=? AND FLOW_ID=?";
        ps = conn.prepareStatement(query);
        ps.setInt(1, prcsId);
        ps.setString(2, flowId);
        rs = ps.executeQuery();
        if (rs.next()) {
            writerFieldStr = rs.getString("WRITER_FIELD");
        }
        query = "SELECT FIELD_NAME,XTYPE FROM FLOW_FORM_ITEM WHERE FORM_ID=?";
        FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
        ps = conn.prepareStatement(query);
        ps.setString(1, flowProcessLogic.getFormIdbyFlowIdLogic(conn, flowId, prcsId));
        rs = ps.executeQuery();
        while (rs.next()) {
            Map<String, String> map = new HashMap<String, String>();
            if (SysTool.isNullorEmpty(writerFieldStr)) {
                map.put("xtype", rs.getString("XTYPE"));
                map.put("fieldName", rs.getString("FIELD_NAME"));
                fieldList.add(map);
            } else {
                writerFieldStr = "," + writerFieldStr + ",";
                if (writerFieldStr.indexOf("," + rs.getString("FIELD_NAME") + ",") < 0) {
                    map.put("xtype", rs.getString("XTYPE"));
                    map.put("fieldName", rs.getString("FIELD_NAME"));
                    fieldList.add(map);
                }
            }
        }
        rs.close();
        ps.close();
        return fieldList;
    }

    // 获取PRCS_NAME
    public String getFlowProcessPrcsNameLogic(Connection conn, String flowId, int prcsId) throws SQLException {
        String returndate = "";
        String query = "SELECT PRCS_NAME FROM FLOW_PROCESS WHERE FLOW_ID=? AND PRCS_ID=?";
        ResultSet rs = null;
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, flowId);
        ps.setInt(2, prcsId);
        rs = ps.executeQuery();
        if (rs.next()) {
            returndate = rs.getString("PRCS_NAME");
        }
        rs.close();
        ps.close();
        return returndate;
    }

    // 获取下一步节点列表
    public String getFlorProcessNextLogic(Connection conn, String flowId, int prcsId) throws SQLException {
        String returnData = "";
        String queryStr = "SELECT NEXT_PRCS FROM FLOW_PROCESS WHERE FLOW_ID=? AND PRCS_ID=?";
        ResultSet rs = null;
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, flowId);
        ps.setInt(2, prcsId);
        rs = ps.executeQuery();
        if (rs.next()) {
            returnData = rs.getString("NEXT_PRCS");
        }
        return returnData;
    }

    // 获取一步列表中步骤的相关信息
    public String getConditionJson(Connection conn, String flowId, int prcsId, String nextPrcsList)
            throws SQLException {
        if (SysTool.isNullorEmpty(nextPrcsList)) {
            return "";
        }
        JSONObject json = new JSONObject();
        JSONObject json1 = new JSONObject();
        JSONArray jsonArr = new JSONArray();
        json1.accumulate("prcsId", prcsId);
        json1.accumulate("prcsName", getFlowProcessPrcsNameLogic(conn, flowId, prcsId));
        json.accumulate("prcs", json1);
        if (nextPrcsList.indexOf(",") > 0) {
            String[] nextPrcs = nextPrcsList.split(",");
            for (int i = 0; nextPrcs.length > i; i++) {
                JSONObject json2 = new JSONObject();
                json2.accumulate("prcsId", nextPrcs[i]);
                json2.accumulate("prcsName",
                        getFlowProcessPrcsNameLogic(conn, flowId, Integer.parseInt(nextPrcs[i])));
                jsonArr.add(json2);
            }
        } else {

            JSONObject json2 = new JSONObject();
            json2.accumulate("prcsId", nextPrcsList);
            json2.accumulate("prcsName",
                    getFlowProcessPrcsNameLogic(conn, flowId, Integer.parseInt(nextPrcsList)));
            jsonArr.add(json2);
        }
        json.accumulate("nextPrcs", jsonArr.toString());
        return json.toString();
    }

    // 更新步骤条件
    public void updatePrcsConditionLoigc(Connection conn, String flowId, int prcsId, String condition)
            throws SQLException {
        String queryStr = "UPDATE FLOW_PROCESS SET PRCS_CONDITION=? WHERE FLOW_ID=? AND PRCS_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, condition);
        ps.setString(2, flowId);
        ps.setInt(3, prcsId);
        ps.executeUpdate();
        ps.close();
    }

    // 获取步骤条件
    public String getPrcsConditionLoigc(Connection conn, String flowId, int prcsId) throws SQLException {
        ResultSet rs = null;
        String returnDate = "";
        String queryStr = "SELECT PRCS_CONDITION FROM FLOW_PROCESS WHERE FLOW_ID=? AND PRCS_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, flowId);
        ps.setInt(2, prcsId);
        rs = ps.executeQuery();
        if (rs.next()) {
            returnDate = rs.getString("PRCS_CONDITION");
        }
        rs.close();
        ps.close();
        return returnDate;
    }

    // 更新步骤基本信息
    public void updatePrcsBasicLogic(Connection conn, String flowId, int prcsId, FlowProcess flowProcess)
            throws SQLException {
        String queryStr = "UPDATE FLOW_PROCESS SET PRCS_NAME=?,NEXT_PRCS=? ,PRCS_TYPE=?,MEMO=?,LEAD_PRCS_LEAVE=?,FORCE_AGGREGATION=?,FORCE_PARALLEL=?,GO_BACK=?,FOLLOW=?,PASS_TIME=? WHERE FLOW_ID=? AND PRCS_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, flowProcess.getPrcsName());
        ps.setString(2, flowProcess.getNextPrcs());
        ps.setString(3, flowProcess.getPrcsType());
        ps.setString(4, flowProcess.getMemo());
        ps.setString(5, flowProcess.getLeadPrcsLeave());
        ps.setString(6, flowProcess.getForceAggregation());
        ps.setString(7, flowProcess.getForceParallel());
        ps.setString(8, flowProcess.getGoBack());
        ps.setString(9, flowProcess.getFollow());
        ps.setInt(10, flowProcess.getPassTime());
        ps.setString(11, flowId);
        ps.setInt(12, prcsId);
        ps.executeUpdate();
        ps.close();
    }

    // 获取子流程步骤基本
    public String getPrcsChildLogic(Connection conn, String flowId, int prcsId) throws SQLException {
        WorkFlowLogic workFlowLogic = new WorkFlowLogic();
        WorkFlow workFlow = new WorkFlow();
        FlowProcess flowProcess = getFlowProcessLogic(conn, flowId, prcsId);
        workFlow = workFlowLogic.getWorkFlowByFlowTypeIdLogic(conn, flowProcess.getChildFlow());
        JSONObject json = new JSONObject();
        json.accumulate("prcsId", flowProcess.getPrcsId());
        json.accumulate("flowId", flowProcess.getFlowId());
        json.accumulate("prcsName", flowProcess.getPrcsName());
        json.accumulate("prcsType", flowProcess.getPrcsType());
        json.accumulate("childFlow", flowProcess.getChildFlow());
        json.accumulate("childFlowName", workFlow.getFlowName());
        json.accumulate("parnetWait", flowProcess.getParnetWait());
        json.accumulate("copyToChild", flowProcess.getCopyToChild());
        json.accumulate("shareFlowDoc", flowProcess.getShareFlowDoc());
        return json.toString();
    }

    // 获取步骤基本信息
    public String getPrcsBasicLogic(Connection conn, String flowId, int prcsId) throws SQLException {
        ResultSet rs = null;
        PreparedStatement ps = null;
        String queryStr = "";
        JSONArray jsonArr = new JSONArray();
        JSONArray jsonArr1 = new JSONArray();
        queryStr = "SELECT PRCS_NAME,PRCS_TYPE,PRCS_ID FROM FLOW_PROCESS WHERE FLOW_ID=?  ORDER BY PRCS_ID ASC";
        ps = conn.prepareStatement(queryStr);
        ps.setString(1, flowId);
        rs = ps.executeQuery();
        while (rs.next()) {
            JSONObject json = new JSONObject();
            json.accumulate("prcsId", rs.getString("PRCS_ID"));
            json.accumulate("prcsName", rs.getString("PRCS_NAME"));
            json.accumulate("prcsType", rs.getString("PRCS_TYPE"));
            jsonArr1.add(json);
        }
        queryStr = "SELECT ID,PRCS_TYPE,PRCS_NAME,LEAD_PRCS_LEAVE,FORCE_AGGREGATION,FORCE_PARALLEL,MEMO,NEXT_PRCS,PRCS_ID,GO_BACK,FOLLOW,PASS_TIME FROM FLOW_PROCESS WHERE FLOW_ID=? AND PRCS_ID=? ORDER BY PRCS_ID ASC";
        ps = conn.prepareStatement(queryStr);
        ps.setString(1, flowId);
        ps.setInt(2, prcsId);
        rs = ps.executeQuery();
        if (rs.next()) {
            JSONObject json = new JSONObject();
            json.accumulate("Id", rs.getString("ID"));
            json.accumulate("prcsId", rs.getString("PRCS_ID"));
            json.accumulate("prcsType", rs.getString("PRCS_TYPE"));
            json.accumulate("prcsName", rs.getString("PRCS_NAME"));
            if (SysTool.isNullorEmpty(rs.getString("LEAD_PRCS_LEAVE"))) {
                json.accumulate("leadPrcsLeave", "");
            } else {
                json.accumulate("leadPrcsLeave", rs.getString("LEAD_PRCS_LEAVE"));
            }
            json.accumulate("forceAggregation", rs.getString("FORCE_AGGREGATION"));
            json.accumulate("forceParallel", rs.getString("FORCE_PARALLEL"));
            json.accumulate("goBack", rs.getString("GO_BACK"));
            json.accumulate("passTime", rs.getString("PASS_TIME"));
            json.accumulate("follow", rs.getString("FOLLOW"));
            json.accumulate("memo", rs.getString("MEMO"));
            json.accumulate("nextPrcs", rs.getString("NEXT_PRCS"));
            json.accumulate("prcsAll", jsonArr1);
            jsonArr.add(json);
        }
        rs.close();
        ps.close();
        return jsonArr.toString();
    }

    // 指向结束节点
    public void nextToEndPrcsLogic(Connection conn, String flowId, int prcsId) throws SQLException {
        String nextPrcsStr = getFlorProcessNextLogic(conn, flowId, prcsId);
        String queryStr = "UPDATE FLOW_PROCESS SET NEXT_PRCS=? WHERE FLOW_ID=? AND PRCS_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        if (nextPrcsStr.equals("") || nextPrcsStr.equals(null)) {
            ps.setString(1, "0");
        } else {
            ps.setString(1, nextPrcsStr + ",0");
        }
        ps.setString(2, flowId);
        ps.setInt(3, prcsId);
        ps.executeUpdate();
        ps.close();
    }

    // 设置智能选人规则
    public void setAutoUserTypeLogic(Connection conn, String flowId, int prcsId, String autoUserModle,
            String sPrcsAuto) throws SQLException {
        String queryStr = "UPDATE FLOW_PROCESS SET AUTO_USER_MODLE=?,S_PRCS_AUTO=? WHERE FLOW_ID=? AND PRCS_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, autoUserModle);
        ps.setString(2, sPrcsAuto);
        ps.setString(3, flowId);
        ps.setInt(4, prcsId);
        ps.executeUpdate();
        ps.close();
    }

    // 获取流程步骤智能选人配置
    public String getAutoUserTypeJsonLogic(Connection conn, String flowId, int prcsId) throws SQLException {
        JSONObject json = new JSONObject();
        String queryStr = "SELECT AUTO_USER_MODLE,S_PRCS_AUTO FROM FLOW_PROCESS WHERE FLOW_ID=? AND PRCS_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, flowId);
        ps.setInt(2, prcsId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            JSONObject jsontmp = new JSONObject();
            jsontmp.accumulate("autoUserType", "");
            jsontmp.accumulate("autoUserType", "");
            jsontmp.accumulate("autoUserRule", "");
            json.accumulate("sPrcsAuto", rs.getString("S_PRCS_AUTO"));
            if (!SysTool.isNullorEmpty(rs.getString("AUTO_USER_MODLE"))) {
                jsontmp = JSONObject.fromObject(rs.getString("AUTO_USER_MODLE"));
            }
            if (SysTool.isNullorEmpty(jsontmp.get("autoUserType").toString())) {
                json.accumulate("autoUserTypePrcsId", "");
                json.accumulate("autoUserRule", "");
                json.accumulate("autoFormField", "");
                json.accumulate("autoDeptId", "");
                json.accumulate("defUserId", "");
            } else {
                json.accumulate("autoUserTypePrcsId", jsontmp.get("autoUserType"));
                json.accumulate("autoUserRule", jsontmp.get("autoUserRule"));
                json.accumulate("autoFormField", jsontmp.get("autoFormField"));
                json.accumulate("autoDeptId", jsontmp.get("autoDeptId"));
                DeptLogic deptLogic = new DeptLogic();
                json.accumulate("autoDeptName",
                        deptLogic.getDeptNameStr(conn, jsontmp.get("autoDeptId") + ""));
                json.accumulate("defUserId", jsontmp.get("defUserId"));
                AccountLogic accountLogic = new AccountLogic();
                json.accumulate("defUserName",
                        accountLogic.getUserNameStr(conn, jsontmp.get("defUserId") + ""));
            }
        }
        rs.close();
        ps.close();
        return json.toString();
    }

    // 获取流程所有步骤列表
    public String getFlowProcessSelsect(Connection conn, String flowId) throws SQLException {
        ResultSet rs = null;
        String queryStr = "SELECT PRCS_ID,PRCS_NAME FROM FLOW_PROCESS WHERE FLOW_ID=? ORDER BY PRCS_ID ASC";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, flowId);
        rs = ps.executeQuery();
        JSONArray jsonArr = new JSONArray();
        while (rs.next()) {
            JSONObject json = new JSONObject();
            json.accumulate("id", rs.getString("PRCS_ID"));
            json.accumulate("text", rs.getString("PRCS_NAME"));
            jsonArr.add(json);
        }
        rs.close();
        ps.close();
        return jsonArr.toString();
    }

    // 获取指定列表JSON
    public String getOneChildTableModelpublic(Connection conn, String flowId, int prcsId, String childTable)
            throws SQLException {
        String returnData = "";
        String queryStr = "SELECT CHILD_TABLE_WRITER_FIELD FROM FLOW_PROCESS WHERE FLOW_ID=? AND PRCS_ID=?";
        ResultSet rs = null;
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, flowId);
        ps.setInt(2, prcsId);
        rs = ps.executeQuery();
        if (rs.next()) {
            returnData = rs.getString("CHILD_TABLE_WRITER_FIELD");
        }
        rs.close();
        ps.close();
        if (!SysTool.isNullorEmpty(returnData)) {
            JSONArray jsonArr = JSONArray.fromObject(returnData);
            for (int i = 0; jsonArr.size() > i; i++) {
                if (jsonArr.getJSONObject(i).get("table").equals(childTable)) {
                    return jsonArr.getJSONObject(i).toString();
                }
            }
        }
        return "";
    }

    // 列表列表控件JSON
    public String getChildTableModelLogic(Connection conn, String flowId, int prcsId) throws SQLException {
        String returnData = "";
        String queryStr = "SELECT CHILD_TABLE_WRITER_FIELD FROM FLOW_PROCESS WHERE FLOW_ID=? AND PRCS_ID=?";
        ResultSet rs = null;
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, flowId);
        ps.setInt(2, prcsId);
        rs = ps.executeQuery();
        if (rs.next()) {
            returnData = rs.getString("CHILD_TABLE_WRITER_FIELD");
        }
        rs.close();
        ps.close();
        return returnData;
    }

    // 更新步骤中列表控件权限
    public void updateChildTableModelLogic(Connection conn, String flowId, int prcsId, String model)
            throws SQLException {
        JSONObject json = JSONObject.fromObject(model);
        String source = getChildTableModelLogic(conn, flowId, prcsId);
        JSONArray jsonArr = new JSONArray();
        if (!SysTool.isNullorEmpty(source)) {
            jsonArr = JSONArray.fromObject(source);
        }
        for (int i = 0; jsonArr.size() > i; i++) {
            JSONObject json1 = new JSONObject();
            json1 = jsonArr.getJSONObject(i);
            if (json1.get("table").equals(json.get("table"))) {
                jsonArr.remove(i);
            }
        }
        jsonArr.add(json);
        String queryStr = "UPDATE FLOW_PROCESS SET CHILD_TABLE_WRITER_FIELD=? WHERE FLOW_ID=? AND PRCS_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, jsonArr.toString());
        ps.setString(2, flowId);
        ps.setInt(3, prcsId);
        ps.executeUpdate();
        ps.close();
    }

    // 设置经办权限
    public void updateUserPriv(Connection conn, String flowId, int prcsId, String userId, String deptId,
            String userPrivId) throws SQLException {
        String queryStr = "UPDATE FLOW_PROCESS SET USER_PRIV=?,DEPT_PRIV=?,PRIV_ID=? WHERE FLOW_ID=? AND PRCS_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, userId);
        ps.setString(2, deptId);
        ps.setString(3, userPrivId);
        ps.setString(4, flowId);
        ps.setInt(5, prcsId);
        ps.executeUpdate();
        ps.close();
    }

    // 获取字段类型
    public Map<String, String> getFieldXtypeLogic(Connection conn, String formId) throws SQLException {
        Map<String, String> map = new HashMap<String, String>();
        String queryStr = "SELECT XTYPE,FIELD_NAME FROM FLOW_FORM_ITEM WHERE FORM_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, formId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            map.put(rs.getString("FIELD_NAME"), rs.getString("XTYPE"));
        }
        rs.close();
        ps.close();
        return map;
    }

    // 获取可写字段STR
    public String getWriterFieldStrByPrcsIdLogic(Connection conn, String flowId, int prcsId)
            throws SQLException {
        String returnData = "";
        String queryStr = "SELECT WRITER_FIELD FROM FLOW_PROCESS WHERE FLOW_ID=? AND PRCS_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, flowId);
        ps.setInt(2, prcsId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            returnData = rs.getString("WRITER_FIELD");
        }
        return returnData;
    }

    // 获取所有步骤列表LIST
    public ArrayList<Integer> getAllPrcsIdByFlowIdLogic(Connection conn, String flowId) throws SQLException {
        ArrayList<Integer> list = new ArrayList<Integer>();
        String queryStr = "SELECT PRCS_ID FROM FLOW_PROCESS WHERE FLOW_ID=? AND PRCS_ID<>0";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, flowId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(rs.getInt("PRCS_ID"));
        }
        rs.close();
        ps.close();
        return list;
    }

    // 获用步骤实例
    public FlowProcess getFlowProcessLogic(Connection conn, String flowId, int prcsId) throws SQLException {
        FlowProcess flowProcess = new FlowProcess();
        String queryStr = "SELECT FORM_ID,PRCS_NAME,NEXT_PRCS,PRCS_TYPE,LEAD_PRCS_LEAVE,FORCE_AGGREGATION,FORCE_PARALLEL,PRINT_X,PRINT_Y,PATH,FILE_NAME,USER_PRIV,DEPT_PRIV,PRIV_ID,"
                + "WRITER_FIELD,CHILD_TABLE_WRITER_FIELD,PRCS_CONDITION,MEMO,AUTO_USER_MODLE,S_PRCS_AUTO,CHILD_FLOW,"
                + "PARENT_WAIT,COPY_TO_CHILD,COPY_TO_PARENT,SHARE_FLOW_DOC,PUBLIC_FILE,WAIT_PRCS_ID,GO_BACK,FOLLOW,PASS_TIME,MUST_FIELD,HIDDEN_FIELD,SMS_CONFIG"
                + " FROM FLOW_PROCESS WHERE FLOW_ID=? AND PRCS_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, flowId);
        ps.setInt(2, prcsId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            flowProcess.setFlowId(flowId);
            flowProcess.setFormId(rs.getString("FORM_ID"));
            flowProcess.setPrcsId(prcsId);
            flowProcess.setPrcsName(rs.getString("PRCS_NAME"));
            flowProcess.setNextPrcs(rs.getString("NEXT_PRCS"));
            flowProcess.setLeadPrcsLeave(rs.getString("LEAD_PRCS_LEAVE"));
            flowProcess.setForceAggregation(rs.getString("FORCE_AGGREGATION"));
            flowProcess.setForceParallel(rs.getString("FORCE_PARALLEL"));
            flowProcess.setPrcsType(rs.getString("PRCS_TYPE"));
            flowProcess.setPrintX(rs.getString("PRINT_X"));
            flowProcess.setPrintY(rs.getString("PRINT_Y"));
            flowProcess.setPath(rs.getString("PATH"));
            flowProcess.setFileName(rs.getString("FILE_NAME"));
            flowProcess.setPassTime(rs.getInt("PASS_TIME"));
            if (rs.getString("USER_PRIV") == null) {
                flowProcess.setUserPriv("");
            } else {
                flowProcess.setUserPriv(rs.getString("USER_PRIV"));
            }
            if (rs.getString("DEPT_PRIV") == null) {
                flowProcess.setDeptPriv("");
            } else {
                flowProcess.setDeptPriv(rs.getString("DEPT_PRIV"));
            }
            if (rs.getString("PRIV_ID") == null) {
                flowProcess.setPrivId("");
            } else {
                flowProcess.setPrivId(rs.getString("PRIV_ID"));
            }
            flowProcess.setWriterField(rs.getString("WRITER_FIELD"));
            flowProcess.setChildTableWriterField(rs.getString("CHILD_TABLE_WRITER_FIELD"));
            flowProcess.setPrcsCondition(rs.getString("PRCS_CONDITION"));
            flowProcess.setMemo(rs.getString("MEMO"));
            flowProcess.setAutoUserModle(rs.getString("AUTO_USER_MODLE"));
            flowProcess.setsPrcsAuto(rs.getString("S_PRCS_AUTO"));
            flowProcess.setChildFlow(rs.getString("CHILD_FLOW"));
            flowProcess.setParnetWait(rs.getString("PARENT_WAIT"));
            flowProcess.setCopyToChild(rs.getString("COPY_TO_CHILD"));
            flowProcess.setCopyToParent(rs.getString("COPY_TO_PARENT"));
            flowProcess.setShareFlowDoc(rs.getString("SHARE_FLOW_DOC"));
            flowProcess.setPublicFile(rs.getString("PUBLIC_FILE"));
            flowProcess.setGoBack(rs.getString("GO_BACK"));
            flowProcess.setFollow(rs.getString("FOLLOW"));
            flowProcess.setWaitPrcsId(rs.getString("WAIT_PRCS_ID"));
            if (SysTool.isNullorEmpty(rs.getString("MUST_FIELD"))) {
                flowProcess.setMustField("");
            } else {
                flowProcess.setMustField(rs.getString("MUST_FIELD"));
            }
            flowProcess.setHiddenField(rs.getString("HIDDEN_FIELD"));
            flowProcess.setSmsConfig(rs.getString("SMS_CONFIG"));
        }
        rs.close();
        ps.close();
        return flowProcess;
    }

    // 更新子流程相关信息
    public void updateForChildFlowLogic(Connection conn, FlowProcess flowProcess) throws SQLException {
        String queryStr = "UPDATE FLOW_PROCESS SET PRCS_NAME=?,PRCS_TYPE=?,CHILD_FLOW=?,PARENT_WAIT=?,COPY_TO_CHILD=?,SHARE_FLOW_DOC=? WHERE FLOW_ID=? AND PRCS_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, flowProcess.getPrcsName());
        ps.setString(2, flowProcess.getPrcsType());
        ps.setString(3, flowProcess.getChildFlow());
        ps.setString(4, flowProcess.getParnetWait());
        ps.setString(5, flowProcess.getCopyToChild());
        ps.setString(6, flowProcess.getShareFlowDoc());
        ps.setString(7, flowProcess.getFlowId());
        ps.setInt(8, flowProcess.getPrcsId());
        ps.executeUpdate();
        ps.close();
    }

    // 获取公文附件权限
    public String getPublicFilePrivLogic(Connection conn, String flowId, String prcsId) throws SQLException {
        JSONObject json = new JSONObject();
        json.accumulate("readOnly", "1");
        json.accumulate("createUpLoad", "1");
        json.accumulate("edit", "1");
        json.accumulate("down", "1");
        String queryStr = "SELECT PUBLIC_FILE FROM FLOW_PROCESS WHERE FLOW_ID=? AND PRCS_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, flowId);
        ps.setString(2, prcsId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (!SysTool.isNullorEmpty(rs.getString("PUBLIC_FILE"))) {
                json = JSONObject.fromObject(rs.getString("PUBLIC_FILE"));
            }
        }
        rs.close();
        ps.close();
        return json.toString();
    }

    // 获取经办权限人员
    public String getUserPrivLogic(Connection conn, String flowId, int prcsId) throws SQLException {
        JSONObject json = new JSONObject();
        String queryStr = "SELECT USER_PRIV,DEPT_PRIV,PRIV_ID FROM FLOW_PROCESS WHERE FLOW_ID=? AND PRCS_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, flowId);
        ps.setInt(2, prcsId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (rs.getString("USER_PRIV") == "" || rs.getString("USER_PRIV") == null) {
                json.accumulate("userId", "");
            } else {
                json.accumulate("userId", rs.getString("USER_PRIV"));
                AccountLogic accountLogic = new AccountLogic();
                json.accumulate("userName", accountLogic.getUserNameStr(conn, rs.getString("USER_PRIV")));
            }
            if (rs.getString("DEPT_PRIV") == "" || rs.getString("DEPT_PRIV") == null) {
                json.accumulate("deptId", "");
            } else {
                json.accumulate("deptId", rs.getString("DEPT_PRIV"));
                DeptLogic deptLogic = new DeptLogic();
                json.accumulate("deptName", deptLogic.getDeptNameStr(conn, rs.getString("DEPT_PRIV")));
            }
            if (rs.getString("PRIV_ID") == "" || rs.getString("PRIV_ID") == null) {
                json.accumulate("userPrivId", "");
            } else {
                json.accumulate("userPrivId", rs.getString("PRIV_ID"));
                UserPrivLogic userprivLogic = new UserPrivLogic();
                json.accumulate("userPrivName",
                        userprivLogic.getUserPrivNameStr(conn, rs.getString("PRIV_ID")));
            }
        }
        rs.close();
        ps.close();
        return json.toString();
    }

    // 更新验证字段
    public int updateValidatorLogic(Connection conn, String flowId, int prcsId, String mustfield,
            String hiddenfield) throws SQLException {
        String queryStr = "UPDATE FLOW_PROCESS SET MUST_FIELD=?,HIDDEN_FIELD=? WHERE FLOW_ID=? AND PRCS_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, mustfield);
        ps.setString(2, hiddenfield);
        ps.setString(3, flowId);
        ps.setInt(4, prcsId);
        int i = ps.executeUpdate();
        ps.close();
        return i;
    }

    // 获取验证字段
    public String getValidatorLogic(Connection conn, String flowId, int prcsId) throws SQLException {
        JSONObject json = new JSONObject();
        String queryStr = "SELECT MUST_FIELD,HIDDEN_FIELD FROM FLOW_PROCESS WHERE FLOW_ID=? AND PRCS_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, flowId);
        ps.setInt(2, prcsId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            json.accumulate("mustfield", rs.getString("MUST_FIELD"));
            json.accumulate("hiddenfield", rs.getString("HIDDEN_FIELD"));
        }
        rs.close();
        ps.close();
        return json.toString();
    }

    // 更新提醒方式
    public int setSmsConfigLogic(Connection conn, String flowId, int prcsId, String smsConfig)
            throws SQLException {
        String queryStr = "UPDATE  FLOW_PROCESS SET SMS_CONFIG=? WHERE FLOW_ID=? AND PRCS_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, smsConfig);
        ps.setString(2, flowId);
        ps.setInt(3, prcsId);
        int i = ps.executeUpdate();
        return i;
    }

    public String getSmsConfigLogic(Connection conn, String flowId, int prcsId) throws SQLException {
        String returnData = "";
        String queryStr = "SELECT SMS_CONFIG FROM FLOW_PROCESS WHERE FLOW_ID=? AND PRCS_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, flowId);
        ps.setInt(2, prcsId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            returnData = rs.getString("SMS_CONFIG");
        }
        return returnData;
    }
}
