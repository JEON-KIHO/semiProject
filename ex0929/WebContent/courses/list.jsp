<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 강좌 목록 </title>
</head>
<body>
	<h1> 강좌목록 </h1>
	<div id="divSearch">
		<form name="form" action="list">
			<select name="key">
				<option value="lcode" <c:out value="${key=='lcode'?'selected':'' }"/> > 강좌번호 </option>
				<option value="lname" <c:out value="${key=='lname'?'selected':'' }"/> > 강좌명 </option>
				<option value="pname" <c:out value="${key=='pname'?'selected':'' }"/> > 교수명 </option>
			</select>
			<input type="text" name="word" value="${word }">
			<input type="submit" value="search">
		</form>
	</div>
	<table>
		<tr class="title">
			<td width=100> 강좌번호 </td>
			<td width=200> 강좌명 </td>
			<td width=100> 담당교수 </td>
			<td width=100> 교수학과 </td>
			<td width=100> 교수정보 </td>
		</tr>
		<c:forEach items="${list }" var="vo">
			<tr class="row">
				<td width=100> ${vo.lcode } </td>
				<td width=200> ${vo.lname } </td>
				<td width=100> ${vo.pname } </td>
				<td width=100> ${vo.dept } </td>
				<td width=100> <button onClick="location.href='read?lcode=${vo.lcode}'"> 교수정보 </button> </td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>