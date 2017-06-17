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
	$(document).ready(function() {
		//
		$(".tablelink").click(function() {
			var _url = $(this).attr('href');
			//alert(_url);
			if (_url.indexOf("delete") != -1){
				$.get(_url, null, function(data) {
					alert(data.msg1);
					if (data.state == 'success') {
						window.location.reload();
					}
				}, 'json');
			}

			return false;
		});
		
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
   
  $("#addSelect").click(function(){
  	   $("#categoryName").val("");
	  $("#userHandle").fadeIn(200);
  });
  
  
  $(".sure").click(function(){
  	var _categoryName = $("#categoryName").val();
  	if(_categoryName == ""){
  		alert("请输入公告类别名称");
  		return;
  	}
  	//保存
  	var _url = _basePath+ "manager/bulletin/category/save.do";
  	var _data = {"category.id":_categoryId,"category.name":_categoryName};
  	$.post(_url, _data, function(data) {
					alert(data.msg1);
					if (data.state == 'success') {
						window.location.reload();
					}
				}, 'json');
  });
  
  
  
  //取消按钮
  $(".cancel").click(function(){
  	$(".tip").fadeOut(100);
  });
});
	//跳转指定页码的通用方法
	function goByPage(_PageIndex){
		if(_PageIndex <= 0 && _PageIndex > _TotalPage){
			alert("您要跳转的页码不存在");
			return;
		}
		var _url = _basePath + "manager/bulletin/category/list.do?page.p="+_PageIndex;
		if(_SearchStr != ""){
			_url = _url + "&searchStr="+encodeURI(_SearchStr);
		}
		window.location.href=_url;
	}
	var _categoryId = 0;
	function update(_id){
		_categoryId = _id;
		var _categoryName = $("#categoryID"+_id + " .categoryName").text();
		$("#categoryName").val(_categoryName);
		$("#userHandle").fadeIn(200);
	}
	
</script>


</head>


<body>
	
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<%@ include file="/manager/common/bulletin/bulletin_manager_li.jsp" %>
			<li>公告类别管理</li>
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
					style="height:33px;width: 120px;" type="text" value="<s:property value="searchStr" />"/></li>
				<li><a href="${pageScope.basePath}manager/bulletin/category/list.do?searchStr="><span><img
							src="${pageScope.basePath}Backstage/images/search.png" /></span>搜索</a></li>
			</ul>

		</div>


		<table class="tablelist">
			<thead>
				<tr>
					<th>编号</th>
					<th>类别名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="categoryList" id="categoryModel" status="list">
					<tr id="categoryID<s:property value="#categoryModel.id"/>">
						<td><s:property value="#categoryModel.id" /></td>
						<td class = "categoryName"><s:property value="#categoryModel.getName()" /></td>
						<td><a
							href="${pageScope.basePath}manager/bulletin/category/delete.do?category.id=<s:property value="#categoryModel.id"/>"
							class="tablelink">删除</a> 
							<a
							href="javascript:;" onclick="update('<s:property value="#categoryModel.id"/>')"
							class="tablelink">修改</a> 
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>

		<%@include file="/manager/common/list/page.jsp" %>

		<div class="tip" id="userHandle">
			<div class="tiptop">
				<span>提示信息:添加(修改)公告类别名称</span>
			</div>
			<div class="tipinfo">
				<input  id="categoryName" type="text" style="width:68%;
					height: 30px;border:solid #ccc 1px;border-radius: 3px;margin-top: 15px;
					margin-left:32px;margin-bottom: 20px;" placeholder="公告类别名称"/>
			</div>
			<div class="tipbtn">
				<input name="" type="button" class="sure" value="保存" />&nbsp; <input
					name="" type="button" class="cancel" value="取消" />
			</div>

		</div>
	</div>
	<%@ include file="/Backstage/common/ajaxMask.jsp" %>
</body>
</html>
