package tfd.system.sms.data;

import java.util.Date;

public class Sms {
	//短消息删除标志
	//未删除
	public static final String SMS_FLAG_NOT_DELETE="1";
	//已删除
	public static final String SMS_FLAG_HAVE_DELETE="0";
	//短消息状态
	//未接收
	public static final String SMS_STATUS_NOT_RECEIVE="0";
	//已接收
	public static final String SMS_STATUS_HAVE_RECEIVE="1";
	//已读
	public static final String SMS_STATUS_HAVE_READ="2";
	
	
	
	private int id;
	private String smsId;
	private String smsFrom;
	private String smsTo;
	private Date smsSendTime;
	private String smsContent;
	private String smsFlag;
	private String smsType;
	private String smsUrl;
	private String smsAttachId;
	private String smsAttachPriv;
	private String smsStatus;
	private String orgId;

	public Date getSmsSendTime() {
		return smsSendTime;
	}

	public void setSmsSendTime(Date smsSendTime) {
		this.smsSendTime = smsSendTime;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	public String getSmsFrom() {
		return smsFrom;
	}

	public void setSmsFrom(String smsFrom) {
		this.smsFrom = smsFrom;
	}

	public String getSmsTo() {
		return smsTo;
	}

	public void setSmsTo(String smsTo) {
		this.smsTo = smsTo;
	}

	public String getSmsFlag() {
		return smsFlag;
	}

	public void setSmsFlag(String smsFlag) {
		this.smsFlag = smsFlag;
	}

	public String getSmsUrl() {
		return smsUrl;
	}

	public void setSmsUrl(String smsUrl) {
		this.smsUrl = smsUrl;
	}

	public String getSmsAttachId() {
		return smsAttachId;
	}

	public void setSmsAttachId(String smsAttachId) {
		this.smsAttachId = smsAttachId;
	}

	public String getSmsAttachPriv() {
		return smsAttachPriv;
	}

	public void setSmsAttachPriv(String smsAttachPriv) {
		this.smsAttachPriv = smsAttachPriv;
	}

	public String getSmsStatus() {
		return smsStatus;
	}

	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}

}
