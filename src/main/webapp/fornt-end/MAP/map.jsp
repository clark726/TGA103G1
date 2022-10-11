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
	width: 9%;
	line-height: 40px;
	font-size: 16px;
	color: rgb(255, 157, 0);
	text-align: center;
	background-color: rgb(255, 255, 255);
	/* border: 5px solid SaddleBrown; */
	border-radius: 15px;
	cursor: pointer;
	margin-inline: 10px;
}

.btn:hover {
	transform: scale(1.05);
}

.btn:active {
	transform: scale(1.1);
	box-shadow: inset 0 0 10px 1px rgba(0, 0, 0, .2);
}

.selected {
	width: 150px;
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


div.f {
	align-items: center;
	display: inline-block;
}

div.nf {
	align-items: center;
	display: inline-block;
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
	<button id="btn5" class="btnn">現在位置</button>
	<div id="map"></div>
	<!-- <button id="btn"> 加入我的最愛</button> -->
	<br>
	<button id="btn" data-id="4" class="btn selected">全部</button>
	<button id="btn" data-id="1" class="btn">Bistro</button>
	<button id="btn" data-id="2" class="btn">Whisky bar</button>
	<button id="btn" data-id="3" class="btn">Cocktail bar</button>
	<button id="btnf">我的最愛</button>
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
	    mapTypeId: "roadmap",
	  });
	  infoWindow = new google.maps.InfoWindow();

	  const locationButton = document.getElementById("btn5");
	  locationButton.textContent = "現在位置";
	  locationButton.classList.add("custom-map-control-button");
	  locationButton.addEventListener("click", () => {
	    if (navigator.geolocation) {
	      navigator.geolocation.getCurrentPosition(
	        (position) => {
	          const pos = {
	            lat: position.coords.latitude,
	            lng: position.coords.longitude,
	          };

	          infoWindow.setPosition(pos);
	          infoWindow.setContent("現在位置");
	          infoWindow.open(map);
	          map.setCenter(pos);
	        },
	        () => {
	          handleLocationError(true, infoWindow, map.getCenter());
	        }
	      );
	    } else {
	      handleLocationError(false, infoWindow, map.getCenter());
	    }
	  });
	  const script = document.createElement("script");
	  script.src = "http://localhost:8080/TGA103G1/fornt-end/MAP/json.jsp";
	  document.getElementsByTagName("head")[0].appendChild(script);
	}

	var a = 4;
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
	    if (a == 1 || a == 2||a==3) {
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
	    } else if (a == 4) {
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
	    }else if (a == 5) {
	    	
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

	 $(function(){
		    fetch("http://localhost:8080/TGA103G1/AllTheme", {
		      method: "POST",
		      headers: { "Content-Type": "application/json" },
		      body: JSON.stringify({
		      }),
		    })
		      .then(resp => resp.json())
		      .then(result => {
		    	  $(".store").empty();
		        for (var i = 0; i < result.length; i++){
		        	let id =result[i].store_id;
		        	let name = "<a href='#' id="+id+">"+(i+1)+"." + result[i].name+"</a>" ;
		        	let pro = " 電話: " + result[i].phone+"<br>"+result[i].produce;
		        	let work = "營業時間："+result[i].work_open+"~"+result[i].work_end;
		        	let f = "<div id='"+id+"' class='nf'><img src='./nheart.png'></div>";
		        	$(".store").append("<div class='storeitem'>" + name+f+"<div class='pro'>"+pro+"<br>"+work+"</div>"+"</div>");
		        }
		        fetch("http://localhost:8080/TGA103G1/AllFavoriteM", {
				      method: "POST",
				      headers: { "Content-Type": "application/json" },
				      body: JSON.stringify({
				    	  member_id:"1"
				      }),
				    })
				      .then(resp => resp.json())
				      .then(result => {
				        for (var i = 0; i < result.length; i++){
				        	let id=result[i].store_id;
				        	$("div#"+id+".nf").children().attr("src","./heart.png");
				        	$("div#"+id+".nf").attr("class","f");
				        }
				      });		
		      });
		   
		  });
	 $(document).on("click", "div.f", function(){
		 if(confirm('是否從我的最愛移除？')){
			 $(this).children().attr("src","./nheart.png");
				$(this).attr("class","nf");
			    let storeId =  this.id
			    fetch("http://localhost:8080/TGA103G1/DeleteFavorite", {
			      method: "POST",
			      headers: { "Content-Type": "application/json" },
			      body: JSON.stringify({
			        store_id: storeId,
			        member_id:"1"
			      }),
			    })
			      .then(resp => resp.json())
			      .then(result => {
			        console.log(result)
			      });
		 }
		
	  });
	 $(document).on("click", "div.nf", function(){
		 if(confirm('是否加入我的最愛？')){
			 $(this).children().attr("src","./heart.png");
				$(this).attr("class","f");
			    let storeId =  this.id
			    fetch("http://localhost:8080/TGA103G1/AddFavorite", {
			      method: "POST",
			      headers: { "Content-Type": "application/json" },
			      body: JSON.stringify({
			        store_id: storeId,
			        member_id:"1"
			      }),
			    })
			      .then(resp => resp.json())
			      .then(result => {
			        console.log(result)
			      });
		 }
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
		        	$(".store").append("<div class='storeitem'>" + name+f+"<div class='pro'>"+pro+"<br>"+work+"</div>"+"</div>");
		        }
		        fetch("http://localhost:8080/TGA103G1/AllFavoriteM", {
				      method: "POST",
				      headers: { "Content-Type": "application/json" },
				      body: JSON.stringify({
				    	  member_id:"1"
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
		      });
		  });

	 $(document).on("click", "button#btnf", function(){
		 fetch("http://localhost:8080/TGA103G1/AllFavoriteM", {
		      method: "POST",
		      headers: { "Content-Type": "application/json" },
		      body: JSON.stringify({
		    	  member_id:"1"
		      }),
		    })
		      .then(resp => resp.json())
		      .then(body => {
		    	$(".store").empty();
		        var g =1;
		        for (var i = 0; i < body.length; i++){
		        	console.log(body[i].store_id);
		        	let d=body[i].store_id;
		            fetch("http://localhost:8080/TGA103G1/AllTheme", {
		  		      method: "POST",
		  		      headers: { "Content-Type": "application/json" },
		  		      body: JSON.stringify({
		  		      }),
		  		    })
		  		      .then(resp => resp.json())
		  		      .then(body => {
		  		        for (var i = 0; i < body.length; i++){
		  		        	let id =body[i].store_id;
		  		        	let pro = " 電話: " + body[i].phone+"<br>"+body[i].produce;
		  		        	let work = "營業時間："+body[i].work_open+"~"+body[i].work_end;
		  		        	let f =  "<div id='"+id+"' class='f'><img src='./heart.png'></div>";
		  		        	if(id==d){
			  		        	$(".store").append("<div class='storeitem'>" + "<a href='#' id="+id+">"+g+"." + body[i].name+"</a>"+f+"<div class='pro'>"+pro+"<br>"+work+"</div>"+"</div>");
								g=g+1;
		  		        	}
		  		        }
		  		      fetch("http://localhost:8080/TGA103G1/AllFavoriteM", {
		  			      method: "POST",
		  			      headers: { "Content-Type": "application/json" },
		  			      body: JSON.stringify({
		  			    	  member_id:"1"
		  			      }),
		  			    })
		  			      .then(resp => resp.json())
		  			      .then(result => {
		  			        for (var i = 0; i < result.length; i++){
		  			        	arr[i]=result[i].store_id;
		  			        }
		  			  	  a = 5;
		  				  initMap();
		  			      });	
		  		      });
		        }
		      });
		
		  });
	function handleLocationError(browserHasGeolocation, infoWindow, pos) {
	  infoWindow.setPosition(pos);
	  infoWindow.setContent(
	    browserHasGeolocation
	      ? "Error: 定位失敗"
	      : "Error: 瀏覽器不支援定位."
	  );
	  infoWindow.open(map);
	}
	window.initMap = initMap;
	window.eqfeed_callback = eqfeed_callback;
</script>
</body>
</html>