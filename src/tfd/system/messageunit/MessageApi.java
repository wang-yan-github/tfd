package tfd.system.messageunit;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.system.tool.GuId;
import com.system.tool.SysTool;

import tfd.system.mobilesms.data.MobileSms;
import tfd.system.mobilesms.logic.MobileSmsLogic;
import tfd.system.sms.data.Sms;
import tfd.system.sms.logic.SmsLogic;
import tfd.system.unit.userinfo.logic.UserInfoLogic;

public class MessageApi {
	//提醒机制
public void sendMessage(Connection conn,String smsType,JSONObject smsRemindJson,String content,String fromAccountId,List<String> toAccountList,String orgId) throws Exception
{
	if(smsRemindJson.getString("wxsms").equals("1"))
	{
		
	}
	if(smsRemindJson.getString("sitesms").equals("1"))
	{
		Sms sms = new Sms();
		SmsLogic smsLogic = new SmsLogic();
		sms.setSmsId(GuId.getGuid());
		sms.setSmsFrom(fromAccountId);
		sms.setSmsContent(content);
		sms.setSmsType(smsType);
		sms.setSmsSendTime(new Date());
		sms.setSmsFlag("1");
		sms.setSmsStatus("0");
		for(int i=0;toAccountList.size()>i;i++)
		{
			sms.setSmsTo(toAccountList.get(i));
			smsLogic.sendSmsLogic(conn, sms);
		}
	}
	if(smsRemindJson.getString("mobilesms").equals("1"))
	{
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		MobileSms mobileSms = new MobileSms();
		MobileSmsLogic mobileLogic = new MobileSmsLogic();
		mobileSms.setMobileSmsId(GuId.getGuid());
		mobileSms.setSendAccountId(fromAccountId);
		mobileSms.setSendContent(content);
		mobileSms.setCreateTime(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
		mobileSms.setSendTime(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
		mobileSms.setOrgId(orgId);
		for(int i=0;toAccountList.size()>i;i++)
		{
			String revMobileNumber=userInfoLogic.getUserMobileNoLogic(conn,toAccountList.get(i));
			mobileSms.setRevMobileNumber(revMobileNumber);
			mobileLogic.addMobileSmsLogic(conn, mobileSms);
		}
	}
	if(smsRemindJson.getString("emailsms").equals("1"))
	{
		
	}
	if(smsRemindJson.getString("webemilesms").equals("1"))
	{
		
	}
}
//新闻 公告类型的提醒机制
public void addMessage(Connection conn,String smsType,JSONObject smsRemindJson,String content,String fromAccountId,List<String> toAccountList,String orgId,String sendTime) throws Exception{
	
	if(smsRemindJson.getString("wxsms").equals("1"))
	{
		
	}
	if(smsRemindJson.getString("sitesms").equals("1"))
	{
		Sms sms = new Sms();
		SmsLogic smsLogic = new SmsLogic();
		sms.setSmsId(GuId.getGuid());
		sms.setSmsFrom(fromAccountId);
		sms.setSmsContent(content);
		sms.setSmsType(smsType);
		sms.setSmsSendTime(SysTool.getDateByStr(sendTime));
		sms.setSmsFlag("1");
		sms.setSmsStatus("0");
		for(int i=0;toAccountList.size()>i;i++)
		{
			sms.setSmsTo(toAccountList.get(i));
			smsLogic.sendSmsLogic(conn, sms);
		}
	}
	if(smsRemindJson.getString("mobilesms").equals("1"))
	{
		UserInfoLogic userInfoLogic = new UserInfoLogic();
		MobileSms mobileSms = new MobileSms();
		MobileSmsLogic mobileLogic = new MobileSmsLogic();
		mobileSms.setMobileSmsId(GuId.getGuid());
		mobileSms.setSendAccountId(fromAccountId);
		mobileSms.setSendContent(content);
		mobileSms.setCreateTime(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
		mobileSms.setSendTime(sendTime);
		mobileSms.setOrgId(orgId);
		for(int i=0;toAccountList.size()>i;i++)
		{
			String revMobileNumber=userInfoLogic.getUserMobileNoLogic(conn,toAccountList.get(i));
			mobileSms.setRevMobileNumber(revMobileNumber);
			mobileLogic.addMobileSmsLogic(conn, mobileSms);
		}
	}
	if(smsRemindJson.getString("emailsms").equals("1"))
	{
		
	}
	if(smsRemindJson.getString("webemilesms").equals("1"))
	{
		
	}
}

}
