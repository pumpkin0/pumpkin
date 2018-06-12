package zhangbin.cumt.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zhangbin.cumt.dao.system.MenuDao;
import zhangbin.cumt.domain.system.Menu;
import zhangbin.cumt.service.system.MenuService;
@Service
@Transactional
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menuDao;
	@Override
	public List<Menu> findByParentMenuIsNull() {
		return menuDao.findByParentMenuIsNull();
	}
	@Override
	public void save(Menu model) {
		Menu parentMenu = model.getParentMenu();
		if(parentMenu != null && parentMenu.getId() == null) {
			model.setParentMenu(null);
		}
		menuDao.save(model);
	}
	@Override
	public List<Menu> findAll() {
		return menuDao.findAll();
	}
	@Override
	public List<Menu> showMenu(Integer id) {
		return menuDao.showMenu(id);
		
	}

}
