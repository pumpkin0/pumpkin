package zhangbin.cumt.service.base.impl;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import zhangbin.cumt.service.base.CourierService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CourierServiceImplTest {
	@Autowired
	private CourierService courierService;
	@Test
	public void test() {
		List<?> list = courierService.findByFixedAreaId("001");
		Iterator<?> iterator = list.iterator();
		
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

}
