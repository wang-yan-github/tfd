package tfd.system.mobilesms.logic;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.system.db.PageTool;
import com.system.tool.GuId;
import com.system.tool.RequestUtil;
import com.system.tool.SysTool;

import tfd.system.mobilesms.data.MobileSms;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.userinfo.logic.UserInfoLogic;

public class MobileSmsLogic {
	//发送手机短信
	public void sendMobileSmsLogic(Connection conn,MobileSms mobileSms) throws NoSuchAlgorithmException, SQLException
	{
		String mobileSmsId=GuId.getGuid();
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		String sendUsers = mobileSms.getAccountIds();
		MobileSms ms = new MobileSms();
		ms.setMobileSmsId(mobileSmsId);
		ms.setSendAccountId(mobileSms.getSendAccountId());
		ms.setSendContent(mobileSms.getSendContent());
		ms.setStatus(mobileSms.getStatus());
		ms.setDelFlag(mobileSms.getDelFlag());
		ms.setSendCount(mobileSms.getSendCount());
		ms.setOrgId(mobileSms.getOrgId());
		if(SysTool.isNullorEmpty(mobileSms.getSendTime()))
		{
			ms.setSendTime(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
		}else
		{
			ms.setSendTime(mobileSms.getSendTime());
		}
		ms.setCreateTime(mobileSms.getCreateTime());
		ms.setOrgId(mobileSms.getOrgId());
		if(!SysTool.isNullorEmpty(sendUsers))
		{
			if(mobileSms.getAccountIds().indexOf(",")>0)
			{
				String [] accountIdArr =sendUsers.split(",");
				for(int i=0;accountIdArr.length>i;i++)
				{
					String mobileNo = userInfoLogic.getUserMobileNoLogic(conn, accountIdArr[i]);
					if(!SysTool.isNullorEmpty(mobileNo))
					{
						ms.setRevMobileNumber(mobileNo);
						this.addMobileSmsLogic(conn, ms);
					}
				}
			}else
			{
				String mobileNo = userInfoLogic.getUserMobileNoLogic(conn,sendUsers);
				if(!SysTool.isNullorEmpty(mobileNo))
				{
					ms.setRevMobileNumber(mobileNo);
					this.addMobileSmsLogic(conn, ms);
				}
			}
		}
		
		String outSideNos = mobileSms.getOutSideNo();
		if(!SysTool.isNullorEmpty(outSideNos))
		{
			if(outSideNos.indexOf(",")>0)
			{
				String [] outSideNoArr = outSideNos.split(",");
				for(int i=0;outSideNoArr.length>i;i++)
				{
					if(!SysTool.isNullorEmpty(outSideNoArr[i]))
					{
						ms.setRevMobileNumber(outSideNoArr[i]);
						this.addMobileSmsLogic(conn, ms);
					}
				}
			}else
			{
					ms.setRevMobileNumber(outSideNos);
					this.addMobileSmsLogic(conn, ms);
			}
		}
		
	
		
	}

	public void addMobileSmsLogic(Connection conn,MobileSms mobileSms) throws SQLException
	{
		String queryStr="INSERT INTO MOBILE_SMS(MOBILE_SMS_ID,SEND_ACCOUNT_ID,SEND_CONTENT,REV_MOBLIE_NUMBER,CREATE_TIME,SEND_TIME,STATUS,DEL_FLAG,SEND_COUNT,ORG_ID)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, mobileSms.getMobileSmsId());
		ps.setString(2, mobileSms.getSendAccountId());
		ps.setString(3,mobileSms.getSendContent());
		ps.setString(4, mobileSms.getRevMobileNumber());
		ps.setString(5, mobileSms.getCreateTime());
		ps.setString(6, mobileSms.getSendTime());
		ps.setString(7,mobileSms.getStatus());
		ps.setString(8, mobileSms.getDelFlag());
		ps.setString(9,mobileSms.getSendCount());
		ps.setString(10, mobileSms.getOrgId());
		ps.executeUpdate();
		ps.close();
	}
	
	
	//手机短信查询
	public String queryMoblieSmsLogic(Connection conn, MobileSms mobileSms,Account account,int pageSize,int page,String sortOrder,String sortName) throws Exception
	{
		List<String> pramList = new ArrayList<String>();
		String queryStr="SELECT ID,MOBILE_SMS_ID,SEND_CONTENT,REV_MOBLIE_NUMBER,CREATE_TIME,STATUS,DEL_FLAG,SEND_COUNT FROM MOBILE_SMS ";
		String whereStr=" WHERE ORG_ID='"+account.getOrgId()+"' ";
		if(!SysTool.isNullorEmpty(mobileSms.getAccountIds()))
		{
			UserInfoLogic userInfoLogic = new UserInfoLogic();
			String mobileNo = userInfoLogic.getUserMobileNoLogic(conn, mobileSms.getAccountIds());
			if(!SysTool.isNullorEmpty(mobileNo))
			{
				whereStr+=" AND REV_MOBLIE_NUMBER='"+mobileNo+"'";
			}
		}
		if(!SysTool.isNullorEmpty(mobileSms.getOutSideNo()))
		{
			whereStr+=" AND REV_MOBLIE_NUMBER='"+mobileSms.getOutSideNo()+"'";
		}
		if(!SysTool.isNullorEmpty(mobileSms.getStatus()))
		{
			whereStr+=" AND STATUS='"+mobileSms.getStatus()+"'";
		}
		if(!SysTool.isNullorEmpty(mobileSms.getDelFlag()))
		{
			whereStr+=" AND DEL_FLAG='"+mobileSms.getDelFlag()+"'";
		}
		if(!SysTool.isNullorEmpty(mobileSms.getCreateTime()))
		{
			whereStr+=" AND CREATE_TIME>'"+mobileSms.getCreateTime()+"'";
		}
		if(!SysTool.isNullorEmpty(mobileSms.getEndCreateTime()))
		{
			whereStr+=" AND CREATE_TIME<'"+mobileSms.getEndCreateTime()+"'";
		}
		queryStr=queryStr+whereStr;
		String optStr= "[]";
		JSONArray optArrJson = new JSONArray();
		optArrJson=JSONArray.fromObject(optStr);
		PageTool pageTool = new PageTool();
		JSONObject json=pageTool.getPageData(conn,queryStr,pramList,pageSize,page,optArrJson,sortOrder,sortName);
		return json.toString();
		
	}
	public String send(String mobile,String msg){
		String param="account=tfdsys&pswd=ts123&mobile="+mobile+"&msg="+msg;
		return RequestUtil.sendPost("http://localhost:8008/ISMS/send.act", param);
	}
}
