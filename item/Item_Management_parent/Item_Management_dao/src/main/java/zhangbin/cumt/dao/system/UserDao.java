package zhangbin.cumt.dao.system;

import org.springframework.data.jpa.repository.JpaRepository;

import zhangbin.cumt.domain.system.User;

public interface UserDao extends JpaRepository<User, Integer> {

	User findByUsername(String username);

}
