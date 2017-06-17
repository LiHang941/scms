package com.scms.sys.action.system;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.scms.bulletin.entity.Category;
import com.scms.bulletin.service.ICategoryService;
import com.scms.json.entity.JsonCurrency;
import com.scms.sys.entity.SysCategory;
import com.scms.sys.service.ISysCategoryService;
import com.scms.util.write.JsonWriterUtil;

/** 
 * @author  航
 * @version 创建时间：2017年2月12日 下午3:01:04 
 * 类说明 
 */
@Namespace("/manager/system/sysCategory")
@ParentPackage("default")
@InterceptorRefs(@InterceptorRef("sysModularCheckStack"))
@Scope("prototype")
@Controller
public class SysCategoryAction extends ActionSupport{
	private List<Category> categoryList ;
	private SysCategory sysCategory;
	@Resource
	private ISysCategoryService sysCategoryService;
	@Resource
	private ICategoryService categoryService;
	
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/jsp/System/manager/sysCategory/update.jsp") })
	public String listUI (){
		categoryList = categoryService.getAll();
		sysCategory = sysCategoryService.getSysCategory();
		return SUCCESS;
	}
	@Action(value = "save")
	public void save(){
		JsonCurrency jc = new JsonCurrency();
		try{
			if (sysCategoryService.update(sysCategory)) {
				jc.setState("success");
				jc.setMsg1("操作成功");
				sysCategoryService.addLog("[修改]系统默认公告", ServletActionContext.getRequest());
				
			} else {
				throw new Exception();
			}
		}catch(Exception e){
			
		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}
	
	public SysCategory getSysCategory() {
		return sysCategory;
	}
	public List<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public void setSysCategory(SysCategory sysCategory) {
		this.sysCategory = sysCategory;
	}
	public void setSysCategoryService(ISysCategoryService sysCategoryService) {
		this.sysCategoryService = sysCategoryService;
	}
}
