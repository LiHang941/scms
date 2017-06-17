<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录记录列表</title>
<%@ include file="/Backstage/common/backstage.jsp"%>
<script type="text/javascript">
var _basePath = "${pageScope.basePath}";
	var _currentPage = <s:property value="page.currentPage"/>; //当前页码
	var _TotalPage = <s:property value="page.totalPage"/>; //总页码
	var _SearchStr = ""; //搜索字串

	$(document).ready(function() {
		 _SearchStr = $(".toolbar1 input").val();
		
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
 
});
	//跳转指定页码的通用方法
	function goByPage(_PageIndex){
		if(_PageIndex <= 0 && _PageIndex > _TotalPage){
			alert("您要跳转的页码不存在");
			return;
		}
		var _url = _basePath + "manager/currency/myInformation/listUI.do?page.p="+_PageIndex;
		if(_SearchStr != ""){
			_url = _url + "&searchStr="+encodeURI(_SearchStr);
		}
		window.location.href=_url;
	}
	
	function showUI(id){
		var _url = _basePath + "manager/currency/myInformation/showUI.do?information.id="+id;
		window.open(_url);
	}
	function deleteId(id){
		var _url = _basePath + "manager/currency/myInformation/delete.do?information.id="+id;
		$.ajax({
		  url: _url,
		  type: "GET",
		  dataType:'json',
		  success: function(jsonData){
		   	alert(jsonData.msg1);
		   	if(jsonData.state == 'success'){
		   		window.location.reload();
		   	}
		  }
		});
		}
	
</script>


</head>


<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="${pageScope.basePath}manager/index.do">首页</a></li>
			<li>收件箱</li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="tools">

			<ul class="toolbar1">
				<li style="padding-right:0px;"><input value="<s:property value="searchStr" />"
					style="height:33px;width: 120px;" type="text" /></li>
				<li><a href="${pageScope.basePath}manager/currency/myInformation/listUI.do?searchStr="><span><img
							src="${pageScope.basePath}Backstage/images/search.png" /></span>搜索</a></li>
			</ul>

		</div>


		<table class="tablelist">
			<thead>
				<tr>
					<th style="width:40%;">标题</th>
					<th style="width:15%;">信息人</th>
					<th style="width:15%;">时间</th>
					<th style="width:10%;">消息状态</th>
					<th style="width:10%;">状态</th>
					<th style="width:10%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="informationAndUserList" id="informationAndUserModel" status="list">
					<tr>
						<td><s:property value="#informationAndUserModel.information.title" /></td>
						<td><s:property value="#informationAndUserModel.information.user.name" /></td>
						<td><s:date name="#informationAndUserModel.information.date" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td><s:property value="#informationAndUserModel.information.getStateStr ()" /></td>
						<td><s:property value="#informationAndUserModel.getStateStr ()" /></td>
						<td>
							<a
							class="tablelink" onclick="showUI(<s:property value="#informationAndUserModel.information.id" />)">查看</a>
							<a
							class="tablelink" onclick="deleteId(<s:property value="#informationAndUserModel.information.id" />)">删除</a> 
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
