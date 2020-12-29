<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<meta charset="UTF-8">
<title> insert </title>
</head>
<body>
	<h1> insert </h1>
		<form name="form" action="insert" method="post">
 		<table id="table">
 			<tr>
 				<td class="title"> CODE </td>
 				<td> <input type="text" name="code" size=12> </td>
 			</tr>
 			<tr>
 				<td class="title"> NAME </td>
 				<td> <input type="text" name="name" size=20> </td>
 			</tr>
 			<tr>
 				<td class="title"> ADDRESS </td>
 				<td> <input type="text" name="address" size=40> </td>
 			</tr>
 			<tr>
 				<td class="title"> PRICE </td>
 				<td> <input type="text" name="price" size=5> ￦ </td>
 			</tr>
 			<tr>
 				<td class="title"> SELF </td>
 				<td>
 					<select name="chkSelf">
 						<option value="O"> O </option>
 						<option value="X"> X </option>
 					</select>
 				</td>
 			</tr>
 		</table>
 		<input type="submit" value="save">
 		<input type="reset" value="cancel">
 	</form>
</body>
<script>

	$(form).submit(function(e) {
		e.preventDefault();
		var code=$(form.code).val();
		var name=$(form.name).val();
		var address=$(form.address).val();
		var price=$(form.price).val();
		var chkSelf=$(form.chkSelf).val();
		if(code=="") {
			alert("insert code");
			$(form.code).focus();
		} else if(name=="") {
			alert("insert name");
			$(form.name).focus();
		} else if(address=="") {
			alert("insert address");
			$(form.address).focus();
		} else if(price=="") {
			alert("insert price");
			$(form.price).focus();
		} else if(price.replace(/[0-9]/g,'')) {
			alert("insert number");
			$(form.price).focus();
		} else {
			if(!confirm("save?")) return;
			form.submit();
		}
		alert("save success");
	});
</script>
</html>