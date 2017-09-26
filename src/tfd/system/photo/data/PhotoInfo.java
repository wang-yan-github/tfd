package tfd.system.photo.data;

import java.util.Date;

public class PhotoInfo {
	private int id;
	private String photoInfoId;
	private String photoInfoName;
	private Date createTime;
	private String createAccountId;
	private String good;
	private String photoPath;
	private String orgId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhotoInfoId() {
		return photoInfoId;
	}
	public void setPhotoInfoId(String photoInfoId) {
		this.photoInfoId = photoInfoId;
	}
	public String getPhotoInfoName() {
		return photoInfoName;
	}
	public void setPhotoInfoName(String photoInfoName) {
		this.photoInfoName = photoInfoName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getGood() {
		return good;
	}
	public void setGood(String good) {
		this.good = good;
	}
	public String getCreateAccountId() {
		return createAccountId;
	}
	public void setCreateAccountId(String createAccountId) {
		this.createAccountId = createAccountId;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	
}
