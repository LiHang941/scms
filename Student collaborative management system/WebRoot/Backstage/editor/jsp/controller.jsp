<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter,java.io.File"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	//保存路径
	String rootPath = application.getRealPath( "/" );
	/* File file = new File(rootPath);
	rootPath = file.getParent() + File.separator;
	if(rootPath == null){
		rootPath = application.getRealPath( "/" );
	}
	System.out.println(rootPath + "----------"  ); */
	out.write( new ActionEnter( request, rootPath ).exec() );
	
%>