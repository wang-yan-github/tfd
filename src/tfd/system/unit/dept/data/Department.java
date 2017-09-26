package tfd.system.unit.dept.data;

public class Department {
	private int id;
	private String deptId="";
	private String deptEmail="";
	private String deptName="";
	private String orgLeaveId="";
	private String deptLongId=null;
	private String deptLongName="";
	private String deptTel="";
	private String deptFax="";
	private String deptLead="";
	private String deptFunction="";
	private String orgId="";
	
	
	
	public String getDeptLongId() {
		return deptLongId;
	}
	public void setDeptLongId(String deptLongId) {
		this.deptLongId = deptLongId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptEmail() {
		return deptEmail;
	}
	public void setDeptEmail(String deptEmail) {
		this.deptEmail = deptEmail;
	}
	public void setOrgLeaveId(String orgLeaveId) {
		this.orgLeaveId = orgLeaveId;
	}
	public int getId() {
		return id;
	}
	public String getDeptName() {
		return deptName;
	}
	public String getOrgLeaveId() {
		return orgLeaveId;
	}
	public String getDeptLongName() {
		return deptLongName;
	}
	public String getDeptTel() {
		return deptTel;
	}
	public String getDeptFax() {
		return deptFax;
	}
	public String getDeptLead() {
		return deptLead;
	}
	public String getDeptFunction() {
		return deptFunction;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public void setOrgleaveId(String orgLeaveId) {
		this.orgLeaveId = orgLeaveId;
	}
	public void setDeptLongName(String deptLongName) {
		this.deptLongName = deptLongName;
	}
	public void setDeptTel(String deptTel) {
		this.deptTel = deptTel;
	}
	public void setDeptFax(String deptFax) {
		this.deptFax = deptFax;
	}
	public void setDeptLead(String deptLead) {
		this.deptLead = deptLead;
	}
	public void setDeptFunction(String deptFunction) {
		this.deptFunction = deptFunction;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}
