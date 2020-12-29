<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<meta charset="UTF-8">
<title> 상 품 조 회 </title>
<style>
	h1 {font-size:25px; text-align:center;}
	#container {background:#BDFF12; width:680px; padding:5px; margin:5px; overflow:hidden; margin:0px auto;}
	.box {background:#C8FFFF; height:200px; width:150px; padding:5px; margin:5px; float:left;}
	.box:hover {background:#4D00ED; color:white;}
	#search {background:#C8FFFF; width:680px; overflow:hidden; padding:5px; margin:5px; margin:0px auto;}
	#pagination {float:right;}
</style>
</head>
<body>
	<h1> [상 품 조 회] </h1>
	<div id="search">
		<form name="form">
			<input type="text" id="word" size=15>
			<input type="button" value="search" id="btnSearch">
			<input type="radio" name="order" value="desc"> 높은가격순
			<input type="radio" name="order" value="asc"> 낮은가격순
			
			<div id="pagination">
			<span id="total"></span>
				<input type="button" value="←" id="btnPre">
				<span id="curPage"></span>
				<input type="button" value="→" id="btnNext">
			</div>
		</form>
	</div>
	<div id="container"></div>
		<script id="temp" type="text/x-handlebars-template">
			{{#each list}}
				<div class="box">
					<img src="/product/img/{{image}}" width=150>
					<div>[{{rn}}] {{code}}</div>
					<div>{{pname}}</div>
					<div>{{price}}</div>
				</div>
			{{/each}}
		</script>
</body>
<script>
	var page=1;
	var word=$("#word").val();
	var order=$("input[name=order]:checked").val();
	
	getList();
	
	function getList() {
		$.ajax({
			type:"get",
			url:"list.json",
			dataType:"json",
			data:{"page":page, "word":word, "order":order},
			success:function(data) {
				var temp = Handlebars.compile($("#temp").html());
				 $("#container").html(temp(data));
				 $("#total").html("post : " + data.total);
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
	
	$(form.order).change(function() {
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
	
	$("#btnSearch").on("click", function() {
		page=1;
		getList();
	});
</script>
</html>