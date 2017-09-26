package tfd.system.regist.data;

import java.util.Date;

public class Reg {
//	{
//	     "unit":"公司名称",
//	     "version":"产品版本",
//	     "sn":"产品序列号",
//	     "regTime":"注册时间，时间格式2015-12-12",
//	     "deadline":"截止日期，时间格式2015-12-12，空值表示永不过期",
//	     "userCount":"用户数",
//	     "imUserCount":"im使用人数",
//	     "diskSn":"硬盘序列号",
//	     "productName":"产品名称",
//	     "productTeam":"开发团队",
//	     "productSite":"产品官网"
//	}

	private String unit;
	private String version;
	private String sn;
	private Date regTime;
	private Date deadline;
	private int userCount;
	private int imUserCount;
	private String diskSn;
	private String productName;
	private String productTeam;
	private String productSite;
	
	public Reg(){}
	public Reg(
				String unit,
				String version,
				String sn,
				Date regTime,
				Date deadline,
				int userCount,
				int imUserCount,
				String diskSn,
				String productName,
				String productTeam,
				String productSite
				){
		this.setUnit(unit);
		this.setVersion(version);
		this.setSn(sn);
		this.setRegTime(regTime);
		this.setDeadline(deadline);
		this.setUserCount(imUserCount);
		this.setImUserCount(imUserCount);
		this.setDiskSn(diskSn);
		this.setProductName(productName);
		this.setProductTeam(productTeam);
		this.setProductSite(productSite);
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	public int getImUserCount() {
		return imUserCount;
	}
	public void setImUserCount(int imUserCount) {
		this.imUserCount = imUserCount;
	}
	public String getDiskSn() {
		return diskSn;
	}
	public void setDiskSn(String diskSn) {
		this.diskSn = diskSn;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductTeam() {
		return productTeam;
	}
	public void setProductTeam(String productTeam) {
		this.productTeam = productTeam;
	}
	public String getProductSite() {
		return productSite;
	}
	public void setProductSite(String productSite) {
		this.productSite = productSite;
	}
}
