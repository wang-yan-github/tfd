package tfd.system.mobile.userpriv.act;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tfd.system.mobile.workflow.logic.WorkflowLogic;

import net.sf.json.JSONObject;

import com.system.global.ResponseConst;
import com.system.tool.SysTool;

public class UserPrivAct {
	
	public void userPrivList(HttpServletRequest request,HttpServletResponse response)throws Exception{
		JSONObject data=JSONObject.fromObject("{'status_code':'500','msg':'请求失败','data':{'time':"+new Date().getTime()/1000+"}}");
		try {
			JSONObject dataTemp=new WorkflowLogic().getTestData("userPrivList");
			if (dataTemp!=null) {
				data=dataTemp;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			response.setContentType(ResponseConst.CONTENT_TYPE_JSON_UTF8);
			PrintWriter writer=response.getWriter();
			writer.print(data.toString());
			SysTool.closePrintWriter(writer);
		}
	}
}
