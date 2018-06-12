package zhangbin.cumt.dao.system;

import org.springframework.data.jpa.repository.JpaRepository;

import zhangbin.cumt.domain.system.Permission;

public interface PermissionDao extends JpaRepository<Permission, Integer> {

}
