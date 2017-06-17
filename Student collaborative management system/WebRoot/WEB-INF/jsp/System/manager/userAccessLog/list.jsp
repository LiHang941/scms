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
		//
		$(".tablelink").click(function() {
			var _url = $(this).attr('href');
			//alert(_url);
			if (_url.indexOf("update") != -1 || _url.indexOf("update") != -1) {
				window.location.href = _url;
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
   //全选  全部取消选中
  $(".tablelist thead input").click(function(){
  		//单击后该属性就变化
   		var _theadChecked = this.checked;
   		var $tbodyInputs =  $(".tablelist tbody input");
   		$.each($tbodyInputs,function(){
   			 $(this).attr("checked",_theadChecked); 
   		});
   });
	  
    $(".tiptop a").click(function(){
  		$(".tip").fadeOut(200);
	});
	
	  	//批量删除
  $("#deleteSelect").click(function(){
	  $("#userHandle .tipright p").text("是否确认对下列信息的删除?");
	  $("#userHandle .tipright cite").text("如果是请点击确定按钮 ，否则请点取消。");
	  $("#userHandle .tipinfo span").html("<img src='"+_basePath+"Backstage/images/ticon.png' />");
	  $("#userHandle .tipbtn").css("display","inline");
	  $("#userHandle").fadeIn(200);
  });
	//确定
  $(".sure").click(function(){
  		$("#userHandle .tipbtn").css("display","none");
  		$("#userHandle .tipinfo span").html("<img src='"+_basePath+"Backstage/images/loaging.gif' />");
  		$("#userHandle .tipright cite").text("");
 		var $tbodyInputs =  $(".tablelist tbody input");
 		var size = 0;
 		$tbodyInputs.each(function(){
 			 if(this.checked){
 			 	size = size + 1;
 			 }
 		})
 		if(size == 0){
 			$("#userHandle").fadeOut(100);
 			return ;
 		}
 		
 		var index = 0;
 		var successCount = 0;
 		var errorCount = 0;
 		$tbodyInputs.each(function(){
   			 if(this.checked){
   			 	$("#userHandle .tipright p").text("正在执行{成功:"+successCount+"||失败:"+errorCount+"}");
   			 	var _id = $(this).val();
   			 	var _url =_basePath +"manager/system/userAccessLog/delete.do?userAccessLog.id="+_id;
   			 	
   			 	$.get(_url,null,function(data){
					//alert(data.msg1);
				 	if(data.state == 'success'){
				 		successCount = successCount +1 
				 	}else{
				 		errorCount = errorCount +1 ;
				 	}
				 	$("#userHandle .tipright p").text("正在执行{成功:"+successCount+"||失败:"+errorCount+"}");	
				 	index = index + 1;
				 	if(index == size  ){
				 		if(size > errorCount){
				 			setTimeout(function(){window.location.reload();}, 2000);
				 		}else{
				 			setTimeout(function(){$("#userHandle").fadeOut(100);}, 2000);
				 			
				 		}
				 	}
				 },'json');
   			 }
   			  
   		});
   		
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
		
		var _url = _basePath + "manager/system/userAccessLog/list.do?page.p="+_PageIndex;
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
			<li>登录记录管理</li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="tools">

			<ul class="toolbar">
				<li class="click" id="deleteSelect"><span><img
						src="${pageScope.basePath}Backstage/images/t03.png" /></span>批量删除</li>
			</ul>


			<ul class="toolbar1">
				<li style="padding-right:0px;"><input value="<s:property value="searchStr" />"
					style="height:33px;width: 120px;" type="text" /></li>
				<li><a href="${pageScope.basePath}manager/system/userAccessLog/list.do?searchStr="><span><img
							src="${pageScope.basePath}Backstage/images/search.png" /></span>搜索</a></li>
			</ul>

		</div>


		<table class="tablelist">
			<thead>
				<tr>
					<th style="width:5%;"><input name="tbodycheckbox"
						type="checkbox" value="" /></th>
					<th style="width:25%;">用户名</th>
					<th style="width:20%;">时间</th>
					<th style="width:15%;">IP地址</th>
					<th style="width:40%;">登录设备</th>
					<th style="width:20%;">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="userAccessLogList" id="userAccessLogModel" status="list">
					<tr>
						<td><input name="" type="checkbox"
							value="<s:property value="#userAccessLogModel.id"/>" /></td>
						<td><s:property value="#userAccessLogModel.user.account" /></td>
						<td><s:date name="#userAccessLogModel.date" format="yyyy-MM-dd HH:mm:ss"/></td>
						<td><s:property value="#userAccessLogModel.ip" /></td>
						<td><s:property value="#userAccessLogModel.systemInfo" /></td>
						<td><a
							href="${pageScope.basePath}manager/system/userAccessLog/delete.do?userAccessLog.id=<s:property value="#userAccessLogModel.id"/>"
							class="tablelink">删除</a> 
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>


		<%@include file="/manager/common/list/page.jsp" %>


		<div class="tip" id="userHandle">
			<div class="tiptop">
				<span>提示信息</span>
			</div>
			<div class="tipinfo">
				<span><img src="${pageScope.basePath}Backstage/images/ticon.png" /></span>
				<div class="tipright">
					<p>是否确认对下列信息的删除?</p>
					<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
				</div>
			</div>
			<div class="tipbtn">
				<input name="" type="button" class="sure" value="确定" />&nbsp; <input
					name="" type="button" class="cancel" value="取消" />
			</div>

		</div>
	</div>
	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	<%@ include file="/Backstage/common/ajaxMask.jsp" %>
</body>
</html>
