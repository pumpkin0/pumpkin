package zhangbin.cumt.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import zhangbin.cumt.domain.base.Standard;

public interface StandardService {

	List<Standard> findAll();

	void save(Standard standard);

	Page pageQuery(PageRequest pageRequest);


	

}
