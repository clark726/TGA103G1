<%@page import="com.member.vo.MemberVO"%>
<%@page
	import="com.forum.model.service.impl.ForumServiceImpl,java.util.*"%>
<%@page import="com.forum.model.ForumVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
ForumVO forumVO = (ForumVO) request.getAttribute("forumVO");
ForumServiceImpl forumService = new ForumServiceImpl();
MemberVO vo = (MemberVO) session.getAttribute("userid");
List<ForumVO> list = forumService.getAllByMemberId(vo.getMember_id());
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Fourm</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backproduct1.css">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
</head>

<body style="background-color: #cab79f73">
	<header class="header">
		<div class="fl_left">
			<h1 id="logo_h1">
				<a href="/TGA103G1/main.html" id="logo">Bar.Jar.Jo</a>
			</h1>
		</div>

		<nav class="fl_right">
			<ul class="nav_ul">
				<li><a href="#" id="home">HOME</a></li>
				<li><a href="">??????</a></li>
				<li><a href="/TGA103G1/fornt-end/forum/forum.jsp">?????????</a></li>
				<li><a href="#" data-bs-toggle="modal" id="loginbox"
					data-bs-target="#loginModal">????????????</a><a
					href="/TGA103G1/ShowProduct" id="normal"
					style="display: none; width: 65px;">????????????</a></li>
				<li><a href="#" class="icon">????????????</a>
					<ul id="store" class="dropdown-menu">
						<li><a href="/TGA103G1/back-end/store/storeType.html"
							data-themid="1" class="theme_id">Bistro</a></li>
						<li><a href="/TGA103G1/back-end/store/storeType.html"
							data-themid="2" class="theme_id">Cocktail bar</a></li>
						<li><a href="/TGA103G1/back-end/store/storeType.html"
							data-themid="3" class="theme_id">Whisky bar</a></li>
					</ul></li>
				<li><a href="#" class="icon">????????????</a>
					<ul id="store" class="dropdown-menu">
						<li><a
							href="/TGA103G1/front-end/member/jsp/member_center.jsp">??????????????????</a></li>
						<li><a
							href="/TGA103G1/front-end/member/jsp/member_centerChangePsw.jsp">????????????</a></li>
						<li><a
							href="/TGA103G1/front-end/member/jsp/member_centerMyLove.jsp">????????????</a></li>
						<li><a
							href="/TGA103G1/front-end/member/jsp/menberCenter%EF%BC%ADanegerOrder.html">????????????</a></li>
					</ul></li>


				<li><a href="/TGA103G1/back-end/shop/shopProduct.html">????????????</a></li>
				<li><a href="/TGA103G1/front-end/member/login.jsp" id="login">????????????</a><a
					href="/TGA103G1/StoreLogout" id="logout"
					style="display: none; width: 40px;">??????</a></li>
			</ul>
		</nav>
	</header>
	<h1 align="center">bar jar jo ?????????</h1>
	<div
		style=" padding: 10px; margin-bottom: 5px; width:;">
		<h4 style="color: black;">?????????,${userid.nickname}</h4>
		<form method="get" action="/TGA103G1/SearchForum">
			<input id="searchforum" name="serach" type="text"
				style="TRANSFORM: TRANSLATEX(525%);">
			<button id="searchbutton" style="TRANSFORM: TRANSLATEX(1800%);" class="btn btn-secondary">??????</button>
		</form>
		<button id="IWantToInsertContent" style="TRANSFORM: TRANSLATEX(0%);"
			class="button btn btn-secondary"">????????????</button>
		<button id="MyContent" style="TRANSFORM: TRANSLATEX(0%);"
			class="myButton btn btn-secondary">????????????</button>


		<table style="border-color: black;"  class="table table-hover"  border="3" align="center">
			<thead style="background-color:rgb(216, 194, 176);">
				<tr>
					<th height="50" width="80">????????????</th>
					<th height="50" width="100">???????????????</th>
					<th height="50" width="200">????????????</th>
					<th height="50" width="400">????????????</th>
					<th height="50" width="80">?????????</th>
					<th height="50" width="80">?????????</th>
					<th height="50" width="80">????????????</th>
					<th height="50" width="80">????????????</th>
					<th height="50" width="80">????????????</th>

				</tr>
			</thead>
			<c:forEach var="forumvo" items="${list}">
				<c:if test="${forumvo.status==1}">
					<tr style="border-color:black">
						<td style="border-color:black">${forumvo.forum_id}</td>
						<td style="border-color:black">${forumvo.member_id}</td>
						<td style="border-color:black">${forumvo.date.year}/${forumvo.date.monthValue}/${forumvo.date.dayOfMonth}
							${forumvo.date.hour}???${forumvo.date.minute}???</td>
						<td style="border-color:black"><a
							href="/TGA103G1/ForumServlet?action=watchOneForum&page=${forumvo.forum_id}">${forumvo.title}</a>
						</td>
						<td style="border-color:black">${forumvo.like}</td>
						<td style="border-color:black">${forumvo.look}</td>
						<td style="border-color:black">${forumvo.message}</td>
					<td style="border-color:black">${forumvo.status==1?"??????":"??????"}</td>
					<td style="border-color:black"><button class="del btn btn-secondary"">??????</button></td>
					</tr>
				</c:if>
			</c:forEach>
			</div>
		</table>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		var IWantToInsertContent = document
			.getElementById('IWantToInsertContent');
		IWantToInsertContent.addEventListener('click', function () {

			console.log();
		})
		$(".button")
			.click(
				function () {
					location.href = "/TGA103G1/PostForumContent?action=AreYouLogin";
				})


		var IWantDelMyContent = document.querySelectorAll(".del");
		IWantDelMyContent.forEach(function (el) {
			el.addEventListener('click', function () {
				if (confirm("?????????")){
					var id = el.closest("tr").children[0].innerText;

					$.ajax({
				url: "/TGA103G1/DeleteMyContent",
				data: {
					"forumId":id},
				type: "post",
				dataType: "text",
				success: function (xhr) {
					alert(xhr);
					history.go(0)
				}
			})

				// 	console.log(id)
				// 	var xhr = new XMLHttpRequest();
				// 	if(xhr.withCredentials){
				// 		alert("?????????ajax");
				// 	} else {
				// 		xhr.open("post","TGA103G1/DeleteMyContent");
				// 		xhr.setRequestHeader("Content-Type","application/json");
				// 		xhr.send({"forumId":id});
				// 		xhr.onload = function(ev){
				// 			var text = xhr.responseText;
				// 			alert(text);
				// 		}
				// 	}

				}
			})
		})
	</script>
</body>

</html>