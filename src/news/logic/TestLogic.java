package news.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import news.data.News;
import news.data.Test;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.dept.logic.DeptLogic;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.unit.userpriv.logic.UserPrivLogic;

import com.system.db.DbPoolConnection;
import com.system.db.PageTool;
import com.system.tool.SysTool;

public class TestLogic {
    /**
     * 添加新闻信息 Author: Wp
     * 
     * @param conn
     * @param news
     *            （对象）
     * @return
     * @throws SQLException
     */
    public int addTestLogic(Connection conn, Test test) throws SQLException {
        String queryStr = "INSERT INTO TEST(TITLE,TYPE,CODE,DEPT_NAME,PUSER,AMOUNT,THIRD_DEPT_NAME)"
                + "VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, test.getTitle());
        ps.setInt(2, test.getType());
        ps.setString(3, test.getCode());
        ps.setString(4, test.getDeptName());
        ps.setString(5, test.getPuser());
        ps.setString(6, test.getAmount());
        ps.setString(7, test.getThirdDeptName());
        int i = ps.executeUpdate();
        ps.close();
        return i;
    }

    /**
     * 修改新闻信息 Author: Wp
     * 
     * @param conn
     * @param news
     * @return
     * @throws SQLException
     */
    public int updateNewsLogic(Connection conn, News news) throws SQLException {
        String queryStr = "UPDATE NEWS SET TITLE=?,TYPE=?,DEPT_PRIV=?,USER_PRIV=?,ACCOUNT_ID=?,CREATE_TIME=?,ATTACH_ID=?,TOP=?,CONTECT=?,END_TIME=?,COMMENT_STATUS=?,IS_SMS=? WHERE NEWS_ID=?  AND CREATE_USER=? AND ORG_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, news.getTitle());
        ps.setString(2, news.getType());
        ps.setString(3, news.getDeptPriv());
        ps.setString(4, news.getUserPriv());
        ps.setString(5, news.getAccountId());
        ps.setString(6, news.getCreateTime());
        ps.setString(7, news.getAttachId());
        ps.setString(8, news.getTop());
        ps.setString(9, news.getContect());
        ps.setString(10, news.getEndTime());
        ps.setString(11, news.getCommentStatus());
        ps.setString(12, news.getIsSms());
        ps.setString(13, news.getNewsId());
        ps.setString(14, news.getCreateUser());
        ps.setString(15, news.getOrgId());
        int i = ps.executeUpdate();
        ps.close();
        return i;
    }

    /**
     * 删除新闻信息 Author: Wp
     * 
     * @param conn
     * @param newsId
     * @param orgId
     * @param createUser
     * @return
     * @throws SQLException
     */
    public int delNewsLogic(Connection conn, String newsId, String orgId, String createUser)
            throws SQLException {
        String queryStr = "DELETE FROM NEWS WHERE NEWS_ID=? AND CREATE_USER =? AND ORG_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, newsId);
        ps.setString(2, createUser);
        ps.setString(3, orgId);
        int i = ps.executeUpdate();
        ps.close();
        return i;
    }

    /**
     * 获取新闻列表 Author: Wp
     * 
     * @param conn
     * @param newstype
     * @param pramList
     * @param pageSize
     * @param page
     * @param sortOrder
     * @param sortName
     * @return
     * @throws Exception
     */
    public String getTestListLogic(Connection conn, List<String> pramList, int pageSize, int page,
            String sortOrder, String sortName) throws Exception {
        String queryStr = "SELECT ID,TITLE,TYPE,CODE,DEPT_NAME,PUSER,AMOUNT,THIRD_DEPT_NAME FROM TEST";
        String optStr = "[{'function':'editTest','name':'编辑','parm':'ID'}]";
        JSONArray optArrJson = JSONArray.fromObject(optStr);
        PageTool pageTool = new PageTool();
        JSONObject json = pageTool.getPageData(conn, queryStr, pramList, pageSize, page, optArrJson,
                sortOrder, sortName);
        return json.toString();
    }

