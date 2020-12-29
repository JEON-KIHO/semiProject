<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<meta charset="UTF-8">
<title> 주 소 목     록 </title>
	<style>
		body h1 {font-size:25px;}
		* {margin:0px auto; text-align:center;}
		#divsearch {background:gray; color:white; width:460px; padding:5px;}
		#content {background:pink; width:600px; margin-top:5px;}
		.title {background:gray;}
		
	</style>
</head>
<body>
	<h1> [주 소 목 록] </h1>
	<div id="divSearch">
		<form name="form" action="list">
			<select name="key">
				<option value="name" <c:out value="${key=='name'?'selected':'' }"/> > NAME </option>
				<option value="tel" <c:out value="${key=='tel'?'selected':'' }"/> > TEL 
				<option value="address" <c:out value="${key=='address'?'selected':'' }"/> > ADDR </option>
			</select>
			<input type="text" name="word" style="width:100px" value="${word }">
			<select name="perPage">
				<option value="5" <c:out value="${perPage=='5'?'selected':'' }"/> > 5post </option>
				<option value="10" <c:out value="${perPage=='10'?'selected':'' }"/> > 10post </option>
				<option value="15" <c:out value="${perPage=='15'?'selected':'' }"/> > 15post </option>
			</select>
			<input type="button" value="search" id="btnSearch">
			<input type="hidden" name="page" style="width:22px;" value="${page}">
			<input type="text" value="${total }" style="width:22px"> post
		</form>
	</div>
	<div id="divContent">
		<table>
			<tr class="title">
				<th width=30> NO. </th>
				<th width=50> NAME </th>
				<th width=100> TEL </th>
				<th width=200> ADDR </th>
			</tr>
			<c:forEach items="${list }" var="vo">
				<tr class="row">
					<td> ${vo.rn } </td>
					<td> ${vo.name } </td>
					<td> ${vo.tel } </td>
					<td> ${vo.address } </td>
				</tr>
			</c:forEach>
		</table>
		<div id="pagination">
			<button id="btnPre"> ◁ </button>
			${page } / ${totalPage }
			<button id="btnNext"> ▷ </button>
		</div>
	</div>
</body>
<script>
	var page="${page}";
	var lastPage="${totalPage}";
	
	if(page==1) $("#btnPre").attr("disabled", true);
	if(page==lastPage) $("#btnNext").attr("disabled", true);
	
	$("#btnPre").on("click", function() {
		page--;
		$(form.page).val(page);
		form.submit();
	});

	$("#btnNext").on("click", function() {
		page++;
		$(form.page).val(page);
		form.submit();
	});
	
	$("#btnSearch").on("click", function() {
		$(form.page).val(1);
		form.submit();
	});
</script>
</html>