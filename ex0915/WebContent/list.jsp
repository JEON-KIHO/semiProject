<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소 목록</title>
	
</head>
<body>
	<h1> [주소 목록] </h1>
	<a href="insert"> [주소 등록] </a>
		<table>
			<c:forEach items="${list}" var="vo">
				<tr>
					<td><a href="read?seq=${vo.seq}"> ${vo.name} </a></td>
					<td> ${vo.tel } </td>
					<td> ${vo.address } </td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>