package zhangbin.cumt.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
/**
 * @TODO这个工具类应该更实用，需要研究一下
 * @author zhang
 *
 */
public class MailUtils {
	
	private static String smtp_host = "smtp.126.com";    //SMTP服务器地址
	private static String username = "itcast_java@126.com"; //发件人邮箱
	//注意：不是使用登陆密码。使用第三方平台登陆邮件需要使用的是授权码--设置-授权码-开启授权码   
	//登陆密码：itcast123
	private static String password = "bos123";  
	private static String from = "itcast_java@126.com"; // 使用当前账户
	
	//激活账户地址
	public static String activeUrl = "http://localhost:8084/bos_fore/customerAction_activeMail.action";

	/**
	  * @Description: 发送邮件方法
	  * @param subject 邮件标题
	  * @param content 邮件正文
	  * @param to      收件人邮箱地址
	  *	  
	 */
	public static void sendMail(String subject, String content, String to) {
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", smtp_host);
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(props);
		Message message = new MimeMessage(session);
		try {
			//设置发件人
			message.setFrom(new InternetAddress(from));
			//RecipientType.TO 直接发送   CC:抄送  BCC:密送
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setContent(content, "text/html;charset=utf-8");
			Transport transport = session.getTransport();
			transport.connect(smtp_host, username, password);
			transport.sendMessage(message, message.getAllRecipients());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("邮件发送失败...");
		}
	}

	public static void main(String[] args) {
		sendMail("测试邮件", "你好，传智播客", "lvhonglong816@126.com");
	}
}
