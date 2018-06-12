package zhangbin.cumt.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zhangbin.cumt.dao.system.PermissionDao;
import zhangbin.cumt.domain.system.Permission;
import zhangbin.cumt.service.system.PermissionService;
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionDao permissionDao;
	@Override
	public void save(Permission model) {
		permissionDao.save(model);
	}

	@Override
	public List<Permission> findAll() {
		return permissionDao.findAll();
	}

}
