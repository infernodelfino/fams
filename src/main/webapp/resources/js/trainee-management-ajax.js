$(document).ready(function () {

    $('#traineeTable').DataTable({
        "scrollY": "500px",
        "scrollCollapse": true,
        "lengthMenu": [ 10, 20, 30, 50, 100 ],
        "order": [[ 3, "asc" ]],
        "searching": false,
        "dom": '<"top"i>rt<"bottom"lp><"clear">',
        "columns": [
            { 
                "searching" : false,
                "orderable": false,
                "width": "3%"
            },
            {
                "width": "5%"
            },
            null,
            null,
            null,
            null
        ]
    });

    $("#checkAll").click(function(){
        if (this.checked) {
            $(':checkbox').each(function () {
                this.checked = true;
            });
        } else {
            $(':checkbox').each(function () {
                this.checked = false;
            });
        }
    });
    
    // check update, alert if != 0 or update > 1 record, load page if == 1
    $("#update-btn").click(function(e){
        var checkedCheckBox = 0;
        
        $(":checkbox").each(function(){
            if (this.checked == true) {
                checkedCheckBox += 1;
            }
        });

        if (checkedCheckBox > 1 ) {
            e.preventDefault();
            alert("You can't edit two or more records at the same time");
        } if (checkedCheckBox == 0) {
            e.preventDefault();
            alert("Please select a record");
        } if (checkedCheckBox == 1) {
        	var id;
        	$(":checkbox").each(function(){
                if (this.checked == true) {
                    id = $(this).attr("ids");
                }
            });
        	
            $.post({
                url: "/trainee-management/update/trainee-profile-ajax",
                data: {
                	id : id
                },
                success: function(data){
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
        }
    });

    // check delete !=0
    $("#delete-btn").click(function(){
        var checkedCheckBox = 0;
        var list = [];
        $("input.checkBoxTrainee").each(function(){
            if (this.checked == true) {
                checkedCheckBox += 1;
                var ids = $(this).attr("ids");
    			list.push(ids); 
            }
        });

        if (checkedCheckBox == 0) {
            alert("Please select a record");
        } else {
        	if(confirm("Delete confirm?")){
    	    	$.post({
    	    		contentType: "application/json",
    	    		data: JSON.stringify(list),
    	    		url: "/trainee-management/delete/trainee",
    	    		success: function(data) {
    	    			$("#ajax-area").html(data);
    	    		},
    	    		error: function(data){
    	    			$("#ajax-area").html(data);
    	    		}
    	    	});
        	}
        }
    });

    // ajax to view page if click in TraineeID
    $(".ajax-class").click(function(e){
    	var empID = $(this).attr("ids");
        e.preventDefault();
        $("#ajax-area").html("Loading...");
        $.ajax({
            type: "GET",
            data: {
            	empID: empID
            },
            url: "/trainee-management/view/trainee-profile-ajax",
            success: function(data) {
                setTimeout(() => {
                    $("#ajax-area").html(data);
                }, 1000);
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