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
	var _save_url = _basePath + "manager/user/save.do";
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
			//校验 
			if(!(checkName()&&checkTel()&&checkEmail()&&checkQQ())){
				return ;
			}
		
			var _name = $("#name").val();
			var _roleId = $(".select1 option:selected").val();
			var _tel = $("#userTel").val();
			var _email = $("#userEmail").val();
			var _QQ = $("#userQQ").val();
			var _record = $("#userRecord").val();
			var _user = {'user.id':_user_id,'user.name':_name,'role.id':_roleId,
			'userInformation.tel':_tel,'userInformation.email':_email,
			'userInformation.record':_record,'userInformation.qq':_QQ
			};

			$.post(_save_url, _user,
			 function(data){
			 	alert(data.msg1);
				    if(data.state == "success"){
				    	window.location.reload();
				    }else{
				    
				    }
				   },'json');

		});
});		
	function checkTel(){
		var str = $("#userTel").val();
		var re= /^0?1[3|4|5|8][0-9]\d{8}$/;
		if(str!= ""){
	         if(re.test(str)){
	         	return true;
	         }else{
	         	re = /^0[\d]{2,3}-[\d]{7,8}$/;
	         	if(re.test(str)){
	         		return true;
	         	}
	         	alert("请输入正确的手机号");
	         	$("#userTel").val("");
	         	$("#userTel").focus();
	         	return false;
	         }
         }else{
         	return true;
         }
	}
	
	function checkEmail(){
		var str = $("#userEmail").val();
		var re= /\w@\w*\.\w/;
        if(str!= ""){
	         if(re.test(str)){
	         	return true;
	         }else{
	         	alert("请输入正确的邮箱地址");
	         	$("#userEmail").val("");
	         	$("#userEmail").focus();;
	         	return false;
	         }
         }else{
         	return true;
         }
	}
	
	function checkQQ(){
		var str = $("#userQQ").val();
		var re= /^[0-9]{5,10}$/;
        if(str!= ""){
	         if(re.test(str)){
	         	return true;
	         }else{
	         	alert("请输入正确的QQ号");
	         	$("#userQQ").val("");
	         	$("#userQQ").focus();
	         	return false;
	         }
         }else{
         	return true;
         }
	}
	/////////////////////////////////////////////////////////////
	////////   必须
	
	function checkName(){
		var str = $("#name").val();
		if(str== ""){
			return false;
		}
		if(str.length < 2 || str.length > 20){
			alert("用户名字符范围在{2-20}");
			$("#name").val("");
			$("#name").focus();
			return false;
		}else{
			return true;
		}
	}
	
</script>


</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="${pageScope.basePath}manager/index.do" target="rightFrame">首页</a></li>
			<li><a href="${pageScope.basePath}manager/user/list.do" target="rightFrame">用户管理</a></li>
			<li>用户修改</li>
		</ul>
		<%@ include file="/Backstage/common/upperUrl.jsp" %>
	</div>

	<div class="formbody">


		<div id="usual1" class="usual">



			<div id="tab1" class="tabson">

				<ul class="forminfo">
					<li><label>用户名<b>*</b></label><input id="name" type="text"
						class="dfinput"  style="width:300px;" value="<s:property value="user.name"/>"/>
						</li>
					
					
					<li><label>权限角色<b>*</b></label><div class="vocation">
							<select class="select1">
								<s:iterator value="roleList" id ="roleModle">
									<s:if test="#roleModle.id == user.role.id">
										<option value="<s:property value="#roleModle.id"/>"><s:property value="#roleModle.name"/></option>
									</s:if>
								</s:iterator>
								<s:iterator value="roleList" id ="roleModle">
									<s:if test="#roleModle.id != user.role.id">
										<option value="<s:property value="#roleModle.id"/>"><s:property value="#roleModle.name"/></option>
									</s:if>
								</s:iterator>
							</select>
						</div>
					</li>
					<li><label>电话号码:</label> <input id="userTel" type="text"
						class="dfinput" style="width:300px;" onblur="checkTel()" 
						value="<s:property value="userInformation.tel"/>"
						/></li>
					<li><label>QQ号码:</label> <input id="userQQ" type="text"
						class="dfinput" style="width:300px;" onblur="checkQQ()"
						value="<s:property value="userInformation.qq"/>"
						/></li>
					<li><label>邮箱:</label> <input id="userEmail" type="text"
						class="dfinput" style="width:300px;" onblur="checkEmail()"
						value="<s:property value="userInformation.email"/>"
						/></li>
					<li><label>个人说明:</label> <input id="userRecord" type="text"
						class="dfinput" style="width:300px;" 
						value="<s:property value="userInformation.record"/>"
						/></li>
					<li><label>&nbsp;</label> <input type="button"
						class="btn" value="修改" /></li>
				</ul>
				
			</div>
		</div>
	</div>
	<%@ include file="/Backstage/common/ajaxMask.jsp" %>
</body>
</html>