    /**
     * 根据Id 获取新闻详情 Author: Wp
     * 
     * @param conn
     * @param newsId
     * @param account
     * @param pramList
     * @return
     * @throws SQLException
     */
    public String getNewsByNewsIdLogic(Connection conn, String newsId, Account account, List<String> pramList)
            throws SQLException {
        JSONObject json = new JSONObject();
        ResultSet rs = null;
        String dbType = DbPoolConnection.getInstance().getDbType();
        String queryStr = "";
        if (dbType.equals("sqlserver")) {

        } else if (dbType.equals("mysql")) {
            queryStr = "SELECT T1.ID AS ID,NEWS_ID,TITLE,TYPE,T3.CODE_NAME AS TYPE_NAME,T1.ACCOUNT_ID AS ACCOUNT_ID,DEPT_PRIV,ONCLICK_COUNT,USER_PRIV,CREATE_TIME,ATTACH_ID,CREATE_USER,T2.USER_NAME AS CREATE_NAME,CONTECT,TOP,END_TIME,COMMENT_STATUS,IS_SMS FROM NEWS T1 LEFT JOIN USER_INFO T2 ON T1.CREATE_USER =T2.ACCOUNT_ID LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE=T1.TYPE  WHERE NEWS_ID=? AND"
                    + " ("
                    + " T1.ACCOUNT_ID='userAll' OR "
                    + " T1.DEPT_PRIV='deptAll' OR "
                    + " T1.USER_PRIV='privAll' OR "
                    + " LOCATE(?,CONCAT(',',T1.ACCOUNT_ID,','))>0 OR "
                    + " LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 OR "
                    + " LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 OR "
                    + " LOCATE(?,CONCAT(',',T1.USER_PRIV,','))>0 OR "
                    + " CREATE_USER =? OR APPROVAL_STAFF =? ) " + " AND T1.ORG_ID=? ";
        } else if (dbType.equals("oracle")) {
            queryStr = "SELECT T1.ID AS ID,NEWS_ID,TITLE,TYPE,T3.CODE_NAME AS TYPE_NAME,T1.ACCOUNT_ID AS ACCOUNT_ID,T1.DEPT_PRIV AS DEPT_PRIV,ONCLICK_COUNT,T1.USER_PRIV AS USER_PRIV,CREATE_TIME,ATTACH_ID,CREATE_USER,T2.USER_NAME AS CREATE_NAME,CONTECT,TOP,END_TIME,COMMENT_STATUS,IS_SMS FROM NEWS T1 LEFT JOIN USER_INFO T2 ON T1.CREATE_USER=T2.ACCOUNT_ID LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE=T1.TYPE  "
                    + " WHERE NEWS_ID=? AND ("
                    + " TO_CHAR(T1.ACCOUNT_ID) ='userAll' OR "
                    + " TO_CHAR(T1.DEPT_PRIV) ='deptAll' OR "
                    + " TO_CHAR(T1.USER_PRIV) ='privAll' OR "
                    + " INSTR(CONCAT(',',TO_CHAR(T1.ACCOUNT_ID))||',',?)>0 OR "
                    + " INSTR(CONCAT(',',TO_CHAR(T1.DEPT_PRIV))||',',?)>0 OR "
                    + " INSTR(CONCAT(',',TO_CHAR(T1.DEPT_PRIV))||',',?)>0 OR "
                    + " INSTR(CONCAT(',',TO_CHAR(T1.USER_PRIV))||',',?)>0 OR "
                    + " CREATE_USER =? OR APPROVAL_STAFF =?) " + " AND T1.ORG_ID=?";
        }
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, newsId);
        ps.setString(2, pramList.get(0));
        ps.setString(3, pramList.get(1));
        ps.setString(4, pramList.get(2));
        ps.setString(5, pramList.get(3));
        ps.setString(6, account.getAccountId());
        ps.setString(7, account.getAccountId());
        ps.setString(8, account.getOrgId());
        rs = ps.executeQuery();
        AccountLogic acclogic = new AccountLogic();
        DeptLogic deptlogic = new DeptLogic();
        UserPrivLogic ulogic = new UserPrivLogic();
        if (rs.next()) {
            json.accumulate("newsId", rs.getString("NEWS_ID"));
            json.accumulate("title", rs.getString("TITLE"));
            json.accumulate("newstype", rs.getString("TYPE"));
            json.accumulate("typeName", rs.getString("TYPE_NAME"));
            if (rs.getString("ACCOUNT_ID") == null) {
                json.accumulate("accountId", "");
            } else {
                json.accumulate("accountId", rs.getString("ACCOUNT_ID"));
            }
            String userName = acclogic.getUserNameStr(conn, rs.getString("ACCOUNT_ID"));
            json.accumulate("userName", userName);
            if (rs.getString("DEPT_PRIV") == null) {
                json.accumulate("deptPriv", "");
            } else {
                json.accumulate("deptPriv", rs.getString("DEPT_PRIV"));
            }
            String deptName = deptlogic.getDeptNameStr(conn, rs.getString("DEPT_PRIV"));
            json.accumulate("deptName", deptName);
            if (rs.getString("USER_PRIV") == null) {
                json.accumulate("userPriv", "");
            } else {
                json.accumulate("userPriv", rs.getString("USER_PRIV"));
            }
            String userPrivName = ulogic.getUserPrivNameStr(conn, rs.getString("USER_PRIV"));
            json.accumulate("userPrivName", userPrivName);
            json.accumulate("createTime", rs.getString("CREATE_TIME"));
            json.accumulate("attachId", rs.getString("ATTACH_ID"));
            json.accumulate("createUser", rs.getString("CREATE_USER"));
            json.accumulate("createName", rs.getString("CREATE_NAME"));
            if (rs.getString("CONTECT") == null) {
                json.accumulate("contect", "");
            } else {
                json.accumulate("contect", rs.getString("CONTECT"));
            }
            json.accumulate("top", rs.getString("TOP"));
            json.accumulate("onclickcount", rs.getString("ONCLICK_COUNT"));
            if (rs.getString("END_TIME") == null) {
                json.accumulate("endTime", "");
            } else {
                json.accumulate("endTime", rs.getString("END_TIME"));
            }
            json.accumulate("commentStatus", rs.getString("COMMENT_STATUS"));
            json.accumulate("isSms", rs.getString("IS_SMS"));
        }
        rs.close();
        ps.close();
        return json.toString();
    }

    /**
     * 直接对新闻进行添加并发布 Author: Wp
     * 
     * @param conn
     * @param news
     * @return
     * @throws SQLException
     */
    public int publishNewsLogic(Connection conn, News news) throws SQLException {
        String queryStr = "INSERT INTO NEWS(NEWS_ID,TITLE,TYPE,DEPT_PRIV,USER_PRIV,ACCOUNT_ID,CREATE_TIME,ATTACH_ID,TOP,CREATE_USER,CONTECT,ORG_ID,STATUS,APPROVAL_STAFF,APPROVAL_STATUS,END_TIME,COMMENT_STATUS,IS_SMS)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, news.getNewsId());
        ps.setString(2, news.getTitle());
        ps.setString(3, news.getType());
        ps.setString(4, news.getDeptPriv());
        ps.setString(5, news.getUserPriv());
        ps.setString(6, news.getAccountId());
        ps.setString(7, news.getCreateTime());
        ps.setString(8, news.getAttachId());
        ps.setString(9, news.getTop());
        ps.setString(10, news.getCreateUser());
        ps.setString(11, news.getContect().toString());
        ps.setString(12, news.getOrgId());
        ps.setString(13, news.getStatus());
        ps.setString(14, news.getApprovalStaff());
        ps.setString(15, news.getApprovalStatus());
        ps.setString(16, news.getEndTime());
        ps.setString(17, news.getCommentStatus());
        ps.setString(18, news.getIsSms());
        int i = ps.executeUpdate();
        ps.close();
        return i;
    }

    /**
     * 对新闻进行修改并发布 Author: Wp
     * 
     * @param conn
     * @param news
     * @return
     * @throws SQLException
     */
    public int publishupdateNewsLogic(Connection conn, News news) throws SQLException {
        String queryStr = "UPDATE NEWS SET TITLE=?,TYPE=?,DEPT_PRIV=?,USER_PRIV=?,ACCOUNT_ID=?,CREATE_TIME=?,ATTACH_ID=?,TOP=?,CONTECT=? ,STATUS=? ,APPROVAL_STAFF =?,APPROVAL_STATUS=?,END_TIME=? ,COMMENT_STATUS=?,IS_SMS=? WHERE NEWS_ID=? AND ORG_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, news.getTitle());
        ps.setString(2, news.getType());
        ps.setString(3, news.getDeptPriv());
        ps.setString(4, news.getUserPriv());
        ps.setString(5, news.getAccountId());
        ps.setString(6, news.getCreateTime());
        ps.setString(7, news.getAttachId());
        ps.setString(8, news.getTop());
        ps.setString(9, news.getContect());
        ps.setString(10, news.getStatus());
        ps.setString(11, news.getApprovalStaff());
        ps.setString(12, news.getApprovalStatus());
        ps.setString(13, news.getEndTime());
        ps.setString(14, news.getCommentStatus());
        ps.setString(15, news.getIsSms());
        ps.setString(16, news.getNewsId());
        ps.setString(17, news.getOrgId());
        int i = ps.executeUpdate();
        ps.close();
        return i;
    }

    /**
     * 进行多条件的新闻查询 Author: Wp
     * 
     * @param conn
     * @param news
     * @param starttime
     * @param endtime
     * @param pageSize
     * @param page
     * @param storOrder
     * @param storName
     * @return
     * @throws Exception
     */
    public String querynewsLogic(Connection conn, News news, String starttime, String endtime, int pageSize,
            int page, String storOrder, String storName) throws Exception {
        String optStr = "[{'function':'editNews','name':'修改','parm':'NEWS_ID'},{'function':'readNews','name':'查看','parm':'NEWS_ID'},{'function':'delNews','name':'删除','parm':'NEWS_ID'}]";
        JSONArray optArrJson = JSONArray.fromObject(optStr);
        JSONObject returnJson = new JSONObject();
        String queryStr = "SELECT DISTINCT *FROM ( SELECT T1.ID AS ID,NEWS_ID,TITLE,T2.CODE_NAME AS TYPE,CREATE_TIME,STATUS,ONCLICK_COUNT,APPROVAL_STATUS FROM NEWS T1 LEFT JOIN CODE_CLASS T2 ON T2.CODE_VALUE=T1.TYPE WHERE CREATE_USER = ?  AND T1.ORG_ID=? ";
        List<String> pramList = new ArrayList<String>();
        pramList.add(news.getCreateUser());
        pramList.add(news.getOrgId());
        if (!news.getType().equals("")) {
            queryStr += " AND T1.TYPE = ?";
            pramList.add(news.getType());
        }
        if (!news.getStatus().equals("")) {
            queryStr += " AND APPROVAL_STATUS= ?";
            pramList.add(news.getStatus());
        }
        if (!news.getTop().equals("")) {
            queryStr += " AND TOP = ?";
            pramList.add(news.getTop());
        }
        if (!news.getTitle().equals("")) {
            queryStr += " AND TITLE LIKE ?";
            pramList.add("%" + news.getTitle() + "%");
        }
        if (!news.getContect().equals("")) {
            queryStr += " AND CONTECT LIKE ?";
            pramList.add("%" + news.getContect() + "%");
        }
        if (!starttime.equals("")) {
            queryStr += " AND CREATE_TIME >= ?";
            pramList.add(starttime);
        }
        if (!endtime.equals("")) {
            queryStr += " AND CREATE_TIME <= ?";
            pramList.add(endtime);
        }
        queryStr += " ) NEWS ";
        PageTool pageTool = new PageTool();
        returnJson = pageTool.getPageData(conn, queryStr, pramList, pageSize, page, optArrJson, storOrder,
                storName);
        return returnJson.toString();
    }

    /**
     * 进行多条件的新闻删除 Author: Wp
     * 
     * @param conn
     * @param news
     * @param starttime
     * @param endtime
     * @return
     * @throws SQLException
     */
    public int delnewsLogic(Connection conn, News news, String starttime, String endtime) throws SQLException {
        String queryStr = "DELETE FROM NEWS  WHERE CREATE_USER = ?  AND ORG_ID=? ";
        List<String> pramList = new ArrayList<String>();
        pramList.add(news.getCreateUser());
        pramList.add(news.getOrgId());
        if (!news.getType().equals("")) {
            queryStr += " AND TYPE = ?";
            pramList.add(news.getType());
        }
        if (!news.getStatus().equals("")) {
            queryStr += " AND STATUS = ?";
            pramList.add(news.getStatus());
        }
        if (!news.getTop().equals("")) {
            queryStr += " AND TOP = ?";
            pramList.add(news.getTop());
        }
        if (!news.getTitle().equals("")) {
            queryStr += " AND TITLE LIKE ?";
            pramList.add("%" + news.getTitle() + "%");
        }
        if (!news.getContect().equals("")) {
            queryStr += " AND CONTECT LIKE ?";
            pramList.add("%" + news.getContect() + "%");
        }
        if (!starttime.equals("")) {
            queryStr += " AND CREATE_TIME >= ?";
            pramList.add(starttime);
        }
        if (!endtime.equals("")) {
            queryStr += " AND CREATE_TIME <= ?";
            pramList.add(endtime);
        }
        PreparedStatement ps = conn.prepareStatement(queryStr);
        for (int i = 1; pramList.size() >= i; i++) {
            ps.setString(i, pramList.get(i - 1));
        }
        int data = ps.executeUpdate();
        ps.close();
        return data;
    }

    /**
     * 获取待审批的新闻列表 Author: Wp
     * 
     * @param conn
     * @param accountId
     * @param orgId
     * @param pageSize
     * @param page
     * @param storOrder
     * @param storName
     * @return
     * @throws Exception
     */
    public String noapprovallogic(Connection conn, String accountId, String orgId, int pageSize, int page,
            String storOrder, String storName) throws Exception {
        String queryStr = "SELECT DISTINCT *FROM ( SELECT T1.ID AS ID,NEWS_ID,TITLE,T3.CODE_NAME AS TYPE,CREATE_TIME,T2.USER_NAME AS CREATE_NAME  FROM NEWS T1 LEFT JOIN USER_INFO T2 ON T2.ACCOUNT_ID =T1.CREATE_USER LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE=T1.TYPE WHERE (END_TIME>? OR END_TIME='0') AND APPROVAL_STAFF=? AND STATUS=1 AND APPROVAL_STATUS=0 AND T1.ORG_ID =? ) NEWS ORDER BY CREATE_TIME DESC,ID DESC";
        String optStr = "[{'function':'applynews','name':'审批','parm':'NEWS_ID'}]";
        JSONArray optArrJson = JSONArray.fromObject(optStr);
        JSONObject returnJson = new JSONObject();
        List<String> pramList = new ArrayList<String>();
        pramList.add(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
        pramList.add(accountId);
        pramList.add(orgId);
        PageTool pageTool = new PageTool();
        returnJson = pageTool.getPageData(conn, queryStr, pramList, pageSize, page, optArrJson, storOrder,
                storName);
        return returnJson.toString();
    }

    /**
     * 获取已审批的新闻列表 Author: Wp
     * 
     * @param conn
     * @param accountId
     * @param orgId
     * @param pageSize
     * @param page
     * @param storOrder
     * @param storName
     * @return
     * @throws Exception
     */
    public String alreadyapprovallogic(Connection conn, String accountId, String orgId, int pageSize,
            int page, String storOrder, String storName) throws Exception {
        String queryStr = "SELECT DISTINCT *FROM ( SELECT T1.ID AS ID,NEWS_ID,TITLE,T3.CODE_NAME AS TYPE,CREATE_TIME,APPROVAL_STATUS,T2.USER_NAME AS CREATE_NAME FROM NEWS T1 LEFT JOIN USER_INFO T2 ON T1.CREATE_USER =T2.ACCOUNT_ID LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE=T1.TYPE WHERE APPROVAL_STAFF=? AND STATUS=1 AND APPROVAL_STATUS !=0 AND T1.ORG_ID =? ) NEWS ORDER BY CREATE_TIME DESC,ID DESC";
        String optStr = "[{'function':'applynews','name':'审批','parm':'NEWS_ID'}]";
        JSONArray optArrJson = JSONArray.fromObject(optStr);
        JSONObject returnJson = new JSONObject();
        List<String> pramList = new ArrayList<String>();
        pramList.add(accountId);
        pramList.add(orgId);
        PageTool pageTool = new PageTool();
        returnJson = pageTool.getPageData(conn, queryStr, pramList, pageSize, page, optArrJson, storOrder,
                storName);
        return returnJson.toString();
    }

    /**
     * 新闻审批处理 Author: Wp
     * 
     * @param conn
     * @param newsId
     * @param orgId
     * @param approvalStatus
     * @return
     * @throws SQLException
     */
    public int managenewsnewslogic(Connection conn, String newsId, String orgId, String approvalStatus)
            throws SQLException {
        String queryStr = "UPDATE NEWS SET APPROVAL_STATUS=? WHERE NEWS_ID =? AND ORG_ID=?";
        PreparedStatement ps = conn.prepareStatement(queryStr);
        ps.setString(1, approvalStatus);
        ps.setString(2, newsId);
        ps.setString(3, orgId);
        int i = ps.executeUpdate();
        ps.close();
        return i;
    }

    /**
     * 进行多条件的新闻查询 Author: Wp
     * 
     * @param conn
     * @param pramList
     * @param news
     * @param starttime
     * @param endtime
     * @param pageSize
     * @param page
     * @param storOrder
     * @param storName
     * @return
     * @throws Exception
     */
    public String readtremlogic(Connection conn, List<String> pramList, News news, String starttime,
            String endtime, int pageSize, int page, String storOrder, String storName) throws Exception {
        String optStr = "[{'function':'readNews','name':'查看','parm':'NEWS_ID'}]";
        JSONArray optArrJson = JSONArray.fromObject(optStr);
        JSONObject returnJson = new JSONObject();
        String dbType = DbPoolConnection.getInstance().getDbType();
        String queryStr = "";
        if (dbType.equals("sqlserver")) {

        } else if (dbType.equals("mysql")) {
            queryStr = "SELECT DISTINCT *FROM ( SELECT T1.ID AS ID,NEWS_ID,TITLE,T3.CODE_NAME AS TYPE,CREATE_TIME,STATUS,ONCLICK_COUNT,APPROVAL_STATUS,T2.USER_NAME AS CREATE_NAME FROM NEWS T1 LEFT JOIN USER_INFO T2 ON T1.CREATE_USER =T2.ACCOUNT_ID LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE=T1.TYPE  "
                    + "WHERE APPROVAL_STATUS=1 AND ("
                    + " T1.ACCOUNT_ID='userAll' OR "
                    + " T1.DEPT_PRIV='deptAll' OR "
                    + " T1.USER_PRIV='privAll' OR "
                    + " LOCATE(?,CONCAT(',',T1.ACCOUNT_ID,','))>0 OR "
                    + " LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 OR "
                    + " LOCATE(?,CONCAT(',',T1.DEPT_PRIV,','))>0 OR "
                    + " LOCATE(?,CONCAT(',',T1.USER_PRIV,','))>0 ) AND T1.ORG_ID=? ";
        } else if (dbType.equals("oracle")) {

            queryStr = "SELECT DISTINCT *FROM ( SELECT  T1.ID AS ID,NEWS_ID,TITLE,T3.CODE_NAME AS TYPE,CREATE_TIME,STATUS,ONCLICK_COUNT,APPROVAL_STATUS,T2.USER_NAME AS CREATE_NAME FROM NEWS T1 LEFT JOIN USER_INFO T2 ON T1.CREATE_USER =T2.ACCOUNT_ID LEFT JOIN CODE_CLASS T3 ON T3.CODE_VALUE=T1.TYPE WHERE APPROVAL_STATUS=1 AND ("
                    + " TO_CHAR(T1.ACCOUNT_ID) ='userAll' OR "
                    + " TO_CHAR(T1.DEPT_PRIV) ='deptAll' OR "
                    + " TO_CHAR(T1.USER_PRIV) ='privAll' OR "
                    + "INSTR(CONCAT(',',TO_CHAR(T1.ACCOUNT_ID))||',',?)>0 OR "
                    + "INSTR(CONCAT(',',TO_CHAR(T1.DEPT_PRIV))||',',?)>0 OR "
                    + "INSTR(CONCAT(',',TO_CHAR(T1.DEPT_PRIV))||',',?)>0 OR "
                    + "INSTR(CONCAT(',',TO_CHAR(T1.USER_PRIV))||',',?)>0 " + " ) AND T1.ORG_ID=? ";
        }
        if (!news.getCreateUser().equals("")) {
            queryStr += " AND CREATE_USER=?";
            pramList.add(news.getCreateUser());
        }
        if (!news.getType().equals("")) {
            queryStr += " AND TYPE = ?";
            pramList.add(news.getType());
        }
        if (!news.getTitle().equals("")) {
            queryStr += " AND TITLE LIKE ?";
            pramList.add("%" + news.getTitle() + "%");
        }
        if (!news.getContect().equals("")) {
            queryStr += " AND CONTECT LIKE ?";
            pramList.add("%" + news.getContect() + "%");
        }
        if (!starttime.equals("")) {
            queryStr += " AND CREATE_TIME >= ?";
            pramList.add(starttime);
        }
        if (!endtime.equals("")) {
            queryStr += " AND CREATE_TIME <= ?";
            pramList.add(endtime);
        }
        queryStr += " ) NEWS ";
        PageTool pageTool = new PageTool();
        returnJson = pageTool.getPageData(conn, queryStr, pramList, pageSize, page, optArrJson, storOrder,
                storName);
        return returnJson.toString();
    }

    /**
     * 查询新闻的查阅情况 Author: Wp
     * 
     * @param conn
     * @param newsId
     * @param account
     * @return
     * @throws SQLException
     */
    public String getIdreadstatusLogic(Connection conn, String newsId, Account account) throws SQLException {
        String queryStr = "SELECT ACCOUNT_ID,DEPT_PRIV,USER_PRIV FROM NEWS WHERE NEWS_ID=? AND CREATE_USER =? AND ORG_ID=?";
        PreparedStatement ps = null;
        ps = conn.prepareStatement(queryStr);
        ps.setString(1, newsId);
        ps.setString(2, account.getAccountId());
        ps.setString(3, account.getOrgId());
        ResultSet rs = ps.executeQuery();
        String deptId = null;
        String accountId = null;
        String userPriv = null;
        if (rs.next()) {
            accountId = rs.getString("ACCOUNT_ID");
            deptId = rs.getString("DEPT_PRIV");
            userPriv = rs.getString("USER_PRIV");
        }
        JSONArray jsonArr = new JSONArray();
        UserInfoLogic infoLogic = new UserInfoLogic();
        if (accountId.equals("userAll") || deptId.equals("deptAll") || userPriv.equals("privAll")) {
            jsonArr = infoLogic.getOrgIdLogic(conn, account.getOrgId());
        } else {
            if (accountId != "") {
                AccountLogic accountLogic = new AccountLogic();
                jsonArr = accountLogic.getjsonArrUserNameStr(conn, accountId);
            }
            if (deptId != "") {
                String[] deptIds = null;
                if (deptId.indexOf(",") > -1) {
                    deptIds = deptId.split(",");
                } else {
                    deptIds = new String[] { deptId };
                }
                for (int i = 0; i < deptIds.length; i++) {
                    queryStr = "SELECT USER_NAME,DEPT_ID FROM USER_INFO WHERE (DEPT_ID=? OR OTHER_DEPT=?) AND ORG_ID=?";
                    ps = conn.prepareStatement(queryStr);
                    ps.setString(1, deptIds[i]);
                    ps.setString(2, deptIds[i]);
                    ps.setString(3, account.getOrgId());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        JSONObject json = new JSONObject();
                        json.accumulate("userName", rs.getString("USER_NAME"));
                        json.accumulate("deptId", rs.getString("DEPT_ID"));
                        jsonArr.add(json);
                    }
                }
            }
            if (userPriv != "") {
                String[] userPrivs = null;
                if (userPriv.indexOf(",") > -1) {
                    userPrivs = userPriv.split(",");
                } else {
                    userPrivs = new String[] { userPriv };
                }
                for (int i = 0; i < userPrivs.length; i++) {
                    queryStr = "SELECT USER_NAME,DEPT_ID FROM USER_INFO WHERE POST_PRIV=? AND ORG_ID=?";
                    ps.setString(1, userPrivs[i]);
                    ps.setString(2, account.getOrgId());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        JSONObject json = new JSONObject();
                        json.accumulate("userName", rs.getString("USER_NAME"));
                        json.accumulate("deptId", rs.getString("DEPT_ID"));
                        jsonArr.add(json);
                    }
                }
            }
        }
        HashSet<Object> hs = new HashSet<Object>();
        for (int i = 0; i < jsonArr.size(); i++) {
            hs.add(jsonArr.get(i));
        }
        rs.close();
        ps.close();
        return hs.toString();
    }

    /**
     * 通过newsId 获取已读新闻人员的信息 Author: Wp
     * 
     * @param conn
     * @param newsId
     * @param account
     * @return
     * @throws SQLException
     */
    public String getreadUserLogic(Connection conn, String newsId, Account account) throws SQLException {
        String queryStr = "SELECT READER FROM NEWS WHERE NEWS_ID =? AND CREATE_USER =? AND ORG_ID=?";
        PreparedStatement ps = null;
        ps = conn.prepareStatement(queryStr);
        ps.setString(1, newsId);
        ps.setString(2, account.getAccountId());
        ps.setString(3, account.getOrgId());
        ResultSet rs = ps.executeQuery();
        String readUserName = "";
        AccountLogic acclogic = new AccountLogic();
        if (rs.next()) {
            readUserName = acclogic.getUserNameStr(conn, rs.getString("READER"));
        }
        rs.close();
        ps.close();
        return readUserName;
    }
}
