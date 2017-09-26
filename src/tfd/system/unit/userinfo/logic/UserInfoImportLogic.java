package tfd.system.unit.userinfo.logic;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
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
import tfd.system.unit.account.logic.AccountLogic;
import tfd.system.unit.dept.logic.DeptLogic;
import tfd.system.unit.userinfo.data.UserInfo;
import tfd.system.unit.userleave.logic.UserLeaveLogic;
import tfd.system.unit.userpriv.logic.UserPrivLogic;

import com.component.datamodel.Record;
import com.component.office.util.ExcelUtil;
import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.GuId;
import com.system.tool.RegexUtil;
import com.system.tool.SysTool;

public class UserInfoImportLogic {
	public BaseDao dao=new BaseDaoImpl();
	AccountLogic accountLogic=new AccountLogic();
	DeptLogic deptLogic=new DeptLogic();
	UserPrivLogic userPrivLogic=new UserPrivLogic();
	UserLeaveLogic userLevelLogic=new UserLeaveLogic();
	UserInfoLogic userInfoLogic=new UserInfoLogic();
	
	public Account account=null;
	
	List<String> manageDeptMap=Arrays.asList("1","2");
	String MANAGE_DEPT_DEFAULT="1";
	
	//模板字段
	public String FIELD_ACCOUNT_ID="账号";
	public String FIELD_USER_NAME="姓名";
	public String FIELD_DEPT="所在部门";
	public String FIELD_DEPT_LEADER="部门领导";
	public String FIELD_USER_PRIV="角色权限";
	public String FIELD_USER_PRIV_OTHER="辅助角色";
	public String FIELD_MANAGE_DEPT="管理的部门"; 
	public String FIELD_ADMINISTRATIVE_LEVEL="行政级别";
	public String FIELD_WORK_NO="工号";
	public String FIELD_SEX="性别";
	public String FIELD_MOIBLE_PHONE="手机号码";
	public String FIELD_SORT="人员排序号";
	public String FIELD_HOME_ADDRESS="家庭住址";
	public String FIELD_HOME_TEL="住宅电话";
	public String FIELD_QQ="QQ号";
	public String FIELD_EMAIL="电子邮箱";
	
	//用于存放数据检查、导入的结果，和前端js中FIELD_CHECK_RESULT保持一致
	//用 “[备注]” 是为了防止和模板字段重名
	public String FIELD_CHECK_RESULT="[备注]";
	
