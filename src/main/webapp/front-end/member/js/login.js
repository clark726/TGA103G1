
window.addEventListener("load",function(){
    // ------------------驗證碼-----------------------
    var arr = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"];
    // console.log(arr);
    //页面刚加载时候调用它
    sjs(arr);
    
    login_check.onclick = function() {
     sjs(arr);
    }
    
    function sjs(arr) {
     var code = Math.floor(Math.random() * arr.length);
     var code1 = Math.floor(Math.random() * arr.length);
     var code2 = Math.floor(Math.random() * arr.length);
     var code3 = Math.floor(Math.random() * arr.length);
     var n = arr[code] + arr[code1] + arr[code2] + arr[code3];
     login_check.textContent = n
    }
    // ------------------------註冊不能空白或是輸入錯誤-------------------------

})