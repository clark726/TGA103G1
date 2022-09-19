<%@page import="com.member.model.MemberJNDIDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>admin</title>
<style>
td {
	border: 1px solid black;
}

.member_id, .account {
	max-width: 100px;
}

form {
	display: inline-block;
}

body {
	display: block;
	margin: 0px;
}


</style>
</head>
<form action="<%=request.getContextPath()%>/control" method="post"
	style="float: right;">
	<input type="hidden" name="action" value="logout">
	<button type="submit">logout</button>
</form>
<h2>Hi ${admin}</h2>

<!--  
<form action="<%=request.getContextPath()%>/control" method="post">
	<input type="hidden" name="action" value="search"> <input
		type="number" name="member_id" placeholder="member_id">
	<button type="submit">search</button>
</form>

<form action="<%=request.getContextPath()%>/control" method="post">
	<input type="hidden" name="action" value="cancelSearch"> 
	<button type="submit">cancel</button>
</form>

<h2>${error}</h2>
<br>
  
<a href="<%=request.getContextPath()%>/admin/console/messageReport.jsp">stores</a>
-->
<a href="<%=request.getContextPath()%>/admin/console/members.jsp?">members</a>
<hr>
<a href="/TGA103G1/admin/console/forumReport.html">forumReport</a>
<hr>

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