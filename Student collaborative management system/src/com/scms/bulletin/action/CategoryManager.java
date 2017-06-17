package com.scms.bulletin.action;

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

import com.scms.bulletin.entity.Category;
import com.scms.bulletin.service.ICategoryService;
import com.scms.bulletin.service.impl.CategoryService;
import com.scms.currency.action.base.ManagerBaseAction;
import com.scms.json.entity.JsonCurrency;
import com.scms.util.write.JsonWriterUtil;

/** 
 * @author  航
 * @version 创建时间：2017年2月7日 下午1:40:51 
 * 类说明 
 */
@Namespace("/manager/bulletin/category")
@ParentPackage("default")
@InterceptorRefs(@InterceptorRef("bulletinModularCheckStack")) 
@Scope("prototype")
@Controller
public class CategoryManager extends ManagerBaseAction {
	private List <Category> categoryList ;
	private Category category ;
	@Resource
	private ICategoryService categoryService ;
	@Action(value="list",results={
			@Result(name="success", location="/WEB-INF/jsp/Bulletin/manager/category/list.jsp")
	})
	public String list(){
		if(searchStr == null){
			page = categoryService.getPage(page.getP(),CategoryService.Category_Page_Manager_Length);
			categoryList = categoryService.getByPage(page);
		}else{
			page = categoryService.getSearchPage(page.getP(),CategoryService.Category_Page_Manager_Length,searchStr);
			categoryList = categoryService.getBySearch(page,searchStr);
		}
		return "success";
	}
	
	@Action (value = "save")
	public void save (){
		JsonCurrency jc = new JsonCurrency();
		try {
			if(category != null){
				if(category.getId() == 0){
					if( categoryService.add(category)){
						jc.setState("success");
						jc.setMsg1("类别添加成功");
						categoryService.addLog("[保存]添加类别", ServletActionContext.getRequest());
					}else{
						jc.setState("error");
						jc.setMsg1("类别添加失败");
					}
				}else{
					if(categoryService.update(category)){
						jc.setState("success");
						jc.setMsg1("类别修改成功");
						categoryService.addLog("[修改]修改类别", ServletActionContext.getRequest());
					}else{
						jc.setState("error");
						jc.setMsg1("类别修改失败");
					}
				}
			}else{
				jc.setState("error");
				jc.setMsg1("操作失败");
			}
		} catch (Exception e) {
			jc.setState("error");
			jc.setMsg1("系统出现错误,操作失败");
			e.printStackTrace();
			JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
			throw e;
		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}
	
	@Action(value = "delete")
	public void delete (){
		JsonCurrency jc = new JsonCurrency();
		try {
			if(category != null){
				if(categoryService.delete(category)){
					jc.setState("success");
					jc.setMsg1("公告类别删除成功");
					categoryService.addLog("[删除]添加类别", ServletActionContext.getRequest());
				}else{
					jc.setState("error");
					jc.setMsg1("公告类别删除失败,可能该公告类别是系统默认类别,请修改系统默认公告类别再删除");
				}
			}else{
				jc.setState("error");
				jc.setMsg1("操作失败");
			}
		} catch (Exception e) {
			jc.setState("error");
			jc.setMsg1("操作失败,该类别被关联,请修改关联的公告再删除");
			e.printStackTrace();
			JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
			throw e;
		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}
	
	public List<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	
}
