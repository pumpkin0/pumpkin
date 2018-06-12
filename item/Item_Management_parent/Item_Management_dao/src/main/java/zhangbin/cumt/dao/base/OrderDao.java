package zhangbin.cumt.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import zhangbin.cumt.domain.take_delivery.Order;

public interface OrderDao extends JpaRepository<Order, Integer>,JpaSpecificationExecutor<Order> {
	
	
}
