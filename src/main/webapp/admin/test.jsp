<%@page import="java.util.Map"%>
<%@page import="java.util.Properties"%>
<%@page import="java.io.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String realPath = request.getRealPath("/") + "admin/ModelBeans";
	Properties properties = new Properties();
	properties.load(new FileInputStream(realPath));
	
	for(Map.Entry<Object,Object> entry:properties.entrySet()){
		out.print("<h2>"+ entry.getKey() +"</h2>");
		out.print("<h2>"+ entry.getValue() +"</h2>");
	}
	%>
</body>
</html>