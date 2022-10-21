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
	href="<%=request.getContextPath()%>/css/backproduct1.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
	crossorigin="anonymous"></script> 

<style>
div.div_func div  a {
	text-decoration: none;
	color: white;
}

img {
	max-width: 100%;
}
</style>
<!-- Boostrap 導入程式 -->
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
<body style="background-color: rgb(216, 208, 208)" onload="connect();">

	<div id="xxx"></div>

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
				<div>
					<a class="edit_item" type="button"
						href="/TGA103G1/back-end/store/storeChat.html">店家私信</a>
				</div>
			</div>
		</aside>
		<main class="main">
			<form action="<%=request.getContextPath()%>/ProductServlet"
				method="post" enctype="multipart/form-data">
				<div class="all_product">
					<div class="title">
						<h1>修改商品</h1>
					</div>

					<h1>
						商品編號 :<%=productVO.getProduct_id()%></h1>

					<div class="comm col-6">
						<label for="p_type" style="width: 200px"> 商品類別: </label> <select name="p_type"
							class="p_type form-select">
							<option value="1" ${productVO.type_id == 1 ? "selected" : ""}>水果類</option>
							<option value="2" ${productVO.type_id == 2 ? "selected" : ""}>茶類</option>
							<option value="3" ${productVO.type_id == 3 ? "selected" : ""}>氣泡類</option>
							<option value="4" ${productVO.type_id == 4 ? "selected" : ""}>草本類</option>
						</select>
					</div>

					<div class="comm col-6">
						<label for="p_name" style="width: 200px">商品名稱 : </label> <input type="text" id="p_name"  class="form-control"
							name="p_name" value="<%=productVO.getName()%>">
						<p class="error">${errorMsgs.p_name}</p>
					</div>

					<div class="comm" id="p_pre">
						<label>商品圖片：</label>
						<div class="div_file">
							<input type="file" class="p_file" id="p_file1" name="p_file1"
								accept="image/gif, image/jpeg, image/png">

						</div>
						<div class="all_preview">
							<div class="preview" id="preview1">
								<img
									src="<%=request.getContextPath()%>/ProductServlet?action=getImg&product_id=${productVO.product_id}">
								<span class="text" id="text1">預覽圖</span>
							</div>

						</div>

					</div>

					<div class="comm col-6">
						<label for="p_price" style="width: 200px;">商品售價 :</label> 
						<input type="text"  class="form-control"
							id="p_price" name="p_price" value="<%=productVO.getPrice()%>">
						<p class="error">${errorMsgs.p_price}</p>
					</div>

					<div class="comm col-6">
						<label for="p_stock" style="width: 150px;">庫存 : </label> <input type="text" id="p_stock" class="form-control"
							name="p_stock" value="<%=productVO.getStock()%>">
						<p class="error">${errorMsgs.p_stock}</p>
					</div>

					<div class="comm col-6">
						<label for="p_type"  style="width: 220px;"> 狀態 : </label> <select name="p_status" class="form-select" 
							id="status" class="p_type">
							<option value="0" ${productVO.status == 0 ? "selected" : ""}>下架</option>
							<option value="1" ${productVO.status == 1 ? "selected" : ""}>上架</option>
						</select>
					</div>

					<div class="comm col-12">
						<label for="p_produce" style="width: 150px;">商品介紹 :</label>
						<textarea class="form-control" name="p_produce" id="p_produce" cols="30" rows="10"><%=productVO.getDescription()%></textarea>
						<p>${errorMsgs.p_produce}</p>
						<div id="button">
							<div id="button_new">
								<input type="hidden" name="action" value="update"> <input
									type="hidden" name="product_id"
									value="<%=productVO.getProduct_id()%>"> <input
									type="submit" value="送出修改" onclick="sendMessage()" id="update" class="btn btn-secondary">

							</div>

						</div>
					</div>


				</div>
			</form>
		</main>
	</div>




	<script src="https://code.jquery.com/jquery-3.6.1.js"
		integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI="
		crossorigin="anonymous"></script>
	<script src="/TGA103G1/back-end/js/header.js"></script>
	<script>
 
     //file_1
     let filereader;
     document.querySelector("#p_file1").addEventListener("change", function(e){
        if(this.files.length > 0){
          document.querySelector("#preview1").innerHTML="";
           filereader = new FileReader();
           filereader.readAsDataURL(this.files[0]);
           filereader.addEventListener("load", function(){
          
          let str = `
            <img src="\${filereader.result}" class="preview_img" >
          `;
          document.querySelector("#preview1").innerHTML = str;
         
        });
      
        }else{
          document.querySelector("#text1").innerText="預覽圖";
        };
      });
     
      

		
		
		   //WS
	    var MyPoint = "/TogetherWS/james";
		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0, path.indexOf('/', 1));
		var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

		var webSocket;
		let img = "/TGA103G1/ProductServlet?action=getImg&product_id=${productVO.product_id}"
		let p_name = document.querySelector("#p_name")
		let toastheader = document.querySelector("#toastheader")
		let toastbody = document.querySelector("#toastbody")
		let liveToastBtn = document.querySelector("#liveToastBtn")
		let status = document.querySelector("#status")
		function connect() {
			// create a websocket
			webSocket = new WebSocket(endPointURL);

			webSocket.onopen = function(event) {
			};

			webSocket.onmessage = function(event) {
				var jsonObj = JSON.parse(event.data);
				console.log(event.data)
				toastbody.innerText = jsonObj.message
				liveToastBtn.click();
			};

		}



		function sendMessage() {
			
			if (p_name.value != "" &&  status.value == 1) {
		
				var jsonObj = {
				 	 /* "img":filereader.result,  */
					"message" : p_name.value+"上架囉",
					"img" : img
				};
				webSocket.send(JSON.stringify(jsonObj)); //送資料出去
			}
		}

		function disconnect() {
			webSocket.close();
		}

    </script>
</body>
</html>
