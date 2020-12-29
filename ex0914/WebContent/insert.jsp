<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 사용자 등록 </title>
	<style>
	table {border-collapse:collapse; margin:5px 5px 5px 0px;}
		td {border:solid 1px black; padding:5px;}
		.title {background:gray; color:white; text-align:center;}
		.row:hover {background:black; color:white;}
	</style>
</head>
<body>
	<h1> [사용자 등록] </h1>
	<form action="insert" method="post">
		<table>
			<tr>
				<td class="title" width=100> ID </td>
				<td width=150> <input type="text" name="id"> </td>
			</tr>
			<tr>
				<td class="title" width=100> NAME </td>
				<td width=150> <input type="text" name="name"> </td>
			</tr>
			<tr>
				<td class="title" width=100> PASSWORD </td>
				<td width=150> <input type="password" name="password"> </td>
			</tr>
		</table>
		<input type="submit" value="save">
		<input type="reset" value="cancel">
		<input type="button" value="inventory" onClick="location.href='list'">
	</form>
</body>
</html>