package zhangbin.cumt.dao.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import zhangbin.cumt.domain.system.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {
	@Query("from Role where id = ?")
	Role findById(Integer id);

}
