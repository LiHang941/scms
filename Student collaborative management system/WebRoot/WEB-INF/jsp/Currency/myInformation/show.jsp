<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/basePath.jsp"%>
<!DOCTYPE html> 
<html lang="en"> 
<head> 
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>公告详情</title> 
<link href="${pageScope.basePath}Reception/css/base.css" rel="stylesheet">
<link href="${pageScope.basePath}Reception/css/bulletin/inform.css" rel="stylesheet">
</head> 
<body>
	<div class="notice-title">
        <i class="notice-icon"></i>
        <span>收件箱</span>
    </div>
    <div class="notice-name"><s:property value="information.title"/></div>
    <div class="notice-time">发布日期:<s:date name="information.date"
							format="yyyy-MM-dd HH:mm:ss" /></div>
    <div class="notice-wrap">
        <div class="notice-content">
        	<s:property value="information.getContent()"  escape="false"/>
        </div>
    </div>
</body> 
</html>
