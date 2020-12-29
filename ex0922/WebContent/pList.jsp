<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<meta charset="UTF-8">
<title> 상 품 조 회 </title>
<style>
	h1 {font-size:25px; text-align:center;}
	#container {background:#BDFF12; width:680px; padding:5px; margin:5px; overflow:hidden; margin:0px auto;}
	.box {background:#C8FFFF; width:150px; padding:5px; margin:5px; float:left;}
	.box:hover {background:#4D00ED; color:white;}
	#search {background:#C8FFFF; width:680px; overflow:hidden; padding:5px; margin:5px; margin:0px auto;}
	#pagination {float:right;}
</style>
</head>
<body>
	<h1> [상 품 조 회] </h1>
	<div id="search">
		<form name="form" action="list">
			<select name="key">
				<option value="code"> code </option>
				<option value="pname"> name </option>
			</select>
			<input type="text" name="word">
			<input type="button" value="search" id="btnSearch">
			<input type="hidden" name="page" value="${page }">
			<span> post : ${total } </span>
			<div id="pagination">
				<input type="button" value="←" id="btnPre">
				${page } / ${lastPage }
				<input type="button" value="→" id="btnNext">
			</div>
		</form>
			
		<form name="pform" action="pList">
			<div>
				$ <input type="text" name="minPrice" id="minPrice" size=10> ~
				<input type="text" name="maxPrice" id="maxPrice" size=10>
				<select>
					<option value="asc"> 오름차순 </option>
					<option value="desc"> 내림차순 </option>
				</select>
				<input type="button" value="search" id="btnPsearch">
			</div>
		</form>
	</div>
	<div id="container">
		<c:forEach items="${list }" var="vo">
			<div class="box">
				<c:if test="${vo.image==null }">
					<image src="http://placehold.it/150x120">
				</c:if>
				<c:if test="${vo.image!=null }">
					<image src="/product/img/${vo.image }" width=150>
				</c:if>
				<div> [<b>${vo.rn }</b>] ${vo.code } </div>
				<div> ${vo.pname } </div>
				<div> $ <fmt:formatNumber value="${vo.price }" pattern="#,###" /></div>
			</div>
		</c:forEach>
	</div>
</body>
<script>
	var page = "${page}";
	var lastPage = "${lastPage}";
	var minPrice = "${minPrice}";
	var maxPrice = "${maxPrice}";
	
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
	
	if(page==1) $("#btnPre").attr("disabled", true);
	if(page==lastPage) $("#btnNext").attr("disabled", true);
	
	$("#btnPsearch").on("click", function() {
		pform.submit();
	});
</script>
</html>