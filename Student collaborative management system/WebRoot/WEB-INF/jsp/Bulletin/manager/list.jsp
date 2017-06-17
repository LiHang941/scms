<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公告管理</title>
<%@ include file="/Backstage/common/backstage.jsp" %>
<script type="text/javascript" > 
var _basePath = "${pageScope.basePath}";
var _currentPage = <s:property value="page.currentPage"/>; //当前页码
var _TotalPage = <s:property value="page.totalPage"/>; //总页码
var _SearchStr = ""; //搜索字串
$(document).ready(function(){
  _SearchStr =  $(".toolbar1 input").val();
  	//逻辑业务处理
  $(".tablelink").click(function(){
	var _url = $(this).attr('href');
	if(_url.indexOf("show")!=-1 || _url.indexOf("update")!=-1){
		if(_url.indexOf("show")!=-1){
			window.open(_url);
		}else{
			window.location.href=_url + "&upperURL=" +window.location.href;;
		}
	}else{
		$.get(_url,null,function(data){
			alert(data.msg1);
		 	if(data.state == 'success'){
		 		window.location.reload();
		 	}
		 },'json');
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
  
  //全选  全部取消选中
  $(".tablelist thead input").click(function(){
  		//单击后该属性就变化
   		var _theadChecked = this.checked;
   		
   		var $tbodyInputs =  $(".tablelist tbody input");
   		
   		$.each($tbodyInputs,function(){
   			 $(this).attr("checked",_theadChecked); 
   		});
   });
   	//添加
  $(".click").click(function(){
  	var _url = _basePath + "manager/bulletin/add.do?&upperURL="+window.location.href;
	 window.location.href=_url;
  });
});

//跳转指定页码的通用方法
function goByPage(_PageIndex){
	if(_PageIndex <= 0 && _PageIndex > _TotalPage){
		alert("您要跳转的页码不存在");
		return;
	}
	var _url = _basePath + "manager/bulletin/list.do?page.p="+_PageIndex;
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
    <li>公告管理</li>
 
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li class="click"><span><img src="${pageScope.basePath}Backstage/images/t01.png" /></span>添加公告</li>
        </ul>
        
        
        <ul class="toolbar1">
        <li style="padding-right:0px;"><input style="height:33px;width: 120px;" type="text" value="<s:property value="searchStr" />"/></li>
        <li><a href="${pageScope.basePath}manager/bulletin/list.do?searchStr="><span><img src="${pageScope.basePath}Backstage/images/search.png"/></span>搜索</a></li>
        </ul>
    
    </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th style="width:4%;"><input name="tbodycheckbox" type="checkbox" value="" /></th>
        <th style="width:45%;">标题</th>
        <th style="width:10%;">用户</th>
        <th style="width:10%;">类别</th>
        <th style="width:10%;">发布时间</th>
        <th style="width:6%;">浏览次数</th>
        <th style="width:5%;">状态</th>
        <th style="width:10%;">操作</th>
        </tr>
        </thead>
        <tbody>
 		<s:iterator value="bulletinList" id="bulletinModel" status="list">
	        <tr>
	        <td><input name="" type="checkbox" value="<s:property value="#bulletinModel.getId()" />" /></td>
	        <td><s:property value="#bulletinModel.getTitle()" /></td>
	        <td><s:property value="#bulletinModel.getUser().account" /></td>
	        <td><s:property value="#bulletinModel.getCategory().getName()" /></td>
	        <td><s:date name="#bulletinModel.getDate()" format="yyyy-MM-dd"/></td>
	        <td><s:property value="#bulletinModel.browseInforMationCount" /></td>
	       
	        <td><a href="${pageScope.basePath}manager/bulletin/state.do?b.id=<s:property value="#bulletinModel.getId()" />" class="tablelink"><s:property value="#bulletinModel.getStateStr()" /></a></td>
	        <td>
				<a href="${pageScope.basePath}manager/bulletin/delete.do?b.id=<s:property value="#bulletinModel.getId()" />" class="tablelink">删除</a>
				<s:if test="#bulletinModel.getState()== #application.BULLETIN_STATE_TRUE">
					<a href="${pageScope.basePath}manager/bulletin/update.do?b.id=<s:property value="#bulletinModel.getId()" />" class="tablelink" >修改</a>
		        	<a href="${pageScope.basePath}bulletin/show/<s:property value="#bulletinModel.getId()" />.html" class="tablelink">查看</a>
	        	</s:if>
	        </td>
	        </tr>
         </s:iterator>
        </tbody>
    </table>
  <%@include file="/manager/common/list/page.jsp" %>
    </div>
    <%@ include file="/Backstage/common/ajaxMask.jsp" %>
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
