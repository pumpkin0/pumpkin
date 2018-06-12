package zhangbin.cumt.action.base;

import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import zhangbin.cumt.action.parent.BaseAction;
import zhangbin.cumt.domain.base.Standard;
import zhangbin.cumt.service.base.StandardService;
@Controller
@Namespace("/")
@ParentPackage("json-default")
@Results(value = { 
		@Result(name="findAll",params= {"root","list"},type="json"),
		@Result(name="pageQuery",params= {"root","map"},type="json"),
		@Result(name="save", location="/pages/base/standard.jsp")
		
})
@Scope("prototype")
public class StandardAction extends BaseAction<Standard> {
	@Autowired
	private StandardService standardService ;
	
	@Action("standard_findAll")
	public String findAll() {
		list = standardService.findAll();
		return "findAll";
	}
	
	@Action("standard_save")
	public String save() {
		standardService.save(model);
		return "save";
	}
	@Action("standard_pageQuery")
	public String pageQuery() {
		Page pageResult = standardService.pageQuery(new PageRequest(page-1, rows));
		map = new HashMap<>();
		map.put("total", pageResult.getNumber());
		map.put("rows", pageResult.getContent());
		return "pageQuery";
	}
	

}
