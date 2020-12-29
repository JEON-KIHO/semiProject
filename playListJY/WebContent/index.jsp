<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>아오</title>
    <link rel="stylesheet" href="home.css">
</head>
<body>
    <div id="divPage">
        <div id="divTop"><jsp:include page="header.jsp"/></div>
        <div id="divCenter">
            <div id="divMenu"><jsp:include page="menu.jsp"/></div>
            <div id="divContent" style="padding:0px 20px 0px 20px">
                <!-- 여기에 내용 등록 시작--------------------------------------------->
                <!-- 여기에 내용 등록 종료--------------------------------------------->
            </div>
         </div>
        <div id="divBottom"><jsp:include page="footer.jsp"/></div>
    </div>
</body>
</html>