package com.scms.sys.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.scms.sys.config.AuthorityManagement;
import com.scms.user.entity.User;
import com.scms.util.Constant;

/** 
 * @author  航
 * @version 创建时间：2017年1月26日 下午3:41:50 
 * 类说明 
 * 
 * 后台主页Action
 */
@Namespace("/manager")
@ParentPackage("default")
@Scope("prototype")
@Controller
public class ManagerAction extends ActionSupport {
	
	private User sessionUser ;
	
	private ManagerAction(){
		//获取Session 存储的User
		sessionUser = (User) ServletActionContext.getRequest().getSession().getAttribute(Constant.MANAGER_USER_SESSSION);
		//排序
		sessionUser.getRole().setAuthoritys(AuthorityManagement.authority_sort(sessionUser.getRole().getAuthoritys()));
	}
	@Override
	public String execute() throws Exception {
		return "managerIndex";
	}
	
	@Action(value="left",results={
			@Result(name="success", location="/WEB-INF/jsp/Manager/left.jsp")
	})
	public String left(){
		return SUCCESS;
	}
	
	@Action(value="top",results={
			@Result(name="success", location="/WEB-INF/jsp/Manager/top.jsp")
	})
	public String top(){
		return SUCCESS;
	}
	
	@Action(value="main",results={
			@Result(name="success", location="/WEB-INF/jsp/Manager/main.jsp")
	})
	public String main(){
		return SUCCESS;
	}
	
	@Action(value="index",results={
			@Result(name="success", location="/WEB-INF/jsp/Manager/index.jsp")
	})
	public String index(){
		return SUCCESS;
	}
	public User getSessionUser() {
		return sessionUser;
	}
	public void setSessionUser(User sessionUser) {
		this.sessionUser = sessionUser;
	}	
	
	
}
