<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link rel="stylesheet" href="/TGA103G1/admin/css/header.css" />
<link rel="stylesheet" href="/TGA103G1/admin/css/back.css">
<style>
	.form{
    display: flex;
    justify-content: center; 
    align-items: center; 
    margin-top:calc(20%);
}
</style>
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
				<li><a href="#">登入</a></li>
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
					<a class="front_paga" type="button">留言檢舉</a>
				</div>
				<div>
					<a class="forum" type="button">文章檢舉</a>
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
			<div class="form">
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
		</main>
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
