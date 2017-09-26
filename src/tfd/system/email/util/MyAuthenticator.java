package tfd.system.email.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
public class MyAuthenticator extends Authenticator {

	private String userName;
	private String passWord;
// * @author Michael.wu
//* 密码和用户的验证
public MyAuthenticator() {
super();
}
public MyAuthenticator(String username ,String password) {
	this.userName = username;
	this.passWord = password;
}

@Override
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(userName, passWord);
}

}
