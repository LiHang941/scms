<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公告类别列表</title>
<%@ include file="/Backstage/common/backstage.jsp"%>
<script type="text/javascript">
var _basePath = "${pageScope.basePath}";
	var _currentPage = <s:property value="page.currentPage"/>; //当前页码
	var _TotalPage = <s:property value="page.totalPage"/>; //总页码
	var _SearchStr = ""; //搜索字串

	$(document).ready(function() {
		 _SearchStr =  $(".toolbar1 input").val();
		$(".tablelink").click(function() {
			var _url = $(this).attr('href');
			//alert(_url);
			if( _url.indexOf("show") != -1){
				window.open(_url);
				return false;
			}
			
			if (_url.indexOf("update") != -1  ){
				window.location.href=_url +"&upperURL=" +window.location.href;
			}else{
				$.get(_url, null, function(data) {
					alert(data.msg1);
					if (data.state == 'success') {
						window.location.reload();
					}
				}, 'json');
			}

			return false;
		});
		
		
		//搜索
   $(".toolbar1 a").click(function(){
   		var _search = $(".toolbar1 input").val();
   		if(_search == ""){
   			alert("请输入搜索内容");
   			return false;
   		}
   		var _url = $(this).attr('href');
   		window.location.href=_url + encodeURI(_search);
    	return false; 
   });
   
   $("#addSelect").click(function(){
   	var _url = _basePath + "manager/bulletin/information/add.do";
   	window.location.href=_url +"?upperURL=" +window.location.href;;
   });
});
//跳转指定页码的通用方法
function goByPage(_PageIndex){
	if(_PageIndex <= 0 && _PageIndex > _TotalPage){
		alert("您要跳转的页码不存在");
		return;
	}
	
	var _url = _basePath + "manager/bulletin/information/list.do?page.p="+_PageIndex;
	if(_SearchStr != ""){
		_url = _url + "&searchStr="+encodeURI(_SearchStr);
	}
	window.location.href=_url;
}
</script>


</head>


<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="${pageScope.basePath}manager/index.do">首页</a></li>
			<li>消息管理</li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="tools">

			<ul class="toolbar">
				<li class="click" id="addSelect"><span><img
						src="${pageScope.basePath}Backstage/images/t01.png" /></span>添加</li>
			</ul>
			<ul class="toolbar1">
				<li style="padding-right:0px;"><input
					style="height:33px;width: 120px;" type="text" value="<s:property value="searchStr" />" /></li>
				<a href="${pageScope.basePath}manager/bulletin/information/list.do?searchStr="><li><span><img
							src="${pageScope.basePath}Backstage/images/search.png" /></span>搜索</li></a>
			</ul>
		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th>标题</th>
					<th>用户</th>
					<th>状态</th>
					<th>查看次数</th>
					<th>时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="informationList" id="informationModel" status="list">
					<tr id="informationId<s:property value="#informationModel.id"/>">
						<td><s:property value="#informationModel.title" /></td>
						<td><s:property value="#informationModel.user.account" /></td>
						<td><s:property value="#informationModel.getStateStr()" /></td>
						<td><s:property value="#informationModel.browseInforMationCount" /></td>
						<td><s:date name="#informationModel.date" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td><a
							href="${pageScope.basePath}manager/bulletin/information/delete.do?information.id=<s:property value="#informationModel.id"/>"
							class="tablelink">删除</a> 
							<a
							href="${pageScope.basePath}manager/bulletin/information/update.do?information.id=<s:property value="#informationModel.id"/>"
							class="tablelink">修改</a> 
							<a
							href="${pageScope.basePath}manager/bulletin/information/remind.do?information.id=<s:property value="#informationModel.id"/>"
							class="tablelink">提醒</a> 
							<a
							href="${pageScope.basePath}manager/bulletin/information/show.do?information.id=<s:property value="#informationModel.id"/>"
							class="tablelink">查看</a> 
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<%@include file="/manager/common/list/page.jsp" %>
	</div>
	<%@ include file="/Backstage/common/ajaxMask.jsp" %>
</body>
</html>
