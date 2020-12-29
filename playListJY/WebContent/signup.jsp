<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<script type="text/javascript">
	function checkValue() {
		if (!document.frm.id.value) {
			alert("아이디를 입력하세요.");
			return false;
		}
		if (!document.frm.pw.value) {
			alert("비밀번호를 입력하세요.");
			return false;
		}
		if (!document.frm.name.value) {
			alert("이름을 입력하세요.");
			return false;
		}
		if (!document.frm.code.value) {
			alert("주민번호를 입력하세요.");
			return false;
		}
	}
</script>

</head>
<div id="divPage">
	<br />
	<br /> <b><font size="6" color="gray">회원가입</font></b> <br />
	<br />
	<br />
	<form method="post" action="asignup" name="frm" onsubmit="return checkValue()">
		<table>
			<tr>
				<td id="title">아이디</td>
				<td><input type="text" name="id" maxlength="12"></td>
			</tr>
			<tr>
				<td id="title">비밀번호</td>
				<td><input type="password" name="pw" maxlength="12">
				</td>
			</tr>
			<tr>
				<td id="title">이름</td>
				<td><input type="text" name="name" maxlength="4"></td>
			</tr>
			<tr>
				<td id ="title">주민번호</td>
				<td><input type ="text" name="code" maxlength="6"></td>
			</tr>
			<tr>
				<td id ="title">닉네임</td>
				<td><input type ="text" name="nickName" maxlength="8"></td>
			</tr>
		</table>
		<div id ="pagination">
			<input type="submit" value="가입"> 
			<input type="reset"value="취소">
			<input type="button" value="돌아가기" onClick ="location.href='index.jsp'">
		</div>
	</form>
</div>
<body>
</body>
<script>

	$(frm).submit(function(e) {
		e.preventDefault();

		var id = $(frm.id).val();
		var pw = $(frm.pw).val();
		var code = $(frm.code).val();
		var name = $(frm.name).val();
		var nickName =$(frm.nickName).val();

		$.ajax({
			type : "post",
			url : "signup",
			data : {"id":id, "pw":pw, "code":code, "name":name, "nickName":nickName},
			success : function(data1) {
				alert(data1.length);
				var data = data1.trim();
				alert(data.length);
				if(data=="false") {
					alert("이미 있는 아이디입ㄴ다~");
				} else if (data=="true"){
					alert("회원가입에 성공했습니다~~~~~~~~~~~~~~~~~!");
 					frm.submit();
 					location.href = "index.jsp";
				}
			}
		});
	});
</script>
</html>