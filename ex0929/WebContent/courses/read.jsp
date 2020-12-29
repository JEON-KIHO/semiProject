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
<h1> 교수 정보 </h1>
	<table>
		<tr>
			<td class="title" width=100> 강좌번호 </td>
			<td width=200> ${vo.lcode } </td>
			<td class="title" width=100> 강좌명 </td>
			<td width=200> ${vo.lname } </td>
		</tr>
		<tr>
			<td class="title" width=100> 담당교수 </td>
			<td width=200> ${vo.pname } </td>
			<td class="title" width=100> 교수학과 </td>
			<td width=200> ${vo.dept } </td>
		</tr>
	</table>
	
	<h1> 수강 신청 목록 </h1>
	<button id="btnUpdate"> choice update </button>
	<table id="tbl"></table>
	<script id="temp" type="text/x-handlebars-template">
		<tr class="title">
			<td><input type="checkbox" id="chkAll"></td>
			<td width=100> 학번 </td>
			<td width=100> 학생명 </td>
			<td width=100> 신청일 </td>
			<td width=150> 점수 </td>
		</tr>
		{{#each .}}
			<tr class="row">
				<td> <input type="checkbox" class="chk"> </td>
				<td width=100 class="scode">{{scode}}</td>
				<td width=100>{{sname}}</td>
				<td width=100>{{edate}}</td>
				<td width=70><input type="text" value="{{grade}}" size=3 class="grade">
				<button> update </button> </td>
			</tr>
		{{/each}}
	</script>
</body>
<script>
var lcode="${vo.lcode}";


// 수강수정
$("#tbl").on("click", ".row button", function() {
	var row=$(this).parent().parent();
	var scode=row.find(".scode").html();
	var grade=row.find(".grade").val();
	if(!confirm(grade + " update " + scode + " ?")) return;
	$.ajax({
		type:"post",
		url:"up_enroll",
		data:{"scode":scode, "lcode":lcode, "grade":grade},
		success:function() {
			alert("update success");
			getList();
		}
	});
});

$("#tbl").on("focusout", ".row .grade", function() {
	var row=$(this).parent().parent();
	var grade=row.find(".grade").val();
	if(grade.replace(/[0-9]/g,'')) {
		alert("insert number");
	}
});

// click chkAll
$("#tbl").on("click", "#chkAll",  function(){
    if($(this).is(":checked")){
        $("#tbl .row .chk").each(function(){
            $(this).prop("checked", true);
         });
    }else{
        $("#tbl .row .chk").each(function(){
            $(this).prop("checked", false);
        });
    }
});

// click chk
$("#tbl").on("click", ".row .chk", function(){
    var isCheck=true
    $("#tbl .row .chk").each(function(){
        if(!$(this).is(":checked")) isCheck=false
    });
    if(isCheck){
        $("#chkAll").prop("checked", true);
    }else{
        $("#chkAll").prop("checked", false);
    }
});

// click chk update
$("#btnUpdate").on("click", function() {
	var cnt=0;
	$("#tbl .row .chk:checked").each(function() { cnt++; });
	if(cnt==0){alert("choose chk"); return;}
	
	if(!confirm("update all?")) return;
	 $("#tbl .row .chk:checked").each(function(){
		 var row=$(this).parent().parent();
		 var scode=row.find(".scode").html();
		 var grade=row.find(".grade").val();
		 $.ajax({
				type:"post",
				url:"up_enroll",
				data:{"scode":scode, "lcode":lcode, "grade":grade},
				success:function() {
					
				}
			});
	 });
	 alert("update success");
	 getList();
});

//점수를 잘못 입력했을 때 alert출력 후 원래값으로 돌려놓기
var preGrade;
$("#tbl").on("focus", ".row .grade", function(){
    preGrade=$(this).val();
});

$("#tbl").on("change",".row .grade", function(e){
    var row=$(this).parent().parent();
    var grade=row.find(".grade").val();
    if(grade<=0 || grade>100){
       alert("insert 0 between 100");
       $(this).val(preGrade);
       $(this).focus();
     }
});


getList();

function getList() {
	$.ajax({
		type:"get",
		url:"enroll.json",
		dataType:"json",
		data:{"lcode":lcode},
		success:function(data) {
			var temp = Handlebars.compile($("#temp").html());
			 $("#tbl").html(temp(data));
		}
	});
}
</script>
</html>