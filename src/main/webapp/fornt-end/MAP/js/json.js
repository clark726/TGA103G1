let map, infoWindow;
	function initMap() {
	  map = new google.maps.Map(document.getElementById("map"), {
	    zoom: 15,
	    center: new google.maps.LatLng(25.048062816572394, 121.51710124831851),
	    mapTypeId: "roadmap",
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
		    .then(result => {
		    	$(".store").empty();
		        for (var i = 0; i < result.length; i++){
		        	let id =result[i].store_id;
		        	let name = "<a href='#' id="+id+">"+(i+1)+"." + result[i].name+"</a>" ;
		        	let pro = " 電話: " + result[i].phone+"<br>"+result[i].produce;
		        	let work = "營業時間："+result[i].work_open+"~"+result[i].work_end;
		        	let f = "<div id='"+id+"' class='nf'><img src='./nheart.png'></div>";
		        	$(".store").append("<div class='storeitem'>" + "<div class='name'>"+name+f+"</div>"+"<div class='pro'>"+pro+"<br>"+work+"</div>"+"</div>");
		        }
				fetch("http://localhost:8080/TGA103G1/checkk", {//驗證登入
		     		method: "GET",
		     		headers: { "Content-Type": "application/json" },
				})
				.then(resp => resp.json())
				.then(user => {
		   		 	console.log(user);
		   		 	if (user == null) {
						console.log(123);
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
				    	.then(result => {
				        	for (var i = 0; i < result.length; i++){
				        		let id=result[i].store_id;
				        		$("div#"+id+".nf").children().attr("src","./heart.png");
				        		$("div#"+id+".nf").attr("class","f");
				       		 }
				   		 });	
					}
				});
		      });
		   
		  });
	 //若登入//////////////////
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
			.then(result => {
			    console.log(result)
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
		    	window.alert("尚未登入，將導向至登入頁");
		      	location = 'http://localhost:8080/TGA103G1/front-end/member/login.jsp';
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
				.then(result => {
					console.log(result)
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
		        	$(".store").append("<div class='storeitem'>" + "<div class='name'>"+name+f+"</div>"+"<div class='pro'>"+pro+"<br>"+work+"</div>"+"</div>");
		        }//若登入執行以下///////////
				fetch("http://localhost:8080/TGA103G1/checkk", {//驗證登入
		     		method: "GET",
		     		headers: { "Content-Type": "application/json" },
				})
				.then(resp => resp.json())
				.then(user => {
		   		 	console.log(user);
		   		 	if (user == null) {
						console.log(123);
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
		      
		        ////////////////////////////////////
		      });
		  });
//若登入///////////////
	 $(document).on("click", "button#btnf", function(){//我的最愛
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
		    		  fetch("http://localhost:8080/TGA103G1/AllFavoriteM", {
					      method: "POST",
					      headers: { "Content-Type": "application/json" },
					      body: JSON.stringify({
					    	  "member_id":user
					      }),
					    })
					      .then(resp => resp.json())
					      .then(body => {
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
					  		      .then(body => {
					  		        for (var i = 0; i < body.length; i++){
					  		        	let id =body[i].store_id;
					  		        	let pro = " 電話: " + body[i].phone+"<br>"+body[i].produce;
					  		        	let work = "營業時間："+body[i].work_open+"~"+body[i].work_end;
					  		        	let f =  "<div id='"+id+"' class='f'><img src='./heart.png'></div>";
					  		        	if(id==d){
						  		        	$(".store").append("<div class='storeitem'>" +"<div class='name'>"+"<a href='#' id="+id+">"+g+"." + body[i].name+"</a>"+f+"</div>"+"<div class='pro'>"+pro+"<br>"+work+"</div>"+"</div>");
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
					  			      .then(result => {//map用
					  			        for (var i = 0; i < result.length; i++){
					  			        	arr[i]=result[i].store_id; 
					  			        }
					  				  initMap();
					  			      });	
					  		      });
					        }
					      });
		    		  
		    		  
		    	  }
		    	  
		      });
		  });
	window.initMap = initMap;
	window.eqfeed_callback = eqfeed_callback;
