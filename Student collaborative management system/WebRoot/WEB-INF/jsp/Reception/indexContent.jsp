<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8">
		<title>学生协同系统</title>
		<%@ include file="/Reception/common/reception.jsp" %>
	</head>
	<body>
		<div class="whole">
			<!--具体内容-->
			<div class="content-content">
				<div class="con-con">
					<div class="company">
						<div class="company-left">
							<div class="company-title">
								<div class="company-name">
									<s:property value="#request.SYSCATEGORY_MODEL.category1.name"/><a>&nbsp;/&nbsp;</a>
								</div>
								<div class="company-more"><a href="${pageScope.basePath}entryNavigation/<s:property value="#request.SYSCATEGORY_MODEL.category1.id"/>.html">更多»</a></div>
							</div>
							<div class="company-name-con">
								<ul class="BulletinList">
									<s:iterator value="bulletinList" id="bulletinModel">
										<s:if test="#bulletinModel.category.id == #request.SYSCATEGORY_MODEL.category1.id">
											<a href="${pageScope.basePath}bulletin/show/<s:property value="#bulletinModel.id"/>.html" onclick="window.open(this.href);return false;"><li><s:property value="#bulletinModel.title"/></li></a>
										</s:if>
									</s:iterator>
								</ul>
							</div>
						</div>
						
						<div class="company-right">
							<div class="company-cases">
								<div class="company-tName">
									用户登录<a>&nbsp;/&nbsp;</a>
								</div>
								<div class="company-more"></div>
							</div>
							<div class="company-name-con" style="padding: 10px 15px;">
								<div id="loginform">
									<form action="${pageScope.basePath}login.html" method="post" >
										<input name="user.account" id="userLogin" type="text" placeholder="用户名"/>
										<input name="user.pwd" id="pwdLogin" type="password" placeholder="密码"/>
										<input type="button" value="登录" class="blue">
									</form>
								</div>
							</div>
						</div>
						<script type="text/javascript">
							$(".blue").click(function(){
							if($("#userLogin").val() == "" ){
							  		alert("请输入账号");
							  		return;
							  	}
							  	if($("#password").val() == "" ){
							  		alert("请输入密码");
							  		return;
							  	}
								$("#loginform form").submit();
							});
						</script>
						
						<div class="company-left">
							<div class="company-title">
								<div class="company-name">
									<s:property value="#request.SYSCATEGORY_MODEL.category2.name"/><a>&nbsp;/&nbsp;</a>
								</div>
								<div class="company-more"><a href="${pageScope.basePath}entryNavigation/<s:property value="#request.SYSCATEGORY_MODEL.category1.id"/>.html">更多»</a></div>
							</div>
						<div class="company-name-con">
								<ul class="BulletinList">
									<s:iterator value="bulletinList" id="bulletinModel">
										<s:if test="#bulletinModel.category.id == #request.SYSCATEGORY_MODEL.category2.id">
											<a href="${pageScope.basePath}bulletin/show/<s:property value="#bulletinModel.id"/>.html" onclick="window.open(this.href);return false;" ><li><s:property value="#bulletinModel.title"/></li></a>
										</s:if>
									</s:iterator>
								</ul>
							</div>
						</div>
						
						<div class="company-right">
							<div class="company-cases">
								<div class="company-tName">
									<s:property value="#request.SYSCATEGORY_MODEL.category3.name"/><a>&nbsp;/&nbsp;</a>
								</div>
								<div class="company-more"><a href="${pageScope.basePath}entryNavigation/<s:property value="#request.SYSCATEGORY_MODEL.category1.id"/>.html">更多»</a></div>
							</div>
							<div class="company-name-con">
								<ul class="BulletinList">
									<s:iterator value="bulletinList" id="bulletinModel">
										<s:if test="#bulletinModel.category.id == #request.SYSCATEGORY_MODEL.category3.id">
											<a href="${pageScope.basePath}bulletin/show/<s:property value="#bulletinModel.id"/>.html" onclick="window.open(this.href);return false;" ><li><s:property value="#bulletinModel.title"/></li></a>
										</s:if>
									</s:iterator>
								</ul>
							</div>
						</div>
						
					</div>
				</div>
			</div>
			<!--具体内容End-->
		</div>
	</body>
</html>
