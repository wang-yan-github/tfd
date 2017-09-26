package tfd.system.publicdisk.data;

public class PublicDisk {
	private int id;
	private String diskId;
	private String diskName;
	private String diskPath;
	private String newUser;
	private String manageUser;
	private String accessUser;
	private String downUser;
	private String newDept;
	private String manageDept;
	private String accessDpet;
	private String downDept;
	private String newPriv;
	private String managePriv;
	private String accessPriv;
	private String downPriv;
	private String diskNo;
	private String spaceLimit;
	private String orderBy;
	private String ascDesc;
	private String orgId;

	
	

	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getDiskId() {
		return diskId;
	}




	public void setDiskId(String diskId) {
		this.diskId = diskId;
	}




	public String getDiskName() {
		return diskName;
	}




	public void setDiskName(String diskName) {
		this.diskName = diskName;
	}




	public String getDiskPath() {
		return diskPath;
	}




	public void setDiskPath(String diskPath) {
		this.diskPath = diskPath;
	}




	public String getNewUser() {
		return newUser;
	}




	public void setNewUser(String newUser) {
		this.newUser = newUser;
	}




	public String getManageUser() {
		return manageUser;
	}




	public void setManageUser(String manageUser) {
		this.manageUser = manageUser;
	}




	public String getAccessUser() {
		return accessUser;
	}




	public void setAccessUser(String accessUser) {
		this.accessUser = accessUser;
	}




	public String getDownUser() {
		return downUser;
	}




	public void setDownUser(String downUser) {
		this.downUser = downUser;
	}




	public String getNewDept() {
		return newDept;
	}




	public void setNewDept(String newDept) {
		this.newDept = newDept;
	}




	public String getManageDept() {
		return manageDept;
	}




	public void setManageDept(String manageDept) {
		this.manageDept = manageDept;
	}




	public String getAccessDpet() {
		return accessDpet;
	}




	public void setAccessDpet(String accessDpet) {
		this.accessDpet = accessDpet;
	}




	public String getDownDept() {
		return downDept;
	}




	public void setDownDept(String downDept) {
		this.downDept = downDept;
	}




	public String getNewPriv() {
		return newPriv;
	}




	public void setNewPriv(String newPriv) {
		this.newPriv = newPriv;
	}




	public String getManagePriv() {
		return managePriv;
	}




	public void setManagePriv(String managePriv) {
		this.managePriv = managePriv;
	}




	public String getAccessPriv() {
		return accessPriv;
	}




	public void setAccessPriv(String accessPriv) {
		this.accessPriv = accessPriv;
	}




	public String getDownPriv() {
		return downPriv;
	}




	public void setDownPriv(String downPriv) {
		this.downPriv = downPriv;
	}




	public String getDiskNo() {
		return diskNo;
	}




	public void setDiskNo(String diskNo) {
		this.diskNo = diskNo;
	}




	public String getSpaceLimit() {
		return spaceLimit;
	}




	public void setSpaceLimit(String spaceLimit) {
		this.spaceLimit = spaceLimit;
	}




	public String getOrderBy() {
		return orderBy;
	}




	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}




	public String getAscDesc() {
		return ascDesc;
	}




	public void setAscDesc(String ascDesc) {
		this.ascDesc = ascDesc;
	}




	public String getOrgId() {
		return orgId;
	}




	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	

	public PublicDisk(int id, String diskId, String diskName, String diskPath,
			String newUser, String manageUser, String accessUser,
			String downUser, String newDept, String manageDept,
			String accessDpet, String downDept, String newPriv,
			String managePriv, String accessPriv, String downPriv,
			String diskNo, String spaceLimit, String orderBy, String ascDesc,
			String orgId) {
		super();
		this.id = id;
		this.diskId = diskId;
		this.diskName = diskName;
		this.diskPath = diskPath;
		this.newUser = newUser;
		this.manageUser = manageUser;
		this.accessUser = accessUser;
		this.downUser = downUser;
		this.newDept = newDept;
		this.manageDept = manageDept;
		this.accessDpet = accessDpet;
		this.downDept = downDept;
		this.newPriv = newPriv;
		this.managePriv = managePriv;
		this.accessPriv = accessPriv;
		this.downPriv = downPriv;
		this.diskNo = diskNo;
		this.spaceLimit = spaceLimit;
		this.orderBy = orderBy;
		this.ascDesc = ascDesc;
		this.orgId = orgId;
	}




	public PublicDisk() {
		super();
	}
}
