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
	table{
	display: flex;
	justify-content: center; 
	align-items: center; 
	}
	td {
		border: 1px solid black;
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
					<a class="manager_item" href="/TGA103G1/admin/console/administrators.jsp">管理員們</a>
				</div>
				<div>
					<a class="edit_item" type="button">修改商家訂單內容</a>
				</div>
				<hr>
				<div>
					<a class="front_paga" href="<%=request.getContextPath()%>/admin/console/messageReport.jsp">留言檢舉</a>
				</div>
				<div>
					<a class="forum" href="/TGA103G1/admin/console/forumReport.html">文章檢舉</a>
				</div>
				<div>
					<a class="audit" type="button">審核上架</a>
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
			<table>
				<tr>
					<td>id</td>
					<td>account</td>
					<td>password</td>
					<td>delete</td>
				</tr>
				<c:forEach items="${admins }" var="admin">
					<tr>
						<td class="adminId">${admin.manager_id}</td>
						<td>${admin.account }</td>
						<td>${admin.password }</td>
						<td><button type="button" class="delete ${admin.manager_id}">delete</button></td>
					</tr>
				</c:forEach>	
			</table>
			<a class="front_paga" href="<%=request.getContextPath()%>/admin/console/register.jsp">註冊新管理員</a>
		</main>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
    document.querySelectorAll(".delete").forEach(function (el) {
        el.addEventListener("click", function (ev) {
            var trEl = el.closest("tr");
          var adminId = trEl.children[0].innerText;
          if (window.confirm("確定要刪除嗎?")) {
            $.ajax({
              url: "/TGA103G1/control",
              type: "post",
              data: { action: "deleteAdmin", adminId: adminId },
              dataType: "text",
              success: function (xhr) {
                alert(xhr);
				trEl.remove();
              },
              error: function (xhr) {
                console.log("error");
                console.log(xhr);
              },
            });
          }
        });
      });
	</script>
</body>
</html>
