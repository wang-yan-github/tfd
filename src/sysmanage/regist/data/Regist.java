package sysmanage.regist.data;

import java.util.Date;

public class Regist {
	private String id;
	private String registUnit;
	private String productSn;
	private Date registTime;
	private Date registDeadline;
	private int registUserCount;
	private int registImUserCount;
	private String registDiskSn;


	public Regist(
			String id,
			String registUnit,
			String productSn,
			Date registTime,
			Date registDeadline,
			int registUserCount,
			int registImUserCount,
			String registDiskSn
	){
		this.setId(id);
		this.setRegistUnit(registUnit);
		this.setProductSn(productSn);
		this.setRegistTime(registTime);
		this.setRegistDeadline(registDeadline);
		this.setRegistUserCount(registUserCount);
		this.setRegistImUserCount(registImUserCount);
		this.setRegistDiskSn(registDiskSn);
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRegistUnit() {
		return registUnit;
	}

	public void setRegistUnit(String registUnit) {
		this.registUnit = registUnit;
	}

	public String getProductSn() {
		return productSn;
	}

	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public Date getRegistDeadline() {
		return registDeadline;
	}

	public void setRegistDeadline(Date registDeadline) {
		this.registDeadline = registDeadline;
	}

	public int getRegistUserCount() {
		return registUserCount;
	}

	public void setRegistUserCount(int registUserCount) {
		this.registUserCount = registUserCount;
	}

	public int getRegistImUserCount() {
		return registImUserCount;
	}

	public void setRegistImUserCount(int registImUserCount) {
		this.registImUserCount = registImUserCount;
	}

	public String getRegistDiskSn() {
		return registDiskSn;
	}

	public void setRegistDiskSn(String registDiskSn) {
		this.registDiskSn = registDiskSn;
	}

	
	
}
