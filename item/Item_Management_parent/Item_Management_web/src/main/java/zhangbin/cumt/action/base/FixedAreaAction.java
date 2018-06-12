package zhangbin.cumt.action.base;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import zhangbin.cumt.action.parent.BaseAction;
import zhangbin.cumt.bos_crm.service.CustomerService;
import zhangbin.cumt.domain.base.FixedArea;
import zhangbin.cumt.service.base.CourierService;
import zhangbin.cumt.service.base.FixedAreaService;
@Controller
@Namespace("/")
@ParentPackage("json-default")
@Scope("prototype")
@Results(value = { 
		@Result(name="operation",location="/pages/base/fixed_area.jsp"),
		@Result(name="pageQuery",params= {"root","map"},type="json"),
		@Result(name="jsonList",params= {"root","list"},type="json")
		
		
})
public class FixedAreaAction extends BaseAction<FixedArea> {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private FixedAreaService fixedAreaService;
	@Autowired
	private CustomerService customerService;
	private String fixedAreaId;
	private Integer courierId;
	private Integer takeTimeId;
	private String[] subareaId;
	private List<Integer> customerIds;
	public void setTakeTimeId(Integer takeTimeId) {
		this.takeTimeId = takeTimeId;
	}
	public void setCourierId(Integer courierId) {
		this.courierId = courierId;
	}
	public void setFixedAreaId(String fixedAreaId) {
		this.fixedAreaId = fixedAreaId;
	}
	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}
	public void setSubareaId(String[] subareaId) {
		this.subareaId = subareaId;
	}
	
	/**
	 * 
	 * @Description: 元素保存
	 */
	@Action("fixedArea_save")
	public String save()  throws Exception {
		fixedAreaService.save(model,subareaId);
		return "operation";
	}
	
	/**
	 * 
	 * @Description: 分页查询
	 */
	@Action("fixedArea_pageQuery")
	public String pageQuery() throws Exception {
		Pageable pageRequest = new PageRequest(page-1, rows);
		Page<FixedArea> pageResult = fixedAreaService.pageQuery(pageRequest);
		map.put("total", fixedAreaService.findAll().size());
		map.put("rows", pageResult.getContent());
		return "pageQuery";
		
	}
	
	@Action("customer_findNotAssociation")
	public String findNotAssociation() throws Exception {
		list = customerService.findNotAssociation();
		return "jsonList";
	}
	
	@Action("customer_findAssociation")
	public String findAssociation() throws Exception {
		list = customerService.findAssociation(fixedAreaId);
		return "jsonList";
	}
	@Action("fixedArea_assignCustomers2FixedArea")
	public String assignCustomers2FixedArea() throws Exception {
		customerService.assignCustomers2FixedArea(model.getId(),customerIds );
		return "operation";
	}
	
	@Autowired
	private CourierService courierService;
	@Action("fixedArea_findCourierList")
	public String findCourierList() throws Exception {
		list = courierService.findByFixedAreaId(model.getId());
		return "jsonList";
	}
	
	@Action("fixedArea_associationCourierToFixedArea")
	public String associationCourierToFixedArea() throws Exception {
		fixedAreaService.associationCourierToFixedArea(model.getId(),courierId,takeTimeId);
		return "operation";
	}
}
