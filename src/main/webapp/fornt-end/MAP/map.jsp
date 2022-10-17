<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<html>
<head>
<title>BarJarJo</title>
<script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0px;
}

header.header {
	font-size: 16px;
	width: 100%;
	min-width: 1200px;
	background-color: rgb(54, 26, 21);
}

.fl_left {
	float: left;
}

a#logo {
	text-decoration: none;
	margin-left: 20px;
	display: inline-block;
	margin-top: 5px;
	color: rgb(177, 123, 56);
}

#logo_h1 {
	margin: 0px;
}

ul.nav_ul {
	position: relative;
	z-index: 2;
	display: inline-block;
	margin-left: 100px;
}

ul.nav_ul li {
	display: inline-block;
	margin: 0 20px;
	position: relative;
}

ul.nav_ul li:hover ul#store {
	visibility: visible;
}

ul.nav_ul li a {
	text-decoration: none;
	color: rgb(177, 123, 56);
	width: 200px;
}

ul.nav_ul ul {
	visibility: hidden;
	position: absolute;
	display: block;
	background-color: rgb(54, 26, 21);
	padding: 0px;
	width: 150px;
	padding-right: 20px;
}

ul.nav_ul ul li {
	margin: 5px;
	width: 100%;
	padding: 5px;
}

ul.nav_ul li a:hover {
	color: white;
}

ul.nav_ul li a.icon::after {
	content: "";
	display: inline-block;
	width: 0px;
	height: 0px;
	border-top: 5px solid gray;
	border-bottom: 0px solid red;
	border-left: 5px solid transparent;
	border-right: 5px solid transparent;
	position: relative;
	top: -2px;
	margin-left: 5px;
}

#map {
	position: relative;
	z-index: 1;
	margin-right: 10px;
	margin-top: 10px;
	height: 720px;
	width: 49%;
	float: right;
}

.btn {
	width: 125px;
	line-height: 40px;
	font-size: 15px;
	color: rgb(255, 157, 0);
	text-align: center;
	background-color: rgb(255, 255, 255);
/* 	border: 5px solid SaddleBrown;  */
	border-radius: 15px;
	cursor: pointer;
	margin-inline: 5px;
}

.btn:hover {
	transform: scale(1.05);
}

.btn:active {
	transform: scale(1.1);
	box-shadow: inset 0 0 10px 1px rgba(0, 0, 0, .2);
}

.selected {
	width: 125px;
	line-height: 40px;
	font-size: 16px;
	color: PapayaWhip;
	text-align: center;
	background-color: Chocolate;
	border: 3px solid SaddleBrown;
	border-radius: 15px;
	cursor: pointer;
}

.store {
	border: 2px solid black;
	display: block;
	width: 49%;
	height: 83%;
	overflow: auto;
	float: left;
	position: relative;
}

.storeitem {
	padding: 8px 16px 6px;
	border-bottom: 1px solid #d8d8d8;
	display: block;
	height: 200px;
}

a {
	text-decoration: none;
}

a:link {
	color: #e6c3c3;
}

a:visited {
	color: #e6c3c3;
}

a:active {
	box-shadow: inset 0 0 10px 1px rgba(0, 0, 0, .1);
}

a:hover {
	color: white;
	box-shadow: inset 0 0 10px 1px rgba(0, 0, 0, .1);
}

a#mapp {
	font-size: 19px;
}

a#mapp:hover {
	color: brown;
}

.pro {
	color: #009393
}

div.name{
  display: flex;
  justify-content: space-between;
}
div.f {
	align-items: center;
	display: inline-block;
}

