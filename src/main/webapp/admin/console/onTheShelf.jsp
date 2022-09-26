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
table {
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
					<a class="front_paga"
						href="<%=request.getContextPath()%>/admin/console/members.jsp">修改會員資料</a>
				</div>
				<div>
					<a class="manager_item" type="button"
						href="<%=request.getContextPath()%>/admin/console/administrators.jsp">管理員們</a>
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
			<select class="select">
				<option disable>查看</option>
				<option value="1">待上架</option>
				<option value="2">全部</option>
			</select>
			<table>
				<tr>
					<td>id</td>
					<td>type</td>
					<td>price</td>
					<td>store</td>
					<td>stock</td>
					<td>status</td>
					<td>date</td>
					<td>image</td>
					<td>update</td>
					<td>reset</td>
				</tr>
				<c:forEach var="product" items="${products}" varStatus="s">
					<c:if test="${product.status <= productStatus}">
						<tr>
							<form action="/TGA103G1/control" method="post">
							<input type="hidden" name="action" value="updateProdcut">
							<input type="hidden" name="productId" value="${product.product_id }">
							<td>${product.product_id }</td>
							<td>${product.name }</td>
							<td>${product.price }</td>
							<td>${product.store_id }</td>
							<td>${product.stock }</td>
							<td>
								<select name="status">
									<option value="1" ${product.status==1?"selected":"" } >待上架</option>
									<option value="2" ${product.status==2?"selected":"" }>已上架</option>
								</select>
							</td>
							<td>${product.date }</td>
							<td>
								<a class="front_paga"
						href="<%=request.getContextPath()%>/control?action=watchimage&id=${product.product_id }">
									看圖片
								</a>
							</td>
							<td><button type="submit">update</button></td>
							<td><button type="reset">reset</button></td>
							</form>
						</tr>
					</c:if>
				</c:forEach>
			</table>
			<h4>${productStatus }</h4>
		</main>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		document.querySelector(".select").addEventListener("change",
				function(ev) {
					//   console.log(ev.srcElement.length);
					for (var x = 0; x < ev.srcElement.length; x++) {
						if (ev.srcElement[x].selected) {
							  console.log(ev.srcElement[x].value);
							$.ajax({
								url : "/TGA103G1/control",
								type : "post",
								data : {
									action : "roductStatusSelect",
									"productStatus" : ev.srcElement[x].value
								},
								dataType : "text",
								success : function(xhr) {
									console.log(xhr);
								},
								complete: function(xhr){
									history.go(0);
								}
							});
						}
					}
				});
	</script>
</body>
</html>
