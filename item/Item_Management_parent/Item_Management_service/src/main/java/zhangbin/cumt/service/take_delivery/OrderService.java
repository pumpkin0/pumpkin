package zhangbin.cumt.service.take_delivery;

import java.util.ArrayList;

import javax.jws.WebService;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import zhangbin.cumt.domain.base.Area;
import zhangbin.cumt.domain.base.Courier;
import zhangbin.cumt.domain.take_delivery.Order;

@WebService
public interface OrderService {
	void save(Order order);

	PageImpl<Order> pageQuery(PageRequest pageRequest);

	ArrayList<Courier> assignCourierByManual(Order model, Area area);

	void updateByManual(Order model);
	
}
