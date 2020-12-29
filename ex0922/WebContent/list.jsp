<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 상 품 조 회 </title>
<style>
	h1 {font-size:25px; text-align:center;}
	#container {background:#BDFF12; width:680px; padding:5px; margin:5px; overflow:hidden; margin:0px auto;}
	.box {background:#C8FFFF; height:200px; width:150px; padding:5px; margin:5px; float:left;}
	.box:hover {background:#4D00ED; color:white;}
	#search {background:#C8FFFF; width:680px; overflow:hidden; padding:5px; margin:5px; margin:0px auto;}
	#pagination {float:right;}
</style>
</head>
<body>
	<h1> [상 품 조 회] </h1>
	<div id="search">
		<form name="form" action="list">
			<input type="text" name="word" size=15 value="${word }">
			<input type="button" value="search" id="btnSearch">
			<input type="hidden" name="page" value="${page }">
			<input type="radio" name="order" value="desc" <c:out value="${order=='desc'?'checked':''}"/> > 높은가격순
			<input type="radio" name="order" value="asc" <c:out value="${order=='asc'?'checked':''}"/> > 낮은가격순
			<span> post : ${total } </span>
			<div id="pagination">
				<input type="button" value="←" id="btnPre">
				${page } / ${lastPage }
				<input type="button" value="→" id="btnNext">
			</div>
		</form>
	</div>
	<div id="container">
		<c:forEach items="${list }" var="vo">
			<div class="box">
				<c:if test="${vo.image==null }">
					<img src="http://placehold.it/150x120">
				</c:if>
				<c:if test="${vo.image!=null }">
					<img src="/product/img/${vo.image }" width=150>
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
	
	$(form.word).keydown(function(key) {
		if(key.keyCode==13) {
			$(form.page).val(1);
			form.submit();
		}
	});
	
	$(form.order).change(function() {
		$(form.page).val(1);
		form.submit();
	});
	
	$("#btnSearch").on("click", function() {
		form.submit();
	});
	
	if(lastPage==0) {
		$("#pagination").hide();
		$("#container").hide();
	}
	
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
	
</script>
</html>