package zhangbin.cumt.service.base.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import zhangbin.cumt.dao.base.OrderDao;
import zhangbin.cumt.domain.take_delivery.Order;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class OderServiceImplTest {
	@Autowired
	private OrderDao orderDao;
	@Test
	public void test() {
		List<Order> list = orderDao.findAll();
		System.out.println(list.size());
	}
	@Test
	public void test2() {
		Order order = new Order();
		order.setId(10033);
		orderDao.save(order);
	}

}
