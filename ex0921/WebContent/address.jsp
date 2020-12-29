<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<meta charset="UTF-8">
<title> 주 소 관 리 </title>
<style>
	* {text-align:center; margin:0px auto;}
	table {border-collapse:collapse; background:gray; color:white;}
	td {border:solid 1px pink;}
	hr {margin:20px 0px 20px 0px;}
	h1 {font-size:25px;}
		.row:hover {background:#BDFF12;}
</style>
</head>
<body>
	<h1> [주 소 관 리] </h1>
	<table>
		<tr>
			<td class="title" width=100> NAME </td>
			<td width=320> <input type="text" id="txtName" size=7> </td>
		</tr>
		<tr>
			<td class="title" width=100> TEL </td>
			<td width=320> <input type="text" id="txtTel" size=15> </td>
		</tr>
		<tr>
			<td class="title" width=100> ADDRESS </td>
			<td width=320> <input type="text" id="txtAddress" size=40> </td>
		</tr>
	</table>
	<button id="btnInsert"> save </button>
	
	<hr>
	
	<h1> [주 소 목 록] </h1>
	total data : <span id="total"> </span>
	<table id="tbl"></table>
	<script id="temp" type="text/x-handlebars-template">
		<tr class="title">
			<td width=50> NO. </td>
			<td width=80> NAME </td>
			<td width=150> TEL </td>
			<td width=300> ADDRESS </td>
			<td width=120> delete / update </td>
		</tr>
		{{#each list}}
			<tr class="row" seq="{{seq}}">
				<td width=50>{{rn}}</td>
				<td width=80><input type="text" value="{{name}}" class="name" size=7></td>
				<td width=150><input type="text" value="{{tel}}" class="tel" size=15></td>
				<td width=300><input type="text" value="{{address}}" class="address" size=30></td>
				<td>
					<button class="btnUpdate"> update </button>
					<button class="btnDelete"> delete </button>
				</td>
			</tr>
		{{/each}}
	</script>
	<div id="pagination">
		<button id="btnPre"> ← </button>
		<span id="page"></span>
		<button id="btnNext"> → </button>
	</div>
</body>
<script>
	var page = 1;
	var lastPage = 0;
	getList();
	
	// update
	$("#tbl").on("click", ".row .btnUpdate", function() {
		var row=$(this).parent().parent();
		var seq=row.attr("seq");
		var name=row.find(".name").val();
		var tel=row.find(".tel").val();
		var address=row.find(".address").val();
			if(!confirm("update?")) return;
			$.ajax({
				type:"post",
				url:"update",
				data:{"seq":seq, "name":name, "tel":tel, "address":address},
				success:function(){
					alert("success");
					getList();
				}
			});
	});
	
	// delete
	$("#tbl").on("click", ".row .btnDelete", function() {
		var seq = $(this).parent().parent().attr("seq");
		if(!confirm("delete " + seq + " ?")) return;
		$.ajax({
			type:"post",
			url:"delete",
			data:{"seq":seq},
			success:function(){
				alert("success");
				getList();
			}
		});
	});
	
	// insert
	$("#btnInsert").on("click", function() {
		var name=$("#txtName").val();
		var tel=$("#txtTel").val();
		var address=$("#txtAddress").val();
		if (name=="") {
			alert("insert name");
			$("#txtName").focus();
		} else if (tel=="") {
			alert("insert tel");
			$("#txtTel").focus();
		} else if (address=="") {
			alert("insert address");
			$("#txtAddress").focus();
		} else {
			if(!confirm("save?")) return;
		}
		$.ajax({
			type:"post",
			url:"insert",
			data:{"name":name, "tel":tel, "address":address},
			success:function(){
				alert("success");
				getList();
			}
		});
	});
	
	// list
	function getList() {
		$.ajax({
			type:"get",
			url:"list.json",
			dataType:"json",
			data:{"page":page},
			success:function(data){
				lastPage = data.lastPage;
				$("#total").html(data.total);
				 $("#page").html(page +" / " + lastPage);
				var temp = Handlebars.compile($("#temp").html());
				 $("#tbl").html(temp(data));
			}
		});
		if(page==1) {
			$("#btnPre").attr("disabled", true);
		} else {
			$("#btnPre").attr("disabled", false);
		}
		
		if(page==lastPage) {
			$("#btnNext").attr("disabled", true);
		} else {
			$("#btnNext").attr("disabled", false);
		}
	};
	
	$("#btnPre").on("click", function() {
		page--;
		getList();
	});
	
	$("#btnNext").on("click", function() {
		page++;
		getList();
	});
</script>
</html>