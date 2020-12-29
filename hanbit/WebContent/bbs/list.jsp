<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<meta charset="UTF-8">
	<title> HANBIT media </title>
	<link rel="stylesheet" href="../home.css"/>
</head>
<body>
	<div id="page">
		<div id="header"> <jsp:include page="../header.jsp"/> </div>
		
		<div id="center">
			<div id="menu"> <jsp:include page="../menu.jsp"/> </div>
			<div id="content">
				<h1> gesipan board </h1>
				post : <span id="total"></span>
				<table id="tbl"></table>
					<input type="button" id="btnPre" value="<">
					<span id="curPage"></span>
					<input type="button" id="btnNext" value=">">
				<script id="temp" type="text/x-handlebars-template">
					<tr class="title">
						<td> NO. </td>
						<td> TITLE </td>
						<td> WRITER </td>
						<td> CONTENT </td>
					</tr>
					{{#each list}}
						<tr class="title">
							<td width=70> {{seq}} </td>
							<td width=150> {{title}} </td>
							<td width=100> {{writer}} </td>
							<td width=300> {{content}} </td>
						</tr>
					{{/each}}
				</script>
			</div>
		</div>
		<div id="footer"> <jsp:include page="../footer.jsp"/> </div>
	</div>
</body>
<script>
var page = 1;

getList();

function getList() {
	var word=$("#word").val();
	$.ajax({
		type:"get",
		url:"list.json",
		dataType:"json",
		data:{"page":page, "word":word},
		success:function(data) {
			var temp = Handlebars.compile($("#temp").html());
			 $("#tbl").html(temp(data));
			 $("#curPage").html(page + " / " + data.lastPage);
			 $("#total").html(data.count);
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
$("#word").keydown(function(key) {
	if(key.keyCode==13) {
		$("#btnSearch").click();
	}
});

$("#btnSearch").on("click", function() {
	page=1;
	getList();
});

$("#btnPre").on("click", function() {
	page--;
	getList();
});

$("#btnNext").on("click", function() {
	page++;
	getList();
});
</script>
</html>