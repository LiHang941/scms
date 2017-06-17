<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统运行记录列表</title>
<%@ include file="/Backstage/common/backstage.jsp"%>
<script type="text/javascript">
var _basePath = "${pageScope.basePath}";
	var _currentPage = <s:property value="page.currentPage"/>; //当前页码
	var _TotalPage = <s:property value="page.totalPage"/>; //总页码

	$(document).ready(function() {
		
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
  
});
	//跳转指定页码的通用方法
	function goByPage(_PageIndex){
		if(_PageIndex <= 0 && _PageIndex > _TotalPage){
			alert("您要跳转的页码不存在");
			return;
		}
		var _url = _basePath + "manager/system/systemLog/list.do?page.p="+_PageIndex;
		window.location.href=_url;
	}
</script>


</head>


<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="${pageScope.basePath}manager/index.do">首页</a></li>
			<li>系统运行日志管理</li>
		</ul>
	</div>
	<div class="rightinfo">

		<div class="tools">

		</div>
	<table class="tablelist">
		<thead>
			<tr>
				
				<th>文件名</th>
				<th>时间</th>
				<th>大小</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="systemLogList" id="systemLogModel"
				status="list">
				<tr>
					<td><s:property
							value="#systemLogModel.file.getName()" /></td>
					<td><s:date name="#systemLogModel.date"
							format="yyyy-MM-dd HH:mm:ss" /></td>
					<td><s:property
							value="#systemLogModel.file.length()" />字节</td>		
					<td><a
						href="${pageScope.basePath}manager/system/systemLog/down.do?fileName=<s:date name="#systemLogModel.date"
							format="yyyy-MM-dd" />"
						class="tablelink">下载</a></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/manager/common/list/page.jsp" %>
	</div>
	<%@ include file="/Backstage/common/ajaxMask.jsp" %>
</body>
</html>
