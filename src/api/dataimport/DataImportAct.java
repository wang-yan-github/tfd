package api.dataimport;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.component.datamodel.Record;
import com.component.office.util.ExcelUtil;
import com.system.db.DbPoolConnection;
import com.system.tool.HttpUtil;
import com.system.tool.SysTool;

public class DataImportAct {
	private DataImportLogic logic=new DataImportLogic();
	/**
	 * 数据导入 模板下载
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String templateDownload(HttpServletRequest request,HttpServletResponse response) throws Exception{
		OutputStream ops=null;
		InputStream is=null;
		try {
			//模板内容
			Map<String,String> template=logic.template();
			
			//将模板内容转化成两行的数据
			List<Record> records=new ArrayList<Record>();
			Record record=new Record();
			for(String column:template.keySet()){
				record.addField(column,column);
			}
			records.add(record);
			
			record=new Record();
			for(String column:template.keySet()){
				record.addField(column,template.get(column));
			}
			records.add(record);
			
			
			String exportFileName="学生信息导入模板.xls";
			
			String ContentDisposition=HttpUtil.getReponseHeader_ContentDisposition(exportFileName, request.getHeader("user-agent"));

			response.setHeader("Cache-control", "private");
			response.setHeader("Cache-Control", "maxage=3600");
			response.setHeader("Pragma", "public");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Accept-Ranges", "bytes");
			response.setHeader("Content-disposition",ContentDisposition);
			
			ops=response.getOutputStream();
			ExcelUtil.writeExcel(ops, records);
			ops.flush();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				if (is!=null) {
					is.close();
				}
			} catch (Exception e2) {}
			try {
				if (ops!=null) {
					ops.close();
				}
			} catch (Exception e2) {}
		}
		return null;
	}
	/**
	 * 数据导入
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String dataImport(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//最终反馈的结果
		JSONArray resultData=new JSONArray();
		InputStream is=null;
		Connection conn=null;
		try {
			conn=DbPoolConnection.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			//获取表单文件流
			is=logic.getFormInputStream(request);
			
			//获取数据
			List<Record> importRecords = logic.getImportRecords(is);
			
			//空数据提示
			boolean nullCheck=importRecords.size()>0;
			if (!nullCheck) {
				JSONObject result=new JSONObject();
				JSONArray checkResult=new JSONArray();
				checkResult.add("请填写导入数据！");
				result.put(logic.FIELD_CHECK_RESULT, checkResult);
				resultData.add(result);
			}
			
			//检查列名是否全乎
			boolean fieldLackCheck=false;
			if (nullCheck) {
				fieldLackCheck=logic.fieldLackCheck(importRecords, resultData);
			}
			
			//检查数据是否正确
			if (fieldLackCheck) {
				logic.fieldCheck(importRecords, resultData,conn);
			}
			
			//判断检查是否通过
			boolean checkBy=true;
			for (int i = 0; i < resultData.size(); i++) {
				checkBy=resultData.getJSONObject(i).getJSONArray(logic.FIELD_CHECK_RESULT).size()==0;
				if (!checkBy) {
					checkBy=false;
					break;
				}
			}
			
			//数据入库
			boolean insertResult=false;
			if (checkBy) {
				try {
					insertResult=logic.dataInsert(request, importRecords,conn);
				} catch (Exception e) {
					throw e;
				}finally{
					request.setAttribute("insertResult", insertResult);
				}
			}
			
			request.setAttribute("resultData", resultData);
			
			if (insertResult) {
				conn.commit();
			}else{
				logic.dao.rollback(conn);
			}
		} catch (Exception e) {
			logic.dao.rollback(conn);
			throw e;
		}finally{
			SysTool.closeInputStream(is);
			logic.dao.close(null, null, conn);
		}
		return "/api/dataimport/index.jsp";
	}
	/**
	 * 数据导入要求
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String dataRequire(HttpServletRequest request,HttpServletResponse response) throws Exception{
		PrintWriter writer=null;
		try {
			writer=response.getWriter();
			writer.print(JSONObject.fromObject(logic.template()).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SysTool.closePrintWriter(writer);
		}
		return null;
	}
}
