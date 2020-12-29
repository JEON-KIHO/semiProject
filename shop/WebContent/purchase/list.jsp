<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 주문 목록 </title>
<link rel="stylesheet" href="../home.css">

<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<style>
	#tblInfo {margin:auto; text-align:center;}
	#tblProduct {margin:auto; text-align:center; margin-top:20px;}
</style>
</head>
<body>
	<div id="divPage">
		<div id="divMenu"><jsp:include page="../menu.jsp"/></div>
		<!-- start -->
		<div id="divHeader"><h2> [주문 목록] </h2></div>
		<div id="divCondition">
			<div id="divSearch">
				<select id="key">
					<option value="order_id"> ORDER NO. </option>
					<option value="name"> NAME </option>
					<option value="address"> ADDRESS </option>
					<option value="tel"> TEL </option>
				</select>
				<input type="text" id="word">
				<input type="hidden" id="col" value="order_id">
				<select id="perPage">
					<option value=5 selected> 5post </option>
					<option value=7> 7post </option>
					<option value=10> 10post </option>
				</select>
				<input type="button" id="btnSearch" value="search">
				post : <span id="cnt"></span>
			</div>
		</div>
			<table id="tbl"></table>
			<script id="temp" type="text/x-handlebars-template">
				<tr class="title">
					<td width=80> CODE </td>
					<td width=100> NAME </td>
					<td width=250> ADDRESS </td>
					<td width=100> TEL </td>
					<td width=40> INFO </td>
				</tr>
				{{#each list}}
				<tr class="row">
					<td class="order_id">{{order_id}}</td>
					<td> {{name}} </td>
					<td> {{address}} </td>
					<td> {{tel}} </td>
					<td><button>INFO</button></td>
				</tr>
				{{/each}}
			</script>
			<div id="pagination">
				<button id="btnPre">★</button>
					<span id="curPage"></span> / <span id="lastPage"></span>
				<button id="btnNext">★</button>
			</div>
		
		<div id="divInfo">
			<table id="tblInfo"></table>
			<script id="tempInfo" type="text/x-handlebars-template">
				<tr>
					<td class="title" width=50>NAME</td>
					<td width=100>{{name}}</td>
					<td class="title" width=50>TEL</td>
					<td width=100>{{tel}}</td>
					<td class="title" width=50>E-MAIL</td>
					<td width=100>{{email}}</td>
					<td class="title" width=50>DATE</td>
					<td width=100>{{pdate}}</td>
				</tr>
				<tr>
					<td class="title" width=50>ADDR</td>
					<td width=250 colspan=3>{{address}}</td>
					<td class="title" width=50>PAY</td>
					<td width=100>{{payType}}</td>
					<td class="title" width=50>STATUS</td>
					<td width=100>{{status}}</td>
				</tr>
			</script>
		</div>
		<div id="divProduct">
			<table id="tblProduct"></table>
			<script id="tempProduct" type="text/x-handlebars-template">
				<tr class="title">
					<td width=80> CODE </td>
					<td width=250> NAME </td>
					<td width=100> PRICE </td>
					<td width=100> QUANTITY </td>
					<td width=100> TOTALPRICE </td>
				</tr>
				{{#each list}}
				<tr class="row">
					<td> {{prod_id}} </td>
					<td> {{prod_name}} </td>
					<td> {{fmtNumber price}} </td>
					<td> {{quantity}} </td>
					<td> {{fmtNumber sum}} </td>
				</tr>
				{{/each}}
			</script>
			<script>
				Handlebars.registerHelper("fmtNumber", function(price){
					return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
				});
				
				Handlebars.registerHelper("fmtNumber", function(sum){
					return sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
				});
			</script>
		</div>
		<!-- end -->
		</div>
</body>
<script>
	var url = "list.json";
	var order_id;
	
	$("#tbl").on("click", ".row button", function() {
		order_id = $(this).parent().parent().find(".order_id").html();
		getInfo();
		getProduct();
	});
	
	function getInfo() {
		$.ajax({
			type:"get",
			url:"read.json",
			dataType:"json",
			data:{"order_id":order_id},
			success:function(data) {
				var temp = Handlebars.compile($("#tempInfo").html());
				 $("#tblInfo").html(temp(data));
			}
		});
	}
	function getProduct() {
		$.ajax({
			type:"get",
			url:"plist.json",
			dataType:"json",
			data:{"order_id":order_id},
			success:function(data) {
				var temp = Handlebars.compile($("#tempProduct").html());
				 $("#tblProduct").html(temp(data));
			}
		});
	}
</script>
<script src="../home.js"></script>
</html>