div.nf {
	align-items: center;
	display: inline-block;
}
img.store{
width:190px;
height:190px;
}
</style>
</head>
<meta charset="UTF-8">
<body style="background-color: rgb(129, 93, 65)">
	<header class="header">
		<div class="fl_left">
			<h1 id="logo_h1">
				<a href="＃" id="logo">Bar.Jar.Jo</a>
			</h1>
		</div>

		<nav class="fl_right">
			<ul class="nav_ul">
				<li><a href="#">HOME</a></li>
				<li><a href="#">地圖</a></li>
				<li><a href="#">討論區</a></li>
				<li><a href="#">廠商專區</a></li>
				<li><a href="#" class="icon">店家主題</a>
					<ul id="store">
						<li><a href="#">Bistro</a></li>
						<li><a href="#">Cocktail bar</a></li>
						<li><a href="#">Whisky bar</a></li>
					</ul></li>
				<li><a href="#" class="icon">會員專區</a>
					<ul id="store">
						<li><a href="#">我的最愛</a></li>
						<li><a href="#">修改會員資料</a></li>
						<li><a href="#">修改密碼</a></li>
						<li><a href="#">訂單管理</a></li>
					</ul></li>

				<li><a href="#">購物商城</a></li>
				<li><a href="#">登入註冊</a></li>
			</ul>
		</nav>
	</header>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<div id="map"></div>
	<br>
	<button id="btn" data-id="4" class="btn selected">全部主題</button>
	<button id="btn" data-id="1" class="btn">Bistro</button>
	<button id="btn" data-id="2" class="btn">Whisky bar</button>
	<button id="btn" data-id="3" class="btn">Cocktail bar</button>
	<button id="btnf" data-id="5" class="btn">我的最愛</button>
	<div class="store"></div>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA8huJrjACqoILrwvDvMuR8Ntv_aAYLOKQ&callback=initMap&v=weekly"
		defer></script>
	<script>
	let map, infoWindow;
	function initMap() {
	  map = new google.maps.Map(document.getElementById("map"), {
	    zoom: 15,
	    center: new google.maps.LatLng(25.048062816572394, 121.51710124831851),
	    mapTypeId: "roadmap"
	  });
	  infoWindow = new google.maps.InfoWindow();
	  const script = document.createElement("script");
	  script.src = "http://localhost:8080/TGA103G1/fornt-end/MAP/json.jsp";
	  document.getElementsByTagName("head")[0].appendChild(script);
	}
	var a = 4;//初始值為全部主題
	var arr = [];
	$(".btn").click(function () {
		$(".btn").removeClass("selected");
		$(this).toggleClass("selected");
		a = this.dataset.id;
		initMap();
	});
	const eqfeed_callback = function (e) {
		for (let i = 0; i < e.features.length; i++) {
	    	const coords = e.features[i].geometry.coordinates;
	    	const latLng = new google.maps.LatLng(coords[1], coords[0]);
	    	const name = e.features[i].properties.name;
	    	const id = e.features[i].properties.storeId;
	    	const theme = e.features[i].properties.theme;
	    	const address = e.features[i].properties.address;
	    	const produce = e.features[i].properties.produce;
	    	const content =
	    	'<div id="content">' +
	      	"主題:" +
	      	theme +
	      	"<a id='mapp' href='#'>" +
	      	name +
	      	"<br>"+
	      	"</a>" +
	      	address+
	      	"</div>";
	    	if (a == 1 || a == 2||a==3) {//主題1.2.3
	      	if (theme == a) {
	        	const marker = new google.maps.Marker({
	          	icon: "beer.png",
	        	animation: google.maps.Animation.DROP,
	        	position: latLng,
	        	map: map,
	        	label: theme,
	        	});
	        	var infowindow = new google.maps.InfoWindow();
	        	marker.addListener("click", function (e) {
	          		var coordinate = { lat: e.latLng.lat(), lng: e.latLng.lng() };
	          		infowindow.setPosition(coordinate);
	          		infowindow.setContent(content);
	          		infowindow.open(map, this);
	        	});
	      	}
	    	} else if (a == 4) {//全部主題
	      		const marker = new google.maps.Marker({
	        		icon: "beer.png",
	        		animation: google.maps.Animation.DROP,
	        		position: latLng,
	        		map: map,
	        		label: theme,
	     		});
	   			var infowindow = new google.maps.InfoWindow();
	     		marker.addListener("click", function (e) {
	        	var coordinate = { lat: e.latLng.lat(), lng: e.latLng.lng() };
	        	infowindow.setPosition(coordinate);
	        	infowindow.setContent(content);
	        	infowindow.open(map, this);
	      });
	    }else if (a == 5) {//我的最愛
	    	for(let x=0 ; x<arr.length;x++){
	    		if(id==arr[x]){
	    			const marker = new google.maps.Marker({
				        icon: "beer.png",
				        animation: google.maps.Animation.DROP,
				        position: latLng,
				        map: map,
				        label: theme,
				      });
				      var infowindow = new google.maps.InfoWindow();
				      marker.addListener("click", function (e) {
				        var coordinate = { lat: e.latLng.lat(), lng: e.latLng.lng() };
				        infowindow.setPosition(coordinate);
				        infowindow.setContent(content);
				        infowindow.open(map, this);
				      });
	    		}
	    	}
		}
	  }
	};

	 $(function(){//初始頁面
		    fetch("http://localhost:8080/TGA103G1/AllTheme", {
		      method: "POST",
		      headers: { "Content-Type": "application/json" },
		      body: JSON.stringify({
		      }),
		    })
		    .then(resp => resp.json())
		    .then(response => {
				console.log(response)
		    	$(".store").empty();
		        for (var i = 0; i < response.length; i++){
		        	let id =response[i].store_id;
		        	let name = "<a href='#' id="+id+">"+(i+1)+"." + response[i].name+"</a>" ;
		        	let pro = " 電話: " + response[i].phone+"<br>"+response[i].produce;
		        	let work = "營業時間："+response[i].work_open+"~"+response[i].work_end;
		        	let f = "<div id='"+id+"' class='nf'><img src='./nheart.png'></div>";
					let img = response[i].imgstr;
		        	$(".store").append("<div class='storeitem' id='"+id+"'>" +"<img  class='store' src='"+img+"'>"+ "<div class='name'>"+name+f+"</div>"+"<div class='pro'>"+pro+"<br>"+work+"</div>"+"</div>");
		        }
				fetch("http://localhost:8080/TGA103G1/checkk", {//驗證登入
		     		method: "GET",
		     		headers: { "Content-Type": "application/json" },
				})
				.then(resp => resp.json())
				.then(user => {
		   		 	console.log("會員ID"+user);
		   		 	if (user == null) {
		    			return;
		   			 }else {
						fetch("http://localhost:8080/TGA103G1/AllFavoriteM", {
				    		method: "POST",
				     		headers: { "Content-Type": "application/json" },
				    		body: JSON.stringify({
				    		member_id:user
				    	}),
				    	})
				    	.then(resp => resp.json())
				    	.then(response => {
				        	for (var i = 0; i < response.length; i++){
				        		let id=response[i].store_id;
				        		$("div#"+id+".nf").children().attr("src","./heart.png");
				        		$("div#"+id+".nf").attr("class","f");
				       		 }
				   		 });	
					}
				});
		      });
		   
		  });
	 $(document).on("click", "div.f", function(){
		fetch("http://localhost:8080/TGA103G1/checkk", {//驗證登入
		      method: "GET",
		      headers: { "Content-Type": "application/json" },
		})
		.then(resp => resp.json())
		.then(user => {
		    console.log(user);
		    if (user == null) {
		    	window.alert("尚未登入，將導向至登入頁");
		      	location = 'http://localhost:8080/TGA103G1/front-end/member/login.jsp';
		    } else {
		if(confirm('是否從我的最愛移除？')){
			$(this).children().attr("src","./nheart.png");
			$(this).attr("class","nf");
			let storeId =  this.id
			fetch("http://localhost:8080/TGA103G1/DeleteFavorite", {
			    method: "POST",
			    headers: { "Content-Type": "application/json" },
			    body: JSON.stringify({
			        store_id: storeId,
			        member_id:user
			    }),
			})
			.then(resp => resp.json())
			.then(response => {
			    console.log(response)
			});
		}
			}
		})
	  });
	 $(document).on("click", "div.nf", function(){
		fetch("http://localhost:8080/TGA103G1/checkk", {//驗證登入
		      method: "GET",
		      headers: { "Content-Type": "application/json" },
		})
		.then(resp => resp.json())
		.then(user => {
		    console.log(user);
		    if (user == null) {
	    		 if( window.confirm("尚未登入，將導向至登入頁")){
		      		location = 'http://localhost:8080/TGA103G1/front-end/member/login.jsp';}
		    }else {
				if(confirm('是否加入我的最愛？')){
				$(this).children().attr("src","./heart.png");
				$(this).attr("class","f");
				let storeId =  this.id
				fetch("http://localhost:8080/TGA103G1/AddFavorite", {
			    	method: "POST",
			    	headers: { "Content-Type": "application/json" },
			    	body: JSON.stringify({
			    	store_id: storeId,
			    	member_id:user
			    }),
				})
				.then(resp => resp.json())
				.then(response => {
					console.log(response)
				});
				}
			}
		});

		
	  });
	
	 $(document).on("click", "button#btn", function(){
		    let themeId =  this.dataset.id;
		    fetch("http://localhost:8080/TGA103G1/ChangeTheme", {
		      method: "POST",
		      headers: { "Content-Type": "application/json" },
		      body: JSON.stringify({
		        theme_id: themeId,
		      }),
		    })
		      .then(resp => resp.json())
		      .then(body => {
		    	  $(".store").empty();
		        console.log(body);
		        for (var i = 0; i < body.length; i++){
		        	let id =body[i].store_id;
		        	let name = "<a href='#' id="+id+">"+(i+1)+"." + body[i].name+"</a>" ;
		        	let pro = " 電話: " + body[i].phone+"<br>"+body[i].produce;
		        	let work = "營業時間："+body[i].work_open+"~"+body[i].work_end;
		        	let f =  "<div id='"+id+"' class='nf'><img src='./nheart.png'></div>";
					let img = body[i].imgstr;

		        	$(".store").append("<div class='storeitem' id='"+id+"'>" +"<img  class='store' src='"+img+"'>"+ "<div class='name'>"+name+f+"</div>"+"<div class='pro'>"+pro+"<br>"+work+"</div>"+"</div>");
		        }
				fetch("http://localhost:8080/TGA103G1/checkk", {//驗證登入
		     		method: "GET",
		     		headers: { "Content-Type": "application/json" },
				})
				.then(resp => resp.json())
				.then(user => {
		   		 	if (user == null) {
		    			return;
		   			}else{
						fetch("http://localhost:8080/TGA103G1/AllFavoriteM", {
				   			method: "POST",
				   			headers: { "Content-Type": "application/json" },
				    		body: JSON.stringify({
				    		member_id:user
				    		}),
						})
						.then(resp => resp.json())
						.then(body => {
				   			 for (var i = 0; i < body.length; i++){
				       			let d=body[i].store_id;
				        		$("div#"+d+".nf").children().attr("src","./heart.png");
				        		$("div#"+d+".nf").attr("class","f");
				    		}
						});
					}
				});
		      });
		  });
	 $(document).on("click", "button#btnf", function(){//我的最愛
		 fetch("http://localhost:8080/TGA103G1/checkk", {//驗證登入
		      method: "GET",
		      headers: { "Content-Type": "application/json" },
		    })
		      .then(resp => resp.json())
		      .then(user => {
		    	  console.log(user);
		    	  if (user == null) {
		    		 if(window.confirm("尚未登入，將導向至登入頁")){
			      		  location = 'http://localhost:8080/TGA103G1/front-end/member/login.jsp';
		    		 }
		    		 $("button.btn")[0].click();
		    	  } else {
		    		  fetch("http://localhost:8080/TGA103G1/AllFavoriteM", {
					      method: "POST",
					      headers: { "Content-Type": "application/json" },
					      body: JSON.stringify({
					    	  "member_id":user
					      }),
					    })
					      .then(resp => resp.json())
					      .then(body => {//user我的最愛中全部storeid
							$(".store").empty();
					        var g =1;
					        for (var i = 0; i < body.length; i++){
					        	let d=body[i].store_id;
					            fetch("http://localhost:8080/TGA103G1/AllTheme", {
					  		      method: "POST",
					  		      headers: { "Content-Type": "application/json" },
					  		      body: JSON.stringify({
					  		      }),
					  		    })
					  		      .then(resp => resp.json())
					  		      .then(response => {
					  		        for (var i = 0; i < response.length; i++){
					  		        	let id =response[i].store_id;
					  		        	let pro = " 電話: " + response[i].phone+"<br>"+response[i].produce;
					  		        	let work = "營業時間："+response[i].work_open+"~"+response[i].work_end;
					  		        	let f =  "<div id='"+id+"' class='f'><img src='./heart.png'></div>";
										let img = response[i].imgstr;

					  		        	if(id==d){
						  		        	$(".store").append("<div class='storeitem' id='"+id+"'>" +"<img  class='store' src='"+img+"'>"+"<div class='name'>"+"<a href='#' id="+id+">"+g+"." + response[i].name+"</a>"+f+"</div>"+"<div class='pro'>"+pro+"<br>"+work+"</div>"+"</div>");
											g=g+1;
					  		        	}
					  		        }
					  		      fetch("http://localhost:8080/TGA103G1/AllFavoriteM", {
					  			      method: "POST",
					  			      headers: { "Content-Type": "application/json" },
					  			      body: JSON.stringify({
					  			    	  member_id:user
					  			      }),
					  			    })
					  			      .then(resp => resp.json())
					  			      .then(response => {//map marker用
										console.log(response);
					  			        for (var i = 0; i < response.length; i++){
					  			        	arr[i]=response[i].store_id; 
					  			        }
					  				  initMap();
					  			      });	
					  		      });
					        }
					      });
		    	  }
		    	  
		      });
		  });
		  $(document).on("click", "div.storeitem", function(){
		  })
	window.initMap = initMap;
	window.eqfeed_callback = eqfeed_callback;
</script>
</body>
</html>