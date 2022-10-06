<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.member.vo.MemberVO"%>
<!DOCTYPE html>
<html lang="en">

<%session.getAttribute("userid");

%>


<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>會員中心</title>
 	<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/member/css/menber_center.css">
</head>
<body>
    <div w3-include-html="/TGA103G1/com/header.html"></div>
    <div class="div_menCenter">
        <div class="menCenter_contain">
            <aside class="menCenter_aside">
                <h2>會員中心</h2>
                <ul class="menCenter_ul">
                    <li>
                        <button><a href="http://localhost:8080/TGA103G1/front-end/member/jsp/member_center.jsp">修改基本資料</a></button>
                    </li>
                    <li>
                        <button><a href="http://localhost:8080/TGA103G1/front-end/member/jsp/member_centerChangePsw.jsp">修改密碼</a></button>
                    </li>
                    <li>
                        <button><a href="http://localhost:8080/TGA103G1/front-end/member/jsp/menberCenter%EF%BC%ADanegerOrder.html">訂單管理</a></button>
                    </li>
                    <li>
                        <button><a href="http://localhost:8080/TGA103G1/front-end/member/jsp/member_centerMyLove.jsp">我的最愛</a></button>
                    </li>
                    <li>
                        <button><a href="http://localhost:8080/TGA103G1/front-end/member/jsp/member_centerForum.jsp">討論區</a></button>
                    </li>
                    <li>
                        <button><a href="">聊天室</a></button>
                    </li>
                    <li>
                        <button><a href="http://localhost:8080/TGA103G1/memberLogout" id="logout">登出</a></button>
                    </li>
                </ul>
            </aside>

            <main class="menCenter_main">
                <div class="menCenter_div">
                    <div class="menCenter_title">修改基本資料</div>
                    <br>
                    <div class="menCenter_innerDiv">
                        <form action="<%=request.getContextPath()%>/member/MemberUpdate" class="menCenter_form" method="post">
<%--                             <div class="form_div">
                                <label for="">帳號<font color=red><b>*</b></font>${memberVO.account}</label>
                            </div> --%>
                            <div class="form_div">
                                <label for="">名稱</label>
                                <input type="text" placeholder="名稱" id="nickname" name="nickname" value="${userid.nickname}">
                            </div>
                                <font size="1" class="nickname" color="red"></font>
                            <div class="form_div">
                                <label for="">信箱</label>
                                <input type="text" placeholder="信箱" id="email" name="email"  value="${userid.email}">
                            </div>
                                <font size="1" class="email" color="red"></font>
                            <div class="form_div">
                                <label for="">地址</label>
                                <input type="text" placeholder="地址" id="address" name="address"  value="${userid.address}">
                            </div>
                                <font size="1" class="address" color="red"></font>
                            <div class="form_div">
                                <label for="">電話</label>
                                <input type="text" placeholder="手機" id="phone" name="phone"  value="${userid.phone}">
                            </div>
                                <font size="1" class="phone" color="red"></font>
                            <div class="div_gender">
                                <label for="">性別</label>
                                <input type="radio" id="male" name="gender"  value="0" class="gender" ${userid.gender == 0 ? "checked" : ""}><label for="male">男</label>
                                <input type="radio" id="famale" name="gender" value="1" class="gender" ${userid.gender  == 0 ? "" : "checked"}><label for="famale">女</label>
                            </div>
						
                            <button type="submit" id="submit">確定修改</button>
                        </form>
                    </div>
                </div>
                <div class="menCenter_div">
              		<!-- 在這邊做值的判斷 -->
                    <img src="<%=request.getContextPath()%>/front-end/member/images/man.png" alt="" class="gender_img">
                    
                </div>
            </main>
        </div>
    </div>
     <div w3-include-html="<%=request.getContextPath()%>/front-end/member/footer.jsp"></div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script>
    //登出
    document.querySelector("#logout").addEventListener("click", function(){
        sessionStorage.removeItem("account")
      })
    
    let nickname = document.querySelector("#nickname");
    let email = document.querySelector("#email");
    let address = document.querySelector("#address");
    let phone = document.querySelector("#phone");
    let emailRex = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
    let phoneRex = "^\\d{10}$";
    $("#submit").on("click",function(e){
            $("font.nickname").empty();
            $("font.email").empty();
            $("font.address").empty();
            $("font.phone").empty();
            
            if(email.value == ""){
                $("font.email").append(`信箱請勿空白`);
                e.preventDefault();
            }else if(!email.value.match(emailRex)){
                $("font.email").append(`信箱格式不正確`);
                e.preventDefault();
            }
            if(address.value == ""){
                $("font.address").append(`地址請勿空白`);
                e.preventDefault();
                
            }

            if(phone.value == ""){
                $("font.phone").append(`電話請勿空白`);                 
                e.preventDefault();                       
            }else if(!phone.value.match(phoneRex)){    
                $("font.phone").append(`電話格式不正確`);                 
                e.preventDefault();             
            }
        })
    
    window.onload = function(){
        var arr = new Array();
        document.querySelectorAll(".gender").forEach(function(e){
            arr.push(e);
        })
        if(arr[0].checked){
        	$(".gender_img").attr("src","<%=request.getContextPath()%>/front-end/member/images/man.png")
        }else{
        	 $(".gender_img").attr("src","<%=request.getContextPath()%>/front-end/member/images/woman.png")
        }
    }
        $(function(){
            $("input.gender").on("click",function(){
                var gender = $(this).val();
                if(gender == 0){
                    $(".gender_img").attr("src","<%=request.getContextPath()%>/front-end/member/images/man.png")
                }else if(gender == 1){
                    $(".gender_img").attr("src","<%=request.getContextPath()%>/front-end/member/images/woman.png")
                }
            })
        })
        function includeHTML() {
          var z, i, elmnt, file, xhttp;
          /* Loop through a collection of all HTML elements: */
          z = document.getElementsByTagName("*");
          for (i = 0; i < z.length; i++) {
            elmnt = z[i];
            /*search for elements with a certain atrribute:*/
            file = elmnt.getAttribute("w3-include-html");
            if (file) {
              /* Make an HTTP request using the attribute value as the file name: */
              xhttp = new XMLHttpRequest();
              xhttp.onreadystatechange = function() {
                if (this.readyState == 4) {
                  if (this.status == 200) {elmnt.innerHTML = this.responseText;}
                  if (this.status == 404) {elmnt.innerHTML = "Page not found.";}
                  /* Remove the attribute, and call this function once more: */
                  elmnt.removeAttribute("w3-include-html");
                  includeHTML();
                }
              }
              xhttp.open("GET", file, true);
              xhttp.send();
              /* Exit the function: */
              return;
            }
          }
        }
        includeHTML();
        </script>
</body>
</html>