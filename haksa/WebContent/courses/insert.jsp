<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 강좌 등록 </title>
<link rel="stylesheet" href="../home.css">
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
	<div id="divPage">
    	<div id="divTop"><jsp:include page="../header.jsp"/></div>
    	 <div id="divMenu"><jsp:include page="../menu.jsp"/></div>
        <div id="divCenter">
            <div id="divContent">
                <!-- 여기에 내용 등록 시작--------------------------------------------->
             <div id="divHeader"><h2> [강좌 등록 시스템] </h2></div>
             <form action="insert" method="post" name="form">
				<table>
             		<tr>
             			<td class="title" width=100> 강좌번호 </td>
             			<td> <input type="text" name="lcode" size=5 placeholder="강좌번호"> </td>
             		</tr>
             		<tr>
             			<td class="title" width=100> 강좌이름 </td>
             			<td> <input type="text" name="lname" size=10 placeholder="강좌이름"> </td>
             		</tr>
             		<tr>
             			<td class="title" width=100> 강의시간 </td>
             			<td width=270>
             				<input type="radio" name="hours" value="1"> 1hours
             				<input type="radio" name="hours" value="2"> 2hours
             				<input type="radio" name="hours" value="3"> 3hours
             				<input type="radio" name="hours" value="4"> 4hours
             			</td>
             		</tr>
             		<tr>
             			<td class="title" width=100> 강의실 </td>
             			<td> <input type="text" name="room" size=3 placeholder="강의실"> </td>
             		</tr>
             		<tr>
             			<td class="title" width=100> 최대수강인원수 </td>
             			<td> <input type="text" name="capacity" size=3 placeholder="수강인원수"> </td>
             		</tr>
             		<tr>
             			<td class="title" width=100> 수강신청인원수 </td>
             			<td> <input type="text" name="persons" size=3 placeholder="수강신청인원수"> </td>
             		</tr>
             		<tr>
             			<td class="title" width=100> 지도교수 </td>
             			<td>
             				click→ <input type="text" name="pcode" size=5>
             				<input type="text" name="pname" size=5>
             			</td>
             		</tr>
             	</table>
             	<div id="pagination">
             		<input type="submit" value="save" style="background:white;">
             		<input type="reset" value="cancel" style="background:white;">
             	</div>
             </form>
                <!-- 여기에 내용 등록 종료--------------------------------------------->
            </div>
         </div>
        <div id="divBottom"><jsp:include page="../footer.jsp"/></div>
    </div>
</body>
<script>
	$(form.pcode).on("click", function() {
		window.open("/haksa/courses/plist.jsp", "pcode", "width=350, height=320, top=400, left=700");
	});

	$(form).submit(function(e) {
		e.preventDefault();
		var lcode=$(form.lcode).val();
		var lname=$(form.lname).val();
		
		if(lcode.length!=4) {
			alert("insert 4word");
			$(form.lcode).focus();
		} else if(lname.trim().length==0) {
			alert("insert name");
			$(form.lname).focus();
		}  else {
			$.ajax({
				type:"get",
				url:"chkCode",
				data:{"lcode":lcode},
				success:function(data){
					if(data==1) {
						alert("already used code");
					} else {
						if(!confirm("save?")) return;
						form.submit();
					}
				}
			});
		}
	});
	
	
</script>
</html>