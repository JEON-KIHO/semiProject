<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<meta charset="UTF-8">
<title> 정보 </title>
<style>
	h1 {font-size:25px;}
	table {border-collapse:collapse; margin-bottom:5px;}
	#table {width:300px;}
	td {border:solid 1px pink;}
	input[type=file] {visibility:hidden; height:0px;}
	input {background:pink;}
</style>
</head>
<body>
 <h1> [정보] </h1>
 	<form name="form" action="update" method="post" enctype="multipart/form-data">
 		<table id="table">
 			<tr>
 				<td class="title"> CODE </td>
 				<td> <input type="text" name="code" size=7 value="${vo.code }" readonly> </td>
 			</tr>
 			<tr>
 				<td class="title"> NAME </td>
 				<td> <input type="text" name="pname" size=25 value="${vo.pname }"> </td>
 			</tr>
 			<tr>
 				<td class="title"> price </td>
 				<td> <input type="text" name="price" size=15 value="${vo.price }"> </td>
 			</tr>
 			<tr>
 				<td class="title"> image </td>
 					<td>
 						<c:if test="${vo.image==null }">
							<img src="http://placehold.it/150x110" width=150 id="image"> 				
 						</c:if>
 						<c:if test="${vo.image!=null }">
 							<img src="/product/img/${vo.image }" width=150 id="image"> 
 						</c:if>
 						<input type="file" accept="image/*" name="image">
 					</td>
 			</tr>
 		</table>
 		<input type="submit" value="update">
 		<input type="button" value="delete" id="btnDelete">
 		<input type="reset" value="cancel">
 		<input type="button" value="inven" onClick="location.href='/'">
 	</form>
</body>
<script>
	var code="${vo.code}";
	var pname="${vo.pname}";
	
	// delete click
	$("#btnDelete").on("click", function() {
		if(!confirm("delete "+ code + " _ " + pname +" ?")) return;
		location.href="delete?code=" + code;
	});

	// update click
	$(form).submit(function(e) {
		e.preventDefault();
		var price=$(form.price).val();
		var code=$(form.code).val();
		var pname=$(form.pname).val();
		if(code=="") {
			alert("insert code");
			$(form.code).focus();
		} else if(pname=="") {
			alert("insert name");
			$(form.pname).focus();
		} else if(price=="") {
			alert("insert price");
			$(form.price).focus();
		} else {
		if(!confirm("update?")) return;
		}
		form.submit();
	});

	$("#image").on("click", function () {
		$(form.image).click();
	});
	
	// image 맛보기
	 $(form.image).on("change", function(e){
        var reader = new FileReader();
        reader.onload=function(e){
            $("#image").attr("src", e.target.result);
        }
        reader.readAsDataURL(this.files[0]);
    });
</script>
</html>