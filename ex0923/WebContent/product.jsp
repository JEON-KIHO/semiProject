<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<meta charset="UTF-8">
<title> 목록 </title>
<style>
	h1 {font-size:25px; text-align:center;}
	#divSearch {background:#7EFFFF; width:680px; padding:5px; margin:5px; margin:0px auto; overflow:hidden;  margin-bottom:5px;}
	#pagination {float:right;}
	#container {background:#7EFFFF; width:680px; padding:5px; margin:5px; margin:0px auto; overflow:hidden; margin-bottom:5px;}
	.box {background:#F3FF48; padding:5px; margin:5px; float:left; width:150px; height:200px; border-radius:10px 10px 10px 10px;}
	.box:hover {background:#FFD6FF; box-shadow:5px 5px 15px;}
	.box:active {background:#5B5AFF; color:white; box-shadow:5px 5px 15px;}
	#curdBox {overflow:hidden; background:#7EFFFF; width:680px; padding:5px; margin:5px; margin:0px auto;}
	#curd {float:right;}
</style>
</head>
<body>
	<h1> [목록] </h1>
	<div id="divSearch">
		<input type="text" id="word" size=15>
		<select id="num">
			<option value="4"> 4post </option>
			<option value="8" selected> 8post </option>
			<option value="12"> 12post </option>
		</select>
		<input type="button" value="search" id="btnSearch">
		
		<select id="order">
			<option value="desc"> 높은가격순 </option>
			<option value="asc" selected> 낮은가격순 </option>
		</select>
		
		<div id="pagination">
			post : <span id="total"></span>
			<input type="button" value="◆" id="btnPre">
			<span id="curPage"></span>
			<input type="button" value="◆" id="btnNext">
		</div>
	</div>
	<div>
	<div id="container"></div>
		<script id="temp" type="text/x-handlebars-template">
			{{#each list}}
				<div class="box" onClick="location.href='read.jsp'">
					<img src="{{srcImage image}}" width=150>
					<div><b>[{{rn}}]</b> {{code}}</div>
					<div>{{pname}}</div>
					<div>{{printPrice price}}＄</div>
				</div>
			{{/each}}
		</script>
		<script>
			Handlebars.registerHelper("srcImage", function(image) {
				var src;
				if(!image) {
					src = "http://placehold.it/150x110";
				} else {
					src = "/product/img/" + image;
				}
				return src;
			});
			
			Handlebars.registerHelper("printPrice", function(price) {
				return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
			});
			
		</script>
		
		<div id="curdBox">
			<div id="curd">
				<input type="button" value="insert" onClick="location.href='insert.html'">
			</div>
		</div>
	</div>
</body>
<script>
	var page=1;
	
	getList();
	
	
	
	function getList() {
		var word=$("#word").val();
		var order=$("#order option:selected").val();
		var num=$("#num option:selected").val();
		$.ajax({
			type:"get",
			url:"list.json",
			dataType:"json",
			data:{"page":page, "word":word, "order":order, "num":num},
			success:function(data) {
				var temp = Handlebars.compile($("#temp").html());
				 $("#container").html(temp(data));
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
	
	$("#num, #order").change(function() {
		page=1;
		getList();
	});
</script>
</html>