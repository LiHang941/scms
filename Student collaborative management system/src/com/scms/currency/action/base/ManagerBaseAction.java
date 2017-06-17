package com.scms.currency.action.base;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.scms.sys.config.AuthorityManagement;
import com.scms.user.entity.User;
import com.scms.user.service.IUserService;
import com.scms.util.Constant;
import com.scms.util.Page;
import com.scms.util.search.SearchFilter;

/** 
 * @author  航
 * @version 创建时间：2017年2月7日 上午11:48:11 
 * 类说明 
 * 
 * 封装页码,搜索字串基本方法
 */
public abstract class ManagerBaseAction extends ActionSupport{
	protected Page page = new Page() ;
	protected String searchStr = null;
	protected String  upperURL = null;
	protected String URL = null;
	
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) throws UnsupportedEncodingException  {
		//Get方式提交的参数需要解码   因为使用二次编码 
		this.searchStr =SearchFilter.searchFilter1(new String(searchStr.getBytes("ISO8859-1"), "utf-8"));
//		System.out.println(this.searchStr);
	}
	
	
	public String getSessionUserName (){
		User sessionUser  = null;
		//获取Session 存储的User
		sessionUser = (User) ServletActionContext.getRequest().getSession().getAttribute(Constant.MANAGER_USER_SESSSION);
		//排序
		sessionUser.getRole().setAuthoritys(AuthorityManagement.authority_sort(sessionUser.getRole().getAuthoritys()));
		
		if(sessionUser == null || sessionUser.getAccount() == null )
			return "未知用户";
		return sessionUser.getAccount() ;
	}
	/**
	 * 获取Session中的User对象 前提是已经登录
	 * @return
	 */
	public User getSessionUser(){
		User sessionUser  = null;
		//获取Session 存储的User
		sessionUser = (User) ServletActionContext.getRequest().getSession().getAttribute(Constant.MANAGER_USER_SESSSION);
		
		return sessionUser;
	}
	public String getUpperURL() {
		return upperURL;
	}

	public void setUpperURL(String upperURL) {
		this.upperURL = upperURL;
	}
}
