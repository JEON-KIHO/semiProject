<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chart part</title>
<link rel="stylesheet" href="home.css">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
	<div id="divPage">
		<div id="divMenu"><jsp:include page="menu.jsp"/></div>
		<div id="divHeader"><h2>[ CHART ]</h2></div>
		<div id="divCondition">
			<select id="chart">
				<option value="avgStudents" selected> students avg grade </option>
				<option value="avgCourses"> courses avg grade </option>
				<option value="deptCount"> courses students </option>
			</select>
		</div>
		<div id="tbl">
			<div id="chart_div" style="width:798px; height:700px; border:1px dotted black;">
			
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

	var title;
	var vtitle;
	var htitle;
	var stype;
    changeChart();
	
    $("#chart").change(function() {
    	changeChart();
    });
    

	function changeChart() {
		var url = $("#chart").val();
		
		switch(url) {
    	case "avgStudents" :
    		title = "students avg grade"
    		vtitle = "grade"
    		htitle = "students"
    		stype = "bars"
    		break;
    	case "avgCourses" :
    		title = "courses avg grade"
    		vtitle = "grade"
    		htitle = "courses"
    		stype = "bars"
    		break;
    	case "deptCount" :
    		title = "dept students count"
    		vtitle = "dept"
    		htitle = "students"
    		stype = "bars"
    		break;
    	}

		google.charts.load('current', {'packages':['corechart']});
	    google.charts.setOnLoadCallback(drawVisualization);
		
		function drawVisualization() {
			/* 데이터 셋팅 */
			$.ajax({
				type : "get",
				url : url,
				dataType : "json",
				success : function(result) {
					var data = google.visualization.arrayToDataTable(result);

					/* 옵션 셋팅 */
					var options = {
						title : title,
						vAxis : { title : vtitle },
						hAxis : { title : htitle },
						seriesType : stype
					};

					/* 차트 그리기 */
					if(url=="deptCount") {
						var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
					}else {
						var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
					}
					chart.draw(data, options);
				}
			});
		}
	}
</script>
</html>