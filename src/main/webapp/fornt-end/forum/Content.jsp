<%@page import="com.mysql.cj.Session" %>
	<%@page import="com.member.vo.MemberVO" %>
		<%@page import="com.forum.model.service.impl.ForumServiceImpl,java.util.*" %>
			<%@page import="com.forum.model.ForumVO" %>
				<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
					<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

						<!DOCTYPE html>
						<html lang="en">

						<head>
							<meta charset="UTF-8" />
							<meta http-equiv="X-UA-Compatible" content="IE=edge" />
							<meta name="viewport" content="width=device-width, initial-scale=1.0" />
							<title>Content</title>
							<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css" />
							<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backproduct.css">
							<!-- CSS only -->
							<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
								rel="stylesheet"
								integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
								crossorigin="anonymous">
								<style>
									p{
									 color: black !important
									}
								</style>
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
							<h2 align="center">${vo.title}</h2>
							<button id="reportContent" style="TRANSFORM: TRANSLATEX(1200%);">檢舉文章</button>
							<div class="container-fluid">
								<div class="row">
									<div class="col-2" align="center">
										<div class="text" style="background-color: White; height: 400px">
											<main style="border: 1px solid green; height: 150px">
												<main style="border: 1px solid green; height: 25px">
													作者: ${forumContentMemberId.account}
													<main style="height: 100px;">
														<img src="${forumContentMemberId.gender==1?"
															/TGA103G1/front-end/member/images/woman.png":"/TGA103G1/front-end/member/images/man.png"}"
															style="width: 100px;">
													</main>
													<main style="border: 1px solid green; height: 25px">性別:
														${forumContentMemberId.gender==1?"女":"男"}</main>
													<main style="border: 1px solid green; height: 25px">電子信箱:
														${forumContentMemberId.email}</main>
										</div>
									</div>
									<div class="col-8" align="center">
										<div class="text" style="background-color: #FFD382; height: 580px">
											<aside style="padding-bottom: 0px">發文時間:${vo.date}</aside>
											<div
												style="border: 1px solid black; height: 502px; margin: 10px; margin: 10px">
												${vo.content}</div>
											<button>我覺得這篇文章讚</button>
										</div>
									</div>

									<hr>
									<c:forEach var="fmsg" items="${forumMessage}">
										<div class="container-fluid">
											<div class="row">
												<div class="col-2" align="center">
													<div class="text" style="background-color: White; height: 200px">
														<main style="border: 1px solid green; height: 150px">
															<main style="border: 1px solid green; height: 25px">
																留言作者: ${fmsg.account}
																<main style="height: 100px;">
																	<img src="${fmsg.gender==1?"
																		/TGA103G1/front-end/member/images/woman.png":"/TGA103G1/front-end/member/images/man.png"}"
																		style="width: 100px;">
																</main>
																<main style="border: 1px solid green; height: 25px">性別:
																	${fmsg.gender==1?"女":"男"}</main>
																<main style="border: 1px solid green; height: 25px">
																	電子信箱:
																	${fmsg.email}</main>
													</div>
												</div>
												<div class="col-8" align="center">
													<div class="text" style="background-color: #FFD382; height: 300px">
														<aside style="padding-bottom: 0px">留言時間:${fmsg.date}</aside>
														<button id="reportMessage"
															style="TRANSFORM: TRANSLATEX(450%);">檢舉留言</button>
														<div
															style="border: 1px solid black; height: 225px; margin: 10px; margin: 10px">
															${fmsg.content}</div>
													</div>
												</div>
												<hr>
											</div>
										</div>
									</c:forEach>
									<div class="container-fluid">
										<div class="row">
											<div class="col-2" align="center">
												<div class="text" style="background-color: White; height: 200px">
													<!-- 						<form action="/TGA103G1/PostForumContentMessage" method="post"> -->
													<main style="border: 1px solid green; height: 150px">
														<main style="border: 1px solid green; height: 25px">留言作者:
															${userid.account}
															<main style="height: 100px;">
																<img src="${userid.gender==1?"
																	/TGA103G1/front-end/member/images/woman.png":"/TGA103G1/front-end/member/images/man.png"}"
																	style="width: 100px;">
															</main>
															<main style="border: 1px solid green; height: 25px">性別:
																${userid.gender==1?"女":"男"}</main>
															<main style="border: 1px solid green; height: 25px">電子信箱:
																${userid.email}</main>
												</div>
											</div>
											<div class="col-8" align="center">
												<div class="text" style="background-color: #FFD382; height: 385px">
													<div>
														<h5>文章留言</h5>
													</div>
													<div
														style="border: 1px solid black; height: 310px; margin: 10px; margin: 10px">
														<textarea name="editorContentMessage"
															style="height: 600px"></textarea>
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
							<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js">

							</script>
							<script>
								var ContentMessage = CKEDITOR.replace('editorContentMessage');

								document.querySelector("#alertContentMessage").addEventListener("click", function () {
									CKEDITOR.instances.editorContentMessage.updateElement();
									if (
								<% Object userId = session.getAttribute("userid");
									out.print(userId == null);%>
								) {
									alert("你沒登入")
									location.href = "/TGA103G1/PostForumContentMessage?action=AreYouLogin";
								} else {
									console.log($('textarea[name=editorContentMessage]').val());
									if (confirm("確定要送出嗎?")) {
										$.ajax({
											url: "/TGA103G1/PostForumContentMessage",
											type: "post",
											data: {
												"memberId": "${userid.member_id}",
												"forumId": <%=request.getParameter("page")%>,
												"content":$('textarea[name=editorContentMessage]').val()
											},
											dataType: "text",
											success: function (xhr) {
												$('textarea[name=editorContentMessage]').val()="";
												console.log(xhr);
											},
											complete:function(xhr){
												location.reload(true);
											}
										});
									}
									window.location.reload(); 
								}
								  })
							</script>
							<script>
								function reportContentFuntion() {
									var r = confirm("確定檢舉此篇文章?");
									if (r == true) {
										x = "yes"
										console.log(x);
									}
								}
								$('#reportContent').on("click", function () {
									reportContentFuntion()
								})
							</script>
						</body>

						</html>