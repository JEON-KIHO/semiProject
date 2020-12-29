<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="user-scalable=no, width=device-width" charset="euc-kr"/>
<link href="home_mobile.css" rel="stylesheet" type="text/css" media="screen and (max-width:799px)">
<link href="home.css" rel="stylesheet" type="text/css" media="screen and (min-width:800px)">
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5ce0232a77c703831f4d0a375cece966"></script>
<title> search book</title>
<style>
	#btnPre {background:#B4FFFF;}
	#btnNext {background:#B4FFFF;}
	#btnPre:active {background:pink;}
	#btnNext:active {background:pink;}
	#tbl {overflow:hidden;}
	.box {background:#E1FF36; box-shadow:5px 5px 2px pink; width:160px; height:250px; padding:10px; margin:10px; float:left; text-align:center;}
	.box:hover {background:#CFFF24; box-shadow:5px 5px 5px gray;}
	.titlee {font-size:13px; text-overflow:ellipsis; overflow:hidden; white-space:nowrap;}
	.pricee {font-size:15px;}
	#divCondition {background:#E1FF36; box-shadow:5px 5px 5px #D4D4D4;}
	#tbl {background:#B4FFFF; box-shadow:5px 5px 5px #D4D4D4;}
	#img {box-shadow:3px 3px 3px gray; margin:10px;}
	.img {box-shadow:2px 2px 2px gray;}
		#darken-background {
            position:absolute;
            top:0px; 
            left:0px;
            right:0px;
            height:100%;
            display:none;
            background:rgba(0, 0, 0, 0.5);
            z-index:10000;
            overflow-y:scroll;
        }

		#lightbox {
            width:700px;
            margin:20px auto;
            padding:15px;
            border: 1px solid #333333;
            border-radius:5px;
            background:white;
            box-shadow:0px 5px 5px rgba(34, 25, 25, 0.4);
            text-align:center;
        }
</style>
</head>
<body>
	<div id="divPage">
		<div id="divMenu"><jsp:include page="menu.jsp"/></div>
		<div id="divHeader"><h2>[ SEARCH LOCAL ]</h2></div>
		<div id="divCondition">
			<input type="text" id="query" value="안드로이드" style="height:15px;">
			<select id="size">
				<option value="4" selected> 4post </option>
				<option value="8"> 8post </option>
				<option value="12"> 12post </option>
			</select>
			<input type="button" value="search" id="btnSearch">
			post : <span id="total"></span>
		</div>
		<div id="tbl"></div>
		<script id="temp" type="text/x-handlebars-template">
			{{#each documents}}
				<div class="box">
					<img class="img" src="{{print thumbnail}}" width=150 contents="{{contents}}">
					<div class="titlee">{{title}}</div>
					<div class="pricee"><b>{{fmtNumber price}}＄</b></div>
				</div>
			{{/each}}
		</script>
		<script>
			Handlebars.registerHelper("print", function(image) {
				if(image) { return image; }
			    else { return "http://placehold.it/150x220"; }
			});
			Handlebars.registerHelper("fmtNumber", function(price){
				return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
			});
		</script>
		<div id="pagination">
			<button id="btnPre">★</button>
				<span id="curPage"></span>
			<button id="btnNext">★</button>
		</div>
		
		<div id="darken-background">
			<div id="lightbox">
				<img id="img" src="http://placehold.it/300x400" width=300>
				<div id="contents"></div>
				<div><button id="btnClose">close</button></div>
			</div>
		</div>
	</div>
</body>
<script>
	var url="https://dapi.kakao.com/v3/search/book?target=title";
	
	$("#tbl").on("click", ".box .img", function() {
		$("#darken-background").show();
		var src=$(this).attr("src");
		var contents = $(this).attr("contents");
		$("#img").attr("src", src);
		$("#contents").html(contents);
	});
	$("#btnClose").on("click", function() {
		$("#darken-background").hide();
	});
</script>
<script src="home.js"></script>
</html>