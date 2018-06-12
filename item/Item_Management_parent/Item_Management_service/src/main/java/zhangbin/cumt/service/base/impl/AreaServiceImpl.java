package zhangbin.cumt.service.base.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import zhangbin.cumt.dao.base.AreaDao;
import zhangbin.cumt.domain.base.Area;
import zhangbin.cumt.service.base.AreaService;
@Service
@Transactional
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaDao areaDao;
	@Override
	public void upload(ArrayList<Area> list) {
		areaDao.save(list);
	}
	@Override
	public Page<Area> pageQuery(Pageable pageRequest) {
		return areaDao.findAll(pageRequest);
	}
	@Override
	public List<Area> findAll() {
		return areaDao.findAll();
		
	}
	@Override
	public void save(Area model) {
		
		areaDao.save(model);
	}
	@Override
	public List<String> findAllProvince() {
		return areaDao.findAllProvince();
	}
	@Override
	public List<String> findAllCityByProvince(String province) {
		return areaDao.findAllCityByProvince(province);
	}
	@Override
	public List<Area> findByProvinceAndCity(String province, String city) {
		
		return areaDao.findByProvinceAndCity(province,city);
	}
	@Override
	public List<Area> findByInputContent(String inputContent) {
		
		Specification<Area> specification = new Specification<Area>() {

			@Override
			public Predicate toPredicate(Root<Area> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(StringUtils.isNotBlank(inputContent)) {
					Path<Object> path = root.get("shortcode");
					Predicate predicate = cb.like(path.as(String.class),inputContent+"%");
					return predicate;
				}
				return null;
			}
		};
		return areaDao.findAll(specification);
	
			
	}

}
