<%@page import="com.member.model.MemberJNDIDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link rel="stylesheet" href="/TGA103G1/admin/css/header.css" />
<link rel="stylesheet" href="/TGA103G1/admin/css/back.css">
<style>
	table{
	display: flex;
	justify-content: center; 
	align-items: center; 
	}
	td {
		border: 1px solid black;
	}
	
	.member_id, .account {
		max-width: 100px;
	}
	
	form {
		display: inline-block;
	}
	</style>
</head>
<body>
	<header class="header">
		<div class="fl_left">
			<h1 id="logo_h1">
				<a href="＃" id="logo">Bar.Jar.Jo</a>
			</h1>
		</div>

		<nav class="fl_right">
			<ul class="nav_ul">
				<li><a href="#">HOME</a></li>
				<li><a href="#">地圖</a></li>
				<li><a href="#">討論區</a></li>
				<li><a href="#">廠商專區</a></li>
				<li><a href="#" class="icon">店家主題</a>
					<ul id="store">
						<li><a href="#">Bistro</a></li>
						<li><a href="#">Cocktail bar</a></li>
						<li><a href="#">Whisky bar</a></li>
					</ul></li>
				<li><a href="#" class="icon">會員專區</a>
					<ul id="store">
						<li><a href="#">我的最愛</a></li>
						<li><a href="#">修改會員資料</a></li>
						<li><a href="#">修改密碼</a></li>
						<li><a href="#">訂單管理</a></li>
					</ul></li>

				<li><a href="#">購物商城</a></li>
				<li><a href="/TGA103G1/control">登出</a></li>
			</ul>
		</nav>
	</header>

	<div class="contain">
		<aside class="aside">
			<p id="p1">管理者後台</p>
			<div class="div_func">
				<div>
					<a class="edit_store" href="<%=request.getContextPath()%>/admin/console/members.jsp?">修改會員資料</a>
				</div>
				<div>
					<a class="manager_item" type="button">上下架商品管理</a>
				</div>
				<div>
					<a class="edit_item" type="button">修改商家訂單內容</a>
				</div>
				<hr>
				<div>
					<a class="front_paga" >留言檢舉</a>
				</div>
				<div>
					<a class="forum" href="/TGA103G1/admin/console/forumReport.html">文章檢舉</a>
				</div>
				<div>
					<a class="audit" type="button">審核上架</a>
				</div>
				<div>
					<a class="change_state" type="button">改變帳號狀態</a>
				</div>
				<div>
					<a class="manager" type="submit">..</a>
				</div>
			</div>
		</aside>

		<main class="main">
			<h2>Hi ${admin}</h2>
			<table>
				<tr>
					<td>report</td>
					<td>member</td>
					<td>message</td>
					<td>reason</td>
					<td>hyperlink</td>
					<td>date</td>
					<td>update</td>
					<td>check</td>
					<td>delete</td>
				</tr>
				<c:forEach var="report" items="${messageReportList}" varStatus="s">
					<c:if test="${report.status == 0}">
						<tr >
							<form action="<%=request.getContextPath()%>/control" method="post">
								<td>${report.message_report_id}</td>
								<td>${report.member_id}</td>
								<td>${report.message_id }</td>
								<td>${report.reason }</td>
								<td><a href="">文章連結</a></td>
								<td>${report.date }</td> <input type="hidden" name="action"
									value="updateMessageReport">
								<td><button type="button" class="seen">已讀</button></td>
								<td><button type="button" value="${report.message_id}"
										class="check">查看</button></td>
								<td><button type=button class="deleteForumMessage">刪帖封號</button></td>
							</form>
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</main>
	</div>
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
		         beforeSend: function(){     
		         },
		         headers: {},
		         statusCode: {                
		           200: function (res) {},
		           404: function (res) {},
		           500: function (res) {}
		         },
		         success: function(xhr){
		 			alert(xhr.context);
		         },
		         error: function(xhr){
		 			console.log("error");         
		             console.log(xhr);
		         },
		         complete: function(xhr){}
		       });
		 })
		 })


document.querySelectorAll(".deleteForumMessage").forEach(function(el){
	el.addEventListener("click",function(even){
		if(confirm("確定要刪除嗎?")){
			 var trEl = el.closest("tr");
			$.ajax({
        url: "/TGA103G1/control",           
        type: "post",                  
        data: {"action":"deleteForumMessage",
		        "deleteVal":trEl.children[3].innerHTML,
				"btnVal":trEl.children[1].innerHTML},                        
        beforeSend: function(){     
        },
        headers: {},
        statusCode: {                
          200: function (res) {},
          404: function (res) {},
          500: function (res) {}
        },
        success: function(xhr){
			console.log("success");
			trEl.remove();
        },
        error: function(xhr){
			console.log("error");         
            console.log(xhr);
        },
        complete: function(xhr){}
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
        beforeSend: function(){     
        },
        headers: {},
        statusCode: {                
          200: function (res) {},
          404: function (res) {},
          500: function (res) {}
        },
        success: function(xhr){
			console.log("success");
			trEl.remove();
        },
        error: function(xhr){
			console.log("error");         
            console.log(xhr);
        },
        complete: function(xhr){}
      });
	})
})
</script>
</body>
</html>
