<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="home.css">
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<title>CGV MOVIE RANK</title>
<style>
	#btnPre {background:#B4FFFF;}
	#btnNext {background:#B4FFFF;}
	#btnPre:active {background:pink;}
	#btnNext:active {background:pink;}
	#tbl {overflow:hidden; background:#B4FFFF; box-shadow:5px 5px 5px #D4D4D4;}
	.box {background:#E1FF36; box-shadow:5px 5px 2px pink; width:160px; height:300px; padding:10px; margin:10px; float:left; text-align:center;}
	.rank {background:#E1FF36; width:150px; height:15px; padding:3px; margin:3px; text-align:center;}
	.box:hover {background:#CFFF24; box-shadow:5px 5px 5px gray;}
	.titlee {font-size:13px;}
	.pricee {font-size:15px;}
	#divCondition {background:#E1FF36; box-shadow:5px 5px 5px #D4D4D4;}
	#img {box-shadow:3px 3px 3px gray; margin:10px;}
	.img {box-shadow:2px 2px 2px gray;}
	#font {font-size:40px;}
</style>
</head>
<body>
	<div id="divPage">
		<div id="divMenu"><jsp:include page="menu.jsp"/></div>
		<div id="divHeader">
			<span id="font">[ MOVIE CHART ]</span>
			TODAY's WEATHER : <span id="weather" style="width:100px"></span>
		<script id="tempWeather" type="text/x-handlebars-template">
			{{#each .}}
				<span style="height:15px" class="w">
					<span>{{part}}</span>
					<span>{{temper}}</span>
					<span>{{wa}}</span>
				</span>
			{{/each}}
		</script>
		</div>
		<!-- <div id="divCondition">
			<input type="text" id="query" value="서울 맘스터치" style="height:15px;">
			<select id="size">
				<option value="5"> 5post </option>
				<option value="10" selected> 10post </option>
				<option value="15"> 15post </option>
			</select>
			<input type="button" value="search" id="btnSearch">
			post : <span id="total"></span>
		</div>
		-->
		<div id="tbl"></div>
		<script id="temp" type="text/x-handlebars-template">
			{{#each .}}
				<div class="box">
					<div>
						<div class="rank">{{rank}}</div>
					</div>
					<div>
						<img class="img" src="{{image}}" width=150>
						<div class="titlee">{{title}}</div>
					</div>
				</div>
			{{/each}}
		</script>
	</div>
</body>
<script>

	var weather=[];
	
	getList();
	getwList();
	
	function getList() {
		$.ajax({
			type:"get",
			url:"movie.json",
			dataType:"json",
			success:function(data) {
				var temp = Handlebars.compile($("#temp").html());
				 $("#tbl").html(temp(data));
			}
		});
	}
	function getwList() {
		$.ajax({
			type:"get",
			url:"weather.json",
			dataType:"json",
			success:function(data) {
				var i = 0;
				$(data).each(function() {
					weather.push(this.part + ":" + this.temper + this.wa);
				});
				var i = 0;
				var interval = setInterval(function() {
					$("#weather").html(weather[i]);
					if(i<weather.length-1) {
						i++;
					} else {
						i=0;
					}
				}, 1000);
			}
		});
	}
</script>
<script src="home.js"></script>
</html>