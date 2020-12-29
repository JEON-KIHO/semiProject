<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../home.css">
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<title> CART </title>
<style>
	.row {text-align:center;}
	input[type=number]{width:50px;}
	#tblProduct {margin:auto;} 
	#tblPurchase {margin:auto; margin-top:20px;} 
	#btnOrder:active {background:pink;}
    #btnReset:active {background:pink;}
</style>
</head>
<body>
	<div id="divPage">
		<div id="divMenu"><jsp:include page="../menu.jsp"/></div>
		<!-- start -->
		<div id="divHeader"><h2> [CART] </h2></div>
		<c:if test="${listCart.size()>0 }">
		<div style="width:900px; margin:auto; margin-bottom:10px; ">
			<button id="btnChkDelete">chkDelete</button>
		</div>
			<table id="tbl" style="width:900px">
				<tr class="title">
					<td width=40><input type="checkbox" id="chkAll"></td>
					<td width=100>CODE</td>
					<td width=250>NAME</td>
					<td width=110>QUANTITY</td>
					<td width=110>QUANTITY</td>
					<td width=100>PRICE</td>
					<td width=100>TOTALPRICE</td>
					<td width=50>DELETE</td>
				</tr>
				<c:forEach items="${listCart }" var="vo">
					<tr class="row" id="${vo.prod_id }" pname="${vo.prod_name }" quantity="${vo.quantity }" price="${vo.price1 }" sum="${vo.sum }">
						<td><input type="checkbox" class="chk"></td>
						<td class="prod_id">${vo.prod_id }</td>
						<td>${vo.prod_name }</td>
						<td>
							<button class="btnDown">－</button>
							<input type="text" value="${vo.quantity }" size=1 class="quantity" style="height:15px">
							<button class="btnUp">＋</button>
						</td>
						<td>
							<input type="number" value="${vo.quantity }" size=1 class="quantity1" min=1 style="height:15px">
						</td>
						<td><fmt:formatNumber value="${vo.price1 }" pattern="#,###" />＄</td>
						<td><fmt:formatNumber value="${vo.sum }" pattern="#,###" />＄</td>
						<td>
							<button class="btnDelete">delete</button>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div id="pagination">
			<button id="btnChoiceOrder">choice order</button>
			<button id="btnOrderAll">order All</button>
		</div>
		</c:if>
		<c:if test="${listCart==null || listCart.size()==0 }">
			<table id="tbl">
				<tr class="row" style="height:500px"> <td><h1>empty CART</h1></td> </tr>			
			</table>
		</c:if>
		
		<!-- [주문정보 출력] -->
		<div id="divOrderInfo">
			<div id="divHeader"><h2>[ORDER INFO]</h2></div>
			<table id="tblProduct"></table>
			<script id="tempProduct" type="text/x-handlebars-template">
				<tr class="title">
					<td width=100> CODE </td>
					<td width=200> NAME </td>
					<td width=250> QUANTITY </td>
					<td width=100> PRICE </td>
					<td width=100> TOTAL </td>
				</tr>
				{{#each .}}
					<tr class="row" prod_id="{{prod_id}}" price="{{price}}" quantity="{{quantity}}">
						<td>{{prod_id}}</td>
						<td>{{prod_name}}</td>
						<td>{{fmtNumber quantity}}</td>
						<td>{{fmtNumber price}}＄</td>
						<td>{{fmtNumber sum}}＄</td>
					</tr>
				{{/each}}
				<tr>
					<td colspan=4 class="title">TOTALPRICE</td>
					<td id="total" style="text-align:center;"></td>
				</tr>
			</script>
			<script>
				Handlebars.registerHelper("fmtNumber", function(price){
					return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
				});
			</script>
			<div id="divHeader"><h2>[PURCHASE INFO]</h2></div>
			<form id="form" method="post">
				<table id="tblPurchase">
					<tr>
						<td class="title"> NAME </td>
						<td> <input type="text" name="name" size=60 value="에베베"> </td>
					</tr>
					<tr>
						<td class="title"> ADDRESS </td>
						<td> <input type="text" name="address" size=60> </td>
					</tr>
					<tr>
						<td class="title"> TEL </td>
						<td> <input type="text" name="tel" size=60 value="010-1234-5678"> </td>
					</tr>
					<tr>
						<td class="title"> EMAIL </td>
						<td> <input type="text" name="email" size=60 value="dpqpqp123@naver.com"> </td>
					</tr>
					<tr>
						<td class="title"> PAYTYPE </td>
						<td>
							<input type="radio" name="payType" value="0"> 무통장
							<input type="radio" name="payType" value="1" checked> 카드
						</td>
					</tr>
				</table>
				<div id="pagination">
					<input type="submit" value="order" id="btnOrder">
					<input type="reset" value="cancel" id="btnReset">
				</div>
			</form>
			
		</div>
		<!-- end -->
	</div>

</body>
<script>
	$("#divOrderInfo").hide();
	$("#tbl .row .chk").each(function() {
		$(this).click();
	});
	$("#tbl #chkAll").click();
	
	// 주소창 클릭할 때 open window
	$(form.address).click(function(){
        new daum.Postcode({
            oncomplete: function(data) {
                $(form.address).val(data.address);
            }
        }).open();
   });
	
	// order button click
	$(form).submit(function(e) {
		e.preventDefault();
		if(!confirm("choose items order???")) return;
		var name=$(form.name).val();
		var address=$(form.address).val();
		var tel=$(form.tel).val();
		var email=$(form.email).val();
		var payType=$("input[name='payType']:checked").val();
		$.ajax({
			type:"post",
			url:"/shop/purchase/insert",
			data:{"name":name, "address":address, "tel":tel, "email":email, "payType":payType},
			success:function(data) {
				var order_id = data.trim();
				$("#tblProduct .row").each(function() {
					var prod_id = $(this).attr("prod_id");
					var price = $(this).attr("price");
					var quantity = $(this).attr("quantity");
				$.ajax({
					type:"post",
					url:"/shop/orders/insert",
					data:{"order_id":order_id, "prod_id":prod_id, "price":price, "quantity":quantity},
					sunccess:function() {
					}
				});
			});
			}
		});
		alert("order success");
		location.href="/shop/index.jsp";
	});
	
	// 전체상품 주문
	$("#btnOrderAll").on("click", function() {
		$("#divOrderInfo").show();
		var total=0;
		var data=[];
		$("#tbl .row").each(function() {
			var prod_id = $(this).attr("id");
			var prod_name = $(this).attr("pname");
			var quantity = $(this).attr("quantity");
			var price = $(this).attr("price");
			var sum = $(this).attr("sum");
			total += parseInt(sum);
			var row={"prod_id":prod_id, "prod_name":prod_name, "quantity":quantity, "price":price, "sum":sum};
			data.push(row);
		});
		var temp = Handlebars.compile($("#tempProduct").html());
		 $("#tblProduct").html(temp(data));
		 $("#total").html(total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "＄");
	});
	
	// 선택상품 주문
	$("#btnChoiceOrder").on("click", function() {
		$("#divOrderInfo").show();
		var total=0;
		var data=[];
		
		if($("#tbl .row .chk:checked").length==0) {
			alert("none checked item");
			$("#divOrderInfo").hide();
			return;
		}
		$("#tbl .row .chk:checked").each(function() {
			var row1 = $(this).parent().parent();
			var prod_id = $(row1).attr("id");
			var prod_name = $(row1).attr("pname");
			var quantity = $(row1).attr("quantity");
			var price = $(row1).attr("price");
			var sum = $(row1).attr("sum");
			total += parseInt(sum);
			var row={"prod_id":prod_id, "prod_name":prod_name, "quantity":quantity, "price":price, "sum":sum};
			data.push(row);
		});
		var temp = Handlebars.compile($("#tempProduct").html());
		 $("#tblProduct").html(temp(data));
		 $("#total").html(total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "＄");
	});

	
	$("#tbl").on("click", ".row .btnDown", function() {
		var row = $(this).parent().parent();
		var quantity = $(row).find(".quantity").val();
		var prod_id = $(row).find(".prod_id").html();
		if(quantity!=1) {
			quantity--;
			$(row).find(".quantity").val(quantity);
		}
			$.ajax({
				type:"post",
				url:"/shop/cart/update",
				data:{"quantity":quantity, "prod_id":prod_id},
				success:function() {
					location.href="list.jsp";
				}
			});
	});
	$("#tbl").on("click", ".row .btnUp", function() {
		var row = $(this).parent().parent();
		var quantity = $(row).find(".quantity").val();
		var prod_id = $(row).find(".prod_id").html();
		quantity++;
		$(row).find(".quantity").val(quantity);
		$.ajax({
			type:"post",
			url:"/shop/cart/update",
			data:{"quantity":quantity, "prod_id":prod_id},
			success:function() {
				location.href="list.jsp";
			}
		});
	});
	$("#tbl").on("change", ".row .quantity1", function() {
		var row = $(this).parent().parent();
		var quantity = $(row).find(".quantity1").val();
		var prod_id = $(row).find(".prod_id").html();
		$(row).find(".quantity1").val(quantity);
		$.ajax({
			type:"post",
			url:"/shop/cart/update",
			data:{"quantity":quantity, "prod_id":prod_id},
			success:function() {
				location.href="list.jsp";
			}
		});
	});
	
	// btn delete
	$("#tbl").on("click", ".row .btnDelete", function() {
		var row = $(this).parent().parent();
		var prod_id = $(row).find(".prod_id").html();
		if(!confirm("delete "+prod_id+" ?")) return;
		$.ajax({
			type:"post",
			url:"/shop/cart/delete",
			data:{"prod_id":prod_id},
			success:function() {
				location.href="list.jsp";
			}
		});
	});
	
	// chk delete
	$("#btnChkDelete").on("click", function() {
		if(!confirm("chkdelete?")) return;
		$("#tbl .row .chk:checked").each(function() {
			var prod_id = $(this).parent().parent().find(".prod_id").html();
			$.ajax({
				type:"post",
				url:"/shop/cart/delete",
				data:{"prod_id":prod_id},
				success:function() {
				}
			});
		});
		location.href="list.jsp";
	});
	
	// click chkAll
	$("#tbl").on("click", "#chkAll",  function(){
	    if($(this).is(":checked")){
	        $("#tbl .row .chk").each(function(){
	            $(this).prop("checked", true);
	         });
	    }else{
	        $("#tbl .row .chk").each(function(){
	            $(this).prop("checked", false);
	        });
	    }
	});

	// click chk
	$("#tbl").on("click", ".row .chk", function(){
	    var isCheck=true
	    $("#tbl .row .chk").each(function(){
	        if(!$(this).is(":checked")) isCheck=false
	    });
	    if(isCheck){
	        $("#chkAll").prop("checked", true);
	    }else{
	        $("#chkAll").prop("checked", false);
	    }
	});
</script>
</html>