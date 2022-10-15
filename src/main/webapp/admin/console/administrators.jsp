<%@page import="java.util.*"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BarJarJo</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="<%=request.getContextPath()%>/admin/css/admin.css"
	rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous" />
</head>
<body>
	<div class="d-flex" id="wrapper">
		<div class="border-end bg-white" id="sidebar-wrapper">
			<div class="sidebar-heading border-bottom bg-light">BarJarJo</div>
			<div class="list-group list-group-flush">
				<a
					class="list-group-item list-group-item-action list-group-item-light p-3"
					href="<%=request.getContextPath()%>/admin/console/admin.jsp">修改會員資料</a>
				<a
					class="list-group-item list-group-item-action list-group-item-light p-3"
					href="<%=request.getContextPath()%>/admin/console/administrators.jsp">修改管理員<span
					id="adminSpan" class="badge"
					style="background-color: rgb(50, 100, 82);"><%=((List) session.getAttribute("admins")).size()%>
				</span></a> <a
					class="list-group-item list-group-item-action list-group-item-light p-3"
					href="<%=request.getContextPath()%>/admin/console/messageReport.jsp">留言檢舉<span
					class="badge" style="background-color: rgb(50, 100, 82);"><%=((List) session.getAttribute("messageReportList")).size()%>
				</span></a> <a
					class="list-group-item list-group-item-action list-group-item-light p-3"
					href="/TGA103G1/admin/console/forumReport.jsp">文章檢舉<span
					class="badge" style="background-color: rgb(50, 100, 82);"><%=((List) session.getAttribute("forumReport")).size()%>
				</span></a> <a
					class="list-group-item list-group-item-action list-group-item-light p-3"
					href="/TGA103G1/admin/console/onTheShelf.jsp">審核商品上架<span
					class="badge" style="background-color: rgb(50, 100, 82);">${listing }
				</span></a> <a
					class="list-group-item list-group-item-action list-group-item-light p-3"
					href="/TGA103G1/admin/console/barjarjoFrontPage.jsp">修改首頁</a>
			</div>
		</div>
		<!-- Page content wrapper-->
		<div id="page-content-wrapper">
			<!-- Top navigation-->
			<nav
				class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
				<div class="container-fluid">
					<button class="btn btn-primary" id="sidebarToggle">Menu</button>
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav ms-auto mt-2 mt-lg-0">
							<li class="nav-item active"><a class="nav-link" href="#!">Home</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="#!">地圖</a></li>
							<li class="nav-item"><a class="nav-link" href="#!">討論區</a></li>
							<li class="nav-item"><a class="nav-link" href="#!">購物商城</a>
							</li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
								role="button" data-bs-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">店家主題</a>
								<div class="dropdown-menu dropdown-menu-end"
									aria-labelledby="navbarDropdown">
									<a class="dropdown-item" href="#!">Bistro</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="#!">Cocktail Bar</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="#!">Whicky Bar</a>
								</div></li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
								role="button" data-bs-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">會員專區</a>
								<div class="dropdown-menu dropdown-menu-end"
									aria-labelledby="navbarDropdown">
									<a class="dropdown-item" href="#!">我的最愛</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="#!">修改密碼</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="#!">訂單管理</a>
								</div></li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
								role="button" data-bs-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">帳號</a>
								<div class="dropdown-menu dropdown-menu-end"
									aria-labelledby="navbarDropdown">
									<a class="dropdown-item" href="#!">修改資料</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="/TGA103G1/control">登出</a>
								</div></li>
						</ul>
					</div>
				</div>
			</nav>
			<!-- Page content-->

			<!-- ----- -->
			<div class="container-fluid">
				<table class="table table-hover">
					<caption>
						<c:if test="${admin.status>1}">
							<a class="front_paga"
								href="<%=request.getContextPath()%>/admin/console/register.jsp">註冊新管理員</a>
						</c:if>
						<a class="front_paga" href="updatePassword.jsp">修改密碼</a>
					</caption>
					<thead>
						<tr>
							<td>id</td>
							<td>帳號</td>
							<td>最後登入時間</td>
							<td>生日</td>
							<td>權限</td>
							<c:if test="${admin.status>1}">
								<td>修改權限</td>
							</c:if>
							<c:if test="${admin.status>1}">
								<td>刪除</td>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${admins }" var="admins">
							<c:if test="${admin.status>=admins.status }">
								<tr>
									<td class="adminId">${admins.manager_id}</td>
									<td>${admins.account }</td>
									<td><c:if test="${not empty admins.lastLoginTime}">
									${admins.lastLoginTime.year}/${admins.lastLoginTime.monthValue}/${admins.lastLoginTime.dayOfMonth}
										${admins.lastLoginTime.hour}:${admins.lastLoginTime.minute}:${admins.lastLoginTime.second}</c:if></td>
									<td>${admins.birthday }</td>
									<td><c:if test="${admin.status>1}">
											<select id="status">
												<option value="2" ${admins.status==2?"selected":""}>最高權限</option>
												<option value="1" ${admins.status==1?"selected":""}>管理員</option>
											</select>
										</c:if> <c:if test="${admin.status!=2}">
											管理員
										</c:if></td>
									<c:if test="${admin.status>1}">
										<td>
											<button type="button" class="update ">update</button>
										</td>
									</c:if>
									<c:if test="${admin.status>1}">
										<td>
											<button type="button" class="delete">delete</button>
										</td>
									</c:if>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="<%=request.getContextPath()%>/admin/js/admin.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		document
				.querySelectorAll(".delete")
				.forEach(
						function(el) {
							el
									.addEventListener(
											"click",
											function(ev) {
												var trEl = el.closest("tr");
												var adminId = trEl.children[0].innerText;
												if (window.confirm("確定要刪除嗎?")) {
													$
															.ajax({
																url : "/TGA103G1/control",
																type : "post",
																data : {
																	action : "deleteAdmin",
																	"adminId" : adminId
																},
																dataType : "text",
																success : function(
																		xhr) {
																	alert(xhr);
																	trEl
																			.remove();
																	var adminspan = document
																			.querySelector("#adminSpan").innerHTML;
																	document
																			.querySelector("#adminSpan").innerHTML = --adminspan;
																},
																error : function(
																		xhr) {
																	console
																			.log("error");
																	console
																			.log(xhr);
																},
															});
												}
											});
						});

		document.querySelectorAll(".update").forEach(function(el) {
			el.addEventListener("click", function(ev) {
				var trEl = el.closest("tr");
				var adminId = trEl.children[0].innerText;
				$.ajax({
					url : "/TGA103G1/control",
					type : "post",
					data : {
						"action" : "updateAdmin",
						"adminId" : adminId,
						"adminStatus" : document.querySelector("#status").value
					},
					dataType : "text",
					success : function(xhr) {

					},
					error : function(xhr) {
						console.log("error");
						console.log(xhr);
					},
				});
			});
		});
	</script>
</body>
</html>