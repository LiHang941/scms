<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>公告导航</title>
<%@ include file="/Reception/common/reception.jsp" %>
</head>
<body style="min-width: 240px;">
	<div class="whole" style="width: 245px;margin-top: 5px;" >
		<div class="BulletinFreamCategory">
				<!-- 标题 -->
				<span> 信息导航 </span>
				<div class="BulletinFreamCategoryList">
					<div>
						<ul>
							<s:iterator value="categoryList" id="categoryModel">
								<a href="${pageScope.basePath}byCategoryList/<s:property value="#categoryModel.id"/>.html" target="rightFrame"><li id="category<s:property value="#categoryModel.id"/>"><s:property value="#categoryModel.name"/></li></a>
							</s:iterator>
							<!-- 如果category.id = categoryModel.id  那么凸显出来 -->
						</ul>
					</div>
				</div>
		</div>
	</div>
	<script type="text/javascript">
			$(".BulletinFreamCategoryList li").click(function(){
				$(".BulletinFreamCategoryList li").each(function (){
					$(this).css("background-color","white");
				});
				$(this).css("background-color","#808080");
				
			});
			
			<s:if test="category!=null && category.id>0">
				var categoryId = <s:property value="category.id"/>;
				$("#category"+categoryId).css("background-color","#808080");
			</s:if>
	</script>
	
</body>
</html>
