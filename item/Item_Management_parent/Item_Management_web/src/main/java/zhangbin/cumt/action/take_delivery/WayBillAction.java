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
import zhangbin.cumt.service.take_delivery.WayBillService;
@Controller
@Namespace("/")
@ParentPackage("json-default")
@Scope("prototype")
@Results(value = { 
		@Result(name="save",params= {"root","result"},type="json")
		
		
})
public class WayBillAction extends BaseAction<WayBill> {
	@Autowired
	private WayBillService wayBillService;
	
	@Action("wayBillAction_save")
	public String save() throws Exception {
		try {
			wayBillService.save(model);
			result = 1;
		} catch (Exception e) {
			result = 0;
		}
		return "save";
	}
}
