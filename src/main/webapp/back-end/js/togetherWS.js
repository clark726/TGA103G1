 //WS 全體推播
 let MyPointWS = "/TogetherWS/james";
 let pathWS = window.location.pathname;
 let webCtxWS = pathWS.substring(0, pathWS.indexOf("/", 1));
 let endPointURLWS = "ws://" + window.location.host + webCtxWS + MyPointWS;

 let webSocketWS;

 let p_name = document.querySelector("#p_name");
 let toastheader = document.querySelector("#toastheader");
 let toastbody = document.querySelector("#toastbody");
 let liveToastBtn = document.querySelector("#liveToastBtn");
 let imgWS = document.querySelector("#imgWS");
 let storeIdWS = document.querySelector("#storeIdWS");
 function connectWS() {
   // create a webSocketWS
   webSocketWS = new WebSocket(endPointURLWS);

   webSocketWS.onopen = function (event) {};

   webSocketWS.onmessage = function (event) {
     var jsonObj = JSON.parse(event.data);
     toastbody.innerText = jsonObj.message;
     imgWS.setAttribute("src", jsonObj.img);
     storeIdWS.setAttribute("href", jsonObj.url)
     imgWS.setAttribute("data-storeid", jsonObj.storeId)

     liveToastBtn.click();
   };
 }
 //bootstrap
 $(document).on("click", "#liveToastBtn", function () {
   new bootstrap.Toast(document.querySelector(".toast")).show();
 });

  //點擊store_idWS

   $(document).on("click", "#storeIdWS", function (e) {
     let store_idWS = e.target.getAttribute("data-storeid");
     sessionStorage.setItem("store_id", store_idWS);
   });
 