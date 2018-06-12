package zhangbin.cumt.bos_crm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import zhangbin.cumt.bos_crm.domain.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer> {

	List<Customer> findByFixedAreaIdIsNull();

	List<Customer> findByFixedAreaId(String fixedAreaId);
	
	@Query("update Customer c set c.fixedAreaId = null where c.fixedAreaId = ?")
	@Modifying
	void cancleAssociation(String fixedAreaId);
	
	@Query("update Customer c set c.fixedAreaId = ? where c.id = ?")
	@Modifying
	void resumeAssociation(String fixedAreaId, Integer id);

	List<Customer> findByTelephone(String tel);

	List<Customer> findByEmail(String email);
	
	@Query("update Customer c set c.type = 1 where telephone = ?")
	@Modifying
	void activeCustomer(String tel);
	@Query("")
	Customer findByTelephoneAndPassword(String tel, String password);
	

	
	
}
