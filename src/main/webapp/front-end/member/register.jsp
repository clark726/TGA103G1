<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>會員註冊</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/footer1.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
    <style>
        *{
    box-sizing: border-box;
}

.div_register{
    background: rgb(0,0,0);
    background: linear-gradient(90deg, rgba(190,188,188,0.6754901789817489) 0%, rgb(122, 253, 255) 50%, rgba(190,188,188,0.6586834562926733) 100%); 
    background-color:rgb(190, 188, 188);
    padding:50px;
    opacity: 0.9;

}

h2{
    text-align: center;
    color:rgb(21, 21, 21);
    padding:10px;

}
label{
    color:black;
    font-size:20px;
}

.div_register .row input{
    font-size:20px;
    color:blue;
    border:1px solid blue;
}

font.font{
      
        margin-left: 300px;
    }

/* div.register_btn{
    display: flex;
    justify-content: space-evenly;
    margin: 20px 80px;
} */
.btn{
    margin-left: 45%;
    background-color: rgb(152, 243, 244) !important;
    border: 2px solid black !important;
    height: 50px;
    
    border-radius: 5px;
    width: 70px;
}
.btn:hover{
    background-color: rgb(190, 188, 188) !important;
    border: 2px solid black !important;
    cursor: pointer;
    height: 50px;
    font-weight: 700;
    width: 70px;
    border-radius: 10px;
}
 #cancel > a{
    text-decoration: none;
    padding: 0px;
}
 #cancel > a:link{
    color: black;
}
 #cancel > a:visited{
    color: black;
}
    </style>
</head>
<body>
    <div w3-include-html="/TGA103G1/com/header.html"></div>
    <div class="div_register">
            <form action="<%=request.getContextPath()%>/register" class="register_form" method="post">
                <h2>會員註冊</h2>
                <div class="row">
                    <div class="col-sm-6">
                    <label for="register_acc">帳號</label>
                    <font size="1" color="red" class="font">${errorMsgs.account}${errorMsgs.accoun}</font>
                     <input type="text" id="register_acc" class="form-control" name="account">  
                    </div>
                    <div class="col-sm-6">
                        <label for="register_sname">暱稱</label>
                        <font size="1" color="red" class="font">${errorMsgs.nickname}</font>
                        <input type="text" id="register_sname" class="form-control" name="nickname">
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-6">
	                    <label for="register_psw">密碼</label>
	                    <font size="1" color="red" class="font">${errorMsgs.password}</font>     
	                    <input type="password" id="register_psw" class="form-control" name="password">
                    </div>
                    <div class="col-sm-6">
                        <label for="register_dbpsw" class="font">確認密碼</label>
                        <font size="1" color="red" class="font">${errorMsgs.checkpassword}</font>
                         <input type="password" id="register_dbpsw" class="form-control" name="dbpassword">
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-6">
                        <label for="register_birth">生日</label>
                        <font size="1" color="red" class="font">${errorMsgs.birthday}</font>
                        <input type="date" id="register_birth" class="form-control" style="font-weight: 700;" name="birthday">
                    </div>

                    <div class="col-sm-6">
                        <br>
                        <label for="">性別</label>
                        <input type="radio" name="gender" id="male" class="gender" value="0" checked><label for="male" >男</label> 
                        <input type="radio" name="gender" id="famale" class="gender" value="1"><label for="famale">女</label> 
                    </div>
                </div>
                <br>
                <div class="row">
                    <label for="register_addr">住址</label>
                    <font size="1" color="red" class="font">${errorMsgs.address}</font>
                    <div class="col-sm-3">
                        <input class="js-demeter-tw-zipcode-selector form-control" data-city="#city" data-dist="#dist" placeholder="請輸入郵遞區號" />
                    </div>
                    <div class="col-sm-3">
                        <select class="form-control" id="city" placeholder="請選擇縣市" name="city"></select>
                    </div>
                    <div class="col-sm-3">
                        <select class="form-control" id="dist" placeholder="請選擇鄉鎮區" name="dist"></select>
                    </div>
                    <div class="col-sm-3">
                        <input type="text" id="register_addr" class="form-control" name="address" >
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-6">
                        <label for="register_mail" class="font">Email</label>
                        <font size="1" color="red" class="font">${errorMsgs.email}</font>
                         <input type="text" id="register_mail" class="form-control" name="email">
                    </div>
                    <div class="col-sm-6">
                        <label for="register_phone">手機</label>
                        <font size="1" color="red" class="font">${errorMsgs.phone}</font>
                        <input type="text" id="register_phone" class="form-control" name="phone">
                    </div>
                </div>
                <br>
                <font size="1" color="red" class="font">${dbaccount}</font>
                <div class="row">
                    <div class="col-sm-6">
                        <button type="submit" id="register" class="btn">註冊</button>
                    </div>
                    <div class="col-sm-6">
                        <button type="button" id="cancel" class="btn"><a href="">離開</a></button>
                    </div>
                </div>
            </form>
    </div>
    <div w3-include-html="/TGA103G1/com/footer.html"></div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>
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
</script>

</body>
</html>