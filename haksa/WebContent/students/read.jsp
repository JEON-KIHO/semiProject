<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 학생 정보 </title>
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
             <div id="divHeader"><h2> [학생 정보] </h2></div>
             <form action="update" method="post" name="form">
             	<table>
             		<tr>
             			<td width=100 class=title> 학생번호 </td>
             			<td width=100><input type="text" value=${vo.scode } size=5 name="scode" readonly></td>
             			<td width=100 class=title> 학생학과 </td>
             			<td width=150>
             				<select name="dept">
             					<option value="전산" <c:out value="${vo.dept=='전산'?'selected':'' }"/> > 전산학과 </option>
             					<option value="전자" <c:out value="${vo.dept=='전자'?'selected':'' }"/> > 전자학과 </option>
             					<option value="건축" <c:out value="${vo.dept=='건축'?'selected':'' }"/> > 건축학과 </option>
             				</select>
             			</td>
             			<td width=100 class=title> 생년월일 </td>
             			<td width=250>
             				<input type="text" size=2 name="yy" value="${fn:substring(vo.birthday,0,4) }"> 年
             				<input type="text" size=1 name="mm" value="${fn:substring(vo.birthday,5,7) }"> 月
             				<input type="text" size=1 name="dd" value="${fn:substring(vo.birthday,8,10) }"> 日
             			</td>
             		</tr>
             		<tr>
             			<td width=100 class=title> 학생이름 </td>
             			<td width=100><input type="text" value=${vo.sname } size=5 name="sname"></td>
             			<td width=100 class=title> 지도교수 </td>
             			<td width=150>
             				<input type="text" value=${vo.advisor } size=3 name="pcode">
             				<input type="text" value=${vo.pname } size=3 name="pname">
             			</td>
             			<td width=100 class=title> 학년 </td>
             			<td width=250>
             				<input type="radio" value="1" name="year" <c:out value="${vo.year==1?'checked':'' }"/> > 1year
             				<input type="radio" value="2" name="year" <c:out value="${vo.year==2?'checked':'' }"/> > 2year
             				<input type="radio" value="3" name="year" <c:out value="${vo.year==3?'checked':'' }"/> > 3year
             				<input type="radio" value="4" name="year" <c:out value="${vo.year==4?'checked':'' }"/> > 4year
             			</td>
             		</tr>
             	</table>
             	<div id="pagination">
             		<input type="submit" value="update" style="background:white;">
             		<input type="button" value="delete" id="btnDelete" style="background:pink;">
             		<input type="reset" value="cancel" style="background:white;">
             		<input type="button" value="back" id="btnBack">
             	</div>
             </form>
             
			<div id="divHeader"><h2> [수강 신청 정보] </h2></div>
			<div id="divCondition" style="width:400px; margin:0px auto; background:pink; padding:10px; margin-top:50px;">
				수강신청 과목  <select id="lcode">
					<c:forEach items="${clist }" var="vo">
						<option value="${vo.lcode }">${vo.lcode}-${vo.lname }-${vo.pname }:${vo.room }</option>
					</c:forEach>
				</select>
				<button id="btnEnroll"> 수강신청 </button>
			</div>
			
             <table id="tbl"></table>
                <script id="temp" type="text/x-handlebars-template">
					<tr class="title">
						<td> 강좌코드 </td>
						<td> 강좌이름 </td>
						<td> 수강신청일 </td>
						<td> 담당교수 </td>
						<td> 강의시수 </td>
						<td> 강의실 </td>
						<td> 수강취소 </td>
					</tr>
					{{#each list}}
						<tr class="row">
							<td class="lcode">{{lcode}}</td>
							<td class="lname">{{lname}}</td>
							<td class="edate">{{edate}}</td>
							<td class="pname">{{pname}}</td>
							<td class="hours">{{hours}}</td>
							<td class="room">{{room}}</td>
							<td><button>수강취소</button></td>
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
	var scode="${vo.scode}";

	getList();

function getList() {
	$.ajax({
		type:"get",
		url:"/haksa/enrollments/list",
		dataType:"json",
		data:{"code":scode},
		success:function(data) {
			var temp = Handlebars.compile($("#temp").html());
			 $("#tbl").html(temp(data));
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
	
	$("#tbl").on("click", ".row button", function() {
		var row = $(this).parent().parent();
		var lcode = row.find(".lcode").html();
		if(!confirm(lcode + "delete?")) return;
		$.ajax({
			type:"get",
			url:"../enrollments/delete",
			data:{"scode":scode, "lcode":lcode},
			success:function() {
				alert("success");
				getList();
			}
		});
	});
	
	$("#btnEnroll").click(function() {
		var lcode = $("#lcode").val();
		if(!confirm(lcode + " yeah-ah?")) return;
		$.ajax({
			type:"get",
			url:"../enrollments/insert",
			data:{"scode":scode, "lcode":lcode},
			success:function(data) {
				if(data==0) {
					alert("success");
					getList();
				} else {
					alert("nope");
				}
			}
		});
	});
	
	
	$("#btnDelete").click(function() {
		if(!confirm("delete?")) return;
		$.ajax ({
			type:"get",
			url:"delete",
			dataType:"json",
			data:{"scode":scode},
			success:function(data) {
				if(data.cnt==0) {
					alert("delete success");
				} else {
					alert(data.cnt + " ^sorry not sorry^");
				}
			}
		});
	});

	$(form).submit(function(e) {
		e.preventDefault();
		var sname=$(form.sname).val();
		
		var birthday=$(form.yy).val() + "-" + $(form.mm).val() + "-" + $(form.dd).val();
		var fmtDate = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/;
		
		if(sname.trim().length==0) {
			alert("insert name");
			$(form.sname).focus();
		}  else if (!fmtDate.test(birthday) && birthday!='--') {
			alert("insert date");
			$(form.yy).focus();
		} else {
			if(!confirm("update?")) return;
			form.submit();
		}
	});
	
	$(form.pcode).on("click", function() {
		window.open("/haksa/professors/plist.jsp", "pcode", "width=350, height=320, top=400, left=700");
	});
</script>
</html>