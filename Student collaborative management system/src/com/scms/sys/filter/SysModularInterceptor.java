package com.scms.sys.filter;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.inject.Container;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.scms.sys.config.AuthorityManagement;
import com.scms.user.entity.User;
import com.scms.user.service.IUserService;
import com.scms.util.Constant;

/** 
 * @author  航
 * @version 创建时间：2017年1月24日 下午8:47:03 
 * 
 * 类说明 
 * 系统模块权限拦截
 */
public class SysModularInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;
	private String SysSysModularName = AuthorityManagement.SYS_MODULAR_AUTHORTIY;
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User) session.getAttribute(Constant.MANAGER_USER_SESSSION);
		if(AuthorityManagement.isAuthority(this.SysSysModularName, user)){
			//有权限  继续执行
			return arg0.invoke();
		}else{
			//无权限  跳转无权限提示页面
			return "noAuthority";
		}
	}
	
}
