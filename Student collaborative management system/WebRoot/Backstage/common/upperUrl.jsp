<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<span id="upper" onclick="showUpper()">返回上一层<input type="hidden" value="<s:property value="upperURL"/>"/></span>
<script type="text/javascript">
	function showUpper(){
		var _url = $("#upper input").val();
		if(_url == ""){
			_url = "${pageScope.basePath}manager/main.do";
		}	
		window.location.href = _url;
	}
	function getUpperUrl (){
		var _url = $("#upper input").val();
		if(_url == ""){
			_url = "${pageScope.basePath}manager/main.do";
		}	
		return _url;
	}
</script>