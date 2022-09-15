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

body {
	display: block;
	margin: 0px;
}
</style>
</head>
<%
Integer a = Integer.parseInt(request.getParameter("page"));
Integer b = (Integer) request.getSession().getAttribute("pages");
if (b != null) {
	if (a > b - 1 || a < 0) {
		response.sendRedirect(request.getContextPath() + "/admin/console/admin.jsp?page=0");
		return;
	}
}
%>
<form action="<%=request.getContextPath()%>/control" method="post"
	style="float: right;">
	<input type="hidden" name="action" value="logout">
	<button type="submit">logout</button>
</form>
<h2>Hi ${admin} ${error}</h2>

<form action="<%=request.getContextPath()%>/control" method="post">
	<input type="hidden" name="action" value="search"> <input
		type="number" name="member_id" placeholder="member_id">
	<button type="submit">search</button>
</form>

<table>
	<tr>
		<td>member_id</td>
		<td>account</td>
		<td>address</td>
		<td>nickname</td>
		<td>phone</td>
		<td>register</td>
		<td>permission</td>
		<td>update</td>
		<td>reset</td>
	</tr>
	<c:forEach var="member" items="${members}" begin="${param.page*5}"
		end="${param.page*5+4}" varStatus="s">
		<tr>
			<form action="<%=request.getContextPath()%>/control" method="post">
				<td><input type="text" name="member_id" class="member_id"
					value="${member.member_id}" readonly></td>
				<td><input readonly type="text" name="account" class="account"
					value="${member.account}"></td> <input type="hidden"
					name="password" value="${member.password}"> <input
					type="hidden" name="birthday" value="${member.birthday}">
				<td><input readonly type="text" name="address"
					value="${member.address}"></td>
				<td><input type="text" name="nickname"
					value="${member.nickname}" class="nickname"></td>
				<td><input type="text" name="phone" value="${member.phone}"
					class="phone"></td>
				<td><input type="datetime-local" name="register"
					value="${member.register}"></td>
				<td><select name="permission">
						<option value="0" ${member.permission == 0 ? "selected":""}>已封鎖</option>
						<option value="1" ${member.permission == 1 ? "selected":""}>已註冊</option>
						<option value="2" ${member.permission == 2 ? "selected":""}>已驗證</option>
				</select></td>
				<td><button type="submit" class="update">update</button></td>
				<td><button type="reset">reset</button></td> <input type="hidden"
					name="action" value="update"> <input type="hidden"
					name="gender" value="${member.gender}"> <input
					type="hidden" name="email" value="${member.email}">
			</form>
		</tr>
	</c:forEach>
	<c:if test="${param.page != 0}">
		<a
			href="<%=request.getContextPath()%>/admin/console/admin.jsp?page=${param.page-1}">上一頁</a>
	</c:if>
	<c:if test="${param.page < pages-1}">
		<a
			href="<%=request.getContextPath()%>/admin/console/admin.jsp?page=${param.page+1}">下一頁</a>
	</c:if>
</table>
<script>
	document.querySelectorAll(".update").forEach(function(target) {
		target.addEventListener("click", function(e) {
			var phonelist = document.querySelectorAll(".phone");
			var nicknamelist = document.querySelectorAll(".nickname");
			for (let x = 0; x < phonelist.length; x++) {
				if (phonelist[x].value.length > 10) {
					e.preventDefault();
					alert("phone number to long");
					return;
				}
				if (!/^\d+$/.test(phonelist[x].value)) {
					e.preventDefault();
					alert("phone is not number");
					return;
				}
				if (phonelist[x].value.length < 8) {
					e.preventDefault();
					alert("phone is to short");
					return;
				}
			}

			for (var x = 0; x < nicknamelist.length; x++) {
				if (nicknamelist[x].value.lenght > 50) {
					e.preventDefault();
					alert("nickname too long");
				}
			}
			;

		})
	});
</script>
</body>
</html>