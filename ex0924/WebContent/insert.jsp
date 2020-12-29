<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<meta charset="UTF-8">
<title> 등록 </title>
<style>
	h1 {font-size:25px;}
	table {border-collapse:collapse; margin-bottom:5px;}
	#table {width:300px;}
	td {border:solid 1px pink;}
	input[type=file] {visibility:hidden; height:0px;}
	input {background:pink;}
</style>
</head>
<body>
 <h1> [등록] </h1>
 	<form name="form" action="insert" method="post" enctype="multipart/form-data">
 		<table id="table">
 			<tr>
 				<td class="title"> CODE </td>
 				<td> <input type="text" name="code" size=7 minlength=4> </td>
 			</tr>
 			<tr>
 				<td class="title"> NAME </td>
 				<td> <input type="text" name="pname" size=25> </td>
 			</tr>
 			<tr>
 				<td class="title"> price </td>
 				<td> <input type="text" name="price" size=15 value="0"> </td>
 			</tr>
 			<tr>
 				<td class="title"> image </td>
 				<td>
 					<img src="http://placehold.it/150x110" width=150 id="image">
 					<input type="file" accept="image/*" name="image">
 				</td>
 			</tr>
 		</table>
 		<input type="submit" value="save">
 		<input type="reset" value="cancel">
 		<input type="button" value="inven" onClick="location.href='/'">
 	</form>
</body>
<script>
	var chkCode;

	function chkCode() {
		var code=$(form.code).val();
		$.ajax({
			type:"get",
			url:"chkCode",
			dataType:"json",
			data:{"code":code},
			success:function(data) {
				if(data.code==1) {
					alert("already insert code");
					$(form.code).focus();
				} else {
					if(!confirm("save?")) return;
					$(form).submit();
				}
			}
		});
	}
	
	$(form).submit(function(e) {
		e.preventDefault();
		var price=$(form.price).val();
		var code=$(form.code).val();
		var pname=$(form.pname).val();
		
		if(code=="") {
			alert("insert code");
			$(form.code).focus();
		} else if(pname.trim()=="") {
			alert("insert name");
			$(form.pname).focus();
		} else if(price=="") {
			alert("insert price");
			$(form.price).focus();
		} else if(price.replace(/[0-9]/g,'')) {
			alert("insert number");
			$(form.price).focus();
		} else {
			chkCode();
		}
	});

	$("#image").on("click", function () {
		$(form.image).click();
	});
	
	// image 맛보기
	 $(form.image).on("change", function(e){
        var reader = new FileReader();
        reader.onload=function(e){
            $("#image").attr("src", e.target.result);
        }
        reader.readAsDataURL(this.files[0]);
    });
</script>
</html>