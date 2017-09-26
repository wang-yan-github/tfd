package fixedasset.act;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.component.datamodel.Record;
import com.component.office.util.ExcelUtil;
import com.system.db.DbPoolConnection;
import com.system.tool.AutoConvertUtil;
import com.system.tool.GuId;

import fixedasset.data.FixedassetType;
import fixedasset.logic.FixedassetTypeLogic;

public class FixedassetTypeAct {
	private FixedassetTypeLogic logic=new FixedassetTypeLogic();
	private AutoConvertUtil autoConvertUtil=new AutoConvertUtil();
	public String typeTree(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			JSONArray typeTree=logic.typeTree(request.getParameterMap(), conn);
			writer=response.getWriter();
			writer.print(typeTree.toString());
			writer.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				if (conn!=null) {
					conn.close();
				}
			} catch (Exception e2) {}
			try {
				if (writer!=null) {
					writer.close();
				}
			} catch (Exception e2) {}
		}
		return null;
	}
	public String update(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		boolean result=false;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			
			FixedassetType type=(FixedassetType) autoConvertUtil.formToEntity(FixedassetType.class, request.getParameterMap());
			
			String updateType="add";
			
			String seqId=type.getSeqId();
			if (seqId==null||seqId.trim().equals("")) {
				seqId=GuId.getGuid();
			}else{
				updateType="update";
			}
			
			String levelId=type.getLevelId()+"."+seqId;
			
			type.setSeqId(seqId);
			type.setLevelId(levelId);
			
			if (updateType.equals("add")) {
				logic.add(type, conn);
			}else if(updateType.equals("update")){
				logic.update(type, conn);
			}
			
			conn.commit();
			result=true;
		} catch (Exception e) {
			// TODO: handle exception
			try{
				conn.rollback();
			}catch(Exception e2){}
			e.printStackTrace();
		}finally{
			try {
				writer=response.getWriter();
				writer.print(result);
				writer.flush();
			} catch (Exception e2) {}
			
			try {
				if (conn!=null) {
					conn.close();
				}
			} catch (Exception e2) {}
			
			try {
				if (writer!=null) {
					writer.close();
				}
			} catch (Exception e2) {}
		}
		return null;
	}
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		boolean result=false;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			
			String[] levelIds=request.getParameter("levelId").split(",");
			for (String levelId : levelIds) {
				logic.delete(levelId, conn);
			}
			
			conn.commit();
			result=true;
		} catch (Exception e) {
			// TODO: handle exception
			try{
				conn.rollback();
			}catch(Exception e2){}
			e.printStackTrace();
		}finally{
			try {
				writer=response.getWriter();
				writer.print(result);
				writer.flush();
			} catch (Exception e2) {}
			
			try {
				if (conn!=null) {
					conn.close();
				}
			} catch (Exception e2) {}
			
			try {
				if (writer!=null) {
					writer.close();
				}
			} catch (Exception e2) {}
		}
		return null;
	}
	public String templateDownload(HttpServletRequest request,HttpServletResponse response) throws Exception{
		PrintWriter writer=null;
		boolean result=false;
		OutputStream ops=null;
		InputStream is=null;
		try {
			
			Map<String,String> template=logic.template();
			
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
			
			
			String exportFileName="固定资产类别导入模板.xls";
			String fileName = URLEncoder.encode(exportFileName, "UTF-8");
			fileName = fileName.replaceAll("\\+", "%20");
			response.setHeader("Cache-control", "private");
			response.setHeader("Cache-Control","maxage=3600");
			response.setHeader("Pragma","public");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Accept-Ranges", "bytes");
			response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
			
			ops=response.getOutputStream();
			ExcelUtil.writeExcel(ops, records);
			
			ops.flush();
			
			result=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				writer=response.getWriter();
				writer.print(result);
				writer.flush();
			} catch (Exception e2) {}
			
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
			try {
				if (writer!=null) {
					writer.close();
				}
			} catch (Exception e2) {}
		}
		return null;
	}
	public String importBatch(HttpServletRequest request,HttpServletResponse response) throws Exception{
		InputStream is=null;
		boolean optionResult=false;
		//检查结果存放容器
		Map<String,List<String>> checkResult=null;
		Connection conn=null;
		try {
			DiskFileItemFactory disk = new DiskFileItemFactory();
			disk.setSizeThreshold(1024 * 5);//设置上传缓冲区容量为5k 
			String tempPath = request.getSession().getServletContext().getRealPath(File.separator)+"temp";
			File tempFile = new File(tempPath);  
			if(!tempFile.exists()) {  
				tempFile.mkdirs();  
			}  
			disk.setRepository(tempFile);
			String charSet="utf-8";
			ServletFileUpload sfu = new ServletFileUpload(disk);
			sfu.setHeaderEncoding(charSet);
			List items =sfu.parseRequest(request);
			
			if (items!=null&&items.size()>0) {
				for (Object object : items) {
					FileItem item = (FileItem)object;
					// 文件字段
					if (!item.isFormField()) {
						is=item.getInputStream();
						break;
					}
				}
			}
			
			
			
			//0.模板字段去空格，内容去空格
			//1.模板字段是否确实
			//2.字段内容是否合乎要求
			
			//0
			List<Record> records=logic.getImportRecords(is);
			//1.
			checkResult=logic.importRecordsCheck(records);
			
			boolean checkThrough=true;
			for(String checkType:checkResult.keySet()){
				if (checkResult.get(checkType).size()>0) {
					checkThrough=false;
					break;
				}
			}
			
			if (checkThrough) {
				conn = DbPoolConnection.getInstance().getConnection();
				logic.importRecords(records, conn);
				conn.commit();
			}
			
			optionResult=true;
		} catch (Exception e) {
			// TODO: handle exception
			optionResult=false;
			try{
				if (conn!=null) {
					conn.rollback();
				}
			}catch(Exception e2){
			}
			e.printStackTrace();
		}finally{
			
			JSONObject result=new JSONObject();
			result.accumulate("optionResult", optionResult);
			result.accumulate("checkResult", checkResult);
			request.setAttribute("result", result.toString());
			
			try {
				if (is!=null) {
					is.close();
				}
			} catch (Exception e2) {
			}
			try {
				if (conn!=null) {
					conn.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return "/fixedasset/type/import-index.jsp";
	}
	public String typeExport(HttpServletRequest request,HttpServletResponse response) throws Exception{
		PrintWriter writer=null;
		boolean result=false;
		OutputStream ops=null;
		InputStream is=null;
		Connection conn=null;
		try {
			conn = DbPoolConnection.getInstance().getConnection();
			
			List<Record> records=logic.getExportRecords(conn);
			
			String exportFileName="固定资产类别列表.xls";
			String fileName = URLEncoder.encode(exportFileName, "UTF-8");
			fileName = fileName.replaceAll("\\+", "%20");
			response.setHeader("Cache-control", "private");
			response.setHeader("Cache-Control","maxage=3600");
			response.setHeader("Pragma","public");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Accept-Ranges", "bytes");
			response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
			
			ops=response.getOutputStream();
			ExcelUtil.writeExcel(ops, records);
			
			ops.flush();
			
			result=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				writer=response.getWriter();
				writer.print(result);
				writer.flush();
			} catch (Exception e2) {}
			
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
			try {
				if (writer!=null) {
					writer.close();
				}
			} catch (Exception e2) {}
			try {
				if (conn!=null) {
					conn.close();
				}
			} catch (Exception e2) {
			}
		}
		return null;
	}
}
