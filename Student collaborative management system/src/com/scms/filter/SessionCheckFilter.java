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

import com.scms.user.entity.User;
import com.scms.util.Constant;
import com.scms.util.url.UrlManager;

/** 
 * @author  航
 * @version 创建时间：2017年1月24日 下午8:10:50 
 * 类说明 
 */
public class SessionCheckFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		
		
		HttpServletRequest req = (HttpServletRequest)request;  
        HttpServletResponse resp = (HttpServletResponse)response;  
        HttpSession session = req.getSession();  
        String reqURI = req.getRequestURI();   // 获取访问的路径
        if(reqURI!= null){
        	if(reqURI.contains("manager") ){  //是否是后台地址  或者后台资源地址
           	 User user = (User) session.getAttribute(Constant.MANAGER_USER_SESSSION);
                if(user ==  null){  //未登录
               	 resp.sendRedirect(req.getContextPath() + "/loginUI.html");  
               	 return;
                }else{
               	 //判断用户状态    强制修改密码?   //跳转到修改密码  
               	 if(!reqURI.contains("setPassword") && user.getIsModifyPassword() == User.Modify_Password_true){
               		 resp.sendRedirect(req.getContextPath() + "/manager/currency/setPassword/setPasswordUI.do");
                   	 return;
               	 }
                }
           }
        /*	//将访问的url压入栈
           	UrlManager urlmananer = (UrlManager)session.getAttribute(Constant.SESSION_URI);
           	if(urlmananer == null){
           		urlmananer = new UrlManager();
           	}
           	urlmananer.push(reqURI);
           	session.setAttribute(Constant.SESSION_URI, urlmananer);*/
        }
        //url记录到session对象中
      //  System.out.println(reqURI);
        // 继续执行  
        chain.doFilter(request, response);  
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
