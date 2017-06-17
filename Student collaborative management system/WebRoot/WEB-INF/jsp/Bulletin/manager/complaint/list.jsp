<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公告管理</title>
<%@ include file="/Backstage/common/backstage.jsp"%>
<script type="text/javascript"> 
var _basePath = "${pageScope.basePath}";
var _currentPage = <s:property value="page.currentPage"/>; //当前页码
var _TotalPage = <s:property value="page.totalPage"/>; //总页码
var _form = $("#form1");
//跳转指定页码的通用方法
function goByPage(_PageIndex){
	if(_PageIndex <= 0 && _PageIndex > _TotalPage){
		alert("您要跳转的页码不存在");
		return;
	}
	
}
function doDelete(_id){
	var _url = _basePath + "manager/bulletin/complaint/delete.do?complaint.id="+_id;
	$.ajax({type: "POST",
		   url: _url,
		   dataType:'json',
		 success: function(jsondata){
			alert(jsondata.msg1);
			if(jsondata.state=="success"){
				$("#tr"+_id).remove();
			}
		}
	});
}
function doShow(_id){
	var _url = _basePath + "manager/bulletin/complaint/showUI.do?complaint.id="+_id;
	_form.attr("action",_url);
	_form.submit();
}
function doHandle(_id){
	var _url = _basePath + "manager/bulletin/complaint/handle.do?complaint.id="+_id;
	$.ajax({type: "POST",
		   url: _url,
		   dataType:'json',
		 success: function(jsondata){
			alert(jsondata.msg1);
			if(jsondata.state=="success"){
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
			<li>反馈管理</li>
		</ul>
	</div>

	<div class="rightinfo">
		<form id="form1" method="post" action="">
			<div class="tools">

				<ul class="toolbar">
					<li class="click"><span>问题</span> <select
						name="complaint.problemContent">
							<option>123</option>
							<option>123</option>
							<option>123</option>
							<option>123</option>
					</select></li>
				</ul>

				<ul class="toolbar1">
					<li style="padding-right:0px;"><input
						style="height:33px;width: 120px;" name="searchStr" type="text"
						value="<s:property value="searchStr" />" /></li>
					<li><a href="javascript:doSearch()"><span><img
								src="${pageScope.basePath}Backstage/images/search.png" /></span>搜索</a></li>
				</ul>
			</div>


			<table class="tablelist">
				<thead>
					<tr>
						<th style="width:50%;">标题</th>
						<th style="width:10%;">问题</th>
						<th style="width:20%;">反馈时间</th>
						<th style="width:10%;">状态</th>
						<th style="width:10%;">操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="complaintList" id="complaintModel" status="list">
						<tr id="tr<s:property value='#complaintModel.id'/>">
							<td><s:property value="#complaintModel.getTitle()" /></td>
							<td><s:property value="#complaintModel.problemContent" /></td>
							<td><s:date name="#complaintModel.date"
									format="yyyy-MM-dd HH:mm" /></td>
							<td><s:property
									value="#request.COMPLAINT_STATE_MAP[#complaintModel.state]" /></td>
							<td><a
								href="javascript:doDelete('<s:property value='#complaintModel.id'/>')"
								class="tablelink">删除</a> <a
								href="javascript:doShow('<s:property value='#complaintModel.id'/>')"
								class="tablelink">查看</a> <a
								href="javascript:doHandle('<s:property value='#complaintModel.id'/>')"
								class="tablelink">处理</a></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<%@include file="/manager/common/list/page.jsp"%>
	</div>

	</form>
	<%@ include file="/Backstage/common/ajaxMask.jsp"%>
	<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
