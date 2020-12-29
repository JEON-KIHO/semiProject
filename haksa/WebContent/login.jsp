<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title> 학사 관 리 시슽헴 </title>
    <link rel="stylesheet" href="home.css">
    <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
    <div id="divPage">
        <div id="divHeader"><jsp:include page="header.jsp"/></div>
        <div id="divCenter">
            <div id="divMenu"><jsp:include page="menu.jsp"/></div>
            <div id="divContent">
                <!-- 여기에 내용 등록 시작--------------------------------------------->
				<form name="form" action="login" method="post">
					<table>
						<tr>
							<td colspan="2" width=150 class="title"> <h3> L O G I N </h3> </td>
						</tr>
						<tr>
							<td class="title"> ID </td>
							<td><input type="text" name="id" size=17></td>
						</tr>
						<tr>
							<td class="title"> PW </td>
							<td><input type="password" name="pw" size=17></td>
						</tr>
						<tr>
							<td colspan="2" style="text-align:right">
								<input type="submit" value="login">
							</td>
						</tr>
					</table>
				</form>
                <!-- 여기에 내용 등록 종료--------------------------------------------->
            </div>
         </div>
        <div id="divBottom"><jsp:include page="footer.jsp"/></div>
    </div>
</body>
<script>
$(form).submit(function(e) {
	var id = $(form.id).val();
	var pw = $(form.pw).val();
	e.preventDefault();
	
	$.ajax({
		type:"post",
		url:"login",
		data:{"id":id, "pw":pw},
		success:function(data) {
			if(data==0) {
				alert("none ID");
			} else if (data==1) {
				alert("difference PW");
			} else {
				alert("login success");
				location.href="index.jsp";
			}
		}
	});
});

$("#cAccount").on("click", function() {
	location.href="cAccount.jsp";
});
</script>
</html>