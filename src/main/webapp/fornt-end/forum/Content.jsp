<%@page import="com.forum.model.ForumService,java.util.*"%>
<%@page import="com.forum.model.ForumVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%
ForumVO forumVO = (ForumVO) request.getAttribute("forumVO");
ForumService forumService = new ForumService();
List<ForumVO> list = forumService.getAll();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Product</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backproduct.css">

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

	<h1 align="center">bar jar jo 討論區</h1>
<%-- 			<c:forEach var="forumvo" items="${list}"> --%>
	<div style="background-color: #FFD382; width:1024px;height:768px;border:3px;margin:0 auto;">
	
	<h2 align="center">文章標題 ${forumvo.title} </h2>
	
	</div>
	
		<table border="1" align="center">

		

<!-- 		<input id="searchforum" type="search" -->
<!-- 			style="TRANSFORM: TRANSLATEX(560%);"> -->
<!-- 		<button style="TRANSFORM: TRANSLATEX(2230%);">搜尋</button> -->
<!-- 		<button style="TRANSFORM: TRANSLATEX(-110%);">發表文章</button> -->



<!-- 				<tr> -->
<!-- 					<th height="50" width="80">文章編號</th> -->
<!-- 					<th height="50" width="80">使用者帳號</th> -->
<!-- 					<th height="50" width="200">建立時間</th> -->
<!-- 					<th height="50" width="400">文章標題</th> -->
<!-- 					<th height="50" width="80">按讚數</th> -->
<!-- 					<th height="50" width="80">觀看數</th> -->
<!-- 					<th height="50" width="80">回覆數量</th> -->
<!-- 					<th height="50" width="80">文章狀態</th> -->

<!-- 				</tr> -->

			
<!-- 				<tr> -->
<%-- 					<td>${forumvo.forum_id}</td> --%>
<%-- 					<td>${forumvo.member_id}</td> --%>
<%-- 					<td>${forumvo.date}</td> --%>
<%-- 					<td>${forumvo.title}</td> --%>
<%-- 					<td>${forumvo.like}</td> --%>
<%-- 					<td>${forumvo.look}</td> --%>
<%-- 					<td>${forumvo.message}</td> --%>
<%-- 					<td>${forumvo.status}</td> --%>
<!-- 					<td> -->
<!-- 						<FORM METHOD="post" -->
<%-- 							ACTION="<%=request.getContextPath()%>/forum/forum.do" --%>
<!-- 							style="margin-bottom: 0px;"> -->
<!-- 							<input type="submit" value="修改"> <input type="hidden" -->
<%-- 								name="empno" value="${forumVO.empno}"> <input --%>
<!-- 								type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 						</FORM> -->
<!-- 					</td> -->
<!-- 					<td> -->
<!-- 						<FORM METHOD="post" -->
<%-- 							ACTION="<%=request.getContextPath()%>/emp/emp.do" --%>
<!-- 							style="margin-bottom: 0px;"> -->
<!-- 							<input type="submit" value="刪除"> <input type="hidden" -->
<%-- 								name="empno" value="${empVO.empno}"> <input --%>
<!-- 								type="hidden" name="action" value="delete"> -->
<!-- 						</FORM> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<%-- 			</c:forEach> --%>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
        window.onload = function () {
            $.ajax({
                url: "/TGA103G1/ForumServlet",          
                type: "post",                  
                data: { "action": "getAllForum" },             
                dataType: "text",
                success: function (data) {      
                    console.log(data);
                }
            });
        }
		</script>
</body>
</html>
