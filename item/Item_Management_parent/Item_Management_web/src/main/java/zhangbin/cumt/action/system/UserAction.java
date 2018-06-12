package zhangbin.cumt.action.system;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import zhangbin.cumt.action.parent.BaseAction;
import zhangbin.cumt.domain.system.User;
import zhangbin.cumt.service.system.UserService;
import zhangbin.cumt.utils.MD5Utils;
@Controller
@Namespace("/")
@ParentPackage("json-default")
@Scope("prototype")
@Results(value = { 
		@Result(name="login",location="/login.jsp"),
		@Result(name="index",type="redirect", location="/index.jsp"),
		@Result(name="save",type="redirect", location="/pages/system/user.jsp"),
		@Result(name="loginState",params= {"root","map"},type="json"),
		@Result(name="findAll",params= {"root","list"},type="json")
		
		
})
public class UserAction extends BaseAction<User> {
	@Autowired
	private UserService userService;
	private Integer[] roleIds;
	private String checkCode;
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	public void setRoleIds(Integer[] roleIds) {
		this.roleIds = roleIds;
	}
	@Action("userAction_login")
	public String login() throws Exception {
		String realCheckCode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if(StringUtils.isNoneBlank(realCheckCode)) {
			if(realCheckCode .equals(checkCode)) {
				Subject subject = SecurityUtils.getSubject();
				
				if(!subject.isAuthenticated()) {
					AuthenticationToken token = new UsernamePasswordToken(model.getUsername(), MD5Utils.md5(model.getPassword()));
					try {
						subject.login(token);
						//认证通过，否则会抛出异常
						User user = (User) subject.getPrincipal();
						ServletActionContext.getRequest().getSession().setAttribute("user", user);
						return "index";
					} catch (Exception e) {
						if(e instanceof UnknownAccountException) {
							this.addActionError("输入账户有误");
						}
						if(e instanceof IncorrectCredentialsException) {
							this.addActionError("输入密码错误");
						}
					}
				}else {
					return "index";
				}
			}
		}
		return "login";
	}
	
	@Action("userAction_loginState")
	public String loginState() throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user != null) {
			flag = true;
			map.put("flag",flag);
			map.put("user", user.getUsername());
		}else {
			flag =false;
			map.put("flag", flag);
		}
		return "loginState";
	}
	
	
	@Action("userAction_logout")
	public String logout() throws Exception {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "login";
	}
	
	@Action("userAction_save")
	public String save() throws Exception {
		userService.save(model,roleIds);
		return "save";
	}
	
	@Action("userAction_findAll")
	public String findAll() throws Exception {
		list = userService.findAll();
		return "findAll";
	}
	
	
	
}
