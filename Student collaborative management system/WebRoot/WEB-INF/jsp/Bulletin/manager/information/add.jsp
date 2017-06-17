<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<%@ include file="/Backstage/common/backstage.jsp" %>

<link href="${pageScope.basePath}Backstage/css/select.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${pageScope.basePath}Backstage/js/jquery.idTabs.min.js"></script>
<script type="text/javascript"
	src="${pageScope.basePath}Backstage/js/select-ui.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageScope.basePath}Backstage/editor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageScope.basePath}Backstage/editor/ueditor.all.min.js">
	
</script>
<script type="text/javascript" charset="utf-8"
	src="${pageScope.basePath}Backstage/editor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
	var _basePath = "${pageScope.basePath}";
	var ue = UE.getEditor('content');
	$(document).ready(function(e) {
		$(".select1").uedSelect({
			width : 518
		});
			//提交按钮事件
		$('#btnSave').click(
			function (){
				var _url = _basePath + "manager/bulletin/information/save.do";
		//		var _title = $("#title").val();
		//		var _state = $("#state option:selected").val();
		//		var _roles = $(".modularcheckbox ")
		//		var _date = "";
				//alert($("#dataFrom").serialize());
				$.ajax({  type: "POST",
						   url: _url,
						   data: $("#dataFrom").serialize(),
						   dataType:'json',
						 success: function(jsondata){
    						alert(jsondata.msg1);
    						if(jsondata.state=="success"){
	    						showUpper();
    						}
						}
				});
			}
		);
		
		
		
	});
</script>


</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="${pageScope.basePath}manager/index.do" target="rightFrame">首页</a></li>
			<li><a href="${pageScope.basePath}manager/bulletin/information/list.do" target="rightFrame">消息管理</a></li>
			<li>发布消息</li>
		</ul>
		<%@ include file="/Backstage/common/upperUrl.jsp" %>
	</div>
	<div class="formbody">
		<div id="usual1" class="usual">
			<div id="tab1" class="tabson">
				<%@ include file="/manager/common/InformationUserName.jsp" %>
				<form id="dataFrom">
				<ul class="forminfo">
					<li><label>消息标题<b>*</b></label><input id="title" type="text"
						class="dfinput"  style="width:518px;" name = "information.title" /></li>
						
					<li><label>消息状态类型<b>*</b></label>
						<div class="vocation">
							<select class="select1" id = "state" name = "information.state">
								<s:iterator value="#request.INFORMATION_STATE_MAP">
									<option value="<s:property value="key" />"><s:property value="value" /></option>
								</s:iterator>
							</select>
						</div></li>
					<li><label>接受的角色<b>*</b></label></li>
					<li>
						<div
							style="width:600px;height:auto;border:1px solid #cccccc;padding: 20px;">
						  	<table style="table-layout:fixed ;" id="roles"> 
						  		<tr>
						  			<th width="5%"></th>
						  			<th width="45%"></th>
						  			<th width="5%"></th>
						  			<th width="45%"></th>
						  		</tr>
								<%-- <s:checkboxlist list="roles" listKey="id"   
                                listValue="name" value="roleId" name="roleId"/>
								 --%>
								 <tr>
									<s:iterator value="roles" id="roleModel" status="list">
										<s:if test="((#list.index)%2)==0">
								</tr>
								<tr>
									</s:if>
									<td><input
										class="modularcheckbox" type="checkbox"
										value="<s:property value="#roleModel.getId()"/>" name = "roles.id" /></td>
									<td style="padding:10px;"><s:property
											value="#roleModel.getName()" /> </td> 
									</s:iterator>
								</tr>
							</table> 
							
						</div>
					</li>
					
					<li><label>消息内容<b>*</b></label></li>
					<li><script id="content" name="information.content" type="text/plain"
							style="width:700px;height:250px;"></script></li>
					<li><label>&nbsp;</label> <input type="button"
						id="btnSave" class="btn" value="马上发布" /></li>
				</ul>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/Backstage/common/ajaxMask.jsp" %>
</body>
</html>
