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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/menber_centerMyLove.css">
</head>
<body>
    <div w3-include-html="<%=request.getContextPath()%>/member/header.jsp"></div>
    <div class="div_menCenter">
        <div class="menCenter_contain">
            <aside class="menCenter_aside">
                <h2>會員中心</h2>
                <ul class="menCenter_ul">
                    <li>
                        <button><a href="<%=request.getContextPath()%>/member/menber_center.jsp">修改基本資料</a></button>
                    </li>
                    <li>
                        <button><a href="<%=request.getContextPath()%>/member/menber_centerChangePsw.jsp">修改密碼</a></button>
                    </li>
                    <li>
                        <button><a href="<%=request.getContextPath()%>/member/menber_centerＭanegerOrder.jsp">訂單管理</a></button>
                    </li>
                    <li>
                        <button><a href="<%=request.getContextPath()%>/member/menber_centerMyLove.jsp">我的最愛</a></button>
                    </li>
                    <li>
                        <button><a href="<%=request.getContextPath()%>/member/menber_centerForum.jsp">討論區</a></button>
                    </li>
                    <li>
                        <button><a href="">聊天室</a></button>
                    </li>
                    <li>
                        <button><a href="">登出</a></button>
                    </li>
                </ul>
            </aside>

            <main class="menCenter_main">
                <div class="menCenter_div">
                    <div class="menCenter_title">我的最愛</div>
                    <br>
                    <ul class="item_list">
                        <li>
                          <div>
                            <img src="<%=request.getContextPath()%>/images/heart.png" alt="" style="width: 30px;height:30px" id="img_heart">
                          </div>
                          <a href="#">
                             
                            <div class="img_block">
                              <img src="<%=request.getContextPath()%>/images/22704186_s.jpeg" style="width: 250px;height:150px">
                            </div>
                            <span class="item_text">店家名稱：</span>
                          </a>
                        </li>
                        
                        <li>
                          <div>
                            <img src="<%=request.getContextPath()%>/images/heart.png" alt="" style="width: 30px;height:30px" id="img_heart">
                          </div>
                            <a href="#">
                              <div class="img_block">
                                <img src="<%=request.getContextPath()%>/images/23304_s.jpeg" style="width: 250px;height:150px">
                              </div>
                              <span class="item_text">店家名稱：</span>
                            </a>
                          </li>
                          <li>
                            <div>
                              <img src="<%=request.getContextPath()%>/images/heart.png" alt="" style="width: 30px;height:30px" id="img_heart">
                            </div>
                            <a href="#">
                              <div class="img_block">
                                <img src="<%=request.getContextPath()%>/images/23529699_s.jpeg" style="width: 250px;height:150px">
                              </div>
                              <span class="item_text">店家名稱：</span>
                            </a>
                          </li>
                          <li>
                            <div>
                              <img src="<%=request.getContextPath()%>/images/heart.png" alt="" style="width: 30px;height:30px" id="img_heart">
                            </div>
                            <a href="#">
                              <div class="img_block">
                                <img src="<%=request.getContextPath()%>/images/381322_s.jpeg" style="width: 250px;height:150px">
                              </div>
                              <span class="item_text">店家名稱：</span>
                            </a>
                          </li>
                          <li>
                            <div>
                              <img src="<%=request.getContextPath()%>/images/heart.png" alt="" style="width: 30px;height:30px" id="img_heart">
                            </div>
                            <a href="#">
                              <div class="img_block">
                                <img src="<%=request.getContextPath()%>/images/03.jpg" style="width: 250px;height:150px">
                              </div>
                              <span class="item_text">店家名稱：</span>
                            </a>
                          </li>
                          <li>
                            <div>
                              <img src="<%=request.getContextPath()%>/images/heart.png" alt="" style="width: 30px;height:30px" id="img_heart">
                            </div>
                            <a href="#">
                              <div class="img_block">
                                <img src="<%=request.getContextPath()%>/images/12.jpg" style="width: 250px;height:150px">
                              </div>
                              <span class="item_text">店家名稱：</span>
                            </a>
                          </li>              
                      </ul>
                </div>

            </main>
        </div>
    </div>
     <div w3-include-html="<%=request.getContextPath()%>/member/footer.jsp"></div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script>
          $(function(){
            $("ul.item_list").on("click","img#img_heart",function(e){
              let r = confirm("確定刪除？")
              
              if(r){
                $(this).closest("li").animate({
                  "opacity":0
                },1000,"swing",function(e){
                  e.preventDefault();
                  $(this).remove();
                })
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
            </script>
            </body>
            </html>