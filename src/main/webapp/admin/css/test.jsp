<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <script>
        window.onload = function () {
	         $.ajax({
	           url: "/TGA103G1/control",
	           type: "post",
	           data: { action: "getAllProduct" },
	           dataType: "json",
	           success: function (xhr) {
	             var tableel = document.querySelector("table");
                 console.log(xhr[x]["product_id"]);
                 trEl = `
	 			<tr>
	 				<td>${xhr[x]["product_id"]}</td>
	 			</tr>	
	 			`;
	             tableel.append(trEl);

	           },
	           error: function (xhr) {
	             console.log("error");
	             console.log(xhr);
	           },
	         });
	       };
    </script>
</body>
</html>