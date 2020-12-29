
var page=1;   
   getList();
   
   
   $("#bsearch").on("click",function(){
	   getList();
   });
   
   $("#query").keydown(function(e){
	   if(e.keyCode==13){
		   page=1;
		  getList(); 
	   }
	   
   });
   
   $("#size").change(function(){
	   getList();
   });
   
   $("#bpre").on("click",function(){
	   page--;
	   getList();
   });
   $("#bnext").on("click",function(){
	   page++;
	   getList();
   });
   
   function getList(){
	   var query=$("#query").val();
	   var size=$("#size").val();
      $.ajax({
         type:"get",
         url:url,
         headers:{"Authorization":"KakaoAK a9434aa58d64c8f07c5137af30f5ade4"},
         dataType:"json",
         data:{"query":query, "size":size,"page":page},
         success:function(data){
            var template = Handlebars.compile($("#temp").html());
               $("#tbl").html(template(data));
               var total=data.meta.pageable_count;
               $("#total").html(total);
               
               if(page==1) $("#bpre").attr("disabled",true)
               else $("#bpre").attr("disabled",false);
               
               if(data.meta.is_end) $("#bnext").attr("disabled",true)
            	   else $("#bnext").attr("disabled",false);
               $("#curPage").html(page);
               var lastPage=0;
               if(total%size == 0) lastPage=total/size
               else lastPage= parseInt(total/size)+1;
               $("#lastPage").html(lastPage);
         }
      });
   }
