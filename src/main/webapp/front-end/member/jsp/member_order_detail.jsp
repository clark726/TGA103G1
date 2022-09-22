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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/member/css/member_order_detail.css">
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
            <div class="maneger_title">訂單明細</div>
            <table class="maneger_orderDetail">
            <tbody>
                <tr>
                    <td>購買人姓名:林ＸＸ</td>
                    <td>訂單編號:Ａ12345</td>
                    <td>訂單日期:2022/08/03</td>
                </tr>
                <tr>
                    <td>出貨方式:宅配</td>
                    <td>付款金額:3000</td>
                    <td>交易狀態:已付款</td>
                </tr>
            </tbody>
        </table>
        <div>
            <p>聯絡電話:0963125826</p>
            <p>收貨地址:新北市汐止去大同路二段200號</p>
        </div>

            <div class="table_orderDetail">
                <table>
                    <thead>
                        <tr>
                            <th>商品內容</th>
                            <th>金額</th>
                            <th>數量</th>
                            <th>小計</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>綠茶調酒</td>
                            <td>450</td>
                            <td>2</td>
                            <td>900</td>
                        </tr>
                        <tr>
                            <td>萬壽菊風味</td>
                            <td>450</td>
                            <td>1</td>
                            <td>450</td>
                        </tr>
                    </tbody>

                    
                </table>
                <p>
                    運費：120元<br>
                    總計：1470元
                </p>
                
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
