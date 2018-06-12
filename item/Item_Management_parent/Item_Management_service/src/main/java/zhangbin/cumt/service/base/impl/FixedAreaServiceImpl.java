package zhangbin.cumt.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zhangbin.cumt.dao.base.CourierDao;
import zhangbin.cumt.dao.base.FixedAreaDao;
import zhangbin.cumt.dao.base.SubAreaDao;
import zhangbin.cumt.domain.base.Courier;
import zhangbin.cumt.domain.base.FixedArea;
import zhangbin.cumt.domain.base.SubArea;
import zhangbin.cumt.domain.base.TakeTime;
import zhangbin.cumt.service.base.FixedAreaService;
@Service
@Transactional
public class FixedAreaServiceImpl implements FixedAreaService {
	@Autowired
	private FixedAreaDao fixedAreaDao;
	@Autowired
	private SubAreaDao subAreaDao;
	@Autowired
	private CourierDao courierDao;
	@Override
	public List<FixedArea> findAll() {
		return fixedAreaDao.findAll();
	}

	@Override
	public Page<FixedArea> pageQuery(Pageable pageRequest) {
		
		return fixedAreaDao.findAll(pageRequest);
	}

	@Override
	public void save(FixedArea model, String[] subareaId) {
		model = fixedAreaDao.save(model);
		for (String id : subareaId) {
			
			SubArea subArea = subAreaDao.findOne(id);
			subArea.setFixedArea(model);
			subAreaDao.save(subArea);
		}
	}

	

	@Override
	public void associationCourierToFixedArea(String id, Integer courierId, Integer takeTimeId) {
		FixedArea fixedArea = fixedAreaDao.findOne(id);
		Courier courier = courierDao.findOne(courierId);
		TakeTime takeTime = new TakeTime();
		takeTime.setId(takeTimeId);
		//持久态关联托管态
		courier.setTakeTime(takeTime);
		//持久态关联持久态
		fixedArea.getCouriers().add(courier);
	}
	
	
	
}
