package zhangbin.cumt.action.base;

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
import zhangbin.cumt.domain.base.SubArea;
import zhangbin.cumt.service.base.SubAreaService;
@Controller
@Namespace("/")
@ParentPackage("json-default")
@Scope("prototype")
@Results(value = { 
		@Result(name="findAll",params= {"root","list"},type="json"),
		@Result(name="pageQuery",params= {"root","map"},type="json"),
		@Result(name="save",location="/pages/base/sub_area.jsp"),
		@Result(name="delete",location="/pages/base/area.jsp"),
		@Result(name="importXls",params= {"root","result"},type="json"),
		@Result(name="pageQuery",params= {"root","map"},type="json"),
		@Result(name="findMeetNull",params= {"root","list"},type="json")
		
		
})
public class SubAreaAction extends BaseAction<SubArea> {
	@Autowired
	private SubAreaService subAreaService;
	
	
	@Action("subarea_save")
	public String save() throws Exception {
		subAreaService.save(model);
		
		return "save";
	}
	@Action("subarea_pageQuery")
	public String pageQuery() throws Exception {
		Pageable pageable = new PageRequest(page-1,rows);
		Page<SubArea> pageResult = subAreaService.pageQuery(pageable);
		map.put("total", pageResult.getNumber());
		map.put("rows", pageResult.getContent());
			
		return "pageQuery";
	}
	@Action("subarea_findMeetNull")
	public String findMeetNull() throws Exception {
		list = subAreaService.findFixedAreaNull();
		
		return "findMeetNull";
	}
	
	
}
