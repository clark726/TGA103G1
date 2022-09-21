var arr = ["./圖檔/1.webp", "./圖檔/2.jpeg", "./圖檔/3.webp"];

window.onload = function () {
  document.querySelectorAll(".change").forEach(function (el) {
    el.addEventListener("click", function (e) {
      $(".change").removeClass("-on");

      e.target.classList.add("-on");

      document.querySelector("#img").src = arr[e.target.value];
    });
  });
};