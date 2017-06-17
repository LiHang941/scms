<%@page import="com.scms.sys.entity.*,com.scms.sys.service.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>强制修改密码</title>
<%@ include file="/common/basePath.jsp"%>
<script type="text/javascript" src="<%= basePath %>Backstage/js/jquery.js"></script>
<link href="<%= basePath %>Backstage/css/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
html {
    background-color: #E9EEF0
}
.wrapper {
    margin: 50px auto;
    width: 884px;
}
.loginBox {
    background-color: #FEFEFE;
    border: 1px solid #BfD6E1;
    border-radius: 5px;
    color: #444;
    font: 14px 'Microsoft YaHei','微软雅黑';
    margin: 0 auto;
    width: 388px
}
.loginBox .loginBoxCenter {
    border-bottom: 1px solid #DDE0E8;
    padding: 24px;
}
.loginBox .loginBoxCenter p {
    margin-bottom: 10px
}
.loginBox .loginBoxButtons {
    background-color: #F0F4F6;
    border-top: 1px solid #FFF;
    border-bottom-left-radius: 5px;
    border-bottom-right-radius: 5px;
    line-height: 28px;
    overflow: hidden;
    padding: 20px 24px;
    vertical-align: center;
}
.loginBox .loginInput {
    border: 1px solid #D2D9dC;
    border-radius: 2px;
    color: #444;
    font: 12px 'Microsoft YaHei','微软雅黑';
    padding: 8px 14px;
    margin-bottom: 8px;
    width: 310px;
}
.loginBox .loginInput:FOCUS {
    border: 1px solid #B7D4EA;
    box-shadow: 0 0 8px #B7D4EA;
}
.loginBox .loginBtn {
    background-image: -moz-linear-gradient(to bottom, #B5DEF2, #85CFEE);
    border: 1px solid #98CCE7;
    border-radius: 20px;
    box-shadow:inset rgba(255,255,255,0.6) 0 1px 1px, rgba(0,0,0,0.1) 0 1px 1px;
    color: #FFF;
    cursor: pointer;
    float: right;
    font: bold 13px Arial;
    padding: 5px 14px;
}
.loginBox .loginBtn:HOVER {
    background-image: -moz-linear-gradient(to top, #B5DEF2, #85CFEE);
}
.loginBox a.forgetLink {
    color: #ABABAB;
    cursor: pointer;
    float: right;
    font: 11px/20px Arial;
    text-decoration: none;
    vertical-align: middle;
}
.loginBox a.forgetLink:HOVER {
    text-decoration: underline;
}
.loginBox input#remember {
    vertical-align: middle;
}
.loginBox label[for="remember"] {
    font: 11px Arial;
}

</style>
<script type="text/javascript">
	var _basePath = "${pageScope.basePath}";
	var _save_url = _basePath + "manager/currency/setPassword/savePassword.do";
	$(document).ready(function(e) {
		
		//添加按钮事件
		$(".loginBtn").click(function (){
			var _password = $("#userPassword").val();
			var _userPasswordCheck = $("#userPasswordCheck").val();
			
			if(_password != _userPasswordCheck){
				alert("密码与确认密码不一致");
				return;
			}else{
				if(_password.length >= 5 && _password.length <= 20){
						var _user = {'user.pwd':_password};
					//	alert(_user+"userStr" + userStr + _url);
						$.post(_save_url, _user,
						 function(data){
						 	alert(data.msg1);
							    if(data.state == "success"){
							    	window.location.href=_basePath+data.url1;
							    }else{
									 $("#userPassword").val("");
									 $("#userPasswordCheck").val("");
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

<body style="text-align:center">
	<div class="wrapper">
    <div class="loginBox">
        <div class="loginBoxCenter">
            <p><label for="username">账号</label></p>
            <p><input type="text"  class="loginInput" autofocus="autofocus" disabled="disabled" value="<s:property value="sessionUser.account"/>" /></p>
            <p><label for="password">密码</label></p>
            <p><input type="password" id="userPassword"  class="loginInput" required="required" placeholder="请输入密码" value="" /></p>
             <p><label for="password">确认密码</label></p>
            <p><input type="password" id="userPasswordCheck" class="loginInput" required="required" placeholder="请再输入密码" value="" /></p>
        </div>
        <div class="loginBoxButtons">
            <button class="loginBtn">修改密码</button>
        </div>
    </div>
	</div>
	<%@ include file="/Backstage/common/ajaxMask.jsp" %>
</body>
</html>
