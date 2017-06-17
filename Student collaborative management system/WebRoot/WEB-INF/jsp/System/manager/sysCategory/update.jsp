<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<%@ include file="/Backstage/common/backstage.jsp"%>
<link href="${pageScope.basePath}Backstage/css/select.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageScope.basePath}Backstage/js/jquery.idTabs.min.js"></script>
<script type="text/javascript"
	src="${pageScope.basePath}Backstage/js/select-ui.min.js"></script>

<script type="text/javascript">
	var _basePath = "${pageScope.basePath}";
	var _url = _basePath + "manager/system/sysCategory/save.do"
	$(document).ready(
			function(e) {
				$(".select1").uedSelect({
					width : 350
				});
				

				//添加按钮事件
				$(".btn").click(
						function() {
								$.ajax({  type: "POST",
							   url: _url,
							   data: $("#dataForm").serialize(),
							   dataType:'json',
							   success: function(jsondata){
			    					alert(jsondata.msg1);
			    					if (jsondata.state == "success") {
									window.location.reload();
									}
								}
							});
							/* var _sysCategoryId = $("#sysCategoryId").val();
							var _sysCategory1Id = $("#sysCategory1 option:selected").val();
							var _sysCategory2Id = $("#sysCategory2 option:selected").val();
							var _sysCategory3Id = $("#sysCategory3 option:selected").val();
							var _sysCategory = {'sysCategory.category1.id':_sysCategory1Id,
												'sysCategory.category2.id':_sysCategory2Id,
												'sysCategory.category3.id':_sysCategory3Id,
												'sysCategory.id':_sysCategoryId
												};
							$.post(_url, _sysCategory, function(data) {
								alert(data.msg1);
								if (data.state == "success") {
									window.location.reload();
								}
							}, 'json'); */

						});
			});
</script>


</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="${pageScope.basePath}manager/index.do"
				target="rightFrame">首页</a></li>
			
			<li>系统默认公告类别设置</li>
		</ul>
	</div>

	<div class="formbody">
		<div id="usual1" class="usual">
			<div id="tab1" class="tabson">
				<form id="dataForm" >
				<ul class="forminfo">
					<li><label>默认类别1<b>*</b></label><div class="vocation" >
					
							<input id="sysCategoryId" name="sysCategory.id" type="hidden" value="<s:property value="sysCategory.id"/>" />
							<select class="select1" id="sysCategory1" name="sysCategory.category1.id">
								<s:iterator value="categoryList" id="categoryModle">
									<option value="<s:property value="#categoryModle.id"/>"
									<s:if test="#categoryModle.id == sysCategory.category1.id">
										selected="selected"
									</s:if>
									><s:property value="#categoryModle.name"/></option>
								</s:iterator>
							</select>
						</div></li>
						<li><label>默认类别2<b>*</b></label><div class="vocation" >
							<select class="select1" id="sysCategory2" name="sysCategory.category2.id">
								<s:iterator value="categoryList" id="categoryModle">
									<option value="<s:property value="#categoryModle.id"/>"
									<s:if test="#categoryModle.id == sysCategory.category2.id">
									selected="selected"
									</s:if>
									><s:property value="#categoryModle.name"/></option>
								</s:iterator>
							</select>
						</div></li>
						<li><label>默认类别3<b>*</b></label><div class="vocation" >
							<select class="select1" id="sysCategory3" name="sysCategory.category3.id">
								<s:iterator value="categoryList" id="categoryModle">
									<option value="<s:property value="#categoryModle.id"/>"
									<s:if test="#categoryModle.id == sysCategory.category3.id">
										selected="selected"
									</s:if>
									><s:property value="#categoryModle.name"/></option>
								</s:iterator>
							</select>
						</div></li>
					<li><label>&nbsp;</label> <input type="button" class="btn"
						value="保存" /></li>
				</ul>
			</form>
			</div>
		</div>
	</div>
	<%@ include file="/Backstage/common/ajaxMask.jsp" %>
</body>
</html>
