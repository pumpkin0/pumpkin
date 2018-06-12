package zhangbin.cumt.jobs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.ServletContextResource;

import zhangbin.cumt.dao.take_delivery.WorkBillDao;
import zhangbin.cumt.domain.take_delivery.WorkBill;

@Component("mailJob")
public class MailJob {
	@Autowired
	private WorkBillDao workBillDao;
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	public void sendMailToAdmin() {
		try {
			List<WorkBill> list = workBillDao.findAll();
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
			helper.setFrom(mailSender.getUsername());
			helper.setTo("97022237@qq.com");
			helper.setSubject(new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"日工单数据");
			
			String domHtml = "";
			for(WorkBill workBill:list) {
				domHtml += "<tr><td>" + workBill.getId() + "</td><td>" + workBill.getPickstate() + "</td>" + "<td>"
						+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(workBill.getBuildtime()) + "</td>" + "<td>"
						+ workBill.getCourier().getName() + "</td></tr>";
			}
			helper.setText("<!DOCTYPE html><html><body>" + "<table border='1' bordercolor='#000000' style='width: 99%'>"
					+ "<tr>" + "<th colspan='4'>" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "</th>"
					+ "</tr><tr>" + "<td>工单编号</td>" + "<td>取件状态</td>" + "<td>生成时间</td>" + "<td>快递员</td></tr>" 
					+ domHtml
					// + "<tr><td>0100001</td><td>取件中</td><td>2018.01.04 11:22:34</td><td>张三</td></tr>"
					+ "</table>" 
					+ "<br><br><br>" 
					+ "<img src='cid:file'/></body></html>", true);
			
			WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
			ServletContext context = applicationContext.getServletContext();
			
			ServletContextResource resource = new ServletContextResource(context, "/mail/test.jpg");
			//和上面的img的标签中的file相同
			helper.addInline("file", resource);
			mailSender.send(msg);
			
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
