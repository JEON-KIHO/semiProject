<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<meta charset="UTF-8">
<title> 학생 등록 </title>
<link rel="stylesheet" href="../home.css">
</head>
<body>
	<div id="divPage">
    	<div id="divTop"><jsp:include page="../header.jsp"/></div>
    	 <div id="divMenu"><jsp:include page="../menu.jsp"/></div>
        <div id="divCenter">
            <div id="divContent">
                <!-- 여기에 내용 등록 시작--------------------------------------------->
             <div id="divHeader"><h2> [학생 등록 시스템] </h2></div>
             <form action="insert" method="post" name="form">
				<table>
             		<tr>
             			<td class="title" width=100> 학생번호 </td>
             			<td> <input type="text" name="scode" size=5 placeholder="학생번호" value="92414029"> </td>
             		</tr>
             		<tr>
             			<td class="title" width=100> 학생이름 </td>
             			<td> <input type="text" name="sname" size=7 placeholder="학생이름"> </td>
             		</tr>
             		<tr>
             		<td class="title" width=100> 소속학과 </td>
             		<td> <select name="dept">
             			<option value="전산"> 전산학과 </option>
             			<option value="전자"> 전자학과 </option>
             			<option value="건축"> 건축학과 </option>
             		</select> </td>
             		</tr>
             		<tr>
             			<td class="title" width=100> 생년월일 </td>
             			<td width=200>
             				<input type="text" name="yy" size=2 placeholder="yyyy">年
             				<input type="text" name="mm" size=1 placeholder="mm">月
             				<input type="text" name="dd" size=1 placeholder="dd">日 
             			</td>
             		</tr>
             		<tr>
             			<td class="title" width=100> 학년 </td>
             			<td width=270>
             				<input type="radio" name="year" value="1"> 1years
             				<input type="radio" name="year" value="2"> 2years
             				<input type="radio" name="year" value="3"> 3years
             				<input type="radio" name="year" value="4"> 4years
             			</td>
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
		window.open("/haksa/professors/plist.jsp", "pcode", "width=350, height=320, top=400, left=700");
	});

	$(form).submit(function(e) {
		e.preventDefault();
		var scode=$(form.scode).val();
		var sname=$(form.sname).val();
		
		var birthday=$(form.yy).val() + "-" + $(form.mm).val() + "-" + $(form.dd).val();
		var fmtDate = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/;
		
		if(scode.length!=8) {
			alert("insert 3word");
			$(form.scode).focus();
		} else if(sname.trim().length==0) {
			alert("insert name");
			$(form.sname).focus();
		}  else if (!fmtDate.test(birthday) && birthday!='--') {
			alert("insert date");
			$(form.yy).focus();
		} else {
			$.ajax({
				type:"get",
				url:"chkCode",
				data:{"scode":scode},
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