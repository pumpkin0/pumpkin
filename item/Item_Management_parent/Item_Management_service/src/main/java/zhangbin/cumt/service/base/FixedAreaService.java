package zhangbin.cumt.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import zhangbin.cumt.domain.base.FixedArea;

public interface FixedAreaService {

	void save(FixedArea model, String[] subareaId);

	List<FixedArea> findAll();

	Page<FixedArea> pageQuery(Pageable pageRequest);

	void associationCourierToFixedArea(String id, Integer courierId, Integer takeTimeId);

}
