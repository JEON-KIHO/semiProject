<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<meta charset="UTF-8">
<title>주소 조회</title>
	<style>
		table {border-collapse:collapse; margin:0px 0px 5px 0px;}
		td {border:solid 2px pink; padding:3px;}
		.row {background:gray;}
	</style>
</head>
<body>
	<h1> [주소 조회] </h1>
	<form name="form">
	<input type="hidden" name="seq" value="${vo.seq}">
		<table>
			<tr class="row">
				<td> NAME : </td>
				<td> <input type="text" name="name" value="${vo.name}"> </td>
			</tr>
			<tr class="row">
				<td> TEL : </td>
				<td> <input type="text" name="tel" value="${vo.tel}"> </td>
			</tr>
			<tr class="row">
				<td> ADDR : </td>
				<td> <input type="text" name="address" value="${vo.address}"> </td>
			</tr>
		</table>
		<input type="submit" value="update">
		<input type="reset" value="cancel">
		<input type="button" value="delete" id="btnDelete">
		<input type="button" value="inventory" onClick="location.href='list'">
	</form>
</body>
<script>
$("#btnDelete").on("click", function() {
	if(!confirm("delete?")) return;
	form.action="delete";
	form.method="post";
	form.submit();
});

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
			if(!confirm("update?")) return;
			form.action="update";
			form.method="post";
			form.submit();
		}
	});
</script>
</html>