package zhangbin.cumt.service.take_delivery.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zhangbin.cumt.dao.base.AreaDao;
import zhangbin.cumt.dao.base.OrderDao;
import zhangbin.cumt.dao.take_delivery.WorkBillDao;
import zhangbin.cumt.domain.base.Area;
import zhangbin.cumt.domain.base.Courier;
import zhangbin.cumt.domain.base.FixedArea;
import zhangbin.cumt.domain.base.SubArea;
import zhangbin.cumt.domain.take_delivery.Order;
import zhangbin.cumt.domain.take_delivery.WorkBill;
import zhangbin.cumt.service.take_delivery.OrderService;
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private WorkBillDao workBillDao;
	@Autowired
	private AreaDao areaDao;
	@Override
	public void save(Order order) {
		//这儿直接用策略二，策略一太不靠谱
		//对sendArea和recArea进行持久化
		Area sendArea = order.getSendArea();
		sendArea = areaDao.findByProvinceAndCityAndDistrict(sendArea.getProvince(),sendArea.getCity(),sendArea.getDistrict());
		
		Area recArea = order.getRecArea();
		recArea = areaDao.findByProvinceAndCityAndDistrict(recArea.getProvince(),recArea.getCity(),recArea.getDistrict());
		
		order.setSendArea(sendArea);
		order.setRecArea(recArea);
		
		order.setOrderTime(new Date());
		//先设置为自动分单，如果是人工分单，再修改这个属性值
		order.setOrderType("自动分单");
		orderDao.save(order);
		
		///自动给快递员进行分单，即生成快递员的工单，保存到快递员工单对应的数据库中，
		
		//首先利用订单中的寄件人订单中省市区的信息(查找出来的sendarea已经含有相关分区的信息),去查找对应的区域，然后就可以找到该区域下面的所有分区，
		Set<SubArea> subAreas = sendArea.getSubareas();
		for (SubArea subArea : subAreas) {
			//遍历分区，找到匹配的分区
			String assistKeyWord = subArea.getAssistKeyWords();
			String keyWords = subArea.getKeyWords();
			if(order.getSendAddress().contains(keyWords) || order.getSendAddress().contains(assistKeyWord)) {
				//根据匹配的分区找到 对应的定区信息，根据定区
				FixedArea fixedArea = subArea.getFixedArea();
				if(fixedArea == null) {
					//如果查找不到对应的定区，则为人工分单
					order.setOrderType("人工分单");
					return;
				}
				
				//根据fixedArea找到对应的快递员
				Set<Courier> couriers = fixedArea.getCouriers();
				for (Courier courier : couriers) {
					//遍历该分区内的所有快递员，并匹配合适时间段的快递员(这儿仅考虑正常的工作日)
					String startTime = courier.getTakeTime().getNormalWorkTime();
					String endTime = courier.getTakeTime().getNormalDutyTime();
					Date nowDate = order.getOrderTime();
					 //利用快递员的上班时间去匹配取件时间，为符合条件的快递员自动生成 工单
					boolean effectiveDate = isEffectiveDate(nowDate, startTime, endTime);
					//表示时间点合适的快递员
					if(effectiveDate) {
						WorkBill workBill = generateWorkBill(order);
						workBill.setCourier(courier);						
						//把自动生成的工单存入对应的数据库中
						workBillDao.save(workBill);
						
						//下面为短信通知快递员的模块
						//同时通过短信的方式提醒快递员    员工${name},您有新的快递工单，用户电话为${telephone}，取件地址为${address}
						String signName = "腰缠万贯下扬州";
						String templateCode = "SMS_133155188";
						Map<String, Object> templateParam = new HashMap<>();
						templateParam.put("name", courier.getName());
						templateParam.put("telephone", order.getSendMobile());
						templateParam.put("address", order.getSendAddress());
						//发短信代码，
						//AliSMSUtil.sendMessge(courier.getTelephone(), signName, templateCode, templateParam);
					}
				}
					
			}else {
				order.setOrderType("人工分单");
			}
		}
		
		//如果自动指派单件不成功，则人工指派单件
		
	}
	
	/**
	 * 根据订单信息生成对应的工单
	 * @Description: TODO
	 * @param order
	 * @return
	 */
	private WorkBill generateWorkBill(Order order) {
		//符合生成工单
				WorkBill workBill = new WorkBill();
				workBill.setType("新单");
				workBill.setPickstate("待取件");
				Date date = new Date();
				workBill.setBuildtime(date);
				//追单次数，第一次为0，先设置为0
				workBill.setAttachbilltimes(0);
				workBill.setRemark(order.getRemark());
				//设置短信序号
				workBill.setSmsNumber(order.getTelephone());
				
				workBill.setOrder(order);
				return workBill;
	}



	/**
	 * 
	 * @Description: 判断时间点是否在某个时间段中
	 * @param nowDate
	 * @param startTime
	 * @param endTime
	 * @return true or false
	 */
	public static boolean isEffectiveDate(Date nowDate, String startTime, String endTime) {
		
		try {
			String format = "HH:mm:ss";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			String nowTime = simpleDateFormat.format(nowDate);
			nowDate = simpleDateFormat.parse(nowTime);
			Date startDate = simpleDateFormat.parse(startTime);
			Date endDate = simpleDateFormat.parse(endTime);
			
					
			if (nowDate.getTime() == startDate.getTime()
			        || nowDate.getTime() == endDate.getTime()) {
			    return true;
			}

			Calendar date = Calendar.getInstance();
			date.setTime(nowDate);

			Calendar begin = Calendar.getInstance();
			begin.setTime(startDate);

			Calendar end = Calendar.getInstance();
			end.setTime(endDate);

			if (date.after(begin) && date.before(end)) {
			    return true;
			} else {
			    return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
    }
	
	/**
	 * 查找需要手动进行分单的订单
	 */
	@Override
	public PageImpl<Order> pageQuery(PageRequest pageRequest) {

		Specification<Order> specification =new Specification<Order>() {

			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<Object> path = root.get("orderType");
				Predicate predicate = cb.equal(path.as(String.class), "人工分单");
				return predicate;
			}
			
		};
		
		return (PageImpl<Order>) orderDao.findAll(specification, pageRequest);
	}


	/**
	 * 手动进行分单-查找匹配的快递员
	 */
	@Override
	public ArrayList<Courier> assignCourierByManual(Order order,Area newSendArea) {
		
		//根据订单编号找到对应的订单，同时更新sendAdress
		Order oldOrder = orderDao.findOne(order.getId());
		oldOrder.setSendAddress(order.getSendAddress());
		//查找持久态的区域
		newSendArea = areaDao.findByProvinceAndCityAndDistrict(newSendArea.getProvince(), newSendArea.getCity(), newSendArea.getDistrict());
		//说明区域数据输入错误，导致查找失败
		if(newSendArea == null)
			return null;
		//持久太关联持久态，修改订单中对应的区域数据，因为区域数据中包含定区及其他需要的数据，否则会空指针
		oldOrder.setSendArea(newSendArea);
		
		//定义一个集合用于储存匹配的快递员
		ArrayList<Courier> list = new ArrayList<>();
		//找到新的区域中的分区
		Set<SubArea> subareas = newSendArea.getSubareas();
		for (SubArea subArea : subareas) {
			//如果寄件详细地址包括 subArea的关键字 或 辅助关键字
			if(order.getSendAddress().contains(subArea.getKeyWords()) || order.getSendAddress().contains(subArea.getAssistKeyWords())) {
				FixedArea fixedArea = subArea.getFixedArea();
				if(fixedArea == null) {
					return null;
				}
				Set<Courier> couriers = fixedArea.getCouriers();
				for (Courier courier : couriers) {
					String startTime = courier.getTakeTime().getNormalWorkTime();
					String endTime = courier.getTakeTime().getNormalDutyTime();
					//利用现在的时刻区匹配合适的快递员
					Date nowDate = new Date();
					 //利用快递员的上班时间去匹配取件时间，为符合条件的快递员自动生成 工单
					boolean effectiveDate = isEffectiveDate(nowDate, startTime, endTime);
					//表示时间点合适的快递员
					if(effectiveDate) {
						list.add(courier);
					}
				}
				//匹配合适的快递员，返回结果
				return list;
			}
		}
		return null;
	}

	@Override
	public void updateByManual(Order model) {
		Order order = orderDao.findOne(model.getId());
		order.setOrderType("人工分单已经完成");
		order.setCourier(model.getCourier());
		orderDao.save(order);
		//生成对应的工单
		WorkBill workBill = generateWorkBill(order);
		workBill.setCourier(order.getCourier());
		workBillDao.save(workBill);
		//给对应的快递员发送短信进行通知
		
	}



	
}
