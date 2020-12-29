<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 상품 정보의 현장 </title>
<link rel="stylesheet" href="../home.css">
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<style>
       #update:active {background:pink;}
       #reset:active {background:pink;}
       input {height:15px;}
</style>
</head>
<body>
	<div id="divPage">
		<div id="divMenu"><jsp:include page="../menu.jsp"/></div>
		<!-- start -->
		<div id="divHeader"><h2> [상품 정보의 현장] </h2></div>
		<form name="form" action="update" method="post" enctype="multipart/form-data">
			<table id="tbl">
				<tr>
					<td width=100 class="title">상품코드</td>
					<td width=100><input type="text" name="prod_id" size=10 value="${vo.prod_id }" readonly></td>
					<td width=100 class="title">제조원/수입원</td>
					<td width=200><input type="text" name="company" value="${vo.company }" size=25></td>
					<td class="title" width=100>판매가격</td>
					<td width=100><input type="text" name="price1" value="${vo.price1 }" size=10></td>
				</tr>
				<tr>
					<td class="title">업체코드</td>
					<td colspan=3>
						<input type="text" name="mall_id" value="${vo.mall_id }" size=10>
						<input type="text" name="mall_name" value="${vo.mall_name }" size=47>
					</td>
					<td class="title">일반가격</td>
					<td>
						<input type="text" name="price2" value="${vo.price2 }" size=10>
					</td>
				</tr>
				<tr>
					<td class="title">상품이름</td>
					<td colspan=3>
						<input type="text" name="prod_name" value="${vo.prod_name }" size=63.5>
					</td>
					<td class="title">판매상태</td>
					<td>
						<input type="checkbox" name="chk_del" value="${vo.prod_del }" <c:out value="${vo.prod_del=='1' ?'checked':''}"/> > 판매중지
						<input type="hidden" name="prod_del" value="${vo.prod_del }">
					</td>
				</tr>
				<tr>
					<td class="title">상품이미지</td>
					<td colspan=5>
					
						<c:if test="${vo.image==null }">
							<img src="http://placehold.it/150x120" id="image" width=150 />
						</c:if>
						
						<c:if test="${vo.image!=null }">
							<img src="/shop/img/${vo.image }" id="image" width=150 />
						</c:if>
						
						<input type="file" name="image" accept="image/*" style="visibility: hidden;">
					</td>
				</tr>
				<tr>
					<td class="title">상품설명</td>
					<td colspan=5><textarea rows="10" cols="105" name="detail">${vo.detail }</textarea></td>
				</tr>
			</table>
			<div id="pagination">
				<input type="submit" value="update" id="update">
				<input type="reset" value="cancel" id="reset">
				<input type="button" value="delete" id="btnDelete">
			</div>
		</form>


		<!-- end -->
	</div>
</body>
<script>
	$("#btnDelete").on("click", function() {
		var prod_id = "${vo.prod_id}";
		$.ajax({
			type:"get",
			url:"delete",
			data:{"prod_id":prod_id},
			success:function(data) {
				if(data==0) {
					alert("delete success");
					location.href="list.jsp";
				} else {
					alert("can not delete because order count " + data + "EA");
				}
			}
		});
	});

	$(form).submit(function(e) {
		e.preventDefault();
		if(!confirm("update?")) return;
		form.submit();
	});

	$(form.chk_del).on("click", function() {
		if($(form.chk_del).is(":checked")) {
			$(form.prod_del).val("1");
		} else {
			$(form.prod_del).val("0");
		}
	});

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