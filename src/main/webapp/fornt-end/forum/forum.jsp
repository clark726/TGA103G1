<%@page import="com.forum.model.service.impl.ForumServiceImpl,java.util.*" %>
	<%@page import="com.forum.model.ForumVO" %>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
			<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
				<% ForumVO forumVO=(ForumVO) request.getAttribute("forumVO"); ForumServiceImpl forumService=new
					ForumServiceImpl(); List<ForumVO> list = forumService.getAll();
					pageContext.setAttribute("list", list);
					%>
					<!DOCTYPE html>
					<html lang="en">

					<head>
						<meta charset="UTF-8" />
						<meta http-equiv="X-UA-Compatible" content="IE=edge" />
						<meta name="viewport" content="width=device-width, initial-scale=1.0" />
						<title>Fourm</title>
						<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css" />
						<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backproduct.css">

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
									<li><a href="http://localhost:8080/TGA103G1/fornt-end/forum/forum.jsp">討論區</a></li>
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
						<div style="background-color: #FFD382; padding: 10px; margin-bottom: 5px;width:;">
							<p style="color: black;">hi,${userid.account}</p>
							<input id="searchforum" type="search" style="TRANSFORM: TRANSLATEX(555%);">
							<button style="TRANSFORM: TRANSLATEX(2200%);">搜尋</button>
							<button id="IWantToInsertContent" style="TRANSFORM: TRANSLATEX(-175%);"
								class="button">發表文章</button>
							<button id="MyContent" style="TRANSFORM: TRANSLATEX(-175%);" class="myButton">我的文章</button>


							<table border="1" align="center">
								<thead>
									<tr>
										<th height="50" width="80">文章編號</th>
										<th height="50" width="100">使用者帳號</th>
										<th height="50" width="200">建立時間</th>
										<th height="50" width="400">文章標題</th>
										<th height="50" width="80">按讚數</th>
										<th height="50" width="80">觀看數</th>
										<th height="50" width="80">回覆數量</th>

									</tr>
								</thead>
								<c:forEach var="forumvo" items="${list}">
									<c:if test="${forumvo.status==1}">
									<tr>
										<td>${forumvo.forum_id}</td>
										<td>${forumvo.member_id}</td>
										<td>${forumvo.date.year}/${forumvo.date.monthValue}/${forumvo.date.dayOfMonth}
											${forumvo.date.hour}時${forumvo.date.minute}分</td>
										<td><a
												href="/TGA103G1/ForumServlet?action=watchOneForum&page=${forumvo.forum_id}">${forumvo.title}</a>
										</td>
										<td>${forumvo.like}</td>
										<td>${forumvo.look}</td>
										<td>${forumvo.message}</td>
									</tr>
									</c:if>
								</c:forEach>
						</div>
						</table>
						<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
						<script>
							var IWantToInsertContent = document.getElementById('IWantToInsertContent');
							IWantToInsertContent.addEventListener('click', function () {

								console.log();
							})
							$(".button").click(function () {
								location.href = "/TGA103G1/PostForumContent?action=AreYouLogin";
							})
						</script>
						<script>
							document.querySelector("#MyContent").addEventListener("click", function (ev) {
								console.log(1);
								if (<%= session.getAttribute("userid") == null %>) {
								alert("您尚未登入")
								location.href = "/TGA103G1/front-end/member/jsp/login.jsp";
							}else {
								location.href = "/TGA103G1/fornt-end/forum/myForumContent.jsp";
							}
			})

						</script>
					</body>

					</html>