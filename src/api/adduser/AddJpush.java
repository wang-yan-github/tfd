package api.adduser;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hdgf.chunks.Chunk;

import net.sf.json.JSONArray;
import tfd.system.mobile.jpush.JpushLogic;

public class AddJpush {
	public static void main(String[] args) throws Exception {
		//自定义扩展字段，方便客户端获取消息信息进行处理
	Map<String, String> extras = new HashMap<String, String>();
	//参数E 代表事件类型   对应的值 是不能改变的
//	类型 （E）			含义	
//	Schedule			日程
//	OpenWork			办理工作流
//	LookWork 			查看工作流
//	other				其他（除以上几种的其他类型）
		//OpenWork 类型 ：办理工作流   Map 中需要添加的字段为 （参数名不能更改 ，参数一个都不能少）
//	extras.put("E", "OpenWork");									// 参数E     代表事件类型
//	extras.put("runId", "8B4038A3-689C-C60F-77A4-381F81AABAAE");  									//runId     代表流水号
//	extras.put("runPrcsId", "1");							    //runPrcsId 代表流程实际步骤号							
//	extras.put("prcsId", "1");									//prcsId    代表流程设计步骤号
	
	//LookWork  类型 查看工作流  
	extras.put("E", "LookWork");
	extras.put("runId", "8B4038A3-689C-C60F-77A4-381F81AABAAE");			//runId     代表流水号
//	
	//Schedule 类型日程
//	extras.put("E", "Schedule");
//	extras.put("id", "B1EFF9A1-7E3B-43DE-F94F-8169A0E2BF16");			//日程id
	
	//other 类型 其他  只需要定义map  不需要存入什么字段
	
	//消息内容
	String alertString="消息内容";
	
	// 发送人  即对应的 accountId 用户的账号 存入JSONArray 数组
	JSONArray aliases=new JSONArray();
	aliases.add("yzz");
	
	//推送时间  格式（"yyyy-MM-dd hh:mm:ss"）  如果是立即推送  则 time=""
	String time="";
	//初始化 极光推送的类
	JpushLogic jp=new JpushLogic();
	//调用方法 进行推送						
	Integer resultNum= jp.JpushALogic(aliases, extras, alertString, time);
	System.out.println(resultNum);
	}
}
