package zhangbin.cumt.service.system;

import java.util.List;

import zhangbin.cumt.domain.system.Menu;

public interface MenuService {

	List<Menu> findByParentMenuIsNull();

	void save(Menu model);

	List<Menu> findAll();

	List<Menu> showMenu(Integer id);

}
