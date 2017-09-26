package tfd.system.syslog.data;


public class SysLog {
	private int seqId;
	  private int userId;
	  private String logDate;
	  private String ip;
	  private String logType;
	  private String remark;
	  public int getSeqId() {
	    return seqId;
	  }
	  public void setSeqId(int seqId) {
	    this.seqId = seqId;
	  }
	  public int getUserId() {
	    return userId;
	  }
	  public void setUserId(int userId) {
	    this.userId = userId;
	  }
	  public String getIp() {
	    return ip;
	  }
	  public void setIp(String ip) {
	    this.ip = ip;
	  }
	  public String getRemark() {
	    return remark;
	  }
	  public void setRemark(String remark) {
	    this.remark = remark;
	  }
	public String getLogDate() {
		return logDate;
	}
	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
}
