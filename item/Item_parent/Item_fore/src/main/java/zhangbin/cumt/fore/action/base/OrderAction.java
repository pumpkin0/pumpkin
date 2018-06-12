package zhangbin.cumt.fore.action.base;

import java.util.Date;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;

import zhangbin.cumt.domain.base.Area;
import zhangbin.cumt.domain.take_delivery.Order;
import zhangbin.cumt.fore.action.parent.BaseAction;
import zhangbin.cumt.item_bos.service.OrderService;
import zhangbin.cumt.item_crm.service.Customer;
@Controller
@Namespace("/")
@ParentPackage("json-default")
@Scope("prototype")
@Results(value = { 
		@Result(name="save",location="/order-success.html")
		
})
public class OrderAction extends BaseAction<Order> {
	@Autowired
	private OrderService orderService;
	//发件人的地址信息
	private String sendAreaInfo;
	public void setSendAreaInfo(String sendAreaInfo) {
		this.sendAreaInfo = sendAreaInfo;
	}
	//收件人的地址信息
	private String recAreaInfo;
	public void setRecAreaInfo(String recAreaInfo) {
		this.recAreaInfo = recAreaInfo;
	}


	@Action("orderAction_save")
	public String save() throws Exception {
		//将订单保信息保存到bos数据库中
		
		//首先利用寄件地址信息和发件地址信息去数据库查找，如果有那么利用返回的结果，否则新建对象(这一过程封装到generateSendAndRecArea方法中)
		Area sendArea = generateSendAndRecArea(sendAreaInfo);
		model.setSendArea(sendArea);
		Area recArea = generateSendAndRecArea(recAreaInfo);
		model.setRecArea(recArea);
		//生成随时的订单号
		String orderNum = UUID.randomUUID().toString();
		model.setOrderNum(orderNum);
		Date date = new Date();
		model.setOrderTime(date);
		
		//客户编号如何解决，不清楚，  从session中取出存储的客户信息，放进去，但是这样不登录就不能存放信息
		Customer customer = (Customer) ServletActionContext.getRequest().getSession().getAttribute("customer");
		
		//下面是用户自动注册的一个过程
		/*if(customer == null) {
			customer = new Customer();
			customer.setTelephone(model.getTelephone());
			//我们把customer存入customer的数据库中，即一个自动注册,感觉好坑
			customerService.save(customer);
		}*/
		
		//如果用户不为空,具备id的是已经注册过的用户
		if(customer != null) {
			model.setCustomer_id(customer.getId());
		}
		orderService.save(model);
		
		return "save";
	}
	
	/**
	 * 
	 * @Description: 用来将一个地址信息的字符串转换为一个Area对象
	 * @return Area对象
	 */
	private Area generateSendAndRecArea(String areaInfo) {
		String[] subAreas = areaInfo.split("/");
		String province = subAreas[0];
		String city = subAreas[1];
		String district = subAreas[2];
		/*
		Area findArea = areaService.findByProvinceAndCityAndDistrict(province,city,district);
		if(findArea != null) {
			return findArea;
		}*/
		//没有查找到，那么生成新的地区
		/*String areaId = UUID.randomUUID().toString();*/
		Area generateArea = new Area(null,province,city,district,null,null,null); 
		return generateArea;
	}
}
