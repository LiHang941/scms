<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户列表</title>
<%@ include file="/Backstage/common/backstage.jsp"%>
<script type="text/javascript">
var _basePath = "${pageScope.basePath}";
	var _currentPage = <s:property value="page.currentPage"/>; //当前页码
	var _TotalPage = <s:property value="page.totalPage"/>; //总页码
	var _SearchStr = ""; //搜索字串

	$(document).ready(function() {
		  _SearchStr =  $(".toolbar1 input").val();
		//
		$(".tablelink").click(function() {
			var _url = $(this).attr('href');
			//alert(_url);
			if (_url.indexOf("update") != -1 || _url.indexOf("showUserInfo") != -1) {
				window.location.href = _url  + "&upperURL=" +window.location.href;;
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
   	
	  $("#export").click(function(){
	  		$("#userExport").fadeIn(200);
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
   			 	var _url =_basePath +"manager/user/delete.do?user.id="+_id;
   			 	
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
  
  	$("#import").click(function(){
  		if($(".toolbar form input").val()!=""){
  			$(".toolbar form").submit();
  		}else{
  			alert("请选择文件");
  		}
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
		
		var _url = _basePath + "manager/user/list.do?page.p="+_PageIndex;
		if(_SearchStr != ""){
			
			_url = _url + "&searchStr="+encodeURI(_SearchStr);
		}
		window.location.href=_url;
	}
	//导出
	function userexport(_type){
		var _url = _basePath + "manager/user/export.do?type="+_type;
		if(_type == 1){
			_url = _url + "&page.p="+_currentPage;
			if(_SearchStr != ""){
				_url = _url +  "&searchStr="+_SearchStr;
			}
		}
		
		if(_type == 3){
			var _temp = "";
			$(".tablelist tbody input").each(function (){
				if(this.checked){
   			 		_temp = _temp + $(this).val();
   			 	}
			});
			_url = _url + "&page.p="+_currentPage;
			if(_temp != ""){
				_url = _url +  "&searchStr="+_temp;
			}
		}
		window.open(_url);
	}
	
	
</script>


</head>


<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="${pageScope.basePath}manager/index">首页</a></li>
			<li>用户管理</li>
		</ul>
		
	</div>

	<div class="rightinfo">

		<div class="tools">

			<ul class="toolbar">
				<li class="click" id="deleteSelect"><span><img
						src="${pageScope.basePath}Backstage/images/t03.png" /></span>批量删除</li>
				<li class="click" id="export"><span><img
						src="${pageScope.basePath}Backstage/images/ico04.png" /></span>导出</li>	
				<li class="click" id="import"><span><img
						src="${pageScope.basePath}Backstage/images/ico02.png" /></span>导入</li>	
				<li class="click" style="padding:0px 10px;"><form action="${pageScope.basePath}manager/user/importUser.do" method="post" enctype="multipart/form-data"><input name="excel" value="" type="file" /></form></li>			
			</ul>


			<ul class="toolbar1">
				<li style="padding-right:0px;"><input value="<s:property value="searchStr" />"
					style="height:33px;width: 120px;" type="text" /></li>
				<li><a href="${pageScope.basePath}manager/user/list.do?searchStr="><span><img
							src="${pageScope.basePath}Backstage/images/search.png" /></span>搜索</a></li>
			</ul>

		</div>


		<table class="tablelist">
			<thead>
				<tr>
					<th style="width:5%;"><input name="tbodycheckbox"
						type="checkbox" value="" /></th>
					<th style="width:15%;">账号</th>
					<th style="width:15%;">用户名</th>
					<th style="width:15%;">角色</th>
					<th style="width:10%;">时间</th>
					<th style="width:10%;">是否强制修改密码</th>
					<th style="width:10%;">是否锁定</th>
					<th style="width:20%;">操作</th>
				</tr>
			</thead>
			<tbody>
		
				<s:iterator value="userList" id="userModel" status="list">
					<tr>
						<td><input name="" type="checkbox"
							value="<s:property value="#userModel.getId()"/>" /></td>
						<td><s:property value="#userModel.account" /></td>
						<td><s:property value="#userModel.getName()" /></td>
						<td><s:property value="#userModel.role.name" /></td>
						<td><s:date name="#userModel.date" format="yyyy-MM-dd"/></td>
						<td><a
							href="${pageScope.basePath}manager/user/modifyPassword.do?user.id=<s:property value="#userModel.getId()"/>"
							class="tablelink"><s:property
									value="#userModel.getModifyPasswordStr ()" /></a></td>
						<td><a
							href="${pageScope.basePath}manager/user/locking.do?user.id=<s:property value="#userModel.getId()"/>"
							class="tablelink"><s:property
									value="#userModel.getLockingStr ()" /></a></td>
						
						<td><a
							href="${pageScope.basePath}manager/user/delete.do?user.id=<s:property value="#userModel.getId()"/>"
							class="tablelink">删除</a> 
							<s:if test="#userModel.getIsLocking() == -1">
								<%
									//未公开就不能查看
								%>
								<a
									href="${pageScope.basePath}manager/user/update.do?user.id=<s:property value="#userModel.getId()"/>"
									class="tablelink">编辑</a>
								<a
									href="${pageScope.basePath}manager/user/updatePassword.do?user.id=<s:property value="#userModel.getId()"/>"
									class="tablelink">修改密码</a>
								<a
									href="${pageScope.basePath}manager/user/showUserInfo.do?user.id=<s:property value="#userModel.getId()"/>"
									class="tablelink">个人信息</a>
							</s:if></td>
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

		<div class="tip" id= "userExport">
			<div class="tiptop">
				<span>提示信息</span><a></a>
			</div>

			<div class="tipinfo">
				<span><img src="${pageScope.basePath}Backstage/images/ticon.png" /></span>
				<div class="tipright">
					<p>您正在使用导出功能</p>
					<cite>请选择</cite>
				</div>
			</div>

			<div class="tipbtn">
				<input onclick="userexport(1)" type="button" class="cancel" value="导出当前" />
				<input onclick="userexport(2)" type="button" class="cancel" value="全部导出" />&nbsp;
				<input onclick="userexport(3)" type="button" class="cancel" value="导出选中" />&nbsp;
			</div>

		</div>


	</div>
	
	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	<%@ include file="/Backstage/common/ajaxMask.jsp" %>
</body>
</html>
