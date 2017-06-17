<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>公告导航</title>
<%@ include file="/Reception/common/reception.jsp" %>
</head>
<script type="text/javascript">
	var _basePath = "${pageScope.basePath}";
	var _currentPage = <s:property value="page.currentPage"/>; //当前页码
	var _TotalPage = <s:property value="page.totalPage"/>; //总页码
	var _categoryId = '<s:property value="category.id"/>';
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
	
	function goByPage(index){
			
		var _url = '';
		if(_categoryId ==''){
		 	 _url = _basePath+"byCategoryList.html?page.p="+index;	
		}else{
			_url = _basePath+"byCategoryList.html?category.id="+_categoryId+"&page.p="+index;
		}
		window.location.href = _url ;
	}
	
	
	

</script>

<body style="min-width: 700px;">
	<div class="whole">
			<!-- 新闻列表 -->
			<div class="BulletinFreamList">
				<div class= "BulletinFreamListContent">
					<div class="BulletinFreamListTop">
						<s:if test="bulletinList == null || bulletinList.size() == 0">
							<!-- 居中div提示没有内容 -->
							<div style="float: left;width: 100%;height: 200px;padding-left: 200px;padding-top: 75px;">
								<font size="3px" color="red">该公告类别没有内容</font>
							</div>
						</s:if>
						<s:else>
							<ul>
								<s:iterator value="bulletinList" id="bulletinModel">
									<a href="${pageScope.basePath}bulletin/show/<s:property value="#bulletinModel.id"/>.html" onclick="window.open(this.href);return false;"><li><span><s:property value="#bulletinModel.title"/></span>
									<i><s:date name="#bulletinModel.date" format="yyyy-MM-dd"/></i></li></a>
								</s:iterator>
							</ul>
						</s:else>
						
					</div>
				</div>
				<div class="pagin">
					<div class="message">
						共<i class="pblue"><s:property value="page.totalCount" /></i>条记录,当前显示第&nbsp;<i
							class="pblue"><s:property value="page.currentPage" />&nbsp;</i>页,共<i
							class="pblue"><s:property value="page.totalPage" /></i>页
					</div>
					<ul class="paginList">
						<s:if test="page.isHasPrePage()">
							<li class="paginItem"><a href="HasPre"><span
									class="pagepre">上</span></a></li>
						</s:if>
				
						<!-- 是否有下一页 -->
						<s:if test="page.isHasNextPage()">
							<li class="paginItem"><a href="HasNext"><span
									class="pagenxt">下</span></a></li>
						</s:if>
				
						<!-- 是否有多页 -->
						<s:if test="page.totalPage > 1">
							<li class="paginItem"><input
								style="float:left;width:31px;height:28px;border:1px solid #DDD; margin-left:32px;"
								type="text" maxlength="3" value="<s:property value="page.currentPage"/>"/></li>
							<li class="paginItem"><a href="goByPage">go</a></li>
						</s:if>
					</ul>
				</div>
			</div>
</body>
</html>
