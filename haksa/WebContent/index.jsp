<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title> 학사 관 리 시슽헴 </title>
    <link rel="stylesheet" href="home.css">
</head>
<body>
    <div id="divPage">
        <div id="divHeader"><jsp:include page="header.jsp"/></div>
        <div id="divCenter">
            <div id="divMenu"><jsp:include page="menu.jsp"/></div>
            <div id="divContent">
                <!-- 여기에 내용 등록 시작--------------------------------------------->
				<p>
				Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
				</p>
                <!-- 여기에 내용 등록 종료--------------------------------------------->
            </div>
         </div>
        <div id="divBottom"><jsp:include page="footer.jsp"/></div>
    </div>
</body>
</html>