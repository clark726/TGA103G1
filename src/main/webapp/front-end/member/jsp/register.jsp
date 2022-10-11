<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>註冊</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/footer1.css">
    <style>
        *{
    box-sizing: border-box;
}
div.div_register{
    background-color: rgb(129, 93, 65);
    /* border: 1px solid red; */
    display: flex;
    justify-content: center;
    height: 100%;
    background-repeat: no-repeat;
    background-size: 100%;
}
div.div_register main.register_main{
    width: 600px;
    height: 100%;   
    background-color: rgba(226, 229, 172,0.8);
    border-radius: 20px;
    margin-top: 20px;
    margin-bottom: 20px;

}
div.div_register main.register_main form.register_form{
    margin: 0px;
    text-align: left;
}
div.div_register main.register_main form.register_form div.register_row{
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    margin-top: 3px;
    margin-bottom: 3px;
    margin-left: 60px;
    font-size: 18px;
    font-weight: 700;
    position: relative;
}
div.div_register main.register_main form.register_form> .font{
        /* border: 1px solid red; */
        margin-left: 400px;
    }
div.div_register main.register_main form.register_form div.register_row > label{
    padding: 10px;
}
div.div_register main.register_main form.register_form > h2{
    text-align: center;
    font-size: 28px;
}
div.div_register main.register_main form.register_form div{
    width: 70%;
}
div.div_register main.register_main form.register_form div > .register_control{
    border: 1px solid #ced4da;
    margin-left: 30px;
    border-radius: 5px;
    height: inherit;
    height: 30px;
    width: 100%;
    }
#register_acc , #register_psw , #register_birth ,#register_addr,#register_sname,#register_phone{
    margin-left: 70px;
}
#register_mail{
    margin-left: 60px;
}
.gender{
    /* border: 1px solid red; */
    margin-left: 35px;
}
div.register_btn{
    display: flex;
    justify-content: space-evenly;
    margin: 20px 80px;
}
div.register_btn  > button{
    height: 40px;
    border-radius: 5px;
    width: 60px;
}
div.register_btn > button:hover{
    background-color: #DD774E;
    cursor: pointer;
    font-weight: 700;
    width: 60px;
    border-radius: 10px;
}
div.register_btn #cancel > a{
    text-decoration: none;
    padding: 0px;
}
div.register_btn #cancel > a:link{
    color: black;
}
div.register_btn #cancel > a:visited{
    color: black;
}
    </style>
</head>
<body>
       <div w3-include-html="<%=request.getContextPath()%>/front-end/member/header.jsp"></div>
    <div class="div_register">
        <main class="register_main">
            <form action="<%=request.getContextPath()%>/register" class="register_form" method="post">
                <h2>會員註冊</h2>
                <div class="register_row">
                    <label for="register_acc">帳號</label>
                    <div>
                        <input type="text" id="register_acc" class="register_control" name="account">  
                    </div>
                </div>
                    <font size="1" color="red" class="font">${errorMsgs.account}${errorMsgs.accoun}</font>

                <div class="register_row">
                    <label for="register_psw">密碼</label>
                    <div>
                        <input type="password" id="register_psw" class="register_control" name="password">
                    </div>
                </div>
                    <font size="1" color="red" class="font">${errorMsgs.password}</font>     
                <div class="register_row">
                    <label for="register_dbpsw" class="font">確認密碼</label>
                    <div>
                        <input type="password" id="register_dbpsw" class="register_control" name="dbpassword">
                    </div>
                </div>
                 <font size="1" color="red" class="font">${errorMsgs.checkpassword}</font>
                
                <div class="register_row">
                    <label for="register_birth">生日</label>
                    <div>
                        <input type="date" id="register_birth" class="register_control" style="font-weight: 700;" name="birthday">
                    </div>
                </div>
				 <font size="1" color="red" class="font">${errorMsgs.birthday}</font>

                <div class="register_row">
                    <label for="register_addr">住址</label>
                    <div class="city">
                            <input class="js-demeter-tw-zipcode-selector" data-city="#city" data-dist="#dist" placeholder="請輸入郵遞區號" style="margin-left: 70px;"/>
                          <select id="city" placeholder="請選擇縣市" style="margin-left: 70px ;height: 25px;"name="city"></select>
                            <select id="dist" placeholder="請選擇鄉鎮區" style="height: 25px;"name="dist"></select>
                        <input type="text" id="register_addr" class="register_control" name="address" >
                    </div>
                </div>
				 <font size="1" color="red" class="font">${errorMsgs.address}</font>
                <div class="register_row">
                    <label for="">性別</label>
                    <div class="gender">
                        <input type="radio" name="gender" id="male" class="gender" value="0" checked><label for="male" >男</label> 
                        <input type="radio" name="gender" id="famale" class="gender" value="1"><label for="famale">女</label> 
                    </div>
                </div>

                <div class="register_row">
                    <label for="register_mail" class="font">Email</label>
                    <div>
                        <input type="text" id="register_mail" class="register_control" name="email">
                    </div>
                </div>
                    <font size="1" color="red" class="font">${errorMsgs.email}</font>
                
                <div class="register_row">
                    <label for="register_sname">暱稱</label>
                    <div>
                        <input type="text" id="register_sname" class="register_control" name="nickname">
                    </div>
                </div>
				 <font size="1" color="red" class="font">${errorMsgs.nickname}</font>
                <div class="register_row">
                    <label for="register_phone">手機</label>
                    <div>
                        <input type="text" id="register_phone" class="register_control" name="phone">
                    </div>
                </div>
                    <font size="1" color="red" class="font">${errorMsgs.phone}</font>
					<font size="1" color="red" class="font">${dbaccount}</font>
                <div class="register_btn">
                    <button type="submit" id="register">註冊</button>
                    <button type="button" id="cancel"><a href="">離開</a></button>
                </div>
            </form>
        </main>
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