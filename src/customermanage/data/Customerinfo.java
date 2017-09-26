package customermanage.data;

public class Customerinfo {

	private int Id;					
	private String customerId;
	private String accountId;		//创建人
	private String joinStaff;		//参与人	
	private String customerName;	//客户名称
	private String telNumber;		//电话号码
	private String faxNumber;		//传真
	private String urlName;			//网址
	private String eMail;			//邮件
	private String areaName;		//地区
	private String addName;			//详细地址
	private String customerType;	//客户类型
	private String customerOrigin;	//客户来源
	private String tradeType;		//行业属性
	private String firmNature;		//企业性质
	private String firmSummary;		//企业简述
	private String remark;			//备注
	private String top;				//置顶
	private String keeper;			//客户经理
	private String customerStatus;	//客户状态
	private String customerTime;	//客户创建时间
	private int delStatus;			//是否删除的状态（0 否 1 是）
	private String orgId;
	
	
	public int getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
	public String getCustomerTime() {
		return customerTime;
	}
	public void setCustomerTime(String customerTime) {
		this.customerTime = customerTime;
	}
	public String getCustomerStatus() {
		return customerStatus;
	}
	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}
	public String getKeeper() {
		return keeper;
	}
	public void setKeeper(String keeper) {
		this.keeper = keeper;
	}
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getJoinStaff() {
		return joinStaff;
	}
	public void setJoinStaff(String joinStaff) {
		this.joinStaff = joinStaff;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getTelNumber() {
		return telNumber;
	}
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getUrlName() {
		return urlName;
	}
	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAddName() {
		return addName;
	}
	public void setAddName(String addName) {
		this.addName = addName;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getCustomerOrigin() {
		return customerOrigin;
	}
	public void setCustomerOrigin(String customerOrigin) {
		this.customerOrigin = customerOrigin;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getFirmNature() {
		return firmNature;
	}
	public void setFirmNature(String firmNature) {
		this.firmNature = firmNature;
	}
	public String getFirmSummary() {
		return firmSummary;
	}
	public void setFirmSummary(String firmSummary) {
		this.firmSummary = firmSummary;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}	
	
	
}
