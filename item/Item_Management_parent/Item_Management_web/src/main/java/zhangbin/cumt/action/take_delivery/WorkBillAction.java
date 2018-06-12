package zhangbin.cumt.action.take_delivery;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import zhangbin.cumt.action.parent.BaseAction;
import zhangbin.cumt.domain.take_delivery.WayBill;
import zhangbin.cumt.domain.take_delivery.WorkBill;
import zhangbin.cumt.service.take_delivery.WorkBillService;
@Controller
@Namespace("/")
@ParentPackage("json-default")
@Scope("prototype")
@Results(value = { 
		@Result(name="save",params= {"root","result"},type="json")
		/*@Result(name="findAll",params= {"root","list"},type="json"),
		@Result(name="pageQuery",params= {"root","map"},type="json"),
		@Result(name="save",location="/pages/base/courier.jsp"),
		@Result(name="delete",location="/pages/base/courier.jsp")*/
		
})
public class WorkBillAction extends BaseAction<WorkBill> {
	@Autowired
	private WorkBillService workBillService;
	@Action("wayBillAction_save")
	public String execute() throws Exception {
	
		return "save";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
