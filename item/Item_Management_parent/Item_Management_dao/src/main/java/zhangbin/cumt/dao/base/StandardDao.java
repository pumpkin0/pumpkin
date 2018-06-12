package zhangbin.cumt.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;

import zhangbin.cumt.domain.base.Standard;

public interface StandardDao extends JpaRepository<Standard, Integer> {
	
}
