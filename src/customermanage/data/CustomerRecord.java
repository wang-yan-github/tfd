package customermanage.data;

public class CustomerRecord {

	private int Id;
	private String recordId;
	private String customerId;
	private String 	linkmanId;
	private String recordContent;
	private String recordWarn;
	private String recordlinkType;
	private String recordTime;
	private String orgId;
	private String accountId;
	private String address;
	private String detail;
	private String lon;
	private String lat;
	
	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getLinkmanId() {
		return linkmanId;
	}
	public void setLinkmanId(String linkmanId) {
		this.linkmanId = linkmanId;
	}
	public String getRecordContent() {
		return recordContent;
	}
	public void setRecordContent(String recordContent) {
		this.recordContent = recordContent;
	}
	public String getRecordWarn() {
		return recordWarn;
	}
	public void setRecordWarn(String recordWarn) {
		this.recordWarn = recordWarn;
	}
	public String getRecordlinkType() {
		return recordlinkType;
	}
	public void setRecordlinkType(String recordlinkType) {
		this.recordlinkType = recordlinkType;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}
