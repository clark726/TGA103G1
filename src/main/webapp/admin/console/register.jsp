<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link rel="stylesheet" href="/TGA103G1/admin/css/header.css" />
<link rel="stylesheet" href="/TGA103G1/admin/css/back.css">
</head>
<body>
	<header class="header">
		<div class="fl_left">
			<h1 id="logo_h1">
				<a href="＃" id="logo">Bar.Jar.Jo</a>
			</h1>
		</div>

		<nav class="fl_right">
			<ul class="nav_ul">
				<li><a href="#">HOME</a></li>
				<li><a href="#">地圖</a></li>
				<li><a href="#">討論區</a></li>
				<li><a href="#">廠商專區</a></li>
				<li><a href="#" class="icon">店家主題</a>
					<ul id="store">
						<li><a href="#">Bistro</a></li>
						<li><a href="#">Cocktail bar</a></li>
						<li><a href="#">Whisky bar</a></li>
					</ul></li>
				<li><a href="#" class="icon">會員專區</a>
					<ul id="store">
						<li><a href="#">我的最愛</a></li>
						<li><a href="#">修改會員資料</a></li>
						<li><a href="#">修改密碼</a></li>
						<li><a href="#">訂單管理</a></li>
					</ul></li>

				<li><a href="#">購物商城</a></li>
				<li><a href="/TGA103G1/control">登出</a></li>
			</ul>
		</nav>
	</header>

	<div class="contain">
		<aside class="aside">
			<p id="p1">管理者後台</p>
			<div class="div_func">
				<div>
					<a class="edit_store" type="button">修改會員資料</a>
				</div>
				<div>
					<a class="manager_item" type="button">上下架商品管理</a>
				</div>
				<div>
					<a class="edit_item" type="button">修改商家訂單內容</a>
				</div>
				<hr>
				<div>
					<a class="front_paga" href="<%=request.getContextPath()%>/admin/console/messageReport.jsp">留言檢舉</a>
				</div>
				<div>
					<a class="forum" href="/TGA103G1/admin/console/forumReport.html">文章檢舉</a>
				</div>
				<div>
					<a class="audit" type="button">審核上架</a>
				</div>
				<div>
					<a class="change_state" type="button">改變帳號狀態</a>
				</div>
				<div>
					<a class="manager" type="submit">..</a>
				</div>
			</div>
		</aside>

		<main class="main">
			<form action="<%=request.getContextPath()%>/control" method="post">
			<input type="text" name="account" placeholder="account" class="account">
			<input type="password" name="password" placeholder="password" class="password1">
			<input type="password" placeholder="password" class="password2">
			<input type="hidden" name="action" value="register">
			<button class="submit" type="button">submit</button>
			</form>
		</main>
	</div>
	<script>
	document.querySelector(".submit").addEventListener("click",function(e){
		var account = document.querySelector(".account").value;
		var password = document.querySelector(".password1").value;
		var again = document.querySelector(".password2").value;
		if(account.length <8 ||account.length>15){
			e.preventDefault();
			alert("8~15");
		}
		if(!(/^\w+$/.test(account))){
			e.preventDefault();
		}
		if(password.length<8 || password.length>15){
			e.preventDefault();
			alert("8~15");
		}
		if(!(/^\w+$/.test(password))){
			e.preventDefault();
		}
		if(password != again){
			e.preventDefault();
			alert("再次確認密碼");
		}
	})
	</script>
</body>
</html>
