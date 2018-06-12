package zhangbin.cumt.bos_crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zhangbin.cumt.bos_crm.dao.CustomerDao;
import zhangbin.cumt.bos_crm.domain.Customer;


@Transactional
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;
	@Override
	public List<Customer> findAll(){
		return customerDao.findAll();
	}
	@Override
	public List<Customer> findNotAssociation() {
		return customerDao.findByFixedAreaIdIsNull();
	}
	@Override
	public List<Customer> findAssociation(String fixedAreaId) {
		return customerDao.findByFixedAreaId(fixedAreaId);
	}
	@Override
	public void assignCustomers2FixedArea(String fixedAreaId, Integer[] customerIds) {
		customerDao.cancleAssociation(fixedAreaId);
		if(customerIds != null) {
			for (Integer id : customerIds) {
				customerDao.resumeAssociation(fixedAreaId,id);
			}
		}
	}
	@Override
	public List<Customer> findByTelephone(String tel) {
		return customerDao.findByTelephone(tel);
	}
	@Override
	public List<Customer> findByEmail(String email) {
		return customerDao.findByEmail(email);
	}
	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}
	@Override
	public void activeCustomer(String tel) {
		customerDao.activeCustomer(tel);
	}
	@Override
	public Customer findByTelephoneAndPassword(String tel, String password) {
		return customerDao.findByTelephoneAndPassword(tel,password);
	}
}
