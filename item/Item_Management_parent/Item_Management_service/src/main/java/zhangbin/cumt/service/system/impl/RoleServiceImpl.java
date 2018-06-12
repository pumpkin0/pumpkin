package zhangbin.cumt.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zhangbin.cumt.dao.system.MenuDao;
import zhangbin.cumt.dao.system.PermissionDao;
import zhangbin.cumt.dao.system.RoleDao;
import zhangbin.cumt.domain.system.Menu;
import zhangbin.cumt.domain.system.Permission;
import zhangbin.cumt.domain.system.Role;
import zhangbin.cumt.service.system.RoleService;
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private MenuDao menuDao;
	@Override
	public void save(Role model, Integer[] permissionIds, String menuIds) {
		model = roleDao.save(model);
		//权限的添加
		if(permissionIds != null && permissionIds.length > 0) {
			for (Integer permissionId : permissionIds) {
				Permission permission = permissionDao.findOne(permissionId);
				model.getPermissions().add(permission);
			}
		}
		if(menuIds == null) {
			return;
		}
		String[] gatherMenuId = menuIds.split(",");
		for (String menuIdStr : gatherMenuId) {
			Menu menu = menuDao.findOne(Integer.parseInt(menuIdStr));
			model.getMenus().add(menu);
		}
	}
	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}
	@Override
	public Role findById(Integer id) {
		return roleDao.findById(id);
	}
}
