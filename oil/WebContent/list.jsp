<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<meta charset="UTF-8">
<title> oil list </title>
<style>
	body {overflow:hidden;}
	#s {overflow:hidden; width:700px; margin-bottom:5px;}
	#pagination {float:right;}
	#tbl {border-collapse:collapse;}
	td {border:solid 1px pink;}
	#list {width:700px; margin-top:5px;}
	#insert {float:right;}
</style>
</head>
<body>
	<h1> list </h1>
	<div id="s">
		<input type="text" id="word" size=8>
		<input type="button" value="search" id="btnSearch">
		post : <span id="count"></span>
			<div id="pagination">
				<input type="hidden" name="page">
				<input type="button" value="♥" id="btnPre">
				<span id="curPage"></span>
				<input type="button" value="♥" id="btnNext">
			</div>
	</div>
	<table id="tbl"></table>
	<script id="temp" type="text/x-handlebars-template">
		<tr class="title">
			<td width=150> NAME </td>
			<td width=300> ADDRESS </td>
			<td width=100> PRICE </td>
			<td width=50> SELF </td>
			<td width=100> UPDATE </td>

		</tr>
		{{#each list}}
			<tr class="row">
				<td width=150>{{name}}</td>
				<td width=300>{{address}}</td>
				<td width=100>{{price}}</td>
				<td width=50>{{self}}</td>
				<td width=100>{{wdate}}</td>
			</tr>
		{{/each}}
	</script>
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
			data:{"word":word, "page":page},
			success:function(data) {
				var temp = Handlebars.compile($("#temp").html());
				 $("#tbl").html(temp(data));
				 $("#count").html(data.count);
				 $("#curPage").html(page +"/"+ data.lastPage);
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