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
					href="<%=request.getContextPath()%>/admin/console/admin.jsp">修改會員資料</a> <a
					class="list-group-item list-group-item-action list-group-item-light p-3"
					href="<%=request.getContextPath()%>/admin/console/administrators.jsp">修改管理員</a> <a
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
							<li class="nav-item active"><a class="nav-link" href="<%=request.getContextPath()%>/main.html" target="_blank">Home</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/fornt-end/MAP/map.html" target="_blank">地圖</a></li>
							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/fornt-end/forum/forum.jsp" target="_blank">討論區</a></li>
							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/shop/shopProduct.html" target="_blank">購物商城</a>
							</li>
<!-- 							<li class="nav-item dropdown"><a -->
<!-- 								class="nav-link dropdown-toggle" id="navbarDropdown" href="#" -->
<!-- 								role="button" data-bs-toggle="dropdown" aria-haspopup="true" -->
<!-- 								aria-expanded="false">店家主題</a> -->
<!-- 								<div class="dropdown-menu dropdown-menu-end" -->
<!-- 									aria-labelledby="navbarDropdown"> -->
<%-- 									<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/store/storeType.html">Bistro</a> --%>
<!-- 									<div class="dropdown-divider"></div> -->
<%-- 									<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/store/storeType.html">Cocktail Bar</a> --%>
<!-- 									<div class="dropdown-divider"></div> -->
<%-- 									<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/store/storeType.html">Whicky Bar</a> --%>
<!-- 								</div></li> -->
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
								role="button" data-bs-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">帳號</a>
								<div class="dropdown-menu dropdown-menu-end"
									aria-labelledby="navbarDropdown">
									<a class="dropdown-item" href="updatePassword.jsp">換密碼</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="/TGA103G1/control">登出</a>
								</div></li>
						</ul>
					</div>
				</div>
			</nav>
			<div class="container-fluid">
				<!------------------------------------------------------->
				<form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/control" method="post">
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">舊密碼</label>
						<div class="col-sm-10">
							<input type="password" class="form-control oldPassword" id="firstname"
								name="oldPassword" placeholder="舊密碼" />
						</div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">新密碼</label>
						<div class="col-sm-10">
							<input type="password" class="form-control newPassword" id="lastname"
								name="newPassword" placeholder="新密碼" />
						</div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">確認密碼</label>
						<div class="col-sm-10">
							<input type="password" class="form-control again" id="lastname"
								placeholder="新密碼" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="hidden" name="action" value="updateAdminPassword">
							<button type="sublit" class="btn btn-primary submit">更改</button>
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
	<script>
	document.querySelector(".submit").addEventListener("click",function(e) {
		var old = document.querySelector(".oldPassword").value;
		var np = document.querySelector(".newPassword").value;
		var again = document.querySelector(".again").value;
		if (old.length<8 || old.length>15) {
			e.preventDefault();
			alert("舊密碼是這樣嗎?");
			return;
		}
		if (np.length<8 || np.length>15) {
			e.preventDefault();
			alert("新密碼8~15字!");
			return;
		}
		if (!(/^\w+$/.test(np))) {
			e.preventDefault();
			alert("不要有特殊符號");
			return;
		}
		if (np != again) {
			e.preventDefault();
			alert("與再次輸入密碼有誤");
			return;
		}
	})
	</script>
</body>
</html>