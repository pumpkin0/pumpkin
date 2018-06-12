package zhangbin.cumt.jms;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Component;

import zhangbin.cumt.utils.AliSMSUtil;
@Component("smsListener")
public class SmsListener implements MessageListener {
	/**
	 * 监听短信队列中的消息
	 */
	@Override
	public void onMessage(Message message) {
		try {
			MapMessage mapMessage = (MapMessage) message;
			String telephone = mapMessage.getString("telephone");
			String signName = mapMessage.getString("signName");
			String templateCode = mapMessage.getString("templateCode");
			Map<String,Object> templateParam = (Map<String, Object>) mapMessage.getObject("templateParam");
			AliSMSUtil.sendMessge(telephone, signName, templateCode, templateParam);
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

}
