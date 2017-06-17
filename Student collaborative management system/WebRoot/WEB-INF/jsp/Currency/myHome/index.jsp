<%@page import="com.scms.sys.entity.*,com.scms.sys.service.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	//List<Modular> modularList = (List)request.getAttribute(IModularService.ModularListStr);		
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<%@ include file="/Backstage/common/backstage.jsp" %>
<link href="${pageScope.basePath}Backstage/css/select.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${pageScope.basePath}Backstage/js/jquery.idTabs.min.js"></script>
<script type="text/javascript"
	src="${pageScope.basePath}Backstage/js/select-ui.min.js"></script>
<script type="text/javascript">
	var _basePath = "${pageScope.basePath}";
	$(document).ready(function(e) {
		$("input").each(function(){
			$(this).attr("disabled",true);
		});
		
		$(".btn").attr("disabled",false)
		.click(function(){
			var _url = "${pageScope.basePath}manager/currency/myHome/update.do";
			window.location.href = _url;
		});
	});
</script>


</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="${pageScope.basePath}manager/index.do" target="rightFrame">首页</a></li>
			<li>个人信息</li>
		</ul>
	</div>

	<div class="formbody">


		<div id="usual1" class="usual">
			<div id="tab1" class="tabson">

				<ul class="forminfo">
					<li>
					<label>账号</label><input type="text"
						class="dfinput" value="<s:property value="myUser.account"/>" />
					</li>
					<li><label>用户名</label><input type="text"
						class="dfinput" value="<s:property value="myUser.name"/>" />
					</li>
					
					<li><label>权限角色</label>
							<input type="text"
						class="dfinput" value="<s:property value="myUser.role.name"/>" />
					</li>
					<li><label>电话号码</label> 
							<input  type="text"
						class="dfinput" value="<s:property value="userInformation.tel"/>" />
					</li>
					<li><label>QQ号码</label> <input type="text"
						class="dfinput" value="<s:property value="userInformation.qq"/>" />
					</li>
					<li><label>邮箱</label> <input  type="text"
						class="dfinput" value="<s:property value="userInformation.email"/>"/>
					</li>
					<li><label>个人说明</label> <input  type="text"
						class="dfinput" value="<s:property value="userInformation.record"/>"/>
					</li>
					<li><label>&nbsp;</label> <input type="button"
						class="btn" value="编辑" /></li>
				</ul>
				
			</div>
		</div>
	</div>
	<%@ include file="/Backstage/common/ajaxMask.jsp" %>
</body>
</html>
