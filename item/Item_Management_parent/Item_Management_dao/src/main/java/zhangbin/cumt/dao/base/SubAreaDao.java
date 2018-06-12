package zhangbin.cumt.dao.base;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import zhangbin.cumt.domain.base.SubArea;

public interface SubAreaDao extends JpaRepository<SubArea, String> {

	List<SubArea> findByFixedAreaNull();
	@Query("from SubArea where keyWords = ?")
	SubArea findBySendAddress(String sendAddress);


}
