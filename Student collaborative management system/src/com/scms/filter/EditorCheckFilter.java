package com.scms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scms.sys.config.AuthorityManagement;
import com.scms.user.entity.User;
import com.scms.util.Constant;

/** 
 * @author  航
 * @version 创建时间：2017年2月22日 下午12:05:40 
 * 类说明 
 */
public class EditorCheckFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;  
        HttpServletResponse resp = (HttpServletResponse)response;  
        User user = (User) req.getSession().getAttribute(Constant.MANAGER_USER_SESSSION);
  	  //是否存在用户    用户是否有公告权限
		  if(user == null || !AuthorityManagement.isAuthority(AuthorityManagement.BULLETIN_MODULAR_AUTHORTIY, user)){
			  //跳转登录页面
			  resp.sendRedirect(req.getContextPath() + "/loginUI.html");
			return;
		  }
		  chain.doFilter(request, response);  
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
