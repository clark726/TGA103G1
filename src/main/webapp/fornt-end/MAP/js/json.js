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
	  // map.controls[google.maps.ControlPosition.TOP_CENTER].push(locationButton);
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
	  script.src = "http://localhost:8081/TGA103G1/front-end/MAP/json.jsp";
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
	$(".btnf").click(function () {
		
		fetch("http://localhost:8081/TGA103G11/AllFavoriteM", {
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
		    fetch("http://localhost:8081/TGA103G11/AllTheme", {
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
		        fetch("http://localhost:8081/TGA103G11/AllFavoriteM", {
				      method: "POST",
				      headers: { "Content-Type": "application/json" },
				      body: JSON.stringify({
				    	  member_id:"1"
				      }),
				    })
				      .then(resp => resp.json())
				      .then(result => {
				        for (var i = 0; i < result.length; i++){
				        	let d=result[i].store_id;
				        	$("div#"+d+".nf").children().attr("src","./heart.png");
				        	$("div#"+d+".nf").attr("class","f");
				        }
				      });		
		      });
		   
		  });
	 $(document).on("click", "div.f", function(){
		 if(confirm('是否從我的最愛移除？')){
			 $(this).children().attr("src","./nheart.png");
				$(this).attr("class","nf");
			    let storeId =  this.id
			    fetch("http://localhost:8081/TGA103G11/DeleteFavorite", {
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
			    fetch("http://localhost:8081/TGA103G11/AddFavorite", {
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
		    fetch("http://localhost:8081/TGA103G11/ChangeTheme", {
		      method: "POST",
		      headers: { "Content-Type": "application/json" },
		      body: JSON.stringify({
		        theme_id: themeId,
		      }),
		    })
		      .then(resp => resp.json())
		      .then(result => {
		    	  $(".store").empty();
		        console.log(result);
		        for (var i = 0; i < result.length; i++){
		        	let id =result[i].store_id;
		        	let name = "<a href='#' id="+id+">"+(i+1)+"." + result[i].name+"</a>" ;
		        	let pro = " 電話: " + result[i].phone+"<br>"+result[i].produce;
		        	let work = "營業時間："+result[i].work_open+"~"+result[i].work_end;
		        	let f =  "<div id='"+id+"' class='nf'><img src='./nheart.png'></div>";
		        	$(".store").append("<div class='storeitem'>" + name+f+"<div class='pro'>"+pro+"<br>"+work+"</div>"+"</div>");
		        }
		        fetch("http://localhost:8081/TGA103G11/AllFavoriteM", {
				      method: "POST",
				      headers: { "Content-Type": "application/json" },
				      body: JSON.stringify({
				    	  member_id:"1"
				      }),
				    })
				      .then(resp => resp.json())
				      .then(result => {
				        for (var i = 0; i < result.length; i++){
				        	let d=result[i].store_id;
				        	$("div#"+d+".nf").children().attr("src","./heart.png");
				        	$("div#"+d+".nf").attr("class","f");
				        }
				      });
		      });
		  });

	 $(document).on("click", "button#btnf", function(){
		 fetch("http://localhost:8081/TGA103G11/AllFavoriteM", {
		      method: "POST",
		      headers: { "Content-Type": "application/json" },
		      body: JSON.stringify({
		    	  member_id:"1"
		      }),
		    })
		      .then(resp => resp.json())
		      .then(result => {
		    	$(".store").empty();
				  var g =1;
		        for (var i = 0; i < result.length; i++){
		        	let d=result[i].store_id;
		            fetch("http://localhost:8081/TGA103G11/AllTheme", {
		  		      method: "POST",
		  		      headers: { "Content-Type": "application/json" },
		  		      body: JSON.stringify({
		  		      }),
		  		    })
		  		      .then(resp => resp.json())
		  		      .then(result => {
		  		    	
		  		        for (var i = 0; i < result.length; i++){
		  		        	let id =result[i].store_id;
		  		        	let pro = " 電話: " + result[i].phone+"<br>"+result[i].produce;
		  		        	let work = "營業時間："+result[i].work_open+"~"+result[i].work_end;
		  		        	let f =  "<div id='"+id+"' class='f'><img src='./heart.png'></div>";
		  		        	if(id==d){
			  		        	$(".store").append("<div class='storeitem'>" + "<a href='#' id="+id+">"+g+"." + result[i].name+"</a>"+f+"<div class='pro'>"+pro+"<br>"+work+"</div>"+"</div>");
								g=g+1;
		  		        	}
		  		        }
		  		       
		  		      });
		        }
		      });
		
		  });
	function handleLocationError(browserHasGeolocation, infoWindow, pos) {
	  infoWindow.setPosition(pos);
	  infoWindow.setContent(
	    browserHasGeolocation
	      ? "Error: 定位失敗"
	      : "Error: 瀏覽器不支援定位"
	  );
	  infoWindow.open(map);
	}
	window.initMap = initMap;
	window.eqfeed_callback = eqfeed_callback;