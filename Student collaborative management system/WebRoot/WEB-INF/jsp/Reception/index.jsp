<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8">
		<title>学生协同系统</title>
		<%@ include file="/Reception/common/reception.jsp" %>
	</head>
	<script type="text/javascript">
	//解决子框架嵌套的问题
	if(window != window.parent){
		window.parent.location.href = window.location.href;
	}
	</script>
<style type="text/css">
.banner-bottom-search{
	float: right; 
	margin-right: 15%;
}
fieldset.search {
    padding: 0px;
    border: none;
    width: 232px;
    background:#e0e0e0;
}
 
fieldset.search:hover {
    background: #a8a8a8;
}
.search input, .search button {
    border: none;
    float: left;
}
.search input.box {
    height: 28px;
    width: 200;
    margin-right: 0px;
    padding-right: 0px;
    background: #e0e0e0;
    margin: 1px;
}
.search input.box:focus {
    background: #e8e8e8 ;
    outline: none;
}
.search button.btn {
    border: none;
    width: 28px;
    height: 28px;
    margin: 0px auto;
    margin: 1px;
    background: url(${pageScope.basePath}Reception/images/search_blue.png) no-repeat top right;
}
.search button.btn:hover {
    background: url(${pageScope.basePath}Reception/images/search_black.png) no-repeat bottom right;
}
</style>

<script type="text/javascript">
	$(".search .btn").click(function (){
		if($(".search .input").val() == ''){
			alert("请输入搜索内容");
			return;
		}
		$("#searchform").submit();
	});
</script>
	<body>
		<div class="whole">
			<!--头部分类-->
			<%@ include file="/Reception/common/bulletin/bulletinHead.jsp" %>
			<!--头部分类End-->
			
			<!--banner图-->
			 <div class="banner">
			 	<div class="banner-pic">
			 		<img src="Reception/images/banner.jpg" width="100%" />
			    </div>
			    <div class="banner-bottom">
			    <!-- 搜索 -->
			    	<div class="banner-bottom-search">
			    		<form method="get" id="searchform" action="${pageScope.basePath}entryNavigation.html" target="contentFrame">
						    <fieldset class="search">
						         <input type="text" class="box" name="searchStr" id="s" class="inputText" placeholder="搜索公告" x-webkit-speech>
						         <button class="btn" title="SEARCH"> </button>
						    </fieldset>
						</form>
			    	</div>
			    </div>
			 </div>
			<!--bannerEnd-->
			
			<div class="content-content">
				<div class="con-con">
						<div class="company">
								<iframe src="${pageScope.basePath}<s:property value="URL"/>" style="width:980px;height:800px;"  name ="contentFrame" id="contentFrame"></iframe>
						</div>
				</div>
			</div>
			<!--底部-->
			<%@ include file="/Reception/common/bulletin/bulletinFooter.jsp" %>
			<!--底部结束-->
		</div>
	</body>
</html>
