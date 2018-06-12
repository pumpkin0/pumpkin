package zhangbin.cumt.action.base;

import java.util.HashMap;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

import zhangbin.cumt.action.parent.BaseAction;
import zhangbin.cumt.domain.base.Courier;
import zhangbin.cumt.service.base.CourierService;
@Controller
@Namespace("/")
@ParentPackage("json-default")
@Scope("prototype")
@Results(value = { 
		@Result(name="findAll",params= {"root","list"},type="json"),
		@Result(name="pageQuery",params= {"root","map"},type="json"),
		@Result(name="save",location="/pages/base/courier.jsp"),
		@Result(name="delete",location="/pages/base/courier.jsp"),
		@Result(name="unauthorized",location="/unauthorized.jsp")
		
})
@ExceptionMappings({ @ExceptionMapping(exception = "org.apache.shiro.authz.UnauthorizedException", result = "unauthorized") })
public class CourierAction extends BaseAction<Courier> {
	@Autowired
	private CourierService courierService;
	
	
	private String ids;
	public void setIds(String ids) {
		this.ids = ids;
	}
		
	@Action("courier_save")
	public String save() {
		courierService.save(model);
		return "save";
	}
	@Action("courier_findAll")
	public String findAll() {
		list = courierService.findAll();
		return "findAll";
	}
	@Action("courier_pageQuery")
	public String pageQuery() throws Exception {
		PageRequest pageRequest = new PageRequest(page-1, rows);
		Page<Courier> pageResult = courierService.pageQuery(model,pageRequest);
		
		//下面这行代码是直接进行分页查询，没有条件筛选
		//Page pageResult = courierService.pageQuery(new PageRequest(page-1, rows));
		map = new HashMap<>();
		map.put("total", pageResult.getNumber());
		map.put("rows", pageResult.getContent());
		return "pageQuery";
	}
	
	@Action("courier_delete")
	public String delete() throws Exception {
		courierService.delete(ids);
		return "delete";
	}
}
