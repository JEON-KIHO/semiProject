<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
    <title> 강좌 관 리  시슽헴 </title>
    <link rel="stylesheet" href="../home.css">
</head>
<body>
    <div id="divPage">
    	<div id="divTop"><jsp:include page="../header.jsp"/></div>
    	 <div id="divMenu"><jsp:include page="../menu.jsp"/></div>
        <div id="divCenter">
            <div id="divContent">
                <!-- 여기에 내용 등록 시작--------------------------------------------->
             <div id="divHeader"><h2> [강좌 관리 시슷템] </h2></div>
             <div id="divCondition">
                <div id="divSearch">
                	<select id="key">
                		<option value="lname" selected> 강좌이름 </option>
                		<option value="lcode"> 강좌번호 </option>
                		<option value="hours"> 강의시간 </option>
                		<option value="room"> 강의실 </option>
                		<option value="pname"> 담당교수 </option>
                	</select>
                	<input type="text" id="word">
                	<select id="perPage">
                		<option value="3" selected> 3post </option>
                		<option value="5"> 5post </option>
                		<option value="7"> 7post </option>
                	</select>
           	    	<input type="button" id="btnSearch" value="search">
           	    	post : <span id="post"></span>
				</div>
			</div>
                
                <div id="content">
                <table id="tbl"></table>
                <script id="temp" type="text/x-handlebars-template">
					<tr class="title">
						<td> NO. </td>
						<td> 강의번호 </td>
						<td> 강의이름 </td>
						<td> 강의시간 </td>
						<td> 강의실 </td>
						<td> 교수번호 </td>
						<td> 지도교수 </td>
						<td> 강의정보 </td>
					</tr>
					{{#each list}}
						<tr class="row">
							<td class="rn">{{rn}}</td>
							<td class="lcode">{{lcode}}</td>
							<td class="lname">{{lname}}</td>
							<td class="hours">{{hours}}</td>
							<td class="room">{{room}}</td>
							<td class="instructor">{{pcode}}</td>
							<td class="pname">{{pname}}</td>
							<td><button>강의정보</button></td>
						</tr>
					{{/each}}
				</script>
				</div>
				<div id="pagination">
					<button id="btnPre"> ☆ </button>
					<span id="curPage"></span>
					<button id="btnNext"> ☆ </button>
				</div>
                <!-- 여기에 내용 등록 종료--------------------------------------------->
            </div>
         </div>
        <div id="divBottom"><jsp:include page="../footer.jsp"/></div>
    </div>
</body>
<script>
	var page = 1;
	
	getList();
	
	function getList() {
		var key=$("#key").val();
		var word=$("#word").val();
		var perPage=$("#perPage").val();
		$.ajax({
			type:"get",
			url:"list.json",
			dataType:"json",
			data:{"page":page, "perPage":perPage, "key":key, "word":word},
			success:function(data) {
				var temp = Handlebars.compile($("#temp").html());
				 $("#tbl").html(temp(data));
				 $("#post").html(data.count);
				 $("#curPage").html(page +" / "+ data.lastPage);
				 if(page==1) {
					 $("#btnPre").attr("disabled", true);
				 } else {
					 $("#btnPre").attr("disabled", false);
				 }
				 if(page==data.lastPage) {
					 $("#btnNext").attr("disabled", true);
				 } else {
					 $("#btnNext").attr("disabled", false);
				 }
			}
		});
	}
	
	$("#btnPre").on("click", function() {
		page--;
		getList();
	});
	$("#btnNext").on("click", function() {
		page++;
		getList();
	});
	$("#btnSearch").on("click", function() {
		page=1;
		getList();
	});
	$("#word").keydown(function(key) {
		if(key.keyCode==13) {
			$("#btnSearch").click();
		}
	});
	$("#perPage").change(function() {
		page=1;
		getList();
	})
	$("#tbl").on("click", ".row button", function() {
		var row = $(this).parent().parent();
		var lcode = row.find(".lcode").html();
		location.href="read?lcode=" + lcode;
	});
</script>

</html>