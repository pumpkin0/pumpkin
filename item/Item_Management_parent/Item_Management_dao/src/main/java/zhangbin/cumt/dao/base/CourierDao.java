package zhangbin.cumt.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import zhangbin.cumt.domain.base.Courier;

public interface CourierDao extends JpaRepository<Courier, Integer> ,JpaSpecificationExecutor<Courier>{
	@Modifying
	@Query("update Courier set deltag = 1 where id = ?")
	void logicDelete(int id);
	
	/*select * from t_courier c where c.c_id not in(
			select c.c_id from t_courier c, t_fixedarea_courier fc, T_FIXED_AREA f where c.c_id = fc.c_courier_id and
			f.c_id = fc.c_fixed_area_id) and c.c_deltag != '1';*/
	
	@Modifying
	@Query("from Courier c where c.id not in (select c.id from Courier c inner join c.fixedAreas f on f.id = ? ) and c.deltag != '1'")
	List<?> findMeetConditionCourier(String fixedAreaId);

}
