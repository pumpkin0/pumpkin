package zhangbin.cumt.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import zhangbin.cumt.domain.base.Area;

public interface AreaDao extends JpaRepository<Area, String>,JpaSpecificationExecutor<Area> {

	Area findByProvinceAndCityAndDistrict(String province, String city, String district);
	
	@Query(" select distinct province  from Area ")
	List<String> findAllProvince();
	
	@Query("select distinct city from Area where province = ?")
	List<String> findAllCityByProvince(String province);

	List<Area> findByProvinceAndCity(String province, String city);
	
}
