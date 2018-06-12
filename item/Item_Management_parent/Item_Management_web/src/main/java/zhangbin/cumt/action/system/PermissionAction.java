package zhangbin.cumt.action.system;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import zhangbin.cumt.action.parent.BaseAction;
import zhangbin.cumt.domain.system.Permission;
import zhangbin.cumt.service.system.PermissionService;
@Controller
@Namespace("/")
@ParentPackage("json-default")
@Scope("prototype")
@Results(value = { 
		@Result(name="list",params= {"root","list"},type="json"),
		@Result(name="save",type="redirect",location="/pages/system/permission.jsp")
})
public class PermissionAction extends BaseAction<Permission> {
	@Autowired
	private PermissionService permissionService;
	
	@Action("permissionAction_save")
	public String execute() throws Exception {
		permissionService.save(model);
		return "save";
	}
	
	@Action("permissionAction_findAll")
	public String findAll() throws Exception {
		list = permissionService.findAll();
		return "list";
	}
	
}
