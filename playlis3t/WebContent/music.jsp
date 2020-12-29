<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="home.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"
	type="text/javascript"></script>
<link href="home_mobile.css" rel="stylesheet" type="text/css"
	media="screen and (max-width:799px)">
<style>
.box {
border-top-left-radius:10px;
border-top-right-radius:10px;
margin-right:-4;
border-bottom-left-radius:10px;
border-bottom-right-radius:10px;
margin-left:-3;
	width: 120px;
	height: 180px;
	border: 1px solid #C0E96A;
	margin: 3px;
	float: left;
	text-align: "center";
	
}

.box1 {

	border: 1px solid #C0E96A;
	margin: 3px;
	align: center;
	margin-top: 30px;
	text-align: "center";
}

.box img {
	margin: 10px auto;
}

.box1 img {
	margin: 10px;
	float: center;
}

.title {
	font-size: 12;
	text-align: center;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

.album {
	border: 3px solid #C0E96A;
}
</style>
</head>
<body>
	<div id="divPage">
		<div id="divTop"><jsp:include page="header.jsp"></jsp:include>
			<div class="searchbox">
				<div class="header"></div>
			</div>
			<div id="divMenu"><jsp:include page="menu.jsp" />

				<div class="item"></div>


			</div>
		</div>

		<div id="content"></div>
	</div>


	<div id="divHeader"></div>

	<div id="pagination" style="margin-top: 30px; text-align: left;">
		<span style="font-size: 18px; padding: 0px 0px 0px 100px;">
		<최신 음악></span>
		
		<span style="font-size: 18px; float: right; padding: 0px 120px;">TOP 100></span>
	</div>

	<div id="tbl" style="margin-top: 20px;"></div>

	<script id="temp" type="text/x-handlebars-template">
{{#each .}}
<div class="box" style="border:3px solid #C0E96A;">
<div class="rank">{{rank}}</div>
<div class="start">{{start}}</div>
<img src="{{image}}" width=80>
<div class="singer" style="text-align: center">{{singer}}</div>
<div>{{othersinger}}</div>
</div>
{{/each}}
</script>

	<table id="tbl1" style="text-align: center; border: 3px solid #C0E96A;"></table>
	<script id="temp1" type="text/x-handlebars-template">
{{#each .}}
<tr class="box1" style="border:4px solid #C0E96A;">
	<td width=10><img src="{{image}}" width=36></td>
	<td class="album"  width=120>{{album}}</td>
	<td class="artist" width=80>{{artist}}</td>
</tr>
{{/each}}
</script>






	</div>
	<div id="footer" style="text-align: center;">
		<jsp:include page="footer.jsp" />
	</div>
</body>

<script>


var page=1; 
	getList();
	getChart();

	  
	
	   function getChart(){
			$.ajax({
				type:"get",
				url:"chart.json",
				dataType:"json",
				success:function(data){
					var temp=Handlebars.compile($("#temp1").html());
					 $("#tbl1").html(temp(data));

				}
			});
		}

	function getList() {
		var query=$("#query").val();
		var size=$("#size").val();
		var page =1;
	
		  
		$.ajax({
			type : "get",
			url : "playlist.json",
			dataType : "json",
			data:{"query":query,"size":size,"page":page},
			success : function(data) {
				var temp = Handlebars.compile($("#temp").html());
				$("#tbl").html(temp(data));
				
	               
	               
			}
		});
		
	}
	 $("#bpre").on("click",function(){
		   page--;
		   getList();
	   });
	   $("#bnext").on("click",function(){
		   page++;
		   getList();
	   });
</script>

</html>