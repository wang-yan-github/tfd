package tfd.system.im.data;

public class ImUser {
	private String accountId;
	private String key;
	private String mid;
	private String defined;
	
	public ImUser(){}
	public ImUser(String accountId,String key,String mid,String defined){
		this.setAccountId(accountId);
		this.setKey(key);
		this.setMid(mid);
		this.setDefined(defined);
	}
	
	
	public String getDefined() {
		return defined;
	}
	public void setDefined(String defined) {
		this.defined = defined;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
