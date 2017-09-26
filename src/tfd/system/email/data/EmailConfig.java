package tfd.system.email.data;

public class EmailConfig {
	private int id;
	private String configId;
	private String emailServer;
	private String serverPort;
	private String emailUser;
	private String emailPwd;
	private String accountId;
	private String orgId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConfigId() {
		return configId;
	}
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	public String getEmailServer() {
		return emailServer;
	}
	public void setEmailServer(String emailServer) {
		this.emailServer = emailServer;
	}
	public String getServerPort() {
		return serverPort;
	}
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}
	public String getEmailUser() {
		return emailUser;
	}
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	public String getEmailPwd() {
		return emailPwd;
	}
	public void setEmailPwd(String emailPwd) {
		this.emailPwd = emailPwd;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public EmailConfig(int id, String configId, String emailServer,
			String serverPort, String emailUser, String emailPwd,
			String accountId, String orgId) {
		super();
		this.id = id;
		this.configId = configId;
		this.emailServer = emailServer;
		this.serverPort = serverPort;
		this.emailUser = emailUser;
		this.emailPwd = emailPwd;
		this.accountId = accountId;
		this.orgId = orgId;
	}
	public EmailConfig() {
		super();
	}
	
}
