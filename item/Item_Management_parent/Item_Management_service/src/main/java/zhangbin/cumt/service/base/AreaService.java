package zhangbin.cumt.service.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import zhangbin.cumt.domain.base.Area;
import zhangbin.cumt.domain.base.Courier;
import zhangbin.cumt.domain.take_delivery.Order;

public interface AreaService {

	void upload(ArrayList<Area> list);

	Page<Area> pageQuery(Pageable pageRequest);

	List<Area> findAll();

	void save(Area model);

	List<String> findAllProvince();

	List<String> findAllCityByProvince(String province);

	List<Area> findByProvinceAndCity(String province, String city);

	List<Area> findByInputContent(String inputContent);

}
