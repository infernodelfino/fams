$(document).ready(function(){

	var expandedUniversity = false;
    var expandedFaculty = false;

    //show university checkbox when you click on select
    $(".universitySelect").click(function () {
        if (!expandedUniversity) {
            $("#universityCheckboxes").attr('style', 'display: block');
            expandedUniversity = true;
        } else {
            $("#universityCheckboxes").attr('style', 'display: none');
            expandedUniversity = false;
        }
    });

    //show faculty checkbox when you click on select
    $(".facultySelect").click(function () {
        if (!expandedFaculty) {
            $("#facultyCheckboxes").attr('style', 'display: block');
            expandedFaculty = true;
        } else {
            $("#facultyCheckboxes").attr('style', 'display: none');
            expandedFaculty = false;
        }
    });

    //turn university input on/off with checkbox Other
    $("#universityOtherCheckBox").change(function () {
        if ($("input#universityOtherCheckBox").is(':checked')) {
            $("#universityOtherInput").attr('type', 'text');
        } else {
            $("#universityOtherInput").attr('type', 'hidden');
        }
    });

    //turn faculty input on/off with checkbox Other
    $("#facultyOtherCheckBox").change(function () {
        if ($("input#facultyOtherCheckBox").is(':checked')) {
            $("#facultyOtherInput").attr('type', 'text');
        } else {
            $("#facultyOtherInput").attr('type', 'hidden');
        }
    });

    //university checkbox: Chỉ click được 1, phải click vào 1, lấy text + value gán cho ô option trong select đầu
    $("input[name='universityName']").change(function () {
        //click vào thì unclick - tổng quan cho tất cả các checkbox
        $("input[name=" + $(this).attr('name') + "]").not(this).prop('checked', false);
        
        var universityCount = 0;
        //phải tích vào 1 ô
        $("input[name='universityName']").each(function(){
            if (this.checked) {
                universityCount+=1;
            }
        });

        if (universityCount == 1) {
             //lấy text trong label của checkbox đang được đánh dấu
             var universityOptionText = $("label[for=" + $("input[name='universityName']:checked").prop('id') + "]").text().trim();
            //lấy value theo tên của checkbox đang được check
            var universityOptionValue = $("input[name='universityName']:checked").prop('value');
            $("#universityOption").html(universityOptionText);
            $("#universityOption").prop('value', universityOptionValue);
        } else {
            $("#universityOption").html('Select a university');
            $("#universityOption").prop('value', '*');
        }

        //Click khác other thì reset
        if ($("input[name='universityName']:checked").val() != 'other') {
            $("#universityOtherInput").prop('value', '');
        }
    });

    //faculty checkbox: Chỉ click được 1, phải click vào 1, lấy text + value gán cho ô option trong select đầu
    $("input[name='facultyName']").change(function () {
        //click vào thì unclick - tổng quan cho tất cả các checkbox
        $("input[name=" + $(this).attr('name') + "]").not(this).prop('checked', false);
        
        var facultyCount = 0;
        //phải tích vào 1 ô
        $("input[name='facultyName']").each(function(){
            if (this.checked) {
                facultyCount+=1;
            }
        });

        if (facultyCount == 1) {
            //lấy text trong label của checkbox đang được đánh dấu
            var facultyOptionText = $("label[for=" + $("input[name='facultyName']:checked").prop('id') + "]").text().trim();
            //lấy value theo tên của checkbox đang được check
            var facultyOptionValue = $("input[name='facultyName']:checked").prop('value');
            $("#facultyOption").html(facultyOptionText);
            $("#facultyOption").prop('value', facultyOptionValue);
        } else {
            $("#facultyOption").html('Select a faculty');
            $("#facultyOption").prop('value', '*');
            $("#facultyOtherInput").prop('value', '');
        }

        //Click khác other thì reset
        if ($("input[name='facultyName']:checked").val() != 'other') {
            $("#facultyOtherInput").prop('value', '');
        }
    });
	
    var d = new Date();
    var day = d.getDate();
    var month = d.getMonth() + 1;
    var year = d.getFullYear();
    var currentDay = year + '-' + (('' + month).length < 2 ?  '0'+ month : month) + '-' + (('' + day).length <2 ? '0' + 'day' : day);

    //if salary = yes => bank account is editable and reverse on change
    $("input[name='salaried']").change(function(){
        if ($("input[name='salaried']:checked").val() == 0){
            $("#bankAccount").attr("readonly", true);
            $("#bankAccount").attr("style", "background-color: #f7f7f7");
        } else if ($("input[name='salaried']:checked").val() == 1) {
            $("#bankAccount").attr("readonly", false);
            $("#bankAccount").attr("style", "background-color: white");
        }
    });
    
  //if salary = yes => bank account is editable and reverse on load
    $("input[name='salaried']").ready(function(){
        if ($("input[name='salaried']:checked").val() == 0){
            $("#bankAccount").attr("readonly", true);
            $("#bankAccount").attr("style", "background-color: #f7f7f7");
        } else if ($("input[name='salaried']:checked").val() == 1) {
            $("#bankAccount").attr("readonly", false);
            $("#bankAccount").attr("style", "background-color: white");
        }
    });

    const msg4 = "You must input all required fields!";
    const msg5 = "Wrong format.";
    const msg9 = "You must start phone number with “0”.";
    const msg10 = "Cannot input future date.";
    const msg13 = "This trainee is already existed.";
    const msg24 = "10 ≤ “Phone” ≤ 14";

    var emailRegex = /^([A-Z|a-z|0-9](\.|_){0,1})+[A-Z|a-z|0-9]\@([A-Z|a-z|0-9])+((\.){0,1}[A-Z|a-z|0-9]){2}\.[a-z]{2,3}$/;
    var nameRegex = /^([A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+\s)*[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]*$/;
    var phoneRegex = /^(0)[0-9]{9,13}$/;
    var birthDateRegex = /^[0-9]{4}\-[0-9]{2}\-[0-9]{2}$/;

    //check regex name
    $.validator.addMethod('namePattern', function(input) {
        return nameRegex.test(input);
    });

    //check regex email
    $.validator.addMethod('emailPattern', function(input) {
        return emailRegex.test(input);
    });
    
    //check regex birth date
    $.validator.addMethod('birthDatePattern', function(input){
    	return birthDateRegex.test(input);
    });

    //check Regex phone
    $.validator.addMethod('phonePattern', function(input) {
        return phoneRegex.test(input);
    });
    
    //check university null
    $.validator.addMethod('checkUniversity', function(){
    	if ($("option#universityOption").val() == "*") {
    		return false;
        } if ($("option#universityOption").val() == "other" && $("#universityOtherInput").val() == "") {
            return false;
        } else {
    		return true;
    	}
    });
    
    //check faculty null
    $.validator.addMethod('checkFaculty', function() {
    	if ($("option#facultyOption").val() == "*") {
    		return false;
        } if ($("option#facultyOption").val() == "other" && $("#facultyOtherInput").val() == "") {
            return false;
        } else {
    		return true;
    	}
    });

    //check phone start with 0
    $.validator.addMethod('startWith0', function(input){
        if (input.charAt(0) == 0) {
            return true;
        } else {
            return false;
        }
    });

    //check phone's length
    $.validator.addMethod('checkPhoneLength', function(input){
        if (input.length < 10 || input.length > 14) {
            return false;
        } else {
            return true;
        }
    });

    $.validator.addMethod('checkBithDate', function (input){
        if ($("#dateOfBirth").val() > currentDay) {
            return false;
        } else {
            return true;
        }
    });

    $("#informationUpdateForm").validate({
        rules: {
            name: {
                required: true,
                namePattern: true
            },
            gender: {
                required: true
            }, 
            dateOfBirth: {
                required: true,
                checkBithDate: true,
                birthDatePattern: true
            },
            phone: {
                required: true,
                startWith0: true,
                checkPhoneLength: true,
                phonePattern: true
            },
            email: {
                required: true,
                emailPattern: true
            },
            salaried: {
                required: true
            },
            university: {
            	checkUniversity: true
            },
            faculty: {
            	checkFaculty: true
            }
        }, messages: {
            name: {
                required: msg4,
                namePattern: msg5
            },
            gender: {
                required: msg4
            },
            dateOfBirth: {
                required: msg4,
                checkBithDate: msg10,
                birthDatePattern: msg5
            },
            phone: {
                required: msg4,
                startWith0: msg9,
                checkPhoneLength: msg24,
                phonePattern: msg5,
            },
            email: {
                required: msg4,
                emailPattern: msg5

            },
            salaried: {
                required: msg4
            }, 
            university: {
            	checkUniversity: msg4
            },
            faculty: {
            	checkFaculty: msg4
            }
        }
    });

    $("#close-btn").click(function(e){
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
    
    $("#btn-submit-update-profile").click(function(){
    	if($("#informationUpdateForm").valid()) {
	    	if(confirm("Are you sure to submit?")){
	    		var traineeIdN = $("#traineeId").attr("empId");
		    	var nameN = $("#name").val().trim();
		    	var genderN = $("input[name='gender']:checked").val();
		    	var dateOfBirthN = $("#dateOfBirth").val();
                
                var universityNameN;
                if ($("#university").val() == "other") {
                    universityNameN = $("#universityOtherInput").val().trim().toUpperCase();
                } else {
                    universityNameN = $("#university").text().trim();
                }
                
                var facultyNameN;
                if ($("#faculty").val() == "other") {
                    facultyNameN = $("#facultyOtherInput").val().trim().toUpperCase();
                } else {
                    facultyNameN = $("#faculty").text().trim();
                }
		    	var phoneN = $("#phone").val().trim();
		    	var emailN = $("#email").val().trim();
		    	var salaryPaidN = $("input[name='salaried']:checked").val();
		    	var tpBankN = $("#bankAccount").val().trim();
		    	var allowanceGroupN = $("#allowanceGroup option:selected").text();
		    	
		    	var traineeProfile = {
	    			id				: traineeIdN,
	    			name 			: nameN,
	    			gender 			: genderN,
	    			dateOfBirth 	: dateOfBirthN,
	    			universityName 	: universityNameN,
	    			facultyName 	: facultyNameN,
	    			phone 			: phoneN,
	    			email 			: emailN,
	    			salaryPaid 		: salaryPaidN,
	    			tPBAccount		: tpBankN,
	    			allowanceGroup 	: allowanceGroupN
	    		}
		    	
		    	$.post({
		    		contentType: "application/json",
		    		url: "/trainee-management/update/trainee-profile-ajax/confirm-update",
		    		data: JSON.stringify(traineeProfile),
		    		success: function(data) {
		    			$("#ajax-area").html(data);
		    		}, error: function () {
		    			console.log("error");
		    		}
		    	});
	    	}
    	}
    });
    
    $("#training-result").click(function(e){
        e.preventDefault();
        var empId = $("#empid").attr("idtrainee");
        $("#ajax-area").html("Loading...");
        $.get({
        	data: {
        		empId: empId
        	},
        	url: "/trainee-management/update/trainee-result-ajax",
            success: function(data) {
                setTimeout(() => {
                    $("#ajax-area").html(data);
                }, 500);
            }
        });
    });
});

