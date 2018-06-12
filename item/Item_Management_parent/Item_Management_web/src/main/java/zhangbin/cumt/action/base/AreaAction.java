package zhangbin.cumt.action.base;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import zhangbin.cumt.action.parent.BaseAction;
import zhangbin.cumt.bos_crm.service.CustomerService;
import zhangbin.cumt.domain.base.Area;
import zhangbin.cumt.service.base.AreaService;
import zhangbin.cumt.utils.PinYin4jUtils;
@Controller
@Namespace("/")
@ParentPackage("json-default")
@Scope("prototype")
@Results(value = { 
		@Result(name="list",params= {"root","list"},type="json"),
		@Result(name="pageQuery",params= {"root","map"},type="json"),
		@Result(name="save",location="/pages/base/area.jsp"),
		@Result(name="delete",location="/pages/base/area.jsp"),
		@Result(name="importXls",params= {"root","result"},type="json"),
		@Result(name="pageQuery",params= {"root","map"},type="json")
		
		
})
public class AreaAction extends BaseAction<Area> {
	@Autowired
	private AreaService areaService;
	@Autowired
	private CustomerService customerProxy;
	private String inputContent;
	public void setInputContent(String inputContent) {
		this.inputContent = inputContent;
	}

	/**
	 * 
	 * @Description: 实现exc文件的上传功能
	 */
	@Action("area_importXls")
	public String importXls()  {
		try {
			ArrayList<Area> list = new ArrayList<>();
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(upload));
			HSSFSheet sheet = workbook.getSheetAt(0);
			for (Row row : sheet) {
				if(row.getRowNum() == 0)
					continue;
				String id = row.getCell(0).getStringCellValue();
				String province = row.getCell(1).getStringCellValue();
				String city = row.getCell(2).getStringCellValue();
				String district = row.getCell(3).getStringCellValue();
				String postcode = row.getCell(4).getStringCellValue();
				Area newArea = new Area(id,province,city,district,postcode,null,null);
				//get its shortcode and citycode;
				province = province.substring(0, province.length()-1);
				city = city.substring(0, city.length()-1);
				district = district.substring(0, district.length()-1);
				String info = province + city + district;
				String[] scArray = PinYin4jUtils.getHeadByString(info);
				String shortcode = PinYin4jUtils.stringArrayToString(scArray);
				String citycode = PinYin4jUtils.hanziToPinyin(city, "");
				newArea.setShortcode(shortcode);
				newArea.setCitycode(citycode);
				list.add(newArea);
				
			}
			areaService.upload(list);
						
			workbook.close();
			result = 0;
		} catch (Exception e) {
			result = 1;
		} 
		return "importXls";
	}
	
	/**
	 * 
	 * @Description: 进行区域的分页查询
	 */
	@Action("area_pageQuery")
	public String pageQuery() throws Exception {
		Pageable pageRequest = new PageRequest(page-1, rows);
		Page<Area> pageResult = areaService.pageQuery(pageRequest);
		map.put("total", areaService.findAll().size());
		map.put("rows", pageResult.getContent());
		return "pageQuery";
	}
	
	/**
	 * 
	 * @Description: 区域元素的保存
	 */
	@Action("area_save")
	public String save() {
		areaService.save(model);
		return "save";
	}
	
	/**
	 * 
	 * @Description: 查询所有的区域元素
	 */
	@Action("area_findAll")
	public String findAll() throws Exception {
		list = areaService.findAll();
		return "list";
	}
	
	@Action("area_findAllProvince")
	public String findAllProvince() throws Exception {
		List<String> provinceList = areaService.findAllProvince();
		ArrayList<Area> areaList = new ArrayList<>();
		for (String province : provinceList) {
			Area area = new Area();
			area.setProvince(province);
			areaList.add(area);
			list = areaList;
		}
		return "list";
	}
	
	
	@Action("area_findAllCityByProvince")
	public String findAllCityByProvince() throws Exception {
		List<String> cityList = areaService.findAllCityByProvince(model.getProvince());
		ArrayList<Area> areaList = new ArrayList<>();
		for (String city : cityList) {
			Area area = new Area();
			area.setCity(city);
			areaList.add(area);
			list = areaList;
		}
		return "list";
	}
	
	@Action("area_findAllCityByProvinceAndCity")
	public String findAllCityByProvinceAndCity() throws Exception {
		list = areaService.findByProvinceAndCity(model.getProvince(),model.getCity());
		return "list";
	}
	
	@Action("area_findByInputContent")
	public String findByInputContent() throws Exception {
		inputContent = inputContent.toUpperCase();
		list = areaService.findByInputContent(inputContent);
		return "list";
	}
	
	
}
