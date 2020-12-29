<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<meta charset="UTF-8">
<title> 교수 등록 </title>
<link rel="stylesheet" href="../home.css">
</head>
<body>
	<div id="divPage">
    	<div id="divTop"><jsp:include page="../header.jsp"/></div>
    	 <div id="divMenu"><jsp:include page="../menu.jsp"/></div>
        <div id="divCenter">
            <div id="divContent">
                <!-- 여기에 내용 등록 시작--------------------------------------------->
             <div id="divHeader"><h2> [교수 등록 시스템] </h2></div>
             <form action="insert" method="post" name="form">
				<table>
             		<tr>
             			<td class="title" width=100> 교수번호 </td>
             			<td> <input type="text" name="pcode" size=3 placeholder="교수번호"> </td>
             		</tr>
             		<tr>
             			<td class="title" width=100> 교수이름 </td>
             			<td> <input type="text" name="pname" size=7 placeholder="교수이름"> </td>
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
             			<td class="title" width=100> 임용일자 </td>
             			<td width=200>
             				<input type="text" name="yy" size=2 placeholder="yyyy">年
             				<input type="text" name="mm" size=1 placeholder="mm">月
             				<input type="text" name="dd" size=1 placeholder="dd">日 
             			</td>
             		</tr>
             		<tr>
             			<td class="title" width=100> 교수직급 </td>
             			<td width=200>
             				<input type="radio" name="title" value="정교수"> 정교수
             				<input type="radio" name="title" value="부교수"> 부교수
             				<input type="radio" name="title" value="조교수"> 조교수
             			</td>
             		</tr>
             		<tr>
             			<td class="title" width=100> 급여 </td>
             			<td> <input type="text" name="salary" size=5 placeholder="급여">＄ </td>
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
	$(form).submit(function(e) {
		e.preventDefault();
		var pcode=$(form.pcode).val();
		var pname=$(form.pname).val();
		var salary=$(form.salary).val();
		
		var hiredate=$(form.yy).val() + "-" + $(form.mm).val() + "-" + $(form.dd).val();
		var fmtDate = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/;
		
		if(pcode.length!=3) {
			alert("insert 3word");
			$(form.pcode).focus();
		} else if(pname.trim().length==0) {
			alert("insert name");
			$(form.pname).focus();
		} else if(salary.replace(/[0-9]/g,'')) {
			alert("insert number");
			$(form.salary).focus();
		} else if(salary.length==0) {
			alert("insert salary");
			$(form.salary).focus();
		} else if (!fmtDate.test(hiredate) && hiredate!='--') {
			alert("insert date");
			$(form.yy).focus();
		} else {
			$.ajax({
				type:"get",
				url:"chkCode",
				data:{"pcode":pcode},
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