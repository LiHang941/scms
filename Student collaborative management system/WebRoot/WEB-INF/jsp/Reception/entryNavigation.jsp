<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学生协同管理系统</title>
<%@ include file="/Reception/common/reception.jsp" %>

</head>
  <frameset cols="25%,75%" frameborder="no" border="0" framespacing="0">
    <frame src="${pageScope.basePath}<s:property value="#request.LEFT_FRAME_URL"/>" name="leftFrame" scrolling="auto" noresize="noresize" id="leftFrame" title="leftFrame" />
    <frame src="${pageScope.basePath}<s:property value="#request.RIGHT_FRAME_URL"/>" name="rightFrame" id="rightFrame" title="rightFrame"  scrolling="no"/>
  </frameset>
<noframes>
<body>
</body>
</noframes>
</html>