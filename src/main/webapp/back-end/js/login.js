function includeHTML() {
	const xxx = document.querySelector("#xxx");
	fetch("/TGA103G1/com/header.html")
		.then((resp) => resp.text())
		.then((content) => {
			xxx.innerHTML = content;
			changelog();

			const username = document.querySelector("#account");
			const password = document.querySelector("#password");
			const errMsg = document.querySelector("#errMsg");
			document.getElementById("btn1").addEventListener("click", () => {
				fetch("/TGA103G1/StoreLogin", {
					method: "POST",
					headers: { "Content-Type": "application/json" },
					body: JSON.stringify({
						account: username.value,
						password: password.value,
					}),
				})
					.then((resp) => resp.json())
					.then((body) => {
						errMsg.textContent = "";
						const { successful, message } = body;
						if (successful) {
							const { account, password } = body;
							sessionStorage.setItem("account", account);
							sessionStorage.setItem("password", password);
							location = "/TGA103G1/ShowProduct";
						} else {
							errMsg.textContent = message;
						}
					});
			});
		});
}
includeHTML();


//換登出鈕
function check() {
	if (sessionStorage.getItem("account")) {
		document.querySelector("#login").style.display = "none";
		document.querySelector("#logout").style.display = "block";
	}
}


//點擊theme_id
let allthemeid = document.querySelectorAll(".theme_id");
allthemeid.forEach(function(e) {
	e.addEventListener("click", function(e) {
		let id = e.target.getAttribute("data-themid");
		sessionStorage.setItem("themeId", id);
	});
});

//logout
function logout() {
	document
		.querySelector("#logout")
		.addEventListener("click", function() {
			sessionStorage.removeItem("account");
			check();
		});
}
logout();

 //換登出鈕
      function changelog() {
        if (sessionStorage.getItem("account")) {
          document.querySelector("#login").style.display = "none";
          document.querySelector("#logout").style.display = "block";
        }
      }



