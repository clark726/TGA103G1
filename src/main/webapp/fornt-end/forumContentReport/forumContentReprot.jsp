<%@page
	import="com.forum.model.service.impl.ForumServiceImpl,java.util.*"%>
<%@page import="com.forum.model.ForumVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>檢舉文章</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backproduct.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
	crossorigin="anonymous">
</head>

<body style="background-color: rgb(129, 93, 65)">
	<script src="https://cdn.ckeditor.com/4.20.0/standard/ckeditor.js"></script>
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
	<h1 align="center">bar jar jo 討論區</h1>
	<h2 align="center">檢舉文章</h2>
	<hr>

	<div class="container-fluid">
		<div class="row">
			<div class="col-2" align="center">
				<div class="text" style="background-color: White; height: 400px">
					<main style="border: 1px solid green; height: 150px">
						<main style="border: 1px solid green; height: 25px">作者:</main>
						放會員圖片的地方
					</main>
					<main style="border: 1px solid green; height: 25px">性別:</main>
					<main style="border: 1px solid green; height: 25px">電子信箱:</main>
				</div>
			</div>
			<div class="col-8" align="center">
				<div class="text" style="background-color: #FFD382; height: 350px">
					<div id="editorReportContent"
						style="border: 1px solid black; height: 310px; margin: 10px; margin: 10px">
						<textarea name="editorContentReport" style="height: 600px">
						<p>請輸入檢舉此篇文章原因</p>
						</textarea>
					</div>
					<button id="alertContentReport">確認送出</button>

				</div>
			</div>
		</div>
	</div>
	<hr>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
		crossorigin="anonymous"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js">
		
	</script>
	<script>
		var editorReportContent = CKEDITOR.replace('editorContentReport');

		document.querySelector("#alertContentReport").addEventListener("click",
				function() {

					CKEDITOR.instances.editorContentReport.updateElement();
					var yes = confirm("確認送出?")
					if (yes) {
						console.log($('textarea[name=editorContentReport]').val());
					}

				})
	</script>




</body>

</html>