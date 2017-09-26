package tfd.system.email.util;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.system.filetool.UpFileTool;

import tfd.system.email.data.EmailBody;
import tfd.system.email.data.EmailConfig;

//@author Michael.wu
// 发送复杂的邮件(文本内容，附件，图片)
public class WebMail {

	public static void SendWebMail(EmailBody emailBody,EmailConfig emailConfig) throws Exception {
		//发送邮件的协议
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth","true");//设置验证机制
		properties.setProperty("mail.transport.protocol","smtp");//发送邮件协议
		properties.setProperty("mail.smtp.host",emailConfig.getEmailServer());//设置邮箱服务器地址
		properties.setProperty("mail.smtp.port",emailConfig.getServerPort());
		Session session = Session.getInstance(properties,new MyAuthenticator(emailConfig.getEmailUser(),emailConfig.getEmailPwd()));
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emailConfig.getEmailUser()));
		message.setSubject(emailBody.getSubject());
		message.setRecipients(RecipientType.TO,InternetAddress.parse(emailBody.getToWebmail()));//接收人
		//message.setRecipients(RecipientType.CC,InternetAddress.parse("1348800595@qq.com"));//抄送人
		UpFileTool upFileTool = new UpFileTool();
		MimeMultipart mimeMuti = new MimeMultipart("mixed");
		if(!emailBody.getAttachmentId().equals("")){
			String srcFilePath = upFileTool.getAttachPath(emailBody.getAttachmentId().substring(0,emailBody.getAttachmentId().length()-1));
			MimeBodyPart bodyPartAttch = createAttachMent(srcFilePath);//附件
			mimeMuti.addBodyPart(bodyPartAttch);
		}
		MimeBodyPart contentBodyPart = new MimeBodyPart();
		contentBodyPart.setContent(emailBody.getContent(),"text/html;charset=utf-8");
		//MimeBodyPart bodyPartContentAndPic = createContentAndPic(emailBody.getContent(),"");//文本内容
		mimeMuti.addBodyPart(contentBodyPart);
		message.setContent(mimeMuti);
		message.saveChanges();
		Transport.send(message);
	}
	//创建附件
	public static MimeBodyPart createAttachMent(String path) throws MessagingException{
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		FileDataSource dataSource = new FileDataSource( new File(path));
		mimeBodyPart.setDataHandler(new DataHandler(dataSource));
		mimeBodyPart.setFileName(dataSource.getName());
		return mimeBodyPart;
	}
	//创建文本和图片
	public static MimeBodyPart createContentAndPic(String content,String path) throws MessagingException{
		MimeMultipart mimeMutiPart = new MimeMultipart("related");
		//图片
		MimeBodyPart picBodyPart = new MimeBodyPart();
		FileDataSource fileDataSource = new FileDataSource( new File(path));
		picBodyPart.setDataHandler(new DataHandler(fileDataSource));
		picBodyPart.setFileName(fileDataSource.getName());
		mimeMutiPart.addBodyPart(picBodyPart);
		//文本
		MimeBodyPart contentBodyPart = new MimeBodyPart();
		contentBodyPart.setContent(content,"text/html;charset=utf-8");
		mimeMutiPart.addBodyPart(contentBodyPart);
		//图片和文本结合
		MimeBodyPart allBodyPart = new MimeBodyPart();
		allBodyPart.setContent(mimeMutiPart);
		return allBodyPart;
	}
}
