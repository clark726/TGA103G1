<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Product</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backproduct.css">
<style>
div.div_func div  a {
	text-decoration: none;
	color: white;
}
</style>
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
			<form action="<%=request.getContextPath()%>/ProductServlet"
				method="post" enctype="multipart/form-data">
				<div class="all_product">
					<div class="title">
						<h2>新增商品</h2>
					</div>
					<div class="comm">
						<label for="p_type"> 商品類別: </label> <select name="p_type"
							class="p_type">
							<option value="1">水果類</option>
							<option value="2">茶類</option>
							<option value="3">氣泡類</option>
							<option value="4">草本類</option>
						</select>
					</div>

					<div class="comm">
						<label for="p_name">商品名稱 : </label> <input type="text" id="p_name"
							name="p_name"
							value="<%=(productVO == null) ? "" : productVO.getName()%>">
						<p>${errorMsgs.p_name}</p>
					</div>

					<div class="comm" id="p_pre">
						<label>商品圖片：</label>
						<div class="div_file">
							<input type="file" class="p_file" id="p_file1" name="p_file1" accept="image/gif, image/jpeg, image/png">
						</div>
						<div class="all_preview">
							<div class="preview" id="preview1">
								<span class="text" id="text1">預覽圖</span>
							</div>
						</div>

					</div>

					<div class="comm">
						<label for="p_price">商品售價 :</label> <input type="text"
							id="p_price" name="p_price"
							value="<%=(productVO == null) ? "" : productVO.getPrice()%>">
						<p>${errorMsgs.p_price}</p>
					</div>

					<div class="comm">
						<label for="p_stock">庫存 : </label> <input type="text" id="p_stock"
							name="p_stock"
							value="<%=(productVO == null) ? "" : productVO.getStock()%>">
						<p>${errorMsgs.p_stock}</p>
					</div>

					<div class="comm">
						<label for="p_produce">商品介紹 :</label>
						<textarea name="p_produce" id="p_produce" cols="30" rows="10"><%=(productVO == null) ? "" : productVO.getDescription()%></textarea>
						<p>${errorMsgs.p_produce}</p>

						<div id="button">
							<div id="button_new">
								<input type="submit" value="新增"></input> <input type="hidden"
									name="action" value="insert">
							</div>

						</div>
					</div>


				</div>
			</form>
		</main>
	</div>




	<script src="../js/jquery-3.6.0.min.js"></script>
	<script src="../js/slider.js"></script>
	<script>
 
     //file_1
     document.querySelector("#p_file1").addEventListener("change", function(e){
        if(this.files.length > 0){
        	console.log("aa");
          document.querySelector("#preview1").innerHTML="";
          let reader = new FileReader();
        reader.readAsDataURL(this.files[0]);
        reader.addEventListener("load", function(){
          
          let str = `
            <img src="\${reader.result}" class="preview_img" >
          `;
          document.querySelector("#preview1").innerHTML = str;
         
        });
      
        }else{
          document.querySelector("#text1").innerText="預覽圖";
        };
      });
      
      
      //header
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
