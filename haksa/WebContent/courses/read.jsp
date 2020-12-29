<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 강좌 정보 </title>
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
             <div id="divHeader"><h2> [강좌 정보] </h2></div>
				<form name="form" action="update" method="post">
					<table>
						<tr>
							<td class="title">강좌번호</td>
							<td> <input type="text" size=10 name="lcode" value="${vo.lcode}" readonly> </td>
							<td class="title" width=150>강의실</td>
							<td> <input type="text" size=5 name="room" value="${vo.room}"> </td>
							<td class="title" width=150>강의시수</td>
							<td> <input type="text" size=5 name="hours" value="${vo.hours}"> </td>
						</tr>
						<tr>
							<td class="title">강좌이름</td>
							<td colspan=3> <input type="text" size=60 name="lname" value="${vo.lname}"> </td>
							<td class="title">최대수강인원</td>
							<td> <input type="text" size=5 name="capacity" value="${vo.capacity}"> </td>
						</tr>
						<tr>
							<td width=90 class="title">담당교수</td>
							<td width=400 colspan=3>
								<input type="text" size=5 name="pcode" value="${vo.instructor}">
								<input type="text" size=10 name="pname" value="${vo.pname}">
							</td>
							<td class="title">수강신청인원</td>
							<td> <input type="text" size=5 name="persons" value="${vo.persons}"> </td>
						</tr>
					</table>
					<div id="pagination">
						<input type="submit" value="update" id="btnUpdate">
						<input type="button" value="delete" id="btnDelete">
						<input type="reset" value="cancel">
						<input type="button" value="back" id="btnBack">
					</div>
				</form>
				
				<div id="divHeader"> <h2>[수강 신청 학생]</h2> </div>
					<div style="width:100px; margin:0px auto; margin-bottom:10px;">
						<input type="submit" value="Choice Update" id="cUpdate">
					</div>
				<table id="tbl"></table>
                <script id="temp" type="text/x-handlebars-template">
					
					<tr class="title">
						<td> <input type="checkbox" id="chkAll"> </td>
						<td> 학생번호 </td>
						<td> 학생이름 </td>
						<td> 수강신청일 </td>
						<td> 학년 </td>
						<td> 학과 </td>
						<td> 담당교수 </td>
						<td> 점수 </td>
					</tr>
					{{#each list}}
						<tr class="row">
							<td> <input type="checkbox" class="chk"> </td>
							<td class="scode">{{scode}}</td>
							<td class="sname">{{sname}}</td>
							<td class="edate">{{edate}}</td>
							<td class="year">{{year}}</td>
							<td class="dept">{{dept}}</td>
							<td class="pname">{{pname}}</td>
							<td>
								<input type="text" class="grade" size=3 value={{grade}}>
								<button>수정</button>
							</td>
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
var lcode="${vo.lcode}";

	getList();

	function getList() {
		$.ajax({
			type : "get",
			url : "/haksa/enrollments/list",
			dataType : "json",
			data : {"code" : lcode},
			success : function(data) {
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
	
	$("#btnDelete").on("click", function() {
		if(!confirm(lcode + "delete?")) return;
		$.ajax({
			type:"get",
			url:"delete",
			dataType:"json",
			data:{"lcode":lcode},
			success:function(data) {
				if(data.cnt==0) {
					alert("delete success");
					location.href="list.jsp";
				} else {
					alert(data.cnt + "nope");
				}
			}
		});
	});
	
	$(form).submit(function(e) {
		var lname = $(form.lname).val();
		var hours = $(form.hours).val();
		var capacity = $(form.capacity).val();
		var persons = $(form.persons).val();
		var room = $(form.room).val();
		var pcode = $(form.pcode).val();
		e.preventDefault();
		if(lname.trim().length==0) {
			alert("insert name");
			$(form.lname).focus();
		}else if(hours.length==0) {
			alert("insert hours");
			$(form.hours).focus();
		}else if(hours.replace(/[0-9]/g,'')) {
			alert("insert number");
			$(form.hours).focus();
		}else if(room.length==0) {
			alert("insert room");
			$(form.room).focus();
		}else if(room.replace(/[0-9]/g,'')) {
			alert("insert number");
			$(form.room).focus();
		}else if(capacity.length==0) {
			alert("insert capacity");
			$(form.capacity).focus();
		}else if(capacity.replace(/[0-9]/g,'')) {
			alert("insert number");
			$(form.capacity).focus();
		}else if(persons.length==0) {
			alert("insert persons");
			$(form.persons).focus();
		}else if(persons.replace(/[0-9]/g,'')) {
			alert("insert number");
			$(form.persons).focus();
		}else{
			if(!confirm("update?")) return;
			$.ajax({
				type:"post",
				url:"update",
				data:{"lcode":lcode, "lname":lname, "hours":hours, "room":room, "capacity":capacity, "persons":persons, "pcode":pcode},
				success:function() {
					alert("update success");
					getList();
				}
			});
		}
	});
	
	$("#tbl").on("click", ".row button", function() {
		var row = $(this).parent().parent();
		var scode = row.find(".scode").html();
		var grade = row.find(".grade").val();
		if(!confirm("save score?")) return;
		$.ajax({
			type:"post",
			url:"../enrollments/update",
			data:{"grade":grade, "scode":scode, "lcode":lcode},
			success:function() {
				alert("update success");
				getList();
			}
		});
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
	$("#cUpdate").on("click", function() {
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
					url:"/haksa/enrollments/update",
					data:{"scode":scode, "lcode":lcode, "grade":grade},
					success:function() {
						
					}
				});
		 });
		 alert("update success");
		 getList();
	});
	
	$(form.pcode).on("click", function() {
				window.open("/haksa/professors/plist.jsp", "pcode", "width=350, height=320, top=400, left=700");
	});
</script>
</html>