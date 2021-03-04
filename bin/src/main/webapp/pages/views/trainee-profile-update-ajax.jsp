<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <link rel="stylesheet" href='<c:url value="/resources/css/trainee-profile-update-ajax.css"></c:url>'>
    <h3 class="pt-3">Trainee profile</h3>
	<h4 class="text-danger">${message}</h4>
    <!-- tabs -->
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link active" data-toggle="tab" href="#traineeInformation" id="trainee-information">Trainee
                Information</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#" data-target="#traineeResult"
                id="training-result">Training Result</a>
        </li>
    </ul>

    <!-- tab area -->
    <div id="traineeInformation" class="container-fluid tab-pane active"><br>
        <form id="informationUpdateForm">
            <table class="table table-bordered">
                <tr>
                    <td colspan="8" class="text-white" style="background-color:cornflowerblue;">Personal
                        Information</td>
                </tr>
                <tr>
                    <td class="traineeAttribute">Empl ID</td>
                    <td class="traineeValue" id="traineeId" empId="${trainee.id}">${trainee.traineeID}</td>
                    <td class="traineeAttribute">Type</td>
                    <td class="traineeValue">${trainee.type}</td>
                    <td class="traineeAttribute">Status</td>
                    <td class="traineeValue">${trainee.status}</td>
                    <td class="traineeAttribute">Allocation Status</td>
                    <td class="traineeValue">${trainee.allocation.allocationStatus}</td>
                </tr>
                <tr>
                    <td class="traineeAttribute">Account</td>
                    <td colspan="3" class="traineeValue">${trainee.account}</td>

                    <td class="traineeAttribute">Name (*)</td>
                    <td colspan="3" class="traineePadding">
                        <input type="text" class="td-input w-100" id="name" name="name" value="${trainee.name}">
                    </td>
                </tr>
                <tr>
                    <td class="traineeAttribute">Gender (*)</td>
                    <td colspan="3" class="">
                        <div class="form-check form-check-inline gender">
                            <input class="form-check-input" type="radio" name="gender" id="male" value="1" ${trainee.gender == true ? 'checked' : ''}>			
                            <label class="form-check-label" for="male">Male</label>
                        </div>
                        <div class="form-check form-check-inline gender">
                            <input class="form-check-input" type="radio" name="gender" id="female" value="0" ${trainee.gender == false ? 'checked' : ''}>
                            <label class="form-check-label" for="female">Female</label>
                        </div>
                    </td>
                    <td class="traineeAttribute">DOB (*)</td>
                    <td colspan="3" class="traineePadding">
                        <input type="date" class="td-input w-100" id="dateOfBirth" name="dateOfBirth" value="${trainee.dateOfBirth}">
                    </td>
                </tr>
                    <tr>
                        <td class="traineeAttribute">University</td>
                        <td colspan="3" class="traineePadding">
                            <div class="selectBox">
                                <select class="td-input w-100" name="university" id="university" >
                                    <option id="universityOption" value="${trainee.university.id}">${trainee.university.universityName}</option>
                                </select>
                                <div class="universitySelect"></div>
                            </div>
                            <div id="universityCheckboxes">
                            	<c:forEach items="${universityList}" var="university">
                            		<label for="university${university.id}">
                            			<input type="checkbox" class="checkBoxClass" id="university${university.id}" 
                            			name="universityName" value="${university.id}" ${trainee.university eq university ? 'checked' : ''}>
                            				${university.universityName}
                            		</label>
                            	</c:forEach>
                                <label for="universityOtherCheckBox">
                                    <input type="checkbox" class="checkBoxClass" name="universityName" id="universityOtherCheckBox" value="other"/>Other</label>
                                <input type="hidden" class="w-100" id="universityOtherInput" placeholder="Enter university name"/>
                            </div>
                        </td>
    
                        <td class="traineeAttribute">Faculty</td>
                        <td colspan="3" class="traineePadding">
                            <div class="selectBox">
                                <select class="td-input w-100" name="faculty" id="faculty">
                                    <option id="facultyOption" value="${trainee.faculty.id}">${trainee.faculty.facultyName}</option>
                                </select>
                                <div class="facultySelect"></div>
                            </div>
                            <div id="facultyCheckboxes">
                            <c:forEach items="${facultyList}" var="faculty">
                            	<label for="faculty${faculty.id}">
                            		<input type="checkbox" class="checkBoxClass" id="faculty${faculty.id}" 
                            		name="facultyName" value="${faculty.id}" ${trainee.faculty eq faculty ? 'checked' : '' }> 
                            			${faculty.facultyName}
                            	</label>
                            </c:forEach>
                                <label for="facultyOtherCheckBox">
                                    <input type="checkbox" class="checkBoxClass" name="facultyName" id="facultyOtherCheckBox" value="other"/>Other</label>
                                <input type="hidden" class="w-100" id="facultyOtherInput" placeholder="Enter faculty"/>
                            </div>
                        </td>
                    </tr>
                <tr>
                    <td class="traineeAttribute">Phone (*)</td>
                    <td colspan="3" class="traineePadding">
                        <input type="text" class="td-input w-100" id="phone" name="phone" value="${trainee.phone}">
                    </td>
                    <td class="traineeAttribute">Email (*)</td>
                    <td colspan="3" class="traineePadding">
                        <input type="text" class="td-input w-100" id="email" name="email" value="${trainee.email}">
                    </td>
                </tr>
                <tr>
                    <td class="traineeAttribute">Salary Paid (*)</td>
                    <td colspan="3" class="">
                        <div class="form-check form-check-inline salaried">
                            <input class="form-check-input " type="radio" name="salaried" id="salaryYes" value="1" ${trainee.salaryPaid == true ? 'checked' : ''}>
                            <label class="form-check-label" for="salaryYes">Yes</label>
                        </div>
                        <div class="form-check form-check-inline salaried">
                            <input class="form-check-input " type="radio" name="salaried" id="salaryNo" value="0" ${trainee.salaryPaid == false ? 'checked' :''}>
                            <label class="form-check-label" for="salaryNo">No</label>
                        </div>
                    </td>

                    <td class="traineeAttribute">TPB Account</td>
                    <td colspan="3" class="traineePadding">
                        <input type="text" class="td-input w-100" id="bankAccount" value="${trainee.tPBAccount}">
                    </td>
                </tr>
                <tr>
                    <td class="traineeAttribute">Allowance Group</td>
                    <td colspan="3" class="traineePadding">
                        <select class="td-input w-100" name="allowanceGroup" id="allowanceGroup">
                            <option value="devn">Dev-N</option>
                            <option value="devs">Dev-S</option>
                        </select>
                    </td>

                    <td class="traineeAttribute">Commitment</td>
                    <td colspan="3" class="traineeValue">6 months - 02/07/2018</td>
                </tr>
                <tr>
                    <td class="traineeAttribute">History</td>
                    <td colspan="7" class="traineeValue">${trainee.history}</td>
                </tr>
            </table>
            <!-- submit button -->
            <button type="button" class="btn btn-customized" id="btn-submit-update-profile">
            	<i class="far fa-check-circle mr-1" style="font: size 20px; color: cornflowerblue;"></i>Submit
            </button>
            <!-- submit button ends -->

            <!-- close button -->
            <a href="#">
                <button type="button" class="btn btn-customized" id="close-btn"><i
                        class="fas fa-reply mr-1" style="color: crimson;"></i>Close
                    </button>
            </a>
            <!-- close button ends -->
        </form>
    </div>
    <!-- body end -->
    <script src= '<c:url value="/resources/js/trainee-profile-update-ajax.js"></c:url>'></script>