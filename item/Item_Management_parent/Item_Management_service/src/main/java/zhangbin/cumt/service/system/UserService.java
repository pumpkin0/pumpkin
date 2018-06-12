package zhangbin.cumt.service.system;

import java.util.List;

import zhangbin.cumt.domain.system.User;

public interface UserService {

	void save(User model, Integer[] roleIds);

	List<User> findAll();

}
