<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<meta charset="UTF-8">
<title> 주소 목 록 </title>
	<style>
		* {margin:0px auto; text-align:center;}
		#divsearch {background:gray; color:white; width:460px; padding:5px;}
		#content {background:pink; width:600px; margin-top:5px;}
		.title {background:gray;}
		#pagination {text-align:center; padding:5px;}
	</style>
</head>
<body>
	<h1> [주 소 목 록] </h1>
	<div id="divsearch">
		<form action="list" name="form">
			<select name="key">
				<option value="name" <c:out value="${key=='name'?'selected':''}"/> > NAME </option>
				<option value="tel" <c:out value="${key=='tel'?'selected':''}"/> > TEL </option>
				<option value="address" <c:out value="${key=='address'?'selected':''}"/> > ADDR </option>
			</select>
			<input type="text" name="word" value="${word }">
			<select name="perPage">
				<option value="5" <c:out value="${perPage=='5'?'selected':''}"/> > 5post </option>
				<option value="10" <c:out value="${perPage=='10'?'selected':''}"/> > 10post </option>
				<option value="15" <c:out value="${perPage=='15'?'selected':''}"/> > 15post </option>
			</select>
			<input type="text" name="page" value="${page }" style="width:30px;"> /${totalPage } page
			<input type="submit" value="search">
			${total } post
		</form> 
	</div>
	<div id="content">
		<table>
			<tr class="title">
				<td width=50> NO. </td>
				<td width=100> NAME </td>
				<td width=200> TEL </td>
				<td width=300> ADDR </td>
			</tr>
			<c:forEach items="${list }" var="vo">
				<tr class="row">
					<td width=50> ${vo.row } </td>
					<td width=100> ${vo.name } </td>
					<td width=200> ${vo.tel } </td>
					<td width=300> ${vo.address } </td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div id="pagination">
		<button id="btnPre"> ◀ </button>
		${page} / ${totalPage}
		<button id="btnNext"> ▶ </button>
	</div>
</body>
<script>
	var page = "${page}";
	var totalPage = "${totalPage}"
	
	if(page==1) {
		$("#btnPre").attr("disabled", true);
	} else if (page==totalPage) {
		$("#btnNext").attr("disabled", true);
	}
	
	$("#btnNext").on("click", function() {
		page++;
		$(form.page).val(page);
		form.submit();
	});
	
	$("#btnPre").on("click", function() {
		page--;
		$(form.page).val(page);
		form.submit();
	});
</script>
</html>