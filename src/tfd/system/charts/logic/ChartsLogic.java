package tfd.system.charts.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.dept.logic.DeptLogic;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.unit.userleave.logic.UserLeaveLogic;
import tfd.system.unit.userpriv.logic.UserPrivLogic;

import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ChartsLogic {

	/**
	 * 获取图表数据
	 * Time:2016-01-08
	 * Author:Yzz
	 * @param conn
	 * @param orgId
	 * @param priv
	 * @return
	 * @throws Exception
	 */
	public String getChartsData(Connection conn, Account account,String priv,String type)throws Exception{
		String orgId=account.getOrgId();
		JSONArray jsonArr = new JSONArray();
		if(!SysTool.isNullorEmpty(priv)){
			if(type.equals("1")){
				String[] privs = priv.split(",");
				for (int i = 0; i < privs.length; i++) {
					if("email".indexOf(privs[i])>-1){
						JSONObject json = this.getEmailData(conn, orgId);
						if(!jsonArr.contains(json)){
							jsonArr.add(json);
						}
					}
					if("email_attach".indexOf(privs[i])>-1){
						JSONObject json = this.getEmailAttachData(conn, orgId, "email","邮件");
						if(!jsonArr.contains(json)){
							jsonArr.add(json);
						}
					}
					if("attach".indexOf(privs[i])>-1){
						JSONObject json = this.getEmailAttachData(conn, orgId, "%%","");
						if(!jsonArr.contains(json)){
							jsonArr.add(json);
						}
					}
					if("workflow_attach".indexOf(privs[i])>-1){
						JSONObject json = this.getEmailAttachData(conn, orgId, "workflow","工作流");
						if(!jsonArr.contains(json)){
							jsonArr.add(json);
						}
					}
					if("attach_module".indexOf(privs[i])>-1){
						JSONObject json = this.getAttachModuleData(conn, orgId);
						if(!jsonArr.contains(json)){
							jsonArr.add(json);
						}
					}
					if("attach_time".indexOf(privs[i])>-1){
						JSONObject json = this.getAttachTimeData(conn, orgId);
						if(!jsonArr.contains(json)){
							jsonArr.add(json);
						}
					}
					if("diary".indexOf(privs[i])>-1){
						JSONObject json = this.getdiaryLogic(conn,account );
						if(!jsonArr.contains(json)){
							jsonArr.add(json);
						}
					}
				}
			}else{
				if(priv.indexOf("email")>-1){
					JSONObject json = this.getEmailData(conn, orgId);
					jsonArr.add(json);
				}
				if(priv.indexOf("email_attach")>-1){
					JSONObject json = this.getEmailAttachData(conn, orgId, "email","邮件");
					jsonArr.add(json);
				}
				if(priv.indexOf("attach")>-1){
					JSONObject json = this.getEmailAttachData(conn, orgId, "%%","");
					jsonArr.add(json);
				}
				if(priv.indexOf("workflow_attach")>-1){
					JSONObject json = this.getEmailAttachData(conn, orgId, "workflow","工作流");
					jsonArr.add(json);
				}
				if(priv.indexOf("attach_module")>-1){
					JSONObject json = this.getAttachModuleData(conn, orgId);
					jsonArr.add(json);
				}
				if(priv.indexOf("attach_time")>-1){
					JSONObject json = this.getAttachTimeData(conn, orgId);
					jsonArr.add(json);
				}
				if(priv.indexOf("diary")>-1){
					JSONObject json = this.getdiaryLogic(conn,account );
					jsonArr.add(json);
				}
			}
		}
		return jsonArr.toString();
	}
	
	/**
	 * 获取所有部门Id
	 * Time:2016-01-08
	 * Author:Yzz
	 * @param conn
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String getDeptIdsByOrgId(Connection conn,String orgId)throws Exception{
		String deptIds = "";
		String queryStr = "SELECT DEPT_ID FROM DEPARTMENT WHERE ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			deptIds += rs.getString("DEPT_ID")+",";
		}
		if(!deptIds.equals("")){
			deptIds = deptIds.substring(0,deptIds.length()-1);
		}
		return deptIds;
	}
	
	/**
	 * 获取邮件收发情况数据
	 * Time:2016-01-11
	 * Author:Yzz
	 * @param conn
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public JSONObject getEmailData(Connection conn,String orgId)throws Exception{
		String type = "bar";
		JSONObject json = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		JSONArray ArrSend = new JSONArray();
		JSONArray ArrIn = new JSONArray();
		String sql = "SELECT T2.DEPT_NAME ,( SELECT COUNT(ID) FROM EMAIL T1 WHERE  "
				+ "(SELECT T3.DEPT_ID FROM USER_INFO T3 WHERE T3.ACCOUNT_ID = T1.TO_ID "
				+ "AND T3.ORG_ID = T1.ORG_ID ) = T2.DEPT_ID AND  BOX_ID != '2' AND BOX_ID != '3' "
				+ "AND BOX_ID != '4' ) AS IN_NUM, (SELECT COUNT(ID) FROM EMAIL_BODY T4 WHERE  "
				+ "(SELECT T5.DEPT_ID FROM USER_INFO T5 WHERE T5.ACCOUNT_ID = T4.FROM_ID "
				+ "AND T5.ORG_ID = T4.ORG_ID ) = T2.DEPT_ID) AS SEND_NUM FROM DEPARTMENT t2 WHERE ORG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, orgId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			jsonArr.add(rs.getString("DEPT_NAME"));
			ArrSend.add(rs.getString("SEND_NUM"));
			ArrIn.add(rs.getString("IN_NUM"));
		}
		json.accumulate("name", "邮件收发情况");
		json.accumulate("module", "email");
		json.accumulate("legend", "[\"发送\",\"接收\"]");
		json.accumulate("type", type);
		json.accumulate("types", "[\"bar\",\"line\"]");
		json.accumulate("listX", jsonArr);
		JSONArray arr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		jsonObj.accumulate("name", "发送");
		jsonObj.accumulate("type", type);
		jsonObj.accumulate("data", ArrSend);
		arr.add(jsonObj);
		jsonObj = new JSONObject();
		jsonObj.accumulate("name", "接收");
		jsonObj.accumulate("type", type);
		jsonObj.accumulate("data", ArrIn);
		arr.add(jsonObj);
		json.accumulate("list", arr);
		rs.close();
		ps.close();
		return json;
	}
	
	/**
	 * 获取邮件附件分布情况
	 * Time:2016-01-11
	 * Author:Yzz
	 * @param conn
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public JSONObject getEmailAttachData(Connection conn,String orgId,String module,String moduleStr)throws Exception{
		String type = "pie";
		JSONObject json = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		JSONArray ArrSize = new JSONArray();
		String sql = "SELECT T2.DEPT_NAME,(SELECT SUM(T1.FILE_SIZE) FROM ATTACHMENT T1 "
				+ "WHERE (SELECT T3.DEPT_ID FROM USER_INFO T3 WHERE T3.ACCOUNT_ID = T1.CREATE_ACCOUNT_ID "
				+ "AND T3.ORG_ID = T1.ORG_ID) = T2.DEPT_ID AND T1.MODULES LIKE ?) AS FILE_SIZE "
				+ "FROM DEPARTMENT T2 WHERE T2.ORG_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, module);
		ps.setString(2, orgId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			jsonArr.add(rs.getString("DEPT_NAME"));
			JSONObject tmp = new JSONObject();
			double fileSize = 0;
			if(rs.getString("FILE_SIZE")!=null){
				fileSize = (double)(Long.parseLong(rs.getString("FILE_SIZE"))/(1024*1024));
			}
			tmp.accumulate("value", fileSize);
			tmp.accumulate("name", rs.getString("DEPT_NAME"));
			ArrSize.add(tmp);
		}
		json.accumulate("name", moduleStr+"附件容量");
		if(module=="%%"){
			json.accumulate("module", "attach");
		}else{
			json.accumulate("module", module+"_attach");
		}
		json.accumulate("legend", "[\"MB\"]");
		json.accumulate("type", type);
		json.accumulate("types", "[\"pie\",\"bar\",\"line\"]");
		json.accumulate("listX", jsonArr);
		JSONArray arr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		jsonObj.accumulate("name", "MB");
		jsonObj.accumulate("type", type);
		jsonObj.accumulate("data", ArrSize);
		arr.add(jsonObj);
		json.accumulate("list", arr);
		rs.close();
		ps.close();
		return json;
	}

	/**
	 * 获取图表的所有模块
	 * Time:2016-01-12
	 * Author:Yzz
	 * @param conn
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public String getChartsModules(Connection conn, String orgId)throws Exception{
		JSONArray jsonArr = new JSONArray();
		String queryStr = "SELECT CHARTS_ID,CHARTS_MODULE,CHARTS_NAME FROM CHARTS ";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			json.accumulate("chartsId", rs.getString("CHARTS_ID"));
			json.accumulate("chartsModule", rs.getString("CHARTS_MODULE"));
			json.accumulate("chartsName", rs.getString("CHARTS_NAME"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}

	/**
	 * 根据模块得到权限详情
	 * Time:2016-01-14
	 * Author:Yzz
	 * @param conn
	 * @param orgId
	 * @param module
	 * @return
	 * @throws Exception
	 */
	public String getPrivByModule(Connection conn, String orgId, String module)throws Exception{
		this.checkChartsConfig(conn, orgId, module);
		JSONObject json = new JSONObject();
		String sql = "SELECT T1.CHARTS_CONFIG_ID,T1.ACCOUNT_ID,T1.DEPT_ID,T1.PRIV_ID FROM CHARTS_CONFIG T1 WHERE ORG_ID = ? AND "
				+ "(SELECT T2.CHARTS_ID FROM CHARTS T2 WHERE T2.CHARTS_MODULE = ? ) = T1.CHARTS_ID";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, orgId);
		ps.setString(2, module);
		ResultSet rs = ps.executeQuery();
		AccountLogic acclogic=new AccountLogic();
		DeptLogic deptlogic=new DeptLogic();
		UserPrivLogic ulogic=new UserPrivLogic();
		if(rs.next()){
			json.accumulate("chartsConfigId", rs.getString("CHARTS_CONFIG_ID"));
			json.accumulate("user", rs.getString("ACCOUNT_ID"));
			String userName = acclogic.getUserNameStr(conn, rs.getString("ACCOUNT_ID"));
			json.accumulate("userName", userName);
			json.accumulate("dept", rs.getString("DEPT_ID"));
			String deptName = deptlogic.getDeptNameStr(conn, rs.getString("DEPT_ID"));
			json.accumulate("deptName",deptName);
			json.accumulate("priv", rs.getString("PRIV_ID"));
			String privName=ulogic.getUserPrivNameStr(conn, rs.getString("PRIV_ID"));
			json.accumulate("privName", privName);
		}
		rs.close();
		ps.close();
		return json.toString();
	}
	
	/**
	 * 验证图表权限表是否存在该图表的数据
	 * Time:2016-01-14
	 * Author:Yzz
	 * @param conn
	 * @param orgId
	 * @param module
	 * @throws Exception
	 */
	public void checkChartsConfig(Connection conn,String orgId,String module)throws Exception{
		String sql = "SELECT T1.CHARTS_CONFIG_ID FROM CHARTS_CONFIG T1 WHERE ORG_ID = ? AND "
				+ "(SELECT T2.CHARTS_ID FROM CHARTS T2 WHERE T2.CHARTS_MODULE = ? ) = T1.CHARTS_ID ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, orgId);
		ps.setString(2, module);
		ResultSet rs = ps.executeQuery();
		if(!rs.next()){
			this.copyChartsToConfig(conn, orgId, module);
		}
		rs.close();
		ps.close();
	}
	
	/**
	 * 复制图表数据到图表权限数据
	 * Time:2016-01-14
	 * Author:Yzz
	 * @param conn
	 * @param orgId
	 * @param module
	 * @throws Exception
	 */
	public void copyChartsToConfig(Connection conn,String orgId,String module)throws Exception{
		String chartsConfigId = GuId.getGuid();
		String sql = "INSERT INTO CHARTS_CONFIG(CHARTS_CONFIG_ID,CHARTS_ID,ACCOUNT_ID,DEPT_ID,PRIV_ID,ORG_ID) "
				+ "SELECT ?,CHARTS_ID,'','','',? FROM CHARTS WHERE CHARTS_MODULE = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, chartsConfigId);
		ps.setString(2, orgId);
		ps.setString(3, module);
		ps.executeUpdate();
		ps.close();
	}

	/**
	 * 根据模块设置权限
	 * Time:2016-01-14
	 * Author:Yzz
	 * @param conn
	 * @param chartsConfigId
	 * @param accountId
	 * @param deptId
	 * @param privId
	 * @return
	 * @throws Exception
	 */
	public int setPrivByModule(Connection conn, String chartsConfigId,String accountId, String deptId, String privId)throws Exception{
		String sql = "UPDATE CHARTS_CONFIG SET ACCOUNT_ID = ?,DEPT_ID = ?,PRIV_ID = ? WHERE CHARTS_CONFIG_ID = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, accountId);
		ps.setString(2, deptId);
		ps.setString(3, privId);
		ps.setString(4, chartsConfigId);
		int i = ps.executeUpdate();
		return i;
	}

	/**
	 * 根据权限获取图表模块
	 * Time:2016-01-14
	 * Author:Yzz
	 * @param conn
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public String getChartsModulesByPriv(Connection conn, Account account)throws Exception{
		JSONArray jsonArr = new JSONArray();
		ResultSet rs =null;
		String queryStr= null;
		String deptId = new UserInfoLogic().getDeptId(conn, account.getAccountId());
		String privId = new AccountLogic().getAccountByAccountId(conn, account.getAccountId()).getUserPriv();
		String dbType=DbPoolConnection.getInstance().getDbType();
		if(dbType.equals("mysql")){
			queryStr = "SELECT T1.CHARTS_CONFIG_ID,T2.CHARTS_MODULE,T2.CHARTS_NAME FROM CHARTS_CONFIG T1 "
					+ "LEFT JOIN CHARTS T2 ON T1.CHARTS_ID = T2.CHARTS_ID WHERE "
					+ " ( LOCATE('"+account.getAccountId()+"',CONCAT(',',T1.ACCOUNT_ID,',')) > 0 "
					+ " OR LOCATE('"+deptId+"',CONCAT(',',T1.DEPT_ID,',')) > 0 "
					+ " OR LOCATE('"+privId+"',CONCAT(',',T1.PRIV_ID,',')) > 0 "
					+ " OR ACCOUNT_ID = 'userAll' OR DEPT_ID = 'deptAll' OR PRIV_ID = 'privAll') AND T1.ORG_ID = ? ";
		}else if(dbType.equals("oracle")){
			queryStr = "SELECT T1.CHARTS_CONFIG_ID,T2.CHARTS_MODULE,T2.CHARTS_NAME FROM CHARTS_CONFIG T1 "
					+ "LEFT JOIN CHARTS T2 ON T1.CHARTS_ID = T2.CHARTS_ID WHERE "
					+ " ( INSTR(CONCAT(',',T1.ACCOUNT_ID)||',','"+account.getAccountId()+"') > 0 "
					+ " OR INSTR(CONCAT(',',T1.DEPT_ID)||',','"+deptId+"') > 0 "
					+ " OR INSTR(CONCAT(',',T1.PRIV_ID)||',','"+privId+"') > 0 "
					+ " OR TO_CHAR(T1.ACCOUNT_ID) = 'userAll' OR TO_CHAR(T1.DEPT_ID) = 'deptAll' OR TO_CHAR(T1.PRIV_ID) = 'privAll' ) AND T1.ORG_ID = ? ";
		}
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getOrgId());
		rs = ps.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			json.accumulate("chartsModule", rs.getString("CHARTS_MODULE"));
			json.accumulate("chartsName", rs.getString("CHARTS_NAME"));
			jsonArr.add(json);
		}
		rs.close();
		ps.close();
		return jsonArr.toString();
	}
	
	
	public JSONObject getAttachModuleData(Connection conn,String orgId)throws Exception{
		String type = "pie";
		JSONObject json = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		JSONArray ArrSize = new JSONArray();
		String sql = "SELECT MODULES,SUM(FILE_SIZE) AS FILE_SIZE FROM ATTACHMENT WHERE ORG_ID = ? GROUP BY MODULES";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, orgId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			jsonArr.add(this.getModuleName(rs.getString("MODULES")));
			JSONObject tmp = new JSONObject();
			double fileSize = 0;
			if(rs.getString("FILE_SIZE")!=null){
				fileSize = (double)(Long.parseLong(rs.getString("FILE_SIZE"))/(1024*1024));
			}
			tmp.accumulate("value", fileSize);
			tmp.accumulate("name", this.getModuleName(rs.getString("MODULES")));
			ArrSize.add(tmp);
		}
		json.accumulate("name", "附件模块分布");
		json.accumulate("module", "attach_module");
		json.accumulate("legend", "[\"MB\"]");
		json.accumulate("type", type);
		json.accumulate("types", "[\"pie\",\"bar\",\"line\"]");
		json.accumulate("listX", jsonArr);
		JSONArray arr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		jsonObj.accumulate("name", "MB");
		jsonObj.accumulate("type", type);
		jsonObj.accumulate("data", ArrSize);
		arr.add(jsonObj);
		json.accumulate("list", arr);
		rs.close();
		ps.close();
		return json;
	}
	
	public String getModuleName(String module)throws Exception{
		String returnData = "其他";
		if(module == null){
			returnData = "其他";
		}else if(module.equals("email")){
			returnData = "邮件";
		}else if(module.equals("workflow")){
			returnData = "工作流";
		}else if(module.equals("diary")){
			returnData = "日志";
		}else if(module.equals("attachance")){
			returnData = "考勤";
		}else if(module.equals("chat")){
			returnData = "聊天";
		}else if(module.equals("notice")){
			returnData = "公告";
		}else if(module.equals("personfolder")){
			returnData = "个人文件柜";
		}else if(module.equals("publicfolder")){
			returnData = "公共文件柜";
		}else if(module.equals("institution")){
			returnData = "制度";
		}else if(module.equals("news")){
			returnData = "新闻";
		}else if(module.equals("photo")){
			returnData = "图片";
		}
		
		return returnData;
	}
	
	public JSONObject getAttachTimeData(Connection conn,String orgId)throws Exception{
		String type = "line";
		JSONObject json = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		JSONArray ArrSize = new JSONArray();
		String sql = "SELECT DATE_FORMAT(UP_TIME,'%Y%m') UP_TIME,SUM(FILE_SIZE) AS FILE_SIZE FROM ATTACHMENT "
				+ "WHERE ORG_ID = ? GROUP BY DATE_FORMAT(UP_TIME,'%Y%m');";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, orgId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			jsonArr.add(rs.getString("UP_TIME"));
			double fileSize = 0;
			if(rs.getString("FILE_SIZE")!=null){
				fileSize = (double)(Long.parseLong(rs.getString("FILE_SIZE"))/(1024*1024));
			}
			ArrSize.add(fileSize);
		}
		json.accumulate("name", "附件月份分布");
		json.accumulate("module", "attach_time");
		json.accumulate("legend", "[\"月份\"]");
		json.accumulate("type", type);
		json.accumulate("types", "[\"bar\",\"line\"]");
		json.accumulate("listX", jsonArr);
		JSONArray arr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		jsonObj.accumulate("name", "月份");
		jsonObj.accumulate("type", type);
		jsonObj.accumulate("data", ArrSize);
		arr.add(jsonObj);
		json.accumulate("list", arr);
		rs.close();
		ps.close();
		return json;
	}
	
	public JSONObject getdiaryLogic(Connection conn, Account account)throws Exception{
		String type = "bar";
		JSONObject json = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		JSONArray ArrSize = new JSONArray();
		UserInfo userInfo = new UserInfo();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		userInfo = userInfoLogic.getUserInfoByAccount(conn, account);
		String manageDept = userInfo.getManageDept();
		String accountId="";
		if(manageDept.equals("2"))
		{
			accountId="SELECT DISTINCT U.USER_NAME AS USER_NAME,(SELECT COUNT(1) FROM DIARY T1 WHERE T1.ACCOUNT_ID=U.ACCOUNT_ID AND T1.ORG_ID=?) AS NUM FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0' AND (U.DEPT_ID=? OR U.OTHER_DEPT LIKE ?) AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID <=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=? ";
		}else
		{
			accountId="SELECT DISTINCT U.USER_NAME AS USER_NAME,(SELECT COUNT(1) FROM DIARY T1 WHERE T1.ACCOUNT_ID=U.ACCOUNT_ID AND T1.ORG_ID=?) AS NUM FROM  USER_INFO U LEFT JOIN ACCOUNT A ON U.ACCOUNT_ID = A.ACCOUNT_ID LEFT JOIN USER_LEAVE L ON U.LEAD_LEAVE = L.LEAVE_ID  WHERE U.ACCOUNT_ID = A.ACCOUNT_ID AND A.NOT_LOGIN='0'  AND U.LEAD_LEAVE=L.LEAVE_ID AND L.LEAVE_NO_ID<=(SELECT L2.LEAVE_NO_ID FROM USER_LEAVE L2 WHERE L2.LEAVE_ID = ?) OR U.LEAD_ID=? ";
		}
		String queryStr=accountId;
		PreparedStatement ps =conn.prepareStatement(queryStr);
		UserLeaveLogic userLeaveLogic = new UserLeaveLogic();
		if(manageDept.equals("2"))
		{
			ps.setString(1, account.getOrgId());
			ps.setString(2, userInfo.getDeptId());
			ps.setString(3, "%"+userInfo.getDeptId()+"%");
			String leaveNoId = userLeaveLogic.getLeaveNoIdLogic(conn,userInfo.getLeadLeave());
			ps.setString(4, leaveNoId);
			ps.setString(5, userInfo.getLeadId());
		}else
		{
			ps.setString(1, account.getOrgId());
			ps.setString(2, userInfo.getLeadLeave());
			ps.setString(3, userInfo.getLeadId());
		}
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			jsonArr.add(rs.getString("USER_NAME"));
			if(rs.getInt("NUM")==0){
				ArrSize.add(1);	
			}else{
				ArrSize.add(rs.getInt("NUM"));
			}
		}
		json.accumulate("name", "日志记录情况");
		json.accumulate("module", "diary");
		json.accumulate("legend", "[\"日志记录情况\"]");
		json.accumulate("type", type);
		json.accumulate("types", "[\"bar\"]");
		json.accumulate("listX", jsonArr);
		JSONArray arr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		jsonObj.accumulate("name", "日志记录情况");
		jsonObj.accumulate("type", type);
		jsonObj.accumulate("data", ArrSize);
		arr.add(jsonObj);
		json.accumulate("list", arr);
		rs.close();
		ps.close();
		return json;
	}
}