	//模板
	public Map<String,String> template(){
		Map<String,String> template=new LinkedHashMap<String, String>();
		//账号 姓名  所在部门  部门领导  角色 辅助角色 
		//家庭住址 住宅电话 手机号码 qq号 电子邮箱 工号 行政级别 
		//性别 人员排序号
		//管理的部门 (1表示管理本部门，2表示管理全体,其他表示管理指定部门)
		
		template.put("账号", "不可为空");
		template.put("姓名", "不可为空");
		template.put("所在部门", "不可为空，部门名称（见菜单，系统管理/组织机构/部门管理），格式如下：一级部门/二级部门/...");
		template.put("部门领导", "不可为空（领导账户）");
		template.put("角色权限", "不可为空，角色名称（见菜单，系统管理/组织机构/权限组管理）");
		template.put("辅助角色", "角色名称（见系统权限组管理），多个请用英文逗号分隔");
		template.put("管理的部门", "1表示管理本部门，2表示管理全体部门，其他表示管理指定部门（格式和字段“所在部门”一致，多个请用英文逗号分隔 ），不填默认是1");
		template.put("行政级别", "不可为空，行政级别名称（见菜单，系统管理/组织机构/行政级别设置）");
		template.put("性别", "不可为空，可填（男|女）");
		template.put("人员排序号", "部门下人员顺序，按从大到小排序，默认0，必须是大于等于0的整数");
		template.put("工号", "");
		template.put("手机号码", "");
		template.put("家庭住址", "");
		template.put("住宅电话", "");
		template.put("QQ号", "");
		template.put("电子邮箱", "");
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
			
			//账号 姓名  所在部门  部门领导  角色 辅助角色 
			//家庭住址 住宅电话 手机号码 qq号 电子邮箱 工号 行政级别 
			//性别 人员排序号
			//管理的部门 (1表示管理本部门，2表示管理全体,其他表示管理指定部门)
			
			String accountId=record.getValueByName(FIELD_ACCOUNT_ID).toString().trim();
			String userName=record.getValueByName(FIELD_USER_NAME).toString().trim();
			String dept=record.getValueByName(FIELD_DEPT).toString().trim();
			String deptLeader=record.getValueByName(FIELD_DEPT_LEADER).toString().trim();
			String userPriv=record.getValueByName(FIELD_USER_PRIV).toString().trim();
			String userPrivOther=record.getValueByName(FIELD_USER_PRIV_OTHER).toString().trim();
			String homeAddress=record.getValueByName(FIELD_HOME_ADDRESS).toString().trim();
			String homeTel=record.getValueByName(FIELD_HOME_TEL).toString().trim();
			String mobilePhone=record.getValueByName(FIELD_MOIBLE_PHONE).toString().trim();
			String qq=record.getValueByName(FIELD_QQ).toString().trim();
			String email=record.getValueByName(FIELD_EMAIL).toString().trim();
			String workNo=record.getValueByName(FIELD_WORK_NO).toString().trim();
			String adminLevel=record.getValueByName(FIELD_ADMINISTRATIVE_LEVEL).toString().trim();
			String sex=record.getValueByName(FIELD_SEX).toString().trim();
			String sort=record.getValueByName(FIELD_SORT).toString().trim();
			String manageDept=record.getValueByName(FIELD_MANAGE_DEPT).toString().trim();
			
			
			//账号
				//不可为空
			if (accountId.equals("")) {
				checkResult.add("账号不可为空！");
			}else{
				//导入的数据中不可重复
				for (int j = 0; j < importRecords.size(); j++) {
					if (j==i) {
						continue;
					}
					Record recordTemp=importRecords.get(j);
					String accountTemp=recordTemp.getValueByName(FIELD_ACCOUNT_ID).toString().trim();
					if (accountId.equals(accountTemp)) {
						checkResult.add("与"+(j+1)+"行的账号重名！");
					}
				}
				//不可重名于系统中的账号
				boolean accountCheck=!accountLogic.checkUserByAccountIdLogic(conn, accountId);
				if (!accountCheck) {
					checkResult.add("该账号系统中已存在！");
				}
			}
			
			
			
			//姓名
			if (userName.equals("")) {
				checkResult.add("姓名不可为空！");
			}
			
			
			//所在部门
				//不可为空
			if (dept.equals("")) {
				checkResult.add("所在部门不可为空！");
			}else{
				//长名称是否存在
				String deptId=deptLogic.getDeptIdByDeptLongName(dept, account.getOrgId(), conn);
				if (deptId==null) {
					checkResult.add("所在部门填写不正确！");
				}
			}
			
			
			//部门领导
				//不可为空
			if (deptLeader.equals("")) {
				checkResult.add("部门领导不可为空！");
			}else{
				//账号在系统中是否存在
				boolean deptLeaderCheck=accountLogic.checkUserByAccountIdLogic(conn, deptLeader);
				//账号在导入的数据中是否存在
				if (!deptLeaderCheck) {
					boolean deptLeaderDataCheck=false;
					for (int j = 0; j < importRecords.size(); j++) {
						Record recordTemp=importRecords.get(j);
						String accountIdTemp=recordTemp.getValueByName(FIELD_ACCOUNT_ID).toString().trim();
						if (deptLeader.equals(accountIdTemp)) {
							deptLeaderDataCheck=true;
							break;
						}
					}
					if (!deptLeaderDataCheck) {
						checkResult.add("部门领导账号不存在！");
					}
				}
			}
			
			
			//角色
				//不可为空
			if (userPriv.equals("")) {
				checkResult.add("角色不可为空！");
			}else{
				//角色名称是否存在
				String userPrivId=userPrivLogic.getUserPrivIdByUserPrivName(userPriv, account.getOrgId(), conn);
				if (userPrivId==null) {
					checkResult.add("角色名称不正确！");
				}
			}
			
			
			//辅助角色
			if (!userPrivOther.equals("")) {
				//角色名称是否存在
				String[] userPrivStr=userPrivOther.split(",");
				List<String> errorPriv=new ArrayList<String>();
				for (int j = 0; j < userPrivStr.length; j++) {
					String userPrivId=userPrivLogic.getUserPrivIdByUserPrivName(userPrivStr[j], account.getOrgId(), conn);
					if (userPrivId==null) {
						errorPriv.add(userPrivStr[j]);
					}
				}
				if (errorPriv.size()>0) {
					checkResult.add("以下辅助角色不存在："+SysTool.join(errorPriv.toArray(), ","));
				}
			}
			
			
			//管理的部门
			if (!manageDept.equals("")) {
				//是否为：1|2
				boolean manageDeptIsNum=manageDeptMap.contains(manageDept);
				//部门名称检查
				if (!manageDeptIsNum) {
					List<String> errorDept=new ArrayList<String>();
					
					String[] manageDepts=manageDept.split(",");
					for (int j = 0; j < manageDepts.length; j++) {
						String deptId=deptLogic.getDeptIdByDeptLongName(manageDepts[j], account.getOrgId(), conn);
						if (deptId==null) {
							errorDept.add(manageDepts[j]);
						}
					}
					
					if (errorDept.size()>0) {
						checkResult.add("以下管理的部门填写不正确：<br/>"+SysTool.join(errorDept.toArray(), "<br/>"));
					}
				}
			}
			
			
			//行政级别
				//不可为空
			if (adminLevel.equals("")) {
				checkResult.add("行政级别不可为空！");
			}else{
				//级别名称是否存在
				String levelId=userLevelLogic.getLevelIdByLevelName(adminLevel, account.getOrgId(), conn);
				if (levelId==null) {
					checkResult.add("请填写正确的行政级别名称！");
				}
			}

			
			//性别
				//不可为空
			if (sex.equals("")) {
				checkResult.add("性别不可为空！");
			}else{
				//填写是否正确
				List<String> sexMap=Arrays.asList("男","女");
				if (!sexMap.contains(sex)) {
					checkResult.add("请参照提示填写正确的性别！");
				}
			}
			
			
			//人员排序号
			if (!sort.equals("")) {
				boolean sortCheck=RegexUtil.getInstance(RegexUtil.REGEX_INT_0_).test(sort);
				if (!sortCheck) {
					checkResult.add("请填写正确人员排序号！");
				}
			}
			
			
			//手机号码
			if (!mobilePhone.equals("")) {
				boolean mobilePhoneCheck=RegexUtil.getInstance(RegexUtil.REGEX_MOBILE_PHONE).test(mobilePhone);
				if (!mobilePhoneCheck) {
					checkResult.add("请填写正确的手机号码！");
				}
			}
			
			
			//住宅电话
			if (!homeTel.equals("")) {
				boolean homeTelCheck=RegexUtil.getInstance(RegexUtil.REGEX_TELEPHONE).test(homeTel);
				if (!homeTelCheck) {
					checkResult.add("请填写正确的住宅电话！");
				}
			}
			
			
			//电子邮箱
			if (!email.equals("")) {
				boolean emailCheck=RegexUtil.getInstance(RegexUtil.REGEX_EMAIL).test(email);
				if (!emailCheck) {
					checkResult.add("请填写正确的电子邮箱！");
				}
			}
			
			
			
			result.put(FIELD_ACCOUNT_ID,accountId);
			result.put(FIELD_USER_NAME, userName);
			result.put(FIELD_DEPT, dept);
			result.put(FIELD_DEPT_LEADER,deptLeader);
			result.put(FIELD_USER_PRIV, userPriv);
			result.put(FIELD_USER_PRIV_OTHER,userPrivOther);
			result.put(FIELD_MANAGE_DEPT,manageDept);
			result.put(FIELD_ADMINISTRATIVE_LEVEL,adminLevel);
			result.put(FIELD_SEX, sex);
			result.put(FIELD_SORT,sort);
			result.put(FIELD_WORK_NO,workNo);
			result.put(FIELD_MOIBLE_PHONE, mobilePhone);
			result.put(FIELD_HOME_ADDRESS,homeAddress);
			result.put(FIELD_HOME_TEL,homeTel);
			result.put(FIELD_QQ,qq);
			result.put(FIELD_EMAIL,email);
			
			result.put(FIELD_CHECK_RESULT, checkResult);
			
			resultData.add(result);
		}
	}
	
	public boolean dataInsert(HttpServletRequest request,List<Record> importRecords,Connection conn) throws Exception{
		boolean insertResult=true;
		
		PreparedStatement stmt=null;
		try {
			for (int i = 0; i < importRecords.size(); i++) {
				Record record=importRecords.get(i);
				
				String accountId=record.getValueByName(FIELD_ACCOUNT_ID).toString().trim();
				String userName=record.getValueByName(FIELD_USER_NAME).toString().trim();
				String dept=record.getValueByName(FIELD_DEPT).toString().trim();
				String deptLeader=record.getValueByName(FIELD_DEPT_LEADER).toString().trim();
				String userPriv=record.getValueByName(FIELD_USER_PRIV).toString().trim();
				String userPrivOther=record.getValueByName(FIELD_USER_PRIV_OTHER).toString().trim();
				String homeAddress=record.getValueByName(FIELD_HOME_ADDRESS).toString().trim();
				String homeTel=record.getValueByName(FIELD_HOME_TEL).toString().trim();
				String mobilePhone=record.getValueByName(FIELD_MOIBLE_PHONE).toString().trim();
				String qq=record.getValueByName(FIELD_QQ).toString().trim();
				String email=record.getValueByName(FIELD_EMAIL).toString().trim();
				String workNo=record.getValueByName(FIELD_WORK_NO).toString().trim();
				String adminLevel=record.getValueByName(FIELD_ADMINISTRATIVE_LEVEL).toString().trim();
				String sex=record.getValueByName(FIELD_SEX).toString().trim();
				String sort=record.getValueByName(FIELD_SORT).toString().trim();
				String manageDept=record.getValueByName(FIELD_MANAGE_DEPT).toString().trim();
				
				
				String userId=GuId.getGuid();
				String deptId=deptLogic.getDeptIdByDeptLongName(dept, account.getOrgId(), conn);
				String otherPriv=userPrivLogic.getUserPrivIdStrByUserPrivNameStr(userPrivOther, account.getOrgId(), conn);
				String postPriv=userPrivLogic.getUserPrivIdByUserPrivName(userPriv, account.getOrgId(), conn);
				
				if (manageDept.equals("")) {
					manageDept=MANAGE_DEPT_DEFAULT;
				}else{
					if (!manageDeptMap.contains(manageDept)) {
						String[] manageDepts=manageDept.split(",");
						List<String> deptIds=new ArrayList<String>();
						for (int j = 0; j < manageDepts.length; j++) {
							String deptIdTemp=deptLogic.getDeptIdByDeptLongName(manageDepts[j], account.getOrgId(), conn);
							deptIds.add(deptIdTemp);
						}
						
						manageDept=SysTool.join(deptIds.toArray(), ",");
					}
				}
				
				String leadLeave=userLevelLogic.getLevelIdByLevelName(adminLevel, account.getOrgId(), conn);
				
				
				
				
				UserInfo userInfo=new UserInfo();
				userInfo.setAccountId(accountId);
				userInfo.setDeptId(deptId);
				userInfo.seteMail(email);
				userInfo.setHomeAdd(homeAddress);
				userInfo.setHomeTel(homeTel);
				userInfo.setLeadId(deptLeader);
				userInfo.setLeadLeave(leadLeave);
				userInfo.setManageDept(manageDept);
				userInfo.setMobileNo(mobilePhone);
				userInfo.setOrgId(account.getOrgId());
				userInfo.setOtherPriv(otherPriv);
				userInfo.setPostPriv(postPriv);
				userInfo.setQq(qq);
				userInfo.setSex(sex);
				userInfo.setSortId(sort);
				userInfo.setUserId(userId);
				userInfo.setUserName(userName);
				userInfo.setWorkId(workNo);
				
				int userInfoAddResult=userInfoLogic.addUserInfoLogic(conn, userInfo);
				
				if (userInfoAddResult>0) {
					String password=accountLogic.getImportInitPassword();
					String passwordType=accountLogic.getPasswordTypeDefault();
					String theme=accountLogic.getThemeDefault();
					String notLogin=accountLogic.getNotLoginDefault();
					String language=accountLogic.getLanguageDefault();
					
					Account account=new Account();
					account.setAccountId(accountId);
					account.setLanguage(language);
					account.setNotLogin(notLogin);
					account.setOrgId(this.account.getOrgId());
					account.setPassWord(password);
					account.setPasswordType(passwordType);
					account.setTheme(theme);
					account.setUserPriv(postPriv);
					
					int accountAddResult=accountLogic.addAccountLogic(conn, account);
					if (accountAddResult<1) {
						insertResult=false;
						break;
					}
				}else{
					insertResult=false;
					break;
				}
			}
		} catch (Exception e) {
			dao.rollback(conn);
			throw e;
		}finally{
			dao.close(null, stmt, null);
		}
		
		return insertResult;
	}
	
}
