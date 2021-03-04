$(document).ready(function () {
	$("#login").click(function(){
//		  $("#message").text(" ");
	});
	
    $("#login-form").validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            username: {
                required: "Username must be not empty!"
            },
            password: {
                required: "Password must be not empty!"
            }
        }
    });
});