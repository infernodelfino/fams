$(document).ready(function(){
	
	//click close > back to the Trainee Listing page
    $("#close-btn").click(function(e){
        e.preventDefault();
        $("#ajax-area").html("Loading...");
        $.get({
        	url: "/trainee-management/trainee-listing",
            success: function(data) {
                setTimeout(() => {
                    $("#ajax-area").html(data);
                }, 500);
            }
        });
    });
    
    $("#trainee-information").click(function(e){
        e.preventDefault();
        $("#ajax-area").html("Loading...");
        $.get({
        	url: "/trainee-management/view/trainee-result-ajax-back-to-profile",
            success: function(data) {
                setTimeout(() => {
                    $("#ajax-area").html(data);
                }, 500);
            }
        });
    });
    
    //cua a linh ???????//
    $("#btn-update-trainee").click(function(e){
        e.preventDefault();
        $("#ajax-area").html("Loading...");
        $.get({
        	url: "/trainee-management/trainee-profile-update-ajax",
        	success: function(data) {
                setTimeout(() => {
                    $("#ajax-area").html(data);
                }, 500);
            }
        });
    });
    
    $("#delete-confirm").click(function (e) {
   	 e.preventDefault();
   	 var empId = $("#empid").val();
   	 var listId = [];
   	 listId.push(empId);
   	 $("#modalDelete").modal("toggle");
   	 $.post({
	    		contentType: "application/json",
	    		data: JSON.stringify(listId),
	    		url: "/trainee-management/delete/trainee",
	    		success: function(data) {
	    			 $("#ajax-area").html(data);
	    		},
	    		error: function(data){
	    			$("#ajax-area").html(data);
	    		},
	    		statusCode: {
		             403: function() {
		            	 alert("403");
		             	$.ajax({
		             		type: "GET",
		 	                url:"/page-403",
		 	                success: function(html) {
		 	                	$("#ajax-area").html(html);
		 	                },
		 	        	});
		             }
		         }
	    	});
   });

    //cua a tuan ?????//
    $("#update-result").click(function(e) {
    	e.preventDefault();
        $("#ajax-area").html("Loading...");
        $.get({
        	//tuanpm22
        	url: "/trainee-management/update/trainee-result-ajax",
            success: function(data) {
                setTimeout(() => {
                    $("#ajax-area").html(data);
                }, 500);
            },
            statusCode: {
	             403: function() {
	            	 alert("403");
	             	$.ajax({
	             		type: "GET",
	 	                url:"/page-403",
	 	                success: function(html) {
	 	                	$("#ajax-area").html(html);
	 	                },
	 	        	});
	             }
	         }
        });
    });
});