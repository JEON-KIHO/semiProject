<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 상품 등록의 현장 </title>
<link rel="stylesheet" href="../home.css">
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<style>
       #save:active {background:pink;}
       #reset:active {background:pink;}
       input {height:15px;}
</style>
</head>
<body>
	<div id="divPage">
		<div id="divMenu"><jsp:include page="../menu.jsp"/></div>
		<!-- start -->
		<div id="divHeader"><h2> [상품 등록의 현장] </h2></div>
		<form name="form" action="insert" method="post" enctype="multipart/form-data">
			<table id="tbl">
				<tr>
					<td width=100 class="title">상품코드</td>
					<td width=100><input type="text" name="prod_id" size=10 value="${code }" readonly></td>
					<td width=100 class="title">제조원/수입원</td>
					<td width=200><input type="text" name="company" size=25></td>
					<td class="title" width=100>판매가격</td>
					<td width=100><input type="text" name="price1" size=10></td>
				</tr>
				<tr>
					<td class="title">업체코드</td>
					<td colspan=3>
						<input type="text" name="mall_id" size=10>
						<input type="text" name="mall_name" size=47>
					</td>
					<td class="title">일반가격</td>
					<td>
						<input type="text" name="price2" size=10>
					</td>
				</tr>
				<tr>
					<td class="title">상품이름</td>
					<td colspan=3>
						<input type="text" name="prod_name" size=63>
					</td>
					<td class="title">판매상태</td>
					<td>
						<input type="hidden" name="prod_del" value="0">
					</td>
				</tr>
				<tr>
					<td class="title">상품이미지</td>
					<td colspan=5>
						<img src="http://placehold.it/150x120" id="image" width=150 />
						<input type="file" name="image" accept="image/*" style="visibility: hidden;">
					</td>
				</tr>
				<tr>
					<td class="title">상품설명</td>
					<td colspan=5><textarea rows="10" cols="105" name="detail"></textarea></td>
				</tr>
			</table>
			<div id="pagination">
				<input type="submit" value="save" id="save">
				<input type="reset" value="cancel" id="reset">
			</div>
		</form>


		<!-- end -->
	</div>
</body>
<script>
	$("#image").on("click", function() {
		$(form.image).click();
	});
	
	// 선택한 이미지 미리보기
	$(form.image).on("change", function() {
		var reader = new FileReader();
		reader.onload=function(e) {
			$("#image").attr("src", e.target.result);
		}
		reader.readAsDataURL(this.files[0]);
	});

	$(form.mall_id).on("click", function() {
		window.open("/shop/mall/mlist.jsp","mall_id","width=400,height=400,top=150,left=450")
	});
</script>
</html>