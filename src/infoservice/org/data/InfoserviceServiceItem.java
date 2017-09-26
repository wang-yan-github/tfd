package infoservice.org.data;

import java.util.Date;

public class InfoserviceServiceItem {
	private int id;
	private String serviceId;
	private String orgId;
	private String serviceName;
	private String serviceDesc;
	private Integer servicePrice=0;
	private Integer servicePriceHighest=0;
	private Date submitDate;
	private String auditStatus;
	private String auditUser;
	private Date auditDate;
	private String auditRemark;
	
	
	public InfoserviceServiceItem(){}
	public InfoserviceServiceItem(
			int id,
			String serviceId,
			String orgId,
			String serviceName,
			String serviceDesc,
			Integer servicePrice,
			Integer servicePriceHighest,
			Date submitDate,
			String auditStatus,
			String auditUser,
			Date auditDate,
			String auditRemark
	){
		this.setId(id);
		this.setServiceId(serviceId);
		this.setOrgId(orgId);
		this.setServiceName(serviceName);
		this.setServiceDesc(serviceDesc);
		this.setServicePrice(servicePrice);
		this.setServicePriceHighest(servicePriceHighest);
		this.setSubmitDate(submitDate);
		this.setAuditStatus(auditStatus);
		this.setAuditUser(auditUser);
		this.setAuditDate(auditDate);
		this.setAuditRemark(auditRemark);
	}
	
	
	
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	public String getAuditRemark() {
		return auditRemark;
	}
	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}
	public Integer getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(Integer servicePrice) {
		this.servicePrice = servicePrice;
	}
	public Integer getServicePriceHighest() {
		return servicePriceHighest;
	}
	public void setServicePriceHighest(Integer servicePriceHighest) {
		this.servicePriceHighest = servicePriceHighest;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceDesc() {
		return serviceDesc;
	}
	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
}
