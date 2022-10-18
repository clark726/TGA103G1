<%@page import="java.util.*"%>
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
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
	crossorigin="anonymous"></script>
	<style>
	form{
	display:inline-block;
	}
	</style>
</head>
<body>
<div class="d-flex" id="wrapper">
      <div class="border-end bg-white" id="sidebar-wrapper">
        <div class="sidebar-heading border-bottom bg-light">BarJarJo</div>
        <div class="list-group list-group-flush">
        
          <a
					class="list-group-item list-group-item-action list-group-item-light p-3"
					href="<%=request.getContextPath()%>/admin/console/admin.jsp">修改會員資料</a> <a
					class="list-group-item list-group-item-action list-group-item-light p-3"
					href="<%=request.getContextPath()%>/admin/console/administrators.jsp">修改管理員<span
					class="badge" style="background-color: rgb(50, 100, 82);"><%=((List) session.getAttribute("admins")).size()%>
				</span></a> <a
					class="list-group-item list-group-item-action list-group-item-light p-3"
					href="<%=request.getContextPath()%>/admin/console/messageReport.jsp">留言檢舉<span
					class="badge" style="background-color: rgb(50, 100, 82);"><%=((List) session.getAttribute("messageReportList")).size()%>
				</span></a> <a
					class="list-group-item list-group-item-action list-group-item-light p-3"
					href="/TGA103G1/admin/console/forumReport.jsp">文章檢舉<span
					class="badge" style="background-color: rgb(50, 100, 82);"><%=((List) session.getAttribute("forumReport")).size()%>
				</span></a> <a
					class="list-group-item list-group-item-action list-group-item-light p-3"
					href="/TGA103G1/admin/console/onTheShelf.jsp">審核商品上架<span
					class="badge" style="background-color: rgb(50, 100, 82);">${listing }
				</span></a> <a
					class="list-group-item list-group-item-action list-group-item-light p-3"
					href="/TGA103G1/admin/console/barjarjoFrontPage.jsp">修改首頁</a>
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
                <li class="nav-item active">
                  <a class="nav-link" href="#!">Home</a>
                </li>
                <li class="nav-item"><a class="nav-link" href="#!">地圖</a></li>
                <li class="nav-item">
                  <a class="nav-link" href="#!">討論區</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#!">購物商城</a>
                </li>
                <li class="nav-item dropdown">
                  <a
                    class="nav-link dropdown-toggle"
                    id="navbarDropdown"
                    href="#"
                    role="button"
                    data-bs-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                    >店家主題</a
                  >
                  <div
                    class="dropdown-menu dropdown-menu-end"
                    aria-labelledby="navbarDropdown"
                  >
                    <a class="dropdown-item" href="#!">Bistro</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#!">Cocktail Bar</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#!">Whicky Bar</a>
                  </div>
                </li>
                <li class="nav-item dropdown">
                  <a
                    class="nav-link dropdown-toggle"
                    id="navbarDropdown"
                    href="#"
                    role="button"
                    data-bs-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                    >會員專區</a
                  >
                  <div
                    class="dropdown-menu dropdown-menu-end"
                    aria-labelledby="navbarDropdown"
                  >
                    <a class="dropdown-item" href="#!">我的最愛</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#!">修改密碼</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#!">訂單管理</a>
                  </div>
                </li>
                <li class="nav-item dropdown">
                  <a
                    class="nav-link dropdown-toggle"
                    id="navbarDropdown"
                    href="#"
                    role="button"
                    data-bs-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                    >帳號</a
                  >
                  <div
                    class="dropdown-menu dropdown-menu-end"
                    aria-labelledby="navbarDropdown"
                  >
                    <a class="dropdown-item" href="#!">修改資料</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/TGA103G1/control">登出</a>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </nav>
        <!-- Page content-->
        <div>
            <form action="/TGA103G1/control"  method="post">
                <input type="hidden" name="action" value="searchForProduct" /> <input
                    type="number" name="productId" />
                <button>search</button>
            </form>
            <form action="/TGA103G1/control"  method="post">
                <input type="hidden" name="action" value="searchForProduct" />
                <input
                    type="hidden" name="productId" value="all"/>
                <button >cancel</button>
            </form>
        </div>
        <div>
            <select class="select">
                <option disable>查看</option>
                <option value="1">待上架</option>
                <option value="2">全部</option>
            </select>
        </div>
        <table>
            
            
        </table>
        <div class="modal fade" id="loginModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- Header -->
                    <div class="modal-header">
                        <h3>商品圖片</h3>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <!-- Body -->
                    <div class="modal-body row justify-content-center" >
                        <img src="" alt="商品沒有圖片" id="img" style="height: 400px;">
                    </div>
                    <!-- Footer -->
                    <div class="modal-footer">
                        <div class="signup"></div>
                    </div>
                </div>
            </div>
        </div>
        <!------------------------->
        <div class="container-fluid">
          <table class="table table-hover">
            <caption>
<%--               ${products} --%>
            </caption>
            <thead>
              <tr>
                <td>id</td>
                <td>類型</td>
                <td>價錢</td>
                <td>商店編號</td>
                <td>庫存</td>
                <td>上架沒</td>
                <td>時間</td>
                <td>圖片</td>
                <td>更新</td>
                <td>回復</td>
            </tr>
            </thead>
            <tbody>
              <c:forEach var="product" items="${products}" varStatus="s">
                <c:if test="${product.status <= productStatus}">
                    <tr>
                        <form action="/TGA103G1/control" method="post">
                            <input type="hidden" name="action" value="updateProdcut">
                            <input type="hidden" name="productId"
                                value="${product.product_id }">
                            <td>${product.product_id }</td>
                            <td>${product.name }</td>
                            <td>${product.price }</td>
                            <td>${product.store_id }</td>
                            <td>${product.stock }</td>
                            <td><select name="status">
                                    <option value="1" ${product.status==1?"selected":"" }>待上架</option>
                                    <option value="2" ${product.status==2?"selected":"" }>已上架</option>
                            </select></td>
                            <td>${product.date }</td>
                            <td><a type="button" data-bs-toggle="modal"
                                data-bs-target="#loginModal" class="linkimg"
                                data-product="${product.product_id }">圖片</a></td>
                            <td><button type="submit">update</button></td>
                            <td><button type="reset">reset</button></td>
                        </form>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="<%=request.getContextPath()%>/admin/js/admin.js"></script>
    <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		document.querySelectorAll(".linkimg").forEach(
				function(el) {
					el.addEventListener("click", function(ev) {
						//   console.log(this.getAttribute("data-product"));
						var productId = this.getAttribute("data-product");
						$.ajax({
							url : "/TGA103G1/control",
							type : "post",
							data : {
								action : "getImageByProductId",
								"productId" : productId
							},
							dataType : "text",
							beforeSend : function() {
							},
							success : function(xhr) {
								document.querySelector("#img").setAttribute(
										"src", xhr);
							},
							error : function(xhr) {
								console.log("error");
								console.log(xhr);
							},
							complete : function(xhr) {
							},
						});
					});
				});

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
								complete : function(xhr) {
									history.go(0);
								}
							});
						}
					}
				});
	</script>
</body>
</html>