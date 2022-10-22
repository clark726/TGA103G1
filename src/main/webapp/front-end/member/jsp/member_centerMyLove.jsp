<%@page import="com.favorite.service.impl.FavoritServiceIpml"%>
<%@page import="com.favorite.model.FavoriteVO"%>
<%@page import="com.member.vo.MemberVO"%>
<%@page import="com.favorite.service.FavoriteService"%>
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/member/css/menber_centerMyLove.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/footer1.css" />
</head>
<body>
	<div w3-include-html="/TGA103G1/com/header.html"></div>
	<div class="div_menCenter">
		<div class="menCenter_contain">
			<aside class="menCenter_aside">
				<h2 style="margin-top: 23px">會員中心</h2>
				<ul class="menCenter_ul">
					<li>
						<button>
							<a
								href="/TGA103G1/front-end/member/jsp/member_center.jsp">修改基本資料</a>
						</button>
					</li>
					<li>
						<button>
							<a
								href="/TGA103G1/front-end/member/jsp/member_centerChangePsw.jsp">修改密碼</a>
						</button>
					</li>
					<li>
						<button>
							<a
								href="/TGA103G1/front-end/member/jsp/menberCenter%EF%BC%ADanegerOrder.html">訂單管理</a>
						</button>
					</li>
					<li>
						<button>
							<a
								href="/TGA103G1/front-end/member/jsp/member_centerMyLove.jsp">我的最愛</a>
						</button>
					</li>
					<li>
						<button>
							<a
								href="/TGA103G1/front-end/member/jsp/member_centerForum.jsp">討論區</a>
						</button>
					</li>
					<li>
						<button>
							<a href="/TGA103G1/memberLogout"
								id="innerLogout">登出</a>
						</button>
					</li>
				</ul>
			</aside>

			<main class="menCenter_main">
				<div class="menCenter_div">
					<div class="menCenter_title">我的最愛</div>
					<br />
					<ul class="item_list"></ul>
				</div>
			</main>
		</div>
	</div>
	<div w3-include-html="/TGA103G1/com/footer.html"></div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
        function changelog() {
          if (sessionStorage.getItem("memberAccount")) {
            $("#login").css("display", "none");
            $("#memberlogout").css("display", "block");
          }
        }
        function logouut() {
          //登出
          document
            .querySelector("#innerLogout")
            .addEventListener("click", function () {
              sessionStorage.removeItem("memberAccount");
            });
          document
            .querySelector("#memberlogout")
            .addEventListener("click", function () {
              sessionStorage.removeItem("memberAccount");
            });
        }

        $(function () {
          changelog();
          $("ul.item_list").on("click", "img#img_heart", function (e) {
            let favorite_id = e.target.getAttribute("data-favorite_id");
            console.log(favorite_id);
            let r = confirm("確定刪除？");

            if (r) {
              $(this)
                .closest("li")
                .animate(
                  {
                    opacity: 0,
                  },
                  1000,
                  "swing",
                  function (e) {
                    // e.preventDefault();
                    $(this).remove();
                  }
                );
            }
            const msg = document.querySelector("#msg");

            fetch("/TGA103G1/favoriteDelete", {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify({
                favorite_id: favorite_id,
              }),
            })
              .then((resp) => resp.json())
              .then((body) => {
                const { successful } = body;
              });
          });

          //取我的最愛
          fetch("/TGA103G1/favorite", {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
            },
          })
            .then((resp) => resp.json())
            .then((body) => {
              console.log(body);
              for (var i = 0; i < body.length; i++) {
                console.log(body[0].name)
                $(".item_list").append(`
                      <li>
                          <div>
                            <img src="/TGA103G1/front-end/member/images/heart.png" data-favorite_id= "\${body[i].status1}" alt="" style="width: 30px;height:30px" id="img_heart">
                          </div>
                          <a href="#">
                            <div class="img_block">
                              <img src="\${body[i].img}" style="width: 250px;height:150px">
                            </div>
                            <span class="item_text">\${body[i].name}</span>
                          </a>
                        </li>
            `);
              }
            });

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
                xhttp.onreadystatechange = function () {
                  if (this.readyState == 4) {
                    if (this.status == 200) {
                      elmnt.innerHTML = this.responseText;
                    }
                    if (this.status == 404) {
                      elmnt.innerHTML = "Page not found.";
                    }
                    /* Remove the attribute, and call this function once more: */
                    elmnt.removeAttribute("w3-include-html");
                    includeHTML();
                    changelog();
                    logouut();
                  }
                };
                xhttp.open("GET", file, true);
                xhttp.send();
                /* Exit the function: */
                return;
              }
            }
          }
          includeHTML();
        });
      </script>
</body>
</html>
</FavoriteVO>
