<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/basePath.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>投诉反馈</title>
</head>
<style type="text/css">
/*该样式表禁止修改*/
html, body, div, span, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, abbr, address, cite, code, del, dfn, em, img, ins, kbd, q, samp, small, strong, sub, sup, var, b, i, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody, tfoot, thead, tr, th, td {border: 0 none;font-size: 100%; margin: 0;padding: 0; list-style:none;}
section, article, aside, header, footer, nav, dialog, figure {display: block;}
figure {margin: 0;}
body,input, textarea, select,div{font-family: "Helvetica Neue","Luxi Sans","DejaVu Sans",Tahoma,"Hiragino Sans GB",STHeiti,"Microsoft YaHei",Arial,sans-serif;}
body{ font-size:12px;}
.clearfix:after{visibility: hidden;display: block;font-size: 0;content: ".";clear: both;height: 0;} 
* html .clearfix{zoom: 1;}
*+ html .clearfix{zoom: 1;}
.display{ display:none;}
img{ border:0;}



/*table*/
.tablelist {width:100%;}
.tablelist tr:hover{ background:#f0f7fd}
.tablelist tr td {border-bottom:1px dotted #CCC; text-align:center; height:40px; line-height:20px;font-size:14px; padding:0 5px}
.tablelist th {background-color:#f0f0f0;border-bottom:1px solid #CCC; height:32px; line-height:20px;font-size:14px; padding:0 5px; font-weight:normal; color:#666}
.tablelist .td_l,.tabletb .td_l {text-align:left}
.tablelist .td_r {text-align:right}
.tablelist i{ color:#f60; font-style:normal; font-size:12px; margin-right:3px; cursor:default}
.tablelist a:visited{ color:#666}
.tabletb {width:100%;}
.tabletb td {height:30px; line-height:20px;font-size:14px; padding:5px}
.tabletb th span {display:inline-block; width:5px; vertical-align:middle; color:#f30}
.tabletb th {height:30px; line-height:20px;font-size:14px; padding:5px; font-weight:normal; color:#666; text-align:right}
.tabletb .tishi{ margin-left:5px;}
.tabletb .tishi em{ margin-right:5px;}
.tabletb1 {width:100%;}
.tabletb1 td { line-height:25px;font-size:14px; padding:0 5px}
.tabletb1 th span {display:inline-block; width:5px; vertical-align:middle; color:#f30}
.tabletb1 th { line-height:25px;font-size:14px; padding:0 5px; font-weight:normal; color:#666; text-align:right}
.tabletb1 .tishi{ margin-left:5px;}
.tabletb1 .tishi em{ margin-right:5px;}
.bingou td{ background:#def0fa}
.tbhover{}
.tbhover tr:hover{ background:#f4f4f4}
.tablelist em{ float:left; margin:6px 5px 0 20px}
.tablelist .bumen{ cursor:pointer; background:#D8E9CF}
.tablelist .move{ border-left:3px dotted #ccc; padding:1px; cursor:move; }
.tablelist .move span{  border-left:3px dotted #ddd; padding:1px 6px;}
.text_l {
	width: 760px;
	background: #fff;
	border: 1px solid #bccfb3;
	font-size: 14px;
}

.text_l h2 {
	font-size: 14px;
	margin: 5px;
	background: #eef8e9;
	border-bottom: 1px solid #bccfb3;
	padding: 8px 20px
}

.text_l h2 a {
	float: right;
	font-size: 12px;
	font-weight: normal;
	*margin-top: -20px
}

.text_l_css {
	padding: 10px 30px;
	width: 700px;
	line-height: 20px
}

.text_l_css .sf1, .text_l_css .zhaopin {
	width: 708px
}

.text_l_css .z_bt {
	width: 695px
}

.text_r {
	width: 218px;
	position: absolute;
	right: 0;
	float: right
}

.text_r ul {
	list-style: none;
	margin: 0;
	padding: 0
}
.text_l_css {
	padding: 10px 30px;
	width: 700px;
	line-height: 20px
}

.text_l_css .sf1, .text_l_css .zhaopin {
	width: 708px
}

.text_l_css .z_bt {
	width: 695px
}

</style>
<script language="JavaScript" src="${pageScope.basePath}Backstage/js/jquery.js"></script>
<s:if test="errorMsg != null">
	<script type="text/javascript">
		alert('<s:property value="errorMsg"/>');
	</script>
</s:if>
<script type="text/javascript">
	function checkform(){
		//输入姓名
		if($("#name").val() == ''){
			alert("输入联系人姓名");
			return false;
		}
		
		if($("#email").val()=='' ||$("#tel").val()=='' ){
			alert("请输入E-Email 或者  联系方式");
			return false;
		}
		if($("#email").val()!=''){
			var str = $("#email").val();
			var patten = new RegExp(/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/);    
			if(!patten.test(str)){
	         	alert("请输入正确的邮箱地址");
	         	$("#email").val("");
	         	$("#email").focus();;
	         	return false;
	         }
		}
		alert($("#content").val());
		if($("#content").val == ''){
			
		}
		return true;
	}

</script>

<body  style="text-align:center;">
	<div class="text_l" style="font-size: 12px;margin:0 auto;">
		<h2>投诉反馈</h2>
		<div class="text_l_css">
			<form name="form1" method="post" action="${pageScope.basePath}saveComplaint.html" id="form1">
				<div>
					 <s:token></s:token>
				</div>
				<div style="color: Red"></div>
				<table class="tabletb">
					<tbody>
						<tr>
							<th width="100" align="right"></th>
						</tr>
						<tr>
							<th align="right">联系人：</th>
							<td><input name="complaint.name" type="text" id="name" value="<s:property value="complaint.name"/>"
								maxlength="30" class="aimp1"></td>
						</tr>
						
						<tr>
							<th align="right">E-Mail：</th>
							<td><input name="complaint.email" type="text" id="email" value="<s:property value="complaint.email"/>"
								maxlength="30" class="aimp1"></td>
						</tr>
						<tr>
							<th align="right">联系方式：</th>
							<td><input name="complaint.contact" type="text" id="tel" maxlength="30" value="<s:property value="complaint.contact"/>"
								class="aimp1"></td>
						</tr>
						<tr>
							<th align="right">标 题：</th>
							<td><input name="complaint.title" type="text" id="title"
								maxlength="30" value="<s:property value="complaint.title"/>"
								style="width: 500px;
                                height: 24px">
							</td>
						</tr>
						<tr>
							<th align="right">反馈内容：</th>
							<td><input type="radio" checked="checked" value="网站链接问题（打不开）" 
								name="complaint.problemContent"> 网站链接问题（打不开） <input type="radio"
								value="优化建议及其他" name="complaint.problemContent"> 优化建议及其他</td>
						</tr>
						<tr>
							<td valign="top" align="right"></td>
							<td><b class="fs12">请具体说明您的问题,或者说明您不满意的原因</b> <textarea
									name="complaint.content" rows="2" cols="20" id="content"
									style="height:120px;width:500px;"><s:property value="complaint.content"/></textarea>
								<p class="fs12">(希望您留下邮箱等联系方式，以便于我们与您进一步沟通)</p></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td height="30"><input type="submit" 
								value="      提  交     " onclick="return checkform();"
								id="btn_ok" class="btn">
								<p class="fs12">
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
