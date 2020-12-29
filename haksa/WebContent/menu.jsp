<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:if test="${login.id==null }">
	<div style="float:right;">
		<button onClick="location.href='login.jsp'">LOGIN</button>
	</div>
	</c:if>
	<c:if test="${login.id!=null }">
<div class="menuItem"><a href="/haksa/professors/list.jsp">교수목록</a></div>
<div class="menuItem"><a href="/haksa/professors/insert.jsp">교수등록</a></div>
<div class="menuItem"><a href="/haksa/students/list.jsp">학생목록</a></div>
<div class="menuItem"><a href="/haksa/students/insert.jsp">학생등록</a></div>
<div class="menuItem"><a href="/haksa/courses/list.jsp">강좌목록</a></div>
<div class="menuItem"><a href="/haksa/courses/insert.jsp">강좌등록</a></div>
		<div style="float:right;">
		<span>${login.name }</span>
			<button onClick="location.href='/haksa/logout'">LOGOUT</button>
		</div>
	</c:if>
