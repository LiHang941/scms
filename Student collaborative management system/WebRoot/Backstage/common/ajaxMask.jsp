<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
//AJAX遮罩
//兼容火狐、IE8   
//显示遮罩层    
  function showMask(){     
      $("#mask").css("height",$(document).height());     
      $("#mask").css("width",$(document).width());     
      $("#mask").show();     
  }  
  //隐藏遮罩层  
  function hideMask(){
      $("#mask").hide();     
  }  
   $(document).ajaxStart(function () {  
      showMask();  
  }).ajaxStop(function () {  
      hideMask(); 
  });   
</script>
<% //遮罩div %>
<div id="mask" class="mask"></div>  
