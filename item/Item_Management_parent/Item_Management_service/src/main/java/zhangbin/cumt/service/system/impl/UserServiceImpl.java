package zhangbin.cumt.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zhangbin.cumt.dao.system.RoleDao;
import zhangbin.cumt.dao.system.UserDao;
import zhangbin.cumt.domain.system.Role;
import zhangbin.cumt.domain.system.User;
import zhangbin.cumt.service.system.UserService;
import zhangbin.cumt.utils.MD5Utils;
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Override
	public void save(User user,Integer[] roleIds) {
		String password = user.getPassword();
		user.setPassword(MD5Utils.md5(password));
		user = userDao.save(user);
		if(roleIds == null || roleIds.length == 0) {
			return;
		}
		for (Integer roleId : roleIds) {
			Role role = roleDao.findOne(roleId);
			user.getRoles().add(role);
		}
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}
}
