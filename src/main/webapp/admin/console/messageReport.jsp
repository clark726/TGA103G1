<%@page import="java.util.*"%>
<%@page import="com.manager.model.ManagerVO"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
					href="<%=request.getContextPath()%>/admin/console/admin.jsp">修改會員資料</a> <a
					class="list-group-item list-group-item-action list-group-item-light p-3"
					href="<%=request.getContextPath()%>/admin/console/administrators.jsp">修改管理員</a> <a
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
        <!-- Page content-->
        <h2>Hi <%ManagerVO vo = (ManagerVO)session.getAttribute("admin");
					String account = vo.getAccount();
					out.print(account.substring(0,account.indexOf("@")));%></h2>
        <!--------------->
        <div class="container-fluid">
          <table class="table table-hover">
            <caption>
<!--               悬停表格布局 -->
            </caption>
            <thead>
              <tr>
                <td>id</td>
                <td>會員編號</td>
                <td>留言編號</td>
                <td>原因</td>
                <td>超連結</td>
                <td>時間</td>
                <td>更新</td>
                <td>查看留言</td>
                <td>刪除</td>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="report" items="${messageReportList}" varStatus="s">
                    <tr >
                        <form action="<%=request.getContextPath()%>/control" method="post">
                            <td>${report.message_report_id}</td>
                            <td>${report.member_id}</td>
                            <td>${report.message_id }</td>
                            <td>${report.reason }</td>
                            <td><a target="_blank" href="<%=request.getContextPath() %>/ForumServlet?action=watchOneForum&page=${report.forum_id}">文章連結</a></td>
                            <td>${report.date }</td> <input type="hidden" name="action"
                                value="updateMessageReport">
                            <td><button type="button" class="seen">已讀</button></td>
                            <td><button type="button" value="${report.message_id}"
                                    class="check">查看</button></td>
                            <td><button type=button class="deleteForumMessage">刪除留言</button></td>
                        </form>
                    </tr>
            </c:forEach>
            </tbody>
          </table>
<!------------------------------------------------------->
          
        </div>
      </div>
    </div>
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="<%=request.getContextPath()%>/admin/js/admin.js"></script>
    <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript">
	 document.querySelectorAll(".check").forEach(function(el){
		 el.addEventListener("click",function(e){
		 	btnVal = e.target.value;
		 	$.ajax({
		         url: "/TGA103G1/control",           
		         type: "post",                  
		         data: {"action":"getFroumMessage",
		 		        		"btnVal":btnVal},              
		         dataType: "json",            
		         success: function(xhr){
		 			alert(xhr.context);
		         }
		       });
		 })
		 })


document.querySelectorAll(".deleteForumMessage").forEach(function(el){
	el.addEventListener("click",function(even){
		if(confirm("確定要刪除嗎?")){
			var reason = window.prompt("刪除原因");
			 var trEl = el.closest("tr");
			$.ajax({
        url: "/TGA103G1/control",           
        type: "post",
        dataType:"text",
        data: {"action":"deleteForumMessage",
		        "deleteVal":trEl.children[3].innerHTML,
				"btnVal":trEl.children[1].innerHTML,
				"reason":reason},                        
        success: function(xhr){
			trEl.remove();
			location.reload();
        },error:function(xhr){
//         	console.log("error");
//         	console.log(xhr);
        }
      });
		}
	})
})

document.querySelectorAll(".seen").forEach(function(el){
	el.addEventListener("click",function(ev){
		var trEl = el.closest("tr");
		$.ajax({
        url: "/TGA103G1/control",           
        type: "post",                  
        data: {"action":"updateforumReport",
		        "btnVal":trEl.children[1].innerHTML},                        
        success: function(xhr){
			trEl.remove();
        },error:function(xhr){
//             console.log("error");
//             console.log(xhr);
          }
      });
	})
})

//         window.onload - function(){
//             $.ajax({
//         url: "/TGA103G1/control",           
//         type: "post",                  
//         data: {"action":"getFroumId"},              
//         dataType: "json",            
//         success: function(xhr){
//             console.log("success")
// 			console.log(xhr);
//         },
//         error: function(xhr){
// 			console.log("error");         
//             console.log(xhr);
//         }});
//         }
</script>
</body>
</html>