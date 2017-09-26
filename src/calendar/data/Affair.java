package calendar.data;

public class Affair {
	private int id;
	private String affairId;
	private String calendarId;
	private String calAffType;
	private String remindType;
	private String remindTime;
	private String remindDate;
	private String endWhile;
	private String Frequency;
	private String isWeek;
	private String orgId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAffairId() {
		return affairId;
	}
	public void setAffairId(String affairId) {
		this.affairId = affairId;
	}
	public String getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}
	public String getCalAffType() {
		return calAffType;
	}
	public void setCalAffType(String calAffType) {
		this.calAffType = calAffType;
	}
	public String getRemindType() {
		return remindType;
	}
	public void setRemindType(String remindType) {
		this.remindType = remindType;
	}
	public String getRemindTime() {
		return remindTime;
	}
	public void setRemindTime(String remindTime) {
		this.remindTime = remindTime;
	}
	public String getRemindDate() {
		return remindDate;
	}
	public void setRemindDate(String remindDate) {
		this.remindDate = remindDate;
	}
	public String getEndWhile() {
		return endWhile;
	}
	public void setEndWhile(String endWhile) {
		this.endWhile = endWhile;
	}
	public String getFrequency() {
		return Frequency;
	}
	public void setFrequency(String frequency) {
		Frequency = frequency;
	}
	public String getIsWeek() {
		return isWeek;
	}
	public void setIsWeek(String isWeek) {
		this.isWeek = isWeek;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public Affair() {
		super();
	}
	
}
