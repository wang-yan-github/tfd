package tfd.system.attend.data;

public class AttendTime {
	private int id;
	private String attendTimeId="";
	private String attendConfigId="";
	private String time1="";
	private String time2="";
	private String timeType1="";
	private String timeType2="";
	private String orgId="";
	
	public String getAttendConfigId() {
		return attendConfigId;
	}
	public void setAttendConfigId(String attendConfigId) {
		this.attendConfigId = attendConfigId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAttendTimeId() {
		return attendTimeId;
	}
	public void setAttendTimeId(String attendTimeId) {
		this.attendTimeId = attendTimeId;
	}
	public String getTime1() {
		return time1;
	}
	public void setTime1(String time1) {
		this.time1 = time1;
	}
	public String getTime2() {
		return time2;
	}
	public void setTime2(String time2) {
		this.time2 = time2;
	}
	public String getTimeType1() {
		return timeType1;
	}
	public void setTimeType1(String timeType1) {
		this.timeType1 = timeType1;
	}
	public String getTimeType2() {
		return timeType2;
	}
	public void setTimeType2(String timeType2) {
		this.timeType2 = timeType2;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public AttendTime() {
		super();
	}
	
}
