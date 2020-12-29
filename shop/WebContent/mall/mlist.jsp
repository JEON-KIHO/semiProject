<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 업체 목록 </title>
<link rel="stylesheet" href="../home.css">

<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
</head>
<body>
	<div id="divPage" style="width:350px;">
		<!-- start -->
		<div id="divCondition" style="visibility:hidden;">
			<div id="divSearch">
				<select id="key">
					<option value="mall_id"> CODE </option>
					<option value="mall_name"> NAME </option>
					<option value="tel"> TEL </option>
					<option value="address"> ADDR </option>
				</select>
				<input type="text" id="word">
				<select id="perPage">
					<option value=3> 3post </option>
					<option value=5 selected> 5post </option>
					<option value=10> 10post </option>
				</select>
				<input type="button" id="btnSearch" value="search">
				post : <span id="cnt"></span>
			</div>
		</div>
		<div id="divHeader"><h2> [업체 목록] </h2></div>
			<table id="tbl" style="width:300px;"></table>
			<script id="temp" type="text/x-handlebars-template">
				<tr class="title">
					<td width=80> CODE </td>
					<td width=150> NAME </td>
					<td width=50> INFO </td>
				</tr>
				{{#each list}}
				<tr class="row">
					<td class="mall_id">{{mall_id}}</td>
					<td class="mall_name">{{mall_name}}</td>
					<td> <input type="button" value="info"> </td>
				</tr>
				{{/each}}
			</script>
			<div id="pagination" style="width:300px;">
				<button id="btnPre" style="active:pink;">★</button>
					<span id="curPage"></span> / <span id="lastPage"></span>
				<button id="btnNext" style="active:pink;">★</button>
			</div>
		</div>

		<!-- end -->
</body>
<script>
	var url = "list.json";
	
	$("#tbl").on("click", ".row", function() {
		var mall_id = $(this).find(".mall_id").html();
		var mall_name = $(this).find(".mall_name").html();
		$(opener.form.mall_id).val(mall_id);
		$(opener.form.mall_name).val(mall_name);
		window.close();
	});
</script>
<script src="../home.js"></script>
</html>