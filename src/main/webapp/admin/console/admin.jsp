<%@page import="java.util.*"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BarJarJo</title>
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<link href="<%=request.getContextPath()%>/admin/css/admin.css" rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous" />
<style>
form {
	display: inline-block;
}
</style>
</head>
<body>
	<%
	try {
		Integer a = Integer.parseInt(request.getParameter("page"));
		Integer b = (Integer) request.getSession().getAttribute("memberPages");
		if (a > b - 1 || a < 0) {
			response.sendRedirect(request.getContextPath() + "/admin/console/admin.jsp?page=0");
			return;
		}
	} catch (Exception e) {
		response.sendRedirect(request.getContextPath() + "/admin/console/admin.jsp?page=0");
		return;
	}
	%>
	<div class="d-flex" id="wrapper">
		<div class="border-end bg-white" id="sidebar-wrapper">
			<div class="sidebar-heading border-bottom bg-light">BarJarJo</div>
			<div class="list-group list-group-flush">
				<a
					class="list-group-item list-group-item-action list-group-item-light p-3"
					href="<%=request.getContextPath()%>/admin/console/admin.jsp">修改會員資料</a> <a
					class="list-group-item list-group-item-action list-group-item-light p-3"
					href="<%=request.getContextPath()%>/admin/console/administrators.jsp">修改管理員<span
					class="badge" style="background-color: rgb(50, 100, 82);"><%=((List) session.getAttribute("admins")).size()%>
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
							<li class="nav-item active"><a class="nav-link" href="<%=request.getContextPath()%>/main.html" target="_blank">Home</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/fornt-end/MAP/map.html" target="_blank">地圖</a></li>
							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/fornt-end/forum/forum.jsp" target="_blank">討論區</a></li>
							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/shop/shopProduct.html" target="_blank">購物商城</a>
							</li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
								role="button" data-bs-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">店家主題</a>
								<div class="dropdown-menu dropdown-menu-end"
									aria-labelledby="navbarDropdown">
									<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/store/storeType.html">Bistro</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/store/storeType.html">Cocktail Bar</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/store/storeType.html">Whicky Bar</a>
								</div></li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
								role="button" data-bs-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">帳號</a>
								<div class="dropdown-menu dropdown-menu-end"
									aria-labelledby="navbarDropdown">
									<a class="dropdown-item" href="updatePassword.jsp">修改資料</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="/TGA103G1/control">登出</a>
								</div></li>
						</ul>
					</div>
				</div>
			</nav>
			<!-- Page content-->

			<form action="<%=request.getContextPath()%>/control" method="post"
				style="float: right;">
				<input type="hidden" name="action" value="logout">
			</form>
			<h2>Hi ${admin.account}</h2>

			<form action="<%=request.getContextPath()%>/control" method="post">
				<input type="hidden" name="action" value="search"> <input
					type="number" name="member_id" placeholder="member_id"
					class="searchInput">
				<button type="submit" class="searchButton">search</button>
			</form>

			<form action="<%=request.getContextPath()%>/control" method="post">
				<input type="hidden" name="action" value="cancelSearch">
				<button type="submit">cancel</button>
			</form>

			<div class="container-fluid">
				<table class="table table-hover">
					<caption>
<%-- 						<a href="<%=request.getContextPath()%>/admin/console/members.jsp">顯示較多</a> --%>
					</caption>
					<c:if test="${param.page != 0}">
						<a
							href="<%=request.getContextPath()%>/admin/console/admin.jsp?page=${param.page-1}">上一頁</a>
					</c:if>
					<c:if test="${param.page < memberPages-1}">
						<a
							href="<%=request.getContextPath()%>/admin/console/admin.jsp?page=${param.page+1}">下一頁</a>
					</c:if>
					<thead>
						<tr>
							<th>id</th>
							<th>帳號</th>
							<th>地址</th>
							<th>小名</th>
							<th>電話</th>
							<th>權限</th>
							<th>修改</th>
							<th>回復</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="member" items="${members}"
							begin="${param.page*datas}" end="${param.page*datas+(datas-1)}"
							varStatus="s">
							<tr>
								<form action="<%=request.getContextPath()%>/control"
									method="post">
									<td>${member.member_id}</td> <input type="hidden"
										name="member_id" class="member_id" value="${member.member_id}">
									<td>${member.account}</td>
									<td>${member.address}</td>
									<td>${member.nickname}</td>
									<td>${member.phone}</td>
									<td><select name="permission">
											<option value="0" ${member.permission == 0 ? "selected":""}>已封鎖</option>
											<option value="1" ${member.permission == 1 ? "selected":""}>已註冊</option>
											<option value="2" ${member.permission == 2 ? "selected":""}>已驗證</option>
									</select></td>
									<td><button type="submit" class="update">update</button></td>
									<td><button type="reset">reset</button></td> <input
										type="hidden" name="action" value="update">
								</form>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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
		document.querySelectorAll(".update").forEach(function(target) {
			target.addEventListener("click", function(e) {
				var phonelist = document.querySelectorAll(".phone");
				var nicknamelist = document.querySelectorAll(".nickname");
				for (let x = 0; x < phonelist.length; x++) {
					if (phonelist[x].value.length > 10) {
						e.preventDefault();
						alert("phone number to long");
						return;
					}
					if (!/^\d+$/.test(phonelist[x].value)) {
						e.preventDefault();
						alert("phone is not number");
						return;
					}
					if (phonelist[x].value.length < 8) {
						e.preventDefault();
						alert("phone is to short");
						return;
					}
				}

				for (var x = 0; x < nicknamelist.length; x++) {
					if (nicknamelist[x].value.lenght > 50) {
						e.preventDefault();
						alert("nickname too long");
					}
				}
				;

			})
		});

		document.querySelector(".searchButton").addEventListener(
				"click",
				function(ev) {
					var ipt = document.querySelector(".searchInput").value
							.trim();
					if (ipt == "") {
						ev.preventDefault();
					}
					if (!(/^\d+$/.test(ipt))) {
						ev.preventDefault();
					}
				})
	</script>
</body>
</html>