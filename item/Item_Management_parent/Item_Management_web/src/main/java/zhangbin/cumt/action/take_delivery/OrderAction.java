package zhangbin.cumt.action.take_delivery;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

import zhangbin.cumt.action.parent.BaseAction;
import zhangbin.cumt.domain.base.Area;
import zhangbin.cumt.domain.base.Courier;
import zhangbin.cumt.domain.take_delivery.Order;
import zhangbin.cumt.service.take_delivery.OrderService;
@Controller
@Namespace("/")
@ParentPackage("json-default")
@Scope("prototype")
@Results(value = { 
		@Result(name="pageQuery",params= {"root","map"},type="json"),
		@Result(name="list",params= {"root","courierList"},type="json"),
		@Result(name="assignCourierForOrder",type="redirect",location="/pages/take_delivery/dispatcher.html"),
		
		
})
public class OrderAction extends BaseAction<Order> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private OrderService orderService;
	@Action("OrderAction_pageQuery")
	public String pageQuery() throws Exception {
		PageRequest pageRequest = new PageRequest(page-1, rows);
		PageImpl<Order> pageResult = orderService.pageQuery(pageRequest);
		map.put("total", pageResult.getNumber());
		map.put("rows", pageResult.getContent());
		return "pageQuery";
		
	}
	
	private String province;
	private String city;
	private String district;
	private List<Courier> courierList;
	public List<Courier> getCourierList() {
		return courierList;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
	/**
	 * 
	 * @Description: 用来匹配快递员    这儿代码很乱
	 * @return
	 * @throws Exception
	 */
	@Action("OrderAction_assignCourierByManual")
	public String assignCourierByManual() throws Exception {
		Area area = new Area();
		area.setProvince(province);
		area.setCity(city);
		area.setDistrict(district);
		courierList = orderService.assignCourierByManual(model,area);
		return "list";
	}
	/**
	 * 
	 * @Description: 订单的更新，为其分配快递员，生成对应的工单
	 * @return
	 * @throws Exception
	 */
	@Action("orderAction_assignCourierForOrder")
	public String assignCourierForOrder() throws Exception {
		orderService.updateByManual(model);
		return "assignCourierForOrder";
	}
	
	
	
}
