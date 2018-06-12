package zhangbin.cumt.bos_crm.service;

import java.util.List;

import javax.jws.WebService;

import zhangbin.cumt.bos_crm.domain.Customer;
@WebService
public interface CustomerService {
	List<Customer> findAll();
	List<Customer> findNotAssociation();
	List <Customer> findAssociation(String fixedAreaId);
	void assignCustomers2FixedArea(String id,Integer[] customerIds);
	List<Customer> findByTelephone(String tel);
	List<Customer> findByEmail(String email);
	void save(Customer customer);
	void activeCustomer(String tel);
	Customer findByTelephoneAndPassword(String tel,String password);
}
