<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 사용자 목록 </title>
	<style>
	table {border-collapse:collapse; margin-top:3px;}
		td {border:solid 1px black; padding:5px;}
		.title {background:gray; color:white; text-align:center;}
		.row:hover {background:black; color:white;}
	</style>
</head>
<body>
	<h1> [사용자 목록] </h1>
	<input type="button" onClick="location.href='insert'" value="regis">
	<table>
		<tr class="title">
			<td width=100> 아이디 </td>
			<td width=100> 이름 </td>
			<td width=100> 패스워드 </td>
		</tr>
		<c:forEach items="${list}" var="vo">
			<tr class="row" onClick="location.href='read?id=${vo.id}'">
				<td> ${vo.id } </td>
				<td> ${vo.name } </td>
				<td> ${vo.password } </td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>