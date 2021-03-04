$(document).ready(function () {
	// back to result view page
	$("#close-btn").click(function(e){
        e.preventDefault();
        $("#ajax-area").html("Loading...");
        $.get({
        	url: "/trainee-management/view/trainee-result-ajax",
            success: function(data) {
                setTimeout(() => {
                    $("#ajax-area").html(data);
                }, 500);
            }
        });
    });
	
	//back to view profile use tab
	$("#trainee-information").click(function(e){
        e.preventDefault();
        $("#ajax-area").html("Loading...");
        $.get({
        	url: "/trainee-management/update/trainee-profile-ajax-view-to-update",
            success: function(data) {
                setTimeout(() => {
                    $("#ajax-area").html(data);
                }, 500);
            }
        });
    });
	
    var countMilestone = 0;

    var listMinestone = [];
    var listTopic = [];
    var listAttendantStatus = [];
    var indexMilestone = $('.milestones:last').attr('index');
    if (indexMilestone == null) {
        indexMilestone = 0;
    } else {
        indexMilestone = parseInt(indexMilestone) + 1;
    }
    var indexAttendanceStatus = $('.attendace-row-n:last').attr('index');
    if (indexAttendanceStatus == null) {
        indexAttendanceStatus = 0;
    } else {
        indexAttendanceStatus = parseInt(indexAttendanceStatus) + 1;
    }
    var indexTopicMarks = $('.topicMarks:last').attr('index');
    if (indexTopicMarks == null) {
        indexTopicMarks = 0;
    } else {
        indexTopicMarks = parseInt(indexTopicMarks) + 1;
    }
    var traineeId = $('#traineeId').text();
    var learningCode = $('#learningCode').attr('lcode');

    // event when click submit button from form confirm
    $('#btn-modal-submit').click(function () {
        //    	$("#rejectClass").modal("hide");
    	//tuanpm22

        var trainee = new Object();
    	var empId = $("#idtrainee").val();
    	var allocateId = $("#fsu").attr("alloId");
    	var fsu = $("#fsu").val();
    	var salaryAllocated = $("#salaryAllocated").val();
    	var dateAllocated = $("#start-date-allocation").val();
    	var allocationStatus = $("#sel1").children("option:selected"). text();
    	var remarkAllocation = $("#remarkAllocated").val();
    	var remarkCommit = $("#commitRemark").val();
    	
    	 $("#rejectClass").modal('toggle');
    	
    	trainee.id = empId;
    	var trainee2 = new Object();
    	trainee2.id = empId;
    	
    	//Declare a commitment
    	var commitment = new Object();
    	if($("#commitId").val()!="") {
    		commitment.id = $("#commitId").val();
    	}
    	commitment.remark = remarkCommit
    	commitment.trainee = trainee2;
    	
    	//Declare allowance
    	var allowances = [];
    	$(".allowance").each( function() {
    		var allowance = new Object();
    		allowance.id = $(this).find(".allowanceId").val();
    		allowance.milestoneName = $(this).find(".mileName").text();
    		allowance.remarks = $(this).find(".remarkAllowance").val();
    		allowance.trainee = trainee2;
    		allowances.push(allowance);
    	});
    	//Declare a allocation
    	var allocation = new Object();
    	
    	if(allocateId != "") {
    		allocation.id = allocateId;
    	}
    	allocation.allocatedFsu = fsu;
    	allocation.salary = salaryAllocated;
    	allocation.startDate = dateAllocated;
    	allocation.allocationStatus = allocationStatus;
    	allocation.remarks = remarkAllocation;
    	allocation.trainee = trainee2;
    	
    	//Declare Reward Penalty
    	var rewardPelnatys = [];
    	$(".reward-penalty").each(function() {
    		var rewpen = new Object();
    		var idrewpen = $(this).find("input[type=hidden]").attr("idrewpen");
    		if(idrewpen!=null) {
    			rewpen.id = idrewpen;
    		}
    		rewpen.milestoneName = $(this).find("option:selected").text();
    		rewpen.createdDate = $(this).find(".date-penalty").val();
    		rewpen.bonusPoint = $(this).find(".bonus-point").val();
    		rewpen.penaltyPoint = $(this).find(".penalty-point").val();
    		rewpen.reason = $(this).find(".reason").val();
    		rewardPelnatys.push(rewpen);
    		rewpen.trainee = trainee2;
    	});
    	trainee.rewardPenalty = rewardPelnatys;
    	trainee.allocation = allocation;
    	trainee.commitment = commitment;
    	trainee.allowances = allowances;
	    
       /////////////////////////////////////////////////////////////////////////////////////////////duonghm
        if (learningCode == "") {
            learningCode = $('#content-btn-learnCode').text();
        }
        $('.milestones').each(function () {
            var name = $(this).find('.milestoneName').text();
            var salaryPaid = $(this).find('.salaryPaid option:selected').text();
            var startDate = $(this).find('.startDate').val();
            var endDate = $(this).find('.endDate').val();
            var topics = [];
            var index = parseInt($(this).attr('index'));
            var topicName = "";
            $(this).next().nextUntil('.milestones').each(function () {
                topicName = $(this).find('.listTopicName option:selected').text();
                var learnDetailId = $(this).find('.listTopicName option:selected').val();
                var topicId = $(this).find('.listTopicName option:selected').attr('topicId');
                var id = $(this).find('.listTopicName').attr('id');
                var maxScore = $(this).find('.max-score').val();
                var passScore = $(this).find('.pass-score').val();
                var scoreWn = $(this).find('.scoreWn').val();
                var indexTopic = parseInt($(this).attr('index'));
                var score = $(`#tbody-toppic-mark #content td.topicMarks:nth-child(${index + 1})`)
                    .find(`tr:nth-child(${indexTopic + 3}) .score-topic`).val();
                topics.push({
                    id: id,
                    learnDetailId: learnDetailId,
                    topicId: topicId,
                    maxScore: maxScore,
                    passScore: passScore,
                    wnumber: scoreWn,
                    score: score
                });
            });
            listMinestone.push(
                {
                    name: name,
                    salaryPaid: salaryPaid,
                    startDate: startDate,
                    endDate: endDate,
                    listOfTopic: topics,
                    trainee: { id: traineeId },
                    learnCode: learningCode
                }
            );
        });

        /////////////////////////////////////////////////////////////////////////////////////////////
        $('#tbody-attendance-status .attendace-row-n').each(function () {
            var id = $(this).find('.milestoneName').attr('id');
            var milestoneName = $(this).find('.milestoneName').text();
            var absentTime = $(this).find('.absentTime').text();
            var lateOrEarly = $(this).find('.lateOrEarly').text();
            var noPermissionRate = $(this).find('.noPermissionRate').text().slice(0, -1);
            var disPoint = $(this).find('.disPoint').text().slice(0, -1);
            listAttendantStatus.push({
                id: id,
                milestoneName: milestoneName,
                absentTime: absentTime,
                lateOrEarlyLeave: lateOrEarly,
                noPermissionsRate: noPermissionRate,
                disciplinaryPoint: disPoint,
                trainee: { id: traineeId }
            });
        });
        /////////////////////////////////////////////////////////////////////////////////////////////

        var listLearningPath = [];
        var iMile = listMinestone.length;
        for (var i = 0; i < iMile; i++) {
            var milestone = listMinestone[i];
            var iTopic = milestone.listOfTopic.length;
            for (var j = 0; j < iTopic; j++) {
                var topic = milestone.listOfTopic[j];
                listLearningPath.push({
                    id: topic.id,
                    trainee: milestone.trainee,
                    milestoneName: milestone.name,
                    salaryPaid: milestone.salaryPaid,
                    startMilestoneDate: milestone.startDate,
                    endMilestoneDate: milestone.endDate,
                    maxScore: topic.maxScore,
                    passScore: topic.passScore,
                    score: topic.score,
                    weightedNumber: topic.wnumber,
                    learningCode: {
                        id: topic.learnDetailId,
                        learnCode: milestone.learnCode,
                        topic: { id: topic.topicId }
                    }
                });
            }

        }
       
        data = {
            traineeId: traineeId,
            listOfAttendantStatus: listAttendantStatus,
            listOfLearningPath: listLearningPath
        }
        
        trainee.learningPaths = listLearningPath;
        trainee.attendantStatus = listAttendantStatus;
        console.log(JSON.stringify(trainee));
        $.post({
            contentType: "application/json",
            url: "/trainee-management/update/trainee-result-ajax/saveOrUpdate-trainee",
//            data: JSON.stringify(data),
            data: JSON.stringify(trainee),
            success: function (res) {
                $("#rejectClass").modal("hide");
                $('div').removeClass('modal-backdrop show in');
                $('body').removeClass('modal-open');
                $("#ajax-area").html(res);
            },
            error: function () {
                alert("fail");
            }
        });
    });

    //end $('#btn-modal-submit').click(function()

    if (learningCode != "") {
        $.get({
            url: "/trainee-management/update/trainee-result-ajax/getAllTopicFromLearnDetail",
            data: { traineeId: traineeId },
            success: function (data) {
                listTopic = data;
            }
        });
    }



    var listMinestone = [];
    var listTopic = [];

    // get the allocation status and prop it to select
    var allocation = $("#sel1").attr("allocation");
    $("#sel1 option").each(function () {
        if ($(this).text() == allocation) {
            $(this).prop('selected', true);
        }
    });

    //prop  milestone penalty
    $(".reward-penalty").each(function () {
        var mile = $(this).find(".rewpen").val();
        console.log($(this));
        $(this).children(".selectMilestone").find("option").each(function () {
            console.log($(this));
            if ($(this).text() == mile) {
                $(this).prop('selected', true);
            }
        });
    });

    ////VALIDATE DATA
    $("#btn-submit").click(function () {
        var check = true;
        $("#message").text("");

        if (!checkTopicMark() || !checkDatePenalty()) {
            check = false;
        }


        $('#milestoneCollapse input').each(function () {
            var txt = $(this).val();
            if (txt == "") {
                $(this).addClass("error-border");
                $("#message").append('please fill file data.<br />');
                check = false;
            } else {
                $(this).removeClass("error-border");
            }
        });
        
        $('.milestones').each(function () {
        	var mileStoneName = $(this).find('.milestoneName').text();
        	var checkCout = 0;
        	var startDate = $(this).find('.startDate').val();
        	var prevEndDate = $(this).prevUntil('.milestones').prev().find('.endDate').val();
        	if(prevEndDate != null && startDate != "" && prevEndDate != "" && Date.parse(prevEndDate)>Date.parse(startDate)){
        		$(this).find('.startDate').css('border', '2px solid red');
        		$("#message").append('“Start Date” must later than “End Date” of prev milestone.<br />');
        		 check = false;
        	}
        	$('.milestones').each(function () {
        		if($(this).find('.milestoneName').text() == mileStoneName){
        			checkCout++;
        		}
        	});
        	if(checkCout>1){
        		$("#message").append('duplicate milestone.<br />');
                $(this).find('.milestoneName').css('border', '2px solid red');
                check = false;
            } else {
              	$(this).find('.milestoneName').css('border','none');
            }
        });

        $('.milestones').each(function () {
            var startDateMilestone = $(this).find('.startDate');
            var endDateMilestone = $(this).find('.endDate');
            if (Date.parse(startDateMilestone.val()) > Date.parse(endDateMilestone.val())) {
                $(startDateMilestone).addClass("error-border");
                $("#message").append('“Start Date” must not be later than “End Date”.<br />');
                check = false;
            } else if(startDateMilestone.val() != "") {
                $(startDateMilestone).removeClass("error-border");
            }
        });
        //validate start date for allocation table
        var startDateCommitment = $('#start-date-commitment').val();
        var startDateAllocation = $('#start-date-allocation').val();
        if (Date.parse(startDateAllocation) < Date.parse(startDateCommitment)) {
            $("#start-date-allocation").addClass("error-border");
            $("#message").append('“Start Date” must be later than “Committed Working Start Date”.<br />');
            check = false;
        } else if(startDateAllocation != "") {
            $("#start-date-allocation").removeClass("error-border");
        }


        // validate select topic

        $(".topic-milestone").each(function () {
            var topic = $(this).val();
            var count = 0;
            $(".topic-milestone").each(function () {
                if ($(this).val() == topic) {
                    count = count + 1;
                }
            });
            if (count > 1) {
                $("#message").append('“Topic” must be unique.<br />');
                $(this).addClass("error-border");
                check = false;
            } else {
                $(this).removeClass("error-border");
            }
        });



        // validata score
        $(".score").each(function () {
            var input = $(this);
            if (isNaN($(this).val())) {
                $("#message").append('Please input number only.<br />');
                $(this).css('border', '1px solid red');
                check = false;
            } else if($(this).val() == ""){
            	 $(this).css('border', '1px solid red');
                 $("#message").append('please fill file data.<br />');
                 check = false;
            }
            else
            	$(this).css('border','none');
        });

        $(".bonus-point").each(function () {
        	if($(this).prop('disabled') == false) {
        		 var point = $(this).val();
                 if (point != null && point < 0 || point > 20) {
                     $("#message").append('0 ≤ “Bonus Point” ≤ 20.<br />');
                     $(this).addClass("error-border");
                     check = false;
                 } else {
                     $(this).removeClass("error-border");
                 }
        	} 
        });

        $(".penalty-point").each(function () {
        	if($(this).prop('disabled') == false) {
                var point = $(this).val();
                if (point != null && point < 0 || point > 20) {
                    $("#message").append('0 ≤ “Penalty Point” ≤ 20.<br />');
                    $(this).addClass("error-border");
                    check = false;
                } else {
                    $(this).removeClass("error-border");
                }
        	}
        });


        //check max score than pass score
        var listMaxCore = $(".max-score");
        var listPassCore = $(".pass-score");
        listPassCore.each(function (i, item) {
            if (parseInt($(listPassCore[i]).val()) > parseInt($(listMaxCore[i]).val())) {
                $("#message").append('“Passing Score” must not be greater than “Max Score”.<br />');
                $(this).addClass("error-border");
                check = false;
            } else {
                $(this).removeClass("error-border");
            }
        });

        // check score with maxsscore

        if (check) {
            $("#rejectClass").modal("show");
        } else {

            $("#rejectClass").modal("hide");
        }
        //
    });

    // click icon add more milestone
    // $("#icon-add-more-milestone").click(function () {
    $(document).off('click', '#icon-add-more-milestone').on('click', "#icon-add-more-milestone", function (e) {
        var now = new Date();
        var day = now.getDate()
        var month = now.getMonth() + 1;
        var year = now.getFullYear().toString().substring(2);
        var dateCode = `Thg${month}-${year}`;
        var today = now.getFullYear() + '-' + month + '-' + day;
        $(this).closest(".milestones").children(".milestoneName").text(dateCode);
        var content = `<tr class="milestones" index='${indexMilestone}'>
                        <td class="icon-delete-milestone"><a href="#"><i
                                    class="fas fa-trash-alt"></i></a>
                        </td>
                        <td colspan="2" class="milestoneName">${dateCode}</td>
                        <td colspan="2" class="td-input salaryPaid">
                            <select class="selectClass w-100">
                                <option value="yes">Yes</option>
                                <option value="no">No</option>
                            </select>
                        </td>
                        <td colspan="2" class="td-input">
                            <!-- <input class="w-75 datePicker" type="date" name="startDate" id="startDate"> -->
                            <input type="date" class="startDate" value='${today}' />
                        </td>
                        <td colspan="2" class="td-input">
                            <!-- <input class="w-75 datePicker" type="date" name="endDate" id="endDate"> -->
                            <input type="date" class="endDate" value='${today}' />
                        </td>
                    </tr>
            <!-- Topic header -->
                <tr class="list-topic">
                    <td></td>
                    <td class=""><a href="#"><i class="fas fa-plus-circle icon-add-more-topic" ></i></a>
                    </td>
                    <td class="th">Topic</td>
                    <td colspan="2" class="th">Max Score</td>
                    <td colspan="2" class="th">Passing Score</td>
                    <td colspan="2" class="th">Weighted Number</td>
                </tr>`;
        indexMilestone++;
        $("#milestoneCollapse").append(content);

        var milestone = $(".milestones:last");

        var milestoneName = $(".milestones:last .milestoneName").text();
        // add more row on attendace status table 
        //        $("#tbody-attendance-status").append(`
        $("#tbody-attendance-status .att-status-tr:last").after(`
            <tr class="attendace-row-n att-status-tr" index='${indexAttendanceStatus}'>
                <th class="th milestoneName " sDate='${today}' eDate=''>${milestoneName}</th>
                <td class="absentTime">0</td>
                <td class="lateOrEarly">0</td>
                <td class="noPermissionRate">0</td>
                <td class="disPoint">0</td>
            </tr>
        `);
        indexAttendanceStatus++;
        // add new area in table Topic Marks
        $("#tbody-toppic-mark #content").append(`
                <td class="tbl-area topicMarks col-4" index="${indexTopicMarks}">
                    <table>
                        <tr>
                            <td class="dateCode">${dateCode}</td>
                            <td>0%</td>
                        </tr>
                        <tr>
                            <td>Topic</td>
                            <td>Score</td>
                        </tr>
                    </table>
                </td>
        `);
        indexTopicMarks++;

        // add new row for GPA table
        $("#tbody-gpa").append(`
            <tr>
                <td class="th dateCode-gpa">${dateCode}</td>
                <td>0%</td>
                <td>0%</td>
                <td>0%</td>
                <td>0%</td>
                <td>0%</td>
            </tr>
       `);

        var selectList = document.createElement('select');
        $('.milestones').each(function () {
            var option = document.createElement('option');
            option.value = $(this).attr('index');
            option.text = $(this).find('.milestoneName').text();
            selectList.appendChild(option);
        });
        $(".selectMilestone").html(selectList);


    });

    //event onchange start date
    $(document).off('change', '.startDate').on('change', '.startDate', function () {
        //        var index = getIndex(this, "tr.milestones", "tr.milestones");
    	var startDate = $(this).val();
    	if(startDate != ""){
    		var index = parseInt($(this).closest('tr').attr('index'));
    		var arr = startDate.split('-');
    		var month = arr[1];
    		var year = arr[0].substring(2);
    		var dateCode = `Thg${month}-${year}`;
    		$(this).closest(".milestones").children(".milestoneName").text(dateCode);
    		$(this).closest(".milestones").children(".milestoneName").prop('sDate', startDate);
    		
    		$(`#tbody-attendance-status tr.attendace-row-n:nth-child(${index + 2}) th`).text(dateCode);
    		$(`#tbody-attendance-status tr.attendace-row-n:nth-child(${index + 2}) th`).attr('sDate', startDate);
    		$(`#tbody-toppic-mark #content td:nth-child(${index + 1})`).find(".dateCode").text(dateCode);
    		
    		$(`#tbody-gpa tr:nth-child(${index + 2})`).find(".dateCode-gpa").text(dateCode);
    		
    		var selectList = document.createElement('select');
    		$('.milestones').each(function () {
    			var option = document.createElement('option');
    			option.value = $(this).attr('index');
    			option.text = $(this).find('.milestoneName').text();
    			selectList.appendChild(option);
    		});
    		$(".selectMilestone").html(selectList);
    	}
    });
    
    //event onchange end date
    $(document).off('change', '.endDate').on('change', '.endDate', function () {
    	var endDate = $(this).val();
    	if(endDate != ""){
    		var index = parseInt($(this).closest('tr').attr('index'));
    		$(`#tbody-attendance-status tr.attendace-row-n:nth-child(${index + 2}) th`).attr('eDate', endDate);
    	}
    });


    // click to add more topic icon
    $(document).off('click', ".icon-add-more-topic").on('click', ".icon-add-more-topic", function (e) {
        //get list topic from database
        var n = listTopic.length;
        var selectListTopic = document.createElement('select');
        selectListTopic.setAttribute('class', 'topic-milestone');
        for (var i = 0; i < n; i++) {
            var option = document.createElement('option');
            option.value = listTopic[i].id;
            option.setAttribute('topicId', listTopic[i].topic.id);
            option.text = listTopic[i].topic.topicName;
            selectListTopic.appendChild(option);
        }
        //
        var indexTopic = $(this).closest('tr').nextUntil('.milestones').last().attr('index');
        if (indexTopic == null) {
            indexTopic = 0;
        } else {
            indexTopic = parseInt(indexTopic) + 1;
        }
        $(this).closest(".list-topic").nextUntil('.milestones').addBack().last().after(`
            <tr class="topics" index='${indexTopic}'>
                <td></td>
                <td><a href="#"><i class="fas fa-trash-alt icon-delete-topic"></i></a>
                </td>
                <td class="td-input listTopicName">
                    
                </td>
                <td colspan="2" class="td-input">
                    <input class="selectClass w-100 score max-score" type="number" />
                </td>
                <td colspan="2" class="td-input">
                    <input class="selectClass w-100 score pass-score" type="number" />
                </td>
                <td colspan="2" class="td-input">
                    <input class="selectClass w-100 scoreWn" type="number" />
                </td>
            </tr>
        `);
        //        $(".listTopicName:last").append(selectListTopic);
        $(this).closest(".list-topic").nextUntil('.milestones').last().find('.listTopicName').html(selectListTopic);

        index = parseInt($(this).closest('tr').prev().attr('index'));

        // event to selver topicmarks table

        var nameMilestone = $(this).closest('tr').prevAll('.milestones').first().find(".milestoneName").text();

        var nameTopic = $(this).closest('tr').nextUntil('.milestones').last().find('.listTopicName option:selected').text();
        $(`#tbody-toppic-mark #content .topicMarks:nth-child(${index + 1}) table tbody`)
            .append(`
            <tr index="${indexTopic}">
                <td class="topicName-topicMarks">${nameTopic}</td>
                <td class="td-input">
                    <input type="text"  class="score-topic score" mScore="" />
                </td>
            </tr>
        `);
    });

    //event onchange name of topic
    $(document).off('change', '.listTopicName').on('change', '.listTopicName', function () {
        var index = $(this).closest('tr').prevUntil('.milestones').last().prev().attr('index');
        if (index == null) {
            index = parseInt($(this).closest('tr').prev().attr('index'));
        } else {
            index = parseInt(index);
        }
        var topicName = $(this).find("option:selected").text();
        var topicId = $(this).find("option:selected").attr('topicId');
        var learnDetailId = $(this).find("option:selected").val();
        var indexOfTopic = parseInt($(this).closest('tr').attr('index'));
        $(`#tbody-toppic-mark #content td.topicMarks:nth-child(${index + 1})`)
            .find(`tr:nth-child(${indexOfTopic + 3}) td.topicName-topicMarks`).text(topicName);
        //        .css({"color": "red", "border": "2px solid red"});            
    });

    //event onchange maxscore
    $(document).off('change', '.max-score').on('change', '.max-score', function () {
        var index = $(this).closest('tr').prevUntil('.milestones').last().prev().attr('index');
        if (index == null) {
            index = parseInt($(this).closest('tr').prev().attr('index'));
        } else {
            index = parseInt(index);
        }
        var indexOfTopic = parseInt($(this).closest('tr').attr('index'));
        var mScore = $(this).val();
        $(`#tbody-toppic-mark #content td.topicMarks:nth-child(${index + 1})`)
            .find(`tr:nth-child(${indexOfTopic + 3}) .score-topic`).attr('mScore', mScore);
        //        .css({"color": "red", "border": "2px solid red"});            
    });

    // click delete milestone icon
    $(document).off('click', '.icon-delete-milestone').on('click', '.icon-delete-milestone', function (e) {
        //        var index = $('.milestones').index($(this).closest('tr'));
        var index = parseInt($(this).closest('tr').attr('index'));
        $(this).closest('.milestones').nextUntil(".milestones").addBack().remove();
        $(`#tbody-attendance-status tr.attendace-row-n:nth-child(${index + 2})`).remove();
        $(`#tbody-toppic-mark #content td:nth-child(${index + 1})`).remove();

        $(`#tbody-gpa tr:nth-child(${index + 2})`).remove();
        var selectList = document.createElement('select');
        $('.milestones').each(function () {
            var option = document.createElement('option');
            option.value = $(this).attr('index');
            option.text = $(this).find('.milestoneName').text();
            selectList.appendChild(option);
        });
        $(".selectMilestone").html(selectList);

    });

    // click delete topic icon
    $(document).off('click', '.icon-delete-topic').on('click', '.icon-delete-topic', function (e) {
        var index = $(this).closest('tr').prevUntil('.milestones').last().prev().attr('index');
        if (index == null) {
            index = parseInt($(this).closest('tr').prev().attr('index'));
        } else {
            index = parseInt(index);
        }
        var indexOfTopic = parseInt($(this).closest('tr').attr('index'));
        $(`#tbody-toppic-mark #content td.topicMarks:nth-child(${index + 1})`)
            .find(`tr:nth-child(${indexOfTopic + 3})`).remove();
        $(this).closest('.topics').remove();
    });


    $("#add-row-penalty-table").click(function () {
        var selectList = document.createElement('select');
        $('.milestones').each(function () {
            var option = document.createElement('option');
            option.value = $(this).attr('index');
            option.text = $(this).find('.milestoneName').text();
            selectList.appendChild(option);
        });
        var content = `
                    <tr class="reward-penalty">
                        <td>
                            <a>
                                <i class="far fa-trash-alt icon-remove-penalty"></i>
                            </a>
                        </td>
                        <td class="td-input selectMilestone"></td>
                        <td class="td-input">
                            <input class="date-penalty" type="date" />
                        </td>
                        <td class="td-input">
                            <input class="bonus-point" type="number" />
                        </td>
                        <td class="td-input ">
                            <input class="penalty-point" type="number" />
                        </td>
                        <td class="td-input">
                            <input class="reason" type="text" />
                        </td>
                    </tr>
                 `;
        $("#tbody-reward-penalty").append(content);
        $(".selectMilestone:last").append(selectList);
    });


  //FUNCTION CHANGE Event of Bonus and Penalty Point
    $(document).on('change', ".bonus-point", function() {
    	
    	if($(this).val()!=0) {
        	$(this).parent().next().find(".penalty-point").prop('disabled', true);
        }
    	else {
    		$(this).parent().next().find(".penalty-point").prop('disabled', false);
    	}
    });
    $(document).on('change', ".penalty-point", function() {

    	 if($(this).val()!=0) {
    		 $(this).parent().prev().find(".bonus-point").prop('disabled', true);
    	    }
    	 else {
    		 $(this).parent().prev().find(".bonus-point").prop('disabled', false);
    	 }
    });


    $(document).off('click', '.icon-remove-penalty').on('click', '.icon-remove-penalty', function (e) {
        $(this).closest('tr').remove();
    });

    function checkTopicMark() {
        var check = true;
        $('#tbody-toppic-mark #content .score-topic').each(function () {
            var score = $(this).val();
            var maxScore = $(this).attr('mScore');
            if (parseInt(score) > parseInt(maxScore)) {
                $(this).css('border', '1px solid red');
                $("#message").append('“Score” must not be greater than “Max Score”.<br />');
                check = false;
            } else {
                $(this).css('border', 'none');
            }

        });
        return check;
    }

    function checkDatePenalty() {
        var check = true;
        $('.date-penalty').each(function () {
            var datePenalty = $(this);
            var date = $(this).val();
            var index = $(this).closest('tr').find('.selectMilestone option:selected').val();
            $('.milestones').each(function () {
                if (index == $(this).attr('index')) {
                    var startDate = $(this).find('.startDate').val();
                    var endDate = $(this).find('.endDate').val();
                    if (Date.parse(date) < Date.parse(startDate) || Date.parse(date) > Date.parse(endDate)) {
                        $(datePenalty).addClass("error-border");
                        $("#message").append('“Date” must be in “Milestone”.<br />');
                        check = false;
                    } else {
                        $(datePenalty).removeClass("error-border");
                    }
                }
            });
        });
        return check;
    }

    //////////////////////////////TINH TOAN/////////////////////////////

    var indexOfAttendanceStatus = -1;
    //get modal attendace status
    $(document).off('click', '.attendace-row-n').on('click', '.attendace-row-n', function (e) {
        var code = $(this).find('.milestoneName').text();
        var startDate = $(this).find('.milestoneName').attr('sDate');
        indexOfAttendanceStatus = $('#tbody-attendance-status tr').index($(this));
        var endDate = $(this).find('.milestoneName').attr('eDate');
        //        alert('index of row in attendance status: '+indexOfAttendanceStatus);
        // truong hop du lieu dc lay tu database ra de hien thi trong entity AttendanceStatus
        // khong co attribute date
        if (startDate == '') {
        	$('.milestones').each(function(){
        		if($(this).find('.milestoneName').text()==code){
        			startDate = $(this).find('.startDate').val();
        			endDate = $(this).find('.endDate').val();
        			return;
        		}
        	});
        	// truong hop nguoi dung k nhap start date ma nhan de hien thi modal attendant status
        	if (startDate == '') {
	            //        	 Thg3-20
	            var arr = code.split('-');
	            var month = arr[0].substring(3);
	            var year = '20' + arr[1];
	            startDate = year + '-' + month + '-02';
	            endDate = "";
        	}
        	//tuong hop khong hop le: start date > end date ==> hien thi tat ca cac ngay trong thang cua start date
        	if(startDate != "" && endDate != "" && (Date.parse(startDate) > Date.parse(endDate))){
        		endDate = "";
        	}
        }

        $.get({
            url: "/trainee-management/update/trainee-result-ajax/modal-attendace-status", 
            data: {
                dateCode	: code,
                startDate	: startDate,
                endDate		: endDate 
            },
            success: function (data) {
                $("#modal-attendace-status").html(data);
                $("#modalViewAttendence").modal("show");
            }
        });
    });


        $(document).off('click', '#btn-update-attendance-status').on('click', '#btn-update-attendance-status', function (e) {
            var days = $('#modalUpdateAttendence th:last').text();
            var countP = 0, countA = 0, countE = 0, countL = 0;
            var countAn = 0, countEn = 0, countLn = 0;
            var value = '';
            $('#modalUpdateAttendence .check-update-attendace-status').each(function () {
                value = $(this).val();
                if (value == 'P')
                    countP++;
                else if (value == 'A')
                    countA++;
                else if (value == 'E')
                    countE++;
                else if (value == 'L')
                    countL++;
                else if (value == 'An')
                    countAn++;
                else if (value == 'En')
                    countEn++;
                else
                    countLn++;
            });

            var absenTime = countA + countAn;
            var lateOrEarly = countL + countLn + countE + countEn;
            var noPermission = ((countAn + countLn + countEn) / (absenTime + lateOrEarly)) * 100;
            if (isNaN(noPermission)) {
                noPermission = 0;
            }
            var notPresentTime = (((lateOrEarly / 2) + absenTime) / (countP + lateOrEarly)) * 100;
            if (isNaN(notPresentTime)) {
                notPresentTime = 0;
            }
            var notAttendanceRate = (notPresentTime / (countP + lateOrEarly)) * 100;
            if (isNaN(notAttendanceRate)) {
                notAttendanceRate = 0;
            }
            var disciplinaryPoint = 0;
            if (notPresentTime <= 5)
                disciplinaryPoint = 100;
            else if (notAttendanceRate > 5 && notAttendanceRate <= 20)
                disciplinaryPoint = 80;
            else if (notAttendanceRate <= 30)
                disciplinaryPoint = 60;
            else if (notAttendanceRate < 50)
                disciplinaryPoint = 50
            else if (notAttendanceRate >= 50 || noPermission < 20)
                disciplinaryPoint = 20;
            else
                disciplinaryPoint = 0;
            noPermission = Math.round(noPermission);
            //        disciplinaryPoint = Math.round(disciplinaryPoint * 100) / 100;
            disciplinaryPoint = Math.round(disciplinaryPoint);
            $(`#tbody-attendance-status tr:nth-child(${indexOfAttendanceStatus + 1})`)
                .find('.absentTime').text(absenTime);
            $(`#tbody-attendance-status tr:nth-child(${indexOfAttendanceStatus + 1})`)
                .find('.lateOrEarly').text(lateOrEarly);
            $(`#tbody-attendance-status tr:nth-child(${indexOfAttendanceStatus + 1})`)
                .find('.noPermissionRate').text(noPermission + '%');
            $(`#tbody-attendance-status tr:nth-child(${indexOfAttendanceStatus + 1})`)
                .find('.disPoint').text(disciplinaryPoint + '%');
            calculateFinal();
        });

        calculateFinal();
        function calculateFinal() {
            var absentTimeFinal = 0;
            var lateOrEarlyFinal = 0;
            var noPermissionFinal = 0;
            var disciplinaryPointFinal = 0;
            var count = 0;
            $("#tbody-attendance-status .attendace-row-n").each(function () {
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
            if (isNaN(noPermissionFinal)) {
                noPermissionFinal = 0;
            } else {
                noPermissionFinal = Math.round(noPermissionFinal);
            }
            disciplinaryPointFinal = disciplinaryPointFinal / count;
            if (isNaN(disciplinaryPointFinal)) {
                disciplinaryPointFinal = 0;
            } else {
                disciplinaryPointFinal = Math.round(disciplinaryPointFinal);
            }
            $('#fn-absent-time').text(absentTimeFinal);
            $('#fn-lastOrEarly').text(lateOrEarlyFinal);
            $('#fn-no-permission').text(noPermissionFinal + '%');
            $('#fn-dis-point').text(disciplinaryPointFinal + '%');
        }

        // xu ly su kien double click vao opton cua learning path
        $(document).off('dblclick', '#learningCode li').on('dblclick', '#learningCode li', function (e) {
            var learnCode = $(this).text();
            $.post({
                url: "/trainee-management/update/trainee-result-ajax/getTopicOfLearningDetail",
                data: { learnCode: learnCode },
                success: function (data) {
                    $('#modal-add-learning-path').html(data);
                    $('#modalAddLearningPath').modal('show');
                },
                error: function () {
                    alert("error");
                }
            });
        });

        $(document).off('click', '#learningCode li').on('click', '#learningCode li', function (e) {
            e.preventDefault();
            e.stopPropagation();
            //	    	var learnCodeOfTrainee = $('#learningCode').attr('lcode');
            var learCode = $(this).text();
            var buttonStr = $('#content-btn-learnCode').text();
            if (learningCode == "") {
                $('#content-btn-learnCode').text(learCode);
                $.get({
                    url: "/trainee-management/update/trainee-result-ajax/getAllTopicByLearCode",
                    data: { learCode: learCode },
                    success: function (data) {
                        listTopic = data;
                        var n = listTopic.length;
                        var selectListTopic = document.createElement('select');
                        selectListTopic.setAttribute('class', 'topic-milestone');
                        for (var i = 0; i < n; i++) {
                            var option = document.createElement('option');
                            option.value = listTopic[i].id;
                            option.setAttribute('topicId', listTopic[i].topic.id);
                            option.text = listTopic[i].topic.topicName;
                            selectListTopic.appendChild(option);
                        }
                        $('.topics').find('.listTopicName').html(selectListTopic);
                    }
                });
            }
        });

    });