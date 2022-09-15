<%@ page pageEncoding="UTF-8"%>
<html>
<head>
<title>LOGIN</title>
<style>
	div{
    display: flex;
    justify-content: center; 
    align-items: center; 
    margin-top:calc(20%);
}
</style>
</head>
<body>
	<% 
	if(session.getAttribute("admin")!=null){
		response.sendRedirect(request.getContextPath() + "/admin/console/admin.jsp?page=0");
		return;
	}
	String error = (String) session.getAttribute("error");
	if (!(error == null || "".equals(error))) {
		out.println("<h1>Error Account or Password or Code</h1>");
	}
	%>
	<div>
		<form action="/TGA103G1/control" method="post">
			user:<input type="text" name="user" value="07"><br>
			password:<input type="password" name="password" value="07"><br>
			code:<input type="text" name="code" maxlength="4" size="4"><img
				src="image.jsp"><br> <input type="hidden" name="action"
				value="login">
			<button type="submit" class="login">login</button>
			<button type="reset">reset</button>
		</form>
	</div>
	<script>
		document.querySelector(".login").addEventListener("click", function(e) {
			var btn = e;
			document.querySelectorAll("input").forEach(function(e) {
				if (e.value.trim() == "") {
					btn.preventDefault();
				}
			});
		});
	</script>
</body>
</html>