package tfd.system.register.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.system.tool.GuId;
import com.system.tool.MD5Util;
import com.system.tool.SysTool;

import tfd.system.unit.account.data.Account;
import tfd.system.unit.dept.data.Department;
import tfd.system.unit.org.data.Unit;
import tfd.system.unit.userinfo.data.UserInfo;

public class RegisterLogic {
	/**
	 * 添加Unit 单位信息数据
	 * Time 2016-1-20
	 * Author Wp
	 * @param conn
	 * @param unit
	 * @return
	 * @throws Exception
	 */
	public int addUnitLogic(Connection conn,Unit unit,Account account)throws Exception{
		String queryStr="INSERT INTO UNIT (ORG_NAME,ORG_ADD,ORG_TEL,ORG_FAX,ORG_POST,ORG_EMAIL,ORG_ID) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, unit.getOrgName());
		ps.setString(2, unit.getOrgAdd());
		ps.setString(3, unit.getOrgTel());
		ps.setString(4, unit.getOrgFax());
		ps.setString(5, unit.getOrgPost());
		ps.setString(6, unit.getOrgEmail());
		ps.setString(7, unit.getOrgId());
		int i=ps.executeUpdate();
		if(i>0){
			this.initLogic(conn, unit.getOrgId(), account);
			this.addOrgConfig(conn, unit.getOrgName(), account.getAccountId(), unit.getOrgId());
		}
		ps.close();
		return i;
	}
	/**
	 * 初始化组织机构数据
	 * Time 2016-1-20
	 * Author Wp
	 * @param conn
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public int initLogic(Connection conn,String orgId,Account account)throws Exception{
		String queryStr="";
		PreparedStatement ps = null;
		//添加sysConfig 数据
		queryStr="INSERT INTO SYS_CONFIG (SYS_CONFIG_ID,UPDATE_NAME,INIT_PWD,PWD_CYCLE,OUT_PWD,PWD_WIDTH,IS_ABC,MISS_NUM,MISS_TIME,MISS_PWD,FIND_PWD,REMBER_USER,MORE_LOGIN,COM_WITH_PHONE,USE_KEY,DOMAIN_LOGIN,USER_IP,REMBER_STATUS,CHAT_HOST,LISTENNER_PORT,CHAT_PORT,ORG_ID) VALUES ('2', '1', '2', '180', '2', '6-20', '2', '3', '10', '2', '2', '1', '2', '1', '2', '2', '3', '2', 'http://115.29.170.136', '8090', '8080', ?);";
		ps=conn.prepareStatement(queryStr);
		ps.setString(1, orgId);
		int i=0;
		i=ps.executeUpdate();
		//添加message_config 初始数据
		List<String> messageList=new ArrayList<String>();
		messageList.add("workNext");
		messageList.add("passTime");
		messageList.add("workEnd");
		messageList.add("workFollow");
		messageList.add("notice");
		messageList.add("news");
		messageList.add("calendar");
		messageList.add("diary");
		messageList.add("meeting");
		messageList.add("email");
		String messageValue="{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}";
		queryStr="INSERT INTO MESSAGE_CONFIG (M_CONFIG_ID,MODULE,VALUE,ORG_ID) VALUES(?,?,?,?)";
		ps=conn.prepareStatement(queryStr);
		for (int j = 0; j < messageList.size(); j++) {
			ps.setString(1, GuId.getGuid());
			ps.setString(2, messageList.get(j));
			ps.setString(3, messageValue);
			ps.setString(4, orgId);
			i=ps.executeUpdate();
		}
		//添加 code_class 初始数据
		List<String> codelist=new ArrayList<String>();
		queryStr="INSERT INTO CODE_CLASS (CODE_ID,CODE_PID,CODE_NAME,PAGE_CODE,CODE_VALUE,ORG_ID) VALUES ('0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '0', '新闻分类', NULL, 'news', '"+orgId+"')";
		codelist.add(queryStr);
		queryStr="INSERT INTO CODE_CLASS (CODE_ID,CODE_PID,CODE_NAME,PAGE_CODE,CODE_VALUE,ORG_ID) VALUES ('24716807-9F02-B284-B229-54C89C97353E', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '公司新闻', NULL, '公司新闻', '"+orgId+"')";
		codelist.add(queryStr);
		queryStr="INSERT INTO CODE_CLASS (CODE_ID,CODE_PID,CODE_NAME,PAGE_CODE,CODE_VALUE,ORG_ID) VALUES ('F2EBBD7C-3D0D-9F12-0BD4-63BED149C04F', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '部门新闻', NULL, '部门新闻', '"+orgId+"')";
		codelist.add(queryStr);
		queryStr="INSERT INTO CODE_CLASS (CODE_ID,CODE_PID,CODE_NAME,PAGE_CODE,CODE_VALUE,ORG_ID) VALUES ('E430FA5A-989B-E24E-1C6C-E388AD92DC60', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '行业新闻', NULL, '行业新闻',  '"+orgId+"')";
		codelist.add(queryStr);
		queryStr="INSERT INTO CODE_CLASS (CODE_ID,CODE_PID,CODE_NAME,PAGE_CODE,CODE_VALUE,ORG_ID) VALUES ('8D13BDBA-505E-4BD0-685B-D48F81BF2F16', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '图片新闻', NULL, '图片新闻', '"+orgId+"')";
		codelist.add(queryStr);
		queryStr="INSERT INTO CODE_CLASS (CODE_ID,CODE_PID,CODE_NAME,PAGE_CODE,CODE_VALUE,ORG_ID) VALUES ('A335D2E5-11EF-65EC-62BD-11AD40A330AC', '0', '工作流分类', NULL, 'workflowtype',  '"+orgId+"')";
		codelist.add(queryStr);
		queryStr="INSERT INTO CODE_CLASS (CODE_ID,CODE_PID,CODE_NAME,PAGE_CODE,CODE_VALUE,ORG_ID) VALUES ('A2D28954-7EE8-83BC-B3EC-9F1F8078D013', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '项目管理', NULL, 'project', '"+orgId+"')";
		codelist.add(queryStr);
		queryStr="INSERT INTO CODE_CLASS (CODE_ID,CODE_PID,CODE_NAME,PAGE_CODE,CODE_VALUE,ORG_ID) VALUES ('2FA62B63-9972-4FC5-D908-2E34F4BE56EA', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '系统工作流', NULL, 'workflow', '"+orgId+"' )";
		codelist.add(queryStr);
		queryStr="INSERT INTO CODE_CLASS (CODE_ID,CODE_PID,CODE_NAME,PAGE_CODE,CODE_VALUE,ORG_ID) VALUES ('A4B51548-6B0F-261A-8221-35409DEED328', '0', '公告分类', NULL, 'notice', '"+orgId+"')";
		codelist.add(queryStr);
		queryStr="INSERT INTO CODE_CLASS (CODE_ID,CODE_PID,CODE_NAME,PAGE_CODE,CODE_VALUE,ORG_ID) VALUES ('D4156023-6C7A-9F5A-AA3F-9378ACF6D2A6', 'A4B51548-6B0F-261A-8221-35409DEED328', '部门公告', NULL, '部门公告', '"+orgId+"')";
		codelist.add(queryStr);
		queryStr="INSERT INTO CODE_CLASS (CODE_ID,CODE_PID,CODE_NAME,PAGE_CODE,CODE_VALUE,ORG_ID) VALUES ('E636536E-4681-484B-4C6E-8B089B42A99A', 'A4B51548-6B0F-261A-8221-35409DEED328', '通知', NULL, '通知', '"+orgId+"')";
		codelist.add(queryStr);
		queryStr="INSERT INTO CODE_CLASS (CODE_ID,CODE_PID,CODE_NAME,PAGE_CODE,CODE_VALUE,ORG_ID) VALUES ('31B0264F-D758-88BD-8ADE-D972C26FEF8A', 'A4B51548-6B0F-261A-8221-35409DEED328', '决定', NULL, '决定', '"+orgId+"')";
		codelist.add(queryStr);
		queryStr="INSERT INTO CODE_CLASS (CODE_ID,CODE_PID,CODE_NAME,PAGE_CODE,CODE_VALUE,ORG_ID) VALUES ('CF08C591-A38C-0C46-1C9D-769F0D321D4C', 'A4B51548-6B0F-261A-8221-35409DEED328', '其他', NULL, '其他', '"+orgId+"')";
		codelist.add(queryStr);
		queryStr="INSERT INTO CODE_CLASS (CODE_ID,CODE_PID,CODE_NAME,PAGE_CODE,CODE_VALUE,ORG_ID) VALUES ('8C07AE92-7FD1-1165-C052-54BA2149E50E', 'A4B51548-6B0F-261A-8221-35409DEED328', '公告', NULL, '公告', '"+orgId+"')";
		codelist.add(queryStr);
		queryStr="INSERT INTO CODE_CLASS (CODE_ID,CODE_PID,CODE_NAME,PAGE_CODE,CODE_VALUE,ORG_ID) VALUES ('167EC5C3-F91B-FC44-4612-044832D2BA06', '0', '客户行业类型', NULL, 'customer', '"+orgId+"')";
		codelist.add(queryStr);
		queryStr="INSERT INTO CODE_CLASS (CODE_ID,CODE_PID,CODE_NAME,PAGE_CODE,CODE_VALUE,ORG_ID) VALUES ('36F594E0-1AFA-4006-6C53-9B77C816F6C7', '167EC5C3-F91B-FC44-4612-044832D2BA06', '建筑行业', NULL, '建筑行业', '"+orgId+"')";
		codelist.add(queryStr);
		queryStr="INSERT INTO CODE_CLASS (CODE_ID,CODE_PID,CODE_NAME,PAGE_CODE,CODE_VALUE,ORG_ID) VALUES ('F806C1EC-FE60-B7C2-573D-150A98BC7806', '167EC5C3-F91B-FC44-4612-044832D2BA06', '科研行业', NULL, '科研行业', '"+orgId+"')";
		codelist.add(queryStr);
		queryStr="INSERT INTO CODE_CLASS (CODE_ID,CODE_PID,CODE_NAME,PAGE_CODE,CODE_VALUE,ORG_ID) VALUES ('48E60FA0-6404-7C38-517E-7E2AB2E0C330', '167EC5C3-F91B-FC44-4612-044832D2BA06', '软件行业', NULL, '软件行业', '"+orgId+"')";
		codelist.add(queryStr);
		queryStr="INSERT INTO CODE_CLASS (CODE_ID,CODE_PID,CODE_NAME,PAGE_CODE,CODE_VALUE,ORG_ID) VALUES ('D9E22569-E8E6-284D-A102-0BCB7F6AA61E', '167EC5C3-F91B-FC44-4612-044832D2BA06', '教育行业', NULL, '教育行业', '"+orgId+"')";
		codelist.add(queryStr);
		queryStr="INSERT INTO CODE_CLASS (CODE_ID,CODE_PID,CODE_NAME,PAGE_CODE,CODE_VALUE,ORG_ID) VALUES ('E0DAA36E-84F7-A410-5144-E55AFA6593B6', '167EC5C3-F91B-FC44-4612-044832D2BA06', '媒体/广告/文化', NULL, '媒体/广告/文化', '"+orgId+"')";
		codelist.add(queryStr);
		for (int j = 0; j < codelist.size(); j++) {
			ps=conn.prepareStatement(codelist.get(j));
			i=ps.executeUpdate();
		}
		
		//创建app_icon表初始数据
		queryStr="INSERT INTO APP_ICON (ICON_ID,MODULE_NAME,ORG_ID,SORT,SELECTED)VALUES(1,'日程安排','"+orgId+"',1,1)";
		ps=conn.prepareStatement(queryStr);
		i=ps.executeUpdate();
		queryStr="INSERT INTO APP_ICON (ICON_ID,MODULE_NAME,ORG_ID,SORT,SELECTED)VALUES(2,'工作日志','"+orgId+"',2,1)";
		ps=conn.prepareStatement(queryStr);
		i=ps.executeUpdate();
		queryStr="INSERT INTO APP_ICON (ICON_ID,MODULE_NAME,ORG_ID,SORT,SELECTED)VALUES(3,'定位考勤','"+orgId+"',3,1)";
		ps=conn.prepareStatement(queryStr);
		i=ps.executeUpdate();
		queryStr="INSERT INTO APP_ICON (ICON_ID,MODULE_NAME,ORG_ID,SORT,SELECTED)VALUES(4,'工作审批','"+orgId+"',4,1)";
		ps=conn.prepareStatement(queryStr);
		i=ps.executeUpdate();
		queryStr="INSERT INTO APP_ICON (ICON_ID,MODULE_NAME,ORG_ID,SORT,SELECTED)VALUES(5,'客户管理','"+orgId+"',5,1)";
		ps=conn.prepareStatement(queryStr);
		i=ps.executeUpdate();
		
		
		//创建初始部门数据
		Department dept = new Department();
		String deptId = GuId.getGuid();
		String deptName = "董事会";
		dept.setOrgId(orgId);
		dept.setDeptId(deptId);
		dept.setDeptName(deptName);
		dept.setOrgleaveId("0");
		dept.setDeptLongId("0/"+deptId);
		dept.setDeptLongName(deptName);
		queryStr = "INSERT INTO DEPARTMENT ("
				+ "			DEPT_ID,DEPT_NAME,ORG_LEAVE_ID,DEPT_LONG_NAME,"
				+ "			ORG_ID,DEPT_LONG_ID)"
				+ "		VALUES(?,?,?,?,?,?)";
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, dept.getDeptId());
		ps.setString(2, dept.getDeptName());
		ps.setString(3, dept.getOrgLeaveId());
		ps.setString(4, dept.getDeptLongName());
		ps.setString(5, dept.getOrgId());
		ps.setString(6, dept.getDeptLongId());
		ps.executeUpdate();
		
		//初始化 user_priv 表数据
		queryStr="INSERT INTO USER_PRIV (USER_PRIV_ID,USER_PRIV_LEAVE,USER_PRIV_NAME,USER_PRIV_STR,ORG_ID)VALUES(?,?,?,?,?)";
		ps = conn.prepareStatement(queryStr);
		String userPriv=GuId.getGuid();
		String userPrivStr="1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85";
		ps.setString(1, userPriv);
		ps.setString(2, "999");
		ps.setString(3, "管理员");
		ps.setString(4, userPrivStr);
		ps.setString(5, orgId);
		ps.executeUpdate();
		
		//初始化 user_leave表数据
		String leaveId=GuId.getGuid();
		queryStr="INSERT INTO USER_LEAVE (LEAVE_ID ,LEAVE_NO_ID,LEAVE_NAME,MIDDING,ORG_ID)VALUES(?,?,?,?,?)";
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, leaveId);
		ps.setString(2, "999");
		ps.setString(3, "管理员");
		ps.setString(4, "");
		ps.setString(5, orgId);
		ps.executeUpdate();
		//初始化attend_config 和 attend_time 表数据
		String attendConfigid=GuId.getGuid();
		String attendtime=GuId.getGuid();
		queryStr="INSERT INTO ATTEND_CONFIG (ATTEND_CONFIG_ID,CONFIG_NAME,CONFIG_TYPE,ATTEND_TIME_ID,ORG_ID)VALUES(?,?,?,?,?)";
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, attendConfigid);
		ps.setString(2, "正常班");
		ps.setString(3, "1");
		ps.setString(4, attendtime);
		ps.setString(5, orgId);
		ps.executeUpdate();
		queryStr="INSERT INTO ATTEND_TIME (ATTEND_TIME_ID,ATTEND_CONFIG_ID,TIME1,TIME2,TIME_TYPE1,TIME_TYPE2,ORG_ID)VALUE(?,?,?,?,?,?,?)";
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, attendtime);
		ps.setString(2, attendConfigid);
		ps.setString(3, "09:00:00");
		ps.setString(4, "17:30:00");
		ps.setString(5, "1");
		ps.setString(6, "2");
		ps.setString(7, orgId);
		ps.executeUpdate();
		//添加管理员数据
		String accountId = account.getAccountId();
		System.out.println(account.getPassWord());
		account.setAccountId(accountId);
		account.setPassWord(MD5Util.getMD5(account.getPassWord()));
		account.setPasswordType("1");
		account.setTheme("1");
		account.setUserPriv(userPriv);
		account.setNotLogin("0");
		account.setByName("");
		account.setLanguage("1");
		account.setOrgId(orgId);
		queryStr = "INSERT INTO ACCOUNT(ACCOUNT_ID,PASSWORD_TYPE,PASS_WORD,THMEM,USER_ID,USER_PRIV,PAGE_PRIV,NOT_LOGIN,"
				+ "LANGUAGE,BY_NAME,ORG_ID)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, account.getAccountId());
		ps.setString(2, account.getPasswordType());
		ps.setString(3, account.getPassWord());
		ps.setString(4, account.getTheme());
		ps.setString(5, account.getUserId());
		ps.setString(6, account.getUserPriv());
		ps.setString(7, account.getPagePriv());
		ps.setString(8, account.getNotLogin());
		ps.setString(9, account.getLanguage());
		ps.setString(10, account.getByName());
		ps.setString(11, account.getOrgId());
		ps.executeUpdate();
		//初始化 user_info 数据
		UserInfo userInfo = new UserInfo();
		String userId = GuId.getGuid();
		userInfo.setUserId(userId);
	    userInfo.setAccountId(account.getAccountId());
	    userInfo.setUserName("管理员");
	    userInfo.setSex("男");
	    userInfo.setDeptId(deptId);
	    userInfo.setLeadId("");
	    userInfo.setPostPriv(userPriv);
	    userInfo.setManageDept("1");
	    userInfo.setLeadLeave(leaveId);
	    userInfo.setOrgId(orgId);
	    
	    queryStr = "INSERT INTO USER_INFO (USER_ID,ACCOUNT_ID,USER_NAME,SEX,DEPT_ID,LEAD_ID,POST_PRIV,OTHER_PRIV,HOME_ADD,HOME_TEL,MOBILE_NO,QQ,E_MAILE,WORK_ID,MANAGE_DEPT,OTHER_DEPT,LEAD_LEAVE,ORG_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, userInfo.getUserId());
		ps.setString(2, userInfo.getAccountId());
		ps.setString(3, userInfo.getUserName());
		ps.setString(4, userInfo.getSex());
		ps.setString(5, userInfo.getDeptId());
		ps.setString(6, userInfo.getLeadId());
		ps.setString(7, userInfo.getPostPriv());
		ps.setString(8, userInfo.getOtherPriv());
		ps.setString(9, userInfo.getHomeAdd());
		ps.setString(10, userInfo.getHomeTel());
		ps.setString(11, userInfo.getMobileNo());
		ps.setString(12, userInfo.getQq());
		ps.setString(13, userInfo.geteMail());
		ps.setString(14, userInfo.getWorkId());
		ps.setString(15, userInfo.getManageDept());
		ps.setString(16, userInfo.getOtherDept());
		ps.setString(17, userInfo.getLeadLeave());
		ps.setString(18, userInfo.getOrgId());
		ps.executeUpdate();
		
		
		ps.close();
		return i;
	}
	/**
	 * 添加组织机构信息
	 * Time 2016-1-20
	 * Author Wp
	 * @param conn
	 * @param orgName
	 * @param accountId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public int addOrgConfig(Connection conn,String orgName,String accountId,String orgId)throws Exception{
		String queryStr="INSERT INTO ORG_CONFIG (ORG_NAME,ORG_ADMIN,CREATE_TIME,ATTACH_MAX,TYPE,ORG_ID)VALUES (?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, orgName);
		ps.setString(2, accountId);
		ps.setString(3, SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
		ps.setString(4, "4");
		ps.setString(5, "PC");
		ps.setString(6, orgId);
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
}
