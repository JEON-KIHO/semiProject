<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<meta charset="UTF-8">
<title> read </title>
</head>
<body>
	<h1> read </h1>
	<input type="text" id="word" size="15">
	<input type="button" id="btnSearch" value="search">
		<table id="tbl"></table>
		<script id="temp" type="text/x-handlebars-template">
		{{#each list}}
			<tr>
 				<td class="title"> CODE </td>
 				<td> <input type="text" name="code" size=12 value={{code}}> </td>
 			</tr>
 			<tr>
 				<td class="title"> NAME </td>
 				<td> <input type="text" name="name" size=20 value={{name}}> </td>
 			</tr>
 			<tr>
 				<td class="title"> ADDRESS </td>
 				<td> <input type="text" name="address" size=40 value={{address}}> </td>
 			</tr>
 			<tr>
 				<td class="title"> PRICE </td>
 				<td> <input type="text" name="price" size=5 value={{price}}> ￦ </td>
 			</tr>
		{{/each}}
	</script>
</body>
<script>
function getList() {
	var code=$("#word").val();
	$.ajax({
		type:"get",
		url:"read",
		dataType:"json",
		data:{"code":code},
		success:function(data) {
			var temp = Handlebars.compile($("#temp").html());
			 $("#tbl").html(temp(data));
		}
	});
}
$("#btnSearch").on("click", function() {
	getList();
});
$("#word").keydown(function(key) {
	if(key.keyCode==13) {
		$("#btnSearch").click();
	}
});
</script>
</html>