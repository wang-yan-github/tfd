package tfd.system.unit.dept.logic;

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

import tfd.system.unit.account.data.Account;
import tfd.system.unit.dept.data.Department;

import com.component.datamodel.Record;
import com.component.office.util.ExcelUtil;
import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.GuId;
import com.system.tool.RegexUtil;
import com.system.tool.SysTool;

public class DeptImportLogic {

	public BaseDao dao=new BaseDaoImpl();
	public DeptLogic logic=new DeptLogic();

	public Account account=null;
	
	
	public String FIELD_DEPT_NAME="部门名称";
	public String FIELD_DEPT_PARENT="父级部门";
	public String FIELD_DEPT_TEL="部门电话";
	public String FIELD_DEPT_EMAIL="部门邮箱";
	public String FIELD_DEPT_FAX="部门传真";
	
	public String FIELD_CHECK_RESULT="[备注]";
	
	public Map<String,String> template(){
		Map<String,String> template=new LinkedHashMap<String, String>();
		template.put("部门名称", "不可为空");
		template.put("父级部门", "不可为空，有必须格式如：一级部门/一级子部门，用“/”描述具体的部门信息以防名称重复");
		template.put("部门电话", "");
		template.put("部门邮箱", "");
		template.put("部门传真", "");
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
	public void fieldCheck(List<Record> importRecords,JSONArray resultData,Connection conn) throws Exception{
		JSONObject result=new JSONObject();
		JSONArray checkResult=new JSONArray();
		
		for (int i = 0; i < importRecords.size(); i++) {
			Record record=importRecords.get(i);
			
			result=new JSONObject();
			checkResult=new JSONArray();
			
			String deptName=record.getValueByName(FIELD_DEPT_NAME).toString().trim();
			String deptParent=record.getValueByName(FIELD_DEPT_PARENT).toString().trim();
			String deptEmail=record.getValueByName(FIELD_DEPT_EMAIL).toString().trim();
			String deptFax=record.getValueByName(FIELD_DEPT_FAX).toString().trim();
			String deptTel=record.getValueByName(FIELD_DEPT_TEL).toString().trim();
			
			//部门名称
			if (deptName.equals("")) {
				checkResult.add("请填写部门名称！");
			}
			
			//父级部门
			String orgLevelId="0";
			boolean deptParentCheck=true;
			boolean deptParentDataInCheck=false;
			if (!deptParent.equals("")) {
				for (int j = 0; j < importRecords.size(); j++) {
					String deptNameTemp=importRecords.get(j).getValueByName(FIELD_DEPT_NAME).toString().trim();
					String deptParentTemp=importRecords.get(j).getValueByName(FIELD_DEPT_PARENT).toString().trim();
					String deptLongNameTemp=deptParentTemp.equals("")?deptNameTemp:deptParentTemp+"/"+deptNameTemp;
					if (deptParent.equals(deptLongNameTemp)) {
						deptParentDataInCheck=true;
						break;
					}
				}
				
				if (!deptParentDataInCheck) {
					String[] deptParents=deptParent.split("/");
					for (int j = 0; j < deptParents.length; j++) {
						String deptId=logic.getDeptIdByDeptNameAndOrgLevelId(deptParents[j], orgLevelId,account.getOrgId(), conn);
						if (deptId==null) {
							deptParentCheck=false;
							break;
						}else{
							orgLevelId=deptId;
						}
					}
				}
				if (!deptParentCheck) {
					checkResult.add("请填写正确的父级部门！");
				}
			}
			
			//系统和导入的数据中， 父级部门下该部门名称是否重复
			if (!deptName.equals("")) {
				if (deptParentCheck) {
					String deptId=logic.getDeptIdByDeptNameAndOrgLevelId(deptName, orgLevelId,account.getOrgId(), conn);
					if (deptId!=null) {
						checkResult.add("系统中该部门已存在！");
					}
					for (int j = 0; j < importRecords.size(); j++) {
						if (j==i) {
							continue;
						}
						
						String deptNameTemp=importRecords.get(j).getValueByName(FIELD_DEPT_NAME).toString().trim();
						String deptParentTemp=importRecords.get(j).getValueByName(FIELD_DEPT_PARENT).toString().trim();
						
						if ((deptParent+deptName).equals(deptParentTemp+deptNameTemp)) {
							checkResult.add("导入的数据中该部门已存在！");
							break;
						}
					}
				}
			}
			
			//部门电话
			if (!deptTel.equals("")) {
				boolean deptTelCheck = RegexUtil.getInstance(RegexUtil.REGEX_TELEPHONE).test(deptTel);
				if (!deptTelCheck) {
					checkResult.add("请填写正确部门电话！");
				}
			}
			
			//部门邮箱
			if (!deptEmail.equals("")) {
				boolean deptEmailCheck = RegexUtil.getInstance(RegexUtil.REGEX_EMAIL).test(deptEmail);
				if (!deptEmailCheck) {
					checkResult.add("请填写正确部门邮箱！");
				}
			}
			
			//部门传真
			if (!deptFax.equals("")) {
				boolean deptFaxCheck = RegexUtil.getInstance(RegexUtil.REGEX_TELEPHONE).test(deptFax);
				if (!deptFaxCheck) {
					checkResult.add("请填写正确部门传真！");
				}
			}
			
			
			result.put(FIELD_DEPT_NAME, deptName);
			result.put(FIELD_DEPT_PARENT, deptParent);
			result.put(FIELD_DEPT_TEL, deptTel);
			result.put(FIELD_DEPT_EMAIL, deptEmail);
			result.put(FIELD_DEPT_FAX, deptFax);
			result.put(FIELD_CHECK_RESULT, checkResult);
			resultData.add(result);
		}
	}
	
	public boolean dataInsert(HttpServletRequest request,List<Record> importRecords,Connection conn) throws Exception{
		boolean insertResult=true;
		
		Account account=(Account) request.getSession().getAttribute("ACCOUNT_ID");
		
		//记录已插入数据的索引
		List<Integer> addedDept=new ArrayList<Integer>();
		List<Department> root=findDeptRoot(importRecords,addedDept);
		
		for(;;){
			
			List<Department> newRoot=new ArrayList<Department>();
			for (int i = 0; i < root.size(); i++) {
				Department rootDept=root.get(i);
				
				//找寻rootDept子部门
				for (int j = 0; j < importRecords.size(); j++) {
					//已插入数据跳过
					if (addedDept.contains(j)) {
						continue;
					}
					
					Record record=importRecords.get(j);
					String deptParent=record.getValueByName(FIELD_DEPT_PARENT).toString().trim();
					
					if (deptParent.equals(rootDept.getDeptLongName())) {
						//数据入库
						Department insertDept= insertRecord(record, account, conn);
						if (insertDept==null) {
							insertResult=false;
							break;
						}else{
							addedDept.add(j);
							newRoot.add(insertDept);
						}
					}
				}
				
				if (!insertResult) {
					break;
				}
			}
			
			if (!insertResult) {
				break;
			}
			
			root=new ArrayList<Department>(newRoot);
			
			if (root.size()==0) {
				root=findDeptRoot(importRecords, addedDept);
				if (root.size()==0) {
					break;
				}
			}
		}
		
		return insertResult;
	}
	public List<Department> findDeptRoot(List<Record> importRecords,List<Integer> addedDept){
		//从数据中找到 top parent
		List<Department> root=new ArrayList<Department>();
		Department rootDept=null;
		
		int level=99999;
		for (int i = 0; i < importRecords.size(); i++) {
			//已插入数据跳过
			if (addedDept.contains(i)) {
				continue;
			}
			
			Record record=importRecords.get(i);
			String deptParent=record.getValueByName(FIELD_DEPT_PARENT).toString().trim();
			
			if (deptParent.equals("")) {
				level=0;
				break;
			}else{
				int levelTemp=deptParent.split("/").length;
				if (levelTemp<level) {
					level=levelTemp;
					//已最小直接跳出
					if (level==1) {
						break;
					}
				}
			}
		}
		
		if (level==0) {
			rootDept=new Department();
			rootDept.setDeptLongName("");
			root.add(rootDept);
		}else{
			for (int i = 0; i < importRecords.size(); i++) {
				//已插入数据跳过
				if (addedDept.contains(i)) {
					continue;
				}
				
				Record record=importRecords.get(i);
				String deptParent=record.getValueByName(FIELD_DEPT_PARENT).toString().trim();
				
				int levelTemp=deptParent.split("/").length;
				if (levelTemp==level) {
					rootDept=new Department();
					rootDept.setDeptLongName(deptParent);
					root.add(rootDept);
				}
			}
		}
		
		return root;
	}
	public Department insertRecord(Record record,Account account,Connection conn) throws Exception{
		PreparedStatement stmt=null;
		try {
			
			String deptId=GuId.getGuid();
			String deptName=record.getValueByName(FIELD_DEPT_NAME).toString().trim();
			String deptParent=record.getValueByName(FIELD_DEPT_PARENT).toString().trim();
			String deptEmail=record.getValueByName(FIELD_DEPT_EMAIL).toString().trim();
			String deptFax=record.getValueByName(FIELD_DEPT_FAX).toString().trim();
			String deptTel=record.getValueByName(FIELD_DEPT_TEL).toString().trim();
			
			String orgLevelId="0";
			String parentDeptLangId="0";
			if (!deptParent.equals("")) {
				String[] deptParents=deptParent.split("/");
				for (int j = 0; j < deptParents.length; j++) {
					orgLevelId=logic.getDeptIdByDeptNameAndOrgLevelId(deptParents[j], orgLevelId,account.getOrgId(), conn);
					parentDeptLangId+="/"+orgLevelId;
				}
			}
			
			String deptLongId=parentDeptLangId+"/"+deptId;
			String deptLongName=deptParent.equals("")?deptName:deptParent+"/"+deptName;
			String orgId=account.getOrgId();
			
			Department dept=new Department();
			dept.setDeptLongName(deptLongName);
			
			
			String sql="insert department (dept_id,dept_name,org_leave_id,dept_long_id,dept_long_name,dept_tel,dept_email,dept_fax,org_id) "
					+ "				values (?,?,?,?,?,?,?,?,?)";
			
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, deptId);
			stmt.setString(2, deptName);
			stmt.setString(3, orgLevelId);
			stmt.setString(4, deptLongId);
			stmt.setString(5, deptLongName);
			stmt.setString(6, deptTel);
			stmt.setString(7, deptEmail);
			stmt.setString(8, deptFax);
			stmt.setString(9, orgId);
			
			if (stmt.executeUpdate()>0) {
				return dept;
			}else{
				return null;
			}
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(null, stmt, null);
		}
	}
}
