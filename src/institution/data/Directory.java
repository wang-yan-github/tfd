package institution.data;

public class Directory {
	private int Id;
	private String dirId;
	private String dirName;
	private String topId;
	private String allDir;
	private String orgId;
	

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getDirId() {
		return dirId;
	}
	public void setDirId(String dirId) {
		this.dirId = dirId;
	}
	public String getDirName() {
		return dirName;
	}
	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public String getTopId() {
		return topId;
	}
	public void setTopId(String topId) {
		this.topId = topId;
	}
	public String getAllDir() {
		return allDir;
	}
	public void setAllDir(String allDir) {
		this.allDir = allDir;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public Directory() {
		super();
	}
	public Directory(String dirId, String dirName, String topId, String allDir,
			String orgId) {
		super();
		this.dirId = dirId;
		this.dirName = dirName;
		this.topId = topId;
		this.allDir = allDir;
		this.orgId = orgId;
	}
	public Directory(int id, String dirId, String dirName, String topId,
			String allDir, String orgId) {
		super();
		Id = id;
		this.dirId = dirId;
		this.dirName = dirName;
		this.topId = topId;
		this.allDir = allDir;
		this.orgId = orgId;
	}
	
}
