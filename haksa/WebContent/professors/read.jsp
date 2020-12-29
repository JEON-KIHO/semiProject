<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 교수 정보 </title>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<link rel="stylesheet" href="../home.css">
</head>
<body>
    <div id="divPage">
    	<div id="divTop"><jsp:include page="../header.jsp"/></div>
    	 <div id="divMenu"><jsp:include page="../menu.jsp"/></div>
        <div id="divCenter">
            <div id="divContent">
                <!-- 여기에 내용 등록 시작--------------------------------------------->
             <div id="divHeader"><h2> [교수 정보] </h2></div>
             <form action="update" method="post" name="form">
             	<table>
             		<tr>
             			<td width=100 class=title> 교수번호 </td>
             			<td width=100><input type="text" value=${vo.pcode } size=5 name="pcode" readonly></td>
             			<td width=100 class=title> 교수학과 </td>
             			<td width=100>
             				<select name="dept">
             					<option value="전산" <c:out value="${vo.dept=='전산'?'selected':'' }"/> >전산학과 </option>
             					<option value="전자" <c:out value="${vo.dept=='전자'?'selected':'' }"/> > 전자학과 </option>
             					<option value="건축" <c:out value="${vo.dept=='건축'?'selected':'' }"/> > 건축학과 </option>
             				</select>
             			</td>
             			<td width=100 class=title> 임용일자 </td>
             			<td width=200>
             				<input type="text" size=2 name="yy" value="${fn:substring(vo.hiredate,0,4) }"> 年
             				<input type="text" size=1 name="mm" value="${fn:substring(vo.hiredate,5,7) }"> 月
             				<input type="text" size=1 name="dd" value="${fn:substring(vo.hiredate,8,10) }"> 日
             			</td>
             		</tr>
             		<tr>
             			<td width=100 class=title> 교수이름 </td>
             			<td width=100><input type="text" value=${vo.pname } size=5 name="pname"></td>
             			<td width=100 class=title> 급여</td>
             			<td width=100><input type="text" value=${vo.salary } size=6 name="salary"></td>
             			<td width=100 class=title> 교수직급 </td>
             			<td width=200>
             				<input type="radio" value="정교수" name="title" <c:out value="${vo.title=='정교수'?'checked':'' }"/> > 정교수
             				<input type="radio" value="부교수" name="title" <c:out value="${vo.title=='부교수'?'checked':'' }"/> > 부교수
             				<input type="radio" value="조교수" name="title" <c:out value="${vo.title=='조교수'?'checked':'' }"/> > 조교수
             			</td>
             		</tr>
             	</table>
             	<div id="pagination">
             		<input type="submit" value="update" style="background:white;">
             		<input type="button" value="delete" id="btnDelete" style="background:pink;">
             		<input type="reset" value="cancel" style="background:white;">
             		<input type="button" valur="back" id="btnBack">
             	</div>
             </form>
             <table id="ctbl"></table>
                <script id="ctemp" type="text/x-handlebars-template">
				<h2> [담당 강좌] </h2>
					<tr class="title">
						<td> 강좌코드 </td>
						<td> 강좌이름 </td>
						<td> 강의시수 </td>
						<td> 강의실 </td>
						<td> 최대인원 </td>
						<td> 수강인원 </td>
						<td> 강좌정보 </td>
					</tr>
					{{#each clist}}
						<tr class="row">
							<td class="lcode">{{lcode}}</td>
							<td class="lname">{{lname}}</td>
							<td class="hours">{{hours}}</td>
							<td class="room">{{room}}</td>
							<td class="capacity">{{capacity}}</td>
							<td class="persons">{{persons}}</td>
							<td><button>강좌정보</button></td>
						</tr>
					{{/each}}
				</script>
			<table id="stbl"></table>
                <script id="stemp" type="text/x-handlebars-template">
				<h2> [담당 학생] </h2>
					<tr class="title">
						<td> 학생코드 </td>
						<td> 학생이름 </td>
						<td> 소속학과 </td>
						<td> 학년 </td>
						<td> 생년월일 </td>
						<td> 학생정보 </td>
					</tr>
					{{#each slist}}
						<tr class="row">
							<td class="scode">{{scode}}</td>
							<td class="sname">{{sname}}</td>
							<td class="dept">{{dept}}</td>
							<td class="year">{{year}}</td>
							<td class="birthday">{{birthday}}</td>
							<td><button>학생정보</button></td>
						</tr>
					{{/each}}
				</script>
			
                <!-- 여기에 내용 등록 종료--------------------------------------------->
            </div>
         </div>
        <div id="divBottom"><jsp:include page="../footer.jsp"/></div>
    </div>
</body>
<script>
	var pcode="${vo.pcode}";
	
	getList();
	
	function getList() {
		$.ajax({
			type:"get",
			url:"plist.json",
			dataType:"json",
			data:{"pcode":pcode},
			success:function(data) {
				var temp = Handlebars.compile($("#ctemp").html());
				 $("#ctbl").html(temp(data));
				 var temp = Handlebars.compile($("#stemp").html());
				 $("#stbl").html(temp(data));
			}
		});
	}
	
	$("#btnBack").click(function() {
		location.href="list.jsp";
	});
	
	$("body").keydown(function(key) {
		if(key.keyCode==8) {
			$("#btnBack").click();
		}
	});
	
	$("#btnDelete").click(function() {
		if(!confirm("delete?")) return;
		$.ajax ({
			type:"get",
			url:"delete",
			dataType:"json",
			data:{"pcode":pcode},
			success:function(data) {
				if(data.scnt==0 && data.ccnt==0) {
					alert("delete success");
					location.href="list.jsp";
				} else {
					alert(data.scnt + "s ^sorry not sorry^ c" + data.ccnt);
				}
			}
		});
	});

	$(form).submit(function(e) {
		e.preventDefault();
		var pname=$(form.pname).val();
		var salary=$(form.salary).val();
		
		var hiredate=$(form.yy).val() + "-" + $(form.mm).val() + "-" + $(form.dd).val();
		var fmtDate = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/;
		
		if(pname.trim().length==0) {
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
			if(!confirm("update?")) return;
			form.submit();
		}
	});
</script>
</html>