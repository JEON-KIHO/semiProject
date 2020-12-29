<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<meta charset="UTF-8">
<title> 도 서 관 리 </title>
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
	<h1> [도서 관리] </h1>
	<table>
		<tr>
			<td width=80 class="title"> CODE </td>
			<td width=250> <input type="text" id="txtCode" size=5 maxlength=4> </td>
		</tr>
		<tr>
			<td width=80 class="title"> TITLE </td>
			<td width=250> <input type="text" id="txtTitle" size=30> </td>
		</tr>
		<tr>
			<td width=80 class="title"> WRITER </td>
			<td width=250> <input type="text" id="txtWriter" size=15> </td>
		</tr>
		<tr>
			<td width=80 class="title"> PRICE </td>
			<td width=250> <input type="text" id="txtPrice" size=10> won </td>
		</tr>
	</table>
	<input type="button" value="save" id="btnInsert">
	
	<hr>
	
	<h1> [도서 목록] </h1>
	<table id="tbl"> </table>
			<script id="temp" type="text/x-handlebars-template">
				<tr class=title>
					<td width=80> CODE </td>
					<td width=300> TITLE </td>
					<td width=150> WRITER </td>
					<td width=100> PRICE </td>
					<td width=150> update / delete </td>
				</tr>
				{{#each .}}
					<tr class=row>
						<td width=80 class="code">{{code}}</td>
						<td width=300> <input class="title" type="text" value="{{title}}" size=30></td>
						<td width=150> <input class="writer" type="text" value="{{writer}}" size=15></td>
						<td width=100> <input class="price" type="text" value="{{price}}" size=8></td>
						<td>
							<button class="btnUpdate"> update </button>
							<button class="btnDelete"> delete </button>
						</td>
					</tr>
				{{/each}}
			</script>
</body>
<script>
	
	getList();
	
	// 목록 출력
	function getList() {
		$.ajax({
			type:"get",
			url:"list.json",
			dataType:"json",
			success:function(data) {
				var temp = Handlebars.compile($("#temp").html());
				$("#tbl").html(temp(data));
			}
		});
	}
	
	// 데이터 입력
	$("#btnInsert").on("click", function() {

		var code = $("#txtCode").val();
		var title = $("#txtTitle").val();
		var writer = $("#txtWriter").val();
		var price = $("#txtPrice").val();
		
		if(code.length!=4){
			alert("insert 4char");
			$("#txtCode").focus();
		} else if(title==""){
			alert("insert title");
			$("#txtTitle").focus();
		} else if(writer==""){
			alert("insert writer");
			$("#txtWriter").focus();
		} else if(price==""){
			alert("insert price");
			$("#txtPrice").focus();
		} else if(price.replace(/[0-9]/g,'')){
			alert("insert number");
			$("#txtPrice").focus();
		} else{
			if(!confirm("save?")) return;
			$.ajax({
				type:"post",
				url:"insert",
				data:{"code":code, "title":title, "writer":writer, "price":price},
				success:function() {
					alert("save success");
					getList();
					$("#txtCode").val("");
					$("#txtTitle").val("");
					$("#txtWriter").val("");
					$("#txtPrice").val("");
				}
			});
		}
	});
	
	// 데이터 삭제
	
	$("#tbl").on("click", ".row .btnDelete", function() {
		var row=$(this).parent().parent();
		var code=row.find(".code").text();
		if(!confirm("delete" + code + "?")) return;
		$.ajax({
			type:"post",
			url:"delete",
			data:{"code":code},
			success:function() {
				alert("delete success");
				getList();
			}
		});
	});
	
	// 데이터 수정
	
	$("#tbl").on("click", ".row .btnUpdate", function() {
		var row = $(this).parent().parent();
		var code = row.find(".code").text();
		var title = row.find(".title").val();
		var writer = row.find(".writer").val();
		var price = row.find(".price").val();
		if(!confirm("update?")) return;
		$.ajax({
			type:"post",
			url:"update",
			data:{"code":code, "title":title, "writer":writer, "price":price},
			success:function() {
				alert("update success");
				getList();
			}
		});
	});
</script>
</html>