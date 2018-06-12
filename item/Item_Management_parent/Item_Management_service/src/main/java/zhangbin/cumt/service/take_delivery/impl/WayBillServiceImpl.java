package zhangbin.cumt.service.take_delivery.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zhangbin.cumt.dao.take_delivery.WayBillDao;
import zhangbin.cumt.domain.take_delivery.WayBill;
import zhangbin.cumt.service.take_delivery.WayBillService;
@Service
public class WayBillServiceImpl implements WayBillService {
	@Autowired
	private WayBillDao wayBillDao;
	@Override
	public void save(WayBill model) {
		wayBillDao.save(model);
	}

}
