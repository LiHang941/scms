package com.scms.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import com.scms.sys.config.AuthorityManagement;
import com.scms.user.entity.User;
import com.scms.user.service.IUserService;
import com.scms.util.Constant;

/**
 * 过滤访问公告文件上传  防止跨权限访问
 * @author 李航_Computer
 *
 */
public class StrutsAndFileUploadFilter extends StrutsPrepareAndExecuteFilter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;  
        String reqURI = req.getRequestURI();  
        //是否访问Ueditor的上传工具
      if(reqURI.contains("editor/jsp/controller.jsp")){
    	  User user = (User) req.getSession().getAttribute(Constant.MANAGER_USER_SESSSION);
    	  //是否存在用户    用户是否有公告权限
      	  if(user == null || !AuthorityManagement.isAuthority(AuthorityManagement.BULLETIN_MODULAR_AUTHORTIY, user)){
      		HttpServletResponse hs = ((HttpServletResponse)response);
      		hs.sendRedirect(req.getContextPath() + "/index");
      		return;
      	  }else{
      		  chain.doFilter(request, response);
          }
      }else{
    	  super.doFilter(request, response, chain);         
      }
	}
}
