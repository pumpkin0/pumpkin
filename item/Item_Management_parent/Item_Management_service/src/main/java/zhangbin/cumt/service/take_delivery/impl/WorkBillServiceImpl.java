package zhangbin.cumt.service.take_delivery.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zhangbin.cumt.dao.take_delivery.WorkBillDao;
import zhangbin.cumt.domain.take_delivery.WorkBill;
import zhangbin.cumt.service.take_delivery.WorkBillService;
@Service
@Transactional
public class WorkBillServiceImpl implements WorkBillService {
	@Autowired
	private WorkBillDao workBillDao;

	@Override
	public void save(WorkBill workBill) {
		workBillDao.save(workBill);
	}
	
}
