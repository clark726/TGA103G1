<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<html>
<head>
<title>BarJarJo</title>
<script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
<link rel="stylesheet" href="../../css/header.css">
<style>
*{
	box-sizing: border-box;
}

body {
	margin: 0px;
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

button.btn {
	width: 127px;
	line-height: 30px;
	font-size: 15px;
	color: rgb(255, 157, 0);
	text-align: center;
	background-color: rgb(255, 255, 255);
	border-radius: 15px;
	cursor: pointer;
	margin-inline: 5px;
	margin-bottom:10px;
}

button.btn:hover {
	transform: scale(1.05);
	color: rgb(255, 157, 0);
	
}
button.selected:hover{
color: PapayaWhip;
}
button.btn:active {
	transform: scale(1.1);
	box-shadow: inset 0 0 10px 1px rgba(0, 0, 0, .2);
}
button.selected {
	width: 127px;
	line-height: 30px;
	font-size: 16px;
	color: PapayaWhip;
	text-align: center;
	background-color:#e59b4a;
	border: 1px solid SaddleBrown;
	border-radius: 15px;
	cursor: pointer;
	margin-bottom:10px;
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
	border-bottom: 1.5px solid #d0aeae;
	display: block;
	height: 200px;
}


a.storename,a.theme{
	text-decoration: none;
	font-size:16px;
}
a:link {
	color: rgb(11, 93, 144);
}

a:visited {
	color: rgb(11, 93, 144);
}

a:active {
	box-shadow: inset 0 0 10px 1px rgba(0, 0, 0, .1);
}

a:hover {
	color: rgb(11, 93, 144);
	box-shadow: inset 0 0 10px 1px rgba(0, 0, 0, .1);
}


div.pro {
	color: rgb(30, 30, 30);
	font-weight:200;
 	line-height: 1.1;
}

div.name{
  display: flex;
  justify-content: space-between;
}
div.f { /*愛心圖片外div*/
	align-items: center;
	display: inline-block;
}

div.nf { /*空心圖片外div*/
	align-items: center;
	display: inline-block;
}
img.storeimg{
width:190px;
height:190px;
float:left
}
</style>
</head>
<meta charset="UTF-8">
<body style="background-color: rgb(224, 215, 208)">
	<div id="xxx"></div>
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	
	<div id="map"></div>
	<br>
	<button id="btn" data-id="4" class="btn selected">全部主題</button>
	<button id="btn" data-id="1" class="btn">Bistro</button>
	<button id="btn" data-id="2" class="btn">Cocktail bar</button>
	<button id="btn" data-id="3" class="btn">Whisky bar</button>
	<button id="btnf" data-id="5" class="btn">我的最愛</button>
	<div class="store"></div>
	<script src="../../back-end/js/header.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA8huJrjACqoILrwvDvMuR8Ntv_aAYLOKQ&callback=initMap&v=weekly"
		defer></script>
	<script>
	let map, infoWindow;
	function initMap() {
	  map = new google.maps.Map(document.getElementById("map"), {
	    zoom: 14.2,
	    center: new google.maps.LatLng(25.05282482972167, 121.53302753980128),
	    mapTypeId: "roadmap"
	  });
	  infoWindow = new google.maps.InfoWindow();
	  const script = document.createElement("script");
	  script.src = "/TGA103G1/fornt-end/MAP/json.jsp";
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
	    	const latLng = new google.maps.LatLng(coords[0], coords[1]);
	    	const name = e.features[i].properties.name;
	    	const id = e.features[i].properties.storeId;
	    	const themeId = e.features[i].properties.theme;
	    	const address = e.features[i].properties.address;
	    	const produce = e.features[i].properties.produce;
	    	if(themeId==1){
	    		theme='Bistro';
	    	}else if(themeId==2){
	    		theme='Cocktail bar';
	    	}else if(themeId==3){
	    		theme='Whisky bar';
	    	} 
	    	const content =
	    	'<div id="content">' +
	      	"<a class='storename' href='/TGA103G1/back-end/store/store.html' id="+id+">" +
	      	name +
	      	"<br>"+
	      	"</a>" +
	      	"<a class='theme' href='/TGA103G1/back-end/store/storeType.html' id="+themeId+">"+
	    	"主題:" +
	      	theme +
	      	"</a>"+
	      	"<br>"+
	      	address+
	      	"</div>";
	    	if (a == 1 || a == 2|| a==3) {//主題1.2.3
	      	if (themeId == a) {
	        	const marker = new google.maps.Marker({
	          	icon: "beer.png",
	        	animation: google.maps.Animation.DROP,
	        	position: latLng,
	        	map: map,
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
		    fetch("/TGA103G1/AllTheme", {
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
		        	let name = "<a class='storename' href='/TGA103G1/back-end/store/store.html' id="+id+">"+(i+1)+"." + response[i].name+"</a>" ;
		        	let pro = " 電話： " + response[i].phone+"<br>"+"介紹："+response[i].produce;
		        	let work = "營業時間："+response[i].work_open+"~"+response[i].work_end;
		        	let add = "地址："+response[i].address+"<br>"
		        	let f = "<div id='"+id+"' class='nf'><img src='./nheart.png'></div>";
					let img = response[i].imgstr;
		        	$(".store").append("<div class='storeitem' id='"+id+"'>" +"<img  class='storeimg' src='"+img+"'>"+ "<div class='name'>"+name+f+"</div>"+"<div class='pro'>"+add+pro+"<br>"+work+"</div>"+"</div>");
		        }
				fetch("/TGA103G1/checkk", {//驗證登入
		     		method: "GET",
		     		headers: { "Content-Type": "application/json" },
				})
				.then(resp => resp.json())
				.then(user => {
		   		 	console.log("會員ID"+user);
		   		 	if (user == null) {
		    			return;
		   			 }else {
						fetch("/TGA103G1/AllFavoriteM", {
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
		fetch("/TGA103G1/checkk", {//驗證登入
		      method: "GET",
		      headers: { "Content-Type": "application/json" },
		})
		.then(resp => resp.json())
		.then(user => {
		    console.log(user);
		    if (user == null) {
		    	window.alert("尚未登入，將導向至登入頁");
		      	location = '/TGA103G1/front-end/member/login.jsp';
		    } else {
		if(confirm('是否從我的最愛移除？')){
			$(this).children().attr("src","./nheart.png");
			$(this).attr("class","nf");
			let storeId =  this.id
			fetch("/TGA103G1/DeleteFavorite", {
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
		fetch("/TGA103G1/checkk", {//驗證登入
		      method: "GET",
		      headers: { "Content-Type": "application/json" },
		})
		.then(resp => resp.json())
		.then(user => {
		    console.log(user);
		    if (user == null) {
	    		 if( window.confirm("尚未登入，將導向至登入頁")){
		      		location = '/TGA103G1/front-end/member/login.jsp';}
		    }else {
				if(confirm('是否加入我的最愛？')){
				$(this).children().attr("src","./heart.png");
				$(this).attr("class","f");
				let storeId =  this.id
				fetch("/TGA103G1/AddFavorite", {
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
		    fetch("/TGA103G1/ChangeTheme", {
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
		        	let add = "地址："+body[i].address+"<br>"
		        	let name = "<a class='storename' href='/TGA103G1/back-end/store/store.html' id="+id+">"+(i+1)+"." + body[i].name+"</a>" ;
		        	let pro = " 電話： " + body[i].phone+"<br>"+"介紹："+body[i].produce;
		        	let work = "營業時間："+body[i].work_open+"~"+body[i].work_end;
		        	let f =  "<div id='"+id+"' class='nf'><img src='./nheart.png'></div>";
					let img = body[i].imgstr;

		        	$(".store").append("<div class='storeitem' id='"+id+"'>" +"<img  class='storeimg' src='"+img+"'>"+ "<div class='name'>"+name+f+"</div>"+"<div class='pro'>"+add+pro+"<br>"+work+"</div>"+"</div>");
		        }
				fetch("/TGA103G1/checkk", {//驗證登入
		     		method: "GET",
		     		headers: { "Content-Type": "application/json" },
				})
				.then(resp => resp.json())
				.then(user => {
		   		 	console.log(user);
		   		 	if (user == null) {
		    			return;
		   			}else{
						fetch("/TGA103G1/AllFavoriteM", {
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
		 fetch("/TGA103G1/checkk", {//驗證登入
		      method: "GET",
		      headers: { "Content-Type": "application/json" },
		    })
		      .then(resp => resp.json())
		      .then(user => {
		    	  if (user == null) {
		    		 if(confirm("尚未登入，將導向至登入頁")){
			      	 	location = '/TGA103G1/front-end/member/login.jsp';
		    		 }
		    		 $("button.btn")[1].click();
		    	  } else {
		    		  fetch("/TGA103G1/AllFavoriteM", {
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
					            fetch("/TGA103G1/AllTheme", {
					  		      method: "POST",
					  		      headers: { "Content-Type": "application/json" },
					  		      body: JSON.stringify({
					  		      }),
					  		    })
					  		      .then(resp => resp.json())
					  		      .then(response => {
					  		        for (var i = 0; i < response.length; i++){
					  		        	let id =response[i].store_id;
							        	let add = "地址："+response[i].address+"<br>"
					  		        	let pro = " 電話： " + response[i].phone+"<br>"+"介紹："+response[i].produce;
					  		        	let work = "營業時間："+response[i].work_open+"~"+response[i].work_end;
					  		        	let f =  "<div id='"+id+"' class='f'><img src='./heart.png'></div>";
										let img = response[i].imgstr;
					  		        	if(id==d){
						  		        	$(".store").append("<div class='storeitem' id='"+id+"'>" +"<img  class='storeimg' src='"+img+"'>"+"<div class='name'>"+"<a class='storename' href='/TGA103G1/back-end/store/store.html' id="+id+">"+g+"." + response[i].name+"</a>"+f+"</div>"+"<div class='pro'>"+add+pro+"<br>"+work+"</div>"+"</div>");
											g=g+1;
					  		        	}
					  		        }
					  		      fetch("/TGA103G1/AllFavoriteM", {
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
		  $(document).on("click", ".storename", function(e){
			  sessionStorage.setItem("store_id",$(this).attr("id"))
		  })
		  $(document).on("click", ".theme", function(){
			  sessionStorage.setItem("themeId",$(this).attr("id"))
		  });
	window.initMap = initMap;
	window.eqfeed_callback = eqfeed_callback;
</script>
</body>
</html>


