<%@ page language="java" import="com.scms.util.*,com.scms.user.entity.User" pageEncoding="UTF-8"%>
<% 
	try{
		String name = ((User)session.getAttribute(Constant.MANAGER_USER_SESSSION)).getName();
		pageContext.setAttribute("SYS_USER_NAME", name);
	}catch(Exception e){
		e.printStackTrace();
	}
%>