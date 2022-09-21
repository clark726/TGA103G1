<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css" />
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


    <script src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/slider.js"></script>
  </body>
</html>
