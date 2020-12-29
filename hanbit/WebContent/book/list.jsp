<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<meta charset="UTF-8">
	<title> HANBIT media </title>
	<link rel="stylesheet" href="../home.css"/>
</head>
<style>
	.title {background:#65FF5E; color:white; text-align:center;}
	.row:hover {background:#A4FFFF;}
	.row:active {background:white; color:white;}
	td {padding:2px; text-align:center;}
	table {margin-top:5px;}
	#container {overflow:hidden;}
	#pagination {float:right;}
	#frm {width:550px;}
</style>
<body>
	<div id="page">
		<div id="header">
			<jsp:include page="../header.jsp"/>
		</div>
		
		<div id="center">
			<div id="menu">
				<jsp:include page="../menu.jsp"/>
			</div>
			<div id="content">
				<h1> [book info] </h1>
				<form name="form" action="list" id="frm">
					<input type="text" name="word" value="${word }">
					<input type="submit" value="search" id="btnSearch">
					<span> post : ${count } </span>
					<div id="pagination">
						<input type="hidden" name="page" value="${page }" size=4>
						<input type="button" value="♥" id="btnPre">
						<span> ${page } / ${lastPage } </span>
						<input type="button" value="♥" id="btnNext">
					</div>
				</form>
				<table>
					<tr class="title">
						<td width=50> NO. </td>
						<td width=300> TITLE </td>
						<td width=90> WRITER </td>
						<td width=90> PRICE </td>
					</tr>
					<c:forEach items="${list}" var="vo">
					<tr class="row">
						<td> ${vo.rn } </td>
						<td> ${vo.title } </td>
						<td> ${vo.writer } </td>
						<td> <fmt:formatNumber value="${vo.price }" pattern="#,###"/>＄ </td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		
		<div id="footer">
			<jsp:include page="../footer.jsp"/>
		</div>
	</div>
</body>
<script>
	var page = ${page};
	var lastPage = ${lastPage};
	
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
		page=1;
		$(form.page).val(page);
		form.submit();
	});
	if(page==1) $("#btnPre").attr("disabled", true);
	
	if(page==lastPage) $("#btnNext").attr("disabled",true);
	
</script>
</html>