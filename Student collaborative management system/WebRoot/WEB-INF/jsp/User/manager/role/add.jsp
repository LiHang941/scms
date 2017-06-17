<%@page import="com.scms.sys.entity.*,com.scms.sys.service.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<%@ include file="/Backstage/common/backstage.jsp"%>
<link href="${pageScope.basePath}Backstage/css/select.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageScope.basePath}Backstage/js/jquery.idTabs.min.js"></script>
<script type="text/javascript"
	src="${pageScope.basePath}Backstage/js/select-ui.min.js"></script>

<script type="text/javascript">
	var _basePath = "${pageScope.basePath}";
	$(document).ready(
			function(e) {
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
				$(".btn").click(
						function() {
							var _roleName = $("#roleName").val();
							if (_roleName == "") {
								alert("请填写角色名称");
								$("#roleName").focus();
								return;
							}
							var _url = _basePath + "manager/user/role/save.do";
						$.ajax({  type: "POST",
						   url: _url,
						   data: $("#dataFrom").serialize(),
						   dataType:'json',
						 success: function(jsondata){
    						alert(jsondata.msg1);
							if (jsondata.state == "success") {
								showUpper();
							} else {

							}
						}
						});
						});
			});
</script>


</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="${pageScope.basePath}manager/index.do"
				target="rightFrame">首页</a></li>
			<li><a href="${pageScope.basePath}manager/user/role/list.do"
				target="rightFrame">角色管理</a></li>
			<li>角色添加</li>
		</ul>
		<%@ include file="/Backstage/common/upperUrl.jsp" %>
	</div>

	<div class="formbody">


		<div id="usual1" class="usual">



			<div id="tab1" class="tabson">
			<form id="dataFrom">
				<ul class="forminfo">
					<li><label>角色名称<b>*</b></label><input id="roleName" name="role.name"
						type="text" class="dfinput" style="width:400px;" /></li>

					<li><label>是否有效<b>*</b></label><div class="vocation">
							<select class="select1" name="role.state">
								<s:iterator value="#request.ROLE_STATE_MAP">
									<option value="<s:property value="key"/>"><s:property value="value"/></option>
								</s:iterator>
							</select>
						</div></li>

					<li><label>权限<b>*</b></label></li>
					<li>
						<div
							style="width:350px;height:auto;border:1px solid #cccccc;padding: 20px;">
							<table style="">
								<tr>
									<s:iterator value="modulars" id="modularModel" status="list">
										<s:if test="((#list.index+1)%5)==0">
								</tr>
								<tr>
									</s:if>
									<td style="padding:10px;"><s:property
											value="#modularModel.getName()" /> <input
										class="modularcheckbox" type="checkbox" name="Modulars.id"
										value="<s:property value="#modularModel.getId()"/>" /></td>
									</s:iterator>
								</tr>
							</table>
						</div>
					</li>

					<li><label>&nbsp;</label> <input type="button" class="btn"
						value="添加" /></li>
				</ul>
	</form>
			</div>
		</div>
	</div>
	<%@ include file="/Backstage/common/ajaxMask.jsp" %>
</body>
</html>
