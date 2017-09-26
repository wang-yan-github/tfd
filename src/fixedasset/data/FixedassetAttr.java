package fixedasset.data;

import java.util.Date;

public class FixedassetAttr {
	private int id;
	private String seqId;
	private String assetNo;
	private String assetName;
	private String standard;
	private String manufacture;
	private Date exFactoryDate;
	private String serviceLife;
	private double unitPrice;
	private String remark;
	private String image;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSeqId() {
		return seqId;
	}
	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}
	public String getAssetNo() {
		return assetNo;
	}
	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public Date getExFactoryDate() {
		return exFactoryDate;
	}
	public void setExFactoryDate(Date exFactoryDate) {
		this.exFactoryDate = exFactoryDate;
	}
	public String getServiceLife() {
		return serviceLife;
	}
	public void setServiceLife(String serviceLife) {
		this.serviceLife = serviceLife;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

}
