<%@page import="java.util.List"%>
<%@page import="com.favorite.service.impl.FavoritServiceIpml"%>
<%@page import="com.favorite.model.FavoriteVO"%>
<%@page import="com.member.vo.MemberVO"%>
<%@page import="com.favorite.service.FavoriteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
  
  
<%		
	MemberVO member = (MemberVO)request.getSession().getAttribute("userid");
	Integer id = member.getMember_id();
    FavoriteService dao = new FavoritServiceIpml();
    List<FavoriteVO> list = dao.getAll(id);
    request.setAttribute("list", list);
    
%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>會員中心</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/member/css/menber_centerMyLove.css">
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
                    <div class="menCenter_title">我的最愛</div>
                    <br>
                    <div></div>
                    <ul class="item_list">
                   
                    <c:forEach var="list" items="${list}">
                        <li>
                          <div>
                            <img src="<%=request.getContextPath()%>/front-end/member/images/heart.png" data-favorite_id= "${list.favorite_id}" alt="" style="width: 30px;height:30px" id="img_heart">
                          </div>
                          <a href="#">
                            <div class="img_block">
                              <img src="<%=request.getContextPath()%>/favorite?action=getImg&member_id=${list.member_id}&store_id=${list.store_id}"  style="width: 250px;height:150px">
                            </div>
                            <span class="item_text">店家名稱：${list.name}</span>
                          </a>
                        </li>
                      </c:forEach>
                      </ul>
                </div>

            </main>
        </div>
    </div>
     <div w3-include-html="/TGA103G1/com/footer.html"></div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script>
    //登出
    document.querySelector("#logout").addEventListener("click", function(){
        sessionStorage.removeItem("account")
      })
      
      $(function(){
            $("ul.item_list").on("click","img#img_heart",function(e){
             let favorite_id =  e.target.getAttribute("data-favorite_id")
              console.log(favorite_id)
              let r = confirm("確定刪除？")
              
              if(r){
                $(this).closest("li").animate({
                  "opacity":0
                },1000,"swing",function(e){
                  // e.preventDefault();
                  $(this).remove();
                })
              }
     const msg = document.querySelector("#msg");          
		
		fetch('/TGA103G1/favoriteDelete', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify({
        favorite_id:favorite_id
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				const { successful } = body;
				
			});
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
            </script>
            </body>
            </html>