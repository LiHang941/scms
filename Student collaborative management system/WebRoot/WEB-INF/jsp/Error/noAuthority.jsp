<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>404错误</title>
<%@ include file="/common/basePath.jsp"%>
<link href="${pageScope.basePath}Backstage/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageScope.basePath}Backstage/js/jquery.js"></script>

<script language="javascript">
	$(function(){
    $('.error').css({'position':'absolute','left':($(window).width()-490)/2});
	$(window).resize(function(){  
    $('.error').css({'position':'absolute','left':($(window).width()-490)/2});
    })  
});  
</script> 
</head>


<body style="background:#edf6fa;">

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="${pageScope.basePath}manager/main.do">首页</a></li>
    <li>404错误提示</li>
    </ul>
    </div>
    
    <div class="error">
    
    <h2>非常遗憾，你无权访问该页面</h2>
    <p>看到这个提示，就自认倒霉吧!</p>
    <div class="reindex"><a href="${pageScope.basePath}manager/main.do" target="_parent">返回首页</a></div>
    </div>
</body>
</html>
