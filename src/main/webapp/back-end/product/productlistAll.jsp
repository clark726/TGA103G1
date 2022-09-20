<%@page import="com.product_img.model.Product_imgVO"%>
<%@page import="com.product_img.model.Product_imgService"%>
<%@page import="com.product.service.ProductService"%>
<%@page import="com.product.service.impl.ProductServiceImpl"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
ProductService productsvc = new ProductServiceImpl();
List<ProductVO> list = productsvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>所有員工資料 - listAllEmp.jsp</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/back.css">
<style>
table#table-1 {
	border: 1px solid black;
	text-align: center;
}

table {
	width: 1000px;
	margin-top: 5px;
	margin-bottom: 5px;
	border: 1px solid black;
	transform: translate(80px, 10%);
}

table#table-1  tr {
	border-color: inherit;
	border-style: solid;
	border-width: 0;
	border: 1px solid black;
}

table#table-1  th {
	border-color: inherit;
	border-style: solid;
	border-width: 0;
	border: 1px solid black;
}

table#table-1  td {
	border-color: inherit;
	border-style: solid;
	border-width: 0;
	border: 1px solid black;
}

th, td {
	padding: 5px;
	text-align: center;
}

h1 {
	text-align: center;
}

h1 a {
	text-decoration: none;
	color: black;
}

h1 button {
	font-size: 20px;
}

div.div_func div  a {
	text-decoration: none;
	color: white;
}

img {
	width: 100px;
	height: 100px
}
</style>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
	crossorigin="anonymous"></script>

</head>
<body style="background-color: rgb(129, 93, 65)">
	<div id="xxx" ></div>
	<div class="contain">
		<aside class="aside">
			<p id="p1">廠商後台</p>
			<div class="div_func">
				<div>
					<a class="edit_store" type="button">修改店家內容</a>
				</div>
				<div>
					<a class="manager_item" type="button"
						href="<%=request.getContextPath()%>/back-end/product/productlist.jsp">商品管理</a>
				</div>
				<div>
					<a class="edit_item" type="button">修改商家訂單內容</a>
				</div>

			</div>
		</aside>
		<main class="main">

			<h1>
				商品列表
				<button>
					<a
						href="<%=request.getContextPath()%>/back-end/product/product.jsp">新增商品</a>
				</button>
			</h1>
			<div class="div_table">


				<table
					class="table table-striped table-bordered table-hover table-condensed"
					style="max-width: 1000px; background-color: #c8bf9b57; border: black; text-align: center;">
					<tr>
						<th>商品編號</th>
						<th>商品圖片</th>
						<th>商品名稱</th>
						<th>價錢</th>
						<th>商品描述</th>
						<th>類型</th>
						<th>庫存</th>
						<th>狀態</th>
						<th>修改</th>
						<th>刪除</th>
					</tr>
					<%@ include file="page1.file"%>
					<c:forEach var="proVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">

						<tr>
							<td>${proVO.product_id}</td>
							<td><img
								src="<%=request.getContextPath()%>/ProductServlet?action=getImg&product_id=${proVO.product_id}">
							</td>
							<td>${proVO.name}</td>
							<td>${proVO.price}</td>
							<td>${proVO.description}</td>
							<td>${proVO.type_id == 1 ?  "水果" : ""}${proVO.type_id == 2 ?  "茶類" : ""}
								${proVO.type_id == 3 ?  "氣泡" : ""} ${proVO.type_id == 4 ?  "草本" : ""}</td>
							<td>${proVO.stock}</td>
							<td>${proVO.status == 0 ? "下架":"上架"}</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/ProductServlet"
									style="margin-bottom: 0px;">
									<input type="submit" value="修改"> <input type="hidden"
										name="product_id" value="${proVO.product_id}"> <input
										type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/ProductServlet"
									style="margin-bottom: 0px;">
									<input type="submit" value="刪除"> <input type="hidden"
										name="product_id" value="${proVO.product_id}"> <input
										type="hidden" name="action" value="delete">
								</FORM>
							</td>
						</tr>
					</c:forEach>
					<%@ include file="page2.file"%>
				</table>
			</div>
		</main>
	</div>
	<script>
		function includeHTML() {
			const xxx = document.querySelector('#xxx');
				fetch('/TGA103G1/com/header.html')
					.then(resp => resp.text())
					.then(content => {
						xxx.innerHTML = content;
						
						const username = document.querySelector('#account');
						const password = document.querySelector('#password');
						const errMsg = document.querySelector('#errMsg');
						document.getElementById('btn1').addEventListener('click', () => {
						    fetch('http://localhost:8080/TGA103G1/StoreLogin', {
						      method: 'POST',
						      headers: { 'Content-Type': 'application/json' },
						      body: JSON.stringify({
						        account: username.value,
						        password: password.value
						      }),
						    })
						      .then(resp => resp.json() )
						      .then(body => {
						        errMsg.textContent = "";
						        const { successful, message } = body;
						        if (successful) {
						          const { account, password} = body;
						          sessionStorage.setItem('account', account);
						          sessionStorage.setItem('password', password);
						          location = '../back-end/product/productlist.jsp';
						        } else {
						          errMsg.textContent = message;
						        }
						      });
						  });			
					});
		}
		includeHTML();
	</script>
</body>
</html>