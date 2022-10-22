<%@page import="com.forum.model.service.impl.ForumServiceImpl,java.util.*" %>
	<%@page import="com.forum.model.ForumVO" %>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
			<!DOCTYPE html>
			<html lang="en">

			<head>
				<meta charset="UTF-8" />
				<meta http-equiv="X-UA-Compatible" content="IE=edge" />
				<meta name="viewport" content="width=device-width, initial-scale=1.0" />
				<title>發表文章</title>
				<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css" />
				<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backproduct.css">
				<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
					integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
					crossorigin="anonymous">
					<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
			</head>

			<body style="background-color: #cab79f73">
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
								</ul>
							</li>
							<li><a href="#" class="icon">會員專區</a>
								<ul id="store">
									<li><a href="#">我的最愛</a></li>
									<li><a href="#">修改會員資料</a></li>
									<li><a href="#">修改密碼</a></li>
									<li><a href="#">訂單管理</a></li>
								</ul>
							</li>

							<li><a href="#">購物商城</a></li>
							<li><a href="#">登入註冊</a></li>
						</ul>
					</nav>
				</header>
				<h1 align="center">bar jar jo 討論區</h1>
				<h2 align="center">發表文章</h2>
				<hr>

				<div class="container-fluid">
					<div class="row">
						<div class="col-2" align="center">
							<div class="text" style="background-color: White; height: 400px ;border: 1px solid black">
								<main style="border: 1px solid balck; height: 150px">
									<main style="border: 1px solid black; height: 25px">作者:${userid.account}</main>
									<main style="height: 100px;">
										<img src="${userid.gender==1?"/TGA103G1/front-end/member/images/woman.png":"/TGA103G1/front-end/member/images/man.png"}" style="width: 100px;">
									</main>
								<main style="border: 1px solid black; height: 25px">性別:${userid.gender==1?"女":"男"}</main>
								<main style="border: 1px solid black; height: 25px">電子信箱:${userid.email}</main>
							</div>
						</div>
						<div class="col-8" align="center">
							<div class="text shadow p-3 mb-5 bg-body rounded" style="background-color: #FFD382; height: 450px;border: 1px solid black">
								<hr>
									<form action="/TGA103G1/PostForumContent" method="post">						
								<div>文章標題<input id="forumContentTitle" type="text" name="forumContentTitle"></input></div>
								<div id="editor"
									style="border: 1px solid black; height: 310px; margin: 10px; margin: 10px">
									<textarea name="editorDemo" style="height: 600px"></textarea>
								</div>
								<button id="alertContent" class="btn btn-secondary">發表文章</button>
									</form>
							</div>
						</div>
					</div>
				</div>
				<hr>
				<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
					integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
					crossorigin="anonymous"></script>
				<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js">
				</script>
				<script>
				
				var forumContent = CKEDITOR.replace('editorDemo');
				
				document.querySelector("#alertContent").addEventListener(
						"click",
						function(e) {
							CKEDITOR.instances.editorDemo.updateElement();
							if (confirm("確定發表?")) {
								console.log($('textarea[name=editorDemo]')
										.val());
							}else{
								e.preventDefault();
							}
						})

				</script>
			</body>

			</html>