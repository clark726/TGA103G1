<%@page import="com.mysql.cj.Session"%>
<%@page import="com.member.vo.MemberVO"%>
<%@page
	import="com.forum.model.service.impl.ForumServiceImpl,java.util.*"%>
<%@page import="com.forum.model.ForumVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>Content</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backproduct1.css">
	<!-- CSS only -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
	<style>
		p {
			color: black !important
		}

		#likely {
			display: none;
		}
	</style>
</head>

<body style="background-color: #cab79f73">
	<script src="https://cdn.ckeditor.com/4.20.0/standard/ckeditor.js"></script>
	<header class="header">
		<div class="fl_left">
			<h1 id="logo_h1">
				<a href="/TGA103G1/main.html" id="logo">Bar.Jar.Jo</a>
			</h1>
		</div>

		<nav class="fl_right">
			<ul class="nav_ul">
				<li><a href="#" id="home">HOME</a></li>
				<li><a href="">地圖</a></li>
				<li><a href="/TGA103G1/fornt-end/forum/forum.jsp">討論區</a></li>
				<li><a href="#" data-bs-toggle="modal" id="loginbox" data-bs-target="#loginModal">廠商專區</a><a
						href="/TGA103G1/ShowProduct" id="normal" style="display: none; width: 65px;">廠商專區</a></li>
				<li><a href="#" class="icon">店家主題</a>
					<ul id="store" class="dropdown-menu">
						<li><a href="/TGA103G1/back-end/store/storeType.html" data-themid="1"
								class="theme_id">Bistro</a></li>
						<li><a href="/TGA103G1/back-end/store/storeType.html" data-themid="2" class="theme_id">Cocktail
								bar</a></li>
						<li><a href="/TGA103G1/back-end/store/storeType.html" data-themid="3" class="theme_id">Whisky
								bar</a></li>
					</ul>
				</li>
				<li><a href="#" class="icon">會員專區</a>
					<ul id="store" class="dropdown-menu">
						<li><a href="/TGA103G1/front-end/member/jsp/member_center.jsp">修改會員資料</a></li>
						<li><a href="/TGA103G1/front-end/member/jsp/member_centerChangePsw.jsp">修改密碼</a></li>
						<li><a href="/TGA103G1/front-end/member/jsp/member_centerMyLove.jsp">我的最愛</a></li>
						<li><a href="/TGA103G1/front-end/member/jsp/menberCenter%EF%BC%ADanegerOrder.html">訂單管理</a></li>
					</ul>
				</li>


				<li><a href="/TGA103G1/back-end/shop/shopProduct.html">購物商城</a></li>
				<li><a href="/TGA103G1/front-end/member/login.jsp" id="login">登入註冊</a><a href="/TGA103G1/StoreLogout"
						id="logout" style="display: none; width: 40px;">登出</a></li>
			</ul>
		</nav>
	</header>


	<h1 align="center">bar jar jo 討論區</h1>
	<h2 align="center">${vo.title}</h2>
	<button id="reportContent" style="TRANSFORM: TRANSLATEX(1200%);">檢舉文章</button>
	<button style="TRANSFORM: TRANSLATEX(500%);" id="like">我覺得這篇文章讚</button>

	<button style="transform: translateX(500%); display: none; margin-left: 480px;" id="like2">已按讚</button>
	<div class="container-fluid">
		<div class="row">
			<div class="col-2" align="center">
				<div class="text" style="background-color: White; height: 400px;border-color: black">
					<main style="height: 150px">
						<main style="border: 1px solid black; height: 25px">
							作者: ${forumContentMemberId.account}
							<main style="height: 100px;">
								<img src="${forumContentMemberId.gender==1?"
									/TGA103G1/front-end/member/images/woman.png":"/TGA103G1/front-end/member/images/man.png"}"
									style="width: 100px;">
							</main>
							<main style="border: 1px solid black; height: 25px">性別:
								${forumContentMemberId.gender==1?"女":"男"}</main>
							<main style="border: 1px solid black; height: 25px">電子信箱:
								${forumContentMemberId.email}</main>
				</div>
			</div>
			<div class="col-8" align="center">
				<div class="text" style="background-color: #FFD382; height: 580px">
					<aside style="padding-bottom: 0px">發文時間:${vo.date.year}/${vo.date.monthValue}/${vo.date.dayOfMonth}
							${vo.date.hour}時${vo.date.minute}分</aside>
					<div style="border: 1px solid black; height: 502px; margin: 10px; margin: 10px">
						${vo.content}</div>
				</div>
			</div>

			<hr>
			<c:forEach var="fmsg" items="${forumMessage}">
				<div class="container-fluid">
					<div class="row">
						<div class="col-2" align="center">
							<div class="text" style="background-color: White; height: 200px;border-color: black">
								<main style="border: 1px solid black; height: 150px">
									<main style="border: 1px solid black; height: 25px">
										留言作者: ${fmsg.account}
										<main style="height: 100px;">
											<img src="${fmsg.gender==1?"
												/TGA103G1/front-end/member/images/woman.png":"/TGA103G1/front-end/member/images/man.png"}"
												style="width: 100px;">
										</main>
										<main style="border: 1px solid black; height: 25px">性別:
											${fmsg.gender==1?"女":"男"}</main>
										<main style="border: 1px solid black; height: 25px">
											電子信箱: ${fmsg.email}</main>
							</div>
						</div>
						<div class="col-8" align="center">
							<main id="main" class="text" style="background-color: #FFD382; height: 300px">
								<aside style="padding-bottom: 0px">留言時間:${fmsg.date.year}/${fmsg.date.monthValue}/${fmag.date.dayOfMonth}
							${fmsg.date.hour}時${fmsg.date.minute}分</aside>
								<button class="reportMessage" style="TRANSFORM: TRANSLATEX(450%);">檢舉留言</button>
								<div style="border: 1px solid black; height: 225px; margin: 10px; margin: 10px">
									${fmsg.content}</div>
							</main>
						</div>
						<hr>
					</div>
				</div>
			</c:forEach>
			<div class="container-fluid">
				<div class="row">
					<div class="col-2" align="center">
						<div class="text" style="background-color: White; height: 200px ;border: 1px solid black;">
							<!-- 						<form action="/TGA103G1/PostForumContentMessage" method="post"> -->
							<main style="border: 1px solid black; height: 150px">
								<main style="height: 25px">
									留言作者: ${userid.account}
									<main style="height: 100px;">
										<img src="${userid.gender==1?"
											/TGA103G1/front-end/member/images/woman.png":"/TGA103G1/front-end/member/images/man.png"}"
											style="width: 100px;">
									</main>
									<main style="border: 1px solid black; height: 25px">性別:
										${userid.gender==1?"女":"男"}</main>
									<main style="border: 1px solid black; height: 25px">電子信箱:
										${userid.email}</main>
						</div>
					</div>
					<div class="col-8" align="center">
						<div class="text" style="background-color: #FFD382; height: 385px">
							<div>
								<h5>文章留言</h5>
							</div>
							<div style="border: 1px solid black; height: 310px; margin: 10px; margin: 10px">
								<textarea name="editorContentMessage" style="height: 600px"></textarea>
							</div>
							<button id="alertContentMessage" class="messageButton">發表留言</button>
							<!-- 							</form> -->
						</div>
					</div>
					<hr>

				</div>
			</div>

		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
		crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		

		//點擊theme_id
		function changeTheme() {
			let allthemeid = document.querySelectorAll(".theme_id");
			allthemeid.forEach(function (e) {
				e.addEventListener("click", function (e) {
					let id = e.target.getAttribute("data-themid");
					sessionStorage.setItem("themeId", id)
				})
			})
		}

		//換登出鈕
		function changelog() {
			if (sessionStorage.getItem("account")) {
				document.querySelector("#login").style.display = "none";
				document.querySelector("#logout").style.display = "block";
				document.querySelector("#loginbox").style.display = "none";
				document.querySelector("#normal").style.display = "block";
			} else if (sessionStorage.getItem("memberAccount")) {
				document.querySelector("#login").style.display = "none";
				document.querySelector("#memberlogout").style.display = "block";
			}

		}
		//登出
		function logout() {
			document.querySelector("#logout").addEventListener("click", function () {
				if (sessionStorage.getItem("account")) {
					sessionStorage.removeItem("account")
				} else if (sessionStorage.getItem("memberAccount")) {
					sessionStorage.removeItem("memberAccount")
				}
			})
		}

		function onTheRportMessage() {

			let TheRportMessage = document.querySelectorAll(".reportMessage")
			
			TheRportMessage.forEach((e, i) => {
				e.addEventListener("click", function (e) {
					console.log(321);
					if (<% Object userId4 = session.getAttribute("userid");
					out.print(userId4 == null);%>) {
					alert("您尚未登入")
					location.href = "/TGA103G1/MessageReport";
				}else {

					if (confirm("確定檢舉此留言?")) {
						var text = $(this).closest("main").find("aside").text();
						// sessionStorage.setItem("forumId",<%=request.getParameter("page")%>);
						var report = prompt('請輸入檢舉原因');
						if (report != null && report != '') {
							$.ajax({
								url: "/TGA103G1/MessageReport",
								data: {
									"action": "insert",
									"forumId":<%=request.getParameter("page") %>,
									"reason": report,
									"time": text
								},
								type: "post",
								dataType: "text",
								success: function (xhr) {
									console.log(xhr);
								}
							})
							alert('已送出');
						} else if (report == '') {
							console.log(2)
							alert('請輸入檢舉原因再送出');
						} else {
							console.log(3)
						}
					} 
				}
			});
		})

}
onTheRportMessage();
		var ContentMessage = CKEDITOR.replace('editorContentMessage');

		document.querySelector("#alertContentMessage").addEventListener("click", function (ev) {
			CKEDITOR.instances.editorContentMessage.updateElement();
			if (
								<% Object userId = session.getAttribute("userid");
			out.print(userId == null);%>
								) {
			alert("您尚未登入")
			location.href = "/TGA103G1/PostForumContentMessage?action=AreYouLogin";
		} else {
			console.log($('textarea[name=editorContentMessage]').val());

			if (confirm("確定要送出嗎?")) {
				$.ajax({
					url: "/TGA103G1/PostForumContentMessage",
					type: "post",
					data: {
						"memberId": "${userid.member_id}",
						"forumId": <%=request.getParameter("page") %>,
						"content": $('textarea[name=editorContentMessage]').val(),
					},
					dataType: "text",
					success: function (xhr) {
						$('textarea[name=editorContentMessage]').val("");
						console.log(xhr);
					},
					complete: function (xhr) {
						location.reload(true);
					}
				});
			}
			// window.location.reload();
		}
		
								  })
		window.onload = () => {
			onTheRportMessage();
		}
	</script>
	<script>



	</script>

	<script>
		document.querySelector("#reportContent").addEventListener("click", function () {

			if (<% Object userId3 = session.getAttribute("userid");
			out.print(userId3 == null);%>) {
			alert("您尚未登入")
			location.href = "/TGA103G1/ForumContentReport";
		}else {

			if (confirm("確定檢舉此篇文章?")) {
				sessionStorage.setItem("forumId",<%=request.getParameter("page") %>);
				location.href = "/TGA103G1/fornt-end/forumContentReport/forumContentReprot.jsp";
			}
		}
						});
	</script>

	<script>
		let forum_id = "${param.page}";
		let member_id = "${userid.member_id}";

		window.onload = function () {
			$.ajax({
				url: "/TGA103G1/AddViewCount",
				data: {
					"action": "addView",
					"forumId":<%=request.getParameter("page") %>},
				type: "post",
				dataType: "text",
				success: function (xhr) {
					console.log(xhr);
				}
			})
		}
	</script>
	<script>
		//判斷是否有按讚
			fetch('/TGA103G1/SelectLike', {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({
					forum_id: forum_id,
					member_id: member_id
				}),
			})
				.then(resp => resp.json())
				.then(body => {
					if(body.successful == true){
						$("#like").css("display" , "none")
						$("#like2").css("display" ,"inline-block")
					}

				});

		document.querySelector("#like").addEventListener("click", function () {
			if (
								<% Object userId2 = session.getAttribute("userid");
			out.print(userId2 == null);%>
									) {
			alert("您尚未登入")
			location.href = "/TGA103G1/PostForumContentMessage?action=AreYouLogin";
		}else {
			// 									console.log("A")

			console.log("forum" + forum_id);
			console.log("member" + member_id);
			fetch('/TGA103G1/AddLike', {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({
					forum_id: forum_id,
					member_id: member_id
				}),
			})
				.then(resp => resp.json())
				.then(body => {
					document.getElementById("like").style.display = "none"
					document.getElementById("like2").style.display = "inline-block"
					console.log(body)

				});

		}
								});

		document.querySelector("#like2").addEventListener("click", function () {



			fetch('/TGA103G1/DeleteLike', {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({
					forum_id: forum_id,
					member_id: member_id
				}),
			})
				.then(resp => resp.json())
				.then(body => {
					document.getElementById("like").style.display = "inline-block"
					document.getElementById("like2").style.display = "none"
					console.log(body)

				});

		});

	</script>
</body>

</html>