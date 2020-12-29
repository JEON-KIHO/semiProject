<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<meta charset="UTF-8">
<title> 학생 정보</title>
</head>
<body>
<h1> 학생 정보 </h1>
	<table>
		<tr>
			<td class="title" width=100> 학번 </td>
			<td width=200> ${vo.scode } </td>
			<td class="title" width=100> 학생명 </td>
			<td width=200> ${vo.sname } </td>
		</tr>
		<tr>
			<td class="title" width=100> 학과 </td>
			<td width=200> ${vo.dept } </td>
			<td class="title" width=100> 생년월일 </td>
			<td width=200> ${vo.birthday } </td>
		</tr>
	</table>
	
	<h1> 수강 신청 </h1>
	<div id="divCourses">
		감자선택 : <select id="lcode">
			<c:forEach items="${clist }" var="vo">
				<option value="${vo.lcode }">${vo.lname }-${vo.pname }</option>
			</c:forEach>
		</select>
		<button id="btnEnroll"> 수강신청 </button>
	</div>
	<table id="tbl"></table>
	<script id="temp" type="text/x-handlebars-template">
		<tr class="title">
			<td width=100> 감자번호 </td>
			<td width=300> 감자이름 </td>
			<td width=100> 신청일 </td>
			<td width=70> 점수 </td>
			<td width=50> 취소 </td>
		</tr>
		{{#each .}}
			<tr class="row">
				<td width=100 class="lcode">{{lcode}}</td>
				<td width=300>{{lname}}</td>
				<td width=100>{{edate}}</td>
				<td width=70>{{grade}}</td>
				<td width=50> <button> delete </button> </td>
			</tr>
		{{/each}}
	</script>
</body>
<script>
var scode="${vo.scode}";

// 수강신청
$("#btnEnroll").click(function() {
	var lcode=$("#lcode").val();
	if(!confirm(lcode + " 수강 하싈?")) return;
	$.ajax({
		type:"post",
		url:"in_enroll",
		dataType:"json",
		data:{"scode":scode, "lcode":lcode},
		success:function(data) {
			if(data==1) {
				alert("already success");
			} else {
				alert("success");
				getList();
			}
		}
	});
});

// 수강삭제
$("#tbl").on("click", ".row button", function() {
	var row = $(this).parent().parent();
	var lcode = row.find(".lcode").html();
	if(!confirm(lcode + " cancel?????? really??????")) return;
	$.ajax({
		type:"post",
		url:"del_enroll",
		data:{"scode":scode, "lcode":lcode},
		success:function() {
			alert("success");
			getList();
		}
	});
});


getList();

function getList() {
	$.ajax({
		type:"get",
		url:"enroll.json",
		dataType:"json",
		data:{"scode":scode},
		success:function(data) {
			var temp = Handlebars.compile($("#temp").html());
			 $("#tbl").html(temp(data));
		}
	});
}
</script>
</html>