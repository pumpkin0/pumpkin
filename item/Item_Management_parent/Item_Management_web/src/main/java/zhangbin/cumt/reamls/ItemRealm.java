package zhangbin.cumt.reamls;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import zhangbin.cumt.dao.system.PermissionDao;
import zhangbin.cumt.dao.system.RoleDao;
import zhangbin.cumt.dao.system.UserDao;
import zhangbin.cumt.domain.system.Permission;
import zhangbin.cumt.domain.system.Role;
import zhangbin.cumt.domain.system.User;
@Component("itemRealm")
public class ItemRealm extends AuthorizingRealm {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private PermissionDao permissionDao;
	/**
	 * 用户的授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		Collection<Role> roleCollection = null;
		Collection<Permission> permissionCollection = null;
		//获得当前得的用户
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		if(user.getUsername().equals("admin")) {
			roleCollection = roleDao.findAll();
		}else {
			roleCollection = user.getRoles();
			
		}
		//一般一个人基本具备一个角色，所以去重也没必要考虑，不是经常发生的事件
		for (Role role : roleCollection) {
			info.addRole(role.getKeyword());
			permissionCollection = role.getPermissions();
			for (Permission permission : permissionCollection) {
				info.addStringPermission(permission.getKeyword());
			}
		}
		return info;
	}
	
	/**
	 * 用户的验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String username = usernamePasswordToken.getUsername();
		User user = userDao.findByUsername(username);
		if(user == null) {
			return null;
		}
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(),this.getName());
		return info;
	}

}
