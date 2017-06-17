<%@ page language="java" import="com.scms.util.*,com.scms.user.entity.User" pageEncoding="UTF-8"%>
<%@ include file="/manager/common/user/getUserName.jsp" %>
<%@ include file="/common/basePath.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageScope.basePath}Backstage/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageScope.basePath}Backstage/js/jquery.js"></script>
<script type="text/javascript">
	var _basePath = "${pageScope.basePath}";
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	

$(document).ready(function() {
	var _url =_basePath + "manager/currency/myInformation/getMyInformationCount.do";
	$.ajax({  type: "POST",
			   url: _url,
			   data: null,
			   dataType:'json',
			 success: function(jsondata){
			 	if(jsondata.state == 'success'){
	 				$("#myInformationCount").text(jsondata.msg1);
			 	}
			 	
			}
		});
		
	/* $(".user").click(function (){
		var _uurl = _basePath + "manager/currency/myInformation/listUI.do";
		rightFrame.location.href = _uurl;
	});	 */

});
</script>


</head>

<body style="background:url(${pageScope.basePath}/Backstage/images/topbg.gif) repeat-x;">

    <div class="topleft">
    <a href="${pageScope.basePath}manager/main.do" target="_parent"><img src="${pageScope.basePath}Backstage/images/logo.png" title="系统首页" /></a>
    </div>
        
    <ul class="nav">
    <li><a href="default.html" target="rightFrame" class="selected"><img src="${pageScope.basePath}Backstage/images/icon01.png" title="主页" /><h2>主页</h2></a></li>
    <li><a href="imgtable.html" target="rightFrame"><img src="${pageScope.basePath}Backstage/images/icon02.png" title="模型管理" /><h2>模型管理</h2></a></li>
    <li><a href="imglist.html"  target="rightFrame"><img src="${pageScope.basePath}Backstage/images/icon03.png" title="模块设计" /><h2>模块设计</h2></a></li>
    <li><a href="tools.html"  target="rightFrame"><img src="${pageScope.basePath}Backstage/images/icon04.png" title="常用工具" /><h2>消息</h2></a></li>
    <li><a href="computer.html" target="rightFrame"><img src="${pageScope.basePath}Backstage/images/icon05.png" title="文件管理" /><h2>个人空间</h2></a></li>
    <li><a href="${pageScope.basePath}manager/currency/myAccessLog/myAccessListUI.do"  target="rightFrame"><img src="${pageScope.basePath}Backstage/images/icon06.png" title="登录日志" /><h2>登录日志</h2></a></li>
    </ul>
            
    <div class="topright">    
    <ul>
    <li><span><img src="${pageScope.basePath}Backstage/images/help.png" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    <li><a href="${pageScope.basePath}leave.do" target="_parent">退出</a></li>
    </ul>
     <s:property value="getSessionUserName()" />
    <a href="${pageScope.basePath}manager/currency/myInformation/listUI.do" target="rightFrame">
	    	<div class="user">
	    <span>${pageScope.SYS_USER_NAME}</span>
	    <i>消息</i>
	    <b id="myInformationCount">n</b>
	    </div>   
    </a>
     
    
    </div>
</body>
</html>
