$(document).ready(function(){

    //click update > to trainee-update page
    $("#update-btn").click(function(e){
        e.preventDefault();
        $("#ajax-area").html("Loading...");
        $.get({
        	url: "/trainee-management/update/trainee-profile-ajax-view-to-update",
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

    // $("#trainee-information").click(function(e){
    //     e.preventDefault();
    //     $("#ajax-area").html("Loading...");
    //     $.get({
    //         url: "trainee-profile-view-ajax.html",
    //         success: function(data) {
    //             setTimeout(() => {
    //                 $("#ajax-area").html(data);
    //             }, 500);
    //         }
    //     });
    // });
    $("#delete-confirm").click(function (e) {
    	 e.preventDefault();
    	 var empId = $("#empid").attr("idtrainee");
    	 var listId = [];
    	 listId.push(empId);
    	 $("#modalDelete").modal("hide");
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

    $("#training-result").click(function(e){
        e.preventDefault();
        var empId = $("#empid").attr("idtrainee");
        $("#ajax-area").html("Loading...");
        $.get({
        	data: {
        		empId: empId
        	},
        	url: "/trainee-management/view/trainee-result-ajax",
            success: function(data) {
                setTimeout(() => {
                    $("#ajax-area").html(data);
                }, 500);
            }
        });
    });

});
//trainee-management-ajax.html