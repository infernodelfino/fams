<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" href= '<c:url value="/resources/css/trainee-result-update-result.css"></c:url>'>
                <h3 class="pt-3">Trainee profile</h3>

                <!-- tabs -->
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#traineeInformation" id="trainee-information" >Trainee Information</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#trainingResult" id="training-result">Training Result</a>
                    </li>

                </ul>

                <!-- tab area -->
                <div id="trainingResult" class="container-fluid tab-pane active"><br>
                    <!-- Place Modal Here-->
                    <!-- First modal to view Attendence Status-->
                    <div id="modal-attendace-status">
                    	<!-- <div id="modalViewAttendence" class="modal fade" role="dialog">
                    	</div> -->
                        <!-- Modal content -->
                    </div>
                    <div id="modal-add-learning-path">
                    	
                    </div>
                    
                    <!-- ///////////////////modal/////////////// -->
                    <div style="text-align: center; color: red;" id="message"></div>
                    <br />
                    <form action="">
                        <table class="table table-bordered">
                            <tr>
                                <td colspan="9" class="text-black" style="background-color:rgb(139, 176, 243);">
                                    <a class="btn btn-primary text-decoration-none border" data-toggle="collapse"
                                        href="#milestoneCollapse" role="button">
                                        <i class="fas fa-sort-down"></i>
                                    </a>
                                    <span class="left-border">Milestone Configuration</span>
                                    	<div id="traineeId" hidden>${traineeId}</div>
                                    		<%-- <c:if test="${listOfAttendaceStatus.length>0}">${listOfAttendaceStatus[0].trainee.id}</c:if> --%>
                                </td>
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
                                    <td colspan="2" class="th">Total Max Score</td>
                                    <td>${totalMaxScore}</td>
                                    <td class="th">Total Passing Score</td>
                                    <td>${totalPassScore}</td>
                                    <td class="th">Total Weighted Number</td>
                                    <td>${totalWNumber}</td>
                                    <td class="th">Learning Path</td>
                                    <td class="td-input dropdown" id="learningCode" lcode="${listOfLearningPath[0].learningCode.learnCode}">
                                        <%-- <c:if test="${listOfLearningPath.length>0}">${listOfLearningPath[0].learningCode.learnCode}</c:if> --%>
                                        <button class="w-100 btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">
                                        <span id="content-btn-learnCode">${listOfLearningPath[0].learningCode.learnCode}</span>
									    <span class="caret"></span></button>
									    <ul class="dropdown-menu dropdown-content dropdown-menu dropdown-menu-right" role="menu" aria-labelledby="menu1">
										    <c:forEach items="${listLearingPathDetail}" var="lp">
									      		<li role="presentation"><a role="menuitem" tabindex="-1" href="#">${lp}</a></li>
		                                    </c:forEach>
									     </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td id="icon-add-more-milestone"><a href="#"><i class="fas fa-plus-circle"></i></a>
                                    </td>
                                    <td colspan="2" class="th">Milestone Name</td>
                                    <td colspan="2" class="th">Salary Paid</td>
                                    <td colspan="2" class="th">Start Date</td>
                                    <td colspan="2" class="th">End Date</td>

                                </tr>
                                
                                <!-- //////////////////////////////////// -->
                                <c:forEach items="${listOfMilestone}" var="milestone" varStatus="i">
                                	<tr class="milestones" index="${i.index}">
				                        <td class="icon-delete-milestone"><a href="#"><i
				                                    class="fas fa-trash-alt"></i></a>
				                        </td>
				                        <td colspan="2" class="milestoneName">${milestone.milestoneName}</td>
				                        <td colspan="2" class="td-input salaryPaid">
				                            <select class="selectClass w-100">
				                                <option value="Yes" ${milestone.salaryPaid=='Yes' ? 'selected' : '' }>Yes</option>
				                                <option value="No" ${milestone.salaryPaid=='No' ? 'selected' : '' }>No</option>
				                            </select>
				                        </td>
				                        <td colspan="2" class="td-input">
				                            <!-- <input class="w-75 datePicker" type="date" name="startDate" id="startDate"> -->
				                            <input type="date" class="startDate" value='${milestone.startDate}' />
				                        </td>
				                        <td colspan="2" class="td-input">
				                            <!-- <input class="w-75 datePicker" type="date" name="endDate" id="endDate"> -->
				                            <input type="date" class="endDate" value='${milestone.endDate}' />
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
					                </tr>
					                <%int ind=0;%>
					                <c:forEach items="${listOfLearningPath}" var="detail">
					                <c:if test="${detail.milestoneName==milestone.milestoneName}">
					                	<tr class="topics" index="<%=ind%>"><%ind++; %>
							                <td></td>
							                <td><a href="#"><i class="fas fa-trash-alt icon-delete-topic"></i></a>
							                </td>
							                <td class="td-input listTopicName" id='${detail.id}'>
							                     <select class="selectClass w-100 topic-milestone">
							                        <c:forEach items="${listOfTopicFromLearningDetail}" var="topicFromLearnDetail">
							                        	<option value="${topicFromLearnDetail.id}" topicId='${topicFromLearnDetail.topic.id}'
							                        		 ${topicFromLearnDetail.topic.id==detail.learningCode.topic.id ? 'selected' : '' }>${topicFromLearnDetail.topic.topicName}</option>
							                        </c:forEach>
							                    </select>
							                </td>
							                <td colspan="2" class="td-input">
							                    <input class="selectClass w-100 score max-score" type="number" value="${detail.maxScore}" />
							                </td>
							                <td colspan="2" class="td-input">
							                    <input class="selectClass w-100 score pass-score" type="number" value="${detail.passScore}" />
							                </td>
							                <td colspan="2" class="td-input">
							                    <input class="selectClass w-100 scoreWn" type="number" score='${detail.score}' value="${detail.weightedNumber}" />
							                </td>
							            </tr>
					                </c:if>
					                </c:forEach>
					                
                                </c:forEach>
                                
				                
				                <!-- ////////////////////////////////////// -->
                                <!-- bbbbbb -->
                                <!-- content will add more here -->
                            </tbody>
                        </table>
                        <!-- table Attendace status -->
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <td colspan="8" class="text-black" style="background-color:rgb(142, 179, 247);">
                                        <span>
                                            <a class="btn btn-primary" data-toggle="collapse"
                                                href="#tbody-attendance-status" role="button">
                                                <i class="fas fa-sort-down"></i>
                                            </a>
                                        </span>
                                        <span>Attendace status</span>
                                </tr>
                            </thead>
                            <tbody class="collapse show" id="tbody-attendance-status">
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
                                    <td id="fn-absent-time">0</td>
                                    <td id="fn-lastOrEarly">0</td>
                                    <td id="fn-no-permission">0</td>
                                    <td id="fn-dis-point">0</td>
                                </tr>
                                <!-- Here to click to display modal-->
                                
                            </tbody>

                        </table>
                        <!-- Table Topic mark -->
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <td colspan="8" class="text-black" style="background-color:rgb(142, 179, 247);">
                                        <span>
                                            <a class="btn btn-primary" data-toggle="collapse" href="#tbody-toppic-mark"
                                                role="button" aria-expanded="false" aria-controls="collapseExample">
                                                <i class="fas fa-sort-down"></i>
                                            </a>
                                        </span>
                                        <span>Topic Mark</span>
                                </tr>
                            </thead>
                            <tbody class="collapse show" id="tbody-toppic-mark">
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
											                <td class="td-input">
											                    <input type="text" class="score-topic score" mScore="${detail.maxScore}" value="${detail.score}" />
											                </td>
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
					<th><a> <i class="fas fa-plus-circle"
							id="add-row-penalty-table"></i>
					</a></th>
					<th>Milestone</th>
					<th>Date</th>
					<th>Bonus Point</th>
					<th>Penalty Point</th>
					<th>Reason</th>
				</tr>
				<!-- content continue of table -->
				<c:forEach items="${listRewPen}" var="rp">
					<tr class="reward-penalty">
						<td><a> <i class="far fa-trash-alt icon-remove-penalty"></i></a></td>
						<td class="td-input selectMilestone"><input class="rewpen"
							type="hidden" value="${rp.milestoneName}" idrewpen="${rp.id}">
							<select class="selectClass w-100">
								<c:forEach items="${mileStoneRewPen}" var="mst">
									<option>${mst}</option>
								</c:forEach>
						</select></td>
						<td class="td-input"><input class="date-penalty" type="date"
							value="${rp.createdDate}" /></td>
						<td class="td-input"><input class="bonus-point" type="number"
							value="${rp.bonusPoint}" /></td>
						<td class="td-input"><input class="penalty-point"
							type="number" value="${rp.penaltyPoint}" /></td>
						<td class="td-input"><input class="reason" type="text"
							value="${rp.reason}" /></td>
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
			<tbody id="tbody-gpa" class="collapse show">
				<tr>
					<th></th>
					<th>Academic Mark</th>
					<th>Disciplinary</th>
					<th>Bonus</th>
					<th>Penalty</th>
					<th>GPA</th>
				</tr>
				<!-- content continue of table -->
				<c:forEach items="${listgpa}" var="gpa">
					<tr>
						<td class="th">${gpa.milestoneName}</td>
						<td><fmt:formatNumber type="percent"
								value="${gpa.acacdemicMark}" /></td>
						<td><fmt:formatNumber type="percent"
								value="${gpa.disciplinaryPoint}" /></td>
						<td>${gpa.bonus *10}%</td>
						<td>${gpa.penalty *10}%</td>
						<td><fmt:formatNumber type="percent" value="${gpa.gpa}" /></td>
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
			<tbody id="tbody-commit" class="collapse show">
				<tr>
					<th>Committed Value</th>
					<th>Commited Working Duration</th>
					<th>Commited Working Start Date</th>
					<th>Commited Working End Date</th>
					<th>Remarks</th>
				</tr>
				<tr>
					<c:if test="${commit !=null}">
						<input type="hidden" id="commitId" value="${commit.id}" />
						<fmt:parseDate value="${commit.startDate}" pattern="yyyy-MM-dd"
							var="startDateCommit" type="date" />
						<fmt:formatDate value="${startDateCommit}"
							var="newStartDateCommit" type="date" pattern="dd/MM/yyyy" />
						<fmt:parseDate value="${commit.endDate}" pattern="yyyy-MM-dd"
							var="endDateCommit" type="date" />
						<fmt:formatDate value="${endDateCommit}" var="newEndDateCommit"
							type="date" pattern="dd/MM/yyyy" />
						<td>${commit.commitmentValue}</td>
						<td>${commit.workingDuration}</td>
						<td>${newStartDateCommit}</td>
						<td>${newEndDateCommit}</td>
						<td class="td-input"><input id="commitRemark" type="text"
							value="${commit.remark}" /></td>
					</c:if>
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
			<tbody id="tbody-allowance" class="collapse show">
				<tr>
					<th></th>
					<th>GPA</th>
					<th>Level</th>
					<th>Salary Paid</th>
					<th>Standard Allowance</th>
					<th>Remarks</th>
				</tr>

				<c:forEach items="${listallowance}" var="allowance">
					<tr class="allowance">
						<input type="hidden" class="allowanceId" value="${allowance.id}" />
						<td class="th mileName">${allowance.milestoneName}</td>
						<td><fmt:formatNumber type="percent" value="${allowance.gpa}" /></td>
						<td>${allowance.level}</td>
						<td>${allowance.salaryPaid}</td>
						<td>${allowance.standardAllowance}VNĐ</td>
						<td class="td-input"><input class="remarkAllowance"
							type="text" value="${allowance.remarks}" /></td>
					</tr>
					<c:set var="totalsalary"
						value="${totalsalary + allowance.standardAllowance}"></c:set>
				</c:forEach>
				<tr>
					<td class="th" colspan="4">Total</td>
					<fmt:formatNumber type="number" pattern="###,###"
						value="${totalsalary}" var="totalFormat" />
					<td colspan="2"><c:out value="${totalFormat}"></c:out></td>
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
			<tbody id="tbody-allocation" class="collapse show">
				<tr>
					<th>Allocated FSU</th>
					<th>Salary</th>
					<th>Start Date</th>
					<th>Allowcation Status</th>
					<th>Remarks</th>
				</tr>
				<tr>

					<td class="td-input"><input id="fsu" alloId="${allocate.id}"
						type="text" value="${allocate.allocatedFsu}" /></td>
					<td class="td-input"><input id="salaryAllocated" type="text"
						value="${allocate.salary}"></td>
					<fmt:parseDate value="${allocate.startDate}" pattern="yyyy-MM-dd"
						var="startDateAlloc" type="date" />
					<fmt:formatDate value="${startDateAlloc}" var="newStartDateAlloc"
						type="date" pattern="dd/MM/yyyy" />
					<td class="td-input"><input id="start-date-allocation"
						type="date" value="${newStartDateAlloc}" /></td>
					<td class="td-input"><select id="sel1" name="allocationStatus"
						allocation="${allocate.allocationStatus}">
							<option>Not allocated</option>
							<option>Allocated</option>
							<option>In processing</option>
							<option>Drop-out</option>
					</select></td>
					<td class="td-input"><input id="remarkAllocated" type="text"
						value="${allocate.remarks}" /></td>
				</tr>
			</tbody>
		</table>
		<button type="button" id="btn-submit" class="btn btn-secondary">Submit</button>
		&nbsp;
		<button class="btn btn-outline-dark" id="btn-close">
			<i class="fas fa-reply" style="color: red;"></i> Close
		</button>
	</form>
</div>

<!-- body end -->
<div class="modal fade" id="rejectClass" tabindex="-1" role="dialog"
	aria-labelledby="rejectClass1" aria-hidden="true">

	<div class="modal-dialog" role="document">

		<div class="modal-content">

			<div class="modal-header">

				<h5 class="modal-title" id="rejectClass1">Confirm</h5>

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">

					<span aria-hidden="true">&times;</span>

				</button>

			</div>

			<div class="modal-body">

				<label for="recipient-name" class="col-form-label">

					<h5>
						<i class="fas fa-info-circle green"></i> Are you sure to submit?
					</h5>

				</label>

			</div>

			<div class="modal-footer">

				<button type="button" id="btn-modal-submit" class="btn btn-primary">OK</button>
				<input type="hidden" id="idtrainee" value="${sessionScope.empId}">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>

			</div>

		</div>

	</div>

</div>


<script src='<c:url value="/resources/js/trainee-result.js"></c:url>'></script>
