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
					href="<%=request.getContextPath()%>/admin/console/members.jsp">修改會員資料<span
					class="badge" style="background-color: rgb(50, 100, 82);"><%=((List) session.getAttribute("members")).size()%>
				</span></a> <a
					class="list-group-item list-group-item-action list-group-item-light p-3"
					href="<%=request.getContextPath()%>/admin/console/administrators.jsp">修改管理員<span
					class="badge" style="background-color: rgb(50, 100, 82);"><%=((List) session.getAttribute("admins")).size()%>
				</span></a> <a
					class="list-group-item list-group-item-action list-group-item-light p-3"
					href="<%=request.getContextPath()%>/admin/console/messageReport.jsp">留言檢舉<span
					class="badge" style="background-color: rgb(50, 100, 82);"><%=((List) session.getAttribute("messageReportList")).size()%>
				</span></a> <a
					class="list-group-item list-group-item-action list-group-item-light p-3"
					href="/TGA103G1/admin/console/forumReport.html">文章檢舉<span
					class="badge" style="background-color: rgb(50, 100, 82);"><%=((List) session.getAttribute("forumReport")).size()%>
				</span></a> <a
					class="list-group-item list-group-item-action list-group-item-light p-3"
					href="/TGA103G1/admin/console/onTheShelf.jsp">審核商品上架<span
					class="badge" style="background-color: rgb(50, 100, 82);"><%=((List) session.getAttribute("products")).size()%>
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
			<div class="container-fluid">
				<table class="table table-hover">
					<caption>悬停表格布局</caption>
					<thead>
						<tr>
							<th>名称</th>
							<th>城市</th>
							<th>邮编</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Tanmay</td>
							<td>Bangalore</td>
							<td>560001</td>
						</tr>
						<tr>
							<td>Sachin</td>
							<td>Mumbai</td>
							<td>400003</td>
						</tr>
						<tr>
							<td>Uma</td>
							<td>Pune</td>
							<td>411027</td>
						</tr>
					</tbody>
				</table>
				<!------------------------------------------------------->
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">名字</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="firstname"
								placeholder="请输入名字" />
						</div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">姓</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="lastname"
								placeholder="请输入姓" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label> <input type="checkbox" />请记住我
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary">登入</button>
						</div>
					</div>
				</form>
				<!------------------------------------------------------->

			</div>
		</div>
	</div>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="<%=request.getContextPath()%>/admin/js/admin.js"></script>
</body>
</html>