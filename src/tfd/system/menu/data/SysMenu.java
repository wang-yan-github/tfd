package tfd.system.menu.data;

public class SysMenu {
	private int Id;
	private int sysMenuId;
	private String sysMenuName;
	private String sysMenuUrl;
	private String sysMenuCode;
	private String sysMenuParm;
	private String sysMenuOpen;
	private String sysMenuLeave;
	private String sysMenuPic;
	private String orgId;

	public int getSysMenuId() {
		return sysMenuId;
	}

	public void setSysMenuId(int sysMenuId) {
		this.sysMenuId = sysMenuId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getSysMenuPic() {
		return sysMenuPic;
	}

	public void setSysMenuPic(String sysMenuPic) {
		this.sysMenuPic = sysMenuPic;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getSysMenuName() {
		return sysMenuName;
	}

	public void setSysMenuName(String sysMenuName) {
		this.sysMenuName = sysMenuName;
	}

	public String getSysMenuUrl() {
		return sysMenuUrl;
	}

	public void setSysMenuUrl(String sysMenuUrl) {
		this.sysMenuUrl = sysMenuUrl;
	}

	public String getSysMenuCode() {
		return sysMenuCode;
	}

	public void setSysMenuCode(String sysMenuCode) {
		this.sysMenuCode = sysMenuCode;
	}

	public String getSysMenuParm() {
		return sysMenuParm;
	}

	public void setSysMenuParm(String sysMenuParm) {
		this.sysMenuParm = sysMenuParm;
	}

	public String getSysMenuOpen() {
		return sysMenuOpen;
	}

	public void setSysMenuOpen(String sysMenuOpen) {
		this.sysMenuOpen = sysMenuOpen;
	}

	public String getSysMenuLeave() {
		return sysMenuLeave;
	}

	public void setSysMenuLeave(String sysMenuLeave) {
		this.sysMenuLeave = sysMenuLeave;
	}
}
