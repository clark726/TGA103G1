window.onload = function () {
        $.ajax({
          url: "/TGA103G1/control",
          type: "post",
          data: {
            action: "getAllforumReport",
          },
          dataType: "json",
          success: function (xhr) {
        	  console.log(xhr);
            for (var x = 0; x < xhr.length - 1; x++) {
              if (xhr[x].status == 0) {
                var tableObject = document.querySelector("table");
                var trEl = document.createElement("tr");
                var tdEl1 = document.createElement("td");
                tdEl1.appendChild(
                  document.createTextNode(xhr[x].forum_report_id)
                );
                var tdEl2 = document.createElement("td");
                tdEl2.appendChild(document.createTextNode(xhr[x].member_id));
                var tdEl3 = document.createElement("td");
                tdEl3.appendChild(document.createTextNode(xhr[x].forum_id));
                var tdEl4 = document.createElement("td");
                tdEl4.appendChild(document.createTextNode(xhr[x].reason));
                var tdEl5 = document.createElement("td");
                tdEl5.appendChild(document.createTextNode(xhr[x].date));

                var btn1 = document.createElement("button");
                btn1.setAttribute("type", "button");
                btn1.setAttribute("class", "search");
                btn1.appendChild(document.createTextNode("search"));
                var tdEl6 = document.createElement("td");
                tdEl6.appendChild(btn1);

                var btn2 = document.createElement("button");
                btn2.setAttribute("class", "seen");
                btn2.appendChild(document.createTextNode("seen"));
                var tdEl7 = document.createElement("td");
                tdEl7.appendChild(btn2);

                var btn3 = document.createElement("button");
                btn3.setAttribute("class", "delete");
                btn3.appendChild(document.createTextNode("delete"));
                var tdEl8 = document.createElement("td");
                tdEl8.appendChild(btn3);

                trEl.appendChild(tdEl1);
                trEl.appendChild(tdEl2);
                trEl.appendChild(tdEl3);
                trEl.appendChild(tdEl4);
                trEl.appendChild(tdEl5);
                trEl.appendChild(tdEl6);
                trEl.appendChild(tdEl7);
                trEl.appendChild(tdEl8);
                tableObject.appendChild(trEl);
              }
            }
            document.querySelector(".admin").innerHTML =
              xhr[xhr.length - 1].admin;
          },
          error: function (xhr) {
            console.log("error");
            console.log(xhr);
          },
          complete: function (xhr) {},
        });
      };
      
		$("table").on("click",".search",function(e){
			var forumId = e.currentTarget.closest("tr").children[2].innerText;
			$.ajax({
	        url: "/TGA103G1/control",           
	        type: "post",                  
	        data: {"action":"readForum",
			        "forumId":forumId},              
	        dataType: "text",            
	        beforeSend: function(){     
	        },
	        headers: {},
	        statusCode: {                
	          200: function (res) {},
	          404: function (res) {},
	          500: function (res) {}
	        },
	        success: function(xhr){
				alert(xhr);
	        },
	        error: function(xhr){
				console.log("error");         
	            console.log(xhr);
	        },
	        complete: function(xhr){}
	      });
		})
		
					$("table").on("click",".seen",function(e){
			var trEl = e.currentTarget.closest("tr");
			var forumId = e.currentTarget.closest("tr").children[0].innerText;
			$.ajax({
	        url: "/TGA103G1/control",           
	        type: "post",                  
	        data: {"action":"seenForum",
			        "forumReport":forumId},
			dataType:"text",
	        beforeSend: function(){     
	        },
	        headers: {},
	        statusCode: {                
	          200: function (res) {},
	          404: function (res) {},
	          500: function (res) {}
	        },
	        success: function(xhr){
				trEl.remove();
				alert(xhr);
	        },
	        error: function(xhr){
				console.log("error");         
	            console.log(xhr);
	        },
	        complete: function(xhr){}
	      });
		})
		
			$("table").on("click",".delete",function(e){
			var trEl = e.currentTarget.closest("tr");
			var forumId = e.currentTarget.closest("tr").children[2].innerText;
			var reportId = e.currentTarget.closest("tr").children[0].innerText;
			$.ajax({
	        url: "/TGA103G1/control",           
	        type: "post",                  
	        data: {"action":"deleteForum",
			        "forumId":forumId,
					"reportId":reportId},              
	        dataType: "text",            
	        beforeSend: function(){     
	        },
	        headers: {},
	        statusCode: {                
	          200: function (res) {},
	          404: function (res) {},
	          500: function (res) {}
	        },
	        success: function(xhr){
				alert(xhr);
				trEl.remove();
	        },
	        error: function(xhr){
				console.log("error");         
	            console.log(xhr);
	        },
	        complete: function(xhr){}
	      });
		})