<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	h1{
    display: flex;
    justify-content: center; 
    align-items: center; 
    margin-top:calc(20%);
}
</style>
</head>
<body>
	<h1>${error}</h1>
	<%response.addHeader("refresh","2;url="+request.getAttribute("url")); %>
</body>
</html>