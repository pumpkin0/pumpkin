package zhangbin.cumt.service.system;

import java.util.List;

import zhangbin.cumt.domain.system.Role;

public interface RoleService {

	void save(Role model, Integer[] permissionIds, String menuIds);

	List<Role> findAll();

	Role findById(Integer id);

}
