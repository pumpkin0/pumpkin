package zhangbin.cumt.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import zhangbin.cumt.domain.base.SubArea;

public interface SubAreaService {

	void save(SubArea model);

	Page<SubArea> pageQuery(Pageable pageable);

	List<SubArea> findFixedAreaNull();

	SubArea findBySendAddress(String sendAddress);

}
