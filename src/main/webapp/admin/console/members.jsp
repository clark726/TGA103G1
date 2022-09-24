<%@page import="java.util.ArrayList"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link rel="stylesheet" href="/TGA103G1/admin/css/header.css" />
<link rel="stylesheet" href="/TGA103G1/admin/css/back.css">
<style>
td {
	border: 1px solid black;
}

.member_id, .account {
	max-width: 100px;
}

form {
	display: inline-block;
}

body {
	display: block;
	margin: 0px;
}
</style>
</head>
<body>
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
				<li><a href="/TGA103G1/control">登出</a></li>

			</ul>
		</nav>
	</header>

	<div class="contain">
		<aside class="aside">
			<p id="p1">管理者後台</p>
			<div class="div_func">
				<div>
					<a class="front_paga" href="<%=request.getContextPath()%>/admin/console/members.jsp">修改會員資料</a>
				</div>
				<div>
					<a class="manager_item" type="button" href="<%=request.getContextPath() %>/admin/console/administrators.jsp">管理員們</a>
				</div>
				<div>
					<a class="edit_item" type="button">修改商家訂單內容</a>
				</div>
				<hr>
				<div>
					<a class="front_paga"
						href="<%=request.getContextPath()%>/admin/console/messageReport.jsp">留言檢舉</a>
				</div>
				<div>
					<a class="forum" href="/TGA103G1/admin/console/forumReport.html">文章檢舉</a>
				</div>
				<div>
					<a class="forum" href="/TGA103G1/admin/console/onTheShelf.jsp">審核上架</a>
				</div>
				<div>
					<a class="change_state" type="button">改變帳號狀態</a>
				</div>
				<div>
					<a class="manager" type="submit">..</a>
				</div>
			</div>
		</aside>

		<main class="main">
			<%-- 			<form action="<%=request.getContextPath()%>/control" method="post" --%>
			<!-- 				style="float: right;"> -->
			<!-- 				<input type="hidden" name="action" value="logout"> -->
			<!-- 				<button type="submit">logout</button> -->
			<!-- 			</form> -->
			<h2>Hi ${admin}</h2>

			<form action="<%=request.getContextPath()%>/control" method="post">
				<input type="hidden" name="action" value="search"
					> <input type="number" name="member_id"
					placeholder="member_id" class="searchInput">
				<button type="submit" class="searchButton">search</button>
			</form>

			<form action="<%=request.getContextPath()%>/control" method="post">
				<input type="hidden" name="action" value="cancelSearch">
				<button type="submit">cancel</button>
			</form>
			<h2>${error}</h2>
			<br>

			<hr>

			<a
				href="<%=request.getContextPath()%>/admin/console/admin.jsp?page=0">顯示較少</a>
			<hr>

			<table>
				<tr>
					<td>member_id</td>
					<td>account</td>
					<td>address</td>
					<td>nickname</td>
					<td>phone</td>
					<td>permission</td>
					<td>update</td>
					<td>reset</td>
				</tr>
				<c:forEach var="member" items="${members}" varStatus="s">
					<tr>
						<form action="<%=request.getContextPath()%>/control" method="post">
							<td>${member.member_id}</td> 
								<input type="hidden" name="member_id" class="member_id" value="${member.member_id}">
							<td>${member.account}</td> 
							<td>${member.address}</td> 
							<td>${member.nickname}</td> 
							<td>${member.phone}</td> 
							<td>
								<select name="permission">
									<option value="0" ${member.permission == 0 ? "selected":""}>已封鎖</option>
									<option value="1" ${member.permission == 1 ? "selected":""}>已註冊</option>
									<option value="2" ${member.permission == 2 ? "selected":""}>已驗證</option>
								</select>
							</td>
							<td><button type="submit" class="update">update</button></td>
							<td><button type="reset">reset</button></td> 
							<input type="hidden" name="action" value="update"> 
						</form>
					</tr>
				</c:forEach>
			</table>
		</main>
	</div>
	<script type="text/javascript">
	document.querySelector(".searchButton").addEventListener("click",function(ev){
		var ipt = document.querySelector(".searchInput").value.trim();
		if(ipt == ""){
			e.preventDefault();
		}
		if(!(/^\d+$/.test(ipt))){
			ev.preventDefault();
		}
	})
	</script>
</body>
</html>
