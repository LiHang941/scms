package com.scms.bulletin.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.scms.bulletin.entity.Bulletin;
import com.scms.bulletin.entity.Category;
import com.scms.bulletin.service.IBulletinService;
import com.scms.bulletin.service.ICategoryService;
import com.scms.bulletin.service.impl.BulletinService;
import com.scms.currency.action.base.ManagerBaseAction;
import com.scms.json.entity.JsonCurrency;
import com.scms.user.entity.User;
import com.scms.user.service.IUserService;
import com.scms.util.Constant;
import com.scms.util.search.SearchFilter;
import com.scms.util.write.JsonWriterUtil;

/** 
 * @author  航
 * @version 创建时间：2017年1月24日 下午6:26:35 
 * 类说明 
 */
@Namespace("/manager/bulletin")
@ParentPackage("default")
@Results(value={
	@Result(name = "json",type="dispatcher")
})
@InterceptorRefs(@InterceptorRef("bulletinModularCheckStack")) 
@Scope("prototype")
@Controller
public class BulletinManager extends ManagerBaseAction {
	//添加一个公告
	@Resource
	private ICategoryService categoryService;
	@Resource
	private IBulletinService bulletinService;
	@Resource
	private IUserService userService;
	
	private Bulletin b= new Bulletin();
	private List<Bulletin> bulletinList = new ArrayList<Bulletin>();
	
	@Action(value="add",results={
			@Result(name="success", location="/WEB-INF/jsp/Bulletin/manager/add.jsp")
	})
	public String add(){
		ServletActionContext.getRequest().setAttribute("CATEGORY_LIST", categoryService.getAll());
		return "success";
	}
	
	@Action(value="list",results={
			@Result(name="success", location="/WEB-INF/jsp/Bulletin/manager/list.jsp")
	})
	//获取指定页码的的公告  方便管理
	public String list(){
		if(searchStr == null){
			page = bulletinService.getPage(page.getP(),Constant.MANAGER_LIST_LENGTH);
			bulletinList = bulletinService.getByPage(page);
		}else{
			page = bulletinService.getSearchPage(page.getP(),Constant.MANAGER_LIST_LENGTH, searchStr);
			bulletinList = bulletinService.getBySearch(page, searchStr);
		}
		//ServletActionContext.getRequest().setAttribute("BULLETIN_STATE_FALSE", Bulletin.BULLETIN_STATE_FALSE);
		return SUCCESS;
	}
	
	@Action(value="save")
	//保存一个公告
	public void save(){
		
		//更新 
		JsonCurrency jc = new JsonCurrency();
		if(b.getId() == 0){
			//添加
			b.setCategory(categoryService.findById(b.getCategory().getId()));
			User user = (User)ServletActionContext.getRequest().getSession().getAttribute(Constant.MANAGER_USER_SESSSION);
			b.setUser(userService.getById(user));
			b.setDate(new Date());
			if(bulletinService.add(b)){
				jc.setState("success");
				jc.setMsg1("公告添加成功");
				jc.setUrl1("manager/bulletin/list");
				bulletinService.addLog("[保存]添加公告", ServletActionContext.getRequest());
			}else{
				jc.setState("error");
				jc.setMsg1("公告添加失败");
			}
		}else{
			if(bulletinService.update(b)){
				jc.setState("success");
				jc.setMsg1("公告更新成功");
				bulletinService.addLog("[修改]更新公告id=" + b.getId(), ServletActionContext.getRequest());
			}else{
				jc.setState("error");
				jc.setMsg1("公告更新失败");
			}
		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}
	
	@Action(value="update",results={
			@Result(name="success", location="/WEB-INF/jsp/Bulletin/manager/update.jsp")
	})
	//更新一个公告
	public String update(){
		b = bulletinService.getById(b);
		if(b == null || b.getState() == 1) //不存在  或者被锁定
			return "managerIndex";  //跳转到管理主界面
		ServletActionContext.getRequest().setAttribute("CATEGORY_LIST", categoryService.getAll());
		return "success";
	}
	
	@Action(value="delete")
	public void delete (){
		JsonCurrency jc = new JsonCurrency();
		if(bulletinService.delete(b)){
			jc.setState("success");
			jc.setMsg1("操作成功");
			bulletinService.addLog("[删除]删除公告id=" + b.getId(), ServletActionContext.getRequest());
		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
	}
	
	@Action(value="state")
	//修改帖子的状态  加锁(不公开)  解锁(公开)
	public String state (){
		JsonCurrency jc = new JsonCurrency();
		if(bulletinService.state(b)){
			jc.setState("success");
			jc.setMsg1("操作成功");
			bulletinService.addLog("[修改]锁定(解锁)公告", ServletActionContext.getRequest());
		}
		JsonWriterUtil.writerJson(jc.jsonStr(), ServletActionContext.getResponse());
		
		return "json";
	}

	////////////////////////////////////////////////////////

	public Bulletin getB() {
		return b;
	}
	public void setB(Bulletin b) {
		this.b = b;
	}

	public List<Bulletin> getBulletinList() {
		return bulletinList;
	}

	public void setBulletinList(List<Bulletin> bulletinList) {
		this.bulletinList = bulletinList;
	}

}
