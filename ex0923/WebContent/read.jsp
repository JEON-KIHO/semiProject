<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<meta charset="UTF-8">
<title> 읽기 </title>
</head>
<body>
<h1> [읽기] </h1>
<div id="container"></div>
	<script id="temp" type="text/x-handlebars-template">
		{{#each list}}
			<div class="box" onClick="location.href='read.jsp'">
				<img src="{{image}}" width=150>
				<div><b>[{{rn}}]</b> {{code}}</div>
				<div>{{pname}}</div>
				<div>{{price}}＄</div>
			</div>
		{{/each}}
	</script>
</body>
<script>
getList();

function getList() {
	var word=$("#word").val();
	var order=$("#order option:selected").val();
	var num=$("#num option:selected").val();
	$.ajax({
		type:"get",
		url:"list.json",
		dataType:"json",
		data:{"page":page, "word":word, "order":order, "num":num},
		success:function(data) {
			var temp = Handlebars.compile($("#temp").html());
			 $("#container").html(temp(data));
		}
	});
}

</script>
</html>