<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href='<c:url value="/resources/css/trainee-profile-view-ajax.css"></c:url>'>
<h3 class="pt-3">Trainee profile</h3>
<h4  class="text-success">${message}</h4>
<!-- tabs -->
<ul class="nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link active" href="#" data-target="#traineeInformation" id="trainee-information">Trainee Information</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="#" data-target="#traineeResult" id="training-result">Training Result</a>
    </li>
</ul>

<!-- tab area -->
<div id="traineeInformation" class="container-fluid tab-pane active"><br>
    <form action="">
        <table class="table table-bordered">
            <tr>
                <td colspan="8" class="text-white" style="background-color:cornflowerblue;">Personal Information</td>
            </tr>
            <tr>
                <td class="traineeAttribute">Empl ID</td>
                <td id="empid" idtrainee="${trainee.id}" class="traineeValue">${trainee.traineeID}</td>
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

                <td class="traineeAttribute">Name</td>
                <td colspan="3" class="traineeValue">${trainee.name}</td>
            </tr>
            <tr>
                <td class="traineeAttribute">Gender</td>
                <c:if test="${trainee.gender ==true }"><td colspan="3" class="traineeValue">Male</td></c:if>
                <c:if test="${trainee.gender ==false}"><td colspan="3" class="traineeValue">Female</td></c:if>
                <td class="traineeAttribute">DOB</td>
                <td colspan="3" class="traineeValue">${trainee.dateOfBirth}</td>
            </tr>
            <tr>
                <td class="traineeAttribute">University</td>
                <td colspan="3" class="traineeValue">${trainee.university.universityName}</td>

                <td class="traineeAttribute">Faculty</td>
                <td colspan="3" class="traineeValue">${trainee.faculty.facultyName}</td>
            </tr>
            <tr>
                <td class="traineeAttribute">Phone</td>
                <td colspan="3" class="traineeValue">${trainee.phone}</td>

                <td class="traineeAttribute">Email</td>
                <td colspan="3" class="traineeValue">${trainee.email}</td>
            </tr>
            <tr>
                <td class="traineeAttribute">Salary Paid</td>
                <td colspan="3" class="traineeValue">Yes</td>
                <td class="traineeAttribute">TPB Account</td>
                <td colspan="3" class="traineeValue">${trainee.tPBAccount}</td>
            </tr>
            <tr>
                <td class="traineeAttribute">Allowance Group</td>
                <td colspan="3" class="traineeValue">${trainee.allowanceGroup}</td>

                <td class="traineeAttribute">Commitment</td>
                <td colspan="3" class="traineeValue">${trainee.commitment.workingDuration} months - ${trainee.commitment.startDate}</td>
            </tr>
            <tr>
                <td class="traineeAttribute">History</td>
                <td colspan="7" class="traineeValue">${trainee.history}</td>
            </tr>
        </table>
        <!-- update button -->
        <a href="#" id="update-link">
            <button type="button" class="btn btn-customized" id="update-btn">
                <i class="fa fa-arrow-circle-up mr-1" style="font: size 20px; color: cornflowerblue;"></i>Update Trainee
            </button>
        </a>
        <!-- update button ends -->
        <!-- delete button -->
        <button type="button" class="btn btn-customized" data-toggle="modal" data-target="#modalDelete"> <i
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
        <!-- delete modal ends -->
        <a href="#">
            <button type="button" class="btn btn-customized" id="close-btn">
                <i class="fas fa-reply mr-1" style="color: crimson;"></i>Close
            </button>
        </a>
    </form>
</div>
<script src='<c:url value="/resources/js/trainee-profile-view-ajax.js"></c:url>'></script>