<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 상품 목록 </title>
<link rel="stylesheet" href="../home.css">

<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
</head>
<body>
	<div id="divPage">
		<div id="divMenu"><jsp:include page="../menu.jsp"/></div>
		<!-- start -->
		<div id="divHeader"><h2> [상품 목록] </h2></div>
		<div id="divCondition">
			<div id="divSearch">
				<select id="key">
					<option value="prod_id"> CODE </option>
					<option value="prod_name"> NAME </option>
					<option value="company"> COMPANY </option>
					<option value="mall_name"> MALLNAME </option>
				</select>
				<input type="text" id="word">
				<input type="hidden" id="col" value="prod_id">
				<select id="perPage">
					<option value=3> 3post </option>
					<option value=4  selected> 4post </option>
					<option value=5> 5post </option>
				</select>
				<input type="button" id="btnSearch" value="search">
				post : <span id="cnt"></span>
			</div>
		</div>
			<table id="tbl"></table>
			<script id="temp" type="text/x-handlebars-template">
				<tr class="title">
					<td width=80> CODE </td>
					<td width=250> NAME </td>
					<td width=150> MALL_NAME </td>
					<td width=100> COMPANY </td>
					<td width=80> NORMAL_PRICE </td>
					<td width=80> SALE_PRICE </td>
					<td width=40> INFO </td>
				</tr>
				{{#each list}}
				<tr class="row">
					<td> {{prod_id}} </td>
					<td> {{prod_name}} </td>
					<td> {{mall_name}} </td>
					<td> {{company}} </td>
					<td> {{price1}} </td>
					<td> {{fmtNumber price2}} </td>
					<td> <button onClick="location.href='read?prod_id={{prod_id}}'">INFO</button> </td>
				</tr>
				{{/each}}
			</script>
			<script>
				Handlebars.registerHelper("fmtNumber", function(price){
					return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
				});
			</script>
			<div id="pagination">
				<button id="btnPre">★</button>
					<span id="curPage"></span> / <span id="lastPage"></span>
				<button id="btnNext">★</button>
			</div>
		</div>

		<!-- end -->
</body>
<script>
	var url = "list.json";
</script>
<script src="../home.js"></script>
</html>