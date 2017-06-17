<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>模块列表</title>
<%@ include file="/Backstage/common/backstage.jsp" %>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
	
})	
</script>


</head>

<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>功能</div>
    
    <dl class="leftmenu">
    <!-- 默认权限 -->
    <dd>
    <div class="title">
    <span><img src="${pageScope.basePath}Backstage/images/leftico00.png" /></span>个人中心
    </div>
    <ul class="menuson">
        <li class="active"><cite></cite><a href="${pageScope.basePath}manager/index.do" target="rightFrame">系统首页</a><i></i></li>
        <li><cite></cite><a href="${pageScope.basePath}manager/currency/myHome/indexUI.do" target="rightFrame">个人信息</a><i></i></li>
        <li><cite></cite><a href="${pageScope.basePath}manager/currency/myInformation/listUI.do" target="rightFrame">收件箱</a><i></i></li>
        <li><cite></cite><a href="${pageScope.basePath}manager/currency/setPassword/setPasswordUI.do" target="rightFrame">修改密码</a><i></i></li>
        <li><cite></cite><a href="${pageScope.basePath}manager/currency/myAccessLog/myAccessListUI.do" target="rightFrame">登录记录</a><i></i></li>
        </ul>     
    </dd> 
    <s:if test="sessionUser.getRole().state == #application.ROLE_STATE_TRUE">
	   <s:iterator value="sessionUser.getRole().getAuthoritys()" id="authority">
			    <dd>
			    <div class="title">
			    <span><img src="${pageScope.basePath}Backstage/<s:property value="#authority.getModular().getImgUrl()"/>" /></span><s:property value="#authority.getModular().getName()"/>
			    </div>
			    	<ul class="menuson">
			    	 <s:iterator value="#authority.getModular().getUrl_method()" id="url_method">
			        	<li><cite></cite><a href="${pageScope.basePath}<s:property value="#url_method.value"/>" target="rightFrame"><s:property value="#url_method.key"/></a><i></i></li>
			         </s:iterator>
			        </ul>    
			    </dd>
	    </s:iterator>
     </s:if>
    </dl>
</body>
</html>
