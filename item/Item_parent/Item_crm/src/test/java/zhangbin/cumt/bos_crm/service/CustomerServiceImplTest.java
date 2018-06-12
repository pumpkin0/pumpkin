package zhangbin.cumt.bos_crm.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import zhangbin.cumt.bos_crm.domain.Customer;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CustomerServiceImplTest {
	
	@Autowired
	private CustomerService customerService;
	@Test
	public void test() {
		List<Customer> list = customerService.findAll();
		System.out.println(list.size());
	}
	@Test
	public void testfindNotAssociation() {
		List<Customer> list = customerService.findNotAssociation();
		System.out.println(list.size());
	}
	@Test
	public void testfindAssociation() {
		List<Customer> list = customerService.findAssociation("001");
		System.out.println(list.size());
	}
	@Test
	public void testassignCustomers2FixedArea() {
		customerService.assignCustomers2FixedArea("001", new Integer[] {2,3});
	}
	@Test
	public void testfindByTelephone() {
		List<Customer> list = customerService.findByTelephone("13112345678");
		System.out.println(list.get(0));
	}
	@Test
	public void testfindByTelephoneAndPassword() {
		Customer customer = customerService.findByTelephoneAndPassword("13522274016","123");
		System.out.println(customer);
	}

}
