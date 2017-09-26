package tfd.system.workflow.form.logic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.workflow.form.data.WorkFlowForm;

import com.system.db.DbOp;
import com.system.db.PageTool;

//新建表单信息
public class WorkFlowFormLogic {
    // 获取流程分类json TREE
    public String getFlowFormTreeJson(Connection conn, Account account) throws SQLException {
        ResultSet rs = null;
        PreparedStatement ps = null;
        String queryStr = "";
        JSONArray jsonArr = new JSONArray();
        queryStr = "SELECT FLOW_TYPE_ID,FLOW_TYPE_NAME,LEAVE_ID FROM WORK_FLOW_TYPE WHERE MODULE_ID='workflow' AND ORG_ID=? AND MANAGE_ACCOUNT_ID=?";
        ps = conn.prepareStatement(queryStr);
        ps.setString(1, account.getOrgId());
        ps.setString(2, account.getAccountId());
        rs = ps.executeQuery();
        while (rs.next()) {
            JSONObject json = new JSONObject();
            json.accumulate("id", rs.getString("FLOW_TYPE_ID"));
            json.accumulate("pId", rs.getString("LEAVE_ID"));
            json.accumulate("name", rs.getString("FLOW_TYPE_NAME"));
            json.accumulate("icon", "/tfd/system/styles/images/file_tree/folder.gif");
            json.accumulate("isParent", true);
            jsonArr.add(json);
        }
        queryStr = "SELECT FORM_ID,FORM_NAME,WORK_FLOW_TYPE_ID FROM WORK_FLOW_FORM WHERE FORM_TYPE=? AND ORG_ID=? AND FORM_CREATE_USER=?";
        ps = conn.prepareStatement(queryStr);
        ps.setString(1, "workflow");
        ps.setString(2, account.getOrgId());
        ps.setString(3, account.getAccountId());
        rs = ps.executeQuery();
        while (rs.next()) {
            JSONObject json = new JSONObject();
            json.accumulate("id", rs.getString("FORM_ID"));
            json.accumulate("pId", rs.getString("WORK_FLOW_TYPE_ID"));
            json.accumulate("name", rs.getString("FORM_NAME"));
            json.accumulate("icon", "/tfd/system/styles/images/file_tree/workflow.gif");
            jsonArr.add(json);
        }
        rs.close();
        ps.close();
        return jsonArr.toString();
    }

    public void addWorkFlowFormLogic(Connection conn, WorkFlowForm workFlowForm) throws SQLException {
        String queryStr = "INSERT INTO WORK_FLOW_FORM (FORM_ID,FORM_NAME,FORM_TABLE_NAME,FORM_CREATE_USER,"
                + "FORM_DEPT_ID,FORM_CREATE_TIME,FORM_TYPE,ORG_ID,WORK_FLOW_TYPE_ID) VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, workFlowForm.getFormId());
        ps.setString(2, workFlowForm.getFormName());
        ps.setString(3, workFlowForm.getFormTableName());
        ps.setString(4, workFlowForm.getFormCreateUser());
        ps.setString(5, workFlowForm.getFormDeptId());
        ps.setDate(6, new Date(workFlowForm.getFormCreatTime().getTime()));
        ps.setString(7, workFlowForm.getFormType());
        ps.setString(8, workFlowForm.getOrgId());
        ps.setString(9, workFlowForm.getWorkFlowTypeId());
        ps.executeUpdate();
        ps.close();
    }

    // 按表单formId查询
    public String getWorkFlowFormByFormId(Connection conn, String formId) throws SQLException {
        JSONArray jsonArr = new JSONArray();
        UserInfoLogic userInfoLogic = new UserInfoLogic();
        String queryStr = "SELECT A.ID AS ID,FORM_ID,FORM_NAME,FORM_TABLE_NAME,FORM_CREATE_USER,"
                + "FORM_DEPT_ID,C.DEPT_NAME AS DEPT_NAME,FORM_CREATE_TIME,FORM_TYPE,A.ORG_ID AS ORG_ID,WORK_FLOW_TYPE_ID,B.FLOW_TYPE_NAME AS WORK_FLOW_TYPE_NAME FROM WORK_FLOW_FORM A, WORK_FLOW_TYPE B,DEPARTMENT C"
                + " WHERE FORM_ID='" + formId
                + "' AND A.WORK_FLOW_TYPE_ID=B.FLOW_TYPE_ID AND C.DEPT_ID=A.FORM_DEPT_ID ";
        ResultSet rs = null;
        PreparedStatement ps = conn.prepareStatement(queryStr);
        rs = ps.executeQuery();
        if (rs.next()) {
            JSONObject json = new JSONObject();
            json.accumulate("formName", rs.getString("FORM_NAME"));
            json.accumulate("formTableName", rs.getString("FORM_TABLE_NAME"));
            json.accumulate("formCreateUser", rs.getString("FORM_CREATE_USER"));
            json.accumulate("formUserName",
                    userInfoLogic.getUserNameByAccountIdLogic(conn, rs.getString("FORM_CREATE_USER")));
            json.accumulate("formDeptId", rs.getString("FORM_DEPT_ID"));
            json.accumulate("formDeptName", rs.getString("DEPT_NAME"));
            json.accumulate("formType", rs.getString("FORM_TYPE"));
            json.accumulate("workFlowTypeId", rs.getString("WORK_FLOW_TYPE_ID"));
            json.accumulate("workFlowTypeName", rs.getString("WORK_FLOW_TYPE_NAME"));
            jsonArr.add(json);
        }
        rs.close();
        ps.close();
        return jsonArr.toString();
    }

