$(document).ready(function(){
    var learnCode = $('#learnCodeOfLearnDetail').text();
    $('#btn-add-addLearnDetail').click(function(){
        var topicId = $('#listTopic').find('option:selected').val();
        var topicName = $('#listTopic').find('option:selected').text();
        var learnDetailId = $('#listTopic').find('option:selected').attr('lcode');
        var option = `<option value="${topicId}" style="text-align: center;" lcode="${learnDetailId}">${topicName}</option>`;
        $('#listTopic').find('option:selected').remove();
        $('#listLearnDetailCurrent').find('select').append(option);
    });

    $('#btn-delete-addLearnDetail').click(function(){
        var topicId = $('#listLearnDetailCurrent').find('option:selected').val();
        var topicName = $('#listLearnDetailCurrent').find('option:selected').text();
        var learnDetailId = $('#listLearnDetailCurrent').find('option:selected').attr('lcode');
        var option = `<option value="${topicId}" style="text-align: center;" lcode="${learnDetailId}">${topicName}</option>`;
        $('#listLearnDetailCurrent').find('option:selected').remove();
        $('#listTopic').find('select').append(option);
    });

    $('#btn-save-addLearnDetail').click(function(){
        var listLearnDetail = [];
        $('#listLearnDetailCurrent').find('option').each(function(){
            var topicId = $(this).val();
            var learnDetailId = $(this).attr('lcode');
            listLearnDetail.push({
                id          :   learnDetailId,
                learnCode   :   learnCode,
                topic       :   {id:topicId}
            });
        });
        console.log(JSON.stringify(listLearnDetail));
        $.post({
        	contentType : "application/json",
            url     :   "/trainee-management/update/trainee-result-ajax/saveOrDeleteLearningDetail",
            data    :   JSON.stringify(listLearnDetail),
            success :   function(res){
            	if(res == true){
            		$('#modalAddLearningPath').modal('hide');
            		$('#success-error-modal').modal("show");
            		$('#message').text('save successfully');
            	}else{
            		$('#modalAddLearningPath').modal('hide');
            		$('#success-error-modal').modal("show");
            		$('#message').text('you can not delete all topic');
            	}
            },
            error   :   function(){
        		$('#modalAddLearningPath').modal('hide');
            	$('#success-error-modal').modal("show");
            	$('#message').text('you can not delete that topic');
            }
        });
    });
});