package infoservice.org.data;
import java.util.Date;


public class InfoserviceOrg {
	private int id;
	private String orgId;
	private int visits;
	private String orgIntroduce;
	private String serviceArea;
	private String businessLicense;
	private String orgCodeCertificate;
	private String taxRegistrationCertificate;
	private Date submitDate;
	private String auditStatus;
	private String auditUser;
	private Date auditDate;
	private String auditRemark;
	
	
	

	public InfoserviceOrg(){}
	public InfoserviceOrg(
		int id,
		String orgId,
		int visits,
		String orgIntroduce,
		String serviceArea,
		String businessLicense,
		String orgCodeCertificate,
		String taxRegistrationCertificate,
		Date submitDate,
		String auditStatus,
		String auditUser,
		Date auditDate,
		String auditRemark
	){
		this.setId(id);
		this.setOrgId(orgId);
		this.setVisits(visits);
		this.setOrgIntroduce(orgIntroduce);
		this.setServiceArea(serviceArea);
		this.setBusinessLicense(businessLicense);
		this.setOrgCodeCertificate(orgCodeCertificate);
		this.setTaxRegistrationCertificate(taxRegistrationCertificate);
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
	public String getOrgIntroduce() {
		return orgIntroduce;
	}
	public void setOrgIntroduce(String orgIntroduce) {
		this.orgIntroduce = orgIntroduce;
	}
	public String getServiceArea() {
		return serviceArea;
	}
	public void setServiceArea(String serviceArea) {
		this.serviceArea = serviceArea;
	}
	public String getBusinessLicense() {
		return businessLicense;
	}
	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
	public String getOrgCodeCertificate() {
		return orgCodeCertificate;
	}
	public void setOrgCodeCertificate(String orgCodeCertificate) {
		this.orgCodeCertificate = orgCodeCertificate;
	}
	public String getTaxRegistrationCertificate() {
		return taxRegistrationCertificate;
	}
	public void setTaxRegistrationCertificate(String taxRegistrationCertificate) {
		this.taxRegistrationCertificate = taxRegistrationCertificate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public int getVisits() {
		return visits;
	}
	public void setVisits(int visits) {
		this.visits = visits;
	}
	
}
