package tfd.system.mobile.workflow.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import com.system.tool.SysTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.workflow.flowprocess.data.FlowProcess;
import tfd.system.workflow.flowprocess.logic.FlowProcessLogic;
import tfd.system.workflow.workflownext.tool.AutoUserTool;

public class WorkFlowAutoUserTool {
	//智能选择处理
	public JSONArray getAutoUserM(Connection conn,String flowId,String runId,String nextPrcsList ,Account account,int prcsId,int runPrcsId) throws NumberFormatException, Exception
			{
				FlowProcess flowProcess = new FlowProcess();
				FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
				JSONArray jsonArr = new JSONArray();
				String autoUserJsonStr="";
				AutoUserTool autoUserTool = new AutoUserTool();
				UserInfoLogic userInfoLogic = new UserInfoLogic();
				String deptId=userInfoLogic.getDeptId(conn, account.getAccountId());
				if(!SysTool.isNullorEmpty(nextPrcsList))
				{
					if(nextPrcsList.indexOf(",")>0)
					{
						String[] nextPrcsArr = nextPrcsList.split(",");
						for(int i =0;nextPrcsArr.length>i;i++)
						{
							flowProcess=flowProcessLogic.getFlowProcessLogic(conn, flowId, Integer.parseInt(nextPrcsArr[i]));
							//智能处理选人
							JSONObject prcsJson=new JSONObject();
							JSONArray prcsUserArr = new JSONArray();
							String autoUserModle=flowProcess.getAutoUserModle();
							prcsJson.accumulate("prcsId", nextPrcsArr[i]);
							prcsJson.accumulate("prcsName", flowProcess.getPrcsName());
							if(SysTool.isNullorEmpty(autoUserModle))
							{
								ArrayList<Map<String,String>> tmpUserList = autoUserTool.getsPrcsAutoUserList(conn, flowProcess, runId, prcsId,runPrcsId,account);
								for(int j=0;tmpUserList.size()>j;j++)
								{
									JSONObject prcsUser = new JSONObject();
									prcsUser.accumulate("id", tmpUserList.get(0).get("accountId"));
									prcsUser.accumulate("name", tmpUserList.get(0).get("userName"));
									prcsUser.accumulate("headImg",tmpUserList.get(0).get("headImg"));
									prcsUserArr.add(prcsUser);
								}
							}else
							{
							autoUserJsonStr=autoUserTool.getAutoUserJson(conn,flowProcess, autoUserModle, account, deptId, runId, Integer.parseInt(nextPrcsArr[i]),runPrcsId);
							prcsJson.accumulate("autoUser", autoUserJsonStr);	
							}
							prcsJson.accumulate("prcsUser", prcsUserArr);
							jsonArr.add(prcsJson);
							
						}			
					}else{
						JSONObject returnJson=new JSONObject();
						String autoUserModle =autoUserTool.getAutoUserModleLogic(conn, flowId, Integer.parseInt(nextPrcsList));
						returnJson.accumulate("prcsId", nextPrcsList);
						autoUserJsonStr=autoUserTool.getAutoUserJson(conn, flowProcess,autoUserModle, account, deptId, runId, Integer.parseInt(nextPrcsList),runPrcsId);
						returnJson.accumulate("autoUser", autoUserJsonStr);
						jsonArr.add(returnJson);
					}
					
				}
				return jsonArr;
			}
		
}
