<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>會員中心</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/member/css/menber_centerManegerOrder.css">
  </head>
  <body>
    <div w3-include-html="<%=request.getContextPath()%>/front-end/member/jsp/header.jsp"></div>
    <div class="div_menCenter">
      <div class="menCenter_contain">
        <aside class="menCenter_aside">
          <p>會員中心</p>
          <ul class="menCenter_ul">
            <li>
              <button><a href="<%=request.getContextPath()%>/front-end/member/jsp/menber_center.jsp">修改基本資料</a></button>
            </li>
            <li>
              <button>
                <a href="<%=request.getContextPath()%>/front-end/member/jsp/menber_centerChangePsw.jsp">修改密碼</a>
              </button>
            </li>
            <li>
              <button>
                <a href="<%=request.getContextPath()%>/front-end/member/jsp/menber_centerＭanegerOrder.jsp">訂單管理</a>
              </button>
            </li>
            <li>
              <button><a href="<%=request.getContextPath()%>/front-end/member/jsp/menber_centerMyLove.jsp">我的最愛</a></button>
            </li>
            <li>
              <button><a href="<%=request.getContextPath()%>/front-end/member/jsp/menber_centerForum.jsp">討論區</a></button>
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
            <div class="maneger_title">訂單管理</div>
            <br />
            <div class="div_search">
              <label for="">搜尋訂單編號</label>
              <input type="text" />
              <img
                src="<%=request.getContextPath()%>/front/member/images/loupe.png"
                alt=""
                style="height: 20px; width: 20px"
              />
            </div>
            <div class="maneger_div">
              <table class="maneger_table">
                <thead class="thead">
                  <tr >
                    <th >訂單編號</th>
                    <th>出貨狀態</th>
                    <th >總金額</th>
                    <th >出貨店家</th>
                    <th >交易日期</th>
                    <th >訂單明細</th>
                  </tr>
                </thead>
                <tbody class="tbody">
                  <tr>
                    <td >A1234567</td>
                    <td >待出貨</td>
                    <td >1220</td>
                    <td >Gym Bar</td>
                    <td >2022/08/09</td>
                    <td >
                      <a href="<%=request.getContextPath()%>/member/member_order_detail.jsp">
                      <img
                        class="page_img"
                        src="<%=request.getContextPath()%>/front-end/member/images/page.png"
                        alt=""
                        style="height: 20px; width: 20px"
                      />
                      </a>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </main>
      </div>
    </div>
   <div w3-include-html="<%=request.getContextPath()%>/front-end/member/jsp/footer.jsp"></div>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script>
                  
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
    //   const data = [
    //     {
    //       id: 1,
    //       status: "已出貨",
    //       name: "eason",
    //       price: 123,
    //       date: 2022 / 04 / 06
    //     },
    //     { id: 2 },
    //   ];

    //   const menberTemplete = (id) => {
    //     return `<tr class="col-12 row">
    //                 <td class="col-2">${id}</td>
    //                 <td class="col-2">${status}</td>
    //                 <td class="col-2">${name}</td>
    //                 <td class="col-2">${price}</td>
    //                 <td class="col-2">${date}</td>
    //                 <td class="col-2"></td>
    //               </tr>`;
    //   };

    //   document.querySelector("#menberTalbe").innerHTML = data
    //     .map((e) => menberTemplete(e.id, e.status, e.name, e.price,e.date))
    //     .toString()
    //     .replaceAll(",", "");
    </script>
  </body>
</html>
