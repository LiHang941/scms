<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色列表</title>
<%@ include file="/Backstage/common/backstage.jsp"%>
<script type="text/javascript">
var _basePath = "${pageScope.basePath}";
var _currentPage = <s:property value="page.currentPage"/>; //当前页码
var _TotalPage = <s:property value="page.totalPage"/>; //总页码
$(document).ready(function() {
	$("#addSelect").click(function (){
		window.location.href = _basePath + "manager/user/role/add.do?&upperURL=" +window.location.href;
	});
	//页码
	$(".paginItem a").click(function (){
			var _url = $(this).attr('href');
			var _PageIndex = 0;
			if(_url == "goByPage"){
				var _goPage = $(".paginItem input").val();
				if(isNaN(_goPage)){
					alert("您输入的不是一个数字");
					return false;
				}else{
					_PageIndex= _goPage;
				}
			}else{
				if(_url == "HasNext"){
					_PageIndex = _currentPage +1 ;
				}else{
					_PageIndex = _currentPage -1 ;
				}
			}
			goByPage(_PageIndex);
			
			return false;
	});
	$(".tablelink").click(function() {
			var _url = $(this).attr('href');
			//alert(_url);
			if (_url.indexOf("update") != -1 ) {
				window.location.href = _url+"&upperURL=" +window.location.href;
			} else {
				$.get(_url, null, function(data) {
					alert(data.msg1);
					if (data.state == 'success') {
						window.location.reload();
					}
				}, 'json');
			}

			return false;
		});
});
//跳转指定页码的通用方法
	function goByPage(_PageIndex){
		if(_PageIndex <= 0 && _PageIndex > _TotalPage){
			alert("您要跳转的页码不存在");
			return;
		}
		
		var _url = _basePath + "manager/user/role/list.do?page.p="+_PageIndex;
		window.location.href=_url;
	}
</script>


</head>


<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="${pageScope.basePath}manager/index.do">首页</a></li>
			<li><a href="${pageScope.basePath}manager/user/list.do">用户管理</a></li>
			<li>角色管理</li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="tools">
			<ul class="toolbar">
				<li class="click" id="addSelect"><span><img
						src="${pageScope.basePath}Backstage/images/t01.png" /></span>添加角色</li>
			</ul>
		</div>


		<table class="tablelist">
			<thead>
				<tr>
					<th style="width:25%;">角色名称</th>
					<th style="width:40%;">权限一览</th>
					<th style="width:15%;">是否有效</th>
					<th style="width:20%;">操作</th>
				</tr>
			</thead>
			<tbody>
		
				<s:iterator value="roleList" id="roleModel" status="list">
					<tr>
						<td><s:property value="#roleModel.name" /></td>
						<td><s:property value="#roleModel.getAuthoritysStr()" /></td>
						<td><a
							href="${pageScope.basePath}manager/user/role/state.do?role.id=<s:property value="#roleModel.getId()"/>"
							class="tablelink"><s:property
									value="#roleModel.getStateStr()" /></a></td>
						<td>
							<s:if test="#roleModel.state == 1">
								<a
									href="${pageScope.basePath}manager/user/role/update.do?role.id=<s:property value="#roleModel.getId()"/>"
									class="tablelink">修改</a>
							</s:if></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<%@include file="/manager/common/list/page.jsp" %>
		</div>
		<%@ include file="/Backstage/common/ajaxMask.jsp" %>
</body>
</html>
