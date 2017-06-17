<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="batten">
	<div class="bat-con">
		<div class="batten-name">学生协同管理系统</div>
	</div>
</div>
<div class="head-nav">
	<div class="logo"><img src="Reception/images/logo.jpg" width="100%" /></div>
	<div class="nav-list">
		<a id="home" class="active" href="indexContent.html" target="contentFrame"><span class="list-pic list-home"></span><span class="list-name">首页</span></a>
		<a href="entryNavigation.html" target="contentFrame"><span class="list-pic list-us" ></span><span class="list-name">导航</span></a>
		<a href="loginUI.html"><span class="list-pic list-cases"></span><span class="list-name">登录</span></a>
		<a href="complaint.html" target="contentFrame"><span class="list-pic list-service"></span><span class="list-name">服务</span></a>
		<a href="#"><span class="list-pic list-content"></span><span class="list-name">联系</span></a>
	</div>
</div>

<script type="text/javascript">
	$(".nav-list a").click(function(){
		//清除颜色
		$(".nav-list a").each(function(){
			$(this).removeAttr("class");
		});
		$(this).attr("class","active");
	});
</script>