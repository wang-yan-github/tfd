package tfd.system.unit.userinfo.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tfd.system.unit.account.data.Account;
import tfd.system.unit.dept.logic.DeptExportLogic;
import tfd.system.unit.dept.logic.DeptLogic;
import tfd.system.unit.userleave.logic.UserLeaveLogic;
import tfd.system.unit.userpriv.logic.UserPrivLogic;

import com.component.datamodel.Record;
import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;

public class UserInfoExportLogic {
	
	public BaseDao dao=new BaseDaoImpl();
	DeptLogic deptLogic=new DeptLogic();
	UserPrivLogic userPrivLogic=new UserPrivLogic();
	UserLeaveLogic userLevelLogic=new UserLeaveLogic();
	UserInfoLogic userInfoLogic=new UserInfoLogic();
	
	static Map<String,String> manageDeptMap=new HashMap<String, String>();
	static{
		manageDeptMap.put("1", "本部门");
		manageDeptMap.put("2", "全体部门");
	}
	
	public String FIELD_ACCOUNT_ID="账号";
	public String FIELD_USER_NAME="姓名";
	public String FIELD_DEPT_OTHER="兼职部门";
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
	
	
	public List<Record> getExportRecords(Account account,Connection conn) throws Exception{
		List<Record> records=new ArrayList<Record>();
		
		//添加列名
		Record record=new Record();
		record.addField(FIELD_ACCOUNT_ID, FIELD_ACCOUNT_ID);
		record.addField(FIELD_USER_NAME, FIELD_USER_NAME);
		record.addField(FIELD_DEPT_OTHER, FIELD_DEPT_OTHER);
		record.addField(FIELD_DEPT_LEADER, FIELD_DEPT_LEADER);
		record.addField(FIELD_USER_PRIV, FIELD_USER_PRIV);
		record.addField(FIELD_USER_PRIV_OTHER, FIELD_USER_PRIV_OTHER);
		record.addField(FIELD_MANAGE_DEPT, FIELD_MANAGE_DEPT);
		record.addField(FIELD_ADMINISTRATIVE_LEVEL, FIELD_ADMINISTRATIVE_LEVEL);
		record.addField(FIELD_WORK_NO, FIELD_WORK_NO);
		record.addField(FIELD_SEX, FIELD_SEX);
		record.addField(FIELD_MOIBLE_PHONE, FIELD_MOIBLE_PHONE);
		record.addField(FIELD_SORT, FIELD_SORT);
		record.addField(FIELD_HOME_ADDRESS, FIELD_HOME_ADDRESS);
		record.addField(FIELD_HOME_TEL, FIELD_HOME_TEL);
		record.addField(FIELD_QQ, FIELD_QQ);
		record.addField(FIELD_EMAIL, FIELD_EMAIL);
		records.add(record);
		
		
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			DeptExportLogic deptExportLogic=new DeptExportLogic();
			List<Record> deptRecords=deptExportLogic.getExportRecords(account, conn);
			deptRecords.remove(0);
			for (int i = 0; i < deptRecords.size(); i++) {
				Record deptRecord=deptRecords.get(i);
				String deptLongName=deptRecord.getValueByName(deptExportLogic.FIELD_DEPT_NAME).toString();
				
				//添加部门
				record=new Record();
				record.addField(FIELD_ACCOUNT_ID, deptLongName);
				record.addField(FIELD_USER_NAME, "");
				record.addField(FIELD_DEPT_OTHER, "");
				record.addField(FIELD_DEPT_LEADER, "");
				record.addField(FIELD_USER_PRIV, "");
				record.addField(FIELD_USER_PRIV_OTHER, "");
				record.addField(FIELD_MANAGE_DEPT, "");
				record.addField(FIELD_ADMINISTRATIVE_LEVEL,"");
				record.addField(FIELD_WORK_NO, "");
				record.addField(FIELD_SEX, "");
				record.addField(FIELD_MOIBLE_PHONE, "");
				record.addField(FIELD_SORT, "");
				record.addField(FIELD_HOME_ADDRESS, "");
				record.addField(FIELD_HOME_TEL, "");
				record.addField(FIELD_QQ, "");
				record.addField(FIELD_EMAIL, "");
				records.add(record);
				
				
				
				String sql=" select "
						+" ui.account_id,"
						+ " ui.user_name,"
						+ " ui.other_dept,"
						+ " (select user_name from user_info where account_id=ui.lead_id) as leader_name,"
						+ " up.user_priv_name,"
						+ " ui.other_priv,"
						+ "	ui.manage_dept,"
						+ "	ul.leave_name,"
						+ "	ui.work_id,"
						+ "	ui.sex,"
						+ "	ui.mobile_no,"
						+ "	ui.sort_id,"
						+ "	ui.home_add,"
						+ "	ui.home_tel,"
						+ "	ui.qq,"
						+ "	ui.e_maile"
						+ " from user_info as ui "
						+ "	left join department as d on ui.dept_id=d.dept_id "
						+"	left join user_priv as up on ui.post_priv=up.user_priv_id"
						+"	left join user_leave as ul on ui.lead_leave=ul.leave_id"	
						+ " where d.dept_long_name=? and ui.org_id=?"
						+ "	order by ui.sort_id desc";
				
				stmt=conn.prepareStatement(sql);
				stmt.setString(1, deptLongName);
				stmt.setString(2, account.getOrgId());
				rs=stmt.executeQuery();
				while(rs.next()){
					String accountId=rs.getString("account_id");
					String userName=rs.getString("user_name");
					String otherDept=rs.getString("other_dept");
					String leaderName=rs.getString("leader_name");
					String userPrivName=rs.getString("user_priv_name");
					String otherPriv=rs.getString("other_priv");
					String manageDept=rs.getString("manage_dept");
					String leaveName=rs.getString("leave_name");
					String workId=rs.getString("work_id");
					String sex=rs.getString("sex");
					String mobileNo=rs.getString("mobile_no");
					String sortId=rs.getString("sort_id");
					String homeAdd=rs.getString("home_add");
					String homeTel=rs.getString("home_tel");
					String qq=rs.getString("qq");
					String email=rs.getString("e_maile");
					
					otherDept=otherDept==null?"":otherDept;
					if (!otherDept.equals("")) {
						otherDept=deptLogic.getDeptLongNameStrByDeptIdStr(otherDept, account.getOrgId(), conn);
					}

					otherPriv=otherPriv==null?"":otherPriv;
					if (!otherPriv.equals("")) {
						otherPriv=userPrivLogic.getUserPrivNameStrByUserPrivIdStr(otherPriv, account.getOrgId(), conn);
					}

					manageDept=manageDept==null?"":manageDept;
					if (manageDept!=null) {
						if (manageDeptMap.containsKey(manageDept)) {
							manageDept=manageDeptMap.get(manageDept);
						}else{
							manageDept=deptLogic.getDeptLongNameStrByDeptIdStr(manageDept, account.getOrgId(), conn);
						}
					}

					leaderName=leaderName==null?"":leaderName;
					workId=workId==null?"":workId;
					sex=sex==null?"":sex;
					mobileNo=mobileNo==null?"":mobileNo;
					homeAdd=homeAdd==null?"":homeAdd;
					homeTel=homeTel==null?"":homeTel;
					qq=qq==null?"":qq;
					email=email==null?"":email;
					
					
					
					record=new Record();
					record.addField(FIELD_ACCOUNT_ID, accountId);
					record.addField(FIELD_USER_NAME, userName);
					record.addField(FIELD_DEPT_OTHER, otherDept);
					record.addField(FIELD_DEPT_LEADER, leaderName);
					record.addField(FIELD_USER_PRIV, userPrivName);
					record.addField(FIELD_USER_PRIV_OTHER, otherPriv);
					record.addField(FIELD_MANAGE_DEPT, manageDept);
					record.addField(FIELD_ADMINISTRATIVE_LEVEL, leaveName);
					record.addField(FIELD_WORK_NO, workId);
					record.addField(FIELD_SEX, sex);
					record.addField(FIELD_MOIBLE_PHONE, mobileNo);
					record.addField(FIELD_SORT, sortId);
					record.addField(FIELD_HOME_ADDRESS, homeAdd);
					record.addField(FIELD_HOME_TEL, homeTel);
					record.addField(FIELD_QQ, qq);
					record.addField(FIELD_EMAIL, email);
					records.add(record);
				}
			}
				
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return records;
	}
}
