<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/basePath.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>导入用户提示</title>
  </head>
  
  <body>
  	<h1>提示:</h1>
  	<hr/>
  	<s:debug></s:debug>
  	<ul>
  	<s:iterator value="importMessages" id="str" status="list">
  		<li><s:property value="#str"/></li>
  	</s:iterator>
  	</ul>
   	<hr/>
   	<a>返回</a>
  </body>
</html>
