<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>로그인</title>
    <link rel="stylesheet" href="home.css">
    <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
    <div id="divPage">
        <div id="divTop"><jsp:include page="header.jsp"/></div>
        <div id="divCenter">
            <!-- <div id="divMenu"><jsp:include page="menu.jsp"/></div> -->
            <div id="divContent" style="padding:0px 20px 0px 20px">
                <!-- 여기에 내용 등록 시작--------------------------------------------->
               	<form name ="frm" action ="login" method ="post">
               		<table>
               			<tr>
               				<td colspan ="2" width=300 class ="title"><h1>로그인</h1></td>
               			</tr>
               			<tr>
               				<td class ="title">아이디</td>
               				<td><input type ="text" name ="id" size =10></td>
               			</tr>
               			<tr>
               				<td class ="title">비밀번호</td>
               				<td><input type ="password" name ="pw" size =10></td>
               			</tr>
               			<tr>
               				<td colspan ="2" style ="text-align:center;">
               					<input type ="submit" value ="로그인">   
               					<input type ="button" value ="회원가입" onClick ="location.href='signup.jsp'">            				
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
	$(frm).submit(function(e){
		var id =$(frm.id).val();
		var pw =$(frm.pw).val();
		
		e.preventDefault();
		$.ajax({
			type:"post",
			url:"login",
			data:{"id":id, "pw":pw},
			success:function(data){
				if(data==0){
					alert("아이디가 없습니다~");
				}else if(data==1){
					alert("비밀번호가 틀립니다~");
				}else{
					alert("로그인 성공 했습니다~");
					location.href="index.jsp";
				}
			}
			
		});
	});
	
</script>
</html>