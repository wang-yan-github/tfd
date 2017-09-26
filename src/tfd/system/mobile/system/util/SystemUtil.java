package tfd.system.mobile.system.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;

import com.system.global.SysConst;

public class SystemUtil {
    /**
     * 根据Phone得到AccountId Time:2015-10-08 Author:Yzz
     * 
     * @param conn
     * @param phone
     * @return
     * @throws Exception
     */
    public static String getAccountIdByPhone(Connection conn, String phone) throws Exception {
        String returnData = "";
        String querStr = "SELECT t1.ACCOUNT_ID FROM ACCOUNT t1 WHERE t1.ACCOUNT_ID = ?";
        PreparedStatement ps = conn.prepareStatement(querStr);
        ps.setString(1, phone);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            returnData = phone;
        } else {
            querStr = "SELECT t1.ACCOUNT_ID FROM USER_INFO t1 WHERE t1.MOBILE_NO = ?";
            ps = conn.prepareStatement(querStr);
            ps.setString(1, phone);
            rs = ps.executeQuery();
            if (rs.next()) {
                returnData = rs.getString("ACCOUNT_ID");
            } else {
                returnData = phone;
            }
        }
        ps.close();
        rs.close();
        return returnData;
    }

    /**
     * 根据AccountId得到用户头像的地址 Time:2015-11-01 Author:Yzz
     * 
     * @param conn
     * @param accountId
     * @param request
     * @return
     * @throws Exception
     */
    public static String getHeadImgByAccountId(Connection conn, String accountId, HttpServletRequest request)
            throws Exception {
        String returnData = "";
        String sql = "SELECT HEAD_IMG FROM USER_INFO WHERE ACCOUNT_ID = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, accountId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (rs.getString("HEAD_IMG") == null) {
                returnData = "";
            } else {
                if (rs.getString("HEAD_IMG").equals("")) {
                    returnData = "";
                } else {
                    returnData = request.getContextPath() + "/attachment/userinfo/"
                            + rs.getString("HEAD_IMG");
                }
            }
        }
        return returnData;
    }

    /**
     * 检验是否收到参数 Time:2015-11-20 Author:Yzz
     * 
     * @param response
     * @param param
     * @param paramValue
     * @return
     * @throws Exception
     */
    public String checkParam(HttpServletResponse response, String param, String paramValue) throws Exception {
        boolean flag = false;
        if (param.equals("phone")) {
            if (paramValue == null || paramValue.equals("")) {
                flag = true;
            }
        }
        if (paramValue == null) {
            flag = true;
        }
        if (flag) {
            JSONObject json = new JSONObject();
            JSONObject returnJson = new JSONObject();
            returnJson.accumulate("status_code", "510");
            returnJson.accumulate("msg", "参数错误:" + param);
            json.accumulate("time", new Date().getTime() / 1000);
            returnJson.accumulate("data", json);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(returnJson.toString());
            response.getWriter().flush();
            throw new Exception("参数错误:" + param);
        }
        return paramValue;
    }

    /**
     * 验证用户 Time:2015-12-03 Author:Yzz
     * 
     * @param request
     * @param response
     * @param access_token
     * @throws Exception
     */
    public void checkAccessToken(HttpServletRequest request, HttpServletResponse response,
            String access_token, String phone) throws Exception {
        String sessionId = request.getSession().getId();
        Account account = (Account) request.getSession().getAttribute(SysConst.LOGIN_USER);
        if (!sessionId.equals(access_token) || !account.getAccountId().equals(phone)) {
            JSONObject json = new JSONObject();
            JSONObject returnJson = new JSONObject();
            returnJson.accumulate("status_code", "530");
            returnJson.accumulate("msg", "用户验证错误");
            json.accumulate("time", new Date().getTime() / 1000);
            returnJson.accumulate("data", json);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(returnJson.toString());
            response.getWriter().flush();
            throw new Exception("用户验证错误");
        }
    }
}
