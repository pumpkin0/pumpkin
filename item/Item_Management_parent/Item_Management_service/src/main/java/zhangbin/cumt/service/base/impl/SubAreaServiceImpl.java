package zhangbin.cumt.service.base.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zhangbin.cumt.dao.base.SubAreaDao;
import zhangbin.cumt.domain.base.SubArea;
import zhangbin.cumt.service.base.SubAreaService;
@Service
@Transactional
public class SubAreaServiceImpl implements SubAreaService {
	@Autowired
	private SubAreaDao subAreaDao;

	@Override
	public void save(SubArea model) {
		model.setId(UUID.randomUUID().toString());
		subAreaDao.save(model);
	}

	@Override
	public Page<SubArea> pageQuery(Pageable pageable) {
		
		return subAreaDao.findAll(pageable);
	}

	@Override
	public List<SubArea> findFixedAreaNull() {
		return subAreaDao.findByFixedAreaNull();
	}
	/**
	 * 在这儿进行模糊查询，根据发送信息，找到对应的 subArea
	 */
	@Override
	public SubArea findBySendAddress(String sendAddress) {
		
		return subAreaDao.findBySendAddress(sendAddress);
	}
}
