package com.scms.bulletin.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.scms.bulletin.entity.Bulletin;
import com.scms.bulletin.entity.Category;
import com.scms.bulletin.service.IBulletinService;
import com.scms.bulletin.service.ICategoryService;
import com.scms.currency.action.base.ManagerBaseAction;
import com.scms.sys.entity.SysCategory;
import com.scms.sys.service.ISysCategoryService;
import com.scms.util.Constant;

/** 
 * @author  航
 * @version 创建时间：2017年1月13日 下午2:49:28 
 * 类说明 
 */
@Namespace("/")
@ParentPackage("default")
@Scope("prototype")
@Controller
public class BulletinShow extends ManagerBaseAction{
	@Resource
	private IBulletinService bulletinService;
	@Resource
	private ISysCategoryService sysCategoryService;
	@Resource
	private ICategoryService categoryService;
	
	private List<Bulletin> bulletinList ;
	private Bulletin bulletin ;
	private List<Category> categoryList;
	private Category category;
	
	//访问主页
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "index";
	}
	
	@Action(value="index",results={
			@Result(name="success", location="/WEB-INF/jsp/Reception/index.jsp")
	})
	public String getIndexPage(){
		if(URL == null || URL.equals("")){
			URL = "indexContent.html";
		}
		return "success";
	}
	@Action(value="indexContent",results={
			@Result(name="success", location="/WEB-INF/jsp/Reception/indexContent.jsp")
	})
	public String getIndexContent(){
		//获取系统category
				SysCategory sysCategoryModel = sysCategoryService.getSysCategory();
				//获取主页该显示的bulletin
				ServletActionContext.getRequest().setAttribute("SYSCATEGORY_MODEL", sysCategoryModel);
				//设置公告集合
				bulletinList = bulletinService.getIndexBulletinList(sysCategoryModel);
				return "success";
		
	}
	
	//进入公告导航页面
	//两种方式  1. 通过主页更多进入  2.通过主页导航进入
	//
	@Action(value="entryNavigation",results={
			@Result(name="success", location="/WEB-INF/jsp/Reception/entryNavigation.jsp")
	})
	public String entryNavigation(){
		String leftUrl = "categoryList.do";
		String rightUrl = "";
		
		//判断是否是搜索方式
		if(searchStr == null || searchStr.equals("")){
			//判断是否有类别
			if(category == null || category.getId()<=0){
				rightUrl = "byCategoryList.do";
			}else{
				leftUrl = leftUrl + "?category.id="+category.getId();
				rightUrl = "byCategoryList.do?category.id="+category.getId();
			}
		}else{
			//搜索方式
			rightUrl = "bySearchList.do?searchStr="+searchStr;
		}
		ServletActionContext.getRequest().setAttribute("LEFT_FRAME_URL", leftUrl);
		ServletActionContext.getRequest().setAttribute("RIGHT_FRAME_URL", rightUrl);
		return "success";
	}
	@Action(value="categoryList",results={
			@Result(name="success", location="/WEB-INF/jsp/Reception/categoryList.jsp")
	})
	//显示Category列表
	public String categoryList(){
		categoryList = categoryService.getAll();
		return SUCCESS;
	}
	
	@Action(value="byCategoryList",results={
			@Result(name="success", location="/WEB-INF/jsp/Reception/byCategoryList.jsp")
	})
	//显示指定Category的bulletin列表
	public String byCategoryList(){
		page = bulletinService.getByCategoryPage(category,page.getP(),Constant.QIANTAI_BULLETIN_LIST_LENGHT);
		bulletinList = bulletinService.getByCategoryList(category,page);
		//标识
		return SUCCESS;
	}
	
	@Action(value="bySearchList",results={
			@Result(name="success", location="/WEB-INF/jsp/Reception/bySearchList.jsp")
	})
	//显示指定搜索内容的bulletin列表
	public String bySearchList(){
		if(searchStr == null || searchStr.equals("")){
			searchStr = "";
			return "byCategoryList";
		}
		page = bulletinService.getBySearchPage(searchStr,page.getP(),Constant.QIANTAI_BULLETIN_LIST_LENGHT);
		bulletinList = bulletinService.getBySearchList(searchStr,page);
		return SUCCESS;
	}


	
	//显示公告    url重写     /bulletin/show/([0-9]+).html
	@Action(value="show",results={
			@Result(name="success", location="/WEB-INF/jsp/Reception/show.jsp")
	})
	public String show(){
		bulletin = bulletinService.Show(bulletin);
		//帖子不存在  或者该帖子被锁定  跳转404页面
		if(bulletin == null || bulletin.getState()==1){
			return "error404";
		}
		return "success";
	}
	///////////////////////////////////////////////////////////////
	public List<Bulletin> getBulletinList() {
		return bulletinList;
	}
	public void setBulletinList(List<Bulletin> bulletinList) {
		this.bulletinList = bulletinList;
	}
	public Bulletin getBulletin() {
		return bulletin;
	}
	public void setBulletin(Bulletin bulletin) {
		this.bulletin = bulletin;
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
