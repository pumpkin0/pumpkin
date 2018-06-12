package zhangbin.cumt.service.base.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zhangbin.cumt.dao.base.CourierDao;
import zhangbin.cumt.domain.base.Courier;
import zhangbin.cumt.domain.base.Standard;
import zhangbin.cumt.service.base.CourierService;
@Service
@Transactional
public class CourierServiceImpl implements CourierService {
	@Autowired
	private CourierDao courierDao;
	@Override
	public void save(Courier courier) {
		courierDao.save(courier);
	}
	@Override
	public List<Courier> findAll() {
		return courierDao.findAll();
	}
	@Override
	public Page pageQuery(PageRequest pageRequest) {
		return courierDao.findAll(pageRequest);
	}
	//@RequiresPermissions("courier_pageQuery")
	@Override
	public Page pageQuery(Courier courier, PageRequest pageRequest) {
		String courierNum = courier.getCourierNum();
		Standard standard = courier.getStandard();
		String company = courier.getCompany();
		String type = courier.getType();
		ArrayList<Predicate> list = new ArrayList<>();
		
		@SuppressWarnings({ "unchecked" })
		Specification<Courier> specification = new Specification<Courier>(){

			@Override
			public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(StringUtils.isNoneBlank(courierNum)) {
					Path<Object> path = root.get("courierNum");
					Predicate predicate = cb.equal(path.as(String.class),courierNum);
					list.add(predicate);
				}
				if(StringUtils.isNoneBlank(company)) {
					 Predicate predicate = cb.like(root.get("company").as(String.class), "%"+company+"%");
					 list.add(predicate);
				}
				if(StringUtils.isNotBlank(type)) {
					Predicate predicate = cb.equal(root.get("type").as(String.class), type);
					list.add(predicate);
				}
				if(standard != null && StringUtils.isNoneBlank(standard.getName())) {
					Join<Object, Object> join = root.join("standard", JoinType.INNER);
					Predicate predicate = cb.equal(join.get("name").as(String.class),standard.getName());
				}
								
				if(list.size()==0) {
					return null;
				}
				Predicate[] restrictions= new Predicate[list.size()];
				restrictions = list.toArray(restrictions);
				return cb.and(restrictions);
				
			}
		};
		return courierDao.findAll(specification, pageRequest);
	}
	@RequiresPermissions("courier_delete")
	@Override
	public void delete(String ids) {
		String[] strings = ids.split(",");
		for (String id : strings) {
			courierDao.logicDelete(Integer.parseInt(id));

		}
	}
	@Override
	public List<?> findByFixedAreaId(String fixedAreaId) {
		return courierDao.findMeetConditionCourier(fixedAreaId);
	}
	

}
