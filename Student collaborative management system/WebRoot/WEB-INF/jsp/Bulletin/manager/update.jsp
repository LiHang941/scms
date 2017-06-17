<%@page import="com.scms.bulletin.entity.Category"%>
<%@ page language="java" import="java.util.*,com.scms.currency.service.*" pageEncoding="UTF-8"%>
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
	$(document).ready(function(){
		$(".select1").uedSelect({
			width : 345
		});
			//提交按钮事件
		$('.btn').click(
			function (){
				var _url =_basePath +  "manager/bulletin/save.do";
				$.ajax({  type: "POST",
							   url: _url,
							   data: $("#dataForm").serialize(),
							   dataType:'json',
							 success: function(jsondata){
	    						alert(jsondata.msg1);
	    						if(jsondata.state == "success"){
	    							showUpper();
	    						}else{
	    						
	    						}
							}
					});
			}
		);
		
		var content =$('#bulletinContent').val();
        //判断ueditor 编辑器是否创建成功
        ue.addListener("ready", function () {
        // editor准备好之后才可以使用
        ue.setContent(content);
        
        });
	});
	function countDown(secs,surl){     
		 if(--secs>0){     
		     setTimeout("countDown("+secs+",'"+surl+"')",1000);     
		 } else{       
			 $(".tip").fadeOut(100);
			     location.href=surl;     
		    }     
		 }
</script>


</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<%@ include file="/manager/common/bulletin/bulletin_manager_li.jsp" %>
			<li>发布公告</li>
		</ul>
		<%@ include file="/Backstage/common/upperUrl.jsp" %>
	</div>

	<div class="formbody">


		<div id="usual1" class="usual">



			<div id="tab1" class="tabson">
				<%@ include file="/manager/common/BulletinUserName.jsp" %>
				<form id="dataForm">
				<ul class="forminfo">
					<input type="hidden" name = "b.id" value="<s:property value="b.id" />" />
					<li><label>公告标题<b>*</b></label><input type="text" name="b.title"
						class="dfinput"  style="width:518px;" value="<s:property value="b.title" />"/></li>
					<li><label>公告备注</label> <input type="text" name="b.record"
						class="dfinput" style="width:518px;" value="<s:property value="b.record" />"/></li>
					<li><label>公告类别<b>*</b></label>
						<div class="vocation">
							<select class="select1" name="b.category.id">
								<s:iterator value="#request.CATEGORY_LIST" id="categoryModel" status="list">
									<option value="<s:property value="#categoryModel.getId()" />"
									<s:if test = "b.category.id == #categoryModel.getId()">
										selected="selected"
									</s:if>
									><s:property value="#categoryModel.getName()" /></option>
								</s:iterator>
							</select>
						</div>
						</li>

					<li><label>公告内容<b>*</b></label></li>
					<input type="hidden" value="<s:property value="b.content" />" id="bulletinContent"/>
					<li><script id="content" name="b.content" type="text/plain"
							style="width:700px;height:250px;"></script></li>
					<li><label>&nbsp;</label> <input type="button"
						class="btn" value="马上修改" /></li>
				</ul>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/Backstage/common/ajaxMask.jsp" %>
</body>
</html>
