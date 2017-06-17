<%@ page language="java" import="com.scms.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/basePath.jsp" %>
<%
	String Login_MSG = (String)request.getAttribute(Constant.QIANTAI_LOGIN_MSG);
	if(Login_MSG == null){
		response.sendRedirect(basePath+"loginUI.do");
	}else if(Login_MSG == "success"){
		response.sendRedirect(basePath+"manager/main.do");
	}
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户登录验证</title>
  </head>
  <body>
 	<script type="text/javascript">
		alert('<s:property value="#request.LOGIN_USER_MEG"/>');
		window.location.href='${pageSocope.basePath}loginUI.do';
 	</script>
  </body>
</html>
