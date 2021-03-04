<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet"
	href='<c:url value="/resources/css/trainee-result.css"></c:url>'>
<h3 class="pt-3">Trainee profile</h3>

<!-- tabs -->
<ul class="nav nav-tabs">
	<li class="nav-item"><a class="nav-link"
		href="trainee-profile-view.html" data-target="#traineeInformation"
		id="trainee-information">Trainee Information</a></li>
	<li class="nav-item"><a class="nav-link active" data-toggle="tab"
		href="#trainingResult" id="training-result">Training Result</a></li>
</ul>

<!-- tab area -->
<input type="hidden" id="empid" value="${sessionScope.empId}"}>
<div id="trainingResult" class="container-fluid tab-pane active">
	<br>
	<form action="">
		<table class="table table-bordered">
			<tr>
				<td colspan="9" class="text-black"
					style="background-color: rgb(139, 176, 243);"><a
					class="btn btn-primary text-decoration-none" data-toggle="collapse"
					href="#milestoneCollapse" role="button"> <i
						class="fas fa-sort-down"></i>
				</a> <span class="left-border">Milestone Configuration</span></td>
			</tr>
			<tbody class="collapse show" id="milestoneCollapse">
				 <c:set var="totalMaxScore" value="${0}" />
                 <c:set var="totalPassScore" value="${0}" />
                 <c:set var="totalWNumber" value="${0}" />
				<c:forEach var="lp" items="${listOfLearningPath}">
				  <c:set var="totalMaxScore" value="${totalMaxScore + (lp.maxScore*lp.weightedNumber)}" />
				  <c:set var="totalPassScore" value="${totalPassScore + (lp.passScore*lp.weightedNumber)}" />
				  <c:set var="totalWNumber" value="${totalWNumber + lp.weightedNumber}" />
				</c:forEach>
                <tr>
                    <td class="th">Total Max Score</td>
                    <td>${totalMaxScore}</td>
                    <td class="th">Total Passing Score</td>
                    <td>${totalPassScore}</td>
                    <td class="th">Total Weighted Number</td>
                    <td>${totalWNumber}</td>
                    <td class="th">Learning Path</td>
                    <td>
                       ${listOfLearningPath[0].learningCode.learnCode}
		             </td>
                 </tr>
                 <tr>
                     <td colspan="2" class="th">Milestone Name</td>
                     <td colspan="2" class="th">Salary Paid</td>
                     <td colspan="2" class="th">Start Date</td>
                     <td colspan="2" class="th">End Date</td>

                 </tr>
                 <c:forEach items="${listOfMilestone}" var="milestone" varStatus="i">
                 <tr>
                  <td colspan="2">${milestone.milestoneName}</td>
                  <td colspan="2">${milestone.salaryPaid}</td>
                  <td colspan="2">
	                  <fmt:parseDate value="${milestone.startDate}" pattern="yyyy-MM-dd" var="parsedStartDate" type="date" />
					  <fmt:formatDate value="${parsedStartDate}" var="newParsedStartDate" type="date" pattern="dd-MM-yyyy" />
					  ${newParsedStartDate}
                  </td>
                  <td colspan="2">
	                  <fmt:parseDate value="${milestone.endDate}" pattern="yyyy-MM-dd" var="parsedEndDate" type="date" />
					  <fmt:formatDate value="${parsedEndDate}" var="newParsedEndDate" type="date" pattern="dd-MM-yyyy" />
					  ${newParsedEndDate}
                  </td>
             	</tr>
		           <!-- Topic header -->
	               <tr class="list-topic">
	                   <td></td>
	                   <td class="th">Topic</td>
	                   <td colspan="2" class="th">Max Score</td>
	                   <td colspan="2" class="th">Passing Score</td>
	                   <td colspan="2" class="th">Weighted Number</td>
	               </tr>
	               <c:forEach items="${listOfLearningPath}" var="detail">
	               <c:if test="${detail.milestoneName==milestone.milestoneName}">
	               	<tr>
		                <td></td>
		                <td>${detail.learningCode.topic.topicName}</td>
		                <td colspan="2">${detail.maxScore}</td>
		                <td colspan="2">${detail.passScore}</td>
		                <td colspan="2">${detail.weightedNumber}</td>
		            </tr>
	               </c:if>
		               </c:forEach>
		           </c:forEach>
			</tbody>
		</table>
		<table class="table table-bordered">
			<thead>
				<tr>
					<td colspan="8" class="text-black"
						style="background-color: rgb(142, 179, 247);"><span> <a
							class="btn btn-primary" data-toggle="collapse"
							href="#tbody-attendance-status-view" role="button"> <i
								class="fas fa-sort-down"></i>
						</a>
					</span> <span>Attendace status</span>
				</tr>
			</thead>
			<tbody class="collapse show" id="tbody-attendance-status-view">
                                <tr class="att-status-tr">
                                    <th></th>
                                    <th>Absent Times</th>
                                    <th>Late & Early Leave</th>
                                    <th>No permisstions Rate</th>
                                    <th>Disciplinary Point</th>
                                </tr>
                                <c:forEach items="${listOfAttendaceStatus}" var="at" varStatus="i"> 
	                                <tr class="attendace-row-n att-status-tr" index="${i.index}">
						                <th class="th milestoneName" id='${at.id}' sDate='' eDate=''>${at.milestoneName}</th>
						                <td class="absentTime">${at.absentTime}</td>
						                <td class="lateOrEarly">${at.lateOrEarlyLeave}</td>
						                <td class="noPermissionRate">${at.noPermissionsRate}%</td>
						                <td class="disPoint">${at.disciplinaryPoint}%</td>
            						</tr>
                                </c:forEach>
                                <tr id="att-status-tr-final">
                                    <th>Final</th>
                                    <td id="fn-absent-time-view">0</td>
                                    <td id="fn-lastOrEarly-view">0</td>
                                    <td id="fn-no-permission-view">0</td>
                                    <td id="fn-dis-point-view">0</td>
                                </tr>
                                <!-- Here to click to display modal-->
                                
                            </tbody>

		</table>
		<!-- Table Topic mark -->
		<table class="table table-bordered">
			<thead>
				<tr>
					<td colspan="8" class="text-black"
						style="background-color: rgb(142, 179, 247);"><span> <a
							class="btn btn-primary" data-toggle="collapse"
							href="#tbody-toppic-mark" role="button" aria-expanded="false"
							aria-controls="collapseExample"> <i class="fas fa-sort-down"></i>
						</a>
					</span> <span>Topic Mark</span>
				</tr>
			</thead>
			<tbody class="collapse show">
                                <tr class="row" id="content" style="padding:0 15px;">
                                    <!-- content of table -->
        								<c:forEach items="${listOfMilestone}" var="milestone" varStatus="i">
	        								<td class="tbl-area topicMarks col-4" index="${i.index}">
		                                     	<table>
		                                     	 <c:set var="scoreOfMilestone" value="${0}" />
		                                     	 <c:set var="maxScoreOfMilestone" value="${0}" />
		                                     	 	<c:forEach items="${listOfLearningPath}" var="lp">
										                <c:if test="${lp.milestoneName==milestone.milestoneName}">
							                        	 <c:set var="scoreOfMilestone" value="${scoreOfMilestone + (lp.score*lp.weightedNumber)}" />
							                        	 <c:set var="maxScoreOfMilestone" value="${maxScoreOfMilestone + (lp.maxScore*lp.weightedNumber)}" />
											            </c:if>
							                        </c:forEach>
		                                     	 <c:set var="AvgScoreOfMilestone" value="${0}" />
		                                     	 <c:if test="${maxScoreOfMilestone!=0}">
		                                     	 	 <c:set var="AvgScoreOfMilestone" value="${scoreOfMilestone/maxScoreOfMilestone*100}" />
		                                     	 </c:if>
							                        <tr>
							                            <td class="dateCode">${milestone.milestoneName}</td>
							                            <td>${Math.round(AvgScoreOfMilestone)}%</td>
							                        </tr>
							                         <tr>
							                            <td>Topic</td>
							                            <td>Score</td>
							                        </tr>
							                        <%int ind=0; %>
										                <c:forEach items="${listOfLearningPath}" var="detail">
										                <c:if test="${detail.milestoneName==milestone.milestoneName}">
							                        	 <tr index="<%=ind%>"><%ind++; %>
											                <td class="topicName-topicMarks">${detail.learningCode.topic.topicName}</td>
											                <td>${detail.score}</td>
											            </tr>
											            </c:if>
							                        </c:forEach>
							                    </table>
						                	</td>
		                                </c:forEach>
                                </tr>
                                <tr class="row" style="padding: 0 15px;">
                                    <td class="col-11">
                                        Final
                                    </td>
                                    <td class="col-1">
                                        ${finalTopicMark}%
                                    </td>
                                </tr>
                            </tbody>

	</table>
	
	<!-- Table Reward & Penalty -->
		<table class="table table-bordered">
			<thead>
				<tr>
					<td colspan="8" class="text-black"
						style="background-color: rgb(142, 179, 247);"><span> <a
							class="btn btn-primary" data-toggle="collapse"
							href="#tbody-reward-penalty" role="button" aria-expanded="false"
							aria-controls="collapseExample"> <i class="fas fa-sort-down"></i>
						</a>
					</span> <span>Reward & Penalty</span>
				</tr>
			</thead>
			<tbody class="collapse show" id="tbody-reward-penalty">
				<tr>

					<th>Milestone</th>
					<th>Date</th>
					<th>Bonus Point</th>
					<th>Penalty Point</th>
					<th>Reason</th>
				</tr>
					<c:forEach items="${listRewPen}" var="rp">
						<tr>
							<td class="th">${rp.milestoneName}</td>
							<td>${rp.createdDate}</td>
							<td>${rp.bonusPoint}</td>
							<td>${rp.penaltyPoint}</td>
							<td>${rp.reason}</td>
						</tr>
					</c:forEach>
			</tbody>

		</table>

		<!-- Table GPA -->
		<table class="table table-bordered" id="gpa">
			<thead>
				<tr>
					<td colspan="8" class="text-black"
						style="background-color: rgb(149, 188, 242);"><span> <a
							class="btn btn-primary" data-toggle="collapse" href="#tbody-gpa"
							role="button" aria-expanded="false"
							aria-controls="collapseExample"> <i class="fas fa-sort-down"></i>
						</a>
					</span> <span>GPA</span>
				</tr>
			</thead>
			<tbody id="tbody-gpa">
				<tr>
					<th></th>
					<th>Accademic Mark</th>
					<th>Discipline Point</th>
					<th>Bonus</th>
					<th>Penalty</th>
					<th>GPA</th>
				</tr>
				<c:forEach items="${listgpa}" var="gpa">
					<tr>
						<td class="th">${gpa.milestoneName}</td>
						<td><fmt:formatNumber type = "percent" value = "${gpa.acacdemicMark}" /></td>
						<td><fmt:formatNumber type = "percent" value = "${gpa.disciplinaryPoint}" /> </td>
						<td>${gpa.bonus *10} %</td>
						<td>${gpa.penalty *10} %</td>
						<td><fmt:formatNumber type = "percent" value = "${gpa.gpa}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


		<!-- Table Commitment-->
		<table class="table table-bordered" id="gpa">
			<thead>
				<tr>
					<td colspan="8" class="text-black"
						style="background-color: rgb(149, 188, 242);"><span> <a
							class="btn btn-primary" data-toggle="collapse"
							href="#tbody-commit" role="button" aria-expanded="false"
							aria-controls="collapseExample"> <i class="fas fa-sort-down"></i>
						</a>
					</span> <span>Commitment</span>
				</tr>
			</thead>
			<tbody id="tbody-commit">
				<tr>

					<th>Committed Value</th>
					<th>Commited Working Duration</th>
					<th>Commited Working Start Date</th>
					<th>Commited Working End Date</th>
					<th>Remarks</th>
				</tr>
				<tr>
					<fmt:parseDate value="${commit.startDate}" pattern="yyyy-MM-dd" var="startDateCommit" type="date" />
					<fmt:formatDate value="${startDateCommit}" var="newStartDateCommit" type="date" pattern="dd/MM/yyyy" />
					<fmt:parseDate value="${commit.endDate}" pattern="yyyy-MM-dd" var="endDateCommit" type="date" />
					<fmt:formatDate value="${endDateCommit}" var="newEndDateCommit" type="date" pattern="dd/MM/yyyy" />
					<td><fmt:formatNumber type="number" pattern="###,###" value="${commit.commitmentValue}"></fmt:formatNumber></td>
					<td>${commit.workingDuration}</td>
					<td>${newStartDateCommit}</td>
					<td>${newEndDateCommit}</td>
					<td class="">${commit.remark}</td>
				</tr>

			</tbody>
		</table>

		<!-- Table Allowance -->
		<table class="table table-bordered" id="allowance">
			<thead>
				<tr>
					<td colspan="8" class="text-black"
						style="background-color: rgb(149, 188, 242);"><span> <a
							class="btn btn-primary" data-toggle="collapse"
							href="#tbody-allowance" role="button" aria-expanded="false"
							aria-controls="collapseExample"> <i class="fas fa-sort-down"></i>
						</a>
					</span> <span>Allowance</span>
				</tr>
			</thead>
			<tbody id="tbody-allowance">
			
				<tr>
					<th></th>
					<th>GPA</th>
					<th>Level</th>
					<th>Salary Paid</th>
					<th>Standard Allowance</th>
					<th>Remarks</th>
				</tr>

				<c:forEach items="${listallowance}" var="allowance">
				
                  <fmt:formatNumber type="number" pattern="###,###" value="${allowance.standardAllowance}" var="allowanceFormat" />
					<tr>
						<td class="th">${allowance.milestoneName}</td>
						<td><fmt:formatNumber type = "percent" value = "${allowance.gpa}"/></td>
						<td>${allowance.level}</td>
						<td>${allowance.salaryPaid}</td>
						<td>${allowanceFormat}</td>
						<td class="">${allowance.remarks}</td>
					</tr>
					<c:set var="totalsalary" value="${totalsalary + allowance.standardAllowance}"></c:set>
				</c:forEach>
				<tr>
					<td class="th" colspan="4">Total</td>
                  <fmt:formatNumber type="number" pattern="###,###" value="${totalsalary}" var="totalFormat" />
					<td colspan="2"><c:out value="${totalFormat}"></c:out> </td>
				</tr>
			</tbody>
		</table>

		<!-- Table Allocation -->
		<table class="table table-bordered" id="allocation">
			<thead>
				<tr>
					<td colspan="8" class="text-black"
						style="background-color: rgb(149, 188, 242);"><span> <a
							class="btn btn-primary" data-toggle="collapse"
							href="#tbody-allocation" role="button" aria-expanded="false"
							aria-controls="collapseExample"> <i class="fas fa-sort-down"></i>
						</a>
					</span> <span>Allocation</span>
				</tr>
			</thead>
			<tbody id="tbody-allocation">
				<tr>
					<th>Allocated FSU</th>
					<th>Salary</th>
					<th>Start Date</th>
					<th>Allocation Status</th>
					<th>Remarks</th>
				</tr>
				<tr>
					<td class="">${allocate.allocatedFsu}</td>
					<td class=""><fmt:formatNumber type="number" pattern="###,###" value="${allocate.salary}"></fmt:formatNumber></td>
					<fmt:parseDate value="${allocate.startDate}" pattern="yyyy-MM-dd" var="startDateAlloc" type="date" />
					<fmt:formatDate value="${startDateAlloc}" var="newStartDateAlloc" type="date" pattern="dd/MM/yyyy" />
					<td class="">${newStartDateAlloc}</td>
					<td class="">${allocate.allocationStatus}</td>
					<td class="">${allocate.remarks}</td>
				</tr>
			</tbody>
		</table>
		<!-- update trainee -->
		<a href="#" id="update-result">
			<button type="button" class="button btn">
				<i class="fa fa-arrow-circle-up mr-1"
					style="font: size 20px; color: cornflowerblue;"></i>Update Trainee

			</button>
		</a>
		<button type="button" class="button btn" data-toggle="modal" data-target="#modalDelete"> <i
                class="fa fa-times" style="color: crimson;"></i> Delete trainee</button>
		<!-- delete modal-->
        <div id="modalDelete" class="modal fade" role="dialog">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Confirm Delete</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body text-center">
                        <i class="fas fa-info-circle mr-2" style="color: green;"></i>Are you sure want to delete ?
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="delete-confirm" class="btn btn-primary">
                            Yes</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                    </div>
                </div>
            </div>
        </div>
		<a href="#" id="close-btn">
			<button type="button" class="button btn btn-default mr-2"
				data-dismiss="modal">
				<i class="fas fa-reply" style="color: crimson;"></i>Close
			</button>
		</a>
	</form>
</div>

<script
	src='<c:url value="/resources/js/trainee-result-view-ajax.js"></c:url>'></script>
<script>
	var absentTimeFinal = 0;
	var lateOrEarlyFinal = 0;
	var noPermissionFinal = 0;
	var disciplinaryPointFinal = 0;
	var count = 0;
	$("#tbody-attendance-status-view .attendace-row-n").each(function () {
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
	$('#fn-absent-time-view').text(absentTimeFinal);
	$('#fn-lastOrEarly-view').text(lateOrEarlyFinal);
	$('#fn-no-permission-view').text(noPermissionFinal + '%');
	$('#fn-dis-point-view').text(disciplinaryPointFinal + '%');
</script>
