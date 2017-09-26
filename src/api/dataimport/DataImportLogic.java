package api.dataimport;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.component.datamodel.Record;
import com.component.office.util.ExcelUtil;
import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.SysTool;

public class DataImportLogic {
	public BaseDao dao=new BaseDaoImpl();
	
	//模板字段
	public String FIELD_NAME="姓名";
	public String FIELD_AGE="年龄";
	
	//用于存放数据检查、导入的结果，和前端js中FIELD_CHECK_RESULT保持一致
	//用 “[备注]” 是为了防止和模板字段重名
	public String FIELD_CHECK_RESULT="[备注]";
	
	//模板
	public Map<String,String> template(){
		Map<String,String> template=new LinkedHashMap<String, String>();
		template.put("姓名", "不可为空");
		template.put("年龄", "不可为空");
		return template;
	}
	
	
	
	
	public InputStream getFormInputStream(HttpServletRequest request) throws Exception{
		InputStream is=null;
		
		DiskFileItemFactory disk = new DiskFileItemFactory();
		disk.setSizeThreshold(1024 * 5);//设置上传缓冲区容量为5k 
		
		String tempPath = request.getSession().getServletContext().getRealPath(File.separator)+"temp";
		File tempFile = new File(tempPath);  
		if(!tempFile.exists()) {  
			tempFile.mkdirs();  
		}  
		disk.setRepository(tempFile);
		
		ServletFileUpload sfu = new ServletFileUpload(disk);
		sfu.setHeaderEncoding("utf-8");
		
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
		
		return is;
	}
	
	public List<Record> getImportRecords(InputStream is) throws Exception{
		List<Record> importRecordsTemp=null;
		Map<String,List<Record>> recordsMap=ExcelUtil.readExcel(is, true);
		for(String sheet:recordsMap.keySet()){
			importRecordsTemp=recordsMap.get(sheet);
			break;
		}
		//重新整理导入的数据，列名去空格
		List<Record> importRecords=new ArrayList<Record>();
		for (Record recordTemp:importRecordsTemp) {
			Record record=new Record();
			for (int j = 0; j < recordTemp.getFieldCnt(); j++) {
				record.addField(recordTemp.getNameByIndex(j).trim(), recordTemp.getValueByIndex(j));
			}
			importRecords.add(record);
		}
		return importRecords;
	}
	
	public boolean fieldLackCheck(List<Record> importRecords,JSONArray resultData){
		JSONObject result=new JSONObject();
		JSONArray checkResult=new JSONArray();
		
		Map<String,String> template=template();
		Record columnNameRecord=importRecords.get(0);
		List<String> lackField=new ArrayList<String>();
		for(String fieldName:template.keySet()){
			boolean check=columnNameRecord.nameMatch(fieldName);
			if (!check) {
				lackField.add(fieldName);
			}
		}
		
		if (lackField.size()>0) {
			checkResult.add("导入的数据中缺失以下字段："+SysTool.join(lackField.toArray(), " , "));
		}
		
		result.put(FIELD_CHECK_RESULT, checkResult);
		resultData.add(result);
		
		return checkResult.size()==0;
	}
	
	public void fieldCheck(List<Record> importRecords,JSONArray resultData,Connection conn){
		JSONObject result=new JSONObject();
		JSONArray checkResult=new JSONArray();
		
		for (int i = 0; i < importRecords.size(); i++) {
			Record record=importRecords.get(i);
			
			result=new JSONObject();
			checkResult=new JSONArray();
			
			String name=record.getValueByName(FIELD_NAME).toString();
			String age=record.getValueByName(FIELD_AGE).toString();
			
			//检查名称
			if (name.trim().equals("")) {
				checkResult.add("请填写名称！");
			}
			//检查年龄
			if (age.trim().equals("")) {
				checkResult.add("请填写年龄！");
			}else{
				int intAge=0;
				try {
					intAge=Integer.parseInt(age);
				} catch (Exception e) {
				}finally{
					if (intAge<=0) {
						checkResult.add("请填写正确的年龄！");
					}
				}
			}
			
			
			result.put(FIELD_NAME,name);
			result.put(FIELD_AGE,age);
			result.put(FIELD_CHECK_RESULT, checkResult);
			
			resultData.add(result);
		}
	}
	
	public boolean dataInsert(HttpServletRequest request,List<Record> importRecords,Connection conn) throws Exception{
		boolean insertResult=true;
		
		PreparedStatement stmt=null;
		try {
			String sql="insert into api_student(name,age) values (?,?)";
			for (int i = 0; i < importRecords.size(); i++) {
				Record record=importRecords.get(i);
				
				String name=record.getValueByName(FIELD_NAME).toString();
				String age=record.getValueByName(FIELD_AGE).toString();
				
				stmt=conn.prepareStatement(sql);
				stmt.setString(1, name);
				stmt.setInt(2, Integer.parseInt(age));
				int executeResult=stmt.executeUpdate();
				if (executeResult<1) {
					insertResult=false;
					break;
				}
			}
		} catch (Exception e) {
			dao.rollback(conn);
			throw e;
		}finally{
			dao.close(null, stmt, conn);
		}
		
		return insertResult;
	}
}
