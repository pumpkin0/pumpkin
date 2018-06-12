package zhangbin.cumt.action.system;


import org.apache.shiro.SecurityUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import zhangbin.cumt.action.parent.BaseAction;
import zhangbin.cumt.domain.system.Menu;
import zhangbin.cumt.domain.system.User;
import zhangbin.cumt.service.system.MenuService;
@Controller
@Namespace("/")
@ParentPackage("json-default")
@Scope("prototype")
@Results(value = { 
		@Result(name="findAll",params= {"root","list"},type="json"),
		//@Result(name="findAllBySimple",params= {"root","list"},type="json"),
		
		//@Result(name = Action.SUCCESS, type = "json", params = { 
		//"excludeProperties","pageList\\.\\list[\\d+\\]\\.tbGrpInfo\\.(tbGrpEmps.*|tbGrpSets.*|tbGrpDepts.*){1}", //过滤的值(我用的是hibernate，这里是过滤集合中tbGrpInfo这个对象，但是tbGrpInfo个对象包含了tbGrpEmps,tbGrpSets,tbGrpDepts对象，因此都需要过滤,{1}这个表示只要有其中一个就过滤掉) 
		//"includeProperties", "success,msg,pageList.*"  //返回的值  excludeProperties","list\\[\\d+\\]\\.children",
		//}), 
		
		@Result(name="save",type="redirect",location="/pages/system/menu.jsp")
})
public class MenuAction extends BaseAction<Menu> {
	@Autowired
	private MenuService menuService;
	@Action("menuAction_findAll")
	public String findAll() throws Exception {
		list = menuService.findByParentMenuIsNull();
		return "findAll";
	}
	
	@Action("menuAction_save")
	public String save() throws Exception {
		menuService.save(model);
		return "save";
	}
	
	@Action("menuAction_findAllBySimple")
	public String findAllBySimple() throws Exception {
		list = menuService.findAll();
		java2Json(list, new String[] {"children","childrenMenus","parentMenu","roles"});
		return NONE;
	}
	
	@Action("menuAction_showMenu")
	public String showMenu() throws Exception {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		list = menuService.showMenu(user.getId());
		java2Json(list, new String[] {"children","childrenMenus","parentMenu","roles"});
		return NONE;
	}
}
