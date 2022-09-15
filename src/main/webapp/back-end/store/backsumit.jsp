<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/storeumit.css" />
<style>
div.city {
	display: flex;
	flex-direction: column;
}
</style>
</head>
<body style="background-color: rgb(129, 93, 65)">
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
				<li><a href="#">登入註冊</a></li>
			</ul>
		</nav>
	</header>

	<div class="div_register">
		<main class="register_main">
			<form action="" class="register_form" method="post" >
				<h2>廠商註冊</h2>
				<div class="register_row">
					<label for="register_acc">帳號</label>
					<div>
						<input type="text" id="register_acc" class="register_control"
							name="account" />
					</div>
				</div>

				<div class="register_row">
					<label for="register_psw">密碼</label>
					<div>
						<input type="password" id="register_psw" class="register_control"
							name="password" />
					</div>
				</div>

				<div class="register_row">
					<label for="register_dbpsw">電話</label>
					<div>
						<input type="text" id="register_dbpsw" class="register_control"
							name="phone" />
					</div>
				</div>

				<div class="register_row">
					<label for="register_email">Email</label>
					<div>
						<input type="text" id="register_email" class="register_control"
							name="email" />
					</div>
				</div>

				<div class="register_row">
					<label for="register_addr">住址</label>
					<div class="city">
						<input class="js-demeter-tw-zipcode-selector" data-city="#city"
							data-dist="#dist" placeholder="請輸入郵遞區號"
							style="width: 100px; margin-left: 100px" />
						<select id="city" placeholder="請選擇縣市" name="city"
							style="height: 25px; width: 100px; margin-left: 100px"></select>
						<select id="dist" placeholder="請選擇鄉鎮區" name="dist"
							style="height: 25px; width: 100px; margin-left: 100px"></select>
						<input type="text" id="register_addr" class="register_control" name="address" />
					</div>
				</div>

				<div class="register_row">
					<label for="register_day">店家類型</label>
					<div>
						<select name="store_type" id="store_type" name="store_type">
							<option value="0">Bistro</option>
							<option value="1">Cocktail Bar</option>
							<option value="2">Whisky Bar</option>
						</select>
					</div>
				</div>

				<div class="register_row">
					<label for="register_day">公休日</label>
					<div>
						<input type="text" id="register_day" class="register_control"
							name="dayoff" />
					</div>
				</div>

				<div class="register_row">
					<label for="register_open">營業開始</label>
					<div>
						<input type="text" id="register_open" class="register_control"
							placeholder="Ex: 18:00" name="open" />
					</div>
				</div>

				<div class="register_row">
					<label for="register_close">營業結束</label>
					<div>
						<input type="text" id="register_close" class="register_control"
							placeholder="Ex: 24:00" name="end" />
					</div>
				</div>

				<div class="register_btn">
					<button type="submit" id="register">註冊</button>
					<button type="button" id="cancel">
						<a href="../back/backlog.html" class="back">離開</a>
					</button>
				</div>
			</form>
		</main>
	</div>

	<script src="../js/jquery-3.6.0.min.js"></script>
	<script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>