<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
    <title> 교수 관 리  시슽헴 </title>
    <link rel="stylesheet" href="../home.css">
    <style>
    	#tbl {float:left;}
    </style>
</head>
<body>
	<!-- 여기에 내용 등록 시작--------------------------------------------->
		<div>
			<select id="key">
				<option value="pname" selected>교수이름</option>
				<option value="pcode">교수번호</option>
				<option value="dept">교수학과</option>
			</select> <input type="text" id="word">
			<select id="perPage">
				<option value="3" selected>3post</option>
				<option value="5">5post</option>
				<option value="7">7post</option>
			</select> <input type="button" id="btnSearch" value="search">
			post : <span id="post"></span>
		</div>

	<table id="tbl"></table>
	<script id="temp" type="text/x-handlebars-template">
		<tr class="title">
			<td> NO. </td>
			<td> 교수번호 </td>
			<td> 교수이름 </td>
			<td> 교수학과 </td>
		</tr>
		{{#each list}}
			<tr class="row">
				<td class="rn">{{rn}}</td>
				<td class="pcode">{{pcode}}</td>
				<td class="pname">{{pname}}</td>
				<td class="dept">{{dept}}</td>
			</tr>
		{{/each}}
	</script>
	<div>
		<button id="btnPre">☆</button>
		<span id="curPage"></span>
		<button id="btnNext">☆</button>
	</div>
	<!-- 여기에 내용 등록 종료--------------------------------------------->
</body>
<script>
	var page = 1;

	getList();
	
	$("#tbl").on("click", ".row", function() {
		var pcode=$(this).find(".pcode").html();
		var pname=$(this).find(".pname").html();
		$(opener.form.pcode).val(pcode);
		$(opener.form.pname).val(pname);
		window.close();
	});

	function getList() {
		var table = "professors";
		var key = $("#key").val();
		var word = $("#word").val();
		var perPage = $("#perPage").val();
		$.ajax({
			type : "get",
			url : "list.json",
			dataType : "json",
			data : {
				"page" : page,
				"perPage" : perPage,
				"key" : key,
				"word" : word,
				"table" : table
			},
			success : function(data) {
				var temp = Handlebars.compile($("#temp").html());
				$("#tbl").html(temp(data));
				$("#post").html(data.count);
				$("#curPage").html(page + " / " + data.lastPage);
				if (page == 1) {
					$("#btnPre").attr("disabled", true);
				} else {
					$("#btnPre").attr("disabled", false);
				}
				if (page == data.lastPage) {
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
		page = 1;
		getList();
	});
	$("#word").keydown(function(key) {
		if (key.keyCode == 13) {
			$("#btnSearch").click();
		}
	});
	$("#perPage").change(function() {
		page = 1;
		getList();
	})
</script>
</html>