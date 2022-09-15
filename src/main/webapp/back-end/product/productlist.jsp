<%@page import="com.product_img.model.Product_imgVO"%>
<%@page import="com.product_img.model.Product_imgService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
ProductService productsvc = new ProductService();
List<ProductVO> list = productsvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有員工資料 - listAllEmp.jsp</title>

<style>
table#table-1 {
	background-color: #20202b;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}

table {
	width: 1000px;
	margin-top: 5px;
	margin-bottom: 5px;
	border: 1px solid red;
	transform: translate(80px, 10%);
}

table, th, td {
	border: 1px solid #37322a;
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
	width: 100px
}
</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/back.css">
</head>
<body style="background-color: rgb(129, 93, 65)">

	<div w3-include-html="<%=request.getContextPath()%>/com/header.jsp"></div>
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


				<table>
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
			var z, i, elmnt, file, xhttp;
			/* Loop through a collection of all HTML elements: */
			z = document.getElementsByTagName("*");
			for (i = 0; i < z.length; i++) {
				elmnt = z[i];
				/*search for elements with a certain atrribute:*/
				file = elmnt.getAttribute("w3-include-html");
				if (file) {
					/* Make an HTTP request using the attribute value as the file name: */
					xhttp = new XMLHttpRequest();
					xhttp.onreadystatechange = function() {
						if (this.readyState == 4) {
							if (this.status == 200) {
								elmnt.innerHTML = this.responseText;
							}
							if (this.status == 404) {
								elmnt.innerHTML = "Page not found.";
							}
							/* Remove the attribute, and call this function once more: */
							elmnt.removeAttribute("w3-include-html");
							includeHTML();
						}
					};
					xhttp.open("GET", file, true);
					xhttp.send();
					/* Exit the function: */
					return;
				}
			}
		}
		includeHTML();
	</script>
</body>
</html>