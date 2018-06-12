package zhangbin.cumt.action.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import freemarker.template.utility.Execute;
import zhangbin.cumt.action.parent.BaseAction;
import zhangbin.cumt.domain.system.Menu;
import zhangbin.cumt.domain.system.Permission;
import zhangbin.cumt.domain.system.Role;
import zhangbin.cumt.service.system.RoleService;
@Controller
@Namespace("/")
@ParentPackage("json-default")
@Scope("prototype")
@Results(value = { 
		@Result(name="list",params= {"root","list"},type="json"),
		@Result(name="map",params= {"root","map"},type="json"),
		@Result(name="findRoleById",params= {"root","role"},type="json"),
		@Result(name="save",type="redirect",location="/pages/system/role.jsp")
})
public class RoleAction extends BaseAction<Role> {
	@Autowired
	private RoleService roleService;
	private Integer[] permissionIds;
	private Role role;
	public Role getRole() {
		return role;
	}
	private String menuIds;
	public void setPermissionIds(Integer[] permissionIds) {
		this.permissionIds = permissionIds;
	}
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	@Action("roleAction_save")
	public String save() throws Exception {
		roleService.save(model,permissionIds,menuIds);
		return "save";
	}
	
	@Action("roleAction_findAll")
	public String findAll() throws Exception {
		list = roleService.findAll();
		return "list";
	}
	
	@Action("roleAction_findAllMenuAndPermissionBySelect")
	public String findAllMenuBySelect() throws Exception {
		Role role = roleService.findById(model.getId());
		Set<Menu> menus = role.getMenus();
		Set<Permission> permissions = role.getPermissions();
		map.put("role", role);
		map.put("menus", menus);
		map.put("permissions", permissions);
		return "map";
	}
	
	/**
	 * 这个方法主要把role的id给存放在了session中，方便在jsp中或后端代码中取出
	 */
	@Action("roleAction_passRoleId")
	public String passRoleId() throws Exception {
		ServletActionContext.getRequest().getSession().setAttribute("id", model.getId());
		return NONE;
	}
}
