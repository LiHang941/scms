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
	var _save_url = _basePath + "manager/user/savePassword.do";
	var _user_id =<s:property value="user.id"/>;
	$(document).ready(function(e) {
		$(".select1").uedSelect({
			width : 345
		});
		$(".select2").uedSelect({
			width : 167
		});
		$(".select3").uedSelect({
			width : 100
		});
		
		//添加按钮事件
		$(".btn").click(function (){
			var _password = $("#userPassword").val();
			var _userPasswordCheck = $("#userPasswordCheck").val();
			
			if(_password != _userPasswordCheck){
				alert("密码与确认密码不一致");
				return;
			}else{
				if(_password.length >= 5 && _password.length <= 20){
						var _user = {'user.id':_user_id,'user.pwd':_password};
						$.post(_save_url, _user,
						 function(data){
						 	alert(data.msg1);
							    if(data.state == "success"){
							    	
							    }else{
							    
							    }
							   },'json');
				}else{
					alert("密码字符范围在{5-20}");
					return ;
				}
			}
		});
});		
</script>


</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="${pageScope.basePath}manager/index.do" target="rightFrame">首页</a></li>
			<li><a href="${pageScope.basePath}manager/user/list.do" target="rightFrame">用户管理</a></li>
			<li>密码修改</li>
		</ul>
		<%@ include file="/Backstage/common/upperUrl.jsp" %>
	</div>

	<div class="formbody">


		<div id="usual1" class="usual">



			<div id="tab1" class="tabson">

				<ul class="forminfo">
					<li><label>账号<b>*</b></label><input id="" type="text"
						class="dfinput"  style="width:300px;" value="<s:property value="user.account"/>" readonly="readonly"/></li>
					<li><label>密码<b>*</b></label><input id="userPassword" type="text"
						class="dfinput"  style="width:300px;" /></li>
					<li><label>确认密码<b>*</b></label><input id="userPasswordCheck" type="text"
						class="dfinput"  style="width:300px;" /></li>
					<li><label>&nbsp;</label> <input type="button"
						class="btn" value="修改" /></li>
				</ul>
				
			</div>
		</div>
		
		
		<div class="tip">
			<div class="tiptop"><span>提示信息</span><a></a></div>
			<div class="tipinfo">
				<span><img src="${pageScope.basePath}Backstage/images/loaging.gif" /></span>
				<div class="tipright">
					<p></p>
					<cite></cite>
				</div>
			</div>
			
			<div class="tipbtn">
				<input name="" type="button"  class="sure" value="确定" />&nbsp;
			</div>
		</div>
    
	</div>
	<%@ include file="/Backstage/common/ajaxMask.jsp" %>
</body>
</html>
