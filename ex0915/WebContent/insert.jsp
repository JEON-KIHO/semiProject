<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<title> 주소 등록 </title>
	<style>
		table {border-collapse:collapse; margin:0px 0px 5px 0px;}
		td {border:solid 2px pink; padding:3px;}
		.row {background:gray;}
	</style>
</head>
<body>
	<h1> [주소 등록] </h1>
	<form name="form">
		<table>
			<tr class="row">
				<td> NAME : </td>
				<td> <input type="text" name="name"> </td>
			</tr>
			<tr class="row">
				<td> TEL : </td>
				<td> <input type="text" name="tel"> </td>
			</tr>
			<tr class="row">
				<td> ADDR : </td>
				<td> <input type="text" name="address"> </td>
			</tr>
		</table>
		<input type="submit" value="save">
		<input type="reset" value="cancel">
		<input type="button" value="inventory" onClick="location.href='list'">
	</form>
</body>
<script>
	$(form).submit(function(e) {
		
		e.preventDefault();
		
		var name=$(form.name).val();
		var tel=$(form.tel).val();
		var address=$(form.address).val();
		
		if (name=="") {
			alert("insert name");
			$(form.name).focus();
		} else if (tel=="") {
			alert("insert tel");
			$(form.tel).focus();
		} else if (address=="") {
			alert("insert address");
			$(form.address).focus();
		} else {
			if(!confirm("save?")) return;
			form.action="insert";
			form.method="post";
			form.submit();
		}
	});
</script>
</html>