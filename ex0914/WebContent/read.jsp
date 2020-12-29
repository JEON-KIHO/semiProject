<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 사용자 정보 </title>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<style>
	table {border-collapse:collapse; margin:5px 5px 5px 0px;}
		td {border:solid 1px black; padding:5px;}
		.title {background:gray; color:white; text-align:center;}
		.row:hover {background:black; color:white;}
	</style>
</head>
<body>
	<h1> [사용자 정보] </h1>
	<form name="form">
		<table>
			<tr>
				<td class="title" width=100> ID </td>
				<td width=150> <input type="text" name="id" value="${vo.id }"> </td>
			</tr>
			<tr>
				<td class="title" width=100> NAME </td>
				<td width=150> <input type="text" name="name" value="${vo.name }"> </td>
			</tr>
			<tr>
				<td class="title" width=100> PASSWORD </td>
				<td width=150> <input type="password" name="password" value="${vo.password }"> </td>
			</tr>
		</table>
		<input type="submit" value="update">
		<input type="reset" value="cancel">
		<input type="button" value="delete" id="btnDelete">
		<input type="button" value="inventory" onClick="location.href='list'">
	</form>
</body>
<script>
	$(form).submit(function() {
		if (!confirm("update?")) return;
		form.action="update";
		form.method="post";
		form.submit();
	});
	
	$("#btnDelete").on("click", function() {
		if (!confirm("delete?")) return;
		form.action="delete";
		form.method="post";
		form.submit();
	});
</script>
</html>