<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> create account </title>
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
					<table>
						<tr>
							<td colspan="2" width=150 class="title"> <h3> [CREATE ACCOUNT] </h3> </td>
						</tr>
						<tr>
							<td class="title"> ID </td>
							<td><input type="text" id="id" size=17></td>
						</tr>
						<tr>
							<td class="title"> PW </td>
							<td><input type="password" id="pw" size=17></td>
						</tr>
						<tr>
							<td class="title"> NAME </td>
							<td><input type="text" id="name" size=17></td>
						</tr>
						<tr>
							<td class="title"> CODE </td>
							<td><input type="text" id="code" size=17></td>
						</tr>
						<tr>
							<td class="title"> TYPE </td>
							<td>
								<select id="title">
									<option value="none"> ---------- </option>
									<option value="professors"> professors </option>
									<option value="students"> students </option>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align:right">
								<input type="submit" value="create" id="btnCreate">
							</td>
						</tr>
					</table>
                <!-- 여기에 내용 등록 종료--------------------------------------------->
            </div>
         </div>
        <div id="divBottom"><jsp:include page="footer.jsp"/></div>
    </div>
</body>
<script>

$("#btnCreate").on("click", function(e) {
	var id = $("#id").val();
	var pw = $("#pw").val();
	var name = $("#name").val();
	var code = $("#code").val();
	var title = $("#title").val();
	e.preventDefault();
	
	$.ajax({
		type:"post",
		url:"create",
		dataType:"json",
		data:{"id":id, "pw":pw, "name":name, "code":code, "title":title},
		success:function(data) {
			if(id.trim().length==0) {
				alert("insert ID");
				$("#id").focus();
			} else if(data.IC==1) {
				alert("already used ID");
				$("#id").focus();
			} else if(pw.trim().length==0) {
				alert("insert PW");
				$("#pw").focus();
			} else if(name.trim().length==0) {
				alert("insert NAME");
				$("#name").focus();
			} else if(title=="professors") {
				if(code.length!=3) {
					alert("insert 3word CODE");
					$("#code").focus();
				}
			} else if(title=="students") {
				if(code.length!=8) {
					alert("insert 8word CODE");
					$("#code").focus();
				}
			} else if(data.CC==1) {
				alert("already used CODE");
				$("#code").focus();
			} else if(code.trim().length==0) {
				alert("insert CODE");
				$("#code").focus();
			}  else {
				alert("success");
				location.href="login.jsp";
			}
		}
	});
});
</script>
</html>