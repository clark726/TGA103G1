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
<body style="background-color: rgb(216, 208, 208)" >
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
						<h2>新增商品</h2>
					</div>
					<div class="comm col-6">
						<label for="p_type" style="width: 200px"> 商品類別: </label> <select name="p_type"
							class="p_type form-select">
							<option value="1">水果類</option>
							<option value="2">茶類</option>
							<option value="3">氣泡類</option>
							<option value="4">草本類</option>
						</select>
					</div>

					<div class="comm col-6">
						<label for="p_name" style="width: 200px">商品名稱 : </label> <input type="text" id="p_name"  class="form-control"
							name="p_name"
							value="<%=(productVO == null) ? "" : productVO.getName()%>">
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
								<span class="text" id="text1">預覽圖</span>
							</div>
						</div>

					</div>

					<div class="comm col-6">
						<label for="p_price" style="width: 200px">商品售價 :</label> <input type="text" class="form-control"
							id="p_price" name="p_price"
							value="<%=(productVO == null) ? "" : productVO.getPrice()%>">
						<p class="error">${errorMsgs.p_price}</p>
					</div>

					<div class="comm col-6">
						<label for="p_stock" style="width: 150px">庫存 : </label> <input type="text" id="p_stock" class="form-control"
							name="p_stock"
							value="<%=(productVO == null) ? "" : productVO.getStock()%>">
						<p class="error">${errorMsgs.p_stock}</p>
					</div>

					<div class="comm col-12">
						<label for="p_produce" style="width: 150px">商品介紹 :</label>
						<textarea name="p_produce" id="p_produce" cols="30" rows="10" class="form-control"><%=(productVO == null) ? "" : productVO.getDescription()%></textarea>
						<p class="error">${errorMsgs.p_produce}</p>

						<div id="button">
							<div id="button_new">
								<input id="insert" type="submit" class="btn btn-secondary" value="新增" ></input> <input type="hidden"
									name="action" value="insert">
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
	<script src="../js/header.js"></script>
	<script>
 
		let filereader;
     //file_1
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

	var statusOutput = document.getElementById("insert");
	var webSocket;
	
	let p_name = document.querySelector("#p_name")
	let toastheader = document.querySelector("#toastheader")
	let toastbody = document.querySelector("#toastbody")
	let liveToastBtn = document.querySelector("#liveToastBtn")
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
		
		if (p_name.value === "") {
			alert("請輸入名稱");
		} else {
			var jsonObj = {
			 	 /* "img":filereader.result,  */
				"message" : p_name.value+"上架囉"
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
