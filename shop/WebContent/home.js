	var page = 1;

	getList();
	
	function getList() {
		var key = $("#key").val();
		var word = $("#word").val();
		var perPage = $("#perPage").val();
		var col = $("#col").val();
		$.ajax({
			type:"get",
			url:url,
			dataType:"json",
			data:{"key":key, "word":word, "page":page, "perPage":perPage, "col":col},
			success:function(data) {
				var temp = Handlebars.compile($("#temp").html());
				 $("#tbl").html(temp(data));
				 $("#curPage").html(page);
				 $("#lastPage").html(data.lastPage);
				 $("#cnt").html(data.cnt);
				 if(page==1) {
					 $("#btnPre").attr("disabled", true);
				 } else {
					 $("#btnPre").attr("disabled", false);
				 }
				 if(page==data.lastPage) {
					 $("#btnNext").attr("disabled", true);
				 } else {
					 $("#btnNext").attr("disabled", false);
				 }
			}
		});
	}
	
	$("#perPage").change(function() {
		$("#btnSearch").click();
	});
	$("#btnPre").on("click", function() {
		page--;
		getList();
	});
	$("#btnNext").on("click", function() {
		page++;
		getList();
	});
	$("#btnSearch").on("click", function() {
		page=1;
		getList();
	});
	$("#word").keydown(function(key){
		if(key.keyCode==13) {
			$("#btnSearch").click();
		}
	});