package zhangbin.cumt.dao.system;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import zhangbin.cumt.domain.system.Menu;

public interface MenuDao extends JpaRepository<Menu, Integer> {
	List<Menu> findByParentMenuIsNull();
	/*select m.* from t_menu m 
	inner join t_role_menu rm on m.c_id = rm.c_menu_id
	inner join t_role r on r.c_id = rm.c_role_id
	inner join t_user_role ur on ur.c_role_id = r.c_id
	inner join t_user u on u.c_id = ur.c_user_id
	where u.c_id = 248;*/ 
	@Query("select m from Menu m inner join m.roles r inner join r.users u on u.id = ? ")
	List<Menu> showMenu(Integer id);
}
