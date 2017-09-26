package tfd.system.workflow.flowutility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;



import com.system.tool.SysTool;

import tfd.system.unit.account.data.Account;
import tfd.system.unit.dept.data.Department;
import tfd.system.unit.dept.logic.DeptLogic;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.workflow.workflow.data.WorkFlow;
import tfd.system.workflow.workflow.logic.WorkFlowLogic;

public class UitilityTool {
//生成编码
//modle
	public String createCode(Connection conn,String modle,String flowTypeId,String runId,Account account) throws SQLException
	{
		String returnCode=modle;
		if(SysTool.isNullorEmpty(returnCode))
		{
			return "";
		}else
		{
		returnCode=returnCode.replace("[yyyy]", getYearNoF());
		returnCode=returnCode.replace("[yy]", getYearNoT());	
		returnCode=returnCode.replace("[MM]", getMouthNo());	
		returnCode=returnCode.replace("[dd]", getDayNo());	
		returnCode=returnCode.replace("[HH]", getHourNo());	
		returnCode=returnCode.replace("[mm]", getMinNo());	
		returnCode=returnCode.replace("[ss]", getSsNo());	
		returnCode=returnCode.replace("[F]", getFlowName(conn,flowTypeId));	
		returnCode=returnCode.replace("[U]", getUserName(conn,account));	
		returnCode=returnCode.replace("[D]", getDeptName(conn,account));	
		return returnCode;
		}
	}

//获用流程名称[F]
public String getFlowName(Connection conn,String flowTypeId) throws SQLException
{
	String returnData="";
	WorkFlowLogic wfl = new WorkFlowLogic();
	WorkFlow workFlow = new WorkFlow();
	workFlow=wfl.getWorkFlowByFlowTypeIdLogic(conn, flowTypeId);
	returnData = workFlow.getFlowName();
	return returnData;
}
//当前用户名[U]
public String getUserName(Connection conn,Account account) throws SQLException
{
	UserInfoLogic ufl = new UserInfoLogic();
	UserInfo uf = new UserInfo();
	uf=ufl.getUserInfoByAccount(conn, account);
	return uf.getUserName();
}
//获取部门[D]
public String getDeptName(Connection conn,Account account) throws SQLException
{
	DeptLogic  df = new DeptLogic();
	Department dept = new Department();
	UserInfoLogic ufl = new UserInfoLogic();
	UserInfo uf = new UserInfo();
	uf=ufl.getUserInfoByAccount(conn, account);
	dept = df.getDepartmentLogic(conn, uf.getDeptId());
	return dept.getDeptName();
}

//获取四位年份[yyyy]
	public String getYearNoF()
	{
		String yearStr="";
		yearStr=new SimpleDateFormat("yyyy").format(new Date());
		return yearStr;
	}
//获取四位年份[yy]
	public String getYearNoT()
	{
		String yearStr="";
		yearStr=new SimpleDateFormat("yy").format(new Date());
		return yearStr;
	}	
//获取月份[MM]
	public String getMouthNo()
	{
		String mouthStr="";
		mouthStr=new SimpleDateFormat("MM").format(new Date());
		return mouthStr;
	}
//获取日期[dd]
	public String getDayNo()
	{
		String dayStr="";
		dayStr=new SimpleDateFormat("dd").format(new Date());
		return dayStr;
	}
	//获取分钟[HH]
		public String getHourNo()
		{
			String hhStr="";
			hhStr=new SimpleDateFormat("HH").format(new Date());
			return hhStr;
		}
//获取分钟[mm]
	public String getMinNo()
	{
		String minStr="";
		minStr=new SimpleDateFormat("mm").format(new Date());
		return minStr;
	}

//获取日期
	public String getSsNo()
	{
		String ssStr="";
		ssStr=new SimpleDateFormat("ss").format(new Date());
		return ssStr;
	}
	
	//生成流程流水编号
	public String createAutoNum(Connection conn,String flowTypeId) throws SQLException
	{
		WorkFlowLogic workFlowLogic = new WorkFlowLogic();
		WorkFlow workFlow = new WorkFlow();
		workFlow=workFlowLogic.getWorkFlowByFlowTypeIdLogic(conn, flowTypeId);
		String autoNum="";
		String queryStr="SELECT COUNT(*) FROM FLOW_RUN WHERE FLOW_ID=?";
		PreparedStatement ps =conn.prepareStatement(queryStr);
		ps.setString(1, flowTypeId);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			autoNum=(rs.getInt(1)+1)+"";
		}
		if(workFlow.getAutoNum()!=null&&workFlow.getAutoNum()!="")
			{
			int autoNumlenght=Integer.parseInt(workFlow.getAutoNum());
			if(autoNumlenght-autoNum.length()>0)
			{
				int toInt=autoNumlenght-autoNum.length();
				for(int i=0;toInt>i;i++)
				{
					autoNum="0"+autoNum;
				}
			}
		}
		rs.close();
		ps.close();
		return autoNum;
	}
}