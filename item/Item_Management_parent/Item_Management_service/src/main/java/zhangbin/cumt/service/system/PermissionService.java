package zhangbin.cumt.service.system;

import java.util.List;

import zhangbin.cumt.domain.system.Permission;

public interface PermissionService {

	void save(Permission model);

	List<Permission> findAll();

}
