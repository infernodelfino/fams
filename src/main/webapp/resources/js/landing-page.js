$(document).ready(function () {

    $("#landing-page-link").click(function(e){
        e.preventDefault();
        $("#ajax-area").html("Loading...");
        $.ajax({
            type: "GET",
            url: "/landing-page/ajax",
            success: function(data) {
                setTimeout(() => {
                    $("#ajax-area").html(data);
                }, 1000);
            }
        });
    });
    $("#trainee-management-link").click(function(e){
        e.preventDefault();
        $("#ajax-area").html("Loading...");
        $.ajax({
            type:"GET",
            url: "/trainee-management/trainee-listing",
            success: function(data) {
                setTimeout(() => {
                    $("#ajax-area").html(data);
                }, 1500);
            }
        });
    });
    
 // display Table in landing page
    $("#type-of-dasboard").ready(function(){
        if($('#type-of-dasboard option:selected').val() == "chart"){
            $.ajax({
                type: "GET",
                url: "/landing-page/chart",
                success: function (html) {
                    $(".dashboard-content").html(html);
                }
            });
        }
        else{
            $.ajax({
                type: "GET",
                url: "/landing-page/content-table/class?location=all&status=all",
                success: function (html) {
                    $(".dashboard-content").html(html);
                }
            });
        }
    });
    
    $("#location").change(function(){
    	var location = $("#location").val();
    	var dashboardFor = $('#dashboard-for').val();
    	var status = $('#status-for').val();
    	var type = $("#type-of-dashboard").val();
    	if($('#type-of-dashboard option:selected').val() == "chart"){
	    	$.ajax({
	            type: "GET",
	            url: "/landing-page/content-chart/"+dashboardFor+"?location="+location+"&status="+status,
	            success: function (html) {
	            	var row = "<row>";
	            	html[0].forEach((item,i)=>{
	            		row += '<div class="col-md-4" style="float:left"><div id="chartContainer'+i+'" style="height: 370px; width: 100%;"></div></div>';
	            	});
	            	row+="</row>";
	            	
	            	var array = [];
	            	html[0].forEach((item,i)=>{
	            		var status = item;
	            		var arrayItem = [];
	            		html[1].forEach((item,i)=>{
		            		if(item.status == status){
		            			var items = {};
			            		items.y = item.percent;
			            		items.label = item.skill;
			            		arrayItem.push(items);
		            		}
		            	});
	            		array.push(arrayItem);
	            	});
	            	$(".dashboard-content").html(row);
	            	array.forEach((item,i)=>{
	            		var chart = new CanvasJS.Chart("chartContainer"+i, {
		                    animationEnabled: true,
		                    title: {
		                    	text: html[0][i]
		                    },
		                    data:[{
		                        type: "pie",
		                        startAngle: 240,
		                        yValueFormatString: "##0.00\"%\"",
		                        indexLabel: "{label} {y}",
		                        dataPoints: item
		                    }]
		                });
		            	chart.render();
	            	});
	            	
	            }
	        });
    	}
    	else{
    		$.ajax({
                type: "GET",
                url: "/landing-page/content-table/"+dashboardFor+"?location="+location+"&status="+status,
                success: function (html) {
                    $(".dashboard-content").html(html);
                }
            });
    	}
    });
    
    $("#status-for").change(function(){
    	var location = $("#location").val();
    	var dashboardFor = $('#dashboard-for').val();
    	var status = $('#status-for').val();
    	var type = $("#type-of-dashboard").val();
    	if($('#type-of-dashboard option:selected').val() == "chart"){
	    	$.ajax({
	            type: "GET",
	            url: "/landing-page/content-chart/"+dashboardFor+"?location="+location+"&status="+status,
	            success: function (html) {
	            	var row = "<row>";
	            	html[0].forEach((item,i)=>{
	            		row += '<div class="col-md-4" style="float:left"><div id="chartContainer'+i+'" style="height: 370px; width: 100%;"></div></div>';
	            	});
	            	row+="</row>";
	            	
	            	var array = [];
	            	html[0].forEach((item,i)=>{
	            		var status = item;
	            		var arrayItem = [];
	            		html[1].forEach((item,i)=>{
		            		if(item.status == status){
		            			var items = {};
			            		items.y = item.percent;
			            		items.label = item.skill;
			            		arrayItem.push(items);
		            		}
		            	});
	            		array.push(arrayItem);
	            	});
	            	$(".dashboard-content").html(row);
	            	array.forEach((item,i)=>{
	            		var chart = new CanvasJS.Chart("chartContainer"+i, {
		                    animationEnabled: true,
		                    title: {
		                    	text: html[0][i]
		                    },
		                    data:[{
		                        type: "pie",
		                        startAngle: 240,
		                        yValueFormatString: "##0.00\"%\"",
		                        indexLabel: "{label} {y}",
		                        dataPoints: item
		                    }]
		                });
		            	chart.render();
	            	});
	            }
	        });
    	}
    	else{
    		$.ajax({
                type: "GET",
                url: "/landing-page/content-table/"+dashboardFor+"?location="+location+"&status="+status,
                success: function (html) {
                    $(".dashboard-content").html(html);
                }
            });
    	}
    });
    
    $("#dashboard-for").change(function(){
    	var location = $("#location").val();
    	var dashboardFor = $('#dashboard-for').val();
    	var status = $('#status-for').val();
    	var type = $("#type-of-dashboard").val();
    	if($('#type-of-dashboard option:selected').val() == "chart"){
	    	$.ajax({
	            type: "GET",
	            url: "/landing-page/content-chart/"+dashboardFor+"?location="+location+"&status="+status,
	            success: function (html) {
	            	var row = "<row>";
	            	html[0].forEach((item,i)=>{
	            		row += '<div class="col-md-4" style="float:left"><div id="chartContainer'+i+'" style="height: 370px; width: 100%;"></div></div>';
	            	});
	            	row+="</row>";
	            	
	            	var array = [];
	            	html[0].forEach((item,i)=>{
	            		var status = item;
	            		var arrayItem = [];
	            		html[1].forEach((item,i)=>{
		            		if(item.status == status){
		            			var items = {};
			            		items.y = item.percent;
			            		items.label = item.skill;
			            		arrayItem.push(items);
		            		}
		            	});
	            		array.push(arrayItem);
	            	});
	            	$(".dashboard-content").html(row);
	            	array.forEach((item,i)=>{
	            		var chart = new CanvasJS.Chart("chartContainer"+i, {
		                    animationEnabled: true,
		                    title: {
		                    	text: html[0][i]
		                    },
		                    data:[{
		                        type: "pie",
		                        startAngle: 240,
		                        yValueFormatString: "##0.00\"%\"",
		                        indexLabel: "{label} {y}",
		                        dataPoints: item
		                    }]
		                });
		            	chart.render();
	            	});
	            }
	        });
    	}
    	else{
    		$.ajax({
                type: "GET",
                url: "/landing-page/content-table/"+dashboardFor+"?location="+location+"&status="+status,
                success: function (html) {
                    $(".dashboard-content").html(html);
                }
            });
    	}
    });
    
    $("#type-of-dashboard").change(function(){
    	var location = $("#location").val();
    	var dashboardFor = $('#dashboard-for').val();
    	var status = $('#status-for').val();
    	var type = $("#type-of-dashboard").val();
    	if($('#type-of-dashboard option:selected').val() == "chart"){
	    	$.ajax({
	            type: "GET",
	            url: "/landing-page/content-chart/"+dashboardFor+"?location="+location+"&status="+status,
	            success: function (html) {
	            	var row = "<row>";
	            	html[0].forEach((item,i)=>{
	            		row += '<div class="col-md-4" style="float:left"><div id="chartContainer'+i+'" style="height: 370px; width: 100%;"></div></div>';
	            	});
	            	row+="</row>";
	            	
	            	var array = [];
	            	html[0].forEach((item,i)=>{
	            		var status = item;
	            		var arrayItem = [];
	            		html[1].forEach((item,i)=>{
		            		if(item.status == status){
		            			var items = {};
			            		items.y = item.percent;
			            		items.label = item.skill;
			            		arrayItem.push(items);
		            		}
		            	});
	            		array.push(arrayItem);
	            	});
	            	$(".dashboard-content").html(row);
	            	array.forEach((item,i)=>{
	            		var chart = new CanvasJS.Chart("chartContainer"+i, {
		                    animationEnabled: true,
		                    title: {
		                    	text: html[0][i]
		                    },
		                    data:[{
		                        type: "pie",
		                        startAngle: 240,
		                        yValueFormatString: "##0.00\"%\"",
		                        indexLabel: "{label} {y}",
		                        dataPoints: item
		                    }]
		                });
		            	chart.render();
	            	});
	            }
	        });
    	}
    	else{
    		$.ajax({
                type: "GET",
                url: "/landing-page/content-table/"+dashboardFor+"?location="+location+"&status="+status,
                success: function (html) {
                    $(".dashboard-content").html(html);
                }
            });
    	}
    });
    
})