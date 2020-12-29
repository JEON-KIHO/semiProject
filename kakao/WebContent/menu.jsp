<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
	<c:if test="${login.id==null }">
	<div class="item"><a href="/shop/index.jsp">Home</a></div>
	<div class="item"><a href="/shop/cart/list.jsp">장바구니</a></div>
	<div style="float:right; margin-right:10px;">
		<input type="image" src="/shop/image/login.jpg" alt="login" onClick="location.href='/shop/login.jsp'">
	</div>
	</c:if>
	<c:if test="${login.id!=null }">
<div class="item"><a href="/shop/index.jsp">Home</a></div>
<div class="item"><a href="/shop/mall/list.jsp">업체목록</a></div>
<div class="item"><a href="/shop/product/list.jsp">상품목록</a></div>
<div class="item"><a href="/shop/product/insert">상품등록</a></div>
<div class="item"><a href="/shop/purchase/list.jsp">주문목록</a></div>
<div class="item"><a href="/shop/cart/list.jsp">장바구니</a></div>
		<div style="float:right; margin-right:10px;">
			<span>login : ${login.name }</span>
			<input type="image" src="/shop/image/logout.jpg" alt="logout" onClick="location.href='/shop/logout'">
		</div>
	</c:if>
	-->
<!-- -->
<div class="item"><a href="local.jsp">LOCAL</a></div>
<div class="item"><a href="book.jsp">BOOK</a></div>
<div class="item"><a href="">BLOG</a></div>
<div class="item"><a href="chart.jsp">google CHART</a></div>
<div class="item"><a href="movie.jsp">MOVIE CHART</a></div>
