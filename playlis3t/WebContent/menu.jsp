<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div>
   <c:if test="${UsersVO.id==null }">
   <div style="float:right;">
      <button onClick="location.href='login.jsp'">LOGIN</button>
   </div>
   </c:if>
   <c:if test="${UsersVO.id!=null }">

<!--<div class="item"><a href="/shop/mall/list.jsp">mypage</a></div>
<div class="item"><a href="/shop/product/list.jsp">상품목록</a></div>
<div class="item"><a href="/shop/product/insert">상품등록</a></div>
<div class="item"><a href="/shop/purchase/list.jsp">주문목록</a></div>
-->
<div  style="float:left;">
      <span>${UsersVO.name }</span>
         <button onClick="location.href='/shop/logOut'">mypage</button>
      </div>
   </c:if>
</div> 

<div class="item"><a href="/playlist/music.jsp">PlayList</a></div>

