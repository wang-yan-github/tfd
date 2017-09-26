package tfd.system.unit.dept.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import tfd.system.unit.account.data.Account;
import tfd.system.unit.dept.data.Department;
import tfd.system.unit.userinfo.logic.UserInfoLogic;

import com.component.datamodel.Record;
import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;

public class DeptExportLogic {
	
	public BaseDao dao=new BaseDaoImpl();
	
	public String FIELD_DEPT_NAME="部门名称";
	public String FIELD_DEPT_PARENT="父级部门";
	public String FIELD_DEPT_TEL="部门电话";
	public String FIELD_DEPT_EMAIL="部门邮箱";
	public String FIELD_DEPT_FAX="部门传真";
	public String FIELD_DEPT_LEADER="部门领导";
	public String FIELD_DEPT_FUNCTION="部门职能";
	
	
	public List<Record> getExportRecords(Account account,Connection conn) throws Exception{
		List<Record> records=new ArrayList<Record>();
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		UserInfoLogic userInfoLogic=new UserInfoLogic();
		try {
			
			//从无到有的“无”
			List<Department> root=new ArrayList<Department>();
			Department rootDept=new Department();
			rootDept.setDeptId("0");
			root.add(rootDept);
			
			//记录record是 deptId，且和records子集顺序一致
			List<String> recordDeptId=new ArrayList<String>();
			
			for(;;){
				List<Department> newRoot=new ArrayList<Department>();
				
				for(Department rootDeptTemp:root){
					
					String deptParent=rootDeptTemp.getDeptId();
					
					String sql="select dept_id,org_leave_id,dept_long_name,dept_tel,"
							+ "		dept_email,dept_fax,dept_lead,dept_function from department where org_id=? and org_leave_id=?";
					
					stmt=conn.prepareStatement(sql);
					stmt.setString(1, account.getOrgId());
					stmt.setString(2, deptParent);
					rs=stmt.executeQuery();
					
					while(rs.next()){
						String deptId=rs.getString("dept_id");
						String orgLevelId=rs.getString("org_leave_id");
						String deptLongName=rs.getString("dept_long_name");
						String deptTel=rs.getString("dept_tel")==null?"":rs.getString("dept_tel");
						String deptEmail=rs.getString("dept_email")==null?"":rs.getString("dept_email");
						String deptFax=rs.getString("dept_fax")==null?"":rs.getString("dept_fax");
						String deptLeader=rs.getString("dept_lead")==null?"":rs.getString("dept_lead");
						String deptFunction=rs.getString("dept_function")==null?"":rs.getString("dept_function");
						
						String deptLeaderName="";
						if (!deptLeader.equals("")) {
							deptLeaderName=userInfoLogic.getUserNameByAccountIdLogic(conn, deptLeader);
						}
						
						Record record=new Record();
						record.addField(FIELD_DEPT_NAME, deptLongName);
						record.addField(FIELD_DEPT_TEL, deptTel);
						record.addField(FIELD_DEPT_EMAIL, deptEmail);
						record.addField(FIELD_DEPT_FAX, deptFax);
						record.addField(FIELD_DEPT_LEADER, deptLeaderName);
						record.addField(FIELD_DEPT_FUNCTION, deptFunction);
						
						//找到部门父级的索引
						Integer insertIndex=null;
						for (int i = 0; i < recordDeptId.size(); i++) {
							if (orgLevelId.equals(recordDeptId.get(i))) {
								insertIndex=i;
								break;
							}
						}
						//追随父级
						if (insertIndex==null) {
							records.add(record);
							recordDeptId.add(deptId);
						}else{
							records.add(insertIndex+1, record);
							recordDeptId.add(insertIndex+1,deptId);
						}
						
						//新的枝桠诞生
						Department deptTemp=new Department();
						deptTemp.setDeptId(deptId);
						newRoot.add(deptTemp);
					}
				}
				
				//将新枝为根
				root=new ArrayList<Department>(newRoot);
				
				if (root.size()==0) {
					break;
				}
			}
			
			//添加列名
			Record record=new Record();
			record.addField(FIELD_DEPT_NAME, FIELD_DEPT_NAME);
			record.addField(FIELD_DEPT_TEL, FIELD_DEPT_TEL);
			record.addField(FIELD_DEPT_EMAIL, FIELD_DEPT_EMAIL);
			record.addField(FIELD_DEPT_FAX, FIELD_DEPT_FAX);
			record.addField(FIELD_DEPT_LEADER, FIELD_DEPT_LEADER);
			record.addField(FIELD_DEPT_FUNCTION, FIELD_DEPT_FUNCTION);
			records.add(0,record);
				
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return records;
	}
}
