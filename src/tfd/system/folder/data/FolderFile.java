package tfd.system.folder.data;

public class FolderFile {
	private int id;
	private String fileId;
	private int fileNo;
	private String fileName;
	private String folderId;
	private String fileContent;
	private String attachId;
	private String orgId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFolderId() {
		return folderId;
	}
	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}
	public String getFileContent() {
		return fileContent;
	}
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
	public String getAttachId() {
		return attachId;
	}
	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public FolderFile(int id, String fileId, int fileNo, String fileName,
			String folderId, String fileContent, String attachId, String orgId) {
		super();
		this.id = id;
		this.fileId = fileId;
		this.fileNo = fileNo;
		this.fileName = fileName;
		this.folderId = folderId;
		this.fileContent = fileContent;
		this.attachId = attachId;
		this.orgId = orgId;
	}
	public FolderFile() {
		super();
	}
}
