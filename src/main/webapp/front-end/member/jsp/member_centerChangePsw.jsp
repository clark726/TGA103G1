<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>會員中心</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/member/css/member_centerChangePsw.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/footer1.css">
</head>
<body>
   <div w3-include-html="/TGA103G1/com/header.html"></div>
    <div class="div_menCenter">
        <div class="menCenter_contain">
            <aside class="menCenter_aside">
                <h2>會員中心</h2>
                <ul class="menCenter_ul">
                    <li>
                        <button><a href="/TGA103G1/front-end/member/jsp/member_center.jsp">修改基本資料</a></button>
                    </li>
                    <li>
                        <button><a href="/TGA103G1/front-end/member/jsp/member_centerChangePsw.jsp">修改密碼</a></button>
                    </li>
                    <li>
                        <button><a href="/TGA103G1/front-end/member/jsp/menberCenter%EF%BC%ADanegerOrder.html">訂單管理</a></button>
                    </li>
                    <li>
                        <button><a href="/TGA103G1/front-end/member/jsp/member_centerMyLove.jsp">我的最愛</a></button>
                    </li>
                    <li>
                        <button><a href="/TGA103G1/front-end/member/jsp/member_centerForum.jsp">討論區</a></button>
                    </li>
                    <li>
                        <button><a href="/TGA103G1/memberLogout" id="innerLogout">登出</a></button>
                    </li>
                </ul>
            </aside>

            <main class="menCenter_main">
                <div class="menCenter_div">
                    <div class="menCenter_title">修改密碼</div>
                   <br>
                    <div class="change_psw">
                        <form action="<%=request.getContextPath()%>/member/MemberUpdatePassword" class="change_form" method="post">
                            <div class="change_div">
                                <label for="">請輸入舊密碼</label>
                                <input type="password" name="oldpassword" id="oldpassword">
                            </div>
                                <font size="1" color="red" class="oldpassword"></font>
                            <div class="change_div">
                                <label for="">請輸入新密碼</label>
                                <input type="password" name="newpassword" id="newpassword">
                            </div>
                                <font size="1" color="red" class="newpassword"></font>
                            <div class="change_div">
                                <label for="">請再輸入新密碼</label>
                                <input type="password" name="dbpassword" id="dbpassword">
                            </div>
                                <font size="1" color="red" class="dbpassword"></font>
                                <br>
								<font size="1" color="red" class="font">${error}</font>
                                <br>
                            <button type="submit" id="submit">確定修改</button>
                        </form>
                    </div>
                </div>

            </main>
        </div>
    </div>
    <div w3-include-html="/TGA103G1/com/footer.html"></div>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script>

    function changelog(){
        if(sessionStorage.getItem("memberAccount")){
        $("#login").css("display", "none")
         $("#memberlogout").css("display", "block")
       }
     }
    function logouut(){
   //登出
       document.querySelector("#innerLogout").addEventListener("click", function(){
       sessionStorage.removeItem("memberAccount")
     })
   	document.querySelector("#memberlogout").addEventListener("click", function(){
   	sessionStorage.removeItem("memberAccount")
     })

    }
      
    let userid = ${userid.password};
    let oldPas = document.querySelector("#oldpassword");
    let newPas = document.querySelector("#newpassword");
    let dbPas = document.querySelector("#dbpassword");
    let psRex = "^[a-zA-z0-9]{4,20}$";
    $(function(){
        changelog();
        $("#submit").on("click",function(e){
            $("font.oldpassword").empty();
            $("font.newpassword").empty();
            $("font.dbpassword").empty();
            
        if(oldPas.value != userid){
        	$("font.oldpassword").append("密碼錯誤");
            e.preventDefault();
        }else if(oldPas.value == ""){  
            $("font.oldpassword").append("舊密碼不能為空");
            e.preventDefault();
        }else{
        }
        if(!newPas.value.match(psRex)){
            $("font.newpassword").append("請輸入4-20個英文或數字");
            e.preventDefault();
        }else{
        }
        if(dbPas.value != newPas.value){
            $("font.dbpassword").append("兩次密碼不一致");
            e.preventDefault();
        }else{
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
                  changelog();
                  logouut();
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