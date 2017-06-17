package com.scms.error.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author  航
 * @version 创建时间：2017年2月21日 下午4:19:13 
 * 类说明
 * 
 * 错误页面显示页
 */
@Namespace("/errorPage")
@ParentPackage("default")
@Scope("prototype")
@Controller
public class ErrorAction extends ActionSupport {
	@Action(value="error404",results={
			@Result(name="success", location="/WEB-INF/jsp/Error/404.jsp")
	})
	public String error404(){
		return SUCCESS;
	}
	@Action(value="noAuthority",results={
			@Result(name="success", location="/WEB-INF/jsp/Error/noAuthority.jsp")
	})
	public String noAuthority(){
		return SUCCESS;
	}
}
