package meeting.data;

public class Boardroomdevice {

	private int Id;
	private String boardroomdeviceId;
	private String deviceId;
	private String deviceName;
	private String boardroomId;
	private String deviceStatus;
	private String deviceSimilar;
	private String deviceDescription;
	private String devicetype;
	
	public String getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}
	private String orgId;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getBoardroomdeviceId() {
		return boardroomdeviceId;
	}
	public void setBoardroomdeviceId(String boardroomdeviceId) {
		this.boardroomdeviceId = boardroomdeviceId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getBoardroomId() {
		return boardroomId;
	}
	public void setBoardroomId(String boardroomId) {
		this.boardroomId = boardroomId;
	}
	public String getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	public String getDeviceSimilar() {
		return deviceSimilar;
	}
	public void setDeviceSimilar(String deviceSimilar) {
		this.deviceSimilar = deviceSimilar;
	}
	public String getDeviceDescription() {
		return deviceDescription;
	}
	public void setDeviceDescription(String deviceDescription) {
		this.deviceDescription = deviceDescription;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	
}
