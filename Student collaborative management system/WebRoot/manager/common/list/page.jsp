<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="pagin">
	<div class="message">
		共<i class="blue"><s:property value="page.totalCount" /></i>条记录,当前显示第&nbsp;<i
			class="blue"><s:property value="page.currentPage" />&nbsp;</i>页,共<i
			class="blue"><s:property value="page.totalPage" /></i>页
	</div>
	<ul class="paginList">
		<!-- //是否有上一页 -->
		<s:if test="page.isHasPrePage()">
			<li class="paginItem"><a href="HasPre"><span
					class="pagepre"></span></a></li>
		</s:if>

		<!-- 是否有下一页 -->
		<s:if test="page.isHasNextPage()">
			<li class="paginItem"><a href="HasNext"><span
					class="pagenxt"></span></a></li>
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
<script type="text/javascript">
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
</script>
