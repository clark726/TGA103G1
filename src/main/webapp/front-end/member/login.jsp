<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>會員登入</title>
     <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/member/css/login.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/footer1.css">
</head>
<body>
    <div w3-include-html="/TGA103G1/com/header.html"></div>
    <div class="div_login">
        <main class="login_main">
            <form method="post" action="<%=request.getContextPath()%>/member/login" class="login_form">
                <h2>會員登入</h2>
                <div>
                    <label for="login_account">帳號:</label>
                    <input type="text" id="login_account" name="account"> 
                </div>
				 <font size="1" class="account" color="red"></font>
                <div>
                    <label for="login_password">密碼:</label>
                    <input type="password" id="login_password" name="password"> 
                </div>
                 <font size="1" class="password" color="red"></font>
                <div>
                    <label for="login_verify">驗證碼:</label>
                    <input type="text" id="login_verify">
                    <button type="button" id="login_check">
                </div>
                 <font size="1" class="verify" color="red"></font>
                <font color="red" size="1"style="text-align:center">${result}</font>
                <div class="two_but">
                <button type="submit" id="login">登入</button>
                <button type="button" id="cancel" ><a href="">離開</a> </button>
            </div>
            </form>

            <div class="div_a">
                <a href="/TGA103G1/front-end/member/register.jsp">會員註冊</a>
                <a href="/TGA103G1/front-end/member/checkAcco.html">忘記密碼</a>
            </div>
        </main>
    </div>
    <div w3-include-html="/TGA103G1/com/footer.html"></div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
  
            var arr = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"];
            // console.log(arr);
            //页面刚加载时候调用它
            sjs(arr);
            
            login_check.onclick = function() {
            sjs(arr);
            }
            
            function sjs(arr) {
            var code = Math.floor(Math.random() * arr.length);
            var code1 = Math.floor(Math.random() * arr.length);
            var code2 = Math.floor(Math.random() * arr.length);
            var code3 = Math.floor(Math.random() * arr.length);
            var n = arr[code] + arr[code1] + arr[code2] + arr[code3];
            login_check.textContent = n
            }


            var equal_code = document.querySelector("#login_verify");
            var login_account_el = document.querySelector("#login_account");
            var password_el = document.querySelector("#login_password");
            
            $("#login").on("click",function(e){
                $("font.account").empty();
                $("font.password").empty();
                $("font.verify").empty();
                //這裡缺少帳號與密碼傳給資料庫必須吻合才能送出
                if(login_account_el.value == ""){
                    $("font.account").append(`帳號請勿空白`);
                    e.preventDefault();
                }else{
                }
                if(password_el.value == ""){
                    $("font.password").append(`密碼請勿空白`);
                    e.preventDefault(); 
                }else{
                	
                }
                if(equal_code.value === login_check.textContent ){
                }else{
                    $("font.verify").append(`驗證碼空白或不正確`);                 
                    e.preventDefault();
                }
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
        })
        $("#login").on("click" , function(){
            var account = document.querySelector("#login_account").value
            sessionStorage.setItem("memberAccount", account)
        });
    </script>
</body>
</html>