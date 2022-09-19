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
	<div>
		<form action="/TGA103G1/control" method="post">
			<h3>${error}</h3>
			user:<input type="text" name="user" value="07"><br>
			password:<input type="password" name="password" value="07"><br>
			code:<input type="text" name="code" maxlength="4" size="4">
			<img src="/TGA103G1/admin/image.jsp"><br> 
			<input type="hidden" name="action" value="login">
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