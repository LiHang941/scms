<%@page import="com.scms.sys.entity.*,com.scms.sys.service.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

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
			if(!(checkUserName()&&checkName()&&checkPsw() &&checkTel()&&checkEmail()&&checkQQ()&&checkPswAndCheckPsw ())){
				return ;
			}
			
			var _userName = $("#userName").val();
			var _name = $("#name").val();
			var _password = $("#userPassword").val();
			var _userPasswordCheck = $("#userPasswordCheck").val();
			var _roleId = $(".select1 option:selected").val();
			var _tel = $("#userTel").val();
			var _email = $("#userEmail").val();
			var _QQ = $("#userQQ").val();
			var _record = $("#userRecord").val();
			
			var _user = {'role.id':_roleId,'user.name':_name,
			'user.account':_userName,'user.pwd':_password,
			'userInformation.tel':_tel,'userInformation.email':_email,
			'userInformation.record':_record
			};
			//var _user = eval("("+userStr+")");
			var _url = _basePath + "manager/user/save.do";
		//	alert(_user+"userStr" + userStr + _url);
			$.post(_url, _user,
			 function(data){
			 	try{
			 	alert(data.msg1);
				    if(data.state == "success"){
				    	window.location.href = _basePath+"manager/user/list.do";
				    }else{
				    
				    }
			    }catch(err){
			    	alert("操作失败");
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
	function checkUserName (){
		
		var str = $("#userName").val();
		if(str== ""){
			return false;
		}
		var re= /^(\w){5,20}$/;
		if(str.length < 5 || str.length > 20 || (!re.test(str))){
			alert("账号字符范围在{5-20}  只能输入5-20个字母、数字、下划线");
			$("#userName").val("");
			$("#userName").focus();
			return false;
		}else{
			//Ajax校验登录名
			
			
			return true;
		}
	}
	
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
	function checkPsw (){
		var str = $("#userPassword").val();
		if(str== ""){
			return false;
			}
		var re= /^(\w){5,20}$/;
		if(str.length < 5 || str.length > 20 || (!re.test(str))){
			alert("密码字符范围在{5-20}  只能输入5-20个字母、数字、下划线");
			$("#userPassword").val("");
			$("#userPasswordCheck").val("");
			$("#userPassword").focus();
			return false;
		}else{
			return true;
		}
	}
	function checkPswAndCheckPsw (){
		if($("#userPasswordCheck").val() ==""){
			return false;
		}
	
		if($("#userPassword").val() != $("#userPasswordCheck").val()){
			alert("密码与确认密码不一致");
			$("#userPasswordCheck").val("");
			$("#userPasswordCheck").focus();
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
			<li>用户添加</li>
		</ul>
	</div>

	<div class="formbody">


		<div id="usual1" class="usual">



			<div id="tab1" class="tabson">

				<ul class="forminfo">
					<li><label>账号<b>*</b></label><input id="userName" type="text"
						class="dfinput"  style="width:300px;" onblur="checkUserName()" /></li>
					<li><label>用户名<b>*</b></label><input id="name" type="text"
						class="dfinput"  style="width:300px;" onblur="checkName()"/></li>
					
					<li><label>密码<b>*</b></label> <input id="userPassword" type="password"
						class="dfinput" style="width:300px;"  onblur="checkPsw ()"/></li>
						
					<li><label>确认密码<b>*</b></label> <input id="userPasswordCheck" type="password"
						class="dfinput" style="width:300px;" onblur="checkPswAndCheckPsw()"/></li>

					<li><label>权限角色<b>*</b></label><div class="vocation">
							<select class="select1">
								<s:iterator value="roleList" id ="roleModle">
									<option value="<s:property value="#roleModle.id"/>"><s:property value="#roleModle.name"/></option>
								</s:iterator>
							</select>
						</div>
					</li>
					<li><label>电话号码:</label> <input id="userTel" type="text"
						class="dfinput" style="width:300px;" onblur="checkTel()"/></li>
					<li><label>QQ号码:</label> <input id="userQQ" type="text"
						class="dfinput" style="width:300px;" onblur="checkQQ()"/></li>
					<li><label>邮箱:</label> <input id="userEmail" type="text"
						class="dfinput" style="width:300px;" onblur="checkEmail()"/></li>
					<li><label>个人说明:</label> <input id="userRecord" type="text"
						class="dfinput" style="width:300px;" /></li>
					<li><label>&nbsp;</label> <input type="button"
						class="btn" value="添加" /></li>
				</ul>
				
			</div>
		</div>
	</div>
	<%@ include file="/Backstage/common/ajaxMask.jsp" %>
</body>
</html>
