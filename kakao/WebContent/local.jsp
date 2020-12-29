<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="home.css">
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5ce0232a77c703831f4d0a375cece966"></script>
<title> search local</title>
<style>
	#btnPre:active {background:pink;}
	#btnNext:active {background:pink;}
	#map {margin:auto;}
</style>
</head>
<body>
	<div id="divPage">
		<div id="divMenu"><jsp:include page="menu.jsp"/></div>
		<div id="divHeader"><h2>[ SEARCH LOCAL ]</h2></div>
		<div id="divCondition">
			<input type="text" id="query" value="서울 맘스터치" style="height:15px;">
			<select id="size">
				<option value="5"> 5post </option>
				<option value="10" selected> 10post </option>
				<option value="15"> 15post </option>
			</select>
			<input type="button" value="search" id="btnSearch">
			post : <span id="total"></span>
		</div>
		<table id="tbl"></table>
		<script id="temp" type="text/x-handlebars-template">
			<tr class="title">
				<td width=150> NAME </td>
				<td width=250> ADDR </td>
				<td width=100> TEL </td>
				<td width=1> MAP </td>
			</tr>
			{{#each documents}}
				<tr class="row">
					<td class="name">{{place_name}}</td>
					<td class="address">{{road_address_name}}</td>
					<td class="tel">{{phone}}</td>
					<td><button class="map" x={{x}} y={{y}}>MAP</button></td>
				</tr>
			{{/each}}
		</script>
		<div id="pagination" style="margin-bottom:20px;">
			<button id="btnPre">★</button>
				<span id="curPage"></span>
			<button id="btnNext">★</button>
		</div>
		<div id="map" style="width:800px;height:400px;"></div>
	</div>
</body>
<script>
	var url = "https://dapi.kakao.com/v2/local/search/keyword.json";
	
	$("#tbl").on("click", ".row .map", function() {
		var x = $(this).attr("x");
		var y = $(this).attr("y");
		var name = $(this).parent().parent().find(".name").html();
		var address = $(this).parent().parent().find(".address").html();
		var tel = $(this).parent().parent().find(".tel").html();
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(y, x),
			level: 3
		};

		var mapContainer = document.getElementById('map'), // 지도의 중심좌표
	    mapOption = { 
	        center: new kakao.maps.LatLng(y, x), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    }; 

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

	// 지도에 마커를 표시합니다 
	var marker = new kakao.maps.Marker({
	    map: map, 
	    position: new kakao.maps.LatLng(y, x)
	});
	var str ="<div style='padding:5px;font-size:12px;'>";
    str += name + "<br>" + tel;
    str +="</div>";
    var info=new kakao.maps.InfoWindow({ content:str });

    kakao.maps.event.addListener(marker, "mouseover", function(){info.open(map, marker); });
    kakao.maps.event.addListener(marker, "mouseout", function(){info.close(map, marker); });
	
	});
</script>
<script src="home.js"> </script>
</html>