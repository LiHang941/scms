package com.scms.bulletin.filter;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
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
 * 访问公告模块权限拦截
 */
public class BulletinModularInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;
	private String BulletinSysModularName = AuthorityManagement.BULLETIN_MODULAR_AUTHORTIY;
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		User user = (User) session.getAttribute(Constant.MANAGER_USER_SESSSION);
		
		if(AuthorityManagement.isAuthority(this.BulletinSysModularName, user)){
			//有权限  继续执行
			return arg0.invoke();
		}else{
			//无权限  跳转管理主页
			return "noAuthority";
		}
	}
	
}
