<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 학생 목록 </title>
</head>
<body>
	<h1> 학생목록 </h1>
	<div id="divSearch">
		<form name="form" action="list">
			<select name="key">
				<option value="scode" <c:out value="${key=='scode'?'selected':'' }"/> > 학번 </option>
				<option value="s.dept" <c:out value="${key=='s.dept'?'selected':'' }"/> > 학과 </option>
				<option value="sname" <c:out value="${key=='sname'?'selected':'' }"/>> 학생명 </option>
				<option value="pname" <c:out value="${key=='pname'?'selected':'' }"/>> 지도교수 </option>
			</select>
			<input type="text" name="word" value="${word }">
			<input type="submit" value="search">
		</form>
	</div>
	<table>
		<tr class="title">
			<td width=100> 학번 </td>
			<td width=100> 학생명 </td>
			<td width=100> 학과 </td>
			<td width=100> 생년월일 </td>
			<td width=100> 지도교수 </td>
			<td width=100> 학생정보 </td>
		</tr>
		<c:forEach items="${list }" var="vo">
			<tr class="row">
				<td width=100> ${vo.scode } </td>
				<td width=100> ${vo.sname } </td>
				<td width=100> ${vo.dept } </td>
				<td width=100> ${vo.birthday } </td>
				<td width=100> ${vo.pname } </td>
				<td width=100> <button onClick="location.href='read?scode=${vo.scode}'"> 학생정보 </button> </td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>