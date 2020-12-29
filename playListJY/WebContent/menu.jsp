<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
   <c:if test="${AccountVO.id==null }">
   <div style="float:left;">
      <button onClick="location.href='login.jsp'">LogIn</button>
      <button onClick="location.href='signup.jsp'">SignUp</button>
   </div>
   </c:if>
   <c:if test="${AccountVO.id!=null }">
      <div style="float:right;">
      <span>${AccountVO.name }</span>
         <button onClick="location.href='/playList/logout'">LogOut</button>
      </div>
   </c:if>
</div>