package zhangbin.cumt.jms;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Component;

import zhangbin.cumt.utils.MailUtil2;
@Component("mailListener")
public class MailListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			MapMessage mapMessage = (MapMessage) message;
			String email = mapMessage.getString("email");
			String emailMsg = mapMessage.getString("emailMsg");
			MailUtil2.sendMail(email, emailMsg);
			
		} catch (JMSException e) {
			e.printStackTrace();
		}catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
