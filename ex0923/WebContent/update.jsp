<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<meta charset="UTF-8">
<title> 업데이트 </title>
<style>
	table {border-collapse:collapse; margin-bottom:5px;}
	#table {width:300px;}
	td {border:solid 1px pink;}
	input[type=file] {visibility:hidden; height:0px;}
	input {background:pink;}
</style>
</head>
<body>
 <h1> [업데이트] </h1>
 	<form name="form" action="insert" method="post">
 		<table id="table">
 			<tr>
 				<td class="title"> CODE </td>
 				<td> <input type="text" name="code" size=7 value="${vo.code}"> </td>
 			</tr>
 			<tr>
 				<td class="title"> NAME </td>
 				<td> <input type="text" name="pname" size=25 value="${vo.pname}"> </td>
 			</tr>
 			<tr>
 				<td class="title"> price </td>
 				<td> <input type="text" name="price" size=15 value="${vo.price}"> </td>
 			</tr>
 			<tr>
 				<td class="title"> image </td>
 				<td>
 					<img src="http://placehold.it/150x110" width=150 id="image">
 					<input type="file" accept="image/*" name="image">
 				</td>
 			</tr>
 		</table>
 		<input type="submit" value="save">
 		<input type="reset" value="cancel">
 		<input type="button" value="inven" onClick="location.href='product.jsp'">
 	</form>
</body>
<script>

$("#image").on("click", function () {
	$(form.image).click();
});

//image 맛보기
$(form.image).on("change", function(e){
   var reader = new FileReader();
   reader.onload=function(e){
       $("#image").attr("src", e.target.result);
   }
   reader.readAsDataURL(this.files[0]);
});
</script>
</html>