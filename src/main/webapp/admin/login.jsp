<%@page import="java.util.ArrayList"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>BarJarJo</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="<%=request.getContextPath()%>/admin/css/admin.css" rel="stylesheet" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
      crossorigin="anonymous"
    />
</head>
<body>
<div class="d-flex" id="wrapper">
      <div class="border-end bg-white" id="sidebar-wrapper">
        <div class="sidebar-heading border-bottom bg-light">BarJarJo</div>
        <div class="list-group list-group-flush">
          <a
            class="list-group-item list-group-item-action list-group-item-light p-3"
            href="<%=request.getContextPath()%>/admin/console/members.jsp"
            >修改會員資料</a
          >
          <a
            class="list-group-item list-group-item-action list-group-item-light p-3"
            href="<%=request.getContextPath() %>/admin/console/administrators.jsp"
            >修改管理員</a
          >
          <a
            class="list-group-item list-group-item-action list-group-item-light p-3"
            href="<%=request.getContextPath()%>/admin/console/messageReport.jsp"
            >留言檢舉</a
          >
          <a
            class="list-group-item list-group-item-action list-group-item-light p-3"
            href="/TGA103G1/admin/console/forumReport.html"
            >文章檢舉</a
          >
          <a
            class="list-group-item list-group-item-action list-group-item-light p-3"
            href="/TGA103G1/admin/console/onTheShelf.jsp"
            >審核商品上架</a
          >
          <a
            class="list-group-item list-group-item-action list-group-item-light p-3"
            href="#!"
            >修改首頁</a
          >
        </div>
      </div>
      <!-- Page content wrapper-->
      <div id="page-content-wrapper">
        <!-- Top navigation-->
        <nav
          class="navbar navbar-expand-lg navbar-light bg-light border-bottom"
        >
          <div class="container-fluid">
            <button class="btn btn-primary" id="sidebarToggle">Menu</button>
            <button
              class="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav ms-auto mt-2 mt-lg-0">
							<li class="nav-item active"><a class="nav-link" href="<%=request.getContextPath()%>/main.html" target="_blank">Home</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/fornt-end/MAP/map.html" target="_blank">地圖</a></li>
							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/fornt-end/forum/forum.jsp" target="_blank">討論區</a></li>
							<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/shop/shopProduct.html" target="_blank">購物商城</a>
							</li>
<!-- 							<li class="nav-item dropdown"><a -->
<!-- 								class="nav-link dropdown-toggle" id="navbarDropdown" href="#" -->
<!-- 								role="button" data-bs-toggle="dropdown" aria-haspopup="true" -->
<!-- 								aria-expanded="false">店家主題</a> -->
<!-- 								<div class="dropdown-menu dropdown-menu-end" -->
<!-- 									aria-labelledby="navbarDropdown"> -->
<%-- 									<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/store/storeType.html">Bistro</a> --%>
<!-- 									<div class="dropdown-divider"></div> -->
<%-- 									<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/store/storeType.html">Cocktail Bar</a> --%>
<!-- 									<div class="dropdown-divider"></div> -->
<%-- 									<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/store/storeType.html">Whicky Bar</a> --%>
<!-- 								</div></li> -->
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
								role="button" data-bs-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">帳號</a>
								<div class="dropdown-menu dropdown-menu-end"
									aria-labelledby="navbarDropdown">
									<a class="dropdown-item" href="updatePassword.jsp">換密碼</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="/TGA103G1/control">登出</a>
								</div></li>
						</ul>
            </div>
          </div>
        </nav>
<!------------------------------------------------------->
			<h3>${error}</h3>
          <form class="form-horizontal" role="form" action="/TGA103G1/control" method="post">
            <div class="form-group">
              <label for="firstname" class="col-sm-2 control-label ">帳號</label>
              <div class="col-sm-10">
                <input type="text" class="form-control login" name="user" id="firstname" placeholder="请输入帳號"/>
              </div>
            </div>
            <div class="form-group">
              <label for="lastname" class="col-sm-2 control-label">密碼</label>
              <div class="col-sm-10">
                <input
                  type="password" name="password"
                  class="form-control login"
                  id="lastname"
                  placeholder="请输入密碼"
                />
              </div>
            </div>
            <div class="form-group">
<!--               <label for="code" class="col-sm-2 control-label">驗證碼</label> -->
              <img src="/TGA103G1/admin/image.jsp">
              <div class="col-sm-10">
                <input
                type="text" name="code" maxlength="4" size="4"
                  
                  class="form-control login"
                  id="code"
                  placeholder="请输入驗證碼"
                />
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                  <label> <input type="checkbox" name="remeberMe"/>記住我 </label>
                   <a href="/TGA103G1/admin/ForgetPassword.jsp">忘記密碼</a>
                </div>
              </div>
            </div>
            <input type="hidden" name="action" value="login"/>
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary" id="login">登入</button>
              </div>
            </div>
          </form>
          <!------------------------------------------------------->
          
          <!-- ------------------------- -->
        </div>
      </div>
    </div>
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="<%=request.getContextPath()%>/admin/js/admin.js"></script>
	<script>
		document.querySelector("#login").addEventListener("click", function(el) {
			var btn = el;
			document.querySelectorAll(".login").forEach(function(ev) {
// 				console.log(ev);
				if (ev.value.trim() == "") {
					btn.preventDefault();
				}
			});
		});
	</script>
</body>
</html>