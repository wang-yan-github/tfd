package tfd.system.mobile.jpush;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.system.global.SysProps;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.schedule.ScheduleResult;
import net.sf.json.JSONArray;

public class JpushLogic {
	protected static final Logger LOG = LoggerFactory.getLogger(JpushLogic.class);
	
	/**
	 * 生成极光推送消息体
	 * @param aliaes
	 * @param extras
	 * @param Content
	 * @param time
	 * @return
	 * @throws Exception
	 */
	public static Integer JpushALogic(JSONArray aliaes,Map<String, String> extras,String Content ,String time)throws Exception{
		PushPayload payload = null;
		payload= PushPayload.newBuilder()  
	                .setPlatform(Platform.android_ios())  
	                .setAudience(Audience.alias(aliaes))  
	                .setOptions(Options.newBuilder().setApnsProduction(true).build())
	                .setNotification(Notification.newBuilder()  
	                        .addPlatformNotification(AndroidNotification.newBuilder()  
	                                .setAlert(Content).setBuilderId(1).addExtras(extras).build())  
	                        .addPlatformNotification(IosNotification.newBuilder()  
	                                .setAlert(Content).setBadge(1).setSound("default")
	                                .addExtras(extras).build())  
	                        .build())  
	                .build();
		System.out.println(payload);
		return sendPush(payload,time);
	}
	/**
	 * 极光推送
	 * @param payload
	 * @param time
	 * @return
	 * 
	 * @throws Exception
	 */
	public static final String APPKEY = "cc1d5c53ec9ae78648a971fc";
	public static final String MASTERSECRET = "0fd719513a84ddf6d04dee1a";
	public static Integer sendPush(PushPayload payload,String time)throws Exception {
		Integer code = 0;
//		String APPKEY= SysProps.getString("APPKEY");
//		String MASTERSECRET=SysProps.getString("MASTERSECRET");
		JPushClient jpushClient=null;
		try {
			jpushClient = new JPushClient(MASTERSECRET, APPKEY);
			if(!time.equals("")){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date Stime=sdf.parse(time);
				System.out.println(Stime);
				if(Stime.getTime()<new Date().getTime()){
					PushResult result = null;
					result=jpushClient.sendPush(payload) ;
					LOG.info("Got result - " + result);	
				}else{
				ScheduleResult result = null;
				result =jpushClient.createSingleSchedule("定时发送", time, payload) ;
				LOG.info("Got result - " + result);	
				}
			}
			else{
				PushResult result = null;
				result=jpushClient.sendPush(payload) ;
				LOG.info("Got result - " + result);
			}
		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later. ", e);
		} catch (APIRequestException e) {
			LOG.error("Error response from JPush server. Should review and fix it. ", e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
			LOG.info("Msg ID: " + e.getMsgId());
			code = e.getErrorCode();
		}
		return code;
	}
}
