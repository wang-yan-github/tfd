package tfd.system.code.data;

public class CodeClass {
	private int id;
	private String codeId;
	private String codePid;
	private String codeName;
	private String pageCode;
	private String codeValue;
	private String orgId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getCodePid() {
		return codePid;
	}
	public void setCodePid(String codePid) {
		this.codePid = codePid;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getPageCode() {
		return pageCode;
	}
	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public CodeClass(int id, String codeId, String codePid, String codeName,
			String pageCode, String codeValue,  String orgId) {
		super();
		this.id = id;
		this.codeId = codeId;
		this.codePid = codePid;
		this.codeName = codeName;
		this.pageCode = pageCode;
		this.codeValue = codeValue;
		this.orgId = orgId;
	}
	public CodeClass(String codeId, String codePid, String codeName,
			String pageCode, String codeValue, String orgId) {
		super();
		this.codeId = codeId;
		this.codePid = codePid;
		this.codeName = codeName;
		this.pageCode = pageCode;
		this.codeValue = codeValue;
		this.orgId = orgId;
	}
	
	
}
