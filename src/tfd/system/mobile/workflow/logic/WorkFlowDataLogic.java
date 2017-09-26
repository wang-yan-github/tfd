package tfd.system.mobile.workflow.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.dept.logic.DeptLogic;
import tfd.system.unit.userinfo.logic.UserInfoLogic;
import tfd.system.unit.userpriv.logic.UserPrivLogic;
import tfd.system.workflow.flowformitem.logic.FLowFormItemLogic;
import tfd.system.workflow.flowprocess.data.FlowProcess;
import tfd.system.workflow.flowprocess.logic.FlowProcessLogic;
import tfd.system.workflow.flowrunprcs.data.FlowRunPrcs;
import tfd.system.workflow.flowrunprcs.logic.FlowRunPrcsLogic;
import tfd.system.workflow.flowutility.UitilityTool;
import tfd.system.workflow.workflow.data.WorkFlow;
import tfd.system.workflow.workflow.logic.WorkFlowLogic;

import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.SysTool;

public class WorkFlowDataLogic {
	public BaseDao dao=new BaseDaoImpl();
	public JSONArray getWorkFlowDataLogic(FlowRunPrcs flowRunPrcs, FlowProcess flowProcess, Account account,Connection conn)throws Exception{
		JSONArray formItem=new JSONArray();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		PreparedStatement formDataStmt=null;
		ResultSet formDataRs=null;
		try {

			String sql="select title,xtype,field_name,model from flow_form_item where form_id=? order by id";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1,flowProcess.getFormId());
			rs=stmt.executeQuery();
			
			String writerField=flowProcess.getWriterField();
			
			Map<String, Integer> itemId=new HashMap<String, Integer>();
			int itemIndex=0;
			while (rs.next()) {
	//			"id":"表单控件id ",
	//			"title":"控件标题",
	//			"xtype":"控件类型",//见备注1
	//			"model":,//见备注1
	//			“data”:,// 表单控件数据，见备注1
	//			“writable”:0|1//不可写|可写
				
				String title=rs.getString("title");
				String xtype=rs.getString("xtype");
				String fieldName=rs.getString("field_name");
				String model=rs.getString("model");
				int writable=(","+writerField+",").indexOf(","+fieldName+",")>-1?1:0;
				String xfetchType=null;
				if (xtype.equals("xfetch")) {
					xfetchType=JSONObject.fromObject(model).getString("type");
				}
				
				if (xtype.equals("xlist")) {
					continue;
				}
				
				JSONObject item=new JSONObject();
				if (xtype.equals("xinput")) {
					JSONObject modelDefined=new JSONObject();
					modelDefined.put("style", "");
					
					item.put("id", fieldName);
					item.put("title", title);
					item.put("xtype", xtype);
					item.put("model", modelDefined);
					item.put("writable", writable);
				}else if(xtype.equals("xtextarea")){
					JSONObject modelDefined=new JSONObject();
					modelDefined.put("style", "");
					
					item.put("id", fieldName);
					item.put("title", title);
					item.put("xtype", xtype);
					item.put("model", modelDefined);
					item.put("writable", writable);
					
				}else if(xtype.equals("xtextuedit")){
					JSONObject modelDefined=new JSONObject();
					modelDefined.put("style", "");
					
					item.put("id", fieldName);
					item.put("title", title);
					item.put("xtype", xtype);
					item.put("model", modelDefined);
					item.put("writable", writable);
					
				}else if(xtype.equals("xselect")){
					JSONObject modelDefined=new JSONObject();
					JSONArray options=new JSONArray();
					if (model!=null&&!model.equals("")) {
						options=JSONArray.fromObject(model);
					}
					modelDefined.put("options", options);
					
					item.put("id", fieldName);
					item.put("title", title);
					item.put("xtype", xtype);
					item.put("model", modelDefined);
					item.put("writable", writable);
				}else if(xtype.equals("xradio")){
					JSONObject modelDefined=new JSONObject();
					JSONArray options=new JSONArray();
					if (model!=null&&!model.equals("")) {
						options=JSONArray.fromObject(model);
					}
					modelDefined.put("options", options);
					
					item.put("id", fieldName);
					item.put("title", title);
					item.put("xtype", xtype);
					item.put("model", modelDefined);
					item.put("writable", writable);
				}else if(xtype.equals("xcheckbox")){
					JSONObject modelDefined=new JSONObject();
					JSONArray options=new JSONArray();
					if (model!=null&&!model.equals("")) {
						options=JSONArray.fromObject(model);
					}
					modelDefined.put("options", options);
					
					item.put("id", fieldName);
					item.put("title", title);
					item.put("xtype", xtype);
					item.put("model", modelDefined);
					item.put("writable", writable);
				}else if(xtype.equals("xcalculate")){
					JSONObject modelDefined=new JSONObject();
					String module="";
					if (model!=null&&!model.equals("")) {
						module=JSONObject.fromObject(model).getString("module");
					}
					modelDefined.put("module", module);
					
					item.put("id", fieldName);
					item.put("title", title);
					item.put("xtype", xtype);
					item.put("model", modelDefined);
					item.put("writable", writable);
				}else if(xtype.equals("xfetch")&&xfetchType.equals("1")){
					JSONObject modelDefined=new JSONObject();
					modelDefined.put("style", "");
					modelDefined.put("format", JSONObject.fromObject(model).getString("format"));
					
					item.put("id", fieldName);
					item.put("title", title);
					item.put("xtype", "xfetch_date");
					item.put("model", modelDefined);
					item.put("writable", writable);
				}else if(xtype.equals("xfetch")&&xfetchType.equals("2")){
					JSONObject modelDefined=new JSONObject();
					modelDefined.put("style", "");
					modelDefined.put("format", JSONObject.fromObject(model).getString("format"));
					
					item.put("id", fieldName);
					item.put("title", title);
					item.put("xtype", "xfetch_time");
					item.put("model", modelDefined);
					item.put("writable", writable);
				}else if(xtype.equals("xfetch")&&xfetchType.equals("3")){
					JSONObject modelDefined=new JSONObject();
					modelDefined.put("style", "");
					
					item.put("id", fieldName);
					item.put("title", title);
					item.put("xtype", "xfetch_dept");
					item.put("model", modelDefined);
					item.put("writable", writable);
				}else if(xtype.equals("xfetch")&&xfetchType.equals("4")){
					JSONObject modelDefined=new JSONObject();
					modelDefined.put("style", "");
					
					item.put("id", fieldName);
					item.put("title", title);
					item.put("xtype", "xfetch_depts");
					item.put("model", modelDefined);
					item.put("writable", writable);
				}else if(xtype.equals("xfetch")&&xfetchType.equals("5")){
					JSONObject modelDefined=new JSONObject();
					modelDefined.put("style", "");
					
					item.put("id", fieldName);
					item.put("title", title);
					item.put("xtype", "xfetch_priv");
					item.put("model", modelDefined);
					item.put("writable", writable);
				}else if(xtype.equals("xfetch")&&xfetchType.equals("6")){
					JSONObject modelDefined=new JSONObject();
					modelDefined.put("style", "");
					
					item.put("id", fieldName);
					item.put("title", title);
					item.put("xtype", "xfetch_privs");
					item.put("model", modelDefined);
					item.put("writable", writable);
				}else if(xtype.equals("xfetch")&&xfetchType.equals("7")){
					JSONObject modelDefined=new JSONObject();
					modelDefined.put("style", "");
					
					item.put("id", fieldName);
					item.put("title", title);
					item.put("xtype", "xfetch_user");
					item.put("model", modelDefined);
					item.put("writable", writable);
				}else if(xtype.equals("xfetch")&&xfetchType.equals("8")){
					JSONObject modelDefined=new JSONObject();
					modelDefined.put("style", "");
					
					item.put("id", fieldName);
					item.put("title", title);
					item.put("xtype", "xfetch_users");
					item.put("model", modelDefined);
					item.put("writable", writable);
				}else if(xtype.equals("xmacro")){
					JSONObject modelDefined=new JSONObject();
					modelDefined.put("style", "");
					
					item.put("id", fieldName);
					item.put("title", title);
					item.put("xtype", xtype);
					item.put("model", modelDefined);
					item.put("writable", writable);
				}else if(xtype.equals("xdocnum")){
					JSONObject modelDefined=new JSONObject();
					modelDefined.put("style", "");
					
					item.put("id", fieldName);
					item.put("title", title);
					item.put("xtype", xtype);
					item.put("model", modelDefined);
					item.put("writable", writable);
				}else if(xtype.equals("xworkflow")){
					JSONObject modelDefined=new JSONObject();
					modelDefined.put("style", "");
					
					item.put("id", fieldName);
					item.put("title", title);
					item.put("xtype", xtype);
					item.put("model", modelDefined);
					item.put("writable", writable);
				}else if(xtype.equals("ximg")){
					JSONObject modelDefined=new JSONObject();
					modelDefined.put("style", "");
					
					item.put("id", fieldName);
					item.put("title", title);
					item.put("xtype", xtype);
					item.put("model", modelDefined);
					item.put("writable", writable);
				}else if(xtype.equals("xupload")){
					JSONObject modelDefined=new JSONObject();
					modelDefined.put("style", "");
					
					item.put("id", fieldName);
					item.put("title", title);
					item.put("xtype", xtype);
					item.put("model", modelDefined);
					item.put("writable", writable);
				}else if(xtype.equals("xuploads")){
					JSONObject modelDefined=new JSONObject();
					modelDefined.put("style", "");
					
					item.put("id", fieldName);
					item.put("title", title);
					item.put("xtype", xtype);
					item.put("model", modelDefined);
					item.put("writable", writable);
				}
				
				
				
				if(!itemId.containsKey(fieldName)){
					formItem.add(item);
					itemId.put(fieldName, itemIndex++);
				}
			}
			
			String formDataSql="select * from "+flowRunPrcs.getTableName()+" where run_id=?";
			formDataStmt=conn.prepareStatement(formDataSql);
			formDataStmt.setString(1, flowRunPrcs.getRunId());
			formDataRs=formDataStmt.executeQuery();
			
			Map<String, String> itemValue=new HashMap<String, String>();
			if (formDataRs.next()) {
				for(String fieldName:itemId.keySet()){
					String value=formDataRs.getString(fieldName);
					itemValue.put(fieldName, value);
				}
			}
			
			for(String fieldName:itemId.keySet()){
				String value=itemValue.get(fieldName);
				value=value==null?"":value;
				
				JSONObject item=formItem.getJSONObject(itemId.get(fieldName));
				String xtype=item.getString("xtype");
				JSONObject model=item.getJSONObject("model");
				
				if (Arrays.asList("xselect","xradio","xcheckbox").contains(xtype)) {
					item.put("data", JSONArray.fromObject(value.split(",")));
				}else if (Arrays.asList("xfetch_dept","xfetch_depts","xfetch_priv","xfetch_privs").contains(xtype)) {
					item.put("data", new JSONArray());
				}else if(Arrays.asList("xfetch_user","xfetch_users").contains(xtype)){
					item.put("data", new JSONArray());
				}else if(xtype.equals("xmacro")){
					String xmacroType=model.getString("type");
					String format=model.getString("format");
					format=format==null?"":format;
					
					if (Arrays.asList("1","2","3").contains(xmacroType)) {
						//时间、日期
						item.put(fieldName,new SimpleDateFormat(format).format(new Date()));
					} else if (xmacroType.equals("5")) {
						//流程名称
						item.put(fieldName,flowRunPrcs.getPrcsName());
					} else if (xmacroType.equals("6")) {
						// 流程编号
						item.put(fieldName,flowRunPrcs.getRunId());
					} else if (xmacroType.equals("7")) {
						// 流程发起人账号
						item.put(fieldName, new FlowRunPrcsLogic().flowBeginAccountId(conn,flowRunPrcs.getRunId()));
					} else if (xmacroType.equals("8")) {
						// 流程发起人姓名
						item.put(fieldName, new FlowRunPrcsLogic().flowBeginUserName(conn,flowRunPrcs.getRunId()));
					} else if (xmacroType.equals("9")) {
						// 流程发起人部门
						String accountId = new FlowRunPrcsLogic().flowBeginAccountId(conn,flowRunPrcs.getRunId());
						String deptId = new UserInfoLogic().getDeptId(conn,accountId);
						item.put(fieldName,new DeptLogic().getDeptNameShort(conn, deptId));
					} else if (xmacroType.equals("10")) {
						// 流程发起人角色
						item.put(fieldName, new UserPrivLogic().getUserPrivNameById(conn,account.getUserPriv()));
					} else if (xmacroType.equals("11")) {
						// 当前用户帐号
						item.put(fieldName,account.getAccountId());
					} else if (xmacroType.equals("12")) {
						// 当前用户姓名
						item.put(fieldName, new UserInfoLogic().getUserNameByAccountIdLogic(conn,account.getAccountId()));
					} else if (xmacroType.equals("13")) {
						// 当前用户部门
						String deptId = new UserInfoLogic().getDeptId(conn,account.getAccountId());
						item.put(fieldName,new DeptLogic().getDeptNameShort(conn, deptId));
					} else if (xmacroType.equals("14")) {
						// 当前用户长部门
						String deptId = new UserInfoLogic().getDeptId(conn,account.getAccountId());
						item.put(fieldName,new DeptLogic().getDeptNameLong(conn, deptId));
					} else if (xmacroType.equals("21")) {
						// 处理工作流文号
						String autoCode = createAutoCode(conn,flowRunPrcs.getFlowId(),flowRunPrcs.getRunId(), account);
						item.put(fieldName, autoCode);
					}
				}else if(xtype.equals("xworkflow")){
					item.put("data", new JSONArray());
				}else if(Arrays.asList("ximg","xupload","xuploads").contains(xtype)){
					item.put("data", new JSONArray());
				}else{
					item.put("data", value);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return formItem;
	}
	
	public JSONArray getWorkFlowDataLogicBack(Connection conn,
			FlowRunPrcs flowRunPrcs, FlowProcess flowProcess, Account account)
			throws SQLException {
		
		JSONArray returnjsonArr = new JSONArray();
		
		String formId = flowProcess.getFormId();
		ResultSet rs = null;
		String query = "";
		String xmacroFlag = "";
		String xdocnumFlag = "";
		JSONObject jsontmp = new JSONObject();
		String[] fieldName = null;
		
		FLowFormItemLogic flowFormItemLogic = new FLowFormItemLogic();
		Map<String, Map<String, String>> fieldMap = flowFormItemLogic
				.getAllFlowFormItemByFormIdLogic(conn, formId);
		
		String selectField = "";
		Set<Entry<String, Map<String, String>>> set = fieldMap.entrySet();
		Iterator<Entry<String, Map<String, String>>> it = set.iterator();
		while (it.hasNext()) {
			Map.Entry<String, Map<String, String>> entry1 = (Map.Entry<String, Map<String, String>>) it
					.next();
			selectField += entry1.getKey() + ",";
		}
		if (selectField.length() > 0) {
			selectField = selectField.substring(0, selectField.length() - 1);
		}

		// String
		// selectField=flowFormItemLogic.getAllFlowFormItemLogic(conn,flowRunPrcs.getFlowId(),flowRunPrcs.getPrcsId());
		if (!SysTool.isNullorEmpty(selectField)) {
			query = "SELECT " + selectField + " FROM "
					+ flowRunPrcs.getTableName() + " WHERE RUN_ID='"
					+ flowRunPrcs.getRunId() + "'";
			fieldName = selectField.split(",");
		} else {
			return null;
		}
		PreparedStatement ps = conn.prepareStatement(query);
		rs = ps.executeQuery();
		if (rs.next()) {
			for (int i = 0; i < fieldName.length; i++) {
				if (SysTool.isNullorEmpty(rs.getString(fieldName[i]))) {
					jsontmp.accumulate(fieldName[i], "");
				} else {
					jsontmp.accumulate(fieldName[i], rs.getString(fieldName[i]));
				}
			}
		}
		rs.close();
		ps.close();
		// 获取步骤会签意见

		// 获取当前可写宏控件列表
		FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
		String writerFieldStr = flowProcess.getWriterField();
		writerFieldStr = "," + writerFieldStr + ",";

		String hiddenfield = flowProcess.getHiddenField();
		// 工作流表单宏控件处理
		for (int j = 0; fieldName.length > j; j++) {
			JSONObject json = new JSONObject();
			String xtype = fieldMap.get(fieldName[j]).get("xtype");
			String model = fieldMap.get(fieldName[j]).get("model");
			json.accumulate("id", fieldName[j]);
			json.accumulate("title", fieldMap.get(fieldName[j]).get("title"));
			if (xtype.equals("xinput") || xtype.equals("xtextarea")
					|| xtype.equals("xtextuedit") || xtype.equals("xmacro")
					|| xtype.equals("xdocnum") || xtype.equals("xworkflow")
					|| xtype.equals("ximg") || xtype.equals("xupload")
					|| xtype.equals("xuploads")) {
				JSONObject md = new JSONObject();
				md.accumulate("style", "");
				model = md.toString();
				json.accumulate("xtype", xtype);
				json.accumulate("model", model);
			} else if (xtype.equals("xradio")) {
				JSONObject md = new JSONObject();
				JSONArray tmpmd = new JSONArray()
						.fromObject("[{\"value\":\"单选1\",\"desc\":\"单选1\"},{\"value\":\"单选2\",\"desc\":\"单选2\"}]");
				md.accumulate("options", tmpmd);
				model = md.toString();
				json.accumulate("xtype", xtype);
				json.accumulate("model", model);
			} else if (xtype.equals("xcheckbox")) {
				JSONObject md = new JSONObject();
				JSONArray tmpmd = new JSONArray()
						.fromObject("[{\"value\":\"复选1\",\"desc\":\"复选1\"},{\"value\":\"复选2\",\"desc\":\"复选2\"}]");
				md.accumulate("options", tmpmd);
				model = md.toString();
				json.accumulate("xtype", xtype);
				json.accumulate("model", model);

			} else if (xtype.equals("xselect")) {
				JSONObject md = new JSONObject();
				JSONArray tmpmd = new JSONArray().fromObject(model);
				md.accumulate("options", tmpmd);
				model = md.toString();
				json.accumulate("xtype", xtype);
				json.accumulate("model", model);
			} else if (xtype.equals("xfetch")) {
				JSONObject tmpjson = new JSONObject().fromObject(model);
				JSONObject v = new JSONObject();
				if (tmpjson.getString("type").equals("1")) {
					v.accumulate("style", "");
					v.accumulate("format", tmpjson.getString("format"));
					json.accumulate("xtype", "xfetch_date");
					json.accumulate("model", v);
				} else if (tmpjson.getString("type").equals("2")) {
					v.accumulate("style", "");
					v.accumulate("format", tmpjson.getString("format"));
					json.accumulate("xtype", "xfetch_time");
					json.accumulate("model", v);
				} else if (tmpjson.getString("type").equals("3")) {
					v.accumulate("style", "");
					json.accumulate("xtype", "xfetch_dept");
					json.accumulate("model", v);
				} else if (tmpjson.getString("type").equals("4")) {
					v.accumulate("style", "");
					json.accumulate("xtype", "xfetch_depts");
					json.accumulate("model", v);
				} else if (tmpjson.getString("type").equals("5")) {
					v.accumulate("style", "");
					json.accumulate("xtype", "xfetch_priv");
					json.accumulate("model", v);
				} else if (tmpjson.getString("type").equals("6")) {
					v.accumulate("style", "");
					json.accumulate("xtype", "xfetch_privs");
					json.accumulate("model", v);
				} else if (tmpjson.getString("type").equals("7")) {
					v.accumulate("style", "");
					json.accumulate("xtype", "xfetch_user");
					json.accumulate("model", v);
				} else if (tmpjson.getString("type").equals("8")) {
					v.accumulate("style", "");
					json.accumulate("xtype", "xfetch_users");
					json.accumulate("model", v);
				}
			} else if (xtype.equals("xcalculate")) {
				JSONObject md = new JSONObject();
				md.accumulate("module", model);
				json.accumulate("xtype", xtype);
				json.accumulate("model", md.toString());
			}

			if (!SysTool.isNullorEmpty(writerFieldStr)) {
				if (writerFieldStr.indexOf("," + fieldName[j] + ",") >= 0) {
					json.accumulate("writable", 1);
					// 处理工作流计数器
					xdocnumFlag = flowFormItemLogic.getXDocNumLogic(conn,
							fieldName[j], flowRunPrcs.getFlowId());
					if (!SysTool.isNullorEmpty(xdocnumFlag)) {
						UitilityTool uitilityTool = new UitilityTool();
						jsontmp.remove(fieldName[j]);
						String value = uitilityTool.createAutoNum(conn,
								flowRunPrcs.getFlowId());
						jsontmp.accumulate(fieldName[j], value);
					}
					// 宏控件处理
					xmacroFlag = flowFormItemLogic.getAutoFieldModelLogic(conn,
							fieldName[j], flowRunPrcs.getFlowId());
					if (!SysTool.isNullorEmpty(xmacroFlag)) {
						JSONObject xmacroJson = JSONObject
								.fromObject(xmacroFlag);
						if (xmacroJson.getString("type").equals("1")) {
							// 年份
							SimpleDateFormat df = new SimpleDateFormat(
									xmacroJson.getString("format"));// 设置日期格式
							jsontmp.remove(fieldName[j]);
							jsontmp.accumulate(fieldName[j],
									df.format(new Date()));
						} else if (xmacroJson.getString("type").equals("2")) {
							// /日期期
							SimpleDateFormat df = new SimpleDateFormat(
									xmacroJson.getString("format"));// 设置日期格式
							jsontmp.remove(fieldName[j]);
							jsontmp.accumulate(fieldName[j],
									df.format(new Date()));
						} else if (xmacroJson.getString("type").equals("3")) {
							// 时间
							SimpleDateFormat df = new SimpleDateFormat(
									xmacroJson.getString("format"));// 设置日期格式
							jsontmp.remove(fieldName[j]);
							jsontmp.accumulate(fieldName[j],
									df.format(new Date()));
						} else if (xmacroJson.getString("type").equals("5")) {
							// 流程名称
							jsontmp.remove(fieldName[j]);
							jsontmp.accumulate(fieldName[j],
									flowRunPrcs.getPrcsName());
						} else if (xmacroJson.getString("type").equals("6")) {
							// 流程编号
							jsontmp.remove(fieldName[j]);
							jsontmp.accumulate(fieldName[j],
									flowRunPrcs.getRunId());
						} else if (xmacroJson.getString("type").equals("7")) {
							// 流程发起人账号
							FlowRunPrcsLogic flowrunPrcsLogic = new FlowRunPrcsLogic();
							jsontmp.remove(fieldName[j]);
							jsontmp.accumulate(fieldName[j], flowrunPrcsLogic
									.flowBeginAccountId(conn,
											flowRunPrcs.getRunId()));
						} else if (xmacroJson.getString("type").equals("8")) {
							// 流程发起人姓名
							FlowRunPrcsLogic flowrunPrcsLogic = new FlowRunPrcsLogic();
							jsontmp.remove(fieldName[j]);
							jsontmp.accumulate(fieldName[j], flowrunPrcsLogic
									.flowBeginUserName(conn,
											flowRunPrcs.getRunId()));
						} else if (xmacroJson.getString("type").equals("9")) {
							// 流程发起人部门
							FlowRunPrcsLogic flowrunPrcsLogic = new FlowRunPrcsLogic();
							String accountId = flowrunPrcsLogic
									.flowBeginAccountId(conn,
											flowRunPrcs.getRunId());
							UserInfoLogic userInfoLogic = new UserInfoLogic();
							String deptId = userInfoLogic.getDeptId(conn,
									accountId);
							DeptLogic deptLogic = new DeptLogic();
							jsontmp.remove(fieldName[j]);
							jsontmp.accumulate(fieldName[j],
									deptLogic.getDeptNameShort(conn, deptId));
						} else if (xmacroJson.getString("type").equals("10")) {
							// 流程发起人角色
							UserPrivLogic userPrivLogic = new UserPrivLogic();
							jsontmp.remove(fieldName[j]);
							jsontmp.accumulate(fieldName[j], userPrivLogic
									.getUserPrivNameById(conn,
											account.getUserPriv()));
						} else if (xmacroJson.getString("type").equals("11")) {
							// 当前用户帐号
							jsontmp.remove(fieldName[j]);
							jsontmp.accumulate(fieldName[j],
									account.getAccountId());
						} else if (xmacroJson.getString("type").equals("12")) {
							// 当前用户姓名
							UserInfoLogic userInfoLogic = new UserInfoLogic();
							jsontmp.remove(fieldName[j]);
							jsontmp.accumulate(fieldName[j], userInfoLogic
									.getUserNameByAccountIdLogic(conn,
											account.getAccountId()));
						} else if (xmacroJson.getString("type").equals("13")) {
							// 当前用户部门
							UserInfoLogic userInfoLogic = new UserInfoLogic();
							String deptId = userInfoLogic.getDeptId(conn,
									account.getAccountId());
							DeptLogic deptLogic = new DeptLogic();
							jsontmp.remove(fieldName[j]);
							jsontmp.accumulate(fieldName[j],
									deptLogic.getDeptNameShort(conn, deptId));
						} else if (xmacroJson.getString("type").equals("14")) {
							// 当前用户长部门
							UserInfoLogic userInfoLogic = new UserInfoLogic();
							String deptId = userInfoLogic.getDeptId(conn,
									account.getAccountId());
							DeptLogic deptLogic = new DeptLogic();
							jsontmp.remove(fieldName[j]);
							jsontmp.accumulate(fieldName[j],
									deptLogic.getDeptNameLong(conn, deptId));
						} else if (xmacroJson.getString("type").equals("21")) {
							// 处理工作流文号
							jsontmp.remove(fieldName[j]);
							String value = createAutoCode(conn,
									flowRunPrcs.getFlowId(),
									flowRunPrcs.getRunId(), account);
							jsontmp.accumulate(fieldName[j], value);
						}
						if (xtype.equals("xselect")) {
							if (SysTool.isNullorEmpty(jsontmp
									.getString((fieldName[j])))) {
								json.accumulate("data", "[]");
							} else {
								json.accumulate("data",
										jsontmp.getString(fieldName[j]));
							}
						} else {
							json.accumulate("data",
									jsontmp.getString(fieldName[j]));
						}
					}
				} else {
					json.accumulate("writable", 0);
					if (xtype.equals("xselect")) {
						if (SysTool.isNullorEmpty(jsontmp
								.getString((fieldName[j])))) {
							json.accumulate("data", "[]");
						} else {
							json.accumulate("data", "");
						}
					}else{
						json.accumulate("data",
								jsontmp.getString(fieldName[j]));
					}

				}
			}
			if (!SysTool.isNullorEmpty(hiddenfield)) {
				if (hiddenfield.indexOf("," + fieldName[j] + ",") >= 0) {
					json.remove(fieldName[j]);
				}
			}
			returnjsonArr.add(json);
		}

		return returnjsonArr;
	}
	
	

	public String createAutoCode(Connection conn, String flowTypeId,
			String runId, Account account) throws SQLException {
		String returnData = "";
		WorkFlowLogic workFlowLogic = new WorkFlowLogic();
		WorkFlow workFlow = new WorkFlow();
		workFlow = workFlowLogic.getWorkFlowByFlowTypeIdLogic(conn, flowTypeId);
		UitilityTool uitilityTool = new UitilityTool();
		returnData = uitilityTool.createCode(conn, workFlow.getAutoCode(),
				flowTypeId, runId, account);
		return returnData;
	}
	
	public static void main(String[] args) {
		System.out.println(new HashMap<String, String>().get("a"));
	}
}
