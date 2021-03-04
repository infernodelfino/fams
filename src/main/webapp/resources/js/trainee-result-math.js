$(document).ready(function(){
    var indexOfAttendanceStatus=-1;
    //get modal attendace status
    $(document).on('click','.attendace-row-n',function(e){
        var code = $(this).find('.milestoneName').text();
        var date = $(this).find('.milestoneName').attr('txt');
        indexOfAttendanceStatus = $('#tbody-attendance-status tr').index($(this));
//        alert('index of row in attendance status: '+indexOfAttendanceStatus);
        // truong hop du lieu dc lay tu database ra de hien thi trong entity AttendanceStatus
        // khong co truong date
        if(date==''){
//        	 Thg3-20
             var arr = code.split('-');
             var month = arr[0].substring(3);
             var year = '20'+arr[1];
             date = year+'-'+month+'-02';
        }
        
         $.get({
         	url: "/trainee-management/modal-attendace-status",
         	data:{
         		dateCode:code,
         		date	:date
         	},
         	success: function (data) {
         		$("#modal-attendace-status").html(data);
         		$("#modalViewAttendence").modal("show");
         	}
         });
        
    });
    
    $(document).on('click','#btn-update-attendance-status',function(e){
        var days = $('#modalUpdateAttendence th:last').text();
        var countP = 0, countA = 0, countE=0, countL=0;
        var countAn = 0, countEn = 0, countLn = 0;
        var value='';
    	$('#modalUpdateAttendence .check-update-attendace-status').each(function(){
    		 value = $(this).val();
             if(value =='P')
                 countP++;
             else if(value == 'A')
                 countA++;
             else if(value == 'E')
                 countE++;
             else if(value == 'L')
                 countL++;
             else if(value == 'An')
                 countAn++;
             else if(value == 'En')
                 countEn++;
             else
                 countLn++;
        });

        var absenTime = countA + countAn;
        var lateOrEarly = countL + countLn + countE + countEn;
        var noPermission = ((countAn + countLn + countEn)/(absenTime + lateOrEarly))*100;
        var notPresentTime = (((lateOrEarly/2)+absenTime)/(countP+lateOrEarly))*100;
        var notAttendanceRate = (notPresentTime/(countP+lateOrEarly))*100;
        var disciplinaryPoint = 0;
        if(notPresentTime <= 5)
            disciplinaryPoint = 100;
        else if(notAttendanceRate>5 && notAttendanceRate<=20)
            disciplinaryPoint = 80;
        else if(notAttendanceRate <= 30)
            disciplinaryPoint = 60;
        else if(notAttendanceRate < 50)
            disciplinaryPoint = 50
        else if(notAttendanceRate >= 50 || noPermission < 20)
            disciplinaryPoint = 20;
        else
            disciplinaryPoint = 0;
        noPermission = Math.round(noPermission * 100) / 100;
        disciplinaryPoint = Math.round(disciplinaryPoint * 100) / 100;
        $(`#tbody-attendance-status tr:nth-child(${indexOfAttendanceStatus+1})`)
        .find('.absentTime').text(absenTime);
        $(`#tbody-attendance-status tr:nth-child(${indexOfAttendanceStatus+1})`)
        .find('.lateOrEarly').text(lateOrEarly);
        $(`#tbody-attendance-status tr:nth-child(${indexOfAttendanceStatus+1})`)
        .find('.noPermissionRate').text(noPermission+'%');
        $(`#tbody-attendance-status tr:nth-child(${indexOfAttendanceStatus+1})`)
        .find('.disPoint').text(disciplinaryPoint+'%');
        
        //
        calculateFinal();
    });
    
    function calculateFinal(){
        var absentTimeFinal = 0;
        var lateOrEarlyFinal = 0;
        var noPermissionFinal = 0;
        var disciplinaryPointFinal = 0;
        var count = 0;
        $("#tbody-attendance-status .attendace-row-n").each(function(){
            count++;
            var absenTime = $(this).find('.absentTime').text();
            var lateOrEarly = $(this).find('.lateOrEarly').text();
            var noPermission = $(this).find('.noPermissionRate').text().slice(0, -1);
            var disciplinaryPoint = $(this).find('.disPoint').text().slice(0, -1);
            absentTimeFinal = absentTimeFinal + parseInt(absenTime);
            lateOrEarlyFinal = lateOrEarlyFinal + parseInt(lateOrEarly);
            noPermissionFinal = noPermissionFinal + parseFloat(noPermission);
            disciplinaryPointFinal = disciplinaryPointFinal + parseFloat(disciplinaryPoint);
        });
        noPermissionFinal = noPermissionFinal / count;
        noPermissionFinal = Math.round(noPermissionFinal * 100) / 100;
        disciplinaryPointFinal = disciplinaryPointFinal / count;
        disciplinaryPointFinal = Math.round(disciplinaryPointFinal * 100) / 100;
        $('#fn-absent-time').text(absentTimeFinal);
        $('#fn-lastOrEarly').text(lateOrEarlyFinal);
        $('#fn-no-permission').text(noPermissionFinal+'%');
        $('#fn-dis-point').text(disciplinaryPointFinal+'%');
    }
});