    // 按表单formId进行保存表单源代码
    public void savaFormCode(Connection conn, String formId, String htmlCode) throws SQLException {
        String query = "UPDATE WORK_FLOW_FORM SET FORM_CODE=? WHERE FORM_ID='" + formId + "'";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, htmlCode);
        ps.executeUpdate();
        ps.close();
    }

    // 按表单formId获取FORMCODE
    public String getFormCode(Connection conn, String formId) throws SQLException {
        ResultSet rs = null;
        String formCode = "";
        String query = "SELECT FORM_CODE FROM WORK_FLOW_FORM WHERE FORM_ID='" + formId + "'";
        PreparedStatement ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        if (rs.next()) {
            formCode = rs.getString("FORM_CODE");
        }
        rs.close();
        ps.close();
        return formCode;
    }

    // 按表单formId获取表单样式
    public String getFormStyle(Connection conn, String formId) throws SQLException {
        ResultSet rs = null;
        JSONObject json = new JSONObject();
        String query = "SELECT FORM_STYLE FROM WORK_FLOW_FORM WHERE FORM_ID='" + formId + "'";
        PreparedStatement ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        if (rs.next()) {
            json.accumulate("formStyle", rs.getString("FORM_STYLE"));
        }
        rs.close();
        ps.close();
        return json.toString();
    }

    // 按表单formId更新表单样式
    public int updateFormStyle(Connection conn, String formId, String Style) throws SQLException {
        String query = "UPDATE WORK_FLOW_FORM SET FORM_STYLE=? WHERE FORM_ID='" + formId + "'";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, Style);
        int i = ps.executeUpdate();
        ps.close();
        return i;

    }

    // 按FORM_ID获取FORM_TABLE_NAME
    public String getFormTableNameByFormIdLogic(Connection conn, String formId) throws SQLException {
        ResultSet rs = null;
        String returndata = "";
        String queryStr = "SELECT FORM_TABLE_NAME FROM WORK_FLOW_FORM WHERE FORM_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, formId);
        rs = ps.executeQuery();
        if (rs.next()) {
            returndata = rs.getString("FORM_TABLE_NAME");
        }
        rs.close();
        ps.close();
        return returndata;
    }

    // 获取FORM_ID与FORM_NAME
    public String getFormIdSelectLogic(Connection conn, Account account) throws SQLException {
        ResultSet rs = null;
        JSONArray jsonArr = new JSONArray();
        String queryStr = "SELECT FORM_ID,FORM_NAME FROM WORK_FLOW_FORM WHERE FORM_TYPE=? AND ORG_ID=? AND FORM_CREATE_USER=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, "workflow");
        ps.setString(2, account.getOrgId());
        ps.setString(3, account.getAccountId());
        rs = ps.executeQuery();
        while (rs.next()) {
            JSONObject json = new JSONObject();
            json.accumulate("text", rs.getString("FORM_NAME"));
            json.accumulate("id", rs.getString("FORM_ID"));
            jsonArr.add(json);
        }
        rs.close();
        ps.close();
        return jsonArr.toString();
    }

    // 更新表单信息
    public void updateWorkFlowFormLogic(Connection conn, WorkFlowForm workFlowForm) throws SQLException {
        String queryStr = "UPDATE WORK_FLOW_FORM SET FORM_NAME=?,FORM_TABLE_NAME=?,FORM_DEPT_ID=?,WORK_FLOW_TYPE_ID=?,FORM_TYPE=? WHERE FORM_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, workFlowForm.getFormName());
        ps.setString(2, workFlowForm.getFormTableName());
        ps.setString(3, workFlowForm.getFormDeptId());
        ps.setString(4, workFlowForm.getWorkFlowTypeId());
        ps.setString(5, workFlowForm.getFormType());
        ps.setString(6, workFlowForm.getFormId());
        ps.executeUpdate();
        ps.close();
    }

    // 获取表单JS脚本
    public String getFormScriptLogic(Connection conn, String formId) throws SQLException {
        ResultSet rs = null;
        JSONObject json = new JSONObject();
        String queryStr = "SELECT SCRIPT FROM WORK_FLOW_FORM WHERE FORM_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, formId);
        rs = ps.executeQuery();
        while (rs.next()) {
            json.accumulate("formscript", rs.getString("SCRIPT"));
        }
        rs.close();
        ps.close();
        return json.toString();
    }

    // 更新表单JS脚本
    public int updateFormScriptLogic(Connection conn, String script, String formId) throws SQLException {
        String queryStr = "UPDATE WORK_FLOW_FORM SET SCRIPT=? WHERE FORM_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, script);
        ps.setString(2, formId);
        int i = ps.executeUpdate();
        ps.close();
        return i;
    }

    // 通过FORM_ID获取FORM_NAME
    public String getFormNameByFormId(Connection conn, String formId) throws SQLException {
        String returnData = "";
        String queryStr = "SELECT FORM_NAME FROM WORK_FLOW_FORM WHERE FORM_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, formId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            returnData = rs.getString("FORM_NAME");
        }
        rs.close();
        ps.close();
        return returnData;
    }

    // 获取表单分类下的表单列表
    public String getFormListByWorkFlowTypeIdLogic(Connection conn, List<String> pramList, int pageSize,
            int page, String sortOrder, String sortName) throws Exception {
        String queryStr = "SELECT A.ID AS ID,FORM_ID,FORM_NAME,FORM_TABLE_NAME,FORM_CREATE_USER,B.DEPT_NAME AS DEPT_NAME,FORM_CREATE_TIME,FORM_TYPE,A.ORG_ID AS ORG_ID FROM WORK_FLOW_FORM A,DEPARTMENT B WHERE B.DEPT_ID=A.FORM_DEPT_ID AND WORK_FLOW_TYPE_ID=? AND FORM_CREATE_USER=? AND A.ORG_ID=?";
        String optStr = "[{'function':'edit','name':'基本属性','parm':'FORM_ID'},{'function':'designForm','name':'设计表单','parm':'FORM_ID,FORM_TABLE_NAME'},{'function':'delForm','name':'删除','parm':'FORM_ID'},{'function':'moreOpt','name':'更多..','parm':'FORM_ID'}]";
        JSONArray optArrJson = JSONArray.fromObject(optStr);
        PageTool pageTool = new PageTool();
        JSONObject json = pageTool.getPageData(conn, queryStr, pramList, pageSize, page, optArrJson,
                sortOrder, sortName);
        return json.toString();
    }

    // 物理删除工作流表单，连同数据表一起删除
    public int delFormByFormIdLogic(Connection conn, String formId) throws SQLException {
        WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
        String tableName = workFlowFormLogic.getFormTableNameByFormIdLogic(conn, formId);
        String queryStr = "DELETE FROM WORK_FLOW_FORM WHERE FORM_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, formId);
        int i = ps.executeUpdate();
        ps.close();
        DbOp dbop = new DbOp();
        dbop.delTable(conn, tableName);
        return i;
    }

    public int updateFormCodeLogic(Connection conn, String code, String formId) throws SQLException {
        String queryStr = "UPDATE WORK_FLOW_FORM SET FORM_CODE = ? WHERE FORM_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, code);
        ps.setString(2, formId);
        int i = ps.executeUpdate();
        ps.close();
        return i;
    }

    // 按表单名称查询
    public String getFormListByFormNameLogic(Connection conn, List<String> pramList, int pageSize, int page,
            String sortOrder, String sortName) throws Exception {
        String queryStr = "SELECT A.ID AS ID,FORM_ID,FORM_NAME,FORM_TABLE_NAME,FORM_CREATE_USER,B.DEPT_NAME AS DEPT_NAME,FORM_CREATE_TIME,FORM_TYPE,A.ORG_ID AS ORG_ID FROM WORK_FLOW_FORM A,DEPARTMENT B WHERE B.DEPT_ID=A.FORM_DEPT_ID AND FORM_NAME LIKE ? AND FORM_CREATE_USER=? AND A.ORG_ID=?";
        String optStr = "[{'function':'edit','name':'基本属性','parm':'FORM_ID'},{'function':'designForm','name':'设计表单','parm':'FORM_ID,FORM_TABLE_NAME'},{'function':'delForm','name':'删除','parm':'FORM_ID'},{'function':'moreOpt','name':'更多..','parm':'FORM_ID'}]";
        JSONArray optArrJson = JSONArray.fromObject(optStr);
        PageTool pageTool = new PageTool();
        JSONObject json = pageTool.getPageData(conn, queryStr, pramList, pageSize, page, optArrJson,
                sortOrder, sortName);
        return json.toString();
    }
}
