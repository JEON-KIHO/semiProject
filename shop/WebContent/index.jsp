<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> HOME </title>
<link rel="stylesheet" href="home.css">

<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
   <style>
      .box{width:200px;float:left;text-align:center; margin:auto; margin:10px 0px 10px 0px;}
       .price1, .prod_id, .prod_name{font-size:12px; width:200px; text-overflow:ellipsis; white-space:nowrap; overflow:hidden;}
       .image img{width:150px; height:120px; margin:auto; margin-bottom:5px;}
       #tbl {background:#73EDFF; box-shadow:10px 10px 10px pink;}
       #divCondition {background:#73EDFF; box-shadow:10px 10px 10px pink;}
       .cart0 {background:#E0FFDB; cursor:pointer; width:30px; margin:auto; margin-top:5px; border:1px solid black; box-shadow:2px 2px 2px gray;}
       .cart1 {background:gray; cursor:wait; color:white; width:80px; margin:auto; margin-top:5px; border:1px solid black; box-shadow:2px 2px 2px gray;}
       .cart0:active {background:pink;}
       #btnPre:active {background:pink;}
       #btnNext:active {background:pink;}
   </style>
</head>
<body>
	<div id="divPage">
		<div id="divMenu"><jsp:include page="menu.jsp"/></div>
		<!-- start -->
		<div id="divHeader"><h2> [HOME] </h2></div>
		<div id="divCondition">
			<div id="divSearch">
				<select id="key">
					<option value="prod_id"> CODE </option>
					<option value="prod_name"> NAME </option>
					<option value="company"> COMPANY </option>
					<option value="mall_name"> MALLNAME </option>
				</select>
				<input type="text" id="word">
				<select id="perPage">
					<option value=4> 4post </option>
					<option value=8  selected> 8post </option>
					<option value=12> 12post </option>
				</select>
				<input type="button" id="btnSearch" value="search">
				post : <span id="cnt"></span>
			</div>
		</div>
			<div id="tbl"></div>
			<script id="temp" type="text/x-handlebars-template">
				{{#each list}}
				<div class="box">
					<div class="image">
						<img src="{{printImg image}}">
					</div>
					<div class="prod_id">{{prod_id}}</div>
					<div class="prod_name">{{prod_name}}</div>
					<div class="price1">{{price1}}＄</div>
					<div class="cart{{prod_del}}">{{printDel prod_del}}</div>
				</div>
				{{/each}}
			</script>
			<script>
				Handlebars.registerHelper("fmtNumber", function(price){
					return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
				});
				
				Handlebars.registerHelper("printImg", function(image){
					if(image) { return "/shop/img/" +image; }
					else { return "http://placehold.it/150x120"; }
				});
				
				Handlebars.registerHelper("printDel", function(prod_del){
					if(prod_del=="0") return "cart";
					else return "roading...";
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
	var url = "/shop/product/list.json";
	
	$("#tbl").on("click", ".box .cart0", function() {
		var prod_id = $(this).parent().find(".prod_id").html();
		if(!confirm(prod_id + " in cart?")) return;
		$.ajax({
			type:"post",
			url:"/shop/cart/insert",
			data:{"prod_id":prod_id},
			success:function() {
				if(!confirm("go to the cart?")) return;
				location.href="/shop/cart/list.jsp";
			}
		});
	});
</script>
<script src="home.js"></script>
</html>