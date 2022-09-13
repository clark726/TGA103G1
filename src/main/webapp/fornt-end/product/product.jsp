<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<% 
	ProductVO productVO = (ProductVO)request.getAttribute("productVO");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Product</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/backproduct.css">

  </head>
  <body style="background-color: rgb(129, 93, 65)">
    <header class="header">
      <div class="fl_left">
        <h1 id="logo_h1"><a href="＃" id="logo">Bar.Jar.Jo</a></h1>
      </div>

      <nav class="fl_right">
        <ul class="nav_ul">
          <li><a href="#">HOME</a></li>
          <li><a href="#">地圖</a></li>
          <li><a href="#">討論區</a></li>
          <li><a href="#">廠商專區</a></li>
          <li>
            <a href="#" class="icon">店家主題</a>
            <ul id="store">
              <li><a href="#">Bistro</a></li>
              <li><a href="#">Cocktail bar</a></li>
              <li><a href="#">Whisky bar</a></li>
            </ul>
          </li>
          <li>
            <a href="#" class="icon">會員專區</a>
            <ul id="store">
              <li><a href="#">我的最愛</a></li>
              <li><a href="#">修改會員資料</a></li>
              <li><a href="#">修改密碼</a></li>
              <li><a href="#">訂單管理</a></li>
            </ul>
          </li>

          <li><a href="#">購物商城</a></li>
          <li><a href="#">登入註冊</a></li>
        </ul>
      </nav>
    </header>


    <div class="contain">
        <aside class="aside">
          <p id="p1">廠商後台</p>
          <div class="div_func">
            <div>
              <a class="edit_store" type="button">修改店家內容</a>
            </div>
            <div>
              <a class="manager_item" type="button">上下架商品管理</a>
            </div>
            <div>
              <a class="edit_item" type="button">修改商家訂單內容</a>
            </div>
            
        </div>
        </aside>
      <main class="main">
        <form action="<%=request.getContextPath()%>/ProductServlet" method="post" enctype="multipart/form-data">
        <div class="all_product">
            <div class="title">
              <h2>新增商品</h2>
            </div>
            <div class="comm">
             <label for="p_type"> 商品類別: </label>
              <select name="p_type" class="p_type">
                <option value="1">水果類</option>
                <option value="2">茶類</option>
                <option value="3">氣泡類</option>
                <option value="4">草本類</option>
              </select>
            </div>

            <div class="comm">
              <label for="p_name">商品名稱 : </label> 
              <input type="text" id="p_name" name="p_name"  
              value="<%= (productVO==null)? "" : productVO.getName()%>">  <p>${errorMsgs.p_name}</p>
            </div>
    
            <div class="comm" id="p_pre">
              <label>商品圖片：</label>
              <div class="div_file">
               <input type="file" class="p_file" id="p_file1" >
               <input type="file" class="p_file" id="p_file2">
               <input type="file" class="p_file" id="p_file3" >
              </div>
              <div class="all_preview">
              <div class="preview" id="preview1">
                <span class="text" id="text1">預覽圖</span>
              </div>
              <div class="preview" id="preview2">
                <span class="text" id="text2">預覽圖</span>
              </div>
              <div class="preview" id="preview3">
                <span class="text" id="text3">預覽圖</span>
              </div>
            </div>
              
            </div>

            <div class="comm">
              <label for="p_price" >商品售價 :</label>
              <input type="text" id="p_price" name="p_price" 
              value="<%= (productVO==null)? "" : productVO.getPrice()%>"><p>${errorMsgs.p_price}</p>
            </div>

            <div class="comm">
              <label for="p_stock">庫存 : </label>
              <input type="text" id="p_stock" name="p_stock" 
              value="<%= (productVO==null) ? "" : productVO.getStock()%>"><p>${errorMsgs.p_stock}</p>
            </div>

            <div class="comm">
              <label for="p_produce">商品介紹 :</label>
              <textarea name="p_produce" id="p_produce" cols="30" rows="10" ><%= (productVO == null)? "" : productVO.getDescription()%></textarea>  <p>${errorMsgs.p_produce}</p> 
             
              <div id="button">
                <div id="button_new">
                <input type="submit" value="新增" ></input>
               <input type="hidden" name="action" value="insert">
              </div>
            
              </div>
            </div>

         
          </div>
        </form>
        </main>
        </div>
        
    </div>
    

    <script src="../js/jquery-3.6.0.min.js"></script>
    <script src="../js/slider.js"></script>
    <script>
 
     //file_1
     document.querySelector("#p_file1").addEventListener("change", function(e){
        if(this.files.length > 0){
          document.querySelector("#preview1").innerHTML="";
          let reader = new FileReader();
        reader.readAsDataURL(this.files[0]);
        reader.addEventListener("load", function(){
          
          let str = `
            <img src="${reader.result}" class="preview_img" >
          `;
          document.querySelector("#preview1").innerHTML = str;
         
        });
      
        }else{
          document.querySelector("#text1").innerText="預覽圖";
        };
      });
      //file_2
      document.querySelector("#p_file2").addEventListener("change", function(e){
        if(this.files.length > 0){
          document.querySelector("#preview2").innerHTML="";
          let reader = new FileReader();
        reader.readAsDataURL(this.files[0]);
        reader.addEventListener("load", function(){
          
          let str = `
            <img src="${reader.result}" class="preview_img" >
          `;
          document.querySelector("#preview2").innerHTML = str;
         
        });
      
        }else{
          document.querySelector("#text2").innerText="預覽圖";
        };
      });
      //file_3
      document.querySelector("#p_file3").addEventListener("change", function(e){
        if(this.files.length > 0){
          document.querySelector("#preview3").innerHTML="";
          let reader = new FileReader();
        reader.readAsDataURL(this.files[0]);
        reader.addEventListener("load", function(){
          
          let str = `
            <img src="${reader.result}" class="preview_img" >
          `;
          document.querySelector("#preview3").innerHTML = str;
         
        });
      
        }else{
          document.querySelector("#text3").innerText="預覽圖";
        };
      });
    </script>
  </body>
</html>
