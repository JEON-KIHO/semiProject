	var page = 1;
	getList();
	
	$("#btnSeatch").on("click", function() {
		getList();
	});
	
	$("#query").keydown(function(key) {
		getList();
	});
	
	$("#size").change(function() {
		page=1;
		getList();
	});
	
	$("#btnPre").on("click", function() {
		page--;
		getList();
	});
	$("#btnNext").on("click", function() {
		page++;
		getList();
	});
	
	function getList() {
		var query = $("#query").val();
		var size = $("#size").val();
		$.ajax({
			type:"get",
			url:url,
			headers:{"Authorization":"KakaoAK 5ce0232a77c703831f4d0a375cece966"},
			dataType:"json",
			data:{"query":query, "size":size, "page":page},
			success:function(data) {
				var total = data.meta.pageable_count;
				var lastPage = (total%size)==0?(total/size):Math.ceil(total/size);
				var temp = Handlebars.compile($("#temp").html());
				 $("#tbl").html(temp(data));
				 $("#total").html(total);
				 $("#curPage").html(page +"/"+lastPage);
				 if(page==1) { $("#btnPre").attr("disabled", true); }
				 else { $("#btnPre").attr("disabled", false); }
				 if(data.meta.is_end) { $("#btnNext").attr("disabled", true); }
				 else { $("#btnNext").attr("disabled", false); }
			}
		});
	}