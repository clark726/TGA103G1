let map, infoWindow;
	function initMap() {
	  map = new google.maps.Map(document.getElementById("map"), {
	    zoom: 15,
	    center: new google.maps.LatLng(25.048062816572394, 121.51710124831851),
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
	    	const latLng = new google.maps.LatLng(coords[1], coords[0]);
	    	const name = e.features[i].properties.name;
	    	const id = e.features[i].properties.storeId;
	    	const themeId = e.features[i].properties.theme;
	    	const address = e.features[i].properties.address;
	    	const produce = e.features[i].properties.produce;
	    	if(themeId==1){
	    		theme='Bistro';
	    	}else if(themeId==2){
	    		theme='Whisky bar';
	    	}else if(themeId==3){
	    		theme='Cocktail bar';
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
	    	if (a == 1 || a == 2||a==3) {//主題1.2.3
	      	if (theme == a) {
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
// 				        label: theme,
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
		        	let pro = " 電話: " + response[i].phone+"<br>"+response[i].produce;
		        	let work = "營業時間："+response[i].work_open+"~"+response[i].work_end;
		        	let add = "地址："+response[i].address+"<br>"
		        	let f = "<div id='"+id+"' class='nf'><img src='./nheart.png'></div>";
					let img = response[i].imgstr;
		        	$(".store").append("<div class='storeitem' id='"+id+"'>" +"<img  class='store' src='"+img+"'>"+ "<div class='name'>"+name+f+"</div>"+"<div class='pro'>"+add+pro+"<br>"+work+"</div>"+"</div>");
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
		        	let name = "<a class='storeName' href='/TGA103G1/back-end/store/store.html' id="+id+">"+(i+1)+"." + body[i].name+"</a>" ;
		        	let pro = " 電話: " + body[i].phone+"<br>"+body[i].produce;
		        	let work = "營業時間："+body[i].work_open+"~"+body[i].work_end;
		        	let f =  "<div id='"+id+"' class='nf'><img src='./nheart.png'></div>";
					let img = body[i].imgstr;

		        	$(".store").append("<div class='storeitem' id='"+id+"'>" +"<img  class='store' src='"+img+"'>"+ "<div class='name'>"+name+f+"</div>"+"<div class='pro'>"+add+pro+"<br>"+work+"</div>"+"</div>");
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
		    		 if(window.confirm("尚未登入，將導向至登入頁")){
			      		  location = '/TGA103G1/front-end/member/login.jsp';
		    		 }
		    		 $("button.btn")[0].click();
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
					  		        	let pro = " 電話: " + response[i].phone+"<br>"+response[i].produce;
					  		        	let work = "營業時間："+response[i].work_open+"~"+response[i].work_end;
					  		        	let f =  "<div id='"+id+"' class='f'><img src='./heart.png'></div>";
										let img = response[i].imgstr;
					  		        	if(id==d){
						  		        	$(".store").append("<div class='storeitem' id='"+id+"'>" +"<img  class='store' src='"+img+"'>"+"<div class='name'>"+"<a class='storeName' href='/TGA103G1/back-end/store/store.html' id="+id+">"+g+"." + response[i].name+"</a>"+f+"</div>"+"<div class='pro'>"+add+pro+"<br>"+work+"</div>"+"</div>");
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
		  $(document).on("click", ".storeName", function(e){
			  sessionStorage.setItem("store_id",$(this).attr("id"))
		  })
		  $(document).on("click", ".theme", function(){
			  sessionStorage.setItem("themeId",$(this).attr("id"))
		  });